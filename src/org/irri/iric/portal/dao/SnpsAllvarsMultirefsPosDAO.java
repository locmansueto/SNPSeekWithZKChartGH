package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.irri.iric.portal.AppContext;

public interface SnpsAllvarsMultirefsPosDAO extends SnpsAllvarsPosDAO {

	/**
	 * Get SNP positions for all varieties in the region
	 * 
	 * @param chromosome
	 * @param startPos
	 * @param endPos
	 * @param buff
	 *            message
	 * @return
	 */
	/*
	 * public List getSNPsInChromosome(String chr, Collection posset, BigDecimal
	 * type, BigDecimal organism, StringBuffer buff); public List
	 * getSNPsInChromosome(String chr, Collection posset, BigDecimal type,
	 * BigDecimal organism); //public List getSNPs(String chromosome, Integer
	 * startPos, Integer endPos , BigDecimal type, BigDecimal organism); public List
	 * getSNPs(String chromosome, Integer startPos, Integer endPos, BigDecimal type,
	 * BigDecimal organism, StringBuffer buff); public List getSNPs(String
	 * chromosome, Integer startPos, Integer endPos, BigDecimal type, StringBuffer
	 * buff);
	 */
	public List getSNPsInChromosome(String chr, Collection posset, Set type, BigDecimal organism, StringBuffer buff);

	public List getSNPsInChromosome(String chr, Collection posset, Set type, BigDecimal organism);

	public List getSNPs(String chromosome, Integer startPos, Integer endPos, Set type, BigDecimal organism,
			StringBuffer buff);

	public List getSNPs(String chromosome, Integer startPos, Integer endPos, Set type, StringBuffer buff);

}
