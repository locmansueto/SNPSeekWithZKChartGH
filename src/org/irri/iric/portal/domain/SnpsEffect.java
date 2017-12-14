package org.irri.iric.portal.domain;

import java.util.List;

public interface SnpsEffect  extends Locus {

	String[] getANN();
	String[] getLOF();
	String[] getNMD();
	String getAnnotation();
	Double getScore();
	
	List<SnpEffectAnn> getANNObj();
	String getSnpset();
	
}
