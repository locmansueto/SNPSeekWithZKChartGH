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
import org.irri.iric.portal.chado.postgres.domain.VSnpSpliceacceptor;
import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.MultiReferencePositionImpl;
import org.irri.iric.portal.domain.SnpsSpliceAcceptor;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnpSpliceacceptor entities.
 * 
 */
@Repository("VSnpSpliceacceptorDAO")
@Primary
@Transactional
public class VSnpSpliceacceptorDAOImpl extends AbstractJpaDao<VSnpSpliceacceptor>
		implements VSnpSpliceacceptorDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VSnpSpliceacceptor.class }));

	/**
	 * EntityManager injected by Spring for persistence unit VM_IRIC
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VSnpSpliceacceptorDAOImpl
	 *
	 */
	public VSnpSpliceacceptorDAOImpl() {
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
	 * JPQL Query - findVSnpSpliceacceptorByPrimaryKey
	 *
	 */
	@Transactional
	public VSnpSpliceacceptor findVSnpSpliceacceptorByPrimaryKey(Long snpFeatureId) throws DataAccessException {

		return findVSnpSpliceacceptorByPrimaryKey(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorByPrimaryKey
	 *
	 */

	@Transactional
	public VSnpSpliceacceptor findVSnpSpliceacceptorByPrimaryKey(Long snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpSpliceacceptorByPrimaryKey", startResult, maxRows, snpFeatureId);
			return (org.irri.iric.portal.chado.postgres.domain.VSnpSpliceacceptor) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorBySrcfeatureId
	 *
	 */
	@Transactional
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorBySrcfeatureId(Integer srcfeatureId) throws DataAccessException {

		return findVSnpSpliceacceptorBySrcfeatureId(srcfeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorBySrcfeatureId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorBySrcfeatureId(Integer srcfeatureId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpSpliceacceptorBySrcfeatureId", startResult, maxRows, srcfeatureId);
		return new LinkedHashSet<VSnpSpliceacceptor>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVSnpSpliceacceptors
	 *
	 */
	@Transactional
	public Set<VSnpSpliceacceptor> findAllVSnpSpliceacceptors() throws DataAccessException {

		return findAllVSnpSpliceacceptors(-1, -1);
	}

	/**
	 * JPQL Query - findAllVSnpSpliceacceptors
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSpliceacceptor> findAllVSnpSpliceacceptors(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVSnpSpliceacceptors", startResult, maxRows);
		return new LinkedHashSet<VSnpSpliceacceptor>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorByChr
	 *
	 */
	@Transactional
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByChr(Integer chr) throws DataAccessException {

		return findVSnpSpliceacceptorByChr(chr, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorByChr
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByChr(Integer chr, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpSpliceacceptorByChr", startResult, maxRows, chr);
		return new LinkedHashSet<VSnpSpliceacceptor>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorBySnpFeatureId
	 *
	 */
	@Transactional
	public VSnpSpliceacceptor findVSnpSpliceacceptorBySnpFeatureId(Long snpFeatureId) throws DataAccessException {

		return findVSnpSpliceacceptorBySnpFeatureId(snpFeatureId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorBySnpFeatureId
	 *
	 */

	@Transactional
	public VSnpSpliceacceptor findVSnpSpliceacceptorBySnpFeatureId(Long snpFeatureId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpSpliceacceptorBySnpFeatureId", startResult, maxRows, snpFeatureId);
			return (org.irri.iric.portal.chado.postgres.domain.VSnpSpliceacceptor) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorByPosition
	 *
	 */
	@Transactional
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByPosition(Integer position) throws DataAccessException {

		return findVSnpSpliceacceptorByPosition(position, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorByPosition
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByPosition(Integer position, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpSpliceacceptorByPosition", startResult, maxRows, position);
		return new LinkedHashSet<VSnpSpliceacceptor>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorByOrganismId
	 *
	 */
	@Transactional
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByOrganismId(Integer organismId) throws DataAccessException {

		return findVSnpSpliceacceptorByOrganismId(organismId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpSpliceacceptorByOrganismId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpSpliceacceptor> findVSnpSpliceacceptorByOrganismId(Integer organismId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpSpliceacceptorByOrganismId", startResult, maxRows, organismId);
		return new LinkedHashSet<VSnpSpliceacceptor>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VSnpSpliceacceptor entity) {
		return true;
	}
	
	
	


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

	@Override
	public Set<SnpsSpliceAcceptor> getSNPsBetween(String chr, Integer start, Integer end) throws DataAccessException {
		// TODO Auto-generated method stub
		return findSnpSpliceAcceptorByChrPosBetween(chr,start,end);
	}

	@Override
	public Set<SnpsSpliceAcceptor> getSNPsIn(String chr, Collection listpos) throws DataAccessException {
		// TODO Auto-generated method stub
		if(chr.toLowerCase().equals("any")) {
			
			Set setSNP = new TreeSet();
			Map<String,Set<BigDecimal>> mapContig2Pos = MultiReferencePositionImpl.getMapContig2SNPPos(listpos);
			Iterator<String> itCont = mapContig2Pos.keySet().iterator();
			while(itCont.hasNext()) {
				String cont = itCont.next();
				
				Set sets[] = AppContext.setSlicer(mapContig2Pos.get(cont));
				for(int iset=0; iset<sets.length; iset++) {
					setSNP.addAll(findSnpSpliceAcceptorByChrPosIn(cont, sets[iset]));
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
			//return findSnpSpliceAcceptorByChrPosIn(chr,listpos);
			Set setSNP = new TreeSet();
			Set sets[] = AppContext.setSlicer(new HashSet(listpos));
			for(int iset=0; iset<sets.length; iset++) {
				setSNP.addAll(findSnpSpliceAcceptorByChrPosIn(chr, sets[iset]));
			}
			return setSNP;
		}
	}
	
	
	
}