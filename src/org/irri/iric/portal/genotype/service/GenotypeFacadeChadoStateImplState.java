package org.irri.iric.portal.genotype.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.irri.iric.portal.domain.SnpsAllvarsPos;

public class GenotypeFacadeChadoStateImplState {

	List listSNPAllVarsMismatches;
	java.util.HashMap<Integer,BigDecimal> mapOrder2Variety; // 0-indexed
	java.util.HashMap<BigDecimal, Integer> mapVariety2Order;  // 0-indexed
	java.util.HashMap<BigDecimal, Integer> mapVariety2Mismatch;
	java.util.HashMap<BigDecimal, Integer> mapVariety2PhyloOrder;
	List<SnpsAllvarsPos> snpsposlist;
	Set<BigDecimal> limitVarIds;
	boolean isCore=false;
	
}
