
package com.example.models;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.persistence.Entity;
import javax.persistence.Id;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name", "serviceId", "displayName", "businessEntityName" })
@Entity
public class Service {

	@JsonProperty("name")
	private String name;
	@JsonProperty("serviceId")
	@Id
	private String serviceId;
	@JsonProperty("displayName")
	private String displayName;
	@JsonProperty("businessEntityName")
	private String businessEntityName;
//	@JsonIgnore
//	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	public Service withName(String name) {
		this.name = name;
		return this;
	}

	@JsonProperty("serviceId")
	public String getServiceId() {
		return serviceId;
	}

	@JsonProperty("serviceId")
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public Service withServiceId(String serviceId) {
		this.serviceId = serviceId;
		return this;
	}

	@JsonProperty("displayName")
	public String getDisplayName() {
		return displayName;
	}

	@JsonProperty("displayName")
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Service withDisplayName(String displayName) {
		this.displayName = displayName;
		return this;
	}

	@JsonProperty("businessEntityName")
	public String getBusinessEntityName() {
		return businessEntityName;
	}

	@JsonProperty("businessEntityName")
	public void setBusinessEntityName(String businessEntityName) {
		this.businessEntityName = businessEntityName;
	}

	public Service withBusinessEntityName(String businessEntityName) {
		this.businessEntityName = businessEntityName;
		return this;
	}

	/*
	 * @JsonAnyGetter public Map<String, Object> getAdditionalProperties() { return
	 * this.additionalProperties; }
	 * 
	 * @JsonAnySetter public void setAdditionalProperty(String name, Object value) {
	 * this.additionalProperties.put(name, value); }
	 * 
	 * public Service withAdditionalProperty(String name, Object value) {
	 * this.additionalProperties.put(name, value); return this; }
	 */

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Service.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
				.append('[');
		sb.append("name");
		sb.append('=');
		sb.append(((this.name == null) ? "<null>" : this.name));
		sb.append(',');
		sb.append("serviceId");
		sb.append('=');
		sb.append(((this.serviceId == null) ? "<null>" : this.serviceId));
		sb.append(',');
		sb.append("displayName");
		sb.append('=');
		sb.append(((this.displayName == null) ? "<null>" : this.displayName));
		sb.append(',');
		sb.append("businessEntityName");
		sb.append('=');
		sb.append(((this.businessEntityName == null) ? "<null>" : this.businessEntityName));
		sb.append(',');
		sb.append("additionalProperties");
		sb.append('=');
		//sb.append(((this.additionalProperties == null) ? "<null>" : this.additionalProperties));
		sb.append(',');
		if (sb.charAt((sb.length() - 1)) == ',') {
			sb.setCharAt((sb.length() - 1), ']');
		} else {
			sb.append(']');
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		int result = 1;
		result = ((result * 31) + ((this.name == null) ? 0 : this.name.hashCode()));
//		result = ((result * 31) + ((this.additionalProperties == null) ? 0 : this.additionalProperties.hashCode()));
		result = ((result * 31) + ((this.serviceId == null) ? 0 : this.serviceId.hashCode()));
		result = ((result * 31) + ((this.businessEntityName == null) ? 0 : this.businessEntityName.hashCode()));
		result = ((result * 31) + ((this.displayName == null) ? 0 : this.displayName.hashCode()));
		return result;
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Service) == false) {
			return false;
		}
		Service rhs = ((Service) other);
		return ((((((this.name == rhs.name) || ((this.name != null) && this.name.equals(rhs.name)))
				//&& ((this.additionalProperties == rhs.additionalProperties) || ((this.additionalProperties != null)
				//		&& this.additionalProperties.equals(rhs.additionalProperties))))
				&& ((this.serviceId == rhs.serviceId)
						|| ((this.serviceId != null) && this.serviceId.equals(rhs.serviceId))))
				&& ((this.businessEntityName == rhs.businessEntityName) || ((this.businessEntityName != null)
						&& this.businessEntityName.equals(rhs.businessEntityName))))
				&& ((this.displayName == rhs.displayName)
						|| ((this.displayName != null) && this.displayName.equals(rhs.displayName)))));
	}

}
