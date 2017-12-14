package org.irri.iric.portal.chado.postgres.daobackup;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.chado.postgres.domain.VSnpSplicedonor;
import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.MultiReferencePositionImpl;
import org.irri.iric.portal.domain.SnpsSpliceAcceptor;
import org.irri.iric.portal.domain.SnpsSpliceDonor;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnpSplicedonor entities.
 * 
 */
@Repository("VSnpSplicedonorDAO")
@Primary
@Transactional
public class VSnpSplicedonorDAOImpl extends AbstractJpaDao<VSnpSplicedonor> implements VSnpSplicedonorDAO
	{

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VSnpSplicedonor.class }));

	/**
	 * EntityManager injected by Spring for persistence unit VM_IRIC
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VSnpSplicedonorDAOImpl
	 *
	 */
	public VSnpSplicedonorDAOImpl() {
		super();
	}

	/**
	 * Get the entity manager that manages persistence unit 
	 *
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Returns the set of entity classes managed by this DAO.
	 *
	 */
	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	/**
	 * JPQL Query - findVSnpSplicedonorBySrcfeatureId
	 *
	 */
	@Transactional
	public Set<VSnpSplicedonor> findVSnpSplicedonorBySrcfeatureId(Integer srcfeatureId) throws DataAccessException {

		return findVSnpSplicedonorBySrcfeatureId(srcfeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSplicedonorBySrcfeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSplicedonor> findVSnpSplicedonorBySrcfeatureId(Integer srcfeatureId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpSplicedonorBySrcfeatureId", startResult, maxRows, srcfeatureId);
		return new LinkedHashSet<VSnpSplicedonor>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpSplicedonorBySnpFeatureId
	 *
	 */
	@Transactional
	public VSnpSplicedonor findVSnpSplicedonorBySnpFeatureId(Long snpFeatureId) throws DataAccessException {

		return findVSnpSplicedonorBySnpFeatureId(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSplicedonorBySnpFeatureId
	 *
	 */

	@Transactional
	public VSnpSplicedonor findVSnpSplicedonorBySnpFeatureId(Long snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpSplicedonorBySnpFeatureId", startResult, maxRows, snpFeatureId);
			return (org.irri.iric.portal.chado.postgres.domain.VSnpSplicedonor) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpSplicedonorByChr
	 *
	 */
	@Transactional
	public Set<VSnpSplicedonor> findVSnpSplicedonorByChr(Integer chr) throws DataAccessException {

		return findVSnpSplicedonorByChr(chr, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSplicedonorByChr
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSplicedonor> findVSnpSplicedonorByChr(Integer chr, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpSplicedonorByChr", startResult, maxRows, chr);
		return new LinkedHashSet<VSnpSplicedonor>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVSnpSplicedonors
	 *
	 */
	@Transactional
	public Set<VSnpSplicedonor> findAllVSnpSplicedonors() throws DataAccessException {

		return findAllVSnpSplicedonors(-1, -1);
	}

	/**
	 * JPQL Query - findAllVSnpSplicedonors
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSplicedonor> findAllVSnpSplicedonors(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVSnpSplicedonors", startResult, maxRows);
		return new LinkedHashSet<VSnpSplicedonor>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpSplicedonorByOrganismId
	 *
	 */
	@Transactional
	public Set<VSnpSplicedonor> findVSnpSplicedonorByOrganismId(Integer organismId) throws DataAccessException {

		return findVSnpSplicedonorByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSplicedonorByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSplicedonor> findVSnpSplicedonorByOrganismId(Integer organismId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpSplicedonorByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<VSnpSplicedonor>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpSplicedonorByPosition
	 *
	 */
	@Transactional
	public Set<VSnpSplicedonor> findVSnpSplicedonorByPosition(Integer position) throws DataAccessException {

		return findVSnpSplicedonorByPosition(position, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSplicedonorByPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSplicedonor> findVSnpSplicedonorByPosition(Integer position, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpSplicedonorByPosition", startResult, maxRows, position);
		return new LinkedHashSet<VSnpSplicedonor>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpSplicedonorByPrimaryKey
	 *
	 */
	@Transactional
	public VSnpSplicedonor findVSnpSplicedonorByPrimaryKey(Long snpFeatureId) throws DataAccessException {

		return findVSnpSplicedonorByPrimaryKey(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSplicedonorByPrimaryKey
	 *
	 */

	@Transactional
	public VSnpSplicedonor findVSnpSplicedonorByPrimaryKey(Long snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpSplicedonorByPrimaryKey", startResult, maxRows, snpFeatureId);
			return (org.irri.iric.portal.chado.postgres.domain.VSnpSplicedonor) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VSnpSplicedonor entity) {
		return true;
	}
	
	
	

	//@Override
	public Set<SnpsSpliceDonor> findSnpSpliceDonorByChrPosBetween(String chr,
			Integer start, Integer end) throws DataAccessException {
		// TODO Auto-generated method stub

		//Query query = createNamedQuery("findVSnpSplicedonorByChrPositionBetween", -1,-1,  chr, BigDecimal.valueOf(start), BigDecimal.valueOf(end) );
		
		Query query = createNamedQuery("findVSnpSplicedonorByChrPositionBetween", -1,-1, Integer.valueOf(AppContext.guessChrFromString(chr)), start, end );
		
		return new LinkedHashSet<SnpsSpliceDonor>(query.getResultList());
		
	}

	//@Override
	public Set<SnpsSpliceDonor> findSnpSpliceDonorByChrPosIn(String chr,
			Collection listpos) throws DataAccessException {
		// TODO Auto-generated method stub
		//Query query = createNamedQuery("findVSnpSplicedonorByChrPositionIn", -1, -1,  chr, listpos);
		//return new LinkedHashSet<SnpsSpliceDonor>(query.getResultList());
		
		Set setpos=new LinkedHashSet();
		Iterator it=listpos.iterator();
		while(it.hasNext())	setpos.add( Integer.valueOf( it.next().toString()));
		
		Query query = createNamedQuery("findVSnpSplicedonorByChrPositionIn", -1, -1, Integer.valueOf(AppContext.guessChrFromString(chr)), setpos);
		return new LinkedHashSet<SnpsSpliceDonor>(query.getResultList());
		
	}
	
	

/*
	//@Override
	public Set<SnpsSpliceAcceptor> findSnpSpliceAcceptorByChrPosBetween(
			String chr, Integer start, Integer end) throws DataAccessException {
		// TODO Auto-generated method stub
		
		//Query query = createNamedQuery("findVSnpSpliceacceptorByChrPositionBetween", -1,-1, "Chr" + chr, BigDecimal.valueOf(start), BigDecimal.valueOf(end) );
		Query query = createNamedQuery("findVSnpSpliceacceptorByChrPositionBetween", -1,-1, Integer.valueOf(AppContext.guessChrFromString(chr)), start, end );
		return new LinkedHashSet<SnpsSpliceAcceptor>(query.getResultList());
	}

	//@Override
	public Set<SnpsSpliceAcceptor> findSnpSpliceAcceptorByChrPosIn(String chr,
			Collection listpos) throws DataAccessException {
		// TODO Auto-generated method stub
		//Query query = createNamedQuery("findVSnpSpliceacceptorByChrPositionIn", -1, -1, "Chr" + chr, listpos);
		
		Set setpos=new LinkedHashSet();
		Iterator it=listpos.iterator();
		while(it.hasNext())	setpos.add( Integer.valueOf( it.next().toString()));
		
		Query query = createNamedQuery("findVSnpSpliceacceptorByChrPositionIn", -1, -1, Integer.valueOf(AppContext.guessChrFromString(chr)), setpos);
		return new LinkedHashSet<SnpsSpliceAcceptor>(query.getResultList());
	}
*/
	
	@Override
	public Set<SnpsSpliceDonor> getSNPsBetween(String chr, Integer start, Integer end) throws DataAccessException {
		// TODO Auto-generated method stub
		return findSnpSpliceDonorByChrPosBetween(chr,start,end);
	}

	@Override
	public Set<SnpsSpliceDonor> getSNPsIn(String chr, Collection listpos)
			throws DataAccessException {
		// TODO Auto-generated method stub
		if(chr.toLowerCase().equals("any")) {
			
			Set setSNP = new TreeSet();
			Map<String,Set<BigDecimal>> mapContig2Pos = MultiReferencePositionImpl.getMapContig2SNPPos(listpos);
			Iterator<String> itCont = mapContig2Pos.keySet().iterator();
			while(itCont.hasNext()) {
				String cont = itCont.next();
				//setSNP.addAll(findSnpSpliceDonorByChrPosIn(cont,mapContig2Pos.get(cont)));	
				
				Set sets[] = AppContext.setSlicer(mapContig2Pos.get(cont));
				for(int iset=0; iset<sets.length; iset++) {
					setSNP.addAll(findSnpSpliceDonorByChrPosIn(cont, sets[iset]));
				}
			}
			return setSNP;
		}
		else if(chr.toLowerCase().equals("loci")) {
			Iterator<Locus> it = listpos.iterator();
			//StringBuffer buff = new StringBuffer();
			Set setPos = new TreeSet();
			while(it.hasNext()) {
				Locus loc=it.next();
				setPos.addAll(  getSNPsBetween(loc.getContig(),  loc.getFmin(),  loc.getFmax()) ); 
			}
			return setPos;
		} else {
			//return findSnpSpliceDonorByChrPosIn(chr,listpos);
			Set retSet = new TreeSet();
			Set sets[] = AppContext.setSlicer(new HashSet(listpos));
			for(int iset=0; iset<sets.length; iset++) {
				retSet.addAll(findSnpSpliceDonorByChrPosIn(chr, sets[iset]));
			}
			return retSet;
		}
	}
}
