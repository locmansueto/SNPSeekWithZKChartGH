package org.irri.iric.portal.genotype.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.IndelStringDAOImpl;
import org.irri.iric.portal.dao.IndelsAllvarsDAO;
import org.irri.iric.portal.dao.IndelsAllvarsPosDAO;
import org.irri.iric.portal.dao.SnpsAllvarsDAO;
import org.irri.iric.portal.dao.SnpsAllvarsPosDAO;
import org.irri.iric.portal.domain.IndelsAllvarsPos;
import org.irri.iric.portal.domain.IndelsStringAllvars;
import org.irri.iric.portal.domain.Snps2VarsImpl;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.domain.VariantIndelStringData;
import org.irri.iric.portal.domain.VariantSnpsStringData;
import org.irri.iric.portal.domain.VariantStringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("IndelService")
public class IndelStringService implements VariantStringService {
	
	@Autowired
	@Qualifier("IndelsAllvarsPosDAO")
	private IndelsAllvarsPosDAO indelsAllvarsPosDAO;
	
	@Autowired
	//@Qualifier("IndelCallDAO")
	@Qualifier("IndelAllvarsDAO")
	private IndelsAllvarsDAO indelsAllvarsDAO;

	
	
	@Override
	public VariantStringData getVariantString(GenotypeQueryParams params,
			Collection colVarids, String chr, Long start, Long stop) {
		// TODO Auto-generated method stub
		return  getIndels(colVarids, Integer.valueOf(chr), start, stop, null);
	}


	@Override
	public VariantStringData getVariantString(GenotypeQueryParams params,
			String chr, Long start, Long stop) {
		// TODO Auto-generated method stub
		return getIndels(null, Integer.valueOf(chr), start, stop, null);
	}


	@Override
	public VariantStringData getVariantString(GenotypeQueryParams params,
			Collection colVarids, String chr, Collection colSnppos) {
		// TODO Auto-generated method stub
		return  getIndels(colVarids, Integer.valueOf(chr), null, null, colSnppos);
	}


	@Override
	public VariantStringData getVariantString(GenotypeQueryParams params,
			String chr, Collection colSnppos) {
		// TODO Auto-generated method stub
		return  getIndels(null, Integer.valueOf(chr), null, null, colSnppos);
	}


	class IndelsStringData {
		
		private String  strRef;
		private Map<BigDecimal,String>  mapVarid2Snpsstr;
		private Map<BigDecimal, Map<Integer,Character>> mapVarid2SnpsAllele2str;
		
