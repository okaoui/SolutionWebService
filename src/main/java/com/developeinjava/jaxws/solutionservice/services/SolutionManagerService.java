package com.developeinjava.jaxws.solutionservice.services;

import com.developeinjava.jaxws.solutionservice.models.Solution;

public interface SolutionManagerService {

	long save(Solution solution);
	boolean acceptSolution(long sid);
	boolean declineSolution(long sid);
	boolean update(Solution solution);
	boolean deleteSolution(long sid, long aid);
	
}
