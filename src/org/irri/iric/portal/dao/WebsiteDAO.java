package org.irri.iric.portal.dao;

import java.util.List;
import java.util.concurrent.Future;

import org.irri.iric.portal.genomics.WebsiteQuery;

public interface WebsiteDAO {

	public List<String> getURL(WebsiteQuery query);
}
