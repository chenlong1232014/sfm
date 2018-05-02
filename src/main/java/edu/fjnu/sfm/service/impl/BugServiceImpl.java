/**
 * 
 */
package edu.fjnu.sfm.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.fjnu.sfm.dao.BugDAOI;
import edu.fjnu.sfm.dao.NodeFlowDAOI;
import edu.fjnu.sfm.dao.ProcessLogDAOI;
import edu.fjnu.sfm.dao.ProcessNodeDAOI;
import edu.fjnu.sfm.dao.UserDAOI;
import edu.fjnu.sfm.po.Bug;
import edu.fjnu.sfm.po.ProcessLog;
import edu.fjnu.sfm.po.ProcessNode;
import edu.fjnu.sfm.po.UserPO;
import edu.fjnu.sfm.service.BugServiceI;
import edu.fjnu.sfm.utils.ProcessType;

/**
 * @author Administrator
 * 
 */
@Service("bugService")
@Transactional
public class BugServiceImpl implements BugServiceI {

	@Resource
	private BugDAOI bugDAO;
	@Resource
	private ProcessLogDAOI processLogDAO;
	@Resource
	private ProcessNodeDAOI proceeNodeDAO;
	@Resource
	private NodeFlowDAOI nodeFlowDAO;
	@Resource
	private UserDAOI userDAO;

	public boolean addBug(Bug bug, String desc, UserPO user) {
		if (bugDAO.add(bug)) {
			bug = bugDAO.getCurrentBug();
			ProcessLog pl = new ProcessLog();
			ProcessNode currentNode = proceeNodeDAO
					.getObjById(ProcessType.ADD_BUG);
			pl.setNode(currentNode);
			pl.setLogDate(new Date());
			pl.setLogDesc(desc);
			pl.setBug(bug);
			pl.setDone(true);
			pl.setPassed(true);
			pl.setHandler(user);
			pl.setCount(bug.getCount());
			processLogDAO.add(pl);

			ProcessNode nextNode = nodeFlowDAO.getNextNode(currentNode);
			// System.out.println(nextNode.getNodeName());
			ProcessLog pl_next = new ProcessLog();
			pl_next.setBug(bug);
			pl_next.setDone(false);
			pl_next.setPassed(false);
			pl_next.setNode(nextNode);
			pl_next.setCount(bug.getCount());

			return processLogDAO.add(pl_next);
			
		}
		return false;
	}

	public boolean assignBug(Integer bid, Integer uid, UserPO handler,String desc) {
		Bug bug = bugDAO.getObjById(bid);
		UserPO user = userDAO.getObjById(uid);
		bug.setResolver(user);
		bugDAO.upt(bug);

		ProcessLog pl = processLogDAO.getLog(bid, bug.getCount());
		pl.setDone(true);
		pl.setHandler(handler);
		pl.setPassed(true);
		pl.setCount(bug.getCount());
		pl.setLogDate(new Date());
		pl.setLogDesc(desc);

		processLogDAO.upt(pl);

		ProcessNode nextNode = nodeFlowDAO.getNextNode(pl.getNode());
		ProcessLog pl_next = new ProcessLog();
		pl_next.setNode(nextNode);
		pl_next.setBug(bug);
		pl_next.setDone(false);
		pl_next.setPassed(false);
		pl_next.setCount(bug.getCount());
		return processLogDAO.add(pl_next);
	}

	public boolean existBug(boolean result, Integer bid, UserPO user,
			String desc) {
		Bug bug = bugDAO.getObjById(bid);
		ProcessLog pl = processLogDAO.getLog(bid, bug.getCount());
		if (result) {
			// 更新bug状态
			bug.setStatus("打开");
			bugDAO.upt(bug);

			// 更新当前节点
			pl.setDone(true);
			pl.setPassed(true);
			pl.setHandler(user);
			pl.setLogDesc(desc);
			pl.setLogDate(new Date());
			pl.setCount(bug.getCount());
			processLogDAO.upt(pl);

			// 创建下一节点日志
			ProcessNode nextNode = nodeFlowDAO.getNextNode(pl.getNode());
			ProcessLog pl_next = new ProcessLog();
			pl_next.setNode(nextNode);
			pl_next.setBug(bug);
			pl_next.setDone(false);
			pl_next.setPassed(false);
			pl_next.setCount(bug.getCount());

			return processLogDAO.add(pl_next);
		} else {
			bug.setStatus("关闭");
			bugDAO.upt(bug);

			pl.setDone(true);
			pl.setPassed(false);
			processLogDAO.upt(pl);

			return processLogDAO.upt(pl);
		}

	}

