package org.irri.iric.portal.genotype.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.dao.MultipleReferenceConverterDAO;
import org.irri.iric.portal.dao.ScaffoldDAO;
import org.irri.iric.portal.dao.SnpsAllvarsMultirefsPosDAO;
import org.irri.iric.portal.dao.SnpsAllvarsPosDAO;
import org.irri.iric.portal.dao.SnpsNonsynAllvarsDAO;
import org.irri.iric.portal.dao.SnpsSpliceAcceptorDAO;
import org.irri.iric.portal.dao.SnpsSpliceDonorDAO;
import org.irri.iric.portal.dao.SnpsStringDAO;
import org.irri.iric.portal.dao.SnpsSynAllvarsDAO;
import org.irri.iric.portal.domain.MultiReferenceLocus;
import org.irri.iric.portal.domain.MultiReferenceLocusImpl;
import org.irri.iric.portal.domain.Organism;
import org.irri.iric.portal.domain.Position;
import org.irri.iric.portal.domain.SnpsAllvarsMultirefsPos;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsNonsynAllele;
import org.irri.iric.portal.domain.SnpsSpliceAcceptor;
import org.irri.iric.portal.domain.SnpsSpliceDonor;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.domain.SnpsStringAllvarsImpl;
import org.irri.iric.portal.domain.SnpsSynAllele;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.genotype.GenotypeQueryParams;
import org.irri.iric.portal.genotype.VariantSnpsStringData;
import org.irri.iric.portal.genotype.VariantStringData;
import org.irri.iric.portal.genotype.VariantStringService;
import org.irri.iric.portal.hdf5.H5Dataset;
//import org.irri.iric.portal.flatfile.dao.SnpcoreRefposindexDAO;
//import org.irri.iric.portal.flatfile.domain.VSnpRefposindex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zkoss.zul.Messagebox;

@Service("SnpsStringService")
public class SnpsStringMultiHDF5nRDBMSHybridService implements VariantStringService {

	// Other refs HDF5 DAOs
	private Map<BigDecimal, H5Dataset> mapFileId2DatasetDAO=new HashMap();
	
	@Autowired
	@Qualifier("H5SNPUniIUPACV2DAO")
	private H5Dataset  nballsnpsdao;
	
	@Autowired
	@Qualifier("H5SNPCoreIUPACV2DAO")
	private H5Dataset  nbcoresnpsdao;
	
	/*
	@Autowired
	@Qualifier("VSnpRefposindexDAO")		// snps reference, allele position file index
	private SnpsAllvarsPosDAO snpstringallvarsposDAO;
	*/
	
	@Autowired
	ScaffoldDAO scaffolddao;
	
	
	@Autowired
	//@Qualifier("VConvertposAny2allrefsDAO")		// snps reference, allele position file index
	@Qualifier("MultipleReferenceConverterDAO")		// snps reference, allele position file index
	private SnpsAllvarsPosDAO snpstringallvarsposDAO;
	//private SnpsAllvarsMultirefsPosDAO snpstringallvarsposDAO;

	
	@Autowired
	@Qualifier("VSnpNonsynallelePosDAO")
	private SnpsNonsynAllvarsDAO snpsnonsynDAO;
	@Autowired
	@Qualifier("VSnpSynallelePosDAO")
	private SnpsSynAllvarsDAO snpssynDAO;

	@Autowired
	private SnpsSpliceAcceptorDAO snpsspliceacceptorDAO;
	@Autowired
	private SnpsSpliceDonorDAO snpssplicedonorDAO;
	
	@Autowired
	@Qualifier("MultipleReferenceConverterDAO")
	//@Qualifier("MultipleReferenceConverterPrecompDAO")
	private MultipleReferenceConverterDAO multiplerefconvertdao;
	
	
	@Autowired
	private ListItemsDAO listitemsdao; 
	

	// these DAOs will be assigned based on selected datatype 
	
	//@Autowired
	//@Qualifier("H5SNPUniAllele1DAO")
	private SnpsStringDAO snpstrAllsnpsAllele1AllvarsDAO;

	//@Autowired
	//@Qualifier("H5SNPUniAllele2DAO")
	private SnpsStringDAO snpstrAllsnpsAllele2AllvarsDAO;

	//@Autowired
	//@Qualifier("H5SNPCoreAllele1DAO")
	private SnpsStringDAO snpstrCoresnpsAllele1AllvarsDAO;

	//@Autowired
	//@Qualifier("H5SNPCoreAllele2DAO")
	private SnpsStringDAO snpstrCoresnpsAllele2AllvarsDAO;
	
	
	
	@Override
	public List checkSNPsInChromosome(String chr, Collection posset, BigDecimal type) {
		// TODO Auto-generated method stub
		snpstringallvarsposDAO = (SnpsAllvarsPosDAO)AppContext.checkBean(snpstringallvarsposDAO, "VSnpRefposindexDAO") ;
		
		return snpstringallvarsposDAO.getSNPsInChromosome(chr, posset, type);
	}

	

	/**
	 * Query SNPs
	 */
	@Override
	public VariantStringData getVariantString(GenotypeQueryParams params) throws Exception { //, boolean exactMismatch, int firstRow, int maxRows) {
	
		// if reference is not Nipponbare
		//		convert positions to other references		
		// if show all reference alleles
		//		if get other reference alleles at positions
		//			convert positions to other references
		//		
		
		VariantStringData vardata = null;
		MultiReferenceLocus locusNipponbare=null;
		if(!params.getOrganism().equals( AppContext.getDefaultOrganism() )) {
			// not nipponbare coordinate. convert coordinates
			
			if(params.getsChr()!=null && params.getlStart()!=null && params.getlEnd()!=null) {

				VariantStringData variantstringdataNPB =  _getSNPsStringQueryReference(params);
				
				MultiReferenceLocus locusQueried = new MultiReferenceLocusImpl(params.getOrganism(), params.getsChr(), params.getlStart().intValue(), params.getlEnd().intValue(), 1);
				if(params.isbShowAllRefAlleles()) {
					vardata = multiplerefconvertdao.convertReferencePositions(variantstringdataNPB, locusNipponbare, locusQueried, null, false);
				}
				/*
				if(params.isbShowAllRefAlleles()) {
					vardata = convertReferencePositionsToAllReferences(params, vardata, locusNipponbare);
					AppContext.debug( "show other alllele refs = " + vardata.getMapOrg2MSU7Pos2ConvertedPos().keySet());
				}
				*/
					
//				multiplerefconvertdao = (MultipleReferenceConverterDAO)AppContext.checkBean(multiplerefconvertdao, "MultipleReferenceConverterDAO");
//				
//				MultiReferenceLocus locusQueried = new MultiReferenceLocusImpl(params.getOrganism(), params.getsChr(), params.getlStart().intValue(), params.getlEnd().intValue(), 1);
//				locusNipponbare = multiplerefconvertdao.convertLocus( locusQueried ,  AppContext.getDefaultOrganism(),  null); 
//				MultiReferenceLocus origMultiReferenceLocus =  params.setNewPosition(locusNipponbare);
//				
//				
//				VariantStringData variantstringdataNPB =  _getSNPsStringNipponbare(params);
//				
//				String toContig = null;
//				if(params.isLimitToQueryContig()) {
//					toContig = locusQueried.getContig();
//				}
//				
//				variantstringdataNPB.setMessage( variantstringdataNPB.getMessage() + "\nSNP Query " + locusQueried + " aligned with " + locusNipponbare);
//				params.setNewPosition(origMultiReferenceLocus);
//				vardata = multiplerefconvertdao.convertReferencePositions(variantstringdataNPB, locusNipponbare, locusQueried, toContig, false);
//
//				
//				if(params.isbShowAllRefAlleles()) {
//					vardata = convertReferencePositionsToAllReferences(params, vardata, locusNipponbare);
//					AppContext.debug( "show other alllele refs = " + vardata.getMapOrg2MSU7Pos2ConvertedPos().keySet());
//				}
				
			}
			else  throw new RuntimeException("Multiple reference query not yet allowed for SNP/locus lists"); 

			
		} else {

			vardata = _getSNPsStringQueryReference(params);
			
			//if(!params.isLocusList() && !params.isSNPList() && params.isbShowAllRefAlleles()) {
			if(params.isbShowAllRefAlleles()) {
				 
				 if(params.getsChr()!=null && params.getlStart()!=null && params.getlEnd()!=null) {
					locusNipponbare =  new MultiReferenceLocusImpl(params.getOrganism(), params.getsChr(), params.getlStart().intValue(), params.getlEnd().intValue(), 1);
					vardata = convertReferencePositionsToAllReferences(params, vardata, locusNipponbare);
					AppContext.debug( "show other alllele refs = " + vardata.getMapOrg2MSU7Pos2ConvertedPos().keySet());
				 } else throw new RuntimeException("Multiple reference query not yet allowed for SNP/locus lists"); 
			}	
			
		}
		
		return vardata;
		
	}
	
	/**
	 * Convert reference position from Nipponbare to other references
	 * @param params
	 * @param variantstringdata
	 * @param npbMultirefLocus
	 * @return
	 * @throws Exception
	 */
	
	private VariantStringData convertReferencePositionsToAllReferences(GenotypeQueryParams params, VariantStringData variantstringdata, MultiReferenceLocus npbMultirefLocus) throws Exception {
		
		//return multiplerefconvertdao.convertReferencePositions(variantstringdata, npbMultirefLocus, null, null, false);
		return multiplerefconvertdao.convertReferencePositions(variantstringdata, npbMultirefLocus, null, null, false);
		
	}
	
//	
//	
//	private VariantStringData convertReferencePositionsToAllReferencesOld(GenotypeQueryParams params, VariantStringData variantstringdata, MultiReferenceLocus npbMultirefLocus) throws Exception {
//		
//		// convert to other references except org in origMultiReferenceLocus
//		// if origMultiReferenceLocus==null, convert to all 4 others
//		
//		multiplerefconvertdao = (MultipleReferenceConverterDAO)AppContext.checkBean(multiplerefconvertdao, "MultipleReferenceConverterDAO");
//		listitemsdao = (ListItemsDAO)AppContext.checkBean(listitemsdao, "ListItems");
//		Iterator<Organism> itOrg = listitemsdao.getOrganisms().iterator();
//		while(itOrg.hasNext()) {
//			Organism org = itOrg.next();
//
//			if(org.getName().equals( params.getOrganism() )) continue; // selected organism already shown
//			if(org.getName().equals( AppContext.getDefaultOrganism()) && params.isbShowNPBPositions() ) continue;
//			
//			AppContext.debug("converting from " + npbMultirefLocus + " to organism " +  org.getName());
//
//			try {
//				MultiReferenceLocus locusThisOrg = multiplerefconvertdao.convertLocus( npbMultirefLocus ,  org.getName(),  null);
//				AppContext.debug("converting from " + npbMultirefLocus + " to " + locusThisOrg);
//				variantstringdata.setMessage(variantstringdata.getMessage() + "\n" + npbMultirefLocus + " aligned with " + locusThisOrg);
//				variantstringdata = multiplerefconvertdao.convertReferencePositions(variantstringdata, npbMultirefLocus, locusThisOrg, null, true);
//			} catch(Exception ex) {
//				//ex.getMessage()
//				ex.printStackTrace();
//				variantstringdata.setMessage(variantstringdata.getMessage() + "\n" + ex.getMessage());
//				throw ex;
//			}
//		}
//		
//		return variantstringdata;
//		
//	}
//	

	
	/**
	 * Contains nucleotide string sequences for each variety based on query criteria
	 * before mismatch (reference or pairwise) counting
	 * @author lmansueto
	 *
	 */
	class SNPsStringData {
		private List<SnpsAllvarsPos> listSnpsPos=new ArrayList();
		private String  strRef="";
		private Map<BigDecimal,String>  mapVarid2Snpsstr = new HashMap();
		private Map<BigDecimal, Map<Integer,Character>> mapVarid2SnpsAllele2str= new HashMap();
		private Map<Position, Set<Character>> mapPos2NonsynAlleles= new TreeMap();
		private Map<Position, Set<Character>> mapPos2Alleles= new TreeMap();
		private Map<Position, Set<Character>> mapPos2SynAlleles= new TreeMap();
		private Set<Position> setSnpInExonPos=new TreeSet();
		private Set<Position> setSnpSpliceDonorPos=new TreeSet();
		private Set<Position> setSnpSpliceAcceptorPos=new TreeSet();
		private String message="";
		
		
		public SNPsStringData() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		public SNPsStringData(List listSnpsPos, String strRef, Map mapVarid2Snpsstr,
				Map mapVarid2SnpsAllele2str, Map mapPos2Alleles, Map mapPos2NonsynAlleles, Map mapPos2SynAlleles,
				Set setSnpInExonPos, Set setSnpSpliceDonorPos,  Set setSnpSpliceAcceptorPos, String message) {
			super();
			
			this.strRef = strRef;
			this.mapVarid2Snpsstr = mapVarid2Snpsstr;
			this.mapVarid2SnpsAllele2str = mapVarid2SnpsAllele2str;
			this.mapPos2NonsynAlleles = mapPos2NonsynAlleles;
			this.mapPos2SynAlleles = mapPos2SynAlleles;
			this.setSnpInExonPos = setSnpInExonPos;
			this.listSnpsPos = listSnpsPos;
			this.setSnpSpliceAcceptorPos = setSnpSpliceAcceptorPos;
			this.setSnpSpliceDonorPos = setSnpSpliceDonorPos;
			this.message=message;
		}		
		public String getStrRef() {
			return strRef;
		}
		public Map<BigDecimal,String> getMapVarid2Snpsstr() {
			return mapVarid2Snpsstr;
		}
		public Map getMapVarid2SnpsAllele2str() {
			return mapVarid2SnpsAllele2str;
		}
		public Map getMapPos2NonsynAlleles() {
			return mapPos2NonsynAlleles;
		}
		
		public Set getSetSnpInExonPos() {
			return setSnpInExonPos;
		}
		public List<SnpsAllvarsPos> getListSnpsPos() {
			return listSnpsPos;
		}
	}
	
	
	/**
	 * Query Nipponbare SNPs 
	 * @param params
	 * @return
	 */
	private VariantStringData _getSNPsStringQueryReference(GenotypeQueryParams params) { //, boolean exactMismatch, int firstRow, int maxRows) {
		
		Collection colVarids=params.getColVarIds();
		//Integer chr= Integer.valueOf( params.getsChr() );
		String chr=  params.getsChr() ;
		BigDecimal start=null; if(params.getlStart()!=null) start=BigDecimal.valueOf( params.getlStart() );
		BigDecimal end=null; if(params.getlEnd()!=null) end=BigDecimal.valueOf(params.getlEnd());
		Collection setPositions=params.getPoslist();
		Collection colLocus=params.getColLoci();
		
		
			// query variants
			SNPsStringData snpstrdata = null;
			snpstrdata=getSNPsStringDataOtherRefs(params);
			
			/*
			if(params.getOrganism().equals(AppContext.getDefaultOrganism()))
				snpstrdata=getSNPsStringData(params, colVarids,  chr,  start,  end,  setPositions,  colLocus);
			//else snpstrdata=getSNPsStringDataOtherRefs(params, colVarids,  chr,  start,  end,  setPositions,  colLocus);
			else snpstrdata=getSNPsStringDataOtherRefs(params);
			*/
			
			if(snpstrdata==null)  {
				throw new RuntimeException("getSNPsString:  snpstrdata==null");
			}
		 
			Map mapVarid2Snpsstr = snpstrdata.getMapVarid2Snpsstr();
			if(mapVarid2Snpsstr==null)  {
				mapVarid2Snpsstr = new HashMap();
			}

			Set setNonsynPos=new HashSet();
			
			Set sortedVarieties = new TreeSet(new SnpsStringAllvarsImplSorter());
			
			Iterator itVar = mapVarid2Snpsstr.keySet().iterator();

			// compare variety mismatch then sort
			while(itVar.hasNext()) {
				Object ovar = itVar.next();
				BigDecimal var=null;
				if(ovar instanceof BigDecimal)
					var = (BigDecimal)ovar;
				else if(ovar instanceof Variety)
					var = ((Variety)ovar).getVarietyId();

				String snpstr = (String)mapVarid2Snpsstr.get(var);
				Set<Position> varNonsynPos = new HashSet();

				double misCount = countVarpairMismatch( snpstrdata.getListSnpsPos(), snpstrdata.getStrRef(), snpstr,  true,   null, (Map)snpstrdata.getMapVarid2SnpsAllele2str().get(var),  
						snpstrdata.getMapPos2NonsynAlleles(),  snpstrdata.getSetSnpInExonPos() , varNonsynPos,  params.isbNonsynPlusSpliceSnps() || params.isbNonsynSnps()); //  .isbExcludeSynonymous() );

				setNonsynPos.addAll(varNonsynPos);
				
				if(!params.isbMismatchonly() || misCount>0 || params.isbPairwiseComparison() || params.hasLocusList() ) {
					sortedVarieties.add( new SnpsStringAllvarsImpl(var,chr, snpstr, 
							BigDecimal.valueOf(misCount) , (Map)snpstrdata.getMapVarid2SnpsAllele2str().get(var), varNonsynPos, 
							snpstrdata.setSnpSpliceDonorPos, snpstrdata.setSnpSpliceAcceptorPos
							) );
				} 
			}
			
			List<SnpsStringAllvars> listResult = new ArrayList();
			// sort included varieties
			Map mapVariety2Order = new HashMap();
			Map mapVariety2Mismatch = new HashMap();
			int ordercount = 0;
			Iterator itSorVars =  sortedVarieties.iterator();
			while(itSorVars.hasNext()) {
				SnpsStringAllvars snpstrvar = (SnpsStringAllvars)itSorVars.next();
				listResult.add( snpstrvar );
				mapVariety2Order.put(snpstrvar.getVar() ,ordercount);
				mapVariety2Mismatch.put( snpstrvar.getVar(), snpstrvar.getMismatch());
				ordercount++;
			}
			
			List listsortedVarieties = new ArrayList();
			listsortedVarieties.addAll(sortedVarieties);
					
			VariantStringData vardata = new VariantSnpsStringData(mapVariety2Mismatch,
					mapVariety2Order , 
					snpstrdata.getListSnpsPos() , 
					listsortedVarieties , 
					snpstrdata.getStrRef(), 
					snpstrdata.getMapVarid2SnpsAllele2str(), 
					snpstrdata.getMapPos2NonsynAlleles(),
					snpstrdata.getSetSnpInExonPos() , 
					snpstrdata.setSnpSpliceDonorPos, 
					snpstrdata.setSnpSpliceAcceptorPos);
			vardata.setMessage(snpstrdata.message );
			
			return vardata;					
	}
	

	/**
	 * query SNPsStringData
	 * @param params
	 * @param colVarids
	 * @param chr
	 * @param start
	 * @param end
	 * @param setPositions
	 * @param colLocus
	 * @return
	 */
	private SNPsStringData getSNPsStringData(GenotypeQueryParams params, Collection colVarids, String chr, BigDecimal start, BigDecimal end, Collection setPositions, Collection colLocus) { //, boolean exactMismatch, int firstRow, int maxRows) {
		// TODO Auto-generated method stub

		//snpstringallvarsposDAO = (SnpsAllvarsPosDAO)AppContext.checkBean(snpstringallvarsposDAO, "VSnpRefposindexDAO") ;
		snpstringallvarsposDAO = (SnpsAllvarsPosDAO)AppContext.checkBean(snpstringallvarsposDAO, "VConvertposAny2allrefsDAO") ;
		
		
		SnpsStringDAO snpstrSnpsAllele1AllvarsDAO=null;
		SnpsStringDAO snpstrSnpsAllele2AllvarsDAO=null;
		BigDecimal snptype=null; 
		
		// get SNP positions and HDF5 column index mapping
		// depends on dataset, version, core or all
		if(params.includeDataset(SnpsAllvarsPosDAO.DATASET_SNPINDELV1)) {
		
			if(params.isbCoreonly()) {
				snpstrSnpsAllele1AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrCoresnpsAllele1AllvarsDAO, "H5SNPCoreAllele1DAO");
				snpstrSnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrCoresnpsAllele1AllvarsDAO, "H5SNPCoreAllele2DAO");
				snptype=SnpsAllvarsPosDAO.TYPE_3KCORESNP;
			} else {
				snptype=SnpsAllvarsPosDAO.TYPE_3KALLSNP;
				snpstrSnpsAllele1AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrAllsnpsAllele1AllvarsDAO, "H5SNPUniAllele1DAO");
				snpstrSnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrAllsnpsAllele2AllvarsDAO, "H5SNPUniAllele2DAO");
			}
		} else if(params.includeDataset(SnpsAllvarsPosDAO.DATASET_SNPINDELV2)) {
			if(params.isbCoreonly()) {
				snpstrSnpsAllele1AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrCoresnpsAllele1AllvarsDAO, "H5SNPCoreAllele1V2DAO");
				snpstrSnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrCoresnpsAllele1AllvarsDAO, "H5SNPCoreAllele2V2DAO");
				snptype=SnpsAllvarsPosDAO.TYPE_3KCORESNP_HDF5_V2;
			} else {
				snptype=SnpsAllvarsPosDAO.TYPE_3KALLSNP_HDF5_V2;
				snpstrSnpsAllele1AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrAllsnpsAllele1AllvarsDAO, "H5SNPUniAllele1V2DAO");
				snpstrSnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrAllsnpsAllele2AllvarsDAO, "H5SNPUniAllele2V2DAO");
			}
			
		} else if(params.includeDataset(SnpsAllvarsPosDAO.DATASET_SNPINDELV2_IUPAC)) {
			if(params.isbCoreonly()) {
				snpstrSnpsAllele1AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrCoresnpsAllele1AllvarsDAO, "H5SNPCoreIUPACV2DAO");
				//snpstrSnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrCoresnpsAllele1AllvarsDAO, "H5SNPCoreAllele2V2DAO");
				snptype=SnpsAllvarsPosDAO.TYPE_3KCORESNP_HDF5_V2;
			} else {
				snptype=SnpsAllvarsPosDAO.TYPE_3KALLSNP_HDF5_V2;
				snpstrSnpsAllele1AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrAllsnpsAllele1AllvarsDAO, "H5SNPUniIUPACV2DAO");
				//snpstrSnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrAllsnpsAllele2AllvarsDAO, "H5SNPUniAllele2V2DAO");
			}
			
		}
		    
		if(snptype==null) throw new RuntimeException("snptype==null");
		if(snpstrSnpsAllele1AllvarsDAO==null) throw new RuntimeException("snpstrSnpsAllele1AllvarsDAO==null");
		
		List<SnpsAllvarsPos> snpsposlist = null;
		List listpos = null;
		if(colVarids==null || colVarids.isEmpty()) {
			if( (setPositions!=null && !setPositions.isEmpty()) ) {
				listpos = new ArrayList();
				listpos.addAll(new TreeSet(setPositions));
				AppContext.resetTimer("getSNPsString start1");
				snpsposlist  = snpstringallvarsposDAO.getSNPsInChromosome(chr.toString(),  listpos, snptype);
			}
			else if( (colLocus!=null && !colLocus.isEmpty()) ) {
				AppContext.resetTimer("getSNPsString start1b");
				snpsposlist  = snpstringallvarsposDAO.getSNPsInChromosome(chr.toString(),  colLocus, snptype);
			}
			else {
				AppContext.resetTimer("getSNPsString start2");
				snpsposlist  = snpstringallvarsposDAO.getSNPs(chr.toString(), start.intValue(), end.intValue(),   snptype, -1, -1);
			}
		} else {
			if(setPositions!=null && !setPositions.isEmpty()) {
				listpos = new ArrayList();
				listpos.addAll(new TreeSet(setPositions));
				AppContext.resetTimer("getSNPsString start3");
				snpsposlist  = snpstringallvarsposDAO.getSNPsInChromosome( chr.toString(),  listpos, snptype);
			}
			else if(colLocus!=null && !colLocus.isEmpty()) {
				AppContext.resetTimer("getSNPsString start3b");
				snpsposlist  = snpstringallvarsposDAO.getSNPsInChromosome( chr.toString(),  colLocus, snptype);
			}
			else {
				AppContext.resetTimer("getSNPsString start4");
				//snpsposlist  = snpstringallvarsposService.getSNPs(colVarids, chr.toString(), start.intValue(), end.intValue(),  snptype, -1, -1);
				snpsposlist  = snpstringallvarsposDAO.getSNPs(chr.toString(), start.intValue(), end.intValue(),  snptype, -1, -1);
			}
			
			//AppContext.debug("colvarids=" + colVarids.toString());
		}
		
		if(snpsposlist==null) throw new RuntimeException("snpsposlist==null");
		
		if(snpsposlist.isEmpty()) return new SNPsStringData();
		

		// get allele column indices from start to end positions
		SnpsAllvarsPos startpos =  snpsposlist.get(0);
		SnpsAllvarsPos endpos =  snpsposlist.get( snpsposlist.size()-1 );

		String strRef=null;
		
		int refLength=-1;
		
		AppContext.debug( snpsposlist.size() + " snpposlist, pos between " +startpos.getPosition() +  "-" + endpos.getPosition() + "  index between " + startpos.getAlleleIndex() + "-" + endpos.getAlleleIndex());
		
		
		// generate column indexes to query HDF5
		
			int indxs[] = new int[snpsposlist.size()];
			List<int[]> listStartStop = new ArrayList();
			int indxscount = 0;
			Map<BigDecimal, Position> mapSnpid2Pos = new HashMap();
			
			Iterator<SnpsAllvarsPos> itSnppos =snpsposlist.iterator();
			StringBuffer buffRef = new StringBuffer();
			
			int previdx=-100;
			int laststart=-100;
			while(itSnppos.hasNext()) {
				SnpsAllvarsPos snppos = itSnppos.next(); 
				buffRef.append( snppos.getRefnuc());
				indxs[indxscount] =  snppos.getAlleleIndex().intValue();
				
				mapSnpid2Pos.put( snppos.getSnpFeatureId() , snppos);

				// merge adjacent indexes into a range, for locus list
				if(indxs[indxscount]==(previdx+1)) {
					previdx=indxs[indxscount];
				} else {
					if(laststart>=0 && previdx>=0) {
						listStartStop.add(new int[]{laststart, previdx});
					}
					laststart=indxs[indxscount];
					previdx=indxs[indxscount];
				}
				indxscount++;
			}
			listStartStop.add(new int[]{laststart, previdx});
			
			int intStartStopIdx[][] = new int[listStartStop.size()][2];
			Iterator<int[]> itStartStop = listStartStop.iterator();
			int sscount=0;
			while(itStartStop.hasNext()) {
				intStartStopIdx[sscount] = itStartStop.next();
				AppContext.debug("idxrange " + intStartStopIdx[sscount][0] + "-" + intStartStopIdx[sscount][1]);
				sscount++;
			}
			
			strRef = buffRef.toString();
			refLength = strRef.length();
			
			snpsnonsynDAO = (SnpsNonsynAllvarsDAO) AppContext.checkBean(snpsnonsynDAO, "VSnpNonsynallelePosDAO");
			snpssynDAO = (SnpsSynAllvarsDAO) AppContext.checkBean(snpssynDAO, "VSnpSynalleleDAO");
			//snpsinexonDAO = (SnpsInExonDAO) AppContext.checkBean(snpsinexonDAO, "SnpsInExonDAO");
			//snpsheteroDAO = (SnpsHeteroAllvarsDAO)AppContext.checkBean(snpsheteroDAO, "SnpsHeteroAllvarsDAO");

			
			// get snpstring for each varieties
			// get allele2 for heterozygous varieties
			Map  mapVarid2Snpsstr = null;
			Map mapVarid2Snpsstr_allele2 = null;
		
			//Set heteroSnps = null;
			Set nonsynAllele = null;
			Set synAllele = null;
			//Set inexonSnps = null;
			
			// using hdf5
			if(  (setPositions!=null && !setPositions.isEmpty())  || (colLocus!=null && !colLocus.isEmpty()) ) {
				
				// position list or locus list
				if(setPositions!=null && !setPositions.isEmpty()) {
					if(colVarids!=null && !colVarids.isEmpty() ) {
						AppContext.resetTimer("using readSNPString1 start");
						mapVarid2Snpsstr = snpstrSnpsAllele1AllvarsDAO.readSNPString((Set)colVarids, chr, indxs);
						if(snpstrSnpsAllele2AllvarsDAO!=null) mapVarid2Snpsstr_allele2= snpstrSnpsAllele2AllvarsDAO.readSNPString((Set)colVarids, chr,  indxs );
						AppContext.resetTimer("using readSNPString1 end");
					}
					else {
						AppContext.resetTimer("using readSNPString2 start");
						mapVarid2Snpsstr = snpstrSnpsAllele1AllvarsDAO.readSNPString(chr,  indxs);
						if(snpstrSnpsAllele2AllvarsDAO!=null) mapVarid2Snpsstr_allele2=  snpstrSnpsAllele2AllvarsDAO.readSNPString(chr,  indxs);
						AppContext.resetTimer("using readSNPString2 end");
					}
				} else if(colLocus!=null && !colLocus.isEmpty()) {
					if(colVarids!=null && !colVarids.isEmpty() ) {
						AppContext.resetTimer("using readSNPString1b start");
						mapVarid2Snpsstr = snpstrSnpsAllele1AllvarsDAO.readSNPString((Set)colVarids, chr, intStartStopIdx);
						if(snpstrSnpsAllele2AllvarsDAO!=null) mapVarid2Snpsstr_allele2= snpstrSnpsAllele2AllvarsDAO.readSNPString((Set)colVarids, chr,  intStartStopIdx );
						AppContext.resetTimer("using readSNPString1b end");
					}
					else {
						AppContext.resetTimer("using readSNPString2b start");
						mapVarid2Snpsstr = snpstrSnpsAllele1AllvarsDAO.readSNPString(chr,  intStartStopIdx);
						if(snpstrSnpsAllele2AllvarsDAO!=null) mapVarid2Snpsstr_allele2=  snpstrSnpsAllele2AllvarsDAO.readSNPString(chr,  intStartStopIdx);
						AppContext.resetTimer("using readSNPString2b end");
					}
				}
				
				if(setPositions!=null && !setPositions.isEmpty()) {
					nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByChrPosIn(chr, listpos, snptype);
					synAllele = snpssynDAO.findSnpSynAlleleByChrPosIn(chr, listpos, snptype);
					//nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleBySnpfeatureidIn(chr, listpos);
					//inexonSnps = snpsinexonDAO.getSnps(chr, listpos);
				} 
				else if(colLocus!=null && !colLocus.isEmpty()) {
					nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByChrPosIn(chr, colLocus, snptype);
					//inexonSnps = snpsinexonDAO.getSnps(chr, colLocus);
					synAllele = snpssynDAO.findSnpSynAlleleByChrPosIn(chr, colLocus, snptype);
				} 
				AppContext.resetTimer("to read nonsynonymous allele, inexon  from  database..");
			}
			else {
				if(colVarids!=null && !colVarids.isEmpty() )
				{
					AppContext.resetTimer("using readSNPString3 start");
						mapVarid2Snpsstr = snpstrSnpsAllele1AllvarsDAO.readSNPString((Set)colVarids, chr, startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());
						if(snpstrSnpsAllele2AllvarsDAO!=null) mapVarid2Snpsstr_allele2 = snpstrSnpsAllele2AllvarsDAO.readSNPString((Set)colVarids, chr, startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());
					AppContext.resetTimer("using readSNPString3 end");
				}
				else {
					AppContext.resetTimer("using readSNPString4 start");
						mapVarid2Snpsstr = snpstrSnpsAllele1AllvarsDAO.readSNPString(chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());
						if(snpstrSnpsAllele2AllvarsDAO!=null) mapVarid2Snpsstr_allele2 = snpstrSnpsAllele2AllvarsDAO.readSNPString(chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());
				}
				
				AppContext.resetTimer("to read allele2 database..");
				//nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByChrPosBetween(chr, start.intValue(), end.intValue());
				//nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleBySnpfeatureidBetween(startpos.getSnpFeatureId(), endpos.getSnpFeatureId(), snptype);
				nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByChrPosBetween(chr, startpos.getPosition().intValue(), endpos.getPosition().intValue(), snptype);
				synAllele = snpssynDAO.findSnpSynAlleleByChrPosBetween(chr, startpos.getPosition().intValue(), endpos.getPosition().intValue(), snptype);
				//inexonSnps = snpsinexonDAO.getSnps(chr,start.intValue(), end.intValue()); 
				AppContext.resetTimer("to read nonsynonymous allele, inexon  from  database..");
			}

			//Iterator itSnpstr = mapVarid2Snpsstr.values().iterator();
			//while(itSnpstr.hasNext()) {
			//	AppContext.debug(  itSnpstr.next().toString() );
			//	break;
			//}
			
			 
