/**
 * 
 */
package edu.fjnu.sfm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.fjnu.sfm.dao.ProcessNodeDAOI;
import edu.fjnu.sfm.dao.RoleDAOI;
import edu.fjnu.sfm.dao.UserDAOI;
import edu.fjnu.sfm.po.ProcessNode;
import edu.fjnu.sfm.po.RolePO;
import edu.fjnu.sfm.po.UserPO;
import edu.fjnu.sfm.service.UserServiceI;

/**
 * @author Administrator
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserServiceI {

	@Resource
	private UserDAOI userDAO;
	@Resource
	private ProcessNodeDAOI processNodeDAO;
	@Resource
	private RoleDAOI roleDAOI;
	
	
	public boolean addUser(UserPO user) {		
		return userDAO.add(user);
	}

	public boolean validateAccount(String account) {
		
		return userDAO.getUserByAccount(account).size()>0?true:false;
	}

	public boolean validatePassword(String account,String password) {
		UserPO user = userDAO.getUserByAccount(account).get(0);
		return password.equals(user.getPassword())?true:false;
	}
	

	public UserPO getUserByAccount(String account) {
		
		return userDAO.getUserByAccount(account).get(0);
	}

	public Map<String, Boolean> getUserAccess(Integer uid) {
		Map<String, Boolean> map = new HashMap<String,Boolean>();
		List<ProcessNode> nodes = processNodeDAO.getNodes(-1,-1);
		for(ProcessNode node:nodes){
			map.put("pn"+node.getPnid(), false);
		}
		List<ProcessNode> accessNodes = processNodeDAO.getAccessNode(uid);
		for(ProcessNode anode:accessNodes){
			map.put("pn"+anode.getPnid(), true);
		}
		return map;
	}

	public UserPO getUserById(Integer uid) {
	
		return userDAO.getObjById(uid);
	}

	public List<UserPO> getDevelopers() {
	
		return userDAO.getDevelopers();
	}

	public boolean isManager(Integer uid) {
		
		return userDAO.getManager(uid).size()>0?true:false;
	}

	
	public boolean pwdModify(String newPwd, Integer uid) {
		UserPO user = userDAO.getObjById(uid);
		user.setPassword(newPwd);
		return userDAO.pwdModify(user);
	}

	public List<UserPO> getUsers(Integer cp,Integer ps) {
		
		return userDAO.getUsers(cp, ps);
	}


	public Integer getUserNum() {
	
		return userDAO.getUsers(-1, -1).size();
	}

	
	public boolean grantUser(Integer uid, int[] rids) {
		
		UserPO user = userDAO.getObjById(uid);
		user.getRoles().clear();
		for(Integer id:rids){
			RolePO role = roleDAOI.getObjById(id);
			user.getRoles().add(role);
		}
		return userDAO.upt(user);
	}

	
	public boolean saveUser(UserPO user) {
		
		return userDAO.upt(user);
	}

	
	public boolean deleteUser(int[] ids) {
		
		for(Integer id:ids){			
			if(!userDAO.del(id))
				return false;
		}
		
		return true;
	}

	
	public String getPasswordByAccount(String account) {
		
		return userDAO.getUserByAccount(account).get(0).getPassword();
	}


}
