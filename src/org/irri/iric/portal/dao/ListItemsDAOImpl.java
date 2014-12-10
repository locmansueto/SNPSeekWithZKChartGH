package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.domain.CvTerm;
import org.irri.iric.portal.domain.Gene;
import org.irri.iric.portal.domain.Variety;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("ListItemsDAO")
public class ListItemsDAOImpl implements  ListItemsDAO {

	@Autowired
	private GeneDAO geneDAO; 
	@Autowired
	@Qualifier("VarietyDAO")
	private VarietyDAO germdao;

	@Autowired
	@Qualifier("IricStockDAO")
	private VarietyDAO iricstockdao;
	
	@Autowired
	@Qualifier("VCvPassportDAO")
	private CvTermDAO cvtermsPassportdao;
	
	@Autowired
	@Qualifier("VCvPhenotypeDAO")
	private CvTermDAO cvtermsPhenotypedao;
	

	private java.util.List<String> genenames;
	
	private java.util.List germnames;
	private java.util.List countries;
	private java.util.List subpopulations;
	private java.util.List irisid;
	
	private java.util.Map<String,Variety> mapVarname2Variety; 
	private Map<BigDecimal, Variety> mapId2Variety; 
	
	private Map<String,BigDecimal> passportDefinitions;
	private Map<String,BigDecimal> phenotypeDefinitions;

	
	
	
	/**
	 * Generate all variety name lists
	 */
	private void initNames() {
		
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
		
		setvars = iricstockdao.findAllVariety();
		AppContext.info(setvars.size() + " vars from iricstock");
		itgerm =  setvars.iterator();
		while( itgerm.hasNext() )	
		{
			Variety germ = itgerm.next();
			if(germ==null) throw new RuntimeException("germ==null");

			if(mapId2Variety.containsKey(germ.getVarietyId())) continue;
			
			AppContext.debug(germ.getName() + "   " + germ.getVarietyId() + "  added");
			
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
		
		AppContext.info(mapId2Variety.size() + " variety Ids;  " + germnames.size()/2 + "  names");
		
		this.germnames = new java.util.ArrayList();
			this.germnames.addAll(germnames);
		this.countries =  new java.util.ArrayList();
			this.countries.addAll(countries);
		this.subpopulations =  new java.util.ArrayList();
			//this.subpopulations.add("");
			this.subpopulations.add("all varieties");
			this.subpopulations.add("all indica");
			//this.subpopulations.add("ALL INDICA");
			this.subpopulations.add("all japonica");
			//this.subpopulations.add("ALL JAPONICA");
		    this.subpopulations.addAll(subpopulations);

		this.irisid =  new java.util.ArrayList();
			this.irisid.addAll(irisid);
	}
	
	@Override
	public List<String> getGenenames() {
		// TODO Auto-generated method stub
		if(genenames==null || genenames.size()==0) {

			genenames = new java.util.ArrayList();
			geneDAO = (GeneDAO)AppContext.checkBean(geneDAO, "GeneDAO");
				
			java.util.Iterator<Gene> it = geneDAO.findAllGene().iterator();
		    while(it.hasNext()) {
		    	Gene gene = it.next();
		    	//String genename = gene.getUniquename();
		    	//genenames.add(genename.toUpperCase());
		    	genenames.add( gene.getUniquename().toLowerCase());
		    }
		}
		return genenames;
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
			var = iricstockdao.findVarietyByNameLike(name);
		}
		return var;	
	}
	
	@Override
	public Variety getGermplasmByName(String name) {
	
		Variety var = germdao.findVarietyByName(name);
		if(var==null) {
			var= iricstockdao.findVarietyByName(name);
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
			allvar.addAll( germdao.findAllVarietyBySubpopulation("ind1")) ;	
			allvar.addAll( germdao.findAllVarietyBySubpopulation("ind2")) ;
			allvar.addAll( germdao.findAllVarietyBySubpopulation("ind3")) ;
			allvar.addAll( germdao.findAllVarietyBySubpopulation("indx")) ;
			return allvar;
		}
		else if(subpopulation.toUpperCase().equals("ALL JAPONICA")) {
			Set allvar = new LinkedHashSet();
			allvar.addAll( germdao.findAllVarietyBySubpopulation("temp")) ;	
			allvar.addAll( germdao.findAllVarietyBySubpopulation("trop")) ;
			allvar.addAll( germdao.findAllVarietyBySubpopulation("temp/trop")) ;
			allvar.addAll( germdao.findAllVarietyBySubpopulation("trop/temp")) ;
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
		System.out.println("passportDefinitions");
		System.out.println(passportDefinitions);
		
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
	
}
