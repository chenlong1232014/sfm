/**
 * 
 */
package edu.fjnu.sfm.dao;

import java.util.List;

import edu.fjnu.sfm.po.Bug;

/**
 * @author Administrator
 *
 */
public interface BugDAOI extends GenericDAOI<Bug, Integer>{

	Bug getCurrentBug();
	List<Bug> getForExistBugs();
	List<Bug> getForAssignBugs();
	List<Bug> getForFixBugs(Integer uid);
	List<Bug> getForValBugs(Integer uid);
	List<Bug> getForAppraisalBugs();
	List<Bug> getBugs(Integer cp,Integer ps);
	
}
