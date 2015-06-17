package org.irri.iric.portal.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.genotype.service.IndelStringService;
import org.springframework.beans.factory.annotation.Autowired;

//import org.irri.iric.portal.genotype.service.VarietiesGenotypeServiceImpl.SnpsAllvarsPosComparator;
//import org.irri.iric.portal.genotype.service.VarietiesGenotypeServiceImpl.SnpsStringAllvarsImplSorter;

public class VariantStringData {
	
	@Autowired
	private ListItemsDAO listitemsdao;
	
	//protected  Map<BigDecimal, Double> mapVariety2Mismatch;
	protected  Map mapVariety2Mismatch = new HashMap();
	protected Map<BigDecimal, Integer> mapVariety2Order = new HashMap();
	protected List<SnpsAllvarsPos> listPos = new ArrayList();
	protected Map<Integer,BigDecimal> mapIdx2Pos = new HashMap();
	protected List<SnpsStringAllvars> listVariantsString = new ArrayList();
	//protected String refnuc[];
	protected String refnuc;
	protected String msgbox="";
	protected Map<BigDecimal, Set<String>> mapPos2Alleleset= new HashMap();
	
	
	private Map<BigDecimal, MultiReferencePosition> mapMSU7Pos2ConvertedPos;
	private Map<String, Map<BigDecimal, MultiReferencePosition>> mapOrg2MSU7Pos2ConvertedPos;
	
	private String npbContig;

	
	private VariantSnpsStringData snpstringdata;
	private VariantIndelStringData indelstringdata;
	
	

	

	public void setMapMSU7Pos2ConvertedPos(
			Map<BigDecimal, MultiReferencePosition> mapMSU7Pos2ConvertedPos, String npbContig) {
		this.mapMSU7Pos2ConvertedPos = mapMSU7Pos2ConvertedPos;
		this.npbContig=npbContig;
	}
	
	public Map<BigDecimal, MultiReferencePosition> getMapMSU7Pos2ConvertedPos() {
		return mapMSU7Pos2ConvertedPos;
	}
	
	
	public Map<String, Map<BigDecimal, MultiReferencePosition>> getMapOrg2MSU7Pos2ConvertedPos() {
		return mapOrg2MSU7Pos2ConvertedPos;
	}

	
	public void addMapOrg2MSU7PosConverterPos(String orgname, Map mapMSU7Pos2ConvertedPos, String npbcontig) {
	
		if(this.npbContig==null)  this.npbContig = npbcontig;
		else if(!npbcontig.equals(this.npbContig)) throw new RuntimeException("NPB contig " + npbcontig + " did not match from previous conversion " + this.npbContig);
			
		if(mapOrg2MSU7Pos2ConvertedPos==null) mapOrg2MSU7Pos2ConvertedPos=new HashMap();
		mapOrg2MSU7Pos2ConvertedPos.put(orgname, mapMSU7Pos2ConvertedPos);
	}
	
	/*
	public void setMapOrg2MSU7Pos2ConvertedPos(
			Map<String, Map<BigDecimal, MultiReferencePosition>> mapOrg2MSU7Pos2ConvertedPos) {
		this.mapOrg2MSU7Pos2ConvertedPos = mapOrg2MSU7Pos2ConvertedPos;
	}
	*/
	
	

	public String getNpbContig() {
		return npbContig;
	}

	public boolean isNipponbareReference() {
		return (mapMSU7Pos2ConvertedPos==null);
	}

	public boolean hasIndel() {
		 return indelstringdata!=null;
	 }
	
	 public boolean hasSnp() {
		 return snpstringdata!=null;
	 }

/*
	public VariantStringData(Map<BigDecimal, BigDecimal> mapVariety2Mismatch,
			Map<BigDecimal, Integer> mapVariety2Order,
			List<SnpsAllvarsPos> listPos, Map<Integer, BigDecimal> mapIdx2Pos,
			List<SnpsStringAllvars> listVariantsString) {
		super();
		this.mapVariety2Mismatch = mapVariety2Mismatch;
		this.mapVariety2Order = mapVariety2Order;
		this.listPos = listPos;
		this.mapIdx2Pos = mapIdx2Pos;
		this.listVariantsString = listVariantsString;
	}

*/
	
	public void setSnpstringdata(VariantSnpsStringData snpstringdata) {
		this.snpstringdata = snpstringdata;
		
		if(snpstringdata!=null && snpstringdata.getListPos()==null) throw new RuntimeException("snpstringdata.getListPos()==null");
		listVariantsString=null;
		//merge();
	}

