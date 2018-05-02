/**
 * 
 */
package edu.fjnu.sfm.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.fjnu.sfm.po.Project;
import edu.fjnu.sfm.po.UserPO;
import edu.fjnu.sfm.service.ProcessLogServiceI;
import edu.fjnu.sfm.service.ProjectServiceI;
import edu.fjnu.sfm.service.UserServiceI;
import edu.fjnu.sfm.utils.ProcessType;

/**
 * @author Administrator
 *
 */
@Controller
@RequiresRoles(value={"系统管理员","项目经理"})
public class ProjectController {

	@Resource
	private ProjectServiceI projectService;
	@Resource
	private UserServiceI userService;
	@Resource
	private ProcessLogServiceI processLogService;
	
	@RequestMapping("/getPrjs")
	public ModelAndView getPrjs(){
		ModelAndView mv = new ModelAndView();
		List<Project> prjs = projectService.getPrjs();
		//System.out.println(prjs.size());
		mv.addObject("prjs", prjs);
		mv.setViewName("add_bug");
		return mv;
	}
	@RequestMapping("/skipPrj")
	public ModelAndView skipPrj(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("add_prj");
		return mv;
	}
	@RequestMapping("/getMyPrj")
	public ModelAndView getMyPrj(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("my_project");
		return mv;
	}
	@RequestMapping("/addPrj")
	@ResponseBody
	public Map<String, String> addPrj(Project prj,HttpSession session){

		Map<String, String> map = new HashMap<String, String>();
		UserPO user = userService.getUserById((Integer)session.getAttribute("id"));

		prj.setStatus("alpha测试");
		prj.setStartDate(new Date());
		prj.setManager(user);
		if(projectService.addPrj(prj)){
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
	
	@RequestMapping(value = "/getPrj", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getPrj(HttpSession session, Integer cp, Integer ps){
		UserPO user = userService.getUserByAccount((String) session
				.getAttribute("account"));
		
		int total = projectService.getPrjNum(user.getUid());
		List<Project> prjs = projectService.getPrj(cp,ps,user.getUid()) ;
		// System.out.println(logs.size());
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		JSONObject data = null;
		jo.put("total", total);
		for (Project  prj: prjs) {
			data = new JSONObject();
			data.put("id", prj.getPid());
			data.put("title", prj.getName());
			data.put("date", prj.getStartDate());
			data.put("status", prj.getStatus());
			ja.put(data);
		}
		jo.put("rows", ja);
		// System.out.println(jo.toString());
		return jo.toString(); 
	}
	@RequestMapping("/toPrj")
	public ModelAndView toPrj() {
	//	System.out.println("11");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("prjs");
		return mv;
	}
	@RequestMapping("/toPrjCount")
	public ModelAndView toPrjCount() {
	//	System.out.println("11");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("prj_count");
		return mv;
	}
	@RequestMapping(value = "/getStcPrj", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getStcPrj(Integer pid) {

		JSONArray ja = new JSONArray();
		JSONObject data = null;
		String[] date = projectService.getPrjDateScope(pid);
		List<Integer> findNum = new ArrayList<Integer>();
		List<Integer> fixNum = new ArrayList<Integer>();
		for(int i=0;i<date.length-1;i++)
		{
			findNum.add(processLogService.getFindNumByDate(pid,date[0], date[i+1]));
			fixNum.add(processLogService.getFixNumByDate(pid,date[0], date[i+1]));
		}
		for(int i=0;i<date.length-1;i++){
			data = new JSONObject();
			data.put("date", date[i+1]);
			data.put("findNum",findNum.get(i));
			data.put("fixNum", fixNum.get(i));
			ja.put(data);
		}
		   

		return ja.toString();
	}
	@RequiresPermissions("新建缺陷")
	@RequestMapping(value = "/getDetailPrj", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getDetailPrj(Integer pid) {

		JSONObject jo = new JSONObject();
		Project prj = projectService.getPrjById(pid);
		
		jo.put("name", prj.getManager().getName());
		jo.put("desc",prj.getDescription());
		jo.put("date", prj.getStartDate());
		jo.put("status", prj.getStatus());

		return jo.toString();
	}

}
