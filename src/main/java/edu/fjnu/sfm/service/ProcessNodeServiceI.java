package edu.fjnu.sfm.service;

import java.util.List;
import java.util.Set;

import edu.fjnu.sfm.po.ProcessNode;

public interface ProcessNodeServiceI {

	boolean addProcessNode(ProcessNode processNode);
	boolean nodeGranted(Integer pnid,Set<Integer> roleIDs);
	List<ProcessNode> getAllNodes();
	Set<String> getPermissons(Integer uid);
}
