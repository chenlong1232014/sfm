/**
 * 
 */
package edu.fjnu.sfm.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.fjnu.sfm.po.Bug;
import edu.fjnu.sfm.po.UserPO;
/**
 * @author Administrator
 *
 */
public interface BugServiceI {
	boolean addBug(Bug bug,String desc,UserPO user);
	boolean assignBug(Integer bid,Integer uid,UserPO handler,String desc);
	boolean existBug(boolean result,Integer bid,UserPO user,String desc);
	boolean fixBug(String desc,Integer bid,boolean result,UserPO user);
	boolean validateBug(String desc,Integer bid,boolean result,UserPO user);
	boolean appraisalBug(boolean result,Integer bid,UserPO user,String desc);
	List<Bug> getForExistBugs();
	List<Bug> getForAssignBugs();
	List<Bug> getForFixBugs(Integer uid);
	List<Bug> getForValBugs(Integer uid);
	List<Bug> getForAppraisalBugs();
	Bug getBugById(Integer bid);
	List<Bug> getBugs(Integer cp,Integer ps);
	boolean saveBug(Bug bug);
	boolean deleteBug(Map<Integer,Integer> ids);
 }
