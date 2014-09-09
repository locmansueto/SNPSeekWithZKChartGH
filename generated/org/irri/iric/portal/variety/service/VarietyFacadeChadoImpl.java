package org.irri.iric.portal.variety.service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.chado.dao.VIricstockPassportDAO;
import org.irri.iric.portal.dao.IricstockPassportDAO;
import org.irri.iric.portal.dao.PhenotypeDAO;
import org.irri.iric.portal.dao.VarietyDAO;
import org.irri.iric.portal.dao.VarietyDistanceDAO;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.domain.VarietyDistance;
import org.irri.iric.portal.service.VarietyService;
//import org.irri.iric.portal.variety.dao.GermplasmDAO;
//import org.irri.iric.portal.variety.dao.List3kDAO;
//import org.irri.iric.portal.variety.dao.PhenotypesDAO;
//import org.irri.iric.portal.variety.domain.Germplasm;
//import org.irri.iric.portal.variety.domain.List3k;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.biojava3.phylo.TreeConstructor;
import org.forester.evoinference.matrix.distance.BasicSymmetricalDistanceMatrix;
//import org.irri.iric.portal.variety.views.IViewDist3kHome;
//import org.irri.iric.portal.variety.views.ViewDist3kId;

@Service("VarietyFacade")
public class VarietyFacadeChadoImpl implements VarietyFacade {

	private static final Log log = LogFactory.getLog(VarietyFacadeChadoImpl.class);

	/*
	@Autowired
	private GermplasmService germservice;
	
	@Autowired
	private GermplasmDAO germdao;
	*/
	
	@Autowired
	private VarietyService germservice;
	
	@Autowired
	@Qualifier("VarietyDAO")
	private VarietyDAO germdao;
	
	@Autowired
	private VIricstockPassportDAO passportdao;
	
	@Autowired
	private PhenotypeDAO phendao;
	
	@Autowired
	private VarietyDistanceDAO dist3kdao;
	
	
	private java.util.List germnames;
	private java.util.List countries;
	private java.util.List subpopulations;
	private java.util.List irisid;
	
	//private java.util.Map mapAccession2IRISId  ;
	//private java.util.Map<String,String[]> mapVarname2IRISId  ;  //0:irisID, 1:ID
	//private java.util.Map<String,String> mapIRISId2Varname  ;
	//private java.util.Map<String,String> mapId2Varname  ;
	
