package org.irri.iric.portal.admin.zkui;

import java.io.File;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

import org.irri.iric.portal.AppContext;
import org.irri.iric.portal.admin.AsyncJob;
import org.irri.iric.portal.admin.JobsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;

@Controller
@Scope("session")
public class JobsController extends SelectorComposer<Component> {

	@Autowired
	@Qualifier("GalaxyJobsFacade")
	private JobsFacade jobsfacade_galaxy;

	@Autowired
	@Qualifier("JobsFacade")
	private JobsFacade jobsfacade_orig;
	
	private JobsFacade jobsfacade;

	@Wire
	private Label labelMessage;
	@Wire
	private Button buttonDownloadResult;
	@Wire
	private Button buttonDownloadMessage;

	@Wire
	private Vbox vboxAdmin;
	@Wire
	private Listbox listboxJobs;

	private String jobid = "";

	@Wire
	private Button buttonSaveJobs;
	@Wire
	private Button buttonDeleteDownloaded;
	@Wire
	private Button buttonDownloadLog;
	@Wire
	private Checkbox checkboxDeleteFiles;
	@Wire
	private Button buttonCancelJob;
	@Wire
	private Button buttonSystemStatus;
	@Wire
	private Button buttonUpdateJobstatus;
	@Wire
	private Textbox msgbox;
	@Wire
	private Checkbox checkboxShowRunning;
	@Wire
	private Checkbox checkboxShowDone;
	@Wire
	private Checkbox checkboxShowDownloaded;

	@Wire
	private Button buttonSessionAtts;
	@Wire
	private Textbox textboxPhylosessions;
	@Wire
	private Iframe iframejobresults;
	@Wire
	private Iframe iframeHtmlresult;
	@Wire
	private Vbox vboxIframeHtml;	
	

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		
		super.doAfterCompose(comp);
		

		jobsfacade_orig = (JobsFacade) AppContext.checkBean(jobsfacade_orig, "JobsFacade");
		jobsfacade_galaxy = (JobsFacade) AppContext.checkBean(jobsfacade_galaxy, "GalaxyJobsFacade");
		jobsfacade=jobsfacade_orig;
		Map<String, String[]> mapParams = Executions.getCurrent().getParameterMap();
		String[] jobs = mapParams.get("jobid");
		if (jobs == null) {
			labelMessage.setValue("No provided job id");
		} else {
			jobid = jobs[0];
			
			if(jobid.startsWith("galaxy")) {
				jobsfacade=jobsfacade_galaxy;	
			}
			
			onclickUpdateJobstatus();
			/*
			 * String msg = jobsfacade.getJobStatus(jobs[0]); try { Double percent =
			 * Double.valueOf(msg); msg= percent.intValue() + "%"; } catch (Exception ex)
			 * {}; labelMessage.setValue("Status for job " + jobs[0] + ": " + msg);
			 * if(msg.equals(JobsFacade.JOBSTATUS_DONE)) {
			 * buttonDownloadResult.setVisible(true); jobid=jobs[0]; }
			 * if(msg.equals(JobsFacade.JOBSTATUS_ERROR)) {
			 * buttonDownloadMessage.setVisible(true); jobid=jobs[0]; }
			 */
		}

