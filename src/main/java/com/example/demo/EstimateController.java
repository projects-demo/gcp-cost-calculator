
package com.example.demo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;

//http://localhost:8085/hello //http://localhost:8080/swagger-ui.html
//http://localhost:8080/hello //http://localhost:8081/Project5/get-vehicles

@Controller
public class EstimateController {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	Json2Java json2Java;
	@Autowired
	UtilService utilService;

	@RequestMapping(value = "/estimate", method = RequestMethod.GET)
	public String getEstimate(ModelMap model, HttpSession session) {
		System.err.println("Getting results for session - " + session.getId());

	/**	String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
		System.err.println("Getting results for session - " + sessionId);
		if (CollectionUtils.isEmpty(utilService.getUserEstimateModelList().get(sessionId)))
			return "estimate.html";

		float estimateCost = 0;
		for (EstimateModel skuEstimate : utilService.getUserEstimateModelList().get(sessionId)) {
			estimateCost += skuEstimate.getTotalCost();
		}
		model.addAttribute("estimateModelList", utilService.getUserEstimateModelList().get(sessionId));
		model.addAttribute("estimateCost", estimateCost);
*/

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		//long milliSeconds= Long.parseLong(x);
		//System.out.println(milliSeconds);

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(session.getLastAccessedTime());
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTimeInMillis(session.getCreationTime());

		
		
		//System.out.println(formatter.format(calendar.getTime())); 
		model.addAttribute("sessionId",session.getId());
		model.addAttribute("lastAccessedTime", calendar.getTime());
		model.addAttribute("creationTime",  calendar1.getTime());


		
		ArrayList<EstimateModel> estimateModelList1;

		if(session.getAttribute(Constants.ESTIMATE_KEY) == null) {
			return "estimate.html";

		}
		
		else{
			estimateModelList1 = (ArrayList<EstimateModel>) session.getAttribute(Constants.ESTIMATE_KEY);
			float estimateCost = 0;
			for (EstimateModel skuEstimate : estimateModelList1) {
				estimateCost += skuEstimate.getTotalCost();
			}
			model.addAttribute("estimateModelList", estimateModelList1);
			model.addAttribute("estimateCost", estimateCost);
		}

		
		return "estimate.html";
	}

	/*
	 * @RequestMapping(value = "/estimate", method = RequestMethod.POST) public
	 * String postEstimate(ModelMap model) {
	 * 
	 * utilService.getEstimateModelList();
	 * model.addAttribute("listOfCachedServices", listOfCachedServices);
	 * 
	 * return "estimate.html"; }
	 */

	@RequestMapping(value = "/clear-estimate", method = RequestMethod.GET)
	public String clearEstimate(HttpSession session) {
		//String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
		//HashMap<String, ArrayList<EstimateModel>> userEstimateModelList = utilService.getUserEstimateModelList();
		//userEstimateModelList.put(sessionId, new ArrayList<EstimateModel>());
		//utilService.setUserEstimateModelList(userEstimateModelList);
		if(session.getAttribute(Constants.ESTIMATE_KEY) != null) {
			session.setAttribute(Constants.ESTIMATE_KEY, null);
		}
		return "estimate.html";
	}

	@RequestMapping(value = "/add-estimate", method = RequestMethod.POST)

