package org.irri.iric.portal.gwas.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.lang.ArrayUtils;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.LimitedFIFOQueue;
import org.irri.iric.portal.LimitedFIFOStack;
import org.irri.iric.portal.dao.SnpsAllvarsPosDAO;
import org.irri.iric.portal.domain.MultiReferencePosition;
import org.irri.iric.portal.domain.Position;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.domain.VarietyPlusPlus;
import org.irri.iric.portal.genotype.GenotypeFacade;
import org.irri.iric.portal.genotype.GenotypeQueryParams;
import org.irri.iric.portal.genotype.VariantStringData;
import org.irri.iric.portal.genotype.VariantTableArray;
import org.irri.iric.portal.genotype.service.VariantAlignmentTableArraysImpl;
import org.irri.iric.portal.gwas.GwasFacade;
import org.irri.iric.portal.gwas.dao.GWASRunDAO;
import org.irri.iric.portal.gwas.dao.ManhattanPlotDAO;
import org.irri.iric.portal.gwas.domain.GWASRun;
import org.irri.iric.portal.gwas.domain.ManhattanPlot;
import org.irri.iric.portal.gwas.service.GwasFacadeImpl.ScoreFeature;
import org.irri.iric.portal.variety.VarietyFacade;
import org.irri.iric.portal.variety.service.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.zkoss.zul.Messagebox;

@Service("GwasFacade")
@Scope(value="singleton")
public class GwasFacadeImpl implements GwasFacade {

	//protected List traits;
	//protected List subpopulations;
	protected Map<String,Set<String>> mapPop2Trait;
	private static Set setATGC=new HashSet();
	
	@Autowired
	protected GenotypeFacade genotype;
	@Autowired
	protected VarietyFacade variety;
	@Autowired
	protected GWASRunDAO gwasrundao;
	@Autowired
	//@Qualifier("ManhattanPlotDAO")
	@Qualifier("ManhattanPlotDAOFlatfileImpl")
	protected ManhattanPlotDAO manhattandao;
	
	private String dataset="3k";
	
	public GwasFacadeImpl() {
		super();
		setATGC.add("A");setATGC.add("T");setATGC.add("G");setATGC.add("C");
		AppContext.debug("GwasFacadeImpl() loaded");
		
	}

	@Override 
	public Map getMapPop2Trait() {
		if(mapPop2Trait==null) {
			mapPop2Trait=new TreeMap();
			
			// TODO Auto-generated constructor stub
			gwasrundao=(GWASRunDAO)AppContext.checkBean(gwasrundao,"GWASRunDAO");
			//manhattandao=(ManhattanPlotDAO)AppContext.checkBean(manhattandao,"ManhattanPlotDAO");
			manhattandao=(ManhattanPlotDAO)AppContext.checkBean(manhattandao,"ManhattanPlotDAOFlatfileImpl");
			//Set setsub=new TreeSet();
			//Set settrait=new TreeSet();
			Iterator<GWASRun> itruns = gwasrundao.getGWASRuns().iterator();
			while(itruns.hasNext()) {
				GWASRun run=itruns.next();
				//setsub.add(run.getSubpopulation());
				//settrait.add(run.getTrait());
				
				Set setTrait= mapPop2Trait.get(run.getSubpopulation());
				if(setTrait==null) {
					setTrait=new TreeSet();
					mapPop2Trait.put(run.getSubpopulation() , setTrait);
				}
				setTrait.add(run.getTrait());
			}
			/*
			subpopulations=new ArrayList();
			traits=new ArrayList();
			traits.addAll(settrait);
			subpopulations.addAll(setsub);
			*/
			AppContext.debug("GwasFacadeImpl "+ mapPop2Trait.size() + " subpopulations");
		}
		return mapPop2Trait;
	}
	
	@Override
	public Map<MultiReferencePosition, Double> getPos2MinusLogP(String trait, String subpop) {
		// TODO Auto-generated method stub
		return null;
	}
	private String getDataset() {
	 return VarietyFacade.DATASET_SNPINDELV2_IUPAC;
	 
	}
	
	
	@Override
	public Map<String, Double> getPosstr2MinusLogP(String trait, String subpop) {
		// TODO Auto-generated method stub
		return getPosstr2MinusLogP( trait,  subpop,  null, "all");
	}

	private GWASRun getGwasRun(String trait, String subpop) {
		Iterator<GWASRun> itruns = gwasrundao.getGWASRuns().iterator();
		GWASRun therun=null;
		while(itruns.hasNext()) {
			GWASRun run=itruns.next();
			if( trait.equals(run.getTrait()) && subpop.equals(run.getSubpopulation())) 
			{
				therun=run;
				break;
			}
		}
		return therun;
	}
	
	@Override
	public Map<String, Double> getPosstr2MinusLogP(String trait, String subpop, Double minlogP, String region) {
		// TODO Auto-generated method stub
		GWASRun therun= getGwasRun(trait, subpop); 
		if(therun==null) return null; 
		
		Map mapPos2P=new LinkedHashMap();
		Iterator<ManhattanPlot>  itPlots = manhattandao.getMinusPValues(therun, minlogP, region).iterator();
		while(itPlots.hasNext()) {
			ManhattanPlot plot = itPlots.next();
			mapPos2P.put(plot.getChr()+"-"+plot.getPosition(), plot.getMinusLogPvalue());
		}
		return mapPos2P;
	}

	
	
	
	@Override
	public String getQQPlotFile(String trait, String subpop) {
		// TODO Auto-generated method stub
		GWASRun therun= getGwasRun(trait, subpop); 
		if(therun==null) return null;
		//return AppContext.getFlatfilesDir()+"gwas/qq_" + therun.getGwasRunId() + ".png";
		//return AppContext.getHostname() + "/iric-portal-static/qq_" + therun.getGwasRunId() + ".png";
		return AppContext.getHostname() + "/static/qq_" + therun.getGwasRunId() + ".png";
	}

	@Override
	public List<String> getTraits(String pop) {
		// TODO Auto-generated method stub
		if(mapPop2Trait==null) getMapPop2Trait();
		List l=new ArrayList();
		l.addAll(mapPop2Trait.get(pop));
		return l;
	}

