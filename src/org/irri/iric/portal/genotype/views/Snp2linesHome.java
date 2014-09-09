package org.irri.iric.portal.genotype.views;

// Generated Jun 27, 2014 8:48:08 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.irri.iric.portal.domain.Snps2Vars;
import org.irri.iric.portal.genotype.service.GenotypeFacade.snpQueryMode;
//import org.hibernate.ejb.EntityManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Home object for domain model class Snp2lines.
 * @see org.irri.iric.portal.genotype.views.Snp2lines
 * @author Hibernate Tools
 */



@Repository("ISnp2linesHome")
//@Repository("Snps2VarsDAOLegacy")
@Transactional
public class Snp2linesHome implements ISnp2linesHome {

	private static final Log log = LogFactory.getLog(Snp2linesHome.class);

	//@Autowired
	//@PersistenceContext(unitName = "Production")
	
	//@PersistenceContext
	//@Resource(name =  "Production")
	@PersistenceContext(unitName = "Production")
	private EntityManager entityManager;
	
	

	protected Session getSession() {
		
		return entityManager.unwrap(Session.class);
	}
	

	@Override
	public void persist(Snp2lines transientInstance) {
		log.debug("persisting Snp2lines instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(Snp2lines instance) {
		log.debug("attaching dirty Snp2lines instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public void attachClean(Snp2lines instance) {
		log.debug("attaching clean Snp2lines instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Snp2lines persistentInstance) {
		log.debug("deleting Snp2lines instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public Snp2lines merge(Snp2lines detachedInstance) {
		log.debug("merging Snp2lines instance");
		try {
			Snp2lines result = (Snp2lines) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Override
	public Snp2lines findById(org.irri.iric.portal.genotype.views.Snp2linesId id) {
		log.debug("getting Snp2lines instance with id: " + id);
		try {
			Snp2lines instance = (Snp2lines) getSession()
					.get("org.irri.iric.portal.genotype.views.Snp2lines", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List findByExample(Snp2lines instance) {
		log.debug("finding Snp2lines instance by example");
		try {
			List results =getSession()
					.createCriteria(
							"org.irri.iric.portal.genotype.views.Snp2lines")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	@Override
	public Criteria createCriteria() {
		return 
				getSession()
		.createCriteria(
				"org.irri.iric.portal.genotype.views.Snp2lines");
	}
	
	@Override
	public List executeSQL(String sql, String entity) {
		//System.out.println("executing :" + sql);
		//log.info("executing :" + sql);
		return getSession().createSQLQuery(sql).addEntity(Snp2linesId.class).list();
	}


	@Override
	public Set<Snps2Vars> findVSnp2varsByVarsChrPosBetweenAll(Integer chr,
			BigDecimal start, BigDecimal end, Integer var1, Integer var2)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return findVSnp2varsByVarsChrPosBetweenAll(chr,
				 start,  end,  var1,  var2,
				 -1, -1);
	}


	@Override
	public Set<Snps2Vars> findVSnp2varsByVarsChrPosBetweenAll(Integer chr,
			BigDecimal start, BigDecimal end, Integer var1, Integer var2,
			int startResult, int maxRows) throws DataAccessException {
		// TODO Auto-generated method stub
		String sql = "select VAR1, VAR2, CHR, POS, REFNUC, VAR1NUC, VAR2NUC from SNP_2LINES where ";
		
		if(var1!=null)
		{
			sql += " var1='" + var1 + "' ";
			if(var2!=null)
				sql += " and var2='" + var2 + "' ";
		} else
		{
			if(var2!=null)
				sql += " var2='" + var2 + "' ";				
		}
			 
		  sql += " and chr=" + chr + " and pos between " +
				(start.intValue()-1) + " and " + (end.intValue() + 1);
		  sql += " and (REFNUC<>VAR1NUC or REFNUC<>VAR2NUC) ";
		
		 return new java.util.LinkedHashSet<Snps2Vars>(executeSQL(sql));
	}


	@Override
	public Set<Snps2Vars> findVSnp2varsByVarsChrPosBetweenMismatch(Integer chr,
			BigDecimal start, BigDecimal end, Integer var1, Integer var2)
			throws DataAccessException {
		// TODO Auto-generated method stub
		
		return findVSnp2varsByVarsChrPosBetweenMismatch( chr,
				 start,  end, var1,  var2,
				 -1, -1);
		
	}


	@Override
	public Set<Snps2Vars> findVSnp2varsByVarsChrPosBetweenMismatch(Integer chr,
			BigDecimal start, BigDecimal end, Integer var1, Integer var2,
			int startResult, int maxRows) throws DataAccessException {
		// TODO Auto-generated method stub
		String sql = "select VAR1, VAR2, CHR, POS, REFNUC, VAR1NUC, VAR2NUC from SNP_2LINES where ";
		
		if(var1!=null)
		{
			sql += " var1='" + var1 + "' ";
			if(var2!=null)
				sql += " and var2='" + var2 + "' ";
		} else
		{
			if(var2!=null)
				sql += " var2='" + var2 + "' ";				
		}
			 
		  sql += " and chr=" + chr + " and pos between " +
				(start.intValue()-1) + " and " + (end.intValue() + 1);
  		  sql += " and VAR1NUC<>VAR2NUC ";
		
		return new java.util.LinkedHashSet<Snps2Vars>(executeSQL(sql));
	}
	
	
	public List executeSQL(String sql) {
		//System.out.println("executing :" + sql);
		//log.info("executing :" + sql);
		return getSession().createSQLQuery(sql).addEntity(ViewSnpAllvarsId.class).list();
	}
	
	
	
}
