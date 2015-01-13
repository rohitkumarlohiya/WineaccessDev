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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "STATE")
@EntityListeners(EntityListener.class)
@NamedQueries({
	@NamedQuery(name = "StateModel.getAll", query = "from StateModel"),
	@NamedQuery(name = "StateModel.getById", query = "from StateModel sm where sm.id = :stateId"),
	@NamedQuery(name = "StateModel.getState", query = "from StateModel s where s.country = :country"),
	@NamedQuery(name = "StateModel.getByCountryId", query = "from StateModel s where s.country.id = :countryId")
})
@Indexed
public class StateModel extends Persistent {

	private static final long serialVersionUID = 2239700425733281031L;

	@Id
	@Field(name = "id", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "ID", columnDefinition = "BIGINT(20) UNSIGNED")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Field(name = "stateName", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "NAME", columnDefinition = "VARCHAR(45) NULL")
	private String stateName;
	
	@Field(name = "stateNameSort", analyze = Analyze.NO, store = Store.NO)
	@Column(name = "NAME", insertable = false, updatable = false)
	private String stateNameSort;

	@Field(name = "stateCode", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "STATE_CODE", columnDefinition = "VARCHAR(45) NULL")
	private String stateCode;
	
	@Field(name = "stateCodeSort", analyze = Analyze.NO, store = Store.NO)
	@Column(name = "STATE_CODE", insertable = false, updatable = false)
	private String stateCodeSort;

	@ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="COUNTRY_ID")
    private CountryModel country;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "STATE_CITY", joinColumns = @JoinColumn(name = "STATE_ID"), inverseJoinColumns = @JoinColumn(name = "CITY_ID"))
    private Set<CityModel> cities = new HashSet<CityModel>();
	
	
	public StateModel(String stateName, String stateCode,
			CountryModel country) {
		super();
		this.stateName = stateName;
		this.stateCode = stateCode;
		this.country = country;
	}

	public StateModel() {
		super();
	}

	public StateModel(String stateName, String stateCode) {
		this.stateName = stateName;
		this.stateCode = stateCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public CountryModel getCountry() {
		return country;
	}

	public void setCountry(CountryModel country) {
		this.country = country;
	}

	public Set<CityModel> getCities() {
		return cities;
	}

	public void setCities(Set<CityModel> cities) {
		this.cities = cities;
	}

	public String getStateNameSort() {
		return stateNameSort;
	}

	public void setStateNameSort(String stateNameSort) {
		this.stateNameSort = stateNameSort;
	}

	public String getStateCodeSort() {
		return stateCodeSort;
	}

	public void setStateCodeSort(String stateCodeSort) {
		this.stateCodeSort = stateCodeSort;
	}
	
	
}