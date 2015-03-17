package org.irri.iric.portal.genomics.zkui;

import java.util.Map;

import org.irri.iric.portal.chado.domain.VLocusNotes;
import org.irri.iric.portal.domain.Locus;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

public class LocusGridRenderer implements  RowRenderer{

	//private Map mapUniquename2Description;
	
	
	
	/*
	public LocusGridRenderer(Map mapUniquename2Description) {
		super();
		this.mapUniquename2Description = mapUniquename2Description;
	}
	*/


	@Override
	public void render(Row row, Object data, int index) throws Exception {
		// TODO Auto-generated method stub
		
			VLocusNotes locus = (VLocusNotes)data;
		
			//row.setAlign("center");
			
			new Label(locus.getUniquename()).setParent(row);
			new Label(locus.getContig()).setParent(row);
			new Label(locus.getFmin().toString()).setParent(row);
			new Label(locus.getFmax().toString()).setParent(row);
			new Label(locus.getStrand().toString()).setParent(row);
			new Label( locus.getNotes() ).setParent(row);
			
			/*
			if(mapUniquename2Description!=null) {
				Object descr = mapUniquename2Description.get(locus.getUniquename());
				if(descr!=null) new Label((String)descr).setParent(row);
			}*/
	}

	public LocusGridRenderer() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
