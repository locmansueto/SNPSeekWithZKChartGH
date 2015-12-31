package org.irri.iric.portal.genotype;

import java.math.BigDecimal;
import java.util.ArrayList;



import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.domain.IndelsAllvarsPos;
import org.irri.iric.portal.domain.Position;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsAllvarsPosImpl;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.genotype.service.IndelStringHDF5nRDBMSHybridService;
//import org.irri.iric.portal.chado.domain.Feature;
//import org.irri.iric.portal.genotype.service.IndelStringService;
import org.irri.iric.portal.genotype.service.SnpsAllvarsPosSorter;

public class VariantIndelStringData extends VariantStringData {
	
	
	private Map<BigDecimal, IndelsAllvarsPos> mapIndelId2Indel;
	private VariantIndelStringData alignedVariantIndelStringData;
	private Set<Position> setPosGapRegion;
	private Set<Position> setPosDeletionRegion;
	//private Set<Integer> setGapIdx;
	private Map<Position,String> mapIndelpos2Refnuc;
	//private Map<Integer,String> mapIndelIdx2Refnuc;
	private String sequence;
	private Map<Position, Set<String>> mapPos2Alleleset;
	
	
	
	
	public VariantIndelStringData() {
		super();
		// TODO Auto-generated constructor stub
		setPosGapRegion = new TreeSet();
		setPosDeletionRegion = new TreeSet();
		listPos = new ArrayList();
		listVariantsString = new ArrayList();
		//setGapIdx = new HashSet();
		sequence = "";
	}



