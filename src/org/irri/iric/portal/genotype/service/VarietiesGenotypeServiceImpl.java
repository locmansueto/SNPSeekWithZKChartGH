package org.irri.iric.portal.genotype.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
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
import org.irri.iric.portal.dao.IndelStringDAOImpl;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.domain.Gene;
import org.irri.iric.portal.domain.IndelsAllvars;
import org.irri.iric.portal.domain.IndelsAllvarsPos;
import org.irri.iric.portal.domain.IndelsStringAllvars;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.domain.VariantIndelStringData;
import org.irri.iric.portal.domain.VariantSnpsStringData;
import org.irri.iric.portal.domain.VariantStringData;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.genotype.service.GenotypeFacadeChadoImpl.SnpsAllvarsPosComparator;
import org.irri.iric.portal.genotype.service.GenotypeFacadeChadoImpl.SnpsStringAllvarsImplSorter;
import org.irri.iric.portal.variety.service.VarietyFacade;
import org.irri.iric.portal.domain.VariantTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("VarietiesGenotypeService")
public class VarietiesGenotypeServiceImpl implements VarietiesGenotypeService {
	
	@Autowired
	private ListItemsDAO listitemsdao;

	@Autowired
	@Qualifier("SnpsStringService")
	VariantStringService allsnpsService;
//	@Autowired
//	@Qualifier("CoreSnpsService")
//	VariantStringService coresnpsService;
	@Autowired
	@Qualifier("IndelService")
	VariantStringService indelsService;
	
	//protected abstract List<SnpsStringAllvars> getVariantsStringInAllVarieties(GenotypeQueryParams params); 
	private VariantStringService getCoreSNPService() 
	{
		return getAllSNPService();
	}
	private VariantStringService getAllSNPService() {
		return (VariantStringService)AppContext.checkBean(allsnpsService, "SnpsStringService");
		
	}
	private VariantStringService getIndelsService(){
		return (VariantStringService)AppContext.checkBean(indelsService, "IndelService");
		
	}
	

