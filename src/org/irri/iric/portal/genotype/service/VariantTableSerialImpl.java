package org.irri.iric.portal.genotype.service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.domain.VariantStringData;
import org.irri.iric.portal.domain.VariantTable;
import org.irri.iric.portal.domain.Variety;
import org.springframework.beans.factory.annotation.Autowired;

public class VariantTableSerialImpl implements VariantTable {

	@Autowired
	private ListItemsDAO listitemdao;
	
	private String delimiter;
	private java.io.StringWriter writer;
	private boolean splitAllele2=false;
	private boolean hasRowHeader=true;
	private boolean hasColHeader=true; 
	
	public VariantTableSerialImpl(String delimiter, java.io.StringWriter writer, boolean splitAllele2, boolean hasRowHeader, boolean hasColHeader ) {
		super();
		this.delimiter = delimiter;
		this.writer=writer;
		this.splitAllele2=splitAllele2;
		this.hasRowHeader=hasRowHeader;
		this.hasColHeader=hasColHeader;
	}
	

	@Override
	public void setVariantStringData(VariantStringData data) {
		// TODO Auto-generated method stub

		StringBuffer buff1 = new StringBuffer();
		StringBuffer buff2 = new StringBuffer();

	
		if(hasColHeader) {
			List<SnpsAllvarsPos> snpsposlist = data.getListPos();
			Long posarr[] = new Long[snpsposlist.size()]; 
			String refnuc[] = new String[snpsposlist.size()];
			Iterator<SnpsAllvarsPos> itPos = snpsposlist.iterator();
			int poscount = 0;
			if(hasRowHeader) {
				buff1.append("POSITION").append(delimiter).append(delimiter);
				buff2.append("REFERENCE").append(delimiter).append("MISMATCH").append(delimiter);
			}
			while(itPos.hasNext()) {
				SnpsAllvarsPos posnuc=itPos.next();
				buff1.append(posnuc.getPos());
				buff2.append(posnuc.getRefnuc());
				poscount++;
				if(itPos.hasNext()) {
					buff1.append(delimiter);
					buff2.append(delimiter);
				}
			}
			writer.append(data.getMessage()).append("\n").append(buff1).append("\n").append(buff2).append("\n");
		}
		
		
		
		listitemdao = (ListItemsDAO)AppContext.checkBean(listitemdao, "ListItemsDAO");
		Map<BigDecimal, Variety> mapVarid2Variety = listitemdao.getMapId2Variety();
		List listTable= data.getListVariantsString();
	
		Iterator<SnpsStringAllvars> itSnpstring = listTable.iterator();
		int varcount = 0;
		while(itSnpstring.hasNext()) {
			SnpsStringAllvars snpstr = itSnpstring.next();
			
			buff1 = new StringBuffer();
			if(hasRowHeader) {
				buff1.append( mapVarid2Variety.get( snpstr.getVar() ).getName()).append(delimiter);
				buff1.append(snpstr.getMismatch()).append(delimiter);
			}
			String allstr[]= IndelStringService.createVarietyString(snpstr,data);
			for(int j=0;j<allstr.length;j++)
			{
				if(splitAllele2) {
					if(allstr[j].isEmpty()) {
						buff1.append(delimiter);
					}
					else {		
						String alleles12[] = allstr[j].split("/");
						buff1.append(alleles12[0]).append(delimiter);
						if(alleles12.length>1) {
							buff1.append(alleles12[1]);
						} else
							buff1.append(alleles12[0]);
					}
				}
				else buff1.append( allstr[j] );
				
				if(j+1<allstr.length) buff1.append( delimiter );
			}
			
			writer.append(buff1).append("\n");
		}
		
	}


	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setMessage(String message) {
		// TODO Auto-generated method stub
		
	}


}
