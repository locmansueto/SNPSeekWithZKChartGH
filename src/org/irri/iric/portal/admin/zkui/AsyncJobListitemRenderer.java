package org.irri.iric.portal.admin.zkui;

import java.util.concurrent.Future;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.admin.AsyncJob;
import org.irri.iric.portal.genotype.GenotypeQueryParams;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

public class AsyncJobListitemRenderer implements ListitemRenderer {

	@Override
	public void render(Listitem item, Object arg1, int arg2) throws Exception {
		// TODO Auto-generated method stub
		AsyncJob job=(AsyncJob)arg1;
		item.setValue(job);
		addListcell(item, job.getJobId());
		addListcell(item, job.getStatus());
		//addListcell(item, ((GenotypeQueryParams)job.getParams()).getSubmitter() );
		//addListcell(item, job.getParams()).getSubmitter() );
		addListcell(item, job.getIpaddress());
		Future jobfuture=job.getFuture();
		
		
		
		/*
		if(jobfuture==null) {
			addListcell(item, "UNKNOWN");
		} 
		else if(jobfuture.isCancelled())
			addListcell(item, "CANCELLED");
		else if(jobfuture.isDone())
			addListcell(item, "DONE");
		else addListcell(item, "");
		*/
		addListcell(item, job.getTermination());
		
		if(job.getParams() instanceof String)
			 addListcell(item, (String)job.getParams());
		else addListcell(item, job.getParams().toString());
		
		if(job.getDateCreated()!=null) {
			addListcell(item, AppContext.getDateFormat().format(job.getDateCreated()));
		} else addListcell(item,"");

		if(job.getDateStarted()!=null) {
			addListcell(item, AppContext.getDateFormat().format(job.getDateStarted()));
		} else addListcell(item,"");

		if(job.getDateDone()!=null) {
			addListcell(item, AppContext.getDateFormat().format(job.getDateDone()));
		} else addListcell(item,"");

		if(job.getWorkerId()!=null) {
			addListcell(item,job.getWorkerId());
		} else addListcell(item,"");
		
	}

    private void addListcell (Listitem listitem, String  value) {
        Listcell lc = new Listcell ();
        Label lb = new Label(value);
        lb.setParent(lc);
        lc.setParent(listitem);
    }
}
