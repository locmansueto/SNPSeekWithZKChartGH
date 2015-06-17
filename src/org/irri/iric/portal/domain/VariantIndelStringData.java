package org.irri.iric.portal.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.chado.domain.Feature;
import org.irri.iric.portal.genotype.service.IndelStringService;
import org.irri.iric.portal.genotype.service.SnpsAllvarsPosSorter;

public class VariantIndelStringData extends VariantStringData {
	
	
	private Map<BigDecimal, IndelsAllvarsPos> mapIndelId2Indel;
	private VariantIndelStringData alignedVariantIndelStringData;
	private Set<BigDecimal> setPosGapRegion;
	private Set<Long> setPosDeletionRegion;
	private Set<Integer> setGapIdx;
	private Map<BigDecimal,String> mapIndelpos2Refnuc;
	//private Map<Integer,String> mapIndelIdx2Refnuc;
	private String sequence;
	
	
	
	
	public VariantIndelStringData() {
		super();
		// TODO Auto-generated constructor stub
		setPosGapRegion = new HashSet();
		setPosDeletionRegion = new HashSet();
		listPos = new ArrayList();
		listVariantsString = new ArrayList();
		setGapIdx = new HashSet();
		sequence = "";
	}



	public VariantIndelStringData(Map<BigDecimal, Double> mapVariety2Mismatch,
			Map<BigDecimal, Integer> mapVariety2Order,
			List<SnpsAllvarsPos> listPos, Map<Integer, BigDecimal> mapIdx2Pos,
			List<SnpsStringAllvars> listVariantsString, Map<BigDecimal,Set<String>> mapPos2Alleleset) {
		super();
		this.mapVariety2Mismatch = mapVariety2Mismatch;
		this.mapVariety2Order = mapVariety2Order;
		this.listPos = listPos;
		this.mapIdx2Pos = mapIdx2Pos;
		this.listVariantsString = listVariantsString;
		this.mapPos2Alleleset=mapPos2Alleleset;
	}
	
	
	
	public Map<BigDecimal, IndelsAllvarsPos> getMapIndelId2Indel() {
		return mapIndelId2Indel;
	}

	public void setMapIndelId2Indel(
			Map<BigDecimal, IndelsAllvarsPos> mapIndelId2Indel) {
		this.mapIndelId2Indel = mapIndelId2Indel;
	}

