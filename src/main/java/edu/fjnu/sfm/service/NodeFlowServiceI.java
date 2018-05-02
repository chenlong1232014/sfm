package edu.fjnu.sfm.service;

import edu.fjnu.sfm.po.ProcessNode;

public interface NodeFlowServiceI {
	
	ProcessNode getNode(Boolean passed,ProcessNode currentNode);

}
