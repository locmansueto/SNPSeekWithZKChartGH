package org.irri.iric.portal.genotype.service;

import java.math.BigDecimal;
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
import org.irri.iric.portal.domain.Variety;
import org.springframework.beans.factory.annotation.Autowired;

public class VariantTableArraysImpl implements VariantTable {

	@Autowired
	private ListItemsDAO lisitemdao;
	
	private String message;
	private String varnames[];
	private String allelestring[][];
	private Double varmismatch[];
	private Long varids[];
	
	private Long posarr[];
	private String refnuc[];
	
	
	@Override
	public void setVariantStringData(VariantStringData data) {
		// TODO Auto-generated method stub
		

		List<SnpsAllvarsPos> snpsposlist = data.getListPos();
		Long posarr[] = new Long[snpsposlist.size()]; 
		String refnuc[] = new String[snpsposlist.size()];
		Iterator<SnpsAllvarsPos> itPos = snpsposlist.iterator();
		int poscount = 0;
		while(itPos.hasNext()) {
			SnpsAllvarsPos posnuc=itPos.next();
			posarr[poscount] = posnuc.getPos().longValue();
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
			
			allelestring[varcount] = IndelStringService.createVarietyString(snpstr,data);
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

	private Long[] getPosarr() {
		return posarr;
	}

	public void setPosarr(Long[] posarr) {
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


	public Long[] getPosition() {
		return this.getPosarr();
	}
	public String[] getReference() {
		return this.getRefnuc();
	}
	public Object[][] getVaralleles() {
		return this.getAllelestring();
	}
	public Long[] getVarid() {
		return this.getVarids();
	}
	public String[] getVarname() {
		return this.getVarnames();
	}
	public Double[] getVarmismatch() {
		return this.varmismatch;
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
