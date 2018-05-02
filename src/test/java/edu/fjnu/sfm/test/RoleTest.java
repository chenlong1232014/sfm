/**
 * 
 */
package edu.fjnu.sfm.test;

import javax.annotation.Resource;
import org.junit.Test;

import edu.fjnu.sfm.po.RolePO;
import edu.fjnu.sfm.service.RoleServiceI;

/**
 * @author Administrator
 *
 */
public class RoleTest extends SpringTest {
	
	@Resource
	private RoleServiceI RoleService; 
	
	@Test
	public void testAddRole(){
		RolePO role = new RolePO();
		
		role.setRoleName("项目经理");

		
		RoleService.addRole(role);
		
	}

}
