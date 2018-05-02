package edu.fjnu.sfm.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.fjnu.sfm.po.ProcessLog;
import edu.fjnu.sfm.po.RolePO;
import edu.fjnu.sfm.po.UserPO;
import edu.fjnu.sfm.service.UserServiceI;

@Controller
public class UserController {

	@Resource
	private UserServiceI userService;

	
	@RequestMapping("/login")
	public String login(HttpSession session, String account,
			String password) {
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(account,password);
		try{
		currentUser.login(token);
		}
		catch(AuthenticationException ae){
			return "user_login";
		}
		UserPO user = userService.getUserByAccount(account);
		session.setAttribute("id", user.getUid());
		session.setAttribute("name", user.getName());
		session.setAttribute("account", user.getAccount());
		return "index";
	}
	@RequestMapping("/toLogin")
	public String login(){
		return "user_login";
	}
	

	@RequiresAuthentication
	@RequestMapping("/getAccess")
	@ResponseBody
	public Map<String, Boolean> getAccess(HttpSession session) {
		// System.out.println("getAccess");
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map = userService.getUserAccess((Integer) session.getAttribute("id"));
		return map;
	}


	@RequiresAuthentication
	@RequestMapping("/isManager")
	@ResponseBody
	public Map<String, Boolean> isManager(HttpSession session) {
		// System.out.println("isManager");
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		boolean result = userService.isManager((Integer) session
				.getAttribute("id"));
		map.put("result", result);
		return map;
	}

	@RequiresAuthentication
	@RequestMapping("/pwdModify")
	@ResponseBody
	public Map<String, String> pwdModify(HttpSession session, String oldPwd,
			String newPwd, String newPwdMore) {

		Map<String, String> map = new HashMap<String, String>();
		UserPO user = userService.getUserByAccount((String) session
				.getAttribute("account"));
		Object result = new SimpleHash("MD5",oldPwd,null,1);
		if (user.getPassword().equals(result.toString())){
			if (newPwd.equals(newPwdMore)) {
				if (newPwd.equals(oldPwd)) {
					map.put("message", "新密码和旧密码不能相同！");
					map.put("result", "0");
					return map;
				} else {
					result = new SimpleHash("MD5",newPwd,null,1).toString();
					if (userService.pwdModify((String)result, user.getUid())) {
						map.put("message", "修改成功");
						map.put("result", "1");
						return map;
					} else {
						map.put("message", "修改失败！");
						map.put("result", "0");
						return map;
					}
				}
			} else {
				map.put("message", "两次输入的新密码不相同！");
				map.put("result", "0");
				return map;
			}
		} else {
			map.put("message", "密码不正确！");
			map.put("result", "0");
			return map;
		}
	}
	@RequiresAuthentication
	@RequestMapping("/toPwdModify")
	public ModelAndView getTask() {
	//	System.out.println("11");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("pwd_modify");
		return mv;
	}
	@RequiresAuthentication
	@RequestMapping("/toUser")
	public ModelAndView toUser() {
	//	System.out.println("11");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("users");
		return mv;
	}
	@RequiresAuthentication
	@RequestMapping(value = "/getUsers", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getUsers(Integer cp, Integer ps,
			String search) {

		int total = userService.getUserNum();
		List<UserPO> users = userService.getUsers(cp, ps);
		// System.out.println(logs.size());
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		JSONObject data = null;
		jo.put("total", total);
		for (UserPO user : users) {
			data = new JSONObject();
			data.put("id",user.getUid());
			data.put("name",user.getName());                                           
			data.put("account",user.getAccount());
			data.put("password",user.getPassword());
			data.put("sex",user.getSex() );
			data.put("telephone",user.getTelephone());
			JSONArray ja2 = new JSONArray();
			JSONObject data2 = null;
			for(RolePO role: user.getRoles()){
				 data2 = new JSONObject();
				data2.put("rid",role.getRid());
				data2.put("roleName",role.getRoleName());
				ja2.put(data2);
			}
			data.put("roles", ja2);
			ja.put(data);
		}
		jo.put("rows", ja);
		return jo.toString();
	}
	@RequiresRoles(value={"系统管理员"})
	@RequestMapping("/userUpt")
	@ResponseBody
	public Map<String, String> userUpt(UserPO user){
		Object result = new SimpleHash("MD5",user.getPassword(),null,1);
		user.setPassword((String)result);
		Map<String, String> map = new HashMap<String, String>();
		UserPO user_ = userService.getUserById(user.getUid());
		user.setRoles(user_.getRoles());
		
		if(userService.saveUser(user)){
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
	@RequestMapping("/userGrant")
	@ResponseBody
	public Map<String, String> userGrant(Integer uid,String rids[]){
		Map<String, String> map = new HashMap<String, String>();
		int[] ids = new int[rids.length];
		for(int i=0;i<rids.length;i++){
			ids[i]=Integer.parseInt(rids[i]);
		}
		if(userService.grantUser(uid, ids)){
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
	@RequestMapping("/userDelete")
	@ResponseBody
	public Map<String, String> userDelete(String ids[]){
		Map<String, String> map = new HashMap<String, String>();
		int[] ids_ = new int[ids.length];
		for(int i=0;i<ids.length;i++){
			ids_[i]=Integer.parseInt(ids[i]);
		}

		if(userService.deleteUser(ids_)){
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
	@RequiresRoles(value={"系统管理员"})
	@RequestMapping("/userAdd")
	@ResponseBody
	public Map<String, String> userAdd(UserPO user){
		Map<String, String> map = new HashMap<String, String>();

		Object result = new SimpleHash("MD5",user.getPassword(),null,1);
		user.setPassword((String)result);
		if(userService.addUser(user)){
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

}
