package org.irri.iric.portal.hdf5.dao;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.hdf5.H5Dataset;
import org.springframework.stereotype.Repository;

@Repository("H5SNPCoreIUPACV2DAO")
public class SNPCore3024VarietiesV2IUPACDAO extends H5Dataset {

	public SNPCore3024VarietiesV2IUPACDAO() {
		super(AppContext.getFlatfilesDir() + "SNPuni_geno_core_NB");
		// TODO Auto-generated constructor stub
	}
}
