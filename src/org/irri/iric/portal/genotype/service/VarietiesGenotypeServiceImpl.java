package org.irri.iric.portal.genotype.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
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
	
	/**
	 * Reformat query results to table view 
	 * 
	 */
	@Override
	public VariantTable fillVariantTable(VariantTable table, VariantStringData origdata, GenotypeQueryParams params) throws Exception {
		
		VariantStringData variantMerged = new VariantStringData();
		
		if(origdata.hasSnp()) {
			//if(params.isbExcludeSynonymous())
			if(params.isbNonsynSnps())
				variantMerged.setSnpstringdata( origdata.getSnpstringdata().getNonsynSnps() );
			else if(params.isbNonsynPlusSpliceSnps())
				variantMerged.setSnpstringdata( origdata.getSnpstringdata().getNonsynPlusSpliceSnps() );
			else variantMerged.setSnpstringdata( origdata.getSnpstringdata() );
			
			AppContext.debug("fillVariantTable:" + variantMerged.getSnpstringdata().getListPos().size() + " SNP positions" );
			
		}
		if(origdata.hasIndel()) {
			if(params.isbAlignIndels()) {
				variantMerged.setIndelstringdata( origdata.getIndelstringdata().getAlignedIndels()  );
			} else {
				variantMerged.setIndelstringdata( origdata.getIndelstringdata());
			}
			AppContext.debug("fillVariantTable:" + variantMerged.getIndelstringdata().getListPos().size() + " Indel positions" );
		}
		
		variantMerged.merge();
		
		AppContext.debug("fillVariantTable:" + variantMerged.getListPos().size() + " positions" );
		
		table.setVariantStringData( variantMerged, params);
		table.setMessage(variantMerged.getMessage());
		return table;
	}
	
	
	@Override
	public VariantStringData compare2VariantStrings(BigDecimal var1, BigDecimal var2, GenotypeQueryParams params)  throws Exception {
		//Set colVarIds = new HashSet();
		Set colVarIds = new LinkedHashSet();
		colVarIds.add(var1);
		colVarIds.add(var2);
		params.setColVarIds(colVarIds);
		params.setsSubpopulation(null);
		
		//params.setbMismatchonly(false);
		return queryVariantStringData(params);
	}
	
	@Override
	public VariantStringData queryVariantStringData( GenotypeQueryParams params) throws Exception {
		// TODO Auto-generated method stub
			
		Collection colVarIds =params.getColVarIds();
		
		
		String sChr = params.getsChr();
		Long lStart = params.getlStart();
		Long lEnd = params.getlEnd();
		//boolean bSNP = params.isbSNP();
		//boolean bIndel = params.isbIndel();
		boolean bCoreonly = params.isbCoreonly();
		//boolean bMismatchonly = params.isbMismatchonly();
		Collection poslist = params.getPoslist();
		String sSubpopulation = params.getsSubpopulation();
		String sLocus = params.getsLocus();
				
	 	boolean bAllvars = colVarIds==null;
			
		GenotypeFacade.snpQueryMode mode=null;
			
		String genename = "";
		if(sLocus!=null) genename = sLocus.toUpperCase();
				
		String msgbox = "";
		if(poslist==null) {

			if( !genename.isEmpty() )
			{
				Gene gene2 =  listitemsdao.getGeneFromName( genename);
				lStart = Long.valueOf(gene2.getFmin()) ;	
				lEnd =  Long.valueOf(gene2.getFmax());	
				//selectChr.setSelectedIndex( gene2.getChr()-1 );
				//sChr =
				sChr = gene2.getChr().toString();
				
				msgbox="SEARCHING: in locus " + genename;
				
				params.setlStart(lStart);
				params.setlEnd(lEnd);
				params.setsChr(sChr);
			}
			else msgbox="SEARCHING: in chromosome " + sChr + " [" + lStart +  "-" + lEnd +  "]" ;
			
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
			
		}
		else 
			msgbox="SEARCHING: in chromosome " + sChr + ", " + poslist.size() + " SNPs";
			
		Set setVarieties = null; 
		if(colVarIds==null) {
			mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
		} else {
			setVarieties = new HashSet(colVarIds);
			mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
			msgbox += ", " + setVarieties.size() + " varieties";
		}
		if( sSubpopulation!=null && !sSubpopulation.isEmpty()) {
			mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
			setVarieties = listitemsdao.getGermplasmBySubpopulation( sSubpopulation );
			msgbox += ", "+ sSubpopulation + " subpopulation";
		}

				
				AppContext.startTimer();


				// if length > maxlengthUni, change to core
				
				// if length > maxlengthCore, not allowed

				
				
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
					
					//if(params.isbAlignIndels()) {
					//	// expand indel positions to align
					//	variantsindels = ((VariantIndelStringData)variantsindels).getAlignedIndels();
					//}
			}
			
			VariantStringData variantMerged = new VariantStringData();
			
			variantMerged.setSnpstringdata( (VariantSnpsStringData)variantssnps);
			variantMerged.setIndelstringdata( (VariantIndelStringData)variantsindels );

			
			//if(variantMerged.getListPos()==null) throw new RuntimeException("variantMerged.getListPos()==null");
			//if(variantssnps!=null && variantssnps.getListPos()==null) throw new RuntimeException("variantssnps.getListPos()==null");
			//if(variantsindels!=null && variantsindels.getListPos()==null) throw new RuntimeException("variantsindels.getListPos()==null");
			//if(variantMerged.getListVariantsString()==null)  throw new RuntimeException("variantMerged.getListVariantsString()==null");
			variantMerged.merge();
			variantMerged.setMessage(msgbox + variantMerged.getMessage());
		
			
			return variantMerged;
					
		}
	

}
