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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Administrator
 *
 *00
 */
@Entity
@Table(name="t_bug")
public class Bug {

	private Integer bid;
	private String title;
	private String status;
	private UserPO reporter;
	private UserPO resolver;
	private UserPO identifier;
	private Project prj;
	private Integer count;
	private Set<ProcessLog> logs;
	
	public Bug() {
		logs = new HashSet<ProcessLog>();
	}
	@Id
	@GeneratedValue
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@OneToOne
	@JoinColumn(name="r_u_id")
	public UserPO getReporter() {
		return reporter;
	}
	public void setReporter(UserPO reporter) {
		this.reporter = reporter;
	}
	@OneToOne
	@JoinColumn(name="rs_u_id")
	public UserPO getResolver() {
		return resolver;
	}
	public void setResolver(UserPO resolver) {
		this.resolver = resolver;
	}
	@OneToOne
	@JoinColumn(name="i_u_id")
	public UserPO getIdentifier() {
		return identifier;
	}
	public void setIdentifier(UserPO identifier) {
		this.identifier = identifier;
	}

	@OneToOne
	@JoinColumn(name="pid")
	public Project getPrj() {
		return prj;
	}
	public void setPrj(Project prj) {
		this.prj = prj;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	@OneToMany(mappedBy="bug",cascade={CascadeType.ALL})
	public Set<ProcessLog> getLogs() {
		return logs;
	}
	public void setLogs(Set<ProcessLog> logs) {
		this.logs = logs;
	}
	
	
	
	
}

