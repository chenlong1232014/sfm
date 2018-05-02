/**
 * 
 */
package edu.fjnu.sfm.po;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
@Table(name="t_node")
public class ProcessNode {
	
	private Integer pnid;
	private String nodeName;
	private Set<RolePO> roles;
	
	public ProcessNode() {
		roles = new HashSet<RolePO>();
	}

	@Id
	@GeneratedValue
	public Integer getPnid() {
		return pnid;
	}
	public void setPnid(Integer pnid) {
		this.pnid = pnid;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	@ManyToMany(targetEntity=RolePO.class,cascade={CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(name="node_role",joinColumns={@JoinColumn(name="n_id")},inverseJoinColumns={@JoinColumn(name="r_id")})
	public Set<RolePO> getRoles() {
		return roles;
	}
	public void setRoles(Set<RolePO> roles) {
		this.roles = roles;
	}


	
	

}
