package org.irri.iric.portal.genotype.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.domain.IndelsAllvarsPos;
import org.irri.iric.portal.domain.IndelsStringAllvars;
//import org.irri.iric.portal.domain.MultiReferenceConversion;
import org.irri.iric.portal.domain.MultiReferencePosition;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.domain.VariantIndelStringData;
import org.irri.iric.portal.domain.VariantSnpsStringData;
import org.irri.iric.portal.domain.VariantStringData;
import org.irri.iric.portal.domain.VariantTable;
import org.irri.iric.portal.domain.VariantTableArray;
import org.irri.iric.portal.domain.Variety;
import org.springframework.beans.factory.annotation.Autowired;

public class VariantAlignmentTableArraysImpl implements VariantTableArray {

	@Autowired
	private ListItemsDAO lisitemdao;
	
	private String message;
	private String varnames[];
	private String allelestring[][];
	private Double varmismatch[];
	private Long varids[];
	private String allrefalleles[][];
	private String allrefallelesnames[];
	
	//private Double posarr[];
	private BigDecimal posarr[];
	private String refnuc[];
	
	private String contigarr[];
	
	
	
	private VariantStringData data;
	private String chr;
	
	//@Autowired
	//private ListItemsDAO listitemsdao;
	
	
	public VariantAlignmentTableArraysImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VariantAlignmentTableArraysImpl(VariantAlignmentTableArraysImpl copyfrom) {
		super();
		this.lisitemdao = copyfrom.lisitemdao;
		this.message = copyfrom.message;
		this.varnames = copyfrom.varnames;
		this.allelestring = copyfrom.allelestring;
		this.varmismatch =copyfrom.varmismatch;
		this.varids = copyfrom.varids;
		this.posarr = copyfrom.posarr;
		this.refnuc = copyfrom.refnuc;
		this.data = copyfrom.data;
		this.contigarr = copyfrom.contigarr;
	}
	
	public VariantAlignmentTableArraysImpl(ListItemsDAO lisitemdao,
			String message, String[] varnames, String[][] allelestring,
			Double[] varmismatch, Long[] varids, BigDecimal[] posarr,
			String[] refnuc, VariantStringData data) {
		super();
		this.lisitemdao = lisitemdao;
		this.message = message;
		this.varnames = varnames;
		this.allelestring = allelestring;
		this.varmismatch = varmismatch;
		this.varids = varids;
		this.posarr = posarr;
		this.refnuc = refnuc;
		this.data = data;
	}


