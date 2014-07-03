package org.irri.iric.portal.genotype.views;

// Generated Jun 27, 2014 8:48:08 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;

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
import org.hibernate.ejb.EntityManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Home object for domain model class Snp2lines.
 * @see org.irri.iric.portal.genotype.views.Snp2lines
 * @author Hibernate Tools
 */



@Repository("ISnp2linesHome")
@Transactional
public class Snp2linesHome implements ISnp2linesHome {

	private static final Log log = LogFactory.getLog(Snp2linesHome.class);

	//@Autowired
	//@PersistenceContext(unitName = "Production")
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	//private  SessionFactory sessionFactory= getSessionFactory();

	protected Session getSession() {
		
		//return sessionFactory;
		
		// to use the jpa entity manager instead of hibernate session
		return entityManager.unwrap(Session.class);
		 
		
		//HibernateEntityManager hem = entityManager.unwrap(HibernateEntityManager.class);
		//Session session = hem.getSession();
		//return session.getSessionFactory();
		
		/*
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}*/
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
		System.out.println("executing :" + sql);
		log.info("executing :" + sql);
		return getSession().createSQLQuery(sql).addEntity(Snp2linesId.class).list();
	}
	
	
}
