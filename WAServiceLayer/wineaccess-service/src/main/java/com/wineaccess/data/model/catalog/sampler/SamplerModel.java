package com.wineaccess.data.model.catalog.sampler;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.NumericField;
import org.hibernate.search.annotations.Store;

import com.wineaccess.data.model.EntityListener;
import com.wineaccess.data.model.user.Persistent;

/**
 * @author gaurav.agarwal1
 *
 */
@Entity
@Table(name="SAMPLER")
@EntityListeners(EntityListener.class)
@NamedQueries({@NamedQuery(name="SamplerModel.getByName",query="from SamplerModel where name = :name"),
               @NamedQuery(name="SamplerModel.getById",query="from SamplerModel where id = :id"),
               @NamedQuery(name="SamplerModel.getAll",query="from SamplerModel")
})
@Indexed
public class SamplerModel extends Persistent{

	private static final long serialVersionUID = -1336766983888973549L;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Field(name = "id", analyze = Analyze.YES, store = Store.NO)
	private Long id;

	@Fields( {
		@Field(name = "name", analyze = Analyze.YES, store = Store.NO),
		@Field(name = "nameSort", analyze = Analyze.NO, store = Store.NO)
	})
	@Column(name = "NAME")
	private String name;
	
	@Field(name = "activeOffer", analyze = Analyze.YES, store = Store.NO)
	@NumericField
	@Column(name = "ACTIVE_OFFER")
	private Long activeOffer = 0L;
	
	@Field(name = "lastOfferDate", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "LAST_OFFER_DATE")
	private Date lastOfferDate;
	
	@Field(name = "totalRevenue", analyze = Analyze.YES, store = Store.NO)
	@NumericField
	@Column(name = "TOTAL_REVENUE")
	private Double totalRevenue = 0.00;
	
	@Field(name = "totalSrp", analyze = Analyze.YES, store = Store.NO)
	@NumericField
	@Column(name = "TOTAL_SRP")
	private Double totalSrp = 0.00;
	
	@Field(name = "isEnabled", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "IS_ENABLED")
	private Boolean isEnabled;
	
	@IndexedEmbedded
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, mappedBy = "samplerId")
	private Set<ProductSamplerModel> productSampler = new HashSet<ProductSamplerModel>();

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

	public Long getActiveOffer() {
		return activeOffer;
	}

	public void setActiveOffer(Long activeOffer) {
		this.activeOffer = activeOffer;
	}

	public Date getLastOfferDate() {
		return lastOfferDate;
	}

	public void setLastOfferDate(Date lastOfferDate) {
		this.lastOfferDate = lastOfferDate;
	}

	public Double getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(Double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public Double getTotalSrp() {
		return totalSrp;
	}

	public void setTotalSrp(Double totalSrp) {
		this.totalSrp = totalSrp;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Set<ProductSamplerModel> getProductSampler() {
		return productSampler;
	}

	public void setProductSampler(Set<ProductSamplerModel> productSampler) {
		this.productSampler = productSampler;
	}

}
