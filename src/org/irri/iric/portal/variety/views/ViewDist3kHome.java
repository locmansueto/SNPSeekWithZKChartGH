package org.irri.iric.portal.variety.views;

// Generated Jul 13, 2014 2:29:59 PM by Hibernate Tools 3.4.0.CR1


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
import org.irri.iric.portal.genotype.views.ViewSnpAllvarsPosId;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Home object for domain model class ViewDist3k.
 * @see org.irri.iric.portal.genotype.views.ViewDist3k
 * @author Hibernate Tools
 */

@Repository("IViewDist3kHome")
@Transactional
public class ViewDist3kHome implements IViewDist3kHome {

	private static final Log log = LogFactory.getLog(ViewDist3kHome.class);

	@PersistenceContext(unitName = "Development")
	private EntityManager entityManager;
	
	

	protected Session getSession() {
		
		return entityManager.unwrap(Session.class);
	}
	
	@Override
	public void persist(ViewDist3k transientInstance) {
		log.debug("persisting ViewDist3k instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(ViewDist3k instance) {
		log.debug("attaching dirty ViewDist3k instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public void attachClean(ViewDist3k instance) {
		log.debug("attaching clean ViewDist3k instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public void delete(ViewDist3k persistentInstance) {
		log.debug("deleting ViewDist3k instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public ViewDist3k merge(ViewDist3k detachedInstance) {
		log.debug("merging ViewDist3k instance");
		try {
			ViewDist3k result = (ViewDist3k) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Override
	public ViewDist3k findById(
			 org.irri.iric.portal.variety.views.ViewDist3kId id) {
		log.debug("getting ViewDist3k instance with id: " + id);
		try {
			ViewDist3k instance = (ViewDist3k) getSession().get(
							"org.irri.iric.portal.genotype.views.ViewDist3k",
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
	public List findByExample(ViewDist3k instance) {
		log.debug("finding ViewDist3k instance by example");
		try {
			List results =getSession()
					.createCriteria(
							"org.irri.iric.portal.genotype.views.ViewDist3k")
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
	public List findAll() {
		
		String sql = "select var1, var2, irisid1, irisid2, dist from view_dist_3k order by var1, var2";
		return executeSQL(sql);
		/*
		log.debug("finding all Dist3k");
		try {
			List results = getSession()
					.createCriteria(
							"org.irri.iric.portal.variety.views.ViewDist3kId").list();
			log.debug("find all, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
		*/
	}
	
	public List findVarieties(String irislist) {
		
		String sql = "select var1, var2, irisid1, irisid2, dist from view_dist_3k where irisid1 in (" + irislist + ") and irisid2 in (" +
				irislist + ") and irisid1>=irisid2 order by irisid1, irisid2";
		return executeSQL(sql);
		/*
		log.debug("finding all Dist3k");
		try {
			List results = getSession()
					.createCriteria(
							"org.irri.iric.portal.variety.views.ViewDist3kId").list();
			log.debug("find all, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
		*/
	}
	
	@Override
	public List executeSQL(String sql) {
		System.out.println("executing :" + sql);
		//log.info("executing :" + sql);
		return getSession().createSQLQuery(sql).addEntity(ViewDist3kId.class).list();
	}
	
}