//			// filter varieties here
//			//if(datatype == SnpcoreRefposindexDAO.TYPE_3KALLSNP && colVarids!=null && !colVarids.isEmpty() && heteroSnps!=null) {
//			if( colVarids!=null && !colVarids.isEmpty() && heteroSnps!=null) {
//				Iterator<SnpsHeteroAllele2> itHetero = heteroSnps.iterator();
//				Set setNewAllele2 = new LinkedHashSet();
//				while(itHetero.hasNext()) {
//					SnpsHeteroAllele2 all2 =  itHetero.next();
//					if(colVarids.contains(all2.getVar()) )
//						setNewAllele2.add( all2 );
//				}
//				heteroSnps = setNewAllele2;	
//			}
//			
//
//			//Map<BigDecimal,Map> mapVarid2SnpsAllele2str = new HashMap();
//			Map<BigDecimal,Map<Position,Character>> mapVarid2SnpsAllele2str = new HashMap();
//			if(heteroSnps!=null) {
//				throw new RuntimeException("Unexpected condition: heteroSnps!=null");
//				
////				Iterator<SnpsHeteroAllele2> itSnp = heteroSnps.iterator();
////				while(itSnp.hasNext()) {
////					SnpsHeteroAllele2 snpallele2 = itSnp.next();
////					Map mapTableidx2Nuc = mapVarid2SnpsAllele2str.get( snpallele2.getVar() );
////					if(mapTableidx2Nuc==null) {
////						mapTableidx2Nuc = new HashMap();
////						mapVarid2SnpsAllele2str.put(snpallele2.getVar() , mapTableidx2Nuc);
////					}
////					mapTableidx2Nuc.put(mapSnpid2TableIdx.get( snpallele2.getSnp() )  , snpallele2.getNuc() );
////				}
//			} else 
				
			Map<BigDecimal,Map> mapVarid2SnpsAllele2str = new HashMap();
			
			if(mapVarid2Snpsstr_allele2!=null) {
				
				if(params.includeDataset(SnpsAllvarsPosDAO.DATASET_SNPINDELV2_IUPAC)) throw new RuntimeException("Unexpected case mapVarid2Snpsstr_allele2!=null for IUPAC");
				
				Iterator itVarid = mapVarid2Snpsstr_allele2.keySet().iterator();
				while(itVarid.hasNext()) {
					BigDecimal varid= (BigDecimal)itVarid.next();
					
					if(colVarids!=null && !colVarids.contains(varid)) continue;
					
					if(params.includeDataset(SnpsAllvarsPosDAO.DATASET_SNPINDELV1)) {					
					
						String allele2str = (String)mapVarid2Snpsstr_allele2.get( varid );
						for(int iStr=0; iStr<allele2str.length(); iStr++) {
							char allele2i =allele2str.charAt(iStr);
							if(allele2i!='*' && allele2i!='0' && allele2i!='.' && allele2i!=' ') {
						
								Map mapTableidx2Nuc = mapVarid2SnpsAllele2str.get( varid );
								if(mapTableidx2Nuc==null) {
									mapTableidx2Nuc = new HashMap();
									mapVarid2SnpsAllele2str.put(varid , mapTableidx2Nuc);
								}
								mapTableidx2Nuc.put( iStr, allele2i);
							}
						}
					
					} else
					if(params.includeDataset(SnpsAllvarsPosDAO.DATASET_SNPINDELV2)) {					
						// revised for SNPv2 (allele2!=0 for allele1==allele2)
						String allele1str = (String)mapVarid2Snpsstr.get( varid );
						String allele2str = (String)mapVarid2Snpsstr_allele2.get( varid );
						
						if(allele1str.length()!=allele2str.length()) throw new RuntimeException( "allele1str.length()!=allele2str.length(): " + allele1str.length() + ", " + allele2str.length());
						
						for(int iStr=0; iStr<allele2str.length(); iStr++) {
							char allele2i =allele2str.charAt(iStr);
							char allele1i =allele1str.charAt(iStr);
							if(allele1i!='0' && allele2i!='0' && allele1i!=allele2i) {
							//if(allele2i!='*' && allele2i!='0' && allele2i!='.' && allele2i!=' ') {
						
								Map mapTableidx2Nuc = mapVarid2SnpsAllele2str.get( varid );
								if(mapTableidx2Nuc==null) {
									mapTableidx2Nuc = new HashMap();
									mapVarid2SnpsAllele2str.put(varid , mapTableidx2Nuc);
								}
								//mapTableidx2Nuc.put( iStr, allele2i);
								
								mapTableidx2Nuc.put( snpsposlist.get(iStr), allele2i);
							}
						}
					}
				}
			} else if (mapVarid2Snpsstr_allele2==null && params.includeDataset(SnpsAllvarsPosDAO.DATASET_SNPINDELV2_IUPAC)) {
				
				Map mapVarid2SnpsstrSplitIUPAC=new LinkedHashMap();
				mapVarid2Snpsstr_allele2=new LinkedHashMap();
				
				Iterator itVarid = mapVarid2Snpsstr.keySet().iterator();
				while(itVarid.hasNext()) {
					BigDecimal varid= (BigDecimal)itVarid.next();
					
					if(colVarids!=null && !colVarids.contains(varid)) continue;

					//if(params.includeDataset(SnpsAllvarsPosDAO.DATASET_SNPINDELV2)) {					
						// revised for SNPv2 (allele2!=0 for allele1==allele2)
						String allele1str = (String)mapVarid2Snpsstr.get( varid );
						
						//String allele2str = (String)mapVarid2Snpsstr_allele2.get( varid );
						//if(allele1str.length()!=allele2str.length()) throw new RuntimeException( "allele1str.length()!=allele2str.length(): " + allele1str.length() + ", " + allele2str.length());
						
						StringBuffer buffNewStr=new StringBuffer();
						StringBuffer buffNewStr2=new StringBuffer();
						for(int iStr=0; iStr<allele1str.length(); iStr++) {
							//char allele2i =allele2str.charAt(iStr);
							char allele1i =allele1str.charAt(iStr);
							String alleles12= AppContext.getNucsFromIUPAC(allele1i);
							if(alleles12.length()==2) {
								Map mapTableidx2Nuc = mapVarid2SnpsAllele2str.get( varid );
								if(mapTableidx2Nuc==null) {
									mapTableidx2Nuc = new HashMap();
									mapVarid2SnpsAllele2str.put(varid , mapTableidx2Nuc);
								}
								mapTableidx2Nuc.put( snpsposlist.get(iStr), alleles12.charAt(1));
								buffNewStr2.append(alleles12.charAt(1));
							} else 
								buffNewStr2.append(alleles12.charAt(0));
							
							buffNewStr.append(alleles12.charAt(0));
						}
						
						mapVarid2SnpsstrSplitIUPAC.put( varid , buffNewStr.toString());
						mapVarid2Snpsstr_allele2.put( varid , buffNewStr2.toString());
						
//						if(!allele1str.equals(buffNewStr.toString())) {
//							AppContext.debug( "varid=" + varid);
//							AppContext.debug( "alleleIUPACstr=" + allele1str);
//							AppContext.debug( "allele1    str=" + buffNewStr);
//							AppContext.debug( "allele2    str=" + buffNewStr2);
//						}
					//}
				}
				
				mapVarid2Snpsstr = mapVarid2SnpsstrSplitIUPAC;
				
				
			} // else throw new RuntimeException("heteroSnps==null and mapVarid2Snpsstr_allele2==null ... no allele2 data");
			
			
			// generate pos2alleles

			Map<Position, Set<Character>> mapPos2Allele=new TreeMap();
			Map<Position, Set<Character>> mapPos2AlleleHetero=new TreeMap();
			
			Iterator<BigDecimal> itVar=mapVarid2Snpsstr.keySet().iterator();
			while(itVar.hasNext()) {
				BigDecimal varid=itVar.next();
				String allele1str = (String)mapVarid2Snpsstr.get( varid );
				String allele2str = (String)mapVarid2Snpsstr_allele2.get( varid );
				itSnppos =snpsposlist.iterator();
				int icount=0;
				while(itSnppos.hasNext()) {
					SnpsAllvarsPos snppos=itSnppos.next();
					
					Set setAlleles=mapPos2Allele.get(snppos);
					if(setAlleles==null) {
						setAlleles=new HashSet();
						mapPos2Allele.put(snppos, setAlleles);
					}
					// hdf5 format v2
					char chari1=allele1str.charAt(icount);
					if(chari1!='0' && chari1!='?') setAlleles.add(chari1);
					char chari2=allele2str.charAt(icount);
					if(chari2!='0' && chari2!='?' ) setAlleles.add(chari2);
					
					if(chari1!=chari2) {
						if(chari1!=snppos.getRefnuc().charAt(0)) {
							Set allele2= mapPos2AlleleHetero.get(snppos);
							if(allele2==null) {
								allele2=new HashSet();
								mapPos2AlleleHetero.put(snppos,  allele2);
							}
							allele2.add(chari1);
						}
						if(chari2!=snppos.getRefnuc().charAt(0)) {
							Set allele2= mapPos2AlleleHetero.get(snppos);
							if(allele2==null) {
								allele2=new HashSet();
								mapPos2AlleleHetero.put(snppos,  allele2);
							}
							allele2.add(chari2);
						}
					}
					
					
					icount++;
				}
			}
			
			
			// non-synonymous alleles for positions
			Map<Position,Set<Character>> mapPos2NonsynAlleles = new TreeMap();
			Map<Position,Set<Character>> mapPos2SynAlleles = new TreeMap();
			
			
			Set<Position> setSnpInExonPos = new TreeSet();
			
			setSnpInExonPos = new TreeSet();
			
			Iterator<SnpsNonsynAllele> itNonsyn = nonsynAllele.iterator();
			while(itNonsyn.hasNext()) {
				SnpsNonsynAllele nonsynallele = itNonsyn.next();
				
				if(mapSnpid2Pos==null) throw new RuntimeException("mapSnpid2Pos==null");
				if(nonsynallele==null) throw new RuntimeException("nonsynallele==null");
				if(nonsynallele.getSnpFeatureId()==null) throw new RuntimeException("nonsynallele.getSnp()==null");
				
				Position pos = mapSnpid2Pos.get( nonsynallele.getSnpFeatureId() );
				
				// assuming all nonsyn are in exon
				setSnpInExonPos.add( pos );
				
				Set<Character> setVarietyAlleles = mapPos2Allele.get(pos);
				if(setVarietyAlleles==null) throw new RuntimeException("setVarietyAlleles==null");
				if(nonsynallele.getAllele()=='\0') throw new RuntimeException("nonsynallele.getAllele()==null");

				if(setVarietyAlleles.contains( nonsynallele.getAllele()) ) {

					Set<Character> alleles = mapPos2NonsynAlleles.get(pos);
					if(alleles==null) {
						alleles = new HashSet();
						mapPos2NonsynAlleles.put( pos , alleles);
					}
					alleles.add( nonsynallele.getAllele() );
				}
			}
			
			Iterator<SnpsSynAllele> itSyn = synAllele.iterator();
			while(itSyn.hasNext()) {
				SnpsSynAllele synallele = itSyn.next();
				Position pos = mapSnpid2Pos.get( synallele.getSnpFeatureId() );
				
				// assuming all syn are in exon
				setSnpInExonPos.add( pos );
				
				Set<Character> setVarietyAlleles = mapPos2Allele.get(pos);
				
				if(setVarietyAlleles.contains( synallele.getAllele()) ) {
					Set<Character> alleles = mapPos2SynAlleles.get(pos);
					if(alleles==null) {
						alleles = new HashSet();
						mapPos2SynAlleles.put( pos , alleles);
					}
					alleles.add( synallele.getAllele() );
				}
			}
		
			if(nonsynAllele!=null) AppContext.debug( nonsynAllele.size() + " non-synonymous alleles, " + mapPos2NonsynAlleles.size() + " positions/idx,  " +  mapPos2NonsynAlleles.size() + "  nonsys alleles positions");
			
			
			// get splice variants
			Set setSpliceAcceptorsPos = null;
			Set setSpliceDonorsPos = null;

			Set setSpliceAcceptors= null;
			Set setSpliceDonors= null;
			
			snpsspliceacceptorDAO = (SnpsSpliceAcceptorDAO)AppContext.checkBean(snpsspliceacceptorDAO, "SnpsSpliceAcceptorDAO");
			snpssplicedonorDAO = (SnpsSpliceDonorDAO)AppContext.checkBean(snpssplicedonorDAO, "SnpsSpliceDonorDAO");
			if(setPositions!=null && !setPositions.isEmpty()) {
				setSpliceAcceptors = snpsspliceacceptorDAO.getSNPsIn(chr, setPositions);
				setSpliceDonors = snpssplicedonorDAO.getSNPsIn(chr, setPositions);
			}
			else if(colLocus!=null && !colLocus.isEmpty()) {
					setSpliceAcceptors = snpsspliceacceptorDAO.getSNPsIn(chr, colLocus);
					setSpliceDonors = snpssplicedonorDAO.getSNPsIn(chr, colLocus);							
			} else {
				setSpliceAcceptors = snpsspliceacceptorDAO.getSNPsBetween(chr, start.intValue(), end.intValue());  
				setSpliceDonors = snpssplicedonorDAO.getSNPsBetween(chr, start.intValue(), end.intValue());							
			}
			
			setSpliceAcceptorsPos = new HashSet();
			setSpliceDonorsPos = new HashSet();
			Iterator<SnpsSpliceAcceptor>  itAcceptors = setSpliceAcceptors.iterator();
			while(itAcceptors.hasNext()) {
				SnpsSpliceAcceptor acc = itAcceptors.next();
				setSpliceAcceptorsPos.add( acc.getPos());
			}
			Iterator<SnpsSpliceDonor>  itDonor = setSpliceDonors.iterator();
			while(itDonor.hasNext()) {
				SnpsSpliceDonor acc = itDonor.next();
				setSpliceDonorsPos.add( acc.getPos());
			}
				
			
			if(AppContext.isLocalhost()) {
				AppContext.debug("mapPos2NonsynAlleles " + mapPos2NonsynAlleles.size() +": " + mapPos2NonsynAlleles.toString() );
				AppContext.debug("mapPos2SynAlleles " + mapPos2SynAlleles.size() + ": " + mapPos2SynAlleles.toString() );
				AppContext.debug("mapPos2Allele " + mapPos2Allele.size() + ": " + mapPos2Allele.toString() );
				AppContext.debug("mapPos2AlleleHetero " + mapPos2AlleleHetero.size() +": " + mapPos2AlleleHetero.toString() );
				AppContext.debug("setSpliceDonorsPos " + setSpliceDonorsPos.size() +": " + setSpliceDonorsPos.toString() );
				AppContext.debug("setSpliceAcceptorsPos " + setSpliceAcceptorsPos.size() +": " + setSpliceAcceptorsPos.toString() );
				//AppContext.debug( nonsynAllele.toString() );
			}
			
			SNPsStringData snpstrdata = new  SNPsStringData(snpsposlist,  strRef, mapVarid2Snpsstr, mapVarid2SnpsAllele2str,
					mapPos2Allele, mapPos2NonsynAlleles, mapPos2SynAlleles, setSnpInExonPos, setSpliceDonorsPos,  setSpliceAcceptorsPos , "");
			return snpstrdata;
	}
	
	
	/**
	 * query SNPsStringData for other (not NB) reference genomes
	 * @param params
	 * @param colVarids
	 * @param chr
	 * @param start
	 * @param end
	 * @param setPositions
	 * @param colLocus
	 * @return
	 */
	private SNPsStringData getSNPsStringDataOtherRefs(GenotypeQueryParams params) {
		// TODO Auto-generated method stub

		AppContext.debug("executing...getSNPsStringDataOtherRefs");
		
		Collection colVarids=params.getColVarIds();
		String chr= params.getsChr();
		BigDecimal start= BigDecimal.valueOf(params.getlStart());
		BigDecimal end= BigDecimal.valueOf(params.getlEnd()); 
		Collection setPositions = params.getPoslist();
		Collection colLocus = params.getColLoci();
			
		
		//snpstringallvarsposDAO = (SnpsAllvarsPosDAO)AppContext.checkBean(snpstringallvarsposDAO, "VSnpRefposindexDAO") ;
		snpstringallvarsposDAO = (SnpsAllvarsPosDAO)AppContext.checkBean(snpstringallvarsposDAO, "VConvertposAny2allrefsDAO") ;
		
		SnpsAllvarsMultirefsPosDAO snpstringallvarsmultirefsposDAO = (SnpsAllvarsMultirefsPosDAO)snpstringallvarsposDAO;
		
		SnpsStringDAO snpstrSnpsAllele1AllvarsDAO=null;
		SnpsStringDAO snpstrSnpsAllele2AllvarsDAO=null;
		BigDecimal snptype=snptype=SnpsAllvarsPosDAO.TYPE_3KALLSNP_HDF5_V2; 

		StringBuffer buffMsg=new StringBuffer();
		
		List<SnpsAllvarsPos> snpsposlist = null;
		List listpos = null;
		if(colVarids==null || colVarids.isEmpty()) {
			if( (setPositions!=null && !setPositions.isEmpty()) ) {
				listpos = new ArrayList();
				listpos.addAll(new TreeSet(setPositions));
				AppContext.resetTimer("getSNPsString start1");
				snpsposlist  = snpstringallvarsmultirefsposDAO.getSNPsInChromosome(chr.toString(),  listpos, snptype, listitemsdao.getOrganismByName(params.getOrganism()).getOrganismId(), buffMsg);
			}
			else if( (colLocus!=null && !colLocus.isEmpty()) ) {
				AppContext.resetTimer("getSNPsString start1b");
				snpsposlist  = snpstringallvarsmultirefsposDAO.getSNPsInChromosome(chr.toString(),  colLocus, snptype, listitemsdao.getOrganismByName(params.getOrganism()).getOrganismId(), buffMsg);
			}
			else {
				AppContext.resetTimer("getSNPsString start2");
				snpsposlist  = snpstringallvarsmultirefsposDAO.getSNPs(chr.toString(), start.intValue(), end.intValue(),   snptype,  listitemsdao.getOrganismByName(params.getOrganism()).getOrganismId(), buffMsg);
			}
		} else {
			if(setPositions!=null && !setPositions.isEmpty()) {
				listpos = new ArrayList();
				listpos.addAll(new TreeSet(setPositions));
				AppContext.resetTimer("getSNPsString start3");
				snpsposlist  = snpstringallvarsmultirefsposDAO.getSNPsInChromosome( chr.toString(),  listpos, snptype,  listitemsdao.getOrganismByName(params.getOrganism()).getOrganismId(), buffMsg);
			}
			else if(colLocus!=null && !colLocus.isEmpty()) {
				AppContext.resetTimer("getSNPsString start3b");
				snpsposlist  = snpstringallvarsmultirefsposDAO.getSNPsInChromosome( chr.toString(),  colLocus, snptype,  listitemsdao.getOrganismByName(params.getOrganism()).getOrganismId(), buffMsg);
			}
			else {
				AppContext.resetTimer("getSNPsString start4");
				snpsposlist  = snpstringallvarsmultirefsposDAO.getSNPs(chr.toString(), start.intValue(), end.intValue(),  snptype, listitemsdao.getOrganismByName(params.getOrganism()).getOrganismId(), buffMsg);
			}
			//AppContext.debug("colvarids=" + colVarids.toString());
		}
		
		if(snpsposlist==null) throw new RuntimeException("snpsposlist==null");
		if(snpsposlist.isEmpty()) return new SNPsStringData();
		
		
		if(params.includeDataset(SnpsAllvarsPosDAO.DATASET_SNPINDELV2_IUPAC)) {
			if(params.isbCoreonly()) {
				throw new RuntimeException("Only non-core " + SnpsAllvarsPosDAO.DATASET_SNPINDELV2_IUPAC + " is available for other reference genomes");
			} else {
				snptype=SnpsAllvarsPosDAO.TYPE_3KALLSNP_HDF5_V2;
				//snpstrSnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrAllsnpsAllele2AllvarsDAO, "H5SNPUniAllele2V2DAO");
			}
		} else	throw new RuntimeException("Only non-core " + SnpsAllvarsPosDAO.DATASET_SNPINDELV2_IUPAC + " is available for other reference genomes");


		snpstrSnpsAllele1AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrAllsnpsAllele1AllvarsDAO, "H5SNPUniIUPACV2DAO");
		if(snpstrSnpsAllele1AllvarsDAO==null) throw new RuntimeException("snpstrSnpsAllele1AllvarsDAO==null");


		AppContext.debug( "sorting out file-contig for " + snpsposlist.size() + " positions");
		
		// sort out which hdf5 files to read and ranges
		Map<String,BigDecimal> mapFileContig2StartIdx=new LinkedHashMap();
		Map<String,BigDecimal> mapFileContig2EndIdx=new LinkedHashMap();
		Map<String,SnpsAllvarsMultirefsPos> mapFileContig2StartPos=new LinkedHashMap();
		Map<String,SnpsAllvarsMultirefsPos> mapFileContig2EndPos=new LinkedHashMap();
		
		
		//Map<String,Integer> mapFileContig2IdxCount=new HashMap();
		//Map<Integer, Object[]> mapPosidx2FileContigIdx=new LinkedHashMap();
		//Map<Integer, Object[]> mapPosidx2FileContigStartEndIdx=new LinkedHashMap();
		//Map<Integer, BigDecimal[]> mapPosidx2FileContigStartEndIdx=new LinkedHashMap();
		//Map<Integer, SnpsAllvarsMultirefsPos[]> mapPosidx2MultirefsPos=new LinkedHashMap();
		Iterator<SnpsAllvarsPos> itPos=snpsposlist.iterator();
		
		int posidx=0;
		SnpsAllvarsMultirefsPos lastpos = null;
		int lastposidx=0;
		int prevposidx=0;
		SnpsAllvarsMultirefsPos prevpos=null;
		String prevfilecontig=null;
		boolean firstpos=true;

		// get minimum start and maximum end column idx for each file-contig
		while(itPos.hasNext()) {
			SnpsAllvarsMultirefsPos ipos=(SnpsAllvarsMultirefsPos)itPos.next();
			
			if(firstpos) {
				prevfilecontig=ipos.getFileId() + "-" + ipos.getContig() + "-" + lastposidx;
				lastpos = ipos;
				mapFileContig2StartIdx.put(prevfilecontig,  lastpos.getAlleleIndex());
				mapFileContig2StartPos.put(prevfilecontig,  lastpos);
				AppContext.debug(posidx + ": min start for " + prevfilecontig + ": " +  lastpos.getAlleleIndex());
				firstpos=false;
			}
			String curfilecontig=ipos.getFileId() + "-" + ipos.getContig() + "-" + posidx;  //+ "-" + posidx;
			
			if(  ((prevpos!=null && (!prevpos.getFileId().equals(ipos.getFileId()) ||  !prevpos.getContig().equals(ipos.getContig()) )) || !itPos.hasNext())  
					||
				(  prevpos!=null &&  prevpos.getFileId().equals(ipos.getFileId()) && prevpos.getContig().equals(ipos.getContig()) &&  (prevpos.getAlleleIndex().longValue()+1 != ipos.getAlleleIndex().longValue())  )					
					) {

				prevfilecontig=prevpos.getFileId() + "-" + prevpos.getContig() +  "-" + lastposidx ;
				
				if(!mapFileContig2EndIdx.containsKey(prevfilecontig)) { 
					if(!itPos.hasNext())  {
						
						if(  prevpos!=null &&  prevpos.getFileId().equals(ipos.getFileId()) && prevpos.getContig().equals(ipos.getContig()) &&  (prevpos.getAlleleIndex().longValue()+1 != ipos.getAlleleIndex().longValue())  )
						{
							mapFileContig2EndIdx.put(prevfilecontig, prevpos.getAlleleIndex() );
							mapFileContig2EndPos.put(prevfilecontig, prevpos);
							AppContext.debug(posidx + ": max end for " + prevfilecontig + ": " +  prevpos.getAlleleIndex());		

							mapFileContig2StartIdx.put(curfilecontig, ipos.getAlleleIndex() );
							mapFileContig2StartPos.put(curfilecontig, ipos);
							AppContext.debug(posidx + ": min start for " + curfilecontig + ": " +  ipos.getAlleleIndex());		
							mapFileContig2EndIdx.put(curfilecontig, ipos.getAlleleIndex() );
							mapFileContig2EndPos.put(curfilecontig, ipos);
							AppContext.debug(posidx + ": max end for " + curfilecontig + ": " +  ipos.getAlleleIndex());		
						} else {
							mapFileContig2EndIdx.put(prevfilecontig, ipos.getAlleleIndex() );
							mapFileContig2EndPos.put(prevfilecontig, ipos);
							AppContext.debug(posidx + ": max end for " + prevfilecontig + ": " +  ipos.getAlleleIndex());
						}
					} else {
						mapFileContig2EndIdx.put(prevfilecontig, prevpos.getAlleleIndex() );
						mapFileContig2EndPos.put(prevfilecontig, prevpos);
						AppContext.debug(posidx + ": max end for " + prevfilecontig + ": " +  prevpos.getAlleleIndex());
					}
				}
				else {
					
					throw new RuntimeException("Unexpected case: mapFileContig2EndIdx.containsKey(prevfilecontig)");
//					if(!itPos.hasNext())  {
//						if(mapFileContig2EndIdx.get(curfilecontig).compareTo( ipos.getAlleleIndex()) <= 0) {
//							//AppContext.debug( "prevend<=curend for :" + curfilecontig + "  " + mapFileContig2EndIdx.get(curfilecontig) + "  "  +  ipos.getAlleleIndex() );
//							mapFileContig2EndIdx.put(curfilecontig, ipos.getAlleleIndex());
//							mapFileContig2EndPos.put(curfilecontig, ipos);
//							AppContext.debug(posidx + ": new max end for " + curfilecontig + ": " +  ipos.getAlleleIndex());
//						} //else 
//							//AppContext.debug( "prevend>curend for :" + curfilecontig + "  " + mapFileContig2EndIdx.get(curfilecontig) + "  "  +  ipos.getAlleleIndex() );
//						
//					} else {
//						if(mapFileContig2EndIdx.get(prevfilecontig).compareTo( prevpos.getAlleleIndex()) <= 0) {
//							//AppContext.debug( "prevend<=curend for :" + prevfilecontig + "  " + mapFileContig2EndIdx.get(prevfilecontig) + "  "  +  prevpos.getAlleleIndex() );
//							mapFileContig2EndIdx.put(prevfilecontig, prevpos.getAlleleIndex());
//							mapFileContig2EndPos.put(prevfilecontig, prevpos);
//							AppContext.debug(posidx + ": new max end for " + prevfilecontig + ": " +  prevpos.getAlleleIndex());
//	
//						} //else 
//						//	AppContext.debug( "prevend>curend for :" + prevfilecontig + "  " + mapFileContig2EndIdx.get(prevfilecontig) + "  "  +  prevpos.getAlleleIndex() );
//	
//					}
				}
				
				if(itPos.hasNext()) {
					if(!mapFileContig2StartIdx.containsKey(curfilecontig)) { 
						mapFileContig2StartIdx.put(curfilecontig,  ipos.getAlleleIndex() );
						mapFileContig2StartPos.put(curfilecontig,  ipos);
						AppContext.debug(posidx + ": min start for " + curfilecontig + ": " +  ipos.getAlleleIndex());
					}
					else {
						if(mapFileContig2StartIdx.get(prevfilecontig).compareTo( prevpos.getAlleleIndex()) >= 0) {
							mapFileContig2StartIdx.put(prevfilecontig, prevpos.getAlleleIndex());
							mapFileContig2StartPos.put(prevfilecontig, prevpos);
							AppContext.debug(posidx + ": new min start for " + prevfilecontig + ": " +  prevpos.getAlleleIndex());
						} 
					}
				}
				
				lastpos =  ipos;
				lastposidx = posidx;
			} 
	
			prevposidx=posidx;
			prevpos=ipos;
			posidx++;			
			
		}

		AppContext.debug("mapFileContig2StartIdx:" + mapFileContig2StartIdx.toString());
		AppContext.debug("mapFileContig2EndIdx:" + mapFileContig2EndIdx.toString());

		posidx=0;
		lastpos = null;
		lastposidx=0;
		prevpos=null;
		prevfilecontig=null;
		firstpos=true;