	public ResponseEntity postAddEstimate(@RequestParam String units, @RequestParam String sku, HttpSession session) {
		//System.err.println("sku for adding to estimate----------->" + sku);
		// redirAttrs.addFlashAttribute("message", "This is message from flash");
		String[] skuTokens = StringUtils.splitByWholeSeparator(sku, "</br>");
		EstimateModel estimateModel = new EstimateModel();
		ArrayList<PricingModel> pricingModelList = new ArrayList<PricingModel>();
		TreeMap<Float, Float> pricingModelMap = new TreeMap<Float, Float>();
		HashSet<String> startUsageAmountSet = new HashSet<String>();
		TreeSet<Integer> pricingSlabTreeSet = new TreeSet<Integer>();

		for (int i = 0; i < skuTokens.length; i++) {
			if (StringUtils.contains(skuTokens[i], "serviceName")) {
				estimateModel.setServiceName(StringUtils.substringAfter(skuTokens[i], ":").trim());
			}
			if (StringUtils.contains(skuTokens[i], "skuName")) {
				estimateModel.setSkuName(StringUtils.substringAfter(skuTokens[i], ":").trim());
			}
			if (StringUtils.contains(skuTokens[i], "startUsageAmount")) {
				PricingModel pricingModel = new PricingModel();

				String internalTokens[] = StringUtils.split(skuTokens[i], "~");
				for (String token : internalTokens) {
					String tokenVal = StringUtils.substringAfter(token, ":").trim();
					if (StringUtils.contains(token, "startUsageAmount")) {
						pricingModel.setStartUsageAmount(tokenVal);
						startUsageAmountSet.add(tokenVal);
					}
					if (StringUtils.contains(token, "currencyCode")) {
						pricingModel.setCurrencyCode(tokenVal);
					}
					if (StringUtils.contains(token, "finalPrice")) {
						// float totalPrice = Float.parseFloat(tokenVal) * Integer.parseInt(units);
						pricingModel.setFinalPrice(tokenVal + "");
					}
					if (StringUtils.contains(token, "displayQuantity")) {
						pricingModel.setDisplayQuantity(tokenVal);
					}
					if (StringUtils.contains(token, "usageUnit")) {

						pricingModel.setUsageUnit(tokenVal);
					}

				}
				pricingModelList.add(pricingModel);

			}

		}

		Float[] pricingSlabTreeArr = new Float[pricingModelList.size()];
		int k = 0;
		for (PricingModel pricingModel : pricingModelList) {
			float key = Float.parseFloat(pricingModel.getStartUsageAmount());
			float val = Float.parseFloat(pricingModel.getFinalPrice());
			pricingSlabTreeArr[k++] = key;
			pricingModelMap.put(key, val);

		}

		float cost = 0;
		int counter = 0;

		//int unitsPending = Integer.parseInt(units);
		for (int i = 0; i < pricingSlabTreeArr.length; i++) {

			if (pricingSlabTreeArr.length == 1) {
				cost = (Float.parseFloat(units) - pricingSlabTreeArr[i]) * pricingModelMap.get(pricingSlabTreeArr[i]);
				break;
			}

			if (i + 1 == pricingSlabTreeArr.length) {
				cost += (Float.parseFloat(units) - pricingSlabTreeArr[i]) * pricingModelMap.get(pricingSlabTreeArr[i]);
				break;
			}

			if ((Float.parseFloat(units) > pricingSlabTreeArr[i + 1])) {
				cost += (pricingSlabTreeArr[i + 1] - pricingSlabTreeArr[i])
						* pricingModelMap.get(pricingSlabTreeArr[i]);
			} else {
				cost += (Float.parseFloat(units) - pricingSlabTreeArr[i]) * pricingModelMap.get(pricingSlabTreeArr[i]);
				break;
			}

		}

		estimateModel.setTotalCost(cost);
		estimateModel.setUnitsRequested(units);
		estimateModel.setPricing(pricingModelList.get(0));

		// ArrayList<EstimateModel> estimateModelList =
		// utilService.getEstimateModelList();
		// estimateModelList.add(estimateModel);
		// utilService.setEstimateModelList(estimateModelList);

		//String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
		System.err.println("Setting results for session - " + session.getId());

		HashMap<String, ArrayList<EstimateModel>> userEstimateModelList = utilService.getUserEstimateModelList();
		ArrayList<EstimateModel> estimateModelList1;
/**	
		if (!CollectionUtils.isEmpty(userEstimateModelList.get(sessionId))) {
			estimateModelList1 = userEstimateModelList.get(sessionId);
			estimateModelList1.add(estimateModel);
		} else {
			estimateModelList1 = new ArrayList<EstimateModel>();
			estimateModelList1.add(estimateModel);
		}
		
		userEstimateModelList.put(sessionId, estimateModelList1);
		utilService.setUserEstimateModelList(userEstimateModelList);
*/
		
		if(session.getAttribute(Constants.ESTIMATE_KEY) != null) {
			estimateModelList1 = (ArrayList<EstimateModel>) session.getAttribute(Constants.ESTIMATE_KEY);
			estimateModelList1.add(estimateModel);
			session.setAttribute(Constants.ESTIMATE_KEY, estimateModelList1);
		}else {
			
			estimateModelList1 = new ArrayList<EstimateModel>();
			estimateModelList1.add(estimateModel);
			session.setAttribute(Constants.ESTIMATE_KEY, estimateModelList1);

		}
		
		
		
		
		
		
		
		System.err.println();
		// return " Success ";
		return new ResponseEntity(HttpStatus.OK);

	}

}
