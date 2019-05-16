package org.irri.iric.galaxy.service;

import com.github.jmchilton.blend4j.galaxy.beans.Workflow;

public class MyWorksflow extends Workflow {
	
	String annotation;
	Workflow wf;
	public MyWorksflow(Workflow wf) {
		super();
		this.wf = wf;
	}
	public String getAnnotation() {
		return annotation;
	}
	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
	public String getName() {
		return wf.getName();
	}
	public void setName(String name) {
		wf.setName(name);
	}
	public void setId(String id) {
		wf.setId(id);
	}
	public void setUrl(String url) {
		wf.setUrl(url);
	}
	public String getUrl() {
		return wf.getUrl();
	}
	public String getId() {
		return wf.getId();
	}
	public int hashCode() {
		return wf.hashCode();
	}
	public boolean equals(Object obj) {
		return wf.equals(obj);
	}
	public String toString() {
		return wf.toString();
	}
	
	

}
