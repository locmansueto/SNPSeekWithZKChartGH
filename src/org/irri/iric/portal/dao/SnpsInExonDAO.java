package org.irri.iric.portal.dao;

import java.util.Collection;
import java.util.Set;

public interface SnpsInExonDAO {

	Set getSnps(Integer chr, Integer start, Integer end);

	Set getSnps(Integer chr, Collection snpfeatureidlist);


}
