/**
 * 
 */
package edu.fjnu.sfm.dao;


import java.util.List;

import edu.fjnu.sfm.po.ProcessLog;

/**
 * @author Administrator
 *
 */
public interface ProcessLogDAOI extends GenericDAOI<ProcessLog,Integer>{

	ProcessLog getLog(Integer bid,Integer count);
	ProcessLog getValLog(Integer bid,Integer count);
	List<ProcessLog> getAllLog(Integer uid);
	List<ProcessLog> getLog(Integer uid,Integer cp,Integer ps);
	List<ProcessLog> getAllSaLog(Integer uid,Boolean done);
	List<ProcessLog> getSaLog(Integer uid,Boolean done,Integer cp,Integer ps);
	List<ProcessLog> getSpfDoneLog (Integer uid,Integer pnid);
	List<ProcessLog> getPageLog(Integer cp,Integer ps);
	ProcessLog getLogBy(Integer pid);
	List<ProcessLog> getFindLogs(Integer pid,String startDate ,String endDate);
	List<ProcessLog> getFixLogs(Integer pid,String startDate ,String endDate);
 }
