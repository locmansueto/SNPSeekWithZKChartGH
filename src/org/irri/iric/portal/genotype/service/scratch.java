package org.irri.iric.portal.genotype.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.IndelStringDAOImpl;
import org.irri.iric.portal.dao.IndelsAllvarsDAO;
import org.irri.iric.portal.dao.IndelsAllvarsPosDAO;
import org.irri.iric.portal.dao.SnpsAllvarsPosDAO;
import org.irri.iric.portal.dao.SnpsHeteroAllvarsDAO;
import org.irri.iric.portal.dao.SnpsInExonDAO;
import org.irri.iric.portal.dao.SnpsNonsynAllvarsDAO;
import org.irri.iric.portal.dao.SnpsStringDAO;
import org.irri.iric.portal.domain.IndelsAllvars;
import org.irri.iric.portal.domain.IndelsAllvarsPos;
import org.irri.iric.portal.domain.IndelsStringAllvars;
import org.irri.iric.portal.domain.IndelsStringAllvarsImpl;
import org.irri.iric.portal.domain.Snp;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsAllvarsPosImpl;
import org.irri.iric.portal.domain.SnpsHeteroAllele2;
import org.irri.iric.portal.domain.SnpsNonsynAllele;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.domain.SnpsStringAllvarsImpl;
import org.irri.iric.portal.flatfile.dao.SnpcoreRefposindexDAO;
import org.irri.iric.portal.flatfile.domain.VSnpRefposindex;
import org.irri.iric.portal.genotype.service.GenotypeFacadeChadoImpl.SNPsStringData;
import org.irri.iric.portal.genotype.service.GenotypeFacadeChadoImpl.SnpsAllvarsPosComparator;
import org.irri.iric.portal.genotype.service.GenotypeFacadeChadoImpl.SnpsStringAllvarsImplSorter;

