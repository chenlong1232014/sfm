package edu.fjnu.sfm.service;

import java.util.List;
import java.util.Map;

import edu.fjnu.sfm.po.UserPO;

public interface UserServiceI {

	boolean addUser(UserPO user);
	boolean validateAccount(String account);
	boolean validatePassword(String account,String password);
	String getPasswordByAccount(String account);
	UserPO getUserByAccount(String account);
	Map<String, Boolean> getUserAccess(Integer uid);
	UserPO getUserById(Integer uid);
	List<UserPO> getDevelopers();
	boolean isManager(Integer uid);
	boolean pwdModify(String newPwd,Integer uid);
	List<UserPO> getUsers(Integer cp,Integer ps);
	Integer getUserNum();
	boolean grantUser(Integer uid,int[] ids);
	boolean saveUser(UserPO user);
	boolean deleteUser(int[] ids);
}
