package org.irri.iric.portal.genomics;

import java.util.List;

import org.irri.iric.portal.domain.LocalAlignmentImpl;

/**
 * BLAST alignment functions
 * @author LMansueto
 *
 */
public interface LocalAlignmentService {

	/**
	 * Align query in database
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List alignWithDB(LocalAlignmentQuery query) throws Exception;
	
}
