/**
 * 
 */
package edu.fjnu.sfm.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.fjnu.sfm.dao.ProcessNodeDAOI;
import edu.fjnu.sfm.dao.RoleDAOI;
import edu.fjnu.sfm.po.ProcessNode;
import edu.fjnu.sfm.po.RolePO;
import edu.fjnu.sfm.service.ProcessNodeServiceI;

/**
 * @author Administrator
 * 
 */
@Service("processNodeService")
@Transactional
public class ProcessNodeServiceImpl implements ProcessNodeServiceI {

	@Resource
	private ProcessNodeDAOI processNodeDAO;
	@Resource
	private RoleDAOI roleDAO;

	public boolean addProcessNode(ProcessNode ProcessNode) {
		return processNodeDAO.add(ProcessNode);
	}


	public boolean nodeGranted(Integer pnid, Set<Integer> roleIDs) {
		ProcessNode processNode = processNodeDAO.getObjById(pnid);

		Set<RolePO> roles = new HashSet<RolePO>();
		for (Integer roleID : roleIDs) {
			roles.add(roleDAO.getObjById(roleID));
			//roleDAO.getObjById(roleID).getNodes().add(processNode);
		}
		processNode.setRoles(roles);

		return processNodeDAO.upt(processNode);
	}


	
	public List<ProcessNode> getAllNodes() {
		
		return processNodeDAO.getNodes(-1, -1);
	}


	
	public Set<String> getPermissons(Integer uid) {
		
		List<ProcessNode> nodes = processNodeDAO.getAccessNode(uid);
		Set<String> nodes_ = new HashSet<String>();
		for(ProcessNode node : nodes){
			nodes_.add(node.getNodeName());
		}
		
		return nodes_;
	}

}
