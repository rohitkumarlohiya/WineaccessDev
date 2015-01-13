package com.wineaccess.data.model.user;


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

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.wineaccess.data.model.EntityListener;

	

/**
 * @author arpit.vijayvargiya@globallogic.com
 */
@Entity
@Table(name = "COUNTRY")
@EntityListeners(EntityListener.class)
@NamedQueries({
	@NamedQuery(name = "CountryModel.getAll", query = "from CountryModel"),
	@NamedQuery(name = "CountryModel.getById", query = "from CountryModel c where c.id = :countryId"),
	@NamedQuery(name = "CountryModel.getByCountryCode", query = "from CountryModel c where c.countryCode = :countryCode")
})
@Indexed
public class CountryModel extends Persistent {

	private static final long serialVersionUID = 2239700425733281031L;

	@Id
	@Column(name = "ID", columnDefinition = "BIGINT(20) UNSIGNED")
	@Field(name = "id", analyze = Analyze.YES, store = Store.NO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Field(name = "countryName", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "NAME", columnDefinition = "VARCHAR(45) NULL")
	private String countryName;
	
	@Field(name = "countryNameSort", analyze = Analyze.NO, store = Store.NO)
	@Column(name = "NAME", insertable = false, updatable = false)
	private String countryNameSort;
	
	@Field(name = "countryCode", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "COUNTRY_CODE", columnDefinition = "VARCHAR(45) NULL")
	private String countryCode;
	
	@Field(name = "countryCodeSort", analyze = Analyze.NO, store = Store.NO)
	@Column(name = "COUNTRY_CODE", insertable = false, updatable = false)
	private String countryCodeSort;

	@OneToMany(mappedBy="country", cascade={CascadeType.ALL},fetch = FetchType.EAGER)
	private Set<StateModel> state;

	
	public CountryModel(String countryName, String countryCode,
			Set<StateModel> state) {
		super();
		this.countryName = countryName;
		this.countryCode = countryCode;
		this.state = state;
	}

	public CountryModel() {
		super();
		state = new HashSet<StateModel>();
	}

	public CountryModel(String name, String code) {
		this();
		this.countryName = name;
		this.countryCode = code;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	public Set<StateModel> getState() {
		return state;
	}

	public void setState(Set<StateModel> state) {
		this.state = state;
	}

	public String getCountryNameSort() {
		return countryNameSort;
	}

	public void setCountryNameSort(String countryNameSort) {
		this.countryNameSort = countryNameSort;
	}
}