	@Override
	public List<String> getSubpopulations() {
		// TODO Auto-generated method stub
		if(mapPop2Trait==null) getMapPop2Trait();
		List l=new ArrayList();
		l.addAll(mapPop2Trait.keySet());
		return  l;
	}

//	
//	@Override
//	public Map[] createAlleleDistributions (String sChr, Collection poslist, String sPhenotype, Collection<Variety> varlist,  boolean normalize) {
//		
//		
//		genotype=(GenotypeFacade)AppContext.checkBean(genotype,"GenotypeFacade");
//		variety=(VarietyFacade)AppContext.checkBean(variety,"VarietyFacade");
//				
//		GenotypeQueryParams params= new  GenotypeQueryParams(null, sChr, null, null, true,false, false,
//				false,  poslist, null,
//				null, false, false); 
//		if(sPhenotype!=null) params.setPhenotype(sPhenotype);
//		params.addDataset(VarietyFacade.DATASET_SNPINDELV2_IUPAC);
//
//		Map<Object,Integer> mapGenotype2Count=new TreeMap();
//		Map<Long,Object> mapVarid2Genotype=new HashMap();
//		Map<BigDecimal,Object> mapVarid2Phenotype=new HashMap();
//		
//		
//		try {
//			VariantStringData data = genotype.queryGenotype( params );
//			VariantTableArray varianttable =  new VariantAlignmentTableArraysImpl();
//			varianttable = (VariantTableArray)genotype.fillGenotypeTable(varianttable, data, params);
//			
//			for(int ivar=0; ivar<varianttable.getVarid().length; ivar++) {
//				//Object allele=varianttable.getVaralleles()[ivar][0];
//				
//				StringBuffer buffallele=new StringBuffer();
//				String varsnpstring[]=(String[])varianttable.getVaralleles()[ivar];
//				for(int allelelen=0; allelelen<varianttable.getVaralleles()[ivar].length; allelelen++) {
//					String allelei= (String)varsnpstring[allelelen]; //.toString();
//					if(allelei.isEmpty()) buffallele.append('?');
//					else buffallele.append(AppContext.getIUPAC(allelei));
//				}
//				Object allele=buffallele.toString();
//				
//				mapVarid2Genotype.put( varianttable.getVarid()[ivar], allele );
//				
//				Integer cnt = mapGenotype2Count.get(allele);
//				if(cnt==null) {
//					cnt=Integer.valueOf(0);
//				}
//				mapGenotype2Count.put( allele, cnt+1);
//			}
//			
//		} catch(Exception ex) {
//			ex.printStackTrace();
//			//Messagebox.show(ex.getMessage());
//		}
//		
//		
//		mapVarid2Phenotype=null;
//		boolean phenIsNumber=true;
//		if(params.getPhenotype()!=null && !params.getPhenotype().isEmpty()) {
//			sPhenotype=params.getPhenotype();
//			mapVarid2Phenotype = variety.getPhenotypeValues(sPhenotype);
//		}
//		
//		/*
//		if( !this.listboxVarietylist.getSelectedItem().getLabel().isEmpty() ) {
//			mapVarid2Phenotype=new HashMap();
//			String varlistname=listboxVarietylist.getSelectedItem().getLabel().trim();
//			Iterator<Variety> itvarlist=workspace.getVarieties(varlistname).iterator();
//			while(itvarlist.hasNext()) {
//				VarietyPlusPlus vp = (VarietyPlusPlus)itvarlist.next();
//				mapVarid2Phenotype.put( vp.getVarietyId() , vp.getValue());
//			}
//		}
//		*/
//		if(varlist!=null) {
//			mapVarid2Phenotype=new HashMap();
//			Iterator<Variety> itvarlist=varlist.iterator();
//			while(itvarlist.hasNext()) {
//				VarietyPlusPlus vp = (VarietyPlusPlus)itvarlist.next();
//				mapVarid2Phenotype.put( vp.getVarietyId() , vp.getValue());
//			}
//		}
//
//		
//		
//		Map<Object, Map<Object,Collection>> mapGenotype2Phenotype2Count=null;
//		Map<Object, Map<Object,Object>> mapAllele2Phenotype2Count=null;
//		Map<Object, Map<Object,Collection>> mapAllele2Phenotype2Set=new HashMap();
//		
//
//
//			mapAllele2Phenotype2Count=new HashMap();
//			mapGenotype2Phenotype2Count=new HashMap();
//		
//		
//		Map<Object, Integer> mapAllele2AllPhenotypeCount=null;
//		Map<Object, Integer> mapGenotype2AllPhenotypeCount=null;
//
//		if(normalize) {
//			mapAllele2AllPhenotypeCount=new HashMap();
//			mapGenotype2AllPhenotypeCount=new HashMap();
//		}
//
//		
//		Iterator<BigDecimal> itVar=mapVarid2Phenotype.keySet().iterator();
//		Map<Object,Integer> mapPhen2Count=new TreeMap();
//		double maxphen=0;
//		double minphen=Double.MAX_VALUE;
//		while(itVar.hasNext()) {
//			BigDecimal varid=itVar.next();
//			Object phen=mapVarid2Phenotype.get(varid);
//			
//			//AppContext.debug(phen.getClass().toString() + "  " + phen);
//			if(phen instanceof String) {
//				phenIsNumber=false;
//			} else {
//				double phenval = ((BigDecimal)phen).doubleValue();
//				if(phenval>maxphen) maxphen=phenval;
//				if(phenval<minphen) minphen=phenval;
//			}
//			
//			Object vargenotype=mapVarid2Genotype.get(varid.longValue());
//			Map<Object,Collection> mapphencnt = mapGenotype2Phenotype2Count.get( vargenotype );
//			if(mapphencnt==null) {
//				mapphencnt=new HashMap();
//				mapGenotype2Phenotype2Count.put(vargenotype, mapphencnt);
//			}
//			
//			if(normalize) {
//				Integer genallphencount = mapGenotype2AllPhenotypeCount.get(vargenotype);
//				if(genallphencount==null) genallphencount=Integer.valueOf(0);
//				 mapGenotype2AllPhenotypeCount.put(vargenotype, genallphencount+1);
//			}
//					 
//			Collection phencnt = mapphencnt.get(phen);
//			if(phencnt==null) {
//				//phencnt=Integer.valueOf(0);
//				phencnt=new HashSet();
//				mapphencnt.put( phen, phencnt);
//			}
//			//mapphencnt.put( phen, phencnt+1 );
//			phencnt.add( varid );
//			mapGenotype2Phenotype2Count.put(vargenotype, mapphencnt);
//			
//			if(params.getPoslist()!=null &&  params.getPoslist().size()==1) {
//			if(vargenotype.toString().contains("/")) {
//				String alleles[] = vargenotype.toString().split("\\/");
//
//				Map<Object,Object> mapallelecnt = mapAllele2Phenotype2Count.get( alleles[0] );
//				Map<Object,Collection> mapalleleset = mapAllele2Phenotype2Set.get( alleles[0] );
//				if(mapallelecnt==null) {
//					mapallelecnt=new HashMap();
//					mapAllele2Phenotype2Count.put(alleles[0], mapallelecnt);
//					mapalleleset=new HashMap();
//					mapAllele2Phenotype2Set.put(alleles[0], mapalleleset);
//				}
//				
//				
//				
//				Object phenallelecnt = mapallelecnt.get(phen);
//				Collection phenalleleset=mapalleleset.get(phen);
//				if(phenallelecnt==null) {
//					phenallelecnt=Integer.valueOf(0);
//				}
//				if(phenalleleset==null) {
//					phenalleleset=new HashSet();
//					mapalleleset.put( phen, phenalleleset);
//				}
//				mapallelecnt.put( phen, phenallelecnt);
//				phenalleleset.add( varid );
//				mapAllele2Phenotype2Count.put(alleles[0], mapallelecnt);
//
//				mapallelecnt = mapAllele2Phenotype2Count.get( alleles[1] );
//				mapalleleset = mapAllele2Phenotype2Set.get( alleles[1] );
//				if(mapallelecnt==null) {
//					mapallelecnt=new HashMap();
//					mapAllele2Phenotype2Count.put(alleles[1], mapallelecnt);
//					mapalleleset=new HashMap();
//					mapAllele2Phenotype2Set.put(alleles[1], mapalleleset);
//				}
//				phenallelecnt = mapallelecnt.get(phen);
//				phenalleleset=mapalleleset.get(phen);
//				if(phenallelecnt==null) {
//					phenallelecnt=Integer.valueOf(0);
//				}
//				if(phenalleleset==null) {
//					phenalleleset=new HashSet();
//					mapalleleset.put( phen, phenalleleset);
//				}
//				phenalleleset.add( varid );
//				mapallelecnt.put( phen, phenallelecnt);
//				mapAllele2Phenotype2Count.put(alleles[1], mapallelecnt);
//				
//				if(normalize) {
//				Integer alleleallphencnt=mapAllele2AllPhenotypeCount.get(alleles[0]);
//				if(alleleallphencnt==null) alleleallphencnt=Integer.valueOf(0);
//				mapAllele2AllPhenotypeCount.put(alleles[0], alleleallphencnt+1);
//				alleleallphencnt=mapAllele2AllPhenotypeCount.get(alleles[1]);
//				if(alleleallphencnt==null) alleleallphencnt=Integer.valueOf(0);
//				mapAllele2AllPhenotypeCount.put(alleles[1], alleleallphencnt+1);
//				}
//				
//				
//			} else { // if(!vargenotype.toString().isEmpty()){
//				Map<Object,Object> mapallelecnt = mapAllele2Phenotype2Count.get( vargenotype );
//				Map<Object,Collection> mapalleleset = mapAllele2Phenotype2Set.get( vargenotype);
//				if(mapallelecnt==null) {
//					mapallelecnt=new HashMap();
//					mapAllele2Phenotype2Count.put(vargenotype, mapallelecnt);
//					mapalleleset=new HashMap();
//					mapAllele2Phenotype2Set.put(vargenotype, mapalleleset);
//				}
//				Object phenallelecnt = mapallelecnt.get(phen);
//				Collection phenalleleset=mapalleleset.get(phen);
//				if(phenallelecnt==null) {
//					phenallelecnt=Integer.valueOf(0);
//				}
//				if(phenalleleset==null) {
//					phenalleleset=new HashSet();
//					mapalleleset.put( phen, phenalleleset);
//				}
//				phenalleleset.add( varid );
//				mapallelecnt.put( phen, Integer.valueOf( (Integer)phenallelecnt+2) );
//				mapAllele2Phenotype2Count.put(vargenotype, mapallelecnt);
//				
//				if(normalize) {
//				Integer alleleallphencnt=mapAllele2AllPhenotypeCount.get(vargenotype);
//				if(alleleallphencnt==null) alleleallphencnt=Integer.valueOf(0);
//				mapAllele2AllPhenotypeCount.put(vargenotype, alleleallphencnt+2);
//				}
//
//			}
//			}
//			
//		
//		}
//		
//		Map mapAllele2Phenotype2Frequency=null;
//		Map mapGenotype2Phenotype2Frequency=null;
//		if(normalize) {
//			mapAllele2Phenotype2Frequency=normalizeMap(mapAllele2Phenotype2Count, mapAllele2AllPhenotypeCount);
//			mapGenotype2Phenotype2Frequency=normalizeMap(mapGenotype2Phenotype2Count, mapGenotype2AllPhenotypeCount);
//		}
//		
//		return new Map[]{mapAllele2Phenotype2Count, mapGenotype2Phenotype2Count, mapAllele2Phenotype2Frequency, mapGenotype2Phenotype2Frequency,mapVarid2Phenotype,mapVarid2Genotype,mapAllele2Phenotype2Set };
//
//		 
//	}

	private Map[] getMapsGenotypeVarietycount(VariantTableArray varianttable ) { 
		Map<Long,String> mapVarid2Genotype=new HashMap();
		Map<String,Integer> mapGenotype2Count=new TreeMap();

		//varianttable.getPosition()
		
		for(int ivar=0; ivar<varianttable.getVarid().length; ivar++) {
			//Object allele=varianttable.getVaralleles()[ivar][0];
			StringBuffer buffallele=new StringBuffer();
			String varsnpstring[]=(String[])varianttable.getVaralleles()[ivar];
			for(int allelelen=0; allelelen<varianttable.getVaralleles()[ivar].length; allelelen++) {
				String allelei= (String)varsnpstring[allelelen]; //.toString();
				if(allelei.isEmpty()) buffallele.append('?');
				else buffallele.append(AppContext.getIUPAC(allelei));
			}
			String allele=buffallele.toString();
			
			mapVarid2Genotype.put( varianttable.getVarid()[ivar], allele );
			Integer cnt = mapGenotype2Count.get(allele);
			if(cnt==null) {
				cnt=Integer.valueOf(0);
			}
			mapGenotype2Count.put( allele, cnt+1);
		}
		
		return new Map[] {mapVarid2Genotype, mapGenotype2Count};
	}
	