		String user[] = mapParams.get("user");
		if (user != null && user[0].equals("bossloc")) {

			textboxPhylosessions.setValue(Integer.toString(jobsfacade.getMapSession2AttsSize()));
			List<AsyncJob> alljobs = jobsfacade.getAllJobs(true, false, false);
			listboxJobs.setItemRenderer(new AsyncJobListitemRenderer());
			listboxJobs.setModel(new SimpleListModel(alljobs));
			vboxAdmin.setVisible(true);

			String log[] = mapParams.get("log");
			if (log != null && log[0].equals("s3")) {
				jobsfacade.setLogDest(false, true);
				msgbox.setValue("log forced to S3");
			} else if (log != null && log[0].equals("local")) {
				jobsfacade.setLogDest(true, false);
				msgbox.setValue("log forced to local");
			} else
				jobsfacade.setLogDest(false, false);

			String pools[] = mapParams.get("corepoolsize");
			if (pools != null) {
				try {
					jobsfacade.setCorePoolSize(Integer.valueOf(pools[0]));
					msgbox.setValue("corepoolsize set to " + pools[0]);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			pools = mapParams.get("maxpoolsize");
			if (pools != null) {
				try {
					jobsfacade.setMaxPoolSize(Integer.valueOf(pools[0]));
					msgbox.setValue("maxpoolsize set to " + pools[0]);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			pools = mapParams.get("queuecapacity");
			if (pools != null) {
				try {
					jobsfacade.setQueueCapacity(Integer.valueOf(pools[0]));
					msgbox.setValue("queuecapacity set to " + pools[0]);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			String context[] = mapParams.get("hostname");
			if (context != null) {
				String old;
				if (context[0].toLowerCase().equals("null"))
					old = AppContext.setHostname(null);
				else
					old = AppContext.setHostname(context[0]);
				AppContext.logQuery("hostname changed from " + old + " to " + context[0]);
				msgbox.setValue("hostname changed from " + old + " to " + context[0]);
			}

			context = mapParams.get("hostdir");
			if (context != null) {
				String old;
				if (context[0].toLowerCase().equals("null"))
					old = AppContext.setHostDirectory(null);
				else
					old = AppContext.setHostDirectory(context[0]);
				AppContext.logQuery("hostdir changed from " + old + " to " + context[0]);
				msgbox.setValue("hostdir changed from " + old + " to " + context[0]);
			}
			String bucket[] = mapParams.get("hostdir");
			if (bucket != null) {
				String old;
				if (bucket[0].toLowerCase().equals("null"))
					old = AppContext.setHostDirectory(null);
				else
					old = AppContext.setHostDirectory(bucket[0]);
				AppContext.logQuery("bucket changed from " + old + " to " + bucket[0]);
				msgbox.setValue("bucket changed from " + old + " to " + bucket[0]);
			}

			String loglevel[] = mapParams.get("loglevel");
			if (loglevel != null) {
				jobsfacade.setLogLevel(loglevel[0]);
				msgbox.setValue("loglevel set to " + loglevel[0]);
			}

		}

	}

	/*
	 * @Bean(name="threadPoolTaskExecutor") public Executor threadPoolTaskExecutor()
	 * {
	 * 
	 * ThreadPoolTaskExecutor ex= new ThreadPoolTaskExecutor();
	 * 
	 * if(AppContext.isAWSBeanstalk()) { ex.setCorePoolSize(2);
	 * ex.setMaxPoolSize(4); } else if(AppContext.isAWS()) { ex.setCorePoolSize(2);
	 * ex.setMaxPoolSize(2); } else if(AppContext.isPollux()) {
	 * ex.setCorePoolSize(3); ex.setMaxPoolSize(5); } ex.setQueueCapacity(5); return
	 * ex;
	 */

	@Listen("onClick =#buttonSessionAtts")
	public void onclickClearSessionatts() {
		AppContext.getMapSession2Atts().clear();
		jobsfacade = (JobsFacade) AppContext.checkBean(jobsfacade, "JobsFacade");
		textboxPhylosessions.setValue(Integer.toString(jobsfacade.getMapSession2AttsSize()));
	}

	@Listen("onClick =#buttonUpdateJobstatus")
	public void onclickUpdateJobstatus() {
		String msg = JobsFacade.JOBSTATUS_MISSING;
		buttonUpdateJobstatus.setVisible(false);
		try {
			msg = jobsfacade.getJobStatus(jobid);
			try {
				Double percent = Double.valueOf(msg);
				msg = percent.intValue() + "%";
				buttonUpdateJobstatus.setVisible(true);
			} catch (Exception ex) {
				if(msg.equals(JobsFacade.JOBSTATUS_DONE) ||  msg.equals(JobsFacade.JOBSTATUS_ERROR)) {}
				else {
					buttonUpdateJobstatus.setVisible(true);
				}
			};
			labelMessage.setValue("Status for job " + jobid + ": " + msg);
			if (msg.equals(JobsFacade.JOBSTATUS_DONE)) {
				/*
				 * if(jobsfacade.useS3()) buttonDownloadResult.setHref(
				 * "https://s3-ap-southeast-1.amazonaws.com/snp-seek-jobs/" + jobid + ".zip");
				 * else buttonDownloadResult.setHref(null);
				 */
				buttonDownloadResult.setVisible(true);

				if (new File(AppContext.getTempDir() + jobid + ".ped.jpeg").exists()) {
					iframejobresults.setSrc("/temp/" + jobid + ".ped.html");
					iframejobresults.setVisible(true);
				} else {
					iframejobresults.setVisible(false);
				}
				
				/*
				if (new File(AppContext.getTempDir() + jobid + ".html").exists()) {
					//iframeHtmlresult.setSrc("/temp/" + jobid + ".html");
					
					String urlhrml=AppContext.readFile(AppContext.getTempDir() + jobid + ".html");
					urlhrml=urlhrml.substring(urlhrml.indexOf("http:"), (urlhrml.indexOf(".html")+5 ));
					AppContext.debug("displaying frame " + urlhrml );
					iframeHtmlresult.setSrc(urlhrml);
					iframeHtmlresult.setVisible(true);
				} else {
					iframeHtmlresult.setVisible(false);
					AppContext.debug("no html output " + AppContext.getTempDir() + jobid + ".html");
				}
				*/

				if (new File(AppContext.getTempDir() + jobid + "-1.html").exists()) {
					List l=vboxIframeHtml.getChildren();
					for(Object obj:l) {
						vboxIframeHtml.removeChild((Component)obj);
					}
					for(int ihtml=1; ihtml<11; ihtml++ ) {
						
						if(!new File(AppContext.getTempDir() + jobid + "-" + ihtml + ".html").exists()) {
							AppContext.debug(AppContext.getTempDir() + jobid + "-" + ihtml + ".html + not found");
							break;
						}
						Hbox hbox20=new Hbox();
						hbox20.setHeight("20px");
						hbox20.setParent(vboxIframeHtml );
						Iframe iframe=new Iframe();
						iframe.setWidth("100%");
						iframe.setHeight("800px");

						//String urlhrml=AppContext.readURL(status[ihtml]);
						String urlhrml=AppContext.readFile(AppContext.getTempDir() + jobid + "-" + ihtml + ".html");
						urlhrml=urlhrml.substring(urlhrml.indexOf("http:"), (urlhrml.indexOf(".html")+5 ));
						AppContext.debug("displaying frame " + urlhrml );
					
						//iframe.setStyle("border: none;overflow:hidden;");
						//iframe.setScrolling("no");
						//Here is the trick. The script sets the frame's height as it's content's scrollHeight plus 8 maybe 10 pixels.
						//iframe.setWidgetListener("onBind", "var f = document.getElementById('"+iframe.getUuid()+"');f.height=f.contentWindow.document.body.scrollHeight+8;");
						
						iframe.setSrc(urlhrml);
						iframe.setVisible(true);
						iframe.setParent(vboxIframeHtml);
					}
					vboxIframeHtml.setVisible(true);
				}
				else {
					vboxIframeHtml.setVisible(false);
					AppContext.debug(AppContext.getTempDir() + jobid + "-1.html + not found");
				}


			}
			if (msg.equals(JobsFacade.JOBSTATUS_ERROR)) {
				/*
				 * if(jobsfacade.useS3()) buttonDownloadMessage.setHref(
				 * "https://s3-ap-southeast-1.amazonaws.com/snp-seek-jobs/" + jobid + ".error");
				 * else buttonDownloadMessage.setHref(null);
				 */

				buttonDownloadMessage.setVisible(true);
			}
		} catch (Exception ex2) {
			ex2.printStackTrace();
		}

		if (msg.equals(JobsFacade.JOBSTATUS_STARTING) || msg.equals(JobsFacade.JOBSTATUS_SUBMITTED)) {
			buttonUpdateJobstatus.setVisible(true);
		}
	}

	@Listen("onClick =#checkboxShowRunning")
	public void onclickcheckboxShowRunning() {
		updateJoblist();
	}

	@Listen("onClick =#checkboxShowDone")
	public void onclickcheckboxShowDone() {
		updateJoblist();
	}

	@Listen("onClick =#checkboxShowDownloaded")
	public void onclickcheckboxShowDownlaoded() {
		updateJoblist();
	}

	private void updateJoblist() {
		List listjobs = jobsfacade.getAllJobs(checkboxShowRunning.isChecked(), checkboxShowDone.isChecked(),
				checkboxShowDownloaded.isChecked());
		this.listboxJobs.setModel(new SimpleListModel(listjobs));
	}

	@Listen("onSelect =#listboxJobs")
	public void onselectJob() {
		AsyncJob job = listboxJobs.getSelectedItem().getValue();
		if (job.getStatus() == null)
			buttonCancelJob.setDisabled(true);
		else if (job.getStatus().equals(JobsFacade.JOBSTATUS_DONE)
				|| job.getStatus().equals(JobsFacade.JOBSTATUS_DOWNLOADED))
			buttonCancelJob.setDisabled(true);
		else
			buttonCancelJob.setDisabled(false);
	}

	@Listen("onClick =#buttonCancelJob")
	public void onclickCancelJob() {
		AsyncJob job = listboxJobs.getSelectedItem().getValue();
		jobsfacade = (JobsFacade) AppContext.checkBean(jobsfacade, "JobsFacade");
		if (jobsfacade.cancelJob(job.getIpaddress())) {
			updateJoblist();
		}
	}

	@Listen("onClick =#buttonSystemStatus")
	public void onclickSystemStatus() {
		jobsfacade = (JobsFacade) AppContext.checkBean(jobsfacade, "JobsFacade");
		msgbox.setValue("SYSTEM:" + AppContext.getSystemStatus() + "\n" + "ASYNC:" + jobsfacade.getAsyncStatus());
	}

	@Listen("onClick =#buttonDownloadResult")
	public void onclickDownloadresult() throws Exception {
		AppContext.debug("onclickDownloadresult jobid=" + jobid);
		//if(jobid.endsWith(".zip")) jobid=jobid.replace(".zip","");
		try {
			
			//if (jobsfacade.useS3()) {
			if (false) {
				//String filetype = "application/zip";
				//byte f[] = jobsfacade.getS3Reader(jobid + ".zip");
				//Filedownload.save(f, filetype, jobid + ".zip");
			} else {
				String filetype = "application/zip";
				//Filedownload.save(new File(AppContext.getTempDir() + jobid+ "/" +jobid + ".zip"), filetype);
				if(jobid.endsWith("zip")) {
					//Filedownload.save(new File(AppContext.getTempDir() + jobid ), filetype);
					if( new File(AppContext.getTempDir()+jobid).exists()) {	
						Filedownload.save(new File(AppContext.getTempDir()+jobid), "application/zip");
					} else {
						
						boolean donwloaded=false;
						for(String ext:AppContext.getFilextMime().keySet()) {
							if( new File(AppContext.getTempDir()+jobid.replace(".zip", ext)).exists()) {	
								Filedownload.save(new File(AppContext.getTempDir()+jobid.replace(".zip", ext)), AppContext.getFilextMime().get(ext));
								donwloaded=true;
								break;
							}
						}
						if(!donwloaded) {
							Messagebox.show("Unable to identify file type for job " + jobid, "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
							if( new File(AppContext.getTempDir()+jobid.replace(".zip", "")).exists()) {	
								Filedownload.save(new File(AppContext.getTempDir()+jobid.replace(".zip", "")),"application/octet-stream"); 
							}
						}

					}
				}
				else {
					
					boolean donwloaded=false;
					for(String ext:AppContext.getFilextMime().keySet()) {
						if( new File(AppContext.getTempDir()+jobid+ext).exists()) {	
							Filedownload.save(new File(AppContext.getTempDir()+jobid+ext), AppContext.getFilextMime().get(ext));
							donwloaded=true;
							break;
						}
					}
					if(!donwloaded) {
						Messagebox.show("Unable to identify file type for job " + jobid, "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
						if( new File(AppContext.getTempDir()+jobid).exists()) {	
							Filedownload.save(new File(AppContext.getTempDir()+jobid),"application/octet-stream"); 
						}
					}

					
					//Filedownload.save(new File(AppContext.getTempDir() + jobid + ".zip"), filetype);
				}
				
				
			}
			// AppContext.debug("File download complete! Saved to: "+filename);
			org.zkoss.zk.ui.Session zksession = Sessions.getCurrent();
			AppContext.debug("snpallvars download complete!" + jobid + " Downloaded to:" + zksession.getRemoteHost() + "  "
					+ zksession.getRemoteAddr());
	
			jobsfacade = (JobsFacade) AppContext.checkBean(jobsfacade, "JobsFacade");
			jobsfacade.downloadJob(jobid);
		} catch(Exception ex) {
			ex.printStackTrace();
		}

	}

	@Listen("onClick =#buttonDownloadMessage")
	public void onclickDownloadMessage() throws Exception {
		try {
			AppContext.debug("buttonDownloadMessage jobid=" + jobid);
			if (jobsfacade.useS3()) {
				String filetype = "text/plain";
				try {
					byte f[] = jobsfacade.getS3Reader(jobid + ".zip");
					Filedownload.save(f, filetype, jobid + ".error");
				} catch (Exception ex) {
					byte f[] = jobsfacade.getS3Reader(jobid + ".error");
					Filedownload.save(f, filetype, jobid + ".error");
				}
			} else {
				String filetype = "text/plain";
				try {
					Filedownload.save(new File(AppContext.getTempDir() + jobid + ".error"), filetype);
				// AppContext.debug("File download complete! Saved to: "+filename);
				} catch(Exception ex) {
					Filedownload.save(new File(AppContext.getTempDir() + jobid + "/" + jobid + ".error"), filetype);
				}
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Listen("onClick =#buttonSaveJobs")
	public void onclickbuttonSaveJobs() {
		jobsfacade = (JobsFacade) AppContext.checkBean(jobsfacade, "JobsFacade");
		jobsfacade.saveJobs();
	}

	@Listen("onClick =#buttonDeleteDownloaded")
	public void onclickbuttonDeleteDownloaded() {
		jobsfacade = (JobsFacade) AppContext.checkBean(jobsfacade, "JobsFacade");
		jobsfacade.clearDownloadedJobs(checkboxDeleteFiles.isChecked());
	}

	@Listen("onClick =#buttonDownloadLog")
	public void onclickbuttonDownloadLog() {
		try {
			jobsfacade = (JobsFacade) AppContext.checkBean(jobsfacade, "JobsFacade");
			jobsfacade.saveJobs();
			Filedownload.save(new File(AppContext.getTempDir() + "jobs.log"), "text/plain");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
