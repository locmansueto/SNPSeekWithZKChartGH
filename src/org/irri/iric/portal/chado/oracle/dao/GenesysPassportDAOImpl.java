package org.irri.iric.portal.chado.oracle.dao;

import java.math.BigDecimal;
import java.util.Set;

import org.brapi.client.GenesysAPI;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.IricstockPassportDAO;
import org.irri.iric.portal.domain.Passport;
import org.springframework.stereotype.Repository;

@Repository("GenesysPassportDAO")
public class GenesysPassportDAOImpl implements IricstockPassportDAO {

	@Override
	public Set getPassportByStockId(BigDecimal id)  throws Exception  {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set getPassportByAccession(String acc) throws Exception {
		// TODO Auto-generated method stub
		//GenesysAPI.getInstance(Passport.class,true).getPassportByAccession("IRGC 122151").toString()); 
    	return GenesysAPI.getInstance(Passport.class, true).getPassportByAccession(acc); 
	}

}
