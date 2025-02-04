
package com.example.service.models;

import java.math.BigInteger;
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
    "usageUnit",
    "usageUnitDescription",
    "baseUnit",
    "baseUnitDescription",
    "baseUnitConversionFactor",
    "displayQuantity",
    "tieredRates"
})
public class PricingExpression {

    @JsonProperty("usageUnit")
    private String usageUnit;
    @JsonProperty("usageUnitDescription")
    private String usageUnitDescription;
    @JsonProperty("baseUnit")
    private String baseUnit;
    @JsonProperty("baseUnitDescription")
    private String baseUnitDescription;
    @JsonProperty("baseUnitConversionFactor")
    private BigInteger baseUnitConversionFactor;
    @JsonProperty("displayQuantity")
    private Integer displayQuantity;
    @JsonProperty("tieredRates")
    private List<TieredRate> tieredRates = new ArrayList<TieredRate>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("usageUnit")
    public String getUsageUnit() {
        return usageUnit;
    }

    @JsonProperty("usageUnit")
    public void setUsageUnit(String usageUnit) {
        this.usageUnit = usageUnit;
    }

    public PricingExpression withUsageUnit(String usageUnit) {
        this.usageUnit = usageUnit;
        return this;
    }

    @JsonProperty("usageUnitDescription")
    public String getUsageUnitDescription() {
        return usageUnitDescription;
    }

    @JsonProperty("usageUnitDescription")
    public void setUsageUnitDescription(String usageUnitDescription) {
        this.usageUnitDescription = usageUnitDescription;
    }

    public PricingExpression withUsageUnitDescription(String usageUnitDescription) {
        this.usageUnitDescription = usageUnitDescription;
        return this;
    }

    @JsonProperty("baseUnit")
    public String getBaseUnit() {
        return baseUnit;
    }

    @JsonProperty("baseUnit")
    public void setBaseUnit(String baseUnit) {
        this.baseUnit = baseUnit;
    }

    public PricingExpression withBaseUnit(String baseUnit) {
        this.baseUnit = baseUnit;
        return this;
    }

    @JsonProperty("baseUnitDescription")
    public String getBaseUnitDescription() {
        return baseUnitDescription;
    }

    @JsonProperty("baseUnitDescription")
    public void setBaseUnitDescription(String baseUnitDescription) {
        this.baseUnitDescription = baseUnitDescription;
    }

    public PricingExpression withBaseUnitDescription(String baseUnitDescription) {
        this.baseUnitDescription = baseUnitDescription;
        return this;
    }

    @JsonProperty("baseUnitConversionFactor")
    public BigInteger getBaseUnitConversionFactor() {
        return baseUnitConversionFactor;
    }

    @JsonProperty("baseUnitConversionFactor")
    public void setBaseUnitConversionFactor(BigInteger baseUnitConversionFactor) {
        this.baseUnitConversionFactor = baseUnitConversionFactor;
    }

    public PricingExpression withBaseUnitConversionFactor(BigInteger baseUnitConversionFactor) {
        this.baseUnitConversionFactor = baseUnitConversionFactor;
        return this;
    }

    @JsonProperty("displayQuantity")
    public Integer getDisplayQuantity() {
        return displayQuantity;
    }

    @JsonProperty("displayQuantity")
    public void setDisplayQuantity(Integer displayQuantity) {
        this.displayQuantity = displayQuantity;
    }

    public PricingExpression withDisplayQuantity(Integer displayQuantity) {
        this.displayQuantity = displayQuantity;
        return this;
    }

    @JsonProperty("tieredRates")
    public List<TieredRate> getTieredRates() {
        return tieredRates;
    }

    @JsonProperty("tieredRates")
    public void setTieredRates(List<TieredRate> tieredRates) {
        this.tieredRates = tieredRates;
    }

    public PricingExpression withTieredRates(List<TieredRate> tieredRates) {
        this.tieredRates = tieredRates;
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

    public PricingExpression withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PricingExpression.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("usageUnit");
        sb.append('=');
        sb.append(((this.usageUnit == null)?"<null>":this.usageUnit));
        sb.append(',');
        sb.append("usageUnitDescription");
        sb.append('=');
        sb.append(((this.usageUnitDescription == null)?"<null>":this.usageUnitDescription));
        sb.append(',');
        sb.append("baseUnit");
        sb.append('=');
        sb.append(((this.baseUnit == null)?"<null>":this.baseUnit));
        sb.append(',');
        sb.append("baseUnitDescription");
        sb.append('=');
        sb.append(((this.baseUnitDescription == null)?"<null>":this.baseUnitDescription));
        sb.append(',');
        sb.append("baseUnitConversionFactor");
        sb.append('=');
        sb.append(((this.baseUnitConversionFactor == null)?"<null>":this.baseUnitConversionFactor));
        sb.append(',');
        sb.append("displayQuantity");
        sb.append('=');
        sb.append(((this.displayQuantity == null)?"<null>":this.displayQuantity));
        sb.append(',');
        sb.append("tieredRates");
        sb.append('=');
        sb.append(((this.tieredRates == null)?"<null>":this.tieredRates));
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
        result = ((result* 31)+((this.usageUnit == null)? 0 :this.usageUnit.hashCode()));
        result = ((result* 31)+((this.baseUnit == null)? 0 :this.baseUnit.hashCode()));
        result = ((result* 31)+((this.tieredRates == null)? 0 :this.tieredRates.hashCode()));
        result = ((result* 31)+((this.displayQuantity == null)? 0 :this.displayQuantity.hashCode()));
        result = ((result* 31)+((this.usageUnitDescription == null)? 0 :this.usageUnitDescription.hashCode()));
        result = ((result* 31)+((this.baseUnitDescription == null)? 0 :this.baseUnitDescription.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.baseUnitConversionFactor == null)? 0 :this.baseUnitConversionFactor.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PricingExpression) == false) {
            return false;
        }
        PricingExpression rhs = ((PricingExpression) other);
        return (((((((((this.usageUnit == rhs.usageUnit)||((this.usageUnit!= null)&&this.usageUnit.equals(rhs.usageUnit)))&&((this.baseUnit == rhs.baseUnit)||((this.baseUnit!= null)&&this.baseUnit.equals(rhs.baseUnit))))&&((this.tieredRates == rhs.tieredRates)||((this.tieredRates!= null)&&this.tieredRates.equals(rhs.tieredRates))))&&((this.displayQuantity == rhs.displayQuantity)||((this.displayQuantity!= null)&&this.displayQuantity.equals(rhs.displayQuantity))))&&((this.usageUnitDescription == rhs.usageUnitDescription)||((this.usageUnitDescription!= null)&&this.usageUnitDescription.equals(rhs.usageUnitDescription))))&&((this.baseUnitDescription == rhs.baseUnitDescription)||((this.baseUnitDescription!= null)&&this.baseUnitDescription.equals(rhs.baseUnitDescription))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.baseUnitConversionFactor == rhs.baseUnitConversionFactor)||((this.baseUnitConversionFactor!= null)&&this.baseUnitConversionFactor.equals(rhs.baseUnitConversionFactor))));
    }

}
