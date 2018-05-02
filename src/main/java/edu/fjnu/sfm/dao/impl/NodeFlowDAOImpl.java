/**
 * 
 */
package edu.fjnu.sfm.dao.impl;

import org.springframework.stereotype.Repository;

import edu.fjnu.sfm.dao.NodeFlowDAOI;
import edu.fjnu.sfm.po.NodeFlow;
import edu.fjnu.sfm.po.ProcessNode;

/**
 * @author Administrator
 *
 */
@Repository("nodeFlowDAO")
public class NodeFlowDAOImpl extends GenericDAOImpl<NodeFlow, Integer> implements NodeFlowDAOI {


	public ProcessNode getNextNode(ProcessNode currentNode) {
	//	System.out.println(currentNode.getPnid());
		String hql = "from NodeFlow nf where nf.currentNode.pnid = "+currentNode.getPnid();
		return getObjs(hql, -1, -1).get(0).getNextNode();
	}

	public ProcessNode getReflectNode(ProcessNode currentNode) {
		String hql = "from NodeFlow nf where nf.currentNode.pnid = "+currentNode.getPnid();
		return getObjs(hql, -1, -1).get(0).getReflectNode();
	}

}
