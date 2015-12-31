package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public interface SnpsAllvarsPosDAO {
	
	//public static BigDecimal TYPE_3KCORESNP = new BigDecimal(4);
	//public static BigDecimal TYPE_3KALLSNP = new BigDecimal(3);
	public static BigDecimal TYPE_3KCORESNP = new BigDecimal(6);
	public static BigDecimal TYPE_3KALLSNP = new BigDecimal(5);
	
	
	//public static BigDecimal TYPE_3KCORESNP_HDF5 = new BigDecimal(4);
	//public static BigDecimal TYPE_3KALLSNP_HDF5 = new BigDecimal(3);

	public static BigDecimal TYPE_3KCORESNP_HDF5_V2 = new BigDecimal(6);
	public static BigDecimal TYPE_3KALLSNP_HDF5_V2 = new BigDecimal(5);
	
	public static BigDecimal TYPE_3KALLINDEL_V2 = new BigDecimal(7);



	
	public static String DATASET_SNPINDELV1="SNP v1";
	public static String DATASET_SNPINDELV2="SNP v2";
	public static String DATASET_SNPINDELV2_IUPAC="SNP v2 IUPAC";
	
	
	/**
	 * Get SNP positions for all varieties in the region
	 * @param chromosome	
	 * @param startPos
	 * @param endPos
	 * @return
	 */
	public List getSNPs(String chromosome, Integer startPos, Integer endPos , BigDecimal type);

	public List getSNPs(String chromosome, Integer startPos, Integer endPos, BigDecimal type, int firstRow, int maxRows);

	public List getSNPsInChromosome(String chr, Collection posset, BigDecimal type);
	

}
