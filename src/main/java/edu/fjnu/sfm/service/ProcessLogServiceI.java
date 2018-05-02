/**
 * 
 */
package edu.fjnu.sfm.service;

import java.util.List;
import java.util.Map;

import edu.fjnu.sfm.po.ProcessLog;

/**
 * @author Administrator
 *
 */
public interface ProcessLogServiceI {

	ProcessLog getValLog(Integer bid,Integer count);
	Integer getTotalLog(Integer uid);
	List<ProcessLog> getLog(Integer uid,Integer cp,Integer ps);
	Integer getTotalSaLog(Integer uid,Boolean done);
	List<ProcessLog> getSaLog(Integer uid,Boolean done,Integer cp,Integer ps);
	Map<Integer,Integer> getStcLog(Integer uid);
	List<ProcessLog> getPageLog(Integer cp,Integer ps);
	ProcessLog getLogById(Integer id);
	boolean saveLog(ProcessLog log);
	Integer getFindNumByDate(Integer pid,String startDate,String endDate);
	Integer getFixNumByDate(Integer pid,String startDate,String endDate);
}
