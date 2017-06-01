package com.developeinjava.jaxws.solutionservice.repositories;

import com.developeinjava.jaxws.solutionservice.models.Solution;

public interface SolutionRepository {

	long save(final Solution solution);
	int totalSolutionByAuthorPerProblem(long aid, long pid);
	boolean changeSolutionStatus(long sid, String status);
	boolean validateAuthorIdWithSolutionId(long aid, long sid);
	boolean update(Solution solution);
	boolean isStatus(long sid, String status);
	boolean delete(long sid);
	
	
}
