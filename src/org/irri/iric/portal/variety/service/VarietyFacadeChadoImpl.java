package org.irri.iric.portal.variety.service;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.chado.oracle.domain.VIricstocksByPhenotype;
import org.irri.iric.portal.dao.CvTermDAO;
import org.irri.iric.portal.dao.CvTermUniqueValuesDAO;
import org.irri.iric.portal.dao.IricstockPassportDAO;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.dao.PhenotypeDAO;
import org.irri.iric.portal.dao.VarietyByPassportDAO;
import org.irri.iric.portal.dao.VarietyByPhenotypeDAO;
import org.irri.iric.portal.dao.VarietyDAO;
import org.irri.iric.portal.dao.VarietyDistanceDAO;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.domain.VarietyDistance;
import org.irri.iric.portal.genomics.OntologyService;
import org.irri.iric.portal.variety.VarietyFacade;
import org.irri.iric.portal.variety.VarietyPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.biojava3.phylo.TreeConstructor;
import org.forester.evoinference.matrix.distance.BasicSymmetricalDistanceMatrix;

@Service("VarietyFacade")
public class VarietyFacadeChadoImpl implements VarietyFacade {

	private static final Log log = LogFactory.getLog(VarietyFacadeChadoImpl.class);

	
	// Variety query DAOs
	
	@Autowired
	//@Qualifier("VarietyBasicprop2DAO")
	@Qualifier("VarietyDAO")
	private VarietyDAO germ2dao;

	@Autowired
	private VarietyByPassportDAO varbypassportdao;

	@Autowired
	@Qualifier("VIricstocksByPhenotypeDAO")
	private VarietyByPhenotypeDAO varbyphenotypedao;

	
	@Autowired
	@Qualifier("VIricstocksByPtocoDAO")
	private VarietyByPhenotypeDAO varbyptocodao;

	@Autowired
	private PlantTraitRiceVarietyOntologyService traitvarietyservice; 
	
	// Passport DAOs
	//@Autowired
	//@Qualifier("VCvPassportDAOPostges")
	//private CvTermDAO cvtermsPassportdao;
	//private VCvPassportDAO cvtermsPassportdao;
	
	@Autowired
	private IricstockPassportDAO passportdao;

	//@Autowired
	//@Qualifier("VCvPassportValuesDAOPostges")
	//@Qualifier("VIricstockPassportValuesDAOPostges")
	private CvTermUniqueValuesDAO cvpassportValuesDao;

	
	// Phenotype DAOs
	@Autowired
	private PhenotypeDAO phendao;
	
	//@Autowired
	//@Qualifier("VCvPhenotypeDAO")
	//@Qualifier("VCvPhenotypeDAOPostges")
	private CvTermDAO cvtermsPhenotypedao;
	
	//@Autowired
	//@Qualifier("VCvPhenotypeQualValuesDAO")
	//@Qualifier("VIricstockPhenotypeQualvalDAOPostges")
	private CvTermUniqueValuesDAO cvphenotypeQualValuesDao;
	
	//@Autowired
	//@Qualifier("VCvPhenotypeQuanValuesDAO")
	//@Qualifier("VIricstockPhenotypeQuanvalDAOPostges")
	private CvTermUniqueValuesDAO cvphenotypeQuanValuesDao;
	

	// phylogenetic tree construction daos
	@Autowired
	private VarietyDistanceDAO dist3kdao;


	// User interface Listboxes values DAO
	@Autowired
	//@Qualifier("ListItemsDAO")
	private ListItemsDAO listitemsDAO;
	

	@Autowired
	private VarietyPropertiesService varpropservice;
	
	
	// Class methods
	// Methods with @Override annotation are implementations
	// defined and documented in the Interface
	
	
	// Variety instance query methods
	@Override
	public Map getIrisId2Variety() {
		return listitemsDAO.getIrisId2Variety();
	}
	
	@Override
	public Variety getGermplasmByIrisId(String name)
	{
		return listitemsDAO.getGermplasmByIrisId(name);
	}
	
	@Override
	public Variety getGermplasmByNameLike(String name) {
		return listitemsDAO.getGermplasmByNameLike(name);
		
	}
	
	@Override
	public Variety getGermplasmByName(String name) {
		return listitemsDAO.getGermplasmByName(name);
		
	}
	
	@Override
	public java.util.Set getGermplasmByCountry(String country) {		
		return listitemsDAO.getGermplasmByCountry(country);
	}
	
