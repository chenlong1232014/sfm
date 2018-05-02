package edu.fjnu.sfm.dao;

import java.io.Serializable;

public interface GenericDAOI<Obj,Id extends Serializable>
{
	int ADD = 1;
	int UPT = 2;
	int DEL = 3;
	boolean add(Obj obj);
	boolean del(Id id);
	boolean upt(Obj obj);
	Obj getObjById(Id id);	
}
