package org.irri.iric.portal.admin;

public class Query {
	protected String jobid;
	protected String submitter;
	protected String filename;
	public String getJobid() {
		return jobid;
	}
	public void setJobid(String jobid) {
		this.jobid = jobid;
	}
	public String getSubmitter() {
		return submitter;
	}
	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}

	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	@Override
	public String toString() {
		
		return "submitter=" + submitter + ";jobid=" + jobid +";filename=" + filename;
	}
	
	
	
}
