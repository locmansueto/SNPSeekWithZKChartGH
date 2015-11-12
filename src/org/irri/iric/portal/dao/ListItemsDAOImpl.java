package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.domain.Cv;
//import org.irri.iric.portal.chado.oracle.dao.VGoOrganismDAO;
import org.irri.iric.portal.domain.CvTerm;
import org.irri.iric.portal.domain.Gene;
import org.irri.iric.portal.domain.Organism;
import org.irri.iric.portal.domain.Scaffold;
import org.irri.iric.portal.domain.Variety;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

//@Service(value={"ListItemsDAO","ListItemsDAOPostges"})
@Service("ListItems")
public class ListItemsDAOImpl implements  ListItemsDAO {

	@Autowired
	private CvDAO cvDAO; 

	
	@Autowired
	private GeneDAO geneDAO; 
	@Autowired
	@Qualifier("VarietyDAO")
	//@Qualifier("VarietyBasicprop2DAO")
	private VarietyDAO germdao;

	/*
	@Autowired
	@Qualifier("IricStockDAO")
	//@Qualifier("VIricstockBoxcodeDAO")
	private VarietyDAO iricstockdao;
	*/
	
	//@Autowired
	//@Qualifier("VCvPassportDAOPostges")
	private CvTermDAO cvtermsPassportdao;
	
	//@Autowired
	//@Qualifier("VCvPhenotypeDAOPostges")
	private CvTermDAO cvtermsPhenotypedao;

	@Autowired
	//@Qualifier("ScaffoldDAO")
	private ScaffoldDAO scaffolddao;

	@Autowired
	//@Qualifier("OrganismDAO")
	private OrganismDAO organismdao;

	//@Autowired
	//@Qualifier("VCvtermDbxrefDAO")
	//private CvTermDAO cvtermlocusdao;
	
	//@Autowired
	//private VLocusCvtermDAO cvtermlocusdao;
	
	//@Autowired
	//@Qualifier("VGoOrganismDAOPostges")
	private CvTermDAO gotermorganismdao;
	//private VGoOrganismDAO gotermorganismdao;
	
	
	//@Autowired
	//@Qualifier("VPatoOrganismDAOPostges")
	private CvTermDAO patotermorganismdao;

	
	
	private Map<String,List> mapOrganismScaffolds = new HashMap();
	private Map<String,List> mapCVOrg2Cvterms = new HashMap();
	
	//private java.util.List<String> genenames;
	private Map<String,Collection> mapOrg2Loci = new HashMap();
	
	private java.util.List germnames;
	private java.util.List countries;
	private java.util.List subpopulations;
	private java.util.List irisid;
	
	private java.util.Map<String,Variety> mapVarname2Variety; 
	private Map<BigDecimal, Variety> mapId2Variety; 
	
	private Map<String,BigDecimal> passportDefinitions;
	private Map<String,BigDecimal> phenotypeDefinitions;

	
	public static boolean lockGenenameReader = false;
	public static boolean lockVarietyReader = false;
	
	
	//private Map<String,Organism> mapOrgname2Org;
	
