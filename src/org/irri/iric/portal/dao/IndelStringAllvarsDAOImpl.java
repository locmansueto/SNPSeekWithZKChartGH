package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.List;

import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("IndelStringAllvarsDAO")
public class IndelStringAllvarsDAOImpl implements IndelStringAllvarsDAO {

	@Autowired
	@Qualifier("IndelsAllvarsPosDAO")
	private IndelsAllvarsPosDAO indelsallvars;
	
	
	@Override
	public List<SnpsStringAllvars> getSNPsString(Integer chr, BigDecimal start,
			BigDecimal end, int firstRow, int maxRows) {
		// TODO Auto-generated method stub
		
		//indelsallvars.getMapIndelId2Indels(chr, start, end);
		
		return null;
	}

	
	
}
