package org.irri.iric.portal.genotype.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.domain.IndelsAllvarsPos;
import org.irri.iric.portal.domain.IndelsStringAllvars;
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
	
	private Double posarr[];
	private String refnuc[];
	
	private VariantStringData data;
	private String chr;
	
	@Autowired
	private ListItemsDAO listitemsdao;
	
	
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
	}
	
	public VariantAlignmentTableArraysImpl(ListItemsDAO lisitemdao,
			String message, String[] varnames, String[][] allelestring,
			Double[] varmismatch, Long[] varids, Double[] posarr,
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
		posarr = new Double[snpsposlist.size()]; 
		refnuc = new String[snpsposlist.size()];
		Iterator<SnpsAllvarsPos> itPos = snpsposlist.iterator();
		int poscount = 0;
		while(itPos.hasNext()) {
			SnpsAllvarsPos posnuc=itPos.next();
			posarr[poscount] = posnuc.getPos().doubleValue(); //.longValue();
			refnuc[poscount] = posnuc.getRefnuc();
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

	private Double[] getPosarr() {
		return posarr;
	}

	public void setPosarr(Double[] posarr) {
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


	@Override
	public Double[] getPosition() {
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

	
	public List getCompare2VarsList(String chromosome) {
		List list = new ArrayList();
		for(int ipos=0; ipos<this.posarr.length; ipos++) {
			list.add(new Object[] { chromosome, posarr[ipos], this.refnuc[ipos], 
					this.allelestring[0][ipos], this.allelestring[1][ipos]} ); 
		}
		return list;
	}

	public List getRowHeaderList() {
		return getRowHeaderList(varids.length, 0);
	}
	
	public List getRowHeaderList(int nRows) {
		return getRowHeaderList(nRows, 0);
	}
	public List getRowHeaderList(int nRows, int firstRow) {
		
		listitemsdao = (ListItemsDAO)AppContext.checkBean(listitemsdao,"ListItemsDAO");
		Map<BigDecimal,Variety> mapVarId2Var = listitemsdao.getMapId2Variety();
		
		int lastIdx=firstRow + nRows;
		if(lastIdx>varids.length) {
			lastIdx=varids.length;
		}
		
		List list = new ArrayList();
		for(int i=firstRow; i<lastIdx; i++) {
			list.add( new Object[]{varnames[i], mapVarId2Var.get(BigDecimal.valueOf(varids[i])).getIrisId(), varmismatch[i] });
		}
		return list;
	}

	
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
