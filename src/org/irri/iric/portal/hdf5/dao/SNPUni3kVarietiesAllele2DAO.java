package org.irri.iric.portal.hdf5.dao;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.SnpsStringDAO;
import org.irri.iric.portal.hdf5.H5Dataset;
import org.irri.iric.portal.hdf5.H5ReadCharmatrix;
import org.irri.iric.portal.hdf5.InputParams;
import org.irri.iric.portal.hdf5.InputParamsIdxs;
import org.irri.iric.portal.hdf5.OutputMatrix;
import org.springframework.stereotype.Repository;

@Repository("H5SNPUniAllele2DAO")
public class SNPUni3kVarietiesAllele2DAO extends H5Dataset  {

	
	
	public SNPUni3kVarietiesAllele2DAO() {
		super(AppContext.getFlatfilesDir() + "SNPUni2");
		// TODO Auto-generated constructor stub
	}
	
	 public static void main(String[] argv)
	    {
		  	SNPUni3kVarietiesAllele2DAO snpuniDAO = new SNPUni3kVarietiesAllele2DAO();
		  	Map mapVar2Str =  snpuniDAO.readSNPString(1,  1000, 1100);
		  	Iterator itVar = mapVar2Str.keySet().iterator();
		  	while(itVar.hasNext()) {
		  		Object var = itVar.next();
		  		System.out.println( var + " : " + mapVar2Str.get(var));
		  	}
	    }

	 
 }