		public IndelsStringData(String strRef, Map mapVarid2Snpsstr,
				Map mapVarid2SnpsAllele2str) {
			super();
			//if(strRef.length()==0) throw new RuntimeException("SNPsStringData: reference has zreo length");
			//if(mapVarid2Snpsstr.size()==0) throw new RuntimeException("SNPsStringData: no variety");
			//if( ((String)mapVarid2Snpsstr.values().iterator().next()).length()==0) throw new RuntimeException("SNPsStringData: first variety has zero length Snpsstr");
			
			this.strRef = strRef;
			this.mapVarid2Snpsstr = mapVarid2Snpsstr;
			this.mapVarid2SnpsAllele2str = mapVarid2SnpsAllele2str;
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
		
	}
	
	
	private VariantIndelStringData getIndels(Collection limitVarIds, Integer chr, Long start, Long end, Collection setPositions){
			
			indelsAllvarsPosDAO = (IndelsAllvarsPosDAO)AppContext.checkBean(indelsAllvarsPosDAO, "IndelsAllvarsPosDAO");
			indelsAllvarsDAO = (IndelsAllvarsDAO)AppContext.checkBean(  indelsAllvarsDAO , "IndelCallDAO");
			
			IndelStringDAOImpl indelstringdao = new IndelStringDAOImpl(indelsAllvarsPosDAO, indelsAllvarsDAO, false);
			Map<BigDecimal, IndelsStringAllvars> mapVar2Indelstring=null;
			
			if(setPositions==null || setPositions.isEmpty()) {
				if(limitVarIds!=null && !limitVarIds.isEmpty() ) {
					
					AppContext.resetTimer("using readIndelString1 start");
					mapVar2Indelstring=indelstringdao.readSNPString((Set)limitVarIds, chr, start.intValue(), end.intValue());
					AppContext.resetTimer("using readIndelString1 end");
	
				}
				else {
					AppContext.resetTimer("using readIndelString2 start");
					mapVar2Indelstring=indelstringdao.readSNPString(chr, start.intValue(), end.intValue());
					AppContext.resetTimer("using readIndelString2 end");
				}
			} else {
				
				int indxs[] = new int[setPositions.size()];
				int indxscount = 0;
				Iterator itSnppos =setPositions.iterator();
				while(itSnppos.hasNext()) {
					BigDecimal snppos = (BigDecimal)itSnppos.next(); 
					indxs[indxscount] =  snppos.intValue();
					indxscount++;
				}
				if(limitVarIds!=null && !limitVarIds.isEmpty() ) {
					
					AppContext.resetTimer("using readIndelString3 start");
					mapVar2Indelstring=indelstringdao.readSNPString((Set)limitVarIds, chr, indxs);
					AppContext.resetTimer("using readIndelString3 end");
	
				}
				else {
					AppContext.resetTimer("using readIndelString4 start");
					mapVar2Indelstring=indelstringdao.readSNPString(chr, indxs);
					AppContext.resetTimer("using readIndelString4 end");
				}
			}
			
			//indelstringdao.get
			//listVariants.addAll( indelstringdao.getListResult());
//			listVariantsIndels = indelstringdao.getListResult();
//			mapIndelVariety2Mismatch = indelstringdao.getMapVariety2Mismatch();
//			mapIndelVariety2Order = indelstringdao.getMapVariety2Order();
//			listIndelsnpsposlist = indelstringdao.getListPos();
//			this.mapIndelId2Indel =  indelstringdao.getMapAlleleId2Indel();
//			this.mapIndelIdx2Pos = indelstringdao.getMapIdx2Pos();
			
			// get pos2allele
			
			Map mapIndelId2Indel = indelstringdao.getMapAlleleId2Indel();

			Map<BigDecimal, Set> mapPos2Allele=new HashMap();
			Iterator<IndelsAllvarsPos> itIndels =  mapIndelId2Indel.values().iterator();
			while(itIndels.hasNext()) {
				IndelsAllvarsPos indelpos = itIndels.next(); 
				Set setalleles = mapPos2Allele.get(indelpos.getPos());
				if(setalleles==null) {
					setalleles = new TreeSet();
					mapPos2Allele.put(indelpos.getPos(), setalleles);
				}
				setalleles.add( getIndelAlleleString( indelpos) );
			}
			
			indelstringdao.getMapVariety2Mismatch();
			indelstringdao.getMapVariety2Order(); 
			indelstringdao.getListPos();
			indelstringdao.getMapIdx2Pos();
			indelstringdao.getListResult();
			
			VariantIndelStringData indelstrdata= new VariantIndelStringData(indelstringdao.getMapVariety2Mismatch(), indelstringdao.getMapVariety2Order(), 
					indelstringdao.getListPos(), indelstringdao.getMapIdx2Pos(), indelstringdao.getListResult() );
			indelstrdata.setMapIndelId2Indel(mapIndelId2Indel);
			indelstrdata.setMapPos2Alleleset(mapPos2Allele);
			
			 return indelstrdata;
	}
	

