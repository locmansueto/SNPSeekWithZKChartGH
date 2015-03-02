package org.irri.iric.portal.genotype.service;

import java.util.Collection;

import org.irri.iric.portal.dao.SnpsAllvarsDAO;
import org.irri.iric.portal.dao.SnpsAllvarsPosDAO;
import org.irri.iric.portal.domain.VariantStringData;
import org.springframework.stereotype.Service;

@Service("CoreSnpsService")
public class CoreSnpsService implements VariantStringService {



	@Override
	public VariantStringData getVariantString(GenotypeQueryParams params,
			Collection colVarids, String chr, Long start, Long stop) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VariantStringData getVariantString(GenotypeQueryParams params,
			String chr, Long start, Long stop) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VariantStringData getVariantString(GenotypeQueryParams params,
			Collection colVarids, String chr, Collection colSnppos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VariantStringData getVariantString(GenotypeQueryParams params,
			String chr, Collection colSnppos) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
