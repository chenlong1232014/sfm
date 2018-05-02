/**
 * 
 */
package edu.fjnu.sfm.test;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import org.junit.Test;

import edu.fjnu.sfm.po.ProcessNode;
import edu.fjnu.sfm.service.ProcessNodeServiceI;

/**
 * @author Administrator
 *
 */
public class ProcessNodeTest extends SpringTest {
	
	@Resource
	private ProcessNodeServiceI processNodeService; 
	
	@Test
	public void testAddProcessNode(){
		ProcessNode processNode = new ProcessNode();
		
		processNode.setNodeName("确认缺陷是否修复");
		
		processNodeService.addProcessNode(processNode);
		
	}
	
	@Test
	public void testNodeGrant(){
		Set<Integer> roleIDs = new HashSet<Integer>();
		roleIDs.add(1);
		roleIDs.add(2);
		
		processNodeService.nodeGranted(1, roleIDs);
		
		
	}
	@Test
	public void testGetAccess(){

	}

}
