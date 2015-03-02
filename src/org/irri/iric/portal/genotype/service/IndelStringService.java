package org.irri.iric.portal.genotype.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.IndelStringDAOImpl;
import org.irri.iric.portal.dao.IndelsAllvarsDAO;
import org.irri.iric.portal.dao.IndelsAllvarsPosDAO;
import org.irri.iric.portal.dao.SequenceDAO;
import org.irri.iric.portal.dao.SnpsAllvarsDAO;
import org.irri.iric.portal.dao.SnpsAllvarsPosDAO;
import org.irri.iric.portal.domain.IndelsAllvars;
import org.irri.iric.portal.domain.IndelsAllvarsPos;
import org.irri.iric.portal.domain.IndelsStringAllvars;
import org.irri.iric.portal.domain.Snps2VarsImpl;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.domain.VariantIndelStringData;
import org.irri.iric.portal.domain.VariantSnpsStringData;
import org.irri.iric.portal.domain.VariantStringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("IndelService")
public class IndelStringService implements VariantStringService {
	
	@Autowired
	@Qualifier("IndelsAllvarsPosDAO")
	private IndelsAllvarsPosDAO indelsAllvarsPosDAO;
	
	@Autowired
	//@Qualifier("IndelCallDAO")
	@Qualifier("IndelAllvarsDAO")
	private IndelsAllvarsDAO indelsAllvarsDAO;

	@Autowired
	private SequenceDAO indelSequenceDAO;
	
	private static BigDecimal bdHundred = BigDecimal.valueOf(100);
	
	@Override
	public VariantStringData getVariantString(GenotypeQueryParams params,
			Collection colVarids, String chr, Long start, Long stop) {
		// TODO Auto-generated method stub
		return  getIndels(colVarids, Integer.valueOf(chr), start, stop, null,params.isbMismatchonly());
	}


	@Override
	public VariantStringData getVariantString(GenotypeQueryParams params,
			String chr, Long start, Long stop) {
		// TODO Auto-generated method stub
		return getIndels(null, Integer.valueOf(chr), start, stop, null,params.isbMismatchonly());
	}


	@Override
	public VariantStringData getVariantString(GenotypeQueryParams params,
			Collection colVarids, String chr, Collection colSnppos) {
		// TODO Auto-generated method stub
		return  getIndels(colVarids, Integer.valueOf(chr), null, null, colSnppos,params.isbMismatchonly());
	}


