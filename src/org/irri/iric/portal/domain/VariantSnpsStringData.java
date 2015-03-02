package org.irri.iric.portal.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.flatfile.domain.VSnpRefposindex;

public class VariantSnpsStringData extends VariantStringData {

	private VariantSnpsStringData nonsynVariantSnpsStringData;
	
	private String  strRef;
	private Map<BigDecimal, Map<Integer,Character>> mapVarid2SnpsAllele2str;
	//private Map<BigDecimal, Set<Character>> mapIdx2NonsynAlleles;
	private Map<Integer, Set<Character>> mapIdx2NonsynAlleles;
	private Set<Integer> setSnpInExonTableIdx;
	private Map<Integer,Integer> mapMergedIdx2SnpIdx;
	private Set<BigDecimal> setSnpSpliceDonorPos;
	private Set<BigDecimal> setSnpSpliceAcceptorPos;
	
	
	public VariantSnpsStringData(Map<BigDecimal, Double> mapVariety2Mismatch,
			Map<BigDecimal, Integer> mapVariety2Order,
			List<SnpsAllvarsPos> listPos, Map<Integer, BigDecimal> mapIdx2Pos,
			List<SnpsStringAllvars> listVariantsString, Map<BigDecimal,Set<String>> mapPos2Alleleset) {
		
		this(mapVariety2Mismatch, mapVariety2Order, listPos, mapIdx2Pos, listVariantsString, null, null, null, null, null, mapPos2Alleleset, null, null);
	}
	
	
	public VariantSnpsStringData(Map<BigDecimal, Double> mapVariety2Mismatch,
			Map<BigDecimal, Integer> mapVariety2Order,
			List<SnpsAllvarsPos> listPos, Map<Integer, BigDecimal> mapIdx2Pos,
			List<SnpsStringAllvars> listVariantsString,
			String strRef,
			Map<BigDecimal, Map<Integer, Character>> mapVarid2SnpsAllele2str,
			Map<Integer, Set<Character>> mapIdx2NonsynAlleles,
			Set<Integer> setSnpInExonTableIdx,
			Map<Integer, Integer> mapMergedIdx2SnpIdx, Map<BigDecimal,Set<String>> mapPos2Alleleset, 
			Set<BigDecimal> setSnpSpliceDonorPos, Set<BigDecimal> setSnpSpliceAcceptorPos) {
		super();

		this.mapVariety2Mismatch = mapVariety2Mismatch;
		this.mapVariety2Order = mapVariety2Order;
		this.listPos = listPos;
		this.mapIdx2Pos = mapIdx2Pos;
		this.listVariantsString = listVariantsString;
		
		this.strRef = strRef;
		this.mapVarid2SnpsAllele2str = mapVarid2SnpsAllele2str;
		this.mapIdx2NonsynAlleles = mapIdx2NonsynAlleles;
		this.setSnpInExonTableIdx = setSnpInExonTableIdx;
		this.mapMergedIdx2SnpIdx = mapMergedIdx2SnpIdx;
		this.mapPos2Alleleset = mapPos2Alleleset;
		
		this.setSnpSpliceAcceptorPos = setSnpSpliceAcceptorPos;
		this.setSnpSpliceDonorPos = setSnpSpliceDonorPos;
	}


	public String getStrRef() {
		return strRef;
	}
	public void setStrRef(String strRef) {
		this.strRef = strRef;
	}
	public Map<BigDecimal, Map<Integer, Character>> getMapVarid2SnpsAllele2str() {
		return mapVarid2SnpsAllele2str;
	}
	public void setMapVarid2SnpsAllele2str(
			Map<BigDecimal, Map<Integer, Character>> mapVarid2SnpsAllele2str) {
		this.mapVarid2SnpsAllele2str = mapVarid2SnpsAllele2str;
	}
	public Map<Integer, Set<Character>> getMapIdx2NonsynAlleles() {
		return mapIdx2NonsynAlleles;
	}
	public void setMapIdx2NonsynAlleles(
			Map<Integer, Set<Character>> mapIdx2NonsynAlleles) {
		this.mapIdx2NonsynAlleles = mapIdx2NonsynAlleles;
	}
	public Set<Integer> getSetSnpInExonTableIdx() {
		return setSnpInExonTableIdx;
	}
	public void setSetSnpInExonTableIdx(Set<Integer> setSnpInExonTableIdx) {
		this.setSnpInExonTableIdx = setSnpInExonTableIdx;
	}
	public Map<Integer, Integer> getMapMergedIdx2SnpIdx() {
		return mapMergedIdx2SnpIdx;
	}
	public void setMapMergedIdx2SnpIdx(Map<Integer, Integer> mapMergedIdx2SnpIdx) {
		this.mapMergedIdx2SnpIdx = mapMergedIdx2SnpIdx;
	}
	
	
	