//
//		itPos=snpsposlist.iterator();
//		
//		while(itPos.hasNext()) {
//			
//			SnpsAllvarsMultirefsPos ipos=(SnpsAllvarsMultirefsPos)itPos.next();
//			
//			String curfilecontig=ipos.getFileId() + "-" + ipos.getContig() + "-" + posidx; 
//			 if(mapFileContig2StartIdx.containsKey(curfilecontig) || mapFileContig2EndIdx.containsKey(curfilecontig)) {
//				mapPosidx2FileContigStartEndIdx.put(posidx , new Object[] {ipos.getFileId() ,  ipos.getContig() , mapFileContig2StartIdx.get(curfilecontig),  mapFileContig2EndIdx.get(curfilecontig), prevpos ,ipos});
//				/*
//				mapPosidx2FileContigStartEndIdx.put(posidx , new BigDecimal[] {lastpos.getFileId(), lastpos.getAlleleIndex(), ipos.getAlleleIndex(),
//						lastpos.getAlleleIndex().add(  mapFileContig2StartIdx.get(prevfilecontig).negate() ) , ipos.getAlleleIndex().add( mapFileContig2StartIdx.get(prevfilecontig).negate()),   });
//				mapPosidx2MultirefsPos.put(posidx, new  SnpsAllvarsMultirefsPos[] {lastpos, ipos} );
//				*/
//				
//				prevfilecontig=curfilecontig;
//			}
//			 prevpos=ipos;
//			 posidx++;
//		}
				
		
//		itPos=snpsposlist.iterator();
//		while(itPos.hasNext()) {
//			SnpsAllvarsMultirefsPos ipos=(SnpsAllvarsMultirefsPos)itPos.next();
//			
//			if(firstpos) {
//				prevfilecontig=ipos.getFileId() + "-" + ipos.getContig()+  "-" + lastposidx ; 
//				lastpos = ipos;
//				mapFileContig2StartIdx.put(prevfilecontig,  lastpos.getAlleleIndex());
//				firstpos=false;
//			}
//			
//			//String curfilecontig=ipos.getFileId() + "-" + ipos.getContig() + lastposidx ; // "-" + posidx;  // +  "-" + ipos ;
//			
//			String curfilecontig=ipos.getFileId() + "-" + ipos.getContig() + "-" + lastposidx; 
//			
//			// create substring for new contig or same contig with skipped column
//			//if(  (prevpos!=null &&   !prevpos.getContig().equals(ipos.getContig())) || !itPos.hasNext() ) {
//			if(  ((prevpos!=null && (!prevpos.getFileId().equals(ipos.getFileId()) ||  !prevpos.getContig().equals(ipos.getContig()) )) || !itPos.hasNext())
//					||
//				( prevpos!=null &&  prevpos.getFileId().equals(ipos.getFileId()) && prevpos.getContig().equals(ipos.getContig()) &&  (prevpos.getAlleleIndex().longValue()+1 != ipos.getAlleleIndex().longValue()))	
//					) {
//
//				prevfilecontig=prevpos.getFileId() + "-" + prevpos.getContig() +  "-" + lastposidx ; 
//				//prevfilecontig=prevpos.getFileId() + "-" + prevpos.getContig() +  "-" + lastposidx ;
//				
//				
//				//if(!mapFileContig2StartIdx.containsKey(curfilecontig)) mapFileContig2StartIdx.put(curfilecontig,  ipos.getAlleleIndex() );
//				
//				
//				//if(!mapFileContig2EndIdx.containsKey(prevfilecontig)) mapFileContig2EndIdx.put(prevfilecontig, prevpos.getAlleleIndex() );
//				//else {
//				//	if(!itPos.hasNext()) mapFileContig2EndIdx.put(curfilecontig, ipos.getAlleleIndex());
//				//	else mapFileContig2EndIdx.put(prevfilecontig, prevpos.getAlleleIndex());
//				//}
//				
//				
//				if(!itPos.hasNext()) {
//					mapPosidx2FileContigStartEndIdx.put(posidx , new BigDecimal[] {lastpos.getFileId(), lastpos.getAlleleIndex(), ipos.getAlleleIndex(),
//							lastpos.getAlleleIndex().add(  mapFileContig2StartIdx.get(prevfilecontig).negate() ) , ipos.getAlleleIndex().add( mapFileContig2StartIdx.get(prevfilecontig).negate()),   });
//					mapPosidx2MultirefsPos.put(posidx, new  SnpsAllvarsMultirefsPos[] {lastpos, ipos} );
//				}
//				else {
//					mapPosidx2FileContigStartEndIdx.put(lastposidx , new BigDecimal[] {lastpos.getFileId(), lastpos.getAlleleIndex(), prevpos.getAlleleIndex(),
//						lastpos.getAlleleIndex().add(  mapFileContig2StartIdx.get(prevfilecontig).negate() ) , prevpos.getAlleleIndex().add( mapFileContig2StartIdx.get(prevfilecontig).negate())});
//					mapPosidx2MultirefsPos.put(lastposidx, new  SnpsAllvarsMultirefsPos[] {lastpos, prevpos} );
//					lastpos =  ipos;
//					lastposidx = posidx;
//				}
//				//prevfilecontig=ipos.getFileId() + "-" + ipos.getContig() +  "-" + lastposidx ;
//			}
//			
//			/*
//			else if(  prevpos!=null &&  prevpos.getFileId().equals(ipos.getFileId()) && prevpos.getContig().equals(ipos.getContig()) &&  (prevpos.getAlleleIndex().longValue()+1 != ipos.getAlleleIndex().longValue())  ) {
//				
//				prevfilecontig=prevpos.getFileId() + "-" + prevpos.getContig() ;
//				
//				if(itPos.hasNext()) {
//					mapPosidx2FileContigStartEndIdx.put(lastposidx , new BigDecimal[] {lastpos.getFileId(), lastpos.getAlleleIndex(), prevpos.getAlleleIndex() ,
//							lastpos.getAlleleIndex().add(  mapFileContig2StartIdx.get(prevfilecontig).negate() ) , prevpos.getAlleleIndex().add( mapFileContig2StartIdx.get(prevfilecontig).negate())});
//					mapPosidx2MultirefsPos.put(lastposidx, new  SnpsAllvarsMultirefsPos[] {lastpos, prevpos} );
//
//				}
//				else {
//					mapPosidx2FileContigStartEndIdx.put(posidx , new BigDecimal[] {lastpos.getFileId(), lastpos.getAlleleIndex(), ipos.getAlleleIndex() ,
//							lastpos.getAlleleIndex().add(  mapFileContig2StartIdx.get(curfilecontig).negate() ) , ipos.getAlleleIndex().add( mapFileContig2StartIdx.get(curfilecontig).negate())});
//					mapPosidx2MultirefsPos.put(posidx, new  SnpsAllvarsMultirefsPos[] {lastpos, ipos} );
//				}
//				
//				lastpos =  ipos;
//				lastposidx = posidx;
//			}
//			*/
//			
//			try {
//			mapPosidx2FileContigIdx.put(posidx, new Object[] {ipos.getFileId(),  ipos.getContig(), ipos.getPosition(),
//					ipos.getAlleleIndex().add(mapFileContig2StartIdx.get(curfilecontig).negate()), curfilecontig } );
//			} catch(Exception ex) {
//				AppContext.debug( posidx + " , " + ipos.getContig() + ", " + ipos.getPosition() + ", " +  ipos.getFileId() + ", " + prevfilecontig + " , "  + mapFileContig2StartIdx.toString());
//				throw new RuntimeException(ex);
//			}
//
//			prevpos=ipos;
//			posidx++;
//		}
		
		if(snpstrSnpsAllele1AllvarsDAO==null) throw new RuntimeException("snpstrSnpsAllele1AllvarsDAO==null");


		
		StringBuffer strbuffMessage=new StringBuffer();
		
		strbuffMessage.append(buffMsg).append("\n");
		
		//AppContext.debug("mapFileContig2IdxCount:" + mapFileContig2IdxCount.toString());
		//StringBuffer buff=new StringBuffer();
		itPos=snpsposlist.iterator();
		prevpos=null;
		posidx=0;
		firstpos=true;
		//lastpos= (SnpsAllvarsMultirefsPos)itPos.next();
		//posidx++;
		while(itPos.hasNext()) {
				SnpsAllvarsMultirefsPos ipos = (SnpsAllvarsMultirefsPos)itPos.next();
				String curfilecontig=ipos.getFileId() + "-" + ipos.getContig()  +"-" + posidx ;
				
				
				if( mapFileContig2StartIdx.containsKey(curfilecontig)) {
					
					
					lastpos=mapFileContig2StartPos.get(curfilecontig);
					prevpos=mapFileContig2EndPos.get(curfilecontig);
					
					if(!lastpos.getContig().equals(prevpos.getContig())) throw new RuntimeException("!lastpos.getContig().equals(prevpos.getContig():" + lastpos.getContig() + "," + prevpos.getContig());
					strbuffMessage.append(params.getOrganism() +"-" + lastpos.getContig() + "[" + lastpos.getPosition() + "-" + prevpos.getPosition() + "] aligned with ");
	
					
					StringBuffer noalignbuf=new StringBuffer();
					
					
					if(!lastpos.getContigName(Organism.REFERENCE_NIPPONBARE).isEmpty()) {
						int aligncount = ipos.getAlignCount(Organism.REFERENCE_NIPPONBARE);
						String strcount="";
						if(aligncount>1) strcount="(" + aligncount + " regions)";
						if(!lastpos.getContigName(Organism.REFERENCE_NIPPONBARE).equals(prevpos.getContigName(Organism.REFERENCE_NIPPONBARE))) // throw new RuntimeException("REFERENCE_NIPPONBARE !lastpos.getContig().equals(prevpos.getContig():" + lastpos.getContigName(Organism.REFERENCE_NIPPONBARE) + "," + prevpos.getContigName(Organism.REFERENCE_NIPPONBARE));
						strbuffMessage.append(Organism.REFERENCE_NIPPONBARE + "-[" + lastpos.getContigName(Organism.REFERENCE_NIPPONBARE) + " " + lastpos.getPosition(Organism.REFERENCE_NIPPONBARE) + "-" + prevpos.getContigName(Organism.REFERENCE_NIPPONBARE) + " " + prevpos.getPosition(Organism.REFERENCE_NIPPONBARE) + "]" + strcount + ";");
						else strbuffMessage.append(Organism.REFERENCE_NIPPONBARE + "-" + lastpos.getContigName(Organism.REFERENCE_NIPPONBARE) + "[" + lastpos.getPosition(Organism.REFERENCE_NIPPONBARE) + "-" + prevpos.getPosition(Organism.REFERENCE_NIPPONBARE) + "]" + strcount + ";");
					} else noalignbuf.append(Organism.REFERENCE_NIPPONBARE+",");
	
					if(!lastpos.getContigName(Organism.REFERENCE_9311).isEmpty()) {
						int aligncount = ipos.getAlignCount(Organism.REFERENCE_9311);
						String strcount="";
						if(!lastpos.getContigName(Organism.REFERENCE_9311).equals(prevpos.getContigName(Organism.REFERENCE_9311))) //throw new RuntimeException("REFERENCE_9311 !lastpos.getContig().equals(prevpos.getContig():" + lastpos.getContigName(Organism.REFERENCE_9311) + "," + prevpos.getContigName(Organism.REFERENCE_9311));
						strbuffMessage.append(Organism.REFERENCE_9311 + "-[" + lastpos.getContigName(Organism.REFERENCE_9311) + " " + lastpos.getPosition(Organism.REFERENCE_9311) + "-" + prevpos.getContigName(Organism.REFERENCE_9311) + " " + prevpos.getPosition(Organism.REFERENCE_9311) + "]" + strcount + ";");
						else strbuffMessage.append(Organism.REFERENCE_9311 + "-" + lastpos.getContigName(Organism.REFERENCE_9311) + "[" + lastpos.getPosition(Organism.REFERENCE_9311) + "-" + prevpos.getPosition(Organism.REFERENCE_9311) + "]" + strcount + ";");
					}else noalignbuf.append(Organism.REFERENCE_9311+",");
	
					if(!lastpos.getContigName(Organism.REFERENCE_IR64).isEmpty()) {
						int aligncount = ipos.getAlignCount(Organism.REFERENCE_IR64);
						String strcount="";
						if(aligncount>1) strcount="(" + aligncount + " regions)";
						if(!lastpos.getContigName(Organism.REFERENCE_IR64).equals(prevpos.getContigName(Organism.REFERENCE_IR64))) //throw new RuntimeException("REFERENCE_IR64 !lastpos.getContig().equals(prevpos.getContig():" + lastpos.getContigName(Organism.REFERENCE_IR64) + "," + prevpos.getContigName(Organism.REFERENCE_IR64));
						strbuffMessage.append(Organism.REFERENCE_IR64 + "-[" + lastpos.getContigName(Organism.REFERENCE_IR64) + " " + lastpos.getPosition(Organism.REFERENCE_IR64) + "-" + prevpos.getContigName(Organism.REFERENCE_IR64) + " " + prevpos.getPosition(Organism.REFERENCE_IR64) + "]" + strcount + ";");
						else strbuffMessage.append(Organism.REFERENCE_IR64 + "-" + lastpos.getContigName(Organism.REFERENCE_IR64) + "[" + lastpos.getPosition(Organism.REFERENCE_IR64) + "-" + prevpos.getPosition(Organism.REFERENCE_IR64) + "]" + strcount + ";");
					}else noalignbuf.append(Organism.REFERENCE_IR64+",");
	
					if(!lastpos.getContigName(Organism.REFERENCE_KASALATH).isEmpty()) {
						int aligncount = ipos.getAlignCount(Organism.REFERENCE_KASALATH);
						String strcount="";
						if(aligncount>1) strcount="(" + aligncount + " regions)";
						if(!lastpos.getContigName(Organism.REFERENCE_KASALATH).equals(prevpos.getContigName(Organism.REFERENCE_KASALATH))) // throw new RuntimeException("REFERENCE_KASALATH !lastpos.getContig().equals(prevpos.getContig():" + lastpos.getContigName(Organism.REFERENCE_KASALATH) + "," + prevpos.getContigName(Organism.REFERENCE_KASALATH));
						strbuffMessage.append(Organism.REFERENCE_KASALATH + "-[" + lastpos.getContigName(Organism.REFERENCE_KASALATH) + " " + lastpos.getPosition(Organism.REFERENCE_KASALATH) + "-" + prevpos.getContigName(Organism.REFERENCE_KASALATH) + " " + prevpos.getPosition(Organism.REFERENCE_KASALATH) + "]" + strcount + ";");
						else strbuffMessage.append(Organism.REFERENCE_KASALATH + "-" + lastpos.getContigName(Organism.REFERENCE_KASALATH) + "[" + lastpos.getPosition(Organism.REFERENCE_KASALATH) + "-" + prevpos.getPosition(Organism.REFERENCE_KASALATH) + "]" + strcount + ";");
					}else noalignbuf.append(Organism.REFERENCE_KASALATH+",");
	
					if(!lastpos.getContigName(Organism.REFERENCE_DJ123).isEmpty()) {
						int aligncount = ipos.getAlignCount(Organism.REFERENCE_DJ123);
						String strcount="";
						if(aligncount>1) strcount="(" + aligncount + " regions)";
						if(!lastpos.getContigName(Organism.REFERENCE_DJ123).equals(prevpos.getContigName(Organism.REFERENCE_DJ123))) // throw new RuntimeException("REFERENCE_DJ123 !lastpos.getContig().equals(prevpos.getContig():" + lastpos.getContigName(Organism.REFERENCE_DJ123) + "," + prevpos.getContigName(Organism.REFERENCE_DJ123));
						strbuffMessage.append(Organism.REFERENCE_DJ123 + "-[" + lastpos.getContigName(Organism.REFERENCE_DJ123) + " " + lastpos.getPosition(Organism.REFERENCE_DJ123) + "-" + prevpos.getContigName(Organism.REFERENCE_DJ123) + " " + prevpos.getPosition(Organism.REFERENCE_DJ123) + "]" + strcount + ";");
						else strbuffMessage.append(Organism.REFERENCE_DJ123 + "-" + lastpos.getContigName(Organism.REFERENCE_DJ123) + "[" + lastpos.getPosition(Organism.REFERENCE_DJ123) + "-" + prevpos.getPosition(Organism.REFERENCE_DJ123) + "]" + strcount + ";");
					}else noalignbuf.append(Organism.REFERENCE_DJ123+",");
					
					if(noalignbuf.length()>0)
						strbuffMessage.append(" No alignment with " + noalignbuf.substring(0,noalignbuf.length()-1));
					strbuffMessage.append("\n");
					lastpos=prevpos;
				}
				
			prevpos= ipos; 	
			posidx++;
		}
			
		
		//AppContext.debug("mapPosidx2FileContigIdx:\nfileId, fromContigId, fromPosition, filecontCount,  filecolidx, filecontig\t\t\tfile:colidx range[-]\tsnpstring:[-]\n" + buff);
		
		// initialize mapVar2SnpStringBuffer
		
		Map<BigDecimal,StringBuffer>  mapVar2SnpStringBuffer=null;
		
		//Map<String, Map<BigDecimal, String>> mapFileContig2MapVar2Snpstring=new HashMap();
		itPos=snpsposlist.iterator();
		/*
		posidx=0;
		prevfilecontig=null;
		prevpos=0;
		int lastPosIdx=0;
		*/
		
		StringBuffer strRefbuff=new StringBuffer();
		posidx=0;
		lastpos = null;
		prevpos=null;
		prevfilecontig=null;
		firstpos=true;
		int lastPosIdx=0;
		Map<BigDecimal,Position> mapSnpid2Pos = new HashMap();
		
		while(itPos.hasNext()) {
		
			SnpsAllvarsMultirefsPos ipos=(SnpsAllvarsMultirefsPos)itPos.next();
			
			mapSnpid2Pos.put( ipos.getSnpFeatureId(), ipos);
			
			strRefbuff.append( ipos.getRefnuc()  );
			String curfilecontig=ipos.getFileId() + "-" + ipos.getContig()  +"-" + posidx ; // + "-" + ipos.getPosition();
			
			if( mapFileContig2StartIdx.containsKey(curfilecontig)) {
								
				//Map<BigDecimal,String>  mapVar2SnpString = mapFileContig2MapVar2Snpstring.get( curfilecontig );
				BigDecimal typeid = BigDecimal.valueOf(Long.valueOf(curfilecontig.split("\\-")[0]));
				if(!typeid.equals(ipos.getFileId())) throw new RuntimeException("!typeid.equals(ipos.getFileId()) at " + ipos);
				//if(mapVar2SnpString==null) {
				
					H5Dataset hdf5dao = mapFileId2DatasetDAO.get( typeid );
					if(hdf5dao==null) {
						
						if( typeid.equals( SnpsAllvarsMultirefsPosDAO.TYPE_3KALLSNP_HDF5_V2 ) ) {
							nballsnpsdao= (H5Dataset)AppContext.checkBean(nballsnpsdao,"H5SNPUniIUPACV2DAO");
							hdf5dao=nballsnpsdao;	
						}
						else if( typeid.equals( SnpsAllvarsMultirefsPosDAO.TYPE_3KCORESNP_HDF5_V2 ) ) {
							nbcoresnpsdao= (H5Dataset)AppContext.checkBean(nbcoresnpsdao,"H5SNPCoreIUPACV2DAO");
							hdf5dao=nbcoresnpsdao;	
						} else
							hdf5dao = new H5Dataset( SnpsAllvarsMultirefsPosDAO.MAP_TYPE2HDF5FILE.get(typeid ));
						mapFileId2DatasetDAO.put(typeid, hdf5dao);
					}
					Map<BigDecimal,String>  mapVar2SnpString = hdf5dao.readSNPString(null, mapFileContig2StartIdx.get(curfilecontig).intValue() , mapFileContig2EndIdx.get(curfilecontig).intValue());
					//mapFileContig2MapVar2Snpstring.put(curfilecontig , mapVar2SnpString);
				//}
				
				// initialize string buffer for all vars
				if(mapVar2SnpStringBuffer==null) {
					mapVar2SnpStringBuffer = new HashMap();
					Iterator<BigDecimal> itVar = mapVar2SnpString.keySet().iterator();
					while(itVar.hasNext()) {
						mapVar2SnpStringBuffer.put( itVar.next(), new StringBuffer());
					}
				}
	
				int idxstart = mapFileContig2StartIdx.get(curfilecontig).intValue() ;
				int idxend = mapFileContig2EndIdx.get(curfilecontig).intValue();
				
				AppContext.debug( "at posidx=" + posidx + " appending from "  + curfilecontig + "["  + idxstart+ "-" +idxend+ "]");
			
				Iterator<BigDecimal> itVar = mapVar2SnpString.keySet().iterator();
				while(itVar.hasNext()) {
					BigDecimal varid=itVar.next();
					//{ipos.getFileId(),  ipos.getContig(),  mapFileContig2IdxCount.get(filecontig)} 
					mapVar2SnpStringBuffer.get(varid).append( mapVar2SnpString.get(varid)); //.substring(idxstart , idxend+1 ));
					
					if(!itVar.hasNext()) {
						if(prevpos!=null)
							AppContext.debug("filling table: pos[" + prevpos.getPosition() + "-" + ipos.getPosition() + "]  " + curfilecontig + " colidx=[" +idxstart+ "-" + idxend + "]"); 
						AppContext.debug( "mapVar2SnpString.get(varid).length=" + mapVar2SnpString.get(varid).length());
						AppContext.debug("mapVar2SnpStringBuffer length=" + mapVar2SnpStringBuffer.get(varid).length());
						/*
						if(posidx !=  mapVar2SnpStringBuffer.get(varid).length() && itPos.hasNext()) {
							AppContext.debug("posidx !=  mapVar2SnpStringBuffer.get(varid).length(), posidx=" + posidx);
							throw new RuntimeException("posidx !=  mapVar2SnpStringBuffer.get(varid).length(), posidx=" + (posidx));
						}
						*/
					}
				}				
				lastpos =  ipos;
				lastPosIdx=posidx;
			}
			
			prevpos=ipos;
			posidx++;
		}				
		
		
//		while(itPos.hasNext()) {
//			
//			SnpsAllvarsMultirefsPos ipos=(SnpsAllvarsMultirefsPos)itPos.next();
//			
//			mapSnpid2Pos.put( ipos.getSnpFeatureId(), ipos);
//			
//			strRefbuff.append( ipos.getRefnuc()  );
//			String curfilecontig=ipos.getFileId() + "-" + ipos.getContig()  +"-" + posidx ; // + "-" + ipos.getPosition();
//			
//			//mapPosidx2FileContigStartEndIdx.put(posidx , new BigDecimal[] {lastpos.getFileId(), lastpos.getAlleleIndex(), ipos.getAlleleIndex() ,
//			//		lastpos.getAlleleIndex().add(  mapFileContig2StartIdx.get(curfilecontig).negate() ) , ipos.getAlleleIndex().add( mapFileContig2StartIdx.get(curfilecontig).negate())});
//
//			
//			// create substring for new contig or same contig with skipped column
//			//if(  (prevpos!=null &&  ( !prevpos.getContig().equals(ipos.getContig()) ||  (prevpos.getAlleleIndex().longValue()+1 != ipos.getAlleleIndex().longValue()))) || !itPos.hasNext() ) {
//			//if(  (prevpos!=null && (!prevpos.getFileId().equals(ipos.getFileId()) ||  !prevpos.getContig().equals(ipos.getContig()) )) || !itPos.hasNext() ) {
//			
//			if(mapPosidx2FileContigStartEndIdx.containsKey(posidx)) {
//								
//				BigDecimal alleleidxranges[]= mapPosidx2FileContigStartEndIdx.get(posidx);
//				Map<BigDecimal,String>  mapVar2SnpString = mapFileContig2MapVar2Snpstring.get( curfilecontig );
//				BigDecimal typeid=alleleidxranges[0];
//				if(!typeid.equals(ipos.getFileId())) throw new RuntimeException("!typeid.equals(ipos.getFileId()) at " + ipos);
//				if(mapVar2SnpString==null) {
//				
//					H5Dataset hdf5dao = mapFileId2DatasetDAO.get( typeid );
//					if(hdf5dao==null) {
//						
//						if( typeid.equals( SnpsAllvarsMultirefsPosDAO.TYPE_3KALLSNP_HDF5_V2 ) ) {
//							nballsnpsdao= (H5Dataset)AppContext.checkBean(nballsnpsdao,"H5SNPUniIUPACV2DAO");
//							hdf5dao=nballsnpsdao;	
//						}
//						else if( typeid.equals( SnpsAllvarsMultirefsPosDAO.TYPE_3KCORESNP_HDF5_V2 ) ) {
//							nbcoresnpsdao= (H5Dataset)AppContext.checkBean(nbcoresnpsdao,"H5SNPCoreIUPACV2DAO");
//							hdf5dao=nbcoresnpsdao;	
//						} else
//							hdf5dao = new H5Dataset( SnpsAllvarsMultirefsPosDAO.MAP_TYPE2HDF5FILE.get(typeid ));
//						mapFileId2DatasetDAO.put(typeid, hdf5dao);
//					}
//					mapVar2SnpString = hdf5dao.readSNPString(null, mapFileContig2StartIdx.get(curfilecontig).intValue() , mapFileContig2EndIdx.get(curfilecontig).intValue());
//					mapFileContig2MapVar2Snpstring.put(curfilecontig , mapVar2SnpString);
//				}
//				
//				// initialize string buffer for all vars
//				if(mapVar2SnpStringBuffer==null) {
//					mapVar2SnpStringBuffer = new HashMap();
//					Iterator<BigDecimal> itVar = mapVar2SnpString.keySet().iterator();
//					while(itVar.hasNext()) {
//						mapVar2SnpStringBuffer.put( itVar.next(), new StringBuffer());
//					}
//				}
//				
//				//mapPosidx2FileContigStartEndIdx.put(posidx , new BigDecimal[] {lastpos.getFileId(), lastpos.getAlleleIndex(), ipos.getAlleleIndex() ,
//				//		lastpos.getAlleleIndex().add(  mapFileContig2StartIdx.get(curfilecontig).negate() ) , ipos.getAlleleIndex().add( mapFileContig2StartIdx.get(curfilecontig).negate())});
//
//				int idxstart = alleleidxranges[3].intValue();
//				int idxend = alleleidxranges[4].intValue();
//				
//				AppContext.debug( "posidx=" + posidx + ": at lastPosIdx=" + lastpos +  " appending from "  + curfilecontig + "["  + idxstart+ "-" +idxend+ "]");
//			
//				Iterator<BigDecimal> itVar = mapVar2SnpString.keySet().iterator();
//				while(itVar.hasNext()) {
//					BigDecimal varid=itVar.next();
//					//{ipos.getFileId(),  ipos.getContig(),  mapFileContig2IdxCount.get(filecontig)} 
//					mapVar2SnpStringBuffer.get(varid).append( mapVar2SnpString.get(varid).substring(idxstart , idxend+1 ));
//					
//					if(!itVar.hasNext()) {
//						AppContext.debug("filling table: pos[" + alleleidxranges[1]+ "-" + alleleidxranges[2] + "]  file=" + alleleidxranges[0] + "  colidx=[" +idxstart+ "-" + idxend + "]"); 
//						AppContext.debug( "mapVar2SnpString.get(varid).length=" + mapVar2SnpString.get(varid).length());
//						AppContext.debug("mapVar2SnpStringBuffer length=" + mapVar2SnpStringBuffer.get(varid).length());
//						/*
//						if(posidx !=  mapVar2SnpStringBuffer.get(varid).length() && itPos.hasNext()) {
//							AppContext.debug("posidx !=  mapVar2SnpStringBuffer.get(varid).length(), posidx=" + posidx);
//							throw new RuntimeException("posidx !=  mapVar2SnpStringBuffer.get(varid).length(), posidx=" + (posidx));
//						}
//						*/
//					}
//				}				
//				lastpos =  ipos;
//				lastPosIdx=posidx;
//			}
//			
//			prevpos=ipos;
//			posidx++;
//		}		

		
		Map mapVarid2Snpsstr=new HashMap();
		Iterator<BigDecimal> itVar=mapVar2SnpStringBuffer.keySet().iterator();
		while(itVar.hasNext()) {
			BigDecimal ivar=itVar.next();
			mapVarid2Snpsstr.put(  ivar, mapVar2SnpStringBuffer.get(ivar).toString());
		}
		
		// get allele column indices from start to end positions
		SnpsAllvarsPos startpos =  snpsposlist.get(0);
		SnpsAllvarsPos endpos =  snpsposlist.get( snpsposlist.size()-1 );

		String strRef=strRefbuff.toString();
		
		int refLength=-1;
		
		AppContext.debug( snpsposlist.size() + " snpposlist, pos between " +startpos.getPosition() +  "-" + endpos.getPosition() + "  index between " + startpos.getAlleleIndex() + "-" + endpos.getAlleleIndex());
		
		
				
			Map mapVarid2Snpsstr_allele2=null;
			Map<BigDecimal,Map> mapVarid2SnpsAllele2str = new HashMap();
			
				if (mapVarid2Snpsstr_allele2==null && params.includeDataset(SnpsAllvarsPosDAO.DATASET_SNPINDELV2_IUPAC)) {
				
				Map mapVarid2SnpsstrSplitIUPAC=new LinkedHashMap();
				mapVarid2Snpsstr_allele2=new LinkedHashMap();
				
				Iterator itVarid = mapVarid2Snpsstr.keySet().iterator();
				while(itVarid.hasNext()) {
					BigDecimal varid= (BigDecimal)itVarid.next();

					//if(params.includeDataset(SnpsAllvarsPosDAO.DATASET_SNPINDELV2)) {					
						// revised for SNPv2 (allele2!=0 for allele1==allele2)
						String allele1str = (String)mapVarid2Snpsstr.get( varid );
						
						//String allele2str = (String)mapVarid2Snpsstr_allele2.get( varid );
						//if(allele1str.length()!=allele2str.length()) throw new RuntimeException( "allele1str.length()!=allele2str.length(): " + allele1str.length() + ", " + allele2str.length());
						
						StringBuffer buffNewStr=new StringBuffer();
						StringBuffer buffNewStr2=new StringBuffer();
						for(int iStr=0; iStr<allele1str.length(); iStr++) {
							//char allele2i =allele2str.charAt(iStr);
							char allele1i =allele1str.charAt(iStr);
							String alleles12= AppContext.getNucsFromIUPAC(allele1i);
							if(alleles12.length()==2) {
								Map mapTableidx2Nuc = mapVarid2SnpsAllele2str.get( varid );
								if(mapTableidx2Nuc==null) {
									mapTableidx2Nuc = new HashMap();
									mapVarid2SnpsAllele2str.put(varid , mapTableidx2Nuc);
								}
								mapTableidx2Nuc.put( snpsposlist.get(iStr), alleles12.charAt(1));
								buffNewStr2.append(alleles12.charAt(1));
							} else 
								buffNewStr2.append(alleles12.charAt(0));
							
							buffNewStr.append(alleles12.charAt(0));
						}
						
						mapVarid2SnpsstrSplitIUPAC.put( varid , buffNewStr.toString());
						mapVarid2Snpsstr_allele2.put( varid , buffNewStr2.toString());
						
						if(!itVarid.hasNext()) AppContext.debug(varid + "  length=" +  buffNewStr.length() + ", " + buffNewStr2.length());
						
//						if(!allele1str.equals(buffNewStr.toString())) {
//							AppContext.debug( "varid=" + varid);
//							AppContext.debug( "alleleIUPACstr=" + allele1str);
//							AppContext.debug( "allele1    str=" + buffNewStr);
//							AppContext.debug( "allele2    str=" + buffNewStr2);
//						}
					//}
				}
				
				mapVarid2Snpsstr = mapVarid2SnpsstrSplitIUPAC;
				
				
			} // else throw new RuntimeException("heteroSnps==null and mapVarid2Snpsstr_allele2==null ... no allele2 data");
			
			AppContext.debug("snpsposlist length=" + snpsposlist.size() );  
				
			// generate pos2alleles

			Map<Position, Set<Character>> mapPos2Allele=new TreeMap();
			Map<Position, Set<Character>> mapPos2AlleleHetero=new TreeMap();
			
			itVar=mapVarid2Snpsstr.keySet().iterator();
			while(itVar.hasNext()) {
				BigDecimal varid=itVar.next();
				String allele1str = (String)mapVarid2Snpsstr.get( varid );
				String allele2str = (String)mapVarid2Snpsstr_allele2.get( varid );
				Iterator<SnpsAllvarsPos> itSnppos =snpsposlist.iterator();
				int icount=0;
				while(itSnppos.hasNext()) {
					SnpsAllvarsPos snppos=itSnppos.next();
					
					Set setAlleles=mapPos2Allele.get(snppos);
					if(setAlleles==null) {
						setAlleles=new HashSet();
						mapPos2Allele.put(snppos, setAlleles);
					}
					// hdf5 format v2
					char chari1=allele1str.charAt(icount);
					if(chari1!='0' && chari1!='?') setAlleles.add(chari1);
					char chari2=allele2str.charAt(icount);
					if(chari2!='0' && chari2!='?' ) setAlleles.add(chari2);
					
					if(chari1!=chari2) {
						if(chari1!=snppos.getRefnuc().charAt(0)) {
							Set allele2= mapPos2AlleleHetero.get(snppos);
							if(allele2==null) {
								allele2=new HashSet();
								mapPos2AlleleHetero.put(snppos,  allele2);
							}
							allele2.add(chari1);
						}
						if(chari2!=snppos.getRefnuc().charAt(0)) {
							Set allele2= mapPos2AlleleHetero.get(snppos);
							if(allele2==null) {
								allele2=new HashSet();
								mapPos2AlleleHetero.put(snppos,  allele2);
							}
							allele2.add(chari2);
						}
					}
					icount++;
				}
			}
			
			 //mapPos2NonsynAlleles, mapPos2SynAlleles, setSnpInExonPos, setSpliceDonorsPos,  setSpliceAcceptorsPos,
			Set setSpliceAcceptorsPos = new HashSet();
			Set setSpliceDonorsPos = new HashSet();
			Map<Position,Set<Character>> mapPos2NonsynAlleles = new TreeMap();
			Map<Position,Set<Character>> mapPos2SynAlleles = new TreeMap();
			Set<Position> setSnpInExonPos = new TreeSet();
				
			
			if(params.getOrganism().equals(AppContext.getDefaultOrganism())) {
	
					snpsnonsynDAO = (SnpsNonsynAllvarsDAO) AppContext.checkBean(snpsnonsynDAO, "VSnpNonsynallelePosDAO");
					snpssynDAO = (SnpsSynAllvarsDAO) AppContext.checkBean(snpssynDAO, "VSnpSynalleleDAO");
					//snpsinexonDAO = (SnpsInExonDAO) AppContext.checkBean(snpsinexonDAO, "SnpsInExonDAO");
					//snpsheteroDAO = (SnpsHeteroAllvarsDAO)AppContext.checkBean(snpsheteroDAO, "SnpsHeteroAllvarsDAO");
	
					
				
					//Set heteroSnps = null;
					Set nonsynAllele = null;
					Set synAllele = null;
					//Set inexonSnps = null;
					
					// using hdf5
					if(  (setPositions!=null && !setPositions.isEmpty())  || (colLocus!=null && !colLocus.isEmpty()) ) {
						
						
						if(setPositions!=null && !setPositions.isEmpty()) {
							nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByChrPosIn(chr, listpos, snptype);
							synAllele = snpssynDAO.findSnpSynAlleleByChrPosIn(chr, listpos, snptype);
						} 
						else if(colLocus!=null && !colLocus.isEmpty()) {
							nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByChrPosIn(chr, colLocus, snptype);
							synAllele = snpssynDAO.findSnpSynAlleleByChrPosIn(chr, colLocus, snptype);
						} 
						AppContext.resetTimer("to read nonsynonymous allele, inexon  from  database..");
					}
					else {
						nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByChrPosBetween(chr, startpos.getPosition().intValue(), endpos.getPosition().intValue(), snptype);
						synAllele = snpssynDAO.findSnpSynAlleleByChrPosBetween(chr, startpos.getPosition().intValue(), endpos.getPosition().intValue(), snptype);
						AppContext.resetTimer("to read nonsynonymous allele, inexon  from  database..");
					}			
				

						
				// non-synonymous alleles for positions
				setSnpInExonPos = new TreeSet();
				
				
				Iterator<SnpsNonsynAllele> itNonsyn = nonsynAllele.iterator();
				while(itNonsyn.hasNext()) {
					SnpsNonsynAllele nonsynallele = itNonsyn.next();
					
					if(mapSnpid2Pos==null) throw new RuntimeException("mapSnpid2Pos==null");
					if(nonsynallele==null) throw new RuntimeException("nonsynallele==null");
					if(nonsynallele.getSnpFeatureId()==null) throw new RuntimeException("nonsynallele.getSnp()==null");
					
					Position pos = mapSnpid2Pos.get( nonsynallele.getSnpFeatureId() );
					
					// assuming all nonsyn are in exon
					setSnpInExonPos.add( pos );
					
					Set<Character> setVarietyAlleles = mapPos2Allele.get(pos);
					if(setVarietyAlleles==null) throw new RuntimeException("setVarietyAlleles==null");
					if(nonsynallele.getAllele()=='\0') throw new RuntimeException("nonsynallele.getAllele()==null");
	
					if(setVarietyAlleles.contains( nonsynallele.getAllele()) ) {
	
						Set<Character> alleles = mapPos2NonsynAlleles.get(pos);
						if(alleles==null) {
							alleles = new HashSet();
							mapPos2NonsynAlleles.put( pos , alleles);
						}
						alleles.add( nonsynallele.getAllele() );
					}
				}
				
				Iterator<SnpsSynAllele> itSyn = synAllele.iterator();
				while(itSyn.hasNext()) {
					SnpsSynAllele synallele = itSyn.next();
					Position pos = mapSnpid2Pos.get( synallele.getSnpFeatureId() );
					
					// assuming all syn are in exon
					setSnpInExonPos.add( pos );
					
					Set<Character> setVarietyAlleles = mapPos2Allele.get(pos);
					
					if(setVarietyAlleles.contains( synallele.getAllele()) ) {
						Set<Character> alleles = mapPos2SynAlleles.get(pos);
						if(alleles==null) {
							alleles = new HashSet();
							mapPos2SynAlleles.put( pos , alleles);
						}
						alleles.add( synallele.getAllele() );
					}
				}
			
				if(nonsynAllele!=null) AppContext.debug( nonsynAllele.size() + " non-synonymous alleles, " + mapPos2NonsynAlleles.size() + " positions/idx,  " +  mapPos2NonsynAlleles.size() + "  nonsys alleles positions");
				
				
				 
				// get splice variants
	
				Set setSpliceAcceptors= new HashSet();
				Set setSpliceDonors= new HashSet();
				
				snpsspliceacceptorDAO = (SnpsSpliceAcceptorDAO)AppContext.checkBean(snpsspliceacceptorDAO, "SnpsSpliceAcceptorDAO");
				snpssplicedonorDAO = (SnpsSpliceDonorDAO)AppContext.checkBean(snpssplicedonorDAO, "SnpsSpliceDonorDAO");
				if(setPositions!=null && !setPositions.isEmpty()) {
					setSpliceAcceptors = snpsspliceacceptorDAO.getSNPsIn(chr, setPositions);
					setSpliceDonors = snpssplicedonorDAO.getSNPsIn(chr, setPositions);
				}
				else if(colLocus!=null && !colLocus.isEmpty()) {
						setSpliceAcceptors = snpsspliceacceptorDAO.getSNPsIn(chr, colLocus);
						setSpliceDonors = snpssplicedonorDAO.getSNPsIn(chr, colLocus);							
				} else {
					setSpliceAcceptors = snpsspliceacceptorDAO.getSNPsBetween(chr, start.intValue(), end.intValue());  
					setSpliceDonors = snpssplicedonorDAO.getSNPsBetween(chr, start.intValue(), end.intValue());							
				}
				
				Iterator<SnpsSpliceAcceptor>  itAcceptors = setSpliceAcceptors.iterator();
				while(itAcceptors.hasNext()) {
					SnpsSpliceAcceptor acc = itAcceptors.next();
					setSpliceAcceptorsPos.add( acc.getPos());
				}
				Iterator<SnpsSpliceDonor>  itDonor = setSpliceDonors.iterator();
				while(itDonor.hasNext()) {
					SnpsSpliceDonor acc = itDonor.next();
					setSpliceDonorsPos.add( acc.getPos());
				}
					
				
				if(AppContext.isLocalhost()) {
					AppContext.debug("mapPos2NonsynAlleles " + mapPos2NonsynAlleles.size() +": " + mapPos2NonsynAlleles.toString() );
					AppContext.debug("mapPos2SynAlleles " + mapPos2SynAlleles.size() + ": " + mapPos2SynAlleles.toString() );
					AppContext.debug("mapPos2Allele " + mapPos2Allele.size() + ": " + mapPos2Allele.toString() );
					AppContext.debug("mapPos2AlleleHetero " + mapPos2AlleleHetero.size() +": " + mapPos2AlleleHetero.toString() );
					AppContext.debug("setSpliceDonorsPos " + setSpliceDonorsPos.size() +": " + setSpliceDonorsPos.toString() );
					AppContext.debug("setSpliceAcceptorsPos " + setSpliceAcceptorsPos.size() +": " + setSpliceAcceptorsPos.toString() );
					//AppContext.debug( nonsynAllele.toString() );
				}
			
			}
			
			SNPsStringData snpstrdata = new  SNPsStringData(snpsposlist,  strRef, mapVarid2Snpsstr, mapVarid2SnpsAllele2str,
					mapPos2Allele, mapPos2NonsynAlleles, mapPos2SynAlleles, setSnpInExonPos, setSpliceDonorsPos,  setSpliceAcceptorsPos, strbuffMessage.toString());
			
			
			
			return snpstrdata;
	}
	
		
	
	