	@Override
	public java.util.Set getGermplasmBySubpopulation(String subpopulation) {		
		return listitemsDAO.getGermplasmBySubpopulation(subpopulation);
	}

	@Override
	public Set getGermplasm() {
		// TODO Auto-generated method stub
		return listitemsDAO.getGermplasm();
	}
	
	@Override
	public Set getGermplasmByExample(Variety germplasm) {
		// TODO Auto-generated method stub
		return listitemsDAO.getGermplasmByExample(germplasm);
	}
	
	@Override
	public java.util.Map<String, Variety> getMapVarname2Variety() {
		listitemsDAO = (ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItems"); 
		return listitemsDAO.getMapVarname2Variety();
	}

	@Override
	public Map<BigDecimal, Variety> getMapId2Variety() {
		listitemsDAO = (ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItems"); 
		return listitemsDAO.getMapId2Variety();
	}



	
	@Override
	public List getVarietyByPassport(String definition, String value) {
		varbypassportdao = (VarietyByPassportDAO)AppContext.checkBean(varbypassportdao, "VIricstocksByPassportDAO");
		listitemsDAO = (ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItems"); 
		return varbypassportdao.findVarietyByPassportEquals(listitemsDAO.getPassportDefinitions().get(definition), value);
	}
	
	

	@Override
	public List getVarietyByPhenotype(String definition, String comparator,  String value, int phenotype_type) {
		
		varbyphenotypedao = (VarietyByPhenotypeDAO)AppContext.checkBean(varbyphenotypedao, "VIricstocksByPhenotypeDAO");
		listitemsDAO = (ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItems"); 

		if(phenotype_type==ListItemsDAO.PHENOTYPETYPE_QUAN) {
			
			if(comparator.equals(COMPARATOR_EQUALS) ) {
				return varbyphenotypedao.findVarietyByQuanPhenotypeEquals(listitemsDAO.getPhenotypeDefinitions().get(definition),  Double.valueOf(value));
			} else if( comparator.equals(COMPARATOR_LESSTHAN) ){
				return varbyphenotypedao.findVarietyByQuanPhenotypeLessThan(listitemsDAO.getPhenotypeDefinitions().get(definition),  Double.valueOf(value));
			} else if( comparator.equals(COMPARATOR_GREATERTHAN) ){
				return varbyphenotypedao.findVarietyByQuanPhenotypeGreaterThan(listitemsDAO.getPhenotypeDefinitions().get(definition),  Double.valueOf(value));
			}
			
		} else if(phenotype_type==ListItemsDAO.PHENOTYPETYPE_QUAL) {
			return varbyphenotypedao.findVarietyByQualPhenotypeEquals(listitemsDAO.getPhenotypeDefinitions().get(definition), value);
		}
		
		return null;
	}
	
	
	@Override
	public List getPhenotypeByPtoco(String cv, String term) {
			traitvarietyservice=(PlantTraitRiceVarietyOntologyService)AppContext.checkBean(traitvarietyservice, "PlantTraitRiceVarietyOntologyService") ;
			return traitvarietyservice.getCVtermDescendants(cv, term);
	}
	
	

	@Override
	public List getVarietyByPhenotype(String phenId) {
		// TODO Auto-generated method stub
		varbyphenotypedao = (VarietyByPhenotypeDAO)AppContext.checkBean(varbyphenotypedao, "VIricstocksByPhenotypeDAO");
		listitemsDAO = (ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItems");
		
		BigDecimal bdId=null;
		try {
			bdId= BigDecimal.valueOf(Long.valueOf(phenId));
		} catch(Exception ex) {
			bdId = listitemsDAO.getPhenotypeDefinitions().get(phenId);
		}
		
		
		return varbyphenotypedao.findVarietyByPhenotype(bdId);
				 
	}

	// User Interface Listbox Items query methods
	@Override
	public java.util.List getVarietyNames() {
		return listitemsDAO.getVarietyNames();

	}
	
	@Override
	public java.util.List getIrisIds() {
		return listitemsDAO.getIrisIds();
	}

	@Override
	public java.util.List getCountries() {
		return listitemsDAO.getCountries();
	}


	@Override
	public java.util.List getSubpopulations() {
		return listitemsDAO.getSubpopulations();
	}

	// Passport query methods 

	@Override
	public Set getPassportByVarietyid(BigDecimal id) {
		// TODO Auto-generated method stub
		//return passportdao.findVIricstockPassportByIricStockId( );
		return passportdao.findIricstockPassportByIricStockId(id );
	}

	@Override
	public Map<String,BigDecimal> getPassportDefinitions() {
		// TODO Auto-generated method stub
		listitemsDAO = (ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItems"); 
		return listitemsDAO.getPassportDefinitions();
	}
	

	@Override
	public Set getPassportUniqueValues(String definition) {
	
		cvpassportValuesDao = (CvTermUniqueValuesDAO)AppContext.checkBean(cvpassportValuesDao, "VCvPassportValuesDAO");
		listitemsDAO = (ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItems"); 
		return cvpassportValuesDao.getUniqueValues( listitemsDAO.getPassportDefinitions().get(definition));

	}	


	// Phenotypes query methods

	@Override
	public List getPhenotypesByGermplasm(Variety var) {
		return phendao.findPhenotypesByVariety(var);
	}
	
	@Override
	public List getPhenotypesByGermplasmName(String name) {
		return phendao.findPhenotypesByVarietyNameLike(name);
	}	
	
	@Override
	public  Map<String,BigDecimal>  getPhenotypeDefinitions() {
		// TODO Auto-generated method stub
		listitemsDAO = (ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItems"); 
		return listitemsDAO.getPhenotypeDefinitions();
	}

	@Override
	public  Map<String,BigDecimal>  getPtocoDefinitions() {
		// TODO Auto-generated method stub
		listitemsDAO = (ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItems"); 
		return listitemsDAO.getPtocoDefinitions();
	}

	@Override
	public Object[] getPhenotypeUniqueValues(String definition) {
		cvphenotypeQuanValuesDao = (CvTermUniqueValuesDAO)AppContext.checkBean(cvphenotypeQuanValuesDao, "VCvPhenotypeQuanValuesDAO");
		listitemsDAO = (ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItems"); 
		Map<String,BigDecimal> phenotypeDefinitions = listitemsDAO.getPhenotypeDefinitions();
		Set values = cvphenotypeQuanValuesDao.getUniqueValues(phenotypeDefinitions.get(definition));
		//System.out.println( definition + "  =>  " + phenotypeDefinitions.get(definition) + "   values=" + values.size() + " : " + values);
		
		int phenotype_type=ListItemsDAO.PHENOTYPETYPE_QUAN;
		
		if(values.size()==1 && values.iterator().next()==null ) {
			
			cvphenotypeQualValuesDao = (CvTermUniqueValuesDAO)AppContext.checkBean(cvphenotypeQualValuesDao, "VCvPhenotypeQualValuesDAO");
			values = cvphenotypeQualValuesDao.getUniqueValues(phenotypeDefinitions.get(definition));
			//System.out.println( definition + "  =>  " + phenotypeDefinitions.get(definition) + "   values=" + values.size() + " : " + values);
			phenotype_type=ListItemsDAO.PHENOTYPETYPE_QUAL;
		}
		
		return  new Object[] {values, phenotype_type}; 
	}
	
	
	
	
	
	
	@Override
	public Map<BigDecimal, Object> getPhenotypeValues(String phenotype) {
		// TODO Auto-generated method stub
	
		Map mapVarid2Value=new HashMap();
		Iterator<VIricstocksByPhenotype> itVars = getVarietyByPhenotype(phenotype).iterator();
		while(itVars.hasNext()) {
			VIricstocksByPhenotype varval=itVars.next();
			mapVarid2Value.put( varval.getIricStockId() , varval.getValue());
		}
		return  mapVarid2Value; 
	}
	

	// Phylogenetic tree construction methods
	@Override
	public String constructPhylotree(String varids, String scale,  String requestid) {
		return constructPhylotree(varids,  scale, null, requestid);
	}
	
	@Override
	public String constructPhylotree(String varids, String scale,  String topN,  String requestid) {
		
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

	@Override
	public String constructPhylotree(Set<BigDecimal> germplasms, String scale,  String requestid) {
			return  constructPhylotree( germplasms,  scale, false, requestid);
	}	
	
	
	private String constructPhylotree(Set<BigDecimal> germplasms, String scale, boolean isAll, String requestid) {
		return constructPhylotree(germplasms,  scale,  isAll, -1, requestid); 
	}

	/**
	 * Construct phylogenetic tree  
	 * @param germplasms	set of germplams (iric_stock_id) to include
	 * @param scale			adjust distances by this factor
	 * @param isAll			if true, all varieties are included, germplasms is ignored
	 * @param topN			consider only the topN distances to construct the tree, only varieties in these pairs are included,
	 * 						-1 to include alll pairs
	 * @param requestid		requestid from the user interface, use to identify the database query
	 * @return				Newick format of tree
	 */
	private String constructPhylotree(Set<BigDecimal> germplasms, String scale, boolean isAll, int topN, String requestid) {
		
		if(isAll)
			// use a precomputed newick tree using all varieties on the core set
			return constructPhylotreeFromCoreNewick();
	
		
		dist3kdao = (VarietyDistanceDAO)AppContext.checkBean(dist3kdao, "VarietyDistanceDAO");
		dist3kdao.setRequestId(requestid);

		// first clean up memory 
		MemoryMXBean bean = ManagementFactory.getMemoryMXBean(); 
		AppContext.debug("heap space used MB:" +   bean.getHeapMemoryUsage().getUsed()*1.0/1000000 );
		bean.gc();
		AppContext.debug("GC successful: heap space used MB:" +  bean.getHeapMemoryUsage().getUsed()*1.0/1000000 );
		
		
		AppContext.debug("constructPhylotree: " + germplasms.size() + ", " + scale + "  " + isAll + "  " + topN );
		

		// construct distance matrix
		BasicSymmetricalDistanceMatrix symdistmatrix = new BasicSymmetricalDistanceMatrix(germplasms.size());
		
		java.util.Iterator<BigDecimal> itgerm=germplasms.iterator();
		int i=0;
		java.util.Map<BigDecimal, Integer> mapVarid2Row = new java.util.HashMap<BigDecimal, Integer>();

		// setup column/row identifiers
		Map<BigDecimal,Variety> mapId2Variety = getMapId2Variety();
		while(itgerm.hasNext()) {
			BigDecimal c = itgerm.next();
			symdistmatrix.setIdentifier( i, "varid_" + mapId2Variety.get(c).getVarietyId() );
			mapVarid2Row.put(c , i);
			i++;			
		}

		// get list of distances
		List<VarietyDistance> listdist;
		if(isAll)
			if(topN>0)
				listdist =  dist3kdao.findAllVarietiesTopN(topN);
			else
				listdist =  dist3kdao.findAllVarieties();
		else	
			listdist =  dist3kdao.findVarieties(germplasms);
			
		
		if(topN>0) {

			// if only the topN distance pairs are considered, 
			// get the new set of distances where only varieties in these top pairs are included
			
			AppContext.debug("ORIG:" +  listdist.size() + " distances; varieties " + germplasms.size());
			germplasms=new HashSet();
			java.util.Iterator<VarietyDistance>  itdist = listdist.iterator();
			while(itdist.hasNext())
			{
				VarietyDistance dist3k = itdist.next();
				germplasms.add(dist3k.getVar1());
				germplasms.add(dist3k.getVar2());
			}
			listdist =  dist3kdao.findVarieties(germplasms);

			// setup column/row identifiers in new distance matrix
			symdistmatrix = new BasicSymmetricalDistanceMatrix(germplasms.size());
			mapVarid2Row = new java.util.HashMap<BigDecimal, Integer>();
			itgerm=germplasms.iterator();
			i=0;
			while(itgerm.hasNext()) {
				BigDecimal c = itgerm.next();
				symdistmatrix.setIdentifier( i, "varid_" + mapId2Variety.get(c).getVarietyId() );
				mapVarid2Row.put(c , i);
				i++;			
			}
			
			AppContext.debug("After topN:" + listdist.size() + " distances; varieties " + germplasms.size());
		}
		
		dist3kdao.setRequestId(null);

		// construct the distance matrix
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
			
			symdistmatrix.setValue( mapVarid2Row.get(var1 ) , mapVarid2Row.get(var2) , dist );
			symdistmatrix.setValue( mapVarid2Row.get(var2) , mapVarid2Row.get(var1),  dist );
		}
		
		itdist = null;
		listdist = null;
		

		// clean memory again, for tree construction
		bean.gc();
		
		try {
			
			TreeConstructor tree = new  TreeConstructor(symdistmatrix,
				org.biojava3.phylo.TreeType.NJ ,
				org.biojava3.phylo.TreeConstructionAlgorithm.PID ,
				//	null);
				new org.biojava3.phylo.ProgessListenerStub());
				tree.process();

				AppContext.debug("Tree construction process done");

			String newick = tree.getNewickString(false, true);
			
			Iterator<BigDecimal> itgerm2 = germplasms.iterator();
			while(itgerm2.hasNext()) {
				BigDecimal c = itgerm2.next();
				
				Variety var = mapId2Variety.get(c);
				
				String subpop = "";
				if( var.getSubpopulation()!=null) subpop =  var.getSubpopulation(); //.replace("/","_").replace(", ","_");
				
				String irisid = "";
				if( var.getIrisId()!=null) irisid=var.getIrisId();

				// remove invalid characters for newick format
				newick = newick.replace("varid_" + c + ":",(var.getName().split("::")[0] + "|" + irisid + "|" + subpop).replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "") + ":"  );
			}
			
			//AppContext.debug(newick);
			return newick;
			
		} catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}


	// get newick for all varieties, using core snp distances 
	private String constructPhylotreeFromCoreNewick() {
		
		germ2dao = (VarietyDAO)AppContext.checkBean(germ2dao, "VarietyDAO");

		// get newick
		String newick= Data.get3kCoreNewick();
		Iterator<Variety> itVars = germ2dao.findAllVariety().iterator();
		
		// rename the varieties using NAME/IRISID/SUBPOPULATION
		while(itVars.hasNext()) {
			Variety var = itVars.next();
			
			String subpop = "";
			if( var.getSubpopulation()!=null) subpop =  var.getSubpopulation(); //.replace("/","_").replace(", ","_");
			String irisid = "";
			if( var.getIrisId()!=null) irisid=var.getIrisId();
			
			String boxcode = var.getIrisId();
			if(boxcode==null) boxcode= var.getBoxCode();
			else boxcode=boxcode.replace("IRIS ", "IRIS_");
			newick = newick.replace(boxcode + ":"  ,(var.getName().split("::")[0].replace(", ","_") + "|" + irisid + "|" + subpop).replace(" ", "_").replace("'","").replace("(", "").replace(")", "").replace("\"", "") + ":"  );
		}
		return newick;
	}

	@Override
	public double[][] constructMDSPlot(String varids, String scale, boolean isAll) {
		
		List setids = new java.util.ArrayList<BigDecimal>();
		String[] ids = varids.split(",");			
		for(int i=0; i<ids.length; i++) setids.add(BigDecimal.valueOf(Long.parseLong(ids[i])));
		return  constructMDSPlot(setids, scale, isAll);
	}

	@Override
	public double[][] constructMDSPlot(List<BigDecimal> germplasms,  String scale, boolean isAll) {
		return constructMDSPlot( germplasms,  scale, isAll, -1);
	}
	
	/**
	 * Calculate MDS plot xy coordinates 
	 * @param germplasms	List of germplasms, ordering is important because the caller will use it to plot
	 * @param scale			adjust distances with this factor
	 * @param isAll			Include all varieties?
	 * @param topN			-- not yet implemented
	 * @return				2xN matrix, xy coordinates, the same order as in the List of germplasms
	 */
	private double[][] constructMDSPlot(List<BigDecimal> germplasms,  String scale, boolean isAll, int topN) {
	
		if(isAll)
			return constructMDSPlotFromCore(germplasms);
			
		dist3kdao = (VarietyDistanceDAO)AppContext.checkBean(dist3kdao, "VarietyDistanceDAO");
		
		MemoryMXBean bean = ManagementFactory.getMemoryMXBean(); 
		AppContext.debug("heap space used MB:" +   bean.getHeapMemoryUsage().getUsed()*1.0/1000000 );
		bean.gc();
		AppContext.debug("GC successful: heap space used MB:" +  bean.getHeapMemoryUsage().getUsed()*1.0/1000000 );
		
		java.util.Map<BigDecimal, Integer> mapVarid2Row = new java.util.HashMap<BigDecimal, Integer>();
		
		int distscale =  Integer.parseInt(scale);
		List<VarietyDistance> listdist;

		int i=0;

		// setup ids, columns, rows
		java.util.Iterator<BigDecimal> itgerm=germplasms.iterator();
		while(itgerm.hasNext()) {
			BigDecimal c = itgerm.next();
			mapVarid2Row.put(c , i);
			i++;			
		}

		if(isAll) {
			listdist =  dist3kdao.findAllVarieties();
			i = listdist.size();
		}
		else {
			listdist =  dist3kdao.findVarieties(new HashSet(germplasms));
		}
		
		java.util.Iterator<VarietyDistance>  itdist = listdist.iterator();
		double input[][] = new double[i][i];
		
		// construct the distance matrix
		while(itdist.hasNext())
		{
			
			VarietyDistance dist3k = itdist.next();

			if(!mapVarid2Row.containsKey(dist3k.getVar1()) ) continue ; //throw new RuntimeException("No key " + dist3k.getVar1() + " in mapVarid2Row");
			if(!mapVarid2Row.containsKey(dist3k.getVar2()) ) continue ; //throw new RuntimeException("No key " + dist3k.getVar2() + " in mapVarid2Row");

			Double dist = dist3k.getDist().doubleValue()*distscale; // Double.valueOf( dist3k.getDist().toString() )*distscale;;
			
			input[ mapVarid2Row.get(dist3k.getVar1() )][mapVarid2Row.get(dist3k.getVar2())] = dist ;
			input[ mapVarid2Row.get(dist3k.getVar2() )][mapVarid2Row.get(dist3k.getVar1())] = dist ;
		}
		
		itdist = null;
		listdist = null;
		
		bean.gc();
		return mdsj.MDSJ.classicalScaling(input);
	}
	
	
	/**
	 * Construct mds for all varieties
	 * @param germplasms
	 * @return	2xN matrix of xy coordinates
	 */
	private double[][] constructMDSPlotFromCore(List<BigDecimal> germplasms)
	{
		
		Map<String, double[]> mapCode2XY = Data.get3kCoreMDSXY();
		
		germ2dao = (VarietyDAO)AppContext.checkBean(germ2dao, "VarietyDAO");
		
		
		Iterator<Variety> itVars = germ2dao.findAllVariety().iterator();
		Map<BigDecimal,Variety> mapId2Var = new HashMap();
		while(itVars.hasNext()) {
			Variety var = itVars.next();
			mapId2Var.put(var.getVarietyId(), var);
		}
		
		Iterator itId = germplasms.iterator();
		
		double xy[][] = new double[2][germplasms.size()];
		
		
		int i=0;
		while(itId.hasNext()) {
			Variety var = mapId2Var.get(itId.next());
			if(var.getIrisId()!=null && !var.getIrisId().isEmpty()) {
				  double xyi[] = mapCode2XY.get( var.getIrisId().replace(" ", "_").toUpperCase() );
				  if(xyi==null) { i++;  continue ;};
				  xy[0][i] = xyi[0];
				  xy[1][i] = xyi[1];
				  i++;
			} else if(var.getBoxCode()!=null && !var.getBoxCode().isEmpty() ) {
				  double xyi[] = mapCode2XY.get( var.getBoxCode().toUpperCase() );
				  if(xyi==null) { i++;  continue; };				  
				  xy[0][i] = xyi[0];
				  xy[1][i] = xyi[1];
				  i++;
			}
			
		}
		//AppContext.debug(i + " varieties in MDS all");
		
		return xy;
		
		
	}

	@Override
	public Map getVarietyExternalURL(String name) {
		// TODO Auto-generated method stub
		
		varpropservice = (VarietyPropertiesService)AppContext.checkBean(varpropservice, "VarietyPropertiesService");
		
		return varpropservice.getProperties(name);
	}
	
	@Override
	public Set checkVariety(String varstrs) {
	    	Set varset = new LinkedHashSet();
	    	String varstrarr[] = varstrs.split(",");
	    	for(int ivar = 0; ivar<  varstrarr.length ; ivar++ ) {
	    		String varstr = varstrarr[ivar];
	    		try {
					Variety var = getGermplasmByName(varstr);
					if(var==null)
						var = getGermplasmByIrisId(varstr);
					if(var==null)
						var = getGermplasmByIrisId("IRIS " + varstr.replace("IRIS_",""));
					if(var==null)
						var = getGermplasmByNameLike(varstr);
					if(var==null)
						var = getGermplasmByNameLike("%" + varstr);
					if(var==null)
						var = getGermplasmByNameLike("%:irgc " + varstr);
					if(var==null)
						var = getGermplasmByNameLike(varstr+"%");
					if(var!=null) 
						varset.add(var);
	    		} catch(Exception ex) {
	    			ex.printStackTrace();
	    		}
	    	}
	    	return varset;
		}
	 
}


// PAST CODES RETAINED

/*
@Override
public List getVarietyByPassport(String sPassId) {
	// TODO Auto-generated method stub
	varbypassportdao = (VarietyByPassportDAO)AppContext.checkBean(varbypassportdao, "VIricstocksByPassportDAO");
	//listitemsDAO = (ListItemsDAO)AppContext.checkBean(listitemsDAO, "ListItemsDAO"); 
	return varbypassportdao.findVarietyByPassport(sPassId);
}
*/