	public Set<BigDecimal> getSetSnpSpliceDonorPos() {
		return setSnpSpliceDonorPos;
	}


	public Set<BigDecimal> getSetSnpSpliceAcceptorPos() {
		return setSnpSpliceAcceptorPos;
	}


	@Override
	public Map<BigDecimal, Set<String>> getMapPos2Alleleset() {
		if(mapPos2Alleleset==null) {
			mapPos2Alleleset=new HashMap();
			Set setNuc = new TreeSet();
			setNuc.add("A");setNuc.add("T");setNuc.add("G");setNuc.add("C");
			Iterator<BigDecimal> itpos = this.mapIdx2Pos.values().iterator();
			while(itpos.hasNext()) {
				mapPos2Alleleset.put(itpos.next(), setNuc);
			}
		}
		return mapPos2Alleleset;
	}

	public VariantSnpsStringData getNonsynPlusSpliceSnps() {
		return getNonsynSpliceSnps(true); 
	}
	
	/**
	 * Non-synonysmous = non-synonymous AND within_exon
	 * @return
	 */
	public VariantSnpsStringData getNonsynSnps() {
		return  getNonsynSpliceSnps(false); 
	}
	
	
	private VariantSnpsStringData getNonsynSpliceSnps(boolean includeSpliceSites) {
		//if(nonsynVariantSnpsStringData==null) {
		if(true) {
			if(this.listPos.size()==0) return null;
			
			
			
			List snpsposlist=this.listPos;
			//int indxs[] = new int[snpsposlist.size()];
			int indxscount = 0;
			int indxsnonsyncount = 0;
			//Map<Long, Integer> mapPos2Idx = new HashMap();
			//Map<BigDecimal, Integer> mapPos2Idx = new HashMap();
			Map<Integer, BigDecimal> mapIdx2PosNonsyn = new HashMap();
			Map<BigDecimal, Integer> mapSnpid2TableIdxNonsyn = new HashMap();
			
			List listNonsynPos=new ArrayList();
			Iterator itSnppos =snpsposlist.iterator();
			StringBuffer buffRef = new StringBuffer();
			Map<Integer,Integer> mapOld2NewIdx = new HashMap();
			
			AppContext.debug( "setSnpInExonTableIdx:" + setSnpInExonTableIdx.size() + ":" + setSnpInExonTableIdx);
			AppContext.debug( "mapIdx2NonsynAlleles:" + mapIdx2NonsynAlleles.size() + ":" + mapIdx2NonsynAlleles);
			Set setInExonAndNonsyn = new TreeSet(mapIdx2NonsynAlleles.keySet());
			setInExonAndNonsyn.retainAll(setSnpInExonTableIdx);
			AppContext.debug("setInExonAndNonsyn:"+ setInExonAndNonsyn.size() + ":" + setInExonAndNonsyn);
			
			while(itSnppos.hasNext()) {
				VSnpRefposindex snppos = (VSnpRefposindex)itSnppos.next();
				
				if(setSnpInExonTableIdx.contains(indxscount)) {
					// in exon, check if it has nonsynallele
					
					if(!mapIdx2NonsynAlleles.containsKey(indxscount)) {
						indxscount++;
						continue;
					}
					
					Set nonsynalleles = mapIdx2NonsynAlleles.get(indxscount);
					if(nonsynalleles==null || nonsynalleles.isEmpty()) {
						indxscount++;
						//AppContext.debug(  "getNonsynSnps: inexon, all synonymous idx=" +  indxscount);
						continue;
					}
					
					//AppContext.debug(  "getNonsynSnps: inexon, with nonsyn allele idx=" +  indxscount);
					
				} else {
					// not in exon, not nonsyn nor syn, 
					if(includeSpliceSites) {
						// include if in splice SNP site
						if(this.setSnpSpliceAcceptorPos!=null && this.setSnpSpliceAcceptorPos.contains( snppos.getPos())   ||
						  this.setSnpSpliceDonorPos!=null && this.setSnpSpliceDonorPos.contains( snppos.getPos())	)
						{ // include this
						}
						else {
							indxscount++;
							continue;
						}
					} else {
						indxscount++;
						continue;
					}
				}
				
				mapOld2NewIdx.put( indxscount , indxsnonsyncount);
				listNonsynPos.add( snppos );
				buffRef.append( snppos.getRefnuc());
				//indxs[indxsnonsyncount] =  snppos.getAlleleIndex().intValue();
				//mapPos2Idx.put(snppos.getPos().longValue(), indxscount);
				mapSnpid2TableIdxNonsyn.put(snppos.getSnpFeatureId(), indxsnonsyncount);
				mapIdx2PosNonsyn.put(indxsnonsyncount, snppos.getPos());
				indxsnonsyncount++;
				indxscount++;
			}
			
			
			Map<BigDecimal, Map<Integer,Character>> mapVarid2SnpsAllele2strNonysn=new HashMap();
			Map<Integer, Set<Character>> mapIdx2NonsynAllelesNonysn=new HashMap();
			Set<Integer> setSnpInExonTableIdxNonysn=new HashSet();
			Map<Integer,Integer> mapMergedIdx2SnpIdxNonysn= new HashMap();

			Iterator<Integer> itOldIdx = mapOld2NewIdx.keySet().iterator();
			while(itOldIdx.hasNext()) {
				Integer oldidx=itOldIdx.next();
				Integer newidx=mapOld2NewIdx.get(oldidx);
				if(newidx==null) continue;
				
				Set setAlleles = mapIdx2NonsynAlleles.get( oldidx);
				if(setAlleles!=null) mapIdx2NonsynAllelesNonysn.put( newidx , setAlleles);
				
				if(setSnpInExonTableIdx.contains( oldidx )) setSnpInExonTableIdxNonysn.add( newidx );
				
				
				Iterator<BigDecimal> itVars = mapVarid2SnpsAllele2str.keySet().iterator();
				while(itVars.hasNext()) {
					BigDecimal varid = itVars.next();
					Map<Integer,Character>  mapIdx2Allele2 = mapVarid2SnpsAllele2str.get(varid);
					Character allele2 = mapIdx2Allele2.get( oldidx );
					if(allele2!=null) {
						Map<Integer,Character>  mapIdx2Allele2Nonsyn =   mapVarid2SnpsAllele2strNonysn.get(varid);
						if(mapIdx2Allele2Nonsyn==null) {
							mapIdx2Allele2Nonsyn=new HashMap();
							mapVarid2SnpsAllele2strNonysn.put(varid, mapIdx2Allele2Nonsyn);
						}
						mapIdx2Allele2Nonsyn.put( newidx , allele2);
					} 
				}
				
			}
			
			nonsynVariantSnpsStringData = new VariantSnpsStringData(mapVariety2Mismatch,
						mapVariety2Order,
						listNonsynPos, mapIdx2PosNonsyn,
						listVariantsString,
						buffRef.toString(),
						mapVarid2SnpsAllele2strNonysn,
						mapIdx2NonsynAllelesNonysn,
						setSnpInExonTableIdxNonysn,
						mapMergedIdx2SnpIdxNonysn , mapPos2Alleleset,
						setSnpSpliceDonorPos, setSnpSpliceAcceptorPos);
			
		}
		
		return nonsynVariantSnpsStringData;
	}
	
	
	/**
	 * Non-synonymous = non-synonymous
	 * @return
	 */
	public VariantSnpsStringData getNonsynSnpsPlusNoncoding() {
		if(nonsynVariantSnpsStringData==null) {
			if(this.listPos.size()==0) return null;
			
			
			List snpsposlist=this.listPos;
			//int indxs[] = new int[snpsposlist.size()];
			int indxscount = 0;
			int indxsnonsyncount = 0;
			//Map<Long, Integer> mapPos2Idx = new HashMap();
			//Map<BigDecimal, Integer> mapPos2Idx = new HashMap();
			Map<Integer, BigDecimal> mapIdx2PosNonsyn = new HashMap();
			Map<BigDecimal, Integer> mapSnpid2TableIdxNonsyn = new HashMap();
			
			List listNonsynPos=new ArrayList();
			Iterator itSnppos =snpsposlist.iterator();
			StringBuffer buffRef = new StringBuffer();
			Map<Integer,Integer> mapOld2NewIdx = new HashMap();
			
			while(itSnppos.hasNext()) {
				VSnpRefposindex snppos = (VSnpRefposindex)itSnppos.next();
				
				if(setSnpInExonTableIdx.contains(indxscount)) {
					// in exon, check is has nonsynallele
					Set nonsynalleles = mapIdx2NonsynAlleles.get(BigDecimal.valueOf(indxscount));
					if(nonsynalleles==null) {
						indxscount++;
						continue;
					}
				}
				mapOld2NewIdx.put( indxscount , indxsnonsyncount);
				listNonsynPos.add( snppos );
				buffRef.append( snppos.getRefnuc());
				//indxs[indxsnonsyncount] =  snppos.getAlleleIndex().intValue();
				//mapPos2Idx.put(snppos.getPos().longValue(), indxscount);
				mapSnpid2TableIdxNonsyn.put(snppos.getSnpFeatureId(), indxsnonsyncount);
				mapIdx2PosNonsyn.put(indxsnonsyncount, snppos.getPos());
				indxsnonsyncount++;
				indxscount++;
			}
			
			
			Map<BigDecimal, Map<Integer,Character>> mapVarid2SnpsAllele2strNonysn=new HashMap();
			Map<Integer, Set<Character>> mapIdx2NonsynAllelesNonysn=new HashMap();
			Set<Integer> setSnpInExonTableIdxNonysn=new HashSet();
			Map<Integer,Integer> mapMergedIdx2SnpIdxNonysn= new HashMap();

			Iterator<Integer> itOldIdx = mapOld2NewIdx.keySet().iterator();
			while(itOldIdx.hasNext()) {
				Integer oldidx=itOldIdx.next();
				Integer newidx=mapOld2NewIdx.get(oldidx);
				if(newidx==null) continue;
				
				Set setAlleles = mapIdx2NonsynAlleles.get( BigDecimal.valueOf(oldidx));
				if(setAlleles!=null) mapIdx2NonsynAllelesNonysn.put( newidx , setAlleles);
				
				if(setSnpInExonTableIdx.contains( oldidx )) setSnpInExonTableIdxNonysn.add( newidx );
				
				
				Iterator<BigDecimal> itVars = mapVarid2SnpsAllele2str.keySet().iterator();
				while(itVars.hasNext()) {
					BigDecimal varid = itVars.next();
					Map<Integer,Character>  mapIdx2Allele2 = mapVarid2SnpsAllele2str.get(varid);
					Character allele2 = mapIdx2Allele2.get( oldidx );
					if(allele2!=null) {
						Map<Integer,Character>  mapIdx2Allele2Nonsyn =   mapVarid2SnpsAllele2strNonysn.get(varid);
						if(mapIdx2Allele2Nonsyn==null) {
							mapIdx2Allele2Nonsyn=new HashMap();
							mapVarid2SnpsAllele2strNonysn.put(varid, mapIdx2Allele2Nonsyn);
						}
						mapIdx2Allele2Nonsyn.put( newidx , allele2);
					} 
				}
				
			}
			
			nonsynVariantSnpsStringData = new VariantSnpsStringData(mapVariety2Mismatch,
						mapVariety2Order,
						listNonsynPos, mapIdx2PosNonsyn,
						listVariantsString,
						buffRef.toString(),
						mapVarid2SnpsAllele2strNonysn,
						mapIdx2NonsynAllelesNonysn,
						setSnpInExonTableIdxNonysn,
						mapMergedIdx2SnpIdxNonysn , mapPos2Alleleset,
						setSnpSpliceDonorPos, setSnpSpliceAcceptorPos);
			
		
			
		}
		
		return nonsynVariantSnpsStringData;
	}
	
	
	
}