//	/**
//	 * query SNPsStringData for other (not NB) reference genomes
//	 * @param params
//	 * @param colVarids
//	 * @param chr
//	 * @param start
//	 * @param end
//	 * @param setPositions
//	 * @param colLocus
//	 * @return
//	 */
//	private SNPsStringData getSNPsStringDataOtherRefsOld2(GenotypeQueryParams params) {
//		// TODO Auto-generated method stub
//
//		AppContext.debug("executing...getSNPsStringDataOtherRefs");
//		
//		Collection colVarids=params.getColVarIds();
//		String chr= params.getsChr();
//		BigDecimal start= BigDecimal.valueOf(params.getlStart());
//		BigDecimal end= BigDecimal.valueOf(params.getlEnd()); 
//		Collection setPositions = params.getPoslist();
//		Collection colLocus = params.getColLoci();
//			
//		
//		//snpstringallvarsposDAO = (SnpsAllvarsPosDAO)AppContext.checkBean(snpstringallvarsposDAO, "VSnpRefposindexDAO") ;
//		snpstringallvarsposDAO = (SnpsAllvarsPosDAO)AppContext.checkBean(snpstringallvarsposDAO, "VConvertposAny2allrefsDAO") ;
//		
//		SnpsAllvarsMultirefsPosDAO snpstringallvarsmultirefsposDAO = (SnpsAllvarsMultirefsPosDAO)snpstringallvarsposDAO;
//		
//		SnpsStringDAO snpstrSnpsAllele1AllvarsDAO=null;
//		SnpsStringDAO snpstrSnpsAllele2AllvarsDAO=null;
//		BigDecimal snptype=snptype=SnpsAllvarsPosDAO.TYPE_3KALLSNP_HDF5_V2; 
//		
//		// get SNP positions and HDF5 column index mapping
//		// depends on dataset, version, core or all
//		/*
//		if(params.includeDataset(SnpsAllvarsPosDAO.DATASET_SNPINDELV1)) {
//			throw new RuntimeException(SnpsAllvarsPosDAO.DATASET_SNPINDELV1 + " not available from other reference genomes");
//		} else if(params.includeDataset(SnpsAllvarsPosDAO.DATASET_SNPINDELV2)) {
//			if(params.isbCoreonly()) {
//				snpstrSnpsAllele1AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrCoresnpsAllele1AllvarsDAO, "H5SNPCoreAllele1V2DAO");
//				snpstrSnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrCoresnpsAllele1AllvarsDAO, "H5SNPCoreAllele2V2DAO");
//				snptype=SnpsAllvarsPosDAO.TYPE_3KCORESNP_HDF5_V2;
//			} else {
//				snptype=SnpsAllvarsPosDAO.TYPE_3KALLSNP_HDF5_V2;
//				snpstrSnpsAllele1AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrAllsnpsAllele1AllvarsDAO, "H5SNPUniAllele1V2DAO");
//				snpstrSnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrAllsnpsAllele2AllvarsDAO, "H5SNPUniAllele2V2DAO");
//			}
//			
//		} else if(params.includeDataset(SnpsAllvarsPosDAO.DATASET_SNPINDELV2_IUPAC)) {
//			if(params.isbCoreonly()) {
//				snpstrSnpsAllele1AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrCoresnpsAllele1AllvarsDAO, "H5SNPCoreIUPACV2DAO");
//				//snpstrSnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrCoresnpsAllele1AllvarsDAO, "H5SNPCoreAllele2V2DAO");
//				snptype=SnpsAllvarsPosDAO.TYPE_3KCORESNP_HDF5_V2;
//			} else {
//				snptype=SnpsAllvarsPosDAO.TYPE_3KALLSNP_HDF5_V2;
//				snpstrSnpsAllele1AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrAllsnpsAllele1AllvarsDAO, "H5SNPUniIUPACV2DAO");
//				//snpstrSnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrAllsnpsAllele2AllvarsDAO, "H5SNPUniAllele2V2DAO");
//			}
//		}
//		*/
//		
//		
//		List<SnpsAllvarsPos> snpsposlist = null;
//		List listpos = null;
//		if(colVarids==null || colVarids.isEmpty()) {
//			if( (setPositions!=null && !setPositions.isEmpty()) ) {
//				listpos = new ArrayList();
//				listpos.addAll(new TreeSet(setPositions));
//				AppContext.resetTimer("getSNPsString start1");
//				snpsposlist  = snpstringallvarsmultirefsposDAO.getSNPsInChromosome(chr.toString(),  listpos, snptype, listitemsdao.getOrganismByName(params.getOrganism()).getOrganismId());
//			}
//			else if( (colLocus!=null && !colLocus.isEmpty()) ) {
//				AppContext.resetTimer("getSNPsString start1b");
//				snpsposlist  = snpstringallvarsmultirefsposDAO.getSNPsInChromosome(chr.toString(),  colLocus, snptype, listitemsdao.getOrganismByName(params.getOrganism()).getOrganismId());
//			}
//			else {
//				AppContext.resetTimer("getSNPsString start2");
//				snpsposlist  = snpstringallvarsmultirefsposDAO.getSNPs(chr.toString(), start.intValue(), end.intValue(),   snptype,  listitemsdao.getOrganismByName(params.getOrganism()).getOrganismId());
//			}
//		} else {
//			if(setPositions!=null && !setPositions.isEmpty()) {
//				listpos = new ArrayList();
//				listpos.addAll(new TreeSet(setPositions));
//				AppContext.resetTimer("getSNPsString start3");
//				snpsposlist  = snpstringallvarsmultirefsposDAO.getSNPsInChromosome( chr.toString(),  listpos, snptype,  listitemsdao.getOrganismByName(params.getOrganism()).getOrganismId());
//			}
//			else if(colLocus!=null && !colLocus.isEmpty()) {
//				AppContext.resetTimer("getSNPsString start3b");
//				snpsposlist  = snpstringallvarsmultirefsposDAO.getSNPsInChromosome( chr.toString(),  colLocus, snptype,  listitemsdao.getOrganismByName(params.getOrganism()).getOrganismId());
//			}
//			else {
//				AppContext.resetTimer("getSNPsString start4");
//				snpsposlist  = snpstringallvarsmultirefsposDAO.getSNPs(chr.toString(), start.intValue(), end.intValue(),  snptype, listitemsdao.getOrganismByName(params.getOrganism()).getOrganismId());
//			}
//			//AppContext.debug("colvarids=" + colVarids.toString());
//		}
//		
//		if(snpsposlist==null) throw new RuntimeException("snpsposlist==null");
//		if(snpsposlist.isEmpty()) return new SNPsStringData();
//		
//		
//		if(params.includeDataset(SnpsAllvarsPosDAO.DATASET_SNPINDELV2_IUPAC)) {
//			if(params.isbCoreonly()) {
//				throw new RuntimeException("Only non-core " + SnpsAllvarsPosDAO.DATASET_SNPINDELV2_IUPAC + " is available for other reference genomes");
//			} else {
//				snptype=SnpsAllvarsPosDAO.TYPE_3KALLSNP_HDF5_V2;
//				//snpstrSnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrAllsnpsAllele2AllvarsDAO, "H5SNPUniAllele2V2DAO");
//			}
//		} else	throw new RuntimeException("Only non-core " + SnpsAllvarsPosDAO.DATASET_SNPINDELV2_IUPAC + " is available for other reference genomes");
//
//
//		snpstrSnpsAllele1AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrAllsnpsAllele1AllvarsDAO, "H5SNPUniIUPACV2DAO");
//		if(snpstrSnpsAllele1AllvarsDAO==null) throw new RuntimeException("snpstrSnpsAllele1AllvarsDAO==null");
//
//
//		AppContext.debug( "sorting out file-contig for " + snpsposlist.size() + " positions");
//		
//		// sort out which hdf5 files to read and ranges
//		Map<String,BigDecimal> mapFileContig2StartIdx=new LinkedHashMap();
//		Map<String,BigDecimal> mapFileContig2EndIdx=new LinkedHashMap();
//		//Map<String,Integer> mapFileContig2IdxCount=new HashMap();
//		Map<Integer, Object[]> mapPosidx2FileContigIdx=new LinkedHashMap();
//		Map<Integer, Object[]> mapPosidx2FileContigStartEndIdx=new LinkedHashMap();
//		//Map<Integer, BigDecimal[]> mapPosidx2FileContigStartEndIdx=new LinkedHashMap();
//		//Map<Integer, SnpsAllvarsMultirefsPos[]> mapPosidx2MultirefsPos=new LinkedHashMap();
//		Iterator<SnpsAllvarsPos> itPos=snpsposlist.iterator();
//		
//		int posidx=0;
//		SnpsAllvarsMultirefsPos lastpos = null;
//		int lastposidx=0;
//		int prevposidx=0;
//		SnpsAllvarsMultirefsPos prevpos=null;
//		String prevfilecontig=null;
//		boolean firstpos=true;
//
//		// get minimum start and maximum end column idx for each file-contig
//		while(itPos.hasNext()) {
//			SnpsAllvarsMultirefsPos ipos=(SnpsAllvarsMultirefsPos)itPos.next();
//			
//			if(firstpos) {
//				prevfilecontig=ipos.getFileId() + "-" + ipos.getContig() + "-" + lastposidx;
//				lastpos = ipos;
//				mapFileContig2StartIdx.put(prevfilecontig,  lastpos.getAlleleIndex());
//				AppContext.debug(posidx + ": min start for " + prevfilecontig + ": " +  lastpos.getAlleleIndex());
//				firstpos=false;
//			}
//			String curfilecontig=ipos.getFileId() + "-" + ipos.getContig() + "-" + posidx;  //+ "-" + posidx;
//			
//			if(  ((prevpos!=null && (!prevpos.getFileId().equals(ipos.getFileId()) ||  !prevpos.getContig().equals(ipos.getContig()) )) || !itPos.hasNext())  
//					||
//				(  prevpos!=null &&  prevpos.getFileId().equals(ipos.getFileId()) && prevpos.getContig().equals(ipos.getContig()) &&  (prevpos.getAlleleIndex().longValue()+1 != ipos.getAlleleIndex().longValue())  )					
//					) {
//
//				prevfilecontig=prevpos.getFileId() + "-" + prevpos.getContig() +  "-" + lastposidx ;
//				
//				if(!mapFileContig2EndIdx.containsKey(prevfilecontig)) { mapFileContig2EndIdx.put(prevfilecontig, prevpos.getAlleleIndex() );
//					AppContext.debug(posidx + ": max end for " + prevfilecontig + ": " +  prevpos.getAlleleIndex());
//				}
//				else {
//					if(!itPos.hasNext())  {
//						if(mapFileContig2EndIdx.get(curfilecontig).compareTo( ipos.getAlleleIndex()) <= 0) {
//							//AppContext.debug( "prevend<=curend for :" + curfilecontig + "  " + mapFileContig2EndIdx.get(curfilecontig) + "  "  +  ipos.getAlleleIndex() );
//							mapFileContig2EndIdx.put(curfilecontig, ipos.getAlleleIndex());
//							AppContext.debug(posidx + ": new max end for " + curfilecontig + ": " +  ipos.getAlleleIndex());
//						} //else 
//							//AppContext.debug( "prevend>curend for :" + curfilecontig + "  " + mapFileContig2EndIdx.get(curfilecontig) + "  "  +  ipos.getAlleleIndex() );
//						
//					} else {
//						if(mapFileContig2EndIdx.get(prevfilecontig).compareTo( prevpos.getAlleleIndex()) <= 0) {
//							//AppContext.debug( "prevend<=curend for :" + prevfilecontig + "  " + mapFileContig2EndIdx.get(prevfilecontig) + "  "  +  prevpos.getAlleleIndex() );
//							mapFileContig2EndIdx.put(prevfilecontig, prevpos.getAlleleIndex());
//							AppContext.debug(posidx + ": new max end for " + prevfilecontig + ": " +  prevpos.getAlleleIndex());
//	
//						} //else 
//						//	AppContext.debug( "prevend>curend for :" + prevfilecontig + "  " + mapFileContig2EndIdx.get(prevfilecontig) + "  "  +  prevpos.getAlleleIndex() );
//	
//					}
//				}
//				
//				if(itPos.hasNext()) {
//					if(!mapFileContig2StartIdx.containsKey(curfilecontig)) { mapFileContig2StartIdx.put(curfilecontig,  ipos.getAlleleIndex() );
//						AppContext.debug(posidx + ": min start for " + curfilecontig + ": " +  ipos.getAlleleIndex());
//					}
//						else {
//							if(mapFileContig2StartIdx.get(prevfilecontig).compareTo( prevpos.getAlleleIndex()) >= 0) {
//								//AppContext.debug( "prevstart>=curstart for :" + curfilecontig + "  " + mapFileContig2StartIdx.get(curfilecontig) + "  "  +  ipos.getAlleleIndex() );
//								mapFileContig2StartIdx.put(prevfilecontig, prevpos.getAlleleIndex());
//								AppContext.debug(posidx + ": new min start for " + prevfilecontig + ": " +  prevpos.getAlleleIndex());
//							} 
//							
//							/*
//								if(!itPos.hasNext()) {
//									if(mapFileContig2StartIdx.get(curfilecontig).compareTo( ipos.getAlleleIndex()) >= 0) {
//										//AppContext.debug( "prevstart>=curstart for :" + curfilecontig + "  " + mapFileContig2StartIdx.get(curfilecontig) + "  "  +  ipos.getAlleleIndex() );
//										mapFileContig2StartIdx.put(curfilecontig,  ipos.getAlleleIndex());
//										AppContext.debug(posidx + ": new min start for " + curfilecontig + ": " +  ipos.getAlleleIndex());
//									}// else 
//									//	AppContext.debug( "prevstart<curstart for :" + curfilecontig + "  " + mapFileContig2StartIdx.get(curfilecontig) + "  "  +  ipos.getAlleleIndex() );
//								}
//								else {
//									if(mapFileContig2StartIdx.get(prevfilecontig).compareTo( prevpos.getAlleleIndex()) >= 0) {
//										//AppContext.debug( "prevstart>=curstart for :" + curfilecontig + "  " + mapFileContig2StartIdx.get(curfilecontig) + "  "  +  ipos.getAlleleIndex() );
//										mapFileContig2StartIdx.put(prevfilecontig, prevpos.getAlleleIndex());
//										AppContext.debug(posidx + ": new min start for " + prevfilecontig + ": " +  prevpos.getAlleleIndex());
//									}// else 
//									//	AppContext.debug( "prevstart<curstart for :" + curfilecontig + "  " + mapFileContig2StartIdx.get(curfilecontig) + "  "  +  ipos.getAlleleIndex() );
//								}
//								*/
//						}
//				}
//				
//				
//				
//				lastpos =  ipos;
//				lastposidx = posidx;
//			} 
//	
//			prevposidx=posidx;
//			prevpos=ipos;
//			posidx++;			
//			
//		}
//
//		AppContext.debug("mapFileContig2StartIdx:" + mapFileContig2StartIdx.toString());
//		AppContext.debug("mapFileContig2EndIdx:" + mapFileContig2EndIdx.toString());
//
//		posidx=0;
//		lastpos = null;
//		lastposidx=0;
//		prevpos=null;
//		prevfilecontig=null;
//		firstpos=true;
//
//
//		itPos=snpsposlist.iterator();
//		
//		while(itPos.hasNext()) {
//			
//			SnpsAllvarsMultirefsPos ipos=(SnpsAllvarsMultirefsPos)itPos.next();
//			
//			String curfilecontig=ipos.getFileId() + "-" + ipos.getContig() + "-" + posidx; 
//			 if(mapFileContig2StartIdx.containsKey(curfilecontig) || mapFileContig2EndIdx.containsKey(curfilecontig)) {
//				mapPosidx2FileContigStartEndIdx.put(posidx , new Object[] {ipos.getFileId() ,  ipos.getContig() , mapFileContig2StartIdx.get(curfilecontig),  mapFileContig2EndIdx.get(curfilecontig), prevpos ,ipos});
//				/*
//				mapPosidx2FileContigStartEndIdx.put(posidx , new BigDecimal[] {lastpos.getFileId(), lastpos.getAlleleIndex(), ipos.getAlleleIndex(),
//						lastpos.getAlleleIndex().add(  mapFileContig2StartIdx.get(prevfilecontig).negate() ) , ipos.getAlleleIndex().add( mapFileContig2StartIdx.get(prevfilecontig).negate()),   });
//				mapPosidx2MultirefsPos.put(posidx, new  SnpsAllvarsMultirefsPos[] {lastpos, ipos} );
//				*/
//				
//				prevfilecontig=curfilecontig;
//			}
//			 prevpos=ipos;
//			 posidx++;
//		}
//				
//		
////		itPos=snpsposlist.iterator();
////		while(itPos.hasNext()) {
////			SnpsAllvarsMultirefsPos ipos=(SnpsAllvarsMultirefsPos)itPos.next();
////			
////			if(firstpos) {
////				prevfilecontig=ipos.getFileId() + "-" + ipos.getContig()+  "-" + lastposidx ; 
////				lastpos = ipos;
////				mapFileContig2StartIdx.put(prevfilecontig,  lastpos.getAlleleIndex());
////				firstpos=false;
////			}
////			
////			//String curfilecontig=ipos.getFileId() + "-" + ipos.getContig() + lastposidx ; // "-" + posidx;  // +  "-" + ipos ;
////			
////			String curfilecontig=ipos.getFileId() + "-" + ipos.getContig() + "-" + lastposidx; 
////			
////			// create substring for new contig or same contig with skipped column
////			//if(  (prevpos!=null &&   !prevpos.getContig().equals(ipos.getContig())) || !itPos.hasNext() ) {
////			if(  ((prevpos!=null && (!prevpos.getFileId().equals(ipos.getFileId()) ||  !prevpos.getContig().equals(ipos.getContig()) )) || !itPos.hasNext())
////					||
////				( prevpos!=null &&  prevpos.getFileId().equals(ipos.getFileId()) && prevpos.getContig().equals(ipos.getContig()) &&  (prevpos.getAlleleIndex().longValue()+1 != ipos.getAlleleIndex().longValue()))	
////					) {
////
////				prevfilecontig=prevpos.getFileId() + "-" + prevpos.getContig() +  "-" + lastposidx ; 
////				//prevfilecontig=prevpos.getFileId() + "-" + prevpos.getContig() +  "-" + lastposidx ;
////				
////				
////				//if(!mapFileContig2StartIdx.containsKey(curfilecontig)) mapFileContig2StartIdx.put(curfilecontig,  ipos.getAlleleIndex() );
////				
////				
////				//if(!mapFileContig2EndIdx.containsKey(prevfilecontig)) mapFileContig2EndIdx.put(prevfilecontig, prevpos.getAlleleIndex() );
////				//else {
////				//	if(!itPos.hasNext()) mapFileContig2EndIdx.put(curfilecontig, ipos.getAlleleIndex());
////				//	else mapFileContig2EndIdx.put(prevfilecontig, prevpos.getAlleleIndex());
////				//}
////				
////				
////				if(!itPos.hasNext()) {
////					mapPosidx2FileContigStartEndIdx.put(posidx , new BigDecimal[] {lastpos.getFileId(), lastpos.getAlleleIndex(), ipos.getAlleleIndex(),
////							lastpos.getAlleleIndex().add(  mapFileContig2StartIdx.get(prevfilecontig).negate() ) , ipos.getAlleleIndex().add( mapFileContig2StartIdx.get(prevfilecontig).negate()),   });
////					mapPosidx2MultirefsPos.put(posidx, new  SnpsAllvarsMultirefsPos[] {lastpos, ipos} );
////				}
////				else {
////					mapPosidx2FileContigStartEndIdx.put(lastposidx , new BigDecimal[] {lastpos.getFileId(), lastpos.getAlleleIndex(), prevpos.getAlleleIndex(),
////						lastpos.getAlleleIndex().add(  mapFileContig2StartIdx.get(prevfilecontig).negate() ) , prevpos.getAlleleIndex().add( mapFileContig2StartIdx.get(prevfilecontig).negate())});
////					mapPosidx2MultirefsPos.put(lastposidx, new  SnpsAllvarsMultirefsPos[] {lastpos, prevpos} );
////					lastpos =  ipos;
////					lastposidx = posidx;
////				}
////				//prevfilecontig=ipos.getFileId() + "-" + ipos.getContig() +  "-" + lastposidx ;
////			}
////			
////			/*
////			else if(  prevpos!=null &&  prevpos.getFileId().equals(ipos.getFileId()) && prevpos.getContig().equals(ipos.getContig()) &&  (prevpos.getAlleleIndex().longValue()+1 != ipos.getAlleleIndex().longValue())  ) {
////				
////				prevfilecontig=prevpos.getFileId() + "-" + prevpos.getContig() ;
////				
////				if(itPos.hasNext()) {
////					mapPosidx2FileContigStartEndIdx.put(lastposidx , new BigDecimal[] {lastpos.getFileId(), lastpos.getAlleleIndex(), prevpos.getAlleleIndex() ,
////							lastpos.getAlleleIndex().add(  mapFileContig2StartIdx.get(prevfilecontig).negate() ) , prevpos.getAlleleIndex().add( mapFileContig2StartIdx.get(prevfilecontig).negate())});
////					mapPosidx2MultirefsPos.put(lastposidx, new  SnpsAllvarsMultirefsPos[] {lastpos, prevpos} );
////
////				}
////				else {
////					mapPosidx2FileContigStartEndIdx.put(posidx , new BigDecimal[] {lastpos.getFileId(), lastpos.getAlleleIndex(), ipos.getAlleleIndex() ,
////							lastpos.getAlleleIndex().add(  mapFileContig2StartIdx.get(curfilecontig).negate() ) , ipos.getAlleleIndex().add( mapFileContig2StartIdx.get(curfilecontig).negate())});
////					mapPosidx2MultirefsPos.put(posidx, new  SnpsAllvarsMultirefsPos[] {lastpos, ipos} );
////				}
////				
////				lastpos =  ipos;
////				lastposidx = posidx;
////			}
////			*/
////			
////			try {
////			mapPosidx2FileContigIdx.put(posidx, new Object[] {ipos.getFileId(),  ipos.getContig(), ipos.getPosition(),
////					ipos.getAlleleIndex().add(mapFileContig2StartIdx.get(curfilecontig).negate()), curfilecontig } );
////			} catch(Exception ex) {
////				AppContext.debug( posidx + " , " + ipos.getContig() + ", " + ipos.getPosition() + ", " +  ipos.getFileId() + ", " + prevfilecontig + " , "  + mapFileContig2StartIdx.toString());
////				throw new RuntimeException(ex);
////			}
////
////			prevpos=ipos;
////			posidx++;
////		}
//		
//		if(snpstrSnpsAllele1AllvarsDAO==null) throw new RuntimeException("snpstrSnpsAllele1AllvarsDAO==null");
//
//
//		
//		StringBuffer strbuffMessage=new StringBuffer();
//		
//		//AppContext.debug("mapFileContig2IdxCount:" + mapFileContig2IdxCount.toString());
//		StringBuffer buff=new StringBuffer();
//		Iterator itPrintPos=mapPosidx2FileContigStartEndIdx.keySet().iterator();
//		while(itPrintPos.hasNext()) {
//			Object key = itPrintPos.next();
//			Object[] obj = mapPosidx2FileContigStartEndIdx.get(key);
//			buff.append(key + " :" );
//			for(int i=0; i<obj.length; i++) {
//				buff.append(obj[i] + ", " );
//			}
//			
////				prevpos=obj[1]; 
////				lastpos=obj[0];
////				
////				if(!lastpos.getContig().equals(prevpos.getContig())) throw new RuntimeException("!lastpos.getContig().equals(prevpos.getContig():" + lastpos.getContig() + "," + prevpos.getContig());
////				strbuffMessage.append(params.getOrganism() +"-" + lastpos.getContig() + "[" + lastpos.getPosition() + "-" + prevpos.getPosition() + "] aligned with ");
////
////				
////				StringBuffer noalignbuf=new StringBuffer();
////				if(!lastpos.getContigName(Organism.REFERENCE_NIPPONBARE).isEmpty()) {
////				if(!lastpos.getContigName(Organism.REFERENCE_NIPPONBARE).equals(prevpos.getContigName(Organism.REFERENCE_NIPPONBARE))) // throw new RuntimeException("REFERENCE_NIPPONBARE !lastpos.getContig().equals(prevpos.getContig():" + lastpos.getContigName(Organism.REFERENCE_NIPPONBARE) + "," + prevpos.getContigName(Organism.REFERENCE_NIPPONBARE));
////				strbuffMessage.append(Organism.REFERENCE_NIPPONBARE + "-[" + lastpos.getContigName(Organism.REFERENCE_NIPPONBARE) + " " + lastpos.getPosition(Organism.REFERENCE_NIPPONBARE) + "-" + prevpos.getContigName(Organism.REFERENCE_NIPPONBARE) + " " + prevpos.getPosition(Organism.REFERENCE_NIPPONBARE) + "];");
////				else strbuffMessage.append(Organism.REFERENCE_NIPPONBARE + "-" + lastpos.getContigName(Organism.REFERENCE_NIPPONBARE) + "[" + lastpos.getPosition(Organism.REFERENCE_NIPPONBARE) + "-" + prevpos.getPosition(Organism.REFERENCE_NIPPONBARE) + "];");
////				} else noalignbuf.append(Organism.REFERENCE_NIPPONBARE+",");
////
////				if(!lastpos.getContigName(Organism.REFERENCE_9311).isEmpty()) {
////				if(!lastpos.getContigName(Organism.REFERENCE_9311).equals(prevpos.getContigName(Organism.REFERENCE_9311))) //throw new RuntimeException("REFERENCE_9311 !lastpos.getContig().equals(prevpos.getContig():" + lastpos.getContigName(Organism.REFERENCE_9311) + "," + prevpos.getContigName(Organism.REFERENCE_9311));
////				strbuffMessage.append(Organism.REFERENCE_9311 + "-[" + lastpos.getContigName(Organism.REFERENCE_9311) + " " + lastpos.getPosition(Organism.REFERENCE_9311) + "-" + prevpos.getContigName(Organism.REFERENCE_9311) + " " + prevpos.getPosition(Organism.REFERENCE_9311) + "];");
////				else strbuffMessage.append(Organism.REFERENCE_9311 + "-" + lastpos.getContigName(Organism.REFERENCE_9311) + "[" + lastpos.getPosition(Organism.REFERENCE_9311) + "-" + prevpos.getPosition(Organism.REFERENCE_9311) + "];");
////				}else noalignbuf.append(Organism.REFERENCE_9311+",");
////
////				if(!lastpos.getContigName(Organism.REFERENCE_IR64).isEmpty()) {
////				if(!lastpos.getContigName(Organism.REFERENCE_IR64).equals(prevpos.getContigName(Organism.REFERENCE_IR64))) //throw new RuntimeException("REFERENCE_IR64 !lastpos.getContig().equals(prevpos.getContig():" + lastpos.getContigName(Organism.REFERENCE_IR64) + "," + prevpos.getContigName(Organism.REFERENCE_IR64));
////				strbuffMessage.append(Organism.REFERENCE_IR64 + "-[" + lastpos.getContigName(Organism.REFERENCE_IR64) + " " + lastpos.getPosition(Organism.REFERENCE_IR64) + "-" + prevpos.getContigName(Organism.REFERENCE_IR64) + " " + prevpos.getPosition(Organism.REFERENCE_IR64) + "];");
////				else strbuffMessage.append(Organism.REFERENCE_IR64 + "-" + lastpos.getContigName(Organism.REFERENCE_IR64) + "[" + lastpos.getPosition(Organism.REFERENCE_IR64) + "-" + prevpos.getPosition(Organism.REFERENCE_IR64) + "];");
////				}else noalignbuf.append(Organism.REFERENCE_IR64+",");
////
////				if(!lastpos.getContigName(Organism.REFERENCE_KASALATH).isEmpty()) {
////				if(!lastpos.getContigName(Organism.REFERENCE_KASALATH).equals(prevpos.getContigName(Organism.REFERENCE_KASALATH))) // throw new RuntimeException("REFERENCE_KASALATH !lastpos.getContig().equals(prevpos.getContig():" + lastpos.getContigName(Organism.REFERENCE_KASALATH) + "," + prevpos.getContigName(Organism.REFERENCE_KASALATH));
////				strbuffMessage.append(Organism.REFERENCE_KASALATH + "-[" + lastpos.getContigName(Organism.REFERENCE_KASALATH) + " " + lastpos.getPosition(Organism.REFERENCE_KASALATH) + "-" + prevpos.getContigName(Organism.REFERENCE_KASALATH) + " " + prevpos.getPosition(Organism.REFERENCE_KASALATH) + "];");
////				else strbuffMessage.append(Organism.REFERENCE_KASALATH + "-" + lastpos.getContigName(Organism.REFERENCE_KASALATH) + "[" + lastpos.getPosition(Organism.REFERENCE_KASALATH) + "-" + prevpos.getPosition(Organism.REFERENCE_KASALATH) + "];");
////				}else noalignbuf.append(Organism.REFERENCE_IR64+",");
////
////				if(!lastpos.getContigName(Organism.REFERENCE_DJ123).isEmpty()) {
////				if(!lastpos.getContigName(Organism.REFERENCE_DJ123).equals(prevpos.getContigName(Organism.REFERENCE_DJ123))) // throw new RuntimeException("REFERENCE_DJ123 !lastpos.getContig().equals(prevpos.getContig():" + lastpos.getContigName(Organism.REFERENCE_DJ123) + "," + prevpos.getContigName(Organism.REFERENCE_DJ123));
////				strbuffMessage.append(Organism.REFERENCE_DJ123 + "-[" + lastpos.getContigName(Organism.REFERENCE_DJ123) + " " + lastpos.getPosition(Organism.REFERENCE_DJ123) + "-" + prevpos.getContigName(Organism.REFERENCE_DJ123) + " " + prevpos.getPosition(Organism.REFERENCE_DJ123) + "];");
////				else strbuffMessage.append(Organism.REFERENCE_DJ123 + "-" + lastpos.getContigName(Organism.REFERENCE_DJ123) + "[" + lastpos.getPosition(Organism.REFERENCE_DJ123) + "-" + prevpos.getPosition(Organism.REFERENCE_DJ123) + "];");
////				}else noalignbuf.append(Organism.REFERENCE_IR64+",");
////				
////				if(noalignbuf.length()>0)
////					strbuffMessage.append(" No alignment with " + noalignbuf.substring(0,noalignbuf.length()-1));
////				strbuffMessage.append("\n");
////			}
//			
//			//buff.append(  Long.toString((mapFileContig2StartIdx.get(obj[0]+"-"+obj[1]+"-" + key ).longValue() + (Long)obj[3])));  
//			//buff.append(  Long.toString((mapFileContig2StartIdx.get(obj[0]+"-"+obj[1]+"-" + key ).longValue() + (Long)obj[3])));
//			buff.append("\n");
//		}
//		
//		//AppContext.debug("mapPosidx2FileContigIdx:\nfileId, fromContigId, fromPosition, filecontCount,  filecolidx, filecontig\t\t\tfile:colidx range[-]\tsnpstring:[-]\n" + buff);
//
//		
//		
//		// initialize mapVar2SnpStringBuffer
//		
//		Map<BigDecimal,StringBuffer>  mapVar2SnpStringBuffer=null;
//		
//		
//		
//		Map<String, Map<BigDecimal, String>> mapFileContig2MapVar2Snpstring=new HashMap();
//		itPos=snpsposlist.iterator();
//		/*
//		posidx=0;
//		prevfilecontig=null;
//		prevpos=0;
//		int lastPosIdx=0;
//		*/
//		
//		StringBuffer strRefbuff=new StringBuffer();
//		posidx=0;
//		lastpos = null;
//		prevpos=null;
//		prevfilecontig=null;
//		firstpos=true;
//		int lastPosIdx=0;
//		Map<BigDecimal,Position> mapSnpid2Pos = new HashMap();
//		
//		while(itPos.hasNext()) {
//		
//			SnpsAllvarsMultirefsPos ipos=(SnpsAllvarsMultirefsPos)itPos.next();
//			
//			mapSnpid2Pos.put( ipos.getSnpFeatureId(), ipos);
//			
//			strRefbuff.append( ipos.getRefnuc()  );
//			String curfilecontig=ipos.getFileId() + "-" + ipos.getContig()  +"-" + posidx ; // + "-" + ipos.getPosition();
//			
//			//mapPosidx2FileContigStartEndIdx.put(posidx , new BigDecimal[] {lastpos.getFileId(), lastpos.getAlleleIndex(), ipos.getAlleleIndex() ,
//			//		lastpos.getAlleleIndex().add(  mapFileContig2StartIdx.get(curfilecontig).negate() ) , ipos.getAlleleIndex().add( mapFileContig2StartIdx.get(curfilecontig).negate())});
//	
//			
//			// create substring for new contig or same contig with skipped column
//			//if(  (prevpos!=null &&  ( !prevpos.getContig().equals(ipos.getContig()) ||  (prevpos.getAlleleIndex().longValue()+1 != ipos.getAlleleIndex().longValue()))) || !itPos.hasNext() ) {
//			//if(  (prevpos!=null && (!prevpos.getFileId().equals(ipos.getFileId()) ||  !prevpos.getContig().equals(ipos.getContig()) )) || !itPos.hasNext() ) {
//			
//			if(mapPosidx2FileContigStartEndIdx.containsKey(posidx)) {
//								
//				//BigDecimal alleleidxranges[]= mapPosidx2FileContigStartEndIdx.get(posidx);
//				Map<BigDecimal,String>  mapVar2SnpString = mapFileContig2MapVar2Snpstring.get( curfilecontig );
//				//BigDecimal typeid=alleleidxranges[0];
//				BigDecimal typeid =  (BigDecimal)((Object[])mapPosidx2FileContigStartEndIdx.get(posidx))[0];
//				if(!typeid.equals(ipos.getFileId())) throw new RuntimeException("!typeid.equals(ipos.getFileId()) at " + ipos);
//				if(mapVar2SnpString==null) {
//				
//					H5Dataset hdf5dao = mapFileId2DatasetDAO.get( typeid );
//					if(hdf5dao==null) {
//						
//						if( typeid.equals( SnpsAllvarsMultirefsPosDAO.TYPE_3KALLSNP_HDF5_V2 ) ) {
//							nballsnpsdao= (H5Dataset)AppContext.checkBean(nballsnpsdao,"H5SNPUniIUPACV2DAO");
//							hdf5dao=nballsnpsdao;	
//						}
//						else if( typeid.equals( SnpsAllvarsMultirefsPosDAO.TYPE_3KCORESNP_HDF5_V2 ) ) {
//							nbcoresnpsdao= (H5Dataset)AppContext.checkBean(nbcoresnpsdao,"H5SNPCoreIUPACV2DAO");
//							hdf5dao=nbcoresnpsdao;	
//						} else
//							hdf5dao = new H5Dataset( SnpsAllvarsMultirefsPosDAO.MAP_TYPE2HDF5FILE.get(typeid ));
//						mapFileId2DatasetDAO.put(typeid, hdf5dao);
//					}
//					mapVar2SnpString = hdf5dao.readSNPString(null, mapFileContig2StartIdx.get(curfilecontig).intValue() , mapFileContig2EndIdx.get(curfilecontig).intValue());
//					mapFileContig2MapVar2Snpstring.put(curfilecontig , mapVar2SnpString);
//				}
//				
//				// initialize string buffer for all vars
//				if(mapVar2SnpStringBuffer==null) {
//					mapVar2SnpStringBuffer = new HashMap();
//					Iterator<BigDecimal> itVar = mapVar2SnpString.keySet().iterator();
//					while(itVar.hasNext()) {
//						mapVar2SnpStringBuffer.put( itVar.next(), new StringBuffer());
//					}
//				}
//				
//				//mapPosidx2FileContigStartEndIdx.put(posidx , new BigDecimal[] {lastpos.getFileId(), lastpos.getAlleleIndex(), ipos.getAlleleIndex() ,
//				//		lastpos.getAlleleIndex().add(  mapFileContig2StartIdx.get(curfilecontig).negate() ) , ipos.getAlleleIndex().add( mapFileContig2StartIdx.get(curfilecontig).negate())});
//	
//				int idxstart = alleleidxranges[3].intValue();
//				int idxend = alleleidxranges[4].intValue();
//				
//				AppContext.debug( "posidx=" + posidx + ": at lastPosIdx=" + lastpos +  " appending from "  + curfilecontig + "["  + idxstart+ "-" +idxend+ "]");
//			
//				Iterator<BigDecimal> itVar = mapVar2SnpString.keySet().iterator();
//				while(itVar.hasNext()) {
//					BigDecimal varid=itVar.next();
//					//{ipos.getFileId(),  ipos.getContig(),  mapFileContig2IdxCount.get(filecontig)} 
//					mapVar2SnpStringBuffer.get(varid).append( mapVar2SnpString.get(varid).substring(idxstart , idxend+1 ));
//					
//					if(!itVar.hasNext()) {
//						AppContext.debug("filling table: pos[" + alleleidxranges[1]+ "-" + alleleidxranges[2] + "]  file=" + alleleidxranges[0] + "  colidx=[" +idxstart+ "-" + idxend + "]"); 
//						AppContext.debug( "mapVar2SnpString.get(varid).length=" + mapVar2SnpString.get(varid).length());
//						AppContext.debug("mapVar2SnpStringBuffer length=" + mapVar2SnpStringBuffer.get(varid).length());
//						/*
//						if(posidx !=  mapVar2SnpStringBuffer.get(varid).length() && itPos.hasNext()) {
//							AppContext.debug("posidx !=  mapVar2SnpStringBuffer.get(varid).length(), posidx=" + posidx);
//							throw new RuntimeException("posidx !=  mapVar2SnpStringBuffer.get(varid).length(), posidx=" + (posidx));
//						}
//						*/
//					}
//				}				
//				lastpos =  ipos;
//				lastPosIdx=posidx;
//			}
//			
//			prevpos=ipos;
//			posidx++;
//		}				
//		
//		
////		while(itPos.hasNext()) {
////			
////			SnpsAllvarsMultirefsPos ipos=(SnpsAllvarsMultirefsPos)itPos.next();
////			
////			mapSnpid2Pos.put( ipos.getSnpFeatureId(), ipos);
////			
////			strRefbuff.append( ipos.getRefnuc()  );
////			String curfilecontig=ipos.getFileId() + "-" + ipos.getContig()  +"-" + posidx ; // + "-" + ipos.getPosition();
////			
////			//mapPosidx2FileContigStartEndIdx.put(posidx , new BigDecimal[] {lastpos.getFileId(), lastpos.getAlleleIndex(), ipos.getAlleleIndex() ,
////			//		lastpos.getAlleleIndex().add(  mapFileContig2StartIdx.get(curfilecontig).negate() ) , ipos.getAlleleIndex().add( mapFileContig2StartIdx.get(curfilecontig).negate())});
////
////			
////			// create substring for new contig or same contig with skipped column
////			//if(  (prevpos!=null &&  ( !prevpos.getContig().equals(ipos.getContig()) ||  (prevpos.getAlleleIndex().longValue()+1 != ipos.getAlleleIndex().longValue()))) || !itPos.hasNext() ) {
////			//if(  (prevpos!=null && (!prevpos.getFileId().equals(ipos.getFileId()) ||  !prevpos.getContig().equals(ipos.getContig()) )) || !itPos.hasNext() ) {
////			
////			if(mapPosidx2FileContigStartEndIdx.containsKey(posidx)) {
////								
////				BigDecimal alleleidxranges[]= mapPosidx2FileContigStartEndIdx.get(posidx);
////				Map<BigDecimal,String>  mapVar2SnpString = mapFileContig2MapVar2Snpstring.get( curfilecontig );
////				BigDecimal typeid=alleleidxranges[0];
////				if(!typeid.equals(ipos.getFileId())) throw new RuntimeException("!typeid.equals(ipos.getFileId()) at " + ipos);
////				if(mapVar2SnpString==null) {
////				
////					H5Dataset hdf5dao = mapFileId2DatasetDAO.get( typeid );
////					if(hdf5dao==null) {
////						
////						if( typeid.equals( SnpsAllvarsMultirefsPosDAO.TYPE_3KALLSNP_HDF5_V2 ) ) {
////							nballsnpsdao= (H5Dataset)AppContext.checkBean(nballsnpsdao,"H5SNPUniIUPACV2DAO");
////							hdf5dao=nballsnpsdao;	
////						}
////						else if( typeid.equals( SnpsAllvarsMultirefsPosDAO.TYPE_3KCORESNP_HDF5_V2 ) ) {
////							nbcoresnpsdao= (H5Dataset)AppContext.checkBean(nbcoresnpsdao,"H5SNPCoreIUPACV2DAO");
////							hdf5dao=nbcoresnpsdao;	
////						} else
////							hdf5dao = new H5Dataset( SnpsAllvarsMultirefsPosDAO.MAP_TYPE2HDF5FILE.get(typeid ));
////						mapFileId2DatasetDAO.put(typeid, hdf5dao);
////					}
////					mapVar2SnpString = hdf5dao.readSNPString(null, mapFileContig2StartIdx.get(curfilecontig).intValue() , mapFileContig2EndIdx.get(curfilecontig).intValue());
////					mapFileContig2MapVar2Snpstring.put(curfilecontig , mapVar2SnpString);
////				}
////				
////				// initialize string buffer for all vars
////				if(mapVar2SnpStringBuffer==null) {
////					mapVar2SnpStringBuffer = new HashMap();
////					Iterator<BigDecimal> itVar = mapVar2SnpString.keySet().iterator();
////					while(itVar.hasNext()) {
////						mapVar2SnpStringBuffer.put( itVar.next(), new StringBuffer());
////					}
////				}
////				
////				//mapPosidx2FileContigStartEndIdx.put(posidx , new BigDecimal[] {lastpos.getFileId(), lastpos.getAlleleIndex(), ipos.getAlleleIndex() ,
////				//		lastpos.getAlleleIndex().add(  mapFileContig2StartIdx.get(curfilecontig).negate() ) , ipos.getAlleleIndex().add( mapFileContig2StartIdx.get(curfilecontig).negate())});
////
////				int idxstart = alleleidxranges[3].intValue();
////				int idxend = alleleidxranges[4].intValue();
////				
////				AppContext.debug( "posidx=" + posidx + ": at lastPosIdx=" + lastpos +  " appending from "  + curfilecontig + "["  + idxstart+ "-" +idxend+ "]");
////			
////				Iterator<BigDecimal> itVar = mapVar2SnpString.keySet().iterator();
////				while(itVar.hasNext()) {
////					BigDecimal varid=itVar.next();
////					//{ipos.getFileId(),  ipos.getContig(),  mapFileContig2IdxCount.get(filecontig)} 
////					mapVar2SnpStringBuffer.get(varid).append( mapVar2SnpString.get(varid).substring(idxstart , idxend+1 ));
////					
////					if(!itVar.hasNext()) {
////						AppContext.debug("filling table: pos[" + alleleidxranges[1]+ "-" + alleleidxranges[2] + "]  file=" + alleleidxranges[0] + "  colidx=[" +idxstart+ "-" + idxend + "]"); 
////						AppContext.debug( "mapVar2SnpString.get(varid).length=" + mapVar2SnpString.get(varid).length());
////						AppContext.debug("mapVar2SnpStringBuffer length=" + mapVar2SnpStringBuffer.get(varid).length());
////						/*
////						if(posidx !=  mapVar2SnpStringBuffer.get(varid).length() && itPos.hasNext()) {
////							AppContext.debug("posidx !=  mapVar2SnpStringBuffer.get(varid).length(), posidx=" + posidx);
////							throw new RuntimeException("posidx !=  mapVar2SnpStringBuffer.get(varid).length(), posidx=" + (posidx));
////						}
////						*/
////					}
////				}				
////				lastpos =  ipos;
////				lastPosIdx=posidx;
////			}
////			
////			prevpos=ipos;
////			posidx++;
////		}		
//
//		
//		Map mapVarid2Snpsstr=new HashMap();
//		Iterator<BigDecimal> itVar=mapVar2SnpStringBuffer.keySet().iterator();
//		while(itVar.hasNext()) {
//			BigDecimal ivar=itVar.next();
//			mapVarid2Snpsstr.put(  ivar, mapVar2SnpStringBuffer.get(ivar).toString());
//		}
//		
//		// get allele column indices from start to end positions
//		SnpsAllvarsPos startpos =  snpsposlist.get(0);
//		SnpsAllvarsPos endpos =  snpsposlist.get( snpsposlist.size()-1 );
//
//		String strRef=strRefbuff.toString();
//		
//		int refLength=-1;
//		
//		AppContext.debug( snpsposlist.size() + " snpposlist, pos between " +startpos.getPosition() +  "-" + endpos.getPosition() + "  index between " + startpos.getAlleleIndex() + "-" + endpos.getAlleleIndex());
//		
//		
//				
//			Map mapVarid2Snpsstr_allele2=null;
//			Map<BigDecimal,Map> mapVarid2SnpsAllele2str = new HashMap();
//			
//				if (mapVarid2Snpsstr_allele2==null && params.includeDataset(SnpsAllvarsPosDAO.DATASET_SNPINDELV2_IUPAC)) {
//				
//				Map mapVarid2SnpsstrSplitIUPAC=new LinkedHashMap();
//				mapVarid2Snpsstr_allele2=new LinkedHashMap();
//				
//				Iterator itVarid = mapVarid2Snpsstr.keySet().iterator();
//				while(itVarid.hasNext()) {
//					BigDecimal varid= (BigDecimal)itVarid.next();
//
//					//if(params.includeDataset(SnpsAllvarsPosDAO.DATASET_SNPINDELV2)) {					
//						// revised for SNPv2 (allele2!=0 for allele1==allele2)
//						String allele1str = (String)mapVarid2Snpsstr.get( varid );
//						
//						//String allele2str = (String)mapVarid2Snpsstr_allele2.get( varid );
//						//if(allele1str.length()!=allele2str.length()) throw new RuntimeException( "allele1str.length()!=allele2str.length(): " + allele1str.length() + ", " + allele2str.length());
//						
//						StringBuffer buffNewStr=new StringBuffer();
//						StringBuffer buffNewStr2=new StringBuffer();
//						for(int iStr=0; iStr<allele1str.length(); iStr++) {
//							//char allele2i =allele2str.charAt(iStr);
//							char allele1i =allele1str.charAt(iStr);
//							String alleles12= AppContext.getNucsFromIUPAC(allele1i);
//							if(alleles12.length()==2) {
//								Map mapTableidx2Nuc = mapVarid2SnpsAllele2str.get( varid );
//								if(mapTableidx2Nuc==null) {
//									mapTableidx2Nuc = new HashMap();
//									mapVarid2SnpsAllele2str.put(varid , mapTableidx2Nuc);
//								}
//								mapTableidx2Nuc.put( snpsposlist.get(iStr), alleles12.charAt(1));
//								buffNewStr2.append(alleles12.charAt(1));
//							} else 
//								buffNewStr2.append(alleles12.charAt(0));
//							
//							buffNewStr.append(alleles12.charAt(0));
//						}
//						
//						mapVarid2SnpsstrSplitIUPAC.put( varid , buffNewStr.toString());
//						mapVarid2Snpsstr_allele2.put( varid , buffNewStr2.toString());
//						
//						if(!itVarid.hasNext()) AppContext.debug(varid + "  length=" +  buffNewStr.length() + ", " + buffNewStr2.length());
//						
////						if(!allele1str.equals(buffNewStr.toString())) {
////							AppContext.debug( "varid=" + varid);
////							AppContext.debug( "alleleIUPACstr=" + allele1str);
////							AppContext.debug( "allele1    str=" + buffNewStr);
////							AppContext.debug( "allele2    str=" + buffNewStr2);
////						}
//					//}
//				}
//				
//				mapVarid2Snpsstr = mapVarid2SnpsstrSplitIUPAC;
//				
//				
//			} // else throw new RuntimeException("heteroSnps==null and mapVarid2Snpsstr_allele2==null ... no allele2 data");
//			
//			AppContext.debug("snpsposlist length=" + snpsposlist.size() );  
//				
//			// generate pos2alleles
//
//			Map<Position, Set<Character>> mapPos2Allele=new TreeMap();
//			Map<Position, Set<Character>> mapPos2AlleleHetero=new TreeMap();
//			
//			itVar=mapVarid2Snpsstr.keySet().iterator();
//			while(itVar.hasNext()) {
//				BigDecimal varid=itVar.next();
//				String allele1str = (String)mapVarid2Snpsstr.get( varid );
//				String allele2str = (String)mapVarid2Snpsstr_allele2.get( varid );
//				Iterator<SnpsAllvarsPos> itSnppos =snpsposlist.iterator();
//				int icount=0;
//				while(itSnppos.hasNext()) {
//					SnpsAllvarsPos snppos=itSnppos.next();
//					
//					Set setAlleles=mapPos2Allele.get(snppos);
//					if(setAlleles==null) {
//						setAlleles=new HashSet();
//						mapPos2Allele.put(snppos, setAlleles);
//					}
//					// hdf5 format v2
//					char chari1=allele1str.charAt(icount);
//					if(chari1!='0' && chari1!='?') setAlleles.add(chari1);
//					char chari2=allele2str.charAt(icount);
//					if(chari2!='0' && chari2!='?' ) setAlleles.add(chari2);
//					
//					if(chari1!=chari2) {
//						if(chari1!=snppos.getRefnuc().charAt(0)) {
//							Set allele2= mapPos2AlleleHetero.get(snppos);
//							if(allele2==null) {
//								allele2=new HashSet();
//								mapPos2AlleleHetero.put(snppos,  allele2);
//							}
//							allele2.add(chari1);
//						}
//						if(chari2!=snppos.getRefnuc().charAt(0)) {
//							Set allele2= mapPos2AlleleHetero.get(snppos);
//							if(allele2==null) {
//								allele2=new HashSet();
//								mapPos2AlleleHetero.put(snppos,  allele2);
//							}
//							allele2.add(chari2);
//						}
//					}
//					icount++;
//				}
//			}
//			
//			 //mapPos2NonsynAlleles, mapPos2SynAlleles, setSnpInExonPos, setSpliceDonorsPos,  setSpliceAcceptorsPos,
//			Set setSpliceAcceptorsPos = new HashSet();
//			Set setSpliceDonorsPos = new HashSet();
//			Map<Position,Set<Character>> mapPos2NonsynAlleles = new TreeMap();
//			Map<Position,Set<Character>> mapPos2SynAlleles = new TreeMap();
//			Set<Position> setSnpInExonPos = new TreeSet();
//				
//			
//			if(params.getOrganism().equals(AppContext.getDefaultOrganism())) {
//	
//					snpsnonsynDAO = (SnpsNonsynAllvarsDAO) AppContext.checkBean(snpsnonsynDAO, "VSnpNonsynallelePosDAO");
//					snpssynDAO = (SnpsSynAllvarsDAO) AppContext.checkBean(snpssynDAO, "VSnpSynalleleDAO");
//					//snpsinexonDAO = (SnpsInExonDAO) AppContext.checkBean(snpsinexonDAO, "SnpsInExonDAO");
//					//snpsheteroDAO = (SnpsHeteroAllvarsDAO)AppContext.checkBean(snpsheteroDAO, "SnpsHeteroAllvarsDAO");
//	
//					
//				
//					//Set heteroSnps = null;
//					Set nonsynAllele = null;
//					Set synAllele = null;
//					//Set inexonSnps = null;
//					
//					// using hdf5
//					if(  (setPositions!=null && !setPositions.isEmpty())  || (colLocus!=null && !colLocus.isEmpty()) ) {
//						
//						
//						if(setPositions!=null && !setPositions.isEmpty()) {
//							nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByChrPosIn(chr, listpos, snptype);
//							synAllele = snpssynDAO.findSnpSynAlleleByChrPosIn(chr, listpos, snptype);
//						} 
//						else if(colLocus!=null && !colLocus.isEmpty()) {
//							nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByChrPosIn(chr, colLocus, snptype);
//							synAllele = snpssynDAO.findSnpSynAlleleByChrPosIn(chr, colLocus, snptype);
//						} 
//						AppContext.resetTimer("to read nonsynonymous allele, inexon  from  database..");
//					}
//					else {
//						nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByChrPosBetween(chr, startpos.getPosition().intValue(), endpos.getPosition().intValue(), snptype);
//						synAllele = snpssynDAO.findSnpSynAlleleByChrPosBetween(chr, startpos.getPosition().intValue(), endpos.getPosition().intValue(), snptype);
//						AppContext.resetTimer("to read nonsynonymous allele, inexon  from  database..");
//					}			
//				
//
//						
//				// non-synonymous alleles for positions
//				setSnpInExonPos = new TreeSet();
//				
//				
//				Iterator<SnpsNonsynAllele> itNonsyn = nonsynAllele.iterator();
//				while(itNonsyn.hasNext()) {
//					SnpsNonsynAllele nonsynallele = itNonsyn.next();
//					
//					if(mapSnpid2Pos==null) throw new RuntimeException("mapSnpid2Pos==null");
//					if(nonsynallele==null) throw new RuntimeException("nonsynallele==null");
//					if(nonsynallele.getSnp()==null) throw new RuntimeException("nonsynallele.getSnp()==null");
//					
//					Position pos = mapSnpid2Pos.get( nonsynallele.getSnp() );
//					
//					// assuming all nonsyn are in exon
//					setSnpInExonPos.add( pos );
//					
//					Set<Character> setVarietyAlleles = mapPos2Allele.get(pos);
//					if(setVarietyAlleles==null) throw new RuntimeException("setVarietyAlleles==null");
//					if(nonsynallele.getAllele()=='\0') throw new RuntimeException("nonsynallele.getAllele()==null");
//	
//					if(setVarietyAlleles.contains( nonsynallele.getAllele()) ) {
//	
//						Set<Character> alleles = mapPos2NonsynAlleles.get(pos);
//						if(alleles==null) {
//							alleles = new HashSet();
//							mapPos2NonsynAlleles.put( pos , alleles);
//						}
//						alleles.add( nonsynallele.getAllele() );
//					}
//				}
//				
//				Iterator<SnpsSynAllele> itSyn = synAllele.iterator();
//				while(itSyn.hasNext()) {
//					SnpsSynAllele synallele = itSyn.next();
//					Position pos = mapSnpid2Pos.get( synallele.getSnp() );
//					
//					// assuming all syn are in exon
//					setSnpInExonPos.add( pos );
//					
//					Set<Character> setVarietyAlleles = mapPos2Allele.get(pos);
//					
//					if(setVarietyAlleles.contains( synallele.getAllele()) ) {
//						Set<Character> alleles = mapPos2SynAlleles.get(pos);
//						if(alleles==null) {
//							alleles = new HashSet();
//							mapPos2SynAlleles.put( pos , alleles);
//						}
//						alleles.add( synallele.getAllele() );
//					}
//				}
//			
//				if(nonsynAllele!=null) AppContext.debug( nonsynAllele.size() + " non-synonymous alleles, " + mapPos2NonsynAlleles.size() + " positions/idx,  " +  mapPos2NonsynAlleles.size() + "  nonsys alleles positions");
//				
//				
//				 
//				// get splice variants
//	
//				Set setSpliceAcceptors= new HashSet();
//				Set setSpliceDonors= new HashSet();
//				
//				snpsspliceacceptorDAO = (SnpsSpliceAcceptorDAO)AppContext.checkBean(snpsspliceacceptorDAO, "SnpsSpliceAcceptorDAO");
//				snpssplicedonorDAO = (SnpsSpliceDonorDAO)AppContext.checkBean(snpssplicedonorDAO, "SnpsSpliceDonorDAO");
//				if(setPositions!=null && !setPositions.isEmpty()) {
//					setSpliceAcceptors = snpsspliceacceptorDAO.getSNPsIn(chr, setPositions);
//					setSpliceDonors = snpssplicedonorDAO.getSNPsIn(chr, setPositions);
//				}
//				else if(colLocus!=null && !colLocus.isEmpty()) {
//						setSpliceAcceptors = snpsspliceacceptorDAO.getSNPsIn(chr, colLocus);
//						setSpliceDonors = snpssplicedonorDAO.getSNPsIn(chr, colLocus);							
//				} else {
//					setSpliceAcceptors = snpsspliceacceptorDAO.getSNPsBetween(chr, start.intValue(), end.intValue());  
//					setSpliceDonors = snpssplicedonorDAO.getSNPsBetween(chr, start.intValue(), end.intValue());							
//				}
//				
//				Iterator<SnpsSpliceAcceptor>  itAcceptors = setSpliceAcceptors.iterator();
//				while(itAcceptors.hasNext()) {
//					SnpsSpliceAcceptor acc = itAcceptors.next();
//					setSpliceAcceptorsPos.add( acc.getPos());
//				}
//				Iterator<SnpsSpliceDonor>  itDonor = setSpliceDonors.iterator();
//				while(itDonor.hasNext()) {
//					SnpsSpliceDonor acc = itDonor.next();
//					setSpliceDonorsPos.add( acc.getPos());
//				}
//					
//				
//				if(AppContext.isLocalhost()) {
//					AppContext.debug("mapPos2NonsynAlleles " + mapPos2NonsynAlleles.size() +": " + mapPos2NonsynAlleles.toString() );
//					AppContext.debug("mapPos2SynAlleles " + mapPos2SynAlleles.size() + ": " + mapPos2SynAlleles.toString() );
//					AppContext.debug("mapPos2Allele " + mapPos2Allele.size() + ": " + mapPos2Allele.toString() );
//					AppContext.debug("mapPos2AlleleHetero " + mapPos2AlleleHetero.size() +": " + mapPos2AlleleHetero.toString() );
//					AppContext.debug("setSpliceDonorsPos " + setSpliceDonorsPos.size() +": " + setSpliceDonorsPos.toString() );
//					AppContext.debug("setSpliceAcceptorsPos " + setSpliceAcceptorsPos.size() +": " + setSpliceAcceptorsPos.toString() );
//					//AppContext.debug( nonsynAllele.toString() );
//				}
//			
//			}
//			
//			SNPsStringData snpstrdata = new  SNPsStringData(snpsposlist,  strRef, mapVarid2Snpsstr, mapVarid2SnpsAllele2str,
//					mapPos2Allele, mapPos2NonsynAlleles, mapPos2SynAlleles, setSnpInExonPos, setSpliceDonorsPos,  setSpliceAcceptorsPos, strbuffMessage.toString());
//			
//			
//			
//			return snpstrdata;
//	}
//	
//	
//
//	/**
//	 * query SNPsStringData for other (not NB) reference genomes
//	 * @param params
//	 * @param colVarids
//	 * @param chr
//	 * @param start
//	 * @param end
//	 * @param setPositions
//	 * @param colLocus
//	 * @return
//	 */
//	private SNPsStringData getSNPsStringDataOtherRefsOld(GenotypeQueryParams params) {
//		// TODO Auto-generated method stub
//
//		AppContext.debug("executing...getSNPsStringDataOtherRefs");
//		
//		Collection colVarids=params.getColVarIds();
//		String chr= params.getsChr();
//		BigDecimal start= BigDecimal.valueOf(params.getlStart());
//		BigDecimal end= BigDecimal.valueOf(params.getlEnd()); 
//		Collection setPositions = params.getPoslist();
//		Collection colLocus = params.getColLoci();
//			
//		
//		//snpstringallvarsposDAO = (SnpsAllvarsPosDAO)AppContext.checkBean(snpstringallvarsposDAO, "VSnpRefposindexDAO") ;
//		snpstringallvarsposDAO = (SnpsAllvarsPosDAO)AppContext.checkBean(snpstringallvarsposDAO, "VConvertposAny2allrefsDAO") ;
//		
//		SnpsAllvarsMultirefsPosDAO snpstringallvarsmultirefsposDAO = (SnpsAllvarsMultirefsPosDAO)snpstringallvarsposDAO;
//		
//		SnpsStringDAO snpstrSnpsAllele1AllvarsDAO=null;
//		SnpsStringDAO snpstrSnpsAllele2AllvarsDAO=null;
//		BigDecimal snptype=snptype=SnpsAllvarsPosDAO.TYPE_3KALLSNP_HDF5_V2; 
//		
//		// get SNP positions and HDF5 column index mapping
//		// depends on dataset, version, core or all
//		/*
//		if(params.includeDataset(SnpsAllvarsPosDAO.DATASET_SNPINDELV1)) {
//			throw new RuntimeException(SnpsAllvarsPosDAO.DATASET_SNPINDELV1 + " not available from other reference genomes");
//		} else if(params.includeDataset(SnpsAllvarsPosDAO.DATASET_SNPINDELV2)) {
//			if(params.isbCoreonly()) {
//				snpstrSnpsAllele1AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrCoresnpsAllele1AllvarsDAO, "H5SNPCoreAllele1V2DAO");
//				snpstrSnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrCoresnpsAllele1AllvarsDAO, "H5SNPCoreAllele2V2DAO");
//				snptype=SnpsAllvarsPosDAO.TYPE_3KCORESNP_HDF5_V2;
//			} else {
//				snptype=SnpsAllvarsPosDAO.TYPE_3KALLSNP_HDF5_V2;
//				snpstrSnpsAllele1AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrAllsnpsAllele1AllvarsDAO, "H5SNPUniAllele1V2DAO");
//				snpstrSnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrAllsnpsAllele2AllvarsDAO, "H5SNPUniAllele2V2DAO");
//			}
//			
//		} else if(params.includeDataset(SnpsAllvarsPosDAO.DATASET_SNPINDELV2_IUPAC)) {
//			if(params.isbCoreonly()) {
//				snpstrSnpsAllele1AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrCoresnpsAllele1AllvarsDAO, "H5SNPCoreIUPACV2DAO");
//				//snpstrSnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrCoresnpsAllele1AllvarsDAO, "H5SNPCoreAllele2V2DAO");
//				snptype=SnpsAllvarsPosDAO.TYPE_3KCORESNP_HDF5_V2;
//			} else {
//				snptype=SnpsAllvarsPosDAO.TYPE_3KALLSNP_HDF5_V2;
//				snpstrSnpsAllele1AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrAllsnpsAllele1AllvarsDAO, "H5SNPUniIUPACV2DAO");
//				//snpstrSnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrAllsnpsAllele2AllvarsDAO, "H5SNPUniAllele2V2DAO");
//			}
//		}
//		*/
//		
//		
//		List<SnpsAllvarsPos> snpsposlist = null;
//		List listpos = null;
//		if(colVarids==null || colVarids.isEmpty()) {
//			if( (setPositions!=null && !setPositions.isEmpty()) ) {
//				listpos = new ArrayList();
//				listpos.addAll(new TreeSet(setPositions));
//				AppContext.resetTimer("getSNPsString start1");
//				snpsposlist  = snpstringallvarsmultirefsposDAO.getSNPsInChromosome(chr.toString(),  listpos, snptype, listitemsdao.getOrganismByName(params.getOrganism()).getOrganismId());
//			}
//			else if( (colLocus!=null && !colLocus.isEmpty()) ) {
//				AppContext.resetTimer("getSNPsString start1b");
//				snpsposlist  = snpstringallvarsmultirefsposDAO.getSNPsInChromosome(chr.toString(),  colLocus, snptype, listitemsdao.getOrganismByName(params.getOrganism()).getOrganismId());
//			}
//			else {
//				AppContext.resetTimer("getSNPsString start2");
//				snpsposlist  = snpstringallvarsmultirefsposDAO.getSNPs(chr.toString(), start.intValue(), end.intValue(),   snptype,  listitemsdao.getOrganismByName(params.getOrganism()).getOrganismId());
//			}
//		} else {
//			if(setPositions!=null && !setPositions.isEmpty()) {
//				listpos = new ArrayList();
//				listpos.addAll(new TreeSet(setPositions));
//				AppContext.resetTimer("getSNPsString start3");
//				snpsposlist  = snpstringallvarsmultirefsposDAO.getSNPsInChromosome( chr.toString(),  listpos, snptype,  listitemsdao.getOrganismByName(params.getOrganism()).getOrganismId());
//			}
//			else if(colLocus!=null && !colLocus.isEmpty()) {
//				AppContext.resetTimer("getSNPsString start3b");
//				snpsposlist  = snpstringallvarsmultirefsposDAO.getSNPsInChromosome( chr.toString(),  colLocus, snptype,  listitemsdao.getOrganismByName(params.getOrganism()).getOrganismId());
//			}
//			else {
//				AppContext.resetTimer("getSNPsString start4");
//				snpsposlist  = snpstringallvarsmultirefsposDAO.getSNPs(chr.toString(), start.intValue(), end.intValue(),  snptype, listitemsdao.getOrganismByName(params.getOrganism()).getOrganismId());
//			}
//			//AppContext.debug("colvarids=" + colVarids.toString());
//		}
//		
//		if(snpsposlist==null) throw new RuntimeException("snpsposlist==null");
//		if(snpsposlist.isEmpty()) return new SNPsStringData();
//		
//		
//		if(params.includeDataset(SnpsAllvarsPosDAO.DATASET_SNPINDELV2_IUPAC)) {
//			if(params.isbCoreonly()) {
//				throw new RuntimeException("Only non-core " + SnpsAllvarsPosDAO.DATASET_SNPINDELV2_IUPAC + " is available for other reference genomes");
//			} else {
//				snptype=SnpsAllvarsPosDAO.TYPE_3KALLSNP_HDF5_V2;
//				//snpstrSnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrAllsnpsAllele2AllvarsDAO, "H5SNPUniAllele2V2DAO");
//			}
//		} else	throw new RuntimeException("Only non-core " + SnpsAllvarsPosDAO.DATASET_SNPINDELV2_IUPAC + " is available for other reference genomes");
//
//
//		snpstrSnpsAllele1AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrAllsnpsAllele1AllvarsDAO, "H5SNPUniIUPACV2DAO");
//		if(snpstrSnpsAllele1AllvarsDAO==null) throw new RuntimeException("snpstrSnpsAllele1AllvarsDAO==null");
//
//
//		AppContext.debug( "sorting out file-contig for " + snpsposlist.size() + " positions");
//		
//		// sort out which hdf5 files to read and ranges
//		Map<String,Long> mapFileContig2StartIdx=new HashMap();
//		Map<String,Long> mapFileContig2EndIdx=new HashMap();
//		//Map<String,Integer> mapFileContig2IdxCount=new HashMap();
//		Map<Integer, Object[]> mapPosidx2FileContigIdx=new LinkedHashMap();
//		Iterator<SnpsAllvarsPos> itPos=snpsposlist.iterator();
//		int posidx=0;
//		SnpsAllvarsMultirefsPos lastpos = null;
//		SnpsAllvarsMultirefsPos prevpos=null;
//		String prevfilecontig=null;
//		boolean firstpos=true;
//		while(itPos.hasNext()) {
//			SnpsAllvarsMultirefsPos ipos=(SnpsAllvarsMultirefsPos)itPos.next();
//			
//			if(firstpos) {
//				prevfilecontig=ipos.getFileId() + "-" + ipos.getContig() + "-" + ipos.getPosition();
//				lastpos = ipos;
//				mapFileContig2StartIdx.put(prevfilecontig,  lastpos.getAlleleIndex().longValue() );
//				firstpos=false;
//			}
//			
//			
//			// create substring for new contig or same contig with skipped column
//			if(  (prevpos!=null &&  ( !prevpos.getContig().equals(ipos.getContig()) ||  (prevpos.getAlleleIndex().longValue()+1 != ipos.getAlleleIndex().longValue()))) || !itPos.hasNext() ) {
//
//				prevfilecontig=prevpos.getFileId() + "-" + prevpos.getContig() + "-" + lastpos.getPosition();
//				
//				if(!mapFileContig2StartIdx.containsKey(prevfilecontig)) mapFileContig2StartIdx.put(prevfilecontig,  lastpos.getAlleleIndex().longValue() );
//				//else 
//				//	throw new RuntimeException( "mapFileContig2StartIdx already contained " + prevfilecontig );
//				
//				if(!mapFileContig2EndIdx.containsKey(prevfilecontig)) mapFileContig2EndIdx.put(prevfilecontig, prevpos.getAlleleIndex().longValue());
//				else {
//					throw new RuntimeException( "mapFileContig2EndIdx already contained " + prevfilecontig );
//					
//					/*
//					Long prevend = mapFileContig2EndIdx.get(filecontig);
//					if(prevend.longValue()>=ipos.getAlleleIndex().longValue()) {
//						scaffolddao = (ScaffoldDAO)AppContext.checkBean(scaffolddao, "ScaffoldDAO");
//						
//						AppContext.debug("filecontig=" + filecontig + " prevend=" + prevend + "   from_contig_id=" +  scaffolddao.getScaffold(ipos.getContig() , ipos.getOrganism()));
//						AppContext.debug(ipos.toString());
//						AppContext.debug("mapFileContig2StartIdx:" + mapFileContig2StartIdx.toString());
//						AppContext.debug("mapFileContig2EndIdx:" + mapFileContig2EndIdx.toString());
//						
//						throw new RuntimeException( "prevend " + prevend + ">= curend" + ipos.getAlleleIndex());
//					}
//					*/
//					//mapFileContig2EndIdx.put(prevfilecontig, ipos.getAlleleIndex().longValue());
//				}
//				
//				/*
//				if(mapFileContig2IdxCount.containsKey(filecontig)) {
//					mapFileContig2IdxCount.put(filecontig, mapFileContig2IdxCount.get(filecontig)+1);
//				} else mapFileContig2IdxCount.put(filecontig, 0);
//				mapPosidx2FileContigIdx.put(posidx, new Object[] {ipos.getFileId(),  ipos.getContig(), ipos.getPosition(),   mapFileContig2IdxCount.get(filecontig)} );
//				*/
//				
//				lastpos =  ipos;
//			}
//			mapPosidx2FileContigIdx.put(posidx, new Object[] {ipos.getFileId(),  ipos.getContig(), ipos.getPosition(),
//					ipos.getAlleleIndex().intValue()- mapFileContig2StartIdx.get(prevfilecontig), prevfilecontig } );
//
//
//			prevpos=ipos;
//			posidx++;
//		}
//		
//		if(snpstrSnpsAllele1AllvarsDAO==null) throw new RuntimeException("snpstrSnpsAllele1AllvarsDAO==null");
//
//		AppContext.debug("mapFileContig2StartIdx:" + mapFileContig2StartIdx.toString());
//		AppContext.debug("mapFileContig2EndIdx:" + mapFileContig2EndIdx.toString());
//		//AppContext.debug("mapFileContig2IdxCount:" + mapFileContig2IdxCount.toString());
//		StringBuffer buff=new StringBuffer();
//		Iterator itPrintPos=mapPosidx2FileContigIdx.keySet().iterator();
//		while(itPrintPos.hasNext()) {
//			Object key = itPrintPos.next();
//			Object[] obj = mapPosidx2FileContigIdx.get(key);
//			buff.append(key + " :" );
//			for(int i=0; i<obj.length; i++) {
//				buff.append(obj[i] + ", " );
//			}
//			buff.append(  Long.toString( (mapFileContig2StartIdx.get( obj[4] ).longValue() + (Long)obj[3])));
//			
//			//buff.append(  Long.toString((mapFileContig2StartIdx.get(obj[0]+"-"+obj[1]+"-" + key ).longValue() + (Long)obj[3])));  
//			//buff.append(  Long.toString((mapFileContig2StartIdx.get(obj[0]+"-"+obj[1]+"-" + key ).longValue() + (Long)obj[3])));
//			buff.append("\n");
//		}
//		
//		AppContext.debug("mapPosidx2FileContigIdx:\nfileId, fromContigId, fromPosition, filecontCount,  filecolidx, filecontig\n" + buff);
//
//		
//		
//		// initialize mapVar2SnpStringBuffer
//		
//		Map<BigDecimal,StringBuffer>  mapVar2SnpStringBuffer=null;
//		
//		
//		
//		Map<String, Map<BigDecimal, String>> mapFileContig2MapVar2Snpstring=new HashMap();
//		itPos=snpsposlist.iterator();
//		/*
//		posidx=0;
//		prevfilecontig=null;
//		prevpos=0;
//		int lastPosIdx=0;
//		*/
//		
//		StringBuffer strRefbuff=new StringBuffer();
//		posidx=0;
//		lastpos = null;
//		prevpos=null;
//		prevfilecontig=null;
//		firstpos=true;
//		int lastPosIdx=0;
//		while(itPos.hasNext()) {
//			
//			SnpsAllvarsMultirefsPos ipos=(SnpsAllvarsMultirefsPos)itPos.next();
//			
//			if(firstpos) {
//				prevfilecontig=ipos.getFileId() + "-" + ipos.getContig() + "-" + ipos.getPosition();
//				lastpos=ipos;
//				firstpos=false;
//			}
//			
//			strRefbuff.append( ipos.getRefnuc()  );
//			
//			
//			// create substring for new contig or same contig with skipped column
//			if(  (prevpos!=null &&  ( !prevpos.getContig().equals(ipos.getContig()) ||  (prevpos.getAlleleIndex().longValue()+1 != ipos.getAlleleIndex().longValue()))) || !itPos.hasNext() ) {
//
//				prevfilecontig=prevpos.getFileId() + "-" + prevpos.getContig() + "-" + lastpos.getPosition();
//				
//				Map<BigDecimal,String>  mapVar2SnpString = mapFileContig2MapVar2Snpstring.get( prevfilecontig );
//				if(mapVar2SnpString==null) {
//				
//					H5Dataset hdf5dao = mapFileId2DatasetDAO.get( prevfilecontig.split("\\-")[0]);
//					if(hdf5dao==null) {
//						
//						if( BigDecimal.valueOf(Long.valueOf(prevfilecontig.split("\\-")[0])).equals( SnpsAllvarsMultirefsPosDAO.TYPE_3KALLSNP_HDF5_V2 ) ) {
//							nballsnpsdao= (H5Dataset)AppContext.checkBean(nballsnpsdao,"H5SNPUniIUPACV2DAO");
//							hdf5dao=nballsnpsdao;	
//						}
//						else if( BigDecimal.valueOf(Long.valueOf(prevfilecontig.split("\\-")[0])).equals( SnpsAllvarsMultirefsPosDAO.TYPE_3KCORESNP_HDF5_V2 ) ) {
//							nbcoresnpsdao= (H5Dataset)AppContext.checkBean(nbcoresnpsdao,"H5SNPCoreIUPACV2DAO");
//							hdf5dao=nbcoresnpsdao;	
//						} else
//							hdf5dao = new H5Dataset( SnpsAllvarsMultirefsPosDAO.MAP_TYPE2HDF5FILE.get(  BigDecimal.valueOf(Long.valueOf(prevfilecontig.split("\\-")[0]))));
//						mapFileId2DatasetDAO.put( BigDecimal.valueOf(Long.valueOf( prevfilecontig.split("\\-")[0])), hdf5dao);
//					}
//					mapVar2SnpString = hdf5dao.readSNPString(null, mapFileContig2StartIdx.get(prevfilecontig).intValue() , mapFileContig2EndIdx.get(prevfilecontig).intValue());
//					mapFileContig2MapVar2Snpstring.put(prevfilecontig , mapVar2SnpString);
//				}
//				
//				// initialize string buffer for all vars
//				if(mapVar2SnpStringBuffer==null) {
//					mapVar2SnpStringBuffer = new HashMap();
//					Iterator<BigDecimal> itVar = mapVar2SnpString.keySet().iterator();
//					while(itVar.hasNext()) {
//						mapVar2SnpStringBuffer.put( itVar.next(), new StringBuffer());
//					}
//				}
//				Object[] filecontigidx=mapPosidx2FileContigIdx.get(posidx-1);
//				
//				// indx of position wrt filecontig
//				int fileidx=((Long)filecontigidx[3]).intValue();
//				int fileidxend=fileidx;
//				fileidxend++;
//				if(!itPos.hasNext()) {
//					fileidxend++;
//				}
//				
//				
//				//AppContext.debug( "posidx=" + posidx + ": at lastPosIdx=" + lastPosIdx +  " appending from "  + prevfilecontig + "["  + Integer.toString(fileidx-(posidx-1-lastPosIdx)) + "-" + Integer.toString(fileidxend) + "]");
//				AppContext.debug( "posidx=" + posidx + ": at lastPosIdx=" + lastpos +  " appending from "  + prevfilecontig + "["  + Long.toString(fileidx-(posidx-1-lastPosIdx)) + "-" + Integer.toString(fileidxend) + "]");
//				
//				Iterator<BigDecimal> itVar = mapVar2SnpString.keySet().iterator();
//				while(itVar.hasNext()) {
//					BigDecimal varid=itVar.next();
//					//{ipos.getFileId(),  ipos.getContig(),  mapFileContig2IdxCount.get(filecontig)} 
//					mapVar2SnpStringBuffer.get(varid).append( mapVar2SnpString.get(varid).substring(fileidx-(posidx-1-lastPosIdx) , fileidxend ));
//					
//					if(!itVar.hasNext()) {
//						AppContext.debug("filling table: posidx[" + lastpos + "-" + (posidx-1) + "] (" +   ((posidx-1)-lastPosIdx+1) + ")  file=" + filecontigidx[0] + "  colidx=[" + Integer.toString(fileidx-(posidx-1-lastPosIdx)) + "-" + fileidxend + "] (" + (fileidxend-(fileidx-(posidx-1-lastPosIdx))+1) + ")" ); 
//								
//						
//						AppContext.debug( "mapVar2SnpString.get(varid).length=" + mapVar2SnpString.get(varid).length());
//						AppContext.debug("mapVar2SnpStringBuffer length=" + mapVar2SnpStringBuffer.get(varid).length());
//						if(posidx !=  mapVar2SnpStringBuffer.get(varid).length() && itPos.hasNext()) {
//							AppContext.debug("posidx !=  mapVar2SnpStringBuffer.get(varid).length(), posidx=" + posidx);
//							throw new RuntimeException("posidx !=  mapVar2SnpStringBuffer.get(varid).length(), posidx=" + (posidx));
//						}
//					}
//				}
//				lastpos =  ipos;
//				lastPosIdx=posidx;
//			}
//			
//			prevpos=ipos;
//			posidx++;
//			
//			
//			
////			SnpsAllvarsMultirefsPos ipos=(SnpsAllvarsMultirefsPos)itPos.next();
////			String filecontig=ipos.getFileId() + "-" + ipos.getContig() + "-" + lastPosIdx;
////			//String filecontiglastpos= filecontig + lastPosIdx;
////			strRefbuff.append( ipos.getRefnuc()  );
////			// read only on first, or prevfilecontig!=filecontig
////			if( (prevfilecontig!=null && (!prevfilecontig.equals(filecontig) || ipos.getPosition().intValue()!=prevpos+1 ) )  || !itPos.hasNext()) {
////					
////				Map<BigDecimal,String>  mapVar2SnpString = mapFileContig2MapVar2Snpstring.get( prevfilecontig );
////				if(mapVar2SnpString==null) {
////				
////					H5Dataset hdf5dao = mapFileId2DatasetDAO.get( prevfilecontig.split("\\-")[0]);
////					if(hdf5dao==null) {
////						
////						if( BigDecimal.valueOf(Long.valueOf(prevfilecontig.split("\\-")[0])).equals( SnpsAllvarsMultirefsPosDAO.TYPE_3KALLSNP_HDF5_V2 ) ) {
////							nballsnpsdao= (H5Dataset)AppContext.checkBean(nballsnpsdao,"H5SNPUniIUPACV2DAO");
////							hdf5dao=nballsnpsdao;	
////						}
////						else if( BigDecimal.valueOf(Long.valueOf(prevfilecontig.split("\\-")[0])).equals( SnpsAllvarsMultirefsPosDAO.TYPE_3KCORESNP_HDF5_V2 ) ) {
////							nbcoresnpsdao= (H5Dataset)AppContext.checkBean(nbcoresnpsdao,"H5SNPCoreIUPACV2DAO");
////							hdf5dao=nbcoresnpsdao;	
////						} else
////							hdf5dao = new H5Dataset( SnpsAllvarsMultirefsPosDAO.MAP_TYPE2HDF5FILE.get(  BigDecimal.valueOf(Long.valueOf(prevfilecontig.split("\\-")[0]))));
////						mapFileId2DatasetDAO.put(prevfilecontig.split("\\-")[0], hdf5dao);
////					}
////					mapVar2SnpString = hdf5dao.readSNPString(null, mapFileContig2StartIdx.get(prevfilecontig).intValue() , mapFileContig2EndIdx.get(prevfilecontig).intValue());
////					mapFileContig2MapVar2Snpstring.put(prevfilecontig , mapVar2SnpString);
////				}
////				
////				// initialize string buffer for all vars
////				if(mapVar2SnpStringBuffer==null) {
////					mapVar2SnpStringBuffer = new HashMap();
////					Iterator<BigDecimal> itVar = mapVar2SnpString.keySet().iterator();
////					while(itVar.hasNext()) {
////						mapVar2SnpStringBuffer.put( itVar.next(), new StringBuffer());
////					}
////				}
////				Object[] filecontigidx=mapPosidx2FileContigIdx.get(posidx-1);
////				
////				// indx of position wrt filecontig
////				int fileidx=((Long)filecontigidx[3]).intValue();
////				int fileidxend=fileidx;
////				fileidxend++;
////				if(!itPos.hasNext()) {
////					fileidxend++;
////				}
////				
////				
////				//AppContext.debug( "posidx=" + posidx + ": at lastPosIdx=" + lastPosIdx +  " appending from "  + prevfilecontig + "["  + Integer.toString(fileidx-(posidx-1-lastPosIdx)) + "-" + Integer.toString(fileidxend) + "]");
////				AppContext.debug( "posidx=" + posidx + ": at lastPosIdx=" + lastPosIdx +  " appending from "  + prevfilecontig + "["  + Integer.toString(fileidx-(posidx-1-lastPosIdx)) + "-" + Integer.toString(fileidxend) + "]");
////				
////				Iterator<BigDecimal> itVar = mapVar2SnpString.keySet().iterator();
////				while(itVar.hasNext()) {
////					BigDecimal varid=itVar.next();
////					//{ipos.getFileId(),  ipos.getContig(),  mapFileContig2IdxCount.get(filecontig)} 
////					mapVar2SnpStringBuffer.get(varid).append( mapVar2SnpString.get(varid).substring(fileidx-(posidx-1-lastPosIdx) , fileidxend ));
////					
////					if(!itVar.hasNext()) {
////						AppContext.debug("filling table: posidx[" + lastPosIdx + "-" + (posidx-1) + "] (" +   ((posidx-1)-lastPosIdx+1) + ")  file=" + filecontigidx[0] + "  colidx=[" + Integer.toString(fileidx-(posidx-1-lastPosIdx)) + "-" + fileidxend + "] (" + (fileidxend-(fileidx-(posidx-1-lastPosIdx))+1) + ")" ); 
////								
////						
////						AppContext.debug( "mapVar2SnpString.get(varid).length=" + mapVar2SnpString.get(varid).length());
////						AppContext.debug("mapVar2SnpStringBuffer length=" + mapVar2SnpStringBuffer.get(varid).length());
////						if(posidx !=  mapVar2SnpStringBuffer.get(varid).length() && itPos.hasNext()) {
////							AppContext.debug("posidx !=  mapVar2SnpStringBuffer.get(varid).length(), posidx=" + posidx);
////							throw new RuntimeException("posidx !=  mapVar2SnpStringBuffer.get(varid).length(), posidx=" + (posidx));
////						}
////					}
////				}
////				lastPosIdx=posidx;
////			}
////			
////			prevpos=ipos.getPosition().intValue();
////			prevfilecontig=filecontig;
////			posidx++;
//
//		}		
//
//		
//		Map mapVarid2Snpsstr=new HashMap();
//		Iterator<BigDecimal> itVar=mapVar2SnpStringBuffer.keySet().iterator();
//		while(itVar.hasNext()) {
//			BigDecimal ivar=itVar.next();
//			mapVarid2Snpsstr.put(  ivar, mapVar2SnpStringBuffer.get(ivar).toString());
//		}
//		
//		// get allele column indices from start to end positions
//		SnpsAllvarsPos startpos =  snpsposlist.get(0);
//		SnpsAllvarsPos endpos =  snpsposlist.get( snpsposlist.size()-1 );
//
//		String strRef=strRefbuff.toString();
//		
//		int refLength=-1;
//		
//		AppContext.debug( snpsposlist.size() + " snpposlist, pos between " +startpos.getPosition() +  "-" + endpos.getPosition() + "  index between " + startpos.getAlleleIndex() + "-" + endpos.getAlleleIndex());
//		
//		
//				
//			Map mapVarid2Snpsstr_allele2=null;
//			Map<BigDecimal,Map> mapVarid2SnpsAllele2str = new HashMap();
//			
//				if (mapVarid2Snpsstr_allele2==null && params.includeDataset(SnpsAllvarsPosDAO.DATASET_SNPINDELV2_IUPAC)) {
//				
//				Map mapVarid2SnpsstrSplitIUPAC=new LinkedHashMap();
//				mapVarid2Snpsstr_allele2=new LinkedHashMap();
//				
//				Iterator itVarid = mapVarid2Snpsstr.keySet().iterator();
//				while(itVarid.hasNext()) {
//					BigDecimal varid= (BigDecimal)itVarid.next();
//
//					//if(params.includeDataset(SnpsAllvarsPosDAO.DATASET_SNPINDELV2)) {					
//						// revised for SNPv2 (allele2!=0 for allele1==allele2)
//						String allele1str = (String)mapVarid2Snpsstr.get( varid );
//						
//						//String allele2str = (String)mapVarid2Snpsstr_allele2.get( varid );
//						//if(allele1str.length()!=allele2str.length()) throw new RuntimeException( "allele1str.length()!=allele2str.length(): " + allele1str.length() + ", " + allele2str.length());
//						
//						StringBuffer buffNewStr=new StringBuffer();
//						StringBuffer buffNewStr2=new StringBuffer();
//						for(int iStr=0; iStr<allele1str.length(); iStr++) {
//							//char allele2i =allele2str.charAt(iStr);
//							char allele1i =allele1str.charAt(iStr);
//							String alleles12= AppContext.getNucsFromIUPAC(allele1i);
//							if(alleles12.length()==2) {
//								Map mapTableidx2Nuc = mapVarid2SnpsAllele2str.get( varid );
//								if(mapTableidx2Nuc==null) {
//									mapTableidx2Nuc = new HashMap();
//									mapVarid2SnpsAllele2str.put(varid , mapTableidx2Nuc);
//								}
//								mapTableidx2Nuc.put( snpsposlist.get(iStr), alleles12.charAt(1));
//								buffNewStr2.append(alleles12.charAt(1));
//							} else 
//								buffNewStr2.append(alleles12.charAt(0));
//							
//							buffNewStr.append(alleles12.charAt(0));
//						}
//						
//						mapVarid2SnpsstrSplitIUPAC.put( varid , buffNewStr.toString());
//						mapVarid2Snpsstr_allele2.put( varid , buffNewStr2.toString());
//						
//						if(!itVarid.hasNext()) AppContext.debug(varid + "  length=" +  buffNewStr.length() + ", " + buffNewStr2.length());
//						
////						if(!allele1str.equals(buffNewStr.toString())) {
////							AppContext.debug( "varid=" + varid);
////							AppContext.debug( "alleleIUPACstr=" + allele1str);
////							AppContext.debug( "allele1    str=" + buffNewStr);
////							AppContext.debug( "allele2    str=" + buffNewStr2);
////						}
//					//}
//				}
//				
//				mapVarid2Snpsstr = mapVarid2SnpsstrSplitIUPAC;
//				
//				
//			} // else throw new RuntimeException("heteroSnps==null and mapVarid2Snpsstr_allele2==null ... no allele2 data");
//			
//			AppContext.debug("snpsposlist length=" + snpsposlist.size() );  
//				
//			// generate pos2alleles
//
//			Map<Position, Set<Character>> mapPos2Allele=new TreeMap();
//			Map<Position, Set<Character>> mapPos2AlleleHetero=new TreeMap();
//			
//			itVar=mapVarid2Snpsstr.keySet().iterator();
//			while(itVar.hasNext()) {
//				BigDecimal varid=itVar.next();
//				String allele1str = (String)mapVarid2Snpsstr.get( varid );
//				String allele2str = (String)mapVarid2Snpsstr_allele2.get( varid );
//				Iterator<SnpsAllvarsPos> itSnppos =snpsposlist.iterator();
//				int icount=0;
//				while(itSnppos.hasNext()) {
//					SnpsAllvarsPos snppos=itSnppos.next();
//					
//					Set setAlleles=mapPos2Allele.get(snppos);
//					if(setAlleles==null) {
//						setAlleles=new HashSet();
//						mapPos2Allele.put(snppos, setAlleles);
//					}
//					// hdf5 format v2
//					char chari1=allele1str.charAt(icount);
//					if(chari1!='0' && chari1!='?') setAlleles.add(chari1);
//					char chari2=allele2str.charAt(icount);
//					if(chari2!='0' && chari2!='?' ) setAlleles.add(chari2);
//					
//					if(chari1!=chari2) {
//						if(chari1!=snppos.getRefnuc().charAt(0)) {
//							Set allele2= mapPos2AlleleHetero.get(snppos);
//							if(allele2==null) {
//								allele2=new HashSet();
//								mapPos2AlleleHetero.put(snppos,  allele2);
//							}
//							allele2.add(chari1);
//						}
//						if(chari2!=snppos.getRefnuc().charAt(0)) {
//							Set allele2= mapPos2AlleleHetero.get(snppos);
//							if(allele2==null) {
//								allele2=new HashSet();
//								mapPos2AlleleHetero.put(snppos,  allele2);
//							}
//							allele2.add(chari2);
//						}
//					}
//					icount++;
//				}
//			}
//			
//			
//			// non-synonymous alleles for positions
//			Map<Position,Set<Character>> mapPos2NonsynAlleles = new TreeMap();
//			Map<Position,Set<Character>> mapPos2SynAlleles = new TreeMap();
//			Set<Position> setSnpInExonPos = new TreeSet();
//			setSnpInExonPos = new TreeSet();
////			
////			
////			Iterator<SnpsNonsynAllele> itNonsyn = nonsynAllele.iterator();
////			while(itNonsyn.hasNext()) {
////				SnpsNonsynAllele nonsynallele = itNonsyn.next();
////				
////				if(mapSnpid2Pos==null) throw new RuntimeException("mapSnpid2Pos==null");
////				if(nonsynallele==null) throw new RuntimeException("nonsynallele==null");
////				if(nonsynallele.getSnp()==null) throw new RuntimeException("nonsynallele.getSnp()==null");
////				
////				Position pos = mapSnpid2Pos.get( nonsynallele.getSnp() );
////				
////				// assuming all nonsyn are in exon
////				setSnpInExonPos.add( pos );
////				
////				Set<Character> setVarietyAlleles = mapPos2Allele.get(pos);
////				if(setVarietyAlleles==null) throw new RuntimeException("setVarietyAlleles==null");
////				if(nonsynallele.getAllele()=='\0') throw new RuntimeException("nonsynallele.getAllele()==null");
////
////				if(setVarietyAlleles.contains( nonsynallele.getAllele()) ) {
////
////					Set<Character> alleles = mapPos2NonsynAlleles.get(pos);
////					if(alleles==null) {
////						alleles = new HashSet();
////						mapPos2NonsynAlleles.put( pos , alleles);
////					}
////					alleles.add( nonsynallele.getAllele() );
////				}
////			}
////			
////			Iterator<SnpsSynAllele> itSyn = synAllele.iterator();
////			while(itSyn.hasNext()) {
////				SnpsSynAllele synallele = itSyn.next();
////				Position pos = mapSnpid2Pos.get( synallele.getSnp() );
////				
////				// assuming all syn are in exon
////				setSnpInExonPos.add( pos );
////				
////				Set<Character> setVarietyAlleles = mapPos2Allele.get(pos);
////				
////				if(setVarietyAlleles.contains( synallele.getAllele()) ) {
////					Set<Character> alleles = mapPos2SynAlleles.get(pos);
////					if(alleles==null) {
////						alleles = new HashSet();
////						mapPos2SynAlleles.put( pos , alleles);
////					}
////					alleles.add( synallele.getAllele() );
////				}
////			}
////		
////			if(nonsynAllele!=null) AppContext.debug( nonsynAllele.size() + " non-synonymous alleles, " + mapPos2NonsynAlleles.size() + " positions/idx,  " +  mapPos2NonsynAlleles.size() + "  nonsys alleles positions");
////			
////			
//			// get splice variants
//			Set setSpliceAcceptorsPos = new HashSet();
//			Set setSpliceDonorsPos = new HashSet();
//
//			Set setSpliceAcceptors= new HashSet();
//			Set setSpliceDonors= new HashSet();
//			
////			snpsspliceacceptorDAO = (SnpsSpliceAcceptorDAO)AppContext.checkBean(snpsspliceacceptorDAO, "SnpsSpliceAcceptorDAO");
////			snpssplicedonorDAO = (SnpsSpliceDonorDAO)AppContext.checkBean(snpssplicedonorDAO, "SnpsSpliceDonorDAO");
////			if(setPositions!=null && !setPositions.isEmpty()) {
////				setSpliceAcceptors = snpsspliceacceptorDAO.getSNPsIn(chr, setPositions);
////				setSpliceDonors = snpssplicedonorDAO.getSNPsIn(chr, setPositions);
////			}
////			else if(colLocus!=null && !colLocus.isEmpty()) {
////					setSpliceAcceptors = snpsspliceacceptorDAO.getSNPsIn(chr, colLocus);
////					setSpliceDonors = snpssplicedonorDAO.getSNPsIn(chr, colLocus);							
////			} else {
////				setSpliceAcceptors = snpsspliceacceptorDAO.getSNPsBetween(chr, start.intValue(), end.intValue());  
////				setSpliceDonors = snpssplicedonorDAO.getSNPsBetween(chr, start.intValue(), end.intValue());							
////			}
////			
////			Iterator<SnpsSpliceAcceptor>  itAcceptors = setSpliceAcceptors.iterator();
////			while(itAcceptors.hasNext()) {
////				SnpsSpliceAcceptor acc = itAcceptors.next();
////				setSpliceAcceptorsPos.add( acc.getPos());
////			}
////			Iterator<SnpsSpliceDonor>  itDonor = setSpliceDonors.iterator();
////			while(itDonor.hasNext()) {
////				SnpsSpliceDonor acc = itDonor.next();
////				setSpliceDonorsPos.add( acc.getPos());
////			}
////				
////			
////			if(AppContext.isLocalhost()) {
////				AppContext.debug("mapPos2NonsynAlleles " + mapPos2NonsynAlleles.size() +": " + mapPos2NonsynAlleles.toString() );
////				AppContext.debug("mapPos2SynAlleles " + mapPos2SynAlleles.size() + ": " + mapPos2SynAlleles.toString() );
////				AppContext.debug("mapPos2Allele " + mapPos2Allele.size() + ": " + mapPos2Allele.toString() );
////				AppContext.debug("mapPos2AlleleHetero " + mapPos2AlleleHetero.size() +": " + mapPos2AlleleHetero.toString() );
////				AppContext.debug("setSpliceDonorsPos " + setSpliceDonorsPos.size() +": " + setSpliceDonorsPos.toString() );
////				AppContext.debug("setSpliceAcceptorsPos " + setSpliceAcceptorsPos.size() +": " + setSpliceAcceptorsPos.toString() );
////				//AppContext.debug( nonsynAllele.toString() );
////			}
//			
//			SNPsStringData snpstrdata = new  SNPsStringData(snpsposlist,  strRef, mapVarid2Snpsstr, mapVarid2SnpsAllele2str,
//					mapPos2Allele, mapPos2NonsynAlleles, mapPos2SynAlleles, setSnpInExonPos, setSpliceDonorsPos,  setSpliceAcceptorsPos);
//			return snpstrdata;
//	}
//	
//	

