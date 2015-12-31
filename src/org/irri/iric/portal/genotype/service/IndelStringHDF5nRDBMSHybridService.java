package org.irri.iric.portal.genotype.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.IndelStringDAO;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.dao.MultipleReferenceConverterDAO;
import org.irri.iric.portal.dao.SequenceDAO;
import org.irri.iric.portal.domain.IndelsAllvarsPos;
import org.irri.iric.portal.domain.IndelsAllvarsPosAlleleImpl;
import org.irri.iric.portal.domain.IndelsStringAllvars;
import org.irri.iric.portal.domain.Position;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.genotype.GenotypeQueryParams;
import org.irri.iric.portal.genotype.VariantIndelStringData;
import org.irri.iric.portal.genotype.VariantStringData;
import org.irri.iric.portal.genotype.VariantStringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("IndelService")
public class IndelStringHDF5nRDBMSHybridService implements VariantStringService {

	@Autowired
	private ListItemsDAO listitemsdao;
	
//	@Autowired
//	@Qualifier("IndelsAllvarsPosDAO")
//	private IndelsAllvarsPosDAO indelsAllvarsPosDAO;
//	
//	@Autowired
//	//@Qualifier("IndelCallDAO")
//	//@Qualifier("IndelAllvarsDAOOracle")
//	@Qualifier("IndelAllvarsDAOHDF5")
//	private IndelsAllvarsDAO indelsAllvarsDAO;
	
	//@Autowired
	//@Qualifier("IndelStringNormalizedDAO")
	//private IndelStringNormalizedDAOImpl  indelstringdao;
	@Autowired
	@Qualifier("IndelStringNormalizedDAO")
	//private IndelStringNormalizedDAOImpl  indelstringdao;
	//private IndelStringNormalizedDAOImpl  indelstringdao;
	private IndelStringDAO  indelstringdao;

	@Autowired
	private SequenceDAO indelSequenceDAO;
	
	@Autowired
	@Qualifier("MultipleReferenceConverterDAO")
	//@Qualifier("MultipleReferenceConverterPrecompDAO")
	private MultipleReferenceConverterDAO multiplerefconvertdao;
	
	private static BigDecimal bdHundred = BigDecimal.valueOf(100);
	
	
	private int getOrganismId(String org) {
		listitemsdao=(ListItemsDAO)AppContext.checkBean(listitemsdao, "ListItems");
		return listitemsdao.getOrganismByName(org).getOrganismId().intValue();
	}
	
	@Override
	public VariantStringData getVariantString(GenotypeQueryParams params)
			throws Exception {
		// TODO Auto-generated method stub
		
		return getIndels(params, params.getColVarIds(), params.getsChr(),  params.getlStart(), params.getlEnd(),  params.getPoslist(), params.isbMismatchonly(),params.getColLoci(), 
				getOrganismId(params.getOrganism()));
		 
	}
	

	@Override
	public List checkSNPsInChromosome(String chr, Collection posset,
			BigDecimal type) {
		// TODO Auto-generated method stub
		throw new RuntimeException("checkSNPsInChromosome not applicable for indels");
	}

	/**
	 * get indels for the params
	 * @param params
	 * @param limitVarIds
	 * @param chr
	 * @param start
	 * @param end
	 * @param setPositions
	 * @param mismatchOnly
	 * @param colLoci
	 * @param organismId
	 * @return
	 * @throws Exception
	 */
	private VariantStringData getIndels(GenotypeQueryParams params, Collection limitVarIds, String chr, Long start, Long end, Collection setPositions, boolean mismatchOnly, Collection colLoci, int organismId) throws Exception { //, boolean exactMismatch, int firstRow, int maxRows) {
	
		
		if(!params.getOrganism().equals( AppContext.getDefaultOrganism() )) {
			// not nipponbare coordinate. convert coordinates
			
			throw new RuntimeException("Indels for other reference genomes not yet available");
			
//			multiplerefconvertdao = (MultipleReferenceConverterDAO)AppContext.checkBean(multiplerefconvertdao, "MultipleReferenceConverterDAO");
//			
//			MultiReferenceLocus locusQueried = new MultiReferenceLocusImpl(params.getOrganism(), params.getsChr(), params.getlStart().intValue(), params.getlEnd().intValue(), 1);
//			
//			MultiReferenceLocus locusNipponbare = multiplerefconvertdao.convertLocus( locusQueried ,  AppContext.getDefaultOrganism(),  null); 
//			MultiReferenceLocus origMultiReferenceLocus =  params.setNewPosition(locusNipponbare);
//			
//			
//			//VariantStringData variantindelsdataNPB =  _getIndelsNPB( limitVarIds,  chr,  start,  end,  setPositions,  mismatchOnly);
//			
//			if(setPositions!=null) throw new Exception("Convert positionlits to " + locusNipponbare + " coordinates");
//			VariantStringData variantindelsdataNPB =  _getIndelsNPB( limitVarIds,  locusNipponbare.getContig(),  locusNipponbare.getFmin().longValue(),  locusNipponbare.getFmin().longValue(),  setPositions,  mismatchOnly, colLoci, organismId);
//			
//			String toContig = null;
//			if(params.isLimitToQueryContig()) {
//				toContig = locusQueried.getContig();
//			}
//			
//			
//			variantindelsdataNPB.setMessage( variantindelsdataNPB.getMessage() + "\nIndel Query " + locusQueried + " aligned with " + locusNipponbare);
//			params.setNewPosition(origMultiReferenceLocus);
//			
//			
//			/*
//			String subseq = "";
//			try {
//				//subseq = indelSequenceDAO.getSubSequence(BigDecimal.valueOf(chr+2), indelstringdao.getListPos().get(0).getPos().longValue() , end+100);
//				subseq = indelSequenceDAO.getSubSequence(chr, indelstringdao.getListPos().get(0).getPos().longValue() , end+100, organismId);
//				AppContext.debug( "indelsequence length=" + subseq.length() );
//			} catch(Exception ex) {
//				ex.printStackTrace();
//			}
//			*/
//			
//			//VariantIndelStringData indelstringdata =  (VariantIndelStringData)variantindelsdataNPB;
//			return multiplerefconvertdao.convertReferencePositions(variantindelsdataNPB, locusNipponbare, locusQueried, toContig, false);
//			//return multiplerefconvertdao.convertReferencePositions(indelstringdata.getAlignedIndels(), locusNipponbare, locusQueried, toContig);
//			//return variantstringdataNPB;
			
		} else {
			return _getIndelsNPB(  limitVarIds,  chr,  start,  end,  setPositions,  mismatchOnly, colLoci);
		}
		
	}
	
	
	
	/**
	 * Get indel for Nipponbare reference
	 * @param limitVarIds
	 * @param chr
	 * @param start
	 * @param end
	 * @param setPositions
	 * @param mismatchOnly
	 * @param colLoci
	 * @param organismId
	 * @return
	 */
	private VariantStringData _getIndelsNPB(Collection limitVarIds, String chr, Long start, Long end, Collection setPositions, boolean mismatchOnly, Collection colLoci){
			
		
			int organismId =  getOrganismId(AppContext.getDefaultOrganism());
		
			//indelsAllvarsPosDAO = (IndelsAllvarsPosDAO)AppContext.checkBean(indelsAllvarsPosDAO, "IndelsAllvarsPosDAO");
			//indelsAllvarsDAO = (IndelsAllvarsDAO)AppContext.checkBean(  indelsAllvarsDAO , "IndelAllvarsDAO");
			//indelstringdao=(IndelStringNormalizedDAOImpl)AppContext.checkBean(  indelstringdao , "IndelStringNormalizedDAO");
			//IndelStringNormalizedDAOImpl indelstringnormalizeddao=indelstringdao;

			indelSequenceDAO = (SequenceDAO)AppContext.checkBean(  indelSequenceDAO , "SequenceDAO");
			indelstringdao=(IndelStringDAO)AppContext.checkBean(  indelstringdao , "IndelStringNormalizedDAO");
			AppContext.debug("indelstringdao=" +indelstringdao.getClass());
			IndelStringDAO indelstringnormalizeddao = indelstringdao;
					
			// read IndelString for varieties
			String msg="";
			Map<BigDecimal, IndelsStringAllvars> mapVar2Indelstring=null;

			if( setPositions!=null && !setPositions.isEmpty()) {
				if(limitVarIds!=null && !limitVarIds.isEmpty() ) {
					AppContext.resetTimer("using readIndelString3 start");
					mapVar2Indelstring= indelstringdao.readSNPString((Set)limitVarIds, chr, setPositions);
					AppContext.resetTimer("using readIndelString3 end");
				} else {
					AppContext.resetTimer("using readIndelString4 start");
					mapVar2Indelstring=indelstringdao.readSNPString(chr, setPositions);
					AppContext.resetTimer("using readIndelString4 end");
				}
			}
			else if( colLoci!=null && !colLoci.isEmpty()) {
				if(limitVarIds!=null && !limitVarIds.isEmpty() ) {
					AppContext.resetTimer("using readIndelString5 start");
					mapVar2Indelstring=indelstringdao.readSNPString((Set)limitVarIds, chr, colLoci);
					AppContext.resetTimer("using readIndelString5 end");
				} else {
					AppContext.resetTimer("using readIndelString6 start");
					mapVar2Indelstring=indelstringdao.readSNPString(chr, colLoci);
					AppContext.resetTimer("using readIndelString6 end");
				}
			}
			else {
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
			}

			if(mapVar2Indelstring.size()==0) return new VariantIndelStringData();
			

			// get reference sequence of the region
			String subseq = "";
			try {
				subseq = indelSequenceDAO.getSubSequence(chr,  indelstringnormalizeddao.getListPos().get(0).getPosition().longValue() , end+10000, organismId);
				AppContext.debug( "indelsequence length=" + subseq.length() );
			} catch(Exception ex) {
				ex.printStackTrace();
			}
				
			
			// get distinct alleles for each position
			Map mapIndelId2Indel = indelstringnormalizeddao.getMapAlleleId2Indel();
			Map<Position, Set<String>> mapPos2Allele=new HashMap();
			Iterator<IndelsAllvarsPos> itIndels =  mapIndelId2Indel.values().iterator();
			while(itIndels.hasNext()) {
				IndelsAllvarsPos indelpos = itIndels.next(); 
				Set setalleles = mapPos2Allele.get(indelpos.getPosition());
				if(setalleles==null) {
					setalleles = new TreeSet();
					mapPos2Allele.put(indelpos , setalleles);
				}
				setalleles.add( getIndelAlleleString( indelpos) );
			}
			
			VariantIndelStringData indelstrdata= new VariantIndelStringData(indelstringnormalizeddao.getMapVariety2Mismatch(), indelstringnormalizeddao.getMapVariety2Order(), 
					indelstringnormalizeddao.getListPos(),  indelstringnormalizeddao.getListResult(),  mapPos2Allele );
			indelstrdata.setMapIndelId2Indel(mapIndelId2Indel);
			indelstrdata.setSequence(subseq);
			indelstrdata.setMessage(msg);
			
			 return indelstrdata;
	}
	
	
	/**
	 * Create descriptive format indel string 
	*/
	
