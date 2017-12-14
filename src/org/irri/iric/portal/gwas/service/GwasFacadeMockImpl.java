package org.irri.iric.portal.gwas.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.domain.MultiReferencePosition;
import org.irri.iric.portal.domain.Position;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.gwas.GwasFacade;
//import org.irri.iric.portal.gwas.service.GwasFacadeImpl.ScoreFeature;
import org.irri.iric.portal.variety.service.Data;
import org.springframework.stereotype.Service;
import org.zkoss.chart.model.DefaultCategoryModel;

//@Service("GwasMockFacade")
public class GwasFacadeMockImpl implements GwasFacade {


	@Override
	public List<String> getTraits(String pop) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getSubpopulations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<MultiReferencePosition, Double> getPos2MinusLogP(String trait,
			String subpop) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Double> getPosstr2MinusLogP(String trait, String subpop) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Double> getPosstr2MinusLogP(String trait, String subpop,
			Double minlogP, String region) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map[] createSubpopDistributions(String sPhenotype,
			Collection<Variety> varlist, boolean normalize) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public String getQQPlotFile(String trait, String subpop) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map[] createBin(Map mapPhen2Count1, Map mapPhen2Count2,
			int binsPhenotype) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float[] calculateOverlapArea(Map<Integer, Float> mapBin2Count1,
			Map<Integer, Float> mapBin2Count2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map[] createAlleleDistributions(String sChr, List poslist,
			String sPhenotype, Collection<Variety> varlist, boolean normalize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List[] getMinArea(String chr, List poslistInit, String sPhenotype,
			Integer binsPhenotype, Integer minCountPercent, Integer nfeatures) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map getMapPop2Trait() {
		// TODO Auto-generated method stub
		return null;
	}


	
//	
	
}
