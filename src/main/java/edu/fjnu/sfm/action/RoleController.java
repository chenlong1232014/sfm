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

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.fjnu.sfm.po.ProcessNode;
import edu.fjnu.sfm.po.RolePO;
import edu.fjnu.sfm.po.UserPO;
import edu.fjnu.sfm.service.RoleServiceI;

/**
 * @author Administrator
 *
 */
@Controller
@RequiresRoles(value={"系统管理员"})
public class RoleController {
	
	@Resource
	private RoleServiceI roleService;
	
	
	@RequestMapping(value = "/getRoles", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getRoles(){
		
		List<RolePO> roles = roleService.getAllRoles();
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		JSONObject data = null;
		for (RolePO role : roles) {
			data = new JSONObject();
			data.put("id", role.getRid());
			data.put("roleName", role.getRoleName());
			ja.put(data);
		}
		jo.put("rows", ja);
		return jo.toString();
	}
	@RequestMapping(value = "/getPageRoles", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getPageRoles(Integer cp,Integer ps){
		
		Integer total = roleService.getAllRoles().size();
		List<RolePO> roles = roleService.getPageRoles(cp, ps);
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		JSONObject data = null;
		for (RolePO role : roles) {
			data = new JSONObject();
			data.put("id", role.getRid());
			data.put("roleName", role.getRoleName());
			JSONArray ja2 = new JSONArray();
			JSONObject data2 = null;
			for(ProcessNode node: role.getNodes()){
				 data2 = new JSONObject();
				data2.put("nid",node.getPnid());
				data2.put("nodeName",node.getNodeName());
				ja2.put(data2);
			}
			data.put("nodes", ja2);
			ja.put(data);
		}
		jo.put("rows", ja);
		jo.put("total", total);
		return jo.toString();
	}
	@RequestMapping("/toRole")
	public ModelAndView toRole() {
	//	System.out.println("11");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("roles");
		return mv;
	}
	@RequestMapping("/roleUpt")
	@ResponseBody
	public Map<String, String> roleUpt(RolePO role){
		Map<String, String> map = new HashMap<String, String>();
		
		
		if(roleService.saveRole(role)){
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
	@RequestMapping("/roleGrant")
	@ResponseBody
	public Map<String, String> roleGrant(Integer rid,String nids[]){
		Map<String, String> map = new HashMap<String, String>();
		Set<Integer> ids = new HashSet<Integer>();
		for(int i=0;i<nids.length;i++){
			ids.add(Integer.parseInt(nids[i]));
		}
		if(roleService.grantRole(rid, ids)){
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
	@RequestMapping("/roleAdd")
	@ResponseBody
	public Map<String, String> roleAdd(RolePO role){
		Map<String, String> map = new HashMap<String, String>();

		if(roleService.addRole(role)){
			map.put("result", "1");
			map.put("message", "添加成功");
			return map;
		}
		else{
			map.put("result", "0");
			map.put("message", "添加失败");
			return map;
		}
	}
	@RequestMapping("/roleDelete")
	@ResponseBody
	public Map<String, String> roleDelete(String ids[]){
		Map<String, String> map = new HashMap<String, String>();
		Set<Integer> rids = new HashSet<Integer>();
		for(int i=0;i<ids.length;i++){
			rids.add(Integer.parseInt(ids[i]));
		}

		if(roleService.deleteRole(rids)){
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
