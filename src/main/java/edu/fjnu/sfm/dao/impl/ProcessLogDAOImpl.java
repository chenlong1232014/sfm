/**
 * 
 */
package edu.fjnu.sfm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.fjnu.sfm.dao.ProcessLogDAOI;
import edu.fjnu.sfm.po.ProcessLog;
import edu.fjnu.sfm.utils.ProcessType;

/**
 * @author Administrator
 *
 */
@Repository("processLogDAO")
public class ProcessLogDAOImpl extends GenericDAOImpl<ProcessLog, Integer> implements ProcessLogDAOI {


	public ProcessLog getLog(Integer bid,Integer count) {
		String hql = "from ProcessLog log where log.bug.bid ="+bid+" and log.count ="+count + " order by plid desc"; 
		return getObjs(hql, -1, -1).get(0);
	}

	public ProcessLog getValLog(Integer bid, Integer count) {
		String hql = "from ProcessLog log where log.bug.bid ="+bid+" and log.count ="+count + " and log.node.pnid = "+ ProcessType.FIX_BUG; 
		return getObjs(hql, -1, -1).get(0);
	}

	public List<ProcessLog> getAllLog(Integer uid) {
		String hql = "from ProcessLog log where log.handler.uid = "+uid;
		return getObjs(hql, -1, -1);
	}

	public List<ProcessLog> getLog(Integer uid, Integer cp, Integer ps) {
		String hql = "from ProcessLog log where log.handler.uid = "+uid;
		return getObjs(hql, cp, ps);
	}

	public List<ProcessLog> getAllSaLog(Integer uid, Boolean done) {
		String hql = "from ProcessLog log where log.handler.uid = "+uid+" and log.done = "+ done;
		return getObjs(hql, -1, -1);
	}

	public List<ProcessLog> getSaLog(Integer uid, Boolean done, Integer cp,
			Integer ps) {
		String hql = "from ProcessLog log where log.handler.uid = "+uid+" and log.done = "+ done;
		return getObjs(hql, cp, ps);
	}

	public List<ProcessLog> getSpfDoneLog(Integer uid, Integer pnid) {

		String hql = "from ProcessLog log where log.handler.uid =" +uid +"and log.done = true and log.node.pnid = "+pnid;
		return getObjs(hql, -1, -1);
	}

	
	public List<ProcessLog> getPageLog(Integer cp, Integer ps) {
		
		String hql = "from ProcessLog log where log.done = true";
		return getObjs(hql, cp, ps);
	}

	
	public ProcessLog getLogBy(Integer pid) {
		
		String hql = "from ProcessLog log where log.bug.pid =" + pid+" order by log.logDate desc"; 
		return getObjs(hql, -1, -1).get(0);
	}

	
	public List<ProcessLog> getFindLogs(Integer pid,String startDate, String endDate) {
		String hql = "from ProcessLog log where log.bug.prj.pid = "+pid+" and log.done = 1 and log.passed = 1 and log.node.pnid = "+ProcessType.COMFIRM_BUG_EXIST+" and log.logDate between '"+startDate+"' and '"+endDate+"'";
		return getObjs(hql, -1, -1);
	}

	
	public List<ProcessLog> getFixLogs(Integer pid,String startDate, String endDate) {
		String hql = "from ProcessLog log where log.bug.prj.pid = "+pid+" and log.done = 1 and log.passed = 1 and log.node.pnid = "+ProcessType.VAL_BUG+" and log.logDate between '"+startDate+"' and '"+endDate+"'";
		return getObjs(hql, -1, -1);
	}

}