public class scratch {

//	private List<SnpsStringAllvars> getSNPsString(Integer chr, BigDecimal start,
//			BigDecimal end, Set setPositions) { 
//		
//		
//		// initialize state/session variables
//
//		if(this.includeSNP) {
//			//listVariants.addAll( _getSNPsString( chr,  start,  end, setPositions));
//			listVariantsSnps = _getSNPsString( chr,  start,  end, setPositions);
//		
//			// fill 
//			mapPos2Allele
//			mapPos2Idx
//		}
//		
//		if(this.includeIndel) {
//
//			indelsAllvarsPosDAO
//			indelsAllvarsDAO
//			
//			IndelStringDAOImpl indelstringdao = new IndelStringDAOImpl(indelsAllvarsPosDAO, indelsAllvarsDAO);
//			
//			Map<BigDecimal, IndelsStringAllvars> mapVar2Indelstring=null;
//			
//			// cases  
//			// setPositions (poslist, null)
//			// limitVarIds  (varids, all)
//			indelstringdao.readSNPString(limitVarIds, chr, start.intValue(), end.intValue(), indx);
//
//
//			// return values
//			listVariantsIndels = indelstringdao.getListResult();
//			mapIndelVariety2Mismatch = indelstringdao.getMapVariety2Mismatch();
//			mapIndelVariety2Order = indelstringdao.getMapVariety2Order();
//			listIndelsnpsposlist = indelstringdao.getListPos();
//			this.mapIndelId2Indel =  indelstringdao.getMapAlleleId2Indel();
//			this.mapIndelIdx2Pos = indelstringdao.getMapIdx2Pos();
//			
//			//fill 
//			mapPos2Allele
//		}
//		
//		// merge positions, 
//		// fill
//		 this.mapVariety2Mismatch = mapMergedVar2Mismatch;
//		 this.mapVariety2Order = mapMergedVar2Order;
//		this.snpsposlist
//		 this.mapMergedIdx2SnpIdx
//		this.mapMergedIdx2Pos
//		listMergedVariants
//		
//		return List<SnpsStringAllvars> listMergedVariants
//
//				
//	}
//	
//	
//	
//	
//	
//	private List<SnpsStringAllvars> _getSNPsString(Integer chr, BigDecimal start,
//			BigDecimal end, Set setPositions) { //, boolean exactMismatch, int firstRow, int maxRows) {
//		// TODO Auto-generated method stub
//		
//			// SNPsStringData snpstrdata = getSNPsStringData( chr,  start,  end,  setPositions);
//		
//			// use VSnpRefposindexDAO
//		
//		// cases
//		// setPositions (poslist, null)
//		// limitVars (varids, all)
//		// isCore (true, false)
//		snpsposlist  = snpstringallvarsposService.getSNPs(chr.toString(),  listpos, start.intValue(),  end.intValue(), SnpcoreRefposindexDAO.TYPE_3KCORESNP/TYPE_3KALLSNP )
//
//				
//		//get
//		//create mapSnpid2TableIdx
//
//				
//		
//		// get data filenames
//			//	cases: (core, all), (flatfile, hdf5), (poslist, null), varids, all)
//			// 	
//				
//				
//			// hdf5 DAOs	
//				snpstrAllsnpsAllele1AllvarsDAO = (SnpsStringDAO)AppContext.checkBean(snpstrAllsnpsAllele1AllvarsDAO, "H5SNPUniAllele1DAO");
//				snpstrAllsnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean(snpstrAllsnpsAllele2AllvarsDAO, "H5SNPUniAllele2DAO");
//				snpstrCoresnpsAllele1AllvarsDAO =  (SnpsStringDAO)AppContext.checkBean(snpstrCoresnpsAllele1AllvarsDAO, "H5SNPCoreAllele1DAO");
//				snpstrCoresnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean(snpstrCoresnpsAllele2AllvarsDAO, "H5SNPCoreAllele2DAO");
//				
//				mapVarid2Snpsstr = snpstrCoresnpsAllele1AllvarsDAO.readSNPString(limitVarIds, chr, indxs);
//				mapVarid2Snpsstr_allele2= snpstrCoresnpsAllele2AllvarsDAO.readSNPString(limitVarIds, chr,  indxs );
//				mapVarid2Snpsstr = snpstrAllsnpsAllele1AllvarsDAO.readSNPString(limitVarIds, chr, indxs);
//				mapVarid2Snpsstr_allele2= snpstrAllsnpsAllele2AllvarsDAO.readSNPString(limitVarIds, chr,  indxs );
//				
//				
//			// flatfile DAOs	
////				if(listpos!=null && !listpos.isEmpty()) {
////					if(this.limitVarIds!=null && !this.limitVarIds.isEmpty() ) {
////						
////						AppContext.resetTimer("using readSNPString1 start");
////						mapVarid2Snpsstr = readSNPString(limitVarIds, chr,  indxs , filename);
////						
////						if(datatype == SnpcoreRefposindexDAO.TYPE_3KCORESNP) {
////							mapVarid2Snpsstr_allele2= readSNPString(limitVarIds, chr,  indxs , filename_allele2);
////						}
////						else {
////							heteroSnps = snpsheteroDAO.findSnpsHeteroAllvarsChrPosIn(chr, listpos, datatype);
//							
//
//		// case nonsyn/syn					
//		if(isNonsynOnly || isColorByNonsyn) {
//			snpsnonsynDAO = (SnpsNonsynAllvarsDAO) AppContext.checkBean(snpsnonsynDAO, "SnpsNonsynAllvarsDAO");
//			snpsinexonDAO = (SnpsInExonDAO) AppContext.checkBean(snpsinexonDAO, "SnpsInExonDAO");
//			nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByChrPosIn(chr, listpos);
//			inexonSnps = snpsinexonDAO.getSnps(chr, listpos);
//			AppContext.resetTimer("to read nonsynonymous allele, inexon  from  database..");
//		}
//
//		// hetero
//		snpsheteroDAO = (SnpsHeteroAllvarsDAO)AppContext.checkBean(snpsheteroDAO, "SnpsHeteroAllvarsDAO");
//							
//		 
//							
//		SNPsStringData snpstrdata = new  SNPsStringData(strRef, mapVarid2Snpsstr, mapVarid2SnpsAllele2str, mapIdx2NonsynAlleles, setSnpInExonTableIdx);
//		
//							
//			// fill state variables
//			
//			call countVarpairMismatch()
//			
//			mapIndex2NonsynAlleles
//			mapVariety2Order
//			mapVariety2Mismatch 
//			
//			// return
//			listResult
//			
//			
//	}
//	
//	
//	
//private Map readIndelsAllvars(Integer chr, BigDecimal start, BigDecimal end, Collection varList, Collection posList) {
//		
//		//private Map<BigDecimal, Map<BigDecimal, IndelsAllvars>> mapVar2AlleleCall;
//		Map<BigDecimal, Map<BigDecimal, IndelsAllvars>> mapVar2AlleleCall;
//		
//		
//		AppContext.debug("getting indelpos in " + chr + " [" + start + "-" + end + "]");
//
//		
//		Set setSnps=null;
//		if(start!=null && end!=null) {
//			mapAlleleId2Indel = indelsAllvarsPosDAO.getMapIndelId2Indels(chr.toString(), start.intValue(), end.intValue());
//			if(varList!=null && varList.size()<10)
//			{
//				AppContext.debug("getting indelcallsbyvar in " + chr + " [" + start + "-" + end + "]");
//				setSnps = indelsAllvarsDAO.findIndelAllvarsByVarChrPosBetween(varList, chr, start, end);
//			} else {
//				AppContext.debug("getting indelcalls in " + chr + " [" + start + "-" + end + "]");
//				setSnps = indelsAllvarsDAO.findIndelAllvarsByChrPosBetween( chr, start, end);
//			}
//		} else if(posList!=null) {
//			mapAlleleId2Indel = indelsAllvarsPosDAO.getMapIndelId2Indels(chr.toString(), posList);
//			if(varList!=null && varList.size()<10)
//			{
//				AppContext.debug("getting indelcallsbyvar in " + chr + " [" + start + "-" + end + "]");
//				setSnps = indelsAllvarsDAO.findIndelAllvarsByVarChrPosIn(varList, chr, posList);
//			} else {
//				AppContext.debug("getting indelcalls in " + chr + " [" + start + "-" + end + "]");
//				setSnps = indelsAllvarsDAO.findIndelAllvarsByChrPosIn( chr,  posList);
//			}
//			
//		}
//		
//		
//		if(varList!=null) AppContext.debug("limiting to " + varList.size() + " varieties"); 
//		
//		if(setSnps==null)
//			AppContext.logger("setSnps==null");
//		else 
//			AppContext.logger("setSnps.size = " + setSnps.size());
//		
//		if(mapAlleleId2Indel==null)
//			AppContext.logger("mapAlleleId2Indel==null");
//		else
//			AppContext.logger("mapAlleleId2Indel.size = " + mapAlleleId2Indel.size());
//			
//		
//		
//		Map<BigDecimal, Integer> mapVar2MismatchCount = new HashMap();
//		
//		mapVar2AlleleCall = new HashMap();
//		sortedPos = new TreeSet();
//		Iterator itIndelcalls = setSnps.iterator();
//		while(itIndelcalls.hasNext()) {
//			IndelsAllvars indelcall = (IndelsAllvars)itIndelcalls.next();
//			
//			if(varList!=null && !varList.contains(indelcall.getVar())) continue;
//			//if(posList!=null && !posList.contains(indelcall.getPos())) continue;
//			
//			Map<BigDecimal, IndelsAllvars> var2calls = mapVar2AlleleCall.get(indelcall.getVar());
//			if(var2calls==null) {
//				var2calls = new TreeMap();
//				mapVar2AlleleCall.put(indelcall.getVar(), var2calls);
//			}
//			var2calls.put( indelcall.getPos() , indelcall );
//			
//			IndelsAllvarsPos indelalelle =  mapAlleleId2Indel.get( indelcall.getAllele1() ); 
//			int indelmismatch  = indelalelle.getDellength();
//			if(indelmismatch==0 && indelalelle.getInsString()!=null) indelmismatch = indelalelle.getInsString().length();
//			
//			int indelmismatch2 = 0;
//			if(indelcall.getAllele2()!=null) {
//				indelalelle =  mapAlleleId2Indel.get( indelcall.getAllele2() );
//				indelmismatch2  = indelalelle.getDellength();
//				if(indelmismatch2==0 && indelalelle.getInsString()!=null) indelmismatch2 = indelalelle.getInsString().length();
//			}
//			
//			Integer prevcount = mapVar2MismatchCount.get(indelcall.getVar());
//			if(prevcount==null) 
//				mapVar2MismatchCount.put(indelcall.getVar(), Integer.valueOf( indelmismatch + indelmismatch2 ));
//			else
//				mapVar2MismatchCount.put(indelcall.getVar(), prevcount + indelmismatch + indelmismatch2);
//			
//			sortedPos.add(indelcall.getPos());
//		}
//		mapIdx2Pos = new TreeMap();
//		Iterator<BigDecimal> itIdx = sortedPos.iterator();
//		int idxcount=0;
//		
//		listPos = new ArrayList();
//		while(itIdx.hasNext()) {
//			BigDecimal pos =  itIdx.next();
//			mapIdx2Pos.put(idxcount  , pos);
//			listPos.add(new SnpsAllvarsPosImpl(pos, ""));
//			idxcount++;
//			
//		}
//		
//		
//		
//		mapVariety2Mismatch = new HashMap();
//		Set sortedVarieties = new TreeSet(new IndelsStringAllvarsImplSorter());
//		Iterator<BigDecimal> itVar = mapVar2AlleleCall.keySet().iterator();
//		while(itVar.hasNext()) {
//			BigDecimal var = itVar.next();
//			Map<BigDecimal, IndelsAllvars> mapPos2Indelallvars=  mapVar2AlleleCall.get(var);
//			
//			sortedVarieties.add( new IndelsStringAllvarsImpl(var, mapPos2Indelallvars, BigDecimal.valueOf(mapVar2MismatchCount.get(var)), Long.valueOf(chr) ) );
//			
//			mapVariety2Mismatch.put(var, mapVar2MismatchCount.get(var));
//		}
//		
//		
//		// sort varieties by sumof lengths of ins and dels
//		listResult = new ArrayList();
//		// sort included varieties
//		mapVariety2Order = new HashMap();
//		Map mapVarId2IndelsStringAllvars  = new LinkedHashMap();
//		int ordercount = 0;
//		Iterator itSorVars =  sortedVarieties.iterator();
//		while(itSorVars.hasNext()) {
//			IndelsStringAllvars snpstrvar = (IndelsStringAllvars)itSorVars.next();
//			listResult.add( snpstrvar );
//			mapVariety2Order.put(snpstrvar.getVar() ,ordercount);
//			mapVarId2IndelsStringAllvars.put(snpstrvar.getVar() , snpstrvar);
//			ordercount++;
//		}
//
//		return mapVarId2IndelsStringAllvars;
//	}
//	
//	
//	
}
