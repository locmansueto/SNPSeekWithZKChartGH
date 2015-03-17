package org.irri.iric.portal.genomics.service;

import java.util.List;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.LocalAlignmentDAO;
import org.irri.iric.portal.domain.LocusLocalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("LocalAlignmentService")
public class LocalAlignmentBLASTServiceImpl implements LocalAlignmentService {
	
	@Autowired
	private LocalAlignmentDAO localblastdao;

	@Override
	public List alignWithDB(LocalAlignmentQuery query)  throws Exception {
		
		localblastdao = (LocalAlignmentDAO)AppContext.checkBean(localblastdao, "LocalAlignmentDAO");
		// setup arguments
		return localblastdao.alignWithDB( query) ;
	}
	
}
