package org.irri.iric.portal.genotype.views;

import java.util.List;

import org.hibernate.Criteria;

public interface ISnp2linesHome {

	public abstract void persist(Snp2lines transientInstance);

	public abstract void attachDirty(Snp2lines instance);

	public abstract void attachClean(Snp2lines instance);

	public abstract void delete(Snp2lines persistentInstance);

	public abstract Snp2lines merge(Snp2lines detachedInstance);

	public abstract Snp2lines findById(
			org.irri.iric.portal.genotype.views.Snp2linesId id);

	public abstract List findByExample(Snp2lines instance);

	public abstract Criteria createCriteria();

	public List executeSQL(String sql, String entity) ;
}