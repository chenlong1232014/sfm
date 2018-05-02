/**
 * 
 */
package edu.fjnu.sfm.po;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author Administrator
 *
 */
@Entity
@Table(name="t_user")
public class UserPO {

	private Integer uid;
	private String name;
	private String account;
	private String password;
	private String sex;
	private Set<RolePO> roles;
	private String telephone;
	

	public UserPO() {
		roles = new HashSet<RolePO>();
	}
	@Id
	@GeneratedValue
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@ManyToMany(targetEntity=RolePO.class,cascade={CascadeType.PERSIST,CascadeType.REFRESH},fetch=FetchType.EAGER)
	@JoinTable(name="user_role",joinColumns={@JoinColumn(name="u_id")},inverseJoinColumns={@JoinColumn(name="r_id")})
	public Set<RolePO> getRoles() {
		return roles;
	}
	public void setRoles(Set<RolePO> roles) {
		this.roles = roles;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}

	
}
