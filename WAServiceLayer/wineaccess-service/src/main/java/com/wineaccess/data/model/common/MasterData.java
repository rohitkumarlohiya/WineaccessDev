package com.wineaccess.data.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;

import com.wineaccess.data.model.MasterDataEntityListner;
import com.wineaccess.data.model.user.Persistent;

/**
 * 
 * @author rohit.lohiya
 * 
 */
@Entity
@Table(name = "MASTER_DATA")
@EntityListeners(MasterDataEntityListner.class)
@NamedQueries({
		@NamedQuery(name = "MasterData.getById", query = "from MasterData where id = :masterDataId"),
		@NamedQuery(name = "MasterData.getLastUpdated", query = "from MasterData where masterDataType = :masterDataType order by modifiedDate desc)"),
		@NamedQuery(name = "MasterData.getAll", query = "from MasterData"),
		@NamedQuery(name = "MasterData.getMasterData", query = "from MasterData where masterDataType.name = :masterDataTypeId and name=:name"),
		@NamedQuery(name = "MasterData.getMasterDataContainsKeyword", query = "from MasterData where masterDataType.name = :masterDataTypeId and name='Warehouse Address'")})
public class MasterData extends Persistent {

	private static final long serialVersionUID = 955583000268559882L;

	@Id
	@Column(name = "ID", columnDefinition = "BIGINT(20) UNSIGNED")
	@Field(name = "id", analyze = Analyze.YES, store = Store.NO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "MASTER_DATA_TYPE_ID", columnDefinition = "BIGINT(20) UNSIGNED NOT NULL")
	private MasterDataType masterDataType;

	@Field(name = "name", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "NAME", columnDefinition = "VARCHAR(255) NOT NULL", unique = true)
	private String name;

	@Field(name = "nameSort", analyze = Analyze.NO, store = Store.NO)
	@Column(name = "NAME", insertable = false, updatable = false)
	private String nameSort;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MasterDataType getMasterDataType() {
		return masterDataType;
	}

	public void setMasterDataType(MasterDataType masterDataType) {
		this.masterDataType = masterDataType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameSort() {
		return nameSort;
	}

	public void setNameSort(String nameSort) {
		this.nameSort = nameSort;
	}
}
