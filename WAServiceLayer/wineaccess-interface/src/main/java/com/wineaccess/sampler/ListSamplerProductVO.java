package com.wineaccess.sampler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement
@XmlType(name = "samplerProducts")
public class ListSamplerProductVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String samplerId;
	protected int offSet;
	protected int limit;
	protected int count;
	private List<ProductDetail> product = new ArrayList<ProductDetail>();
	public String getSamplerId() {
		return samplerId;
	}
	public void setSamplerId(String samplerId) {
		this.samplerId = samplerId;
	}
	public List<ProductDetail> getProduct() {
		return product;
	}
	public void setProduct(List<ProductDetail> product) {
		this.product = product;
	}
	public int getOffSet() {
		return offSet;
	}
	public void setOffSet(int offSet) {
		this.offSet = offSet;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

}
