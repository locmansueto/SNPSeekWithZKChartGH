package org.irri.iric.portal.genotype.views;

import java.util.List;

public interface IViewCount2linesMismatchHome {

	public abstract void persist(ViewCount2linesMismatch transientInstance);

	public abstract void attachDirty(ViewCount2linesMismatch instance);

	public abstract void attachClean(ViewCount2linesMismatch instance);

	public abstract void delete(ViewCount2linesMismatch persistentInstance);

	public abstract ViewCount2linesMismatch merge(
			ViewCount2linesMismatch detachedInstance);

	public abstract ViewCount2linesMismatch findById(
			org.irri.iric.portal.genotype.views.ViewCount2linesMismatchId id);

	public abstract List findByExample(ViewCount2linesMismatch instance);

	public List executeSQL(String sql) ;
}