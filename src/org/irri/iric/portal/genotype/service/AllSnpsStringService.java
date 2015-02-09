package org.irri.iric.portal.genotype.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.SnpsAllvarsDAO;
import org.irri.iric.portal.dao.SnpsAllvarsPosDAO;
import org.irri.iric.portal.dao.SnpsHeteroAllvarsDAO;
import org.irri.iric.portal.dao.SnpsInExonDAO;
import org.irri.iric.portal.dao.SnpsNonsynAllvarsDAO;
import org.irri.iric.portal.dao.SnpsStringDAO;
import org.irri.iric.portal.domain.Snp;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsHeteroAllele2;
import org.irri.iric.portal.domain.SnpsNonsynAllele;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.domain.SnpsStringAllvarsImpl;
import org.irri.iric.portal.domain.VariantSnpsStringData;
import org.irri.iric.portal.domain.VariantStringData;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.flatfile.dao.SnpcoreRefposindexDAO;
import org.irri.iric.portal.flatfile.domain.VSnpRefposindex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("SnpsStringService")
public class AllSnpsStringService implements VariantStringService {

	//protected boolean isCore = false;
	//protected boolean isMismatchOnly = false;
	//protected Collection listpos;
	//protected Collection limitVarIds;
	
	@Autowired
	@Qualifier("VSnpRefposindexDAO")		// snps reference, allele position file index
	private SnpsAllvarsPosDAO snpstringallvarsposService;

	@Autowired
	private SnpsHeteroAllvarsDAO snpsheteroDAO;
	
	@Autowired
	private SnpsNonsynAllvarsDAO snpsnonsynDAO;
	
	@Autowired
	private SnpsInExonDAO snpsinexonDAO;
	
	@Autowired
	@Qualifier("H5SNPUniAllele1DAO")
	private SnpsStringDAO snpstrAllsnpsAllele1AllvarsDAO;

	@Autowired
	@Qualifier("H5SNPUniAllele2DAO")
	private SnpsStringDAO snpstrAllsnpsAllele2AllvarsDAO;

	@Autowired
	@Qualifier("H5SNPCoreAllele1DAO")
	private SnpsStringDAO snpstrCoresnpsAllele1AllvarsDAO;

	@Autowired
	@Qualifier("H5SNPCoreAllele2DAO")
	private SnpsStringDAO snpstrCoresnpsAllele2AllvarsDAO;
	
	
	
	@Override
	public VariantStringData getVariantString(GenotypeQueryParams params, Collection colVarids, String chr, Long start, Long stop) {
		// TODO Auto-generated method stub
		return getSNPsString( params,  colVarids,  Integer.valueOf(chr),  BigDecimal.valueOf(start),  BigDecimal.valueOf(stop), null);
	}

	@Override
	public VariantStringData getVariantString(GenotypeQueryParams params, String chr, Long start, Long stop) {
		// TODO Auto-generated method stub
		return getSNPsString( params,  null, Integer.valueOf(chr),  BigDecimal.valueOf(start),  BigDecimal.valueOf(stop), null);
	}

	@Override
	public VariantStringData getVariantString(GenotypeQueryParams params, Collection colVarids, String chr, Collection colSnppos) {
		// TODO Auto-generated method stub

		return getSNPsString( params,  colVarids, Integer.valueOf(chr),  null, null, colSnppos);

	}

	@Override
	public VariantStringData getVariantString(GenotypeQueryParams params, String chr, Collection colSnppos) {
		// TODO Auto-generated method stub
		return getSNPsString( params, null, Integer.valueOf(chr),  null, null, colSnppos);
	}


