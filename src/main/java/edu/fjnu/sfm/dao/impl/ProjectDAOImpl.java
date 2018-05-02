/**
 * 
 */
package edu.fjnu.sfm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.fjnu.sfm.dao.ProjectDAOI;
import edu.fjnu.sfm.po.Project;

/**
 * @author Administrator
 *
 */
@Repository("projectDAO")
public class ProjectDAOImpl extends GenericDAOImpl<Project, Integer> implements ProjectDAOI {

	public List<Project> getPrjs() {
		String hql = "from Project prj";
		return getObjs(hql, -1, -1);
	}

	public List<Project> getPrjByUid(Integer uid, Integer cp, Integer ps) {
		
		String hql = "from Project prj where prj.manager.uid = "+uid;
		return getObjs(hql, cp, ps);
	}

	

}
