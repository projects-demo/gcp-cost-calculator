
package com.example.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.models.Service;

//http://localhost:8085/hello //http://localhost:8080/swagger-ui.html
//http://localhost:8080/hello //http://localhost:8081/Project5/get-vehicles

@Controller
public class AdminController {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	Json2Java json2Java;
	@Autowired
	UtilService utilService;

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String getAdmin() {
		return "admin.html";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public String postAdmin(@RequestParam String BASE_PATH_CLOUD, ModelMap model) {
		//System.err.println(BASE_PATH_CLOUD);
		utilService.setBASE_PATH_CLOUD(BASE_PATH_CLOUD);
		return "admin.html";

	}

	@RequestMapping(value = "/addToCacheService", method = RequestMethod.POST)
	//@ResponseBody
	public String addToCacheService(@RequestParam String serviceId) {
		json2Java.addToCacheService(serviceId);
		return "admin.html";
		// return simulateSearchResult1(searchTerm);
	}

	@RequestMapping(value = "/removeFromCacheService", method = RequestMethod.POST)
	//@ResponseBody
	public String removeFromCacheService(@RequestParam String serviceId) {
		json2Java.removeFromCacheService(serviceId);
		return "admin.html";
		// return simulateSearchResult1(searchTerm);
	}

	@RequestMapping(value = "/listCachedServices", method = RequestMethod.GET)
	public String listAllCachedServices(Model model) {

		Session session = sessionFactory.openSession();
		ArrayList<ServiceModel> listOfCachedServices = new ArrayList<ServiceModel>();
		Criteria crit;
		List<Service> service;
		String serviceId;
		int counter =1;

		StringBuffer fileSB = new StringBuffer();
		//ArrayList<String> fileNames = utilService.getAllJsonFiles();
		Set<String> fileNames = utilService.cachedServicesMap.keySet();
		for (String fileName : fileNames) {
			try {

				serviceId = fileName;// StringUtils.substringBefore(fileName, ".");
				crit = session.createCriteria(Service.class);
				crit.add(Restrictions.eq("serviceId", serviceId));
				service = crit.list();
				String displayName = service.get(0).getDisplayName();
				// fileSB.append(serviceId).append("(").append(service.get(0).getDisplayName()).append(")").append("\n");
				listOfCachedServices.add(new ServiceModel(""+counter++, serviceId, displayName));
			} catch (Exception e) {
				System.err.println("Error occurred during - "+ fileName);
			}
		}

		model.addAttribute("listOfCachedServices", listOfCachedServices);

		return "admin.html";
		// return simulateSearchResult1(searchTerm);
	}	
	
	@RequestMapping(value = "/runBatchJob", method = RequestMethod.GET)
	public String runBatchJob() {
		String serviceId;
		List<Service> services = json2Java.getGCPServices();
		for (Service service : services) {
			try {
				serviceId = service.getServiceId();
				if (!utilService.isJSONFilePresent(serviceId))
					json2Java.addToCacheService(serviceId);
			} catch (Exception e) {
				System.err.println("runBatchJob exception");
			}
		}

		return "admin.html";
		// return simulateSearchResult1(searchTerm);
	}
		
	@RequestMapping(value = "/deleteBatchJob", method = RequestMethod.GET)
	public String deleteBatchJob() {
		utilService.deleteAllJsonFiles();
		return "admin.html";
		// return simulateSearchResult1(searchTerm);
	}
}
