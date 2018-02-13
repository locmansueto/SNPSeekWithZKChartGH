package org.irri.iric.portal.chado.oracle.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.chado.oracle.domain.VSnpGenotypeRDBMS;
import org.irri.iric.portal.chado.oracle.domain.VSnpRefposindex;
import org.irri.iric.portal.dao.ScaffoldDAO;
import org.irri.iric.portal.dao.SnpsAllvarsPosDAO;
import org.irri.iric.portal.domain.GenotypeRunPlatform;
import org.irri.iric.portal.domain.MultiReferencePositionImpl;
import org.irri.iric.portal.domain.Position;
import org.irri.iric.portal.domain.Scaffold;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage VSnpGenotypeRDBMS entities.
 * 
 */
@Repository("VSnpGenotypeRDBMSDAO")
@Transactional
public class VSnpGenotypeRDBMSDAOImpl extends AbstractJpaDao<VSnpGenotypeRDBMS>
		implements VSnpGenotypeRDBMSDAO {

	//@Autowired
	//private ScaffoldDAO scafdao; 
	
	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { VSnpGenotypeRDBMS.class }));

	/**
	 * EntityManager injected by Spring for persistence unit IRIC_Production
	 *
	 */
	@PersistenceContext(unitName = "IRIC_Production")
	private EntityManager entityManager;

	/**
	 * Instantiates a new VSnpGenotypeRDBMSDAOImpl
	 *
	 */
	public VSnpGenotypeRDBMSDAOImpl() {
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
	 * JPQL Query - findVSnpGenotypeRDBMSByRDBMSStockId
	 *
	 */
	@Transactional
	public VSnpGenotypeRDBMS findVSnpGenotypeRDBMSByRDBMSStockId(BigDecimal RDBMSStockId) throws DataAccessException {

		return findVSnpGenotypeRDBMSByRDBMSStockId(RDBMSStockId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByRDBMSStockId
	 *
	 */

	@Transactional
	public VSnpGenotypeRDBMS findVSnpGenotypeRDBMSByRDBMSStockId(BigDecimal RDBMSStockId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpGenotypeRDBMSByRDBMSStockId", startResult, maxRows, RDBMSStockId);
			return (org.irri.iric.portal.chado.oracle.domain.VSnpGenotypeRDBMS) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByRefcallContaining
	 *
	 */
	@Transactional
	public Set<VSnpGenotypeRDBMS> findVSnpGenotypeRDBMSByRefcallContaining(String refcall) throws DataAccessException {

		return findVSnpGenotypeRDBMSByRefcallContaining(refcall, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByRefcallContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpGenotypeRDBMS> findVSnpGenotypeRDBMSByRefcallContaining(String refcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpGenotypeRDBMSByRefcallContaining", startResult, maxRows, refcall);
		return new LinkedHashSet<VSnpGenotypeRDBMS>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllVSnpGenotypeRDBMSs
	 *
	 */
	@Transactional
	public Set<VSnpGenotypeRDBMS> findAllVSnpGenotypeRDBMSs() throws DataAccessException {

		return findAllVSnpGenotypeRDBMSs(-1, -1);
	}

	/**
	 * JPQL Query - findAllVSnpGenotypeRDBMSs
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpGenotypeRDBMS> findAllVSnpGenotypeRDBMSs(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllVSnpGenotypeRDBMSs", startResult, maxRows);
		return new LinkedHashSet<VSnpGenotypeRDBMS>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByAllele2Containing
	 *
	 */
	@Transactional
	public Set<VSnpGenotypeRDBMS> findVSnpGenotypeRDBMSByAllele2Containing(String allele2) throws DataAccessException {

		return findVSnpGenotypeRDBMSByAllele2Containing(allele2, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByAllele2Containing
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpGenotypeRDBMS> findVSnpGenotypeRDBMSByAllele2Containing(String allele2, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpGenotypeRDBMSByAllele2Containing", startResult, maxRows, allele2);
		return new LinkedHashSet<VSnpGenotypeRDBMS>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByMismatch
	 *
	 */
	@Transactional
	public Set<VSnpGenotypeRDBMS> findVSnpGenotypeRDBMSByMismatch(BigDecimal mismatch) throws DataAccessException {

		return findVSnpGenotypeRDBMSByMismatch(mismatch, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByMismatch
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpGenotypeRDBMS> findVSnpGenotypeRDBMSByMismatch(BigDecimal mismatch, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpGenotypeRDBMSByMismatch", startResult, maxRows, mismatch);
		return new LinkedHashSet<VSnpGenotypeRDBMS>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByAllele1Containing
	 *
	 */
	@Transactional
	public Set<VSnpGenotypeRDBMS> findVSnpGenotypeRDBMSByAllele1Containing(String allele1) throws DataAccessException {

		return findVSnpGenotypeRDBMSByAllele1Containing(allele1, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByAllele1Containing
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpGenotypeRDBMS> findVSnpGenotypeRDBMSByAllele1Containing(String allele1, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpGenotypeRDBMSByAllele1Containing", startResult, maxRows, allele1);
		return new LinkedHashSet<VSnpGenotypeRDBMS>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByAllele2
	 *
	 */
	@Transactional
	public Set<VSnpGenotypeRDBMS> findVSnpGenotypeRDBMSByAllele2(String allele2) throws DataAccessException {

		return findVSnpGenotypeRDBMSByAllele2(allele2, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByAllele2
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpGenotypeRDBMS> findVSnpGenotypeRDBMSByAllele2(String allele2, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpGenotypeRDBMSByAllele2", startResult, maxRows, allele2);
		return new LinkedHashSet<VSnpGenotypeRDBMS>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByRefcall
	 *
	 */
	@Transactional
	public Set<VSnpGenotypeRDBMS> findVSnpGenotypeRDBMSByRefcall(String refcall) throws DataAccessException {

		return findVSnpGenotypeRDBMSByRefcall(refcall, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByRefcall
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpGenotypeRDBMS> findVSnpGenotypeRDBMSByRefcall(String refcall, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpGenotypeRDBMSByRefcall", startResult, maxRows, refcall);
		return new LinkedHashSet<VSnpGenotypeRDBMS>(query.getResultList());
	}

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByPrimaryKey
	 *
	 */
	@Transactional
	public VSnpGenotypeRDBMS findVSnpGenotypeRDBMSByPrimaryKey(BigDecimal RDBMSStockId) throws DataAccessException {

		return findVSnpGenotypeRDBMSByPrimaryKey(RDBMSStockId, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByPrimaryKey
	 *
	 */

	@Transactional
	public VSnpGenotypeRDBMS findVSnpGenotypeRDBMSByPrimaryKey(BigDecimal RDBMSStockId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findVSnpGenotypeRDBMSByPrimaryKey", startResult, maxRows, RDBMSStockId);
			return (org.irri.iric.portal.chado.oracle.domain.VSnpGenotypeRDBMS) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByAllele1
	 *
	 */
	@Transactional
	public Set<VSnpGenotypeRDBMS> findVSnpGenotypeRDBMSByAllele1(String allele1) throws DataAccessException {

		return findVSnpGenotypeRDBMSByAllele1(allele1, -1, -1);
	}

	/**
	 * JPQL Query - findVSnpGenotypeRDBMSByAllele1
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<VSnpGenotypeRDBMS> findVSnpGenotypeRDBMSByAllele1(String allele1, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findVSnpGenotypeRDBMSByAllele1", startResult, maxRows, allele1);
		return new LinkedHashSet<VSnpGenotypeRDBMS>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(VSnpGenotypeRDBMS entity) {
		return true;
	}
	
	
/*
	private List executeSQL(String sql) 
	{	if(AppContext.isLocalhost()) AppContext.debug("executing :" + sql);
		//log.info("executing :" + sql);
		return  getSession().createSQLQuery(sql).addEntity(VSnpGenotypeRDBMS.class).list();
		
	}
	*/

	private List executeSQL(String sql) 
	{	if(AppContext.isIRRILAN()) AppContext.debug("executing :" + sql);
		//log.info("executing :" + sql);
		
		List listResult=new ArrayList(); 
		List<Object[]> listobj= getSession().createSQLQuery(sql).addScalar("stock_sample_id",Hibernate.LONG).addScalar("allele1",Hibernate.STRING).addScalar("allele2",Hibernate.STRING)
				 .list();
		
		AppContext.debug(listobj.size() + " rows");
		
		Iterator<Object[]> itobj=listobj.iterator();
		while(itobj.hasNext()) {
			Object[] objsc=itobj.next();
			VSnpGenotypeRDBMS vargen=new VSnpGenotypeRDBMS();
			vargen.setRDBMSStockId(BigDecimal.valueOf((Long)objsc[0]));
			vargen.setAllele1((String)objsc[1]);
			vargen.setAllele2((String)objsc[2]);
			listResult.add(vargen);
		}
		return listResult;
		
	}

	
	private List executeSQL2(String sql) 
	{	if(AppContext.isLocalhost()) AppContext.debug("executing using jdbc :" + sql);

		//getSession().createSQLQuery(sql)
			
		Connection con = null;
		Statement stmt = null;

		List listResult=null; 
		try {

			con = AppContext.getJdbcConn();
			
			if(con==null) {
				//con = getSession().connection(); // AppContext.getJdbcConn();
				Properties properties = new Properties(); // Create Properties object
				properties.put("user", "iricguest");         // Set user ID for connection
				properties.put("password", "iricguest");
				properties.put("maxIdle",16);
				properties.put("maxIdle",32);
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@172.29.4.231:1521:orcl",properties);
			}
			stmt = con.createStatement();
			AppContext.setJdbcConn(con);

			ResultSet rs = stmt.executeQuery(sql);
			listResult = new ArrayList<VSnpGenotypeRDBMS>();
			while(rs.next()) {
				
				VSnpGenotypeRDBMS vargen=new VSnpGenotypeRDBMS();
				vargen.setRDBMSStockId( rs.getBigDecimal("stock_sample_id"));
				vargen.setAllele1(rs.getString("allele1"));
				vargen.setAllele2(rs.getString("allele2"));
				vargen.setMismatch(rs.getBigDecimal("mismatch"));
				vargen.setRefcall(rs.getString("refcall"));
				listResult.add(vargen);
			}
			rs.close();
			stmt.close();
			con.close();
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		catch(Exception ex) {
			ex.printStackTrace();

		} finally {
			  try{
			         if(stmt!=null)
			            stmt.close();
			      }catch(SQLException se2){
			      }// nothing we can do
			      try{
			         if(con!=null)
			            con.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }
		}
		
		return listResult;
		
	/*
		//log.info("executing :" + sql);
		return  getSession().createSQLQuery(sql).addEntity(VSnpGenotypeRDBMS.class).list();
		*/
	}

	
	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	
	@Override
	public Map[] readSNPString(GenotypeRunPlatform run,String chr, List<SnpsAllvarsPos> listpos) {
		return readSNPString(run, null,chr,listpos); 
	}
	
	@Override
	public Map[] readSNPString(GenotypeRunPlatform run, Set<BigDecimal> colVarids, String chr, List<SnpsAllvarsPos> listpos) {
		if(chr.toLowerCase().equals("any") || chr.toLowerCase().equals("loci")) {

			Map<BigDecimal,StringBuffer> mapVar2StrAllele1=new LinkedHashMap();
			Map<BigDecimal,StringBuffer> mapVar2StrAllele2=new LinkedHashMap();

			Map<String,List<SnpsAllvarsPos>> mapChr2Pos = MultiReferencePositionImpl.getMapContig2SNPPos(listpos);
			Iterator<String> itConts=mapChr2Pos.keySet().iterator();
			while(itConts.hasNext()) {
				String cont=itConts.next();
				Map mapVar2Strs[] = _readSNPString(run, colVarids, cont,mapChr2Pos.get(cont));
				
				Iterator<BigDecimal> itVarid= mapVar2Strs[0].keySet().iterator();
				while(itVarid.hasNext()) {
					BigDecimal varid=itVarid.next();
					StringBuffer buff=mapVar2StrAllele1.get(varid);
					if(buff==null) {
						buff=new StringBuffer();
						mapVar2StrAllele1.put(varid, buff);
					}
					buff.append(  mapVar2Strs[0].get(varid) );
					
					buff=mapVar2StrAllele2.get(varid);
					if(buff==null) {
						buff=new StringBuffer();
						mapVar2StrAllele2.put(varid, buff);
					}
					buff.append(  mapVar2Strs[1].get(varid) );
				}
			}
			
			Map mapVar2StrAllele1s=new LinkedHashMap();
			Map mapVar2StrAllele2s=new LinkedHashMap();
			Iterator<BigDecimal> itVarid=mapVar2StrAllele1.keySet().iterator();
			while(itVarid.hasNext()) {
				BigDecimal varid=itVarid.next();
				mapVar2StrAllele1s.put(varid, mapVar2StrAllele1.get(varid).toString() );		
				mapVar2StrAllele2s.put(varid, mapVar2StrAllele2.get(varid).toString() );
			}
			
			AppContext.debug("readSNPString listpos.length=" + listpos.size() + ", mapVar2StrAllele1s.length=" +  ((String)mapVar2StrAllele1s.values().iterator().next()).length() + 		
					", mapVar2StrAllele2s.length=" +  ((String)mapVar2StrAllele2s.values().iterator().next()).length() );

			
			return new Map[] {mapVar2StrAllele1s, mapVar2StrAllele2s};
			
		} else {
			return _readSNPString(run, colVarids, chr, listpos);	
		}
	}
	
	private Map[] _readSNPString(GenotypeRunPlatform run,Collection colVarids, String chr, List<SnpsAllvarsPos> listpos) {
		//return _readSNPStringOracle(colVarids, chr, listpos);
		
		if(AppContext.isOracle()) return _readSNPStringOracle(colVarids, chr, listpos);
		if(AppContext.isPostgres()) return _readSNPStringPostgres(run, colVarids, chr, listpos);
		return null;
		
	}
	
	private Map[] _readSNPStringOracle(Collection colVarids, String chr, List<SnpsAllvarsPos> listpos) {
		// TODO Auto-generated method stub
		

		
		//scafdao = (ScaffoldDAO)AppContext.checkBean(scafdao,"ScaffoldDAO");
		//Scaffold contig= scafdao.getScaffold(chr, AppContext.getDefaultOrganism());
		
		Map<BigDecimal,StringBuffer> mapVar2StrAllele1=new LinkedHashMap();
		Map<BigDecimal,StringBuffer> mapVar2StrAllele2=new LinkedHashMap();

		Set setVarids=null;
		Set varidset[] = null;
		
		if(colVarids!=null && colVarids.isEmpty()) colVarids=null;
		if(colVarids!=null) {
			setVarids=new LinkedHashSet(colVarids);
			varidset = AppContext.setSlicer(setVarids, 1000);
		}
		
		
		for(int ivar=0; colVarids==null || ivar<varidset.length; ivar++) {

			StringBuffer sqlVarids=new StringBuffer();
			
			if(varidset!=null) {
				Iterator<BigDecimal> itVarid=varidset[ivar].iterator();
				while(itVarid.hasNext()) {
					BigDecimal varid=itVarid.next();
					sqlVarids.append(varid);
					if(itVarid.hasNext()){
						sqlVarids.append(",");
					} else 
						AppContext.debug(varidset[ivar].size() + " varidn=" + varid);
				}
				
			} else AppContext.debug("varid=all");
			
			Set setposall= new LinkedHashSet(listpos);
			Set posset[] = AppContext.setSlicer(setposall, 450);
			
			int totallenin=0;
			int totallen=0;
				
				for(int ipos=0; ipos<posset.length ; ipos++) {
		
		

					StringBuffer sqlAl1=new StringBuffer();
					StringBuffer sqlAl2=new StringBuffer();
					StringBuffer sqlPivot=new StringBuffer();
					StringBuffer sqlIds=new StringBuffer();
					Iterator<SnpsAllvarsPos> itPos=posset[ipos].iterator();
					//Iterator itPos=posset[ipos].iterator();
					while(itPos.hasNext()) {
						//Position pos=itPos.next();
						SnpsAllvarsPos pos=itPos.next();
						
						sqlAl1.append( "p").append(pos.getPosition()).append("_al1");
						sqlAl2.append( "p").append(pos.getPosition()).append("_al2");
						sqlPivot.append(pos.getPosition()).append(" p").append(pos.getPosition());
						sqlIds.append( pos.getSnpFeatureId() );
						
						if(itPos.hasNext()) {
							sqlAl1.append("||");
							sqlAl2.append("||");
							sqlPivot.append(",");
							sqlIds.append(",");
						} else {
							AppContext.debug(posset[ipos].size() + " posn=" +  pos);
						}
					}
					//"and exists (select column_value from table(sys.odcinumberlist(" + slicedset[iset].toString().replace("[", "").replace("]", "") + " )) t"
					
					/*
					with names (fname,lname) as (
							    values
							        ('John','Smith'),
							        ('Mary','Jones')
							)
						select city from user
							    inner join names on
							        fname=firstName and
							        lname=lastName;
							        */
					/*
					String sql="select stock_sample_id, REPLACE(" + sqlAl1 + ", '0','?') allele1, REPLACE(" + sqlAl2 + ",'0','?')  allele2 ,\r\n" + 
							" ''  refcall  from (\r\n" + 
							"select stock_sample_id, sfl.srcfeature_id-2 chromosome, sfl.position+1 position, allele1, allele2, refcall from " + AppContext.getDefaultSchema() + ".snp_genotype sg\r\n" + 
							//"where sfl.SNP_FEATURE_ID=sg.SNP_FEATURE_ID and sfl.srcfeature_id=3 and sfl.position+1 between 10000 and 13000 \r\n" + 
							"where  exists (select column_value from table(sys.odcinumberlist(" + sqlIds + ")) t where t.column_value=sg.snp_feature_id) \r\n" +
							")\r\n" + 
							"pivot\r\n" + 
							"(   min(allele1) al1, min(allele2) al2 , min(refcall) refcall   \r\n" + 
							"    for position in (" + sqlPivot +  ")\r\n" + 
							") order by stock_sample_id";
							*/
					//String sql="select stock_sample_id, cast(trim( REPLACE(" + sqlAl1 + ", '0','?')) as varchar2(450)) as allele1, cast(trim(REPLACE(" + sqlAl2 +  ",'0','?')) as varchar2(450)) as allele2, 0 mismatch, null refcall  \r\n" + 
					String sql="select stock_sample_id stock_sample_id, REPLACE(" + sqlAl1 + ", '0','?') allele1, REPLACE(" + sqlAl2 +  ",'0','?') allele2, 0 mismatch, null refcall  \r\n" +
							" from (\r\n" + 
							"select sg.stock_sample_id, sfl.srcfeature_id-" + AppContext.chr2srcfeatureidOffset()+ " chromosome, sfl.position+1 position, sg.allele1, sg.allele2 from " + AppContext.getDefaultSchema() + ".snp_genotype sg, " + AppContext.getDefaultSchema() + ".snp_featureloc sfl \r\n" + 
							"where  exists (select column_value from table(sys.odcinumberlist(" + sqlIds + ")) t where t.column_value=sg.snp_feature_id) \r\n";
							
						if(colVarids!=null)
							sql+= " and exists (select column_value from table(sys.odcinumberlist(" + sqlVarids + ")) u where u.column_value=sg.stock_sample_id) \r\n";
						
							sql +="and sfl.snp_feature_id=sg.snp_feature_id\r\n" + 
							")\r\n" + 
							"pivot\r\n" + 
							"(   min(allele1) al1, min(allele2) al2 \r\n" + 
							"    for position in (" + sqlPivot+ ")\r\n" + 
							") order by stock_sample_id";
					
					totallenin+=posset[ipos].size();
					Iterator<VSnpGenotypeRDBMS> itStr=executeSQL(sql).iterator();
					StringBuffer buffError=new StringBuffer();
					while(itStr.hasNext()) {
						VSnpGenotypeRDBMS RDBMSstr=itStr.next();
						
						if(colVarids!=null && !colVarids.contains(RDBMSstr.getRDBMSStockId())) continue;
						
						if(RDBMSstr.getAllele1()!=null && RDBMSstr.getAllele2()!=null ) {
							if(RDBMSstr.getAllele1().length()!=RDBMSstr.getAllele2().length()) {
								AppContext.debug(RDBMSstr.toString());
								throw new RuntimeException("str.getAllele1().length()!=str.getAllele2().length() " +RDBMSstr.getAllele1().length()+","+ RDBMSstr.getAllele2().length()+" for varid=" + RDBMSstr.getRDBMSStockId() );
							}
							//AppContext.debug(str.toString());
							
							if( (posset[ipos].size()!=RDBMSstr.getAllele1().length() || posset[ipos].size()!=RDBMSstr.getAllele2().length())) {
								buffError.append(RDBMSstr.toString()).append("\n");
								if(!itStr.hasNext()) {
									AppContext.debug(buffError.toString());
									AppContext.debug("posset[ipos].size()!=str.getAllele1().length(): " + posset[ipos].size()+ ", " + RDBMSstr.getAllele1().length());
								}
								//throw new RuntimeException("posset[ipos].size()!=str.getAllele1().length(): " + posset[ipos].size()+ ", " + str.getAllele1().length());
							}
							
							
						}
		
						StringBuffer buff=mapVar2StrAllele1.get(RDBMSstr.getRDBMSStockId());
						if(buff==null) {
							buff=new StringBuffer();
							mapVar2StrAllele1.put(RDBMSstr.getRDBMSStockId(), buff);
						}
						
						String stral1=null;
						try {
							stral1=RDBMSstr.getAllele1().substring(0,  posset[ipos].size());
						} catch(Exception ex) {
							AppContext.debug(buffError.toString());

							AppContext.debug(RDBMSstr.toString() +"  posset[ipos].size()=" + posset[ipos].size() +" RDBMSstr.getAllele1().length()=" + RDBMSstr.getAllele1().length());
							
							throw new RuntimeException(ex);
						}
						if(!itStr.hasNext()) totallen+=stral1.length();
						buff.append( stral1 );
						
						buff=mapVar2StrAllele2.get(RDBMSstr.getRDBMSStockId());
						if(buff==null) {
							buff=new StringBuffer();
							mapVar2StrAllele2.put(RDBMSstr.getRDBMSStockId(), buff);
						}
		
						String stral2=null;
						try {
							stral2=RDBMSstr.getAllele2().substring(0,  posset[ipos].size());
						} catch(Exception ex) {
							AppContext.debug(buffError.toString());

							AppContext.debug(RDBMSstr.toString() +"  posset[ipos].size()=" + posset[ipos].size() +" RDBMSstr.getAllele2().length()=" + RDBMSstr.getAllele2().length());
							throw new RuntimeException(ex);
						}
						buff.append(stral2);
						
		
						/*
						if(posset[ipos].size()!=str.getAllele1().length()) 
							AppContext.debug("posset[ipos].size()!=str.getAllele1().length() " + posset[ipos].size() + "," + str.getAllele1().length() + " varid="+str.getRDBMSStockId());
						
						StringBuffer buff=mapVar2StrAllele1.get(str.getRDBMSStockId());
						if(buff==null) {
							buff=new StringBuffer();
							mapVar2StrAllele1.put(str.getRDBMSStockId(), buff);
						}
						buff.append(  (str.getAllele1()==null||str.getAllele1().isEmpty()?"?":str.getAllele1()) );
						
						buff=mapVar2StrAllele2.get(str.getRDBMSStockId());
						if(buff==null) {
							buff=new StringBuffer();
							mapVar2StrAllele2.put(str.getRDBMSStockId(), buff);
						}
						buff.append(  (str.getAllele2()==null||str.getAllele2().isEmpty()?"?":str.getAllele2()) );
						*/
					}
				}
		
					AppContext.debug("vars=" + (varidset==null?"all":varidset[ivar].size())+ ", setposall=" + setposall.size() + ",totallenin=" + totallenin + ",totallen=" + totallen);
		
					
					if(colVarids==null) break;
		}
			
		Map mapVar2StrAllele1s=new LinkedHashMap();
		Map mapVar2StrAllele2s=new LinkedHashMap();
		Iterator<BigDecimal> itVarid=mapVar2StrAllele1.keySet().iterator();
		while(itVarid.hasNext()) {
			BigDecimal varid=itVarid.next();
			mapVar2StrAllele1s.put(varid, mapVar2StrAllele1.get(varid).toString() );		
			mapVar2StrAllele2s.put(varid, mapVar2StrAllele2.get(varid).toString() );
		}
		/*
		AppContext.debug("_readSNPString setposall.length=" + setposall.size() + ", mapVar2StrAllele1s.length=" +  ((String)mapVar2StrAllele1s.values().iterator().next()).length() + 		
				", mapVar2StrAllele2s.length=" +  ((String)mapVar2StrAllele2s.values().iterator().next()).length() );
		if( setposall.size()!=((String)mapVar2StrAllele2s.values().iterator().next()).length() || setposall.size()!=((String)mapVar2StrAllele2s.values().iterator().next()).length())
			throw new RuntimeException("lengths not equal");
			*/
		
		return new Map[] {mapVar2StrAllele1s, mapVar2StrAllele2s};
	}
	
	
	private Map[] _readSNPStringPostgres(GenotypeRunPlatform run, Collection colVarids, String chr, List<SnpsAllvarsPos> listpos) {
		// TODO Auto-generated method stub
		
		Map<BigDecimal,StringBuffer> mapVar2StrAllele1=new LinkedHashMap();
		Map<BigDecimal,StringBuffer> mapVar2StrAllele2=new LinkedHashMap();

		Set setVarids=null;
		Set varidset[] = null;
		
		if(colVarids!=null && colVarids.isEmpty()) colVarids=null;
		if(colVarids!=null) {
			setVarids=new LinkedHashSet(colVarids);
			varidset = AppContext.setSlicer(setVarids, 1000);
		}
		
/*		

select stock_sample_id+3024 stock_sample_id, replace(p1_69679[1]||p1_69809[1]||p1_69856[1], '0','?') allele1, replace(p1_69679[2]||p1_69809[2]||p1_69856[2], '0','?') allele2, 0 mismatch, null refcall  
 from crosstab(
'select sg.stock_sample_id, ''p'' ||(sfl.srcfeature_id-2)|| ''_'' ||(sfl.position+1) "position", ARRAY[sg.allele1, sg.allele2] allele12 from public.snp_genotype sg, public.snp_featureloc sfl 
where  exists ( select t.column_value from (select unnest(ARRAY[110,111,112])column_value) t where t.column_value=sg.snp_feature_id) 
and sfl.snp_feature_id=sg.snp_feature_id order by 1,2'::text)
as final_result(stock_sample_id numeric, p1_69679 character varying[],p1_69809 character varying[],p1_69856 character varying[]);


*/
		
		for(int ivar=0; colVarids==null || ivar<varidset.length; ivar++) {

			StringBuffer sqlVarids=new StringBuffer();
			
			if(varidset!=null) {
				Iterator<BigDecimal> itVarid=varidset[ivar].iterator();
				while(itVarid.hasNext()) {
					BigDecimal varid=itVarid.next();
					sqlVarids.append(varid);
					if(itVarid.hasNext()){
						sqlVarids.append(",");
					} else 
						AppContext.debug(varidset[ivar].size() + " varidn=" + varid);
				}
				
			} else AppContext.debug("varid=all");
			
			Set setposall= new LinkedHashSet(listpos);
			Set posset[] = AppContext.setSlicer(setposall, 450);
			
			int totallenin=0;
			int totallen=0;
				
				for(int ipos=0; ipos<posset.length ; ipos++) {
		
		

					StringBuffer sqlAl1=new StringBuffer();
					StringBuffer sqlAl2=new StringBuffer();
					StringBuffer sqlPivot=new StringBuffer();
					StringBuffer sqlIds=new StringBuffer();
					Iterator<SnpsAllvarsPos> itPos=posset[ipos].iterator();
					//Iterator itPos=posset[ipos].iterator();
					while(itPos.hasNext()) {
						//Position pos=itPos.next();
						SnpsAllvarsPos pos=itPos.next();
						
						sqlAl1.append( "p").append(pos.getChr() +"_"+ pos.getPosition()).append("[1]");
						sqlAl2.append( "p").append(pos.getChr() +"_"+ pos.getPosition()).append("[2]");

						//sqlAl1.append( "foo.allele12[0]"); //.append(pos.getChr() +"_"+ pos.getPosition()).append("_al1");
						//sqlAl2.append( "p").append(pos.getChr() +"_"+ pos.getPosition()).append("_al2");

						
						sqlPivot.append(" p").append(pos.getChr() +"_"+pos.getPosition()).append(" character(1)[]");
						sqlIds.append( pos.getSnpFeatureId().longValue() );
						
						if(itPos.hasNext()) {
							sqlAl1.append("||");
							sqlAl2.append("||");
							sqlPivot.append(",");
							sqlIds.append(",");
						} else {
							AppContext.debug(posset[ipos].size() + " posn=" +  pos);
						}
					}
					
					String sql="select stock_sample_id , replace(" + sqlAl1 + ", '0','?') allele1, replace(" + sqlAl2 +  ",'0','?') allele2, 0 mismatch, null refcall  \r\n" +
							" from crosstab(\r\n" + 
							"'select cast(sg.stock_sample_id as numeric) stock_sample_id, ''p''||(sfl.srcfeature_id-" + AppContext.chr2srcfeatureidOffset() + ")||''_''||(sfl.position+1) \"position\", ARRAY[sg.allele1, sg.allele2] allele12 from " + AppContext.getDefaultSchema() + ".snp_genotype sg, " + AppContext.getDefaultSchema() + ".snp_featureloc sfl \r\n" + 
					//		"where  exists (select column_value from table(sys.odcinumberlist(" + sqlIds + ")) t where t.column_value=sg.snp_feature_id) \r\n";
					"where  exists ( select t.column_value from (select unnest(ARRAY[" +  sqlIds + "])column_value) t where t.column_value=sg.snp_feature_id) \r\n";

					
					
						if(colVarids!=null)
							//sql+= " and exists (select column_value from table(sys.odcinumberlist(" + sqlVarids + ")) u where u.column_value=sg.stock_sample_id+3024) \r\n";
							sql+= " and exists (select u.column_value from (select unnest(ARRAY[" + sqlVarids + "])column_value) u where u.column_value=sg.stock_sample_id) \r\n";
						
					sql +=" and sg.genotype_run_id=" + run.getGenotypeRunId() + " and sfl.snp_feature_id=sg.snp_feature_id order by 1,2' \r\n" + 
					")\r\n" + 
					" as final_result(stock_sample_id numeric, " + sqlPivot + ")\r\n" + 
					" order by stock_sample_id";
					
					
					totallenin+=posset[ipos].size();
					Iterator<VSnpGenotypeRDBMS> itStr=executeSQL(sql).iterator();
					StringBuffer buffError=new StringBuffer();
					while(itStr.hasNext()) {
						VSnpGenotypeRDBMS RDBMSstr=itStr.next();
						
						if(colVarids!=null && !colVarids.contains(RDBMSstr.getRDBMSStockId())) continue;
						
						if(RDBMSstr.getAllele1()!=null && RDBMSstr.getAllele2()!=null ) {
							if(RDBMSstr.getAllele1().length()!=RDBMSstr.getAllele2().length()) {
								AppContext.debug(RDBMSstr.toString());
								throw new RuntimeException("str.getAllele1().length()!=str.getAllele2().length() " +RDBMSstr.getAllele1().length()+","+ RDBMSstr.getAllele2().length()+" for varid=" + RDBMSstr.getRDBMSStockId() );
							}
							//AppContext.debug(str.toString());
							
							if( (posset[ipos].size()!=RDBMSstr.getAllele1().length() || posset[ipos].size()!=RDBMSstr.getAllele2().length())) {
								buffError.append(RDBMSstr.toString()).append("\n");
								if(!itStr.hasNext()) {
									AppContext.debug(buffError.toString());
									AppContext.debug("posset[ipos].size()!=str.getAllele1().length(): " + posset[ipos].size()+ ", " + RDBMSstr.getAllele1().length());
								}
								//throw new RuntimeException("posset[ipos].size()!=str.getAllele1().length(): " + posset[ipos].size()+ ", " + str.getAllele1().length());
							}
							
							
						}
		
						StringBuffer buff=mapVar2StrAllele1.get(RDBMSstr.getRDBMSStockId());
						if(buff==null) {
							buff=new StringBuffer();
							mapVar2StrAllele1.put(RDBMSstr.getRDBMSStockId(), buff);
						}
						
						String stral1=null;
						try {
							stral1=RDBMSstr.getAllele1().substring(0,  posset[ipos].size());
						} catch(Exception ex) {
							AppContext.debug(buffError.toString());

							AppContext.debug(RDBMSstr.toString() +"  posset[ipos].size()=" + posset[ipos].size() +" RDBMSstr.getAllele1().length()=" + RDBMSstr.getAllele1().length());
							
							throw new RuntimeException(ex);
						}
						if(!itStr.hasNext()) totallen+=stral1.length();
						buff.append( stral1 );
						
						buff=mapVar2StrAllele2.get(RDBMSstr.getRDBMSStockId());
						if(buff==null) {
							buff=new StringBuffer();
							mapVar2StrAllele2.put(RDBMSstr.getRDBMSStockId(), buff);
						}
		
						String stral2=null;
						try {
							stral2=RDBMSstr.getAllele2().substring(0,  posset[ipos].size());
						} catch(Exception ex) {
							AppContext.debug(buffError.toString());

							AppContext.debug(RDBMSstr.toString() +"  posset[ipos].size()=" + posset[ipos].size() +" RDBMSstr.getAllele2().length()=" + RDBMSstr.getAllele2().length());
							throw new RuntimeException(ex);
						}
						buff.append(stral2);
						
					}
				}
		
					AppContext.debug("vars=" + (varidset==null?"all":varidset[ivar].size())+ ", setposall=" + setposall.size() + ",totallenin=" + totallenin + ",totallen=" + totallen);
		
					
					if(colVarids==null) break;
		}
			
		Map mapVar2StrAllele1s=new LinkedHashMap();
		Map mapVar2StrAllele2s=new LinkedHashMap();
		Iterator<BigDecimal> itVarid=mapVar2StrAllele1.keySet().iterator();
		while(itVarid.hasNext()) {
			BigDecimal varid=itVarid.next();
			mapVar2StrAllele1s.put(varid, mapVar2StrAllele1.get(varid).toString() );		
			mapVar2StrAllele2s.put(varid, mapVar2StrAllele2.get(varid).toString() );
		}
		/*
		AppContext.debug("_readSNPString setposall.length=" + setposall.size() + ", mapVar2StrAllele1s.length=" +  ((String)mapVar2StrAllele1s.values().iterator().next()).length() + 		
				", mapVar2StrAllele2s.length=" +  ((String)mapVar2StrAllele2s.values().iterator().next()).length() );
		if( setposall.size()!=((String)mapVar2StrAllele2s.values().iterator().next()).length() || setposall.size()!=((String)mapVar2StrAllele2s.values().iterator().next()).length())
			throw new RuntimeException("lengths not equal");
			*/
		
		return new Map[] {mapVar2StrAllele1s, mapVar2StrAllele2s};
	}
	
	@Override
	public Map[] readSNPString(List<SnpsAllvarsPos> listpos) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public Map readSNPString(String chr, int startIdx, int endIdx) {
		// TODO Auto-generated method stub
//		String sql="select stock_sample_id, REPLACE( pos11832_al1||pos12340_al1||pos12742_al1||pos12911_al1, '0','?') allele1, REPLACE( pos11832_al2||pos12340_al2||pos12742_al2||pos12911_al2,'0','?')  allele2 ,\r\n" + 
//				"REPLACE( pos11832_refcall||pos12340_refcall||pos12742_refcall||pos12911_refcall,'0','?')  refcall  from (\r\n" + 
//				"select stock_sample_id, sfl.srcfeature_id-" + AppContext.chr2srcfeatureidOffset() + " chromosome, sfl.position+1 position, allele1, allele2, refcall from snp_genotype sg, SNP_FEATURELOC sfl\r\n" + 
//				"where sfl.SNP_FEATURE_ID=sg.SNP_FEATURE_ID and sfl.srcfeature_id=3 and sfl.position+1 between 10000 and 13000 \r\n" + 
//				")\r\n" + 
//				"pivot\r\n" + 
//				"(   min(allele1) al1, min(allele2) al2 , min(refcall) refcall   \r\n" + 
//				"    for position in (11832 pos11832,  12340 pos12340,   12742 pos12742,12911 pos12911)\r\n" + 
//				") order by stock_sample_id";
		return null;
	}

	@Override
	public Map readSNPString(String chr, int[] posIdxs) {
		// TODO Auto-generated method stub
//		
//		String sql="select stock_sample_id, REPLACE( pos11832_al1||pos12340_al1||pos12742_al1||pos12911_al1, '0','?') allele1, REPLACE( pos11832_al2||pos12340_al2||pos12742_al2||pos12911_al2,'0','?')  allele2 ,\r\n" + 
//				"REPLACE( pos11832_refcall||pos12340_refcall||pos12742_refcall||pos12911_refcall,'0','?')  refcall  from (\r\n" + 
//				"select stock_sample_id, sfl.srcfeature_id-2 chromosome, sfl.position+1 position, allele1, allele2, refcall from snp_genotype sg, SNP_FEATURELOC sfl\r\n" + 
//				"where sfl.SNP_FEATURE_ID=sg.SNP_FEATURE_ID and sfl.srcfeature_id=3 and sfl.position+1 between 10000 and 13000 \r\n" + 
//				")\r\n" + 
//				"pivot\r\n" + 
//				"(   min(allele1) al1, min(allele2) al2 , min(refcall) refcall   \r\n" + 
//				"    for position in (11832 pos11832,  12340 pos12340,   12742 pos12742,12911 pos12911)\r\n" + 
//				") order by stock_sample_id";
//		
		return null;
	}

	@Override
	public Map readSNPString(Set<BigDecimal> colVarids, String chr,
			int[] posIdxs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map readSNPString(Set<BigDecimal> colVarids, String chr,
			int startIdx, int endIdx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map readSNPString(Set colVarids, String chr, int[][] posidxstartend) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map readSNPString(String chr, int[][] posidxstartend) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map readSNPString(String chr, 
			int starvarid, int endvarid, int[][] posstartendIdxs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map readSNPString(String chr, int[] posIdxs, int starvarid,
			int endvarid) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