	public void setIndelstringdata(VariantIndelStringData indelstringdata) {
		this.indelstringdata = indelstringdata;

		if(indelstringdata!=null && indelstringdata.getListPos()==null) throw new RuntimeException("indelstringdata.getListPos()==null");
		listVariantsString=null;
		//merge();
	}

	public List<SnpsStringAllvars> getListVariantsString() {
		return listVariantsString;
	}
	

	/*
	public String[] getRefnuc() {
		if(refnuc==null) {
			refnuc = new String[listPos.size()];
			Iterator<SnpsAllvarsPos> itlistPos = listPos.iterator();
			int i=0;
			while(itlistPos.hasNext()) {
				refnuc[i]= itlistPos.next().getRefnuc();
				i++;
			}
		}
		return refnuc;
	}
	*/

	public String getRefnuc() {
		if(refnuc==null) {
			StringBuffer buff = new StringBuffer();
			Iterator<SnpsAllvarsPos> itlistPos = listPos.iterator();
			while(itlistPos.hasNext()) {
				String refnuc = itlistPos.next().getRefnuc() ;
				if(refnuc.length()!=1) throw new RuntimeException("refnuc.length()!=1");
				buff.append( refnuc );
			}
			refnuc = buff.toString();
		}
		return refnuc;
	}


	public VariantSnpsStringData getSnpstringdata() {
		return snpstringdata;
	}

	public VariantIndelStringData getIndelstringdata() {
		return indelstringdata;
	}

	public Map getMapVariety2Mismatch() {
		return mapVariety2Mismatch;
	}

	public Map<BigDecimal, Integer> getMapVariety2Order() {
		return mapVariety2Order;
	}

	public List<SnpsAllvarsPos> getListPos() {
		return listPos;
	}

	public Map<Integer, BigDecimal> getMapIdx2Pos() {
		return mapIdx2Pos;
	}


	public void setMessage(String message) {
		// TODO Auto-generated method stub
		this.msgbox=message;
	}

	public String getMessage() {
		return msgbox;
	}
		

