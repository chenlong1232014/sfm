package edu.fjnu.sfm.service;

import java.util.List;
import java.util.Set;

import edu.fjnu.sfm.po.RolePO;

public interface RoleServiceI {

	boolean addRole(RolePO role);
	List<RolePO> getAllRoles();
	List<RolePO> getPageRoles(Integer cp,Integer ps);
	boolean saveRole(RolePO role);
	boolean grantRole(Integer rid,Set<Integer> nids);
	boolean deleteRole(Set<Integer> rids);
	Set<String> getRoleByUid(Integer uid);
}
