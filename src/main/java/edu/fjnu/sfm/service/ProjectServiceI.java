package edu.fjnu.sfm.service;

import java.util.List;

import edu.fjnu.sfm.po.Project;

public interface ProjectServiceI {

	List<Project> getPrjs();
	Project getPrjById(Integer id);
	boolean addPrj(Project prj);
	List<Project> getPrj(Integer cp,Integer ps,Integer uid);
	Integer getPrjNum(Integer uid);
	String[] getPrjDateScope(Integer pid);
}
