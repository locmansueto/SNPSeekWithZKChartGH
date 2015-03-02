package org.irri.iric.portal.hdf5.dao;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.hdf5.H5Dataset;
import org.springframework.stereotype.Repository;

@Repository("H5SNPCoreAllele2DAO")
public class SNPCore3kVarietiesAllele2DAO extends H5Dataset {

	public SNPCore3kVarietiesAllele2DAO() {
		super(AppContext.getFlatfilesDir() + "3kcore-allele2");
		// TODO Auto-generated constructor stub
	}
	
}
