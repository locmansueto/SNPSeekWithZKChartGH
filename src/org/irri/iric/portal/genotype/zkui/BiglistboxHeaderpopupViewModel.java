package org.irri.iric.portal.genotype.zkui;

import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.zk.ui.Component;

public class BiglistboxHeaderpopupViewModel {

	private Integer colIdx;

	public Integer getColIdx() {
		return colIdx;
	}

	public void setColIdx(Integer colIdx) {
		this.colIdx = colIdx;
	}
	
	public void doAfterCompose(@ContextParam(ContextType.VIEW) Component view,
            @ExecutionArgParam("colIdx") Integer colIdx) throws Exception {
		this.colIdx=colIdx;
	}
	
}

