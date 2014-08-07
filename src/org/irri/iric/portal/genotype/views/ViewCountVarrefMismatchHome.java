package org.irri.iric.portal.genotype.views;

// Generated Jul 22, 2014 8:45:17 AM by Hibernate Tools 3.4.0.CR1

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
 * Home object for domain model class ViewCountVarrefMismatch.
 * @see org.irri.iric.portal.genotype.views.ViewCountVarrefMismatch
 * @author Hibernate Tools
 */
@Repository("IViewCountVarrefMismatchHome")
@Transactional
public class ViewCountVarrefMismatchHome implements IViewCountVarrefMismatchHome {

	private static final Log log = LogFactory
			.getLog(ViewCountVarrefMismatchHome.class);


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
	public void persist(ViewCountVarrefMismatch transientInstance) {
		log.debug("persisting ViewCountVarrefMismatch instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(ViewCountVarrefMismatch instance) {
		log.debug("attaching dirty ViewCountVarrefMismatch instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public void attachClean(ViewCountVarrefMismatch instance) {
		log.debug("attaching clean ViewCountVarrefMismatch instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public void delete(ViewCountVarrefMismatch persistentInstance) {
		log.debug("deleting ViewCountVarrefMismatch instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public ViewCountVarrefMismatch merge(
			ViewCountVarrefMismatch detachedInstance) {
		log.debug("merging ViewCountVarrefMismatch instance");
		try {
			ViewCountVarrefMismatch result = (ViewCountVarrefMismatch) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Override
	public ViewCountVarrefMismatch findById(
			org.irri.iric.portal.genotype.views.ViewCountVarrefMismatchId id) {
		log.debug("getting ViewCountVarrefMismatch instance with id: " + id);
		try {
			ViewCountVarrefMismatch instance = (ViewCountVarrefMismatch) getSession()
					.get("org.irri.iric.portal.genotype.views.ViewCountVarrefMismatch",
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
	public List findByExample(ViewCountVarrefMismatch instance) {
		log.debug("finding ViewCountVarrefMismatch instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"org.irri.iric.portal.genotype.views.ViewCountVarrefMismatch")
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
		return getSession().createSQLQuery(sql).addEntity(ViewCountVarrefMismatchId.class).list();
	}
}