	@Override
	public void setVariantStringData(VariantStringData data, GenotypeQueryParams params) {
		// TODO Auto-generated method stub
		

		this.data=data;

		List<SnpsAllvarsPos> snpsposlist = data.getListPos();
		posarr = new BigDecimal[snpsposlist.size()]; 
		refnuc = new String[snpsposlist.size()];
		if(params.isLocusList() || params.isSNPList()) {
			contigarr =  new String[snpsposlist.size()];
		}
		
		
		
		Iterator<SnpsAllvarsPos> itPos = snpsposlist.iterator();
		int poscount = 0;
		while(itPos.hasNext()) {
			SnpsAllvarsPos posnuc=itPos.next();
			//posarr[poscount] = posnuc.getPos().doubleValue(); //.longValue();
			posarr[poscount] = posnuc.getPos(); //.doubleValue(); //.longValue();
			refnuc[poscount] = posnuc.getRefnuc();
			
			if(contigarr!=null) {
				contigarr[poscount]= posnuc.getContig();	
			}
			
			poscount++;
		}
		
		lisitemdao = (ListItemsDAO)AppContext.checkBean(lisitemdao, "ListItemsDAO");
		
		Map<BigDecimal, Variety> mapVarid2Variety = lisitemdao.getMapId2Variety();
		List listTable= data.getListVariantsString();
		varnames = new String[listTable.size()];
		varids = new Long[listTable.size()];
		varmismatch = new Double[listTable.size()];
		allelestring = new String[listTable.size()][data.getListPos().size()];
	
		Iterator<SnpsStringAllvars> itSnpstring = listTable.iterator();
		int varcount = 0;
		while(itSnpstring.hasNext()) {
			SnpsStringAllvars snpstr = itSnpstring.next();
			
			varmismatch[varcount]=snpstr.getMismatch().doubleValue();
			varnames[varcount]=mapVarid2Variety.get( snpstr.getVar() ).getName();
			varids[varcount]= snpstr.getVar().longValue();
			
			if(params.isbIndel()) {
				if(params.isbAlignIndels())
					allelestring[varcount] = IndelStringService.createVarietyStringAligned(snpstr, data);
				else
					allelestring[varcount] = IndelStringService.createVarietyString(snpstr, data);
			} else if (params.isbSNP()) {
				allelestring[varcount] = IndelStringService.createVarietyString(snpstr, data);
			}
			
			varcount++;
		}
		message = data.getMessage();
		
		AppContext.debug( message);
		AppContext.debug( "creating table: isNPB=" +   data.isNipponbareReference());
		
		if(!data.isNipponbareReference()) {
			// change coordinates, remove columns with no conversion
			
			Map mapPos2Newpos = data.getMapMSU7Pos2ConvertedPos();

			//int convertedTableSize = mapPos2Newpos.size();
			int convertedTableSize = data.getListPos().size();
			
			//contigarr =  new String[convertedTableSize];
			itPos = snpsposlist.iterator();
			
			int newref_poscount = 0;
			BigDecimal newref_posarr[] = new BigDecimal[convertedTableSize]; 
			String newref_refnuc[] = new String[convertedTableSize];
			
			Set setRemoveIdx = new HashSet();
			
			int idxPos = 0;
			while(itPos.hasNext()) {
				SnpsAllvarsPos posnuc=itPos.next();
				
				MultiReferencePosition newpos = (MultiReferencePosition)mapPos2Newpos.get( posnuc.getPos() ); 
				
				if(newpos!=null) {
					//posarr[poscount] = posnuc.getPos().doubleValue(); //.longValue();
					newref_posarr[newref_poscount] =  newpos.getPosition() ;
					newref_refnuc[newref_poscount] =  newpos.getRefcall();  //posnuc.getRefnuc();
					//contigarr[newref_poscount] = newpos.getToContig();
					newref_poscount++;
				}  else {
					setRemoveIdx.add(idxPos);
					newref_posarr[newref_poscount] =  null ;
					newref_refnuc[newref_poscount] =  null;
					//contigarr[newref_poscount] = null;
					newref_poscount++;
				}
				
				idxPos++;
			}
			
			Double newref_varmismatch[] = new Double[listTable.size()];
			String newref_allelestring[][] = new String[listTable.size()][convertedTableSize];
			
			itSnpstring = listTable.iterator();
			varcount = 0;
			while(itSnpstring.hasNext()) {
				SnpsStringAllvars snpstr = itSnpstring.next();
				newref_varmismatch[varcount]=varmismatch[varcount];

				newref_poscount = 0; 
				for(int idx=0; idx<snpsposlist.size(); idx++) {
					
					if(setRemoveIdx.contains(idx)) {
						if(!refnuc.equals( allelestring[varcount][idx] )) {
							newref_varmismatch[varcount] = newref_varmismatch[varcount]-1;
						}
						newref_allelestring[varcount][newref_poscount] = "";
					} 
					else 
						newref_allelestring[varcount][newref_poscount] = allelestring[varcount][idx];
					
					newref_poscount++;		
				}
				varcount++;
			}
			
			varmismatch =  newref_varmismatch;
			allelestring =  newref_allelestring;
			posarr = newref_posarr;
			refnuc = newref_refnuc;
		}
		
		
		
		Map<String, Map<BigDecimal, MultiReferencePosition>> mapOrg2Posmap = data.getMapOrg2MSU7Pos2ConvertedPos();
		if(mapOrg2Posmap!=null && !mapOrg2Posmap.isEmpty()) {
			List listOrgsAllrefs = new ArrayList();
			listOrgsAllrefs.addAll( mapOrg2Posmap.keySet() );
			allrefalleles = new String[listOrgsAllrefs.size()][data.getListPos().size()];
			allrefallelesnames = new String[listOrgsAllrefs.size()];
			for(int iref=0; iref<listOrgsAllrefs.size(); iref++) {
				Map<BigDecimal, MultiReferencePosition> mapPos = mapOrg2Posmap.get(  listOrgsAllrefs.get(iref) );
				allrefallelesnames[iref]= (String)listOrgsAllrefs.get(iref);
				itPos = snpsposlist.iterator();
				int newref_poscount=0;
				while(itPos.hasNext()) {
					SnpsAllvarsPos posnuc=itPos.next();
					MultiReferencePosition newpos = (MultiReferencePosition)mapPos.get( posnuc.getPos() ); 
					if(newpos!=null) {
						allrefalleles[iref][newref_poscount] =  newpos.getRefcall();  //posnuc.getRefnuc();
					}  else {
						allrefalleles[iref][newref_poscount] =  null;
					}
					newref_poscount++;
				}
			}
		}
	}

	
	public String[] getAllrefallelesnames() {
		return allrefallelesnames;
	}