	/**
	 * merge SNPs and Indels
	 */
	public void merge() {
		// merge
		//VariantStringData variantsmerged = null;
		
		if(snpstringdata!=null && indelstringdata!=null) {
			
			listitemsdao=(ListItemsDAO)AppContext.checkBean(listitemsdao, "ListItemsDAO");
			 
			//merge listVariants
			List<SnpsStringAllvars> listMergedVariants = new ArrayList();
			
			// varwithsnps
			Set hasSnps = new HashSet();
			Iterator<SnpsStringAllvars> itSnpString = snpstringdata.getListVariantsString().iterator();
			Map<BigDecimal, SnpsStringAllvars> mapVar2SnpStringAllvars = new HashMap();
			while(itSnpString.hasNext()) {
				SnpsStringAllvars snpstring = itSnpString.next();
				mapVar2SnpStringAllvars.put(snpstring.getVar() , snpstring);
				hasSnps.add(snpstring.getVar());
			}
			
			// varswithindels
			Set hasIndels = new HashSet();
			itSnpString = indelstringdata.getListVariantsString().iterator();
			while(itSnpString.hasNext()) {
				IndelsStringAllvars indelstring = (IndelsStringAllvars)itSnpString.next();
				BigDecimal var = indelstring.getVar();
				indelstring.delegateSnpString( mapVar2SnpStringAllvars.get(var) );
				
				hasIndels.add(var);
				listMergedVariants.add(indelstring);
			}
			
			// has snps only
			Set hasSnpsOnly = new HashSet( hasSnps );
			hasSnpsOnly.removeAll( hasIndels );
			itSnpString = hasSnpsOnly.iterator();
			while(itSnpString.hasNext()) {
				listMergedVariants.add( mapVar2SnpStringAllvars.get(itSnpString.next() )  );
			}			
			
			System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		 	
			
			if(this.hasIndel())
				Collections.sort(listMergedVariants, new IndelStringAllvarsImplSorter());
			else
				Collections.sort(listMergedVariants, new SnpsStringAllvarsImplSorter());
		 	

			// merge pos
			Set setMergedAllvarsPos = new TreeSet(new SnpsAllvarsPosComparator());
			setMergedAllvarsPos.addAll( snpstringdata.getListPos() );
			setMergedAllvarsPos.addAll( indelstringdata.getListPos() );
			
			
			
			// update mismatches and order
			Map<BigDecimal, Double>  mapMergedVar2Mismatch = new HashMap();
			Map<BigDecimal, Integer> mapMergedVar2Order = new HashMap();
			Iterator<SnpsStringAllvars> itSnpstring = listMergedVariants.iterator();
			int ordercnt = 0;
			while(itSnpstring.hasNext()) {
				SnpsStringAllvars snpstring = itSnpstring.next();
				mapMergedVar2Mismatch.put(snpstring.getVar() , snpstring.getMismatch().doubleValue() );
				mapMergedVar2Order.put(snpstring.getVar() , ordercnt);
				ordercnt++;
			}
			 
			
			// update mapMergedIdx2SnpIdx
			 Map<BigDecimal, Integer> mapSNPPos2SnpIdx = new HashMap();
			 Iterator<SnpsAllvarsPos> itSNPosList =  snpstringdata.getListPos().iterator();
			 int idxcnt = 0;
			 while(itSNPosList.hasNext()) {
				 SnpsAllvarsPos snppos = itSNPosList.next();
				 mapSNPPos2SnpIdx.put(snppos.getPos(), idxcnt);
				 idxcnt++;
			 }
			 
		
			//Set<Integer> setNewGappedIdx = new HashSet();
			 
			Map<Integer,Integer> mapMergedIdx2SnpIdx = new HashMap();
			// merge column indexing, update gapped set
			 Map<Integer, BigDecimal> mapMergedIndex2Pos = new HashMap(); 
			 Iterator<SnpsAllvarsPos> itSnppos = setMergedAllvarsPos.iterator();
			 idxcnt=0;
			 while(itSnppos.hasNext()) {
				 SnpsAllvarsPos posallvars = itSnppos.next();
				 mapMergedIndex2Pos.put( idxcnt, posallvars.getPos() );
				 if(mapSNPPos2SnpIdx.containsKey( posallvars.getPos()))
					 mapMergedIdx2SnpIdx.put(idxcnt, mapSNPPos2SnpIdx.get( posallvars.getPos() ) );
				 
			//	 if(posallvars.getRefnuc().equals(IndelStringService.INDEL_GAP)) setNewGappedIdx.add(idxcnt);
				 idxcnt++;
			 }
			 

			 
			this.mapVariety2Mismatch = mapMergedVar2Mismatch;
			this.mapVariety2Order= mapMergedVar2Order;
			this.mapIdx2Pos=mapMergedIndex2Pos;
			this.listPos = new ArrayList();
			this.listPos.addAll(setMergedAllvarsPos);
			this.listVariantsString= listMergedVariants;

			this.snpstringdata.setMapMergedIdx2SnpIdx( mapMergedIdx2SnpIdx );
			//this.indelstringdata.setSetIdxGap( setNewGappedIdx );

			
			this.mapPos2Alleleset = new HashMap();
			this.mapPos2Alleleset.putAll( this.snpstringdata.getMapPos2Alleleset() );
			this.mapPos2Alleleset.putAll( this.indelstringdata.mapPos2Alleleset );
			
			//variantsmerged = new  VariantStringData(mapMergedVar2Mismatch, mapMergedVar2Order,
			//		 listPos, mapMergedIndex2Pos, listMergedVariants);
			// variantsmerged.setMapMergedIdx2SnpIdx(mapMergedIdx2SnpIdx);
			 
			
			if(!snpstringdata.isNipponbareReference() || !indelstringdata.isNipponbareReference()) {
				this.mapMSU7Pos2ConvertedPos=new HashMap();
				if(!snpstringdata.isNipponbareReference()) 
					mapMSU7Pos2ConvertedPos.putAll(snpstringdata.getMapMSU7Pos2ConvertedPos());
				if(!indelstringdata.isNipponbareReference()) 
					mapMSU7Pos2ConvertedPos.putAll(indelstringdata.getMapMSU7Pos2ConvertedPos());
			}
			
			if(snpstringdata.getMapOrg2MSU7Pos2ConvertedPos()!=null || indelstringdata.getMapOrg2MSU7Pos2ConvertedPos()!=null ) {
				this.mapOrg2MSU7Pos2ConvertedPos= new HashMap();
				if(snpstringdata.getMapOrg2MSU7Pos2ConvertedPos()!=null && indelstringdata.getMapOrg2MSU7Pos2ConvertedPos()!=null) {
					this.mapOrg2MSU7Pos2ConvertedPos.putAll( snpstringdata.getMapOrg2MSU7Pos2ConvertedPos() );
					Map<String, Map<BigDecimal,MultiReferencePosition>> mapOrg2posMapIndel = indelstringdata.getMapOrg2MSU7Pos2ConvertedPos();
					
					Iterator<String> itOrg = mapOrg2posMapIndel.keySet().iterator();
					Map mapNewOrgs = new HashMap();
					while(itOrg.hasNext()) {
						String orgname=itOrg.next();
						Map mapPos = mapOrg2MSU7Pos2ConvertedPos.get(orgname);
						if(mapPos==null) {
							mapOrg2MSU7Pos2ConvertedPos.put( orgname, mapOrg2posMapIndel.get(orgname));
						} else {
							mapPos.putAll( mapOrg2posMapIndel.get(orgname) );
						}
					}
					
				}
				else if(snpstringdata.getMapOrg2MSU7Pos2ConvertedPos()!=null)
					this.mapOrg2MSU7Pos2ConvertedPos.putAll( snpstringdata.getMapOrg2MSU7Pos2ConvertedPos() );
				else if( indelstringdata.getMapOrg2MSU7Pos2ConvertedPos()!=null)
					this.mapOrg2MSU7Pos2ConvertedPos.putAll( indelstringdata.getMapOrg2MSU7Pos2ConvertedPos() );
			}
			
			this.msgbox += " ... RESULT: " +  listMergedVariants.size() + " varieties x " + snpstringdata.getListPos().size()  + 
					" SNP, "   + indelstringdata.getListPos().size() + " INDEL positions\n";
			
			if(!snpstringdata.msgbox.isEmpty())
			 this.msgbox += snpstringdata.msgbox.trim()  + "\n";
			
			if(!indelstringdata.msgbox.isEmpty())
				this.msgbox += indelstringdata.msgbox.trim() + "\n" ;
			
			
		} else if(indelstringdata!=null) {
			 	msgbox += " ... RESULT: " +  indelstringdata.getListVariantsString().size() + " varieties x " + indelstringdata.getListPos().size() + " INDEL positions\n";
			 
				this.mapVariety2Mismatch = indelstringdata.mapVariety2Mismatch;
				this.mapVariety2Order= indelstringdata.mapVariety2Order;
				this.mapIdx2Pos= indelstringdata.mapIdx2Pos;
				this.listPos = indelstringdata.listPos;
				this.listVariantsString= indelstringdata.listVariantsString;
				this.msgbox += indelstringdata.msgbox;
				this.refnuc = indelstringdata.refnuc;
				this.mapPos2Alleleset = indelstringdata.mapPos2Alleleset;
				this.mapMSU7Pos2ConvertedPos = indelstringdata.getMapMSU7Pos2ConvertedPos(); // .mapMSU7Pos2ConvertedPos;
				this.npbContig = indelstringdata.getNpbContig();
				this.mapOrg2MSU7Pos2ConvertedPos = indelstringdata.getMapOrg2MSU7Pos2ConvertedPos();
			
		} else if(snpstringdata!=null) {

			 msgbox += " ... RESULT: " +  snpstringdata.getListVariantsString().size() + " varieties x "  + snpstringdata.getListPos().size() + " SNPS positions\n";
				this.mapVariety2Mismatch = snpstringdata.mapVariety2Mismatch;
				this.mapVariety2Order= snpstringdata.mapVariety2Order;
				this.mapIdx2Pos= snpstringdata.mapIdx2Pos;
				this.listPos = snpstringdata.listPos;
				this.listVariantsString= snpstringdata.listVariantsString;
				this.mapPos2Alleleset = snpstringdata.getMapPos2Alleleset();
				this.msgbox += snpstringdata.msgbox;
				this.refnuc = snpstringdata.refnuc;
				this.mapMSU7Pos2ConvertedPos = snpstringdata.getMapMSU7Pos2ConvertedPos(); // .mapMSU7Pos2ConvertedPos;
				this.npbContig = snpstringdata.getNpbContig();
				this.mapOrg2MSU7Pos2ConvertedPos = snpstringdata.getMapOrg2MSU7Pos2ConvertedPos();
				
				Map mapMergedIdx2SnpIdx=new HashMap();
				for(int i=0; i<listPos.size(); i++)
					mapMergedIdx2SnpIdx.put(i, i);
				this.snpstringdata.setMapMergedIdx2SnpIdx(mapMergedIdx2SnpIdx);
		}
	}
	
	

