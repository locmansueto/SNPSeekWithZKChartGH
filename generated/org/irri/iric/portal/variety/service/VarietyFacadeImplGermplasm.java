package org.irri.iric.portal.variety.service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.variety.dao.GermplasmDAO;
import org.irri.iric.portal.variety.dao.PhenotypesDAO;
import org.irri.iric.portal.variety.domain.Germplasm;
import org.irri.iric.portal.variety.domain.List3k;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.biojava3.phylo.TreeConstructor;
import org.forester.evoinference.matrix.distance.BasicSymmetricalDistanceMatrix;
import org.irri.iric.portal.variety.views.IViewDist3kHome;
import org.irri.iric.portal.variety.views.ViewDist3kId;

@Service("VarietyFacadeGermplasm")
public class VarietyFacadeImplGermplasm  {

	private static final Log log = LogFactory.getLog(VarietyFacadeImplGermplasm.class);

	
	@Autowired
	private GermplasmService germservice;
	
	@Autowired
	private GermplasmDAO germdao;
	
	
	@Autowired
	private PhenotypesDAO phendao;
	
	@Autowired
	private IViewDist3kHome dist3kdao;
	
	
	private java.util.List germnames;
	private java.util.List countries;
	private java.util.List subpopulations;
	
	private java.util.Map mapAccession2IRISId  ;
	
	

	
	private void initNames() {
		
		AppContext.checkBean(germdao, "GermplasmDAO");

		java.util.Set<String> germnames= new java.util.TreeSet<String>();
		java.util.Set<String> countries= new java.util.TreeSet<String>();
		java.util.Set<String> subpopulations= new java.util.TreeSet<String>();
		
		java.util.Iterator<Germplasm> itgerm =  germdao.findAllGermplasms().iterator();
		while( itgerm.hasNext() )	
		{
			Germplasm germ = itgerm.next();
			if(germ==null) throw new RuntimeException("germ==null");
			if(germ.getAccession()==null)
				{
					System.out.println( "germ..getAccession()==null");
					continue;					
				}
			if(germ.getCountry()!=null) // throw new RuntimeException("germ..getCountry()==null");
			{
				countries.add( germ.getCountry().toLowerCase() );
				countries.add( germ.getCountry().toUpperCase() );	
				//countries.add( germ.getCountry() );
			}
			
			
			if(germ.getSubpopulation()!=null) // throw new RuntimeException("germ..getSubpopulation()==null");
			{
				subpopulations.add( germ.getSubpopulation().toLowerCase() );
				subpopulations.add( germ.getSubpopulation().toUpperCase() );	
				//subpopulations.add( germ.getSubpopulation() );
			}
			
			germnames.add( germ.getAccession().toLowerCase() );
			germnames.add( germ.getAccession().toUpperCase() );		
			//germnames.add( germ.getAccession());
		}
		
		this.germnames = new java.util.ArrayList();
			this.germnames.addAll(germnames);
		this.countries =  new java.util.ArrayList();
			this.countries.addAll(countries);
		this.subpopulations =  new java.util.ArrayList();
			this.subpopulations.addAll(subpopulations);
		
		
	}


	
	public java.util.List getVarietyNames() {
		if(germnames==null) initNames();
		return germnames;
	}
	
	public Germplasm getGermplasmByName(String name) {
	
		java.util.Set setgerm =  germdao.findGermplasmByAccession(name.toUpperCase());
		Germplasm firstgem=null;
		if(setgerm.size()>0){
			Iterator<Germplasm> itgerm = setgerm.iterator();
			firstgem = itgerm.next();
			
			if(itgerm.hasNext())
			{
				System.out.println(" germdao.findGermplasmByAccession(" + name + ").size>1");	
				System.out.println(firstgem.getAccession() + "\t" + firstgem.getCountry() + "\t" + firstgem.getNsftvId() + "\t" + firstgem.getSubpopulation() );
				while(itgerm.hasNext()) {
					Germplasm nextgerm = itgerm.next();
					System.out.println(nextgerm.getAccession() + "\t" + nextgerm.getCountry() + "\t" + nextgerm.getNsftvId() + "\t" + nextgerm.getSubpopulation() );
				}
			}
			//throw new RuntimeException(" germdao.findGermplasmByAccession(" + name + ").size!=1");		
		} else if(setgerm.size()==0)
		{
			throw new RuntimeException(" germdao.findGermplasmByAccession(" + name + ").size==0");	
		}
		return firstgem;
	}
	
	
	public java.util.Set getGermplasmByCountry(String country) {		
		return  germdao.findGermplasmByCountry(country);	
	}
	
	public java.util.Set getGermplasmBySubpopulation(String subpopulation) {		
		return  germdao.findGermplasmBySubpopulation(subpopulation) ;	
	}



