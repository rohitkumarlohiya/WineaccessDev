package com.wineaccess.data.model.user;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQuery;

import com.wineaccess.data.model.EntityListener;

	

/**
 * @author arpit.vijayvargiya@globallogic.com
 */
@Entity
@Table(name = "CITY")
@EntityListeners(EntityListener.class)
@NamedQueries({
	@NamedQuery(name = "CityModel.getAll", query = "from CityModel"),
	@NamedQuery(name = "CityModel.getById", query = "from CityModel cm where cm.id = :cityId"),
	@NamedQuery(name = "CityModel.getByCityCode", query = "from CityModel cm where cm.cityCode = :cityCode")
})
@NamedNativeQuery(name="CityModel.getByStateId", query="select * from CITY city where city.ID in(select state.CITY_ID from STATE_CITY state where state.STATE_ID = :stateId)", resultClass=CityModel.class)
public class CityModel extends Persistent {

	private static final long serialVersionUID = 2239700425733281031L;

	@Id
	@Column(name = "ID", columnDefinition = "BIGINT(20) UNSIGNED")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NAME", columnDefinition = "VARCHAR(45) NULL")
	private String cityName;

	@Column(name = "CITY_CODE", columnDefinition = "VARCHAR(45) NULL")
	private String cityCode;

	@ManyToMany(mappedBy="cities")
    private Set<StateModel> states = new HashSet<StateModel>();
	
	public CityModel(String cityName, String cityCode) {
		super();
		this.cityName = cityName;
		this.cityCode = cityCode;
	}

	public CityModel() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public Set<StateModel> getStates() {
		return states;
	}

	public void setStates(Set<StateModel> states) {
		this.states = states;
	}
	
	@Override
	public int hashCode() {
		try {
			return this.cityCode.hashCode();
		} catch (Exception ex) {
		}
		return 0;
	}
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof CityModel && this.cityCode.equals(((CityModel)o).getCityCode()));
	}

}