	public VariantIndelStringData getAlignedIndels() {
		
		if(alignedVariantIndelStringData==null) {

			
			if(this.listPos.size()==0) return new VariantIndelStringData();
			
			//mapIndelIdx2Refnuc = new HashMap();
			mapIndelpos2Refnuc = new HashMap();
			int firstPos = this.listPos.get(0).getPos().intValue();
			// get maximum insertion length for each indel positions
			Map<BigDecimal, Integer> mapPos2Maxinsertlen = new HashMap();
			Map<BigDecimal, Integer> mapPos2Maxdeletelen = new HashMap();
			Iterator<BigDecimal> itPos = new TreeSet(mapPos2Alleleset.keySet()).iterator();
			int idxcount=0;
			while(itPos.hasNext()) {
				BigDecimal pos = itPos.next();
				
				//mapIndelIdx2Refnuc.put(idxcount, String.valueOf( sequence.charAt( pos.intValue() - firstPos ) ));
				mapIndelpos2Refnuc.put(pos, String.valueOf( sequence.charAt( pos.intValue() - firstPos ) ));
				idxcount++;
				
				Iterator<String> itAlleles = mapPos2Alleleset.get(pos).iterator();
				while(itAlleles.hasNext()) {
					String allele=itAlleles.next();
					int indeltype = IndelStringService.getIndelType(allele);
					Integer maxlen;
					switch(indeltype) {
						case IndelStringService.INDELTYPE_INSERTION:
							maxlen = mapPos2Maxinsertlen.get(pos);
							if(maxlen==null) {
								mapPos2Maxinsertlen.put(pos, allele.length());
							} else {
								if(allele.length()>maxlen) mapPos2Maxinsertlen.put(pos,allele.length());
							}
							break;
						case IndelStringService.INDELTYPE_DELETION:
							maxlen = mapPos2Maxdeletelen.get(pos);
							int dellen= Integer.valueOf(allele.replace("del","").trim());
							if(maxlen==null) {
								mapPos2Maxdeletelen.put(pos,  dellen);
							} else {
								if(dellen>maxlen) mapPos2Maxdeletelen.put(pos,dellen);
							}							
							break;
					}
				}
			}
			
			setPosGapRegion = new HashSet();
			// create gap positions
			List<SnpsAllvarsPos> gapPositions = new ArrayList();
			itPos =  mapPos2Maxinsertlen.keySet().iterator();
			while(itPos.hasNext()) {
				BigDecimal pos = itPos.next();
				Integer maxlen = mapPos2Maxinsertlen.get(pos);
				for(int i=1; i<=maxlen; i++) {
					BigDecimal bdGappos =  BigDecimal.valueOf( pos.longValue() + i*1.0/100.0);
					SnpsAllvarsPos gappos = new SnpsAllvarsPosImpl(bdGappos, IndelStringService.INDEL_GAP );
					setPosGapRegion.add(bdGappos );
					gapPositions.add(gappos);
					
				}
			}
		
		
			
			// fill refnucs 
//			Iterator<SnpsAllvarsPos> itPosAllvars = this.listPos.iterator();
//			while(itPosAllvars.hasNext()) {
//				SnpsAllvarsPos posallvars = itPosAllvars.next();
//				if(posallvars.getRefnuc().isEmpty()) {
//					posallvars.setRefnuc( String.valueOf( sequence.charAt( posallvars.getPos().intValue() - firstPos ) ));
//				}
//			}
			
			//AppContext.debug(" subseq length=" + sequence.length() + ", firstPos=" + firstPos);
			
			setPosDeletionRegion = new HashSet();
			// create deletion reference positions
			List<SnpsAllvarsPos> refconsensusPositions = new ArrayList();
			itPos =  mapPos2Maxdeletelen.keySet().iterator();
			while(itPos.hasNext()) {
				BigDecimal pos = itPos.next();
				Integer maxlen = mapPos2Maxdeletelen.get(pos);
				for(int i=1; i<=maxlen; i++) {
					try {
					BigDecimal bddelpos = 	BigDecimal.valueOf( pos.longValue() + i);
					SnpsAllvarsPos refpos = new SnpsAllvarsPosImpl( bddelpos, String.valueOf( sequence.charAt( pos.intValue()+i - firstPos ) ) );
					//SnpsAllvarsPos refpos = new SnpsAllvarsPosImpl( BigDecimal.valueOf( pos.longValue() + i), IndelStringService.INDEL_REFCONSENSUS );
					refconsensusPositions.add(refpos);
					 //AppContext.debug( "pos.intValue()+i=" + pos.intValue()+i); 
					setPosDeletionRegion.add(pos.longValue() + i);
					} catch(Exception ex) {
						ex.printStackTrace();
						throw new RuntimeException(ex);
					}
				}
			}
			
			List listAlignedPos = new ArrayList();
			listAlignedPos.addAll(this.listPos );
			listAlignedPos.addAll( gapPositions);
			listAlignedPos.addAll( refconsensusPositions);
			
			
			Collections.sort( listAlignedPos, new SnpsAllvarsPosSorter());
			
			Set<Integer> newsetIdxGap = new HashSet();
			Iterator< SnpsAllvarsPos> itSnpspos =  listAlignedPos.iterator();
			int idx=0;
			Map<Integer,BigDecimal> mapAlignedIdx2Pos = new TreeMap();
			while(itSnpspos.hasNext()) {
				mapAlignedIdx2Pos.put(idx,  itSnpspos.next().getPos() );
				newsetIdxGap.add(idx);
				idx++;
			}
			
			alignedVariantIndelStringData = new VariantIndelStringData(this.mapVariety2Mismatch,
					this.mapVariety2Order, listAlignedPos, mapAlignedIdx2Pos,  listVariantsString, mapPos2Alleleset);
			alignedVariantIndelStringData.setMapIndelId2Indel( this.mapIndelId2Indel );
			alignedVariantIndelStringData.setSetPosDeletionRegion(setPosDeletionRegion);
			alignedVariantIndelStringData.setSetPosGapRegion(setPosGapRegion);
			alignedVariantIndelStringData.setSetGapIdx(newsetIdxGap);
			//alignedVariantIndelStringData.setMapIndelIdx2Refnuc(mapIndelIdx2Refnuc);
			alignedVariantIndelStringData.setMapIndelpos2Refnuc(mapIndelpos2Refnuc);
			alignedVariantIndelStringData.setSequence(sequence);
			//alignedVariantIndelStringData.setMessage(msgbox);
			//alignedVariantIndelStringData.setMapMSU7Pos2ConvertedPos(mapMSU7Pos2ConvertedPos);
					
		}
		
		return alignedVariantIndelStringData;
	}





	public Set<BigDecimal> getSetPosGapRegion() {
		return setPosGapRegion;
	}



	public void setSetPosGapRegion(Set<BigDecimal> setPosGapRegion) {
		this.setPosGapRegion = setPosGapRegion;
	}



	public Set<Long> getSetPosDeletionRegion() {
		return setPosDeletionRegion;
	}



	public void setSetPosDeletionRegion(Set<Long> setPosDeletionRegion) {
		this.setPosDeletionRegion = setPosDeletionRegion;
	}


	/**
	 * First position is the first entry in SNP Pos List 
	 * @return
	 */
	public String getSequence() {
		return sequence;
	}


	/**
	 * First position is the first entry in SNP Pos List 
	 * @return
	 */
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}





//	public Map<Integer, String> getMapIndelIdx2Refnuc() {
//		return mapIndelIdx2Refnuc;
//	}
//
//
//
//	public void setMapIndelIdx2Refnuc(Map<Integer, String> mapIndelIdx2Refnuc) {
//		this.mapIndelIdx2Refnuc = mapIndelIdx2Refnuc;
//	}
//


	public Map<BigDecimal, String> getMapIndelpos2Refnuc() {
		return mapIndelpos2Refnuc;
	}



	public void setMapIndelpos2Refnuc(Map<BigDecimal, String> mapIndelpos2Refnuc) {
		this.mapIndelpos2Refnuc = mapIndelpos2Refnuc;
	}



	public Set<Integer> getSetGapIdx() {
		return setGapIdx;
	}



	public void setSetGapIdx(Set<Integer> setGapIdx) {
		this.setGapIdx = setGapIdx;
	}


	

}
