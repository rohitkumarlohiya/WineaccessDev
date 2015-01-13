package com.wineaccess.data.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.wineaccess.data.model.EntityListener;

/**
 * @author jyoti.yadav@globallogic.com
 */
@Indexed
@Entity
@Table(name = "ROLE")
@EntityListeners(EntityListener.class)
@NamedQueries({
	@NamedQuery(name = "UserRoles.getByRoleId", query = "from UserRoles where id = :roleId"),
	@NamedQuery(name = "UserRoles.getByRoleName", query = "from UserRoles where roleName = :roleName"),
	@NamedQuery(name = "UserRoles.updateByRoleName", query = "Update UserRoles set roleName=:updatedRoleName where roleName = :roleName"),
	@NamedQuery(name = "UserRoles.updateByRoleId", query = "Update UserRoles set status=:status where id = :roleId"),
	@NamedQuery(name = "UserRoles.getAll", query = "from UserRoles"),
})
@NamedNativeQuery(name="UserRoles.getByUserId", query="select * from role a, user_role b where a.id = b.role_id and b.user_id = :userId", resultClass=UserRoles.class)
public class UserRoles extends Persistent {

	private static final long serialVersionUID = -7698726292416820367L;

	
	@Id
	@Column(name = "ID", columnDefinition = "BIGINT(20) UNSIGNED ")
	@Field(name = "id", analyze = Analyze.YES, store = Store.NO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ROLE_NAME", columnDefinition = "VARCHAR(45) NOT NULL")
	private String roleName;
	
	@Column(name = "STATUS", columnDefinition = "TINYINT(1) NOT NULL")
	private boolean status;
	
	public UserRoles(){
	}
	
	public UserRoles(String roleName, boolean status){
		this.roleName = roleName;
		this.status = status;
	}
	
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*public Set<UserModel> getUserModels() {
		return userModels;
	}*/

	/*public void setUserModels(Set<UserModel> userModels) {
		this.userModels = userModels;
	}*/
	
	
}