	public VariantIndelStringData(Map<BigDecimal, Double> mapVariety2Mismatch,
			Map<BigDecimal, Integer> mapVariety2Order,
			List<SnpsAllvarsPos> listPos,
			//Map<Integer, BigDecimal> mapIdx2Pos,
			List<SnpsStringAllvars> listVariantsString, 
			Map<Position,Set<String>> mapPos2Alleleset) {
		this();
		this.mapVariety2Mismatch = mapVariety2Mismatch;
		this.mapVariety2Order = mapVariety2Order;
		this.listPos = listPos;
		//this.mapIdx2Pos = mapIdx2Pos;
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
		AppContext.debug("VariantIndelStringData getAlignedIndels: alignedVariantIndelStringData=" + alignedVariantIndelStringData);
		if(alignedVariantIndelStringData==null) {

			
			if(this.listPos.size()==0) return new VariantIndelStringData();
			
			
			//String contig= listPos.get(0).getContig();
			//mapIndelIdx2Refnuc = new HashMap();
			mapIndelpos2Refnuc = new TreeMap();
			
			int firstPos = this.listPos.get(0).getPosition().intValue();
			Iterator<SnpsAllvarsPos> itPos2 = listPos.iterator();
			while(itPos2.hasNext()) {
				SnpsAllvarsPos pos2 = itPos2.next();
				if(pos2.getRefnuc()==null || pos2.getRefnuc().isEmpty()) {
					String refnuc= sequence.substring(pos2.getPosition().intValue() - firstPos, pos2.getPosition().intValue() - firstPos +1 );
					AppContext.debug("setting indelpos ref at " + pos2.getPosition() + " to "+  refnuc);
					pos2.setRefnuc( refnuc);
				}
				mapIndelpos2Refnuc.put(pos2, String.valueOf( sequence.charAt( (pos2.getPosition().intValue() - firstPos ) )));
			}
			
			// get maximum insertion length for each indel positions
			Map<Position, Integer> mapPos2Maxinsertlen = new TreeMap();
			Map<Position, Integer> mapPos2Maxdeletelen = new TreeMap();
			Iterator<SnpsAllvarsPos> itPos = new TreeSet(mapPos2Alleleset.keySet()).iterator();
			int idxcount=0;
			while(itPos.hasNext()) {
				Position pos = itPos.next();
				
				//mapIndelIdx2Refnuc.put(idxcount, String.valueOf( sequence.charAt( pos.intValue() - firstPos ) ));
				mapIndelpos2Refnuc.put(pos, String.valueOf( sequence.charAt( pos.getPosition().intValue() - firstPos ) ));
				idxcount++;
				
				
				Iterator<String> itAlleles = mapPos2Alleleset.get(pos).iterator();
				while(itAlleles.hasNext()) {
					String allele=itAlleles.next();
					//int indeltype = IndelStringService.getIndelType(allele);
					int indeltype = IndelStringHDF5nRDBMSHybridService.getIndelType(allele);
					Integer maxlen;
					
					AppContext.debug("VariantIndelStringData getAlignedIndels: indeltype " + pos.getClass().getName() + "   " +  pos.getPosition() + "  " + allele + "=" + indeltype);
					
					if(indeltype==VariantStringService.INDELTYPE_INSERTION) {
						maxlen = mapPos2Maxinsertlen.get(pos);
						if(maxlen==null) {
							mapPos2Maxinsertlen.put(pos, allele.length());
						} else {
							if(allele.length()>maxlen) mapPos2Maxinsertlen.put(pos,allele.length());
						}
					}
					else if(indeltype==VariantStringService.INDELTYPE_DELETION) {
						maxlen = mapPos2Maxdeletelen.get(pos);
						int dellen= Integer.valueOf(allele.replace("del","").trim());
						
						if(dellen>51 || dellen<0) throw new RuntimeException(allele +"-> dellen=" + dellen );
						
						if(maxlen==null) {
							mapPos2Maxdeletelen.put(pos,  dellen);
						} else {
							if(dellen>maxlen.intValue()) mapPos2Maxdeletelen.put(pos,dellen);
						}
					}					
					else if(indeltype==VariantStringService.INDELTYPE_EXTENDDELETION) {
						
						maxlen = mapPos2Maxdeletelen.get(pos);
						int dellen= Integer.valueOf(allele.replace("extdel","").replace("-", "").trim());

						if(dellen>51 || dellen<0) throw new RuntimeException(allele +"-> dellen=" + dellen );

						//if(dellen>0 || maxlen==null) {
						if(maxlen==null) {
							mapPos2Maxdeletelen.put(pos,  dellen);
						} else {
							if(dellen>maxlen.intValue()) mapPos2Maxdeletelen.put(pos,dellen);
						}
						
					}					
					
					/*
					switch(indeltype) {
						case IndelStringService.INDELTYPE_INSERTION: 
						case IndelStringHDF5Service.INDELTYPE_INSERTION:
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
					*/
				}
			}
			
			
			AppContext.debug("mapPos2Maxinsertlen=" + mapPos2Maxinsertlen);
			
			setPosGapRegion = new TreeSet();
			// create gap positions
			//List<SnpsAllvarsPos> gapPositions = new ArrayList();
			Iterator<Position> itPos3 =  mapPos2Maxinsertlen.keySet().iterator();
			while(itPos3.hasNext()) {
				Position pos = itPos3.next();
				Integer maxlen = mapPos2Maxinsertlen.get(pos);
				for(int i=1; i<=maxlen; i++) {
					BigDecimal bdGappos =  BigDecimal.valueOf( pos.getPosition().longValue() + i*1.0/100.0);
					SnpsAllvarsPos gappos = new SnpsAllvarsPosImpl(bdGappos, VariantStringService.INDEL_GAP, pos.getContig() );
					//setPosGapRegion.add(bdGappos );
					//gapPositions.add(gappos);
					setPosGapRegion.add(gappos );
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
			
			setPosDeletionRegion = new TreeSet();
			// create deletion reference positions
			List<SnpsAllvarsPos> refconsensusPositions = new ArrayList();
			itPos3 =  mapPos2Maxdeletelen.keySet().iterator();
			while(itPos3.hasNext()) {
				Position pos = itPos3.next();
				Integer maxlen = mapPos2Maxdeletelen.get(pos);
				for(int i=1; i<=maxlen; i++) {
					try {
						BigDecimal bddelpos = 	BigDecimal.valueOf( pos.getPosition().longValue() + i);
						SnpsAllvarsPos refpos = new SnpsAllvarsPosImpl( bddelpos, String.valueOf( sequence.charAt( pos.getPosition().intValue()+i - firstPos ) ) ,  pos.getContig());
						//SnpsAllvarsPos refpos = new SnpsAllvarsPosImpl( BigDecimal.valueOf( pos.longValue() + i), IndelStringService.INDEL_REFCONSENSUS );
						refconsensusPositions.add(refpos);
						 //AppContext.debug( "pos.intValue()+i=" + pos.intValue()+i); 
						//setPosDeletionRegion.add(pos.longValue() + i);
						setPosDeletionRegion.add(refpos);
						
					} catch(Exception ex) {
						ex.printStackTrace();
						
						AppContext.debug("pos=" + pos + " ;maxdelen=" + maxlen);
						AppContext.debug("seqlength=" +  sequence.length() + ";pos.intValue()+i - firstPos="  + (pos.getPosition().intValue()+i - firstPos)+"; pos.intValue()="+pos.getPosition().intValue()+" ;i="+i + "  ;firstpos="+firstPos);
						
						
						throw new RuntimeException(ex);
					}
				}
			}
			
			/*
			List listAlignedPos = new ArrayList();
			listAlignedPos.addAll(this.listPos );
			listAlignedPos.addAll( gapPositions);
			listAlignedPos.addAll( refconsensusPositions);
			Collections.sort( listAlignedPos, new SnpsAllvarsPosSorter());
			*/
			
			Set setAlignedPos = new TreeSet(new SnpsAllvarsPosSorter());
			
			//setAlignedPos.addAll(this.listPos );
			//setAlignedPos.addAll( gapPositions);
			//setAlignedPos.addAll( refconsensusPositions);
			
			setAlignedPos.addAll(this.listPos );
			setAlignedPos.addAll( setPosGapRegion);
			setAlignedPos.addAll( setPosDeletionRegion);

			
			List listAlignedPos = new ArrayList();
			listAlignedPos.addAll(setAlignedPos);
			
//			
//			Set<Integer> newsetIdxGap = new HashSet();
//			Iterator< SnpsAllvarsPos> itSnpspos =  listAlignedPos.iterator();
//			int idx=0;
//			Map<Integer,BigDecimal> mapAlignedIdx2Pos = new TreeMap();
//			while(itSnpspos.hasNext()) {
//				mapAlignedIdx2Pos.put(idx,  itSnpspos.next().getPos() );
//				newsetIdxGap.add(idx);
//				idx++;
//			}
			
//			alignedVariantIndelStringData = new VariantIndelStringData(this.mapVariety2Mismatch,
//					this.mapVariety2Order, listAlignedPos, mapAlignedIdx2Pos,  listVariantsString, mapPos2Alleleset);
//			alignedVariantIndelStringData.setMapIndelId2Indel( this.mapIndelId2Indel );
//			alignedVariantIndelStringData.setSetPosDeletionRegion(setPosDeletionRegion);
//			alignedVariantIndelStringData.setSetPosGapRegion(setPosGapRegion);
//			alignedVariantIndelStringData.setSetGapIdx(newsetIdxGap);
//			//alignedVariantIndelStringData.setMapIndelIdx2Refnuc(mapIndelIdx2Refnuc);
//			alignedVariantIndelStringData.setMapIndelpos2Refnuc(mapIndelpos2Refnuc);
//			alignedVariantIndelStringData.setSequence(sequence);
//			//alignedVariantIndelStringData.setMessage(msgbox);
//			//alignedVariantIndelStringData.setMapMSU7Pos2ConvertedPos(mapMSU7Pos2ConvertedPos);
			

			alignedVariantIndelStringData = new  VariantIndelStringData(mapVariety2Mismatch,
					mapVariety2Order,
					listAlignedPos,
					listVariantsString, 
					mapPos2Alleleset);
			alignedVariantIndelStringData.setMapIndelId2Indel( this.mapIndelId2Indel );
			alignedVariantIndelStringData.setSetPosDeletionRegion(setPosDeletionRegion);
			alignedVariantIndelStringData.setSetPosGapRegion(setPosGapRegion);
//			alignedVariantIndelStringData.setSetGapIdx(newsetIdxGap);
//			alignedVariantIndelStringData.setMapIndelIdx2Refnuc(mapIndelIdx2Refnuc);
			alignedVariantIndelStringData.setMapIndelpos2Refnuc(mapIndelpos2Refnuc);
			alignedVariantIndelStringData.setSequence(sequence);
			alignedVariantIndelStringData.setMessage(msgbox);
			//alignedVariantIndelStringData.setMapMSU7Pos2ConvertedPos(mapMSU7Pos2ConvertedPos);
			
			if(AppContext.isLocalhost()) {
			AppContext.debug("mapIndelpos2Refnuc=" + mapIndelpos2Refnuc);
			AppContext.debug("setPosGapRegion=" + setPosGapRegion);
			AppContext.debug("setPosDeletionRegion=" + setPosDeletionRegion);
			}
			
					
		}
		
		return alignedVariantIndelStringData;
	}





	public Set<Position> getSetPosGapRegion() {
		return setPosGapRegion;
	}



	public void setSetPosGapRegion(Set<Position> setPosGapRegion) {
		this.setPosGapRegion = setPosGapRegion;
	}



	public Set<Position> getSetPosDeletionRegion() {
		return setPosDeletionRegion;
	}



	public void setSetPosDeletionRegion(Set<Position> setPosDeletionRegion) {
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


	public Map<Position, String> getMapIndelpos2Refnuc() {
		return mapIndelpos2Refnuc;
	}



	public void setMapIndelpos2Refnuc(Map<Position, String> mapIndelpos2Refnuc) {
		this.mapIndelpos2Refnuc = mapIndelpos2Refnuc;
	}


/*
	public Set<Integer> getSetGapIdx() {
		return setGapIdx;
	}

	public void setSetGapIdx(Set<Integer> setGapIdx) {
		this.setGapIdx = setGapIdx;
	}
*/

	

}
