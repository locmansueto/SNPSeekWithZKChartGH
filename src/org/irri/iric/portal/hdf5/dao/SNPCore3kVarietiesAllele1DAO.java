package org.irri.iric.portal.hdf5.dao;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.hdf5.H5Dataset;
import org.springframework.stereotype.Repository;

@Repository("H5SNPCoreAllele1DAO")
public class SNPCore3kVarietiesAllele1DAO extends H5Dataset {

	
	public SNPCore3kVarietiesAllele1DAO() {
		super(AppContext.getFlatfilesDir() + "3kcore-allele1");
		// TODO Auto-generated constructor stub
	}
	
}
