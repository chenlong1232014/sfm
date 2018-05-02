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
import edu.fjnu.sfm.po.UserPO;
import edu.fjnu.sfm.service.RoleServiceI;

/**
 * @author Administrator
 *
 */
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleServiceI {

	@Resource
	private RoleDAOI roleDAO;
	@Resource
	private ProcessNodeDAOI processNode;
	
	public boolean addRole(RolePO role) {		
		return roleDAO.add(role);
	}


	public List<RolePO> getAllRoles() {
		
		return roleDAO.getRoles(-1, -1);
	}


	
	public List<RolePO> getPageRoles(Integer cp, Integer ps) {
		
		return roleDAO.getRoles(cp, ps);
	}


	
	public boolean saveRole(RolePO role) {
		
		return roleDAO.upt(role);
	}


	
	public boolean grantRole(Integer rid, Set<Integer> nids) {
		RolePO role = roleDAO.getObjById(rid);
		role.getNodes().clear();
		for(Integer id:nids){
			ProcessNode node = processNode.getObjById(id);
			role.getNodes().add(node);
		}
		return roleDAO.upt(role);

	}



	public boolean deleteRole(Set<Integer> rids) {
		for(Integer id:rids){
			if(!roleDAO.del(id))
				return false;
		}
		return true;
	}


	
	public Set<String> getRoleByUid(Integer uid) {
		List<RolePO> roles = roleDAO.getRoleByUid(uid);
		Set<String> roles_ = new HashSet<String>();
		for(RolePO role:roles){
			roles_.add(role.getRoleName());		
		}
		
		return roles_;
	}

}