	@Override
	public Map[] createAlleleDistributions(String sChr, List poslist, String sPhenotype, Collection<Variety> varlist,  boolean normalize) {
		return createAlleleDistributions(sChr, poslist, sPhenotype,  varlist,  normalize, null, null,poslist.size() ) ;
	}

	private Map[] createAlleleDistributions(String sChr, List poslist, String sPhenotype, Collection<Variety> varlist,  boolean normalize, Map<Long,Object> mapVarid2GenotypeInit, Map<BigDecimal,Object> mapVarid2Phenotype, int nfeatures) {
		
		
		genotype=(GenotypeFacade)AppContext.checkBean(genotype,"GenotypeFacade");
		variety=(VarietyFacade)AppContext.checkBean(variety,"VarietyFacade");
				

		
		try {

			boolean phenIsNumber=true;

			Map mapVarid2Genotype=new HashMap();
			
			if(mapVarid2GenotypeInit==null || mapVarid2Phenotype==null|| mapVarid2GenotypeInit.isEmpty() || mapVarid2Phenotype.isEmpty() || nfeatures==poslist.size() ) {
			
				//GenotypeQueryParams params= new  GenotypeQueryParams(null, sChr, null, null, true,false, GenotypeQueryParams.SNP_ALL,
				Set sSnp=new HashSet();Set sVariety=new HashSet(); Set sRun=new HashSet();
				sSnp.add("3kall"); sVariety.add("3k"); 
				
				sRun.addAll( genotype.getGenotyperuns(sVariety, sSnp, "SNP") );
				//sRun.add("3kall");
				
				
				GenotypeQueryParams params= new  GenotypeQueryParams(null, sChr, null, null, true,false, sSnp, sVariety, sRun,
						false,  poslist, null,
						null, false, false); 
				if(sPhenotype!=null) params.setPhenotype(sPhenotype);
				//params.addDataset(VarietyFacade.DATASET_SNPINDELV2_IUPAC);
				
				
				VariantStringData data = genotype.queryGenotype( params );
				VariantTableArray varianttable =  new VariantAlignmentTableArraysImpl();
				varianttable = (VariantTableArray)genotype.fillGenotypeTable(varianttable, data, params);
	
				int permutations=2^nfeatures;
	
				if(mapVarid2GenotypeInit==null) mapVarid2GenotypeInit=new HashMap();
				if(mapVarid2Phenotype==null) mapVarid2Phenotype=new HashMap();
						
				Map[] mapgenvarcount=getMapsGenotypeVarietycount(varianttable);
				Map<Object,Integer> mapGenotype2Count=mapgenvarcount[1];
				mapVarid2GenotypeInit.putAll(mapgenvarcount[0]);
				mapVarid2Genotype.putAll(mapgenvarcount[0]);
				mapVarid2Phenotype.clear();

				//mapVarid2Phenotype=null;
				if(params.getPhenotype()!=null && !params.getPhenotype().isEmpty()) {
					sPhenotype=params.getPhenotype();
					mapVarid2Phenotype.putAll(variety.getPhenotypeValues(sPhenotype, dataset));
				}
			

				/*
				if( !this.listboxVarietylist.getSelectedItem().getLabel().isEmpty() ) {
					mapVarid2Phenotype=new HashMap();
					String varlistname=listboxVarietylist.getSelectedItem().getLabel().trim();
					Iterator<Variety> itvarlist=workspace.getVarieties(varlistname).iterator();
					while(itvarlist.hasNext()) {
						VarietyPlusPlus vp = (VarietyPlusPlus)itvarlist.next();
						mapVarid2Phenotype.put( vp.getVarietyId() , vp.getValue());
					}
				}
				*/
				if(varlist!=null) {
					mapVarid2Phenotype=new HashMap();
					Iterator<Variety> itvarlist=varlist.iterator();
					while(itvarlist.hasNext()) {
						VarietyPlusPlus vp = (VarietyPlusPlus)itvarlist.next();
						mapVarid2Phenotype.put( vp.getVarietyId() , vp.getValue());
					}
				}
	
			} else {
				// new set of varid2genotype
				List randidx = selectRandomIndex(2);
				Map<String,Collection> gen2var=new HashMap();
				Iterator itVarid=mapVarid2GenotypeInit.keySet().iterator();
				while(itVarid.hasNext()) {
					Long varid= (Long)itVarid.next();
					String genotype =(String)mapVarid2GenotypeInit.get(varid);
					Collection colVars = gen2var.get(genotype);
					if(colVars==null) {
						colVars=new HashSet();
						gen2var.put( genotype,colVars);
					}
					colVars.add( varid );
				}
				
				int newidx[]=new int[randidx.size()];
				Iterator itIdx=randidx.iterator();
				int i=0;
				List newposlist=new ArrayList();
				
				while(itIdx.hasNext()) {
					newidx[i]=(Integer)itIdx.next();
					newposlist.add( poslist.get(newidx[i]));
					i++;
				}
				poslist.clear();
				poslist.add( newposlist );
				
				Map<String, Collection> newgeno2vars=new HashMap();
				Iterator itgenotype=gen2var.keySet().iterator();
				while(itgenotype.hasNext()) {
					String geno= (String)itgenotype.next();
					StringBuffer bufnewgeno=new StringBuffer();
					for(i=0; i<newidx.length; i++ ) {
						bufnewgeno.append( geno.charAt(newidx[i]));	
					}
					String newgeno=bufnewgeno.toString();
					Collection colVars = newgeno2vars.get(newgeno);
					if(colVars==null) {
						colVars=new HashSet();
						newgeno2vars.put( newgeno,colVars);
					}
					colVars.addAll( gen2var.get(geno));
				}
					
				mapVarid2Genotype.clear();
				mapVarid2Genotype.putAll(newgeno2vars);
				
			}
			
			
			Map<Object, Map<Object,Collection>> mapGenotype2Phenotype2Count=null;
			Map<Object, Map<Object,Object>> mapAllele2Phenotype2Count=null;
			Map<Object, Map<Object,Collection>> mapAllele2Phenotype2Set=new HashMap();
			
	
			mapAllele2Phenotype2Count=new HashMap();
			mapGenotype2Phenotype2Count=new HashMap();
			
			
			Map<Object, Integer> mapAllele2AllPhenotypeCount=null;
			Map<Object, Integer> mapGenotype2AllPhenotypeCount=null;
	
			if(normalize) {
				mapAllele2AllPhenotypeCount=new HashMap();
				mapGenotype2AllPhenotypeCount=new HashMap();
			}
	
			
			Iterator<BigDecimal> itVar=mapVarid2Phenotype.keySet().iterator();
			Map<Object,Integer> mapPhen2Count=new TreeMap();
			double maxphen=0;
			double minphen=Double.MAX_VALUE;
			StringBuffer ballele=new StringBuffer();
			
			while(itVar.hasNext()) {
				BigDecimal varid=itVar.next();
				Object phen=mapVarid2Phenotype.get(varid);
				
				//AppContext.debug(phen.getClass().toString() + "  " + phen);
				if(phen instanceof String) {
					phenIsNumber=false;
				} else {
					double phenval = ((BigDecimal)phen).doubleValue();
					if(phenval>maxphen) maxphen=phenval;
					if(phenval<minphen) minphen=phenval;
				}
				
				Object vargenotype=mapVarid2Genotype.get(varid.longValue());
				Map<Object,Collection> mapphencnt = mapGenotype2Phenotype2Count.get( vargenotype );
				if(mapphencnt==null) {
					mapphencnt=new HashMap();
					mapGenotype2Phenotype2Count.put(vargenotype, mapphencnt);
				}
				
				if(normalize) {
					Integer genallphencount = mapGenotype2AllPhenotypeCount.get(vargenotype);
					if(genallphencount==null) genallphencount=Integer.valueOf(0);
					 mapGenotype2AllPhenotypeCount.put(vargenotype, genallphencount+1);
				}
						 
				Collection phencnt = mapphencnt.get(phen);
				if(phencnt==null) {
					//phencnt=Integer.valueOf(0);
					phencnt=new HashSet();
					mapphencnt.put( phen, phencnt);
				}
				//mapphencnt.put( phen, phencnt+1 );
				phencnt.add( varid );
				mapGenotype2Phenotype2Count.put(vargenotype, mapphencnt);
				
				
				//if(params.getPoslist()!=null &&  params.getPoslist().size()==1) {
				if(poslist!=null &&  poslist.size()==1) {
				
				ballele.append(vargenotype).append(" ");	
				//if(vargenotype.toString().contains("/")) {
				String strgenotype=vargenotype.toString();
				if( !setATGC.contains(strgenotype) || strgenotype.contains("/")) {
					
					String alleles[]=null;
					if( strgenotype.contains("/"))
						alleles =strgenotype.split("\\/");
					else {
						String nucs=AppContext.getNucsFromIUPAC(strgenotype);
						if(nucs.length()==2) {
							alleles=new String[]{nucs.substring(0,1), nucs.substring(1,2)};
						} else alleles=new String[]{nucs,nucs};
					}
	
					Map<Object,Object> mapallelecnt = mapAllele2Phenotype2Count.get( alleles[0] );
					Map<Object,Collection> mapalleleset = mapAllele2Phenotype2Set.get( alleles[0] );
					if(mapallelecnt==null) {
						mapallelecnt=new HashMap();
						mapAllele2Phenotype2Count.put(alleles[0], mapallelecnt);
						mapalleleset=new HashMap();
						mapAllele2Phenotype2Set.put(alleles[0], mapalleleset);
					}
					
					
					
					Object phenallelecnt = mapallelecnt.get(phen);
					Collection phenalleleset=mapalleleset.get(phen);
					if(phenallelecnt==null) {
						phenallelecnt=Integer.valueOf(0);
					}
					if(phenalleleset==null) {
						phenalleleset=new HashSet();
						mapalleleset.put( phen, phenalleleset);
					}
					mapallelecnt.put( phen, phenallelecnt);
					phenalleleset.add( varid );
					mapAllele2Phenotype2Count.put(alleles[0], mapallelecnt);
	
					mapallelecnt = mapAllele2Phenotype2Count.get( alleles[1] );
					mapalleleset = mapAllele2Phenotype2Set.get( alleles[1] );
					if(mapallelecnt==null) {
						mapallelecnt=new HashMap();
						mapAllele2Phenotype2Count.put(alleles[1], mapallelecnt);
						mapalleleset=new HashMap();
						mapAllele2Phenotype2Set.put(alleles[1], mapalleleset);
					}
					phenallelecnt = mapallelecnt.get(phen);
					phenalleleset=mapalleleset.get(phen);
					if(phenallelecnt==null) {
						phenallelecnt=Integer.valueOf(0);
					}
					if(phenalleleset==null) {
						phenalleleset=new HashSet();
						mapalleleset.put( phen, phenalleleset);
					}
					phenalleleset.add( varid );
					mapallelecnt.put( phen, phenallelecnt);
					mapAllele2Phenotype2Count.put(alleles[1], mapallelecnt);
					
					if(normalize) {
					Integer alleleallphencnt=mapAllele2AllPhenotypeCount.get(alleles[0]);
					if(alleleallphencnt==null) alleleallphencnt=Integer.valueOf(0);
					mapAllele2AllPhenotypeCount.put(alleles[0], alleleallphencnt+1);
					alleleallphencnt=mapAllele2AllPhenotypeCount.get(alleles[1]);
					if(alleleallphencnt==null) alleleallphencnt=Integer.valueOf(0);
					mapAllele2AllPhenotypeCount.put(alleles[1], alleleallphencnt+1);
					}
					
					
				} else { // if(!vargenotype.toString().isEmpty()){
					Map<Object,Object> mapallelecnt = mapAllele2Phenotype2Count.get( vargenotype );
					Map<Object,Collection> mapalleleset = mapAllele2Phenotype2Set.get( vargenotype);
					if(mapallelecnt==null) {
						mapallelecnt=new HashMap();
						mapAllele2Phenotype2Count.put(vargenotype, mapallelecnt);
						mapalleleset=new HashMap();
						mapAllele2Phenotype2Set.put(vargenotype, mapalleleset);
					}
					Object phenallelecnt = mapallelecnt.get(phen);
					Collection phenalleleset=mapalleleset.get(phen);
					if(phenallelecnt==null) {
						phenallelecnt=Integer.valueOf(0);
					}
					if(phenalleleset==null) {
						phenalleleset=new HashSet();
						mapalleleset.put( phen, phenalleleset);
					}
					phenalleleset.add( varid );
					mapallelecnt.put( phen, Integer.valueOf( (Integer)phenallelecnt+2) );
					mapAllele2Phenotype2Count.put(vargenotype, mapallelecnt);
					
					if(normalize) {
					Integer alleleallphencnt=mapAllele2AllPhenotypeCount.get(vargenotype);
					if(alleleallphencnt==null) alleleallphencnt=Integer.valueOf(0);
					mapAllele2AllPhenotypeCount.put(vargenotype, alleleallphencnt+2);
					}
	
				}
				}
				
			
			}
			
			AppContext.debug("alleles[]=" + ballele);
			
			Map mapAllele2Phenotype2Frequency=null;
			Map mapGenotype2Phenotype2Frequency=null;
			if(normalize) {
				mapAllele2Phenotype2Frequency=normalizeMap(mapAllele2Phenotype2Count, mapAllele2AllPhenotypeCount);
				mapGenotype2Phenotype2Frequency=normalizeMap(mapGenotype2Phenotype2Count, mapGenotype2AllPhenotypeCount);
			}
			
			return new Map[]{mapAllele2Phenotype2Count, mapGenotype2Phenotype2Count, mapAllele2Phenotype2Frequency, mapGenotype2Phenotype2Frequency,mapVarid2Phenotype,mapVarid2Genotype,mapAllele2Phenotype2Set };
		
		
		} catch(Exception ex) {
			ex.printStackTrace();
			//Messagebox.show(ex.getMessage());
		}
		
		return null;
	}
	
