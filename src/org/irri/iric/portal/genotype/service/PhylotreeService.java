package org.irri.iric.portal.genotype.service;

import java.math.BigDecimal;
import java.util.Map;

public interface PhylotreeService {


	String[] constructPhylotree(String scale, String chr, int start, int end,
			String requestid);


	Map<BigDecimal, Integer> orderVarietiesFromPhylotree(String tmpfile);

	String[] constructPhylotree(PhylotreeQueryParams params, String requestid);

}
