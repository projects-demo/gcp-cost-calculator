
package com.example.service.models;

import java.math.BigDecimal;
import java.math.BigInteger;
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
    "startUsageAmount",
    "unitPrice"
})
public class TieredRate {

    @JsonProperty("startUsageAmount")
    private BigDecimal startUsageAmount;
    @JsonProperty("unitPrice")
    private UnitPrice unitPrice;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("startUsageAmount")
    public BigDecimal getStartUsageAmount() {
        return startUsageAmount;
    }

    @JsonProperty("startUsageAmount")
    public void setStartUsageAmount(BigDecimal startUsageAmount) {
        this.startUsageAmount = startUsageAmount;
    }

    public TieredRate withStartUsageAmount(BigDecimal startUsageAmount) {
        this.startUsageAmount = startUsageAmount;
        return this;
    }

    @JsonProperty("unitPrice")
    public UnitPrice getUnitPrice() {
        return unitPrice;
    }

    @JsonProperty("unitPrice")
    public void setUnitPrice(UnitPrice unitPrice) {
        this.unitPrice = unitPrice;
    }

    public TieredRate withUnitPrice(UnitPrice unitPrice) {
        this.unitPrice = unitPrice;
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

    public TieredRate withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TieredRate.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("startUsageAmount");
        sb.append('=');
        sb.append(((this.startUsageAmount == null)?"<null>":this.startUsageAmount));
        sb.append(',');
        sb.append("unitPrice");
        sb.append('=');
        sb.append(((this.unitPrice == null)?"<null>":this.unitPrice));
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
        result = ((result* 31)+((this.unitPrice == null)? 0 :this.unitPrice.hashCode()));
        result = ((result* 31)+((this.startUsageAmount == null)? 0 :this.startUsageAmount.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TieredRate) == false) {
            return false;
        }
        TieredRate rhs = ((TieredRate) other);
        return ((((this.unitPrice == rhs.unitPrice)||((this.unitPrice!= null)&&this.unitPrice.equals(rhs.unitPrice)))&&((this.startUsageAmount == rhs.startUsageAmount)||((this.startUsageAmount!= null)&&this.startUsageAmount.equals(rhs.startUsageAmount))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
