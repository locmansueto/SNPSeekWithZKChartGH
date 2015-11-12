package org.irri.iric.portal.hdf5.dao;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.hdf5.H5Dataset;
import org.springframework.stereotype.Repository;

@Repository("H5SNPCoreAllele2V2DAO")
public class SNPCore3024VarietiesAllele2v2DAO  extends H5Dataset   {

	public SNPCore3024VarietiesAllele2v2DAO() {
		super(AppContext.getFlatfilesDir() + "SNPuni2_core_NB");
		// TODO Auto-generated constructor stub
	}
}
