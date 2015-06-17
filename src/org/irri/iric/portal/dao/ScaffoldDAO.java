package org.irri.iric.portal.dao;

import java.math.BigDecimal;
import java.util.List;

import org.irri.iric.portal.domain.Scaffold;

public interface ScaffoldDAO {

	public List<Scaffold> getScaffolds(String organism);
	public List<Scaffold> getScaffolds(BigDecimal organism);
	public Long getScaffoldLength(String scaffold, String organism);
	public Long getScaffoldLength(String scaffold, BigDecimal organism);
}
