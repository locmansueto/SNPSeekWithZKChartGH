package org.irri.iric.portal.genomics;

import java.util.Collection;

import org.irri.iric.portal.domain.MultiReferenceLocus;

/**
 * Sequence download parameters
 * @author LMansueto
 *
 */
public class VariantSequenceQuery {

	Collection colVars;
	Collection colLocus;
	public VariantSequenceQuery(Collection<String> colVars, Collection<MultiReferenceLocus> colLocus) {
		super();
		this.colVars = colVars;
		this.colLocus = colLocus;
	}
	public Collection getColVars() {
		return colVars;
	}
	public Collection getColLocus() {
		return colLocus;
	}
	
	
	
}
