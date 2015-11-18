package org.irri.iric.portal.hdf5;



import java.util.Map;

public class OutputMatrix {
	private Map mapVar2String;

	public OutputMatrix(Map mapVar2String) {
		super();
		this.mapVar2String = mapVar2String;
	}

	public Map getMapVar2String() {
		return mapVar2String;
	}

	/*
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer buff= new StringBuffer();
		if(listVarString!=null)
		{
			Iterator it = listVarString.iterator();
			while(it.hasNext()) {
				buff.append( it.next() ).append("\n");
			}
		}
		return buff.toString();
	}
	*/
	
	
	
	
}