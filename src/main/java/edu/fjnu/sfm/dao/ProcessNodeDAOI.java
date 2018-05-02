/**
 * 
 */
package edu.fjnu.sfm.dao;


import java.util.List;

import edu.fjnu.sfm.po.ProcessNode;

/**
 * @author Administrator
 *
 */
public interface ProcessNodeDAOI extends GenericDAOI<ProcessNode,Integer>{

	List<ProcessNode> getNodes(Integer cp,Integer ps);
	List<ProcessNode> getAccessNode(Integer uid);
}
