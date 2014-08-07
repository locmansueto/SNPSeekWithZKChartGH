package org.irri.iric.portal.utils.zkui;


/**
 * SearchObject depending on the Search Object from the Hibernate-Generic-DAO
 * framework. <br>
 * 
 * @see http://code.google.com/p/hibernate-generic-dao/ <br>
 *      Many thanks to David Wolvert.
 * 
 * @author sge
 * 
 * @param <E>
 */

public class HibernateSearchObject<E>  {
	
	private static final long serialVersionUID = 1L;
	private Class entclass;
	private int firstResult;
	private int maxResults;
	

	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	public HibernateSearchObject(Class<E> entityClass) {
		//super(entityClass);
		//super();
		
		this.entclass = entityClass;
	}
	
	public HibernateSearchObject(Class<E> entityClass, int pageSize) {
		//super(entityClass);
		this.entclass = entityClass;
		setFirstResult(0);
		setMaxResults(pageSize);
	}

	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public void clearSorts() {
		// TODO Auto-generated method stub
		
	}

	public void addSort(String orderBy, boolean b) {
		// TODO Auto-generated method stub
		
	}

	public Object getSorts() {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}