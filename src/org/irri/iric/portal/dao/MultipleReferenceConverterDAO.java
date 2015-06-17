package org.irri.iric.portal.dao;

import org.irri.iric.portal.domain.MultiReferenceLocus;
import org.irri.iric.portal.domain.MultiReferenceConversion;
import org.irri.iric.portal.domain.VariantStringData;

public interface MultipleReferenceConverterDAO {

	MultiReferenceConversion convertPosition(MultiReferenceConversion fromPos, String toReference, String toContig) throws Exception;
	MultiReferenceLocus convertLocus(MultiReferenceLocus fromLocus, String toReference, String toContig) throws Exception;
	VariantStringData convertReferencePositions(VariantStringData variantstringdataNPB, MultiReferenceLocus npbMultirefLocus, MultiReferenceLocus origMultiReferenceLocus, String toContig, boolean isOtherRefs) throws Exception;

	
}