	//private Map normalizeMap(Map<Object,Map> mapCount, Map<Object,Integer> mapTotal) {
	private Map normalizeMap(Map mapCount, Map mapTotal) {
		
		Map mapFrequency=new HashMap();
		Iterator it=mapCount.keySet().iterator();
		while(it.hasNext()) {
			Object allele=it.next();
			Map mapphen2cnt = (Map)mapCount.get(allele);
			Object objtotal = mapTotal.get(allele);
			int total=0;
			if(objtotal instanceof Number) total=((Number) objtotal).intValue();
			else if(objtotal instanceof Collection) total=((Collection) objtotal).size();
			
			Map<Object,Double> mapphenfreq= new HashMap();
			mapFrequency.put(allele, mapphenfreq);
			Iterator itPhen=mapphen2cnt.keySet().iterator();
			while(itPhen.hasNext()) {
				Object phen=itPhen.next();
				Object objcnt = mapphen2cnt.get(phen);
				int cnt=0;
				if(objcnt instanceof Number) cnt=((Number) objcnt).intValue();
				else if(objcnt instanceof Collection) cnt=((Collection) objcnt).size();
				mapphenfreq.put( phen, cnt*1.0/total);
			}
		}
		
		return mapFrequency;
	}
	
	
	
	@Override
//	public Map[] createSubpopDistributions (String sChr, Collection poslist, String sPhenotype, Collection<Variety> varlist,  boolean normalize) {
	public Map[] createSubpopDistributions (String sPhenotype, Collection<Variety> varlist,  boolean normalize) {
		
		genotype=(GenotypeFacade)AppContext.checkBean(genotype,"GenotypeFacade");
		variety=(VarietyFacade)AppContext.checkBean(variety,"VarietyFacade");
				
		Map<BigDecimal,String> mapVarid2Subpop=new HashMap();
		Map<BigDecimal,Object> mapVarid2Phenotype=null;
		boolean phenIsNumber=true;
		
		if(varlist!=null) {
			mapVarid2Phenotype=new HashMap();
			Iterator<Variety> itvarlist=varlist.iterator();
			while(itvarlist.hasNext()) {
				VarietyPlusPlus vp = (VarietyPlusPlus)itvarlist.next();
				mapVarid2Phenotype.put( vp.getVarietyId() , vp.getValue());
			}
		} else {
			mapVarid2Phenotype=variety.getPhenotypeValues(sPhenotype, dataset);
		}

		Iterator<Variety> itVarid=variety.getMapId2Variety(getDataset()).values().iterator();
		while(itVarid.hasNext()) {
			Variety var=itVarid.next();
			mapVarid2Subpop.put(var.getVarietyId(), var.getSubpopulation());
		}
		
		Map<Object, Map<Object,Collection>> mapSubpopGen2Phenotype2Count=new HashMap();
		Map<Object, Map<Object,Collection>> mapSubpop2Phenotype2Count=new HashMap();
		
		Map mapSubpop2AllPhenotypeCount=null;
		Map mapSubpopGen2AllPhenotypeCount=null;
		if(normalize) {
			mapSubpop2AllPhenotypeCount=new HashMap();
			mapSubpopGen2AllPhenotypeCount=new HashMap();
		}
		
		
		Iterator<BigDecimal> itVar=mapVarid2Phenotype.keySet().iterator();
		Map<Object,Integer> mapPhen2Count=new TreeMap();
		double maxphen=0;
		double minphen=Double.MAX_VALUE;
		while(itVar.hasNext()) {
			BigDecimal varid=itVar.next();
			Object phen=mapVarid2Phenotype.get(varid);
			
			//AppContext.debug(phen.getClass().toString() + "  " + phen);
			if(phen instanceof String) {
				phenIsNumber=false;
			} else {
				double phenval = ((BigDecimal)phen).doubleValue();
				if(phenval>maxphen) maxphen=phenval;
				if(phenval<minphen) minphen=phenval;
			}
			
			
			
				String subpop=mapVarid2Subpop.get(varid);
				Map mapphencnt = mapSubpop2Phenotype2Count.get( subpop );
				if(mapphencnt==null) {
					mapphencnt=new HashMap();
					mapSubpop2Phenotype2Count.put(subpop, mapphencnt);
				}
				Set phencnt = (Set)mapphencnt.get(phen);
				if(phencnt==null) {
					//phencnt=Integer.valueOf(0);
					phencnt=new HashSet();
					mapphencnt.put( phen, phencnt);
				}
				//mapphencnt.put( phen, phencnt+1 );
				phencnt.add( varid );
				mapSubpop2Phenotype2Count.put(subpop, mapphencnt);
				
				if(normalize) {
				Set setallphencnt = (Set)mapSubpop2AllPhenotypeCount.get(subpop);
				if(setallphencnt==null){
					setallphencnt=new HashSet();
					mapSubpop2AllPhenotypeCount.put(subpop, setallphencnt);
				}
				setallphencnt.add(varid);
				}
				
				String gensubpop= Data.getGeneralSubpopulation(subpop);
				if(gensubpop!=null) {
					mapphencnt = mapSubpopGen2Phenotype2Count.get( gensubpop );
					if(mapphencnt==null) {
						mapphencnt=new HashMap();
						mapSubpopGen2Phenotype2Count.put(gensubpop, mapphencnt);
					}
					phencnt = (Set)mapphencnt.get(phen);
					if(phencnt==null) {
						//phencnt=Integer.valueOf(0);
						phencnt=new HashSet();
						mapphencnt.put( phen, phencnt);
					}
					//mapphencnt.put( phen, phencnt+1 );
					phencnt.add( varid );
					mapSubpopGen2Phenotype2Count.put(gensubpop, mapphencnt);
				}
				
				if(normalize) {
				Set setallphencnt = (Set)mapSubpopGen2AllPhenotypeCount.get(gensubpop);
				if(setallphencnt==null){
					setallphencnt=new HashSet();
					mapSubpopGen2AllPhenotypeCount.put(gensubpop, setallphencnt);
				}
				setallphencnt.add(varid);
				}

				
				gensubpop="all varieties";
				mapphencnt = mapSubpopGen2Phenotype2Count.get( gensubpop );
				if(mapphencnt==null) {
					mapphencnt=new HashMap();
					mapSubpopGen2Phenotype2Count.put(gensubpop, mapphencnt);
				}
				phencnt = (Set)mapphencnt.get(phen);
				if(phencnt==null) {
					//phencnt=Integer.valueOf(0);
					phencnt=new HashSet();
					mapphencnt.put( phen, phencnt);
				}
				//mapphencnt.put( phen, phencnt+1 );
				phencnt.add( varid );
				mapSubpopGen2Phenotype2Count.put(gensubpop, mapphencnt);

				if(normalize) {
				Set setallphencnt = (Set)mapSubpopGen2AllPhenotypeCount.get(gensubpop);
				if(setallphencnt==null){
					setallphencnt=new HashSet();
					mapSubpopGen2AllPhenotypeCount.put(gensubpop, setallphencnt);
				}
				setallphencnt.add(varid);
				}
		}
		
		Map mapSubpop2Phenotype2Frequency=null;
		Map mapSubpopGen2Phenotype2Frequency=null;
		if(normalize) {
			mapSubpop2Phenotype2Frequency=normalizeMap(mapSubpop2Phenotype2Count, mapSubpop2AllPhenotypeCount);
			mapSubpopGen2Phenotype2Frequency=normalizeMap(mapSubpopGen2Phenotype2Count, mapSubpopGen2AllPhenotypeCount);
		}
		
		return new Map[]{mapSubpop2Phenotype2Count, mapSubpopGen2Phenotype2Count, mapSubpop2Phenotype2Frequency, mapSubpopGen2Phenotype2Frequency, mapVarid2Phenotype};
	}
	
	
	/*
	public void analysePhenotype() {
		String trait = getTraits().get(0);
		String subpop=this.getSubpopulations().get(0);
		
		double minlogP=10.0;
		Map<String,Double> mapPos2MinuslogP = getPosstr2MinusLogP(trait, subpop, 10.0);
		
		
		Iterator<GWASRun> itruns = gwasrundao.getGWASRuns().iterator();
		GWASRun therun=null;
		while(itruns.hasNext()) {
			GWASRun run=itruns.next();
			if( trait.equals(run.getTrait()) && subpop.equals(run.getSubpopulation())) 
			{
				therun=run;
				break;
			}
		}
		if(therun==null) return; 
		
		Map mapPos2P=new LinkedHashMap();
		
		AppContext.debug("reading " + trait + " " + subpop);
		List poslist=manhattandao.getMinusPValues(therun, minlogP);
		getMinArea("any",  poslist,trait, 20); 
	}
	*/
	
