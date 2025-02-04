package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.service.models.Sku;

@Service("utilService")
//@Scope("session")
public class UtilService implements Constants {

	private String BASE_PATH_CLOUD = "C:/springproject1/";
	@Autowired
	ResourceLoader resourceLoader;
	@Autowired
	JdbcTemplate jdbcTemplate;

	Map<String, String> mapList1;
	
	ArrayList<EstimateModel> estimateModelList = new ArrayList<EstimateModel>();
	HashMap<String, List<Sku>> cachedServicesMap = new HashMap<String, List<Sku>>();
	
	public HashMap<String, List<Sku>> getCachedServicesMap() {
		return cachedServicesMap;
	}

	public void setCachedServicesMap(HashMap<String, List<Sku>> cachedServicesMap) {
		this.cachedServicesMap = cachedServicesMap;
	}

	HashMap<String, ArrayList<EstimateModel>> userEstimateModelList = new HashMap<String, ArrayList<EstimateModel>>();
	
	public HashMap<String, ArrayList<EstimateModel>> getUserEstimateModelList() {
		return userEstimateModelList;
	}

	public void setUserEstimateModelList(HashMap<String, ArrayList<EstimateModel>> userEstimateModelList) {
		this.userEstimateModelList = userEstimateModelList;
	}

	public ArrayList<EstimateModel> getEstimateModelList() {
		return estimateModelList;
	}

	public void setEstimateModelList(ArrayList<EstimateModel> estimateModelList) {
		this.estimateModelList = estimateModelList;
	}

	public String getBASE_PATH_CLOUD() {
		return BASE_PATH_CLOUD;
	}

	public void setBASE_PATH_CLOUD(String bASE_PATH_CLOUD) {
		BASE_PATH_CLOUD = bASE_PATH_CLOUD;
	}

	/**
	 * public List<File> getAllJsonFiles() { String basePath =
	 * System.getProperty("user.dir"); String[] extensions = new String[] { "json"
	 * }; String targetLocation = basePath + "\\src\\main\\resources\\jsons\\";
	 * StringBuffer fileSB = new StringBuffer(); List<File> files = (List<File>)
	 * FileUtils.listFiles(new File(targetLocation), extensions, true); return
	 * files; }
	 */

	public ArrayList<String> getAllJsonFiles() {
		// Resource gg2 = resourceLoader.getResource("classpath:hello.txt");
		// InputStream inputStream = gg2.getInputStream();
		Resource[] resources = null;
		List<File> files = new ArrayList<File>();
		ArrayList<String> fileNameList = new ArrayList<String>();
		try {
			resources = ResourcePatternUtils.getResourcePatternResolver(resourceLoader)
					.getResources("classpath:*.json");
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Resource file : resources) {
		
					String fileName = file.getFilename().trim();
					//System.err.println("fileName--->" + fileName );
					fileNameList.add(fileName);
			
		}
		
		//System.err.println("fileNameList.get(0)->" + fileNameList.get(0) );
		//Resource resource = resourceLoader.getResource("classpath:" +fileNameList.get(0));
		
		//Resource resource = resourceLoader.getResource("classpath:hello.txt");
		//Resource resource = resourceLoader.getResource("classpath:F17B-412E-CB64.json");
		
/**		try{
	     	  InputStream is = resource.getInputStream();
	          BufferedReader br = new BufferedReader(new InputStreamReader(is));

	          String line;
	          while ((line = br.readLine()) != null) {
	             System.out.println(line);
	       	  }
	          br.close();

	    	}catch(IOException e){
	    		e.printStackTrace();
	    	}
*/			
	/**		for (String currentFileName : fileNameList) {
				Resource resource1 = resourceLoader.getResource(currentFileName);
				try {
					files.add(resource1.getFile());
				} catch (IOException e) {
					System.err.println("Exception ocurred - > " + currentFileName);
					e.printStackTrace();
				}
			}
	*/		

	
		return fileNameList;
	}

	public boolean isJSONFilePresent(String serviceId) {
		ArrayList<String> listOfFiles = getAllJsonFiles();
		String fileName;
		for (String file : listOfFiles) {
			fileName = StringUtils.substringBefore(file, ".");
			if (StringUtils.equalsIgnoreCase(fileName.trim(), serviceId.trim()))
				return true;
		}
		return false;
	}

	public void deleteAllJsonFiles() {
	/**	for (File file : getAllJsonFiles()) {
			try {
				FileUtils.deleteQuietly(file);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	*/
	}
	
	public Map<String, String> getRegionLocationMappingsMap() {
		if (!CollectionUtils.isEmpty(mapList1))
			return mapList1;

		String sql = "select * from RegionLocationMappings;";

		List<Map<String, Object>> regionLocationMappingsMap = jdbcTemplate.queryForList(sql);
		mapList1 = regionLocationMappingsMap.stream()
				.collect(Collectors.toMap(k -> (String) k.get("regionID"), k -> (String) k.get("locationName")));
		
		//System.err.println(mapList1);
		return mapList1;

	}

}
