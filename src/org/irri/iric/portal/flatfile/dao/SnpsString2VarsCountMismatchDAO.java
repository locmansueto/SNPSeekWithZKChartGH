package org.irri.iric.portal.flatfile.dao;

import java.io.File;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.Snps2VarsCountMismatchDAO;
import org.irri.iric.portal.dao.SnpsAllvarsPosDAO;
import org.irri.iric.portal.dao.VarietyDAO;
import org.irri.iric.portal.domain.Snps2VarsCountmismatch;
import org.irri.iric.portal.domain.Snps2VarsCountmismatchImpl;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsStringAllvarsImpl;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.flatfile.domain.VSnpRefposindex;
//import org.irri.iric.portal.flatfile.domain.SnpcoreRefposindex;
import org.irri.iric.portal.genotype.service.GenotypeFacadeChadoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("SnpsString2VarsCountMismatchDAO")
public class SnpsString2VarsCountMismatchDAO implements Snps2VarsCountMismatchDAO {

	@Autowired
	//@Qualifier("SnpcoreRefposindexDAO")		// using core snps, allelestring in database
	@Qualifier("VSnpRefposindexDAO")
	private SnpsAllvarsPosDAO snpstringcoreallvarsposService;
	
	@Autowired
	@Qualifier("VarietyDAO")
	private VarietyDAO germdao;

	private Map mapVarid2Subpop;
	private Map mapVarid2Country;
	
	Map<BigDecimal,String> getVarid2Subpop() {

		if(mapVarid2Subpop==null) {
			mapVarid2Subpop = new HashMap();
			mapVarid2Country = new HashMap();
			
			germdao = (VarietyDAO)AppContext.checkBean(germdao, "VarietyDAO");
			Set setvars = germdao.findAllVariety();
			java.util.Iterator<Variety> itgerm =  setvars.iterator();
			while( itgerm.hasNext() )	
			{
				Variety germ = itgerm.next();
				if(germ==null) throw new RuntimeException("germ==null");
				if(germ.getSubpopulation()!=null) // throw new RuntimeException("germ..getSubpopulation()==null");
				{
					mapVarid2Subpop.put( germ.getVarietyId() ,  germ.getSubpopulation());	
				}
				if(germ.getCountry()!=null) // throw new RuntimeException("germ..getSubpopulation()==null");
				{
					mapVarid2Country.put( germ.getVarietyId() ,  germ.getCountry());	
				}
			}
		}
		return mapVarid2Subpop;
	}
	

	Map<BigDecimal,String> getVarid2Country() {
		return mapVarid2Country;
	}	
	
	
	@Override
	public List<Snps2VarsCountmismatch> countMismatch(Integer chr,
			BigDecimal start, BigDecimal end) {
		// TODO Auto-generated method stub
		return countMismatch( chr,
				start,  end, -1,
				null, false);
	}

	@Override
	public List<Snps2VarsCountmismatch> countMismatch(Integer chr,
			BigDecimal start, BigDecimal end, int topN) {
		// TODO Auto-generated method stub
		return countMismatch( chr,
				start,  end, topN,
				null, false);
	}

	@Override
	public List<Snps2VarsCountmismatch> countMismatch(Integer chr,
			BigDecimal start, BigDecimal end, Set<BigDecimal> varieties) {
		// TODO Auto-generated method stub
		return countMismatch( chr,
				 start,  end, -1,
				 varieties, false);
	}

	@Override
	public List<Snps2VarsCountmismatch> countMismatch(Integer chr,
			BigDecimal start, BigDecimal end, int topN,
			Set<BigDecimal> varieties) {
		// TODO Auto-generated method stub
		return  countMismatch( chr,
				start,  end,  topN,
				varieties, false );
	}

