package org.irri.iric.portal.genotype.views;

// Generated Jul 10, 2014 12:01:30 PM by Hibernate Tools 3.4.0.CR1

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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Home object for domain model class ViewSnpAllvars.
 * @see org.irri.iric.portal.genotype.views.ViewSnpAllvars
 * @author Hibernate Tools
 */

@Repository("IViewSnpAllvarsHome")
@Transactional
public class ViewSnpAllvarsHome implements IViewSnpAllvarsHome {

	private static final Log log = LogFactory.getLog(ViewSnpAllvarsHome.class);

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
	} */

	@Override
	public void persist(ViewSnpAllvars transientInstance) {
		log.debug("persisting ViewSnpAllvars instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(ViewSnpAllvars instance) {
		log.debug("attaching dirty ViewSnpAllvars instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public void attachClean(ViewSnpAllvars instance) {
		log.debug("attaching clean ViewSnpAllvars instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public void delete(ViewSnpAllvars persistentInstance) {
		log.debug("deleting ViewSnpAllvars instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public ViewSnpAllvars merge(ViewSnpAllvars detachedInstance) {
		log.debug("merging ViewSnpAllvars instance");
		try {
			ViewSnpAllvars result = (ViewSnpAllvars)getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	
	

	@Override
	public ViewSnpAllvars findById(
			org.irri.iric.portal.genotype.views.ViewSnpAllvarsId id) {
		log.debug("getting ViewSnpAllvars instance with id: " + id);
		try {
			ViewSnpAllvars instance = (ViewSnpAllvars)getSession()
					.get("org.irri.iric.portal.genotype.views.ViewSnpAllvars",
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
	public List findByExample(ViewSnpAllvars instance) {
		log.debug("finding ViewSnpAllvars instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"org.irri.iric.portal.genotype.views.ViewSnpAllvars")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	/*
	public long countSnpsVareities(String chr, long start, long end) {
		
		Number count = (Number) session.createQuery(
		    "select count(p.id) from Person p"
		    + " where p.birthDate is not null and p.isStudent = true").uniqueResult();
	}*/
	
	
	public List findAll() {
		log.debug("finding ViewSnpAllvars instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"org.irri.iric.portal.genotype.views.ViewSnpAllvars").list();
			log.debug("find all, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	@Override
	public List executeSQL(String sql) {
		//System.out.println("executing :" + sql);
		//log.info("executing :" + sql);
		return getSession().createSQLQuery(sql).addEntity(ViewSnpAllvarsId.class).list();
	}
	
}
