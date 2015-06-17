package org.irri.iric.portal.genomics.service;

import java.util.List;

import org.irri.iric.portal.domain.LocalAlignmentImpl;

public interface LocalAlignmentService {

	public List alignWithDB(LocalAlignmentQuery query) throws Exception;
	
}
