/**
 * 
 */
package edu.fjnu.sfm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.fjnu.sfm.dao.ProcessLogDAOI;
import edu.fjnu.sfm.po.ProcessLog;
import edu.fjnu.sfm.service.ProcessLogServiceI;
import edu.fjnu.sfm.utils.ProcessType;

/**
 * @author Administrator
 *
 */
@Service("processLogService")
@Transactional
public class ProcessLogServiceImpl implements ProcessLogServiceI {

	@Resource
	ProcessLogDAOI processLogDAO;
	
	public ProcessLog getValLog(Integer bid, Integer count) {
		
		return processLogDAO.getValLog(bid, count);
	}

	public Integer getTotalLog(Integer uid) {
		
		return processLogDAO.getAllLog(uid).size();
	}

	public List<ProcessLog> getLog(Integer uid, Integer cp, Integer ps) {
		return processLogDAO.getLog(uid, cp, ps);
	}

	public Integer getTotalSaLog(Integer uid, Boolean done) {
		
		return processLogDAO.getAllSaLog(uid, done).size();
	}

	public List<ProcessLog> getSaLog(Integer uid, Boolean done, Integer cp,
			Integer ps) {
		
		return processLogDAO.getSaLog(uid, done, cp, ps);
	}

	public Map<Integer,Integer> getStcLog(Integer uid) {
		Map<Integer,Integer> map = new HashMap<Integer, Integer>();
		map.put(1, processLogDAO.getSpfDoneLog(uid, ProcessType.ADD_BUG).size());
		map.put(2, processLogDAO.getSpfDoneLog(uid, ProcessType.COMFIRM_BUG_EXIST).size());
		map.put(3, processLogDAO.getSpfDoneLog(uid, ProcessType.ASSIGN_BUG).size());
		map.put(4, processLogDAO.getSpfDoneLog(uid, ProcessType.FIX_BUG).size());
		map.put(5, processLogDAO.getSpfDoneLog(uid, ProcessType.COMFIRM_BUG_EXIST).size());
		map.put(6, processLogDAO.getSpfDoneLog(uid, ProcessType.VAL_BUG).size());
		return map;
	}

	
	public List<ProcessLog> getPageLog(Integer cp, Integer ps) {
		
		return processLogDAO.getPageLog(cp, ps);
	}

	
	public ProcessLog getLogById(Integer id) {
		
		return processLogDAO.getObjById(id);
	}

	
	public boolean saveLog(ProcessLog log) {
		
		return processLogDAO.upt(log);
	}

	
	public Integer getFindNumByDate(Integer pid,String startDate, String endDate) {
		
		
		return processLogDAO.getFindLogs(pid,startDate, endDate).size();
	}

	
	public Integer getFixNumByDate(Integer pid,String startDate, String endDate) {
	
		return processLogDAO.getFixLogs(pid,startDate, endDate).size();
	}

}