/**
 * Count mismatch between nucelotide sequences, based on several criteria
 * @param var1	variety 1 allele1 string
 * @param var2	variety 2 allele1 string
 * @param var1isref	variety 1 is reference
 * @param var1allele2str	variety 1 allele2 string
 * @param var2allele2str	variety 2 allele2 string
 * @param mapIdx2NonsynAlleles	map table index 2 nonsysynonymous nucleotide set
 * @param setSnpInExonTableIdx	set of table indices in exon
 * @param setNonsynIdx		set of table indices with nonsynonymous (return value)
 * @param isNonsynOnly	include only nonsynonymous
 * @param isColorSynGray	color nonsynonymous as gray
 * @return
 */
	
public static double countVarpairMismatch(List listpos, String var1, String var2, boolean var1isref, Map<Position,Character> var1allele2str, Map<Position,Character> var2allele2str,	
				Map<Position,Set<Character>> mapPos2NonsynAlleles,  Set<Position> setInExon, Set<Position> setNonsynPos, boolean isNonsynOnly) {

		double misCount = 0;
		for(int iStr=0; iStr<var2.length(); iStr++) {
			char var1char = var1.charAt(iStr);
			char var2char = var2.charAt(iStr);
			
			//boolean snpInExon = false;
			//if(setSnpInExonTableIdx==null || (setSnpInExonTableIdx!=null && setSnpInExonTableIdx.contains(iStr))) snpInExon=true;
			
			Position pos= (Position)listpos.get(iStr);
			
			Boolean isNonsyn[] = new Boolean[2];
			isNonsyn[0] = false;
			isNonsyn[1] = false;
			Character var1allele2 = null;
			if(!var1isref && var1allele2str!=null) var1allele2 =  var1allele2str.get(iStr);
			
			Character var2allele2 = null;
			if(var2allele2str!=null) var2allele2 = var2allele2str.get(pos);
			Set setNonsyns = mapPos2NonsynAlleles.get(pos);
			
			misCount += countVarpairMismatchNuc( var1.charAt(iStr),  var2.charAt(iStr),  var1isref, var1allele2, var2allele2,
					setNonsyns, setInExon.contains(pos),  isNonsyn,  isNonsynOnly);
					
			if(isNonsyn[0] || isNonsyn[1]) setNonsynPos.add(pos);					
		}
		return misCount;
}

