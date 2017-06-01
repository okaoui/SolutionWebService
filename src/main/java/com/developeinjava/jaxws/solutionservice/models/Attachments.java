package com.developeinjava.jaxws.solutionservice.models;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;

public class Attachments {
	
	private List<Attachment> list;
	
	@XmlElement(name="attachment")
	public List<Attachment> getList() {
		return list;
	}

	public void setList(List<Attachment> list) {
		this.list = list;
	}

	
	
	
	

}
