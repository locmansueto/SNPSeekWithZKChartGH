package org.irri.iric.portal.genotype.views;

import java.util.List;

import org.irri.iric.portal.dao.SnpsAllvarsDAO;

public interface IViewSnpAllvarsHome extends SnpsAllvarsDAO {

	public abstract void persist(ViewSnpAllvars transientInstance);

	public abstract void attachDirty(ViewSnpAllvars instance);

	public abstract void attachClean(ViewSnpAllvars instance);

	public abstract void delete(ViewSnpAllvars persistentInstance);

	public abstract ViewSnpAllvars merge(ViewSnpAllvars detachedInstance);

	public abstract ViewSnpAllvars findById(
			org.irri.iric.portal.genotype.views.ViewSnpAllvarsId id);

	public abstract List findByExample(ViewSnpAllvars instance);
	
	public List findAll();
	public List executeSQL(String sql);
}