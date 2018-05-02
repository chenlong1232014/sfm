/**
 * 
 */
package edu.fjnu.sfm.action;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.fjnu.sfm.po.Bug;
import edu.fjnu.sfm.po.ProcessLog;
import edu.fjnu.sfm.po.Project;
import edu.fjnu.sfm.po.RolePO;
import edu.fjnu.sfm.po.UserPO;
import edu.fjnu.sfm.service.BugServiceI;
import edu.fjnu.sfm.service.ProcessLogServiceI;
import edu.fjnu.sfm.service.ProjectServiceI;
import edu.fjnu.sfm.service.UserServiceI;

/**
 * @author Administrator
 *
 */
@Controller
public class BugController {
	
	@Resource
	private BugServiceI bugService;
	@Resource
	private ProcessLogServiceI processLogService;
	@Resource
	private UserServiceI userService;
	@Resource
	private ProjectServiceI projectService;
	
	@RequiresPermissions(value="新建缺陷")
	@RequestMapping("/addBug")
	@ResponseBody
	public Map<String, String> addBug(Integer prj_id,String bugTitle,String bugDesc,HttpSession session)
	{
		Map<String, String> map = new HashMap<String, String>();
		UserPO user = userService.getUserByAccount((String)session.getAttribute("account"));
		Project prj = projectService.getPrjById(prj_id);
		Bug bug = new Bug();
		bug.setPrj(prj);
		bug.setReporter(user);
		bug.setTitle(bugTitle);
		bug.setCount(1);

		
		if(bugService.addBug(bug,bugDesc,user)){
			map.put("result", "1");
			map.put("message", "保存成功");
			return map;
		}
		else{
			map.put("result", "0");
			map.put("message", "保存失败");
			return map;
		}
	}
	@RequiresPermissions(value="确认缺陷是否存在")
	@RequestMapping("/getForExistBugs")
	public ModelAndView getExistBugs(){
		ModelAndView mv = new ModelAndView();
		List<Bug> bugs = bugService.getForExistBugs();
		mv.addObject("bugs",bugs);
		mv.setViewName("exist_bug");
		return mv;
	}
	@RequiresPermissions(value="确认缺陷是否存在")
	@RequestMapping("/existBug")
	@ResponseBody
	public Map<String, String> existBug(Integer bid,Integer result,HttpSession session,String desc){
		Map<String, String> map = new HashMap<String, String>();
		UserPO user = userService.getUserByAccount((String)session.getAttribute("account"));
		boolean r = false;
		if(result == 1){
			r = true;
		}
		if(bugService.existBug(r, bid,user,desc)){
			map.put("result", "1");
			map.put("message", "保存成功");
			return map;
		}
		else{
			map.put("result", "0");
			map.put("message", "保存失败");
			return map;
		}	
	}