	public static String[] createVarietyString(SnpsStringAllvars snpstr, VariantStringData varstringdata) {
		
		//AppContext.debug("HDF5 createVarietyString: " +  snpstr.getClass() + " " + snpstr);
		
		int cols = varstringdata.getListPos().size();

		Map<BigDecimal, IndelsAllvarsPos>	mapIndelId2Indels=null;
		//Map<Integer, Integer> mapMergedIdx2SnpIdx=null;
		if(varstringdata.hasIndel()) {
			mapIndelId2Indels = varstringdata.getIndelstringdata().getMapIndelId2Indel();
		}
		
		Map<Position,Character> mapPos2allele2 = snpstr.getMapPos2Allele2();

		// create allele for each position
		
		String allelesstr[] = new String[cols];  
		for(int iCols=0; iCols<cols; iCols++) {

			StringBuffer buff = new StringBuffer();
			Position pos = varstringdata.convertMergedIdx2Pos(iCols);
			
			if(pos.getPosition().intValue()==38382527 &&  snpstr.getVar().intValue()==2292)
				AppContext.debug( snpstr.toString() );
			
				if(snpstr instanceof IndelsStringAllvars) {
					
					Integer j=iCols;
					if(pos!=null) {
					
						IndelsStringAllvars indelstring=(IndelsStringAllvars)snpstr;
			
						if(indelstring.getVarnuc()!=null) {
							
							// has SNPs
							
							// if not merged use j, if merged but not in mapMergedIdx2SnpIdx dont add, if merged but not in mapMergedIdx2SnpIdx  add using mapMergedIdx2SnpIdx.get(j)
							j = varstringdata.convertMergedIdx2SnpIdx(iCols);
							if(j!=null) {
								
								char element = indelstring.getVarnuc().substring(j,j+1).charAt(0);
								if(element!='0' && element!='.' && element!=' ' && element!='*' && element!='?') {

									if(mapPos2allele2!=null && mapPos2allele2.get(pos)!=null) {
										char element2 = mapPos2allele2.get(pos);
										if(element2!='0' && element2!='.' && element2!=' ' && element2!='*' && element2!=element) {
											SnpsAllvarsPos  snppos = varstringdata.getListPos().get(iCols);
											if(snppos.getRefnuc().charAt(0)==element) 
												buff.append(element) .append("/").append(element2);
											else if(snppos.getRefnuc().charAt(0)==element2)
												buff.append(element2) .append("/").append(element);
											else {
												if(element2>element) 
													buff.append(element) .append("/").append(element2);
												 else 
													buff.append(element2) .append("/").append(element);
											}
										} else 
											buff.append(element);
									} else
										buff.append(element);
								}
							}
						}  
						
						BigDecimal alleleid = null; //indelstring.getAllele1( pos );
						IndelsAllvarsPos indelpos = null; //apIndelId2Indels.get(alleleid);

						try{
							alleleid = indelstring.getAllele1( pos );
							indelpos = mapIndelId2Indels.get(alleleid);
						}catch (Exception ex) {
							//AppContext.debug("pos=" + pos + " alleleid=" + alleleid + " indelpos=" + indelpos );
						}
						
						if(alleleid!=null && indelpos!=null) {
							buff.append( IndelStringHDF5nRDBMSHybridService.getIndelAlleleString(indelpos) );
							BigDecimal alleleid2 = indelstring.getAllele2( pos );
							indelpos = mapIndelId2Indels.get(alleleid2);
							
							if(alleleid2!=null && !alleleid2.equals(alleleid)) {
								alleleid=alleleid2;
								indelpos = mapIndelId2Indels.get(alleleid);
								if(indelpos!=null) {
									buff.append("/").append(  IndelStringHDF5nRDBMSHybridService.getIndelAlleleString(indelpos) );
								}								
							}
						}
								
							
					}
					
				} else {
					
					// SNPs only
					int j=iCols;
					// if not merged use j, if merged but not in mapMergedIdx2SnpIdx dont add, if merged but not in mapMergedIdx2SnpIdx  add using mapMergedIdx2SnpIdx.get(j)
					
					j = varstringdata.convertMergedIdx2SnpIdx(iCols);
					
					char element = snpstr.getVarnuc().substring(j,j+1).charAt(0);
					if(element!='0' && element!='.' && element!=' ' && element!='*' && element!='?') {

						if(mapPos2allele2!=null && mapPos2allele2.get(pos)!=null) {								
							char element2 = mapPos2allele2.get(pos);
							if(element2!='0' && element2!='.' && element2!=' ' && element2!='*' && element2!=element && element2!='?') {
								
								SnpsAllvarsPos  snppos = varstringdata.getListPos().get(iCols);
								if(snppos.getRefnuc().charAt(0)==element) 
									buff.append(element) .append("/").append(element2);
								else if(snppos.getRefnuc().charAt(0)==element2)
									buff.append(element2) .append("/").append(element);
								else {
									if(element2>element) 
										buff.append(element) .append("/").append(element2);
									 else 
										buff.append(element2) .append("/").append(element);
								}
								
							} else 
								buff.append(element);
						} else
							buff.append(element);
					}
				}
				allelesstr[iCols] = buff.toString();
			}
		
		return allelesstr;
	}
		
	
	/**
	 * Create MSA format indel string
	 * @param snpstr
	 * @param varstringdata
	 * @return
	 */
	public static String[] createVarietyStringAligned(SnpsStringAllvars snpstr, VariantStringData varstringdata) {
	
		int cols = varstringdata.getListPos().size();
		Map<Integer,Position> mapMergedIdx2Pos = varstringdata.getMapIdx2Pos();
		
		Map<BigDecimal, IndelsAllvarsPos>	mapIndelId2Indels=null;
		if(varstringdata.hasIndel()) {
			mapIndelId2Indels = varstringdata.getIndelstringdata().getMapIndelId2Indel();
		}
		
		String allele1Indelnucs=null;
		String allele2Indelnucs=null;
		int allele1Gapcount=0;
		Integer allele2Gapcount=0;
		
		
		List<SnpsAllvarsPos> listpos = varstringdata.getListPos();
		
		Map<Position,Character> mapPos2allele2 = snpstr.getMapPos2Allele2();
		
		// create indel string for each position
		String allelesstr[] = new String[cols];  
		for(int iCols=0; iCols<cols; iCols++) {
			
			StringBuffer buffGap = new StringBuffer();
			StringBuffer buff = new StringBuffer();
			
			Position pos = mapMergedIdx2Pos.get(iCols);
			
				if(snpstr instanceof IndelsStringAllvars) {
					
					// InsdelString
					Integer j=iCols;
					if(pos!=null) {
						
						int insertOffset = pos.getPosition().remainder(BigDecimal.ONE).multiply(bd100).intValue(); 
						
						if(varstringdata==null) throw new RuntimeException("varstringdata");
						if( varstringdata.getIndelstringdata()==null) throw new RuntimeException(" varstringdata.getIndelstringdata()");
						if(varstringdata.getIndelstringdata().getSetPosGapRegion()==null) throw new RuntimeException("varstringdata.getIndelstringdata().getSetPosGapRegion()");
						if(pos==null) throw new RuntimeException("pos");
						
						if( varstringdata.getIndelstringdata().getSetPosGapRegion().contains(pos))
						{
							// insertion region
							String alleles1="";
							if(allele1Indelnucs!=null && allele1Indelnucs.length()>=insertOffset) {
								//buff.append( allele1Indelnucs.charAt(insertOffset-1));
								alleles1= String.valueOf( allele1Indelnucs.charAt(insertOffset-1) );
								//AppContext.debug("i"+ allele1Indelnucs.charAt(insertOffset-1) + "(" + pos +")");
							}
							else {
								allele1Indelnucs=null;
								//buff.append(INDEL_GAP);
								alleles1= INDEL_GAP;
								//AppContext.debug("i-"+  "(" + pos +")");
							}
							
							if(allele2Indelnucs!=null && allele2Indelnucs.length()>=insertOffset) {
								String alleles2= String.valueOf( allele2Indelnucs.charAt(insertOffset-1));
								//buff.append("/").append( allele2Indelnucs.charAt(insertOffset-1));
								if(alleles1.equals(alleles2)) {
									buffGap.append(alleles1);
								} else {
									SnpsAllvarsPos  snppos = varstringdata.getListPos().get(iCols);
									if(snppos.getRefnuc().equals(alleles1))
										buffGap.append(alleles1).append("/").append(alleles2);
									else if(snppos.getRefnuc().equals(alleles2))
										buffGap.append(alleles2).append("/").append(alleles1);
									else {
									if(alleles2.compareTo(alleles1)>0) 
										buffGap.append(alleles1).append("/").append(alleles2);
									else 
										buffGap.append(alleles2).append("/").append(alleles1);
									}
								}
							}
							else {
								allele2Indelnucs=null;
								buffGap.append(alleles1);
							}
						}
						else if(varstringdata.getIndelstringdata().getSetPosDeletionRegion().contains(pos))
						{
							// deletion region
							String alleles1="";
							if(allele1Gapcount>0) {
								//buff.append(INDEL_GAP);
								alleles1=INDEL_GAP;
								allele1Gapcount--;
								
								//AppContext.debug("d-"+  "(" + pos +")");
								
							}// else buff.append(INDEL_REFCONSENSUS);
							 else {
								 //buff.append(listpos.get(j).getRefnuc());
								 alleles1=listpos.get(j).getRefnuc();
								 //AppContext.debug("d" + listpos.get(j).getRefnuc() + "(" + pos +")");
							 }
							
							if(allele2Gapcount!=null) {
								
									
								if(allele2Gapcount>0) {
									//buff.append("/").append(INDEL_GAP);
									if(!alleles1.equals(INDEL_GAP)) {
										SnpsAllvarsPos  snppos = varstringdata.getListPos().get(iCols);
										if(snppos.getRefnuc().equals(alleles1) )
											buffGap.append(alleles1).append("/").append(INDEL_GAP);
										else if(snppos.getRefnuc().equals(INDEL_GAP) )
											buffGap.append(INDEL_GAP).append("/").append(alleles1);
										else {
											if(alleles1.compareTo(INDEL_GAP)>0) 
												buffGap.append(INDEL_GAP).append("/").append(alleles1);
											else 
												buffGap.append(alleles1).append("/").append(INDEL_GAP);
											}
										
									} else buffGap.append(alleles1);
									
									allele2Gapcount--;
								}
								else if(allele2Gapcount==0) {
									allele2Gapcount=null;
									buffGap.append(alleles1);
								}
								
							} else {
								buffGap.append(alleles1);
							}
						}
						
						
						if(true)  {
							
								IndelsStringAllvars indelstring=(IndelsStringAllvars)snpstr;
							
								StringBuffer buffSNP= new StringBuffer();

								String snpAllele1="";
								String snpAllele2="";
								String indelAllele1="";
								String indelAllele2="";
								
								char element='\0';
								char element2='\0';
								
								boolean hasSnp1Missing=false;
								String snpElements="";
								boolean hasIndel1Missing=true;
								String indelElements="";
								boolean hasSnp2Missing=false;
								boolean hasIndel2Missing=true;

								
								if(indelstring.getVarnuc()!=null) {
									
									// if not merged use j, if merged but not in mapMergedIdx2SnpIdx dont add, if merged but not in mapMergedIdx2SnpIdx  add using mapMergedIdx2SnpIdx.get(j)
									Integer objj = varstringdata.convertMergedIdx2SnpIdx(iCols);
		
										if(objj!=null) {
											j=objj;
											element = indelstring.getVarnuc().substring(j,j+1).charAt(0);
											if(element!='0' && element!='.' && element!=' ' && element!='*' && element!='?') {
												snpAllele1 = String.valueOf( element ); 
											} else {
												hasSnp1Missing=true;
											}
											snpElements+="1=" + element +";";

											if(pos.getPosition().intValue()==38382527 &&  snpstr.getVar().intValue()==2292)
												AppContext.debug("allele1= " + element + "  mapPos2allele2=" + mapPos2allele2);

											if(mapPos2allele2!=null && mapPos2allele2.get(pos)!=null) {	
												
												element2 = mapPos2allele2.get(pos);		
												if(element2!='0' && element2!='.' && element2!=' ' && element2!='*' &&  element2!='?') {
													snpAllele2=String.valueOf(element2);
												} else {
													hasSnp2Missing=true;
												}
												snpElements+="2=" + element2 +";";
											}
											
											if(!snpAllele1.isEmpty() && !snpAllele2.isEmpty() && !snpAllele1.equals(snpAllele2) ) {
												
												
												SnpsAllvarsPos  snppos = varstringdata.getListPos().get(iCols);
												if(snppos.getRefnuc().equals(snpAllele1)) 
													buffSNP.append(snpAllele1) .append("/").append(snpAllele2);
												else if(snppos.getRefnuc().equals(snpAllele2))
													buffSNP.append(snpAllele2) .append("/").append(snpAllele1);
												else {
													if(snpAllele1.compareTo(snpAllele2)>0) 
														buffSNP.append(snpAllele2) .append("/").append(snpAllele1);
													 else 
														 buffSNP.append(snpAllele1) .append("/").append(snpAllele2);
												}
												
											} else if(!snpAllele1.isEmpty()) {
												buffSNP.append(snpAllele1);
											} else if(!snpAllele2.isEmpty()) {
												buffSNP.append(snpAllele2);
											}
										}
										
								}
							
								StringBuffer buffIndel=new StringBuffer();
								if(buffSNP.length()>=0) {
											
										// snp/insertion/deletion anchor point
										hasIndel1Missing=false;
										hasIndel2Missing=false;
										BigDecimal alleleid = null;  //indelstring.getAllele1( pos );
										IndelsAllvarsPos indelpos =  null; //mapIndelId2Indels.get(alleleid);
		
										try {
											alleleid = indelstring.getAllele1( pos );
											indelpos = mapIndelId2Indels.get(alleleid);
										} catch( Exception ex) {
											//ex.printStackTrace();
										}
										
										String alleles1="";
										String alleles1Aligned="";
										if(alleleid!=null && indelpos!=null) {
											
											allele1Indelnucs = null;
											allele1Gapcount = 0;
											int indeltype = IndelStringHDF5nRDBMSHybridService.getIndelType(indelpos);
											switch (indeltype ) {
												case INDELTYPE_INSERTION:
													allele1Indelnucs = indelpos.getInsString()  ;
													alleles1 = varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos);

													if(alleles1==null) {
														AppContext.debug("INDELTYPE_INSERTION cant find " + pos + " IN " + varstringdata.getIndelstringdata().getMapIndelpos2Refnuc());
														throw new RuntimeException("cant find " + pos ); 
													}
													break;
												case INDELTYPE_SNP: 
													alleles1=indelpos.getInsString(); 
													alleles1Aligned=alleles1;
													break;
													
												case INDELTYPE_DELETION:
													allele1Gapcount = Integer.valueOf(indelpos.getInsString());
													alleles1 = varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos);

													if(alleles1==null) {
														AppContext.debug("INDELTYPE_DELETION cant find " + pos + " IN " + varstringdata.getIndelstringdata().getMapIndelpos2Refnuc());
														throw new RuntimeException("cant find " + pos ); 
													}
													break;
												case INDELTYPE_EXTENDDELETION:
													allele1Gapcount = Integer.valueOf(indelpos.getInsString().replace("-",""));
													alleles1 = INDEL_GAP;
													alleles1Aligned=alleles1;
													break;
												case INDELTYPE_REFERENCE: 
													alleles1=varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos);
													
													if(alleles1==null) {
														AppContext.debug("INDELTYPE_REFERENCE cant find " + pos + " IN " + varstringdata.getIndelstringdata().getMapIndelpos2Refnuc());
														throw new RuntimeException("cant find " + pos ); 
													}
													break;
												case INDELTYPE_MISSING: 
													alleles1="";
													hasIndel1Missing=true;
													break;
											}  

											indelElements+="1=" + indelpos.getInsString() +";";
											if(alleles1==null) throw new RuntimeException("alleles1=null,  indeltype="  + indeltype + " indelpos.getInsString()=" + indelpos.getInsString());
											indelAllele1=alleles1;
											allele2Gapcount = null;
											BigDecimal alleleid2 = indelstring.getAllele2( pos );
											indelpos = mapIndelId2Indels.get(alleleid2);
											
											if( alleleid2!=null && !alleleid2.equals(alleleid)) {
												alleleid=alleleid2;
												indelpos = mapIndelId2Indels.get(alleleid);
												if(indelpos!=null) {
													
													indeltype = IndelStringHDF5nRDBMSHybridService.getIndelType(indelpos);
													String alleles2="";
													switch (indeltype ) {
														case INDELTYPE_INSERTION:
															allele2Indelnucs = indelpos.getInsString()  ; 
															break;
														case INDELTYPE_SNP: 
															alleles2=indelpos.getInsString(); 
															if(alleles2.compareTo(alleles1Aligned)>0)
																buffIndel.append(alleles1Aligned).append("/").append(alleles2);
															else
																buffIndel.append(alleles2).append("/").append(alleles1Aligned);
															break;
														case INDELTYPE_DELETION: 
															allele2Gapcount = indelpos.getMaxDellength();
															break;
														case INDELTYPE_EXTENDDELETION: 
															allele2Gapcount = Integer.valueOf(indelpos.getInsString().replace("-",""));
															buffIndel.append(alleles1Aligned).append('/').append(INDEL_GAP);
															break;
														case INDELTYPE_REFERENCE: 
															break;
															
														case INDELTYPE_MISSING: 
															alleles2="";
															hasIndel2Missing=true;
															break;
													}
													indelElements+="2=" + indelpos.getInsString() +";";
													
													if(alleles2==null) throw new RuntimeException("alleles2=null,  indeltype="  + indeltype + " indelpos.getInsString()=" + indelpos.getInsString());
													indelAllele2=alleles2;
												}								
											} 
											else  {
												hasIndel2Missing=true;
												buffIndel.append(alleles1Aligned);
											}
											
											
										} else {
											hasIndel1Missing=true;
											hasIndel2Missing=true;
										}
								
								}
								
								
								if(buffSNP.length()>0) {
									// if has SNP allele, use SNPs
									buff.append(buffSNP.toString().trim());	
								} else if(buffGap.length()>0) {
									// if has gap use gap
									buff.append(buffGap.toString().trim());
								}
								else {
									// use indel
									String al1="";
									if(!snpAllele1.isEmpty()) al1=snpAllele1;
									else al1=indelAllele1;
									String al2="";
									if(!snpAllele2.isEmpty()) al2=snpAllele2;
									else al2=indelAllele2;
									
									if(al1==null) throw new RuntimeException("al1==null");
									if(al2==null) throw new RuntimeException("al2==null");
									String all12="";
									if(al1.compareTo(al2)<0)
										all12= al1 + "/" + al2;
									else if(al1.compareTo(al2)>0)
										all12= al2 + "/" + al1;
									else all12=al1;
									
									all12=all12.trim();
									
									if( (all12.length()>0 && all12.charAt(0)=='/') || all12.endsWith("/"))
									{
										//throw new RuntimeException( "al1="+ al1 + ";al2="+ al2);
										all12=all12.replace("/","").trim();
									}
									
									if(all12.length()>0) {
										buff.append(all12);
									}
								}
								
						}
					} else {
						throw new RuntimeException("pos==null: Impossible!");
					}
					
				} else {
					
					// SNP String only
					
					Integer j=iCols;
					
					if(pos!=null) {
						int insertOffset = pos.getPosition().remainder(BigDecimal.ONE).multiply(bd100).intValue(); 
						if( insertOffset > 0 )
						{
							buff.append(INDEL_GAP);
						} else if(listpos.get(iCols).getRefnuc().equals(INDEL_REFCONSENSUS))	{
							buff.append(INDEL_REFCONSENSUS);
						}
						else {
							// if not merged use j, if merged but not in mapMergedIdx2SnpIdx dont add, if merged but not in mapMergedIdx2SnpIdx  add using mapMergedIdx2SnpIdx.get(j)
							
								j=varstringdata.convertMergedIdx2SnpIdx( j );
								
								if(j!=null) {
									char element = snpstr.getVarnuc().substring(j,j+1).charAt(0);
									String element1= "";
									if(element!='0' && element!='.' && element!=' ' && element!='*' &&  element!='?') {
										element1 = String.valueOf(element);
										if(mapPos2allele2!=null && mapPos2allele2.get(pos)!=null) {
											char element2 = mapPos2allele2.get(pos);
											
											SnpsAllvarsPos  snppos = varstringdata.getListPos().get(iCols);
											if(snppos.getRefnuc().charAt(0)==element) 
												buff.append(element) .append("/").append(element2);
											else if(snppos.getRefnuc().charAt(0)==element2)
												buff.append(element2) .append("/").append(element);
											else {
												
												if(element2>element) 
													buff.append(element) .append("/").append(element2);
												 else 
													buff.append(element2) .append("/").append(element);
											}
										}
										else buff.append(element1);   
									}
								}
							}
						} else throw new RuntimeException("pos==null: Imposible!");
					
				}
				
				String finalallele=buff.toString().trim();
				
				if(finalallele.length()==0) {
					VariantIndelStringData indelstrdata= varstringdata.getIndelstringdata();
					if( indelstrdata!=null &&  indelstrdata.getSetPosGapRegion().contains(pos)) {
						finalallele=pos.getRefnuc();
						AppContext.debug("var=" + snpstr.getVar() + " gap at pos=" + pos.getPosition() + " buff=" + buff);
					}
					if( indelstrdata!=null &&  indelstrdata.getSetPosDeletionRegion().contains(pos)) {
						finalallele=pos.getRefnuc();
						AppContext.debug("var=" + snpstr.getVar() + " deletion at pos=" + pos.getPosition() + " buff=" + buff);
					}
				}
				
				allelesstr[iCols] = finalallele;
			}
		return allelesstr;
	}	


	/**
	 * Get descriptive allele for indel
	 * @param ip
	 * @return
	 */
	public static String getIndelAlleleString(IndelsAllvarsPos ip) {
	
			IndelsAllvarsPosAlleleImpl indelpos=(	IndelsAllvarsPosAlleleImpl)ip;
		
			if(indelpos.getInsString().length()>0 && indelpos.getInsString().charAt(0)=='-') {
				return "extdel " + indelpos.getInsString().replace("-","").trim();
			}
				
			try {
				Integer.valueOf(indelpos.getInsString());
				return "del " + indelpos.getInsString();
			} catch(Exception ex) {
				if(indelpos.getInsString().equals("."))
					return "ref";
				else if(indelpos.getInsString().equals("?"))
					return "?";
				else return indelpos.getInsString();
			}
	}
	
	
	/**
	 * Get type of Indel
	 * @param allele
	 * @return
	 */
	public static int getIndelType(String allele) {

		int ret=-1;
		if(allele.equals(".") || allele.equals("ref"))  ret= INDELTYPE_REFERENCE;
		else if(allele.equals("?") || allele.equals(" ") )  ret= INDELTYPE_MISSING;
		else if(allele.contains("->"))
			 ret= INDELTYPE_SUBSTITUTION;
		else if(allele.startsWith("del")) {
			ret= INDELTYPE_DELETION;
		}
		else if(allele.startsWith("extdel") || (allele.length() > 0 && allele.charAt(0) == '-')) {
			ret= INDELTYPE_EXTENDDELETION;
		}
		else {
			try{
				Integer.valueOf(allele);
				ret= INDELTYPE_DELETION;
			} catch(Exception ex) {
				ret= INDELTYPE_INSERTION;
			}
		}
		return ret;
	}

	public static int getIndelType(IndelsAllvarsPos ip) {
		
		IndelsAllvarsPosAlleleImpl indelpos=(IndelsAllvarsPosAlleleImpl)ip;
		int ret=-1;
		
		return getIndelType(indelpos.getInsString());
	}
	

	
	
	
}


