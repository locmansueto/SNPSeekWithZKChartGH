package org.irri.iric.portal.genotype.views;

import java.util.List;

public interface IViewSnpAllvarsPosHome {

	public abstract void persist(ViewSnpAllvarsPos transientInstance);

	public abstract void attachDirty(ViewSnpAllvarsPos instance);

	public abstract void attachClean(ViewSnpAllvarsPos instance);

	public abstract void delete(ViewSnpAllvarsPos persistentInstance);

	public abstract ViewSnpAllvarsPos merge(ViewSnpAllvarsPos detachedInstance);

	//public abstract ViewSnpAllvarsPos findById(java.lang.Long id);

	public abstract List findByExample(ViewSnpAllvarsPos instance);
	
	//public List findAll();
	public List executeSQL(String sql);

}