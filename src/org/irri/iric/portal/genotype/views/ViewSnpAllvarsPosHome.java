package org.irri.iric.portal.genotype.views;

// Generated Jul 10, 2014 8:32:19 PM by Hibernate Tools 3.4.0.CR1

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
 * Home object for domain model class ViewSnpAllvarsPos.
 * @see org.irri.iric.portal.genotype.views.ViewSnpAllvarsPos
 * @author Hibernate Tools
 */
@Repository("IViewSnpAllvarsPosHome")
@Transactional
public class ViewSnpAllvarsPosHome implements IViewSnpAllvarsPosHome {

	private static final Log log = LogFactory
			.getLog(ViewSnpAllvarsPosHome.class);


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
	public void persist(ViewSnpAllvarsPos transientInstance) {
		log.debug("persisting ViewSnpAllvarsPos instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ViewSnpAllvarsPos instance) {
		log.debug("attaching dirty ViewSnpAllvarsPos instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ViewSnpAllvarsPos instance) {
		log.debug("attaching clean ViewSnpAllvarsPos instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ViewSnpAllvarsPos persistentInstance) {
		log.debug("deleting ViewSnpAllvarsPos instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ViewSnpAllvarsPos merge(ViewSnpAllvarsPos detachedInstance) {
		log.debug("merging ViewSnpAllvarsPos instance");
		try {
			ViewSnpAllvarsPos result = (ViewSnpAllvarsPos) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ViewSnpAllvarsPos findById(
			org.irri.iric.portal.genotype.views.ViewSnpAllvarsPosId id) {
		log.debug("getting ViewSnpAllvarsPos instance with id: " + id);
		try {
			ViewSnpAllvarsPos instance = (ViewSnpAllvarsPos)getSession()
					.get("org.irri.iric.portal.genotype.views.ViewSnpAllvarsPos",
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

	public List findByExample(ViewSnpAllvarsPos instance) {
		log.debug("finding ViewSnpAllvarsPos instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"org.irri.iric.portal.genotype.views.ViewSnpAllvarsPos")
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
		return getSession().createSQLQuery(sql).addEntity(ViewSnpAllvarsPosId.class).list();
	}
	
	
}
