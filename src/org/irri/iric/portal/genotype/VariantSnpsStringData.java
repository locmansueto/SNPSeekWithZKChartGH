package org.irri.iric.portal.genotype;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.chado.oracle.domain.VSnpRefposindex;
import org.irri.iric.portal.domain.Position;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.domain.SnpsStringAllvarsImpl;
import org.irri.iric.portal.genotype.service.SnpsStringHDF5nRDBMSHybridService;
import org.irri.iric.portal.genotype.service.SnpsStringAllvarsImplSorter;

public class VariantSnpsStringData extends VariantStringData {

	private VariantSnpsStringData nonsynVariantSnpsStringData;
	

	
	private String  strRef;
	private Map<BigDecimal, Map<Position,Character>> mapVarid2SnpsAllele2str;
	//private Map<BigDecimal, Set<Character>> mapIdx2NonsynAlleles;
	private Map<Position , Set<Character>> mapPos2NonsynAlleles;
	private Set<Position> setSnpInExonPos;
	//private Map<Integer,Integer> mapMergedIdx2SnpIdx;
	private Set<Position> setSnpSpliceDonorPos;
	private Set<Position> setSnpSpliceAcceptorPos;
	
	
	public VariantSnpsStringData(Map<BigDecimal, Double> mapVariety2Mismatch,
			Map<BigDecimal, Integer> mapVariety2Order,
			List<SnpsAllvarsPos> listPos, 
			//Map<Integer, BigDecimal> mapIdx2Pos,
			List<SnpsStringAllvars> listVariantsString) {
		
		this(mapVariety2Mismatch, mapVariety2Order, listPos,  listVariantsString, null, null, null, null, null,  null);
	}
	
	
	public VariantSnpsStringData(Map<BigDecimal, Double> mapVariety2Mismatch,
			Map<BigDecimal, Integer> mapVariety2Order,
			List<SnpsAllvarsPos> listPos, 
			//Map<Integer, BigDecimal> mapIdx2Pos,
			List<SnpsStringAllvars> listVariantsString,
			String strRef,
			
			//Map<BigDecimal, Map<Integer, Character>> mapVarid2SnpsAllele2str,
			Map<BigDecimal, Map<Position, Character>> mapVarid2SnpsAllele2str,
			
			Map<Position, Set<Character>> mapPos2NonsynAlleles,
			Set<Position> setSnpInExonPos,
			//Map<Integer, Integer> mapMergedIdx2SnpIdx, Map<BigDecimal,Set<String>> mapPos2Alleleset, 
			Set<Position> setSnpSpliceDonorPos,
			Set<Position> setSnpSpliceAcceptorPos) {
		super();

		this.mapVariety2Mismatch = mapVariety2Mismatch;
		this.mapVariety2Order = mapVariety2Order;
		this.listPos = listPos;
		//this.mapIdx2Pos = mapIdx2Pos;
		this.listVariantsString = listVariantsString;
		
		this.strRef = strRef;
		this.mapVarid2SnpsAllele2str = mapVarid2SnpsAllele2str;
		this.mapPos2NonsynAlleles = mapPos2NonsynAlleles;
		this.setSnpInExonPos = setSnpInExonPos;
		//this.mapMergedIdx2SnpIdx = mapMergedIdx2SnpIdx;
		//this.mapPos2Alleleset = mapPos2Alleleset;
		
		this.setSnpSpliceAcceptorPos = setSnpSpliceAcceptorPos;
		this.setSnpSpliceDonorPos = setSnpSpliceDonorPos;
	}


	public String getStrRef() {
		return strRef;
	}
	public void setStrRef(String strRef) {
		this.strRef = strRef;
	}
	public Map<BigDecimal, Map<Position, Character>> getMapVarid2SnpsAllele2str() {
		return mapVarid2SnpsAllele2str;
	}
	public void setMapVarid2SnpsAllele2str( Map<BigDecimal, Map<Position, Character>> mapVarid2SnpsAllele2str) {
		this.mapVarid2SnpsAllele2str = mapVarid2SnpsAllele2str;
	}
	
	

	public Map<Position, Set<Character>> getMapPos2NonsynAlleles() {
		return mapPos2NonsynAlleles;
	}


	public Set<Position> getSetSnpInExonPos() {
		return setSnpInExonPos;
	}


	public Set<Position> getSetSnpSpliceDonorPos() {
		return setSnpSpliceDonorPos;
	}


	public Set<Position> getSetSnpSpliceAcceptorPos() {
		return setSnpSpliceAcceptorPos;
	}

/*
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
*/
	public VariantSnpsStringData getNonsynPlusSpliceSnps() {
		return getNonsynSpliceSnps(true, false); 
	}
	
