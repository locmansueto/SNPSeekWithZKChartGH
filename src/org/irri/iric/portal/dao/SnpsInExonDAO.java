package org.irri.iric.portal.dao;

import java.util.Collection;
import java.util.Set;

import org.irri.iric.portal.domain.MultiReferencePosition;

public interface SnpsInExonDAO {

	/**
	 * Get SNPs in exon in chromosome, from start to end
	 * 
	 * @param chr
	 * @param start
	 * @param end
	 * @return
	 */
	Set getSnps(String chr, Integer start, Integer end);

	Set getSnps(String chr, Collection<MultiReferencePosition> poslist);

}
