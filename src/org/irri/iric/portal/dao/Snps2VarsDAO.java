package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.irri.iric.portal.chado.domain.VSnp2vars;
import org.irri.iric.portal.domain.Snps2Vars;
import org.springframework.dao.DataAccessException;

public interface Snps2VarsDAO {

	public Set<Snps2Vars> findVSnp2varsByVarsChrPosBetweenAll(Integer chr, java.math.BigDecimal start,  java.math.BigDecimal end, BigDecimal var1Id, BigDecimal var2Id) throws DataAccessException;
	public Set<Snps2Vars> findVSnp2varsByVarsChrPosBetweenAll(Integer chr, java.math.BigDecimal start,  java.math.BigDecimal end, BigDecimal var1, BigDecimal var2, int startResult, int maxRows) throws DataAccessException;
	public Set<Snps2Vars> findVSnp2varsByVarsChrPosBetweenMismatch(Integer chr, java.math.BigDecimal start,  java.math.BigDecimal end, BigDecimal var1Id, BigDecimal var2Id) throws DataAccessException;
	public Set<Snps2Vars> findVSnp2varsByVarsChrPosBetweenMismatch(Integer chr, java.math.BigDecimal start,  java.math.BigDecimal end, BigDecimal var1, BigDecimal var2, int startResult, int maxRows) throws DataAccessException;
	
	
}
