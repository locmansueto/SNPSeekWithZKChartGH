package org.irri.iric.portal.variety.zkui;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.genotype.service.GenotypeFacade;
import org.irri.iric.portal.genotype.zkui.SNPListItemRenderer;
import org.irri.iric.portal.variety.domain.Germplasm;
import org.irri.iric.portal.variety.domain.List3k;
import org.irri.iric.portal.variety.service.VarietyFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.gmaps.Gmaps;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Textbox;

@Controller
public class VarietyQueryController extends SelectorComposer<Component> {

	
    private static final long serialVersionUID = 1L;

    @Wire
    private Gmaps gmaps;
    
    
    @Wire
    private Combobox comboVarname;
    @Wire
    private Combobox comboIrisId;

    
    @Wire
    private Combobox comboCountry;
    
    @Wire
    private Combobox comboSubpopulation;
    
    @Wire
    private Textbox msgbox;
    

    @Wire
    private Button searchButton;
    
    @Autowired
    private VarietyFacade  variety;
    
    @Wire 
    private Listbox varietyresult;
    
    
    @Wire
    private Grid gridGermplasm;
    @Wire
    private Textbox textboxGermAccession;
    @Wire
    private Textbox textboxGermNsftvId;
    @Wire
    private Textbox textboxGermSubpopulation;
    @Wire
    private Textbox textboxGermCountry;
    @Wire
    private Textbox textboxGermLatitude;
    @Wire
    private Textbox textboxGermLongitude;
    @Wire
    private Listbox listboxGermPhenotypes;	
    @Wire
    private Textbox textboxIRISId;
    
    @Wire
    private Iframe iframePhylotree;
    
    //@Wire
    //private Listbox selectTreescaling;
		