/**
 * Compare two nucleotides, heterozygous are counted as 0.5 if one allele is mismatched
 * @param var1char
 * @param var2char
 * @param var1isref
 * @param var1allele2
 * @param var2allele2
 * @param setNonsynAlleles
 * @param snpInExon
 * @param isNonsyn
 * @param isNonsynOnly
 * @return
 */
	 
  public static double countVarpairMismatchNuc(char var1char, char var2char , boolean var1isref,Character var1allele2, Character var2allele2,	
		Set<Character> setNonsynAlleles, boolean  snpInExon, Boolean isNonsyn[], boolean isNonsynOnly) {
			double misCount = 0;

				isNonsyn[0]=false;
				isNonsyn[1]=false;
				//boolNonsyn = false;
				
				if( var2allele2 != null) {
					if(var2allele2=='*') var2allele2 = var2char;
					else if( var2allele2=='0' || var2allele2=='.' || var2allele2=='?' ) var2allele2=null;
				}
				if( var1allele2 != null) {
					if(var1allele2=='*') var1allele2 = var1char; 
					else if(var1allele2=='0' || var1allele2=='.' || var2allele2=='?') var1allele2=null;
				}
				
				
				if(var2char=='0' || var2char=='.'  || var2char == '*'  || var2char == '?')
					{}
				else if(!var1isref && (var1char=='0' || var1char=='.'  || var1char == '*' || var1char == '?')) 
					{}
				else {
					if(snpInExon) {
						// idx in exon
						if(setNonsynAlleles!=null && (setNonsynAlleles.contains(var2char) || (var2allele2!=null && setNonsynAlleles.contains(var2allele2) ) ) )
							// var2 allele1 or allele2 in nonsynonymous
							isNonsyn[1]=true;
						
						if(!var1isref && setNonsynAlleles!=null && (setNonsynAlleles.contains(var1char) || (var1allele2!=null && setNonsynAlleles.contains(var1allele2) ) ) )
							// var1 is not reference, and var1 allele1 or allele2 in nonsynonymous
							isNonsyn[0]=true;
					} 
					else {
						// not in exon, OR no exon information, include in nonsynonymous
						isNonsyn[0]=true; 
						isNonsyn[1]=true;
					}
				}
				
				if(isNonsynOnly && !isNonsyn[0]  && !isNonsyn[0]) return 0;

				
				if(var1isref) {
					// compare with reference
					
					// assump: no 0 * . $ characters in reference
					// if homozygous, mismatch allele1, miscount +1
					// if heterozygous, match allele1 or allele2, miscount +0.5
					// if not nonsynonymos and isNonsynOnly , no count 
					if(var1char==var2char) {
						if(var2allele2!=null && var2allele2!=var2char ) misCount+=0.5;
					}
					else if(var2char!='0' && var2char!='.'  &&  var2char!='*' &&  var2char!='$' &&  var2char!='?') {
						//check with allele 2
						if(var2allele2!=null &&  var2allele2==var1char) misCount+=0.5;
						else misCount +=1.0;
					}
					
				} else {
					// pairwise comparison
					
					// check all pairs
					if(var1char=='0' || var2char=='0' ||  var1char=='.' || var2char=='.' ||  var1char=='*' || var2char=='*' ||  var1char=='?' || var2char=='?'  ) {}
					else if(var1char==var2char) {
						if(var2allele2!=null && var1char!= var2allele2)
							misCount+=0.5;
						if(var1allele2!=null && var2char!= var1allele2)
							misCount+=0.5;
						
					}
					else {
						//var1 allele1 != var2 allele1
						if(var1allele2==null && var2allele2==null) misCount+=1;  
						else {
							//if(var1allele2==null || var2allele2==null) misCount+=0.5;
							if(var1allele2!=null && var2char!=var1allele2)
								misCount+=0.5;
							if(var2allele2!=null && var1char!=var2allele2)
								misCount+=0.5;
						}
					}
				}
		return misCount;
	}
}




