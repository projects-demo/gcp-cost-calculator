package com.example.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.example.models.Inputjson;
import com.example.service.models.PricingInfo;
import com.example.service.models.Sku;
import com.example.service.models.TieredRate;
import com.example.service.models.UnitPrice;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("json2Java")
public class Json2Java implements Constants {
	@Autowired
	UtilService utilService;
	@Autowired
	ResourceLoader resourceLoader;
	
	List<com.example.models.Service> gcpService;

	HashSet<String> filterResourceFamily;
	HashSet<String> filterResourceGroup;
	HashMap<String, HashSet<String>> filterResourceGroupFamilyMap;

	HashSet<String> resourceSet;

	HashSet<String> regionsSet;

	public static void main(String[] args) {
		// new Json2Java().getGCPServices();
		// new Json2Java().getServiceDetails("");
		// new Json2Java().addToCacheService("F17B-412E-CB64");
		new Json2Java().addToCacheAllServices();

	}

	public void addToCacheAllServices() {
		// serviceId = "F17B-412E-CB64";
		RestTemplate restTemplate = new RestTemplate();
		String url = inputUrl;
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root;
		String basePath = utilService.getBASE_PATH_CLOUD();

		String targetLocation = basePath + "/src/main/resources/jsons/" + "ALLGCPSERVICES" + ".json";
		writeToFile(response.getBody(), targetLocation, false);
		System.err.println();

	}

