package org.irri.iric.portal.hdf5.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.IndelsAllvarsDAO;
import org.irri.iric.portal.domain.IndelsAllvars;
import org.irri.iric.portal.domain.IndelsAllvarsStrImpl;
import org.irri.iric.portal.domain.IndelsAllvarsPos;
import org.irri.iric.portal.hdf5.H5Dataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Read indel matrix from indel1, indel2 matrices, and join them into an IndelsAllvarsStr object
 * @author LMansueto
 *
 */
@Repository("IndelAllvarsDAOHDF5")
public class IndelAllvarsHDF5DAOImpl implements IndelsAllvarsDAO {

	@Autowired
	@Qualifier("H5IndelUniAllele1V2DAO")
	private H5Dataset indelallele1;

	@Autowired
	@Qualifier("H5IndelUniAllele2V2DAO")
	private H5Dataset indelallele2;
	
	private void checkBeans() {
		indelallele1 = (H5Dataset)AppContext.checkBean(indelallele1, "H5IndelUniAllele1V2DAO");
		indelallele2 = (H5Dataset)AppContext.checkBean(indelallele2, "H5IndelUniAllele2V2DAO");
	}

	
	@Override
	public Set<IndelsAllvars> findIndelAllvarsByChrPosBetween(String chr,
			BigDecimal startIdx, BigDecimal endIdx, List listpos) {
		// TODO Auto-generated method stub
		checkBeans();
		Map mapAllele1 = indelallele1.readSNPString(chr, startIdx.intValue(), endIdx.intValue());
		Map mapAllele2 = indelallele2.readSNPString(chr, startIdx.intValue(), endIdx.intValue());
		return createIndelsAllvars(mapAllele1, mapAllele2, listpos);
	}

	@Override
	public Set<IndelsAllvars> findIndelAllvarsByVarChrPosBetween(
			Collection varids, String chr, BigDecimal startIdx, BigDecimal endIdx, List listpos) {
		// TODO Auto-generated method stub
		
		checkBeans();
		Map mapAllele1 = indelallele1.readSNPString((Set)varids, chr, startIdx.intValue(), endIdx.intValue());
		Map mapAllele2 = indelallele2.readSNPString((Set)varids, chr, startIdx.intValue(), endIdx.intValue());
		return createIndelsAllvars(mapAllele1, mapAllele2, listpos);
	}

	
	@Override
	public Set<IndelsAllvars> findIndelAllvarsByVarChrPosIn(Collection varList, String chr, int posList[][], List listpos) {
		// TODO Auto-generated method stub
		Map mapAllele1 = indelallele1.readSNPString((Set)varList, chr, posList);
		Map mapAllele2 = indelallele2.readSNPString((Set)varList, chr, posList);
		return createIndelsAllvars(mapAllele1, mapAllele2, listpos);
	}

	@Override
	public Set<IndelsAllvars> findIndelAllvarsByChrPosIn(String chr, int posList[][], List listpos) {
		// TODO Auto-generated method stub
		Map mapAllele1 = indelallele1.readSNPString( chr, posList);
		Map mapAllele2 = indelallele2.readSNPString( chr, posList);
		return createIndelsAllvars(mapAllele1, mapAllele2, listpos);
	}



	@Override
	public Set<IndelsAllvars> findIndelAllvarsByVarChrPosIn(Collection varList, String chr, Collection posList, List listpos) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Set<IndelsAllvars> findIndelAllvarsByChrPosIn(String chr, Collection posList, List listpos) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Set<IndelsAllvars> getAllIndelCalls() {
		// TODO Auto-generated method stub
		checkBeans();
		return null;
	}
	