	public Map<BigDecimal, Set<String>> getMapPos2Alleleset() {
		return mapPos2Alleleset;
	}

	
	
	
	/*
	class SNPsStringData {
		
		private String  strRef;
		private Map<BigDecimal,String>  mapVarid2Snpsstr;
		private Map<BigDecimal, Map<Integer,Character>> mapVarid2SnpsAllele2str;
		private Map<BigDecimal, Set<Character>> mapIdx2NonsynAlleles;
		private Set<Integer> setSnpInExonTableIdx;
		
		public SNPsStringData(String strRef, Map mapVarid2Snpsstr,
				Map mapVarid2SnpsAllele2str, Map mapIdx2NonsynAlleles,
				Set setSnpInExonTableIdx) {
			super();
			//if(strRef.length()==0) throw new RuntimeException("SNPsStringData: reference has zreo length");
			//if(mapVarid2Snpsstr.size()==0) throw new RuntimeException("SNPsStringData: no variety");
			//if( ((String)mapVarid2Snpsstr.values().iterator().next()).length()==0) throw new RuntimeException("SNPsStringData: first variety has zero length Snpsstr");
			
			this.strRef = strRef;
			this.mapVarid2Snpsstr = mapVarid2Snpsstr;
			this.mapVarid2SnpsAllele2str = mapVarid2SnpsAllele2str;
			this.mapIdx2NonsynAlleles = mapIdx2NonsynAlleles;
			this.setSnpInExonTableIdx = setSnpInExonTableIdx;
		}
		public String getStrRef() {
			return strRef;
		}
		public Map<BigDecimal,String> getMapVarid2Snpsstr() {
			return mapVarid2Snpsstr;
		}
		public Map getMapVarid2SnpsAllele2str() {
			return mapVarid2SnpsAllele2str;
		}
		public Map getMapIdx2NonsynAlleles() {
			return mapIdx2NonsynAlleles;
		}
		public Set getSetSnpInExonTableIdx() {
			return setSnpInExonTableIdx;
		}
		
	}
	
	class IndelsStringData {
		
		private String  strRef;
		private Map<BigDecimal,String>  mapVarid2Snpsstr;
		private Map<BigDecimal, Map<Integer,Character>> mapVarid2SnpsAllele2str;
		
		public IndelsStringData(String strRef, Map mapVarid2Snpsstr,
				Map mapVarid2SnpsAllele2str) {
			super();
			//if(strRef.length()==0) throw new RuntimeException("SNPsStringData: reference has zreo length");
			//if(mapVarid2Snpsstr.size()==0) throw new RuntimeException("SNPsStringData: no variety");
			//if( ((String)mapVarid2Snpsstr.values().iterator().next()).length()==0) throw new RuntimeException("SNPsStringData: first variety has zero length Snpsstr");
			
			this.strRef = strRef;
			this.mapVarid2Snpsstr = mapVarid2Snpsstr;
			this.mapVarid2SnpsAllele2str = mapVarid2SnpsAllele2str;
		}
		public String getStrRef() {
			return strRef;
		}
		public Map<BigDecimal,String> getMapVarid2Snpsstr() {
			return mapVarid2Snpsstr;
		}
		public Map getMapVarid2SnpsAllele2str() {
			return mapVarid2SnpsAllele2str;
		}
		
	}
	*/
	






