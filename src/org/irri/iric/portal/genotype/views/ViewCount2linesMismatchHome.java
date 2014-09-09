package org.irri.iric.portal.genotype.views;

// Generated Jul 31, 2014 6:00:12 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.List;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.irri.iric.portal.domain.Snps2VarsCountmismatch;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Home object for domain model class ViewCount2linesMismatch.
 * @see org.irri.iric.portal.genotype.views.ViewCount2linesMismatch
 * @author Hibernate Tools
 */

@Repository("IViewCount2linesMismatchHome")
@Transactional
public class ViewCount2linesMismatchHome implements IViewCount2linesMismatchHome {

	private static final Log log = LogFactory
			.getLog(ViewCount2linesMismatchHome.class);

	
	@PersistenceContext(unitName = "Production")
	private EntityManager entityManager;
		

	protected Session getSession() {
		
		return entityManager.unwrap(Session.class);
	}
	/*
	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}
	*/

	@Override
	public void persist(ViewCount2linesMismatch transientInstance) {
		log.debug("persisting ViewCount2linesMismatch instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(ViewCount2linesMismatch instance) {
		log.debug("attaching dirty ViewCount2linesMismatch instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public void attachClean(ViewCount2linesMismatch instance) {
		log.debug("attaching clean ViewCount2linesMismatch instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public void delete(ViewCount2linesMismatch persistentInstance) {
		log.debug("deleting ViewCount2linesMismatch instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public ViewCount2linesMismatch merge(
			ViewCount2linesMismatch detachedInstance) {
		log.debug("merging ViewCount2linesMismatch instance");
		try {
			ViewCount2linesMismatch result = (ViewCount2linesMismatch) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Override
	public ViewCount2linesMismatch findById(
			org.irri.iric.portal.genotype.views.ViewCount2linesMismatchId id) {
		log.debug("getting ViewCount2linesMismatch instance with id: " + id);
		try {
			ViewCount2linesMismatch instance = (ViewCount2linesMismatch) getSession()
					.get("org.irri.iric.portal.genotype.views.ViewCount2linesMismatch",
							id);
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
	public List findByExample(ViewCount2linesMismatch instance) {
		log.debug("finding ViewCount2linesMismatch instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"org.irri.iric.portal.genotype.views.ViewCount2linesMismatch")
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
	public List executeSQL(String sql) {
		//System.out.println("executing :" + sql);
		//log.info("executing :" + sql);
		return getSession().createSQLQuery(sql).addEntity(ViewCount2linesMismatchId.class).list();
	}

	@Override
	public List<Snps2VarsCountmismatch> countMismatch(Integer chr,
			BigDecimal start, BigDecimal end) {
		// TODO Auto-generated method stub
		
		String sql = "select upper(var1) var1, upper(var2) var2, count(*) mismatch from snp_2lines where " 
				+ " var1nuc<>var2nuc "
				+ " and (var1nuc is not null or var2nuc is not null) "
				+ " and chr=" + chr 
				+ " and pos between " + (start.intValue()-1) + " and " + (end.intValue()+1) 
				+ " and var1<=var2 "
				+ " group by var1, var2 "
				+ " order by var1, var2";
				
		
		return executeSQL(sql);
	}
	
	
	
	
}
