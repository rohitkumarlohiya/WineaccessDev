package com.wineaccess.command.search.sampler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.wineaccess.common.JsonDateSerializer;
import com.wineaccess.data.model.catalog.sampler.ProductSamplerModel;
import com.wineaccess.data.model.catalog.sampler.SamplerModel;
import com.wineaccess.wine.WineRepository;

public class SamplerModelVO implements Serializable {

	private static final long serialVersionUID = 5192717520590744666L;

	private Long id;
	
	private String name;
	
	private List<String> wineNames = new ArrayList<String>();;
	
	private double totalSrp;
	
	private double totalRevenue;
	
	private long activeOffer;
	
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date lastOfferDate;
	
	public SamplerModelVO(){
	}

	public SamplerModelVO(SamplerModel samplerModel) {
		this.id = samplerModel.getId();
		this.name = samplerModel.getName();
		if (samplerModel.getProductSampler() != null) {
			this.wineNames= new ArrayList<String>(samplerModel.getProductSampler().size());
			for (ProductSamplerModel productSamplerModel : samplerModel.getProductSampler()) {
				wineNames.add(WineRepository.getWineById(productSamplerModel.getProductId().getItemId()).getWineFullName() + productSamplerModel.getQuantity().getName() + " @ $"  + productSamplerModel.getSrpPrice());
			}
		}
		this.totalSrp = samplerModel.getTotalSrp();
		this.totalRevenue = samplerModel.getTotalRevenue();
		if(samplerModel.getActiveOffer() != null){
		this.activeOffer = samplerModel.getActiveOffer();
		}
		this.lastOfferDate = samplerModel.getLastOfferDate();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTotalSrp() {
		return totalSrp;
	}

	public void setTotalSrp(double totalSrp) {
		this.totalSrp = totalSrp;
	}

	public double getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public long getActiveOffer() {
		return activeOffer;
	}

	public void setActiveOffer(long activeOffer) {
		this.activeOffer = activeOffer;
	}

	public Date getLastOfferDate() {
		return lastOfferDate;
	}

	public void setLastOfferDate(Date lastOfferDate) {
		this.lastOfferDate = lastOfferDate;
	}

	public List<String> getWineNames() {
		return wineNames;
	}

	public void setWineNames(List<String> wineName) {
		this.wineNames = wineName;
	}
}
