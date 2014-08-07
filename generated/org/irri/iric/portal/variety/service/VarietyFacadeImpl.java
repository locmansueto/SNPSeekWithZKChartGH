package org.irri.iric.portal.variety.service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.variety.dao.GermplasmDAO;
import org.irri.iric.portal.variety.dao.List3kDAO;
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

@Service("VarietyFacade")
public class VarietyFacadeImpl implements VarietyFacade {

	private static final Log log = LogFactory.getLog(VarietyFacadeImpl.class);

	/*
	@Autowired
	private GermplasmService germservice;
	
	@Autowired
	private GermplasmDAO germdao;
	*/
	
	@Autowired
	private List3kService germservice;
	
	@Autowired
	private List3kDAO germdao;
	
	
	@Autowired
	private PhenotypesDAO phendao;
	
	@Autowired
	private IViewDist3kHome dist3kdao;
	
	
	private java.util.List germnames;
	private java.util.List countries;
	private java.util.List subpopulations;
	private java.util.List irisid;
	
	//private java.util.Map mapAccession2IRISId  ;
	private java.util.Map<String,String[]> mapVarname2IRISId  ;  //0:irisID, 1:ID
	private java.util.Map<String,String> mapIRISId2Varname  ;
	private java.util.Map<String,String> mapId2Varname  ;


	
	private void initNames() {
		
		AppContext.checkBean(germdao, "GermplasmDAO");

		java.util.Set<String> germnames= new java.util.TreeSet<String>();
		java.util.Set<String> countries= new java.util.TreeSet<String>();
		java.util.Set<String> subpopulations= new java.util.TreeSet<String>();
		java.util.Set<String> irisid= new java.util.TreeSet<String>();
		
		
		mapVarname2IRISId = new java.util.HashMap<String, String[]>() ;
		mapIRISId2Varname  = new java.util.HashMap<String, String>() ;
		mapId2Varname  = new java.util.HashMap<String, String>() ;
		
		int germcount = 0;
		java.util.Iterator<List3k> itgerm =  germdao.findAllList3ks().iterator();
		while( itgerm.hasNext() )	
		{
			List3k germ = itgerm.next();
			if(germ==null) throw new RuntimeException("germ==null");
			if(germ.getVarnameOfGenStockSrc()==null)
				{
					System.out.println( "germ..getVarnameOfGenStockSrc()==null");
					continue;					
				}
			if(germ.getCountryOfOriginOfSource()!=null) // throw new RuntimeException("germ..getCountry()==null");
			{
				countries.add( germ.getCountryOfOriginOfSource().toLowerCase() );
				countries.add( germ.getCountryOfOriginOfSource().toUpperCase() );	
				//countries.add( germ.getCountry() );
			}
			
			
			if(germ.getVarietygroupOfSource()!=null) // throw new RuntimeException("germ..getSubpopulation()==null");
			{
				subpopulations.add( germ.getVarietygroupOfSource().toLowerCase() );
				subpopulations.add( germ.getVarietygroupOfSource().toUpperCase() );	
				//subpopulations.add( germ.getSubpopulation() );
			}
			
			if(germ.getIrisUniqueId()!=null) // throw new RuntimeException("germ..getSubpopulation()==null");
			{
				irisid.add( germ.getIrisUniqueId().toLowerCase() );
				irisid.add( germ.getIrisUniqueId().toUpperCase() );	
				//subpopulations.add( germ.getSubpopulation() );
			}
			
			if(germ.getVarnameOfGenStockSrc()!=null && germ.getIrisUniqueId()!=null) {
				mapVarname2IRISId.put(germ.getVarnameOfGenStockSrc().toUpperCase(), new String[] {germ.getIrisUniqueId().toUpperCase(), Long.toString(germcount),
					germ.getVarietygroupOfSource()} );
				mapIRISId2Varname.put(germ.getIrisUniqueId().toUpperCase() , germ.getVarnameOfGenStockSrc().toUpperCase() );
				mapId2Varname.put(Long.toString(germcount),  germ.getVarnameOfGenStockSrc().toUpperCase() );
			}

			germnames.add( germ.getVarnameOfGenStockSrc().toLowerCase() );
			germnames.add( germ.getVarnameOfGenStockSrc().toUpperCase() );		
	
			//germnames.add( germ.getAccession());
			germcount++;
		}
		
		this.germnames = new java.util.ArrayList();
			this.germnames.addAll(germnames);
		this.countries =  new java.util.ArrayList();
			this.countries.addAll(countries);
		this.subpopulations =  new java.util.ArrayList();
			this.subpopulations.addAll(subpopulations);
		this.irisid =  new java.util.ArrayList();
			this.irisid.addAll(irisid);
		
		
	}


	
	public java.util.List getVarietyNames() {
		if(germnames==null) initNames();
		return germnames;
	}
	
