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

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.dao.ListItemsDAO;
import org.irri.iric.portal.domain.MultiReferenceConversion;
import org.irri.iric.portal.domain.MultiReferencePosition;
import org.irri.iric.portal.domain.SnpsAllvarsPos;
import org.irri.iric.portal.domain.SnpsStringAllvars;
import org.irri.iric.portal.domain.VariantStringData;
import org.irri.iric.portal.domain.VariantTable;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.genotype.service.GenotypeQueryParams;
import org.irri.iric.portal.genotype.service.VariantAlignmentTableArraysImpl;
import org.irri.iric.portal.genotype.service.VariantTableArraysImpl;
import org.irri.iric.portal.genotype.service.VariantTableRandomImpl;
import org.irri.iric.portal.genotype.zkui.SNPQueryController.Object2StringMatrixComparatorProvider2.Object2StringComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
//import org.zkoss.addon.MatrixModel;
import org.zkoss.lang.Objects;
import org.zkoss.zkmax.zul.MatrixModel;
import org.zkoss.zul.AbstractListModel;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelExt;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.event.ListDataEvent;
import org.zkoss.zul.ext.Sortable;

/**
 * A faker MatrixModel is used to handle a unlimited big table data. 
 * @author jumperchen
 */
//@Component("Object2StringMatrixModel")
//@Scope("prototype")
public class Object2StringMatrixModel<Head extends List, Row extends List, Cell, Header> extends
		AbstractListModel<Row> implements MatrixModel<Row, Head, Cell, Header>, Sortable , ListModelExt {
	
	private int frozenCols=4;
	private VariantTable data;
	private String message;
	private GenotypeQueryParams params;
	private Map<BigDecimal,Variety> mapVarid2Variety;
	private Grid gridHeader;
	private int biglistboxRows, lastY;
	
	private Map<BigDecimal, MultiReferencePosition> mapMSU7Pos2ConvertedPos;
	
	private List<String> listOtherRefs;
	private Map<String, Map<BigDecimal, MultiReferencePosition>> mapOrg2MSU7Pos2ConvertedPos;

	
	
	@Autowired
	private ListItemsDAO listitemdao;
	
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
	private List<String> _headerData3;
	private List<String> _headerData4;
	
	private List<String> _headerDataAlleles1;
	private List<String> _headerDataAlleles2;
	private List<String> _headerDataAlleles3;
	private List<String> _headerDataAlleles4;
	
	
	private Comparator<Cell> _sorting;

	private boolean _sortDir = true;

	@SuppressWarnings("unchecked")
	@Override
	public void sort(Comparator cmpr, boolean ascending) {
		
		
		
		//try {
		_sorting = cmpr;
		_sortDir = ascending;
	
		Collections.sort(_listSnpString, cmpr);
		
		//AppContext.debug("cmpr=" + cmpr.getClass() + "  " + cmpr + "  asc=" + ascending);
		
		Object2StringComparator cmp=(Object2StringComparator)cmpr;
		
		int col = cmp.getColumn();
		Iterator<Object[]> itMatrix = _listSnpString.iterator();
		while(itMatrix.hasNext()) {
			Object[] obj = itMatrix.next();
			//AppContext.debug(   obj[0] + " " + obj[1] + " " + obj[2] + " " +   obj[col].toString());
		}
		AppContext.debug("sorted by column = " + col + ", sortDir=" + _sortDir);
		_rowCache = new HashMap<String, List<String>>();
		
		//ListDataEvent.
		//fireEvent(ListDataEvent.STRUCTURE_CHANGED, -1, -1);
		fireEvent(ListDataEvent.CONTENTS_CHANGED, -1, -1);
		
		//}catch(Exception ex) {
		//	ex.printStackTrace();
		//	throw ex;
		//}
		
		
		try {
			
			//gridBiglistheader.setModel( new SimpleListModel(  model.getRowHeaderList(biglistboxRows, lastY) ));
			gridHeader.setModel(new SimpleListModel( getRowHeaderList(biglistboxRows, lastY)));
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	void setHeaderRows(int nRows, int lastY) {
		this.biglistboxRows=nRows;
		this.lastY=lastY;
	}

	@Override
	public String getSortDirection(Comparator cmpr) {
		if (Objects.equals(_sorting, cmpr))
			return _sortDir ? "ascending" : "descending";
		return "natural";
	}

	
	
	//public Object2StringMatrixModel() {
	//	super();
	//	// TODO Auto-generated constructor stub
	//}

	public Object2StringMatrixModel(VariantTable data, GenotypeQueryParams params, Map mapVarid2Variety, Grid gridHeader) {
		super();
		// TODO Auto-generated constructor stub
		this.data=data;
		this.params=params;
		this.mapVarid2Variety=mapVarid2Variety;
		this.gridHeader=gridHeader;
		mapMSU7Pos2ConvertedPos = data.getVariantStringData().getMapMSU7Pos2ConvertedPos();
		
		mapOrg2MSU7Pos2ConvertedPos=data.getVariantStringData().getMapOrg2MSU7Pos2ConvertedPos();
		if(mapOrg2MSU7Pos2ConvertedPos!=null) {
			listOtherRefs=new ArrayList();
			listOtherRefs.addAll( mapOrg2MSU7Pos2ConvertedPos.keySet()  );
			AppContext.debug( "other refalleles: " + listOtherRefs);
		}

		
		AppContext.debug("Object2StringMatrixModel getMapMSU7Pos2ConvertedPos="  + mapMSU7Pos2ConvertedPos  );
		 _Object2StringMatrixModel();
	}
	
	
	private void  _Object2StringMatrixModel() {
		
		Map<Integer,BigDecimal> mapOrder2Variety = new HashMap();
		Map<BigDecimal,Number> mapVar2Mismatch = data.getVariantStringData().getMapVariety2Mismatch();

		/*
		Map<BigDecimal,Integer> mapVar2Order =  data.getVariantStringData().getMapVariety2Order();
		Iterator<BigDecimal> itVar2Order = mapVar2Order.keySet().iterator();
		while(itVar2Order.hasNext()) {
			BigDecimal varid = itVar2Order.next();
			mapOrder2Variety.put( mapVar2Order.get(varid) , varid);
		}
		*/
		
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
				
				
				//listStrArray.add( objtable[i] );
				
				//BigDecimal varid = mapOrder2Variety.get(i);
				
				BigDecimal varid = BigDecimal.valueOf(tabledata.getVarid()[i]);
				Variety var = this.mapVarid2Variety.get(varid);
				listStrArray.add( ArrayUtils.addAll(new Object[]{var.getName().toUpperCase(), var.getIrisId(), var.getSubpopulation(), mapVar2Mismatch.get(varid).toString() }, objtable[i] ));
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
					//listStrArray.add( objtable[i] );
					
					//BigDecimal varid = mapOrder2Variety.get(i);
					//Variety var = this.mapVarid2Variety.get(tabledata.getVarid()[i]);
					
					BigDecimal varid = BigDecimal.valueOf(tabledata.getVarid()[i]);
					Variety var = this.mapVarid2Variety.get(varid);	
					listStrArray.add( ArrayUtils.addAll(new Object[]{var.getName().toUpperCase(), var.getIrisId(), var.getSubpopulation(), mapVar2Mismatch.get(varid).toString() }, objtable[i] ));
					
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
						String out="";
						
						  
						
						DecimalFormat df = new DecimalFormat("0.00");
						
						if(data.getVariantStringData().isNipponbareReference()) {
							out += df.format(  snppos.getPos() ).replace(".00","");
							if(out.endsWith(".0")) out=out.replace(".0", "");
							
							if(data.getContigs()!=null && !out.isEmpty())
								out = data.getContigs()[index-frozenCols] + "-" + out;
							
							//AppContext.debug( snppos.getPos() + " -> " );
						} else {
							
//							if(snppos.getPos().toString().contains("\\.")) {
//								
//								out += df.format( 							
//										data.getVariantStringData().getMapMSU7Pos2ConvertedPos().get(BigDecimal.valueOf(Long.valueOf(   snppos.getPos().toString().split("\\.")[0]))).getPosition()
//								).replace(".00","");
//								if(out.endsWith(".0")) out=out.replace(".0", "");
//								//out = data.getContigs()[index-frozenCols] + "-" + out;
//							}
//							else {
//								out += df.format( data.getVariantStringData().getMapMSU7Pos2ConvertedPos().get(snppos.getPos()).getPosition() ).replace(".00","");
//								if(out.endsWith(".0")) out=out.replace(".0", "");
//								//out = data.getContigs()[index-frozenCols] + "-" + out;
//							}
							
							
							if(mapMSU7Pos2ConvertedPos.get(snppos.getPos())==null) {
								out ="";
							}
							else {
								out += df.format(mapMSU7Pos2ConvertedPos.get(snppos.getPos()).getPosition() ).replace(".00","");
								if(out.endsWith(".0")) out=out.replace(".0", "");
								
								//AppContext.debug( snppos.getPos() + " -> " + mapMSU7Pos2ConvertedPos.get(snppos.getPos()).getPosition() );
							}
							
							if(data.getContigs()!=null && !out.isEmpty())
								out = data.getContigs()[index-frozenCols] + "-" + out;
							
						}
						
						//AppContext.debug( "getHeaderModel (" + index + "):" +  out);
						
						
						
						return out;
	
						//VariantTableArraysImpl tabledata = (VariantTableArraysImpl)data;
						
						
					}
					else if(index==3)
						return "Mismatch";
					else if(index==2)
						return "Subpopulation";
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
					if(data.getVariantStringData().isNipponbareReference()) {
						return snppos.getRefnuc() ;
					} else {
						MultiReferencePosition multiref =mapMSU7Pos2ConvertedPos.get(snppos.getPos()); 
						if(multiref==null) {
							return "";
						}
						else {
							return multiref.getRefcall();
						}
					}
				}
				else if(index==0)
					//return "Reference";
					return params.getOrganism();
				else
					return "";
			}});
		
		
		if(params.isbShowNPBPositions()) {
			
			_headerData3 = new FakerKeyList<String>( _colSize, 2, new Fun() {
				@Override
				public Object apply(int index) {
				
						if(index>=frozenCols) {
							
							SnpsAllvarsPos snppos = data.getVariantStringData().getListPos().get(index-frozenCols);
							String out="";
							DecimalFormat df = new DecimalFormat("0.00");
							
							out += df.format(snppos.getPos()).replace(".00","");
							if(out.endsWith(".0")) out=out.replace(".0", "");
								
							return out;
						}
						else if(index==3)
							return "Mismatch";
						else if(index==2)
							return "Subpopulation";
						else if(index==1)
							return "IRIS ID";
						else //if(index==0)
							return "Variety";
				}});
			_headerData4 = new FakerKeyList<String>( _colSize, 3, new Fun() {
				@Override
				public Object apply(int index) {
					//return "Header x = " + index;
					//
					if(index>=frozenCols) {
						SnpsAllvarsPos snppos = data.getVariantStringData().getListPos().get(index-frozenCols);
						return snppos.getRefnuc() ;
					}
					else if(index==0)
						return "Nipponbare";
					else
						return "";
				}});
			
		}
		
		if(listOtherRefs!=null && !listOtherRefs.isEmpty()) {
			
			AppContext.debug( "preparing model otheralleles for references: " + listOtherRefs);
			
			//if(params.getOrganism().equals(AppContext.getDefaultOrganism())) {
						
				  if(listOtherRefs.size()>0) {
						_headerDataAlleles1 = new FakerKeyList<String>( _colSize, 4, new Fun() {
							@Override
							public Object apply(int index) {
								//return "Header x = " + index;
								//
								if(index>=frozenCols) {
									SnpsAllvarsPos snppos = data.getVariantStringData().getListPos().get(index-frozenCols);
										Map<BigDecimal, MultiReferencePosition> mapPos2Refpos =  mapOrg2MSU7Pos2ConvertedPos.get( listOtherRefs.get(0));
										MultiReferencePosition multiref = mapPos2Refpos.get(snppos.getPos()); 
										if(multiref==null) {
											return "";
										}
										else {
											return multiref.getRefcall();
										}
								}
								else if(index==0)
									//return "Reference";
									return listOtherRefs.get(0); // params.getOrganism();
								else
									return "";
							}});
				  }
				  if(listOtherRefs.size()>1) {
						_headerDataAlleles2 = new FakerKeyList<String>( _colSize, 5, new Fun() {
							@Override
							public Object apply(int index) {
								//return "Header x = " + index;
								//
								if(index>=frozenCols) {
									SnpsAllvarsPos snppos = data.getVariantStringData().getListPos().get(index-frozenCols);
										Map<BigDecimal, MultiReferencePosition> mapPos2Refpos =  mapOrg2MSU7Pos2ConvertedPos.get( listOtherRefs.get(1));
										MultiReferencePosition multiref = mapPos2Refpos.get(snppos.getPos()); 
										if(multiref==null) {
											return "";
										}
										else {
											return multiref.getRefcall();
										}
								}
								else if(index==0)
									//return "Reference";
									return listOtherRefs.get(1); // params.getOrganism();
								else
									return "";
							}});
				  }				  
				  if(listOtherRefs.size()>2) {
						_headerDataAlleles3 = new FakerKeyList<String>( _colSize, 6, new Fun() {
							@Override
							public Object apply(int index) {
								//return "Header x = " + index;
								//
								if(index>=frozenCols) {
									SnpsAllvarsPos snppos = data.getVariantStringData().getListPos().get(index-frozenCols);
										Map<BigDecimal, MultiReferencePosition> mapPos2Refpos =  mapOrg2MSU7Pos2ConvertedPos.get( listOtherRefs.get(2));
										MultiReferencePosition multiref = mapPos2Refpos.get(snppos.getPos()); 
										if(multiref==null) {
											return "";
										}
										else {
											return multiref.getRefcall();
										}
								}
								else if(index==0)
									//return "Reference";
									return listOtherRefs.get(2); // params.getOrganism();
								else
									return "";
							}});
				  }
				  if(listOtherRefs.size()>3) {
						_headerDataAlleles4 = new FakerKeyList<String>( _colSize, 7, new Fun() {
							@Override
							public Object apply(int index) {
								//return "Header x = " + index;
								//
								if(index>=frozenCols) {
									SnpsAllvarsPos snppos = data.getVariantStringData().getListPos().get(index-frozenCols);
										Map<BigDecimal, MultiReferencePosition> mapPos2Refpos =  mapOrg2MSU7Pos2ConvertedPos.get( listOtherRefs.get(3));
										MultiReferencePosition multiref = mapPos2Refpos.get(snppos.getPos()); 
										if(multiref==null) {
											return "";
										}
										else {
											return multiref.getRefcall();
										}
								}
								else if(index==0)
									//return "Reference";
									return listOtherRefs.get(3); // params.getOrganism();
								else
									return "";
							}});
				  }				  
				  
			//}
		}
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
		
		final int rowIndex = index;  // _listSnpString already reversed in sort();
		
		//final int rowIndex = _sortDir ? index : getSize() - index - 1; // handle the sorting
		final String key = String.valueOf(rowIndex);
		List<String> value = _rowCache.get(key);
		
		//AppContext.debug("getrow = " + rowIndex);
		
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
		
		if(params.isbShowNPBPositions()) {
			if(params.isbShowAllRefAlleles()) {
				return 7;
			}
			else return 4;
		}
		else {
			if(params.isbShowAllRefAlleles()) {
				return 6;
			}
			else return 2;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Head getHeadAt(int rowIndex) {
		
		/*
		if(rowIndex==0)
			return (Head) _headerData;
		else if(rowIndex==1) 
			return (Head) _headerData2;
		else if(rowIndex==2) 
			return (Head) _headerData3;
		else 
			return (Head) _headerData4;
			*/
		
		if(params.isbShowAllRefAlleles()) {
			if(rowIndex==0)
				return (Head) _headerData;
			else if(rowIndex==1) 
				return (Head) _headerData2;
			
			if(params.isbShowNPBPositions()) {
				if(rowIndex==2) 
					return (Head) _headerData3;
				else if(rowIndex==3)
					return (Head) _headerData4;
				else if(rowIndex==4)
					return (Head) _headerDataAlleles1;
				else if(rowIndex==5)
					return (Head) _headerDataAlleles2;
				else if(rowIndex==6)
					return (Head) _headerDataAlleles3;
				else if(rowIndex==7)
					return (Head) _headerDataAlleles4;
			} else {
				if(rowIndex==2)
					return (Head) _headerDataAlleles1;
				else if(rowIndex==3)
					return (Head) _headerDataAlleles2;
				else if(rowIndex==4)
					return (Head) _headerDataAlleles3;
				else if(rowIndex==5)
					return (Head) _headerDataAlleles4;
			}
			
		} else {
			if(rowIndex==0)
				return (Head) _headerData;
			else if(rowIndex==1) 
				return (Head) _headerData2;
			else if(rowIndex==2) 
				return (Head) _headerData3;
			else 
				return (Head) _headerData4;
		}
		
		return null;
		
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
		
		//AppContext.debug("Model columnIndex=" + columnIndex);
		
		return (Header) headData.get(columnIndex);
	}

	public VariantTable getData() {
		return data;
	}

	public List getRowHeaderList(int nRows) {
		return getRowHeaderList(nRows, 0);
	}
	
	public List getRowHeaderList(int nRows, int firstRow) {
		// TODO Auto-generated method stub
		listitemdao = (ListItemsDAO)AppContext.checkBean(listitemdao,"ListItemsDAO");
		//Map<BigDecimal,Variety> mapVarId2Var = listitemsdao.getMapId2Variety();
		Map<String,Variety> mapVarname2Var = listitemdao.getMapVarname2Variety();
		List list = new ArrayList();
		
		int lastIdx=firstRow + nRows;
		if(lastIdx>getSize()) {
			lastIdx=getSize();
		}
		
		for(int i=firstRow; i<lastIdx; i++) {
			Object[] rowarr = (Object[])_listSnpString.get(i);
			Variety var = mapVarname2Var.get( rowarr[0].toString() );
			//list.add( new Object[]{varnames[i], var.getIrisId(), var.getSubpopulation(), varmismatch[i] });
			list.add( new Object[]{var.getName() , var.getIrisId(), var.getSubpopulation(), rowarr[3] });
			//AppContext.debug(var.getName() + " " + var.getIrisId() + " " + var.getSubpopulation() + " " +rowarr[2]);
		}
		
		return list;
	}

	
	public List getRowHeaderHeaderList() {
		// TODO Auto-generated method stub
		List listHeads = new ArrayList();
		int headsize = this.getHeadSize();
		for (int i=0; i<headsize; i++) {
			 Head ihead = getHeadAt(i);
			 listHeads.add( ihead.get(0));
		}
		return listHeads;
	}

	
	/*
	public List getRowHeaderList(Long[] varid, int nRows, int firstRow) {
		
		listitemsdao = (ListItemsDAO)AppContext.checkBean(listitemsdao,"ListItemsDAO");
		Map<BigDecimal,Variety> mapVarId2Var = listitemsdao.getMapId2Variety();
		
		int lastIdx=firstRow + nRows;
		if(lastIdx>varid.length) {
			lastIdx=varid.length;
		}
		
		List list = new ArrayList();
		for(int i=firstRow; i<lastIdx; i++) {
			Variety var = mapVarId2Var.get(BigDecimal.valueOf(varid[i]));
			//list.add( new Object[]{varnames[i], var.getIrisId(), var.getSubpopulation(), varmismatch[i] });
			list.add( new Object[]{varnames[i], var.getIrisId(), var.getSubpopulation(), varmismatch[i] });
		}
		return list;
	}
	*/

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
