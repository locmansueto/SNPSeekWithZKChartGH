package org.irri.iric.portal.dao;

import org.irri.iric.portal.genomics.VariantSequenceQuery;

/**
 * Get alternate sequence
 * @author LMansueto
 *
 */
public interface VariantSequenceDAO {

		public String getFile(VariantSequenceQuery query) throws Exception;
}