// PAST CODES RETAINED
// 
//public static String[] createVarietyStringAlignedOrig(SnpsStringAllvars snpstr, VariantStringData varstringdata) {
//		//AppContext.debug("HDF5 createVarietyStringAligned: " + snpstr.getClass() + " " + snpstr);
//	
//		
//	
//		int cols = varstringdata.getListPos().size();
//		Map<Integer,Position> mapMergedIdx2Pos = varstringdata.getMapIdx2Pos();
//		//Map<Integer,BigDecimal> mapIndelIdx2Pos  =varstringdata.getMapIdx2Pos();
//		
//		
//		Map<BigDecimal, IndelsAllvarsPos>	mapIndelId2Indels=null;
//		//Map<Integer, Integer> mapMergedIdx2SnpIdx=null;
//		if(varstringdata.hasIndel()) {
//			//VariantIndelStringData varindelstringdata = (VariantIndelStringData)varstringdata;
//			mapIndelId2Indels = varstringdata.getIndelstringdata().getMapIndelId2Indel();
//		}
//
//		
//		String allele1Indelnucs=null;
//		String allele2Indelnucs=null;
//		int allele1Gapcount=0;
//		Integer allele2Gapcount=0;
//		
//		
//		List<SnpsAllvarsPos> listpos = varstringdata.getListPos();
//		/*
//		Iterator<SnpsAllvarsPos>  itpos=listpos.iterator();
//		while(itpos.hasNext()) {
//			SnpsAllvarsPos acpos=itpos.next();
//			AppContext.debug( acpos.getClass().toString() ); // + " " + acpos);
//			break;
//		}
//		*/
//		
//		Map<Position,Character> mapPos2allele2 = snpstr.getMapPos2Allele2();
//		
//		String allelesstr[] = new String[cols];  
//		for(int iCols=0; iCols<cols; iCols++) {
//			//SnpsStringAllvars snpstr =  (SnpsStringAllvars)matrixModel.getCellAt(cellsdata, iCols);
//			
//			StringBuffer buffGap = new StringBuffer();
//			StringBuffer buff = new StringBuffer();
//			
//			Position pos = mapMergedIdx2Pos.get(iCols);
//			//BigDecimal pos=contigpos.getPosition();
//			
//				if(snpstr instanceof IndelsStringAllvars) {
//					
//					// InsdelString
//					
//					Integer j=iCols;
//					
//					
//					//if(mapMergedIdx2Pos!=null) pos = mapMergedIdx2Pos.get(j);
//					
//					if(pos!=null) {
//						
//						int insertOffset = pos.getPosition().remainder(BigDecimal.ONE).multiply(bd100).intValue(); 
//						
//						if(varstringdata==null) throw new RuntimeException("varstringdata");
//						if( varstringdata.getIndelstringdata()==null) throw new RuntimeException(" varstringdata.getIndelstringdata()");
//						if(varstringdata.getIndelstringdata().getSetPosGapRegion()==null) throw new RuntimeException("varstringdata.getIndelstringdata().getSetPosGapRegion()");
//						if(pos==null) throw new RuntimeException("pos");
//						
//						if( varstringdata.getIndelstringdata().getSetPosGapRegion().contains(pos))
//						{
//							// insertion region
//							String alleles1="";
//							if(allele1Indelnucs!=null && allele1Indelnucs.length()>=insertOffset) {
//								//buff.append( allele1Indelnucs.charAt(insertOffset-1));
//								alleles1= String.valueOf( allele1Indelnucs.charAt(insertOffset-1) );
//								//AppContext.debug("i"+ allele1Indelnucs.charAt(insertOffset-1) + "(" + pos +")");
//							}
//							else {
//								allele1Indelnucs=null;
//								//buff.append(INDEL_GAP);
//								alleles1= INDEL_GAP;
//								//AppContext.debug("i-"+  "(" + pos +")");
//							}
//							
//							if(allele2Indelnucs!=null && allele2Indelnucs.length()>=insertOffset) {
//								String alleles2= String.valueOf( allele2Indelnucs.charAt(insertOffset-1));
//								//buff.append("/").append( allele2Indelnucs.charAt(insertOffset-1));
//								if(alleles1.equals(alleles2)) {
//									buffGap.append(alleles1);
//								} else {
//									SnpsAllvarsPos  snppos = varstringdata.getListPos().get(iCols);
//									if(snppos.getRefcall().equals(alleles1))
//										buffGap.append(alleles1).append("/").append(alleles2);
//									else if(snppos.getRefcall().equals(alleles2))
//										buffGap.append(alleles2).append("/").append(alleles1);
//									else {
//									if(alleles2.compareTo(alleles1)>0) 
//										buffGap.append(alleles1).append("/").append(alleles2);
//									else 
//										buffGap.append(alleles2).append("/").append(alleles1);
//									}
//								}
//							}
//							else {
//								allele2Indelnucs=null;
//								buffGap.append(alleles1);
//							}
//						}
//						else if(varstringdata.getIndelstringdata().getSetPosDeletionRegion().contains(pos))
//						{
//							// deletion region
//							String alleles1="";
//							if(allele1Gapcount>0) {
//								//buff.append(INDEL_GAP);
//								alleles1=INDEL_GAP;
//								allele1Gapcount--;
//								
//								//AppContext.debug("d-"+  "(" + pos +")");
//								
//							}// else buff.append(INDEL_REFCONSENSUS);
//							 else {
//								 //buff.append(listpos.get(j).getRefnuc());
//								 alleles1=listpos.get(j).getRefnuc();
//								 //AppContext.debug("d" + listpos.get(j).getRefnuc() + "(" + pos +")");
//							 }
//							
//							if(allele2Gapcount!=null) {
//								
//									
//								if(allele2Gapcount>0) {
//									//buff.append("/").append(INDEL_GAP);
//									if(!alleles1.equals(INDEL_GAP)) {
//										SnpsAllvarsPos  snppos = varstringdata.getListPos().get(iCols);
//										if(snppos.getRefcall().equals(alleles1) )
//											buffGap.append(alleles1).append("/").append(INDEL_GAP);
//										else if(snppos.getRefcall().equals(INDEL_GAP) )
//											buffGap.append(INDEL_GAP).append("/").append(alleles1);
//										else {
//											if(alleles1.compareTo(INDEL_GAP)>0) 
//												buffGap.append(INDEL_GAP).append("/").append(alleles1);
//											else 
//												buffGap.append(alleles1).append("/").append(INDEL_GAP);
//											}
//										
//									} else buffGap.append(alleles1);
//									
//									allele2Gapcount--;
//								}
//								else if(allele2Gapcount==0) {
//									allele2Gapcount=null;
//									buffGap.append(alleles1);
//								}
//								
//							} else {
//								buffGap.append(alleles1);
//							}
//						}
//						
//						/*
//						int insertOffset = pos.remainder(BigDecimal.ONE).multiply(bd100).intValue(); 
//						if( insertOffset > 0 )
//						{
//							// insertion region
//							if(allele1Indelnucs!=null && allele1Indelnucs.length()>=insertOffset)
//								buff.append( allele1Indelnucs.charAt(insertOffset-1));
//							else {
//								allele1Indelnucs=null;
//								buff.append(INDEL_GAP);
//							}
//							if(allele2Indelnucs!=null && allele2Indelnucs.length()>=insertOffset)
//								buff.append("/").append( allele2Indelnucs.charAt(insertOffset-1));
//							else {
//								allele2Indelnucs=null;
//							}
//						}
//						else if(listpos.get(iCols).getRefnuc().equals(INDEL_REFCONSENSUS))
//						{
//							// deletion region
//							if(allele1Gapcount>0) {
//								buff.append(INDEL_GAP);
//								allele1Gapcount--;
//							} else buff.append(INDEL_REFCONSENSUS);
//							
//							if(allele2Gapcount!=null) {
//								if(allele2Gapcount>0) {
//									buff.append("/").append(INDEL_GAP);
//									allele2Gapcount--;
//								}
//								if(allele2Gapcount==0) allele2Gapcount=null;
//							}
//						}
//						*/
//						//else  {
//						if(true)  {
//							
//							IndelsStringAllvars indelstring=(IndelsStringAllvars)snpstr;
//							
//								StringBuffer buffSNP= new StringBuffer();
//
//								String snpAllele1="";
//								String snpAllele2="";
//								String indelAllele1="";
//								String indelAllele2="";
//								
//								char element='\0';
//								char element2='\0';
//								
//								boolean hasSnp1Missing=false;
//								String snpElements="";
//								boolean hasIndel1Missing=true;
//								String indelElements="";
//								boolean hasSnp2Missing=false;
//								boolean hasIndel2Missing=true;
//
//								
//								if(indelstring.getVarnuc()!=null) {
//									
//									// if not merged use j, if merged but not in mapMergedIdx2SnpIdx dont add, if merged but not in mapMergedIdx2SnpIdx  add using mapMergedIdx2SnpIdx.get(j)
//									//if(mapMergedIdx2SnpIdx!=null && mapMergedIdx2SnpIdx.containsKey(iCols) && mapMergedIdx2SnpIdx.get(iCols)!=null) {
//									//	Integer objj = mapMergedIdx2SnpIdx.get(iCols);
//									
//									Integer objj = varstringdata.convertMergedIdx2SnpIdx(iCols);
//		
//										if(objj!=null) {
//											j=objj;
//											//AppContext.debug( "indelstring.getVarnuc().length()=" + snpstr.getVarnuc().length() + " j=" + j + " iCols=" + iCols);
//											element = indelstring.getVarnuc().substring(j,j+1).charAt(0);
//											if(element!='0' && element!='.' && element!=' ' && element!='*' && element!='?') {
//												//buff.append(element);
//												snpAllele1 = String.valueOf( element ); 
//											} else {
//												hasSnp1Missing=true;
//											}
//											snpElements+="1=" + element +";";
//
//											if(pos.getPosition().intValue()==38382527 &&  snpstr.getVar().intValue()==2292)
//												AppContext.debug("allele1= " + element + "  mapPos2allele2=" + mapPos2allele2);
//
//											
//											//Map<Integer,Character> mapPosidx2allele2 = indelstring.getMapPosIdx2Allele2();
//											//if(mapPosidx2allele2!=null && mapPosidx2allele2.get(j)!=null) {
//											//Map<Position,Character> mapPos2allele2 = indelstring.getMapPos2Allele2();
//											if(mapPos2allele2!=null && mapPos2allele2.get(pos)!=null) {	
//												
//												element2 = mapPos2allele2.get(pos);		
//												if(element2!='0' && element2!='.' && element2!=' ' && element2!='*' &&  element2!='?') {
//													snpAllele2=String.valueOf(element2);
//												} else {
//													hasSnp2Missing=true;
//												}
//												snpElements+="2=" + element2 +";";
//											}
//											
//											if(!snpAllele1.isEmpty() && !snpAllele2.isEmpty() && !snpAllele1.equals(snpAllele2) ) {
//												
//												
//												SnpsAllvarsPos  snppos = varstringdata.getListPos().get(iCols);
//												if(snppos.getRefcall().equals(snpAllele1)) 
//													buffSNP.append(snpAllele1) .append("/").append(snpAllele2);
//												else if(snppos.getRefcall().equals(snpAllele2))
//													buffSNP.append(snpAllele2) .append("/").append(snpAllele1);
//												else {
//													if(snpAllele1.compareTo(snpAllele2)>0) 
//														buffSNP.append(snpAllele2) .append("/").append(snpAllele1);
//													 else 
//														 buffSNP.append(snpAllele1) .append("/").append(snpAllele2);
//												}
//												/*
//													if(snpAllele1.compareTo(snpAllele2) < 0) 
//														buffSNP.append( snpAllele1).append("/").append(snpAllele2);
//													else if(snpAllele1.compareTo(snpAllele2) > 0)
//														buffSNP.append( snpAllele2).append("/").append(snpAllele1);
//													else buffSNP.append(snpAllele1);
//													*/
//												
//											} else if(!snpAllele1.isEmpty()) {
//												buffSNP.append(snpAllele1);
//											} else if(!snpAllele2.isEmpty()) {
//												buffSNP.append(snpAllele2);
//											}
//										}
//										
//								}
//								
//							
//							
//										StringBuffer buffIndel=new StringBuffer();
//							
//										
//										if(buffSNP.length()>=0) {
//											
//										// snp/insertion/deletion anchor point
//										hasIndel1Missing=false;
//										hasIndel2Missing=false;
//
//
//										
//		
//										BigDecimal alleleid = null;  //indelstring.getAllele1( pos );
//										IndelsAllvarsPos indelpos =  null; //mapIndelId2Indels.get(alleleid);
//		
//										try {
//											alleleid = indelstring.getAllele1( pos );
//											indelpos = mapIndelId2Indels.get(alleleid);
//										
//										} catch( Exception ex) {
//											//AppContext.debug( "pos=" + pos + " alleleid=" + alleleid + " indelpos=" + indelpos);
//											//ex.printStackTrace();
//										}
//										
//										//if(indelpos==null) throw new RuntimeException("cant find alleleid=" + alleleid);
//										
//										String alleles1="";
//										String alleles1Aligned="";
//										
//										//if(snpAllele1.isEmpty() && alleleid!=null && indelpos!=null) {
//										if(alleleid!=null && indelpos!=null) {
//											
//											allele1Indelnucs = null;
//											allele1Gapcount = 0;
//											int indeltype = IndelStringHDF5Service.getIndelType(indelpos);
//											switch (indeltype ) {
//												case INDELTYPE_INSERTION:
//													allele1Indelnucs = indelpos.getInsString()  ;
//													
//													//buff.append(varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos));
//													alleles1 = varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos);
//
//													if(alleles1==null) {
//														AppContext.debug("INDELTYPE_INSERTION cant find " + pos + " IN " + varstringdata.getIndelstringdata().getMapIndelpos2Refnuc());
//														throw new RuntimeException("cant find " + pos ); 
//													}
//
//													//AppContext.debug("inserting " + allele1Indelnucs +" at " + pos);
//													
//													//buff.append(varstringdata.getIndelstringdata().getMapIndelIdx2Refnuc().get(j));
//													break;
//												case INDELTYPE_SNP: 
//													//AppContext.debug("snp  " +  indelpos.getInsString() +" at " + pos);
//													//buff.append(indelpos.getInsString()); break;
//													alleles1=indelpos.getInsString(); 
//													alleles1Aligned=alleles1;
//													break;
//													
//												case INDELTYPE_DELETION:
//													//allele1Gapcount = indelpos.getMaxDellength();
//													allele1Gapcount = Integer.valueOf(indelpos.getInsString());
//													
//													//buff.append(varstringdata.getIndelstringdata().getMapIndelIdx2Refnuc().get(j));
//													//buff.append(varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos));
//													alleles1 = varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos);
//
//													if(alleles1==null) {
//														AppContext.debug("INDELTYPE_DELETION cant find " + pos + " IN " + varstringdata.getIndelstringdata().getMapIndelpos2Refnuc());
//														throw new RuntimeException("cant find " + pos ); 
//													}
//														
//
//													//AppContext.debug("deleting  " + allele1Gapcount +" at " + pos);
//													
//													break;
//												case INDELTYPE_EXTENDDELETION:
//													allele1Gapcount = Integer.valueOf(indelpos.getInsString().replace("-",""));
//													alleles1 = INDEL_GAP;
//													alleles1Aligned=alleles1;
//													break;
//												case INDELTYPE_REFERENCE: 
//													//buff.append(varstringdata.getIndelstringdata().getMapIndelIdx2Refnuc().get(j));
//													//buff.append(varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos));
//													alleles1=varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos);
//													
//													
//													if(alleles1==null) {
//														AppContext.debug("INDELTYPE_REFERENCE cant find " + pos + " IN " + varstringdata.getIndelstringdata().getMapIndelpos2Refnuc());
//														throw new RuntimeException("cant find " + pos ); 
//													}
//														
//													//AppContext.debug("ref  " +  varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos) +" at " + pos);
//													break;
//		
//												case INDELTYPE_MISSING: 
//													//buff.append(varstringdata.getIndelstringdata().getMapIndelIdx2Refnuc().get(j));
//													//buff.append(varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos));
//													alleles1="";
//													
//													//AppContext.debug("ref  " +  varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos) +" at " + pos);
//													
//													hasIndel1Missing=true;
//													
//													break;
//													
//													
//													/*
//												case INDELTYPE_INSERTION:
//													allele1Indelnucs = indelpos.getInsString()  ;
//													buff.append(INDEL_REFCONSENSUS);
//													break;
//												case INDELTYPE_SNP: 
//													buff.append(indelpos.getInsString()); break;
//												case INDELTYPE_DELETION:
//													allele1Gapcount = indelpos.getDellength();
//													buff.append(INDEL_REFCONSENSUS);
//													break;
//												case INDELTYPE_REFERENCE: 
//													buff.append(INDEL_REFCONSENSUS);
//													break;
//													*/
//											}  
//												
//											
//											indelElements+="1=" + indelpos.getInsString() +";";
//
//											
//											if(alleles1==null) throw new RuntimeException("alleles1=null,  indeltype="  + indeltype + " indelpos.getInsString()=" + indelpos.getInsString());
//											
//											indelAllele1=alleles1;
//											
//											allele2Gapcount = null;
//											BigDecimal alleleid2 = indelstring.getAllele2( pos );
//											indelpos = mapIndelId2Indels.get(alleleid2);
//											
//											//if(snpAllele2.isEmpty() && alleleid2!=null && !alleleid2.equals(alleleid)) {
//											if( alleleid2!=null && !alleleid2.equals(alleleid)) {
//												alleleid=alleleid2;
//												indelpos = mapIndelId2Indels.get(alleleid);
//												if(indelpos!=null) {
//													
//													indeltype = IndelStringHDF5Service.getIndelType(indelpos);
//													String alleles2="";
//													switch (indeltype ) {
//														case INDELTYPE_INSERTION:
//															allele2Indelnucs = indelpos.getInsString()  ; 
//															//buff.append('/').append(INDEL_REFCONSENSUS);
//															break;
//														case INDELTYPE_SNP: 
//															//buff.append('/').append(indelpos.getInsString()); break;
//															alleles2=indelpos.getInsString(); 
//															if(alleles2.compareTo(alleles1Aligned)>0)
//																buffIndel.append(alleles1Aligned).append("/").append(alleles2);
//															else
//																buffIndel.append(alleles2).append("/").append(alleles1Aligned);
//															break;
//														case INDELTYPE_DELETION: 
//															allele2Gapcount = indelpos.getMaxDellength();
//															//buff.append('/').append(INDEL_REFCONSENSUS);
//															break;
//														case INDELTYPE_EXTENDDELETION: 
//															allele2Gapcount = Integer.valueOf(indelpos.getInsString().replace("-",""));
//															buffIndel.append(alleles1Aligned).append('/').append(INDEL_GAP);
//															break;
//														case INDELTYPE_REFERENCE: 
//															//buff.append('/').append(varstringdata.getIndelstringdata().getMapIndelIdx2Refnuc().get(j));
//															
//															// replace allele1 with reference
//															//buffIndel.append(varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos));
//															break;
//															
//														case INDELTYPE_MISSING: 
//															//buff.append(varstringdata.getIndelstringdata().getMapIndelIdx2Refnuc().get(j));
//															//buff.append(varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos));
//															alleles2="";
//															//buffIndel.append(alleles1).append("/").append(alleles2);
//															//AppContext.debug("ref  " +  varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos) +" at " + pos);
//															
//															hasIndel2Missing=true;
//															
//															break;
//															
//													}
//													indelElements+="2=" + indelpos.getInsString() +";";
//													
//													if(alleles2==null) throw new RuntimeException("alleles2=null,  indeltype="  + indeltype + " indelpos.getInsString()=" + indelpos.getInsString());
//
//													
//													indelAllele2=alleles2;
//												}								
//											} 
//											else  {
//												hasIndel2Missing=true;
//												buffIndel.append(alleles1Aligned);
//											}
//											
//											
//										} else {
//											hasIndel1Missing=true;
//											hasIndel2Missing=true;
//										}
//										
//										}
//								
//										
////								//if(pos.getPosition().equals(BigDecimal.valueOf(38383721))) {
////								if(true) {
////									if(snpstr.getVar().intValue()==1128) {
////										AppContext.debug("pos=" + pos.getPosition() + " var=JATI% snp=" + buffSNP.toString() + " gap=" + buffGap.toString() + "  snpAllele1=" + snpAllele1 + " snpAllele2=" + snpAllele1 + 
////												" indelAllele1=" + indelAllele1 + " indelAllele2=" + indelAllele2);
////									}
////									else if(snpstr.getVar().intValue()==1142) {
////										AppContext.debug("pos=" + pos.getPosition() + " var=QING TAI% snp=" + buffSNP.toString() + " gap=" + buffGap.toString() + "  snpAllele1=" + snpAllele1 + " snpAllele2=" + snpAllele1 + 
////												" indelAllele1=" + indelAllele1 + " indelAllele2=" + indelAllele2);
////									}
////									if(snpstr.getVar().intValue()==1093) {
////										AppContext.debug("pos=" + pos.getPosition() + " var=RONG DAO% snp=" + buffSNP.toString() + " gap=" + buffGap.toString() + "  snpAllele1=" + snpAllele1 + " snpAllele2=" + snpAllele1 + 
////												" indelAllele1=" + indelAllele1 + " indelAllele2=" + indelAllele2);
////									}
////									
////								}
////								
////								if(pos.getPosition().equals(BigDecimal.valueOf(38383721))) {
////									AppContext.debug("pos=" + pos.getPosition() + " var=" + snpstr.getVar().intValue() + " snp=" + buffSNP.toString() + " gap=" + buffGap.toString() + "  snpAllele1=" + snpAllele1 + " snpAllele2=" + snpAllele1 + 
////											" indelAllele1=" + indelAllele1 + " indelAllele2=" + indelAllele2);
////								}
//										
//								if(buffSNP.length()>0) {
//									buff.append(buffSNP.toString().trim());	
//								} else if(buffGap.length()>0) {
//									buff.append(buffGap.toString().trim());
//								}
//								else {
//									// buff.append( buffIndel );
//									String al1="";
//									if(!snpAllele1.isEmpty()) al1=snpAllele1;
//									else al1=indelAllele1;
//									String al2="";
//									if(!snpAllele2.isEmpty()) al2=snpAllele2;
//									else al2=indelAllele2;
//									
//									if(al1==null) throw new RuntimeException("al1==null");
//									if(al2==null) throw new RuntimeException("al2==null");
//									
//									
//									String all12="";
//									if(al1.compareTo(al2)<0)
//										all12= al1 + "/" + al2;
//									else if(al1.compareTo(al2)>0)
//										all12= al2 + "/" + al1;
//									else all12=al1;
//									
//									all12=all12.trim();
//									
//									if(all12.startsWith("/") || all12.endsWith("/"))
//									{
//										//throw new RuntimeException( "al1="+ al1 + ";al2="+ al2);
//										all12=all12.replace("/","").trim();
//									}
//									
//									if(all12.length()>0) {
//										buff.append(all12);
//										/*
//										else if(buff.toString().equals(all12)) {}
//										else {
//											AppContext.debug("Inconsistent var=" +  snpstr.getVar()  + " alleles=" + buff.toString() + " ;pos=" + varstringdata.getListPos().get(iCols).getPos() +
//											varstringdata.getListPos().get(iCols).getPos() + " ;snp=" + buff + "; indel=" + all12);
//											//buff.append(all12);
//										}
//										*/
//									}
//								}
//								
//						}
//					} else {
//						throw new RuntimeException("pos==null: Impossible!");
//					}
//					
//				} else {
//					
//					// SNP String
//					
//					Integer j=iCols;
//					
//					//BigDecimal pos = null;
//					//if(mapMergedIdx2Pos!=null) pos = mapMergedIdx2Pos.get(j);
//					
//					//pos=varstringdata.convertMergedIdx2Pos(iCols);
//					
//					if(pos!=null) {
//						int insertOffset = pos.getPosition().remainder(BigDecimal.ONE).multiply(bd100).intValue(); 
//						if( insertOffset > 0 )
//						{
//							buff.append(INDEL_GAP);
//						} else if(listpos.get(iCols).getRefnuc().equals(INDEL_REFCONSENSUS))	{
//							buff.append(INDEL_REFCONSENSUS);
//						}
//						else {
//							// if not merged use j, if merged but not in mapMergedIdx2SnpIdx dont add, if merged but not in mapMergedIdx2SnpIdx  add using mapMergedIdx2SnpIdx.get(j)
//							//if(mapMergedIdx2SnpIdx!=null && mapMergedIdx2SnpIdx.containsKey(iCols) && mapMergedIdx2SnpIdx.get(iCols)!=null) {
//							//	j = mapMergedIdx2SnpIdx.get(iCols);
//							//}
//							
//							j=varstringdata.convertMergedIdx2SnpIdx( j );
//							
//							if(j!=null) {
//							//if(mapMergedIdx2SnpIdx==null || mapMergedIdx2SnpIdx.get(iCols)!=null ) {
//								//AppContext.debug( "snpstr.getVarnuc().length()=" + snpstr.getVarnuc().length() + " j=" + j + " iCols=" + iCols);
//								char element = snpstr.getVarnuc().substring(j,j+1).charAt(0);
//								String element1= "";
//
//								if(element!='0' && element!='.' && element!=' ' && element!='*' &&  element!='?') {
//									//buff.append(element);
//									element1 = String.valueOf(element);
//									//Map<Integer,Character> mapPosidx2allele2 = snpstr.getMapPosIdx2Allele2();
//									//if(mapPosidx2allele2!=null && mapPosidx2allele2.get(j)!=null) {
//									//	char element2 = mapPosidx2allele2.get(j);
//									//Map<Position,Character> mapPos2allele2 = snpstr.getMapPos2Allele2();
//									
//									if(pos.getPosition().intValue()==38382527 &&  snpstr.getVar().intValue()==2292)
//										AppContext.debug("allele1= " + element + "  mapPos2allele2=" + mapPos2allele2);
//
//									
//									if(mapPos2allele2!=null && mapPos2allele2.get(pos)!=null) {
//										char element2 = mapPos2allele2.get(pos);
//										
//										SnpsAllvarsPos  snppos = varstringdata.getListPos().get(iCols);
//										if(snppos.getRefcall().charAt(0)==element) 
//											buff.append(element) .append("/").append(element2);
//										else if(snppos.getRefcall().charAt(0)==element2)
//											buff.append(element2) .append("/").append(element);
//										else {
//											
//											if(element2>element) 
//												buff.append(element) .append("/").append(element2);
//											 else 
//												buff.append(element2) .append("/").append(element);
//										}
//										
//										/*
//										char element2 = mapPos2allele2.get(pos);
//										if(element2!='0' && element2!='.' && element2!=' ' && element2!='*' && element2!=element &&  element2!='?')  {
//											if(element2>element) 
//												buff.append(element1).append("/").append(  element2 );
//											else
//												buff.append(element2).append("/").append(element1);
//										}
//										else  buff.append(element1);
//										*/
//									}
//									else buff.append(element1);   
//								}
//							}
//						}
//	
//
//						
//					} else {
//						
//						
//						throw new RuntimeException("pos==null: Imposible!");
//
////						j=varstringdata.convertMergedIdx2SnpIdx( j );
////						
////						//if(mapMergedIdx2SnpIdx!=null && mapMergedIdx2SnpIdx.containsKey(iCols) && mapMergedIdx2SnpIdx.get(iCols)!=null) {
////						//	j = mapMergedIdx2SnpIdx.get(iCols);
////						//}
////						//if(mapMergedIdx2SnpIdx==null || mapMergedIdx2SnpIdx.get(iCols)!=null ) {
////							//AppContext.debug( "snpstr.getVarnuc().length()=" + snpstr.getVarnuc().length() + " j=" + j + " iCols=" + iCols);
////						
////						if(j!=null) {
////							char element = snpstr.getVarnuc().substring(j,j+1).charAt(0);
////							if(element!='0' && element!='.' && element!=' ' && element!='*' && element!='?') {
////								//buff.append(element);
////								//Map<Integer,Character> mapPosidx2allele2 = snpstr.getMapPosIdx2Allele2();
////								Map<Position,Character> mapPos2allele2 = snpstr.getMapPos2Allele2();
////								if(mapPos2allele2!=null && mapPos2allele2.get(pos)!=null) {
////									char element2 = mapPos2allele2.get(pos);
////									if(element2!='0' && element2!='.' && element2!=' ' && element2!='*' && element2!=element && element2!='?') { 
////										//buff.append("/").append(  element2 );
////										if(element2>element)
////											buff.append(element).append("/").append(  element2 );
////										else 
////											buff.append(element2).append("/").append(  element );
////									}
////									else  buff.append(element);
////								} 
////								else buff.append(element);
////							}
////						}
//						
//					}
//					
//				}
//				
//				String finalallele=buff.toString().trim();
//				
//				if(finalallele.length()==0) {
//					VariantIndelStringData indelstrdata= varstringdata.getIndelstringdata();
//					if( indelstrdata!=null &&  indelstrdata.getSetPosGapRegion().contains(pos)) {
//						finalallele=pos.getRefcall();
//						AppContext.debug("var=" + snpstr.getVar() + " gap at pos=" + pos.getPosition() + " buff=" + buff);
//					}
//					if( indelstrdata!=null &&  indelstrdata.getSetPosDeletionRegion().contains(pos)) {
//						finalallele=pos.getRefcall();
//						AppContext.debug("var=" + snpstr.getVar() + " deletion at pos=" + pos.getPosition() + " buff=" + buff);
//					}
//				}
//				
//				allelesstr[iCols] = finalallele;
//			}
//		
//		//AppContext.displayStringArray(allelesstr);
//		return allelesstr;
//	}