	@Listen("onClick = #searchButton")    
	public void searchList3k()    {
		
		variety =  (VarietyFacade)AppContext.checkBean(variety , "VarietyFacade");
		
		java.util.List<List3k> varsresult = new java.util.ArrayList<List3k>();
		
		if(comboVarname.getValue()!=null &&  !comboVarname.getValue().isEmpty() && comboIrisId.getValue()!=null &&  !comboIrisId.getValue().isEmpty())
		{
			msgbox.setValue( "INVALID INPUT: Only one of either Variety name or IRIS ID can be specified" );
			msgbox.setStyle( "font-color:red" );
			return;
		}
		
		if(comboVarname.getValue()!=null &&  !comboVarname.getValue().isEmpty() ) {
			
			msgbox.setValue(comboVarname.getValue().toUpperCase() +  " PASSPORT DATA"); 
			
			show_passport( variety.getGermplasmByName( comboVarname.getValue() ) );
		//	gridGermplasm.setVisible(true);
		} else if (comboIrisId.getValue()!=null &&  !comboIrisId.getValue().isEmpty() ) 
		{
			msgbox.setValue(comboIrisId.getValue().toUpperCase() +  " PASSPORT DATA"); 
			show_passport( variety.getGermplasmByIrisId( comboIrisId.getValue() ) );
			
		}
		else {
			
			gridGermplasm.setVisible(false);
			iframePhylotree.setVisible(true);
			varietyresult.setVisible(true);
			
			List3k example = new List3k();
			
			StringBuffer msg= new StringBuffer();
			if(comboCountry==null) throw new RuntimeException("comboCountry==null");
			if(comboCountry.getValue()==null) throw new RuntimeException("comboCountry.getValue()==null");
			
			if(comboCountry.getValue()!=null && !comboCountry.getValue().isEmpty() ) {
				example.setCountryOfOriginOfSource(  comboCountry.getValue());
				msg.append(" COUNTRY=" + comboCountry.getValue().toUpperCase());
			}
	
			if(comboSubpopulation==null) throw new RuntimeException("comboSubpopulation==null");
			if(comboSubpopulation.getValue()!=null && !comboSubpopulation.getValue().isEmpty() )	{	
				example.setVarietygroupOfSource( comboSubpopulation.getValue());
				if(msg.length()>0) msg.append(" AND ");
					
				msg.append(" SUBPOPULATION=" + comboSubpopulation.getValue().toUpperCase());
			}
			
			msgbox.setValue("VARIETY WHERE " + msg.toString());
					
			varsresult.addAll(  variety.getGermplasmByExample( example )  );
			msgbox.setValue( msgbox.getValue() + " ... RESULT: " + varsresult.size() + " rows" );
			msgbox.setStyle( "font-color:black" );
		}
		
		
		
		

		// update table header
		Listheader lhr = (Listheader)varietyresult.getListhead().getFirstChild();    	
		String[] headers = new String[]{"NAME","SUBPOPULATION", "COUNTRY"};
		int i = 0;
		while(lhr != null) {
			lhr.setLabel(headers[i] );
			lhr = (Listheader)lhr.getNextSibling();
			i++;
		}
		
		if(varsresult.size()>2)
		{
			
			java.util.Map<String,String[]> mapAccession2IRISId = variety.getAccession2IRISMap();
					
			
			StringBuffer varnames = new StringBuffer();
			java.util.Iterator<List3k> itvars = varsresult.iterator();
			while(itvars.hasNext()) {
				varnames.append(  mapAccession2IRISId.get(itvars.next().getVarnameOfGenStockSrc().toUpperCase() )[1] );
				if(itvars.hasNext()) varnames.append(",");
			}
			show_phylotree(varnames.toString()); 
		} 
		else
			iframePhylotree.setVisible(false);
		
	//	gridGermplasm.setVisible(false);
		varietyresult.setModel( new SimpleListModel(varsresult) );
		
		
		gmaps.setCenter( 14.167774, 121.254547);
		gmaps.setZoom(15);
		
	}
	/*
	private void searchGermplasm()    {
		
		variety =  (VarietyFacade)AppContext.checkBean(variety , "VarietyFacade");
		
		java.util.List<Germplasm> varsresult = new java.util.ArrayList<Germplasm>();
		
		if(comboVarname.getValue()!=null &&  !comboVarname.getValue().isEmpty() ) {
			
			msgbox.setValue(comboVarname.getValue().toUpperCase() +  " PASSPORT DATA"); 
			
			show_passport( variety.getGermplasmByName( comboVarname.getValue() ) );
		//	gridGermplasm.setVisible(true);
		}
		else {
			
			gridGermplasm.setVisible(false);
			iframePhylotree.setVisible(true);
			varietyresult.setVisible(true);
			
			Germplasm example = new Germplasm();
			
			StringBuffer msg= new StringBuffer();
			if(comboCountry==null) throw new RuntimeException("comboCountry==null");
			if(comboCountry.getValue()==null) throw new RuntimeException("comboCountry.getValue()==null");
			
			if(comboCountry.getValue()!=null && !comboCountry.getValue().isEmpty() ) {
				example.setCountry(  comboCountry.getValue());
				msg.append(" COUNTRY=" + comboCountry.getValue().toUpperCase());
			}
	
			if(comboSubpopulation==null) throw new RuntimeException("comboSubpopulation==null");
			if(comboSubpopulation.getValue()!=null && !comboSubpopulation.getValue().isEmpty() )	{	
				example.setSubpopulation(comboSubpopulation.getValue());
				if(msg.length()>0) msg.append(" AND ");
					
				msg.append(" SUBPOPULATION=" + comboSubpopulation.getValue().toUpperCase());
			}
			
			msgbox.setValue("VARIETY WHERE " + msg.toString());
					
			varsresult.addAll(  variety.getGermplasmByExample( example )  );
			msgbox.setValue( msgbox.getValue() + " ... RESULT: " + varsresult.size() + " rows" );
			msgbox.setStyle( "font-color:black" );
		}
		
		
		
		

		// update table header
		Listheader lhr = (Listheader)varietyresult.getListhead().getFirstChild();    	
		String[] headers = new String[]{"NAME","SUBPOPULATION", "COUNTRY"};
		int i = 0;
		while(lhr != null) {
			lhr.setLabel(headers[i] );
			lhr = (Listheader)lhr.getNextSibling();
			i++;
		}
		
		if(varsresult.size()>2)
		{
			
			java.util.Map mapAccession2IRISId = variety.getAccession2IRISMap();
					
			
			StringBuffer varnames = new StringBuffer();
			java.util.Iterator<Germplasm> itvars = varsresult.iterator();
			while(itvars.hasNext()) {
				varnames.append(  mapAccession2IRISId.get(itvars.next().getAccession().toUpperCase() ));
				if(itvars.hasNext()) varnames.append(",");
			}
			show_phylotree(varnames.toString()); 
		} 
		else
			iframePhylotree.setVisible(false);
		
	//	gridGermplasm.setVisible(false);
		varietyresult.setModel( new SimpleListModel(varsresult) );
		
	}


	private void show_passport(Germplasm germ) {
		
		iframePhylotree.setVisible(false);
		varietyresult.setVisible(false);

	    textboxGermAccession.setValue( germ.getAccession());
	    
	    textboxGermNsftvId.setValue( germ.getNsftvId().toString());
	    textboxGermLatitude.setValue( germ.getLatitude().toString());
		textboxGermLongitude.setValue( germ.getLongitude().toString());
		textboxGermCountry.setValue( germ.getCountry());
		textboxGermSubpopulation.setValue( germ.getSubpopulation());
		
		java.util.List listPhen ;
		if(germ.getPhenotypeses()==null)
			listPhen = new java.util.ArrayList( new java.util.TreeSet(  variety.getPhenotypesByGermplasmNsftid(germ.getNsftvId())));
		else listPhen = new java.util.ArrayList( new java.util.TreeSet(  germ.getPhenotypeses() )); 
		
		listboxGermPhenotypes.setModel( new SimpleListModel(listPhen) );
		
		//germ.getGenotypings()
		
		gridGermplasm.setVisible(true);
		
	}
	*/
	
