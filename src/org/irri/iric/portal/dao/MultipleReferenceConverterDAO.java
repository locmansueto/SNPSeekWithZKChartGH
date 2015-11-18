package org.irri.iric.portal.dao;

import org.irri.iric.portal.domain.MultiReferenceLocus;
import org.irri.iric.portal.domain.MultiReferenceConversion;
import org.irri.iric.portal.genotype.VariantStringData;

public interface MultipleReferenceConverterDAO {

	/**
	 * Convert from NPB position to target reference and contig
	 * @param fromPos
	 * @param toReference
	 * @param toContig
	 * @return
	 * @throws Exception
	 */
	public MultiReferenceConversion convertPosition(MultiReferenceConversion fromPos, String toReference, String toContig) throws Exception;

	/**
	 * Convert NPB locus to target reference and contig
	 * @param variantstringdataNPB
	 * @param npbMultirefLocus
	 * @param origMultiReferenceLocus
	 * @param toContig
	 * @param isOtherRefs
	 * @return
	 * @throws Exception
	 */
	public MultiReferenceLocus convertLocus(MultiReferenceLocus fromLocus, String toReference, String toContig) throws Exception;
	

	VariantStringData convertReferencePositions(VariantStringData variantstringdataNPB, MultiReferenceLocus npbMultirefLocus, MultiReferenceLocus origMultiReferenceLocus, String toContig, boolean isOtherRefs) throws Exception;

	
}