//	public static String[] createVarietyString(SnpsStringAllvars snpstr, VariantStringData varstringdata) {
//		
//		//AppContext.debug("HDF5 createVarietyString: " +  snpstr.getClass() + " " + snpstr);
//		
//		int cols = varstringdata.getListPos().size();
//		Map<Integer,BigDecimal> mapMergedIdx2Pos = varstringdata.getMapIdx2Pos();
//		Map<Integer,BigDecimal> mapIndelIdx2Pos  =varstringdata.getMapIdx2Pos();
//		
//		
//		Map<BigDecimal, IndelsAllvarsPos>	mapIndelId2Indels=null;
//		Map<Integer, Integer> mapMergedIdx2SnpIdx=null;
//		if(varstringdata.hasIndel()) {
//			VariantIndelStringData varindelstringdata = varstringdata.getIndelstringdata();
//			mapIndelId2Indels = varindelstringdata.getMapIndelId2Indel();
//			if(varstringdata.hasSnp()) {
//				VariantSnpsStringData varsnpstringdata = varstringdata.getSnpstringdata();
//				mapMergedIdx2SnpIdx = varsnpstringdata.getMapMergedIdx2SnpIdx();
//			}
//		}
//
//		String allelesstr[] = new String[cols];  
//		for(int iCols=0; iCols<cols; iCols++) {
//			//SnpsStringAllvars snpstr =  (SnpsStringAllvars)matrixModel.getCellAt(cellsdata, iCols);
//			StringBuffer buff = new StringBuffer();
//			
//				if(snpstr instanceof IndelsStringAllvars) {
//					
//					int j=iCols;
//					
//					BigDecimal pos = null;
//					if(mapMergedIdx2Pos!=null) 
//						pos = mapMergedIdx2Pos.get(j);
//					else 
//						pos = mapIndelIdx2Pos.get(j);
//					
//					if(pos!=null) {
//					
//						IndelsStringAllvars indelstring=(IndelsStringAllvars)snpstr;
//			
//						if(indelstring.getVarnuc()!=null) {
//							
//							// if not merged use j, if merged but not in mapMergedIdx2SnpIdx dont add, if merged but not in mapMergedIdx2SnpIdx  add using mapMergedIdx2SnpIdx.get(j)
//							if(mapMergedIdx2SnpIdx!=null && mapMergedIdx2SnpIdx.containsKey(iCols) && mapMergedIdx2SnpIdx.get(iCols)!=null) {
//								j = mapMergedIdx2SnpIdx.get(iCols);
//
//								//AppContext.debug( "indelstring.getVarnuc().length()=" + snpstr.getVarnuc().length() + " j=" + j + " iCols=" + iCols);
//								char element = indelstring.getVarnuc().substring(j,j+1).charAt(0);
//								if(element!='0' && element!='.' && element!=' ' && element!='*' && element!='?') {
//									Map<Integer,Character> mapPosidx2allele2 = indelstring.getMapPosIdx2Allele2();
//									if(mapPosidx2allele2!=null && mapPosidx2allele2.get(j)!=null) {
//										char element2 = mapPosidx2allele2.get(j);
//										if(element2!='0' && element2!='.' && element2!=' ' && element2!='*' && element2!=element) {
//											if(element2>element) 
//												buff.append(element) .append("/").append(element2);
//											 else 
//												buff.append(element2) .append("/").append(element);
//										} else 
//											buff.append(element);
//									} else
//										buff.append(element);
//									
//									/*
//									buff.append(element);
//								
//									Map<Integer,Character> mapPosidx2allele2 = indelstring.getMapPosIdx2Allele2();
//									if(mapPosidx2allele2!=null && mapPosidx2allele2.get(j)!=null) {
//										char element2 = mapPosidx2allele2.get(j);
//										if(element2!='0' && element2!='.' && element2!=' ' && element2!='*' && element2!=element) 
//											buff.append("/").append(element2);
//									}
//									*/
//									
//								}
//							}
//						}  
//						
//						if(true) {
//							
//							BigDecimal alleleid = null; //indelstring.getAllele1( pos );
//							IndelsAllvarsPos indelpos = null; //apIndelId2Indels.get(alleleid);
//
//							try{
//								alleleid = indelstring.getAllele1( pos );
//								indelpos = mapIndelId2Indels.get(alleleid);
//								
//							}catch (Exception ex) {
//								//AppContext.debug("pos=" + pos + " alleleid=" + alleleid + " indelpos=" + indelpos );
//							}
//							
//							//if(indelpos==null) throw new RuntimeException("cant find alleleid=" + alleleid);
//							if(alleleid!=null && indelpos!=null) {
//								
//								buff.append( IndelStringHDF5Service.getIndelAlleleString(indelpos) );
//								
//								BigDecimal alleleid2 = indelstring.getAllele2( pos );
//								indelpos = mapIndelId2Indels.get(alleleid2);
//								
//								if(alleleid2!=null && !alleleid2.equals(alleleid)) {
//									alleleid=alleleid2;
//									indelpos = mapIndelId2Indels.get(alleleid);
//									if(indelpos!=null) {
//										buff.append("/").append(  IndelStringHDF5Service.getIndelAlleleString(indelpos) );
//									}								
//								}
//							}
//								
//							
//						}
//					}
//					
//				} else {
//					int j=iCols;
//					// if not merged use j, if merged but not in mapMergedIdx2SnpIdx dont add, if merged but not in mapMergedIdx2SnpIdx  add using mapMergedIdx2SnpIdx.get(j)
//					if(mapMergedIdx2SnpIdx!=null && mapMergedIdx2SnpIdx.containsKey(iCols) && mapMergedIdx2SnpIdx.get(iCols)!=null) {
//						j = mapMergedIdx2SnpIdx.get(iCols);
//					}
//					
//					if(mapMergedIdx2SnpIdx==null || mapMergedIdx2SnpIdx.get(iCols)!=null ) {
//						//AppContext.debug( "snpstr.getVarnuc().length()=" + snpstr.getVarnuc().length() + " j=" + j + " iCols=" + iCols);
//						char element = snpstr.getVarnuc().substring(j,j+1).charAt(0);
//						if(element!='0' && element!='.' && element!=' ' && element!='*' && element!='?') {
//							Map<Integer,Character> mapPosidx2allele2 = snpstr.getMapPosIdx2Allele2();
//							if(mapPosidx2allele2!=null && mapPosidx2allele2.get(j)!=null) {
//								char element2 = mapPosidx2allele2.get(j);
//								if(element2!='0' && element2!='.' && element2!=' ' && element2!='*' && element2!=element && element2!='?') {
//									if(element2>element) 
//										buff.append(element).append("/").append(element2);
//									 else 
//										buff.append(element2).append("/").append(element);
//								} else 
//									buff.append(element);
//							} else
//								buff.append(element);
//
//							/*
//							buff.append(element);
//							Map<Integer,Character> mapPosidx2allele2 = snpstr.getMapPosIdx2Allele2();
//							if(mapPosidx2allele2!=null && mapPosidx2allele2.get(j)!=null) {
//								char element2 = mapPosidx2allele2.get(j);
//								if(element2!='0' && element2!='.' && element2!=' ' && element2!='*' && element2!=element) 
//									buff.append("/").append(  element2 );
//							}
//							*/
//						}
//					}
//					
//				}
//				allelesstr[iCols] = buff.toString();
//			}
//		return allelesstr;
//	}
//		
//
//public static String[] createVarietyStringAligned(SnpsStringAllvars snpstr, VariantStringData varstringdata) {
//		//AppContext.debug("HDF5 createVarietyStringAligned: " + snpstr.getClass() + " " + snpstr);
//	
//		
//	
//		int cols = varstringdata.getListPos().size();
//		Map<Integer,BigDecimal> mapMergedIdx2Pos = varstringdata.getMapIdx2Pos();
//		//Map<Integer,BigDecimal> mapIndelIdx2Pos  =varstringdata.getMapIdx2Pos();
//		
//		
//		Map<BigDecimal, IndelsAllvarsPos>	mapIndelId2Indels=null;
//		Map<Integer, Integer> mapMergedIdx2SnpIdx=null;
//		if(varstringdata.hasIndel()) {
//			//VariantIndelStringData varindelstringdata = (VariantIndelStringData)varstringdata;
//			mapIndelId2Indels = varstringdata.getIndelstringdata().getMapIndelId2Indel();
//			if(varstringdata.hasSnp()) {
//				//VariantSnpsStringData varsnpstringdata = (VariantSnpsStringData)varstringdata;
//				mapMergedIdx2SnpIdx = varstringdata.getSnpstringdata().getMapMergedIdx2SnpIdx();
//			}
//		}
//
//		
//		String allele1Indelnucs=null;
//		String allele2Indelnucs=null;
//		int allele1Gapcount=0;
//		Integer allele2Gapcount=0;
//		
//		
//		List<SnpsAllvarsPos> listpos = varstringdata.getListPos();
//		/*
//		Iterator<SnpsAllvarsPos>  itpos=listpos.iterator();
//		while(itpos.hasNext()) {
//			SnpsAllvarsPos acpos=itpos.next();
//			AppContext.debug( acpos.getClass().toString() ); // + " " + acpos);
//			break;
//		}
//		*/
//		
//		String allelesstr[] = new String[cols];  
//		for(int iCols=0; iCols<cols; iCols++) {
//			//SnpsStringAllvars snpstr =  (SnpsStringAllvars)matrixModel.getCellAt(cellsdata, iCols);
//			
//			StringBuffer buffGap = new StringBuffer();
//			StringBuffer buff = new StringBuffer();
//			
//				if(snpstr instanceof IndelsStringAllvars) {
//					
//					// InsdelString
//					
//					int j=iCols;
//					
//					BigDecimal pos = null;
//					if(mapMergedIdx2Pos!=null) pos = mapMergedIdx2Pos.get(j);
//					
//					if(pos!=null) {
//						
//						int insertOffset = pos.remainder(BigDecimal.ONE).multiply(bd100).intValue(); 
//						if( varstringdata.getIndelstringdata().getSetPosGapRegion().contains(pos))
//						{
//							// insertion region
//							String alleles1="";
//							if(allele1Indelnucs!=null && allele1Indelnucs.length()>=insertOffset) {
//								//buff.append( allele1Indelnucs.charAt(insertOffset-1));
//								alleles1= String.valueOf( allele1Indelnucs.charAt(insertOffset-1) );
//								//AppContext.debug("i"+ allele1Indelnucs.charAt(insertOffset-1) + "(" + pos +")");
//							}
//							else {
//								allele1Indelnucs=null;
//								//buff.append(INDEL_GAP);
//								alleles1= INDEL_GAP;
//								//AppContext.debug("i-"+  "(" + pos +")");
//							}
//							
//							if(allele2Indelnucs!=null && allele2Indelnucs.length()>=insertOffset) {
//								String alleles2= String.valueOf( allele2Indelnucs.charAt(insertOffset-1));
//								//buff.append("/").append( allele2Indelnucs.charAt(insertOffset-1));
//								if(alleles2.compareTo(alleles1)>0) 
//									buffGap.append(alleles1).append("/").append(alleles2);
//								else 
//									buffGap.append(alleles2).append("/").append(alleles1);
//							}
//							else {
//								allele2Indelnucs=null;
//								buffGap.append(alleles1);
//							}
//						}
//						else if(varstringdata.getIndelstringdata().getSetPosDeletionRegion().contains(pos.longValue()))
//						{
//							// deletion region
//							String alleles1="";
//							if(allele1Gapcount>0) {
//								//buff.append(INDEL_GAP);
//								alleles1=INDEL_GAP;
//								allele1Gapcount--;
//								
//								//AppContext.debug("d-"+  "(" + pos +")");
//								
//							}// else buff.append(INDEL_REFCONSENSUS);
//							 else {
//								 //buff.append(listpos.get(j).getRefnuc());
//								 alleles1=listpos.get(j).getRefnuc();
//								 //AppContext.debug("d" + listpos.get(j).getRefnuc() + "(" + pos +")");
//							 }
//							
//							if(allele2Gapcount!=null) {
//								if(allele2Gapcount>0) {
//									//buff.append("/").append(INDEL_GAP);
//									buffGap.append(alleles1).append("/").append(INDEL_GAP);
//									allele2Gapcount--;
//								}
//								else if(allele2Gapcount==0) {
//									allele2Gapcount=null;
//									buffGap.append(alleles1);
//								}
//								
//							} else {
//								buffGap.append(alleles1);
//							}
//						}
//						
//						/*
//						int insertOffset = pos.remainder(BigDecimal.ONE).multiply(bd100).intValue(); 
//						if( insertOffset > 0 )
//						{
//							// insertion region
//							if(allele1Indelnucs!=null && allele1Indelnucs.length()>=insertOffset)
//								buff.append( allele1Indelnucs.charAt(insertOffset-1));
//							else {
//								allele1Indelnucs=null;
//								buff.append(INDEL_GAP);
//							}
//							if(allele2Indelnucs!=null && allele2Indelnucs.length()>=insertOffset)
//								buff.append("/").append( allele2Indelnucs.charAt(insertOffset-1));
//							else {
//								allele2Indelnucs=null;
//							}
//						}
//						else if(listpos.get(iCols).getRefnuc().equals(INDEL_REFCONSENSUS))
//						{
//							// deletion region
//							if(allele1Gapcount>0) {
//								buff.append(INDEL_GAP);
//								allele1Gapcount--;
//							} else buff.append(INDEL_REFCONSENSUS);
//							
//							if(allele2Gapcount!=null) {
//								if(allele2Gapcount>0) {
//									buff.append("/").append(INDEL_GAP);
//									allele2Gapcount--;
//								}
//								if(allele2Gapcount==0) allele2Gapcount=null;
//							}
//						}
//						*/
//						//else  {
//						if(true)  {
//							
//							IndelsStringAllvars indelstring=(IndelsStringAllvars)snpstr;
//							
//								StringBuffer buffSNP= new StringBuffer();
//
//								String snpAllele1="";
//								String snpAllele2="";
//								String indelAllele1="";
//								String indelAllele2="";
//								
//								char element='\0';
//								char element2='\0';
//								
//								boolean hasSnp1Missing=false;
//								String snpElements="";
//								boolean hasIndel1Missing=true;
//								String indelElements="";
//								boolean hasSnp2Missing=false;
//								boolean hasIndel2Missing=true;
//
//								
//								if(indelstring.getVarnuc()!=null) {
//									
//									// if not merged use j, if merged but not in mapMergedIdx2SnpIdx dont add, if merged but not in mapMergedIdx2SnpIdx  add using mapMergedIdx2SnpIdx.get(j)
//									if(mapMergedIdx2SnpIdx!=null && mapMergedIdx2SnpIdx.containsKey(iCols) && mapMergedIdx2SnpIdx.get(iCols)!=null) {
//										Integer objj = mapMergedIdx2SnpIdx.get(iCols);
//		
//										if(objj!=null) {
//											j=objj;
//											//AppContext.debug( "indelstring.getVarnuc().length()=" + snpstr.getVarnuc().length() + " j=" + j + " iCols=" + iCols);
//											element = indelstring.getVarnuc().substring(j,j+1).charAt(0);
//											if(element!='0' && element!='.' && element!=' ' && element!='*' && element!='?') {
//												//buff.append(element);
//												snpAllele1 = String.valueOf( element ); 
//											} else {
//												hasSnp1Missing=true;
//											}
//											snpElements+="1=" + element +";";
//
//											
//											Map<Integer,Character> mapPosidx2allele2 = indelstring.getMapPosIdx2Allele2();
//											if(mapPosidx2allele2!=null && mapPosidx2allele2.get(j)!=null) {
//												element2 = mapPosidx2allele2.get(j);		
//												if(element2!='0' && element2!='.' && element2!=' ' && element2!='*' &&  element2!='?') {
//													snpAllele2=String.valueOf(element2);
//												} else {
//													hasSnp2Missing=true;
//												}
//												snpElements+="2=" + element2 +";";
//											}
//											
//											if(!snpAllele1.isEmpty() && !snpAllele2.isEmpty()) {
//													if(snpAllele1.compareTo(snpAllele2) < 0) 
//														buffSNP.append( snpAllele1).append("/").append(snpAllele2);
//													else if(snpAllele1.compareTo(snpAllele2) > 0)
//														buffSNP.append( snpAllele2).append("/").append(snpAllele1);
//													else buffSNP.append(snpAllele1);
//											} else if(!snpAllele1.isEmpty()) {
//												buffSNP.append(snpAllele1);
//											}
//										}
//										
//									} 
//								}
//								
//							
//							
//										StringBuffer buffIndel=new StringBuffer();
//							
//										
//										if(buffSNP.length()>=0) {
//											
//										// snp/insertion/deletion anchor point
//										hasIndel1Missing=false;
//										hasIndel2Missing=false;
//
//
//										
//		
//										BigDecimal alleleid = null;  //indelstring.getAllele1( pos );
//										IndelsAllvarsPos indelpos =  null; //mapIndelId2Indels.get(alleleid);
//		
//										try {
//											alleleid = indelstring.getAllele1( pos );
//											indelpos = mapIndelId2Indels.get(alleleid);
//										
//										} catch( Exception ex) {
//											//AppContext.debug( "pos=" + pos + " alleleid=" + alleleid + " indelpos=" + indelpos);
//											//ex.printStackTrace();
//										}
//										
//										//if(indelpos==null) throw new RuntimeException("cant find alleleid=" + alleleid);
//										
//										String alleles1="";
//										String alleles1Aligned="";
//										
//										//if(snpAllele1.isEmpty() && alleleid!=null && indelpos!=null) {
//										if(alleleid!=null && indelpos!=null) {
//											
//											allele1Indelnucs = null;
//											allele1Gapcount = 0;
//											int indeltype = IndelStringHDF5Service.getIndelType(indelpos);
//											switch (indeltype ) {
//												case INDELTYPE_INSERTION:
//													allele1Indelnucs = indelpos.getInsString()  ;
//													
//													//buff.append(varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos));
//													alleles1 = varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos);
//													
//													//AppContext.debug("inserting " + allele1Indelnucs +" at " + pos);
//													
//													//buff.append(varstringdata.getIndelstringdata().getMapIndelIdx2Refnuc().get(j));
//													break;
//												case INDELTYPE_SNP: 
//													//AppContext.debug("snp  " +  indelpos.getInsString() +" at " + pos);
//													//buff.append(indelpos.getInsString()); break;
//													alleles1=indelpos.getInsString(); 
//													alleles1Aligned=alleles1;
//													break;
//													
//												case INDELTYPE_DELETION:
//													//allele1Gapcount = indelpos.getMaxDellength();
//													allele1Gapcount = Integer.valueOf(indelpos.getInsString());
//													
//													//buff.append(varstringdata.getIndelstringdata().getMapIndelIdx2Refnuc().get(j));
//													//buff.append(varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos));
//													alleles1 = varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos);
//													
//													//AppContext.debug("deleting  " + allele1Gapcount +" at " + pos);
//													
//													break;
//												case INDELTYPE_EXTENDDELETION:
//													allele1Gapcount = Integer.valueOf(indelpos.getInsString().replace("-",""));
//													alleles1 = INDEL_GAP;
//													alleles1Aligned=alleles1;
//													break;
//												case INDELTYPE_REFERENCE: 
//													//buff.append(varstringdata.getIndelstringdata().getMapIndelIdx2Refnuc().get(j));
//													//buff.append(varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos));
//													alleles1=varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos);
//													
//													//AppContext.debug("ref  " +  varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos) +" at " + pos);
//													break;
//		
//												case INDELTYPE_MISSING: 
//													//buff.append(varstringdata.getIndelstringdata().getMapIndelIdx2Refnuc().get(j));
//													//buff.append(varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos));
//													alleles1="";
//													
//													//AppContext.debug("ref  " +  varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos) +" at " + pos);
//													
//													hasIndel1Missing=true;
//													
//													break;
//													
//													
//													/*
//												case INDELTYPE_INSERTION:
//													allele1Indelnucs = indelpos.getInsString()  ;
//													buff.append(INDEL_REFCONSENSUS);
//													break;
//												case INDELTYPE_SNP: 
//													buff.append(indelpos.getInsString()); break;
//												case INDELTYPE_DELETION:
//													allele1Gapcount = indelpos.getDellength();
//													buff.append(INDEL_REFCONSENSUS);
//													break;
//												case INDELTYPE_REFERENCE: 
//													buff.append(INDEL_REFCONSENSUS);
//													break;
//													*/
//											}  
//												
//											
//											indelElements+="1=" + indelpos.getInsString() +";";
//
//											indelAllele1=alleles1;
//											
//											allele2Gapcount = null;
//											BigDecimal alleleid2 = indelstring.getAllele2( pos );
//											indelpos = mapIndelId2Indels.get(alleleid2);
//											
//											//if(snpAllele2.isEmpty() && alleleid2!=null && !alleleid2.equals(alleleid)) {
//											if( alleleid2!=null && !alleleid2.equals(alleleid)) {
//												alleleid=alleleid2;
//												indelpos = mapIndelId2Indels.get(alleleid);
//												if(indelpos!=null) {
//													
//													indeltype = IndelStringHDF5Service.getIndelType(indelpos);
//													String alleles2="";
//													switch (indeltype ) {
//														case INDELTYPE_INSERTION:
//															allele2Indelnucs = indelpos.getInsString()  ; 
//															//buff.append('/').append(INDEL_REFCONSENSUS);
//															break;
//														case INDELTYPE_SNP: 
//															//buff.append('/').append(indelpos.getInsString()); break;
//															alleles2=indelpos.getInsString(); 
//															if(alleles2.compareTo(alleles1Aligned)>0)
//																buffIndel.append(alleles1Aligned).append("/").append(alleles2);
//															else
//																buffIndel.append(alleles2).append("/").append(alleles1Aligned);
//															break;
//														case INDELTYPE_DELETION: 
//															allele2Gapcount = indelpos.getMaxDellength();
//															//buff.append('/').append(INDEL_REFCONSENSUS);
//															break;
//														case INDELTYPE_EXTENDDELETION: 
//															allele2Gapcount = Integer.valueOf(indelpos.getInsString().replace("-",""));
//															buffIndel.append(alleles1Aligned).append('/').append(INDEL_GAP);
//															break;
//														case INDELTYPE_REFERENCE: 
//															//buff.append('/').append(varstringdata.getIndelstringdata().getMapIndelIdx2Refnuc().get(j));
//															
//															// replace allele1 with reference
//															//buffIndel.append(varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos));
//															break;
//															
//														case INDELTYPE_MISSING: 
//															//buff.append(varstringdata.getIndelstringdata().getMapIndelIdx2Refnuc().get(j));
//															//buff.append(varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos));
//															alleles2="";
//															//buffIndel.append(alleles1).append("/").append(alleles2);
//															//AppContext.debug("ref  " +  varstringdata.getIndelstringdata().getMapIndelpos2Refnuc().get(pos) +" at " + pos);
//															
//															hasIndel2Missing=true;
//															
//															break;
//															
//													}
//													indelElements+="2=" + indelpos.getInsString() +";";
//													
//													indelAllele2=alleles2;
//												}								
//											} 
//											else  {
//												hasIndel2Missing=true;
//												buffIndel.append(alleles1Aligned);
//											}
//											
//											
//										} else {
//											hasIndel1Missing=true;
//											hasIndel2Missing=true;
//										}
//										
//										}
//								
//								if(buffSNP.length()>0) {
//									buff.append(buffSNP.toString().trim());	
//								} else if(buffGap.length()>0) {
//									buff.append(buffGap.toString().trim());
//								}
//										
//								else {
//									// buff.append( buffIndel );
//									String al1="";
//									if(!snpAllele1.isEmpty()) al1=snpAllele1;
//									else al1=indelAllele1;
//									String al2="";
//									if(!snpAllele2.isEmpty()) al2=snpAllele2;
//									else al2=indelAllele2;
//									
//									
//									String all12="";
//									if(al1.compareTo(al2)<0)
//										all12= al1 + "/" + al2;
//									else if(al1.compareTo(al2)>0)
//										all12= al2 + "/" + al1;
//									else all12=al1;
//									
//									all12=all12.trim();
//									
//									if(all12.startsWith("/") || all12.endsWith("/"))
//									{
//										//throw new RuntimeException( "al1="+ al1 + ";al2="+ al2);
//										all12=all12.replace("/","").trim();
//									}
//									
//									if(all12.length()>0) {
//										buff.append(all12);
//										/*
//										else if(buff.toString().equals(all12)) {}
//										else {
//											AppContext.debug("Inconsistent var=" +  snpstr.getVar()  + " alleles=" + buff.toString() + " ;pos=" + varstringdata.getListPos().get(iCols).getPos() +
//											varstringdata.getListPos().get(iCols).getPos() + " ;snp=" + buff + "; indel=" + all12);
//											//buff.append(all12);
//										}
//										*/
//									}
//								}
//						}
//					}
//					
//				} else {
//					
//					// SNP String
//					
//					int j=iCols;
//					
//					BigDecimal pos = null;
//					if(mapMergedIdx2Pos!=null) pos = mapMergedIdx2Pos.get(j);
//					if(pos!=null) {
//						int insertOffset = pos.remainder(BigDecimal.ONE).multiply(bd100).intValue(); 
//						if( insertOffset > 0 )
//						{
//							buff.append(INDEL_GAP);
//						} else if(listpos.get(iCols).getRefnuc().equals(INDEL_REFCONSENSUS))	{
//							buff.append(INDEL_REFCONSENSUS);
//						}
//						else {
//							// if not merged use j, if merged but not in mapMergedIdx2SnpIdx dont add, if merged but not in mapMergedIdx2SnpIdx  add using mapMergedIdx2SnpIdx.get(j)
//							if(mapMergedIdx2SnpIdx!=null && mapMergedIdx2SnpIdx.containsKey(iCols) && mapMergedIdx2SnpIdx.get(iCols)!=null) {
//								j = mapMergedIdx2SnpIdx.get(iCols);
//							}
//							
//							if(mapMergedIdx2SnpIdx==null || mapMergedIdx2SnpIdx.get(iCols)!=null ) {
//								//AppContext.debug( "snpstr.getVarnuc().length()=" + snpstr.getVarnuc().length() + " j=" + j + " iCols=" + iCols);
//								char element = snpstr.getVarnuc().substring(j,j+1).charAt(0);
//								String element1= "";
//
//								if(element!='0' && element!='.' && element!=' ' && element!='*' &&  element!='?') {
//									//buff.append(element);
//									element1 = String.valueOf(element);
//									Map<Integer,Character> mapPosidx2allele2 = snpstr.getMapPosIdx2Allele2();
//									if(mapPosidx2allele2!=null && mapPosidx2allele2.get(j)!=null) {
//										char element2 = mapPosidx2allele2.get(j);
//										if(element2!='0' && element2!='.' && element2!=' ' && element2!='*' && element2!=element &&  element2!='?')  {
//											if(element2>element) 
//												buff.append(element1).append("/").append(  element2 );
//											else
//												buff.append(element2).append("/").append(element1);
//										}
//										else  buff.append(element1);
//									}
//									else buff.append(element1);   
//								}
//							}
//						}
//					} else {
//
//						if(mapMergedIdx2SnpIdx!=null && mapMergedIdx2SnpIdx.containsKey(iCols) && mapMergedIdx2SnpIdx.get(iCols)!=null) {
//							j = mapMergedIdx2SnpIdx.get(iCols);
//						}
//						
//						if(mapMergedIdx2SnpIdx==null || mapMergedIdx2SnpIdx.get(iCols)!=null ) {
//							//AppContext.debug( "snpstr.getVarnuc().length()=" + snpstr.getVarnuc().length() + " j=" + j + " iCols=" + iCols);
//							char element = snpstr.getVarnuc().substring(j,j+1).charAt(0);
//							if(element!='0' && element!='.' && element!=' ' && element!='*' && element!='?') {
//								//buff.append(element);
//								Map<Integer,Character> mapPosidx2allele2 = snpstr.getMapPosIdx2Allele2();
//								if(mapPosidx2allele2!=null && mapPosidx2allele2.get(j)!=null) {
//									char element2 = mapPosidx2allele2.get(j);
//									if(element2!='0' && element2!='.' && element2!=' ' && element2!='*' && element2!=element && element2!='?') { 
//										//buff.append("/").append(  element2 );
//										if(element2>element)
//											buff.append(element).append("/").append(  element2 );
//										else 
//											buff.append(element2).append("/").append(  element );
//									}
//									else  buff.append(element);
//								} 
//								else buff.append(element);
//							}
//						}
//						
//					}
//					
//				}
//				allelesstr[iCols] = buff.toString();
//			}
//		
//		//AppContext.displayStringArray(allelesstr);
//		return allelesstr;
//	}


	

