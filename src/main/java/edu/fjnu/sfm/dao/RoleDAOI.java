/**
 * 
 */
package edu.fjnu.sfm.dao;

import java.util.List;

import edu.fjnu.sfm.po.RolePO;




/**
 * @author Administrator
 *
 */
public interface RoleDAOI extends GenericDAOI<RolePO,Integer>{

	List<RolePO> getRoles(Integer cp,Integer ps);
	List<RolePO> getRoleByUid(Integer uid);
}