	//private List<SnpsStringAllvars> _getSNPsString(boolean isMismatchOnly, Integer chr, BigDecimal start,
	private VariantStringData getSNPsString(GenotypeQueryParams params, Collection colVarids, Integer chr, BigDecimal start, BigDecimal end, Collection setPositions) { //, boolean exactMismatch, int firstRow, int maxRows) {
		// TODO Auto-generated method stub
		
	
		
		
			SNPsStringData snpstrdata = getSNPsStringData(params, colVarids,  chr,  start,  end,  setPositions); 
			if(snpstrdata==null)  {
				throw new RuntimeException("getSNPsString:  snpstrdata==null");
				//return new VariantStringData();
			}
		 
			Map mapVarid2Snpsstr = snpstrdata.getMapVarid2Snpsstr();
			Set setNonsynIdx=new HashSet();
			Set sortedVarieties = new TreeSet(new SnpsStringAllvarsImplSorter());
			
			Map mapIndex2NonsynAlleles = snpstrdata.getMapIdx2NonsynAlleles();
			
		 	//Iterator<BigDecimal> itVar = mapVarid2Snpsstr.keySet().iterator();
			Iterator itVar = mapVarid2Snpsstr.keySet().iterator();
		 	
			while(itVar.hasNext()) {
				Object ovar = itVar.next();
				BigDecimal var=null;
				if(ovar instanceof BigDecimal)
					var = (BigDecimal)ovar;
				else if(ovar instanceof Variety)
					var = ((Variety)ovar).getVarietyId();

				String snpstr = (String)mapVarid2Snpsstr.get(var);

				//countVarpairMismatch(String var1, String var2, boolean var1isref, String var1allele2str, String var2allele2str, 
				//	Map<Integer,Set<Character>> mapIdx2NonsynAlleles, Set setSnpInExonTableIdx, Set setNonsynIdx, boolean isNonsynOnly);

				Set varNonsynIdx = new HashSet();
				
				double misCount = countVarpairMismatch(snpstrdata.getStrRef(), snpstr, true, null, (Map)snpstrdata.getMapVarid2SnpsAllele2str().get(var),  
						(Map)snpstrdata.getMapIdx2NonsynAlleles(),  snpstrdata.getSetSnpInExonTableIdx(), varNonsynIdx,  params.isbExcludeSynonymous() );
				setNonsynIdx.addAll(varNonsynIdx);
				
				if(!params.isbMismatchonly() || misCount>0) {
					sortedVarieties.add( new SnpsStringAllvarsImpl(var,Long.valueOf(chr), snpstr, 
							BigDecimal.valueOf(misCount) , (Map)snpstrdata.getMapVarid2SnpsAllele2str().get(var), varNonsynIdx) );
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
				mapVariety2Mismatch.put( snpstrvar.getVar(), snpstrvar.getMismatch().intValue());
				ordercount++;
			}
			
//			Map<BigDecimal, BigDecimal> mapVariety2Mismatch,
//			Map<BigDecimal, Integer> mapVariety2Order,
//			List<SnpsAllvarsPos> listPos, Map<Integer, BigDecimal> mapIdx2Pos,
//			List<SnpsStringAllvars> listVariantsString,
//			String strRef,
//			Map<BigDecimal, Map<Integer, Character>> mapVarid2SnpsAllele2str,
//			Map<BigDecimal, Set<Character>> mapIdx2NonsynAlleles,
//			Set<Integer> setSnpInExonTableIdx,
//			Map<Integer, Integer> mapMergedIdx2SnpIdx
			
			List listsortedVarieties = new ArrayList();
			listsortedVarieties.addAll(sortedVarieties);
			VariantStringData vardata = new VariantSnpsStringData(mapVariety2Mismatch, mapVariety2Order , snpstrdata.getListSnpsPos() ,  snpstrdata.getMapIdx2Pos(), listsortedVarieties , 
					snpstrdata.getStrRef(), snpstrdata.getMapVarid2SnpsAllele2str(), snpstrdata.getMapIdx2NonsynAlleles(),  snpstrdata.getSetSnpInExonTableIdx() , null, null);
			
			
			//AppContext.debug( listResult.size() + " sortedvarieties in list, "  + sortedVarieties.size() + " in set" );
			
			return vardata;					
	}
	

	//private List<SnpsStringAllvars> 
	private SNPsStringData getSNPsStringData(GenotypeQueryParams params, Collection colVarids, Integer chr, BigDecimal start, BigDecimal end, Collection setPositions) { //, boolean exactMismatch, int firstRow, int maxRows) {
		// TODO Auto-generated method stub

		// get snp positions/index
		snpstringallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpstringallvarsposService, "VSnpRefposindexDAO") ;
		
		SnpsStringDAO snpstrSnpsAllele1AllvarsDAO;
		SnpsStringDAO snpstrSnpsAllele2AllvarsDAO;
		BigDecimal snptype; 
		if(params.isbCoreonly()) {
			snpstrSnpsAllele1AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrCoresnpsAllele1AllvarsDAO, "H5SNPCoreAllele1DAO");
			snpstrSnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrCoresnpsAllele1AllvarsDAO, "H5SNPCoreAllele2DAO");
			snptype=SnpcoreRefposindexDAO.TYPE_3KCORESNP;
		} else {
			snptype=SnpcoreRefposindexDAO.TYPE_3KALLSNP;
			snpstrSnpsAllele1AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrAllsnpsAllele1AllvarsDAO, "H5SNPUniAllele1DAO");
			snpstrSnpsAllele2AllvarsDAO = (SnpsStringDAO)AppContext.checkBean( snpstrAllsnpsAllele2AllvarsDAO, "H5SNPUniAllele2DAO");
		}
		    
				
		
		
		List<SnpsAllvarsPos> snpsposlist = null;
		List listpos = null;
		
		if(colVarids==null || colVarids.isEmpty()) {
			if(setPositions!=null && !setPositions.isEmpty()) {
				listpos = new ArrayList();
				listpos.addAll(new TreeSet(setPositions));
				AppContext.resetTimer("getSNPsString start1");
				snpsposlist  = snpstringallvarsposService.getSNPsInChromosome(chr.toString(),  listpos, snptype);
			}
			else {
				AppContext.resetTimer("getSNPsString start2");
				snpsposlist  = snpstringallvarsposService.getSNPs(chr.toString(), start.intValue(), end.intValue(),   snptype, -1, -1);
			}
		} else {
			if(setPositions!=null && !setPositions.isEmpty()) {
				listpos = new ArrayList();
				listpos.addAll(new TreeSet(setPositions));
				AppContext.resetTimer("getSNPsString start3");
				snpsposlist  = snpstringallvarsposService.getSNPsInChromosome( chr.toString(),  listpos, snptype);
			}
			else {
				AppContext.resetTimer("getSNPsString start4");
				//snpsposlist  = snpstringallvarsposService.getSNPs(colVarids, chr.toString(), start.intValue(), end.intValue(),  snptype, -1, -1);
				snpsposlist  = snpstringallvarsposService.getSNPs(chr.toString(), start.intValue(), end.intValue(),  snptype, -1, -1);
			}
			
			AppContext.debug("colvarids=" + colVarids.toString());
		}
		
		if(snpsposlist==null) throw new RuntimeException("snpsposlist==null");
		
		if(snpsposlist.isEmpty()) return new SNPsStringData();
		
		//if(snpsposlist.isEmpty()) return new SNPsStringData("",  new HashMap(), new HashMap(), new HashMap(), new HashSet());
		//if(snpsposlist.isEmpty()) return new VariantStringData(); // SNPsStringData("",  new HashMap(), new HashMap(), new HashMap(), new HashSet());
		
		
		
		//Map mapVarid2Var = varietyfacade.getMapId2Variety();

		

		// get allele column indices from start to end positions
		VSnpRefposindex startpos =  (VSnpRefposindex)snpsposlist.get(0);
		VSnpRefposindex endpos =  (VSnpRefposindex)snpsposlist.get( snpsposlist.size()-1 );

		// if recount
		String strRef=null;
		Map<Float,Map> mapMis2Vars = new TreeMap();
		
		int refLength=-1;
		
		AppContext.debug( snpsposlist.size() + " snpposlist, pos between " +startpos.getPos() +  "-" + endpos.getPos() + "  index between " + startpos.getAlleleIndex() + "-" + endpos.getAlleleIndex());
		
		
			
			int indxs[] = new int[snpsposlist.size()];
			int indxscount = 0;
			//Map<Long, Integer> mapPos2Idx = new HashMap();
			//Map<BigDecimal, Integer> mapPos2Idx = new HashMap();
			Map<Integer, BigDecimal> mapIdx2Pos = new HashMap();
			Map<BigDecimal, Integer> mapSnpid2TableIdx = new HashMap();
			
			Iterator itSnppos =snpsposlist.iterator();
			StringBuffer buffRef = new StringBuffer();
			
			while(itSnppos.hasNext()) {
				VSnpRefposindex snppos = (VSnpRefposindex)itSnppos.next(); 
				buffRef.append( snppos.getRefnuc());
				indxs[indxscount] =  snppos.getAlleleIndex().intValue();
				//mapPos2Idx.put(snppos.getPos().longValue(), indxscount);
				mapSnpid2TableIdx.put(snppos.getSnpFeatureId(), indxscount);
				mapIdx2Pos.put(indxscount, snppos.getPos());
				indxscount++;
			}
			strRef = buffRef.toString();
			refLength = strRef.length();
			
			
			String filename="";
			String filename_allele2="";
			
			
			Map  mapVarid2Snpsstr = null;
			Map mapVarid2Snpsstr_allele2 = null;
			
			AppContext.resetTimer(" to get position indexes from database..");
			
			// get snpstring for each varieties
			// get allele2 for heterozygous varieties
			snpsheteroDAO = (SnpsHeteroAllvarsDAO)AppContext.checkBean(snpsheteroDAO, "SnpsHeteroAllvarsDAO");
			
			if(params.isbExcludeSynonymous() || params.isbGraySynonymous()) {
				snpsnonsynDAO = (SnpsNonsynAllvarsDAO) AppContext.checkBean(snpsnonsynDAO, "SnpsNonsynAllvarsDAO");
				snpsinexonDAO = (SnpsInExonDAO) AppContext.checkBean(snpsinexonDAO, "SnpsInExonDAO");
			}
		
			Set heteroSnps = null;
			Set nonsynAllele = null;
			Set inexonSnps = null;
			
			// using hdf5
			if(setPositions!=null && !setPositions.isEmpty()) {
				if(colVarids!=null && !colVarids.isEmpty() ) {
					
					AppContext.resetTimer("using readSNPString1 start");
					//	mapVarid2Snpsstr = readSNPString(limitVarIds, chr,  indxs , filename);
					
						mapVarid2Snpsstr = snpstrSnpsAllele1AllvarsDAO.readSNPString((Set)colVarids, chr, indxs);
						//heteroSnps = snpsheteroDAO.findSnpsHeteroAllvarsChrPosIn(chr, listpos,  datatype);	
						mapVarid2Snpsstr_allele2= snpstrSnpsAllele2AllvarsDAO.readSNPString((Set)colVarids, chr,  indxs );

					AppContext.resetTimer("using readSNPString1 end");

				}
				else {
					AppContext.resetTimer("using readSNPString2 start");
					//	mapVarid2Snpsstr = readSNPString(chr,  indxs, filename);


						mapVarid2Snpsstr = snpstrSnpsAllele1AllvarsDAO.readSNPString(chr,  indxs);
						//heteroSnps = snpsheteroDAO.findSnpsHeteroAllvarsChrPosIn(chr, listpos,  datatype);
						mapVarid2Snpsstr_allele2=  snpstrSnpsAllele2AllvarsDAO.readSNPString(chr,  indxs);

					AppContext.resetTimer("using readSNPString2 end");
				}
				
				if(params.isbExcludeSynonymous() || params.isbGraySynonymous()) {
					nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByChrPosIn(chr, listpos);
					inexonSnps = snpsinexonDAO.getSnps(chr, listpos);
					AppContext.resetTimer("to read nonsynonymous allele, inexon  from  database..");
				}
				
			}
			else {
				if(colVarids!=null && !colVarids.isEmpty() )
				{
					AppContext.resetTimer("using readSNPString3 start");
					//mapVarid2Snpsstr = readSNPString(limitVarIds, chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue(), filename);


						mapVarid2Snpsstr = snpstrSnpsAllele1AllvarsDAO.readSNPString((Set)colVarids, chr, startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());
						//heteroSnps = snpsheteroDAO.findSnpsHeteroVarsChrPosBetween(chr, start, end, limitVarIds, datatype);
						//heteroSnps = snpsheteroDAO.findSnpsHeteroAllvarsChrPosBetween(chr, start, end, datatype);
						mapVarid2Snpsstr_allele2 = snpstrSnpsAllele2AllvarsDAO.readSNPString((Set)colVarids, chr, startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());

					AppContext.resetTimer("using readSNPString3 end");
				}
				else {
					AppContext.resetTimer("using readSNPString4 start");
					//mapVarid2Snpsstr = readSNPString(chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue(), filename);
					

						mapVarid2Snpsstr = snpstrSnpsAllele1AllvarsDAO.readSNPString(chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());
						//heteroSnps = snpsheteroDAO.findSnpsHeteroAllvarsChrPosBetween(chr, start, end, datatype);
						mapVarid2Snpsstr_allele2 = snpstrSnpsAllele2AllvarsDAO.readSNPString(chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue());

				}
				
				AppContext.resetTimer("to read allele2 database..");
				
				if(params.isbExcludeSynonymous() || params.isbGraySynonymous()) {
					nonsynAllele = snpsnonsynDAO.findSnpNonsynAlleleByChrPosBetween(chr, start.intValue(), end.intValue());
					inexonSnps = snpsinexonDAO.getSnps(chr,start.intValue(), end.intValue()); 
					AppContext.resetTimer("to read nonsynonymous allele, inexon  from  database..");
				}
			}

			
			 
			// filter varieties here
			//if(datatype == SnpcoreRefposindexDAO.TYPE_3KALLSNP && colVarids!=null && !colVarids.isEmpty() && heteroSnps!=null) {
			if( colVarids!=null && !colVarids.isEmpty() && heteroSnps!=null) {
				Iterator<SnpsHeteroAllele2> itHetero = heteroSnps.iterator();
				Set setNewAllele2 = new LinkedHashSet();
				while(itHetero.hasNext()) {
					SnpsHeteroAllele2 all2 =  itHetero.next();
					if(colVarids.contains(all2.getVar()) )
						setNewAllele2.add( all2 );
				}
				heteroSnps = setNewAllele2;	
			}
			
			
			// non-synonymous alleles for given table index
			Map<Integer,Set<Character>> mapIdx2NonsynAlleles = new HashMap();
			
			//mapIdx2NonsynAlleles = new HashMap();
			Set<Integer> setSnpInExonTableIdx = null;
			if(params.isbExcludeSynonymous() || params.isbGraySynonymous()) {
				
				setSnpInExonTableIdx = new HashSet();
				Iterator<Snp> itInexon = inexonSnps.iterator();
				while(itInexon.hasNext()) setSnpInExonTableIdx.add( mapSnpid2TableIdx.get( itInexon.next().getSnpFeatureId() ) );
				
				Iterator<SnpsNonsynAllele> itNonsyn = nonsynAllele.iterator();
				while(itNonsyn.hasNext()) {
					SnpsNonsynAllele nonsynallele = itNonsyn.next();
					
					Set<Character> alleles =  mapIdx2NonsynAlleles.get( mapSnpid2TableIdx.get( nonsynallele.getSnp() )  );
					if(alleles==null) {
						alleles = new HashSet();
						mapIdx2NonsynAlleles.put( mapSnpid2TableIdx.get( nonsynallele.getSnp() )  , alleles);
					}
					alleles.add( nonsynallele.getAllele() );
				}
			}
			
			if(nonsynAllele!=null) AppContext.debug( nonsynAllele.size() + " non-synonymous alleles, " + mapSnpid2TableIdx.size() + " nonsys alleles positions/idx");
			

			Map<BigDecimal,Map> mapVarid2SnpsAllele2str = new HashMap();
			if(heteroSnps!=null) {
				Iterator<SnpsHeteroAllele2> itSnp = heteroSnps.iterator();
				while(itSnp.hasNext()) {
					SnpsHeteroAllele2 snpallele2 = itSnp.next();
					Map mapTableidx2Nuc = mapVarid2SnpsAllele2str.get( snpallele2.getVar() );
					if(mapTableidx2Nuc==null) {
						mapTableidx2Nuc = new HashMap();
						mapVarid2SnpsAllele2str.put(snpallele2.getVar() , mapTableidx2Nuc);
					}
					mapTableidx2Nuc.put(mapSnpid2TableIdx.get( snpallele2.getSnp() )  , snpallele2.getNuc() );
				}
			} else if(mapVarid2Snpsstr_allele2!=null) {
				
				Iterator itVarid = mapVarid2Snpsstr_allele2.keySet().iterator();
				while(itVarid.hasNext()) {
					BigDecimal varid= (BigDecimal)itVarid.next();
					
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
				}
			}; // else throw new RuntimeException("heteroSnps==null and mapVarid2Snpsstr_allele2==null ... no allele2 data");
			
			
			//VariantStringData vardata = new VariantStringData(snpsposlist, );

			SNPsStringData snpstrdata = new  SNPsStringData(snpsposlist, mapIdx2Pos, strRef, mapVarid2Snpsstr, mapVarid2SnpsAllele2str, mapIdx2NonsynAlleles, setSnpInExonTableIdx);
			
			return snpstrdata;

		
	}
	
	/**
	 * Contains nucleotide string sequences for each variety based on query criteria
	 * before mismatch (reference or pairwise) counting
	 * @author lmansueto
	 *
	 */
	class SNPsStringData {
		
		private List<SnpsAllvarsPos> listSnpsPos;
		private String  strRef;
		private Map<BigDecimal,String>  mapVarid2Snpsstr;
		private Map<BigDecimal, Map<Integer,Character>> mapVarid2SnpsAllele2str;
		private Map<BigDecimal, Set<Character>> mapIdx2NonsynAlleles;
		private Set<Integer> setSnpInExonTableIdx;
		private Map<Integer,BigDecimal> mapIdx2Pos;
		
		
		
		public SNPsStringData() {
			super();
			// TODO Auto-generated constructor stub
			strRef="";
		}
		public SNPsStringData(List listSnpsPos, Map mapIdx2Pos, String strRef, Map mapVarid2Snpsstr,
				Map mapVarid2SnpsAllele2str, Map mapIdx2NonsynAlleles,
				Set setSnpInExonTableIdx) {
			super();
			//if(strRef.length()==0) throw new RuntimeException("SNPsStringData: reference has zreo length");
			//if(mapVarid2Snpsstr.size()==0) throw new RuntimeException("SNPsStringData: no variety");
			//if( ((String)mapVarid2Snpsstr.values().iterator().next()).length()==0) throw new RuntimeException("SNPsStringData: first variety has zero length Snpsstr");
			
			this.strRef = strRef;
			this.mapVarid2Snpsstr = mapVarid2Snpsstr;
			this.mapVarid2SnpsAllele2str = mapVarid2SnpsAllele2str;
			this.mapIdx2NonsynAlleles = mapIdx2NonsynAlleles;
			this.setSnpInExonTableIdx = setSnpInExonTableIdx;
			this.listSnpsPos = listSnpsPos;
			this.mapIdx2Pos = mapIdx2Pos;
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
		public Map getMapIdx2NonsynAlleles() {
			return mapIdx2NonsynAlleles;
		}
		public Set getSetSnpInExonTableIdx() {
			return setSnpInExonTableIdx;
		}
		public List<SnpsAllvarsPos> getListSnpsPos() {
			return listSnpsPos;
		}
		public Map<Integer, BigDecimal> getMapIdx2Pos() {
			return mapIdx2Pos;
		}
		
		
		
	}
	

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
  public static double countVarpairMismatch(String var1, String var2, boolean var1isref, Map<Integer,Character> var1allele2str, Map<Integer,Character> var2allele2str,	
				Map<Integer,Set<Character>> mapIdx2NonsynAlleles, Set setSnpInExonTableIdx, Set setNonsynIdx, boolean isNonsynOnly) {

		double misCount = 0;
		for(int iStr=0; iStr<var2.length(); iStr++) {
			char var1char = var1.charAt(iStr);
			char var2char = var2.charAt(iStr);
			boolean snpInExon = false;
			if(setSnpInExonTableIdx!=null && setSnpInExonTableIdx.contains(iStr)) snpInExon=true;
			
			Boolean isNonsyn[] = new Boolean[2];
			isNonsyn[0] = false;
			isNonsyn[1] = false;
			Character var1allele2 = null;
			if(!var1isref && var1allele2str!=null) var1allele2 =  var1allele2str.get(iStr);
			
			Character var2allele2 = null;
			if(var2allele2str!=null) var2allele2 = var2allele2str.get(iStr);
			Set setNonsyns = null;
			if(mapIdx2NonsynAlleles!=null) setNonsyns = mapIdx2NonsynAlleles.get(iStr);
			
			misCount += countVarpairMismatchNuc( var1.charAt(iStr),  var2.charAt(iStr),  var1isref, var1allele2, var2allele2,
					setNonsyns, snpInExon,  isNonsyn,  isNonsynOnly);
					
			if(isNonsyn[0] || isNonsyn[1]) setNonsynIdx.add(iStr);					
		}
		return misCount;
  }
	  
  public static double countVarpairMismatchNuc(char var1char, char var2char , boolean var1isref,Character var1allele2, Character var2allele2,	
		Set<Character> setNonsynAlleles, boolean snpInExon, Boolean isNonsyn[], boolean isNonsynOnly) {
		double misCount = 0;
//		for(int iStr=0; iStr<var2.length(); iStr++) {
//			char var1char = var1.charAt(0);
//			char var2char = var2.charAt(0);
			
				isNonsyn[0]=false;
				isNonsyn[1]=false;
				//boolNonsyn = false;
				
				if( var2allele2 != null) {
					if(var2allele2=='*') var2allele2 = var2char;
					else if( var2allele2=='0' || var2allele2=='.') var2allele2=null;
				}
				if( var1allele2 != null) {
					if(var1allele2=='*') var1allele2 = var1char; 
					else if(var1allele2=='0' || var1allele2=='.') var1allele2=null;
				}
				
				
				if(var2char=='0' || var2char=='.'  || var2char == '*')
					{}
				else if(!var1isref && (var1char=='0' || var1char=='.'  || var1char == '*')) 
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
					else if(var2char!='0' && var2char!='.'  &&  var2char!='*' &&  var2char!='$') {
						//check with allele 2
						if(var2allele2!=null &&  var2allele2==var1char) misCount+=0.5;
						else misCount +=1.0;
					}
					
				} else {
					// pairwise comparison
					
					// check all pairs
					if(var1char=='0' || var2char=='0' ||  var1char=='.' || var2char=='.' ||  var1char=='*' || var2char=='*' ) {}
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
				
				//String ismis = "          *** ";
				//if(var1char==var2char) ismis= "";
				//AppContext.debug("var1isref=" + var1isref + "  var1char=" + var1char + "  var2char=" + var2char + " var1allele2=" + var1allele2 + " var2allele2=" + var2allele2  + "  mis=" + misCount  +  ismis);
				
		//}
				
		return misCount;
	}
  
	

}
