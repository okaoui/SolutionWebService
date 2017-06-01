package com.developeinjava.jaxws.solutionservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developeinjava.jaxws.solutionservice.repositories.ProblemRepository;


@Service
public class ProblemManagerServiceImpl implements ProblemManagerService{

	private static Logger LOGGER = LoggerFactory.getLogger(ProblemManagerServiceImpl.class);
	
	@Autowired
	private ProblemRepository problemRepository;
	
	public boolean isOpen(long pid){
		
		return problemRepository.isOpen(pid);
	}

	public ProblemRepository getProblemRepository() {
		return problemRepository;
	}

	public void setProblemRepository(ProblemRepository problemRepository) {
		this.problemRepository = problemRepository;
	}
	
	

}