	public String[][] getAllrefalleles() {
		return allrefalleles;
	}

	public void setAllrefalleles(String[][] allrefalleles) {
		this.allrefalleles = allrefalleles;
	}

	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}
	public void setMessage(String message) {
		// TODO Auto-generated method stub
		this.message = message;
	}

	private ListItemsDAO getLisitemdao() {
		return lisitemdao;
	}

	public void setLisitemdao(ListItemsDAO lisitemdao) {
		this.lisitemdao = lisitemdao;
	}

	private String[] getVarnames() {
		return varnames;
	}

	public void setVarnames(String[] varnames) {
		this.varnames = varnames;
	}
	private String[][] getAllelestring() {
		return allelestring;
	}
	public void setAllelestring(String[][] allelestring) {
		this.allelestring = allelestring;
	}

	private Long[] getVarids() {
		return varids;
	}

	public void setVarids(Long[] varids) {
		this.varids = varids;
	}

	private BigDecimal[] getPosarr() {
		return posarr;
	}

	public void setPosarr(BigDecimal[] posarr) {
		this.posarr = posarr;
	}
	private String[] getRefnuc() {
		return refnuc;
	}

	public void setRefnuc(String[] refnuc) {
		this.refnuc = refnuc;
	}
	public void setVarmismatch(Double[] varmismatch) {
		this.varmismatch = varmismatch;
	}

	
	
	public BigDecimal[] getPositionNPB() {
		
		List listpos = data.getListPos();
		BigDecimal[] posNPB = new BigDecimal[listpos.size()];
		Iterator<SnpsAllvarsPos> itPos=listpos.iterator();
		int icount=0;
		while(itPos.hasNext()) {
			posNPB[icount] = itPos.next().getPos();
			icount++;
		}
		return posNPB;
	}
	
	
	public String[] getReferenceNPB() {
		List listpos = data.getListPos();
		String[] refNPB = new String[listpos.size()];
		Iterator<SnpsAllvarsPos> itPos=listpos.iterator();
		int icount=0;
		while(itPos.hasNext()) {
			refNPB[icount] = itPos.next().getRefnuc();
			icount++;
		}
		return refNPB;
	}
	

	@Override
	public String[] getContigs() {
		return this.contigarr;
	}
	
	@Override
	public BigDecimal[] getPosition() {
		return this.getPosarr();
	}
	
	@Override
	public String[] getReference() {
		return this.getRefnuc();
	}
	@Override
	public Object[][] getVaralleles() {
		return this.getAllelestring();
	}
	@Override
	public Long[] getVarid() {
		return this.getVarids();
	}
	@Override
	public String[] getVarname() {
		return this.getVarnames();
	}
	@Override
	public Double[] getVarmismatch() {
		return this.varmismatch;
	}


	@Override
	public VariantStringData getVariantStringData() {
		// TODO Auto-generated method stub
		return data;
	}

	
	public List getCompare2VarsList(String chromosome, GenotypeQueryParams params) {
		List list = new ArrayList();
		if(allelestring.length==2) {
			
			String npbContig="";
			if(params.isbShowNPBPositions()) {
				npbContig = getVariantStringData().getNpbContig();
			}
			
			if(params.isbNonsynSnps() || params.isbNonsynPlusSpliceSnps()) {
				

				if(params.isbMismatchonly()) {
					for(int ipos=0; ipos<this.posarr.length; ipos++) {
						Set setNonsynAlleles = data.getSnpstringdata().getMapIdx2NonsynAlleles().get(ipos);
						if(setNonsynAlleles==null) continue;
						if(    (!allelestring[0][ipos].isEmpty() && setNonsynAlleles.contains( allelestring[0][ipos].charAt(0))) || 
								(!allelestring[1][ipos].isEmpty() && setNonsynAlleles.contains( allelestring[1][ipos].charAt(0))) ) {
							if(!allelestring[0][ipos].equals(allelestring[1][ipos])) {
								if(params.isbShowNPBPositions()) {
									SnpsAllvarsPos snppos = data.getListPos().get(ipos);
									list.add(new Object[] { chromosome, posarr[ipos], this.refnuc[ipos], npbContig, snppos.getPos(), snppos.getRefnuc(), 
											this.allelestring[0][ipos], this.allelestring[1][ipos]} );
								}
								else list.add(new Object[] { chromosome, posarr[ipos], this.refnuc[ipos], 
										this.allelestring[0][ipos], this.allelestring[1][ipos]} );
							}
						}
					}
				}
				else {
						for(int ipos=0; ipos<this.posarr.length; ipos++) {
							Set setNonsynAlleles = data.getSnpstringdata().getMapIdx2NonsynAlleles().get(ipos);
							if(setNonsynAlleles==null) continue;
							if((!allelestring[0][ipos].isEmpty() && setNonsynAlleles.contains( allelestring[0][ipos].charAt(0))) || 
									(!allelestring[1][ipos].isEmpty() && setNonsynAlleles.contains( allelestring[1][ipos].charAt(0)))
								) {
								if(params.isbShowNPBPositions()) {
									SnpsAllvarsPos snppos = data.getListPos().get(ipos);
									list.add(new Object[] { chromosome, posarr[ipos], this.refnuc[ipos], npbContig, snppos.getPos(), snppos.getRefnuc(), 
											this.allelestring[0][ipos], this.allelestring[1][ipos]} );
								}
								else list.add(new Object[] { chromosome, posarr[ipos], this.refnuc[ipos], 
										this.allelestring[0][ipos], this.allelestring[1][ipos]} );
							}
						}
					}
				
			} else {
				if(params.isbMismatchonly()) {
					for(int ipos=0; ipos<this.posarr.length; ipos++) {
							if(!allelestring[0][ipos].equals(allelestring[1][ipos])) {
								if(params.isbShowNPBPositions()) {
									SnpsAllvarsPos snppos = data.getListPos().get(ipos);
									list.add(new Object[] { chromosome, posarr[ipos], this.refnuc[ipos], npbContig, snppos.getPos(), snppos.getRefnuc(), 
											this.allelestring[0][ipos], this.allelestring[1][ipos]} );
								}
								else list.add(new Object[] { chromosome, posarr[ipos], this.refnuc[ipos], 
										this.allelestring[0][ipos], this.allelestring[1][ipos]} );
							}
						}
				}
				else {
					for(int ipos=0; ipos<this.posarr.length; ipos++) {
						if(params.isbShowNPBPositions()) {
							SnpsAllvarsPos snppos = data.getListPos().get(ipos);
							list.add(new Object[] { chromosome, posarr[ipos], this.refnuc[ipos], npbContig, snppos.getPos(), snppos.getRefnuc(), 
									this.allelestring[0][ipos], this.allelestring[1][ipos]} );
						}
						else list.add(new Object[] { chromosome, posarr[ipos], this.refnuc[ipos], 
								this.allelestring[0][ipos], this.allelestring[1][ipos]} );
					}
				}
			}
			
			
		}
		return list;
	}

	/*
	public List getRowHeaderList() {
		return getRowHeaderList(varids, varids.length, 0);
	}
	public List getRowHeaderList(int nRows) {
		return getRowHeaderList(varids, nRows, 0);
	}
	public List getRowHeaderList(int nRows, int firstRow) {
		return getRowHeaderList(varids, nRows, firstRow);
	}
	*/

	/*
	public List getRowHeaderList() {
		return getRowHeaderList(varids, varids.length, 0);
	}
	public List getRowHeaderList(Long[] varid) {
		return getRowHeaderList(varid, varid.length, 0);
	}
	
	public List getRowHeaderList(int nRows) {
		return getRowHeaderList(varids, nRows, 0);
	}

	public List getRowHeaderList(Long[] varid, int nRows) {
		return getRowHeaderList(varid, nRows, 0);
	}
*/
	
	/*
	public List getRowHeaderList(Long[] varid, int nRows, int firstRow) {
		
		lisitemdao  = (ListItemsDAO)AppContext.checkBean(lisitemdao,"ListItemsDAO");
		Map<BigDecimal,Variety> mapVarId2Var = lisitemdao.getMapId2Variety();
		
		int lastIdx=firstRow + nRows;
		if(lastIdx>varid.length) {
			lastIdx=varid.length;
		}
		
		List list = new ArrayList();
		for(int i=firstRow; i<lastIdx; i++) {
			Variety var = mapVarId2Var.get(BigDecimal.valueOf(varid[i]));
			//list.add( new Object[]{varnames[i], var.getIrisId(), var.getSubpopulation(), varmismatch[i] });
			list.add( new Object[]{varnames[i], var.getIrisId(), var.getSubpopulation(), varmismatch[i] });
		}
		return list;
	}
	*/

	
/*
	private String getIndelAlleleString(IndelsAllvarsPos indelpos) {
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
	*/
	
	
	
}
