package org.irri.iric.portal.dao;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface IndelStringDAO extends SnpsStringDAO {

	Map readSNPString(Set colVarids, String chr, Collection colpos);
	Map readSNPString(String chr, Collection colpos);

}
