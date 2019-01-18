package org.irri.iric.portal.gwas.dao;

import java.util.List;

import org.irri.iric.portal.domain.CvTerm;
import org.irri.iric.portal.gwas.domain.GWASRun;
import org.irri.iric.portal.gwas.domain.ManhattanPlot;
import org.springframework.stereotype.Repository;

@Repository
public interface ManhattanPlotDAO {


	public List<ManhattanPlot> getMinusPValues(GWASRun run);
	public List<ManhattanPlot> getMinusPValues(GWASRun run, Double minlogP, String region);
	
}
