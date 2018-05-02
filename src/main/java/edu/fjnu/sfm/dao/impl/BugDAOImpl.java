/**
 * 
 */
package edu.fjnu.sfm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.fjnu.sfm.dao.BugDAOI;
import edu.fjnu.sfm.po.Bug;
import edu.fjnu.sfm.utils.ProcessType;

/**
 * @author Administrator
 *
 */
@Repository("bugDAO")
public class BugDAOImpl extends GenericDAOImpl<Bug, Integer> implements BugDAOI {


	public Bug getCurrentBug() {
		String hql="from Bug bug where bug.bid=(select max(bid) from bug)";
		return getObjs(hql, -1, -1).get(0);
	}

	public List<Bug> getForExistBugs() {
		String hql = "select distinct bug from Bug bug,ProcessLog log where log.bug = bug and log.node.pnid = "+ProcessType.COMFIRM_BUG_EXIST+" and log.done = false";
		return getObjs(hql, -1, -1);
	}

	public List<Bug> getForAssignBugs() {
		String hql = "select distinct bug from Bug bug,ProcessLog log where log.bug = bug and log.node.pnid = "+ProcessType.ASSIGN_BUG+" and log.done = false";
		return getObjs(hql, -1, -1);
	}

	public List<Bug> getForFixBugs(Integer uid) {
		String hql = "select distinct bug from Bug bug,ProcessLog log where bug.resolver.uid = "+ uid +"and log.bug = bug and log.node.pnid = "+ProcessType.FIX_BUG+" and log.done = false";
		return getObjs(hql, -1, -1);
	}

	public List<Bug> getForValBugs(Integer uid) {
		String hql = "select distinct bug from Bug bug,ProcessLog log where bug.reporter.uid = "+ uid +"and log.bug = bug and log.node.pnid = "+ProcessType.VAL_BUG+" and log.done = false";
		return getObjs(hql, -1, -1);
	}

	public List<Bug> getForAppraisalBugs() {
		String hql = "select distinct bug from Bug bug,ProcessLog log where log.bug = bug and log.node.pnid = "+ProcessType.APPRAISAL_BUG+" and log.done = false";
		return getObjs(hql, -1, -1);
	}

	
	public List<Bug> getBugs(Integer cp, Integer ps) {
		String hql = "from Bug bug";
		return getObjs(hql, cp, ps);
	}


}
