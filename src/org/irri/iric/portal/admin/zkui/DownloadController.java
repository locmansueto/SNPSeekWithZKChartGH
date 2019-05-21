package org.irri.iric.portal.admin.zkui;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.SimpleListModelExt;
import org.irri.iric.portal.admin.AsyncJob;
import org.irri.iric.portal.admin.AsyncJobImpl;
import org.irri.iric.portal.admin.AsyncJobReport;
import org.irri.iric.portal.admin.JobsFacade;
import org.irri.iric.portal.admin.WorkspaceFacade;
import org.irri.iric.portal.domain.Gene;
import org.irri.iric.portal.domain.MultiReferenceLocusImpl;
import org.irri.iric.portal.domain.Organism;
import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.genomics.GenomicsFacade;
import org.irri.iric.portal.genomics.VariantSequenceQuery;
import org.irri.iric.portal.genotype.GenotypeFacade;
import org.irri.iric.portal.variety.VarietyFacade;
import org.irri.iric.portal.variety.VarietyPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.A;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.SimpleListModel;

@Controller()
// @EnableAsync
public class DownloadController extends SelectorComposer<Component> {

	@Wire
	private Grid gridRawVarietyFiles;

	@Autowired
	private VarietyFacade varietyfacade;

	@Autowired
	private GenomicsFacade genomicsfacade;

	@Autowired
	@Qualifier("GalaxyJobsFacade")
	private JobsFacade jobsfacade;

	@Autowired
	@Qualifier("GenotypeFacade")
	private GenotypeFacade genotypefacade;

	@Autowired
	@Qualifier("WorkspaceFacade")
	private WorkspaceFacade workspacefacade;

	@Wire
	private Label labelDownloadProgressMsg;
	@Wire
	private A aDownloadProgressURL;

	@Wire
	private Checkbox checkboxFastq;
	@Wire
	private Checkbox checkboxBAM;
	@Wire
	private Checkbox checkboxVCF;
	@Wire
	private Listbox listboxVarlist;
	@Wire
	private Button buttonUnmarkAll;
	@Wire
	private Button buttonDownloadMarked;
	@Wire
	private Listbox listboxSite;

	@Wire
	private Listbox listboxQueryvar;
	@Wire
	private Listbox listboxVarlistSeq;

	@Wire
	private Listbox listboxLocuslist;

	@Wire
	private Combobox comboboxLocus;

	@Wire
	private Combobox comboQuerychr;
	@Wire
	private Listbox listboxQuerystrand;
	@Wire
	private Intbox intboxStart;
	@Wire
	private Intbox intboxEnd;
	@Wire
	private Button buttonDownloadSequence;

	@Wire
	private Listbox listboxReference;

	@Wire
	private Label labelExampleLocus;
	@Wire
	private Label labelExampleContig;

	private boolean renderedDownloadtable = false;

	private Set getDataset() {
		Set s = new HashSet();
		s.add(VarietyFacade.DATASET_SNPINDELV2_IUPAC);
		return s;
	}

