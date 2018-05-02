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
@Table(name="t_role")
public class RolePO {

	private Integer rid;
	private String roleName;
	private Set<UserPO> users;
	private Set<ProcessNode> nodes;
	
	public RolePO() {
		users = new HashSet<UserPO>();
		nodes = new HashSet<ProcessNode>();
	}
	
	@Id
	@GeneratedValue
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@ManyToMany(targetEntity=UserPO.class,cascade={CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(name="user_role",joinColumns={@JoinColumn(name="r_id")},inverseJoinColumns={@JoinColumn(name="u_id")})
	public Set<UserPO> getUsers() {
		return users;
	}
	public void setUsers(Set<UserPO> users) {
		this.users = users;
	}
	@ManyToMany(targetEntity=ProcessNode.class,cascade={CascadeType.PERSIST,CascadeType.REFRESH},fetch=FetchType.EAGER)
	@JoinTable(name="node_role",joinColumns={@JoinColumn(name="r_id")},inverseJoinColumns={@JoinColumn(name="n_id")})
	public Set<ProcessNode> getNodes() {
		return nodes;
	}
	public void setNodes(Set<ProcessNode> nodes) {
		this.nodes = nodes;
	}
	
}
