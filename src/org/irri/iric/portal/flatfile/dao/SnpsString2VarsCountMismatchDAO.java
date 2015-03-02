package org.irri.iric.portal.flatfile.dao;

import java.io.File;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
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
//import org.irri.iric.portal.genotype.service.GenotypeFacadeChadoImpl;
//import org.irri.iric.portal.genotype.service.GenotypeFacadeChadoImpl.SnpsStringAllvarsImplSorter;
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

//	@Override
//	public List<Snps2VarsCountmismatch> countMismatch(Integer chr,
//			BigDecimal start, BigDecimal end, int topN,
//			Set<BigDecimal> varieties, boolean isCore) {
//		// TODO Auto-generated method stub
//		
//
//		List listResult = new ArrayList();
//		
//		
//		// get snp positions/index
//		snpstringcoreallvarsposService = (SnpsAllvarsPosDAO)AppContext.checkBean(snpstringcoreallvarsposService, "VSnpRefposindexDAO") ; 
//		List<SnpsAllvarsPos>  snpsposlist = null;
//		if(isCore)
//			snpsposlist = snpstringcoreallvarsposService.getSNPs(chr.toString(), start.intValue(), end.intValue(), SnpcoreRefposindexDAO.TYPE_3KCORESNP, -1, -1);
//		else
//			snpsposlist = snpstringcoreallvarsposService.getSNPs(chr.toString(), start.intValue(), end.intValue(), SnpcoreRefposindexDAO.TYPE_3KALLSNP , -1,-1);
//		
//		
//		if(snpsposlist.isEmpty()) return listResult;
//		
//		// get allele column indices from start to end positions
//		VSnpRefposindex startpos =  (VSnpRefposindex)snpsposlist.get(0);
//		VSnpRefposindex endpos =  (VSnpRefposindex)snpsposlist.get( snpsposlist.size()-1 );
//		
//		String filename ="";
//		if(isCore)
//			filename = AppContext.getFlatfilesDir() +  "chr-" + chr + ".txt";
//		else
//			filename = AppContext.getFlatfilesDir() +  "varsorted_allelestring_chr" + chr + ".txt" ;  //"allsnp_chr-" + chr + ".txt";
//		
//		Map<BigDecimal,String>  mapVarid2Snpsstr = null;
//		if(varieties!=null && !varieties.isEmpty()) {
//			System.out.println("limit distance to " +  varieties.size() + " varieties");
//			mapVarid2Snpsstr = readSNPString(varieties, chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue() , filename);
//		}
//		else	
//			mapVarid2Snpsstr = readSNPString(chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue() , filename);
//		
//		
//		List<BigDecimal> listVarids = new ArrayList();
//		listVarids.addAll( new TreeSet(mapVarid2Snpsstr.keySet())  );
//		
//		List mismatches = new ArrayList();
//		
//		for(int iVar=0; iVar<listVarids.size(); iVar++ ) {
//			for(int jVar=iVar; jVar<listVarids.size(); jVar++ ) {
//				
//				String vari = mapVarid2Snpsstr.get(listVarids.get(iVar));
//				String varj=  mapVarid2Snpsstr.get(listVarids.get(jVar));
//				int misCount = 0;
//				for(int iStr=0; iStr<vari.length(); iStr++) {
//					char varichar = vari.charAt(iStr);
//					char varjchar = varj.charAt(iStr);
//					
//					if(varichar!='0' && varjchar!='0' && 
//							varichar!='.' && varjchar!='.' &&
//									varichar!='*' && varjchar!='*' &&
//										varichar!=varjchar) misCount++;
//				}
//				mismatches.add( new Snps2VarsCountmismatchImpl(listVarids.get(iVar), listVarids.get(jVar), BigDecimal.valueOf(misCount) ));
//			}
//		}
//		System.out.println(mismatches.size() + " total distance pairs");
//		
//		if(topN>0) 
//		{
//			Set topNMismatch = new TreeSet(new Snps2VarCountMismatchDescSorter());
//			topNMismatch.addAll(mismatches);
//			mismatches = new ArrayList();
//			
//			long topcount= 0 ;
//			Iterator<Snps2VarsCountmismatch> itMis = topNMismatch.iterator();
//			while(itMis.hasNext() && topcount<topN) {
//				mismatches.add( itMis.next() );
//				topcount++;
//			}
//			
//			System.out.println("topN=" + topN + "  actual topN=" +  topcount  + " mismatch distances");
//		}
//		
//		return mismatches;
//	}
	
	
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
			mapVarid2Snpsstr = readSNPString(varieties, chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue() , filename);
		}
		else	
			mapVarid2Snpsstr = readSNPString(chr,  startpos.getAlleleIndex().intValue(), endpos.getAlleleIndex().intValue() , filename);
		
		
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
	
	
	

