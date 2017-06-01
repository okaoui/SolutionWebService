package com.developeinjava.jaxws.solutionservice.types;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreateAuthorRequest", propOrder = {
    "firstname","lastname","email","occupation","mobile","birthday"
})
public class CreateAuthorRequest {
	private String firstname;
	private String lastname;
	private String email;
	private String occupation;
	private String mobile;
	@XmlSchemaType(name = "date")
    protected XMLGregorianCalendar birthday;
	
	
}
