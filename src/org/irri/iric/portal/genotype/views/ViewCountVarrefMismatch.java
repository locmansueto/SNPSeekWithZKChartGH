package org.irri.iric.portal.genotype.views;

import javax.persistence.Entity;
import javax.persistence.Id;

// Generated Jul 22, 2014 8:45:17 AM by Hibernate Tools 3.4.0.CR1

/**
 * ViewCountVarrefMismatch generated by hbm2java
 */
@Entity
public class ViewCountVarrefMismatch implements java.io.Serializable {

	@Id
	private ViewCountVarrefMismatchId id;

	public ViewCountVarrefMismatch() {
	}

	public ViewCountVarrefMismatch(ViewCountVarrefMismatchId id) {
		this.id = id;
	}

	public ViewCountVarrefMismatchId getId() {
		return this.id;
	}

	public void setId(ViewCountVarrefMismatchId id) {
		this.id = id;
	}

}
