package org.irri.iric.portal.genotype.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.domain.Gene;

import org.irri.iric.portal.genotype.GenotypeQueryParams;
import org.irri.iric.portal.genotype.VariantIndelStringData;
import org.irri.iric.portal.genotype.VariantSnpsStringData;
import org.irri.iric.portal.genotype.VariantStringData;
import org.irri.iric.portal.genotype.VariantStringService;
import org.irri.iric.portal.genotype.VariantTable;
import org.irri.iric.portal.genotype.VarietiesGenotypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("VarietiesGenotypeService")
public class VarietiesGenotypeSNPsIndelServiceImpl implements VarietiesGenotypeService {
	
	@Autowired
	private ListItemsDAO listitemsdao;

	@Autowired
	@Qualifier("SnpsStringService")
	VariantStringService allsnpsService;

	@Autowired
	@Qualifier("IndelService")
	//@Qualifier("IndelServiceOracle")
	VariantStringService indelsService;
	

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
			AppContext.debug("SNP fillVariantTable:" + variantMerged.getSnpstringdata().getListPos().size() + " SNP positions" );
		}
		
		if(origdata.hasIndel()) {
			if(params.isbAlignIndels()) {
				VariantIndelStringData indeldata =  origdata.getIndelstringdata().getAlignedIndels() ;
				indeldata.setMapMSU7Pos2ConvertedPos( origdata.getIndelstringdata().getMapMSU7Pos2ConvertedPos(), origdata.getIndelstringdata().getNpbContig() );
				indeldata.setMessage( origdata.getIndelstringdata().getMessage() );
				variantMerged.setIndelstringdata( indeldata );
				//variantMerged.setMapMSU7Pos2ConvertedPos(origdata.getIndelstringdata().getMapMSU7Pos2ConvertedPos());
				AppContext.debug("origdata.getIndelstringdata().getMapMSU7Pos2ConvertedPos()=" + origdata.getIndelstringdata().getMapMSU7Pos2ConvertedPos());
			} else {
				variantMerged.setIndelstringdata( origdata.getIndelstringdata());
			}
			AppContext.debug("Indel fillVariantTable:" + variantMerged.getIndelstringdata().getListPos().size() + " Indel positions" );
		}
		
		
		//variantMerged.setMapMSU7Pos2ConvertedPos(origdata.getMapMSU7Pos2ConvertedPos());
		variantMerged.merge();
		
		//AppContext.debug("fillVariantTable variantMerged.getMapMSU7Pos2ConvertedPos()=" + variantMerged.getMapMSU7Pos2ConvertedPos().size() + ": " +   variantMerged.getMapMSU7Pos2ConvertedPos() );
		//AppContext.debug("fillVariantTable variantMerged.getListPos():" + variantMerged.getListPos().size() + ": "+ variantMerged.getListPos() );
		
		table.setVariantStringData( variantMerged, params);
		
		//AppContext.debug("fillVariantTable:  table.getVariantStringData().getMapMSU7Pos2ConvertedPos()=" + table.getVariantStringData().getMapMSU7Pos2ConvertedPos() );
		
		table.setMessage(variantMerged.getMessage());
		//table.setMessage(origdata.getMessage());
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
	public List checkSNPsInChromosome(String chr, Collection posset, BigDecimal type) {
		// TODO Auto-generated method stub
		return allsnpsService.checkSNPsInChromosome( chr,  posset,  type);
	}
	
	
	@Override
	public VariantStringData queryVariantStringData( GenotypeQueryParams params) throws Exception {
		// TODO Auto-generated method stub
			
		listitemsdao =  (ListItemsDAO)AppContext.checkBean( listitemsdao,"ListItems");
	
		Collection colVarIds =params.getColVarIds();
		String sChr = params.getsChr();
		Long lStart = params.getlStart();
		Long lEnd = params.getlEnd();
		//boolean bSNP = params.isbSNP();
		//boolean bIndel = params.isbIndel();
		boolean bCoreonly = params.isbCoreonly();
		//boolean bMismatchonly = params.isbMismatchonly();
		Collection poslist = params.getPoslist();
		Collection locuslist = params.getColLoci();
		String sSubpopulation = params.getsSubpopulation();
		String sLocus = params.getsLocus();
				
	 	boolean bAllvars = colVarIds==null;
			
		String genename = "";
		if(sLocus!=null) genename = sLocus.toUpperCase();
		String msgbox = "";
		
		// validate parameters.. duplicated for the Webapp, but useful for the Web Service
		if(poslist==null && locuslist==null) {

			if( !genename.isEmpty() )
			{
				Gene gene2 =  listitemsdao.findGeneFromName(genename, params.getOrganism());
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
			
				int chrlen = listitemsdao.getFeatureLength( sChr,  params.getOrganism() ).intValue();
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
		else{
			if(poslist!=null && locuslist!=null)
				msgbox="SEARCHING: in chromosome " + sChr + " " + poslist.size() + " SNPs, " + locuslist.size() + " loci";
			else if(poslist!=null)
				msgbox="SEARCHING: in chromosome " + sChr + " " + poslist.size() + " SNPs";
			if(locuslist!=null)
				msgbox="SEARCHING: in " + locuslist.size() + " loci";
		}
			
			
		Set setVarieties = null; 
		if(colVarIds==null) {
			//mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
		} else {
			setVarieties = new HashSet(colVarIds);
			//mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
			msgbox += ", " + setVarieties.size() + " varieties";
		}
		if( sSubpopulation!=null && !sSubpopulation.isEmpty()) {
			//mode = GenotypeFacade.snpQueryMode.SNPQUERY_ALLVARIETIESPOS;
			setVarieties = listitemsdao.getGermplasmBySubpopulation( sSubpopulation );
			msgbox += ", "+ sSubpopulation + " subpopulation";
		}

		AppContext.startTimer();
		
		VariantStringData variantMerged = queryVariantStringDataNipponbare(params);
		variantMerged.setMessage(msgbox + variantMerged.getMessage());
			
		AppContext.debug("queryVariantStringData msg=" +  variantMerged.getMessage() );
		AppContext.resetTimer("queryVariantstring");
			return variantMerged;
		
					
	}
	
	/**
	 * Query  variants from Nipponbare reference
	 * @param params
	 * @return
	 * @throws Exception
	 */
	private VariantStringData queryVariantStringDataNipponbare( GenotypeQueryParams params) throws Exception {

		VariantStringData variantssnps = null;
		if(params.isbSNP()) {
			// include SNPs
			variantssnps =  getAllSNPService().getVariantString(params);
		}

		VariantStringData variantsindels = null;
		if(params.isbIndel() && !params.isSNPList() ) {
			// include Indels
			variantsindels=  getIndelsService().getVariantString(params);
		}
		
		VariantStringData variantMerged = new VariantStringData();
		variantMerged.setSnpstringdata( (VariantSnpsStringData)variantssnps);
		variantMerged.setIndelstringdata( (VariantIndelStringData)variantsindels );
		variantMerged.merge();
		
		return variantMerged;
		
	}
	
//	private VariantStringData queryVariantStringDataAllRefs( GenotypeQueryParams params) throws Exception {
//		
//		
//		
//		
//		if(!params.getOrganism().equals( AppContext.getDefaultOrganism() )) {
//			// not nipponbare coordinate. convert coordinates
//			
//			multiplerefconvertdao = (MultipleReferenceConverterDAO)AppContext.checkBean(multiplerefconvertdao, "MultipleReferenceConverterDAO");
//			
//			MultiReferenceLocus locusQueried = new MultiReferenceLocusImpl(params.getOrganism(), params.getsChr(), params.getlStart(), params.getlEnd(), 1L);
//			
//			MultiReferenceLocus locusNipponbare = multiplerefconvertdao.convertLocus( locusQueried ,  AppContext.getDefaultOrganism(),  null); 
//			MultiReferenceLocus origMultiReferenceLocus =  params.setNewPosition(locusNipponbare);
//			
//			VariantStringData variantstringdataNPB =  queryVariantStringDataNipponbare(params);
//			
//			String toContig = null;
//			if(params.isLimitToQueryContig()) {
//				toContig = locusQueried.getContig();
//			}
//			
//			
//			
//			variantstringdataNPB.setMessage( variantstringdataNPB.getMessage() + "\nQuery " + locusQueried + " aligned with " + locusNipponbare);
//			
//			AppContext.debug( variantstringdataNPB.toString() );
//			AppContext.debug( variantstringdataNPB.getMessage() );
//			
//			VariantStringData convertedSNPPositions = multiplerefconvertdao.convertReferencePositions(variantstringdataNPB, locusNipponbare, locusQueried, toContig);
//			params.setNewPosition( origMultiReferenceLocus );
//			
//			AppContext.debug( convertedSNPPositions.toString() );
//			AppContext.debug("convertedSNPPositions msg=" +  convertedSNPPositions.getMessage() );
//			
//			return  convertedSNPPositions;
//			
//			
//			
//			//return variantstringdataNPB;
//			
//		} else {
//			return queryVariantStringDataNipponbare(params);
//		}		
//		
//	}
	
	
}
