package org.irri.iric.portal.variety.service;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.chado.dao.VIricstockPassportDAO;
import org.irri.iric.portal.chado.domain.VIricstockBasicprop2;
import org.irri.iric.portal.dao.CvTermDAO;
import org.irri.iric.portal.dao.CvTermUniqueValuesDAO;
//import org.irri.iric.portal.dao.IricstockPassportDAO;
import org.irri.iric.portal.dao.PhenotypeDAO;
import org.irri.iric.portal.dao.VarietyByPassportDAO;
import org.irri.iric.portal.dao.VarietyByPhenotypeDAO;
import org.irri.iric.portal.dao.VarietyDAO;
import org.irri.iric.portal.dao.VarietyDistanceDAO;
import org.irri.iric.portal.domain.CvTerm;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.domain.VarietyDistance;
//import org.irri.iric.portal.service.VarietyService;
//import org.irri.iric.portal.variety.dao.GermplasmDAO;
//import org.irri.iric.portal.variety.dao.List3kDAO;
//import org.irri.iric.portal.variety.dao.PhenotypesDAO;
//import org.irri.iric.portal.variety.domain.Germplasm;
//import org.irri.iric.portal.variety.domain.List3k;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.biojava3.phylo.TreeConstructor;
import org.forester.evoinference.matrix.distance.BasicSymmetricalDistanceMatrix;
//import org.irri.iric.portal.variety.views.IViewDist3kHome;
//import org.irri.iric.portal.variety.views.ViewDist3kId;

@Service("VarietyFacade")
//@Scope("prototype")
@Scope(value="session",  proxyMode = ScopedProxyMode.INTERFACES)
public class VarietyFacadeChadoImpl implements VarietyFacade {

	private static final Log log = LogFactory.getLog(VarietyFacadeChadoImpl.class);

	private static final int PHENOTYPETYPE_NONE=0;
	private static final int PHENOTYPETYPE_QUAL=1;
	private static final int PHENOTYPETYPE_QUAN=2;
	
	private int phenotype_type=PHENOTYPETYPE_NONE;
	
	
	@Autowired
	@Qualifier("VarietyDAO")
	private VarietyDAO germdao;

	@Autowired
	@Qualifier("VarietyBasicprop2DAO")
	private VarietyDAO germ2dao;

	
	@Autowired
	@Qualifier("IricStockDAO")
	private VarietyDAO iricstockdao;
	
	@Autowired
	private VarietyByPassportDAO varbypassportdao;
	@Autowired
	private VarietyByPhenotypeDAO varbyphenotypedao;
	
	
	@Autowired
	private VIricstockPassportDAO passportdao;
	
	@Autowired
	private PhenotypeDAO phendao;
	
	@Autowired
	private VarietyDistanceDAO dist3kdao;
	
	@Autowired
	@Qualifier("VCvPassportDAO")
	private CvTermDAO cvtermsPassportdao;
	
	@Autowired
	@Qualifier("VCvPhenotypeDAO")
	private CvTermDAO cvtermsPhenotypedao;
	
	@Autowired
	@Qualifier("VCvPassportValuesDAO")
	private CvTermUniqueValuesDAO cvpassportValuesDao;
	
	//@Autowired
	//@Qualifier("VCvPhenotypeValuesDAO")
	//private CvTermUniqueValuesDAO cvphenotypeValuesDao;
	
	@Autowired
	@Qualifier("VCvPhenotypeQualValuesDAO")
	private CvTermUniqueValuesDAO cvphenotypeQualValuesDao;
	
	@Autowired
	@Qualifier("VCvPhenotypeQuanValuesDAO")
	private CvTermUniqueValuesDAO cvphenotypeQuanValuesDao;
	
	
	
	private java.util.List germnames;
	private java.util.List countries;
	private java.util.List subpopulations;
	private java.util.List irisid;
	
	private Map<String,BigDecimal> passportDefinitions;
	private Map<String,BigDecimal> phenotypeDefinitions;
	
	
	private java.util.Map<String,Variety> mapVarname2Variety; 
	private Map<BigDecimal, Variety> mapId2Variety; 


	/**
	 * Generate all name lists
	 */
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
		
		Set setvars = germdao.findAllVariety();
		System.out.println(setvars.size() + " vars from iricstock_basicprop");
		java.util.Iterator<Variety> itgerm =  setvars.iterator();
		
	
		