//	public static String getIndelAlleleString(IndelsAllvarsPos indelpos) {
//
//		if(indelpos==null) return "";
//		if(indelpos.getDellength()==0) {
//			if(indelpos.getInsString()==null || indelpos.getInsString().trim().isEmpty()) 
//				return "ref";
//			else return indelpos.getInsString();
//		} else {
//			if(indelpos.getInsString()!=null && !indelpos.getInsString().trim().isEmpty() ) {
//				if(indelpos.getInsString().trim().length()==1)
//					return "snp -> " + indelpos.getInsString();
//				else return "del " + indelpos.getDellength() + " -> " + indelpos.getInsString();
//			}
//			else return "del " + indelpos.getDellength();
//		}
//	}
	

//public static double countVarpairMismatchIndel(IndelsAllvarsPos var1char, IndelsAllvarsPos var2char , boolean var1isref, IndelsAllvarsPos var1allele2, IndelsAllvarsPos var2allele2) {
//	
//	  
//	double misCount = 0;
//	if(var1char==null && var2char==null) {
//	}
//	else if(var1char==null && var2char!=null) {
//	if(var2char.getInsString()!=null) misCount+=var2char.getInsString().length();
//	 misCount+= var2char.getMaxDellength();
//	 
//	 if(var2allele2!=null) {
//			if(var2allele2.getInsString()!=null) misCount+=var2allele2.getInsString().length();
//			 misCount+= var2allele2.getMaxDellength();
//	 }
//	} else if(var2char==null && var1char!=null) {
//	if(var1char.getInsString()!=null) misCount+=var1char.getInsString().length();
//	 misCount+= var1char.getMaxDellength();
//	 
//	 if(var1allele2!=null) {
//			if(var1allele2.getInsString()!=null) misCount+=var1allele2.getInsString().length();
//			 misCount+= var1allele2.getMaxDellength();
//	 }
//		
//	} else if(var1isref) {
//	if(var2char.getInsString()!=null) misCount+=var2char.getInsString().length();
//	 misCount+= var2char.getMaxDellength();
//	 
//	 if(var2allele2!=null) {
//			if(var2allele2.getInsString()!=null) misCount+=var2allele2.getInsString().length();
//			 misCount+= var2allele2.getMaxDellength();
//	 }
//		
//	
//} else {
//
//	// allele2 not yet considered in for pairwise comparison
//	
//	if(var1char.getInsString()!=null && !var1char.getInsString().isEmpty()) 
//	{
//		if(var2char.getInsString()!=null && !var2char.getInsString().isEmpty()) {
//			// both are ins 
//			if(var2char.getInsString().equals(var1char.getInsString())) {
//				// equal
//			} else if(var2char.getInsString().startsWith( var1char.getInsString() ) || var1char.getInsString().startsWith( var2char.getInsString() )  ) {
//				// substring
//				double deflength =  var2char.getInsString().length() -  var1char.getInsString().length();
//				
//				if(deflength>0)
//					misCount +=  deflength;
//				else 
//					misCount +=  -deflength;
//			} else {
//				misCount += var2char.getInsString().length() +  var1char.getInsString().length();
//			}
//		} else {
//			// var2 is del
//			misCount += var1char.getInsString().length();
//			misCount += var2char.getMaxDellength();
//		} 
//	} else {
//
//		// var1 is del
//		if(var2char.getInsString()!=null && !var2char.getInsString().isEmpty()) {
//			// var2 is ins
//			misCount += var2char.getInsString().length() +  var1char.getMaxDellength();
//		} else {
//			// var2 is del
//			double defdel = var1char.getMaxDellength() -   var2char.getMaxDellength();
//			if(defdel>0)
//				misCount += defdel; 
//			else 
//				misCount += - defdel;
//		} 
//	}
//}
//
//return misCount;
//}

