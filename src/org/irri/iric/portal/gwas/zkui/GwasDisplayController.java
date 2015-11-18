package org.irri.iric.portal.gwas.zkui;

import java.util.Iterator;
import java.util.Map;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.variety.service.Data;
import org.zkoss.chart.AxisLabels;

import org.zkoss.chart.Charts;

import org.zkoss.chart.ChartsSelectionEvent;



import org.zkoss.chart.model.DefaultCategoryModel;

import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;

import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

public class GwasDisplayController extends SelectorComposer<Window> {

	
	private int nLabels=25;
	
	@Wire
	private Listbox listboxTrait;

	@Wire
	private Button buttonResetzoom;

	
	@Wire
	private Charts chartManhattan;
	
	
	
	
	
	
	public GwasDisplayController() {
		super();
		// TODO Auto-generated constructor stub
		
		
		//chartManhattan.getChart().getResetZoomButton().
		
		
	}
	
	@Listen("onClick =#buttonResetzoom")
	public void oncleckReset() {
		//onselectTrait();
		int size = ((DefaultCategoryModel)chartManhattan.getModel()).getKeys().size();
		long interval =size/nLabels;
		
		chartManhattan.getXAxis().setMax(size);
	    chartManhattan.getXAxis().setMin(0);
	    chartManhattan.getXAxis().getLabels().setStep(interval);
	    
	}

	@Listen("onSelect =#listboxTrait")
	public void onselectTrait() {
		String trait= listboxTrait.getSelectedItem().getLabel();
		
		AppContext.debug(trait + " selected");
		
		if(trait==null || trait.isEmpty()) return;
		
		//chartManhattan.getChart().setResetZoomButton(null);


		
	    AxisLabels xlabels = chartManhattan.getXAxis().getLabels();
	    xlabels.setRotation(-80);
	    xlabels.setAlign("right");
	    //xlabels.setMaxStaggerLines(20);
	    
	    //xlabels.setStaggerLines(10);

	    //chartManhattan.getXAxis().setTickPixelInterval(20);
	    //chartManhattan.getXAxis().setMinTickInterval(10);
	    chartManhattan.getXAxis().setTickInterval(null);
	    //chartManhattan.getXAxis().getm
	    
		chartManhattan.getYAxis().setTitle("-logP");
		chartManhattan.setTitle("Manhattan plot");

		DefaultCategoryModel model=readManhattan(trait);
		chartManhattan.setModel(model);
		
		AppContext.debug("linewidth=" +  chartManhattan.getPlotOptions().getLine().getLineWidth());
		chartManhattan.getPlotOptions().getLine().setLineWidth(0);
		//chartManhattan.getPlotOptions().getLine().setColor(color);
		
		
		/*
		YAxis yaxis = chartManhattan.getYAxis(0);
		Iterator<PlotLine> itPlot = yaxis.getPlotLines().iterator();
		while(itPlot.hasNext()) {
			itPlot.next().setWidth(0);
		}
		*/
		//chartManhattan.getYAxis(0).getPlotLines();
		
		
		AppContext.debug("chartManhattan.getyAxisSize()="+ chartManhattan.getyAxisSize());
		
		//chartManhattan.getChart().getResetZoomButton().setRelativeTo(relativeTo);
				//chartManhattan.getYAxis(0).getPlotLines().
		
		
		
		//.getSeries(0). .size();
		//model.getSeries()
		
		//long interval = (chartManhattan.getXAxis().getMax().longValue() -chartManhattan.getXAxis().getMin().longValue())/50;
		long interval =model.getKeys().size()/nLabels;
		
		 //ChartsEvents.
		
	    //AppContext.debug( "setMaxStaggerLines=" + xlabels.getMaxStaggerLines() + "  StaggerLines=" +  xlabels.getStaggerLines() + " max="+chartManhattan.getXAxis().getMax() +
	    //		" min=" + chartManhattan.getXAxis().getMin() + " step=" + xlabels.getStep() + " xaxissize=" + chartManhattan.getXAxisSize() + "  model.getKeys().size()=" + model.getKeys().size());

	    //chartManhattan.getXAxis().get

	    xlabels.setStep( interval );
	}
	
	
	@Listen("onSelection = #chartManhattan")
	public void doSelection(ChartsSelectionEvent event) {
	    // doing the zooming in function
		
		
	    double min = event.getXAxisMin().longValue();
	    double max = event.getXAxisMax().longValue();
	    //updateSelection(min, max);
	    
	    AppContext.debug("event.getData()=" + event.getData());
	    
	    
	    
	    		
	    //displaySNPDetails( chartManhattan.getModel() )
	    
	    
	    long interval = Math.max(1,  Double.valueOf((max-min)/25.0).longValue());
	    
	    // enable the zooming out button
	    //btn.setVisible(true);
	    //Events.sendEvent(event.getName(), chartManhattan, null);
	    
	    AppContext.debug("1event.getName()=" + event.getName() + "  min=" + min + " max=" + max + " interval=" + interval);
	    
	 //   chartManhattan.getXAxis().getLabels().setStep( interval );
	    //chartManhattan.getXAxis()
	    //chartManhattan.re
	    
	    //AppContext.debug("2event.getName()=" + event.getName() + "  min=" + min + " max=" + max + " interval=" + interval);
	    chartManhattan.getXAxis().getLabels().setStep( interval );
	    chartManhattan.getXAxis().setMax(max);
	    chartManhattan.getXAxis().setMin(min);
	    
	   // chartManhattan.invalidate();
	}
	
	
	private DefaultCategoryModel readManhattan(String trait) {
	
		
		DefaultCategoryModel model= new DefaultCategoryModel();
		Map<String, double[]>  mapPos2Values =  Data.getGWASPlot();
		Iterator<String> itPos=mapPos2Values.keySet().iterator();
		while(itPos.hasNext()) {
			
			String pos= itPos.next();
			double vals[]= mapPos2Values.get(pos);
			model.setValue(("chr " + (vals[0])).replace(".0","") , pos, -Math.log10(vals[2]));
		}
		
		AppContext.debug(mapPos2Values.size() + " pvalues");
		
		return model;
	
		
	}
	
}
