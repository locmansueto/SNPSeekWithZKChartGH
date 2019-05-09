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
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;

@Controller
@Scope("session")
public class JobsController extends SelectorComposer<Component> {

	@Autowired
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

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		
		super.doAfterCompose(comp);

		jobsfacade = (JobsFacade) AppContext.checkBean(jobsfacade, "JobsFacade");

		Map<String, String[]> mapParams = Executions.getCurrent().getParameterMap();
		String[] jobs = mapParams.get("jobid");
		if (jobs == null) {
			labelMessage.setValue("No provided job id");
		} else {
			jobid = jobs[0];
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
			}
			;
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
		
		try {
			
			//if (jobsfacade.useS3()) {
			if (false) {
				String filetype = "application/zip";
				byte f[] = jobsfacade.getS3Reader(jobid + ".zip");
				Filedownload.save(f, filetype, jobid + ".zip");
			} else {
				String filetype = "application/zip";
				Filedownload.save(new File(AppContext.getTempDir() + jobid + "/" +jobid + ".zip"), filetype);
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
				Filedownload.save(new File(AppContext.getTempDir() + jobid + "/" + jobid + ".error"), filetype);
				// AppContext.debug("File download complete! Saved to: "+filename);
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