	/**
	 * Generate all variety name lists
	 */
	private void initNames() {
		
		if(lockVarietyReader) {
			try {
				while(true) {
				 Thread.sleep(5000);
				 if(!lockVarietyReader) return;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		
		
		lockVarietyReader = true;
		java.util.Set<String> germnames= new java.util.TreeSet<String>();
		java.util.Set<String> countries= new java.util.TreeSet<String>();
		java.util.Set<String> subpopulations= new java.util.TreeSet<String>();
		java.util.Set<String> irisid= new java.util.TreeSet<String>();
		
		
		mapVarname2Variety = new java.util.HashMap<String, Variety>() ;
		mapId2Variety = new java.util.HashMap<BigDecimal, Variety>() ;
		
		int germcount = 0;
		
		Set setvars = germdao.findAllVariety();
		AppContext.info(setvars.size() + " vars from iricstock_basicprop");
		java.util.Iterator<Variety> itgerm =  setvars.iterator();
		
		
		while( itgerm.hasNext() )	
		{
			Variety germ = itgerm.next();
			if(germ==null) throw new RuntimeException("germ==null");

			mapId2Variety.put(germ.getVarietyId(), germ);
			germcount++;
			
			if(germ.getName()==null)
				{
					AppContext.info("variety.getVarnameOfGenStockSrc()==null");
					continue;					
				}

			mapVarname2Variety.put(germ.getName().toUpperCase(), germ);
			
			if(germ.getCountry()!=null) // throw new RuntimeException("germ..getCountry()==null");
			{
				//countries.add( germ.getCountry().toLowerCase() );
				countries.add( germ.getCountry().toUpperCase() );	
			}
			
			
			if(germ.getSubpopulation()!=null) // throw new RuntimeException("germ..getSubpopulation()==null");
			{
				subpopulations.add( germ.getSubpopulation().toLowerCase() );
				//subpopulations.add( germ.getSubpopulation().toUpperCase() );	
			}
			
			if(germ.getIrisId()!=null) // throw new RuntimeException("germ..getSubpopulation()==null");
			{
				//irisid.add( germ.getIrisId().toLowerCase() );
				irisid.add( germ.getIrisId().toUpperCase() );	
			}

			//germnames.add( germ.getName().toLowerCase() );
			germnames.add( germ.getName().toUpperCase() );		
	
		}
		/*
		if(germnames.size()<3000) {
		
			setvars = iricstockdao.findAllVariety();
			AppContext.info(setvars.size() + " vars from iricstock");
			itgerm =  setvars.iterator();
			while( itgerm.hasNext() )	
			{
				Variety germ = itgerm.next();
				if(germ==null) throw new RuntimeException("germ==null");
	
				if(mapId2Variety.containsKey(germ.getVarietyId())) continue;
				
				//AppContext.debug(germ.getName() + "   " + germ.getVarietyId() + "  added");
				
				germcount++;
				mapId2Variety.put(germ.getVarietyId(), germ);
				
				if(germ.getName()==null)
					{
						AppContext.debug("germ..getVarnameOfGenStockSrc()==null");
						continue;					
					}
	
				mapVarname2Variety.put(germ.getName().toUpperCase(), germ);
				//germnames.add( germ.getName().toLowerCase() );
				germnames.add( germ.getName().toUpperCase() );		
			}	
		
		}
		*/
		
		AppContext.info(mapId2Variety.size() + " variety Ids;  " + germnames.size()/2 + "  names");
		
		this.germnames = new java.util.ArrayList();
			this.germnames.addAll(germnames);
		this.countries =  new java.util.ArrayList();
			this.countries.addAll(countries);
		this.subpopulations =  new java.util.ArrayList();
			//this.subpopulations.add("");
			//this.subpopulations.add("all varieties");
			this.subpopulations.add("all indica");
			//this.subpopulations.add("ALL INDICA");
			this.subpopulations.add("all japonica");
			//this.subpopulations.add("ALL JAPONICA");
		    this.subpopulations.addAll(subpopulations);

		this.irisid =  new java.util.ArrayList();
			this.irisid.addAll(irisid);
			
		lockVarietyReader = false;			
	}
	
	@Override
	public List<String> getGenenames() {
		// TODO Auto-generated method stub
		
		return getGenenames(AppContext.getDefaultOrganism());
	}
	
	@Override
	public List<String> getGenenames(String organism) {
		// TODO Auto-generated method stub
		
		while(lockGenenameReader) {
			try {
			 Thread.sleep(5000); 
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		
		Collection genenames = mapOrg2Loci.get(organism);

		if(genenames==null || genenames.size()==0) {
			lockGenenameReader = true;

			genenames = new java.util.ArrayList();
			geneDAO = (GeneDAO)AppContext.checkBean(geneDAO, "GeneDAO");
				
			java.util.Iterator<Gene> it = geneDAO.findAllGene(getOrganismByName(organism).getOrganismId().intValue()).iterator();
		    while(it.hasNext()) {
		    	Gene gene = it.next();
		    	//String genename = gene.getUniquename();
		    	//genenames.add(genename.toUpperCase());
		    	genenames.add( gene.getUniquename().toLowerCase());
		    }
		    mapOrg2Loci.put(organism , genenames);
			
			/*
			genenames = new java.util.ArrayList();
			geneDAO = (GeneDAO)AppContext.checkBean(geneDAO, "GeneDAO");
				
			java.util.Iterator<Gene> it = geneDAO.findAllGene(getOrganismByName(organism).getOrganismId().intValue()).iterator();
		    while(it.hasNext()) {
		    	Gene gene = it.next();
		    	//String genename = gene.getUniquename();
		    	//genenames.add(genename.toUpperCase());
		    	genenames.add( gene.getUniquename().toLowerCase());
		    }
		    */
			
		    lockGenenameReader = false;
		}
		
		AppContext.debug(genenames.size() + " loci for " + organism);
		
		return (List)genenames;
	}
	
	
	
	@Override
	public Gene findGeneFromName(String  genename, String organism) {
		geneDAO = (GeneDAO)AppContext.checkBean(geneDAO, "GeneDAO");
		return geneDAO.findGeneByName(genename, getOrganismByName(organism).getOrganismId().intValue());
	}

	
	@Override
	public Map getIrisId2Variety() {
	
		java.util.Map irisid= new java.util.HashMap();
		
		Set setvars = germdao.findAllVariety();
		java.util.Iterator<Variety> itgerm =  setvars.iterator();
		
		while( itgerm.hasNext() )	
		{
			Variety germ = itgerm.next();
			if(germ==null) throw new RuntimeException("germ==null");
			if(germ.getIrisId()!=null) // throw new RuntimeException("germ..getSubpopulation()==null");
				irisid.put( germ.getIrisId().toUpperCase() , germ);	
		}
		return irisid;
	}
	
	@Override
	public java.util.List getVarietyNames() {
		if(germnames==null || germnames.size()==0) initNames();
		return germnames;
	}
	
	@Override
	public java.util.List getIrisIds() {
		if(irisid==null) initNames();
		return irisid;		
	}
	
	@Override
	public Variety getGermplasmByIrisId(String name)
	{
			return germdao.findVarietyByIrisId(name);
	}
	
	@Override
	public Variety getGermplasmByNameLike(String name) {
		
		Variety var =  germdao.findVarietyByNameLike(name);
		if(var==null) {
			//var = iricstockdao.findVarietyByNameLike(name);
		}
		return var;	
	}
	
	@Override
	public Variety getGermplasmByName(String name) {
	
		Variety var = germdao.findVarietyByName(name);
		if(var==null) {
			//var= iricstockdao.findVarietyByName(name);
		}
		return var;
	}
	
	/*	
	@Override
	public List getGermplasmsByNameOrIrisid(String names) {
		// TODO Auto-generated method stub
		//irisIds = irisIds.replace("_"," ");
		
		
		//return germdao.findVarietyByNameOrIrisId(String names,  irisIds);
		return null;
	}
	*/
	
	@Override
	public java.util.Set getGermplasmByCountry(String country) {		
		return  germdao.findAllVarietyByCountry(country);	
	}
	
	@Override
	public java.util.Set getGermplasmBySubpopulation(String subpopulation) {
		
		
		if(subpopulation.toUpperCase().equals("ALL INDICA"))
		{
			Set allvar = new LinkedHashSet();
			/*
			allvar.addAll( germdao.findAllVarietyBySubpopulation("ind1")) ;	
			allvar.addAll( germdao.findAllVarietyBySubpopulation("ind2")) ;
			allvar.addAll( germdao.findAllVarietyBySubpopulation("ind3")) ;
			allvar.addAll( germdao.findAllVarietyBySubpopulation("indx")) ;
			*/
			
			allvar.addAll( germdao.findAllVarietyBySubpopulation("ind1A")) ;
			allvar.addAll( germdao.findAllVarietyBySubpopulation("ind1B")) ;
			allvar.addAll( germdao.findAllVarietyBySubpopulation("ind2")) ;
			allvar.addAll( germdao.findAllVarietyBySubpopulation("ind3")) ;
			allvar.addAll( germdao.findAllVarietyBySubpopulation("indx")) ;
			
			return allvar;
		}
		else if(subpopulation.toUpperCase().equals("ALL JAPONICA")) {
			Set allvar = new LinkedHashSet();
			/*
			allvar.addAll( germdao.findAllVarietyBySubpopulation("temp")) ;	
			allvar.addAll( germdao.findAllVarietyBySubpopulation("trop")) ;
			allvar.addAll( germdao.findAllVarietyBySubpopulation("temp/trop")) ;
			allvar.addAll( germdao.findAllVarietyBySubpopulation("trop/temp")) ;
			*/
			allvar.addAll( germdao.findAllVarietyBySubpopulation("temp")) ;	
			allvar.addAll( germdao.findAllVarietyBySubpopulation("trop1")) ;
			allvar.addAll( germdao.findAllVarietyBySubpopulation("trop2")) ;
			allvar.addAll( germdao.findAllVarietyBySubpopulation("japx")) ;
			return allvar;
		}
		else
			return  germdao.findAllVarietyBySubpopulation(subpopulation) ;	
	}


	@Override
	public Set getGermplasm() {
		// TODO Auto-generated method stub
		return germdao.findAllVariety() ;
	}


	@Override
	public java.util.List getCountries() {
		if(countries==null) initNames();
		return countries;
	}

	@Override
	public java.util.List getSubpopulations() {
		if(subpopulations==null) initNames();
		return subpopulations;
	}


	@Override
	public Set getGermplasmByExample(Variety germplasm) {
		// TODO Auto-generated method stub
		
		if(germplasm.getCountry()!=null && !germplasm.getCountry().isEmpty()  && germplasm.getSubpopulation()!=null &&  !germplasm.getSubpopulation().isEmpty() )
			return germdao.findAllVarietyByCountryAndSubpopulation(germplasm.getCountry(), germplasm.getSubpopulation()); // .findAllVarietyByExample(germplasm);

		if(germplasm.getCountry()!=null && !germplasm.getCountry().isEmpty())
			return getGermplasmByCountry( germplasm.getCountry() );
		
		if(germplasm.getSubpopulation()!=null && !germplasm.getSubpopulation().isEmpty())
			return getGermplasmBySubpopulation( germplasm.getSubpopulation() );
		return null;
	}

	@Override
	public java.util.Map<String, Variety> getMapVarname2Variety() {
		if(mapVarname2Variety==null) initNames();
		return mapVarname2Variety;
	}

	@Override
	public Map<BigDecimal, Variety> getMapId2Variety() {
		
		if(mapId2Variety==null) initNames();
		
		return mapId2Variety;
	}
	
	private void initMoreConstraints() {
		
		cvtermsPassportdao = (CvTermDAO)AppContext.checkBean(cvtermsPassportdao, "VCvPassportDAO");
		cvtermsPhenotypedao = (CvTermDAO)AppContext.checkBean(cvtermsPhenotypedao, "VCvPhenotypeDAO");
			
		List listCVPassport =  cvtermsPassportdao.getAllTerms();
		passportDefinitions = new TreeMap<String,BigDecimal>();
	//	passportDefinitions.put("", BigDecimal.ZERO);
		Iterator<CvTerm> itTerm=listCVPassport.iterator();
		while(itTerm.hasNext())
		{
			CvTerm term = itTerm.next();
			if(term.getDefinition().length()>100)				
				passportDefinitions.put(term.getDefinition().substring(0,99)+"...", term.getCvTermId());
			else
				passportDefinitions.put(term.getDefinition(), term.getCvTermId());
			
		}	
		//System.out.println("passportDefinitions");
		//System.out.println(passportDefinitions);
		
		List listCVPhenotype =  cvtermsPhenotypedao.getAllTerms();
		phenotypeDefinitions = new TreeMap<String,BigDecimal>();
//		phenotypeDefinitions.put("", BigDecimal.ZERO);
		Iterator<CvTerm>  itTerm2=listCVPhenotype.iterator();
		while(itTerm2.hasNext())
		{
			CvTerm term = itTerm2.next();
			phenotypeDefinitions.put(term.getDefinition(), term.getCvTermId());
		}	
		
	}
	
	@Override
	public  Map<String,BigDecimal>  getPhenotypeDefinitions() {
		// TODO Auto-generated method stub
		if(phenotypeDefinitions==null) initMoreConstraints();
		return phenotypeDefinitions;
	}
	
	@Override
	public Map<String,BigDecimal> getPassportDefinitions() {
		// TODO Auto-generated method stub
		if(passportDefinitions==null) initMoreConstraints();		
		return passportDefinitions;
	}

	
//	@Override
//	public Integer getFeatureLength(String feature) {
//		// TODO Auto-generated method stub
//		
//		/*
//		java.util.Map<String,Integer> chrLength = new java.util.HashMap<String,Integer>();		
//		chrLength.put("1", 43270923);
//		chrLength.put("2", 35937250);
//		chrLength.put("3",36413819);
//		chrLength.put("4",35502694);
//		chrLength.put("5",29958434);
//		chrLength.put("6",31248787);
//		chrLength.put("7",29697621);
//		chrLength.put("8",28443022);
//		chrLength.put("9",23012720);
//		chrLength.put("10",23207287);
//		chrLength.put("11",29021106);
//		chrLength.put("12",27531856);
//		
//		return chrLength.get(feature);
//		*/
//		
//		return getFeatureLength(feature, AppContext.getDefaultOrganism()).intValue();
//	}


	@Override
	public List getOrganisms() throws Exception {
		// TODO Auto-generated method stub
		organismdao = (OrganismDAO)AppContext.checkBean(organismdao, "OrganismDAO");
		Map mapOrgname2Org=organismdao.getMapName2Organism();
		
		return new ArrayList(mapOrgname2Org.values());
	}

	@Override
	public List getContigs(String organism) {
		// TODO Auto-generated method stub
		
		if(!mapOrganismScaffolds.containsKey(organism)) {
			
			scaffolddao = (ScaffoldDAO)AppContext.checkBean(scaffolddao, "ScaffoldDAO");
			Iterator<Scaffold> it = scaffolddao.getScaffolds( getOrganismByName(organism).getOrganismId() ).iterator(); 
			List contignames = new ArrayList();
			while(it.hasNext()) {
				contignames.add( it.next().getName()); 
			}
			mapOrganismScaffolds.put(organism, contignames);
		}
		List list = mapOrganismScaffolds.get(organism);
		AppContext.debug(list.size() + " contigs for " + organism);
		return list;
	}

	@Override
	public Long getFeatureLength(String feature, String organism) {
		// TODO Auto-generated method stub
		scaffolddao = (ScaffoldDAO)AppContext.checkBean(scaffolddao, "ScaffoldDAO");
		return scaffolddao.getScaffoldLength(feature,  getOrganismByName(organism).getOrganismId() );
	}
	
	private List createCvtermMap(List cvterms) {
		//Map mapCvid2Term=new LinkedHashMap();
		List terms=new ArrayList();
		Iterator<CvTerm> itCvterm=cvterms.iterator();
		while(itCvterm.hasNext()) {
			CvTerm cvterm=itCvterm.next();
			//mapCvid2Term.put( cvterm.getCvTermId(), cvterm.getName());
			terms.add(cvterm.getName());
		}
		return terms;
	}
		
	@Override
	public List getGOTermsWithLoci(String cv, String organism) {
		//cvtermlocusdao = (VLocusCvtermDAO)AppContext.checkBean(cvtermlocusdao, "VLocusCvtermDAO");
		//return cvtermlocusdao.getAllTerms(organism);
		
		List cvterms  = mapCVOrg2Cvterms.get(cv + "-" + organism);
		if(cvterms==null) {
			gotermorganismdao = (CvTermDAO)AppContext.checkBean(gotermorganismdao, "VGoOrganismDAO");
			//cvterms = gotermorganismdao.getAllTerms(cv, organism);
			cvterms=createCvtermMap(  gotermorganismdao.getAllTerms( this.getCvByName(cv).getCvId(), this.getOrganismByName(organism).getOrganismId()) );
			mapCVOrg2Cvterms.put(cv + "-" + organism, cvterms);
		}
		AppContext.debug("getting cv terms " +  cv + " for " +  organism);
		
		return cvterms;
	}
	
	
	@Override
	public List<String> getPATOTermsWithLoci(String cv, String organism) {
		// TODO Auto-generated method stub
		List cvterms  = mapCVOrg2Cvterms.get(cv + "-" + organism);
		if(cvterms==null) {
			patotermorganismdao = (CvTermDAO)AppContext.checkBean(patotermorganismdao, "VPatoOrganismDAO");
			//cvterms = patotermorganismdao.getAllTerms(cv, organism);
			AppContext.debug("get cvid for " + cv);
			if(patotermorganismdao==null) AppContext.debug("patotermorganismdao==null");
			if(getCvByName(cv)==null) AppContext.debug("getCvByName(cv)==null");
			List allterms = patotermorganismdao.getAllTerms( this.getCvByName(cv).getCvId(), this.getOrganismByName(organism).getOrganismId());
			if(allterms==null) AppContext.debug("allterms==null");
			
			cvterms = createCvtermMap( allterms );
			mapCVOrg2Cvterms.put(cv + "-" + organism, cvterms);
		}
		
		AppContext.debug("getting cv terms " +  cv + " for " +  organism);
		
		return cvterms;
	}

	@Override
	public Organism getOrganismByName(String name)   {
		
		organismdao = (OrganismDAO)AppContext.checkBean(organismdao, "OrganismDAO");
		Map<String, Organism> mapOrg = organismdao.getMapName2Organism();
		AppContext.debug("getting organism " + name + " from " + mapOrg);
		
		return mapOrg.get(name);
	}
	
	@Override
	public Organism getOrganismById(Integer id)   {
		
		organismdao = (OrganismDAO)AppContext.checkBean(organismdao, "OrganismDAO");
		return organismdao.getOrganismByID(id);
	}

	@Override
	public Cv getCvByName(String cv) {
		// TODO Auto-generated method stub
		cvDAO = (CvDAO)AppContext.checkBean(cvDAO, "CvDAO");
		return cvDAO.getMapName2Cv().get(cv);
	}
	
	
	
	

	
}
