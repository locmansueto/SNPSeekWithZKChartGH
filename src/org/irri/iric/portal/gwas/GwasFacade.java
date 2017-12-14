package org.irri.iric.portal.gwas;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.irri.iric.portal.domain.MultiReferencePosition;
import org.irri.iric.portal.domain.Position;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.gwas.service.GwasFacadeImpl.ScoreFeature;

public interface GwasFacade {

	Map<MultiReferencePosition, Double> getPos2MinusLogP(String trait, String subpop);
	Map<String, Double> getPosstr2MinusLogP(String trait, String subpop);
	Map<String, Double> getPosstr2MinusLogP(String trait, String subpop, Double minlogP, String region);
	String getQQPlotFile(String trait, String subpop);
	List<String> getTraits(String pop);
	List<String> getSubpopulations();
	
	/**
	 * 
	 * @param sPhenotype
	 * @param varlist
	 * @param normalize
	 * @return Map[]{mapSubpop2Phenotype2Count, mapSubpopGen2Phenotype2Count, mapSubpop2Phenotype2Frequency, mapSubpopGen2Phenotype2Frequency, mapVarid2Phenotype}
	 */
	Map[] createSubpopDistributions(String sPhenotype, Collection<Variety> varlist, boolean normalize);
	
	
	
	/**
	 * 
	 * @param sChr
	 * @param poslist
	 * @param sPhenotype
	 * @param varlist
	 * @param normalize
	 * @return Map[] mapAllele2Phenotype2Count, mapGenotype2Phenotype2Count, mapAllele2Phenotype2Frequency, mapGenotype2Phenotype2Frequency,mapVarid2Phenotype,mapVarid2Genotype,mapAllele2Phenotype2Set 
	 */
	Map[] createAlleleDistributions(String sChr, List poslist, String sPhenotype, Collection<Variety> varlist, boolean normalize);
	
	
	Map[] createBin(Map mapPhen2Count1, Map mapPhen2Count2, int binsPhenotype);
	Float[] calculateOverlapArea(Map<Integer, Float> mapBin2Count1,
			Map<Integer, Float> mapBin2Count2);
	//List[] getMinArea(String chr, List randPos, String label, Integer value, List<Map[]> mapAlleleHist, Integer minCountPercent);
	//List[] getMinArea(String chr, List poslist, String sPhenotype, Integer binsPhenotype, Map[] mapAlleleDist, Integer minCountPercent, Map<Position, String> mapPos2Sameallele);
	List[] getMinArea(String chr, List poslistInit, String sPhenotype, Integer binsPhenotype, Integer minCountPercent, Integer nfeatures);
	Map getMapPop2Trait();
	
	
}
