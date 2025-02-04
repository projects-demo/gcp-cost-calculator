
package com.example.service.models;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "serviceDisplayName",
    "resourceFamily",
    "resourceGroup",
    "usageType"
})
public class Category {

    @JsonProperty("serviceDisplayName")
    private String serviceDisplayName;
    @JsonProperty("resourceFamily")
    private String resourceFamily;
    @JsonProperty("resourceGroup")
    private String resourceGroup;
    @JsonProperty("usageType")
    private String usageType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("serviceDisplayName")
    public String getServiceDisplayName() {
        return serviceDisplayName;
    }

    @JsonProperty("serviceDisplayName")
    public void setServiceDisplayName(String serviceDisplayName) {
        this.serviceDisplayName = serviceDisplayName;
    }

    public Category withServiceDisplayName(String serviceDisplayName) {
        this.serviceDisplayName = serviceDisplayName;
        return this;
    }

    @JsonProperty("resourceFamily")
    public String getResourceFamily() {
        return resourceFamily;
    }

    @JsonProperty("resourceFamily")
    public void setResourceFamily(String resourceFamily) {
        this.resourceFamily = resourceFamily;
    }

    public Category withResourceFamily(String resourceFamily) {
        this.resourceFamily = resourceFamily;
        return this;
    }

    @JsonProperty("resourceGroup")
    public String getResourceGroup() {
        return resourceGroup;
    }

    @JsonProperty("resourceGroup")
    public void setResourceGroup(String resourceGroup) {
        this.resourceGroup = resourceGroup;
    }

    public Category withResourceGroup(String resourceGroup) {
        this.resourceGroup = resourceGroup;
        return this;
    }

    @JsonProperty("usageType")
    public String getUsageType() {
        return usageType;
    }

    @JsonProperty("usageType")
    public void setUsageType(String usageType) {
        this.usageType = usageType;
    }

    public Category withUsageType(String usageType) {
        this.usageType = usageType;
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

    public Category withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Category.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("serviceDisplayName");
        sb.append('=');
        sb.append(((this.serviceDisplayName == null)?"<null>":this.serviceDisplayName));
        sb.append(',');
        sb.append("resourceFamily");
        sb.append('=');
        sb.append(((this.resourceFamily == null)?"<null>":this.resourceFamily));
        sb.append(',');
        sb.append("resourceGroup");
        sb.append('=');
        sb.append(((this.resourceGroup == null)?"<null>":this.resourceGroup));
        sb.append(',');
        sb.append("usageType");
        sb.append('=');
        sb.append(((this.usageType == null)?"<null>":this.usageType));
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
        result = ((result* 31)+((this.resourceGroup == null)? 0 :this.resourceGroup.hashCode()));
        result = ((result* 31)+((this.serviceDisplayName == null)? 0 :this.serviceDisplayName.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.resourceFamily == null)? 0 :this.resourceFamily.hashCode()));
        result = ((result* 31)+((this.usageType == null)? 0 :this.usageType.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Category) == false) {
            return false;
        }
        Category rhs = ((Category) other);
        return ((((((this.resourceGroup == rhs.resourceGroup)||((this.resourceGroup!= null)&&this.resourceGroup.equals(rhs.resourceGroup)))&&((this.serviceDisplayName == rhs.serviceDisplayName)||((this.serviceDisplayName!= null)&&this.serviceDisplayName.equals(rhs.serviceDisplayName))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.resourceFamily == rhs.resourceFamily)||((this.resourceFamily!= null)&&this.resourceFamily.equals(rhs.resourceFamily))))&&((this.usageType == rhs.usageType)||((this.usageType!= null)&&this.usageType.equals(rhs.usageType))));
    }

}
