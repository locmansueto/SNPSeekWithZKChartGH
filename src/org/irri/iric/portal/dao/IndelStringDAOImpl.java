package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.domain.IndelsAllvars;
import org.irri.iric.portal.domain.IndelsAllvarsPos;
import org.irri.iric.portal.domain.IndelsStringAllvars;
import org.irri.iric.portal.domain.IndelsStringAllvarsImpl;
import org.irri.iric.portal.domain.SnpsAllvars;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsAllvarsPosImpl;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.domain.SnpsStringAllvarsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;


@Scope("value= prototype")
public class IndelStringDAOImpl implements IndelStringDAO {

	
	private Map<BigDecimal, IndelsAllvarsPos> mapAlleleId2Indel;
	private Map<Integer, BigDecimal> mapIdx2Pos;
	private Map<BigDecimal, Integer> mapVariety2Order;
	private Map<BigDecimal, Double> mapVariety2Mismatch;
	private Set sortedPos;
	private List<SnpsAllvarsPos> listPos;
	private List<SnpsStringAllvars> listResult;
	
	private IndelsAllvarsPosDAO indelsAllvarsPosDAO;
	private IndelsAllvarsDAO indelsAllvarsDAO;
	private boolean isMismatchOnly=false;
	
	public IndelStringDAOImpl(IndelsAllvarsPosDAO indelsAllvarsPosDAO,
			IndelsAllvarsDAO indelsAllvarsDAO, boolean isMismatchOnly) {
		super();
		this.indelsAllvarsPosDAO = indelsAllvarsPosDAO;
		this.indelsAllvarsDAO = indelsAllvarsDAO;
		this.isMismatchOnly = isMismatchOnly;
	}