	private java.util.Map<String,Variety> mapVarname2Variety; 
	private Map<BigDecimal, Variety> mapId2Variety; 


	
	private void initNames() {
		
		//AppContext.checkBean(germdao, "GermplasmDAO");

		java.util.Set<String> germnames= new java.util.TreeSet<String>();
		java.util.Set<String> countries= new java.util.TreeSet<String>();
		java.util.Set<String> subpopulations= new java.util.TreeSet<String>();
		java.util.Set<String> irisid= new java.util.TreeSet<String>();
		
		
		//mapVarname2IRISId = new java.util.HashMap<String, String[]>() ;
		//mapIRISId2Varname  = new java.util.HashMap<String, String>() ;
		//mapId2Varname  = new java.util.HashMap<String, String>() ;
		
		mapVarname2Variety = new java.util.HashMap<String, Variety>() ;
		mapId2Variety = new java.util.HashMap<BigDecimal, Variety>() ;
		
		int germcount = 0;
		java.util.Iterator<Variety> itgerm =  germdao.findAllVariety().iterator();
		while( itgerm.hasNext() )	
		{
			Variety germ = itgerm.next();
			if(germ==null) throw new RuntimeException("germ==null");

			mapId2Variety.put(germ.getVarietyId(), germ);
			
			if(germ.getName()==null)
				{
					System.out.println( "germ..getVarnameOfGenStockSrc()==null");
					continue;					
				}

			mapVarname2Variety.put(germ.getName().toUpperCase(), germ);
			
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
			
			if(germ.getIrisId()!=null) // throw new RuntimeException("germ..getSubpopulation()==null");
			{
				irisid.add( germ.getIrisId().toLowerCase() );
				irisid.add( germ.getIrisId().toUpperCase() );	
				//subpopulations.add( germ.getSubpopulation() );
			}
			
			/*
			if(germ.getName()!=null && germ.getIrisId()!=null) {
				mapVarname2IRISId.put(germ.getName().toUpperCase(), new String[] {germ.getIrisId().toUpperCase(), Long.toString(germcount),
					germ.getSubpopulation()} );
				mapIRISId2Varname.put(germ.getIrisId().toUpperCase() , germ.getName().toUpperCase() );
				mapId2Varname.put(Long.toString(germcount),  germ.getName().toUpperCase() );
			}
			*/

			germnames.add( germ.getName().toLowerCase() );
			germnames.add( germ.getName().toUpperCase() );		
	
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
		if(germnames==null || germnames.size()==0) initNames();
		return germnames;
	}
	
	public java.util.List getIrisIds() {
		if(irisid==null) initNames();
		return irisid;		
	}
	
	public Variety getGermplasmByIrisId(String name)
	{
			return germdao.findVarietyByIrisId(name);
	}
	
	
	public Variety getGermplasmByNameLike(String name) {
		
		return germdao.findVarietyByNameLike(name);
		
	}
	
	public Variety getGermplasmByName(String name) {
	
		return germdao.findVarietyByName(name);
		
		/*
		java.util.Set setgerm =  germdao.findVarietyByName(name);
		IricStock firstgem=null;
		
		
		System.out.println(setgerm.size() + " germdao.findIricStockByVarnameOfGenStockSrc(" + name );
		

		if(setgerm.size()>0){
			Iterator<IricStock> itgerm = setgerm.iterator();
			firstgem = itgerm.next();
			
			if(itgerm.hasNext())
			{
				System.out.println(" germdao.getVarnameOfGenStockSrc(" + name + ").size>1");	
				//System.out.println(firstgem.getVarnameOfGenStockSrc() + "\t" + firstgem.getCountryOfOriginOfSource() + "\t" + firstgem.getVarietygroupOfSource() );
				while(itgerm.hasNext()) {
					IricStock nextgerm = itgerm.next();
					//System.out.println(nextgerm.getVarnameOfGenStockSrc() + "\t" + nextgerm.getCountryOfOriginOfSource() +  "\t" + nextgerm.getCountryOfOriginOfSource() );
				}
			}
			//throw new RuntimeException(" germdao.findGermplasmByAccession(" + name + ").size!=1");		
		} else if(setgerm.size()==0)
		{
			throw new RuntimeException(" germdao.getVarnameOfGenStockSrc(" + name + ").size==0");	
		}
		
		return firstgem;
		*/
	
	}
	

	
	
	public java.util.Set getGermplasmByCountry(String country) {		
		return  germdao.findAllVarietyByCountry(country);	
	}
	
	public java.util.Set getGermplasmBySubpopulation(String subpopulation) {		
		return  germdao.findAllVarietyBySubpopulation(subpopulation) ;	
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
	
	
	public List getPhenotypesByGermplasm(Variety var) {
	//	return phendao.findPhenotypesByNsftvId(id);
	//	return null;
		return phendao.findPhenotypesByVariety(var);
	}
	
	public List getPhenotypesByGermplasmName(String name) {
	//	return phendao.findPhenotypesByNsftvId(id);
	//	return null;
		return phendao.findPhenotypesByVarietyNameLike(name);
	}	
	
	
	
	
	public String constructPhylotree(String varids, String scale) {
		
		//return "'((((penHA34a,penHA34b,penHA32b,penHA32a,penSH30b,penSH30a,penSH28b,penSH28a,penIT13b,penIT13a,penIT12a,firSA26b,firGU7b,firGU8b,firSP18b,firSP20b,firSP36b,firSP39b,penSH31a,penSH31b),(firSP19b,(firSP17b,penIT12b))),firSA24a,firSA24b,firSA25a,firSA26a,firGU7a,firGU8a,firSP17a,firSP18a,firSP19a,firSP20a,firSP36a,firSP39a,(firSA25b,firSP40b),firSP40a,penIT11b,penIT11a),(ovi47a,ovi47b));'";

		//varnames = varnames.replace(' ', '_');
		Set setids = new java.util.TreeSet<BigDecimal>();		
		if(varids.equals("all")) {
			setids.addAll( getMapId2Variety().keySet() );
			return  constructPhylotree(setids, scale, true);
		} else {
			String[] ids = varids.split(",");			
			for(int i=0; i<ids.length; i++) setids.add(BigDecimal.valueOf(Long.parseLong(ids[i])));
			return  constructPhylotree(setids, scale, false);
		}
		
	
	}
	
	
public String constructPhylotree(Set<BigDecimal> germplasms, String scale) {
		return  constructPhylotree( germplasms,  scale, false);
}	
	
private String constructPhylotree(Set<BigDecimal> germplasms, String scale, boolean isAll) {
		
		dist3kdao = (VarietyDistanceDAO)AppContext.checkBean(dist3kdao, "VarietyDistanceDAO");
		
		// get 
		//Set<Integer> sortedIds =  new java.util.TreeSet<Integer>();
		//Iterator<Integer> itId=germplasms.iterator();
		//while(itId.hasNext() )
		//	sortedNames.add(   mapVarname2IRISId.get(  mapId2Varname.get( itId.next()  )   )[0] );
			  
		
		//java.util.Set sortedNames = new java.util.TreeSet(germplasms);
		
		
		
		//germplasms
		System.out.println(germplasms.size() + " germplasms");
		log.debug(germplasms.size() + " germplasms, warn");
		log.info(germplasms.size() + " germplasms, info");
		
		
		
		System.out.println("symdistmatrix done");
		
		
		BasicSymmetricalDistanceMatrix symdistmatrix = new BasicSymmetricalDistanceMatrix(germplasms.size());
		
		java.util.Iterator<BigDecimal> itgerm=germplasms.iterator();
		int i=0;
		java.util.Map<BigDecimal, Integer> mapVarid2Row = new java.util.HashMap<BigDecimal, Integer>();

		//StringBuffer buffVarId = new StringBuffer();
		
		while(itgerm.hasNext()) {
			BigDecimal c = itgerm.next();
			symdistmatrix.setIdentifier( i, "varid_" + mapId2Variety.get(c).getVarietyId() );

			mapVarid2Row.put(c , i);
			
			//buffVarId.append( c );
			
			//if(itgerm.hasNext()) {
			//	buffVarId.append(",");
			//}			
			i++;			
		
		}

		
		//List listdist =  dist3kdao.findVarieties(buffIrisId.toString());
		List<VarietyDistance> listdist;
		
		if(isAll)
			listdist =  dist3kdao.findAllVarieties();
		else	
			listdist =  dist3kdao.findVarieties(germplasms);
			

		System.out.println(listdist.size() + " distances; varieties " + germplasms.size());
		java.util.Iterator<VarietyDistance>  itdist = listdist.iterator();
		
		
		int distscale =  Integer.parseInt(scale);
		
		while(itdist.hasNext())
		{
			
			VarietyDistance dist3k = itdist.next();

			if(!mapVarid2Row.containsKey(dist3k.getVar1()) ) continue ; //throw new RuntimeException("No key " + dist3k.getVar1() + " in mapVarid2Row");
			if(!mapVarid2Row.containsKey(dist3k.getVar2()) ) continue ; //throw new RuntimeException("No key " + dist3k.getVar2() + " in mapVarid2Row");

			Double dist = Double.valueOf( dist3k.getDist().toString() )*distscale;
			//System.out.println(dist3k.getIrisid1() + " "  +  dist3k.getIrisid2() + " " + dist) ;
			
			symdistmatrix.setValue( mapVarid2Row.get(dist3k.getVar1() ) , mapVarid2Row.get(dist3k.getVar2()) , dist );
			symdistmatrix.setValue( mapVarid2Row.get(dist3k.getVar2() ) , mapVarid2Row.get(dist3k.getVar1()),  dist );
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
			
			//newick = newick.replace(' ', '_').replace("'","").replace(":-",":");
			//newick = newick.replace("IRIS", newChar)
			
			//newick=newick.replace("-", "");
			System.out.println(newick);
			Iterator<BigDecimal> itgerm2 = germplasms.iterator();
			while(itgerm2.hasNext()) {
				BigDecimal c = itgerm2.next();
				//String newc = c.replace(' ', '_');
				// replace iris_id to varnames
				//String varname  = mapIRISId2Varname.get(c);
				//newick = newick.replace(c,  mapIRISId2Varname.get(c).replace("-", "_").replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "")   );
				//String subpop = mapVarname2IRISId.get(varname)[2];
				//if(subpop == null) subpop = ""; 
				
				Variety var = mapId2Variety.get(c);
				
				String subpop = "";
				if( var.getSubpopulation()!=null) subpop = "/" +  var.getSubpopulation();
				
				newick = newick.replace("varid_" + c + ":",(var.getName().split("::")[0] + "/" + var.getIrisId() + subpop).replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "") + ":"  );
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

/*	
	public String constructPhylotreeIRIS(Set<Integer> germplasms, String scale) {
		
		dist3kdao = (VarietyDistanceDAO)AppContext.checkBean(dist3kdao, "VarietyDistanceDAO");
		
		// get 
		//Set<Integer> sortedIds =  new java.util.TreeSet<Integer>();
		//Iterator<Integer> itId=germplasms.iterator();
		//while(itId.hasNext() )
		//	sortedNames.add(   mapVarname2IRISId.get(  mapId2Varname.get( itId.next()  )   )[0] );
			  
		
		//java.util.Set sortedNames = new java.util.TreeSet(germplasms);
		
		
		
		//germplasms
		System.out.println(germplasms.size() + " germplasms");
		log.debug(germplasms.size() + " germplasms, warn");
		log.info(germplasms.size() + " germplasms, info");
		
		
		
		System.out.println("symdistmatrix done");
		
		java.util.Iterator<Integer> itgerm=germplasms.iterator();
		int i=0;
		java.util.Set<Integer> setHasIrisId = new HashSet<Integer>();
		while(itgerm.hasNext()) {
			Integer c = itgerm.next();
			if( mapId2Variety.get(c).getIrisId()==null ||  mapId2Variety.get(c).getIrisId().isEmpty() ) continue;
			setHasIrisId.add(c);
			i++;
		}

		java.util.Map<Integer, Integer> mapVarid2Row = new java.util.HashMap<Integer, Integer>();
		java.util.Map<String, Integer> mapIrisId2Row = new java.util.HashMap<String, Integer>();

		StringBuffer buffVarId = new StringBuffer();
		StringBuffer buffIrisId = new StringBuffer();
				
		BasicSymmetricalDistanceMatrix symdistmatrix = new BasicSymmetricalDistanceMatrix(i);
		itgerm=setHasIrisId.iterator();
		i=0;
		while(itgerm.hasNext())
		{
			Integer c = itgerm.next();
			
			symdistmatrix.setIdentifier( i, "varid_" + mapId2Variety.get(c).getVarietyId() );

			mapVarid2Row.put(c , i);
			mapIrisId2Row.put( mapId2Variety.get(c).getIrisId(), i);
			
			buffVarId.append( c );
			buffIrisId.append("'").append(  mapId2Variety.get(c).getIrisId() ).append("'");
			
			if(itgerm.hasNext()) {
				buffVarId.append(",");
				buffIrisId.append(",");
			}			
			i++;
		}
		
		
		//List listdist =  dist3kdao.findVarieties(buffIrisId.toString());
		List<VarietyDistance> listdist =  dist3kdao.findVarieties(buffVarId.toString());

		System.out.println(listdist.size() + " distances; varieties " + germplasms.size());
		java.util.Iterator<VarietyDistance>  itdist = listdist.iterator();
		
		
		int distscale =  Integer.parseInt(scale);
		
		while(itdist.hasNext())
		{
			
			VarietyDistance dist3k = itdist.next();
			Double dist = Double.valueOf( dist3k.getDist().toString() )*distscale;
			//System.out.println(dist3k.getIrisid1() + " "  +  dist3k.getIrisid2() + " " + dist) ;
			
			symdistmatrix.setValue( mapIrisId2Row.get(dist3k.getIrisid1()) , mapIrisId2Row.get(dist3k.getIrisid2()) , dist );
			symdistmatrix.setValue( mapIrisId2Row.get(dist3k.getIrisid2()) , mapIrisId2Row.get(dist3k.getIrisid1()),  dist );
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
			
			//newick = newick.replace(' ', '_').replace("'","").replace(":-",":");
			//newick = newick.replace("IRIS", newChar)
			
			//newick=newick.replace("-", "");
			System.out.println(newick);
			java.util.Iterator<Integer> itgerm2 = germplasms.iterator();
			while(itgerm2.hasNext()) {
				Integer c = itgerm2.next();
				//String newc = c.replace(' ', '_');
				// replace iris_id to varnames
				//String varname  = mapIRISId2Varname.get(c);
				//newick = newick.replace(c,  mapIRISId2Varname.get(c).replace("-", "_").replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "")   );
				//String subpop = mapVarname2IRISId.get(varname)[2];
				//if(subpop == null) subpop = ""; 
				
				Variety var = mapId2Variety.get(c);
				
				String subpop = "";
				if( var.getSubpopulation()!=null) subpop = "/" +  var.getSubpopulation();
				
				newick = newick.replace("varid_" + c + ":",(var.getName().split("::")[0] + "/" + var.getIrisId() + subpop).replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "") + ":"  );
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
*/


	public java.util.Map<String, Variety> getMapVarname2Variety() {
		if(mapVarname2Variety==null) initNames();
		return mapVarname2Variety;
	}


	

	public Map<BigDecimal, Variety> getMapId2Variety() {
		if(mapId2Variety==null) initNames();
		return mapId2Variety;
	}



	@Override
	public Set getPassportByVarietyid(BigDecimal id) {
		// TODO Auto-generated method stub
		return passportdao.findVIricstockPassportByIricStockId(id );
	}

	
	
	
}
