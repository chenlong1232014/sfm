/**
 * 
 */
package edu.fjnu.sfm.dao;


import java.util.List;

import edu.fjnu.sfm.po.Project;

/**
 * @author Administrator
 *
 */
public interface ProjectDAOI extends GenericDAOI<Project,Integer>{

	List<Project> getPrjs();
	List<Project> getPrjByUid(Integer uid,Integer cp,Integer ps);

}