	/**
	 * Non-synonysmous = non-synonymous AND within_exon
	 * @return
	 */
	public VariantSnpsStringData getNonsynSnps() {
		return  getNonsynSpliceSnps(false, true); 
	}
	
	
	private VariantSnpsStringData getNonsynSpliceSnps(boolean includeSpliceSites, boolean includeNoncoding) {
		//if(nonsynVariantSnpsStringData==null) {
		if(true) {
			if(this.listPos.size()==0) return null;
			
			List snpsposlist=this.listPos;

			List listNonsynPos=new ArrayList();
			Iterator<SnpsAllvarsPos> itSnppos =snpsposlist.iterator();
			StringBuffer buffRef = new StringBuffer();

			Set setInExonAndNonsyn = new TreeSet( this.mapPos2NonsynAlleles.keySet());
			//setInExonAndNonsyn.retainAll(setSnpInExonTableIdx);
			AppContext.debug("setInExonAndNonsyn:"+ setInExonAndNonsyn.size() + ":" + setInExonAndNonsyn);
			
			int includeidx=0;
			//Map<BigDecimal, Map<Integer,Character>> mapVar2Idx2Allele2=new HashMap(); 
			
			Set<Integer> setIncludeIdx=new TreeSet();
			while(itSnppos.hasNext()) {
				//VSnpRefposindex snppos = (VSnpRefposindex)itSnppos.next();
				SnpsAllvarsPos snppos = itSnppos.next();
				boolean includethis=false;
				//if(setInExonAndNonsyn.contains( snppos.getPos())) {
				if(setInExonAndNonsyn.contains( snppos)) {
					listNonsynPos.add( snppos );
					includethis=true;
				}
				
				if(includeSpliceSites) {
					// include if in splice SNP site
					//if(setSnpSpliceAcceptorPos.contains( snppos.getPos())   ||
					//  setSnpSpliceDonorPos.contains( snppos.getPos())	)
					if(setSnpSpliceAcceptorPos.contains( snppos) || setSnpSpliceDonorPos.contains( snppos)	)
					{ // include this
						listNonsynPos.add( snppos );
						includethis=true;
					}
				}
				
				if(includethis) {
					buffRef.append( snppos.getRefnuc());
					setIncludeIdx.add(includeidx);
				}
				includeidx++;
			}
			
			String nonsynRef= buffRef.toString();
			
			//List listNonsynVariantsString=new ArrayList();
			Set sortedVarieties = new TreeSet(new SnpsStringAllvarsImplSorter());
			
			Iterator<SnpsStringAllvars> itAllvars =  listVariantsString.iterator();
			while(itAllvars.hasNext()) {
				SnpsStringAllvars varstr = itAllvars.next();
				String varnuc = varstr.getVarnuc();
				if(varnuc.length()!=snpsposlist.size()) throw new RuntimeException("varnuc.length()!=snpsposlist.size(): " + varnuc.length() + " ; " + snpsposlist.size());
				StringBuffer buffNuc=new StringBuffer();
				
				Map<Position,Character> mapPos2Allele2=getMapVarid2SnpsAllele2str().get(varstr.getVar());
				Map<Integer,Character> mapIdx2Allele2=null;
				int includecount=0;
				Iterator<Integer> itInclude = setIncludeIdx.iterator();
				while(itInclude.hasNext()) {
					int includei=itInclude.next();
					buffNuc.append( varnuc.substring(includei, includei+1 ));
				}
				
				String varNuc=buffNuc.toString();
				//List listpos, String var1, String var2, boolean var1isref, Map<Integer,Character> var1allele2str, Map<Integer,Character> var2allele2str,	
				//Map<Position,Set<Character>> mapPos2NonsynAlleles,  Set<Position> setInExon, Set<Position> setNonsynPos, boolean isNonsynOnly
				
				
				
				double misCount = SnpsStringHDF5nRDBMSHybridService.countVarpairMismatch(listNonsynPos, nonsynRef, varNuc,  true,   null,  varstr.getMapPos2Allele2() ,  
						//(Map)snpstrdata.getMapIdx2NonsynAlleles(),  snpstrdata.getSetSnpInExonTableIdx(), varNonsynIdx,  params.isbExcludeSynonymous() );
						this.getMapPos2NonsynAlleles() ,  this.getSetSnpInExonPos() , varstr.getNonsynPosset(),   true ); //  .isbExcludeSynonymous() );
				
				
				sortedVarieties.add(  new  SnpsStringAllvarsImpl(varstr.getVar(), varstr.getContig(),   varNuc,
						//BigDecimal.valueOf(misCount),    varstr.getMapPos2Allele2(),  varstr.getNonsynPosset(), varstr.getDonorPosset(),  varstr.getAcceptorPosset() ));
						BigDecimal.valueOf(misCount),   mapPos2Allele2,  varstr.getNonsynPosset(), varstr.getDonorPosset(),  varstr.getAcceptorPosset() ));
				
				
			}
			
			
			List<SnpsStringAllvars> listResult = new ArrayList();
			// sort included varieties
			Map mapVariety2Order = new HashMap();
			Map mapVariety2Mismatch = new HashMap();
			int ordercount = 0;
			Iterator itSorVars =  sortedVarieties.iterator();
			while(itSorVars.hasNext()) {
				SnpsStringAllvars snpstrvar = (SnpsStringAllvars)itSorVars.next();
				listResult.add( snpstrvar );
				mapVariety2Order.put(snpstrvar.getVar() ,ordercount);
				mapVariety2Mismatch.put( snpstrvar.getVar(), snpstrvar.getMismatch().intValue());
				ordercount++;
			}
			
			
			
			
//			Map<BigDecimal, Map<Integer,Character>> mapVarid2SnpsAllele2strNonysn=new HashMap();
//			Map<Integer, Set<Character>> mapIdx2NonsynAllelesNonysn=new HashMap();
//			Set<Integer> setSnpInExonTableIdxNonysn=new HashSet();
//			Map<Integer,Integer> mapMergedIdx2SnpIdxNonysn= new HashMap();
//
//			Iterator<Integer> itOldIdx = mapOld2NewIdx.keySet().iterator();
//			while(itOldIdx.hasNext()) {
//				Integer oldidx=itOldIdx.next();
//				Integer newidx=mapOld2NewIdx.get(oldidx);
//				if(newidx==null) continue;
//				
//				Set setAlleles = mapIdx2NonsynAlleles.get( oldidx);
//				if(setAlleles!=null) mapIdx2NonsynAllelesNonysn.put( newidx , setAlleles);
//				
//				if(setSnpInExonTableIdx.contains( oldidx )) setSnpInExonTableIdxNonysn.add( newidx );
//				
//				
//				Iterator<BigDecimal> itVars = mapVarid2SnpsAllele2str.keySet().iterator();
//				while(itVars.hasNext()) {
//					BigDecimal varid = itVars.next();
//					Map<Integer,Character>  mapIdx2Allele2 = mapVarid2SnpsAllele2str.get(varid);
//					Character allele2 = mapIdx2Allele2.get( oldidx );
//					if(allele2!=null) {
//						Map<Integer,Character>  mapIdx2Allele2Nonsyn =   mapVarid2SnpsAllele2strNonysn.get(varid);
//						if(mapIdx2Allele2Nonsyn==null) {
//							mapIdx2Allele2Nonsyn=new HashMap();
//							mapVarid2SnpsAllele2strNonysn.put(varid, mapIdx2Allele2Nonsyn);
//						}
//						mapIdx2Allele2Nonsyn.put( newidx , allele2);
//					} 
//				}
//				
//			}
//			
//			nonsynVariantSnpsStringData = new VariantSnpsStringData(mapVariety2Mismatch,
//						mapVariety2Order,
//						listNonsynPos, mapIdx2PosNonsyn,
//						listVariantsString,
//						buffRef.toString(),
//						mapVarid2SnpsAllele2strNonysn,
//						mapIdx2NonsynAllelesNonysn,
//						setSnpInExonTableIdxNonysn,
//						mapMergedIdx2SnpIdxNonysn , mapPos2Alleleset,
//						setSnpSpliceDonorPos, setSnpSpliceAcceptorPos);
			
			nonsynVariantSnpsStringData = new VariantSnpsStringData(mapVariety2Mismatch,
					mapVariety2Order,
					listNonsynPos, 
					listResult,
					buffRef.toString(),
					mapVarid2SnpsAllele2str,
					mapPos2NonsynAlleles,
					setSnpInExonPos,
					setSnpSpliceDonorPos,
					setSnpSpliceAcceptorPos);
			
		}
		
		return nonsynVariantSnpsStringData;
	}
	
	
	/**
	 * Non-synonymous = non-synonymous
	 * @return
	 */
	public VariantSnpsStringData getNonsynSnpsPlusNoncoding() {
		return getNonsynSpliceSnps(true, true); 
	}
	
	
}
