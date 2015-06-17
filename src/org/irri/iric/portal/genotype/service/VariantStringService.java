package org.irri.iric.portal.genotype.service;

import java.util.Collection;
import java.util.List;

import org.irri.iric.portal.dao.SnpsAllvarsDAO;
import org.irri.iric.portal.dao.SnpsAllvarsPosDAO;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.domain.VariantStringData;

public interface VariantStringService {

//	List<SnpsStringAllvars> getVariantString(Collection colVarids, String chr, Long start, Long stop);
//	List<SnpsStringAllvars> getVariantString(String chr, Long start, Long stop);
//	List<SnpsStringAllvars> getVariantString(Collection colVarids, String chr, Collection colSnppos);
//	List<SnpsStringAllvars> getVariantString(String chr, Collection colSnppos);
	
	VariantStringData getVariantString(GenotypeQueryParams params, Collection colVarids, String chr, Long start, Long stop) throws Exception;
	VariantStringData getVariantString(GenotypeQueryParams params, String chr, Long start, Long stop) throws Exception;
	VariantStringData getVariantString(GenotypeQueryParams params, Collection colVarids, String chr, Collection colSnppos) throws Exception;
	VariantStringData getVariantString(GenotypeQueryParams params, String chr, Collection colSnppos) throws Exception;	
//	SnpsAllvarsPosDAO getVariantPositionsDAO();
//	SnpsAllvarsDAO getVariantCallsDAO();
	VariantStringData getVariantString(GenotypeQueryParams params, Collection colLocus);
	VariantStringData getSNPsString(GenotypeQueryParams params) throws Exception;
	VariantStringData getVariantIndelsString(GenotypeQueryParams params) throws Exception;
	
}