	public boolean fixBug(String desc, Integer bid, boolean result, UserPO user) {
		Bug bug = bugDAO.getObjById(bid);
		ProcessLog pl = processLogDAO.getLog(bid, bug.getCount());

		if (result) {

			bug.setStatus("修复");
			bugDAO.upt(bug);

			pl.setDone(true);
			pl.setPassed(true);
			pl.setHandler(user);
			pl.setLogDesc(desc);
			pl.setLogDate(new Date());
			processLogDAO.upt(pl);

			ProcessNode nextNode = nodeFlowDAO.getNextNode(pl.getNode());
			ProcessLog pl_next = new ProcessLog();
			pl_next.setNode(nextNode);
			pl_next.setBug(bug);
			pl_next.setDone(false);
			pl_next.setPassed(false);
			pl_next.setCount(bug.getCount());

			return processLogDAO.add(pl_next);
		} else {

			pl.setLogDesc(desc);
			pl.setLogDate(new Date());
			pl.setHandler(user);
			pl.setDone(true);
			pl.setPassed(false);
			processLogDAO.upt(pl);

			// 增加下一节点评审日志
			ProcessNode reflectNode = nodeFlowDAO.getReflectNode(pl.getNode());
			ProcessLog pl_reflect = new ProcessLog();
			pl_reflect.setNode(reflectNode);
			pl_reflect.setBug(bug);
			pl_reflect.setDone(false);
			pl_reflect.setPassed(false);
			pl_reflect.setCount(bug.getCount());

			return processLogDAO.add(pl_reflect);

		}
	}

	/**
	 * 验证缺陷是否修复的过程
	 * desc 修复过程描述
	 * bid 缺陷id
	 * result 修复是否成功
	 * user 处理人
	 */
	public boolean validateBug(String desc, Integer bid, boolean result,
			UserPO user) {
		Bug bug = bugDAO.getObjById(bid);
		ProcessLog pl = processLogDAO.getLog(bid, bug.getCount());

		if (result) {

			bug.setStatus("关闭");
			bugDAO.upt(bug);

			pl.setLogDesc(desc);
			pl.setLogDate(new Date());
			pl.setHandler(user);
			pl.setDone(true);
			pl.setPassed(true);
			return processLogDAO.upt(pl);

		} else {

			bug.setStatus("打开");
			bug.setCount(bug.getCount() + 1);
			bugDAO.upt(bug);

			pl.setLogDesc(desc);
			pl.setLogDate(new Date());
			pl.setHandler(user);
			pl.setDone(true);
			pl.setPassed(false);
			processLogDAO.upt(pl);

			// 增加下一节点修复日志
			ProcessNode reflectNode = nodeFlowDAO.getReflectNode(pl.getNode());
			ProcessLog pl_reflect = new ProcessLog();
			pl_reflect.setNode(reflectNode);
			pl_reflect.setBug(bug);
			pl_reflect.setDone(false);
			pl_reflect.setPassed(false);
			pl_reflect.setCount(bug.getCount());

			return processLogDAO.add(pl_reflect);
		}

	}

	public boolean appraisalBug(boolean result, Integer bid, UserPO user,String desc) {
		Bug bug = bugDAO.getObjById(bid);
		ProcessLog pl = processLogDAO.getLog(bid, bug.getCount());

		if (result) {

			bug.setStatus("重置");
			bugDAO.upt(bug);

			pl.setDone(true);
			pl.setPassed(true);
			pl.setHandler(user);
			pl.setLogDesc(desc);
			pl.setLogDate(new Date());
			return processLogDAO.upt(pl);

		} else {

			bug.setCount(bug.getCount() + 1);
			bugDAO.upt(bug);

			pl.setDone(true);
			pl.setPassed(false);
			processLogDAO.upt(pl);
			pl.setLogDesc(desc);
			pl.setLogDate(new Date());
			pl.setHandler(user);

			// 增加下一节点日志
			ProcessNode reflectNode = nodeFlowDAO.getReflectNode(pl.getNode());
			ProcessLog pl_reflect = new ProcessLog();
			pl_reflect.setNode(reflectNode);
			pl_reflect.setBug(bug);
			pl_reflect.setDone(false);
			pl_reflect.setPassed(false);
			pl_reflect.setCount(bug.getCount());

			return processLogDAO.add(pl_reflect);
		}
	}

	public List<Bug> getForExistBugs() {

		return bugDAO.getForExistBugs();
	}

	public List<Bug> getForAssignBugs() {

		return bugDAO.getForAssignBugs();
	}

	public Bug getBugById(Integer bid) {

		return bugDAO.getObjById(bid);
	}

	public List<Bug> getForFixBugs(Integer uid) {

		return bugDAO.getForFixBugs(uid);
	}

	public List<Bug> getForValBugs(Integer uid) {

		return bugDAO.getForValBugs(uid);
	}

	public List<Bug> getForAppraisalBugs() {

		return bugDAO.getForAppraisalBugs();
	}

	
	public List<Bug> getBugs(Integer cp,Integer ps) {
		
		return bugDAO.getBugs(cp, ps);
	}

	
	public boolean saveBug(Bug bug) {
		
		return bugDAO.upt(bug);
	}

	
	public boolean deleteBug(Map<Integer,Integer> ids) {
		
		for(Integer id:ids.keySet()){
			if(!bugDAO.del(id))
				return false;
		}
		return true;
	}

}
