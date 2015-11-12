package org.irri.iric.portal.hdf5;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ncsa.hdf.object.Dataset;

import org.irri.iric.portal.AppContext;

public interface H5ReadMatrix {

	   public  OutputMatrix read(H5Dataset hfdata, InputParamsIdxs input) throws Exception;
	   public  List<OutputMatrix> read(H5Dataset hfdata, List inputs);

		
}