//
//public static String createGaps(int length) {
//	StringBuffer buff = new StringBuffer();
//	for(int i=0; i<length; i++) buff.append(INDEL_GAP);
//	return buff.toString();
//}


///**
// * Get base (alignment) format allele
// * @param indelpos
// * @return
// */
//public static String getAlignedIndelAlleleString(IndelsAllvarsPos indelpos) {
//
//	//AppContext.debug("HDF5 getAlignedIndelAlleleString: " + indelpos.getClass() + " " + indelpos);
//	
//	if(indelpos==null) return "";
//	
//	int insIdx = indelpos.getPos().remainder(BigDecimal.ONE).multiply(bdHundred).intValue();
//	
//	if(insIdx>0) {
//		return indelpos.getInsString().substring(insIdx-1,insIdx);
//	} else {
//		
//		return getIndelAlleleString(indelpos);
//		
////		if(indelpos.getDellength()==0) {
////			if(indelpos.getInsString()==null || indelpos.getInsString().trim().isEmpty()) 
////				return "ref";
////			else return indelpos.getInsString();
////		} else {
////			if(indelpos.getInsString()!=null && !indelpos.getInsString().trim().isEmpty() ) {
////				if(indelpos.getInsString().trim().length()==1)
////					return "snp -> " + indelpos.getInsString();
////				else return "del " + indelpos.getDellength() + " -> " + indelpos.getInsString();
////			}
////			else return "del " + indelpos.getDellength();
////		}
//	}
//}
//
///*
//public static String getIndelType(String allele) {
//	if(allele.startsWith("ref")) return "reference";
//	else if(allele.startsWith("snp")) return "snp";
//	else if(allele.startsWith("del")) {
//		if(allele.contains("->"))
//			return "substitution";
//		else return "deletion";
//	}
//	else return "insertion";
//}
//*/