	public java.util.List getIrisIds() {
		if(irisid==null) initNames();
		return irisid;		
	}
	
	public List3k getGermplasmByIrisId(String name)
	{
			return germdao.findList3kByIrisUniqueId(name);
	}
	
	
	public List3k getGermplasmByName(String name) {
	
		java.util.Set setgerm =  germdao.findList3kByVarnameOfGenStockSrc(name);
		List3k firstgem=null;
		
		
		System.out.println(setgerm.size() + " germdao.findList3kByVarnameOfGenStockSrc(" + name );
		

		if(setgerm.size()>0){
			Iterator<List3k> itgerm = setgerm.iterator();
			firstgem = itgerm.next();
			
			if(itgerm.hasNext())
			{
				System.out.println(" germdao.getVarnameOfGenStockSrc(" + name + ").size>1");	
				System.out.println(firstgem.getVarnameOfGenStockSrc() + "\t" + firstgem.getCountryOfOriginOfSource() + "\t" + firstgem.getVarietygroupOfSource() );
				while(itgerm.hasNext()) {
					List3k nextgerm = itgerm.next();
					System.out.println(nextgerm.getVarnameOfGenStockSrc() + "\t" + nextgerm.getCountryOfOriginOfSource() +  "\t" + nextgerm.getCountryOfOriginOfSource() );
				}
			}
			//throw new RuntimeException(" germdao.findGermplasmByAccession(" + name + ").size!=1");		
		} else if(setgerm.size()==0)
		{
			throw new RuntimeException(" germdao.getVarnameOfGenStockSrc(" + name + ").size==0");	
		}
		return firstgem;
	}
	
	
	public java.util.Set getGermplasmByCountry(String country) {		
		return  germdao.findList3kByCountryOfOriginOfSource(country);	
	}
	
	public java.util.Set getGermplasmBySubpopulation(String subpopulation) {		
		return  germdao.findList3kByVarietygroupOfSource(subpopulation) ;	
	}



	public java.util.List getCountries() {
		if(countries==null) initNames();
		return countries;
	}



	public java.util.List getSubpopulations() {
		if(subpopulations==null) initNames();
		return subpopulations;
	}



	@Override
	public Set getGermplasmByExample(List3k germplasm) {
		// TODO Auto-generated method stub
		return germdao.findAllList3kByExample(germplasm);
		
	}
	
	
	public Set getPhenotypesByGermplasmNsftid(Integer id) {
		return phendao.findPhenotypesByNsftvId(id);
	}
	
	
	public String constructPhylotree(String varnames, String scale) {
		
		//return "'((((penHA34a,penHA34b,penHA32b,penHA32a,penSH30b,penSH30a,penSH28b,penSH28a,penIT13b,penIT13a,penIT12a,firSA26b,firGU7b,firGU8b,firSP18b,firSP20b,firSP36b,firSP39b,penSH31a,penSH31b),(firSP19b,(firSP17b,penIT12b))),firSA24a,firSA24b,firSA25a,firSA26a,firGU7a,firGU8a,firSP17a,firSP18a,firSP19a,firSP20a,firSP36a,firSP39a,(firSA25b,firSP40b),firSP40a,penIT11b,penIT11a),(ovi47a,ovi47b));'";

		//varnames = varnames.replace(' ', '_');
		
		String[] names = varnames.split(",");
		Set setnames = new java.util.TreeSet<String>();
		for(int i=0; i<names.length; i++) setnames.add(names[i]);
		return  constructPhylotree(setnames, scale);
	
	}
	
