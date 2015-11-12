package org.irri.iric.portal.dao;

import java.util.List;

public interface LocusCvTermDAO {

	public static String GENEMODEL_ALL="all";
	public static String GENEMODEL_IRIC="iric";
	public static String GENEMODEL_MSU7="msu7";
	public static String GENEMODEL_RAP="rap";
	public static String GENEMODEL_FGENESH="fgenesh";
	
	List getLocusByDescription(String goterm, Integer organismId, Integer cvId);

}