	class SnpsAllvarsPosComparator implements Comparator {
		@Override
		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub
			SnpsAllvarsPos pos1 = (SnpsAllvarsPos)o1;
			SnpsAllvarsPos pos2 = (SnpsAllvarsPos)o2;
			return pos1.getPos().compareTo(pos2.getPos());
		}
	}
	
	/**
	 * Sorts variety by mismatch desc, subpopulation, then country, then id
	 * Used in Mismatch ordering for the same number of mismatch,
	 * assuming variety from same subpopulation, then country will be closer relative than random 
	 * @author lmansueto
	 *
	 */
	class  SnpsStringAllvarsImplSorter implements Comparator {
		@Override
		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub
			SnpsStringAllvars s1 = (SnpsStringAllvars)o1; 
			SnpsStringAllvars s2 = (SnpsStringAllvars)o2;
			int ret = -s1.getMismatch().compareTo(s2.getMismatch());
//			if(ret==0) {
//				//return s1.getVar().compareTo( s2.getVar());
//				
//				
//				if(s1 instanceof IndelsStringAllvars && s2 instanceof IndelsStringAllvars) {
//					IndelsStringAllvars is1 = (IndelsStringAllvars)s1; 
//					IndelsStringAllvars is2 = (IndelsStringAllvars)s2;
//					//if(is1.getMapPos2Indels().size()<is2.getMapPos2Indels().size()) ret = 1;
//					//else if(is1.getMapPos2Indels().size()>is2.getMapPos2Indels().size()) ret = -1;
//					
//					//int sumIns1 = 0;
//					//int sumIns2 = 0;
//					//int sumDel1 = 0;
//					//int sumDel2 = 0;
//					
//					Set setAlleles1 = new HashSet();
//					Set setAlleles2 = new HashSet();
//					Iterator<IndelsAllvars> itIndels1 = is1.getMapPos2Indels().values().iterator();
//					Iterator<IndelsAllvars> itIndels2 = is1.getMapPos2Indels().values().iterator();
//					while(itIndels1.hasNext()) {
//						IndelsAllvars indel = itIndels1.next();
//						setAlleles1.add( indel.getAllele1() );
//					}
//					while(itIndels2.hasNext()) {
//						IndelsAllvars indel = itIndels2.next();
//						setAlleles2.add( indel.getAllele1() );
//					}
//					Set allele1notin2 = new HashSet(setAlleles1);
//					allele1notin2.removeAll(setAlleles2);
//					Set allele2notin1 = new HashSet(setAlleles2);
//					allele2notin1.removeAll(setAlleles1);
//					
//					if(allele2notin1.size() >  allele1notin2.size())
//						return allele2notin1.size() +  allele1notin2.size();
//					else if(allele2notin1.size() <  allele1notin2.size()) 
//						return - allele2notin1.size() -  allele1notin2.size();
//					else ret = 0;
//					
////					Set uniques = new HashSet(allele1notin2);
////					uniques.addAll(allele2notin1);
////					if(allele1notin2.size()>allele2notin1.size())
////						ret = uniques.size();
////					else if(allele1notin2.size()<allele2notin1.size())
////						ret = -uniques.size();
////					else if(uniques.size()!=0) {
////						if(setAlleles1.size()>setAlleles2.size())
////							return setAlleles1.size();
////						else if(setAlleles1.size()<setAlleles2.size())
////							return -setAlleles2.size();
////						else ret = 0;
////					} else ret=0;
//				}
//			}

			if(ret!=0) return ret;
			
			Variety v1 =listitemsdao.getMapId2Variety().get(s1.getVar());
			Variety v2 =listitemsdao.getMapId2Variety().get(s2.getVar());
			if(v1.getSubpopulation()!=null && v2.getSubpopulation()!=null)
			{
				ret=v1.getSubpopulation().compareTo(v2.getSubpopulation());
				if( ret!=0 )  return ret;
			} 
			
			if(v1.getCountry()!=null && v2.getCountry()!=null) {
					ret = v1.getCountry().compareTo(v2.getCountry());
					if(ret!=0) return ret;
			} 
			return v1.getVarietyId().compareTo(v2.getVarietyId());
		}
	}
	
	
	class IndelStringAllvarsImplSorter implements Comparator {
		@Override
		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub
			SnpsStringAllvars s1 = (SnpsStringAllvars)o1; 
			SnpsStringAllvars s2 = (SnpsStringAllvars)o2;
			int ret = -s1.getMismatch().compareTo(s2.getMismatch());

			if(ret!=0) return ret;
			
			Variety v1 =listitemsdao.getMapId2Variety().get(s1.getVar());
			Variety v2 =listitemsdao.getMapId2Variety().get(s2.getVar());
			return v1.getVarietyId().compareTo(v2.getVarietyId());
			
			/*
			Variety v1 =listitemsdao.getMapId2Variety().get(s1.getVar());
			Variety v2 =listitemsdao.getMapId2Variety().get(s2.getVar());
			if(v1.getSubpopulation()!=null && v2.getSubpopulation()!=null)
			{
				ret=v1.getSubpopulation().compareTo(v2.getSubpopulation());
				if( ret!=0 )  return ret;
			} 
			
			if(v1.getCountry()!=null && v2.getCountry()!=null) {
					ret = v1.getCountry().compareTo(v2.getCountry());
					if(ret!=0) return ret;
			} 
			
			return v1.getVarietyId().compareTo(v2.getVarietyId());
			*/
				
		}
	}
	
}
