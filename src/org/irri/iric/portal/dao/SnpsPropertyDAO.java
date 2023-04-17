package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.chado.oracle.domain.VSnpNonsynallelePos;
import org.irri.iric.portal.chado.oracle.domain.VSnpRefposindex;
import org.irri.iric.portal.domain.LabelCount;
import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.MultiReferencePositionImpl;
import org.irri.iric.portal.domain.SnpsNonsynAllele;
import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;

abstract public class SnpsPropertyDAO<T> extends AbstractJpaDao<T> {

	protected abstract Session getSession();

	protected List executeSQL(String sql, Class retclass) {
		// System.out.println("executing :" + sql);
		// log.info("executing :" + sql);
		AppContext.debug("executing " + sql);
		List listResult = null;
		try {
			listResult = getSession().createSQLQuery(sql).addEntity(retclass).list();
		} catch (Exception ex) {
			AppContext.debug(ex.getMessage());
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		AppContext.debug("result " + listResult.size() + " SnpsProps:" + retclass.getCanonicalName());
		return listResult;
	}

	public Set findSnpsPropertyByChrPosIn(String chr, Collection poslist, Set variantset, String propname,
			String viewname, String columnname, Class retclass) {
		

		Set setSnpfeatureid = new HashSet();
		if (chr.toLowerCase().equals("any")) {

			Set setAll = new HashSet();

			Map mapChr2Pos = MultiReferencePositionImpl.getMapContig2SNPPos(poslist);
			Iterator<String> itChr = mapChr2Pos.keySet().iterator();
			while (itChr.hasNext()) {
				String chrstr = itChr.next();
				Set sets[] = AppContext.setSlicer(new LinkedHashSet((Set) mapChr2Pos.get(chrstr)));
				for (int iset = 0; iset < sets.length; iset++) {

					if (AppContext.isBypassViews() && AppContext.isPostgres()) {
						/*
						 * String sql =
						 * "SELECT cast(mv.snp_feature_id as numeric) snp_feature_id, mv.chromosome AS chr, mv.\"position\" + 1 AS \"position\", mv.type_id "
						 * + columnname + ", cast(" + AppContext.getDefaultOrganismId() +
						 * " as numeric)  organism_id,  cast(mv.chromosome+2 AS numeric) srcfeature_id  FROM mv_snp_refposindex mv, snp_featureprop sfp "
						 * + " WHERE mv.VARIANTSET IN (" + AppContext.toCSVquoted(variantset,"'") +
						 * ") and mv.snp_feature_id = sfp.snp_feature_id and  mv.chromosome=" +
						 * AppContext.guessChrFromString(chrstr) + " and " +
						 * " exists ( select t.column_value from (select unnest(ARRAY" + sets[iset] +
						 * ")column_value) t where t.column_value-1=mv.POSITION ) " +
						 * " AND sfp.type_id in (select cvterm_id from cvterm where name='" + propname
						 * +"')"; setAll.addAll(executeSQL(sql, retclass));
						 */
						setAll.addAll(findSnpsPropertyByChrPosIn(chrstr, sets[iset], variantset, propname, viewname,
								columnname, retclass));

					} else {
						// Query query = createNamedQuery("findVSnpNonsynAlleleBySnpFeatureIdIn", -1,
						// -1,setSnpfeatureid);
						Query query = createNamedQuery("find" + viewname + "ByChrPositionInSnpset", -1, -1,
								BigDecimal.valueOf(Long.valueOf(AppContext.guessChrFromString(chrstr))), sets[iset],
								variantset);
						setAll.addAll(query.getResultList());
					}
				}
			}
			// return new HashSet<SnpsNonsynAllele>(query.getResultList());
			return setAll;

			/*
			 * // Iterator<VSnpRefposindex> it =
			 * (Iterator<VSnpRefposindex>)poslist.iterator(); // //StringBuffer buff = new
			 * StringBuffer(); // while(it.hasNext()) { // VSnpRefposindex pos = it.next();
			 * // setSnpfeatureid.add( pos .getSnpFeatureId()); // //buff.append(snpfearueid
			 * + ", " ); // } // //AppContext.debug(" snpfeatureid in " + buff); // // Set
			 * setAll = new HashSet(); // Set sets[] =
			 * AppContext.setSlicer(setSnpfeatureid); // for(int iset=0; iset<sets.length;
			 * iset++) { // //Query query =
			 * createNamedQuery("findVSnpNonsynAlleleBySnpFeatureIdIn", -1,
			 * -1,setSnpfeatureid); // Query query =
			 * createNamedQuery("findVSnpNonsynallelePosBySnpFeatureIdIn", -1,
			 * -1,sets[iset]); // setAll.addAll(query.getResultList()); // } // //return new
			 * HashSet<SnpsNonsynAllele>(query.getResultList()); // return setAll;
			 */

			/*
			 * // AppContext.debug("checking " + posset.size() + " snp positions"); // Map
			 * mapChr2Pos = MultiReferencePositionImpl.getMapContig2SNPPos(posset); //
			 * String sql =
			 * "select SNP_FEATURE_ID, TYPE_ID , CHROMOSOME, POSITION , REFCALL , ALLELE_INDEX from "
			 * + AppContext.getDefaultSchema() + ".V_SNP_REFPOSINDEX WHERE 1=1 and ("; //
			 * Iterator<String> itContig= mapChr2Pos.keySet().iterator(); //
			 * while(itContig.hasNext()) { // String contigstr = itContig.next(); // String
			 * contig = contigstr.toUpperCase().replace("CHR0","").replace("CHR",""); //
			 * Collection setPos = (Collection)mapChr2Pos.get(contigstr); // sql+=
			 * "( chromosome=" + contig + " and position in (" +
			 * setPos.toString().replace("[", "").replace("]", "") + ")) "; //
			 * if(itContig.hasNext()) // sql += " or "; // }; // // sql += ") and TYPE_ID="
			 * + type + " order by CHROMOSOME, POSITION"; // return executeSQL(sql);
			 */
		} else if (chr.toLowerCase().equals("loci")) {
			Iterator<Locus> it = poslist.iterator();
			// StringBuffer buff = new StringBuffer();
			Set setPos = new TreeSet();
			while (it.hasNext()) {
				Locus loc = it.next();
				setPos.addAll(findSnpsPropertyByChrPosBetween(loc.getContig(), loc.getFmin(), loc.getFmax(), variantset,
						propname, viewname, columnname, retclass));
			}
			return setPos;
		} else {

			Set setAll = new HashSet();
			Set sets[] = AppContext.setSlicer(new LinkedHashSet(poslist));
			if (columnname == null)
				columnname = "";
			else if (columnname != null && !columnname.isEmpty() && !columnname.startsWith(","))
				columnname = ", sfp.value AS " + columnname;

			for (int iset = 0; iset < sets.length; iset++) {
				// Query query = createNamedQuery("findVSnpNonsynAlleleBySnpFeatureIdIn", -1,
				// -1,setSnpfeatureid);

				if (AppContext.isBypassViews() && AppContext.isPostgres()) {
					/*
					 * // String sql =
					 * "SELECT cast(mv.snp_feature_id as numeric) snp_feature_id, mv.chromosome AS chr, mv.\"position\" + 1 AS \"position\", mv.type_id "
					 * + columnname + ", cast(" + AppContext.getDefaultOrganismId() +
					 * " as numeric)  organism_id,  cast(mv.chromosome+2 AS numeric) srcfeature_id FROM mv_snp_refposindex mv, snp_featureprop sfp "
					 * + // " WHERE mv.variantset in (" + AppContext.toCSVquoted(variantset, "'") +
					 * ") and mv.snp_feature_id = sfp.snp_feature_id and  mv.chromosome=" +
					 * AppContext.guessChrFromString(chr) + " and " + //
					 * " exists ( select t.column_value from (select unnest(ARRAY" + sets[iset] +
					 * ")column_value) t where t.column_value-1=mv.POSITION ) " + //
					 * " AND sfp.type_id in (select cvterm_id from cvterm where name='" + propname +
					 * "')"; // setAll.addAll(executeSQL(sql,retclass));
					 */

					String sqldirect = "";
					sqldirect += "SELECT sfl.snp_feature_id, sfl.srcfeature_id-" + AppContext.chr2srcfeatureidOffset()
							+ " AS chromosome, sfl.position + 1 AS \"position\", v.variantset_id AS type_id, v.name AS variantset "
							+ columnname;
					sqldirect += " FROM " + AppContext.getDefaultSchema() + ".snp_featureloc sfl, "
							+ AppContext.getDefaultSchema() + ".variant_variantset vvs, "
							+ AppContext.getDefaultSchema() + ".variantset v ";
					sqldirect += " , snp_featureprop sfp  WHERE  sfl.snp_feature_id = sfp.snp_feature_id and sfl.snp_feature_id = vvs.variant_feature_id AND vvs.variantset_id = v.variantset_id ";
					sqldirect += "  AND sfp.type_id in (select cvterm_id from cvterm where name='" + propname
							+ "') and v.name in (" + AppContext.toCSVquoted(variantset, "'") + ")";
					sqldirect += " and sfl.organism_id=" + AppContext.getDefaultOrganismId() + " and sfl.srcfeature_id="
							+ AppContext.guessSrcfeataureidFromString(chr);
					sqldirect += " and exists ( select t.column_value from (select unnest(ARRAY" + sets[iset]
							+ ")column_value) t where t.column_value-1=sfl.position ) ";
					sqldirect += " order by sfl.position";
					setAll.addAll(executeSQL(sqldirect, retclass));

				} else {
					Query query = createNamedQuery("find" + viewname + "ByChrPositionInSnpset", -1, -1,
							BigDecimal.valueOf(Long.valueOf(AppContext.guessChrFromString(chr))), sets[iset],
							variantset);
					setAll.addAll(query.getResultList());
				}
			}
			// return new HashSet<SnpsNonsynAllele>(query.getResultList());
			return setAll;
		}
	}

	public Set findSnpsPropertyByChrPosBetween(String chr, Integer start, Integer end, BigDecimal typeid,
			String propname, String viewname, String columnname, Class retclass) throws DataAccessException {

		if (AppContext.isBypassViews()) {
			if (columnname == null)
				columnname = "";
			else if (columnname != null && !columnname.isEmpty() && !columnname.startsWith(","))
				columnname = ", sfp.value AS " + columnname;

			String sqldirect = "";
			sqldirect += "SELECT sfl.snp_feature_id, sfl.srcfeature_id-" + AppContext.chr2srcfeatureidOffset()
					+ " AS chromosome, sfl.position + 1 AS \"position\", v.variantset_id AS type_id, v.name AS variantset "
					+ columnname;
			sqldirect += " FROM " + AppContext.getDefaultSchema() + ".snp_featureloc sfl, "
					+ AppContext.getDefaultSchema() + ".variant_variantset vvs, " + AppContext.getDefaultSchema()
					+ ".variantset v ";
			sqldirect += " , snp_featureprop sfp  WHERE  sfl.snp_feature_id = sfp.snp_feature_id and sfl.snp_feature_id = vvs.variant_feature_id AND vvs.variantset_id = v.variantset_id ";
			sqldirect += "  AND sfp.type_id in (select cvterm_id from cvterm where name='" + propname + "')";
			sqldirect += " and sfl.organism_id=" + +AppContext.getDefaultOrganismId() + " and sfl.srcfeature_id="
					+ AppContext.guessSrcfeataureidFromString(chr);
			sqldirect += " and sfl.position between " + start + "-1 and " + end + "-1 and v.variantset_id=" + typeid
					+ " order by sfl.position";
			return new LinkedHashSet(executeSQL(sqldirect, retclass));
			/*
			 * String sql =
			 * "SELECT cast(mv.snp_feature_id as numeric) snp_feature_id, mv.chromosome AS chr, mv.\"position\" + 1 AS \"position\", mv.type_id "
			 * + columnname + ", cast(" + AppContext.getDefaultOrganismId() +
			 * " as numeric)  organism_id,  cast(mv.chromosome+2 AS numeric) srcfeature_id FROM mv_snp_refposindex mv, snp_featureprop sfp "
			 * + " WHERE mv.variantset in (" + AppContext.toCSVquoted(variantset,"'") +
			 * ") and mv.snp_feature_id = sfp.snp_feature_id and  mv.chromosome=" +
			 * AppContext.guessChrFromString(chr) + " and mv.POSITION between " + start +
			 * " and " + end +
			 * " AND sfp.type_id in (select cvterm_id from cvterm where name='" + propname +
			 * "')"; return new LinkedHashSet(executeSQL(sql,retclass));
			 */
		}
		return null;
	}

	public Set findSnpsPropertyByChrPosBetween(String chr, Integer start, Integer end, Set variantset, String propname,
			String viewname, String columnname, Class retclass) throws DataAccessException {

		if (AppContext.isBypassViews()) {
			if (columnname == null)
				columnname = "";
			else if (columnname != null && !columnname.isEmpty() && !columnname.startsWith(","))
				columnname = ", sfp.value AS " + columnname;

			/*
			 * using snp_feature_id // String sqldirect=""; // sqldirect+
			 * ="SELECT sfl.snp_feature_id, sfl.srcfeature_id-2 AS chromosome, sfl.position + 1 AS \"position\", v.variantset_id AS type_id, v.name AS variantset "
			 * + columnname ; // sqldirect+=" FROM " +AppContext.getDefaultSchema()
			 * +".snp_featureloc sfl, " +AppContext.getDefaultSchema()
			 * +".variant_variantset vvs, " + AppContext.getDefaultSchema()
			 * +".variantset v "; // sqldirect+
			 * =" , snp_featureprop sfp  WHERE  sfl.snp_feature_id = sfp.snp_feature_id and sfl.snp_feature_id = vvs.variant_feature_id AND vvs.variantset_id = v.variantset_id "
			 * ; //
			 * sqldirect+="  AND sfp.type_id in (select cvterm_id from cvterm where name='"
			 * + propname + "')"; //
			 * sqldirect+=" and sfl.organism_id=9 and sfl.srcfeature_id=" +
			 * AppContext.guessChrFromString(chr) + "+2 "; //
			 * sqldirect+=" and sfl.position between " + start + "-1 and " + end +
			 * "-1 and v.name in (" + AppContext.toCSVquoted(variantset, "'") +
			 * ") order by sfl.position"; // return new
			 * LinkedHashSet(executeSQL(sqldirect,retclass));
			 */

			// using position
			String sqldirect = "";
			if ( AppContext.chr2srcfeatureidOffset()>-1) {
				sqldirect += "SELECT sfl.snp_feature_id, sfl.srcfeature_id-" + AppContext.chr2srcfeatureidOffset()
						+ " AS chromosome, sfl.position + 1 AS \"position\", v.variantset_id AS type_id, v.name AS variantset "
						+ columnname;
				sqldirect += " FROM " + AppContext.getDefaultSchema() + ".snp_featureloc sfl, "
						+ AppContext.getDefaultSchema() + ".variant_variantset vvs, " + AppContext.getDefaultSchema()
						+ ".variantset v, ";
				sqldirect += AppContext.getDefaultSchema() + ".snp_featureloc sfl3k ";
				sqldirect += " , snp_featureprop sfp  WHERE  sfl3k.snp_feature_id = sfp.snp_feature_id and sfl.snp_feature_id = vvs.variant_feature_id AND vvs.variantset_id = v.variantset_id ";
				sqldirect += "  AND sfp.type_id in (select cvterm_id from cvterm where name='" + propname + "')";
				sqldirect += " and sfl3k.organism_id=" + AppContext.getDefaultOrganismId() + " and sfl3k.srcfeature_id="
						+ AppContext.guessSrcfeataureidFromString(chr);
				sqldirect += " and sfl3k.position=sfl.position and sfl3k.srcfeature_id=sfl.srcfeature_id and sfl.organism_id="
						+ AppContext.getDefaultOrganismId();
				sqldirect += " and sfl3k.position between " + start + "-1 and " + end + "-1 and v.name in ("
						+ AppContext.toCSVquoted(variantset, "'") + ") order by sfl3k.position";
			} else {
				sqldirect += "SELECT sfl.snp_feature_id, f.name as contig,  null "
						+ " AS chromosome, sfl.position + 1 AS \"position\", v.variantset_id AS type_id, v.name AS variantset "
						+ columnname;
				sqldirect += " FROM " + AppContext.getDefaultSchema() + ".snp_featureloc sfl, "
						+ AppContext.getDefaultSchema() + ".variant_variantset vvs, " + AppContext.getDefaultSchema()
						+ ".variantset v, ";
				sqldirect += AppContext.getDefaultSchema() + ".snp_featureloc sfl3k, ";
				sqldirect += AppContext.getDefaultSchema() + ".feature f ";
				sqldirect += " , snp_featureprop sfp  WHERE sfl.srcfeature_id=f.feature_id and  sfl3k.snp_feature_id = sfp.snp_feature_id and sfl.snp_feature_id = vvs.variant_feature_id AND vvs.variantset_id = v.variantset_id ";
				sqldirect += "  AND sfp.type_id in (select cvterm_id from cvterm where name='" + propname + "')";
				sqldirect += " and sfl3k.organism_id=" + AppContext.getDefaultOrganismId() + " and sfl3k.srcfeature_id="
						+ AppContext.guessSrcfeataureidFromString(chr);
				sqldirect += " and sfl3k.position=sfl.position and sfl3k.srcfeature_id=sfl.srcfeature_id and sfl.organism_id="
						+ AppContext.getDefaultOrganismId();
				sqldirect += " and sfl3k.position between " + start + "-1 and " + end + "-1 and v.name in ("
						+ AppContext.toCSVquoted(variantset, "'") + ") order by sfl3k.position";
																							
			}
			return new LinkedHashSet(executeSQL(sqldirect, retclass));

			/*
			 * // String sql =
			 * "SELECT cast(mv.snp_feature_id as numeric) snp_feature_id, mv.chromosome AS chr, mv.\"position\" + 1 AS \"position\", mv.type_id "
			 * + columnname + ", cast(" + AppContext.getDefaultOrganismId() +
			 * " as numeric)  organism_id,  cast(mv.chromosome+2 AS numeric) srcfeature_id FROM mv_snp_refposindex mv, snp_featureprop sfp "
			 * + // " WHERE mv.variantset in (" + AppContext.toCSVquoted(variantset,"'") +
			 * ") and mv.snp_feature_id = sfp.snp_feature_id and  mv.chromosome=" +
			 * AppContext.guessChrFromString(chr) + " and mv.POSITION between " + start +
			 * " and " + end +
			 * " AND sfp.type_id in (select cvterm_id from cvterm where name='" + propname +
			 * "')"; // return new LinkedHashSet(executeSQL(sql,retclass));
			 */
		}

		Query query = createNamedQuery("find" + viewname + "ByChrPositionBetweenSnpset", -1, -1,
				BigDecimal.valueOf(Long.valueOf(AppContext.guessChrFromString(chr))), BigDecimal.valueOf(start),
				BigDecimal.valueOf(end), variantset);
		java.util.List list = query.getResultList();
		java.util.Set set = new LinkedHashSet<SnpsNonsynAllele>(list);
		AppContext.debug("find" + viewname + "ByChrPosBetween list=" + list.size() + " set=" + set.size());
		return set;
	}

	public Set findSnpsPropertyByFeatureidIn(Collection featureid, String propname, String viewname, String columnname,
			Class retclass) throws DataAccessException {
		
		if (columnname != null && !columnname.isEmpty()) {
			if (!columnname.startsWith(","))
				columnname = ", sfp.value AS " + columnname;
		} else
			columnname = "";
		Set sets[] = AppContext.setSlicer((Set) featureid);
		Set setAll = new LinkedHashSet();
		// if(columnname==null) columnname=""; else
		// if(columnname!=null && !columnname.isEmpty() && !columnname.startsWith(","))
		// columnname=", sfp.value AS " + columnname;

		for (int iset = 0; iset < sets.length; iset++) {
			if (AppContext.isBypassViews() && AppContext.isPostgres()) {
				/*
				 * // String sql =
				 * "SELECT cast(mv.snp_feature_id as numeric) snp_feature_id, mv.chromosome AS chr, mv.\"position\" + 1 AS \"position\", mv.type_id "
				 * + columnname + ", cast(" + AppContext.getDefaultOrganismId() +
				 * " as numeric) organism_id,  cast(mv.chromosome+2 AS numeric) srcfeature_id  FROM mv_snp_refposindex mv, snp_featureprop sfp "
				 * + // " WHERE mv.TYPE_ID=" + dataset +
				 * " and mv.snp_feature_id = sfp.snp_feature_id and  " + //
				 * " exists ( select t.column_value from (select unnest(ARRAY" + sets[iset] +
				 * ")column_value) t where t.column_value=mv.snp_feature_id ) " + //
				 * " AND sfp.type_id in (select cvterm_id from cvterm where name='" + propname+
				 * "')"; // setAll.addAll(executeSQL(sql, retclass));
				 */

				/*
				 * using snp_feature_id // String sqldirect=""; // sqldirect+
				 * ="SELECT sfl.snp_feature_id, sfl.srcfeature_id-2 AS chromosome, sfl.position + 1 AS \"position\", v.variantset_id AS type_id, v.name AS variantset "
				 * + columnname; // sqldirect+=" FROM " +AppContext.getDefaultSchema()
				 * +".snp_featureloc sfl, " +AppContext.getDefaultSchema()
				 * +".variant_variantset vvs, " + AppContext.getDefaultSchema()
				 * +".variantset v "; // sqldirect+
				 * =" , snp_featureprop sfp  WHERE  sfl.snp_feature_id = sfp.snp_feature_id and sfl.snp_feature_id = vvs.variant_feature_id AND vvs.variantset_id = v.variantset_id "
				 * ; //
				 * sqldirect+="  AND sfp.type_id in (select cvterm_id from cvterm where name='"
				 * + propname + "')"; // sqldirect+=" and sfl.organism_id=9"; //
				 * sqldirect+=" and exists ( select t.column_value from (select unnest(ARRAY" +
				 * sets[iset] + ")column_value) t where t.column_value=sfl.snp_feature_id ) ";
				 * // sqldirect+=" order by sfl.position"; //
				 * setAll.addAll(executeSQL(sqldirect,retclass));
				 */
				// using position
				String sqldirect = "";
				sqldirect += "SELECT sfl.snp_feature_id, sfl.srcfeature_id-" + AppContext.chr2srcfeatureidOffset()
						+ " AS chromosome, sfl.position + 1 AS \"position\", v.variantset_id AS type_id, v.name AS variantset "
						+ columnname;
				sqldirect += " FROM " + AppContext.getDefaultSchema() + ".snp_featureloc sfl, "
						+ AppContext.getDefaultSchema() + ".variant_variantset vvs, " + AppContext.getDefaultSchema()
						+ ".variantset v, ";
				sqldirect += AppContext.getDefaultSchema() + ".snp_featureloc sfl3k ";
				sqldirect += " , snp_featureprop sfp  WHERE  sfl3k.snp_feature_id = sfp.snp_feature_id and sfl.snp_feature_id = vvs.variant_feature_id AND vvs.variantset_id = v.variantset_id ";
				sqldirect += "  AND sfp.type_id in (select cvterm_id from cvterm where name='" + propname + "')";
				sqldirect += " and sfl3k.organism_id=" + AppContext.getDefaultOrganismId()
						+ " and sfl3k.position=sfl.position and sfl3k.srcfeature_id=sfl.srcfeature_id and sfl.organism_id=9 ";
				sqldirect += " and exists ( select t.column_value from (select unnest(ARRAY" + sets[iset]
						+ ")column_value) t where t.column_value=sfl.snp_feature_id ) ";
				sqldirect += " order by sfl.position";
				setAll.addAll(executeSQL(sqldirect, retclass));
			} else {
				Query query = createNamedQuery("find" + viewname + "BySnpFeatureIdIn", -1, -1, sets[iset]);
				setAll.addAll(query.getResultList());
			}
		}
		return setAll;

	}

	public Map variantset2snppropCount(String propname, Set variantset) {

		/*
		 * using snp_feature_id String
		 * sql="select vs.name variantset, count(1) from cvterm ct, snp_featureprop sfp, variant_variantset vvs, variantset vs "
		 * + " where ct.name in ('" + propname +
		 * "')  and ct.cvterm_id=sfp.type_id and sfp.snp_feature_id=vvs.variant_feature_id"
		 * + " and vvs.variantset_id=vs.variantset_id and  vs.name in ('" +
		 * AppContext.toCSVquoted(variantset, "'") + "') group by vs.name";
		 * List<LabelCount> labelcount = executeSQL(sql, LabelCount.class); Map m=new
		 * HashMap(); for(LabelCount lc:labelcount) m.put(lc.getLabel(), lc.getCount());
		 * return m;
		 */

		// using position
		String sql = "select vs.name variantset, count(1) from cvterm ct, snp_featureprop sfp, variant_variantset vvs, variantset vs, "
				+ " snp_featureloc sfl, snp_featureloc sfl3k " + " where ct.name in ('" + propname + "') "
				+ " and sfl3k.organism_id=" + AppContext.getDefaultOrganismId()
				+ " and sfl3k.position=sfl.position and sfl3k.srcfeature_id=sfl.srcfeature_id and sfl.organism_id=9 "
				+ " and sfl3k.snp_feature_id=sfp.snp_feature_id "
				+ " and ct.cvterm_id=sfp.type_id and sfl.snp_feature_id=vvs.variant_feature_id"
				+ " and vvs.variantset_id=vs.variantset_id and  vs.name in ('" + AppContext.toCSVquoted(variantset, "'")
				+ "') group by vs.name";
		List<LabelCount> labelcount = executeSQL(sql, LabelCount.class);
		Map m = new HashMap();
		for (LabelCount lc : labelcount)
			m.put(lc.getLabel(), lc.getCount());
		return m;
	}

}
