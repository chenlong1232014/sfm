/**
 * 
 */
package edu.fjnu.sfm.po;

import javax.persistence.CascadeType;
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
@Table(name="t_node_flow")
public class NodeFlow {
	
	private Integer nfid;
	private ProcessNode currentNode;
	private ProcessNode nextNode;
	private ProcessNode reflectNode;
	
	@Id
	@GeneratedValue
	public Integer getNfid() {
		return nfid;
	}
	public void setNfid(Integer nfid) {
		this.nfid = nfid;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="c_pnid")
	public ProcessNode getCurrentNode() {
		return currentNode;
	}
	public void setCurrentNode(ProcessNode currentNode) {
		this.currentNode = currentNode;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="n_pnid")
	public ProcessNode getNextNode() {
		return nextNode;
	}
	public void setNextNode(ProcessNode nextNode) {
		this.nextNode = nextNode;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="r_pnid")
	public ProcessNode getReflectNode() {
		return reflectNode;
	}
	public void setReflectNode(ProcessNode reflectNode) {
		this.reflectNode = reflectNode;
	}

}
