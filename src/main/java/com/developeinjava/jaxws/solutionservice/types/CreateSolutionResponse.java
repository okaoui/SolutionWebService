package com.developeinjava.jaxws.solutionservice.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreateSolutionResponse", propOrder = {
    "sid"
})
public class CreateSolutionResponse {
	
	@XmlElement(required = true)
    @XmlSchemaType(name = "unsignedLong")
	private long sid;

	public long getSid() {
		return sid;
	}

	public void setSid(long sid) {
		this.sid = sid;
	}
	
	
}
