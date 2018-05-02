/**
 * 
 */
package edu.fjnu.sfm.po;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * @author Administrator
 *
 */
@Entity
@Table(name="t_log")
public class ProcessLog {
	/** 主键id */
	private Integer plid;
	/** 记录处理的bug */
	private Bug bug;
	/** 当前流程节点 */
	private ProcessNode node;
	/** 是否处理 */
	private boolean done;
	/** 是否通过 */
	private boolean passed;
	/** 测试轮数 */
	private Integer count;
	private String logDesc;
	private Date logDate;
	private UserPO handler;
	
	@Id
	@GeneratedValue
	public Integer getPlid() {
		return plid;
	}
	public void setPlid(Integer plid) {
		this.plid = plid;
	}
	@ManyToOne
	@JoinColumn(name="bid")
	public Bug getBug() {
		return bug;
	}
	public void setBug(Bug bug) {
		this.bug = bug;
	}
	@OneToOne
	@JoinColumn(name="pnid")
	public ProcessNode getNode() {
		return node;
	}
	public void setNode(ProcessNode node) {
		this.node = node;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public boolean isPassed() {
		return passed;
	}
	public void setPassed(boolean passed) {
		this.passed = passed;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	@ManyToOne
	@JoinColumn(name="h_u_id")
	public UserPO getHandler() {
		return handler;
	}
	public void setHandler(UserPO handler) {
		this.handler = handler;
	}
	public String getLogDesc() {
		return logDesc;
	}
	public void setLogDesc(String logDesc) {
		this.logDesc = logDesc;
	}
	public Date getLogDate() {
		return logDate;
	}
	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}
	
	
	
	

	
	
	
	
	

}
