package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.irri.iric.portal.AppContext;

public interface SnpsAllvarsMultirefsPosDAO extends SnpsAllvarsPosDAO {

	
	public static BigDecimal TYPE_3K9311SNP_HDF5_V2 = new BigDecimal(8);
	public static BigDecimal TYPE_3KIR64SNP_HDF5_V2 = new BigDecimal(9);
	public static BigDecimal TYPE_3KKASALATHSNP_HDF5_V2 = new BigDecimal(10);
	public static BigDecimal TYPE_3KDJ123SNP_HDF5_V2 = new BigDecimal(11);
	
	public static Map<BigDecimal,String> MAP_TYPE2HDF5FILE = 
            Collections.unmodifiableMap(
                    new HashMap<BigDecimal, String>() {
                        {
                            //Unnamed init block.
                            put(TYPE_3KCORESNP_HDF5_V2, AppContext.getFlatfilesDir() + "SNPuni_geno_core_NB");
                            put(TYPE_3KALLSNP_HDF5_V2, AppContext.getFlatfilesDir() + "SNPuni_geno_NB");
                            put(TYPE_3K9311SNP_HDF5_V2, AppContext.getFlatfilesDir() + "SNPuni_geno_9311");
                            put(TYPE_3KIR64SNP_HDF5_V2, AppContext.getFlatfilesDir() + "SNPuni_geno_IR64");
                            put(TYPE_3KKASALATHSNP_HDF5_V2, AppContext.getFlatfilesDir() + "SNPuni_geno_Kas");
                            put(TYPE_3KDJ123SNP_HDF5_V2, AppContext.getFlatfilesDir() + "SNPuni_geno_DJ123");
                        }
                    });
	
	/**
	 * Get SNP positions for all varieties in the region
	 * @param chromosome	
	 * @param startPos
	 * @param endPos
	 * @param buff	message
	 * @return
	 */
	public List getSNPsInChromosome(String chr, Collection posset, BigDecimal type, BigDecimal organism, StringBuffer buff);
	public List getSNPsInChromosome(String chr, Collection posset, BigDecimal type, BigDecimal organism);

	
	public List getSNPs(String chromosome, Integer startPos, Integer endPos , BigDecimal type, BigDecimal organism);
	public List getSNPs(String chromosome, Integer startPos, Integer endPos, BigDecimal type, BigDecimal organism, StringBuffer buff);
	public List getSNPs(String chromosome, Integer startPos, Integer endPos, BigDecimal type, StringBuffer buff);
	
}
