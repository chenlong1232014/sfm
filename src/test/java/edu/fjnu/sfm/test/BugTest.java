/**
 * 
 */
package edu.fjnu.sfm.test;

import javax.annotation.Resource;

import org.junit.Test;

import edu.fjnu.sfm.po.Bug;
import edu.fjnu.sfm.po.UserPO;
import edu.fjnu.sfm.service.BugServiceI;

/**
 * @author Administrator
 *
 */
public class BugTest extends SpringTest {
	
	@Resource
	private BugServiceI bugService;
	
	
	@Test
	public void testAddBug(){
		Bug bug = new Bug();
		bug.setTitle("测试bug1");
		bug.setCount(1);
		String desc ="";
		bugService.addBug(bug,desc,new UserPO());
	}
	
	@Test
	public void testExistBug(){
		//bugService.existBug(true, 5);
	}
	
	@Test
	public void test(){
		
	}
	
	@Test 
	public void testAssignBug(){
		//bugService.assignBug(5, 3);
	}
	
	
	@Test
	public void testFixBug(){
		//bugService.fixBug("修复描述1", 5, true);
		}
	
	@Test
	public void testValidateBug(){
		//bugService.validateBug("验证描述1", 5, true);
	}

}
