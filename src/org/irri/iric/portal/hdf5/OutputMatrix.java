package org.irri.iric.portal.hdf5;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

import org.irri.iric.portal.AppContext;

public class OutputMatrix {
	// private Map<BigDecimal,String[]> mapVar2String;
	private Map mapVar2String;

	public OutputMatrix(Map mapVar2String) {
		super();

		int pos = -1;
		this.mapVar2String = mapVar2String;
		if (mapVar2String.size() > 0) {
			if (mapVar2String.values().iterator().next() instanceof List) {
				Map mapvar2str = new LinkedHashMap();
				Iterator itVar = mapVar2String.keySet().iterator();
				while (itVar.hasNext()) {
					Object key = itVar.next();
					List<String> strlist = (List<String>) mapVar2String.get(key);
					if (pos > -1 && pos != strlist.size())
						throw new RuntimeException(
								"OutputMatrix: pos>-1 && pos!=strlist.size() " + pos + "," + strlist.size());
					pos = strlist.size();
					mapvar2str.put(key, strlist.toArray(new String[pos]));
				}
				this.mapVar2String = mapvar2str;
			}
		}
		AppContext.debug("OutputMatrix vars=" + mapVar2String.size() + " pos=" + pos);
	}

	public Map getMapVar2String() {
		return mapVar2String;
	}

	public OutputMatrix offsetVarId(int varid_offset) {
		if (varid_offset > 0) {
			// Map<BigDecimal,String[]> mapNew=new LinkedHashMap();
			Map mapNew = new LinkedHashMap();
			Iterator<BigDecimal> itVar = mapVar2String.keySet().iterator();
			while (itVar.hasNext()) {
				BigDecimal v = itVar.next();
				mapNew.put(BigDecimal.valueOf(v.longValue() + varid_offset), mapVar2String.get(v));
				AppContext.debug("offset mapVar2String varid " + v + " -> " + (v.longValue() + varid_offset));
			}
			mapVar2String = mapNew;
		}
		return this;
	}

	public OutputMatrix remapVarId(Map mapIdx2SampleId) {
		if (mapIdx2SampleId != null) {
			// Map<BigDecimal,String[]> mapNew=new LinkedHashMap();
			Map mapNew = new LinkedHashMap();
			// StringBuffer buf=new StringBuffer();
			// buf.append("remap mapVar2String key -> mapIdx2SampleId -> +
			// mapIdx2SampleId.get(key)\n");
			Iterator<BigDecimal> itVar = mapVar2String.keySet().iterator();
			while (itVar.hasNext()) {
				BigDecimal v = itVar.next();
				mapNew.put((BigDecimal) mapIdx2SampleId.get(v.intValue()), mapVar2String.get(v));
				// buf.append(v + " -> mapIdx2SampleId -> " + mapIdx2SampleId.get(v.intValue())
				// +"\n");
			}
			// AppContext.debug(buf.toString());
			mapVar2String = mapNew;
		}
		return this;
	}
	/*
	 * @Override public String toString() { 
	 * StringBuffer buff= new StringBuffer(); if(listVarString!=null) { Iterator it
	 * = listVarString.iterator(); while(it.hasNext()) { buff.append( it.next()
	 * ).append("\n"); } } return buff.toString(); }
	 */

}