package org.irri.iric.portal.admin.zkui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.chado.oracle.domain.VAllsampleBasicprop;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;

public class DynamicColumnModel extends AbstractJpaDao {


	private List<String> dataList = new ArrayList<String>();
	private List<String> columnList = new ArrayList<String>();
	
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set getTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canBeMerged(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Init
	public void init(){
		
		
		dataList.add("Data 1");
		dataList.add("Data 2");
		dataList.add("Data 3");
		dataList.add("Data 4");
		dataList.add("Data 5");
		dataList.add("Data 6");
		dataList.add("Data 7");
		
		columnList.add("Dynamic Col A");
		columnList.add("Dynamic Col B");
		columnList.add("Dynamic Col C");
		columnList.add("Dynamic Col D");
		
	}
	
	public List<String> getColumnList() {
		return columnList;
	}
	public void setColumnList(List<String> columnList) {
		this.columnList = columnList;
	}
	
	public List<String> getDataList() {
		return dataList;
	}
	public void setDataList(List<String> dataList) {
		this.dataList = dataList;
	}
	

	
	@Override
	public List executeQuery(String queryString, Integer firstResult, Integer maxResults, Object... parameters) {
		// TODO Auto-generated method stub
		return super.executeQuery(queryString, firstResult, maxResults, parameters);
	}

	@Override
	public List executeQuery(String queryString, Object... parameters) {
		// TODO Auto-generated method stub
		return super.executeQuery(queryString, parameters);
	}

	public List executeSQL(String sql) {
		// System.out.println("executing :" + sql);
		// log.info("executing :" + sql);
		AppContext.debug("executing " + sql);
		List l= executeQuery(sql);
		AppContext.debug("result = " + l.size());
		if( l.size()>0) {
			Object o=l.get(0);
			AppContext.debug(o.getClass() + "   " + o.toString());
		}
		return l;
		
	}
}