//	Set sortedVarieties = new TreeSet(new SnpsStringAllvarsImplSorter());
//	Map<BigDecimal, boolean[]> mapVar2NonsynFlags = new HashMap();
//	
//	Iterator<BigDecimal> itVar = mapVarid2Snpsstr.keySet().iterator();
//	while(itVar.hasNext()) {
//		BigDecimal var = itVar.next();
//		String snpstr = (String)mapVarid2Snpsstr.get(var);
//
//			boolean isnonsyn[] = new boolean[refLength];
//			if(isNonsynOnly || isColorByNonsyn) {
//				boolean includeVar = false;
//				
//				// check for positions with non-synonymous alleles
//				// include variety if it has one or more non-synonymous allele snp
//				for(int iStr=0; iStr<refLength; iStr++) {
//					char charatistr=snpstr.charAt(iStr);
//					if(charatistr=='0' || charatistr=='.'  || charatistr=='*') 
//						isnonsyn[iStr]= false;
//					else {
//						if(setSnpInExonTableIdx.contains(iStr)) {
//							// idx in exon
//							Set<Character> nonsynalleles =  mapIdx2NonsynAlleles.get(iStr);
//							if(nonsynalleles==null) {
//								isnonsyn[iStr]= false;
//							} else {
//								if(nonsynalleles.contains( charatistr ) ) {
//									isnonsyn[iStr]= true;
//									includeVar = true;
//								}
//								else
//									isnonsyn[iStr]= false;
//							}
//						} 
//						else {
//							// not in exon, include as nonsynonymous
//							isnonsyn[iStr]= true;
//							includeVar = true;
//						}
//					}
//				}
//				
//				if(!includeVar && isNonsynOnly && isMismatchOnly) {
//					// do not include variety
//					AppContext.debug("var " + var + " all synonymous or unknowns");
//					continue;
//				}
//			}
//			
//			
//			Map<Integer, Character> mapTableIdx2Allele2 =   mapVarid2SnpsAllele2str.get(var);
//			
//			// count mismatches
//			// include variety if mismatch>0 or unchecked mismatch only
//			
//			float misCount = 0;
//			for(int iStr=0; iStr<refLength; iStr++) {
//				char charatistr=snpstr.charAt(iStr);
//				
//				Character allele2 = null;
//				if(mapTableIdx2Allele2!=null) {
//					allele2 = mapTableIdx2Allele2.get(iStr);
//					if(allele2!=null && allele2==charatistr) throw new RuntimeException(charatistr + "=" + allele2 + " allele1==allele2 for var=" + var + " pos=" +  snpsposlist.get(iStr).getPos() +  "  refnuc=" + snpsposlist.get(iStr).getRefnuc() );
//				}
//
//				
//				// if homozygous, mismatch allele1, miscount +1
//				// if heterozygous, match allele1 or allele2, miscount +0.5
//				// if not non-synonymos and (isNonsynOnly or isColorByNonsyn), no count 
//				
//				if(strRef.charAt(iStr)==charatistr) {
//					if( (isNonsynOnly || isColorByNonsyn) && !isnonsyn[iStr] ) {}
//					else if(allele2!=null) misCount+=0.5;
//				}
//				else if(charatistr!='0' && charatistr!='.'  && charatistr!='*' && charatistr!='$') {
//					
//					// if consider non-syn only, dont count synonymous as mismatch
//					if( (isNonsynOnly || isColorByNonsyn) && !isnonsyn[iStr] ) {}
//					else if(allele2!=null &&  allele2.charValue()==strRef.charAt(iStr) ) misCount+=0.5;
//					else misCount++;
//				}
//				//if(charatistr!='0' && charatistr!='.'  && charatistr!='*' && charatistr!='$' && strRef.charAt(iStr)!=charatistr) misCount++;
//			}
//			
//			if(!isMismatchOnly || misCount>0) {
//				
//				sortedVarieties.add( new SnpsStringAllvarsImpl(var,Long.valueOf(chr), snpstr,  BigDecimal.valueOf(misCount) , mapTableIdx2Allele2, isnonsyn) );
//				
//			} 
//	}	
	
	
	
	
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
	
	
	// ********************************************* Flatfile SNPString Readers *********************************************************************************	
	
		public static Map readSNPString(int chr,  int startIdx,  int endIdx,  String filename) {
			
			/*
			if(limitVarIds!=null && !limitVarIds.isEmpty()){
				return readSNPString(limitVarIds,  chr,   startIdx,   endIdx, filename); 
			}
			*/
			
			Map mapVarid2Snps = new HashMap();
			
			//File file = new File( AppContext.getFlatfilesDir() + "chr-" + chr + ".txt");
			File file = new File( filename);
			try {
			RandomAccessFile raf = new RandomAccessFile(file,"r");
			
			String firstline = raf.readLine();
			AppContext.debug("File has " + firstline.length() + " alleles");
			AppContext.debug("reading for 3000 rows/varieties from " +  startIdx + "-" + endIdx + " (" + (endIdx-startIdx+1) + ") cols/snps");
			
			long rowlength = firstline.length() + 1;
			// return to start
			raf.seek(0);
			
			byte readBuffer[] = new byte[endIdx-startIdx+1];

			for(int varid=1; varid<=3000; varid++) {
				raf.seek(  (varid-1)*rowlength + startIdx -1 );
				raf.read( readBuffer );
				mapVarid2Snps.put(BigDecimal.valueOf(varid) , new String(readBuffer));
			}
			
			raf.close();
			
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
			
			return mapVarid2Snps;
			
			
		}
		
		public static Map readSNPString(int chr,  int posIdxs[],  String filename) {
			
			/*
			if(limitVarIds!=null && !limitVarIds.isEmpty()){
				return readSNPString(limitVarIds,  chr,   startIdx,   endIdx, filename); 
			}
			*/
			
			Map mapVarid2Snps = new HashMap();
			
			//File file = new File( AppContext.getFlatfilesDir() + "chr-" + chr + ".txt");
			File file = new File( filename);
			try {
			RandomAccessFile raf = new RandomAccessFile(file,"r");
			
			String firstline = raf.readLine();
			AppContext.debug("File has " + firstline.length() + " alleles");
			AppContext.debug("reading for 3000 rows/varieties for (" + posIdxs.length + ") cols/snps");
			
			long rowlength = firstline.length() + 1;
			// return to start
			raf.seek(0);
			
			byte readBuffer[] = new byte[1];

			for(int varid=1; varid<=3000; varid++) {
				StringBuffer readSNPBuffer = new StringBuffer();
				for(int iIdx=0; iIdx<posIdxs.length; iIdx++) {
					raf.seek(  (varid-1)*rowlength + posIdxs[iIdx]-1 );
					raf.read( readBuffer );
					readSNPBuffer.append( new String(readBuffer)); 
				}
				if(readSNPBuffer.length()!=posIdxs.length) {
					AppContext.debug("readSNPBuffer.length()!=posIdxs.length:" + readSNPBuffer.length() +"!=" + posIdxs.length);
				}
				mapVarid2Snps.put(BigDecimal.valueOf(varid) , readSNPBuffer.toString() );
			}
			
			raf.close();
			
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
			
			return mapVarid2Snps;
			
			
		}
		
		public static Map readSNPString(Set colVarids, int chr,  int posIdxs[],  String filename) {
			
			// order varids based on file ordering for 1pass/smooth disk read
			Set orderedVarids = new TreeSet(colVarids);
			
			Map mapVarid2Snps = new HashMap();
			
			//File file = new File(AppContext.getFlatfilesDir() +  "chr-" + chr + ".txt");
			File file = new File( filename);
			try {
			RandomAccessFile raf = new RandomAccessFile(file,"r");
			
			String firstline = raf.readLine();
			AppContext.debug("File has " + firstline.length() + " alleles");
			AppContext.debug("reading for " + colVarids.size() + " rows/varieties for (" + posIdxs.length + ") cols/snps");
			
			long rowlength = firstline.length() + 1;
			// return to start
			raf.seek(0);
			
			Iterator<BigDecimal> itVarid = orderedVarids.iterator();
			
			byte readBuffer[] = new byte[1];
			while(itVarid.hasNext()) {
				BigDecimal bdVarid = itVarid.next(); 
				int varid = bdVarid.intValue();
				StringBuffer readSNPBuffer = new StringBuffer();
				for(int iIdx=0; iIdx<posIdxs.length; iIdx++) {
					raf.seek(  (varid-1)*rowlength + posIdxs[iIdx]-1 );
					raf.read( readBuffer );
					readSNPBuffer.append( new String(readBuffer)); 
				}
				if(readSNPBuffer.length()!=posIdxs.length) {
					AppContext.debug("readSNPBuffer.length()!=posIdxs.length:" + readSNPBuffer.length() +"!=" + posIdxs.length);
				}
				mapVarid2Snps.put(bdVarid , readSNPBuffer.toString() );
			}
				
			raf.close();
			
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
			
			
			return mapVarid2Snps;
			
		}


		
		public static Map readSNPString(Set colVarids, int chr,  int startIdx, int endIdx,  String filename) {
			
			// order varids based on file ordering for 1pass/smooth disk read
			Set orderedVarids = new TreeSet(colVarids);
			
			Map mapVarid2Snps = new HashMap();
			
			//File file = new File(AppContext.getFlatfilesDir() +  "chr-" + chr + ".txt");
			File file = new File( filename);
			try {
			RandomAccessFile raf = new RandomAccessFile(file,"r");
			
			String firstline = raf.readLine();
			AppContext.debug("File has " + firstline.length() + " alleles");
			AppContext.debug("reading for " + colVarids.size() + " rows/varieties for" +  startIdx + "-" + endIdx + " (" + (endIdx-startIdx+1) + ") cols/snps");
			
			long rowlength = firstline.length() + 1;
			// return to start
			raf.seek(0);
			
			Iterator<BigDecimal> itVarid = orderedVarids.iterator();
			
			byte readBuffer[] = new byte[endIdx-startIdx+1];
			while(itVarid.hasNext()) {
				BigDecimal bdVarid = itVarid.next(); 
				int varid = bdVarid.intValue();
				raf.seek(  (varid-1)*rowlength + startIdx-1 );
				raf.read( readBuffer );
				mapVarid2Snps.put(bdVarid , new String(readBuffer) );
			}
				
			raf.close();
			
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
			
			return mapVarid2Snps;
			
		}

}
