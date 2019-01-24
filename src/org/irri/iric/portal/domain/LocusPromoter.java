package org.irri.iric.portal.domain;

public interface LocusPromoter extends MergedLoci {

	String getPromoterName();

	String getPromoterType();

	String getPromoterDB();

	int getPromoterStart();

	int getPromoterEnd();

	int getDistanceFromTSS();

}
