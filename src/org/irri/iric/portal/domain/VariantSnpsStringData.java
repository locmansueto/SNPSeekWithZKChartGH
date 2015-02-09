package org.irri.iric.portal.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class VariantSnpsStringData extends VariantStringData {

	private String  strRef;
	private Map<BigDecimal, Map<Integer,Character>> mapVarid2SnpsAllele2str;
	private Map<BigDecimal, Set<Character>> mapIdx2NonsynAlleles;
	private Set<Integer> setSnpInExonTableIdx;
	private Map<Integer,Integer> mapMergedIdx2SnpIdx;
	
	
	public VariantSnpsStringData(Map<BigDecimal, Double> mapVariety2Mismatch,
			Map<BigDecimal, Integer> mapVariety2Order,
			List<SnpsAllvarsPos> listPos, Map<Integer, BigDecimal> mapIdx2Pos,
			List<SnpsStringAllvars> listVariantsString, Map<BigDecimal,Set<String>> mapPos2Alleleset) {
		
		this(mapVariety2Mismatch, mapVariety2Order, listPos, mapIdx2Pos, listVariantsString, null, null, null, null, null, mapPos2Alleleset);
	}
	
	
	public VariantSnpsStringData(Map<BigDecimal, Double> mapVariety2Mismatch,
			Map<BigDecimal, Integer> mapVariety2Order,
			List<SnpsAllvarsPos> listPos, Map<Integer, BigDecimal> mapIdx2Pos,
			List<SnpsStringAllvars> listVariantsString,
			String strRef,
			Map<BigDecimal, Map<Integer, Character>> mapVarid2SnpsAllele2str,
			Map<BigDecimal, Set<Character>> mapIdx2NonsynAlleles,
			Set<Integer> setSnpInExonTableIdx,
			Map<Integer, Integer> mapMergedIdx2SnpIdx, Map<BigDecimal,Set<String>> mapPos2Alleleset) {
		super();

		this.mapVariety2Mismatch = mapVariety2Mismatch;
		this.mapVariety2Order = mapVariety2Order;
		this.listPos = listPos;
		this.mapIdx2Pos = mapIdx2Pos;
		this.listVariantsString = listVariantsString;
		
		this.strRef = strRef;
		this.mapVarid2SnpsAllele2str = mapVarid2SnpsAllele2str;
		this.mapIdx2NonsynAlleles = mapIdx2NonsynAlleles;
		this.setSnpInExonTableIdx = setSnpInExonTableIdx;
		this.mapMergedIdx2SnpIdx = mapMergedIdx2SnpIdx;
		this.mapPos2Alleleset = mapPos2Alleleset;
	}


	public String getStrRef() {
		return strRef;
	}
	public void setStrRef(String strRef) {
		this.strRef = strRef;
	}
	public Map<BigDecimal, Map<Integer, Character>> getMapVarid2SnpsAllele2str() {
		return mapVarid2SnpsAllele2str;
	}
	public void setMapVarid2SnpsAllele2str(
			Map<BigDecimal, Map<Integer, Character>> mapVarid2SnpsAllele2str) {
		this.mapVarid2SnpsAllele2str = mapVarid2SnpsAllele2str;
	}
	public Map<BigDecimal, Set<Character>> getMapIdx2NonsynAlleles() {
		return mapIdx2NonsynAlleles;
	}
	public void setMapIdx2NonsynAlleles(
			Map<BigDecimal, Set<Character>> mapIdx2NonsynAlleles) {
		this.mapIdx2NonsynAlleles = mapIdx2NonsynAlleles;
	}
	public Set<Integer> getSetSnpInExonTableIdx() {
		return setSnpInExonTableIdx;
	}
	public void setSetSnpInExonTableIdx(Set<Integer> setSnpInExonTableIdx) {
		this.setSnpInExonTableIdx = setSnpInExonTableIdx;
	}
	public Map<Integer, Integer> getMapMergedIdx2SnpIdx() {
		return mapMergedIdx2SnpIdx;
	}
	public void setMapMergedIdx2SnpIdx(Map<Integer, Integer> mapMergedIdx2SnpIdx) {
		this.mapMergedIdx2SnpIdx = mapMergedIdx2SnpIdx;
	}


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

	
	
}
