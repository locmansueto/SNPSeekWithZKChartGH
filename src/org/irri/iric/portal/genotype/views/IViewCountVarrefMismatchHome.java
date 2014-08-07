package org.irri.iric.portal.genotype.views;

import java.util.List;

public interface IViewCountVarrefMismatchHome {

	public abstract void persist(ViewCountVarrefMismatch transientInstance);

	public abstract void attachDirty(ViewCountVarrefMismatch instance);

	public abstract void attachClean(ViewCountVarrefMismatch instance);

	public abstract void delete(ViewCountVarrefMismatch persistentInstance);

	public abstract ViewCountVarrefMismatch merge(
			ViewCountVarrefMismatch detachedInstance);

	public abstract ViewCountVarrefMismatch findById(
			org.irri.iric.portal.genotype.views.ViewCountVarrefMismatchId id);

	public abstract List findByExample(ViewCountVarrefMismatch instance);
	
	public List executeSQL(String sql) ;

}