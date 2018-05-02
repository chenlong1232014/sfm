/**
 * 
 */
package edu.fjnu.sfm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.fjnu.sfm.dao.RoleDAOI;
import edu.fjnu.sfm.po.RolePO;

/**
 * @author Administrator
 *
 */
@Repository("roleDAO")
public class RoleDAOImpl extends GenericDAOImpl<RolePO, Integer> implements RoleDAOI {

	
	public List<RolePO> getRoles(Integer cp, Integer ps) {
		String hql = "from RolePO role";
		return getObjs(hql, cp, ps);
	}

	
	public List<RolePO> getRoleByUid(Integer uid) {
		String hql = "select role from RolePO role inner join role.users user where user.uid = "+uid;
		return getObjs(hql, -1, -1);
	}

}
