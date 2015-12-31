package org.irri.iric.portal.dao;

import java.util.List;

public interface LocusCvTermDAO {

	public static String GENEMODEL_ALL="all";
	public static String GENEMODEL_IRIC="iric";
	public static String GENEMODEL_MSU7="msu7";
	public static String GENEMODEL_RAP="rap";
	public static String GENEMODEL_FGENESH="fgenesh";
	
	public static String QTL_ALL="all";
	public static String QTL_QTARO="qtaro";
	public static String QTL_GRAMENE="gramene";

	
	/**
	 * Get locus with CV term in organism and CV
	 * @param desc
	 * @param organismId
	 * @param cvId
	 * @return
	 */
	List getLocusByDescription(String desc, Integer organismId, Integer cvId);

}