	@RequiresPermissions(value="缺陷分配")
	@RequestMapping("/getForAssignBugs")
	public ModelAndView getAssignBugs(){
		ModelAndView mv = new ModelAndView();
		List<Bug> bugs = bugService.getForAssignBugs();
		List<UserPO> users = userService.getDevelopers();
		mv.addObject("bugs",bugs);
		mv.addObject("users",users);
		mv.setViewName("assign_bug");
		return mv;
	}
	@RequiresPermissions(value="缺陷分配")
	@RequestMapping("/assignBug")
	@ResponseBody
	public Map<String, String> assignBug(Integer bid,Integer uid,HttpSession session,String desc){
		Map<String, String> map = new HashMap<String, String>();
		UserPO user = userService.getUserByAccount((String)session.getAttribute("account"));
		if(bugService.assignBug(bid, uid,user,desc)){
			map.put("result", "1");
			map.put("message", "保存成功");
			return map;
		}
		else{
			map.put("result", "0");
			map.put("message", "保存失败");
			return map;
		}		
	}
	@RequiresPermissions(value="缺陷修复")
	@RequestMapping("/getForFixBugs")
	public ModelAndView getFixBugs(HttpSession session){
		ModelAndView mv = new ModelAndView();
		List<Bug> bugs = bugService.getForFixBugs((Integer)session.getAttribute("id"));
		mv.addObject("bugs",bugs);
		mv.setViewName("fix_bug");
		return mv;
	}
	@RequiresPermissions(value="缺陷修复")
	@RequestMapping("/fixBug")
	@ResponseBody
	public Map<String, String> fixBug(Integer bid,Integer result,String fixDesc,HttpSession session){
		Map<String, String> map = new HashMap<String, String>();
		UserPO user = userService.getUserByAccount((String)session.getAttribute("account"));
		boolean r = false;
		if(result == 1){
			r = true;
		}
		if(bugService.fixBug(fixDesc, bid, r,user)){
			map.put("result", "1");
			map.put("message", "保存成功");
			return map;
		}
		else{
			map.put("result", "0");
			map.put("message", "保存失败");
			return map;
		}	
	}
	@RequiresPermissions(value="确认缺陷是否修复")
	@RequestMapping("/getForValBugs")
	public ModelAndView getValBugs(HttpSession session){
		ModelAndView mv = new ModelAndView();
		List<Bug> bugs = bugService.getForValBugs((Integer)session.getAttribute("id"));
		mv.addObject("bugs",bugs);
		mv.setViewName("val_bug");
		return mv;
	}
	@RequiresPermissions(value="确认缺陷是否修复")
	@RequestMapping("/validateBug")
	@ResponseBody
	public Map<String, String> validateBug(Integer bid,Integer result,String valDesc,HttpSession session){
		Map<String, String> map = new HashMap<String, String>();
		UserPO user = userService.getUserByAccount((String)session.getAttribute("account"));
		boolean r = false;
		if(result == 1){
			r = true;
		}
		if(bugService.validateBug(valDesc, bid, r,user)){
			map.put("result", "1");
			map.put("message", "保存成功");
			return map;
		}
		else{
			map.put("result", "0");
			map.put("message", "保存失败");
			return map;
		}	
	}
	@RequiresPermissions(value="评审")
	@RequestMapping("/getBug")
	@ResponseBody
	public Map<String,String> getBug(Integer bid){
		Map<String,String> map = new HashMap<String,String>();
		Bug bug = bugService.getBugById(bid);
		ProcessLog log = processLogService.getValLog(bid, bug.getCount());
		map.put("valDesc",log.getLogDesc());
		map.put("project",bug.getPrj().getName());
		return map;		
	}
	@RequiresPermissions(value="评审")
	@RequestMapping("/getForAplBugs")
	public ModelAndView getAplBugs(){
		ModelAndView mv = new ModelAndView();
		List<Bug> bugs = bugService.getForAppraisalBugs();
		mv.addObject("bugs",bugs);
		mv.setViewName("appraisal_bug");
		return mv;
	}
	@RequiresPermissions(value="评审")
	@RequestMapping("/appraisalBug")
	@ResponseBody
	public Map<String, String> appraisalBug(Integer bid,Integer result,HttpSession session,String desc){
		Map<String,String> map = new HashMap<String,String>();
		UserPO user = userService.getUserByAccount((String)session.getAttribute("account"));
		boolean r = false;
		if(result == 1){
			r = true;
		}
		if(bugService.appraisalBug(r, bid,user,desc)){
			map.put("result", "1");
			map.put("message", "保存成功");
			return map;
		}
		else{
			map.put("result", "0");
			map.put("message", "保存失败");
			return map;
		}
	}
	@RequiresRoles(value={"系统管理员"})
	@RequestMapping("/toBug")
	public ModelAndView toBug() {
	//	System.out.println("11");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bugs");
		return mv;
	}
	@RequiresRoles(value={"系统管理员"})
	@RequestMapping(value = "/getBugs", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getBugs(Integer cp,Integer ps){
		List<Bug> bugs = bugService.getBugs(cp, ps);
		int total = bugService.getBugs(-1, -1).size();
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		JSONObject data = null;
		for (Bug bug : bugs) {
			data = new JSONObject();
			data.put("id", bug.getBid());
			data.put("title", bug.getTitle());
			data.put("reporter", bug.getReporter()==null?"无":bug.getReporter().getName());
			data.put("resolver", bug.getResolver()==null?"无": bug.getResolver().getName());
			data.put("identifier", bug.getIdentifier()==null?"无":bug.getIdentifier().getName());
			data.put("status", bug.getStatus());
			data.put("count", bug.getCount());
			data.put("prj", bug.getPrj().getName());
			ja.put(data);
		}
		jo.put("total", total);
		jo.put("rows", ja);
		return jo.toString();
	}
	@RequiresRoles(value={"系统管理员"})
	@RequestMapping("/bugUpt")
	@ResponseBody
	public Map<String, String> bugUpt(Bug bug){
		Map<String, String> map = new HashMap<String, String>();
		
		Bug bug_ = bugService.getBugById(bug.getBid());
		bug_.setTitle(bug.getTitle());
		
		if(bugService.saveBug(bug_)){
			map.put("result", "1");
			map.put("message", "保存成功");
			return map;
		}
		else{
			map.put("result", "0");
			map.put("message", "保存失败");
			return map;
		}
	}
	@RequiresRoles(value={"系统管理员"})
	@RequestMapping("/bugDelete")
	@ResponseBody
	public Map<String, String> bugDelete(String ids[],String count[]){
		Map<String, String> map = new HashMap<String, String>();
		Map<Integer,Integer> bids = new HashMap<Integer,Integer>();
		for(int i=0;i<ids.length;i++){
			bids.put(Integer.parseInt(ids[i]),Integer.parseInt(count[i]));
		}

		if(bugService.deleteBug(bids)){
			map.put("result", "1");
			map.put("message", "删除成功");
			return map;
		}
		else{
			map.put("result", "0");
			map.put("message", "删除失败");
			return map;
		}
	}


}