	private void show_passport(List3k germ) {
		
		iframePhylotree.setVisible(false);
		varietyresult.setVisible(false);

	    textboxGermAccession.setValue( germ.getVarnameOfGenStockSrc());
	    textboxIRISId.setValue( germ.getIrisUniqueId());
	    textboxGermNsftvId.setValue("");
	    textboxGermLatitude.setValue("");
		textboxGermLongitude.setValue("");
		textboxGermCountry.setValue( germ.getCountryOfOriginOfSource());
		textboxGermSubpopulation.setValue( germ.getVarietygroupOfSource());
		/*
		java.util.List listPhen ;
		if(germ.getPhenotypeses()==null)
			listPhen = new java.util.ArrayList( new java.util.TreeSet(  variety.getPhenotypesByGermplasmNsftid(germ.getNsftvId())));
		else listPhen = new java.util.ArrayList( new java.util.TreeSet(  germ.getPhenotypeses() )); 
		*/
		
		listboxGermPhenotypes.setModel( new SimpleListModel(new java.util.ArrayList()) );
		
		//germ.getGenotypings()
		
		gridGermplasm.setVisible(true);
		
	}
	
	private void show_phylotree(String varnames) {
		
		//if(varnames.split(",").length<400) {
		
			//int height = varnames.split(",").length*25;
			int nvars = varnames.split(",").length;
			int height = nvars*21;
			int width = nvars*30;
			
			int treescale = 1;
			/*
			switch ( selectTreescaling.getSelectedIndex() ) {
				case 0: treescale=1; break;
				case 1: treescale=5; break;
				case 2: treescale=10; break;
				case 3: treescale=50; break;
				case 4: treescale=100; break;
			}
			*/
			/*
		    <listitem label="x1"/>    
		    <listitem label="x5" selected="true" />    
		    <listitem label="x10"/>    
		    <listitem label="x50"/>    
		    <listitem label="x100"/>    
		    */
			
			
			iframePhylotree.setStyle("height:" + Integer.toString(height) + "px;width:1500px");
			//iframePhylotree.setStyle("height:" + Integer.toString(height) + "px;width:" + Integer.toString(width) + "px");
			iframePhylotree.setScrolling("yes");
			
			iframePhylotree.setVisible(true);
			//varnames=varnames.replaceAll(" ", "%20");
			varnames=varnames.replaceAll(" ", "%20");
			String url = "jsp/phylotreeGermplasms.jsp?scale=" + treescale + "&nsftvid=" + varnames ;
			System.out.println( url );
			iframePhylotree.setSrc( url );
		//} else
		//	iframePhylotree.setSrc( "jsp/viewtree.html" );
	
			
			
			
	}
	
}