	public List<com.example.models.Service> getGCPServices() {
		ObjectMapper objectMapper = new ObjectMapper();
		Inputjson services;
		List<com.example.models.Service> obj = null;
		if(!CollectionUtils.isEmpty(gcpService)) //return cached response
			return gcpService;
		
		try {
			// obj = objectMapper.readValue(new
			// File("C:\\Users\\Saurabh\\git\\code.examples\\code.examples\\inputjson.json"),
			// Inputjson.class);
			System.err.println(inputUrl);

			String basePath = utilService.getBASE_PATH_CLOUD();

			if (utilService.isJSONFilePresent(ALL_GCPSERVICES_FILE)) {
				String classPathFileName = "classpath:" + ALL_GCPSERVICES_FILE + ".json";
				Resource allGCPServicesFile = resourceLoader.getResource(classPathFileName);
				InputStream inputStream = allGCPServicesFile.getInputStream();

				services = objectMapper.readValue(inputStream, Inputjson.class);
				System.err.println("Cache found, reading JSON from class path");
			} else {
				services = objectMapper.readValue(new URL(inputUrl), Inputjson.class);
				System.err.println("Cache NOT found, hitting URL - " + inputUrl);

			}

			// Service[] obj= (Service[]) pojos.getAdditionalProperties().get("services");
			/*
			 * for (int i = 0; i < obj.length; i++) {
			 * System.err.println(obj[i].getDisplayName()); }
			 */
			obj = services.getServices();
			gcpService = obj;

			System.err.println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gcpService;
	}

	
	/**
	 * Get the SKU details for provided service
	 * 
	 * @param serviceId
	 * @param familyID
	 * @param regionID
	 * @param resourceID
	 * @return
	 */
	public List<Sku> getServiceDetailsFromCache(String serviceId, String[] filterResourceFamilyId,
			String filterRegionId, String[] resourceCandidatesForFamilyCheckbox) {
		if (StringUtils.isNotEmpty(filterRegionId))
			filterRegionId = StringUtils.substringBefore(filterRegionId, "(").trim();

		ObjectMapper objectMapper = new ObjectMapper();
		com.example.service.models.Inputjson serviceDetails;
		List<Sku> skuList = null;
		List<Sku> filteredSkuList = new ArrayList<Sku>();
		filterResourceFamily = new HashSet<String>();
		filterResourceGroup = new HashSet<String>();
		regionsSet = new HashSet<String>();
		filterResourceGroupFamilyMap = new HashMap<String, HashSet<String>>();


		try {
			String url = serviceUrl_base + serviceId + "/skus?key=" + API_KEY;

			if (utilService.isJSONFilePresent(serviceId)) {
				String classPathFileName = "classpath:" + serviceId + ".json";
				Resource fileResource = resourceLoader.getResource(classPathFileName);
				InputStream inputStream = fileResource.getInputStream();
				serviceDetails = objectMapper.readValue(inputStream, com.example.service.models.Inputjson.class);
				System.err.println("Cache found, reading JSON classpath - " + serviceId);
			} else {
				serviceDetails = objectMapper.readValue(new URL(url), com.example.service.models.Inputjson.class);
				System.err.println("Cache NOT found, hitting URL - " + url);

			}
			skuList = serviceDetails.getSkus();
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<String> regionsId = new ArrayList<String>();
		boolean isGlobalRegion, hasRegion, isFilterRegion, isFilterResourceFamily, isFilterResourceGroup;
		String family;
		HashSet<String> resourceSetForFamily;

		for (Sku sku : skuList) {
			family = sku.getCategory().getResourceFamily();
			filterResourceFamily.add(family);
			// filterResourceGroup.add(sku.getCategory().getResourceGroup());
			if (filterResourceGroupFamilyMap.containsKey(family))
				resourceSetForFamily = filterResourceGroupFamilyMap.get(family);
			else
				resourceSetForFamily = new HashSet<String>();

			resourceSetForFamily.add(sku.getCategory().getResourceGroup());
			
			filterResourceGroupFamilyMap.put(family, resourceSetForFamily);


			filterResourceFamily.add(sku.getCategory().getResourceFamily());
			filterResourceGroup.add(sku.getCategory().getResourceGroup());

			isGlobalRegion = false;
			isFilterRegion = false;
			isFilterResourceFamily = false;
			hasRegion = false;
			isFilterResourceGroup = false;
			try {
				if (!CollectionUtils.isEmpty(sku.getGeoTaxonomy().getRegions())) {
					appendLocationToRegionID(sku);
					regionsId = sku.getGeoTaxonomy().getRegions();
					// hasRegion = true;
				}
			} catch (Exception e) {
				// isGlobalRegion = true;
				regionsId.add(GLOBAL_REGION);
				//System.out.println("regions not available, its GLOBAL-------------->" + " sku -" + sku.getDescription());
			}

			if (StringUtils.isNotEmpty(filterRegionId)) {
				if (!regionsId.contains(filterRegionId)) {
					// System.out.println("Skipping results for region -> " + filterRegionId);
					continue;
				} else {
					isFilterRegion = true;
					 //System.out.println("region candidate -> " + filterRegionId);
				}
			}

			if (ArrayUtils.isNotEmpty(filterResourceFamilyId) && filterResourceFamilyId.length > 0) {
				if (!ArrayUtils.contains(filterResourceFamilyId, sku.getCategory().getResourceFamily())) {
					// System.out.println("Skipping results for filter -> " +
					// sku.getCategory().getResourceFamily());
					continue;
				} else {
					isFilterResourceFamily = true;
					 //System.out.println("filter family candidate -> " + sku.getCategory().getResourceFamily());
				}
			}
			
			if (ArrayUtils.isNotEmpty(resourceCandidatesForFamilyCheckbox) && resourceCandidatesForFamilyCheckbox.length > 0) {
				//System.err.println("current resource ->" + sku.getCategory().getResourceGroup());
				if(sku.getCategory().getResourceGroup().equalsIgnoreCase("LocalSSD") || sku.getCategory().getResourceGroup().equalsIgnoreCase("FlexInstancesCpu")){
					
					System.err.println("");
					
				}
				if (!ArrayUtils.contains(resourceCandidatesForFamilyCheckbox, sku.getCategory().getResourceGroup())) {
					System.err.println("current resource skipped ->" + sku.getCategory().getResourceGroup());
					continue;
				}
				else {
					isFilterResourceGroup = true;
					 System.out.println("filter resource candidate -> " + sku.getCategory().getResourceGroup());
				}
			}
			
			
			

			System.err.println("Added sku :: Region filter=" + isFilterRegion + " & ResourceFamilyFilter="
					+ isFilterResourceFamily + " & ResourceFamilyGroup=" + isFilterResourceGroup + " for SKU -> "
					+ sku.getDescription());
			filteredSkuList.add(sku);
		}

		// System.err.println(response);

		if (ArrayUtils.isNotEmpty(filterResourceFamilyId) || StringUtils.isNotBlank(filterRegionId)) {
			return filteredSkuList;
		} else
			return skuList;

	}

	private void appendLocationToRegionID(Sku sku) {
		Map<String, String> regionLocationMap = utilService.getRegionLocationMappingsMap();
		for (String region : sku.getGeoTaxonomy().getRegions()) {
			if (regionLocationMap.containsKey(region)) {
				region += " (" + regionLocationMap.get(region) + ")";
			}
			regionsSet.add(region);
		}
	}

	public List<Sku> getServiceDetails(String serviceId) {
		ObjectMapper objectMapper = new ObjectMapper();
		com.example.service.models.Inputjson serviceDetails;
		List<com.example.models.Service> obj = null;
		List<Sku> skuList = null;
		try {
			// public String serviceUrl =
			// "https://cloudbilling.googleapis.com/v1/services/F17B-412E-CB64/skus?key=AIzaSyCqJoF6E3lX5a7uT2jH3pPvTEV-47w49ek";
			String url = serviceUrl_base + serviceId + "/skus?key=" + API_KEY;
			System.err.println(url);
			serviceDetails = objectMapper.readValue(new URL(url), com.example.service.models.Inputjson.class);
			skuList = serviceDetails.getSkus();
			StringBuffer response = new StringBuffer();
			int pricingCounter = 1, skuCounter = 1;
			for (Sku sku : skuList) {
				response.append("SKU counter").append(skuCounter++).append("--->>>>>>>>>>>>>>>>>>>");
				response.append(sku.getSkuId()).append(" : ").append(sku.getDescription()).append("\n");
				for (PricingInfo pricingInfoforCurrentSku : sku.getPricingInfo()) {

					pricingCounter = 1;
					for (TieredRate pricingInfoforCurrenttieredRate : pricingInfoforCurrentSku.getPricingExpression()
							.getTieredRates()) {
						UnitPrice unitPrice = pricingInfoforCurrenttieredRate.getUnitPrice();
						response.append("price counter").append(pricingCounter++).append("---------------------->");
						response.append("StartUsageAmount ")
								.append(pricingInfoforCurrenttieredRate.getStartUsageAmount());
						response.append(unitPrice.getCurrencyCode()).append(" : ").append(unitPrice.getUnits())
								.append(" : ").append(unitPrice.getNanos());
						response.append("\n");
					}
					response.append("\n");
				}
				response.append("\n\n");

			}

			System.err.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return skuList;
	}

	public void addToCacheService(String serviceId) {
		String url = serviceUrl_base + serviceId + "/skus?key=" + API_KEY;
		// serviceId = "F17B-412E-CB64";
	/**	RestTemplate restTemplate = new RestTemplate();
		String url = serviceUrl_base + serviceId + "/skus?key=" + API_KEY;
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root;
		// String basePath = System.getProperty("user.dir");
		String basePath = utilService.getBASE_PATH_CLOUD();

		String targetLocation = basePath + "/src/main/resources/jsons/" + serviceId + ".json";
		*/
		
		ObjectMapper objectMapper = new ObjectMapper();
		com.example.service.models.Inputjson serviceDetails = null;
		
		try {
			serviceDetails = objectMapper.readValue(new URL(url), com.example.service.models.Inputjson.class);
			utilService.cachedServicesMap.put(serviceId, serviceDetails.getSkus());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		//writeToFile(response.getBody(), serviceId, false);
		System.err.println();

	}

	public void removeFromCacheService(String serviceId) {
		/**
		String basePath = utilService.getBASE_PATH_CLOUD();
		String targetLocation = basePath + "/src/main/resources/jsons/" + serviceId + ".json";
		try {
			FileUtils.forceDelete(new File(targetLocation));
			System.err.println("File deleted from cache - " + targetLocation);
		} catch (IOException e) {
			e.printStackTrace();
		}
*/
		
		try {
			utilService.cachedServicesMap.remove(serviceId);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		

	}

	private void writeToFile(String sourceString, String serviceId, boolean appendFlag) {
		File myFoo = null;
		try {
			myFoo = new File("./src/main/resources/" + serviceId + ".json");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		FileOutputStream fooStream;
		try {
			fooStream = new FileOutputStream(myFoo, appendFlag);
			byte[] myBytes = sourceString.getBytes();
			fooStream.write(myBytes);
			fooStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		} // true to append
			// false to overwrite.
	}

	public HashSet<String> getFilterResourceFamily() {
		return filterResourceFamily;
	}

	public void setFilterResourceFamily(HashSet<String> filterResourceFamily) {
		this.filterResourceFamily = filterResourceFamily;
	}

	public HashSet<String> getRegionsSet() {
		regionsSet.add(GLOBAL_REGION);
		return regionsSet;
	}

	public void setRegionsSet(HashSet<String> regionsSet) {
		this.regionsSet = regionsSet;
	}

	public HashSet<String> getFilterResourceGroup() {
		return filterResourceGroup;
	}

	public void setFilterResourceGroup(HashSet<String> filterResourceGroup) {
		this.filterResourceGroup = filterResourceGroup;
	}

	/**
	 * public void addToCacheService(String serviceId) { //serviceId =
	 * "F17B-412E-CB64"; RestTemplate restTemplate = new RestTemplate(); String url
	 * = serviceUrl_base + serviceId + "/skus?key=" + API_KEY;
	 * ResponseEntity<String> response = restTemplate.getForEntity(url,
	 * String.class); ObjectMapper mapper = new ObjectMapper(); JsonNode root;
	 * //String basePath = System.getProperty("user.dir"); String basePath =
	 * utilService.getBASE_PATH_CLOUD();
	 * 
	 * String targetLocation = basePath + "/src/main/resources/jsons/" + serviceId +
	 * ".json"; writeToFile(response.getBody(), targetLocation, false);
	 * System.err.println();
	 * 
	 * }
	 * 
	 * private void writeToFile(String sourceString, String targetLocation, boolean
	 * appendFlag) { File myFoo = new File(targetLocation); FileOutputStream
	 * fooStream; try { fooStream = new FileOutputStream(myFoo, appendFlag); byte[]
	 * myBytes = sourceString.getBytes(); fooStream.write(myBytes);
	 * fooStream.close();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } // true to append // false to
	 * overwrite. }
	 * 
	 * 
	 */
	
	public HashMap<String, HashSet<String>> getFilterResourceGroupFamilyMap() {
		return filterResourceGroupFamilyMap;
	}

	public void setFilterResourceGroupFamilyMap(HashMap<String, HashSet<String>> filterResourceGroupFamilyMap) {
		this.filterResourceGroupFamilyMap = filterResourceGroupFamilyMap;
	}

} 