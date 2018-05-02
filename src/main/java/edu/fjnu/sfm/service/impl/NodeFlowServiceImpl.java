/**
 * 
 */
package edu.fjnu.sfm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.fjnu.sfm.dao.NodeFlowDAOI;
import edu.fjnu.sfm.po.ProcessNode;
import edu.fjnu.sfm.service.NodeFlowServiceI;

/**
 * @author Administrator
 *
 */
@Service("nodeFlowService")
@Transactional
public class NodeFlowServiceImpl implements NodeFlowServiceI {

	@Resource
	private NodeFlowDAOI nodeFlowDAO;
	
	public ProcessNode getNode(Boolean passed,ProcessNode currentNode) {
		
		return passed ? nodeFlowDAO.getNextNode(currentNode):nodeFlowDAO.getReflectNode(currentNode);
	}

}
