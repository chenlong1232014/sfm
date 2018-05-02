/**
 * 
 */
package edu.fjnu.sfm.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Administrator
 *
 */
@Entity
@Table(name="t_prj")
public class Project {

	private Integer pid;
	private String name;
	private String description;
	private String status;
	private Date startDate;
	private UserPO manager;
	
	@Id
	@GeneratedValue
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@OneToOne
	@JoinColumn(name="m_u_id")
	public UserPO getManager() {
		return manager;
	}
	public void setManager(UserPO manager) {
		this.manager = manager;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	
	
	
}
