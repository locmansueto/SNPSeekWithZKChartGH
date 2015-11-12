package org.irri.iric.portal.dao;

import org.irri.iric.portal.genomics.VariantSequenceQuery;

public interface VariantSequenceDAO {

		public String getFile(VariantSequenceQuery query) throws Exception;
}