	public class ScoreFeature implements Comparable {
		private String name;
		private Number score;
		private Object feature1;
		private Object feature2;
		private Object allfeatures;
		
		public ScoreFeature(String name, Number score, Object feature1, Object feature2, Object allfeatures) {
			super();
			this.name=name;
			this.score = score;
			this.feature1 = feature1;
			this.feature2 = feature2;
			this.allfeatures = allfeatures;
		}

		@Override
		public int compareTo(Object arg0) {
			// TODO Auto-generated method stub
			ScoreFeature s=(ScoreFeature)arg0;
			int ret= ((Comparable)name).compareTo(s.name);
			if(ret!=0) return ret;
			ret= -((Comparable)score).compareTo(s.score);
			if(ret!=0) return ret;
			ret= ((Comparable)feature1).compareTo(s.feature1);
			if(ret!=0) return ret;
			ret= ((Comparable)feature2).compareTo(s.feature2);
			return ret;
		}

		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			final int prime = 31;
			int result = 1;
			result = (int) (prime * result + ((name == null) ? 0 : name.hashCode()));
			result = (int) (prime * result + ((score == null) ? 0 : score.hashCode()));
			result = (int) (prime * result + ((feature1 == null) ? 0 : feature1.hashCode()));
			result = (int) (prime * result + ((feature2 == null) ? 0 : feature2.hashCode()));
			return result;

		}

		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			return compareTo(obj)==0;
		}

		public Number getScore() {
			return score;
		}

		public Object getFeature1() {
			return feature1;
		}

		public Object getFeature2() {
			return feature2;
		}

		public Object getAllfeatures() {
			return allfeatures;
		}

		public String getName() {
			return name;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return name;
		}

	
		
		
		
		
	}
	
	// get random permutation of positions with length npos
	private List selectRandomPos(List poslist, int npos) {
		
		Set posSelset=new TreeSet();
		//Random rand = new Random(831);
		Random rand = new Random();
		while(posSelset.size()!=npos) {
			for(int i=0; i<npos; i++) {
				posSelset.add( poslist.get(rand.nextInt(poslist.size())));
			}
		}
		List randList=new ArrayList();
		randList.addAll( posSelset );
		return randList;
	}
	private List selectRandomIndex(int npos) {
		
		Set posSelset=new TreeSet();
		//Random rand = new Random(831);
		Random rand = new Random();
		while(posSelset.size()!=npos) {
			posSelset.add(rand.nextInt(npos));
		}
		
		List randList=new ArrayList();
		randList.addAll( posSelset );
		return randList;
	}
	
