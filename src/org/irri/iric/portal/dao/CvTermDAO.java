package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.List;

import org.irri.iric.portal.domain.Cv;
import org.irri.iric.portal.domain.Organism;

public interface CvTermDAO {

		//public List getAllTerms(String cv, String organism);
		//public List getAllTerms(String organism);
		public List getAllTerms();
		public List getAllTerms(BigDecimal cvByName, BigDecimal organismByName);
}
