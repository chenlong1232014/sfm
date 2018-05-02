/**
 * 
 */
package edu.fjnu.sfm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.fjnu.sfm.dao.ProcessLogDAOI;
import edu.fjnu.sfm.dao.ProjectDAOI;
import edu.fjnu.sfm.po.Project;
import edu.fjnu.sfm.service.ProjectServiceI;

/**
 * @author Administrator
 *
 */
@Service("projectService")
@Transactional
public class ProjectServiceImpl implements ProjectServiceI {

	@Resource
	private ProjectDAOI projectDAO;
	@Resource
	private ProcessLogDAOI processLogDAO;
	
	public List<Project> getPrjs() {
		return projectDAO.getPrjs();
	}

	public Project getPrjById(Integer id) {
		return projectDAO.getObjById(id);
	}

	public boolean addPrj(Project prj) {
		
		return projectDAO.add(prj);
	}

	
	public List<Project> getPrj(Integer cp, Integer ps, Integer uid) {
		
		return projectDAO.getPrjByUid(uid, cp, ps);
	}

	
	public Integer getPrjNum(Integer uid) {
		// TODO Auto-generated method stub
		return projectDAO.getPrjByUid(uid, -1, -1).size();
	}

	
	public String[] getPrjDateScope(Integer pid) {
		Date startDate = projectDAO.getObjById(pid).getStartDate();
		String[] date = new String[10];
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<10;i++){
			date[i]= df.format(startDate).toString();
			 Calendar c = Calendar.getInstance();
		     c.setTime(startDate);
		     c.add(Calendar.DAY_OF_MONTH, 1);
		     startDate = c.getTime();
		}
		return date;
	}
	
	

}
