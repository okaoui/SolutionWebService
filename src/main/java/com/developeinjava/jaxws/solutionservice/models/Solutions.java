package com.developeinjava.jaxws.solutionservice.models;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Solutions {
	@XmlElement(name="solution")
	List<Solution> solutions;
}