//**********************  PAST CODES  ***************

//  
//  public static double countVarpairMismatchNucOLD(char var1char, char var2char , boolean var1isref,Character var1allele2, Character var2allele2,	
//			Set<Character> setNonsynAlleles, boolean  snpInExon, Boolean isNonsyn[], boolean isNonsynOnly) {
//			double misCount = 0;
////			for(int iStr=0; iStr<var2.length(); iStr++) {
////				char var1char = var1.charAt(0);
////				char var2char = var2.charAt(0);
//				
//					isNonsyn[0]=false;
//					isNonsyn[1]=false;
//					//boolNonsyn = false;
//					
//					if( var2allele2 != null) {
//						if(var2allele2=='*') var2allele2 = var2char;
//						else if( var2allele2=='0' || var2allele2=='.') var2allele2=null;
//					}
//					if( var1allele2 != null) {
//						if(var1allele2=='*') var1allele2 = var1char; 
//						else if(var1allele2=='0' || var1allele2=='.') var1allele2=null;
//					}
//					
//					
//					if(var2char=='0' || var2char=='.'  || var2char == '*')
//						{}
//					else if(!var1isref && (var1char=='0' || var1char=='.'  || var1char == '*')) 
//						{}
//					else {
//						if(snpInExon) {
//							// idx in exon
//							if(setNonsynAlleles!=null && (setNonsynAlleles.contains(var2char) || (var2allele2!=null && setNonsynAlleles.contains(var2allele2) ) ) )
//								// var2 allele1 or allele2 in nonsynonymous
//								isNonsyn[1]=true;
//							
//							if(!var1isref && setNonsynAlleles!=null && (setNonsynAlleles.contains(var1char) || (var1allele2!=null && setNonsynAlleles.contains(var1allele2) ) ) )
//								// var1 is not reference, and var1 allele1 or allele2 in nonsynonymous
//								isNonsyn[0]=true;
//						} 
//						else {
//							// not in exon, OR no exon information, include in nonsynonymous
//							isNonsyn[0]=true; 
//							isNonsyn[1]=true;
//						}
//					}
//					
//					if(isNonsynOnly && !isNonsyn[0]  && !isNonsyn[0]) return 0;
//
//					
//					if(var1isref) {
//						// compare with reference
//						
//						// assump: no 0 * . $ characters in reference
//						// if homozygous, mismatch allele1, miscount +1
//						// if heterozygous, match allele1 or allele2, miscount +0.5
//						// if not nonsynonymos and isNonsynOnly , no count 
//						if(var1char==var2char) {
//							if(var2allele2!=null && var2allele2!=var2char ) misCount+=0.5;
//						}
//						else if(var2char!='0' && var2char!='.'  &&  var2char!='*' &&  var2char!='$') {
//							//check with allele 2
//							if(var2allele2!=null &&  var2allele2==var1char) misCount+=0.5;
//							else misCount +=1.0;
//						}
//						
//					} else {
//						// pairwise comparison
//						
//						// check all pairs
//						if(var1char=='0' || var2char=='0' ||  var1char=='.' || var2char=='.' ||  var1char=='*' || var2char=='*' ) {}
//						else if(var1char==var2char) {
//							if(var2allele2!=null && var1char!= var2allele2)
//								misCount+=0.5;
//							if(var1allele2!=null && var2char!= var1allele2)
//								misCount+=0.5;
//							
//						}
//						else {
//							//var1 allele1 != var2 allele1
//							if(var1allele2==null && var2allele2==null) misCount+=1;  
//							else {
//								//if(var1allele2==null || var2allele2==null) misCount+=0.5;
//								if(var1allele2!=null && var2char!=var1allele2)
//									misCount+=0.5;
//								if(var2allele2!=null && var1char!=var2allele2)
//									misCount+=0.5;
//							}
//						}
//					}
//					
//					//String ismis = "          *** ";
//					//if(var1char==var2char) ismis= "";
//					//AppContext.debug("var1isref=" + var1isref + "  var1char=" + var1char + "  var2char=" + var2char + " var1allele2=" + var1allele2 + " var2allele2=" + var2allele2  + "  mis=" + misCount  +  ismis);
//					
//			//}
//					
//			return misCount;
//		}
//  
//}



//	public SNPsStringData(List listSnpsPos, Map mapIdx2Pos, String strRef, Map mapVarid2Snpsstr,
//	Map mapVarid2SnpsAllele2str, Map mapIdx2NonsynAlleles,
//	Set setSnpInExonTableIdx, Set setSnpSpliceDonorPos,  Set setSnpSpliceAcceptorPos) {
//super();
////if(strRef.length()==0) throw new RuntimeException("SNPsStringData: reference has zreo length");
////if(mapVarid2Snpsstr.size()==0) throw new RuntimeException("SNPsStringData: no variety");
////if( ((String)mapVarid2Snpsstr.values().iterator().next()).length()==0) throw new RuntimeException("SNPsStringData: first variety has zero length Snpsstr");
//
//this.strRef = strRef;
//this.mapVarid2Snpsstr = mapVarid2Snpsstr;
//this.mapVarid2SnpsAllele2str = mapVarid2SnpsAllele2str;
//this.mapIdx2NonsynAlleles = mapIdx2NonsynAlleles;
////this.setSnpInExonTableIdx = setSnpInExonTableIdx;
//this.listSnpsPos = listSnpsPos;
////this.mapIdx2Pos = mapIdx2Pos;
//this.setSnpSpliceAcceptorPos = setSnpSpliceAcceptorPos;
//this.setSnpSpliceDonorPos = setSnpSpliceDonorPos;
//}
  
//public static double countVarpairMismatch(List listpos, String var1, String var2, boolean var1isref, Map<Integer,Character> var1allele2str, Map<Integer,Character> var2allele2str,	
//	Map<Position,Set<Character>> mapPos2NonsynAlleles,  Set<Position> setInExon, Set<Position> setNonsynPos, boolean isNonsynOnly) {
//
//double misCount = 0;
//for(int iStr=0; iStr<var2.length(); iStr++) {
//char var1char = var1.charAt(iStr);
//char var2char = var2.charAt(iStr);
//
////boolean snpInExon = false;
////if(setSnpInExonTableIdx==null || (setSnpInExonTableIdx!=null && setSnpInExonTableIdx.contains(iStr))) snpInExon=true;
//
//Position pos= (Position)listpos.get(iStr);
//
//Boolean isNonsyn[] = new Boolean[2];
//isNonsyn[0] = false;
//isNonsyn[1] = false;
//Character var1allele2 = null;
//if(!var1isref && var1allele2str!=null) var1allele2 =  var1allele2str.get(iStr);
//
//Character var2allele2 = null;
//if(var2allele2str!=null) var2allele2 = var2allele2str.get(iStr);
//Set setNonsyns = mapPos2NonsynAlleles.get(pos);
//
//misCount += countVarpairMismatchNuc( var1.charAt(iStr),  var2.charAt(iStr),  var1isref, var1allele2, var2allele2,
//		setNonsyns, setInExon.contains(pos),  isNonsyn,  isNonsynOnly);
//		
//if(isNonsyn[0] || isNonsyn[1]) setNonsynPos.add(pos);					
//}
//return misCount;
//}
  
	
//	/**
//	 * Count mismatch between nucelotide sequences, based on several criteria
//	 * @param var1	variety 1 allele1 string
//	 * @param var2	variety 2 allele1 string
//	 * @param var1isref	variety 1 is reference
//	 * @param var1allele2str	variety 1 allele2 string
//	 * @param var2allele2str	variety 2 allele2 string
//	 * @param mapIdx2NonsynAlleles	map table index 2 nonsysynonymous nucleotide set
//	 * @param setSnpInExonTableIdx	set of table indices in exon
//	 * @param setNonsynIdx		set of table indices with nonsynonymous (return value)
//	 * @param isNonsynOnly	include only nonsynonymous
//	 * @param isColorSynGray	color nonsynonymous as gray
//	 * @return
//	 */
//  public static double countVarpairMismatch(String var1, String var2, boolean var1isref, Map<Integer,Character> var1allele2str, Map<Integer,Character> var2allele2str,	
//				Map<Integer,Set<Character>> mapIdx2NonsynAlleles, Set setSnpInExonTableIdx, Set setNonsynIdx, boolean isNonsynOnly) {
//
//		double misCount = 0;
//		for(int iStr=0; iStr<var2.length(); iStr++) {
//			char var1char = var1.charAt(iStr);
//			char var2char = var2.charAt(iStr);
//			boolean snpInExon = false;
//			if(setSnpInExonTableIdx==null || (setSnpInExonTableIdx!=null && setSnpInExonTableIdx.contains(iStr))) snpInExon=true;
//			
//			Boolean isNonsyn[] = new Boolean[2];
//			isNonsyn[0] = false;
//			isNonsyn[1] = false;
//			Character var1allele2 = null;
//			if(!var1isref && var1allele2str!=null) var1allele2 =  var1allele2str.get(iStr);
//			
//			Character var2allele2 = null;
//			if(var2allele2str!=null) var2allele2 = var2allele2str.get(iStr);
//			Set setNonsyns = null;
//			if(mapIdx2NonsynAlleles!=null) setNonsyns = mapIdx2NonsynAlleles.get(iStr);
//			
//			misCount += countVarpairMismatchNuc( var1.charAt(iStr),  var2.charAt(iStr),  var1isref, var1allele2, var2allele2,
//					setNonsyns, snpInExon,  isNonsyn,  isNonsynOnly);
//					
//			if(isNonsyn[0] || isNonsyn[1]) setNonsynIdx.add(iStr);					
//		}
//		return misCount;
//  }
//	  
//  public static double countVarpairMismatchNuc(char var1char, char var2char , boolean var1isref,Character var1allele2, Character var2allele2,	
//		Set<Character> setNonsynAlleles, boolean snpInExon, Boolean isNonsyn[], boolean isNonsynOnly) {
//		double misCount = 0;
////		for(int iStr=0; iStr<var2.length(); iStr++) {
////			char var1char = var1.charAt(0);
////			char var2char = var2.charAt(0);
//			
//				isNonsyn[0]=false;
//				isNonsyn[1]=false;
//				//boolNonsyn = false;
//				
//				if( var2allele2 != null) {
//					if(var2allele2=='*') var2allele2 = var2char;
//					else if( var2allele2=='0' || var2allele2=='.') var2allele2=null;
//				}
//				if( var1allele2 != null) {
//					if(var1allele2=='*') var1allele2 = var1char; 
//					else if(var1allele2=='0' || var1allele2=='.') var1allele2=null;
//				}
//				
//				
//				if(var2char=='0' || var2char=='.'  || var2char == '*')
//					{}
//				else if(!var1isref && (var1char=='0' || var1char=='.'  || var1char == '*')) 
//					{}
//				else {
//					if(snpInExon) {
//						// idx in exon
//						if(setNonsynAlleles!=null && (setNonsynAlleles.contains(var2char) || (var2allele2!=null && setNonsynAlleles.contains(var2allele2) ) ) )
//							// var2 allele1 or allele2 in nonsynonymous
//							isNonsyn[1]=true;
//						
//						if(!var1isref && setNonsynAlleles!=null && (setNonsynAlleles.contains(var1char) || (var1allele2!=null && setNonsynAlleles.contains(var1allele2) ) ) )
//							// var1 is not reference, and var1 allele1 or allele2 in nonsynonymous
//							isNonsyn[0]=true;
//					} 
//					else {
//						// not in exon, OR no exon information, include in nonsynonymous
//						isNonsyn[0]=true; 
//						isNonsyn[1]=true;
//					}
//				}
//				
//				if(isNonsynOnly && !isNonsyn[0]  && !isNonsyn[0]) return 0;
//
//				
//				if(var1isref) {
//					// compare with reference
//					
//					// assump: no 0 * . $ characters in reference
//					// if homozygous, mismatch allele1, miscount +1
//					// if heterozygous, match allele1 or allele2, miscount +0.5
//					// if not nonsynonymos and isNonsynOnly , no count 
//					if(var1char==var2char) {
//						if(var2allele2!=null && var2allele2!=var2char ) misCount+=0.5;
//					}
//					else if(var2char!='0' && var2char!='.'  &&  var2char!='*' &&  var2char!='$') {
//						//check with allele 2
//						if(var2allele2!=null &&  var2allele2==var1char) misCount+=0.5;
//						else misCount +=1.0;
//					}
//					
//				} else {
//					// pairwise comparison
//					
//					// check all pairs
//					if(var1char=='0' || var2char=='0' ||  var1char=='.' || var2char=='.' ||  var1char=='*' || var2char=='*' ) {}
//					else if(var1char==var2char) {
//						if(var2allele2!=null && var1char!= var2allele2)
//							misCount+=0.5;
//						if(var1allele2!=null && var2char!= var1allele2)
//							misCount+=0.5;
//						
//					}
//					else {
//						//var1 allele1 != var2 allele1
//						if(var1allele2==null && var2allele2==null) misCount+=1;  
//						else {
//							//if(var1allele2==null || var2allele2==null) misCount+=0.5;
//							if(var1allele2!=null && var2char!=var1allele2)
//								misCount+=0.5;
//							if(var2allele2!=null && var1char!=var2allele2)
//								misCount+=0.5;
//						}
//					}
//				}
//				
//				//String ismis = "          *** ";
//				//if(var1char==var2char) ismis= "";
//				//AppContext.debug("var1isref=" + var1isref + "  var1char=" + var1char + "  var2char=" + var2char + " var1allele2=" + var1allele2 + " var2allele2=" + var2allele2  + "  mis=" + misCount  +  ismis);
//				
//		//}
//				
//		return misCount;
//	}
//
//@Override
//public VariantStringData getIndelsString(GenotypeQueryParams params)
//		throws Exception {
//	// TODO Auto-generated method stub
//	return null;
//}
//  
//	

  

//	@Override
//	public VariantStringData getVariantString(GenotypeQueryParams params, Collection colVarids, String chr, Long start, Long stop) {
//		// TODO Auto-generated method stub
//		//return getSNPsString( params,  colVarids,  Integer.valueOf(chr),  BigDecimal.valueOf(start),  BigDecimal.valueOf(stop), null,null);
//		return null;
//	}
//
//	@Override
//	public VariantStringData getVariantString(GenotypeQueryParams params, String chr, Long start, Long stop) {
//		// TODO Auto-generated method stub
//		//return getSNPsString( params,  null, Integer.valueOf(chr),  BigDecimal.valueOf(start),  BigDecimal.valueOf(stop), null,null);
//		return null;
//	}
//
//	
//	@Override
//	public VariantStringData getVariantString(GenotypeQueryParams params, Collection colLocus) {
//		// TODO Auto-generated method stub
//		//return getSNPsString( params,  null, null, null, null, null, colLocus);
//		return null;
//	}
//
//	
//	@Override
//	public VariantStringData getVariantString(GenotypeQueryParams params, Collection colVarids, String chr, Collection colSnppos) {
//		// TODO Auto-generated method stub
//
//		//return getSNPsString( params,  colVarids, Integer.valueOf(chr),  null, null, colSnppos, null);
//		return null;
//
//	}
//
//	@Override
//	public VariantStringData getVariantString(GenotypeQueryParams params, String chr, Collection colSnppos) {
//		// TODO Auto-generated method stub
//		//return getSNPsString( params, null, Integer.valueOf(chr),  null, null, colSnppos, null);
//		return null;
//	}
  
  