	@Override
	public VariantTable fillVariantTable(VariantTable table, GenotypeQueryParams params) throws Exception {
		// TODO Auto-generated method stub
			
		Collection colVarIds =params.getColVarIds();
		String sChr = params.getsChr();
		Long lStart = params.getlStart();
		Long lEnd = params.getlEnd();
		boolean bSNP = params.isbSNP();
		boolean bIndel = params.isbIndel();
		boolean bCoreonly = params.isbCoreonly();
		boolean bMismatchonly = params.isbMismatchonly();
		Collection poslist = params.getPoslist();
		String sSubpopulation = params.getsSubpopulation();
		String sLocus = params.getsLocus();
				
	 	boolean bAllvars = colVarIds==null;
			
		GenotypeFacade.snpQueryMode mode=null;
			
				
		String msgbox = "";
		if(poslist==null)
			msgbox="SEARCHING: in chromosome " + sChr + " [" + lStart +  "-" + lEnd +  "]" ;
		else 
			msgbox="SEARCHING: in chromosome " + sChr;
			
				Set setVarieties = null; 
				
				if(colVarIds==null) {
					mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
				} else {
					setVarieties = new HashSet(colVarIds);
					mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
				}
				if( sSubpopulation!=null && !sSubpopulation.isEmpty()) {
					mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
					setVarieties = listitemsdao.getGermplasmBySubpopulation( sSubpopulation );
				}

				
				List listSNPs = new java.util.ArrayList();

				String genename = "";
				if(sLocus!=null) genename = sLocus.toUpperCase();
				
				AppContext.startTimer();

				if( !genename.isEmpty() )
				{
					Gene gene2 =  listitemsdao.getGeneFromName( genename);
					lStart = Long.valueOf(gene2.getFmin()) ;	
					lEnd =  Long.valueOf(gene2.getFmax());	
					//selectChr.setSelectedIndex( gene2.getChr()-1 );
					//sChr =
					sChr = gene2.getChr().toString();
				}
				
				Set snpposlist = null;
				int chrlen = listitemsdao.getFeatureLength( sChr );
				
				if(lEnd> chrlen  ||lStart> chrlen)
				{
					throw new Exception("Positions should be less than length");
				} 
				if(lEnd<1  || lStart<1)
				{
					throw new Exception("Positions should be positive integer");
				}   				
				if(lEnd<lStart)
				{
					throw new Exception("End should be greater than or equal to start");
				}  

				// if length > maxlengthUni, change to core
				
				// if length > maxlengthCore, not allowed

				
				int maxlength = -1;
				String limitmsg = "";
				if(bAllvars && !bCoreonly) {
					maxlength = AppContext.getMaxlengthUni();
					limitmsg = "Limit to " + (maxlength/1000) + " kbp range for All Snps, or " + AppContext.getMaxlengthCore()/1000 + " kbp for Core Snps, All Varieties query.";
				} else if(bAllvars && bCoreonly ) {
					maxlength = AppContext.getMaxlengthCore();
					limitmsg = "Limit to " + (maxlength/1000) + " kbp range for Core Snps, all varieties query";
				} else
				{
					maxlength = AppContext.getMaxlengthPairwise();
					limitmsg = "Limit to " + (maxlength/1000) + " kbp range for Pairwise variety query";
				}
				
				long querylength = lEnd-lStart; 
				//if(!checkboxCoreSNP.isChecked() && querylength > maxlength )
				if( querylength > maxlength )
				{
					throw new Exception(limitmsg);
				}   
		
				
				
				String chr= sChr;
				if(sLocus!=null && !sLocus.isEmpty())
					msgbox="Searching within GENE " + sLocus.toUpperCase()  + " " + chr + " [" + lStart + ".." + lEnd + "] ";
//					else if(listboxMySNPList.getSelectedIndex()>0)
//						msgbox.setValue("SEARCHING: in myList " + listboxMySNPList.getSelectedItem().getLabel()  );
				else
					msgbox="SEARCHING: in chromosome " + chr + " [" + lStart +  "-" + lEnd +  "]" ;
					
					
				
			List  newpagelist = new ArrayList();
			VariantStringData variantssnps = null;
			if(params.isbSNP()) {
				
				if(params.isbCoreonly()) {
					if(params.getColVarIds()!=null && !params.getColVarIds().isEmpty()) {
						if(params.getPoslist()!=null && !params.getPoslist().isEmpty()) {
							variantssnps =  getCoreSNPService().getVariantString(params, params.getColVarIds(), params.getsChr(), params.getPoslist());
						}
						else {
							variantssnps = getCoreSNPService().getVariantString(params, params.getColVarIds(), params.getsChr(), params.getlStart(), params.getlEnd()) ;
						}
					} else {
						if(params.getPoslist()!=null && !params.getPoslist().isEmpty()) {
							variantssnps =   getCoreSNPService().getVariantString(params, params.getsChr(), params.getPoslist());
						}
						else {
							variantssnps =  getCoreSNPService().getVariantString(params, params.getsChr(), params.getlStart(), params.getlEnd());
						}
					}
				} else {
					if(params.getColVarIds()!=null && !params.getColVarIds().isEmpty()) {
						if(params.getPoslist()!=null && !params.getPoslist().isEmpty()) {
							variantssnps =   getAllSNPService().getVariantString(params, params.getColVarIds(), params.getsChr(), params.getPoslist()) ;
						}
						else {
							variantssnps =   getAllSNPService().getVariantString(params, params.getColVarIds(), params.getsChr(), params.getlStart(), params.getlEnd()) ;
						}
					} else {
						if(params.getPoslist()!=null && !params.getPoslist().isEmpty()) {
							variantssnps =  getAllSNPService().getVariantString(params, params.getsChr(), params.getPoslist());
						}
						else {
							variantssnps =   getAllSNPService().getVariantString(params, params.getsChr(), params.getlStart(), params.getlEnd()) ;
						}
					}
				}
			}
			
			
			
			VariantStringData variantsindels = null;
			if(params.isbIndel()) {
					if(params.getColVarIds()!=null && !params.getColVarIds().isEmpty()) {
						if(params.getPoslist()!=null && !params.getPoslist().isEmpty()) {
							variantsindels= getIndelsService().getVariantString(params, params.getColVarIds(), params.getsChr(), params.getPoslist()) ;
						}
						else {
							variantsindels=  getIndelsService().getVariantString(params, params.getColVarIds(), params.getsChr(), params.getlStart(), params.getlEnd()) ;
						}
					} else {
						if(params.getPoslist()!=null && !params.getPoslist().isEmpty()) {
							variantsindels=  getIndelsService().getVariantString(params, params.getsChr(), params.getPoslist()) ;
						}
						else {
							variantsindels=  getIndelsService().getVariantString(params,  params.getsChr(), params.getlStart(), params.getlEnd()) ;
						}
					}
			}
			
			VariantStringData variantMerged = new VariantStringData();
			variantMerged.setSnpstringdata( (VariantSnpsStringData)variantssnps);
			variantMerged.setIndelstringdata( (VariantIndelStringData)variantsindels );
			
			variantMerged.setMessage(msgbox + variantMerged.getMessage());
			table.setVariantStringData( variantMerged );
			table.setMessage(variantMerged.getMessage());
			
			return table;
			
//			
//			// merge
//			VariantStringData variantsmerged = null;
//			if(params.isbSNP() && params.isbIndel()) {
//				
//				//merge listVariants
//				List<SnpsStringAllvars> listMergedVariants = new ArrayList();
//				
//				// varwithsnps
//				Set hasSnps = new HashSet();
//				Iterator<SnpsStringAllvars> itSnpString = variantssnps.getListVariantsString().iterator();
//				Map<BigDecimal, SnpsStringAllvars> mapVar2SnpStringAllvars = new HashMap();
//				while(itSnpString.hasNext()) {
//					SnpsStringAllvars snpstring = itSnpString.next();
//					mapVar2SnpStringAllvars.put(snpstring.getVar() , snpstring);
//					hasSnps.add(snpstring.getVar());
//				}
//				
//				// varswithindels
//				Set hasIndels = new HashSet();
//				itSnpString = variantsindels.getListVariantsString().iterator();
//				while(itSnpString.hasNext()) {
//					IndelsStringAllvars indelstring = (IndelsStringAllvars)itSnpString.next();
//					BigDecimal var = indelstring.getVar();
//					indelstring.delegateSnpString( mapVar2SnpStringAllvars.get(var) );
//					
//					hasIndels.add(var);
//					listMergedVariants.add(indelstring);
//				}
//				
//				// has snps only
//				Set hasSnpsOnly = new HashSet( hasSnps );
//				hasSnpsOnly.removeAll( hasIndels );
//				itSnpString = hasSnpsOnly.iterator();
//				while(itSnpString.hasNext()) {
//					listMergedVariants.add( mapVar2SnpStringAllvars.get(itSnpString.next() )  );
//				}			
//				
//			 	Collections.sort(listMergedVariants, new SnpsStringAllvarsImplSorter());
//			 	
//
//				// merge pos
//				Set setMergedAllvarsPos = new TreeSet(new SnpsAllvarsPosComparator());
//				setMergedAllvarsPos.addAll( variantssnps.getListPos() );
//				setMergedAllvarsPos.addAll( variantsindels.getListPos() );
//				List<SnpsAllvarsPos> listPos = new ArrayList();
//				listPos.addAll(setMergedAllvarsPos);
//
//				
//				// update mismatches and order
//				Map<BigDecimal, BigDecimal>  mapMergedVar2Mismatch = new HashMap();
//				Map<BigDecimal, Integer> mapMergedVar2Order = new HashMap();
//				Iterator<SnpsStringAllvars> itSnpstring = listMergedVariants.iterator();
//				int ordercnt = 0;
//				while(itSnpstring.hasNext()) {
//					SnpsStringAllvars snpstring = itSnpstring.next();
//					mapMergedVar2Mismatch.put(snpstring.getVar() , snpstring.getMismatch() );
//					mapMergedVar2Order.put(snpstring.getVar() , ordercnt);
//					ordercnt++;
//				}
//				 
//				 Map<BigDecimal, Integer> mapSNPPos2SnpIdx = new HashMap();
//				 Iterator<SnpsAllvarsPos> itSNPosList = listPos.iterator();
//				 int idxcnt = 0;
//				 while(itSNPosList.hasNext()) {
//					 SnpsAllvarsPos snppos = itSNPosList.next();
//					 mapSNPPos2SnpIdx.put(snppos.getPos(), idxcnt);
//					 idxcnt++;
//				 }
//				 
//				Map<Integer,Integer> mapMergedIdx2SnpIdx = new HashMap();
//				// merge column indexing
//				 Map<Integer, BigDecimal> mapMergedIndex2Pos = new HashMap(); 
//				 Iterator<SnpsAllvarsPos> itSnppos = listPos.iterator();
//				 idxcnt=0;
//				 while(itSnppos.hasNext()) {
//					 SnpsAllvarsPos posallvars = itSnppos.next();
//					 mapMergedIndex2Pos.put( idxcnt, posallvars.getPos() );
//					 if(mapSNPPos2SnpIdx.containsKey( posallvars.getPos()))
//						 mapMergedIdx2SnpIdx.put(idxcnt, mapSNPPos2SnpIdx.get( posallvars.getPos() ) );
//					 idxcnt++;
//				 }
//							
//				 variantsmerged = new  VariantStringData(mapMergedVar2Mismatch, mapMergedVar2Order,
//						 listPos, mapMergedIndex2Pos, listMergedVariants);
//				 variantsmerged.setMapMergedIdx2SnpIdx(mapMergedIdx2SnpIdx);
//				 
//				 msgbox += " ... RESULT: " +  variantsmerged.getListVariantsString().size() + " varieties x " + variantssnps.getListPos().size() + 
//							" SNP, " + variantsindels.getListPos().size() + " INDEL positions";
//				
//			} else if(params.isbSNP()) {
//				 variantsmerged = variantssnps;
//				 msgbox += " ... RESULT: " +  variantsmerged.getListVariantsString().size() + " varieties x " + variantssnps.getListPos().size() + 
//							" SNP positions";
//				
//			} else if(params.isbIndel()) {
//				variantsmerged = variantsindels;
//				 msgbox += " ... RESULT: " +  variantsmerged.getListVariantsString().size() + " varieties x "  + variantsindels.getListPos().size() + " INDEL positions";
//			}			
//			

					
		}

/*
	@Override
	public VariantTable createGenotypeTable(VariantTable vartable, VariantStringData varstringdata ) //, boolean updateHeaders, String filename, String delimiter, String header, boolean doDownload, Integer chromosome) //, int firstRow, int numRows)
	{

		List<SnpsStringAllvars> listSNPs = varstringdata.getListVariantsString();
		
		//boolean fetchMismatchOnly =  AppContext.isSNPAllvarsFetchMismatchOnly();  //listSNPs contains only NULL and referfence mismatches
		
		
		List<SnpsAllvarsPos> snpsposlist = varstringdata.getListPos();
			
		
		Long posarr[] = new Long[snpsposlist.size()]; 
		String refnuc[] = new String[snpsposlist.size()];
		Iterator<SnpsAllvarsPos> itPos = snpsposlist.iterator();
		int poscount = 0;
		while(itPos.hasNext()) {
			SnpsAllvarsPos posnuc=itPos.next();
			posarr[poscount] = posnuc.getPos().longValue();
			refnuc[poscount] = posnuc.getRefnuc();
			poscount++;
		}
		vartable.setPosition(posarr);
		vartable.setReference(refnuc);
			
		listitemsdao = (ListItemsDAO)AppContext.checkBean(listitemsdao, "ListItemsDAO");
		Map<BigDecimal,Variety> mapVarid2Variety = listitemsdao.getMapId2Variety();

		vartable.setAlleles(mapVarid2Variety, snpsposlist, listSNPs );
		
//			List listTable= listSNPs;
//			String varnames[] = new String[listTable.size()];
//			Long varids[] = new Long[listTable.size()];
//			Double varmismatch[] = new Double[listTable.size()];
//			String allelestring[][] = new String[listTable.size()][snpsposlist.size()];
//		
//			Iterator<SnpsStringAllvars> itSnpstring = listTable.iterator();
//			int varcount = 0;
//			while(itSnpstring.hasNext()) {
//				SnpsStringAllvars snpstr = itSnpstring.next();
//				
//				varmismatch[varcount]=snpstr.getMismatch().doubleValue();
//				varnames[varcount]=mapVarid2Variety.get( snpstr.getVar() ).getName();
//				varids[varcount]= snpstr.getVar().longValue();
//				
//				allelestring[varcount] = createVarietyString(snpstr,varstringdata);
//				varcount++;
//			}
			
			vartable.setVarname(varnames);
			vartable.setVarid(varids);
			vartable.setVaralleles(allelestring);
			vartable.setVarmismatch(varmismatch);
			
			return vartable;
		
	}


	private String[] createVarietyString(SnpsStringAllvars snpstr, VariantStringData varstringdata) {
		
		int cols = varstringdata.getListPos().size();
		Map<Integer,BigDecimal> mapMergedIdx2Pos = varstringdata.getMapIdx2Pos();
		Map<Integer,BigDecimal> mapIndelIdx2Pos  =varstringdata.getMapIdx2Pos();
		Map<BigDecimal, IndelsAllvarsPos>	mapIndelId2Indels = varstringdata.getMapIndelId2Indel();
		Map<Integer, Integer> mapMergedIdx2SnpIdx = varstringdata.getMapMergedIdx2SnpIdx();

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
							
							buff.append( getIndelAlleleString(indelpos) );
							
							BigDecimal alleleid2 = indelstring.getAllele2( pos );
							indelpos = mapIndelId2Indels.get(alleleid2);
							
							if(alleleid2!=null && !alleleid2.equals(alleleid)) {
								alleleid=alleleid2;
								indelpos = mapIndelId2Indels.get(alleleid);
								if(indelpos!=null) {
									buff.append("/").append( getIndelAlleleString(indelpos) );
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
										element = mapPosidx2allele2.get(j);
										if(element!='0' && element!='.' && element!=' ' && element!='*') 
											buff.append("/").append(element);
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
								element = mapPosidx2allele2.get(j);
								if(element!='0' && element!='.' && element!=' ' && element!='*') 
									buff.append("/").append(  element );
							}
						}
					}
					
				}
				allelesstr[iCols] = buff.toString();
			}
		return allelesstr;
	}

	

	private String getIndelAlleleString(IndelsAllvarsPos indelpos) {

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
	
	
	

}
