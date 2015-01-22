package org.irri.iric.portal.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class VariantIndelStringData extends VariantStringData {
	
	private Map<BigDecimal, IndelsAllvarsPos> mapIndelId2Indel;
	private Map<BigDecimal, Set> mapPos2Alleleset;

	
	public VariantIndelStringData(Map<BigDecimal, Double> mapVariety2Mismatch,
			Map<BigDecimal, Integer> mapVariety2Order,
			List<SnpsAllvarsPos> listPos, Map<Integer, BigDecimal> mapIdx2Pos,
			List<SnpsStringAllvars> listVariantsString) {
		super();
		this.mapVariety2Mismatch = mapVariety2Mismatch;
		this.mapVariety2Order = mapVariety2Order;
		this.listPos = listPos;
		this.mapIdx2Pos = mapIdx2Pos;
		this.listVariantsString = listVariantsString;
	}
	
	
	public Map<BigDecimal, IndelsAllvarsPos> getMapIndelId2Indel() {
		return mapIndelId2Indel;
	}

	public void setMapIndelId2Indel(
			Map<BigDecimal, IndelsAllvarsPos> mapIndelId2Indel) {
		this.mapIndelId2Indel = mapIndelId2Indel;
	}


	public Map<BigDecimal, Set> getMapPos2Alleleset() {
		return mapPos2Alleleset;
	}


	public void setMapPos2Alleleset(Map<BigDecimal, Set> mapPos2Alleleset) {
		this.mapPos2Alleleset = mapPos2Alleleset;
	}

	

}
