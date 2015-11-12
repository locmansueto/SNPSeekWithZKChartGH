package org.irri.iric.portal.hdf5.dao;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.hdf5.H5Dataset;
import org.springframework.stereotype.Repository;

@Repository("H5SNPUniAllele2V2DAO")
public class SNPUni3024VarietiesAllele2v2DAO extends H5Dataset {

	public SNPUni3024VarietiesAllele2v2DAO() {
		super(AppContext.getFlatfilesDir() + "SNPuni_2");
		// TODO Auto-generated constructor stub
	}
}
