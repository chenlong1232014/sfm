/**
 * 
 */
package edu.fjnu.sfm.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.fjnu.sfm.po.ProcessNode;
import edu.fjnu.sfm.po.RolePO;
import edu.fjnu.sfm.service.ProcessNodeServiceI;

/**
 * @author Administrator
 *
 */
@Controller
@RequiresRoles(value={"系统管理员"})
public class NodeController {
	
	@Resource
	private ProcessNodeServiceI processNodeService;
	
	@RequestMapping(value = "/getNodes", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getRoles(){
		
		List<ProcessNode> nodes = processNodeService.getAllNodes();
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		JSONObject data = null;
		for (ProcessNode node : nodes) {
			data = new JSONObject();
			data.put("nid", node.getPnid());
			data.put("nodeName", node.getNodeName());
			ja.put(data);
		}
		jo.put("rows", ja);
		return jo.toString();
	}

}
