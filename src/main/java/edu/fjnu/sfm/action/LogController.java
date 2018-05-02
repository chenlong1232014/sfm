/**
 * 
 */
package edu.fjnu.sfm.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.fjnu.sfm.po.Bug;
import edu.fjnu.sfm.po.ProcessLog;
import edu.fjnu.sfm.po.UserPO;
import edu.fjnu.sfm.service.ProcessLogServiceI;
import edu.fjnu.sfm.service.UserServiceI;
import edu.fjnu.sfm.utils.ProcessType;

/**
 * @author Administrator
 * 
 */
@Controller
@RequiresAuthentication
public class LogController {

	@Resource
	private ProcessLogServiceI processLogService;
	@Resource
	private UserServiceI userService;

	@RequestMapping(value = "/getTask", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getTask(HttpSession session, Integer cp, Integer ps,
			String search) {
		// System.out.println(cp);
		// System.out.println(ps);
		UserPO user = userService.getUserByAccount((String) session
				.getAttribute("account"));
		int total = processLogService.getTotalLog(user.getUid());
		List<ProcessLog> logs = processLogService.getLog(user.getUid(), cp, ps);
		// System.out.println(logs.size());
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		JSONObject data = null;
		jo.put("total", total);
		for (ProcessLog log : logs) {
			data = new JSONObject();
			data.put("id", log.getPlid());
			data.put("bid",log.getBug().getBid());
			data.put("title", log.getBug().getTitle());
			data.put("date", log.getLogDate());
			data.put("type", log.getNode().getNodeName());
			data.put("done", log.isDone());
			data.put("prj", log.getBug().getPrj().getName());
			data.put("node", log.getNode().getPnid());
			ja.put(data);
		}
		jo.put("rows", ja);
		// System.out.println(jo.toString());
		return jo.toString();
	}

	@RequestMapping(value = "/getLog", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getLog(HttpSession session, Integer cp, Integer ps,
			Integer opt) {
		// System.out.println(cp);
		// System.out.println(ps);
		boolean done = false;
		UserPO user = userService.getUserByAccount((String) session
				.getAttribute("account"));
		if (opt == 1) {
			done = true;
		}
		int total = processLogService.getTotalSaLog(user.getUid(), done);
		List<ProcessLog> logs = processLogService.getSaLog(user.getUid(), done,
				cp, ps);
		// System.out.println(logs.size());
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		JSONObject data = null;
		jo.put("total", total);
		for (ProcessLog log : logs) {
			data = new JSONObject();
			data.put("id", log.getPlid());
			data.put("title", log.getBug().getTitle());
			data.put("date", log.getLogDate());
			data.put("type", log.getNode().getNodeName());
			data.put("done", log.isDone());
			data.put("prj", log.getBug().getPrj().getName());
			data.put("node", log.getNode().getPnid());
			ja.put(data);
		}
		jo.put("rows", ja);
		// System.out.println(jo.toString());
		return jo.toString();
	}

	@RequestMapping(value = "/getStcLog", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getStcLog(HttpSession session) {
		UserPO user = userService.getUserByAccount((String) session
				.getAttribute("account"));
		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();
		JSONObject data = new JSONObject();
		Map<Integer, Integer> map = processLogService.getStcLog(user.getUid());
		data.put("nodeName", "新建");
		data.put("num", map.get(ProcessType.ADD_BUG));
		ja.put(data);
		data = new JSONObject();
		data.put("nodeName", "确认存在");
		data.put("num", map.get(ProcessType.COMFIRM_BUG_EXIST));
		ja.put(data);
		data = new JSONObject();
		data.put("nodeName", "分配");
		data.put("num", map.get(ProcessType.ASSIGN_BUG));
		ja.put(data);
		data = new JSONObject();
		data.put("nodeName", "修复");
		data.put("num", map.get(ProcessType.FIX_BUG));
		ja.put(data);
		data = new JSONObject();
		data.put("nodeName", "验证修复");
		data.put("num", map.get(ProcessType.COMFIRM_BUG_EXIST));
		ja.put(data);
		data = new JSONObject();
		data.put("nodeName", "评审");
		data.put("num", map.get(ProcessType.VAL_BUG));
		ja.put(data);

		jo.put("list", ja);

		return jo.toString();
	}

	@RequestMapping("/myTask")
	public ModelAndView getTask() {
	//	System.out.println("11");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("mytask");
		return mv;
	}
	@RequestMapping("/countTask")
	public ModelAndView getCountTask() {
	//	System.out.println("11");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("task_count");
		return mv;
	}
	@RequestMapping(value = "/getPageLog", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getPageLog(Integer cp, Integer ps) {
		// System.out.println(cp);
		// System.out.println(ps);
		
		int total = processLogService.getPageLog(-1, -1).size();
		List<ProcessLog> logs = processLogService.getPageLog(cp, ps);
		// System.out.println(logs.size());
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		JSONObject data = null;
		jo.put("total", total);
		for (ProcessLog log : logs) {
			data = new JSONObject();
			data.put("id", log.getPlid());
			data.put("title", log.getBug().getTitle());
			data.put("logDate", log.getLogDate());
			data.put("nodeName", log.getNode().getNodeName());
			data.put("passed", log.isPassed());
			data.put("prj", log.getBug().getPrj().getName());
			data.put("logDesc", log.getLogDesc());
			data.put("handler", log.getHandler().getName());
			ja.put(data);
		}
		jo.put("rows", ja);
		// System.out.println(jo.toString());
		return jo.toString();
	}
	@RequestMapping("/toLog")
	public ModelAndView toLog() {
	//	System.out.println("11");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("logs");
		return mv;
	}
	@RequestMapping("/logUpt")
	@ResponseBody
	public Map<String, String> bugUpt(ProcessLog log){
		Map<String, String> map = new HashMap<String, String>();
		
		ProcessLog log_ = processLogService.getLogById(log.getPlid());
		log_.setLogDesc(log.getLogDesc());
		
		if(processLogService.saveLog(log_)){
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

}