	@Listen("onClick = #buttonDownloadSequence")
	public void onclickDownlaodsequence() {

		varietyfacade = (VarietyFacade) AppContext.checkBean(varietyfacade, "VarietyFacade");
		workspacefacade = (WorkspaceFacade) AppContext.checkBean(workspacefacade, "WorkspaceFacade");

		// try {
		Set setVarieties = new LinkedHashSet();
		Set setLoci = new LinkedHashSet();
		String selOrg = this.listboxReference.getSelectedItem().getLabel();

		if (listboxQueryvar.getSelectedItem().getLabel().toString().isEmpty()) {
			if (!this.listboxVarlistSeq.getSelectedItem().getLabel().isEmpty()) {
				AppContext.debug("querylist " + listboxVarlistSeq.getSelectedItem().getLabel());
				if (listboxVarlistSeq.getSelectedItem().getLabel().toLowerCase().equals("all")) {
					if (intboxStart.getValue() != null && intboxEnd.getValue() != null) {
						if (Math.abs(intboxEnd.getValue() - intboxStart.intValue()) > AppContext
								.getMaxlengthUniDownload("snp_filtered")) {
							Messagebox.show("Please limit region to "
									+ AppContext.getMaxlengthUniDownload("snp_filtered") / 1000 + " kbp");
							return;
						}
					}

					setVarieties = varietyfacade.getGermplasm(getDataset());
				} else
					setVarieties = workspacefacade.getVarieties(listboxVarlistSeq.getSelectedItem().getLabel());
			} else {
				Messagebox.show("Please select variety or variety list");
				return;
			}
		} else {

			setVarieties.add(varietyfacade.getGermplasmByName(listboxQueryvar.getSelectedItem().getValue().toString(),
					getDataset()));
		}

		if (this.listboxLocuslist.getSelectedItem().getLabel().isEmpty()) {
			if (comboQuerychr.getValue() == null || comboQuerychr.getValue().isEmpty() || intboxEnd.getValue() == null
					|| intboxStart.getValue() == null || listboxQuerystrand.getSelectedItem().getLabel().isEmpty()) {
				Messagebox.show("Please complete region information, or select locus list");
				return;
			} else
				setLoci.add(new MultiReferenceLocusImpl(selOrg, comboQuerychr.getValue(), intboxStart.getValue(),
						intboxEnd.getValue(),
						Integer.valueOf(listboxQuerystrand.getSelectedItem().getValue().toString())));
		} else {
			setLoci = workspacefacade.getLoci(listboxLocuslist.getSelectedItem().getLabel());
		}

		if (setLoci.size() > AppContext.getMaxLocusListDownload("snp_all")) {
			Messagebox.show("Please limit  " + AppContext.getMaxLocusListDownload("snp_all") + " loci");
			return;
		}
		
		labelDownloadProgressMsg.setVisible(false);
		aDownloadProgressURL.setVisible(false);
		if ( setVarieties.size() * setLoci.size() > 10) {
			// asynchronosu
//			aDownloadProgressURL.setVisible(false);/			AppContext.debug("waiting for varseq");
			String[] status=downloadsequence(setVarieties, setLoci, null, (String) listboxReference.getSelectedItem().getValue(),false);
			if(!status[0].equals(JobsFacade.JOBSTATUS_ERROR)) {
				aDownloadProgressURL.setHref(status[1]);
				aDownloadProgressURL.setLabel(status[1]);
				labelDownloadProgressMsg.setValue("Job is submitted. Please monitor progress at ");
				labelDownloadProgressMsg.setVisible(true);
				aDownloadProgressURL.setVisible(true);
			}

		} else {
			String[] status=downloadsequence(setVarieties, setLoci, null, (String) listboxReference.getSelectedItem().getValue(),true);
			if(!status[0].equals(JobsFacade.JOBSTATUS_DONE)) {
				Messagebox.show(status[1], status[0], Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}

//		//if ((AppContext.isDev() && (setVarieties.size() > 10 || setLoci.size() > 5)) || setVarieties.size() > 100
//		//		|| setLoci.size() > 50) {
//		if ( setVarieties.size() > 10 || setLoci.size() > 10) {
//			// use asynchronous
//			try {
//				Callable<AsyncJobReport> callreport = callableDownloadsequence(setVarieties, setLoci);
//				AsyncJobReport report = callreport.call();
//				if (report != null) {
//					if (report.getMessage().equals(JobsFacade.JOBSTATUS_SUBMITTED)) {
//						this.labelDownloadProgressMsg.setValue("Job is submitted. Please monitor progress at ");
//						this.aDownloadProgressURL.setLabel(report.getUrlProgress());
//						this.aDownloadProgressURL.setHref(report.getUrlProgress());
//						aDownloadProgressURL.setVisible(true);
//						enableDownloadButtons(false);
//						AppContext.debug("job " + report.getJobid() + " submitted at " + report.getUrlProgress());
//					} else {
//						this.labelDownloadProgressMsg.setValue(report.getMessage());
//					}
//					labelDownloadProgressMsg.setVisible(true);
//				}
//			} catch (Exception ex) {
//				ex.printStackTrace();
//				Messagebox.show(ex.getMessage());
//			}
//
//		} else {
//			// wait to finish
//			labelDownloadProgressMsg.setVisible(false);
//			aDownloadProgressURL.setVisible(false);
//			AppContext.debug("waiting for varseq");
//			downloadsequence(setVarieties, setLoci, null, (String) listboxReference.getSelectedItem().getValue());
//		}
	}

	public Callable<AsyncJobReport> callableDownloadsequence(final Collection setVarieties, final Collection setLoci) {
		return new Callable<AsyncJobReport>() {
			@Override
			public AsyncJobReport call() throws Exception {

				Future future = null;
				jobsfacade = (JobsFacade) AppContext.checkBean(jobsfacade, "GalaxyJobsFacade");

				String msg = JobsFacade.JOBSTATUS_SUBMITTED;
				String jobid = "vcf2fasta-" + AppContext.createTempFilename();
				String url = AppContext.getHostname() + "/" + AppContext.getHostDirectory() + "/_jobs.zul?jobid="
						+ jobid;
				if (AppContext.getHostDirectory().isEmpty())
					url = AppContext.getHostname() + "/_jobs.zul?jobid=" + jobid;

				/*
				 * Object req = Executions.getCurrent().getNativeRequest(); String reqstr="";
				 * if(req !=null && req instanceof HttpServletRequest) { HttpServletRequest
				 * servreq= (HttpServletRequest)req; reqstr="-"+ servreq.getRemoteAddr() + "-" +
				 * servreq.getRemoteHost();
				 * 
				 * String forwardedfor= servreq.getHeader("x-forwarded-for");
				 * if(forwardedfor!=null) reqstr+="-" + forwardedfor; }
				 * 
				 * String ipaddres= Sessions.getCurrent().getLocalAddr() +"-"+
				 * Sessions.getCurrent().getLocalName() + "-" +
				 * Sessions.getCurrent().getRemoteAddr() + "-" +
				 * Sessions.getCurrent().getRemoteHost() + "-" +
				 * Sessions.getCurrent().getServerName() + reqstr;
				 * 
				 */

				String ipaddres = AppContext.getSubmitter();

				if (jobsfacade.checkSubmitter(ipaddres)) {
					msg = "You have a running long job. Please try again when that job is done.";
				} else {
					AsyncJob job = new AsyncJobImpl(jobid, "varlist=" + setVarieties.size() + ";loci=" + setLoci.size(),
							ipaddres);
					if (jobsfacade.addJob(job)) {
						// Future futureReportjob = downloadsequenceAsync(setVarieties, setLoci, jobid);

						VariantSequenceQuery query = new VariantSequenceQuery(setVarieties, setLoci, jobid,
								(String) listboxReference.getSelectedItem().getValue());
						query.setSubmitter(ipaddres);
						genomicsfacade = (GenomicsFacade) AppContext.checkBean(genomicsfacade, "GenomicsFacade");
						Future futureReportjob = genomicsfacade.createVariantsFastaAsync(query);

						// AsyncJobReport rep=(AsyncJobReport)futureReportjob.get();
						// AppContext.debug( (rep==null? "rep=null":rep.getMessage() ));
						// rep.
						job.setFuture(futureReportjob);
						future = futureReportjob;
						msg = jobsfacade.JOBSTATUS_SUBMITTED;
						jobid = job.getJobId();
						url = job.getUrl();
					} else {
						msg = jobsfacade.JOBSTATUS_REFUSED;
					}
				}
				AppContext.debug("callableDownloadsequence.. submitted");
				return new AsyncJobReport(jobid, msg, url, future);

			}
		};
	}

	private void enableDownloadButtons(boolean enable) {
		this.buttonDownloadSequence.setDisabled(!enable);
	}

	/*
	 * @Async public Future<Void> downloadsequenceAsync(Collection setVarieties,
	 * Collection setLoci, String jobid) { try { VariantSequenceQuery query=new
	 * VariantSequenceQuery(setVarieties, setLoci, jobid); genomicsfacade =
	 * (GenomicsFacade)AppContext.checkBean(genomicsfacade, "GenomicsFacade");
	 * String dir = genomicsfacade.createVariantsFasta(query); } catch(Exception ex)
	 * { ex.printStackTrace(); Messagebox.show(ex.getMessage()); } return new
	 * AsyncResult<Void>(null); }
	 */

	@Listen("onSelect = #listboxReference")
	public void onselectlistboxReference() {

		genotypefacade = (GenotypeFacade) AppContext.checkBean(genotypefacade, "GenotypeFacade");
		String selOrg = listboxReference.getSelectedItem().getLabel();
		// get contigs for selected reference
		// List listContigs =
		// AppContext.createUniqueUpperLowerStrings(genotypefacade.getContigsForReference(
		// selOrg ), true,true);
		List listContigs = genotypefacade.getContigsForReference(selOrg);

		// get loci for reference
		// List listLoci = AppContext.createUniqueUpperLowerStrings(
		// genotypefacade.getLociForReference( selOrg ), true,true);
		List listLoci = new ArrayList(genotypefacade.getLociForReference(selOrg));

		this.comboQuerychr.setModel(new SimpleListModelExt(listContigs));
		this.comboboxLocus.setModel(new SimpleListModelExt(listLoci)); // .subList(0, 100)));
		comboQuerychr.setValue("");
		comboboxLocus.setValue("");
		this.intboxEnd.setValue(null);
		this.intboxStart.setValue(null);

		if (selOrg.equals(Organism.REFERENCE_9311))
			labelExampleContig.setValue("(ex. 9311_chr01, scaffold01_4619) ");
		else if (selOrg.equals(Organism.REFERENCE_KASALATH))
			labelExampleContig.setValue("(ex. chr01, um) ");
		// else if(selOrg.equals(AppContext.getDefaultOrganism()))
		// labelChrExample.setValue("(ex. 9311_chr01, scaffold01_4619) " );
		else
			labelExampleContig.setValue("(ex. " + listContigs.get(1).toString().toLowerCase() + ") ");

		if (selOrg.equals(Organism.REFERENCE_NIPPONBARE))
			labelExampleLocus.setValue("(ex. loc_os01g01010)");
		else if (selOrg.equals(Organism.REFERENCE_IR64))
			labelExampleLocus.setValue("(ex. osir64_00001g0100)");
		else if (selOrg.equals(Organism.REFERENCE_9311))
			labelExampleLocus.setValue("(ex. os9311_01g010100)");
		else if (selOrg.equals(Organism.REFERENCE_KASALATH))
			labelExampleLocus.setValue("(ex. oskasal01g010050)");
		else if (selOrg.equals(Organism.REFERENCE_DJ123))
			labelExampleLocus.setValue("(ex. osdj12_00001g010100)");

	}

	public String[] downloadsequence(Collection setVarieties, Collection setLoci, String jobid, String reference, boolean sync) {

		try {

			VariantSequenceQuery query = new VariantSequenceQuery(setVarieties, setLoci, jobid, reference);
			query.setSubmitter(AppContext.getSubmitter());
			query.setSync(sync);
			genomicsfacade = (GenomicsFacade) AppContext.checkBean(genomicsfacade, "GenomicsFacade");
			String dir = genomicsfacade.createVariantsFasta(query);

				//String paths[]= dir.split(Pattern.quote(File.separator));
				//String zipfilename = AppContext.getTempDir() + paths[paths.length - 1].replace("\\","") + ".zip";
				String zipfilename= AppContext.getTempDir()  + new File(dir).getName()+".zip";
				
			if(sync) {
				// Filedownload.save(zipfilename, "application/zip");
				Filedownload.save(new File(zipfilename), "application/zip");
				// AppContext.debug("File download complete! Saved to: "+filename);
				org.zkoss.zk.ui.Session zksession = Sessions.getCurrent();
				AppContext.debug("variantsequence download complete!" + zipfilename + " "
						+ "Downloaded to:"
						+ zksession.getRemoteHost() + "  " + zksession.getRemoteAddr());
				return new String[] {JobsFacade.JOBSTATUS_DONE, zipfilename};
			} else {
				String fname=new File(dir).getName();
				return new String[] {JobsFacade.JOBSTATUS_SUBMITTED, AppContext.getHostname()+AppContext.getHostDirectory()+ "_jobs.zul?jobid="+ fname};
			}

		
		} catch (Exception ex) {
			ex.printStackTrace();
			//Messagebox.show(ex.getMessage());
			return new String[] {JobsFacade.JOBSTATUS_ERROR, ex.getMessage()};
		}

	}
	
	public String[] downloadsequence2(Collection setVarieties, Collection setLoci, String jobid, String reference, boolean sync) {

		try {

			VariantSequenceQuery query = new VariantSequenceQuery(setVarieties, setLoci, jobid, reference);
			query.setSubmitter(AppContext.getSubmitter());
			query.setSync(sync);
			genomicsfacade = (GenomicsFacade) AppContext.checkBean(genomicsfacade, "GenomicsFacade");
			String dir = genomicsfacade.createVariantsFasta(query);

			/*
			 * List listFiles=new ArrayList(); File folder = new File(dir); File[]
			 * listOfFiles = folder.listFiles();
			 * 
			 * for (int i = 0; i < listOfFiles.length; i++) { if (listOfFiles[i].isFile()) {
			 * //System.out.println("File " + listOfFiles[i].getName());
			 * if(listOfFiles[i].getName().endsWith(".fsa") ||
			 * listOfFiles[i].getName().endsWith(".txt"))
			 * listFiles.add(listOfFiles[i].getAbsolutePath()); } else if
			 * (listOfFiles[i].isDirectory()) { AppContext.debug("Directory " +
			 * listOfFiles[i].getName() + " not added in ZIP"); } } String
			 * paths[]=dir.split("/"); String zipfilename=AppContext.getTempDir() +
			 * paths[paths.length-1] + ".zip"; CreateZipMultipleFiles zip = new
			 * CreateZipMultipleFiles( zipfilename, listFiles); zip.create(false);
			 */

			if (jobid == null) {
				String paths[]= dir.split(Pattern.quote(File.separator));
				/*
				if(AppContext.isWindows()) {
					paths=dir.split("\\\\");		
					paths = path.split(Pattern.quote(File.separator));
				} else {
					paths = dir.split("/");
				}
				*/
				//String fname=new File(dir).getName();
				String zipfilename = AppContext.getTempDir() + paths[paths.length - 1] + ".zip";
				//String zipfilename = AppContext.getTempDir() + paths[paths.length - 1] + paths[paths.length - 1].replace("\\","").replace("/","")+ ".zip";
				
				//String zipfilename = dir + paths[paths.length - 1] + ".zip";
				
				
				// Filedownload.save(zipfilename, "application/zip");
				Filedownload.save(new File(zipfilename), "application/zip");
				// AppContext.debug("File download complete! Saved to: "+filename);
				org.zkoss.zk.ui.Session zksession = Sessions.getCurrent();
				AppContext.debug("variantsequence download complete!" + zipfilename + " "
						+ "Downloaded to:"
						+ zksession.getRemoteHost() + "  " + zksession.getRemoteAddr());
				return new String[] {JobsFacade.JOBSTATUS_DONE, zipfilename};
			}
			

		} catch (Exception ex) {
			ex.printStackTrace();
			//Messagebox.show(ex.getMessage());
			return new String[] {JobsFacade.JOBSTATUS_ERROR, ex.getMessage()};
		}
		
		return new String[] {JobsFacade.JOBSTATUS_DONE, null};

	}

	@Listen("onSelect = #listboxQueryvar")
	public void onSelectlistboxQueryvar() {
		listboxVarlistSeq.setSelectedIndex(0);
	}

	@Listen("onSelect = #listboxVarlistSeq")
	public void onSelectlistboxVarlistSeq() {
		listboxQueryvar.setSelectedIndex(0);
		if (listboxVarlistSeq.getSelectedItem().getLabel().equals("create new list...")) {
			Executions.sendRedirect("_workspace.zul?from=variety&src=download");
		}
	}

	@Listen("onSelect = #comboboxLocus")
	public void onSelectcomboboxLocus() {

		if (!comboboxLocus.getValue().isEmpty()) {
			try {
				genotypefacade = (GenotypeFacade) AppContext.checkBean(genotypefacade, "GenotypeFacade");
				Gene gene = genotypefacade.getGeneFromName(comboboxLocus.getValue(),
						this.listboxReference.getSelectedItem().getLabel());

				// listboxQuerychr.setValue(gene.getContig().toLowerCase());
				comboQuerychr.setValue(gene.getContig().toLowerCase());

				intboxStart.setValue(gene.getFmin());
				intboxEnd.setValue(gene.getFmax());
				if (gene.getStrand() > 0)
					listboxQuerystrand.setSelectedIndex(1);
				else
					listboxQuerystrand.setSelectedIndex(2);

				listboxLocuslist.setSelectedIndex(0);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Listen("onSelect = #listboxLocuslist")
	public void onSelectlistboxLocuslist() {
		onClearLocuslist();
		if (listboxLocuslist.getSelectedItem().getLabel().equals("create new list...")) {
			Executions.sendRedirect("_workspace.zul?from=locus&src=download");
		} else {
			comboQuerychr.setValue("");
			intboxStart.setValue(null);
			intboxEnd.setValue(null);
			comboboxLocus.setValue("");
		}
	}

	@Listen("onSelect = #listboxQuerystrand")
	public void onSelectlistboxQuerystrand() {
		comboboxLocus.setValue("");
		listboxLocuslist.setSelectedIndex(0);

	}

	@Listen("onSelect = #comboQuerychr")
	public void onselectcombochr() {
		comboboxLocus.setValue("");
		listboxLocuslist.setSelectedIndex(0);
	}

	@Listen("onSelect = #comboLocus")
	public void onselectcombolocus() {
		listboxLocuslist.setSelectedIndex(0);
		comboQuerychr.setValue("");
		intboxStart.setValue(null);
		intboxEnd.setValue(null);
		listboxLocuslist.setSelectedIndex(0);

	}

	@Listen("onChange = #intboxStart")
	public void onchangeStart() {
		comboboxLocus.setValue("");
		listboxLocuslist.setSelectedIndex(0);

	}

	@Listen("onChange = #intboxEnd")
	public void onchangeEnd() {
		comboboxLocus.setValue("");
		listboxLocuslist.setSelectedIndex(0);
	}

	private void onClearLocuslist() {
		intboxStart.setValue(null);
		intboxEnd.setValue(null);
		// listboxQuerystrand.setSelectedIndex(0);
		comboQuerychr.setValue("");
		comboboxLocus.setValue("");
	}

	@Listen("onClick = #tabRawfiles")
	public void onclickRawfiles() {

		if (!renderedDownloadtable) {

			try {
				varietyfacade = (VarietyFacade) AppContext.checkBean(varietyfacade, "VarietyFacade");

				Set<Variety> allvars = new TreeSet(varietyfacade.getGermplasm(getDataset()));

				List listvars = new ArrayList();
				listvars.addAll(allvars);
				gridRawVarietyFiles.setModel(new SimpleListModel(listvars));
				gridRawVarietyFiles.setRowRenderer(new VarietyDownloadsRowRenderer(
						varietyfacade.getVarietyExternalURL(VarietyPropertiesService.ID_ERS),
						varietyfacade.getVarietyExternalURL(VarietyPropertiesService.ID_SRA)));

				AppContext.debug(listvars.size() + " varieties in file download list");

				renderedDownloadtable = true;
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}

	}

	@Listen("onClick =#buttonUnmarkAll")
	public void onclickUnmarkall() {
		VarietyDownloadsRowRenderer renderer = (VarietyDownloadsRowRenderer) gridRawVarietyFiles.getRowRenderer();
		Iterator<Checkbox> itChecks = renderer.getListchecks().iterator();
		while (itChecks.hasNext()) {
			Checkbox check = itChecks.next();
			check.setChecked(false);
		}
	}

	@Listen("onClick =#buttonDownloadMarked")
	public void onclickDownload() {

		String selSite = listboxSite.getSelectedItem().getLabel();
		if (selSite.equals("AWS HTTP")) {
			if (this.checkboxFastq.isChecked()) {

			}
			if (this.checkboxBAM.isChecked()) {

			}
			if (this.checkboxVCF.isChecked()) {

			}
		} else if (selSite.equals("ASTI HTTP")) {

		} else if (selSite.equals("ASTI FTP")) {

		}

	}

	@Listen("onSelect =#listboxVarlist")
	public void onselectvarlist() {
		varietyfacade = (VarietyFacade) AppContext.checkBean(varietyfacade, "VarietyFacade");

		int rowcount = 0;
		Iterator<Listitem> itItems = listboxVarlist.getItems().iterator();
		while (itItems.hasNext()) {
			Listitem item = itItems.next();
			if (item.isSelected()) {
				break;
			}
			rowcount++;
		}
		int pageNum = rowcount / 100;
		gridRawVarietyFiles.setActivePage(pageNum);

	}

	public void onselectvarlistOld() {

		workspacefacade = (WorkspaceFacade) AppContext.checkBean(workspacefacade, "WorkspaceFacade");

		String selitem = listboxVarlist.getSelectedItem().getLabel().trim();
		if (selitem.isEmpty())
			return;
		if (selitem.equals("create new list...")) {
			Executions.sendRedirect("_workspace.zul?from=variety&src=download");
			return;
		}

		Set setVarieties = workspacefacade.getVarieties(selitem);

		Set setvarnames = new HashSet();
		Iterator<Variety> itVar = setVarieties.iterator();
		while (itVar.hasNext()) {
			Variety var = itVar.next();
			setvarnames.add(var.getName());
		}

		VarietyDownloadsRowRenderer renderer = (VarietyDownloadsRowRenderer) gridRawVarietyFiles.getRowRenderer();
		Iterator<String> itNames = renderer.getListNames().iterator();

		List listChecks = renderer.getListchecks();
		int icount = 0;
		while (itNames.hasNext()) {
			String name = itNames.next();
			if (setvarnames.contains(name)) {
				Checkbox cb = (Checkbox) listChecks.get(icount);
				cb.setChecked(true);
			}
			icount++;
		}

	}

}