	private Map readIndelsAllvars(Integer chr, BigDecimal start, BigDecimal end, Collection varList, Collection posList) {
		
		//private Map<BigDecimal, Map<BigDecimal, IndelsAllvars>> mapVar2AlleleCall;
		Map<BigDecimal, Map<BigDecimal, IndelsAllvars>> mapVar2AlleleCall;
		
		
		AppContext.debug("getting indelpos in " + chr + " [" + start + "-" + end + "]");

		
		Set setSnps=null;
		if(start!=null && end!=null) {
			mapAlleleId2Indel = indelsAllvarsPosDAO.getMapIndelId2Indels(chr.toString(), start.intValue(), end.intValue());
			if(varList!=null && varList.size()<10)
			{
				AppContext.debug("getting indelcallsbyvar in " + chr + " [" + start + "-" + end + "]");
				setSnps = indelsAllvarsDAO.findIndelAllvarsByVarChrPosBetween(varList, chr, start, end);
			} else {
				AppContext.debug("getting indelcalls in " + chr + " [" + start + "-" + end + "]");
				setSnps = indelsAllvarsDAO.findIndelAllvarsByChrPosBetween( chr, start, end);
			}
		} else if(posList!=null) {
			mapAlleleId2Indel = indelsAllvarsPosDAO.getMapIndelId2Indels(chr.toString(), posList);
			if(varList!=null && varList.size()<10)
			{
				AppContext.debug("getting indelcallsbyvar in " + chr + " [" + start + "-" + end + "]");
				setSnps = indelsAllvarsDAO.findIndelAllvarsByVarChrPosIn(varList, chr, posList);
			} else {
				AppContext.debug("getting indelcalls in " + chr + " [" + start + "-" + end + "]");
				setSnps = indelsAllvarsDAO.findIndelAllvarsByChrPosIn( chr,  posList);
			}
			
		}
		
		
		if(varList!=null) AppContext.debug("limiting to " + varList.size() + " varieties"); 
		
		if(setSnps==null)
			AppContext.logger("setSnps==null");
		else 
			AppContext.logger("setSnps.size = " + setSnps.size());
		
		if(mapAlleleId2Indel==null)
			AppContext.logger("mapAlleleId2Indel==null");
		else
			AppContext.logger("mapAlleleId2Indel.size = " + mapAlleleId2Indel.size());
			
		
		
		Map<BigDecimal, Integer> mapVar2MismatchCount = new HashMap();
		
		mapVar2AlleleCall = new HashMap();
		sortedPos = new TreeSet();
		Iterator itIndelcalls = setSnps.iterator();
		while(itIndelcalls.hasNext()) {
			IndelsAllvars indelcall = (IndelsAllvars)itIndelcalls.next();
			
			if(varList!=null && !varList.contains(indelcall.getVar())) continue;
			//if(posList!=null && !posList.contains(indelcall.getPos())) continue;
			
			Map<BigDecimal, IndelsAllvars> var2calls = mapVar2AlleleCall.get(indelcall.getVar());
			if(var2calls==null) {
				var2calls = new TreeMap();
				mapVar2AlleleCall.put(indelcall.getVar(), var2calls);
			}
			var2calls.put( indelcall.getPos() , indelcall );
			
			IndelsAllvarsPos indelalelle =  mapAlleleId2Indel.get( indelcall.getAllele1() ); 
			int indelmismatch  = indelalelle.getDellength();
			if(indelmismatch==0 && indelalelle.getInsString()!=null) indelmismatch = indelalelle.getInsString().length();
			
			int indelmismatch2 = 0;
			if(indelcall.getAllele2()!=null) {
				indelalelle =  mapAlleleId2Indel.get( indelcall.getAllele2() );
				indelmismatch2  = indelalelle.getDellength();
				if(indelmismatch2==0 && indelalelle.getInsString()!=null) indelmismatch2 = indelalelle.getInsString().length();
			}
			
			Integer prevcount = mapVar2MismatchCount.get(indelcall.getVar());
			if(prevcount==null) 
				mapVar2MismatchCount.put(indelcall.getVar(), Integer.valueOf( indelmismatch + indelmismatch2 ));
			else
				mapVar2MismatchCount.put(indelcall.getVar(), prevcount + indelmismatch + indelmismatch2);
			
			sortedPos.add(indelcall.getPos());
		}
		mapIdx2Pos = new TreeMap();
		Iterator<BigDecimal> itIdx = sortedPos.iterator();
		int idxcount=0;
		
		listPos = new ArrayList();
		while(itIdx.hasNext()) {
			BigDecimal pos =  itIdx.next();
			mapIdx2Pos.put(idxcount  , pos);
			listPos.add(new SnpsAllvarsPosImpl(pos, ""));
			idxcount++;
			
		}
		
		
		
		mapVariety2Mismatch = new HashMap();
		Set sortedVarieties = new TreeSet(new IndelsStringAllvarsImplSorter());
		Iterator<BigDecimal> itVar = mapVar2AlleleCall.keySet().iterator();
		while(itVar.hasNext()) {
			BigDecimal var = itVar.next();
			
			if(isMismatchOnly && mapVar2MismatchCount.get(var)==0) continue;
			
			Map<BigDecimal, IndelsAllvars> mapPos2Indelallvars=  mapVar2AlleleCall.get(var);
			sortedVarieties.add( new IndelsStringAllvarsImpl(var, mapPos2Indelallvars, BigDecimal.valueOf(mapVar2MismatchCount.get(var)), Long.valueOf(chr) ) );
			
			mapVariety2Mismatch.put(var, Double.valueOf(mapVar2MismatchCount.get(var)));
		}
		
		
		// sort varieties by sumof lengths of ins and dels
		listResult = new ArrayList();
		// sort included varieties
		mapVariety2Order = new HashMap();
		Map mapVarId2IndelsStringAllvars  = new LinkedHashMap();
		int ordercount = 0;
		Iterator itSorVars =  sortedVarieties.iterator();
		while(itSorVars.hasNext()) {
			IndelsStringAllvars snpstrvar = (IndelsStringAllvars)itSorVars.next();
			listResult.add( snpstrvar );
			mapVariety2Order.put(snpstrvar.getVar() ,ordercount);
			mapVarId2IndelsStringAllvars.put(snpstrvar.getVar() , snpstrvar);
			ordercount++;
		}

		return mapVarId2IndelsStringAllvars;
	}
	
