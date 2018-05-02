/**
 * 
 */
package edu.fjnu.sfm.dao;


import java.util.List;

import edu.fjnu.sfm.po.UserPO;

/**
 * @author Administrator
 *
 */
public interface UserDAOI extends GenericDAOI<UserPO,Integer>{

	List<UserPO> getUserByAccount(String account);
	List<UserPO> getDevelopers();
	List<UserPO> getManager(Integer uid);
	boolean pwdModify(UserPO user);
	List<UserPO> getUsers(Integer cp,Integer ps);
}