	private Set<IndelsAllvars> createIndelsAllvars(Map<BigDecimal,String[]> allele1, Map<BigDecimal,String[]> allele2, List listpos) {
		Set setSnps = new HashSet();
		if(allele1.size()!=allele2.size()) throw new RuntimeException("allele1.size()!=allele2.size(): " +allele1.size() + "  "  + allele2.size() );
		Iterator<BigDecimal> itVarid= allele1.keySet().iterator();
		
		
		while(itVarid.hasNext()) {
			BigDecimal varid=itVarid.next();
			String varallele1[] = allele1.get(varid);
			
			String varallele2[] = allele2.get(varid);
			
			if(varallele1.length!=varallele2.length) throw new RuntimeException("varallele1.length!=varallele2.length: " +varallele1.length + "  "  + varallele2.length );

			
			//long prevdel1=0;
			Integer  prevdel1=null;
			long prevdel1pos=0;
			Integer prevdel2=null;
			long prevdel2pos=0;
			for(int idx=0; idx<varallele1.length; idx++) {
				//BigDecimal varId, BigDecimal pos, String refnuc, String varnuc, String contig, Long chr
				IndelsAllvarsPos pos = (IndelsAllvarsPos) listpos.get(idx);
				
				String varnuc="";
				String allele1idx = new String(varallele1[idx]);
				String allele2idx = new String(varallele2[idx]);

				if(!allele1idx.equals(allele2idx)) {
					//AppContext.debug( "var=" + varid + " i=" + idx + " pos=" +pos.getPos() + "  allele1=" + allele1idx + ";  allele2=" + allele2idx );
					
					if(AppContext.isIgnoreHeteroIndels()) {
						allele1idx="?";
						allele2idx="?";
					}
				}
				

				
					//int del1=0;
					//int del1pos=0;
					if(allele1idx.equals(".") || allele1idx.equals("0") || allele1idx.isEmpty() ) {
						// same as reference
					} else {

						boolean isdel=false;
						try{
							Integer.valueOf(allele1idx);
							isdel=true;
						} catch(Exception ex) {}
						
						//
						
						if(prevdel1!=null)
							prevdel1=prevdel1-Long.valueOf(pos.getPos().longValue()-prevdel1pos).intValue();

						
						if(isdel) {
							//if(prevdel1!=null &&  prevdel1>-1) throw new RuntimeException("var=" + varid + " pos=" + pos.getPos() + ": past deletion at " + prevdel1pos + " extend here but allele1=" +allele1idx);
							if(prevdel1!=null &&  prevdel1>0) throw new RuntimeException("var=" + varid + " pos=" + pos.getPos() + ": past deletion at " + prevdel1pos + " extend here but allele1=" +allele1idx);
							//if(prevdel1>0) throw new RuntimeException("var=" + varid + " pos=" + pos + ": past deletion at " + prevdel1pos + " extend here but allele1=" +allele1idx);
							try{
								//del=Integer.valueOf(allele1idx.replace("del","").trim());
								prevdel1=Integer.valueOf(allele1idx);
								prevdel1pos=pos.getPos().longValue();
							}catch(Exception ex) {};
						}
						else if(prevdel1!=null && prevdel1>-1) {
							// extend deletion from prev pos
							
							
							if(!allele1idx.equals("?"))
							{
								//throw new RuntimeException("var=" + varid + "pos=" + pos + ": past deletion at " + prevdel1pos + " extend here but allele1=" + allele1idx);
								AppContext.debug("var=" + varid + "pos=" + pos.getPos() + " prevdel1=" + prevdel1 +  "  : past deletion at " + prevdel1pos + " extend here but allele1=" + allele1idx);
							} 
							if(prevdel1>0) allele1idx="-" + prevdel1;
							else if(prevdel1==0) {
								//AppContext.debug("allele1 extdel0 at " + pos.getPos());
								allele1idx="-0";
								prevdel1=null; prevdel1pos=0;
							}
							else {
								prevdel1=null; prevdel1pos=0;
							}
						}
						varnuc=allele1idx;
					}
					

					
					varnuc+="/";
					//int del2=0;
					//int del2pos=0;
					if(allele2idx.equals(".") || allele2idx.equals("0") || allele2idx.isEmpty() ) {
					} else {
						boolean isdel=false;
						try{
							Integer.valueOf(allele2idx);
							isdel=true;
						} catch(Exception ex) {}
						
						if(prevdel2!=null) {
							
							//AppContext.debug("prevdel2pos=" + prevdel2pos);
							prevdel2=prevdel2- Long.valueOf(pos.getPos().longValue()-prevdel2pos).intValue();
							//AppContext.debug("prevdel2pos=" + prevdel2pos);
						}
						
						if(isdel) {
							//if(prevdel2!=null && prevdel2>-1) throw new RuntimeException("var=" + varid + " pos=" + pos.getPos() + ": past deletion at " + prevdel2pos + " extend here but allele2=" + allele2idx);
							if(prevdel2!=null && prevdel2>0) throw new RuntimeException("var=" + varid + " pos=" + pos.getPos() + ": past deletion at " + prevdel2pos + " extend here but allele2=" + allele2idx);
							//AppContext.debug("Illegal?? pos=" + pos + ": past deletion at " + prevdel2pos + " extend here but allele2=" + allele2idx);
							try{
								prevdel2 =Integer.valueOf(allele2idx);
								prevdel2pos=pos.getPos().longValue();
								
							}catch(Exception ex) {};
						}
						//else if(prevdel2!=null && prevdel2>-1) {
						else if(prevdel2!=null && prevdel2>-1) {
							// extend deletion from prev pos
							
							if(!allele2idx.equals("?"))
							{
							//	throw new RuntimeException("var=" + varid + "pos=" + pos + ": past deletion at " + prevdel2pos + " extend here but allele2=" +allele2idx);
								AppContext.debug("var=" + varid + "pos=" + pos.getPos() +  "prevdel2=" + prevdel2 + " : past deletion at " + prevdel2pos + " extend here but allele2=" +allele2idx);
							}
							
							if(prevdel2>0) allele2idx="-" + prevdel2;
							else if(prevdel2==0) {
								//AppContext.debug("allele2 extdel0 at " + pos.getPos());
								allele2idx="-0";
								prevdel2=null; prevdel2pos=0;
							}
							else {
								prevdel2=null; prevdel2pos=0;
							}
						}						
						
						varnuc+=allele2idx;
					}

					if(prevdel1!=null &&  prevdel1>100) throw new RuntimeException("var=" + varid +  "prevdel1>100 "  +prevdel1 + "; prevdel1pos=" + prevdel1pos + "; curpos=" + pos);
					if(prevdel2!=null &&   prevdel2>100) throw new RuntimeException("var=" + varid + "prevdel2>100 "  +prevdel2 + "; prevdel1pos=" + prevdel1pos + "; curpos=" + pos);

					
					
				//if(varnuc.equals("/")) continue;
				setSnps.add(new IndelsAllvarsStrImpl(varid, pos.getPos(), pos.getRefnuc(), varnuc, pos.getContig() ,  Long.valueOf(AppContext.guessChrFromString(pos.getContig())), allele1idx, allele2idx));
						
				//setSnps.add(  new IndelsAllvarsStrImpl(varid, pos.getPos(), pos.getRefnuc(), varnuc, pos.getContig() , Long.valueOf(AppContext.guessChrFromString(pos.getContig()))) );
				//setSnps.add(  new IndelsAllvarsStrImpl(varid, pos.getPos(), pos.getRefnuc(), varnuc, pos.getContig() , Long.valueOf(AppContext.guessChrFromString(pos.getContig()))) );
			}
		}
		return setSnps;
	}
	
	
}
	
