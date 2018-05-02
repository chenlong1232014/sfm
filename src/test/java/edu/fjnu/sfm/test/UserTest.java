/**
 * 
 */
package edu.fjnu.sfm.test;


import javax.annotation.Resource;

import org.junit.Test;

import edu.fjnu.sfm.po.UserPO;
import edu.fjnu.sfm.service.UserServiceI;

/**
 * @author Administrator
 *
 */
public class UserTest extends SpringTest {
	
	@Resource
	private UserServiceI userService; 
	
	@Test
	public void testAddUser(){
		UserPO user = new UserPO();
		user.setName("小红");
		user.setPassword("123123");
		user.setSex("女");
		user.setTelephone("7685763");
		
		
		userService.addUser(user);
		
	}
	@Test 
	public void testValidateUser(){
		System.out.println(userService.validateAccount("123"));;
	}
	@Test 
	public void testValidatePassword(){
		System.out.println(userService.validatePassword("123", "123123"));;
	}
	@Test
	public void testGetAccess(){
		userService.deleteUser(new int[]{24});
	}

}
