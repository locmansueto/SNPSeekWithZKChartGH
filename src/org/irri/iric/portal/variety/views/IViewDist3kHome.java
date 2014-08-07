package org.irri.iric.portal.variety.views;

import java.util.List;

public interface IViewDist3kHome {

	public abstract void persist(ViewDist3k transientInstance);

	public abstract void attachDirty(ViewDist3k instance);

	public abstract void attachClean(ViewDist3k instance);

	public abstract void delete(ViewDist3k persistentInstance);

	public abstract ViewDist3k merge(ViewDist3k detachedInstance);

	public abstract ViewDist3k findById(
			org.irri.iric.portal.variety.views.ViewDist3kId id);

	public abstract List findByExample(ViewDist3k instance);

	public List findAll();
	public List executeSQL(String sql) ;
	public List findVarieties(String irislist); 
}