//******************  OLD CODE RETAINED **********************
	
	/*
	@Override
	public Set findIndelAllvarsByVarChrPosIn(Collection varList, String chr, Collection posList) {
		if(chr.toLowerCase().equals("loci")) {
			
			StringBuffer buffVar = new StringBuffer();
			Iterator<String> itList = AppContext.setSlicerIds((Set)varList).iterator();
			buffVar.append(" ( ");
			while(itList.hasNext()) {
				 buffVar.append(  " var in (" +  itList.next() + ") ");
				 if(itList.hasNext()) buffVar.append(" or ");
			}
			buffVar.append(")");
			
			String sql="select * from iric.V_INDEL_ALLVARS where " + buffVar.toString() + " and (";
			Iterator<Locus> itLoc =  posList.iterator();
			while(itLoc.hasNext()) {
				Locus loc =  itLoc.next();
				sql += "( partition_id=" + ( Integer.valueOf(AppContext.guessChrFromString(loc.getContig()))+2) + " and pos between " + loc.getFmin() + " and " + loc.getFmax() + ") ";
				if(itLoc.hasNext())
					sql+= " or ";
			}
			sql +=")";
			return new LinkedHashSet<VIndelAllvars>(executeSQL(sql));
		}
		else if(chr.toLowerCase().equals("any")) {
			AppContext.debug("snp list not supported for indel.");
			StringBuffer buffVar = new StringBuffer();
			Iterator<String> itList = AppContext.setSlicerIds((Set)varList).iterator();
			buffVar.append(" ( ");
			while(itList.hasNext()) {
				 buffVar.append(  " var in (" +  itList.next() + ") ");
				 if(itList.hasNext()) buffVar.append(" or ");
			}
			buffVar.append(")");
			String sql="select * from iric.V_INDEL_ALLVARS where " + buffVar.toString() + " and (";
			Iterator<MultiReferencePosition> itLoc =  posList.iterator();
			while(itLoc.hasNext()) {
				MultiReferencePosition loc =  itLoc.next();
				sql += "( partition_id=" + (Integer.valueOf( AppContext.guessChrFromString(loc.getContig()))+2) + " and pos=" + loc.getPosition() + ") ";
				if(itLoc.hasNext())
					sql+= " or ";
			}
			sql +=")";
			return new LinkedHashSet<VIndelAllvars>(executeSQL(sql));
		}
		else {
			chr=AppContext.guessChrFromString(chr);
			Query query = createNamedQuery("findVIndelAllvarsByVarChrPosIn", -1, -1, BigDecimal.valueOf(Long.valueOf(chr)+2), varList, posList );
			return new LinkedHashSet<VIndelAllvars>(query.getResultList());
		}
	}

	@Override
	public Set findIndelAllvarsByChrPosIn(String chr, Collection posList) {
		// TODO Auto-generated method stub
		
		if(chr.toLowerCase().equals("loci")) {
			
			String sql="select * from iric.V_INDEL_ALLVARS where (";
			Iterator<Locus> itLoc =  posList.iterator();
			while(itLoc.hasNext()) {
				Locus loc =  itLoc.next();
				sql += "( partition_id=" + ( Integer.valueOf(AppContext.guessChrFromString(loc.getContig()))+2) + " and pos between " + loc.getFmin() + " and " + loc.getFmax() + ") ";
				if(itLoc.hasNext())
					sql+= " or ";
			}
			sql +=")";
			return new LinkedHashSet<VIndelAllvars>(executeSQL(sql));
		}
		else if(chr.toLowerCase().equals("any")) {
			AppContext.debug("snp list not supported for indel.");
			String sql="select * from iric.V_INDEL_ALLVARS where (";
			Iterator<MultiReferencePosition> itLoc =  posList.iterator();
			while(itLoc.hasNext()) {
				MultiReferencePosition loc =  itLoc.next();
				sql += "( partition_id=" + ( Integer.valueOf(AppContext.guessChrFromString(loc.getContig()))+2) + " and pos=" + loc.getPosition() + ") ";
				if(itLoc.hasNext())
					sql+= " or ";
			}
			sql +=")";
			return new LinkedHashSet<VIndelAllvars>(executeSQL(sql));
		}
		else {
			chr=AppContext.guessChrFromString(chr);
			Query query = createNamedQuery("findVIndelAllvarsByChrPosIn", -1, -1, BigDecimal.valueOf(Long.valueOf(chr)+2), posList );
			return new LinkedHashSet<VIndelAllvars>(query.getResultList());
		}	
		
		
	}
	
	*/
	

