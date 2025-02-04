
package com.example.service.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "skuId",
    "description",
    "category",
    "serviceRegions",
    "pricingInfo",
    "serviceProviderName",
    "geoTaxonomy"
})
public class Sku {

    @JsonProperty("name")
    private String name;
    @JsonProperty("skuId")
    private String skuId;
    @JsonProperty("description")
    private String description;
    @JsonProperty("category")
    private Category category;
    @JsonProperty("serviceRegions")
    private List<String> serviceRegions = new ArrayList<String>();
    @JsonProperty("pricingInfo")
    private List<PricingInfo> pricingInfo = new ArrayList<PricingInfo>();
    @JsonProperty("serviceProviderName")
    private String serviceProviderName;
    @JsonProperty("geoTaxonomy")
    private GeoTaxonomy geoTaxonomy;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    private String skuToString;

    public String getSkuToString() {
    	this.skuToString = toString();
		return skuToString;
	}
	
	@JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public Sku withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("skuId")
    public String getSkuId() {
        return skuId;
    }

    @JsonProperty("skuId")
    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public Sku withSkuId(String skuId) {
        this.skuId = skuId;
        return this;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Sku withDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("category")
    public Category getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(Category category) {
        this.category = category;
    }

    public Sku withCategory(Category category) {
        this.category = category;
        return this;
    }

    @JsonProperty("serviceRegions")
    public List<String> getServiceRegions() {
        return serviceRegions;
    }

    @JsonProperty("serviceRegions")
    public void setServiceRegions(List<String> serviceRegions) {
        this.serviceRegions = serviceRegions;
    }

    public Sku withServiceRegions(List<String> serviceRegions) {
        this.serviceRegions = serviceRegions;
        return this;
    }

    @JsonProperty("pricingInfo")
    public List<PricingInfo> getPricingInfo() {
        return pricingInfo;
    }

    @JsonProperty("pricingInfo")
    public void setPricingInfo(List<PricingInfo> pricingInfo) {
        this.pricingInfo = pricingInfo;
    }

    public Sku withPricingInfo(List<PricingInfo> pricingInfo) {
        this.pricingInfo = pricingInfo;
        return this;
    }

    @JsonProperty("serviceProviderName")
    public String getServiceProviderName() {
        return serviceProviderName;
    }

    @JsonProperty("serviceProviderName")
    public void setServiceProviderName(String serviceProviderName) {
        this.serviceProviderName = serviceProviderName;
    }

    public Sku withServiceProviderName(String serviceProviderName) {
        this.serviceProviderName = serviceProviderName;
        return this;
    }

    @JsonProperty("geoTaxonomy")
    public GeoTaxonomy getGeoTaxonomy() {
        return geoTaxonomy;
    }

    @JsonProperty("geoTaxonomy")
    public void setGeoTaxonomy(GeoTaxonomy geoTaxonomy) {
        this.geoTaxonomy = geoTaxonomy;
    }

    public Sku withGeoTaxonomy(GeoTaxonomy geoTaxonomy) {
        this.geoTaxonomy = geoTaxonomy;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Sku withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Sku.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("skuId");
        sb.append('=');
        sb.append(((this.skuId == null)?"<null>":this.skuId));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("category");
        sb.append('=');
        sb.append(((this.category == null)?"<null>":this.category));
        sb.append(',');
        sb.append("serviceRegions");
        sb.append('=');
        sb.append(((this.serviceRegions == null)?"<null>":this.serviceRegions));
        sb.append(',');
        sb.append("pricingInfo");
        sb.append('=');
        sb.append(((this.pricingInfo == null)?"<null>":this.pricingInfo));
        sb.append(',');
        sb.append("serviceProviderName");
        sb.append('=');
        sb.append(((this.serviceProviderName == null)?"<null>":this.serviceProviderName));
        sb.append(',');
        sb.append("geoTaxonomy");
        sb.append('=');
        sb.append(((this.geoTaxonomy == null)?"<null>":this.geoTaxonomy));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.serviceRegions == null)? 0 :this.serviceRegions.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.category == null)? 0 :this.category.hashCode()));
        result = ((result* 31)+((this.geoTaxonomy == null)? 0 :this.geoTaxonomy.hashCode()));
        result = ((result* 31)+((this.serviceProviderName == null)? 0 :this.serviceProviderName.hashCode()));
        result = ((result* 31)+((this.skuId == null)? 0 :this.skuId.hashCode()));
        result = ((result* 31)+((this.pricingInfo == null)? 0 :this.pricingInfo.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Sku) == false) {
            return false;
        }
        Sku rhs = ((Sku) other);
        return ((((((((((this.serviceRegions == rhs.serviceRegions)||((this.serviceRegions!= null)&&this.serviceRegions.equals(rhs.serviceRegions)))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.category == rhs.category)||((this.category!= null)&&this.category.equals(rhs.category))))&&((this.geoTaxonomy == rhs.geoTaxonomy)||((this.geoTaxonomy!= null)&&this.geoTaxonomy.equals(rhs.geoTaxonomy))))&&((this.serviceProviderName == rhs.serviceProviderName)||((this.serviceProviderName!= null)&&this.serviceProviderName.equals(rhs.serviceProviderName))))&&((this.skuId == rhs.skuId)||((this.skuId!= null)&&this.skuId.equals(rhs.skuId))))&&((this.pricingInfo == rhs.pricingInfo)||((this.pricingInfo!= null)&&this.pricingInfo.equals(rhs.pricingInfo))));
    }

}
