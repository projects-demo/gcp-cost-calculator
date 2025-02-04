
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
    "aggregationLevel",
    "aggregationInterval",
    "aggregationCount"
})
public class AggregationInfo {

    @JsonProperty("aggregationLevel")
    private String aggregationLevel;
    @JsonProperty("aggregationInterval")
    private String aggregationInterval;
    @JsonProperty("aggregationCount")
    private Integer aggregationCount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("aggregationLevel")
    public String getAggregationLevel() {
        return aggregationLevel;
    }

    @JsonProperty("aggregationLevel")
    public void setAggregationLevel(String aggregationLevel) {
        this.aggregationLevel = aggregationLevel;
    }

    public AggregationInfo withAggregationLevel(String aggregationLevel) {
        this.aggregationLevel = aggregationLevel;
        return this;
    }

    @JsonProperty("aggregationInterval")
    public String getAggregationInterval() {
        return aggregationInterval;
    }

    @JsonProperty("aggregationInterval")
    public void setAggregationInterval(String aggregationInterval) {
        this.aggregationInterval = aggregationInterval;
    }

    public AggregationInfo withAggregationInterval(String aggregationInterval) {
        this.aggregationInterval = aggregationInterval;
        return this;
    }

    @JsonProperty("aggregationCount")
    public Integer getAggregationCount() {
        return aggregationCount;
    }

    @JsonProperty("aggregationCount")
    public void setAggregationCount(Integer aggregationCount) {
        this.aggregationCount = aggregationCount;
    }

    public AggregationInfo withAggregationCount(Integer aggregationCount) {
        this.aggregationCount = aggregationCount;
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

    public AggregationInfo withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(AggregationInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("aggregationLevel");
        sb.append('=');
        sb.append(((this.aggregationLevel == null)?"<null>":this.aggregationLevel));
        sb.append(',');
        sb.append("aggregationInterval");
        sb.append('=');
        sb.append(((this.aggregationInterval == null)?"<null>":this.aggregationInterval));
        sb.append(',');
        sb.append("aggregationCount");
        sb.append('=');
        sb.append(((this.aggregationCount == null)?"<null>":this.aggregationCount));
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
        result = ((result* 31)+((this.aggregationInterval == null)? 0 :this.aggregationInterval.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.aggregationLevel == null)? 0 :this.aggregationLevel.hashCode()));
        result = ((result* 31)+((this.aggregationCount == null)? 0 :this.aggregationCount.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AggregationInfo) == false) {
            return false;
        }
        AggregationInfo rhs = ((AggregationInfo) other);
        return (((((this.aggregationInterval == rhs.aggregationInterval)||((this.aggregationInterval!= null)&&this.aggregationInterval.equals(rhs.aggregationInterval)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.aggregationLevel == rhs.aggregationLevel)||((this.aggregationLevel!= null)&&this.aggregationLevel.equals(rhs.aggregationLevel))))&&((this.aggregationCount == rhs.aggregationCount)||((this.aggregationCount!= null)&&this.aggregationCount.equals(rhs.aggregationCount))));
    }

}
