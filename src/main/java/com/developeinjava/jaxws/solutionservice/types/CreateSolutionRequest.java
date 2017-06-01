package com.developeinjava.jaxws.solutionservice.types;
import java.util.List;

import javax.xml.bind.annotation.*; 

import com.developeinjava.jaxws.solutionservice.models.Attachment;
import com.developeinjava.jaxws.solutionservice.models.Attachments;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreateSolutionRequest", propOrder = {
    "aid","pid","attachments","description"
})
public class CreateSolutionRequest {
	private long aid;
	private long pid;
	private Attachments attachments;
	private String description;
	public long getAid() {
		return aid;
	}
	public void setAid(long aid) {
		this.aid = aid;
	}
	public long getPid() {
		return pid;
	}
	public void setPid(long pid) {
		this.pid = pid;
	}
	public Attachments getAttachments() {
		return attachments;
	}
	public void setAttachments(Attachments attachments) {
		this.attachments = attachments;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