	@Override
	public VariantStringData getVariantString(GenotypeQueryParams params,
			String chr, Collection colSnppos) {
		// TODO Auto-generated method stub
		return  getIndels(null, Integer.valueOf(chr), null, null, colSnppos,params.isbMismatchonly());
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
	
	
	private VariantIndelStringData getIndels(Collection limitVarIds, Integer chr, Long start, Long end, Collection setPositions, boolean mismatchOnly){
			
			indelsAllvarsPosDAO = (IndelsAllvarsPosDAO)AppContext.checkBean(indelsAllvarsPosDAO, "IndelsAllvarsPosDAO");
			indelsAllvarsDAO = (IndelsAllvarsDAO)AppContext.checkBean(  indelsAllvarsDAO , "IndelCallDAO");
			indelSequenceDAO = (SequenceDAO)AppContext.checkBean(  indelSequenceDAO , "SequenceDAO");
			
			
			IndelStringDAOImpl indelstringdao = new IndelStringDAOImpl(indelsAllvarsPosDAO, indelsAllvarsDAO, mismatchOnly);
			Map<BigDecimal, IndelsStringAllvars> mapVar2Indelstring=null;
			
			if(setPositions==null || setPositions.isEmpty()) {
				if(limitVarIds!=null && !limitVarIds.isEmpty() ) {
					
					AppContext.resetTimer("using readIndelString1 start");
					mapVar2Indelstring=indelstringdao.readSNPString((Set)limitVarIds, chr, start.intValue(), end.intValue());
					AppContext.resetTimer("using readIndelString1 end");
	
				}
				else {
					AppContext.resetTimer("using readIndelString2 start");
					mapVar2Indelstring=indelstringdao.readSNPString(chr, start.intValue(), end.intValue());
					AppContext.resetTimer("using readIndelString2 end");
				}
			} else {
				
				int indxs[] = new int[setPositions.size()];
				int indxscount = 0;
				Iterator itSnppos =setPositions.iterator();
				while(itSnppos.hasNext()) {
					BigDecimal snppos = (BigDecimal)itSnppos.next(); 
					indxs[indxscount] =  snppos.intValue();
					indxscount++;
				}
				if(limitVarIds!=null && !limitVarIds.isEmpty() ) {
					
					AppContext.resetTimer("using readIndelString3 start");
					mapVar2Indelstring=indelstringdao.readSNPString((Set)limitVarIds, chr, indxs);
					AppContext.resetTimer("using readIndelString3 end");
	
				}
				else {
					AppContext.resetTimer("using readIndelString4 start");
					mapVar2Indelstring=indelstringdao.readSNPString(chr, indxs);
					AppContext.resetTimer("using readIndelString4 end");
				}
			}
			
			
			if(mapVar2Indelstring.size()==0) return new VariantIndelStringData();
			
			String subseq = "";
			try {
				subseq = indelSequenceDAO.getSubSequence(BigDecimal.valueOf(chr+2), indelstringdao.getListPos().get(0).getPos().longValue() , end+100);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
				
			
			//indelstringdao.get
			//listVariants.addAll( indelstringdao.getListResult());
//			listVariantsIndels = indelstringdao.getListResult();
//			mapIndelVariety2Mismatch = indelstringdao.getMapVariety2Mismatch();
//			mapIndelVariety2Order = indelstringdao.getMapVariety2Order();
//			listIndelsnpsposlist = indelstringdao.getListPos();
//			this.mapIndelId2Indel =  indelstringdao.getMapAlleleId2Indel();
//			this.mapIndelIdx2Pos = indelstringdao.getMapIdx2Pos();
			
			// get pos2allele
			
			Map mapIndelId2Indel = indelstringdao.getMapAlleleId2Indel();

			Map<BigDecimal, Set<String>> mapPos2Allele=new HashMap();
			Iterator<IndelsAllvarsPos> itIndels =  mapIndelId2Indel.values().iterator();
			while(itIndels.hasNext()) {
				IndelsAllvarsPos indelpos = itIndels.next(); 
				Set setalleles = mapPos2Allele.get(indelpos.getPos());
				if(setalleles==null) {
					setalleles = new TreeSet();
					mapPos2Allele.put(indelpos.getPos(), setalleles);
				}
				setalleles.add( getIndelAlleleString( indelpos) );
			}
			
//			indelstringdao.getMapVariety2Mismatch();
//			indelstringdao.getMapVariety2Order(); 
//			indelstringdao.getListPos();
//			indelstringdao.getMapIdx2Pos();
//			indelstringdao.getListResult();
			
			VariantIndelStringData indelstrdata= new VariantIndelStringData(indelstringdao.getMapVariety2Mismatch(), indelstringdao.getMapVariety2Order(), 
					indelstringdao.getListPos(), indelstringdao.getMapIdx2Pos(), indelstringdao.getListResult(),  mapPos2Allele );
			indelstrdata.setMapIndelId2Indel(mapIndelId2Indel);
			indelstrdata.setSequence(subseq);

			
			 return indelstrdata;
	}
	

	public static String[] createVarietyString(SnpsStringAllvars snpstr, VariantStringData varstringdata) {
		
		int cols = varstringdata.getListPos().size();
		Map<Integer,BigDecimal> mapMergedIdx2Pos = varstringdata.getMapIdx2Pos();
		Map<Integer,BigDecimal> mapIndelIdx2Pos  =varstringdata.getMapIdx2Pos();
		
		
		Map<BigDecimal, IndelsAllvarsPos>	mapIndelId2Indels=null;
		Map<Integer, Integer> mapMergedIdx2SnpIdx=null;
		if(varstringdata.hasIndel()) {
			VariantIndelStringData varindelstringdata = varstringdata.getIndelstringdata();
			mapIndelId2Indels = varindelstringdata.getMapIndelId2Indel();
			if(varstringdata.hasSnp()) {
				VariantSnpsStringData varsnpstringdata = varstringdata.getSnpstringdata();
				mapMergedIdx2SnpIdx = varsnpstringdata.getMapMergedIdx2SnpIdx();
			}
		}

		String allelesstr[] = new String[cols];  
		for(int iCols=0; iCols<cols; iCols++) {
			//SnpsStringAllvars snpstr =  (SnpsStringAllvars)matrixModel.getCellAt(cellsdata, iCols);
			StringBuffer buff = new StringBuffer();
			
				if(snpstr instanceof IndelsStringAllvars) {
					
					int j=iCols;
					
					BigDecimal pos = null;
					if(mapMergedIdx2Pos!=null) 
						pos = mapMergedIdx2Pos.get(j);
					else 
						pos = mapIndelIdx2Pos.get(j);
					
					if(pos!=null) {
					
						IndelsStringAllvars indelstring=(IndelsStringAllvars)snpstr;
						
						
						
						BigDecimal alleleid = indelstring.getAllele1( pos );
						IndelsAllvarsPos indelpos = mapIndelId2Indels.get(alleleid);
						
						//if(indelpos==null) throw new RuntimeException("cant find alleleid=" + alleleid);
						if(alleleid!=null && indelpos!=null) {
							
							buff.append( IndelStringService.getIndelAlleleString(indelpos) );
							
							BigDecimal alleleid2 = indelstring.getAllele2( pos );
							indelpos = mapIndelId2Indels.get(alleleid2);
							
							if(alleleid2!=null && !alleleid2.equals(alleleid)) {
								alleleid=alleleid2;
								indelpos = mapIndelId2Indels.get(alleleid);
								if(indelpos!=null) {
									buff.append("/").append(  IndelStringService.getIndelAlleleString(indelpos) );
								}								
							}
						}
						
						if(indelstring.getVarnuc()!=null) {
							
							// if not merged use j, if merged but not in mapMergedIdx2SnpIdx dont add, if merged but not in mapMergedIdx2SnpIdx  add using mapMergedIdx2SnpIdx.get(j)
							if(mapMergedIdx2SnpIdx!=null && mapMergedIdx2SnpIdx.containsKey(iCols) && mapMergedIdx2SnpIdx.get(iCols)!=null) {
								j = mapMergedIdx2SnpIdx.get(iCols);

								//AppContext.debug( "indelstring.getVarnuc().length()=" + snpstr.getVarnuc().length() + " j=" + j + " iCols=" + iCols);
								char element = indelstring.getVarnuc().substring(j,j+1).charAt(0);
								if(element!='0' && element!='.' && element!=' ' && element!='*') {
									buff.append(element);
								
									Map<Integer,Character> mapPosidx2allele2 = indelstring.getMapPosIdx2Allele2();
									if(mapPosidx2allele2!=null && mapPosidx2allele2.get(j)!=null) {
										char element2 = mapPosidx2allele2.get(j);
										if(element2!='0' && element2!='.' && element2!=' ' && element2!='*' && element2!=element) 
											buff.append("/").append(element2);
									}
								}
							}
						}
					}
					
				} else {
					int j=iCols;
					// if not merged use j, if merged but not in mapMergedIdx2SnpIdx dont add, if merged but not in mapMergedIdx2SnpIdx  add using mapMergedIdx2SnpIdx.get(j)
					if(mapMergedIdx2SnpIdx!=null && mapMergedIdx2SnpIdx.containsKey(iCols) && mapMergedIdx2SnpIdx.get(iCols)!=null) {
						j = mapMergedIdx2SnpIdx.get(iCols);
					}
					
					if(mapMergedIdx2SnpIdx==null || mapMergedIdx2SnpIdx.get(iCols)!=null ) {
						//AppContext.debug( "snpstr.getVarnuc().length()=" + snpstr.getVarnuc().length() + " j=" + j + " iCols=" + iCols);
						char element = snpstr.getVarnuc().substring(j,j+1).charAt(0);
						if(element!='0' && element!='.' && element!=' ' && element!='*') {
							buff.append(element);
							Map<Integer,Character> mapPosidx2allele2 = snpstr.getMapPosIdx2Allele2();
							if(mapPosidx2allele2!=null && mapPosidx2allele2.get(j)!=null) {
								char element2 = mapPosidx2allele2.get(j);
								if(element2!='0' && element2!='.' && element2!=' ' && element2!='*' && element2!=element) 
									buff.append("/").append(  element2 );
							}
						}
					}
					
				}
				allelesstr[iCols] = buff.toString();
			}
		return allelesstr;
	}
		
	
	public static BigDecimal bd100 =  BigDecimal.valueOf(100);
	public static final int INDELTYPE_INSERTION=1;
	public static final int INDELTYPE_DELETION=2;
	public static final int INDELTYPE_SNP=3;
	public static final int INDELTYPE_REFERENCE=4;
	public static final int INDELTYPE_SUBSTITUTION=5;
	
	
	public static final String INDEL_GAP= "&#151;";
	public static final String INDEL_REFCONSENSUS= "*";
	
public static String createGaps(int length) {
		StringBuffer buff = new StringBuffer();
		for(int i=0; i<length; i++) buff.append(INDEL_GAP);
		return buff.toString();
	}
	
public static String[] createVarietyStringAligned(SnpsStringAllvars snpstr, VariantStringData varstringdata) {
		
		int cols = varstringdata.getListPos().size();
		Map<Integer,BigDecimal> mapMergedIdx2Pos = varstringdata.getMapIdx2Pos();
		//Map<Integer,BigDecimal> mapIndelIdx2Pos  =varstringdata.getMapIdx2Pos();
		
		
		Map<BigDecimal, IndelsAllvarsPos>	mapIndelId2Indels=null;
		Map<Integer, Integer> mapMergedIdx2SnpIdx=null;
		if(varstringdata.hasIndel()) {
			//VariantIndelStringData varindelstringdata = (VariantIndelStringData)varstringdata;
			mapIndelId2Indels = varstringdata.getIndelstringdata().getMapIndelId2Indel();
			if(varstringdata.hasSnp()) {
				//VariantSnpsStringData varsnpstringdata = (VariantSnpsStringData)varstringdata;
				mapMergedIdx2SnpIdx = varstringdata.getSnpstringdata().getMapMergedIdx2SnpIdx();
			}
		}

		String allele1Indelnucs=null;
		String allele2Indelnucs=null;
		int allele1Gapcount=0;
		Integer allele2Gapcount=0;
		List<SnpsAllvarsPos> listpos = varstringdata.getListPos();
		
		String allelesstr[] = new String[cols];  
		for(int iCols=0; iCols<cols; iCols++) {
			//SnpsStringAllvars snpstr =  (SnpsStringAllvars)matrixModel.getCellAt(cellsdata, iCols);
			StringBuffer buff = new StringBuffer();
			
				if(snpstr instanceof IndelsStringAllvars) {
					
					int j=iCols;
					
					BigDecimal pos = null;
					if(mapMergedIdx2Pos!=null) pos = mapMergedIdx2Pos.get(j);
					
					if(pos!=null) {
						
						int insertOffset = pos.remainder(BigDecimal.ONE).multiply(bd100).intValue(); 
						if( varstringdata.getIndelstringdata().getSetPosGapRegion().contains(pos))
						{
							// insertion region
							if(allele1Indelnucs!=null && allele1Indelnucs.length()>=insertOffset) {
								buff.append( allele1Indelnucs.charAt(insertOffset-1));
							
								//AppContext.debug("i"+ allele1Indelnucs.charAt(insertOffset-1) + "(" + pos +")");
							}
							else {
								allele1Indelnucs=null;
								buff.append(INDEL_GAP);
								//AppContext.debug("i-"+  "(" + pos +")");
							}
							if(allele2Indelnucs!=null && allele2Indelnucs.length()>=insertOffset)
								buff.append("/").append( allele2Indelnucs.charAt(insertOffset-1));
							else {
								allele2Indelnucs=null;
							}
						}
						else if(varstringdata.getIndelstringdata().getSetPosDeletionRegion().contains(pos.longValue()))
						{
							// deletion region
							if(allele1Gapcount>0) {
								buff.append(INDEL_GAP);
								allele1Gapcount--;
								
								//AppContext.debug("d-"+  "(" + pos +")");
								
							}// else buff.append(INDEL_REFCONSENSUS);
							 else {
								 buff.append(listpos.get(j).getRefnuc());
								 //AppContext.debug("d" + listpos.get(j).getRefnuc() + "(" + pos +")");
							 }
							
							if(allele2Gapcount!=null) {
								if(allele2Gapcount>0) {
									buff.append("/").append(INDEL_GAP);
									allele2Gapcount--;
								}
								if(allele2Gapcount==0) allele2Gapcount=null;
							}
						}
						
						/*
						int insertOffset = pos.remainder(BigDecimal.ONE).multiply(bd100).intValue(); 
						if( insertOffset > 0 )
						{
							// insertion region
							if(allele1Indelnucs!=null && allele1Indelnucs.length()>=insertOffset)
								buff.append( allele1Indelnucs.charAt(insertOffset-1));
							else {
								allele1Indelnucs=null;
								buff.append(INDEL_GAP);
							}
							if(allele2Indelnucs!=null && allele2Indelnucs.length()>=insertOffset)
								buff.append("/").append( allele2Indelnucs.charAt(insertOffset-1));
							else {
								allele2Indelnucs=null;
							}
						}
						else if(listpos.get(iCols).getRefnuc().equals(INDEL_REFCONSENSUS))
						{
							// deletion region
							if(allele1Gapcount>0) {
								buff.append(INDEL_GAP);
								allele1Gapcount--;
							} else buff.append(INDEL_REFCONSENSUS);
							
							if(allele2Gapcount!=null) {
								if(allele2Gapcount>0) {
									buff.append("/").append(INDEL_GAP);
									allele2Gapcount--;
								}
								if(allele2Gapcount==0) allele2Gapcount=null;
							}
						}
						*/
						else {
							
							// snp/insertion/deletion point
					
								IndelsStringAllvars indelstring=(IndelsStringAllvars)snpstr;
								
								BigDecimal alleleid = indelstring.getAllele1( pos );
								IndelsAllvarsPos indelpos = mapIndelId2Indels.get(alleleid);
								
								//if(indelpos==null) throw new RuntimeException("cant find alleleid=" + alleleid);
								if(alleleid!=null && indelpos!=null) {
									
									allele1Indelnucs = null;
									allele1Gapcount = 0;
									int indeltype = IndelStringService.getIndelType(indelpos);
									switch (indeltype ) {
										case INDELTYPE_INSERTION:
											allele1Indelnucs = indelpos.getInsString()  ;
											
											buff.append(varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos));
											
											//AppContext.debug("inserting " + allele1Indelnucs +" at " + pos);
											
											//buff.append(varstringdata.getIndelstringdata().getMapIndelIdx2Refnuc().get(j));
											break;
										case INDELTYPE_SNP: 
											//AppContext.debug("snp  " +  indelpos.getInsString() +" at " + pos);
											buff.append(indelpos.getInsString()); break;
										case INDELTYPE_DELETION:
											allele1Gapcount = indelpos.getDellength();
											//buff.append(varstringdata.getIndelstringdata().getMapIndelIdx2Refnuc().get(j));
											buff.append(varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos));
											
											//AppContext.debug("deleting  " + allele1Gapcount +" at " + pos);
											
											break;
										case INDELTYPE_REFERENCE: 
											//buff.append(varstringdata.getIndelstringdata().getMapIndelIdx2Refnuc().get(j));
											buff.append(varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos));
											//AppContext.debug("ref  " +  varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos) +" at " + pos);
											break;
									
											/*
										case INDELTYPE_INSERTION:
											allele1Indelnucs = indelpos.getInsString()  ;
											buff.append(INDEL_REFCONSENSUS);
											break;
										case INDELTYPE_SNP: 
											buff.append(indelpos.getInsString()); break;
										case INDELTYPE_DELETION:
											allele1Gapcount = indelpos.getDellength();
											buff.append(INDEL_REFCONSENSUS);
											break;
										case INDELTYPE_REFERENCE: 
											buff.append(INDEL_REFCONSENSUS);
											break;
											*/
									}
									
									allele2Gapcount = null;
									BigDecimal alleleid2 = indelstring.getAllele2( pos );
									indelpos = mapIndelId2Indels.get(alleleid2);
									
									if(alleleid2!=null && !alleleid2.equals(alleleid)) {
										alleleid=alleleid2;
										indelpos = mapIndelId2Indels.get(alleleid);
										if(indelpos!=null) {
											
											indeltype = IndelStringService.getIndelType(indelpos);
											switch (indeltype ) {
												case INDELTYPE_INSERTION:
													allele2Indelnucs = indelpos.getInsString()  ; 
													//buff.append('/').append(INDEL_REFCONSENSUS);
													break;
												case INDELTYPE_SNP: 
													buff.append('/').append(indelpos.getInsString()); break;
												case INDELTYPE_DELETION: 
													allele2Gapcount = indelpos.getDellength();
													//buff.append('/').append(INDEL_REFCONSENSUS);
													break;
												case INDELTYPE_REFERENCE: 
													//buff.append('/').append(varstringdata.getIndelstringdata().getMapIndelIdx2Refnuc().get(j));
													buff.append(varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos));
													break;
											}
											
										}								
									}
								}
								
								if(indelstring.getVarnuc()!=null) {
									
									// if not merged use j, if merged but not in mapMergedIdx2SnpIdx dont add, if merged but not in mapMergedIdx2SnpIdx  add using mapMergedIdx2SnpIdx.get(j)
									if(mapMergedIdx2SnpIdx!=null && mapMergedIdx2SnpIdx.containsKey(iCols) && mapMergedIdx2SnpIdx.get(iCols)!=null) {
										Integer objj = mapMergedIdx2SnpIdx.get(iCols);
		
										
										if(objj!=null) {
											j=objj;
											//AppContext.debug( "indelstring.getVarnuc().length()=" + snpstr.getVarnuc().length() + " j=" + j + " iCols=" + iCols);
											char element = indelstring.getVarnuc().substring(j,j+1).charAt(0);
											if(element!='0' && element!='.' && element!=' ' && element!='*') {
												buff.append(element);
											
												Map<Integer,Character> mapPosidx2allele2 = indelstring.getMapPosIdx2Allele2();
												if(mapPosidx2allele2!=null && mapPosidx2allele2.get(j)!=null) {
													char element2 = mapPosidx2allele2.get(j);
													if(element2!='0' && element2!='.' && element2!=' ' && element2!='*' && element2!=element) 
														buff.append("/").append(element2);
												}
											}
										}
										else {
											buff.append(listpos.get(iCols).getRefnuc());
										}
									} 
								}
						}
					}
					
				} else {
					int j=iCols;
					
					BigDecimal pos = null;
					if(mapMergedIdx2Pos!=null) pos = mapMergedIdx2Pos.get(j);
					if(pos!=null) {
						int insertOffset = pos.remainder(BigDecimal.ONE).multiply(bd100).intValue(); 
						if( insertOffset > 0 )
						{
							buff.append(INDEL_GAP);
						} else if(listpos.get(iCols).getRefnuc().equals(INDEL_REFCONSENSUS))	{
							buff.append(INDEL_REFCONSENSUS);
						}
						else {
							// if not merged use j, if merged but not in mapMergedIdx2SnpIdx dont add, if merged but not in mapMergedIdx2SnpIdx  add using mapMergedIdx2SnpIdx.get(j)
							if(mapMergedIdx2SnpIdx!=null && mapMergedIdx2SnpIdx.containsKey(iCols) && mapMergedIdx2SnpIdx.get(iCols)!=null) {
								j = mapMergedIdx2SnpIdx.get(iCols);
							}
							
							if(mapMergedIdx2SnpIdx==null || mapMergedIdx2SnpIdx.get(iCols)!=null ) {
								//AppContext.debug( "snpstr.getVarnuc().length()=" + snpstr.getVarnuc().length() + " j=" + j + " iCols=" + iCols);
								char element = snpstr.getVarnuc().substring(j,j+1).charAt(0);
								if(element!='0' && element!='.' && element!=' ' && element!='*') {
									buff.append(element);
									Map<Integer,Character> mapPosidx2allele2 = snpstr.getMapPosIdx2Allele2();
									if(mapPosidx2allele2!=null && mapPosidx2allele2.get(j)!=null) {
										char element2 = mapPosidx2allele2.get(j);
										if(element2!='0' && element2!='.' && element2!=' ' && element2!='*' && element2!=element) 
											buff.append("/").append(  element2 );
									}
								}
							}
						}
					} else {

						if(mapMergedIdx2SnpIdx!=null && mapMergedIdx2SnpIdx.containsKey(iCols) && mapMergedIdx2SnpIdx.get(iCols)!=null) {
							j = mapMergedIdx2SnpIdx.get(iCols);
						}
						
						if(mapMergedIdx2SnpIdx==null || mapMergedIdx2SnpIdx.get(iCols)!=null ) {
							//AppContext.debug( "snpstr.getVarnuc().length()=" + snpstr.getVarnuc().length() + " j=" + j + " iCols=" + iCols);
							char element = snpstr.getVarnuc().substring(j,j+1).charAt(0);
							if(element!='0' && element!='.' && element!=' ' && element!='*') {
								buff.append(element);
								Map<Integer,Character> mapPosidx2allele2 = snpstr.getMapPosIdx2Allele2();
								if(mapPosidx2allele2!=null && mapPosidx2allele2.get(j)!=null) {
									char element2 = mapPosidx2allele2.get(j);
									if(element2!='0' && element2!='.' && element2!=' ' && element2!='*' && element2!=element) 
										buff.append("/").append(  element2 );
								}
							}
						}
						
					}
					
				}
				allelesstr[iCols] = buff.toString();
			}
		return allelesstr;
	}

	public static String getIndelAlleleString(IndelsAllvarsPos indelpos) {

		if(indelpos==null) return "";
		if(indelpos.getDellength()==0) {
			if(indelpos.getInsString()==null || indelpos.getInsString().trim().isEmpty()) 
				return "ref";
			else return indelpos.getInsString();
		} else {
			if(indelpos.getInsString()!=null && !indelpos.getInsString().trim().isEmpty() ) {
				if(indelpos.getInsString().trim().length()==1)
					return "snp -> " + indelpos.getInsString();
				else return "del " + indelpos.getDellength() + " -> " + indelpos.getInsString();
			}
			else return "del " + indelpos.getDellength();
		}
	}
	
	public static String getAlignedIndelAlleleString(IndelsAllvarsPos indelpos) {

		if(indelpos==null) return "";
		
		int insIdx = indelpos.getPos().remainder(BigDecimal.ONE).multiply(bdHundred).intValue();
		
		if(insIdx>0) {
			return indelpos.getInsString().substring(insIdx-1,insIdx);
		} else {
			if(indelpos.getDellength()==0) {
				if(indelpos.getInsString()==null || indelpos.getInsString().trim().isEmpty()) 
					return "ref";
				else return indelpos.getInsString();
			} else {
				if(indelpos.getInsString()!=null && !indelpos.getInsString().trim().isEmpty() ) {
					if(indelpos.getInsString().trim().length()==1)
						return "snp -> " + indelpos.getInsString();
					else return "del " + indelpos.getDellength() + " -> " + indelpos.getInsString();
				}
				else return "del " + indelpos.getDellength();
			}
		}
	}

	/*
	public static String getIndelType(String allele) {
		if(allele.startsWith("ref")) return "reference";
		else if(allele.startsWith("snp")) return "snp";
		else if(allele.startsWith("del")) {
			if(allele.contains("->"))
				return "substitution";
			else return "deletion";
		}
		else return "insertion";
	}
	*/
	
	public static int getIndelType(String allele) {
		if(allele.startsWith("ref")) return INDELTYPE_REFERENCE;
		else if(allele.startsWith("snp")) return INDELTYPE_SNP;
		else if(allele.startsWith("del")) {
			if(allele.contains("->"))
				return INDELTYPE_SUBSTITUTION;
			else return INDELTYPE_DELETION;
		}
		else return INDELTYPE_INSERTION;
	}

	public static int getIndelType(IndelsAllvarsPos indelpos) {
		if(indelpos.getDellength()==0) {
			if(indelpos.getInsString()==null || indelpos.getInsString().isEmpty()) return INDELTYPE_REFERENCE;
			if(indelpos.getInsString().length()>0) return  INDELTYPE_INSERTION;
		} 
		if(indelpos.getDellength()==1) {
			if(indelpos.getInsString()==null || indelpos.getInsString().isEmpty()) return INDELTYPE_DELETION;
			if(indelpos.getInsString()!=null && indelpos.getInsString().length()==1) return INDELTYPE_SNP;
			if(indelpos.getInsString()!=null && indelpos.getInsString().length()>1) return INDELTYPE_SUBSTITUTION;
		}
		if(indelpos.getDellength()>1) {
			if(indelpos.getInsString()==null || indelpos.getInsString().isEmpty()) return INDELTYPE_DELETION;
			if(indelpos.getInsString()!=null && indelpos.getInsString().length()>0) return INDELTYPE_SUBSTITUTION;
		}
		return -1;
	}
	

	public static double countVarpairMismatchIndel(IndelsAllvarsPos var1char, IndelsAllvarsPos var2char , boolean var1isref, IndelsAllvarsPos var1allele2, IndelsAllvarsPos var2allele2) {
			
		  
			double misCount = 0;
			if(var1char==null && var2char==null) {
			}
			else if(var1char==null && var2char!=null) {
			if(var2char.getInsString()!=null) misCount+=var2char.getInsString().length();
			 misCount+= var2char.getDellength();
			 
			 if(var2allele2!=null) {
					if(var2allele2.getInsString()!=null) misCount+=var2allele2.getInsString().length();
					 misCount+= var2allele2.getDellength();
			 }
			} else if(var2char==null && var1char!=null) {
			if(var1char.getInsString()!=null) misCount+=var1char.getInsString().length();
			 misCount+= var1char.getDellength();
			 
			 if(var1allele2!=null) {
					if(var1allele2.getInsString()!=null) misCount+=var1allele2.getInsString().length();
					 misCount+= var1allele2.getDellength();
			 }
				
			} else if(var1isref) {
			if(var2char.getInsString()!=null) misCount+=var2char.getInsString().length();
			 misCount+= var2char.getDellength();
			 
			 if(var2allele2!=null) {
					if(var2allele2.getInsString()!=null) misCount+=var2allele2.getInsString().length();
					 misCount+= var2allele2.getDellength();
			 }
				
			
		} else {
	
			// allele2 not yet considered in for pairwise comparison
			
			
			if(var1char.getInsString()!=null && !var1char.getInsString().isEmpty()) 
			{
				if(var2char.getInsString()!=null && !var2char.getInsString().isEmpty()) {
					// both are ins 
					if(var2char.getInsString().equals(var1char.getInsString())) {
						// equal
					} else if(var2char.getInsString().startsWith( var1char.getInsString() ) || var1char.getInsString().startsWith( var2char.getInsString() )  ) {
						// substring
						double deflength =  var2char.getInsString().length() -  var1char.getInsString().length();
						
						if(deflength>0)
							misCount +=  deflength;
						else 
							misCount +=  -deflength;
					} else {
						misCount += var2char.getInsString().length() +  var1char.getInsString().length();
					}
				} else {
					// var2 is del
					misCount += var1char.getInsString().length();
					misCount += var2char.getDellength();
				} 
			} else {

				// var1 is del
				if(var2char.getInsString()!=null && !var2char.getInsString().isEmpty()) {
					// var2 is ins
					misCount += var2char.getInsString().length() +  var1char.getDellength();
				} else {
					// var2 is del
					double defdel = var1char.getDellength() -   var2char.getDellength();
					if(defdel>0)
						misCount += defdel; 
					else 
						misCount += - defdel;
				} 
			}
		}
		
		return misCount;
	}

}
