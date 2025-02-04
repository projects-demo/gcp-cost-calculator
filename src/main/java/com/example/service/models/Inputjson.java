
package com.example.service.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "skus", "nextPageToken" })
public class Inputjson {

	@JsonProperty("skus")
	private List<Sku> skus = new ArrayList<Sku>();
	@JsonProperty("nextPageToken")
	private String nextPageToken;

	@JsonProperty("skus")
	public List<Sku> getSkus() {
		return skus;
	}

	@JsonProperty("skus")
	public void setSkus(List<Sku> skus) {
		this.skus = skus;
	}

	public Inputjson withSkus(List<Sku> skus) {
		this.skus = skus;
		return this;
	}

	@JsonProperty("nextPageToken")
	public String getNextPageToken() {
		return nextPageToken;
	}

	@JsonProperty("nextPageToken")
	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}

	public Inputjson withNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Inputjson.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
				.append('[');
		sb.append("skus");
		sb.append('=');
		sb.append(((this.skus == null) ? "<null>" : this.skus));
		sb.append(',');
		sb.append("nextPageToken");
		sb.append('=');
		sb.append(((this.nextPageToken == null) ? "<null>" : this.nextPageToken));
		sb.append(',');
		sb.append("additionalProperties");
		sb.append('=');
		// sb.append(((this.additionalProperties ==
		// null)?"<null>":this.additionalProperties));
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
		result = ((result * 31) + ((this.skus == null) ? 0 : this.skus.hashCode()));
		// result = ((result* 31)+((this.additionalProperties == null)? 0
		// :this.additionalProperties.hashCode()));
		result = ((result * 31) + ((this.nextPageToken == null) ? 0 : this.nextPageToken.hashCode()));
		return result;
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Inputjson) == false) {
			return false;
		}
		Inputjson rhs = ((Inputjson) other);
		return ((((this.skus == rhs.skus) || ((this.skus != null) && this.skus.equals(rhs.skus)))

				&& ((this.nextPageToken == rhs.nextPageToken)
						|| ((this.nextPageToken != null) && this.nextPageToken.equals(rhs.nextPageToken)))));
	}

}