//	@Override
//	public List<Stack[]> getMinArea(String chr, List poslist, String sPhenotype,
//			Integer binsPhenotype, List<Map[]> listMapAlleleDist, Integer minCountPercent) {
//		
//		
//		return getMinArea(chr,poslist, sPhenotype, binsPhenotype, mapAlleleDist, minCountPercent, null);
//		
//		List listStack=new ArrayList();
//		Map mapAlleleDist[]=new HashMap[7];
//		Stack[] scores1 = getMinArea(chr,poslist, sPhenotype, binsPhenotype, mapAlleleDist, minCountPercent, null);
//		
//		listMapAlleleDist.add( mapAlleleDist);
//		listStack.add(scores1);
//		return listStack; 
//		
//		/*
//		//return getMinArea(chr,poslist, sPhenotype, binsPhenotype, mapAlleleDist, minCountPercent, null);
//		
//		int usescore=2; // maxdistinctarea;
//
//		List<Stack[]> listStacks=new ArrayList();
//		Map<Position,String> mapPos2Sameallele=null;
//		int iter=0;
//		while(mapPos2Sameallele==null || !mapPos2Sameallele.isEmpty()) {
//
//			Map mapAlleleDist[]=new HashMap[7];
//			 
//			Stack[] scores1 = getMinArea(chr,poslist, sPhenotype, binsPhenotype, mapAlleleDist, minCountPercent, null);
//			listStacks.add(scores1);
//			listMapAlleleDist.add(mapAlleleDist);
//			Stack stack=scores1[usescore];
//			ScoreFeature topscore= (ScoreFeature )stack.peek();
//
//			String gen1= (String)topscore.getFeature1();
//			String gen2= (String)topscore.getFeature2();
//			if(gen1.length()!=poslist.size() || gen2.length()!=poslist.size() ) throw new RuntimeException("gen1.length()!=poslist.size() " + poslist.size() +"," + gen1.length() + "," + gen2.length());
//			mapPos2Sameallele=new TreeMap();
//			for(int i=0;i<gen1.length(); i++) {
//				if(gen1.charAt(i)==gen2.charAt(i)) {
//					mapPos2Sameallele.put( (Position)poslist.get(i), String.valueOf(gen1.charAt(i)));
//				}
//			}
//			poslist.removeAll( mapPos2Sameallele.keySet() );
//			AppContext.debug("iter=" + iter + " mapPos2Sameallele:" + mapPos2Sameallele + "  newposlist=" + poslist);
//			
//			
//			
//			if(poslist.isEmpty()) break;
//			iter++;
//		}
//		*/
//		
//		/*
//		List listScores=new ArrayList();
//		AppContext.debug("top scores for " + score.getName());
//		for(int i=0; i<stack.size(); i++) {
//			ScoreFeature scorei= (ScoreFeature)stack.get(i);
//			listScores.add( scorei.getScore());
//			AppContext.debug(i + " " + scorei.getScore() + "  " +scorei.getFeature1() + "  " + scorei.getFeature2());
//		}
//		mapTops.put(score.getName(), listScores);
//		*/
//		//return null;
//
//		
//	}
//	
	
	private Stack[] calcGenotypeMinarea( List poslist,  Map mapGenotype2Phenotype2Count, Map mapGenotype2Phenotype2Frequency, int binsPhenotype, int minCountPercent) {
		float minarea=Float.MAX_VALUE;
		 String minAllele1=null;
		 String minAllele2=null;

		 float maxmeandif=0;
		 String maxAllele1=null;
		 String maxAllele2=null;

		 float maxareadist=0;
		 String maxAreaAllele1=null;
		 String maxareaAllele2=null;
		 
		 // get allowed genotypes
		 float totalcount4allgen=0;
		 //Map mapGenotype2Phenotype2Count=mapAlleleDist[1];
		 Map mapGenotype2Varcount=new HashMap();
		 Iterator itGenotype=mapGenotype2Phenotype2Count.keySet().iterator();
		 while(itGenotype.hasNext()) {
			 Object genot=itGenotype.next();
			 Map mapPhe2Count= (Map)mapGenotype2Phenotype2Count.get(genot);
			 // get total count\
			 Iterator itCount=mapPhe2Count.values().iterator();
			 float totalcount4gen=0;
			 while(itCount.hasNext()) {
				 Object cnt=itCount.next();
				 if(cnt instanceof Collection) totalcount4gen += ((Collection)cnt).size();
				 else totalcount4gen += ((Number)cnt).floatValue();
			 }
			 totalcount4allgen+=totalcount4gen;
			 mapGenotype2Varcount.put(genot, totalcount4gen);
		 }
		 
		 float threspercent = (float)1.0/(2^(poslist.size()-1));

		 float includedVars=0;
		 Set setIncludeGen=new HashSet();
		 itGenotype=mapGenotype2Phenotype2Count.keySet().iterator();
		 while(itGenotype.hasNext()) {
			 Object genot=itGenotype.next();
			 float gencount = (Float)mapGenotype2Varcount.get(genot);
			 if(gencount*100/(totalcount4allgen*threspercent)>=minCountPercent) {
				 setIncludeGen.add(genot);
				 includedVars+=gencount;
			 }
		 }
		 
		 if(setIncludeGen.isEmpty()) return new Stack[6];
		 
		 AppContext.debug(setIncludeGen.size() + "/" +  mapGenotype2Phenotype2Count.size() + " genotypes, " +includedVars+"/" + totalcount4allgen + " varieties included, " + totalcount4allgen + ", genotype: " + setIncludeGen);
		 
		 
		
		 
		 Set alleles=new TreeSet(mapGenotype2Phenotype2Frequency.keySet());
		 Iterator itAl1=alleles.iterator();
		 Set alleles2=new TreeSet(mapGenotype2Phenotype2Frequency.keySet());
		 
		 String class1_gen=null;
		 String class2_gen=null;
		 

			
		Stack queueMinarea=new LimitedFIFOStack(5);
		Stack queueMaxMeandif=new LimitedFIFOStack(5);
		Stack queueMaxAreadistinct=new LimitedFIFOStack(5);
			 
			
		 long comparecount=0;
		 while(itAl1.hasNext()) {
			 String al1=(String)itAl1.next();
			 
			// AppContext.debug("al1=" + al1);
			 if(!setIncludeGen.contains(al1)) continue;
			 if(al1.replace("?","").isEmpty()) continue;
			 
			 
			 //if(al1.equals("?")) continue;
			 
			 
			 Iterator itAl2=alleles2.iterator();
			 while(itAl2.hasNext()) {
				 String al2=(String)itAl2.next();

				 if(!setIncludeGen.contains(al2)) continue;
				 //if(al2.equals("?")) continue;
				 if(al2.replace("?","").isEmpty()) continue;
				 
				 if(al1.compareTo(al2)>=0) continue;
				 
				 //AppContext.debug(al1 + "<" + al2 + " ..compare");
				 
				 Map[] mapBins = createBin( (Map)mapGenotype2Phenotype2Frequency.get(al1), (Map)mapGenotype2Phenotype2Frequency.get(al2), binsPhenotype);
				 
				 Float[] areameans = calculateOverlapArea(mapBins[1], mapBins[2]);
				 
				 float areaover = Math.abs(areameans[0]);
				 float areadisminusover = areameans[3]-areaover;
				 float meandif=Math.abs(areameans[1]-areameans[2]);
				 if(areaover<minarea) {
					 minarea=areaover;
					 minAllele1=al1;
					 minAllele2=al2;
					 AppContext.debug("new minoverarea=" + minarea + " minAllele1=" + minAllele1 +" minAllele2=" + minAllele2);
					 
					 queueMinarea.push(new ScoreFeature("minoverlap", Float.valueOf(minarea), minAllele1, minAllele2, poslist));
					 
				 }
				 
				 if(areadisminusover>maxareadist) {
					 maxareadist=areadisminusover;
					 maxAreaAllele1=al1;
					 maxareaAllele2=al2;
					 AppContext.debug("new maxdistnct area=" + maxareadist + " maxAreaAllele1=" + maxAreaAllele1 +" maxAreaAllele2=" + maxareaAllele2);
					 queueMaxMeandif.push(new ScoreFeature("maxdistarea", Float.valueOf(maxmeandif), maxAreaAllele1, maxareaAllele2, poslist));
					 
				 }
				 
				 if(meandif>maxmeandif) {
					 maxmeandif=meandif;
					 maxAllele1=al1;
					 maxAllele2=al2;
					 if(areameans[1]<areameans[2]) {
						 class1_gen=maxAllele1;
						 class2_gen=maxAllele2;
					 } else {
						 class1_gen=maxAllele2;
						 class2_gen=maxAllele1;
					 }
					 AppContext.debug("new maxdif=" + maxmeandif + " maxAllele1=" + maxAllele1 +" maxAllele2=" + maxAllele2);
					 queueMaxAreadistinct.push(new ScoreFeature("maxmeandif", Float.valueOf(maxareadist), maxAllele1, maxAllele2, poslist));

				 }
				 comparecount++;
			 }
		 }
		 //ScoreFeature[] scores=new ScoreFeature[3];
		 //scores[0]=new ScoreFeature("minoverlap", Float.valueOf(minarea), minAllele1, minAllele2, poslist);
		 //scores[1]=new ScoreFeature("maxmeandif", Float.valueOf(maxmeandif), maxAllele1, maxAllele2, poslist);
		 //scores[2]=new ScoreFeature("maxdistarea", Float.valueOf(maxareadist), maxAreaAllele1, maxareaAllele2, poslist);
		 
		 AppContext.debug(mapGenotype2Phenotype2Frequency.size() + " genotypes, " +  poslist.size() + " positions, " +  alleles.size() + " genotypes, " + comparecount + " pairs, (minarea al1 al2 maxdif al1 al2 maxdist al1 al2), " + minarea + "," + minAllele1 +   "," + minAllele2 + 
				 ","+maxmeandif + "," + maxAllele1 +   "," + maxAllele2 +"," + maxareadist + "," + maxAreaAllele1+"," + maxareaAllele2);
		 
		 Stack queueNvarsAll= new LimitedFIFOStack(5);
		 Stack queueNvars1= new LimitedFIFOStack(5);
		 Stack queueNvars2= new LimitedFIFOStack(5);
		 queueNvars1.push(new ScoreFeature("vars_class1",  (Float)mapGenotype2Varcount.get(class1_gen), maxAreaAllele1, maxareaAllele2, poslist));
		 queueNvars2.push(new ScoreFeature("vars_class2",  (Float)mapGenotype2Varcount.get(class2_gen), maxAreaAllele1, maxareaAllele2, poslist));
		 queueNvarsAll.push(new ScoreFeature("vars_allgenotypes", Float.valueOf(totalcount4allgen), null,null,null));
		 return new Stack[] {queueMinarea,queueMaxMeandif,queueMaxAreadistinct, queueNvarsAll,queueNvars1, queueNvars2};
	}
	
	@Override
	public List[] getMinArea(String chr, List poslistInit, String sPhenotype,
			Integer binsPhenotype, Integer minCountPercent, Integer nfeatures) {
		// TODO Auto-generated method stub

		//variety.getMapId2Variety(VarietyFacade.DATASET_SNPINDELV2_IUPAC).values() 

		/*
		List listt=new ArrayList();
		listt.addAll(poslist);
		 List randPos = selectRandomPos(listt, Math.min(poslist.size(),maxFeatures));
		 randPos=poslist;
		 */
		
		
		 AppContext.debug("using " + poslistInit.size() + " positions");
		
		 Map mapVarid2GenotypeInit=new HashMap();
		 Map mapVarid2Phenotype=new HashMap();

		 List<Map[]> listAlleleDist=new ArrayList();
		 List<Stack[]> listScoreStack=new ArrayList();

		 int iter=0;
		 //for(iter=0; iter<poslistInit.size(); iter++ ){
		 for(iter=0; iter< (nfeatures!=null?nfeatures:poslistInit.size()); iter++ ){

			 List poslist=new ArrayList();
			 poslist.addAll(poslistInit);
			 Map mapAlleleDist[] = createAlleleDistributions(chr,  poslist,  sPhenotype, null,true, mapVarid2GenotypeInit, mapVarid2Phenotype, (iter==0?poslistInit.size():iter));
				 
			 Map genotype2phenotypefreq= mapAlleleDist[3];
			 Map genotype2phenotypecount =  mapAlleleDist[1];
			 
			 Stack  scores[] = calcGenotypeMinarea(  poslist, genotype2phenotypecount, genotype2phenotypefreq,  binsPhenotype,  minCountPercent );
			 
			 listAlleleDist.add(mapAlleleDist);
			 listScoreStack.add(scores);
		 }		 

		 return new List[] {listAlleleDist, listScoreStack};
		 
		 
		 /*
		 float minarea=Float.MAX_VALUE;
		 String minAllele1=null;
		 String minAllele2=null;

		 float maxmeandif=0;
		 String maxAllele1=null;
		 String maxAllele2=null;

		 float maxareadist=0;
		 String maxAreaAllele1=null;
		 String maxareaAllele2=null;
		 
		 // get allowed genotypes
		 float totalcount4allgen=0;
		 Map mapGenotype2Phenotype2Count=mapAlleleDist[1];
		 Map mapGenotype2Varcount=new HashMap();
		 Iterator itGenotype=mapGenotype2Phenotype2Count.keySet().iterator();
		 while(itGenotype.hasNext()) {
			 Object genot=itGenotype.next();
			 Map mapPhe2Count= (Map)mapGenotype2Phenotype2Count.get(genot);
			 // get total count\
			 Iterator itCount=mapPhe2Count.values().iterator();
			 float totalcount4gen=0;
			 while(itCount.hasNext()) {
				 Object cnt=itCount.next();
				 if(cnt instanceof Collection) totalcount4gen += ((Collection)cnt).size();
				 else totalcount4gen += ((Number)cnt).floatValue();
			 }
			 totalcount4allgen+=totalcount4gen;
			 mapGenotype2Varcount.put(genot, totalcount4gen);
		 }
		 
		 float threspercent = (float)1.0/(2^(poslist.size()-1));

		 float includedVars=0;
		 Set setIncludeGen=new HashSet();
		 itGenotype=mapGenotype2Phenotype2Count.keySet().iterator();
		 while(itGenotype.hasNext()) {
			 Object genot=itGenotype.next();
			 float gencount = (Float)mapGenotype2Varcount.get(genot);
			 if(gencount*100/(totalcount4allgen*threspercent)>=minCountPercent) {
				 setIncludeGen.add(genot);
				 includedVars+=gencount;
			 }
		 }
		 
		 if(setIncludeGen.isEmpty()) return new Stack[6];
		 
		 AppContext.debug(setIncludeGen.size() + "/" +  mapGenotype2Phenotype2Count.size() + " genotypes, " +includedVars+"/" + totalcount4allgen + " varieties included, " + totalcount4allgen + ", genotype: " + setIncludeGen);
		 
		 
		 
		 //Map mapGenotype2Phenotype2Frequency=mapAlleleDist[3];
		 
		 Set alleles=new TreeSet(mapGenotype2Phenotype2Frequency.keySet());
		 Iterator itAl1=alleles.iterator();
		 Set alleles2=new TreeSet(mapGenotype2Phenotype2Frequency.keySet());
		 
		 String class1_gen=null;
		 String class2_gen=null;
		 
		 long comparecount=0;
		 while(itAl1.hasNext()) {
			 String al1=(String)itAl1.next();
			 
			// AppContext.debug("al1=" + al1);
			 if(!setIncludeGen.contains(al1)) continue;
			 if(al1.replace("?","").isEmpty()) continue;
			 
			 
			 //if(al1.equals("?")) continue;
			 
			 
			 Iterator itAl2=alleles2.iterator();
			 while(itAl2.hasNext()) {
				 String al2=(String)itAl2.next();

				 if(!setIncludeGen.contains(al2)) continue;
				 //if(al2.equals("?")) continue;
				 if(al2.replace("?","").isEmpty()) continue;
				 
				 if(al1.compareTo(al2)>=0) continue;
				 
				 //AppContext.debug(al1 + "<" + al2 + " ..compare");
				 
				 Map[] mapBins = createBin( (Map)mapGenotype2Phenotype2Frequency.get(al1), (Map)mapGenotype2Phenotype2Frequency.get(al2), binsPhenotype);
				 
				 Float[] areameans = calculateOverlapArea(mapBins[1], mapBins[2]);
				 
				 float areaover = Math.abs(areameans[0]);
				 float areadisminusover = areameans[3]-areaover;
				 float meandif=Math.abs(areameans[1]-areameans[2]);
				 if(areaover<minarea) {
					 minarea=areaover;
					 minAllele1=al1;
					 minAllele2=al2;
					 AppContext.debug("new minoverarea=" + minarea + " minAllele1=" + minAllele1 +" minAllele2=" + minAllele2);
					 
					 queueMinarea.push(new ScoreFeature("minoverlap", Float.valueOf(minarea), minAllele1, minAllele2, poslist));
					 
				 }
				 
				 if(areadisminusover>maxareadist) {
					 maxareadist=areadisminusover;
					 maxAreaAllele1=al1;
					 maxareaAllele2=al2;
					 AppContext.debug("new maxdistnct area=" + maxareadist + " maxAreaAllele1=" + maxAreaAllele1 +" maxAreaAllele2=" + maxareaAllele2);
					 queueMaxMeandif.push(new ScoreFeature("maxdistarea", Float.valueOf(maxmeandif), maxAreaAllele1, maxareaAllele2, poslist));
					 
				 }
				 
				 if(meandif>maxmeandif) {
					 maxmeandif=meandif;
					 maxAllele1=al1;
					 maxAllele2=al2;
					 if(areameans[1]<areameans[2]) {
						 class1_gen=maxAllele1;
						 class2_gen=maxAllele2;
					 } else {
						 class1_gen=maxAllele2;
						 class2_gen=maxAllele1;
					 }
					 AppContext.debug("new maxdif=" + maxmeandif + " maxAllele1=" + maxAllele1 +" maxAllele2=" + maxAllele2);
					 queueMaxAreadistinct.push(new ScoreFeature("maxmeandif", Float.valueOf(maxareadist), maxAllele1, maxAllele2, poslist));

				 }
				 comparecount++;
			 }
		 }
		 //ScoreFeature[] scores=new ScoreFeature[3];
		 //scores[0]=new ScoreFeature("minoverlap", Float.valueOf(minarea), minAllele1, minAllele2, poslist);
		 //scores[1]=new ScoreFeature("maxmeandif", Float.valueOf(maxmeandif), maxAllele1, maxAllele2, poslist);
		 //scores[2]=new ScoreFeature("maxdistarea", Float.valueOf(maxareadist), maxAreaAllele1, maxareaAllele2, poslist);
		 
		 AppContext.debug(mapGenotype2Phenotype2Frequency.size() + " genotypes, " +  poslist.size() + " positions, " +  alleles.size() + " genotypes, " + comparecount + " pairs, (minarea al1 al2 maxdif al1 al2 maxdist al1 al2), " + minarea + "," + minAllele1 +   "," + minAllele2 + 
				 ","+maxmeandif + "," + maxAllele1 +   "," + maxAllele2 +"," + maxareadist + "," + maxAreaAllele1+"," + maxareaAllele2);
		 
		 Stack queueNvarsAll= new LimitedFIFOStack(5);
		 Stack queueNvars1= new LimitedFIFOStack(5);
		 Stack queueNvars2= new LimitedFIFOStack(5);
		 queueNvars1.push(new ScoreFeature("vars_class1",  (Float)mapGenotype2Varcount.get(class1_gen), maxAreaAllele1, maxareaAllele2, poslist));
		 queueNvars2.push(new ScoreFeature("vars_class2",  (Float)mapGenotype2Varcount.get(class2_gen), maxAreaAllele1, maxareaAllele2, poslist));
		 queueNvarsAll.push(new ScoreFeature("vars_allgenotypes", Float.valueOf(totalcount4allgen), null,null,null));
	
		 return new Stack[] {queueMinarea,queueMaxMeandif,queueMaxAreadistinct, queueNvarsAll,queueNvars1, queueNvars2};
		 
		 */
	}
	
	/**
	 * 
	 * @param mapPhen2Count1
	 * @param mapPhen2Count2
	 * @param binsPhenotype
	 * @return Map phen value 2 bin number
	 */
	@Override
	public Map[] createBin(Map mapPhen2Count1, Map mapPhen2Count2, int binsPhenotype) {
		Set setPhen1 = new TreeSet(mapPhen2Count1.keySet());
		Set setPhen2 = new TreeSet(mapPhen2Count2.keySet());
		
		Map mapPhen2Bin=new HashMap();
		
		// get range
		Iterator itPhen = setPhen1.iterator();
		Number min1= (Number)itPhen.next();
		Number max1=min1;
		while(itPhen.hasNext()) {
			max1= (Number)itPhen.next();
		}
		
		itPhen = setPhen2.iterator();
		Number min2=(Number)itPhen.next();
		Number max2=min2;
		while(itPhen.hasNext()) {
			max2=(Number)itPhen.next();
		}
		
		float binmin=Math.min(min1.floatValue(), min2.floatValue());
		float binmax=Math.max(max1.floatValue(), max2.floatValue());
		
		//AppContext.debug("phenotype range: " + binmin + "-" + binmax );
				
		float binRange=binmax - binmin;
		float binWidth=binRange/binsPhenotype;
		//AppContext.debug("phenotype range: " + binmin + "-" + binmax  + "  binwidth=" + binWidth);

		
		Map<Integer,Float> mapBin1=new TreeMap();
		Map<Integer,Float> mapBin2=new TreeMap();
		Map<Integer,Float[]> mapBinValues=new TreeMap();
		
		int lastbin1=-1;
		int lastbin2=-1;
		int firstbin1=-1;
		int firstbin2=-1;
		
		for(int ibin=0; ibin<binsPhenotype; ibin++) {

			float binval=0;
			float bincount=0;
			float ibinmin=binmin+ibin*binWidth;
			float ibinmax=binmin+(ibin+1)*binWidth;
			
			mapBinValues.put(ibin,new Float[]{ibinmin, ibinmax});
			
			itPhen = setPhen1.iterator();
			while(itPhen.hasNext()) {
				Object fphen=itPhen.next();
				float phen= ((Number)fphen).floatValue();
				if(phen>=ibinmax) break; 
				if(phen<ibinmin) continue;
				
				Object ophencnt=mapPhen2Count1.get(fphen);
				float phencount = 0;
				if(ophencnt instanceof Collection)
					phencount= ((Collection)ophencnt).size();
				else 
					phencount=((Number)ophencnt).floatValue();
					
				bincount+=phencount;
				binval+=phen;
				
				lastbin1=ibin;
				if(firstbin1<0) firstbin1=ibin;
			}
			mapBin1.put(ibin,Float.valueOf(bincount));
			
			if(AppContext.isEqual(0, bincount)) AppContext.debug("area=0 for " + mapPhen2Count1);
			
			binval=0;
			bincount=0;
			itPhen = setPhen2.iterator();
			while(itPhen.hasNext()) {
				Object fphen=itPhen.next();
				float phen= ((Number)fphen).floatValue();
				if(phen>=ibinmax) break; 
				if(phen<ibinmin) continue;

				Object ophencnt=mapPhen2Count2.get(fphen);
				float phencount = 0;
				if(ophencnt instanceof Collection)
					phencount= ((Collection)ophencnt).size();
				else 
					phencount=((Number)ophencnt).floatValue();

				bincount+=phencount;
				binval+=phen;
				lastbin2=ibin;
				if(firstbin2<0) firstbin2=ibin;
			}
			mapBin2.put(ibin,Float.valueOf(bincount));
			
			if(AppContext.isEqual(0, bincount)) AppContext.debug("area=0 for " + mapPhen2Count2);

		}
		
		// interpolate and normalize bins
		/*
		Map<Integer,Float> mapZeroXY=null;
		Integer lastnonzerobin=null;
		for(int ibin=0; ibin<binsPhenotype; ibin++) {
			if( AppContext.isEqual(mapBin1.get(ibin),0.0) && ibin>firstbin1 && ibin<lastbin1) {
				if(mapZeroXY==null) mapZeroXY=new TreeMap();
				Float[] binvals=mapBinValues.get(ibin);
				mapZeroXY.put(ibin, (binvals[0]+binvals[1])/2 );
			} else {
				if(mapZeroXY!=null) {
					// interpolate past values
					Float lastx[] = mapBinValues.get(lastnonzerobin);
					Float curx[] = mapBinValues.get(ibin);
					float slope = (mapBin1.get(ibin)-mapBin1.get(lastnonzerobin))/( ((curx[0]+curx[1])/2)-((lastx[0]+lastx[1])/2));
					
					Iterator<Integer> itZerobins=mapZeroXY.keySet().iterator();
					while(itZerobins.hasNext()) {
						Integer zerobin=itZerobins.next();
						float newy=  mapBin1.get(lastnonzerobin) + slope*mapZeroXY.get(zerobin);
						mapBin1.put(zerobin , newy);
					}
					mapZeroXY=null;
				}
				else lastnonzerobin=ibin;
			}
		}
		mapZeroXY=null;
		lastnonzerobin=null;
		for(int ibin=0; ibin<binsPhenotype; ibin++) {
			if( AppContext.isEqual(mapBin2.get(ibin),0.0) && ibin>firstbin2 && ibin<lastbin2) {
				if(mapZeroXY==null) mapZeroXY=new TreeMap();
				Float[] binvals=mapBinValues.get(ibin);
				mapZeroXY.put(ibin, (binvals[0]+binvals[1])/2 );
			} else {
				if(mapZeroXY!=null) {
					// interpolate past values
					Float lastx[] = mapBinValues.get(lastnonzerobin);
					Float curx[] = mapBinValues.get(ibin);
					float slope = (mapBin2.get(ibin)-mapBin2.get(lastnonzerobin))/( ((curx[0]+curx[1])/2)-((lastx[0]+lastx[1])/2));
					
					Iterator<Integer> itZerobins=mapZeroXY.keySet().iterator();
					while(itZerobins.hasNext()) {
						Integer zerobin=itZerobins.next();
						float newy=  mapBin2.get(lastnonzerobin) + slope*mapZeroXY.get(zerobin);
						mapBin2.put(zerobin , newy);
					}
					mapZeroXY=null;
				}
				else lastnonzerobin=ibin;
			}
		}
		*/
		//normalize bins
		/*
		Float floatzero=Float.valueOf(0);
		float total=0;
		Iterator<Float> itVals = mapBin1.values().iterator();
		while(itVals.hasNext())
			total+= itVals.next();
		
		Map<Integer,Float> norm1 =new HashMap();
		Iterator<Integer> itBin = mapBin1.keySet().iterator();
		while(itBin.hasNext()) {
			Integer bin=itBin.next();
			
			if(Float.isNaN( mapBin1.get(bin)) || Float.isNaN(total) || AppContext.isEqual(0, total)) norm1.put(bin, floatzero);
			else norm1.put(bin, mapBin1.get(bin)/total);
		}
		
		total=0;
		itVals = mapBin2.values().iterator();
		while(itVals.hasNext())
			total+= itVals.next();
		
		
		Map<Integer,Float> norm2 =new HashMap();
		itBin = mapBin2.keySet().iterator();
		while(itBin.hasNext()) {
			Integer bin=itBin.next();
			if(Float.isNaN( mapBin2.get(bin)) ||  Float.isNaN(total) || AppContext.isEqual(0, total)) norm2.put(bin, floatzero);
			else norm2.put(bin, mapBin2.get(bin)/total);
		}
		*/
		
		Map norm1=mapBin1;
		Map norm2=mapBin2;
		//AppContext.debug( "norm1=" + norm1.toString());
		//AppContext.debug( "norm2=" + norm2.toString());
		
		//Iterator itPhen = setPhen1.iterator();
		return new Map[]{mapBinValues, norm1, norm2};
	}
	
	@Override
	public Float[] calculateOverlapArea(Map<Integer,Float> mapBin2Count1, Map<Integer,Float> mapBin2Count2) {
		
		
		
		float mean1=0;
		float mean2=0;
		float sumx=0;
		float sumxy1=0;
		float sumxy2=0;
		float areaover=0;
		
		float areadistinct=0;
		
		//AppContext.debug(mapBin2Count1.size() + " bins");
		int binsPhenotype=mapBin2Count1.size();
		if(binsPhenotype!=mapBin2Count2.size()) throw new RuntimeException("binsPhenotype!=mapPhen2Count2.size()");
		for(int ibin=0; ibin<binsPhenotype; ibin++) {
			Float val1=mapBin2Count1.get(ibin);
			Float val2=mapBin2Count2.get(ibin);
			if(val1!=null)
				sumxy1+=ibin*val1;
			if(val2!=null)
				sumxy2+=ibin*val2;
			
			if(val1!=null && val2!=null) {
				areaover+= Math.min(mapBin2Count1.get(ibin),mapBin2Count2.get(ibin));
				areadistinct += Math.abs(mapBin2Count1.get(ibin)-mapBin2Count2.get(ibin) );
			}
			else if(val1!=null) {
				areaover+=val1;
				areadistinct += val1;
			}
			else if(val2!=null) {
				areaover+=val2;
				areadistinct += val1;
			}
			
			
			sumx+=ibin;
		}
		
		//AppContext.debug(areaover + "," + sumx + "," + sumxy1 + "," + sumxy2 +"," + areadistinct);
		
		return new Float[] {areaover, sumxy1/sumx,sumxy2/sumx, areadistinct };
	}
	
}
