package com.wineaccess.data.model.common;

import java.util.HashSet;
import java.util.Set;

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
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import com.wineaccess.data.model.EntityListener;
import com.wineaccess.data.model.user.Persistent;

/**
 * @author gaurav.agarwal1@globallogic.com
 */
@Entity
@Table(name = "MASTER_DATA_TYPE")
@EntityListeners(EntityListener.class)
@NamedQueries({
	@NamedQuery(name = "MasterDataType.getById", query = "from MasterDataType where id = :masterDataTypeId"),
	@NamedQuery(name = "MasterDataType.getAllByStatus", query = "from MasterDataType where status = :status"),
	@NamedQuery(name = "MasterDataType.getAll", query = "from MasterDataType"),
	@NamedQuery(name = "MasterDataType.getMasterDataByName", query = "from MasterDataType where name = :name")
})

public class MasterDataType extends Persistent {

	private static final long serialVersionUID = -6718732941107982172L;

	@Id
	@Column(name = "ID",columnDefinition = "BIGINT(20) UNSIGNED")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Field(name = "id", analyze = Analyze.YES, store = Store.YES, index=Index.YES)
	private Long id;

	@Field(name = "name", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "NAME", columnDefinition = "VARCHAR(255) NOT NULL")
	private String name;

	@Field(name = "description", analyze = Analyze.YES, store = Store.NO)
	@Column(name = "DESCRIPTION", columnDefinition = "VARCHAR(255) NULL")
	private String description;

	@Column(name = "STATUS", columnDefinition = "TINYINT(1) NOT NULL")
	private boolean status;
	
	@Column(name = "LABEL")
	private String label;

	@IndexedEmbedded
	@OneToMany(fetch = FetchType.EAGER , cascade = {}, mappedBy="masterDataType")
	Set<MasterData> masterData = new HashSet<MasterData>();
	
	public MasterDataType(){
	}
	
	public MasterDataType(String name, String description, boolean status, String label){
		this.name = name;
		this.description = description;
		this.status = status;
		this.label = label;
	}
	

	public Set<MasterData> getMasterData() {
		return masterData;
	}

	public void setMasterData(Set<MasterData> masterData) {
		this.masterData = masterData;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