	/**
	 * Sorts variety by mismatch desc, subpopulation, then country, then id
	 * Used in Mismatch ordering for the same number of mismatch,
	 * assuming variety from same subpopulation, then country will be closer relative than random 
	 * @author lmansueto
	 *
	 */
	class  IndelsStringAllvarsImplSorter implements Comparator {
		@Override
		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub
			IndelsStringAllvarsImpl s1 = (IndelsStringAllvarsImpl)o1; 
			IndelsStringAllvarsImpl s2 = (IndelsStringAllvarsImpl)o2;
			int ret = -s1.getMismatch().compareTo(s2.getMismatch());
			if(ret==0) {
				return s1.getVar().compareTo( s2.getVar());
				/*
				Variety v1 =varietyfacade.getMapId2Variety().get(s1.getVar());
				Variety v2 =varietyfacade.getMapId2Variety().get(s2.getVar());
				if(v1.getSubpopulation()!=null && v2.getSubpopulation()!=null)
				{
					ret=v1.getSubpopulation().compareTo(v2.getSubpopulation());
					if( ret==0 ) {
						if(v1.getCountry()!=null && v2.getCountry()!=null) {
							ret = v1.getCountry().compareTo(v2.getCountry());
							if(ret==0) return v1.getVarietyId().compareTo(v2.getVarietyId());
							else return ret;
						}
					} else return ret;
				} else if(v1.getCountry()!=null && v2.getCountry()!=null) {
						ret = v1.getCountry().compareTo(v2.getCountry());
						if(ret==0) return v1.getVarietyId().compareTo(v2.getVarietyId());
						else return ret;
				} return v1.getVarietyId().compareTo(v2.getVarietyId());
				*/
				
			} else return ret;
		}
	}
	
	
	// use positions instead on index
	
	@Override
	public Map readSNPString(int chr, int startIdx, int endIdx) {
		// TODO Auto-generated method stub
		return readIndelsAllvars(Integer.valueOf(chr), BigDecimal.valueOf( startIdx) ,
				BigDecimal.valueOf(endIdx), null, null);
	}

	@Override
	public Map readSNPString(int chr, int[] posIdxs) {
		// TODO Auto-generated method stub
		List<BigDecimal> listpos = new ArrayList();
		for(int i=0; i<posIdxs.length; i++)
			listpos.add( BigDecimal.valueOf(posIdxs[i]));
		return readIndelsAllvars(Integer.valueOf(chr), null, null, null, listpos);
	}

	@Override
	public Map readSNPString(Set colVarids, int chr, int[] posIdxs) {
		// TODO Auto-generated method stub
		List<BigDecimal> listpos = new ArrayList();
		for(int i=0; i<posIdxs.length; i++)
			listpos.add( BigDecimal.valueOf(posIdxs[i]));
		
		return readIndelsAllvars(Integer.valueOf(chr), null, null, colVarids, listpos);
	}

	@Override
	public Map readSNPString(Set colVarids, int chr, int startIdx, int endIdx) {
		// TODO Auto-generated method stub
		return readIndelsAllvars(Integer.valueOf(chr), BigDecimal.valueOf(startIdx) ,
				 BigDecimal.valueOf(endIdx), colVarids, null);
	}
	
	
	

	public Map<BigDecimal, IndelsAllvarsPos> getMapAlleleId2Indel() {
		return mapAlleleId2Indel;
	}

	public Map<Integer, BigDecimal> getMapIdx2Pos() {
		return mapIdx2Pos;
	}

	public Map<BigDecimal, Integer> getMapVariety2Order() {
		return mapVariety2Order;
	}

	public Set getSortedPos() {
		return sortedPos;
	}

	public List<SnpsStringAllvars> getListResult() {
		return listResult;
	}

	public Map<BigDecimal, Double> getMapVariety2Mismatch() {
		return mapVariety2Mismatch;
	}

	public List<SnpsAllvarsPos> getListPos() {
		return listPos;
	}
	

	
	
	
}