	public static String[] createVarietyString(SnpsStringAllvars snpstr, VariantStringData varstringdata) {
		
		int cols = varstringdata.getListPos().size();
		Map<Integer,BigDecimal> mapMergedIdx2Pos = varstringdata.getMapIdx2Pos();
		Map<Integer,BigDecimal> mapIndelIdx2Pos  =varstringdata.getMapIdx2Pos();
		
		
		Map<BigDecimal, IndelsAllvarsPos>	mapIndelId2Indels=null;
		Map<Integer, Integer> mapMergedIdx2SnpIdx=null;
		if(varstringdata.hasIndel()) {
			VariantIndelStringData varindelstringdata = (VariantIndelStringData)varstringdata;
			mapIndelId2Indels = varindelstringdata.getMapIndelId2Indel();
			if(varstringdata.hasSnp()) {
				VariantSnpsStringData varsnpstringdata = (VariantSnpsStringData)varstringdata;
				mapMergedIdx2SnpIdx = varsnpstringdata.getMapMergedIdx2SnpIdx();
			}
		}

		String allelesstr[] = new String[cols];  
		for(int iCols=0; iCols<cols; iCols++) {
			//SnpsStringAllvars snpstr =  (SnpsStringAllvars)matrixModel.getCellAt(cellsdata, iCols);
			StringBuffer buff = new StringBuffer();
			
				if(snpstr instanceof IndelsStringAllvars) {
					
					int j=iCols;
					
					BigDecimal pos = null;
					if(mapMergedIdx2Pos!=null) 
						pos = mapMergedIdx2Pos.get(j);
					else 
						pos = mapIndelIdx2Pos.get(j);
					
					if(pos!=null) {
					
						IndelsStringAllvars indelstring=(IndelsStringAllvars)snpstr;
						
						
						
						BigDecimal alleleid = indelstring.getAllele1( pos );
						IndelsAllvarsPos indelpos = mapIndelId2Indels.get(alleleid);
						
						//if(indelpos==null) throw new RuntimeException("cant find alleleid=" + alleleid);
						if(alleleid!=null && indelpos!=null) {
							
							buff.append( IndelStringService.getIndelAlleleString(indelpos) );
							
							BigDecimal alleleid2 = indelstring.getAllele2( pos );
							indelpos = mapIndelId2Indels.get(alleleid2);
							
							if(alleleid2!=null && !alleleid2.equals(alleleid)) {
								alleleid=alleleid2;
								indelpos = mapIndelId2Indels.get(alleleid);
								if(indelpos!=null) {
									buff.append("/").append(  IndelStringService.getIndelAlleleString(indelpos) );
								}								
							}
						}
						
						if(indelstring.getVarnuc()!=null) {
							
							// if not merged use j, if merged but not in mapMergedIdx2SnpIdx dont add, if merged but not in mapMergedIdx2SnpIdx  add using mapMergedIdx2SnpIdx.get(j)
							if(mapMergedIdx2SnpIdx!=null && mapMergedIdx2SnpIdx.containsKey(iCols) && mapMergedIdx2SnpIdx.get(iCols)!=null) {
								j = mapMergedIdx2SnpIdx.get(iCols);

								//AppContext.debug( "indelstring.getVarnuc().length()=" + snpstr.getVarnuc().length() + " j=" + j + " iCols=" + iCols);
								char element = indelstring.getVarnuc().substring(j,j+1).charAt(0);
								if(element!='0' && element!='.' && element!=' ' && element!='*') {
									buff.append(element);
								
									Map<Integer,Character> mapPosidx2allele2 = indelstring.getMapPosIdx2Allele2();
									if(mapPosidx2allele2!=null && mapPosidx2allele2.get(j)!=null) {
										element = mapPosidx2allele2.get(j);
										if(element!='0' && element!='.' && element!=' ' && element!='*') 
											buff.append("/").append(element);
									}
								}
							}
						}
					}
					
				} else {
					int j=iCols;
					// if not merged use j, if merged but not in mapMergedIdx2SnpIdx dont add, if merged but not in mapMergedIdx2SnpIdx  add using mapMergedIdx2SnpIdx.get(j)
					if(mapMergedIdx2SnpIdx!=null && mapMergedIdx2SnpIdx.containsKey(iCols) && mapMergedIdx2SnpIdx.get(iCols)!=null) {
						j = mapMergedIdx2SnpIdx.get(iCols);
					}
					
					if(mapMergedIdx2SnpIdx==null || mapMergedIdx2SnpIdx.get(iCols)!=null ) {
						//AppContext.debug( "snpstr.getVarnuc().length()=" + snpstr.getVarnuc().length() + " j=" + j + " iCols=" + iCols);
						char element = snpstr.getVarnuc().substring(j,j+1).charAt(0);
						if(element!='0' && element!='.' && element!=' ' && element!='*') {
							buff.append(element);
							Map<Integer,Character> mapPosidx2allele2 = snpstr.getMapPosIdx2Allele2();
							if(mapPosidx2allele2!=null && mapPosidx2allele2.get(j)!=null) {
								element = mapPosidx2allele2.get(j);
								if(element!='0' && element!='.' && element!=' ' && element!='*') 
									buff.append("/").append(  element );
							}
						}
					}
					
				}
				allelesstr[iCols] = buff.toString();
			}
		return allelesstr;
	}
		
	public static String getIndelAlleleString(IndelsAllvarsPos indelpos) {

		if(indelpos==null) return "";
		if(indelpos.getDellength()==0) {
			if(indelpos.getInsString()==null || indelpos.getInsString().trim().isEmpty()) 
				return "ref";
			else return indelpos.getInsString();
		} else {
			if(indelpos.getInsString()!=null && !indelpos.getInsString().trim().isEmpty() ) {
				if(indelpos.getInsString().trim().length()==1)
					return "snp -> " + indelpos.getInsString();
				else return "del " + indelpos.getDellength() + " -> " + indelpos.getInsString();
			}
			else return "del " + indelpos.getDellength();
		}
	}

	
	public static String getIndelType(String allele) {
		if(allele.startsWith("ref")) return "reference";
		else if(allele.startsWith("snp")) return "snp";
		else if(allele.startsWith("del")) {
			if(allele.contains("->"))
				return "substitution";
			else return "deletion";
		}
		else return "insertion";
	}


}
