
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
    "summary",
    "pricingExpression",
    "aggregationInfo",
    "currencyConversionRate",
    "effectiveTime"
})
public class PricingInfo {

    @JsonProperty("summary")
    private String summary;
    @JsonProperty("pricingExpression")
    private PricingExpression pricingExpression;
    @JsonProperty("aggregationInfo")
    private AggregationInfo aggregationInfo;
    @JsonProperty("currencyConversionRate")
    private Integer currencyConversionRate;
    @JsonProperty("effectiveTime")
    private String effectiveTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("summary")
    public String getSummary() {
        return summary;
    }

    @JsonProperty("summary")
    public void setSummary(String summary) {
        this.summary = summary;
    }

    public PricingInfo withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    @JsonProperty("pricingExpression")
    public PricingExpression getPricingExpression() {
        return pricingExpression;
    }

    @JsonProperty("pricingExpression")
    public void setPricingExpression(PricingExpression pricingExpression) {
        this.pricingExpression = pricingExpression;
    }

    public PricingInfo withPricingExpression(PricingExpression pricingExpression) {
        this.pricingExpression = pricingExpression;
        return this;
    }

    @JsonProperty("aggregationInfo")
    public AggregationInfo getAggregationInfo() {
        return aggregationInfo;
    }

    @JsonProperty("aggregationInfo")
    public void setAggregationInfo(AggregationInfo aggregationInfo) {
        this.aggregationInfo = aggregationInfo;
    }

    public PricingInfo withAggregationInfo(AggregationInfo aggregationInfo) {
        this.aggregationInfo = aggregationInfo;
        return this;
    }

    @JsonProperty("currencyConversionRate")
    public Integer getCurrencyConversionRate() {
        return currencyConversionRate;
    }

    @JsonProperty("currencyConversionRate")
    public void setCurrencyConversionRate(Integer currencyConversionRate) {
        this.currencyConversionRate = currencyConversionRate;
    }

    public PricingInfo withCurrencyConversionRate(Integer currencyConversionRate) {
        this.currencyConversionRate = currencyConversionRate;
        return this;
    }

    @JsonProperty("effectiveTime")
    public String getEffectiveTime() {
        return effectiveTime;
    }

    @JsonProperty("effectiveTime")
    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public PricingInfo withEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
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

    public PricingInfo withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PricingInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("summary");
        sb.append('=');
        sb.append(((this.summary == null)?"<null>":this.summary));
        sb.append(',');
        sb.append("pricingExpression");
        sb.append('=');
        sb.append(((this.pricingExpression == null)?"<null>":this.pricingExpression));
        sb.append(',');
        sb.append("aggregationInfo");
        sb.append('=');
        sb.append(((this.aggregationInfo == null)?"<null>":this.aggregationInfo));
        sb.append(',');
        sb.append("currencyConversionRate");
        sb.append('=');
        sb.append(((this.currencyConversionRate == null)?"<null>":this.currencyConversionRate));
        sb.append(',');
        sb.append("effectiveTime");
        sb.append('=');
        sb.append(((this.effectiveTime == null)?"<null>":this.effectiveTime));
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
        result = ((result* 31)+((this.summary == null)? 0 :this.summary.hashCode()));
        result = ((result* 31)+((this.pricingExpression == null)? 0 :this.pricingExpression.hashCode()));
        result = ((result* 31)+((this.effectiveTime == null)? 0 :this.effectiveTime.hashCode()));
        result = ((result* 31)+((this.aggregationInfo == null)? 0 :this.aggregationInfo.hashCode()));
        result = ((result* 31)+((this.currencyConversionRate == null)? 0 :this.currencyConversionRate.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PricingInfo) == false) {
            return false;
        }
        PricingInfo rhs = ((PricingInfo) other);
        return (((((((this.summary == rhs.summary)||((this.summary!= null)&&this.summary.equals(rhs.summary)))&&((this.pricingExpression == rhs.pricingExpression)||((this.pricingExpression!= null)&&this.pricingExpression.equals(rhs.pricingExpression))))&&((this.effectiveTime == rhs.effectiveTime)||((this.effectiveTime!= null)&&this.effectiveTime.equals(rhs.effectiveTime))))&&((this.aggregationInfo == rhs.aggregationInfo)||((this.aggregationInfo!= null)&&this.aggregationInfo.equals(rhs.aggregationInfo))))&&((this.currencyConversionRate == rhs.currencyConversionRate)||((this.currencyConversionRate!= null)&&this.currencyConversionRate.equals(rhs.currencyConversionRate))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