		while( itgerm.hasNext() )	
		{
			Variety germ = itgerm.next();
			if(germ==null) throw new RuntimeException("germ==null");

			mapId2Variety.put(germ.getVarietyId(), germ);
			germcount++;
			
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

		}
		
		setvars = iricstockdao.findAllVariety();
		System.out.println(setvars.size() + " vars from iricstock");
		itgerm =  setvars.iterator();
		while( itgerm.hasNext() )	
		{
			Variety germ = itgerm.next();
			if(germ==null) throw new RuntimeException("germ==null");

			if(mapId2Variety.containsKey(germ.getVarietyId())) continue;
			
			
			System.out.println(germ.getName() + "   " + germ.getVarietyId() + "  added");
			
			
			germcount++;
			mapId2Variety.put(germ.getVarietyId(), germ);
			
			if(germ.getName()==null)
				{
					System.out.println( "germ..getVarnameOfGenStockSrc()==null");
					continue;					
				}

			mapVarname2Variety.put(germ.getName().toUpperCase(), germ);

			germnames.add( germ.getName().toLowerCase() );
			germnames.add( germ.getName().toUpperCase() );		
	
			//germnames.add( germ.getAccession());
			
			
			
			
		}	
		
		System.out.println(mapId2Variety.size() + " variety Ids;  " + germnames.size()/2 + "  names");
		
