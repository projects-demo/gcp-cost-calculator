
package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.models.Service;
import com.example.service.models.Sku;

@Controller
public class HomeController implements Constants{

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	Json2Java json2Java;

	List<Sku> skuList = null; 
	
	private String currentServiceId;
	private String currentServiceName;



	/**
	 * Hibernate query Employee.Vehicle
	 * 
	 * @return
	 */
	//@PostMapping(path = "/set-service", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/set-service", method = RequestMethod.GET)
	@ResponseBody
	public String addService() {

		List<Service> services = json2Java.getGCPServices();
		System.err.println(services.get(0).getDisplayName());

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		for (Service service : services) {
			session.save(service);

		}
		session.getTransaction().commit();
		session.close();
		return services.size() + " gcp services saved....";
	}

	@RequestMapping(value = "/demo", method = RequestMethod.GET)
	public String demo(Model model) {
		return "demo.html";
	}
	
	@RequestMapping(value = "/get-services", method = RequestMethod.GET)
	public String getServices(Model model) {
		List<Service> services = json2Java.getGCPServices();
		ArrayList<ServiceModel> listOfAllServices = new ArrayList<ServiceModel>();
		int counter =1;

		for (Service service : services) {
			listOfAllServices.add(new ServiceModel(""+counter++, service.getServiceId(), service.getDisplayName()));
		}
			
		model.addAttribute("listOfAllServices", listOfAllServices);
		return "admin.html";
	}
	
	@RequestMapping(value = "/getSuggestions", method = RequestMethod.GET)
	public @ResponseBody List<Service> getTags(@RequestParam String searchTerm) {
		return simulateSearchResult(searchTerm);
	}

	@RequestMapping(value = "/getSuggestions1", method = RequestMethod.GET)
	public @ResponseBody List<Sku> getTags1(@RequestParam String searchTerm) {
		return simulateSearchResult1(searchTerm);
	}

	@GetMapping({ "/", "/search" })
	public ModelAndView getPages(HttpSession session) {
		System.err.println("Session ID homecontroller- " + session.getId());
		session.setMaxInactiveInterval(10*60);
		
		ModelAndView map = new ModelAndView("search");
		map.addObject("skuList", new ArrayList());
		map.addObject("regionsSet", new ArrayList());
		//System.err.println("GOOGLE_APPLICATION_CREDENTIALS property----------> " + System.getProperty("GOOGLE_APPLICATION_CREDENTIALS"));
		return map;
	}
	
	@PostMapping(path = "/search-service")
	@ResponseBody
	public ModelAndView searchService(@ModelAttribute ServiceModel serviceModel, ModelMap model) {

		//skuList = json2Java.getServiceDetails(serviceModel.getServiceId());
		currentServiceId = serviceModel.getServiceId();
		currentServiceName = serviceModel.getDisplayName();
		skuList = json2Java.getServiceDetailsFromCache(serviceModel.getServiceId(), null, null, null);
		ModelAndView map = new ModelAndView("search");

		HashMap<String, String> filterRegionMap = new HashMap<String, String>();

		filterRegionMap.put(DEFAULT_DROPDOWN_SELECTION, "true");

		for (String region : json2Java.getRegionsSet()) {
				filterRegionMap.put(region, "false");
		}

		ArrayList<FamilyModel> listOfFamilyModels = new ArrayList<FamilyModel>();
		FamilyModel currentFamilyModel ;
		if (json2Java.getFilterResourceGroupFamilyMap().size() > 0) {
			for (Entry<String, HashSet<String>> familyMap : json2Java.getFilterResourceGroupFamilyMap().entrySet()) {
				currentFamilyModel = new FamilyModel();
				currentFamilyModel.setFamilyName(familyMap.getKey());
				currentFamilyModel.setResources(familyMap.getValue());
				currentFamilyModel.setSelected(false);
				listOfFamilyModels.add(currentFamilyModel);
			}
		}
		
		if(listOfFamilyModels.size() > 0)
			map.addObject("listOfFamilyModels", listOfFamilyModels);

		map.addObject("filterRegionMap", filterRegionMap);
		map.addObject("currentServiceName", currentServiceName);
		map.addObject("skuList", skuList);

		return map;
	}

	@GetMapping({"/sku-list" })
	@ResponseBody
	public List<Sku> getSku(HttpSession session) {
		System.err.println("skuList....");
	  return skuList;
	}
	