//	//private List<SnpsStringAllvars> 
//	private SNPsStringData getSNPsStringData_v1(GenotypeQueryParams params, Collection colVarids, String chr, BigDecimal start, BigDecimal end, Collection setPositions, Collection colLocus) { //, boolean exactMismatch, int firstRow, int maxRows) {
//		// TODO Auto-generated method stub
//
//		// get snp positions/index
//		snpstringallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpstringallvarsposService, "VSnpRefposindexDAO") ;
//		
//		SnpsStringDAO snpstrSnpsAllele1AllvarsDAO=null;
//		SnpsStringDAO snpstrSnpsAllele2AllvarsDAO=null;
//		BigDecimal snptype=null; 
//		
//		if(params.includeDataset(SnpsAllvarsPosDAO.DATASET_SNPINDELV1)) {
//		
//			if(params.isbCoreonly()) {
//				snpstrSnpsAllele1AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrCoresnpsAllele1AllvarsDAO, "H5SNPCoreAllele1DAO");
//				snpstrSnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrCoresnpsAllele1AllvarsDAO, "H5SNPCoreAllele2DAO");
//				snptype=SnpsAllvarsPosDAO.TYPE_3KCORESNP;
//			} else {
//				snptype=SnpsAllvarsPosDAO.TYPE_3KALLSNP;
//				snpstrSnpsAllele1AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrAllsnpsAllele1AllvarsDAO, "H5SNPUniAllele1DAO");
//				snpstrSnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrAllsnpsAllele2AllvarsDAO, "H5SNPUniAllele2DAO");
//			}
//		} else if(params.includeDataset(SnpsAllvarsPosDAO.DATASET_SNPINDELV2)) {
//			if(params.isbCoreonly()) {
//				snpstrSnpsAllele1AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrCoresnpsAllele1AllvarsDAO, "H5SNPCoreAllele1V2DAO");
//				snpstrSnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrCoresnpsAllele1AllvarsDAO, "H5SNPCoreAllele2V2DAO");
//				snptype=SnpsAllvarsPosDAO.TYPE_3KCORESNP_HDF5_V2;
//			} else {
//				snptype=SnpsAllvarsPosDAO.TYPE_3KALLSNP_HDF5_V2;
//				snpstrSnpsAllele1AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrAllsnpsAllele1AllvarsDAO, "H5SNPUniAllele1V2DAO");
//				snpstrSnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrAllsnpsAllele2AllvarsDAO, "H5SNPUniAllele2V2DAO");
//			}
//			
//		}
//		    
//		if(snptype==null) throw new RuntimeException("snptype==null");
//		if(snpstrSnpsAllele1AllvarsDAO==null) throw new RuntimeException("snpstrSnpsAllele1AllvarsDAO==null");
//		if(snpstrSnpsAllele2AllvarsDAO==null) throw new RuntimeException("snpstrSnpsAllele2AllvarsDAO==null");
//		
//		
//		List<SnpsAllvarsPos> snpsposlist = null;
//		List listpos = null;
//		
//		if(colVarids==null || colVarids.isEmpty()) {
////			if(colLocus!=null && !colLocus.isEmpty()) {
////				AppContext.resetTimer("getSNPsString start5");
////				snpsposlist  = snpstringallvarsposService.getSNPsInChromosome(colLocus, snptype);
////						
////			} else 
//			if( (setPositions!=null && !setPositions.isEmpty()) ) {
//				listpos = new ArrayList();
//				listpos.addAll(new TreeSet(setPositions));
//				AppContext.resetTimer("getSNPsString start1");
//				snpsposlist  = snpstringallvarsposService.getSNPsInChromosome(chr.toString(),  listpos, snptype);
//			}
//			else if( (colLocus!=null && !colLocus.isEmpty()) ) {
//				AppContext.resetTimer("getSNPsString start1b");
//				snpsposlist  = snpstringallvarsposService.getSNPsInChromosome(chr.toString(),  colLocus, snptype);
//			}
//			else {
//				AppContext.resetTimer("getSNPsString start2");
//				snpsposlist  = snpstringallvarsposService.getSNPs(chr.toString(), start.intValue(), end.intValue(),   snptype, -1, -1);
//			}
//		} else {
////			if(colLocus!=null && !colLocus.isEmpty()) {
////				AppContext.resetTimer("getSNPsString start6");
////				snpsposlist  = snpstringallvarsposService.getSNPsInChromosome(colLocus, snptype);
////				
////			} else
//			if(setPositions!=null && !setPositions.isEmpty()) {
//				listpos = new ArrayList();
//				listpos.addAll(new TreeSet(setPositions));
//				AppContext.resetTimer("getSNPsString start3");
//				snpsposlist  = snpstringallvarsposService.getSNPsInChromosome( chr.toString(),  listpos, snptype);
//			}
//			else if(colLocus!=null && !colLocus.isEmpty()) {
//				AppContext.resetTimer("getSNPsString start3b");
//				snpsposlist  = snpstringallvarsposService.getSNPsInChromosome( chr.toString(),  colLocus, snptype);
//			}
//			else {
//				AppContext.resetTimer("getSNPsString start4");
//				//snpsposlist  = snpstringallvarsposService.getSNPs(colVarids, chr.toString(), start.intValue(), end.intValue(),  snptype, -1, -1);
//				snpsposlist  = snpstringallvarsposService.getSNPs(chr.toString(), start.intValue(), end.intValue(),  snptype, -1, -1);
//			}
//			
//			//AppContext.debug("colvarids=" + colVarids.toString());
//		}
//		
//		if(snpsposlist==null) throw new RuntimeException("snpsposlist==null");
//		
//		if(snpsposlist.isEmpty()) return new SNPsStringData();
//		
//		//if(snpsposlist.isEmpty()) return new SNPsStringData("",  new HashMap(), new HashMap(), new HashMap(), new HashSet());
//		//if(snpsposlist.isEmpty()) return new VariantStringData(); // SNPsStringData("",  new HashMap(), new HashMap(), new HashMap(), new HashSet());
//		
//		
//		
//		//Map mapVarid2Var = varietyfacade.getMapId2Variety();
//
//		
//
//		// get allele column indices from start to end positions
//		SnpsAllvarsPos startpos =  (SnpsAllvarsPos)snpsposlist.get(0);
//		SnpsAllvarsPos endpos =  (SnpsAllvarsPos)snpsposlist.get( snpsposlist.size()-1 );
//
//		// if recount
//		String strRef=null;
//		Map<Float,Map> mapMis2Vars = new TreeMap();
//		
//		int refLength=-1;
//		
//		AppContext.debug( snpsposlist.size() + " snpposlist, pos between " +startpos.getPos() +  "-" + endpos.getPos() + "  index between " + startpos.getAlleleIndex() + "-" + endpos.getAlleleIndex());
//		
//		
//			
//			int indxs[] = new int[snpsposlist.size()];
//			List<int[]> listStartStop = new ArrayList();
//			int indxscount = 0;
//			//Map<Long, Integer> mapPos2Idx = new HashMap();
//			//Map<BigDecimal, Integer> mapPos2Idx = new HashMap();
//			Map<Integer, BigDecimal> mapIdx2Pos = new HashMap();
//			//Map<Integer, BigDecimal> mapIdx2Cromosome = new HashMap();
//			Map<BigDecimal, Integer> mapSnpid2TableIdx = new HashMap();
//			Map<Integer, SnpsAllvarsPos> mapIdx2SnpRefposindex = new HashMap();
//			
//			Iterator<SnpsAllvarsPos> itSnppos =snpsposlist.iterator();
//			StringBuffer buffRef = new StringBuffer();
//			
//			int previdx=-100;
//			int laststart=-100;
//			while(itSnppos.hasNext()) {
//				SnpsAllvarsPos snppos = itSnppos.next(); 
//				buffRef.append( snppos.getRefnuc());
//				indxs[indxscount] =  snppos.getAlleleIndex().intValue();
//				//mapPos2Idx.put(snppos.getPos().longValue(), indxscount);
//				mapSnpid2TableIdx.put(snppos.getSnpFeatureId(), indxscount);
//				mapIdx2Pos.put(indxscount, snppos.getPos());
//				//mapIdx2Cromosome.put( indxscount, snppos.getChromosome());
//				mapIdx2SnpRefposindex.put(indxscount, snppos);
//				
//				if(indxs[indxscount]==(previdx+1)) {
//					previdx=indxs[indxscount];
//				} else {
//					if(laststart>=0 && previdx>=0) {
//						listStartStop.add(new int[]{laststart, previdx});
//					}
//					laststart=indxs[indxscount];
//					previdx=indxs[indxscount];
//				}
//				indxscount++;
//			}
//			listStartStop.add(new int[]{laststart, previdx});
//			
//			int intStartStopIdx[][] = new int[listStartStop.size()][2];
//			Iterator<int[]> itStartStop = listStartStop.iterator();
//			int sscount=0;
//			while(itStartStop.hasNext()) {
//				intStartStopIdx[sscount] = itStartStop.next();
//				AppContext.debug("idxrange " + intStartStopIdx[sscount][0] + "-" + intStartStopIdx[sscount][1]);
//				sscount++;
//			}
//			
//			strRef = buffRef.toString();
//			refLength = strRef.length();
//			
//			
//			String filename="";
//			String filename_allele2="";
//			
//			
//			Map  mapVarid2Snpsstr = null;
//			Map mapVarid2Snpsstr_allele2 = null;
//			
//			AppContext.resetTimer(" to get position indexes from database..");
//			
//			// get snpstring for each varieties
//			// get allele2 for heterozygous varieties
//			snpsheteroDAO = (SnpsHeteroAllvarsDAO)AppContext.checkBean(snpsheteroDAO, "SnpsHeteroAllvarsDAO");
//			
//			//if(params.isbExcludeSynonymous() || params.isbGraySynonymous()) {
//			//if(params.isbAllSnps() || params.isbHighlightNonsynSnps() || params.isbNonsynSnps() || params.isbNonsynPlusSpliceSnps()) { 				
//			if(true) {
//				snpsnonsynDAO = (SnpsNonsynAllvarsDAO) AppContext.checkBean(snpsnonsynDAO, "SnpsNonsynAllvarsDAO");
//				snpsinexonDAO = (SnpsInExonDAO) AppContext.checkBean(snpsinexonDAO, "SnpsInExonDAO");
//			}
//			
//			
//			
//		
//			Set heteroSnps = null;
//			Set nonsynAllele = null;
//			Set inexonSnps = null;
//			
//			// using hdf5
//			if(  (setPositions!=null && !setPositions.isEmpty())  || (colLocus!=null && !colLocus.isEmpty()) ) {
//				if(setPositions!=null && !setPositions.isEmpty()) {
//					if(colVarids!=null && !colVarids.isEmpty() ) {
//						AppContext.resetTimer("using readSNPString1 start");
//						mapVarid2Snpsstr = snpstrSnpsAllele1AllvarsDAO.readSNPString((Set)colVarids, chr, indxs);
//						mapVarid2Snpsstr_allele2= snpstrSnpsAllele2AllvarsDAO.readSNPString((Set)colVarids, chr,  indxs );
//						AppContext.resetTimer("using readSNPString1 end");
//					}
//					else {
//						AppContext.resetTimer("using readSNPString2 start");
//						mapVarid2Snpsstr = snpstrSnpsAllele1AllvarsDAO.readSNPString(chr,  indxs);
//						mapVarid2Snpsstr_allele2=  snpstrSnpsAllele2AllvarsDAO.readSNPString(chr,  indxs);
//						AppContext.resetTimer("using readSNPString2 end");
//					}
//				} else if(colLocus!=null && !colLocus.isEmpty()) {
//					if(colVarids!=null && !colVarids.isEmpty() ) {
//						AppContext.resetTimer("using readSNPString1b start");
//						mapVarid2Snpsstr = snpstrSnpsAllele1AllvarsDAO.readSNPString((Set)colVarids, chr, intStartStopIdx);
//						mapVarid2Snpsstr_allele2= snpstrSnpsAllele2AllvarsDAO.readSNPString((Set)colVarids, chr,  intStartStopIdx );
//						AppContext.resetTimer("using readSNPString1b end");
//					}
//					else {
//						AppContext.resetTimer("using readSNPString2b start");
//						mapVarid2Snpsstr = snpstrSnpsAllele1AllvarsDAO.readSNPString(chr,  intStartStopIdx);
//						mapVarid2Snpsstr_allele2=  snpstrSnpsAllele2AllvarsDAO.readSNPString(chr,  intStartStopIdx);
//						AppContext.resetTimer("using readSNPString2b end");
//					}
//				}
//				
//				if(true) {			
//					if(setPositions!=null && !setPositions.isEmpty()) {
//					nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByChrPosIn(chr, listpos);
//					inexonSnps = snpsinexonDAO.getSnps(chr, listpos);
//					} 
//					else if(colLocus!=null && !colLocus.isEmpty()) {
//					nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByChrPosIn(chr, colLocus);
//					inexonSnps = snpsinexonDAO.getSnps(chr, colLocus);
//					} 
//					AppContext.resetTimer("to read nonsynonymous allele, inexon  from  database..");
//				}
//			}
//			else {
//				if(colVarids!=null && !colVarids.isEmpty() )
//				{
//					AppContext.resetTimer("using readSNPString3 start");
//						mapVarid2Snpsstr = snpstrSnpsAllele1AllvarsDAO.readSNPString((Set)colVarids, chr, startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());
//						mapVarid2Snpsstr_allele2 = snpstrSnpsAllele2AllvarsDAO.readSNPString((Set)colVarids, chr, startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());
//					AppContext.resetTimer("using readSNPString3 end");
//				}
//				else {
//					AppContext.resetTimer("using readSNPString4 start");
//						mapVarid2Snpsstr = snpstrSnpsAllele1AllvarsDAO.readSNPString(chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());
//						mapVarid2Snpsstr_allele2 = snpstrSnpsAllele2AllvarsDAO.readSNPString(chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());
//				}
//				
//				AppContext.resetTimer("to read allele2 database..");
//				if(true) {					
//					nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByChrPosBetween(chr, start.intValue(), end.intValue());
//					inexonSnps = snpsinexonDAO.getSnps(chr,start.intValue(), end.intValue()); 
//					AppContext.resetTimer("to read nonsynonymous allele, inexon  from  database..");
//				}
//			}
//
//			//Iterator itSnpstr = mapVarid2Snpsstr.values().iterator();
//			//while(itSnpstr.hasNext()) {
//			//	AppContext.debug(  itSnpstr.next().toString() );
//			//	break;
//			//}
//			
//			 
//			// filter varieties here
//			//if(datatype == SnpcoreRefposindexDAO.TYPE_3KALLSNP && colVarids!=null && !colVarids.isEmpty() && heteroSnps!=null) {
//			if( colVarids!=null && !colVarids.isEmpty() && heteroSnps!=null) {
//				Iterator<SnpsHeteroAllele2> itHetero = heteroSnps.iterator();
//				Set setNewAllele2 = new LinkedHashSet();
//				while(itHetero.hasNext()) {
//					SnpsHeteroAllele2 all2 =  itHetero.next();
//					if(colVarids.contains(all2.getVar()) )
//						setNewAllele2.add( all2 );
//				}
//				heteroSnps = setNewAllele2;	
//			}
//			
//			
//			// non-synonymous alleles for given table index
//			Map<Integer,Set<Character>> mapIdx2NonsynAlleles = new TreeMap();
//			
//			//mapIdx2NonsynAlleles = new HashMap();
//			Set<Integer> setSnpInExonTableIdx = null;
//			//if(params.isbExcludeSynonymous() || params.isbGraySynonymous()) {
//			//if(params.isbAllSnps() || params.isbHighlightNonsynSnps() || params.isbNonsynSnps() || params.isbNonsynPlusSpliceSnps()) {
//			if(true) {
//				
//				setSnpInExonTableIdx = new TreeSet();
//				Iterator<Snp> itInexon = inexonSnps.iterator();
//				while(itInexon.hasNext()) {
//					Integer tableidx =  mapSnpid2TableIdx.get( itInexon.next().getSnpFeatureId());
//					if(tableidx!=null)
//						setSnpInExonTableIdx.add( tableidx );
//				}
//				
//				Iterator<SnpsNonsynAllele> itNonsyn = nonsynAllele.iterator();
//				while(itNonsyn.hasNext()) {
//					SnpsNonsynAllele nonsynallele = itNonsyn.next();
//					
//					Integer tableidx = mapSnpid2TableIdx.get( nonsynallele.getSnp());
//					if(tableidx==null) continue;
//					
//					Set<Character> alleles =  mapIdx2NonsynAlleles.get( tableidx  );
//					if(alleles==null) {
//						alleles = new HashSet();
//						mapIdx2NonsynAlleles.put( mapSnpid2TableIdx.get( nonsynallele.getSnp() )  , alleles);
//					}
//					alleles.add( nonsynallele.getAllele() );
//				}
//			}
//			
//			if(nonsynAllele!=null) AppContext.debug( nonsynAllele.size() + " non-synonymous alleles, " + mapSnpid2TableIdx.size() + " nonsys alleles positions/idx");
//			
//
//			Map<BigDecimal,Map> mapVarid2SnpsAllele2str = new HashMap();
//			if(heteroSnps!=null) {
//				Iterator<SnpsHeteroAllele2> itSnp = heteroSnps.iterator();
//				while(itSnp.hasNext()) {
//					SnpsHeteroAllele2 snpallele2 = itSnp.next();
//					Map mapTableidx2Nuc = mapVarid2SnpsAllele2str.get( snpallele2.getVar() );
//					if(mapTableidx2Nuc==null) {
//						mapTableidx2Nuc = new HashMap();
//						mapVarid2SnpsAllele2str.put(snpallele2.getVar() , mapTableidx2Nuc);
//					}
//					mapTableidx2Nuc.put(mapSnpid2TableIdx.get( snpallele2.getSnp() )  , snpallele2.getNuc() );
//				}
//			} else if(mapVarid2Snpsstr_allele2!=null) {
//				
//				Iterator itVarid = mapVarid2Snpsstr_allele2.keySet().iterator();
//				while(itVarid.hasNext()) {
//					BigDecimal varid= (BigDecimal)itVarid.next();
//					
//					if(params.includeDataset(SnpsAllvarsPosDAO.DATASET_SNPINDELV1)) {					
//					
//						String allele2str = (String)mapVarid2Snpsstr_allele2.get( varid );
//						for(int iStr=0; iStr<allele2str.length(); iStr++) {
//							char allele2i =allele2str.charAt(iStr);
//							if(allele2i!='*' && allele2i!='0' && allele2i!='.' && allele2i!=' ') {
//						
//								Map mapTableidx2Nuc = mapVarid2SnpsAllele2str.get( varid );
//								if(mapTableidx2Nuc==null) {
//									mapTableidx2Nuc = new HashMap();
//									mapVarid2SnpsAllele2str.put(varid , mapTableidx2Nuc);
//								}
//								mapTableidx2Nuc.put( iStr, allele2i);
//							}
//						}
//					
//					} else
//					if(params.includeDataset(SnpsAllvarsPosDAO.DATASET_SNPINDELV2)) {					
//						// revised for SNPv2 (allele2!=0 for allele1==allele2)
//						String allele1str = (String)mapVarid2Snpsstr.get( varid );
//						String allele2str = (String)mapVarid2Snpsstr_allele2.get( varid );
//						for(int iStr=0; iStr<allele2str.length(); iStr++) {
//							char allele2i =allele2str.charAt(iStr);
//							char allele1i =allele1str.charAt(iStr);
//							if(allele1i!='0' && allele2i!='0' && allele1i!=allele2i) {
//							//if(allele2i!='*' && allele2i!='0' && allele2i!='.' && allele2i!=' ') {
//						
//								Map mapTableidx2Nuc = mapVarid2SnpsAllele2str.get( varid );
//								if(mapTableidx2Nuc==null) {
//									mapTableidx2Nuc = new HashMap();
//									mapVarid2SnpsAllele2str.put(varid , mapTableidx2Nuc);
//								}
//								mapTableidx2Nuc.put( iStr, allele2i);
//							}
//						}
//					}
//				}
//			}; // else throw new RuntimeException("heteroSnps==null and mapVarid2Snpsstr_allele2==null ... no allele2 data");
//			
//			
//			// get splice variants
//			Set setSpliceAcceptorsPos = null;
//			Set setSpliceDonorsPos = null;
//			//if(params.isbColorSpliceSNP()) {
//			if(true) {
//				
//				Set setSpliceAcceptors= null;
//				Set setSpliceDonors= null;
//				
//				snpsspliceacceptorDAO = (SnpsSpliceAcceptorDAO)AppContext.checkBean(snpsspliceacceptorDAO, "SnpsSpliceAcceptorDAO");
//				snpssplicedonorDAO = (SnpsSpliceDonorDAO)AppContext.checkBean(snpssplicedonorDAO, "SnpsSpliceDonorDAO");
//				if(setPositions!=null && !setPositions.isEmpty()) {
//					setSpliceAcceptors = snpsspliceacceptorDAO.getSNPsIn(chr, setPositions);
//					setSpliceDonors = snpssplicedonorDAO.getSNPsIn(chr, setPositions);
//				}
//				else if(colLocus!=null && !colLocus.isEmpty()) {
//						setSpliceAcceptors = snpsspliceacceptorDAO.getSNPsIn(chr, colLocus);
//						setSpliceDonors = snpssplicedonorDAO.getSNPsIn(chr, colLocus);							
//				} else {
//					setSpliceAcceptors = snpsspliceacceptorDAO.getSNPsBetween(chr, start.intValue(), end.intValue());  
//					setSpliceDonors = snpssplicedonorDAO.getSNPsBetween(chr, start.intValue(), end.intValue());							
//				}
//				
//				setSpliceAcceptorsPos = new HashSet();
//				setSpliceDonorsPos = new HashSet();
//				Iterator<SnpsSpliceAcceptor>  itAcceptors = setSpliceAcceptors.iterator();
//				while(itAcceptors.hasNext()) {
//					SnpsSpliceAcceptor acc = itAcceptors.next();
//					setSpliceAcceptorsPos.add( acc.getPos());
//				}
//				Iterator<SnpsSpliceDonor>  itDonor = setSpliceDonors.iterator();
//				while(itDonor.hasNext()) {
//					SnpsSpliceDonor acc = itDonor.next();
//					setSpliceDonorsPos.add( acc.getPos());
//				}
//				
//			}
//			
//			
//			//VariantStringData vardata = new VariantStringData(snpsposlist, );
//
//			SNPsStringData snpstrdata = new  SNPsStringData(snpsposlist, mapIdx2Pos, strRef, mapVarid2Snpsstr, mapVarid2SnpsAllele2str,
//					mapIdx2NonsynAlleles, setSnpInExonTableIdx, setSpliceDonorsPos,  setSpliceAcceptorsPos);
//			
//			return snpstrdata;
//
//		
//	}
		
	
	
//	private SNPsStringData getSNPsStringDataBackup(GenotypeQueryParams params, Collection colVarids, String chr, BigDecimal start, BigDecimal end, Collection setPositions, Collection colLocus) { //, boolean exactMismatch, int firstRow, int maxRows) {
//		// TODO Auto-generated method stub
//
//		// get snp positions/index
//		snpstringallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpstringallvarsposService, "VSnpRefposindexDAO") ;
//		
//		SnpsStringDAO snpstrSnpsAllele1AllvarsDAO;
//		SnpsStringDAO snpstrSnpsAllele2AllvarsDAO;
//		BigDecimal snptype; 
//		if(params.isbCoreonly()) {
//			snpstrSnpsAllele1AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrCoresnpsAllele1AllvarsDAO, "H5SNPCoreAllele1DAO");
//			snpstrSnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrCoresnpsAllele1AllvarsDAO, "H5SNPCoreAllele2DAO");
//			snptype=SnpsAllvarsPosDAO.TYPE_3KCORESNP;
//		} else {
//			snptype=SnpsAllvarsPosDAO.TYPE_3KALLSNP;
//			snpstrSnpsAllele1AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrAllsnpsAllele1AllvarsDAO, "H5SNPUniAllele1DAO");
//			snpstrSnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrAllsnpsAllele2AllvarsDAO, "H5SNPUniAllele2DAO");
//		}
//		    
//		
//		List<SnpsAllvarsPos> snpsposlist = null;
//		List listpos = null;
//		
//		if(colVarids==null || colVarids.isEmpty()) {
////			if(colLocus!=null && !colLocus.isEmpty()) {
////				AppContext.resetTimer("getSNPsString start5");
////				snpsposlist  = snpstringallvarsposService.getSNPsInChromosome(colLocus, snptype);
////						
////			} else 
//			if(setPositions!=null && !setPositions.isEmpty()) {
//				listpos = new ArrayList();
//				listpos.addAll(new TreeSet(setPositions));
//				AppContext.resetTimer("getSNPsString start1");
//				snpsposlist  = snpstringallvarsposService.getSNPsInChromosome(chr.toString(),  listpos, snptype);
//			}
//			else {
//				AppContext.resetTimer("getSNPsString start2");
//				snpsposlist  = snpstringallvarsposService.getSNPs(chr.toString(), start.intValue(), end.intValue(),   snptype, -1, -1);
//			}
//		} else {
////			if(colLocus!=null && !colLocus.isEmpty()) {
////				AppContext.resetTimer("getSNPsString start6");
////				snpsposlist  = snpstringallvarsposService.getSNPsInChromosome(colLocus, snptype);
////				
////			} else
//			if(setPositions!=null && !setPositions.isEmpty()) {
//				listpos = new ArrayList();
//				listpos.addAll(new TreeSet(setPositions));
//				AppContext.resetTimer("getSNPsString start3");
//				snpsposlist  = snpstringallvarsposService.getSNPsInChromosome( chr.toString(),  listpos, snptype);
//			}
//			else {
//				AppContext.resetTimer("getSNPsString start4");
//				//snpsposlist  = snpstringallvarsposService.getSNPs(colVarids, chr.toString(), start.intValue(), end.intValue(),  snptype, -1, -1);
//				snpsposlist  = snpstringallvarsposService.getSNPs(chr.toString(), start.intValue(), end.intValue(),  snptype, -1, -1);
//			}
//			
//			//AppContext.debug("colvarids=" + colVarids.toString());
//		}
//		
//		if(snpsposlist==null) throw new RuntimeException("snpsposlist==null");
//		
//		
//		
//		AppContext.debug(snpsposlist.size() + " snpsposlists");
//		
//		if(snpsposlist.isEmpty()) return new SNPsStringData();
//		
//		//if(snpsposlist.isEmpty()) return new SNPsStringData("",  new HashMap(), new HashMap(), new HashMap(), new HashSet());
//		//if(snpsposlist.isEmpty()) return new VariantStringData(); // SNPsStringData("",  new HashMap(), new HashMap(), new HashMap(), new HashSet());
//		
//		
//		
//		//Map mapVarid2Var = varietyfacade.getMapId2Variety();
//
//		
//
//		// get allele column indices from start to end positions
//		SnpsAllvarsPos startpos =  (SnpsAllvarsPos)snpsposlist.get(0);
//		SnpsAllvarsPos endpos =  (SnpsAllvarsPos)snpsposlist.get( snpsposlist.size()-1 );
//
//		// if recount
//		String strRef=null;
//		Map<Float,Map> mapMis2Vars = new TreeMap();
//		
//		int refLength=-1;
//		
//		AppContext.debug( snpsposlist.size() + " snpposlist, pos between " +startpos.getPos() +  "-" + endpos.getPos() + "  index between " + startpos.getAlleleIndex() + "-" + endpos.getAlleleIndex());
//		
//		
//			
//			int indxs[] = new int[snpsposlist.size()];
//			int indxscount = 0;
//			//Map<Long, Integer> mapPos2Idx = new HashMap();
//			//Map<BigDecimal, Integer> mapPos2Idx = new HashMap();
//			Map<Integer, BigDecimal> mapIdx2Pos = new HashMap();
//			Map<BigDecimal, Integer> mapSnpid2TableIdx = new HashMap();
//			
//			Iterator<SnpsAllvarsPos> itSnppos =snpsposlist.iterator();
//			StringBuffer buffRef = new StringBuffer();
//			
//			while(itSnppos.hasNext()) {
//				SnpsAllvarsPos snppos = itSnppos.next(); 
//				buffRef.append( snppos.getRefnuc());
//				indxs[indxscount] =  snppos.getAlleleIndex().intValue();
//				//mapPos2Idx.put(snppos.getPos().longValue(), indxscount);
//				mapSnpid2TableIdx.put(snppos.getSnpFeatureId(), indxscount);
//				mapIdx2Pos.put(indxscount, snppos.getPos());
//				indxscount++;
//			}
//			strRef = buffRef.toString();
//			refLength = strRef.length();
//			
//			
//			String filename="";
//			String filename_allele2="";
//			
//			
//			Map  mapVarid2Snpsstr = null;
//			Map mapVarid2Snpsstr_allele2 = null;
//			
//			AppContext.resetTimer(" to get position indexes from database..");
//			
//			// get snpstring for each varieties
//			// get allele2 for heterozygous varieties
//			snpsheteroDAO = (SnpsHeteroAllvarsDAO)AppContext.checkBean(snpsheteroDAO, "SnpsHeteroAllvarsDAO");
//			
//			//if(params.isbExcludeSynonymous() || params.isbGraySynonymous()) {
//			//if(params.isbAllSnps() || params.isbHighlightNonsynSnps() || params.isbNonsynSnps() || params.isbNonsynPlusSpliceSnps()) { 				
//			if(true) {
//				snpsnonsynDAO = (SnpsNonsynAllvarsDAO) AppContext.checkBean(snpsnonsynDAO, "SnpsNonsynAllvarsDAO");
//				snpsinexonDAO = (SnpsInExonDAO) AppContext.checkBean(snpsinexonDAO, "SnpsInExonDAO");
//			}
//			
//			
//			
//		
//			Set heteroSnps = null;
//			Set nonsynAllele = null;
//			Set inexonSnps = null;
//			
//			// using hdf5
//			if(setPositions!=null && !setPositions.isEmpty()) {
//				if(colVarids!=null && !colVarids.isEmpty() ) {
//					
//					AppContext.resetTimer("using readSNPString1 start");
//					//	mapVarid2Snpsstr = readSNPString(limitVarIds, chr,  indxs , filename);
//					
//						mapVarid2Snpsstr = snpstrSnpsAllele1AllvarsDAO.readSNPString((Set)colVarids, chr, indxs);
//						//heteroSnps = snpsheteroDAO.findSnpsHeteroAllvarsChrPosIn(chr, listpos,  datatype);	
//						mapVarid2Snpsstr_allele2= snpstrSnpsAllele2AllvarsDAO.readSNPString((Set)colVarids, chr,  indxs );
//
//					AppContext.resetTimer("using readSNPString1 end");
//
//				}
//				else {
//					AppContext.resetTimer("using readSNPString2 start");
//					//	mapVarid2Snpsstr = readSNPString(chr,  indxs, filename);
//
//
//						mapVarid2Snpsstr = snpstrSnpsAllele1AllvarsDAO.readSNPString(chr,  indxs);
//						//heteroSnps = snpsheteroDAO.findSnpsHeteroAllvarsChrPosIn(chr, listpos,  datatype);
//						mapVarid2Snpsstr_allele2=  snpstrSnpsAllele2AllvarsDAO.readSNPString(chr,  indxs);
//
//					AppContext.resetTimer("using readSNPString2 end");
//				}
//				
//				//if(params.isbExcludeSynonymous() || params.isbGraySynonymous()) {
//				//if(params.isbAllSnps() || params.isbHighlightNonsynSnps() || params.isbNonsynSnps() || params.isbNonsynPlusSpliceSnps()) {		
//				if(true) {					
//					nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByChrPosIn(chr, listpos);
//					inexonSnps = snpsinexonDAO.getSnps(chr, listpos);
//					AppContext.resetTimer("to read nonsynonymous allele, inexon  from  database..");
//				}
//				
//				
//				
//			}
//			else {
//				if(colVarids!=null && !colVarids.isEmpty() )
//				{
//					AppContext.resetTimer("using readSNPString3 start");
//					//mapVarid2Snpsstr = readSNPString(limitVarIds, chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue(), filename);
//
//
//						mapVarid2Snpsstr = snpstrSnpsAllele1AllvarsDAO.readSNPString((Set)colVarids, chr, startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());
//						//heteroSnps = snpsheteroDAO.findSnpsHeteroVarsChrPosBetween(chr, start, end, limitVarIds, datatype);
//						//heteroSnps = snpsheteroDAO.findSnpsHeteroAllvarsChrPosBetween(chr, start, end, datatype);
//						mapVarid2Snpsstr_allele2 = snpstrSnpsAllele2AllvarsDAO.readSNPString((Set)colVarids, chr, startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());
//
//					AppContext.resetTimer("using readSNPString3 end");
//				}
//				else {
//					AppContext.resetTimer("using readSNPString4 start");
//					//mapVarid2Snpsstr = readSNPString(chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue(), filename);
//					
//
//						mapVarid2Snpsstr = snpstrSnpsAllele1AllvarsDAO.readSNPString(chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());
//						//heteroSnps = snpsheteroDAO.findSnpsHeteroAllvarsChrPosBetween(chr, start, end, datatype);
//						mapVarid2Snpsstr_allele2 = snpstrSnpsAllele2AllvarsDAO.readSNPString(chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());
//
//				}
//				
//				AppContext.resetTimer("to read allele2 database..");
//				
//				//if(params.isbExcludeSynonymous() || params.isbGraySynonymous()) {
//				//if(params.isbAllSnps() || params.isbHighlightNonsynSnps() || params.isbNonsynSnps() || params.isbNonsynPlusSpliceSnps()) {
//				if(true) {					
//					nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByChrPosBetween(chr, start.intValue(), end.intValue());
//					inexonSnps = snpsinexonDAO.getSnps(chr,start.intValue(), end.intValue()); 
//					AppContext.resetTimer("to read nonsynonymous allele, inexon  from  database..");
//				}
//			}
//
//			//Iterator itSnpstr = mapVarid2Snpsstr.values().iterator();
//			//while(itSnpstr.hasNext()) {
//			//	AppContext.debug(  itSnpstr.next().toString() );
//			//	break;
//			//}
//			
//			 
//			// filter varieties here
//			//if(datatype == SnpcoreRefposindexDAO.TYPE_3KALLSNP && colVarids!=null && !colVarids.isEmpty() && heteroSnps!=null) {
//			if( colVarids!=null && !colVarids.isEmpty() && heteroSnps!=null) {
//				Iterator<SnpsHeteroAllele2> itHetero = heteroSnps.iterator();
//				Set setNewAllele2 = new LinkedHashSet();
//				while(itHetero.hasNext()) {
//					SnpsHeteroAllele2 all2 =  itHetero.next();
//					if(colVarids.contains(all2.getVar()) )
//						setNewAllele2.add( all2 );
//				}
//				heteroSnps = setNewAllele2;	
//			}
//			
//			
//			// non-synonymous alleles for given table index
//			Map<Integer,Set<Character>> mapIdx2NonsynAlleles = new TreeMap();
//			
//			//mapIdx2NonsynAlleles = new HashMap();
//			Set<Integer> setSnpInExonTableIdx = null;
//			//if(params.isbExcludeSynonymous() || params.isbGraySynonymous()) {
//			//if(params.isbAllSnps() || params.isbHighlightNonsynSnps() || params.isbNonsynSnps() || params.isbNonsynPlusSpliceSnps()) {
//			if(true) {
//				
//				setSnpInExonTableIdx = new TreeSet();
//				Iterator<Snp> itInexon = inexonSnps.iterator();
//				while(itInexon.hasNext()) {
//					Integer tableidx =  mapSnpid2TableIdx.get( itInexon.next().getSnpFeatureId());
//					if(tableidx!=null)
//						setSnpInExonTableIdx.add( tableidx );
//				}
//				
//				Iterator<SnpsNonsynAllele> itNonsyn = nonsynAllele.iterator();
//				while(itNonsyn.hasNext()) {
//					SnpsNonsynAllele nonsynallele = itNonsyn.next();
//					
//					Integer tableidx = mapSnpid2TableIdx.get( nonsynallele.getSnp());
//					if(tableidx==null) continue;
//					
//					Set<Character> alleles =  mapIdx2NonsynAlleles.get( tableidx  );
//					if(alleles==null) {
//						alleles = new HashSet();
//						mapIdx2NonsynAlleles.put( mapSnpid2TableIdx.get( nonsynallele.getSnp() )  , alleles);
//					}
//					alleles.add( nonsynallele.getAllele() );
//				}
//			}
//			
//			if(nonsynAllele!=null) AppContext.debug( nonsynAllele.size() + " non-synonymous alleles, " + mapSnpid2TableIdx.size() + " nonsys alleles positions/idx");
//			
//
//			Map<BigDecimal,Map> mapVarid2SnpsAllele2str = new HashMap();
//			if(heteroSnps!=null) {
//				Iterator<SnpsHeteroAllele2> itSnp = heteroSnps.iterator();
//				while(itSnp.hasNext()) {
//					SnpsHeteroAllele2 snpallele2 = itSnp.next();
//					Map mapTableidx2Nuc = mapVarid2SnpsAllele2str.get( snpallele2.getVar() );
//					if(mapTableidx2Nuc==null) {
//						mapTableidx2Nuc = new HashMap();
//						mapVarid2SnpsAllele2str.put(snpallele2.getVar() , mapTableidx2Nuc);
//					}
//					mapTableidx2Nuc.put(mapSnpid2TableIdx.get( snpallele2.getSnp() )  , snpallele2.getNuc() );
//				}
//			} else if(mapVarid2Snpsstr_allele2!=null) {
//				
//				Iterator itVarid = mapVarid2Snpsstr_allele2.keySet().iterator();
//				while(itVarid.hasNext()) {
//					BigDecimal varid= (BigDecimal)itVarid.next();
//					
//					String allele2str = (String)mapVarid2Snpsstr_allele2.get( varid );
//					for(int iStr=0; iStr<allele2str.length(); iStr++) {
//						char allele2i =allele2str.charAt(iStr);
//						if(allele2i!='*' && allele2i!='0' && allele2i!='.' && allele2i!=' ') {
//					
//							Map mapTableidx2Nuc = mapVarid2SnpsAllele2str.get( varid );
//							if(mapTableidx2Nuc==null) {
//								mapTableidx2Nuc = new HashMap();
//								mapVarid2SnpsAllele2str.put(varid , mapTableidx2Nuc);
//							}
//							mapTableidx2Nuc.put( iStr, allele2i);
//						}
//					}
//				}
//			}; // else throw new RuntimeException("heteroSnps==null and mapVarid2Snpsstr_allele2==null ... no allele2 data");
//			
//			
//			// get splice variants
//			Set setSpliceAcceptorsPos = null;
//			Set setSpliceDonorsPos = null;
//			//if(params.isbColorSpliceSNP()) {
//			if(true) {
//				
//				Set setSpliceAcceptors= null;
//				Set setSpliceDonors= null;
//				
//				snpsspliceacceptorDAO = (SnpsSpliceAcceptorDAO)AppContext.checkBean(snpsspliceacceptorDAO, "SnpsSpliceAcceptorDAO");
//				snpssplicedonorDAO = (SnpsSpliceDonorDAO)AppContext.checkBean(snpssplicedonorDAO, "SnpsSpliceDonorDAO");
//				if(setPositions!=null && !setPositions.isEmpty()) {
//					setSpliceAcceptors = snpsspliceacceptorDAO.getSNPsIn(chr, setPositions);
//					setSpliceDonors = snpssplicedonorDAO.getSNPsIn(chr, setPositions);							
//				} else {
//					setSpliceAcceptors = snpsspliceacceptorDAO.getSNPsBetween(chr, start.intValue(), end.intValue());  
//					setSpliceDonors = snpssplicedonorDAO.getSNPsBetween(chr, start.intValue(), end.intValue());							
//				}
//				
//				setSpliceAcceptorsPos = new HashSet();
//				setSpliceDonorsPos = new HashSet();
//				Iterator<SnpsSpliceAcceptor>  itAcceptors = setSpliceAcceptors.iterator();
//				while(itAcceptors.hasNext()) {
//					SnpsSpliceAcceptor acc = itAcceptors.next();
//					setSpliceAcceptorsPos.add( acc.getPos());
//				}
//				Iterator<SnpsSpliceDonor>  itDonor = setSpliceDonors.iterator();
//				while(itDonor.hasNext()) {
//					SnpsSpliceDonor acc = itDonor.next();
//					setSpliceDonorsPos.add( acc.getPos());
//				}
//				
//			}
//			
//			
//			//VariantStringData vardata = new VariantStringData(snpsposlist, );
//
//			SNPsStringData snpstrdata = new  SNPsStringData(snpsposlist, mapIdx2Pos, strRef, mapVarid2Snpsstr, mapVarid2SnpsAllele2str,
//					mapIdx2NonsynAlleles, setSnpInExonTableIdx, setSpliceDonorsPos,  setSpliceAcceptorsPos);
//			
//			return snpstrdata;
//
//		
//	}
//	

