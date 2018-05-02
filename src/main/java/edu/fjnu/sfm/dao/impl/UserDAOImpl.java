/**
 * 
 */
package edu.fjnu.sfm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.fjnu.sfm.dao.UserDAOI;
import edu.fjnu.sfm.po.UserPO;

/**
 * @author Administrator
 *
 */
@Repository("userDAO")
public class UserDAOImpl extends GenericDAOImpl<UserPO, Integer> implements UserDAOI {

	public List<UserPO> getUserByAccount(String account) {
		
		String hql = "from UserPO user where user.account =" + account;
		return getObjs(hql, -1, -1);
	}

	public List<UserPO> getDevelopers() {
		String hql = "select user from UserPO user inner join user.roles role where role.roleName = '开发人员' ";
		return getObjs(hql, -1, -1);
	}

	public List<UserPO> getManager(Integer uid) {
		String hql = "select user from UserPO user inner join user.roles role where role.roleName = '项目经理' and user.uid = "+uid;
		return getObjs(hql, -1, -1);
	}

	
	public boolean pwdModify(UserPO user) {
		return upt(user);
	}

	public List<UserPO> getUsers(Integer cp, Integer ps) {
		
		String hql = "from UserPO user";
		return getObjs(hql, cp, ps);
	}

}
