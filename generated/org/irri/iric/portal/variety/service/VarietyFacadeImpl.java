package org.irri.iric.portal.variety.service;

import org.irri.iric.portal.variety.dao.GermplasmDAO;
import org.irri.iric.portal.variety.domain.Germplasm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("VarietyFacade")
public class VarietyFacadeImpl implements VarietyFacade {

	
	@Autowired
	private GermplasmService germservice;
	
	@Autowired
	private GermplasmDAO germdao;
	
	
	public java.util.List getVarietyNames() {
		
		//java.util.Set<Germplasm> germ = new  java.util.TreeSet<Germplasm>( ) germdao.findAllGermplasms();
		
		//germdao.findGermplasmByAccession(accession)
		
		//germservice.loadGermplasms() 
		//;
		
		return null;

	}
	
}
