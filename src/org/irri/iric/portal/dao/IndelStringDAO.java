package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsStringAllvars;

public interface IndelStringDAO extends SnpsStringDAO {

	Map readSNPString(Set colVarids, String chr, Collection colpos);

	Map readSNPString(String chr, Collection colpos);

	Map getMapAlleleId2Indel();

	Map<BigDecimal, Integer> getMapVariety2Order();

	Map<BigDecimal, Double> getMapVariety2Mismatch();

	List<SnpsAllvarsPos> getListPos();

	List<SnpsStringAllvars> getListResult();

}