	public java.util.List getCountries() {
		if(countries==null) initNames();
		return countries;
	}



	public java.util.List getSubpopulations() {
		if(subpopulations==null) initNames();
		return subpopulations;
	}



	//@Override
	public Set getGermplasmByExample(Germplasm germplasm) {
		// TODO Auto-generated method stub
		return germdao.findAllGermplasmByExample(germplasm);
		
	}
	
	
	public Set getPhenotypesByGermplasmNsftid(Integer id) {
		return phendao.findPhenotypesByNsftvId(id);
	}
	
	
	public String constructPhylotree(String varnames) {
		
		//return "'((((penHA34a,penHA34b,penHA32b,penHA32a,penSH30b,penSH30a,penSH28b,penSH28a,penIT13b,penIT13a,penIT12a,firSA26b,firGU7b,firGU8b,firSP18b,firSP20b,firSP36b,firSP39b,penSH31a,penSH31b),(firSP19b,(firSP17b,penIT12b))),firSA24a,firSA24b,firSA25a,firSA26a,firGU7a,firGU8a,firSP17a,firSP18a,firSP19a,firSP20a,firSP36a,firSP39a,(firSA25b,firSP40b),firSP40a,penIT11b,penIT11a),(ovi47a,ovi47b));'";

		String[] names = varnames.split(",");
		Set setnames = new java.util.TreeSet<String>();
		for(int i=0; i<names.length; i++) setnames.add(names[i]);
		return  constructPhylotree(setnames);
	
	}
	
	public java.util.Map getAccession2IRISMap() {
		if(  mapAccession2IRISId ==null) {
			mapAccession2IRISId = new java.util.HashMap<String,String>();
			dist3kdao = (IViewDist3kHome)AppContext.checkBean(dist3kdao, "IViewDist3kHome");
			Iterator<ViewDist3kId> itvardist = dist3kdao.findAll().iterator();
			while(itvardist.hasNext()) {
				ViewDist3kId dist = itvardist.next();
				mapAccession2IRISId.put(dist.getVar1().toUpperCase(),  dist.getIrisid1().toUpperCase());
				mapAccession2IRISId.put(dist.getVar2().toUpperCase(),  dist.getIrisid2().toUpperCase());
			}
			
		}
		return mapAccession2IRISId;
	}
	
	public String constructPhylotree(Set<String> germplasms) {
		
		dist3kdao = (IViewDist3kHome)AppContext.checkBean(dist3kdao, "IViewDist3kHome");
		
		// get 
		java.util.Set sortedNames = new java.util.TreeSet(germplasms);
		
		
		
		//germplasms
		System.out.println(germplasms.size() + " germplasms");
		log.debug(germplasms.size() + " germplasms, warn");
		log.info(germplasms.size() + " germplasms, info");
		
		java.util.Map<String, Integer> mapName2Row = new java.util.HashMap<String, Integer>();
		
		BasicSymmetricalDistanceMatrix symdistmatrix = new BasicSymmetricalDistanceMatrix(germplasms.size() );
		
		System.out.println("symdistmatrix done");
		
		java.util.Iterator<String> itgerm=sortedNames.iterator();
		int i=0;
		while(itgerm.hasNext()) {
			String c = itgerm.next();
			symdistmatrix.setIdentifier( i, c.replace("-", "_") );
			mapName2Row.put(c , i);
			i++;
		}
		
		//java.util.Iterator<ViewDist3kId>  itdist = dist3kdao.findAll().iterator(); 
		
		java.util.Iterator<ViewDist3kId>  itdist = dist3kdao.findAll().iterator(); 
		
		while(itdist.hasNext())
		{
			ViewDist3kId dist3k = itdist.next();
			symdistmatrix.setValue( mapName2Row.get(dist3k.getIrisid1()) , mapName2Row.get(dist3k.getIrisid2()) ,  Double.valueOf( dist3k.getDist().toString() ) );
			symdistmatrix.setValue( mapName2Row.get(dist3k.getIrisid2()) , mapName2Row.get(dist3k.getIrisid1()),  Double.valueOf( dist3k.getDist().toString() ) );
		}
		
		System.out.print(symdistmatrix.getSize() + " symdistmatrix ready");
		
		try {
			
			TreeConstructor tree = new  TreeConstructor(symdistmatrix,
				org.biojava3.phylo.TreeType.NJ ,
				org.biojava3.phylo.TreeConstructionAlgorithm.PID ,
			//	null);
				new org.biojava3.phylo.ProgessListenerStub());
				tree.process();

				System.out.println("process done");
			//tree.getN
			return tree.getNewickString(false, true);
			
		} catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	            //   NJTreeProgressListener _treeProgessListener)
		
	}
	
}
