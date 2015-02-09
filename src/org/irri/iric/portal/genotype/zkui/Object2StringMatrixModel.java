/* FakerMatrixModel.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Feb 23, 2012 2:29:27 PM , Created by jumperchen
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
 */
package org.irri.iric.portal.genotype.zkui;

import java.text.DecimalFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.domain.VariantStringData;
import org.irri.iric.portal.domain.VariantTable;
import org.irri.iric.portal.genotype.service.VariantAlignmentTableArraysImpl;
import org.irri.iric.portal.genotype.service.VariantTableArraysImpl;
import org.irri.iric.portal.genotype.service.VariantTableRandomImpl;
//import org.zkoss.addon.MatrixModel;
import org.zkoss.lang.Objects;
import org.zkoss.zkmax.zul.MatrixModel;
import org.zkoss.zul.AbstractListModel;
import org.zkoss.zul.event.ListDataEvent;
import org.zkoss.zul.ext.Sortable;

/**
 * A faker MatrixModel is used to handle a unlimited big table data. 
 * @author jumperchen
 */
public class Object2StringMatrixModel<Head extends List, Row extends List, Cell, Header> extends
		AbstractListModel<Row> implements MatrixModel<Row, Head, Cell, Header>, Sortable  {
	
	private int frozenCols=3;
	private VariantTable data;
	private String message;
	
	// a rendering function
	private interface Fun<T> {
		public T apply(int index);
	}
	
	// A faker of key list implementation that contains a key to speed up the performance.
	// Because Java Collection framework didn't handle it well for huge data, it will
	// go through whole the list entries to receive the value for those methods,
	// hashCode(), equals(), and toString()
	private class FakerKeyList<T> extends AbstractList<T> {
		final int _size;
		Map<String, T> _updateCache = new HashMap<String,T> ();
		final Fun<?> _fn;
		final String _key;

		public FakerKeyList(int size, int key, Fun<?> fn) {
			_size = size;
			_key = key + "_" + size;
			_fn = fn;
		}

		@Override
		public int size() {
			return _size;
		}

		@Override
		public boolean isEmpty() {
			return _size == 0;
		}

		@Override
		public T get(int index) {
			// if changed, returns the changed value
			Object val = _updateCache.get(String.valueOf(index));
			if (val != null)
				return (T) val;
			return (T) _fn.apply(index);
		}

		@Override
		public T set(int index, T element) {
			_updateCache.put(String.valueOf(index), element);
			return element;
		}

		@Override
		public int hashCode() {
			return _key.hashCode();
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj == this)
				return true;
			if (obj instanceof FakerKeyList) {
				return _key.equals(((FakerKeyList)(obj))._key);
			}
			return false;
		}
		
		@Override
		public String toString() {
			return _key;
		}
	}

	private int _colSize;

	private int _rowSize;

	private Map<String, List<String>> _rowCache;
	
	
	//private List<SnpsStringAllvars> _listSnpString;
	private List _listSnpString;

	private List<String> _headerData;
	//private List<String[]> _headerData;
	private List<String> _headerData2;

	private Comparator<Cell> _sorting;

	private boolean _sortDir = true;

	@SuppressWarnings("unchecked")
	@Override
	public void sort(Comparator cmpr, boolean ascending) {
		_sorting = cmpr;
		_sortDir = ascending;
		fireEvent(ListDataEvent.STRUCTURE_CHANGED, -1, -1);
	}

	@Override
	public String getSortDirection(Comparator cmpr) {
		if (Objects.equals(_sorting, cmpr))
			return _sortDir ? "ascending" : "descending";
		return "natural";
	}

	
	
	public Object2StringMatrixModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Object2StringMatrixModel(VariantTable data) {
		super();
		// TODO Auto-generated constructor stub
		this.data=data;
		 _Object2StringMatrixModel();
	}
	
	
	private void  _Object2StringMatrixModel() {
		if(data instanceof VariantAlignmentTableArraysImpl) {
			VariantAlignmentTableArraysImpl tabledata = (VariantAlignmentTableArraysImpl)data;
			_colSize = tabledata.getPosition().length+frozenCols;
			_rowSize = tabledata.getVarid().length;
			List listStrArray = new ArrayList();
			Object objtable[][] = tabledata.getVaralleles(); 
			for(int i=0; i<objtable.length; i++) {
				
				//List collist = new ArrayList();
				//for(int j = 0; j<objtable[i].length; j++)
				//	collist.add(objtable[i][j]);
				//listStrArray.add( collist );	
				
				listStrArray.add( objtable[i] );
			}
			_listSnpString = listStrArray;
		}
		else if(data instanceof VariantTableArraysImpl) {
				VariantTableArraysImpl tabledata = (VariantTableArraysImpl)data;
				_colSize = tabledata.getPosition().length+frozenCols;
				_rowSize = tabledata.getVarid().length;
				List listStrArray = new ArrayList();
				Object objtable[][] = tabledata.getVaralleles(); 
				for(int i=0; i<objtable.length; i++) {
					listStrArray.add( objtable[i] );
					
					//List collist = new ArrayList();
					//for(int j = 0; j<objtable[i].length; j++)
					//	collist.add(objtable[i][j]);
					//listStrArray.add( collist );
				}
				_listSnpString = listStrArray;
		}

		_rowCache = new HashMap<String, List<String>>();
		
		_headerData = new FakerKeyList<String>( _colSize, 0, new Fun() {
			@Override
			public Object apply(int index) {
				//return "Header x = " + index;
				//
				if(index>=frozenCols) {
					SnpsAllvarsPos snppos = data.getVariantStringData().getListPos().get(index-frozenCols);
					DecimalFormat df = new DecimalFormat("0.00");
					String out = df.format(  snppos.getPos() ).replace(".00","");
					if(out.endsWith(".0")) out=out.replace(".0", "");
					return out;
				}
				else if(index==2)
					return "Mismatch";
				else if(index==1)
					return "IRIS ID";
				else //if(index==0)
					return "Variety";
			}});
		_headerData2 = new FakerKeyList<String>( _colSize, 1, new Fun() {
			@Override
			public Object apply(int index) {
				//return "Header x = " + index;
				//
				if(index>=frozenCols) {
					SnpsAllvarsPos snppos = data.getVariantStringData().getListPos().get(index-frozenCols);
					return snppos.getRefnuc() ;
				}
				else if(index==0)
					return "Reference";
				else
					return "";
			}});
	}
	

	public void update(Integer[] axis, String value) {
		List<String> list = _rowCache.get(String.valueOf(axis[1]));
		list.set(axis[0], value);
		this.fireEvent(ListDataEvent.CONTENTS_CHANGED, axis[0], axis[1]);
	}

	public void setSize(int colSize, int rowSize) {
		_colSize = colSize;
		_rowSize = rowSize;
		this.fireEvent(ListDataEvent.STRUCTURE_CHANGED, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Row getElementAt(int index) {
		final int rowIndex = _sortDir ? index : getSize() - index - 1; // handle the sorting
		final String key = String.valueOf(rowIndex);
		List<String> value = _rowCache.get(key);
		if (value == null) {
			value = new FakerKeyList<String>(_colSize, rowIndex, new Fun() {
				@Override
				public Object apply(int index) {
					
					return _listSnpString.get(rowIndex);
					
					//return "y = " + rowIndex;
					//return _listSnpString.get(rowIndex).getVarnuc();
					/*
					List<String> extlist = new ArrayList();
					SnpsStringAllvars snpstr = _listSnpString.get(rowIndex);
					extlist.add(snpstr.getVar().toString());
					extlist.add(snpstr.getMismatch().toString());
					extlist.add(snpstr.getVarnuc());
					return extlist;
					*/
					//return _listSnpString.get(rowIndex).getVarnuc();
					
				}});
			_rowCache.put(key, value);
		}
		return (Row) value;
	}

	@Override
	public int getSize() {
		return _rowSize;
	}

	@Override
	public int getColumnSize() {
		return _colSize;
	}

	@Override
	public int getHeadSize() {
		return 2;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Head getHeadAt(int rowIndex) {
		if(rowIndex==0)
			return (Head) _headerData;
		else 
			return (Head) _headerData2;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Cell getCellAt(Row rowData, int columnIndex) {
		
			//return (Cell) rowData.get(columnIndex);
		return (Cell) rowData.get(columnIndex);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Header getHeaderAt(Head headData, int columnIndex) {
		return (Header) headData.get(columnIndex);
	}

	public VariantTable getData() {
		return data;
	}

//	@Override
//	public void setVariantStringData(VariantStringData data) {
//		// TODO Auto-generated method stub
//		this.data=data;
//		 _FakerMatrixModel(data.getListVariantsString(), data.getListPos());
//	}
//
//	@Override
//	public String getMessage() {
//		// TODO Auto-generated method stub
//		return message;
//	}
//
//	@Override
//	public void setMessage(String message) {
//		// TODO Auto-generated method stub
//		this.message=message;
//	}
//
//	@Override
//	public VariantStringData getVariantStringData() {
//		// TODO Auto-generated method stub
//		return data;
//	}

	
	
}
