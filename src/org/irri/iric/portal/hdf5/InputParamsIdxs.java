package org.irri.iric.portal.hdf5;

import java.math.BigDecimal;
import java.util.List;



public class InputParamsIdxs {

	
	public InputParamsIdxs(int listPosidx[]) {
		this('\0', '\0', listPosidx, null);
	}
	
	public InputParamsIdxs(int listPosidx[], int listVaridx[]) {
		this('\0','\0', listPosidx, listVaridx);
	}

	
	public InputParamsIdxs(int startPosidx, int endPosidx) {
		this(startPosidx, endPosidx, null, null);
	}

	public InputParamsIdxs(int startPosidx, int endPosidx, int listVaridx[]) {
		this(startPosidx, endPosidx, null, listVaridx);
	}
	
	
	public InputParamsIdxs(int startPosidx, int endPosidx,
			int listPosidx[], int listVaridx[]) {
		super();
		this.startPosidx = startPosidx;
		this.endPosidx = endPosidx;
		this.listPosidx = listPosidx;
		this.listVaridx = listVaridx;
	}
	
	int startPosidx;
	int endPosidx;
	int listPosidx[];
	int listVaridx[];
}