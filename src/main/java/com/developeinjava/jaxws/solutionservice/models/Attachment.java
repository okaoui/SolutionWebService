package com.developeinjava.jaxws.solutionservice.models;


public class Attachment {
	
	private long attId;
	private String filename;
	private String filepath;
	private String description;
	private long sid;
	
	
	public long getAttId() {
		return attId;
	}
	public void setAttId(long attId) {
		this.attId = attId;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getSid() {
		return sid;
	}
	public void setSid(long sid) {
		this.sid = sid;
	}
	
	

}