	@PostMapping(path = "/filter-service")
	@ResponseBody
	public ModelAndView filterService(@RequestParam(value = "filtersMap", required = false) String[] filtersMap,
			@RequestParam(value = "resourceCandidatesForFamilyCheckbox", required = false) String[] resourceCandidatesForFamilyCheckbox,
			@RequestParam(value = "regionId", required = false) String regionId,
			@RequestParam(value = "regionDD", required = false) String regionDD, ModelMap model) {
		System.err.println("");
		HashMap<String, String> familyResourceFilterMap = new HashMap<String, String>();
		String regionDDFilter = regionDD;
		if(StringUtils.containsIgnoreCase(regionDD, DEFAULT_DROPDOWN_SELECTION))
			regionDDFilter = null;
		
		String key, val;
		if (!ArrayUtils.isEmpty(filtersMap)) {
			for (int i = 0; i < filtersMap.length; i++) {
				if (StringUtils.contains(filtersMap[i], ":")) {
					key = StringUtils.substringBefore(filtersMap[i], ":").trim();
					// val = StringUtils.substringAfter(filters[i], ",").trim();
					familyResourceFilterMap.put(key, "");
				}
			}
		}		
		
		String[] familyFilters = familyResourceFilterMap.keySet().toArray(new String[familyResourceFilterMap.size()]);
		skuList = json2Java.getServiceDetailsFromCache(currentServiceId, familyFilters, regionDDFilter, resourceCandidatesForFamilyCheckbox);

		ModelAndView map = new ModelAndView("search");
		
		HashMap<String, String> filterRegionMap = new HashMap<String, String>();
		ArrayList<FamilyModel> listOfFamilyModels = new ArrayList<FamilyModel>();
		FamilyModel currentFamilyModel ;

		if (json2Java.getFilterResourceGroupFamilyMap().size() > 0) {
			for (Entry<String, HashSet<String>> familyMap : json2Java.getFilterResourceGroupFamilyMap().entrySet()) {
				currentFamilyModel = new FamilyModel();
				currentFamilyModel.setFamilyName(familyMap.getKey());
				currentFamilyModel.setResources(familyMap.getValue());
				if (ArrayUtils.contains(familyFilters, familyMap.getKey()))
					currentFamilyModel.setSelected(true);
				else
					currentFamilyModel.setSelected(false);
				listOfFamilyModels.add(currentFamilyModel);

			}
		}
		
		if(listOfFamilyModels.size() > 0)
			map.addObject("listOfFamilyModels", listOfFamilyModels);

		if (regionDD.equalsIgnoreCase(DEFAULT_DROPDOWN_SELECTION))
			filterRegionMap.put(DEFAULT_DROPDOWN_SELECTION, "true");
		else 
			filterRegionMap.put(DEFAULT_DROPDOWN_SELECTION, "false");
		for (String region : json2Java.getRegionsSet()) {
			if (StringUtils.equalsIgnoreCase(region, regionDD))
				filterRegionMap.put(region, "true");
			else
				filterRegionMap.put(region, "false");
		}

		map.addObject("filterRegionMap", filterRegionMap);
		map.addObject("currentServiceName", currentServiceName);
		map.addObject("skuList", skuList);

		return map;
	}

	private List<Sku> simulateSearchResult1(String searchTerm) {
		List<Sku> result = new ArrayList<Sku>();
		for (Sku sku : skuList) {
			if (StringUtils.containsIgnoreCase(sku.getDescription(), searchTerm)) {
				result.add(sku);
			}
		}
		return result;
	}

	private List<Service> simulateSearchResult(String searchTerm) {
		List<Service> result = new ArrayList<Service>();
		List<Service> services = json2Java.getGCPServices();

		for (Service service : services) {
			if (StringUtils.containsIgnoreCase(service.getDisplayName(), searchTerm)) {
				result.add(service);
			}
		}
		return result;
	}

	
/**	
	private List<Service> simulateSearchResult(String searchTerm) {
		// List<Service> services = json2Java.getGCPServices();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// String hql = "SELECT serviceId, displayName FROM service WHERE displayName LIKE '%Bigquery%';";
		//SELECT * FROM service WHERE serviceId  LIKE '%0885%';
		String hql = "FROM service";
		Service service1 = session.get(Service.class, "0885-57C8-655B");
		System.err.println(service1.getDisplayName());
		String likeTagName = "%" + searchTerm + "%";
		Criteria crit = session.createCriteria(Service.class);
		crit.add(Restrictions.like("displayName", likeTagName, MatchMode.ANYWHERE));
		List<Service> results = crit.list();

		System.err.println();
		List<Service> result = new ArrayList<Service>();

		for (Service service : results) {
			if (StringUtils.containsIgnoreCase(service.getDisplayName(), searchTerm)) {
				result.add(service);
			}
		}

		session.getTransaction().commit();
		session.close();

		return result;
	}
*/
	public List<Sku> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<Sku> skuList) {
		this.skuList = skuList;
	}

	public String getCurrentServiceId() {
		return currentServiceId;
	}

	public void setCurrentServiceId(String currentServiceId) {
		this.currentServiceId = currentServiceId;
	}
	
	public String getCurrentServiceName() {
		return currentServiceName;
	}

	public void setCurrentServiceName(String currentServiceName) {
		this.currentServiceName = currentServiceName;
	}
  
} 
