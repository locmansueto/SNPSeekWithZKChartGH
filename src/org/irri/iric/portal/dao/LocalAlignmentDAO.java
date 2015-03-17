package org.irri.iric.portal.dao;

import java.util.List;

import org.irri.iric.portal.domain.LocusLocalAlignment;
import org.irri.iric.portal.genomics.service.LocalAlignmentQuery;

public interface LocalAlignmentDAO {

	List alignWithDB(LocalAlignmentQuery query) throws Exception;

}
