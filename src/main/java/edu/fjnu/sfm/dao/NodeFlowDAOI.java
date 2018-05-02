/**
 * 
 */
package edu.fjnu.sfm.dao;


import edu.fjnu.sfm.po.NodeFlow;
import edu.fjnu.sfm.po.ProcessNode;

/**
 * @author Administrator
 *
 */
public interface NodeFlowDAOI extends GenericDAOI<NodeFlow,Integer>{

	ProcessNode getNextNode(ProcessNode currentNode);
	ProcessNode getReflectNode(ProcessNode currentNode);
}
