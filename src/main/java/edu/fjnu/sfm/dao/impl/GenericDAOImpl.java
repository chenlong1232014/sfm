package edu.fjnu.sfm.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;





import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import edu.fjnu.sfm.dao.GenericDAOI;

@Repository
public class GenericDAOImpl<Obj,Id extends Serializable> implements GenericDAOI<Obj, Id> {

	private Class<Obj> clzz;
	
	//绗竴绉�
//	public void setClzz(Class<Obj> clzz) {
//		this.clzz = clzz;
//	}
	
	//绗簩绉�
	@SuppressWarnings("unchecked")
	public GenericDAOImpl()
	{
		Type type = this.getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType)type;
		clzz = (Class<Obj>) pt.getActualTypeArguments()[0];
	}
	
	@Resource
	private  SessionFactory sessionFactory;

	

	public boolean add(Obj obj) {
		return cud(ADD,obj);
	}


	public boolean del(Id id) {
		return cud(DEL,getObjById(id));
	}


	public boolean upt(Obj obj) {
		return cud(UPT,obj);
	}

	@SuppressWarnings("unchecked")
	public Obj getObjById(Id id) {
		Obj obj = null;
		Session session = null;
		try
		{
			session  = sessionFactory.getCurrentSession();
			obj = (Obj)session.get(clzz, id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		/*finally
		{
			if(session!=null)
				session.close();
		}*/
		return obj;
	}

	@SuppressWarnings("unchecked")
	protected List<Obj> getObjs(String hql,int curPage,int pageSize) 
	{
		List<Obj> objs = null;
		Session session = null;
		try
		{
			session  = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			
			//鍒嗛〉
			if(curPage>=0)
			{
				query.setFirstResult(curPage*pageSize);
				query.setMaxResults(pageSize);
			}
			objs = query.list();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	/*	finally
		{
			if(session!=null)
				session.close();
		}*/
		return objs;
	}

	public boolean cud(int opt,Obj obj)
	{
		boolean flag = false;
		Session session = null;
		try
		{
			session  = sessionFactory.getCurrentSession();
			//session.beginTransaction();
			switch(opt)
			{
				case ADD:
					session.save(obj);
					break;
				case DEL:
					session.delete(obj);
					break;
				case UPT:
					 session.update(obj);
					 break;
			}
			//session.getTransaction().commit();
			flag = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
/*		finally
		{
			if(session!=null)
				session.close();
		}*/
		return flag;
	}
}