	public java.util.Map<String,String[]> getAccession2IRISMap() {

		return this.mapVarname2IRISId;
	}
	
	public String constructPhylotree(Set<String> germplasms, String scale) {
		
		dist3kdao = (IViewDist3kHome)AppContext.checkBean(dist3kdao, "IViewDist3kHome");
		
		// get 
		Set<String> sortedNames =  new java.util.TreeSet<String>();
		Iterator itId=germplasms.iterator();
		while(itId.hasNext() )
			sortedNames.add(   mapVarname2IRISId.get(  mapId2Varname.get( itId.next()  )   )[0] );
			  
			
		
		
		//java.util.Set sortedNames = new java.util.TreeSet(germplasms);
		
		
		
		//germplasms
		System.out.println(germplasms.size() + " germplasms");
		log.debug(germplasms.size() + " germplasms, warn");
		log.info(germplasms.size() + " germplasms, info");
		
		java.util.Map<String, Integer> mapName2Row = new java.util.HashMap<String, Integer>();
		
		BasicSymmetricalDistanceMatrix symdistmatrix = new BasicSymmetricalDistanceMatrix(germplasms.size() );
		
		System.out.println("symdistmatrix done");
		
		java.util.Iterator<String> itgerm=sortedNames.iterator();
		int i=0;
		StringBuffer buffIRISId = new StringBuffer();
		
		while(itgerm.hasNext()) {
			String c = itgerm.next();
			mapName2Row.put(c , i);
			//symdistmatrix.setIdentifier( i, c.replace("-", "_") );
			symdistmatrix.setIdentifier( i, c );
			
			buffIRISId.append("'"); buffIRISId.append( c.toUpperCase() );  buffIRISId.append("'");
			
			if(itgerm.hasNext()) buffIRISId.append(",");
			i++;
		}
		
		//java.util.Iterator<ViewDist3kId>  itdist = dist3kdao.findAll().iterator(); 
		
		//java.util.Iterator<ViewDist3kId>  itdist = dist3kdao.findAll().iterator(); 
		
		List listdist =  dist3kdao.findVarieties(buffIRISId.toString());

		System.out.println(listdist.size() + " distances; varieties " + sortedNames.size());
		java.util.Iterator<ViewDist3kId>  itdist = listdist.iterator();
		
		

		
		int distscale =  Integer.parseInt(scale);
		
		while(itdist.hasNext())
		{
			
			ViewDist3kId dist3k = itdist.next();
			Double dist = Double.valueOf( dist3k.getDist().toString() )*distscale;
			//System.out.println(dist3k.getIrisid1() + " "  +  dist3k.getIrisid2() + " " + dist) ;
			
			symdistmatrix.setValue( mapName2Row.get(dist3k.getIrisid1()) , mapName2Row.get(dist3k.getIrisid2()) , dist );
			symdistmatrix.setValue( mapName2Row.get(dist3k.getIrisid2()) , mapName2Row.get(dist3k.getIrisid1()),  dist );
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
			String newick = tree.getNewickString(false, true);
			
			newick = newick.replace(' ', '_').replace("'","").replace(":-",":");
			//newick = newick.replace("IRIS", newChar)
			
			
			System.out.println(newick);
			java.util.Iterator<String> itgerm2 = sortedNames.iterator();
			while(itgerm2.hasNext()) {
				String c = itgerm2.next();
				String newc = c.replace(' ', '_');
				// replace iris_id to varnames
				String varname  = mapIRISId2Varname.get(c);
				//newick = newick.replace(c,  mapIRISId2Varname.get(c).replace("-", "_").replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "")   );
				String subpop = mapVarname2IRISId.get(varname)[2];
				if(subpop == null) subpop = ""; 
				newick = newick.replace(newc, newc + " " + subpop + " " +  varname.replace("-", "_").replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "")   );
			}
			
			
			System.out.println(newick);
			return newick;
			
			
		} catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	            //   NJTreeProgressListener _treeProgessListener)
		
	}



	@Override
	public Set getGermplasmByExample(Germplasm germplasm) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
