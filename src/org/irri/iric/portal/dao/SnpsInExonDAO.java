package org.irri.iric.portal.dao;

import java.util.Collection;
import java.util.Set;

public interface SnpsInExonDAO {

	Set getSnps(String chr, Integer start, Integer end);

	Set getSnps(String chr, Collection snpfeatureidlist);


}
