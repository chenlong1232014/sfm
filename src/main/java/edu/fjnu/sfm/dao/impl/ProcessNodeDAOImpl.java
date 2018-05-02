/**
 * 
 */
package edu.fjnu.sfm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.fjnu.sfm.dao.ProcessNodeDAOI;
import edu.fjnu.sfm.po.ProcessNode;

/**
 * @author Administrator
 *
 */
@Repository("processNodeDAO")
public class ProcessNodeDAOImpl extends GenericDAOImpl<ProcessNode, Integer> implements ProcessNodeDAOI {

	public List<ProcessNode> getNodes(Integer cp,Integer ps) {
		String hql = "from ProcessNode node";
		return getObjs(hql, cp, ps);
	}

	public List<ProcessNode> getAccessNode(Integer uid) {
		String hql = "select node from ProcessNode node inner join node.roles role inner join role.users user where user.uid = "+ uid;
		return getObjs(hql, -1, -1);
	}

}