	@Override
	public List<Snps2VarsCountmismatch> countMismatch(Integer chr,
			BigDecimal start, BigDecimal end, int topN,
			Set<BigDecimal> varieties, boolean isCore) {
		// TODO Auto-generated method stub
		

		List listResult = new ArrayList();
		
		
		// get snp positions/index
		snpstringcoreallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpstringcoreallvarsposService, "VSnpRefposindexDAO") ; 
		List<SnpsAllvarsPos>  snpsposlist = null;
		if(isCore)
			snpsposlist = snpstringcoreallvarsposService.getSNPs(chr.toString(), start.intValue(), end.intValue(), SnpcoreRefposindexDAO.TYPE_3KCORESNP, -1, -1);
		else
			snpsposlist = snpstringcoreallvarsposService.getSNPs(chr.toString(), start.intValue(), end.intValue(), SnpcoreRefposindexDAO.TYPE_3KALLSNP , -1,-1);
		
		
		if(snpsposlist.isEmpty()) return listResult;
		
		// get allele column indices from start to end positions
		VSnpRefposindex startpos =  (VSnpRefposindex)snpsposlist.get(0);
		VSnpRefposindex endpos =  (VSnpRefposindex)snpsposlist.get( snpsposlist.size()-1 );
		
		String filename ="";
		if(isCore)
			filename = AppContext.getFlatfilesDir() +  "chr-" + chr + ".txt";
		else
			filename = AppContext.getFlatfilesDir() +  "varsorted_allelestring_chr" + chr + ".txt" ;  //"allsnp_chr-" + chr + ".txt";
		
		Map<BigDecimal,String>  mapVarid2Snpsstr = null;
		if(varieties!=null && !varieties.isEmpty()) {
			System.out.println("limit distance to " +  varieties.size() + " varieties");
			mapVarid2Snpsstr = GenotypeFacadeChadoImpl.readSNPString(varieties, chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue() , filename);
		}
		else	
			mapVarid2Snpsstr = GenotypeFacadeChadoImpl.readSNPString(chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue() , filename);
		
		
		List<BigDecimal> listVarids = new ArrayList();
		listVarids.addAll( new TreeSet(mapVarid2Snpsstr.keySet())  );
		
		List mismatches = new ArrayList();
		
		for(int iVar=0; iVar<listVarids.size(); iVar++ ) {
			for(int jVar=iVar; jVar<listVarids.size(); jVar++ ) {
				
				String vari = mapVarid2Snpsstr.get(listVarids.get(iVar));
				String varj=  mapVarid2Snpsstr.get(listVarids.get(jVar));
				int misCount = 0;
				for(int iStr=0; iStr<vari.length(); iStr++) {
					char varichar = vari.charAt(iStr);
					char varjchar = varj.charAt(iStr);
					
					if(varichar!='0' && varjchar!='0' && 
							varichar!='.' && varjchar!='.' &&
									varichar!='*' && varjchar!='*' &&
										varichar!=varjchar) misCount++;
				}
				mismatches.add( new Snps2VarsCountmismatchImpl(listVarids.get(iVar), listVarids.get(jVar), BigDecimal.valueOf(misCount) ));
			}
		}
		System.out.println(mismatches.size() + " total distance pairs");
		
		if(topN>0) 
		{
			Set topNMismatch = new TreeSet(new Snps2VarCountMismatchDescSorter());
			topNMismatch.addAll(mismatches);
			mismatches = new ArrayList();
			
			long topcount= 0 ;
			Iterator<Snps2VarsCountmismatch> itMis = topNMismatch.iterator();
			while(itMis.hasNext() && topcount<topN) {
				mismatches.add( itMis.next() );
				topcount++;
			}
			
			System.out.println("topN=" + topN + "  actual topN=" +  topcount  + " mismatch distances");
		}
		
		return mismatches;
	}
	
	
	class Snps2VarCountMismatchDescSorter implements Comparator {

		@Override
		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub
			Snps2VarsCountmismatch v1 = (Snps2VarsCountmismatch)o1;
			Snps2VarsCountmismatch v2 = (Snps2VarsCountmismatch)o2;
			return  -v1.getMismatch().compareTo( v2.getMismatch() ); 
			 
			//return 0;
		}
		
	}

}
