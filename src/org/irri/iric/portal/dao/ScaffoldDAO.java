package org.irri.iric.portal.dao;

import java.util.List;

import org.irri.iric.portal.domain.Scaffold;

public interface ScaffoldDAO {

	public List<Scaffold> getScaffolds(String organism);
	public Long getScaffoldLength(String scaffold, String organism);
}
