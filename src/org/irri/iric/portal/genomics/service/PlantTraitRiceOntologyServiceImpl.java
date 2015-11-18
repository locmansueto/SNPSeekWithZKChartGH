package org.irri.iric.portal.genomics.service;

import java.util.Collection;
import java.util.List;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.CvTermLocusCountDAO;
import org.irri.iric.portal.dao.CvTermPathDAO;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.genomics.OntologyService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service("PATOntologyService")
public class PlantTraitRiceOntologyServiceImpl implements OntologyService {

	@Autowired
	//@Qualifier("CvTermPathDAO")
	private CvTermPathDAO cvtermpathDAO;
	@Autowired
	private  CvTermLocusCountDAO cvtermlocuscountdao;
	@Autowired
	private ListItemsDAO listitemsdao;

	
	@Override
	public String queryAccession(String q) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String overRepresentationTest(String organism, Collection genelist,
			String enrichmentType) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List countLociInTerms(String organism, Collection genelist, String cv)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public List getCVtermAncestors(String cv, String term) {
		// TODO Auto-generated method stub
		cvtermpathDAO = (CvTermPathDAO)AppContext.checkBean(cvtermpathDAO, "CvTermPathDAO");
		return cvtermpathDAO.getAncestors(cv, term);
	}


	@Override
	public List getCVtermDescendants(String cv, String term) {
		// TODO Auto-generated method stub
		cvtermpathDAO = (CvTermPathDAO)AppContext.checkBean(cvtermpathDAO, "CvTermPathDAO");
		return cvtermpathDAO.getDescendants(cv, term);
	}

        

}
