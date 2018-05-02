/**
 * 
 */
package edu.fjnu.sfm.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import edu.fjnu.sfm.service.ProcessNodeServiceI;
import edu.fjnu.sfm.service.RoleServiceI;
import edu.fjnu.sfm.service.UserServiceI;

/**
 * @author Administrator
 * 
 */
public class ShiroRealm extends AuthorizingRealm {

	@Resource
	private UserServiceI userService;
	@Resource 
	private RoleServiceI roleService;
	@Resource
	private ProcessNodeServiceI processNodeService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Object principal = principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> roles = new HashSet<String>();
		Set<String> permissions = new HashSet<String>();
		roles = roleService.getRoleByUid(userService.getUserByAccount((String)principal).getUid());
		permissions = processNodeService.getPermissons(userService.getUserByAccount((String)principal).getUid());
		info.addRoles(roles);
		info.addStringPermissions(permissions);

		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		System.out.println("认证！");
		SimpleAuthenticationInfo info = null;
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		if (userService.validateAccount(username)) {
			Object principal = username;
			Object credentials = userService.getPasswordByAccount(username);
			String realmName = getName();
			info = new SimpleAuthenticationInfo(principal, credentials, null,
					realmName);
			
		}
		else
			throw  new AuthenticationException("账号不存在！");
		return info;
	}
	public static void main(String[] args) {
		String name = "MD5";
		Object password = "123123";
		Object result = new SimpleHash(name,password,null,1);
		System.out.println(result);
	}

}