		this.germnames = new java.util.ArrayList();
			this.germnames.addAll(germnames);
		this.countries =  new java.util.ArrayList();
			this.countries.addAll(countries);
		this.subpopulations =  new java.util.ArrayList();
			this.subpopulations.addAll(subpopulations);
		this.irisid =  new java.util.ArrayList();
			this.irisid.addAll(irisid);
		
		
	}

	@Override
	public Map getIrisId2Variety() {
		
	
		java.util.Map irisid= new java.util.HashMap();
		
		Set setvars = germdao.findAllVariety();
		System.out.println(setvars.size() + " vars from iricstock_basicprop");
		java.util.Iterator<Variety> itgerm =  setvars.iterator();
	
		
		while( itgerm.hasNext() )	
		{
			Variety germ = itgerm.next();
			if(germ==null) throw new RuntimeException("germ==null");
			if(germ.getIrisId()!=null) // throw new RuntimeException("germ..getSubpopulation()==null");
			{
				irisid.put( germ.getIrisId().toUpperCase() , germ);	
			}
		}
		return irisid;
		
	}

	
	private void initMoreConstraints() {
		
		cvtermsPassportdao = (CvTermDAO)AppContext.checkBean(cvtermsPassportdao, "VCvPassportDAO");
		cvtermsPhenotypedao = (CvTermDAO)AppContext.checkBean(cvtermsPhenotypedao, "VCvPhenotypeDAO");
			
		List listCVPassport =  cvtermsPassportdao.getAllTerms();
		passportDefinitions = new TreeMap<String,BigDecimal>();
		passportDefinitions.put("", BigDecimal.ZERO);
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
		phenotypeDefinitions.put("", BigDecimal.ZERO);
		Iterator<CvTerm>  itTerm2=listCVPhenotype.iterator();
		while(itTerm2.hasNext())
		{
			CvTerm term = itTerm2.next();
			phenotypeDefinitions.put(term.getDefinition(), term.getCvTermId());
		}	
		
		System.out.println("phenotypeDefinitions");
		System.out.println(phenotypeDefinitions);
		
				
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
		
		Variety var =  germdao.findVarietyByNameLike(name);
		if(var==null) {
			var = iricstockdao.findVarietyByNameLike(name);
		}
		return var;	
	}
	
	public Variety getGermplasmByName(String name) {
	
		Variety var = germdao.findVarietyByName(name);
		if(var==null) {
			var= iricstockdao.findVarietyByName(name);
		}
		return var;
	}
		
	@Override
	public List getGermplasmsByNameOrIrisid(String names) {
		// TODO Auto-generated method stub
		//irisIds = irisIds.replace("_"," ");
		
		
		//return germdao.findVarietyByNameOrIrisId(String names,  irisIds);
		return null;
		
	}
	
	
	public java.util.Set getGermplasmByCountry(String country) {		
		return  germdao.findAllVarietyByCountry(country);	
	}
	
	public java.util.Set getGermplasmBySubpopulation(String subpopulation) {		
		return  germdao.findAllVarietyBySubpopulation(subpopulation) ;	
	}



	@Override
	public Set getGermplasm() {
		// TODO Auto-generated method stub
		return germdao.findAllVariety() ;
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
	
	
	
	/**
	 * Construct phylogenetic tree for varieties in variety list varid (comma separated)
	 */
	public String constructPhylotree(String varids, String scale,  String requestid) {
		return constructPhylotree(varids,  scale, null, requestid);
	}
	
	@Override
	public String constructPhylotree(String varids, String scale,  String topN,  String requestid) {
		
		//return "'((((penHA34a,penHA34b,penHA32b,penHA32a,penSH30b,penSH30a,penSH28b,penSH28a,penIT13b,penIT13a,penIT12a,firSA26b,firGU7b,firGU8b,firSP18b,firSP20b,firSP36b,firSP39b,penSH31a,penSH31b),(firSP19b,(firSP17b,penIT12b))),firSA24a,firSA24b,firSA25a,firSA26a,firGU7a,firGU8a,firSP17a,firSP18a,firSP19a,firSP20a,firSP36a,firSP39a,(firSA25b,firSP40b),firSP40a,penIT11b,penIT11a),(ovi47a,ovi47b));'";

		//varnames = varnames.replace(' ', '_');
		Set setids = new java.util.TreeSet<BigDecimal>();		
		if(varids.equals("all")) {
			setids.addAll( getMapId2Variety().keySet() );
			if(topN!=null)
				return  constructPhylotree(setids, scale, true, Integer.parseInt(topN), requestid);
			else	
				return  constructPhylotree(setids, scale, true, requestid);
		} else {
			String[] ids = varids.split(",");			
			for(int i=0; i<ids.length; i++) setids.add(BigDecimal.valueOf(Long.parseLong(ids[i])));
			if(topN!=null)
				return  constructPhylotree(setids, scale, false, Integer.parseInt(topN), requestid);
			else	
				return  constructPhylotree(setids, scale, false, requestid);
		}
		
	
	}
	
/**
 * Construct phylogenetic tree for set of germplasm
 */
public String constructPhylotree(Set<BigDecimal> germplasms, String scale,  String requestid) {
		return  constructPhylotree( germplasms,  scale, false, requestid);
}	
	
/**
 * Construct phylogenetic tree for set of germplasm
 * @param germplasms
 * @param scale
 * @param isAll	all germplasm, if true germplasms is ignored
 * @return
 */
private String constructPhylotree(Set<BigDecimal> germplasms, String scale, boolean isAll, String requestid) {
	return constructPhylotree(germplasms,  scale,  isAll, -1, requestid); 
}

private String constructPhylotree(Set<BigDecimal> germplasms, String scale, boolean isAll, int topN, String requestid) {
		
		if(isAll)
			return constructPhylotreeFromCoreNewick();
	
		dist3kdao = (VarietyDistanceDAO)AppContext.checkBean(dist3kdao, "VarietyDistanceDAO");
		dist3kdao.setRequestId(requestid);
		
		

		MemoryMXBean bean = ManagementFactory.getMemoryMXBean(); 
		System.out.println("heap space used MB:" +   bean.getHeapMemoryUsage().getUsed()*1.0/1000000 );
		//Collector
		bean.gc();
		System.out.print("GC successful");
		System.out.println("heap space used MB:" +  bean.getHeapMemoryUsage().getUsed()*1.0/1000000 );
		
		
		// get 
		//Set<Integer> sortedIds =  new java.util.TreeSet<Integer>();
		//Iterator<Integer> itId=germplasms.iterator();
		//while(itId.hasNext() )
		//	sortedNames.add(   mapVarname2IRISId.get(  mapId2Varname.get( itId.next()  )   )[0] );
			  
		
		//java.util.Set sortedNames = new java.util.TreeSet(germplasms);
		
		System.out.println("constructPhylotree: " + germplasms.size() + ", " + scale + "  " + isAll + "  " + topN );
		
		//germplasms
		System.out.println(germplasms.size() + " germplasms");
		log.debug(germplasms.size() + " germplasms, warn");
		log.info(germplasms.size() + " germplasms, info");
		
		
		
		//System.out.println("symdistmatrix done");
		
		
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
			if(topN>0)
				listdist =  dist3kdao.findAllVarietiesTopN(topN);
			else
				listdist =  dist3kdao.findAllVarieties();
		else	
			listdist =  dist3kdao.findVarieties(germplasms);
			
		
		if(topN>0) {
			System.out.println("ORIG:" +  listdist.size() + " distances; varieties " + germplasms.size());
			germplasms=new HashSet();
			java.util.Iterator<VarietyDistance>  itdist = listdist.iterator();
			while(itdist.hasNext())
			{
				VarietyDistance dist3k = itdist.next();
				germplasms.add(dist3k.getVar1());
				germplasms.add(dist3k.getVar2());
			}
			listdist =  dist3kdao.findVarieties(germplasms);
			
			symdistmatrix = new BasicSymmetricalDistanceMatrix(germplasms.size());
			mapVarid2Row = new java.util.HashMap<BigDecimal, Integer>();
			itgerm=germplasms.iterator();
			i=0;
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
		}
		
		dist3kdao.setRequestId(null);

		System.out.println("topN:" + listdist.size() + " distances; varieties " + germplasms.size());
		java.util.Iterator<VarietyDistance>  itdist = listdist.iterator();
		
		
		int distscale =  Integer.parseInt(scale);
		
		while(itdist.hasNext())
		{
			
			VarietyDistance dist3k = itdist.next();
			BigDecimal var1 = dist3k.getVar1();
			BigDecimal var2 = dist3k.getVar2();
			

			if(!mapVarid2Row.containsKey(var1) ) continue ; //throw new RuntimeException("No key " + dist3k.getVar1() + " in mapVarid2Row");
			if(!mapVarid2Row.containsKey(var2) ) continue ; //throw new RuntimeException("No key " + dist3k.getVar2() + " in mapVarid2Row");

			Double dist = dist3k.getDist().doubleValue()*distscale; 
			//Double dist = Double.valueOf( dist3k.getDist().toString() )*distscale;
			//System.out.println(dist3k.getIrisid1() + " "  +  dist3k.getIrisid2() + " " + dist) ;
			
			symdistmatrix.setValue( mapVarid2Row.get(var1 ) , mapVarid2Row.get(var2) , dist );
			symdistmatrix.setValue( mapVarid2Row.get(var2) , mapVarid2Row.get(var1),  dist );
		}
		
		itdist = null;
		listdist = null;
		
		
		System.out.println("heap space used MB:" +  bean.getHeapMemoryUsage().getUsed()*1.0/1000000 );
		bean.gc();
		System.out.print("GC successful, " + symdistmatrix.getSize() + " symdistmatrix ready");
		System.out.println("heap space used MB:" +  bean.getHeapMemoryUsage().getUsed()*1.0/1000000 );
		
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
			//System.out.println(newick);
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
				if( var.getSubpopulation()!=null) subpop =  var.getSubpopulation(); //.replace("/","_").replace(", ","_");
				
				String irisid = "";
				if( var.getIrisId()!=null) irisid=var.getIrisId();
				
				newick = newick.replace("varid_" + c + ":",(var.getName().split("::")[0] + "|" + irisid + "|" + subpop).replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "") + ":"  );
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


	private String constructPhylotreeFromCoreNewick() {
		
		germ2dao = (VarietyDAO)AppContext.checkBean(germ2dao, "VarietyBasicprop2DAO");
	
		String newick= Data.get3kCoreNewick();
	
		
		Iterator<VIricstockBasicprop2> itVars = germ2dao.findAllVariety().iterator();
		while(itVars.hasNext()) {
			VIricstockBasicprop2 var = itVars.next();
			
			
			String subpop = "";
			if( var.getSubpopulation()!=null) subpop =  var.getSubpopulation(); //.replace("/","_").replace(", ","_");
			String irisid = "";
			if( var.getIrisId()!=null) irisid=var.getIrisId();
			
			String boxcode = var.getIrisId();
			if(boxcode==null) boxcode= var.getBoxCode();
			else boxcode=boxcode.replace("IRIS ", "IRIS_");
			newick = newick.replace(boxcode + ":"  ,(var.getName().split("::")[0].replace(", ","_") + "|" + irisid + "|" + subpop).replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "") + ":"  );
		}
		//System.out.println(newick);
		return newick;
	}

	public double[][] constructMDSPlot(String varids, String scale, boolean isAll) {
		
		//return "'((((penHA34a,penHA34b,penHA32b,penHA32a,penSH30b,penSH30a,penSH28b,penSH28a,penIT13b,penIT13a,penIT12a,firSA26b,firGU7b,firGU8b,firSP18b,firSP20b,firSP36b,firSP39b,penSH31a,penSH31b),(firSP19b,(firSP17b,penIT12b))),firSA24a,firSA24b,firSA25a,firSA26a,firGU7a,firGU8a,firSP17a,firSP18a,firSP19a,firSP20a,firSP36a,firSP39a,(firSA25b,firSP40b),firSP40a,penIT11b,penIT11a),(ovi47a,ovi47b));'";
	
		//varnames = varnames.replace(' ', '_');
		List setids = new java.util.ArrayList<BigDecimal>();
		
		/* dont use all because varids ordering is fixed by the caller
		if(varids.equals("all")) {
			setids.addAll( getMapId2Variety().keySet() );
			return  constructMDSPlot(null, scale, true);
		} else {
			String[] ids = varids.split(",");			
			for(int i=0; i<ids.length; i++) setids.add(BigDecimal.valueOf(Long.parseLong(ids[i])));
			return  constructMDSPlot(setids, scale, false);
		}
		*/
		
		String[] ids = varids.split(",");			
		for(int i=0; i<ids.length; i++) setids.add(BigDecimal.valueOf(Long.parseLong(ids[i])));
		return  constructMDSPlot(setids, scale, isAll);
	}


	public double[][] constructMDSPlot(List<BigDecimal> germplasms,  String scale, boolean isAll) {
		return constructMDSPlot( germplasms,  scale, isAll, -1);
	}
	
	public double[][] constructMDSPlot(List<BigDecimal> germplasms,  String scale, boolean isAll, int topN) {
	
		if(isAll)
			return constructMDSPlotFromCore(germplasms);
			
		
		
		dist3kdao = (VarietyDistanceDAO)AppContext.checkBean(dist3kdao, "VarietyDistanceDAO");
		
		MemoryMXBean bean = ManagementFactory.getMemoryMXBean(); 
		System.out.println("heap space used MB:" +   bean.getHeapMemoryUsage().getUsed()*1.0/1000000 );
		//Collector
		bean.gc();
		System.out.print("GC successful");
		System.out.println("heap space used MB:" +  bean.getHeapMemoryUsage().getUsed()*1.0/1000000 );
		
		
		
//		BasicSymmetricalDistanceMatrix symdistmatrix = new BasicSymmetricalDistanceMatrix(germplasms.size());
		java.util.Map<BigDecimal, Integer> mapVarid2Row = new java.util.HashMap<BigDecimal, Integer>();
		

		int distscale =  Integer.parseInt(scale);
		//List listdist =  dist3kdao.findVarieties(buffIrisId.toString());
		List<VarietyDistance> listdist;

		int i=0;

		java.util.Iterator<BigDecimal> itgerm=germplasms.iterator();
		while(itgerm.hasNext()) {
			BigDecimal c = itgerm.next();
			mapVarid2Row.put(c , i);
			i++;			
		}

		
		/*
		if(isAll)
			if(topN>0)
				listdist =  dist3kdao.findAllVarietiesTopN(topN);
			else
				listdist =  dist3kdao.findAllVarieties();
		else	
			listdist =  dist3kdao.findVarieties(new HashSet(germplasms));
		
		if(topN>0) {
			System.out.println("ORIG:" +  listdist.size() + " distances; varieties " + germplasms.size());
			germplasms=new HashSet();
			java.util.Iterator<VarietyDistance>  itdist = listdist.iterator();
			while(itdist.hasNext())
			{
				VarietyDistance dist3k = itdist.next();
				germplasms.add(dist3k.getVar1());
				germplasms.add(dist3k.getVar2());
			}
			listdist =  dist3kdao.findVarieties(germplasms);
			
			symdistmatrix = new BasicSymmetricalDistanceMatrix(germplasms.size());
			mapVarid2Row = new java.util.HashMap<BigDecimal, Integer>();
			itgerm=germplasms.iterator();
			i=0;
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
		}
		

		System.out.println("topN:" + listdist.size() + " distances; varieties " + germplasms.size());
*/		
		
		if(isAll) {
			listdist =  dist3kdao.findAllVarieties();
			i = listdist.size();
		}
		else {
			listdist =  dist3kdao.findVarieties(new HashSet(germplasms));
		}
		
		java.util.Iterator<VarietyDistance>  itdist = listdist.iterator();
		double input[][] = new double[i][i];
		
		while(itdist.hasNext())
		{
			
			VarietyDistance dist3k = itdist.next();

			if(!mapVarid2Row.containsKey(dist3k.getVar1()) ) continue ; //throw new RuntimeException("No key " + dist3k.getVar1() + " in mapVarid2Row");
			if(!mapVarid2Row.containsKey(dist3k.getVar2()) ) continue ; //throw new RuntimeException("No key " + dist3k.getVar2() + " in mapVarid2Row");

			Double dist = dist3k.getDist().doubleValue()*distscale; // Double.valueOf( dist3k.getDist().toString() )*distscale;;
			//System.out.println(dist3k.getIrisid1() + " "  +  dist3k.getIrisid2() + " " + dist) ;
			
			input[ mapVarid2Row.get(dist3k.getVar1() )][mapVarid2Row.get(dist3k.getVar2())] = dist ;
			input[ mapVarid2Row.get(dist3k.getVar2() )][mapVarid2Row.get(dist3k.getVar1())] = dist ;
		}
		
		itdist = null;
		listdist = null;
		
		
		
		
		System.out.println("heap space used MB:" +   bean.getHeapMemoryUsage().getUsed()*1.0/1000000 );
		
		//Collector
		bean.gc();
		
		
		System.out.print("GC successful");
		System.out.println("heap space used MB:" +  bean.getHeapMemoryUsage().getUsed()*1.0/1000000 );
		
		
		return mdsj.MDSJ.classicalScaling(input);
		
		
	}
	
	private double[][] constructMDSPlotFromCore(List<BigDecimal> germplasms)
	{
		Map<String, double[]> mapCode2XY = Data.get3kCoreMDSXY();
		
		
		germ2dao = (VarietyDAO)AppContext.checkBean(germ2dao, "VarietyBasicprop2DAO");
		
		Iterator<VIricstockBasicprop2> itVars = germ2dao.findAllVariety().iterator();
		Map<BigDecimal,VIricstockBasicprop2> mapId2Var = new HashMap();
		while(itVars.hasNext()) {
			VIricstockBasicprop2 var = itVars.next();
			mapId2Var.put(var.getVarietyId(), var);
		}
		
		Iterator itId = germplasms.iterator();
		
		double xy[][] = new double[2][germplasms.size()];
		
		
		int i=0;
		while(itId.hasNext()) {
			VIricstockBasicprop2 var = mapId2Var.get(itId.next());
			if(var.getIrisId()!=null && !var.getIrisId().isEmpty()) {
				  double xyi[] = mapCode2XY.get( var.getIrisId().replace(" ", "_").toUpperCase() );
				  xy[0][i] = xyi[0];
				  xy[1][i] = xyi[1];
				  i++;
			} else if(var.getBoxCode()!=null && !var.getBoxCode().isEmpty() ) {
				  double xyi[] = mapCode2XY.get( var.getBoxCode().toUpperCase() );
				  xy[0][i] = xyi[0];
				  xy[1][i] = xyi[1];
				  i++;
			}
			
		}
		System.out.println(i + " varieties in MDS all");
		
		return xy;
	}
	

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



	@Override
	public Map<String,BigDecimal> getPassportDefinitions() {
		// TODO Auto-generated method stub
		if(passportDefinitions==null) initMoreConstraints();		
		return passportDefinitions;
	}


	@Override
	public  Map<String,BigDecimal>  getPhenotypeDefinitions() {
		// TODO Auto-generated method stub
		if(phenotypeDefinitions==null) initMoreConstraints();
		phenotype_type=PHENOTYPETYPE_NONE;
		return phenotypeDefinitions;
	}
	
	
	@Override
	public Set getPassportUniqueValues(String definition) {
	
		cvpassportValuesDao = (CvTermUniqueValuesDAO)AppContext.checkBean(cvpassportValuesDao, "VCvPassportValuesDAO");
		return cvpassportValuesDao.getUniqueValues(passportDefinitions.get(definition));

	}	

	/*
	@Override
	public Set getPhenotypeUniqueValues(String definition) {
		cvphenotypeValuesDao = (CvTermUniqueValuesDAO)AppContext.checkBean(cvphenotypeValuesDao, "VCvPhenotypeValuesDAO");
		
		Set values = cvphenotypeValuesDao.getUniqueValues(phenotypeDefinitions.get(definition));
		System.out.println( definition + "  =>  " + phenotypeDefinitions.get(definition) + "   values=" + values.size() + " : " + values);
		return values;
	}
	*/	

	@Override
	public Set getPhenotypeUniqueValues(String definition) {
		cvphenotypeQuanValuesDao = (CvTermUniqueValuesDAO)AppContext.checkBean(cvphenotypeQuanValuesDao, "VCvPhenotypeQuanValuesDAO");
		Set values = cvphenotypeQuanValuesDao.getUniqueValues(phenotypeDefinitions.get(definition));
		System.out.println( definition + "  =>  " + phenotypeDefinitions.get(definition) + "   values=" + values.size() + " : " + values);
		
		phenotype_type=PHENOTYPETYPE_QUAN;
		
		if(values.size()==1 && values.iterator().next()==null ) {
			
			cvphenotypeQualValuesDao = (CvTermUniqueValuesDAO)AppContext.checkBean(cvphenotypeQualValuesDao, "VCvPhenotypeQualValuesDAO");
			values = cvphenotypeQualValuesDao.getUniqueValues(phenotypeDefinitions.get(definition));
			System.out.println( definition + "  =>  " + phenotypeDefinitions.get(definition) + "   values=" + values.size() + " : " + values);
			phenotype_type=PHENOTYPETYPE_QUAL;
		}
		return values;
	}
	
	@Override
	public List getVarietyByPassport(String definition, String value) {
		varbypassportdao = (VarietyByPassportDAO)AppContext.checkBean(varbypassportdao, "VIricstocksByPassportDAO");
		return varbypassportdao.findVarietyByPassportEquals(passportDefinitions.get(definition), value);
	}
	
	@Override
	public List getVarietyByPhenotype(String definition, String comparator,  String value) {
		
		varbyphenotypedao = (VarietyByPhenotypeDAO)AppContext.checkBean(varbyphenotypedao, "VIricstocksByPhenotypeDAO");
		
		/*
		@Autowired
		@Qualifier("VCvPhenotypeQualValuesDAO")
		private CvTermUniqueValuesDAO cvphenotypeQualValuesDao;
		
		@Autowired
		@Qualifier("VCvPhenotypeQuanValuesDAO")
		private CvTermUniqueValuesDAO cvphenotypeQuanValuesDao;
		*/
		
		if(phenotype_type==PHENOTYPETYPE_QUAN) {
			
			if(comparator.equals(COMPARATOR_EQUALS) ) {
				return varbyphenotypedao.findVarietyByQuanPhenotypeEquals(phenotypeDefinitions.get(definition),  Double.valueOf(value));
			} else if( comparator.equals(COMPARATOR_LESSTHAN) ){
				return varbyphenotypedao.findVarietyByQuanPhenotypeLessThan(phenotypeDefinitions.get(definition),  Double.valueOf(value));
			} else if( comparator.equals(COMPARATOR_GREATERTHAN) ){
				return varbyphenotypedao.findVarietyByQuanPhenotypeGreaterThan(phenotypeDefinitions.get(definition),  Double.valueOf(value));
			}
			
		} else if(phenotype_type==PHENOTYPETYPE_QUAL) {
			return varbyphenotypedao.findVarietyByQualPhenotypeEquals(phenotypeDefinitions.get(definition), value);
		}
		
		//varbypassportdao = (VarietyByPassportDAO)AppContext.checkBean(varbypassportdao, "VIricstocksByPassportDAO");
		//return varbypassportdao.findVarietyByPassportEquals(phenotypeDefinitions.get(definition), value);
		return null;
	}




	
	
	
	
}
