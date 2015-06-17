package org.irri.iric.portal.admin.zkui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.domain.Variety;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Html;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

public class VarietyDownloadsRowRenderer implements RowRenderer {

	private List listchecks = new ArrayList();
	private List listNames = new ArrayList();
	
	private Map mapIris2ERS;
	private Map mapIris2SRA;
	
	
	
	public VarietyDownloadsRowRenderer(Map mapIris2ERS, Map mapIris2SRA) {
		super();
		this.mapIris2ERS = mapIris2ERS;
		this.mapIris2SRA = mapIris2SRA;
	}
	@Override
	public void render(Row row, Object data, int index) throws Exception {
		// TODO Auto-generated method stub
	
		Variety var = (Variety)data;
		Checkbox chkbox = new Checkbox();
		listchecks.add(index, chkbox );
		chkbox.setParent(row);
		
		Label lblName = new Label();
		lblName.setValue( var.getName());
		lblName.setParent(row);
		
		listNames.add(index, var.getName() );
		
		lblName = new Label();
		lblName.setValue( var.getIrisId() );
		lblName.setParent(row);

		/*
		createdownloadLinks(AppContext.getFastqURL() + var.getIrisId() + ".html", AppContext.getFastqURL() + var.getIrisId() + ".html", 
				AppContext.getFastqURL() + var.getIrisId() + ".html").setParent(row );
				*/
		
		createFastQdownloadLinks("http://www.ncbi.nlm.nih.gov/sra?LinkName=biosample_sra&from_uid=" + mapIris2SRA.get(var.getIrisId().replace(" ", "_")), 
				"http://www.ebi.ac.uk/ena/data/view/" +  mapIris2ERS.get(var.getIrisId().replace(" ", "_")) ).setParent(row);

		createdownloadLinks(AppContext.getBamURL() + var.getIrisId() + ".html", AppContext.getBamURL() + var.getIrisId().replace(" ", "_") + ".html", 
				AppContext.getBamURL() + var.getIrisId().replace(" ", "_") + ".html").setParent(row );

		createdownloadLinks(AppContext.getVcfURL() + var.getIrisId() + ".html", AppContext.getVcfURL() + var.getIrisId().replace(" ", "_") + ".html", 
				AppContext.getVcfURL() + var.getIrisId().replace(" ", "_") + ".html").setParent(row );

		/*
		Hbox hboxButtons = new Hbox();
		
		Button butt = new Button();
		butt.setHeight("30px");
		butt.setWidth("55px");
		butt.setImage("images/iric/awshttp.png");
		butt.setHref(  AppContext.getFastqURL() + var.getIrisId() + ".html"  );
		butt.setParent(hboxButtons);

		Hbox hboxPadd1 = new Hbox();
		hboxPadd1.setWidth("10px");
		hboxButtons.appendChild(hboxPadd1);
		
		butt = new Button();
		butt.setHeight("30px");
		butt.setWidth("55px");
		butt.setImage("images/iric/astihttp.png");
		butt.setHref(  AppContext.getFastqURL() + var.getIrisId() + ".html"  );
		butt.setParent(hboxButtons);

		Hbox hboxPadd2 = new Hbox();
		hboxPadd2.setWidth("10px");
		hboxButtons.appendChild(hboxPadd2);
		
		butt = new Button();
		butt.setHeight("30px");
		butt.setWidth("55px");
		butt.setImage("images/iric/astiftp.png");
		butt.setHref(  AppContext.getFastqURL() + var.getIrisId() + ".html"  );
		butt.setParent(hboxButtons);
		
		hboxButtons.setParent(row );
		*/
		
		/*
		Html html = new Html( AppContext.getFastqURL() + var.getIrisId() + ".html" );
		html.setParent(row);
		*/
		
	}
	private Hbox createdownloadLinks(String href1, String href2, String href3) {
		Hbox hboxButtons = new Hbox();
		
		Button butt = new Button();
		butt.setHeight("30px");
		butt.setWidth("55px");
		butt.setSclass("myimgbutton");
		butt.setImage("images/iric/awshttp.png");
		butt.setHref( href1 );
		butt.setTarget("_rawdownload");
		butt.setParent(hboxButtons);

		Hbox hboxPadd1 = new Hbox();
		hboxPadd1.setWidth("10px");
		hboxButtons.appendChild(hboxPadd1);
		
		butt = new Button();
		butt.setHeight("30px");
		butt.setWidth("55px");
		butt.setSclass("myimgbutton");
		butt.setImage("images/iric/astihttp.png");
		butt.setHref(  href2 );
		butt.setTarget("_rawdownload");
		butt.setParent(hboxButtons);

		Hbox hboxPadd2 = new Hbox();
		hboxPadd2.setWidth("10px");
		hboxButtons.appendChild(hboxPadd2);
		
		butt = new Button();
		butt.setHeight("30px");
		butt.setWidth("55px");
		butt.setSclass("myimgbutton");
		butt.setImage("images/iric/astiftp.png");
		butt.setHref( href3 );
		butt.setTarget("_rawdownload");
		butt.setParent(hboxButtons);
		return hboxButtons;
	}
	
	private Hbox createFastQdownloadLinks(String hrefsra, String hrefers) {
		
		
		Hbox hboxButtons = new Hbox();
		
		Button butt = new Button();
		butt.setHeight("30px");
		butt.setWidth("55px");
		butt.setSclass("myimgbutton");
		butt.setImage("images/iric/sra.png");
		butt.setHref( hrefsra );
		butt.setTarget("_rawdownload");
		butt.setParent(hboxButtons);

		Hbox hboxPadd1 = new Hbox();
		hboxPadd1.setWidth("10px");
		hboxButtons.appendChild(hboxPadd1);
		
		butt = new Button();
		butt.setHeight("30px");
		butt.setWidth("55px");
		butt.setSclass("myimgbutton");
		butt.setImage("images/iric/ena.png");
		butt.setHref(  hrefers );
		butt.setTarget("_rawdownload");
		butt.setParent(hboxButtons);

		
		return hboxButtons;
	}


	public List getListchecks() {
		return listchecks;
	}


	public List getListNames() {
		return listNames;
	}
	
	
	
	
}
