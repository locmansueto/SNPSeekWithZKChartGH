package org.irri.iric.portal.hdf5;

import java.math.BigDecimal;
import java.util.List;



public class InputParamsIdxs {

	
	public InputParamsIdxs(int listPosidx[]) {
		this(-1, -1, listPosidx, null,null);
	}
	
	public InputParamsIdxs(int listPosidx[], int listVaridx[]) {
		this(-1,-1, listPosidx, listVaridx,null);
	}

	public InputParamsIdxs(int listStartEndPosidx[][]) {
		this(-1, -1, null, null,listStartEndPosidx);
	}
	
	public InputParamsIdxs(int listStartEndPosidx[][], int listVaridx[]) {
		this(-1,-1, null, listVaridx, listStartEndPosidx);
	}

	
	public InputParamsIdxs(int startPosidx, int endPosidx) {
		this(startPosidx, endPosidx, null, null,null);
	}

	public InputParamsIdxs(int startPosidx, int endPosidx, int listVaridx[]) {
		this(startPosidx, endPosidx, null, listVaridx,null);
	}
	
	
	public InputParamsIdxs(int startPosidx, int endPosidx,
			int listPosidx[], int listVaridx[], int listStartEndPosidx[][]) {
		super();
		this.startPosidx = startPosidx;
		this.endPosidx = endPosidx;
		this.listPosidx = listPosidx;
		this.listVaridx = listVaridx;
		this.listStartEndPosidx = listStartEndPosidx;

	}
	
	int startPosidx;
	int endPosidx;
	int listPosidx[];
	int listVaridx[];
	int listStartEndPosidx[][];
}