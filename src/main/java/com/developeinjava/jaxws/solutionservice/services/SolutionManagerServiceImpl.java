package com.developeinjava.jaxws.solutionservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developeinjava.jaxws.solutionservice.constants.ApplicationConstants;
import com.developeinjava.jaxws.solutionservice.models.Solution;
import com.developeinjava.jaxws.solutionservice.repositories.AuthorRepository;
import com.developeinjava.jaxws.solutionservice.repositories.SolutionRepository;

@Service
public class SolutionManagerServiceImpl implements SolutionManagerService{
	private static Logger LOGGER = LoggerFactory.getLogger(SolutionManagerServiceImpl.class);
	
	@Autowired
	private SolutionRepository solutionRepository;
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private ProblemManagerService problemManagerService;
	
	public long save(Solution solution){
		/**
		 * Check problem has status 'open'.
		 * Check is there are solutions that has been created by this author, if any, 
		 * the author can only create another solution for the same problem if all his
		 * solutions have been 'declined'.
		 * Maximum solutions allowed per author to create are 3. 
		 */
		long sid = 0l;
		boolean open = problemManagerService.isOpen(solution.getPid());
		if(open){
			//check if any solutions created for this problem
			int total = getTotalSolutionCreatedByAuthorForProblem(solution.getAid(), solution.getPid());
			
			if(total == 0){
				// first solution by author
				sid = solutionRepository.save(solution);
			}else if(total == 3){
				// cannot create another solution
			}else if(total < 3){
				// all status of the stored solutions have to be 'declined' in order to create another solution
			}
		}
		return sid;
	}
	
	

	public SolutionRepository getSolutionRepository() {
		return solutionRepository;
	}

	public void setSolutionRepository(SolutionRepository solutionRepository) {
		this.solutionRepository = solutionRepository;
	}

	public ProblemManagerService getProblemManagerService() {
		return problemManagerService;
	}

	
	public AuthorRepository getAuthorRepository() {
		return authorRepository;
	}



	public void setAuthorRepository(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}



	public void setProblemManagerService(ProblemManagerService problemManagerService) {
		this.problemManagerService = problemManagerService;
	}
	
	public int getTotalSolutionCreatedByAuthorForProblem(long aid, long pid){
		return solutionRepository.totalSolutionByAuthorPerProblem(aid, pid);
	}

	public boolean acceptSolution(long sid) {
		return solutionRepository.changeSolutionStatus(sid, ApplicationConstants.SOLUTION_STATUS_ACCEPTED);
	}

	public boolean declineSolution(long sid) {
		
		return solutionRepository.changeSolutionStatus(sid, ApplicationConstants.SOLUTION_STATUS_DECLINED);
	}



	public boolean update(Solution solution) {
		/**
		 * Only the author who create the solution can update it 
		 */
		boolean flag = false;
		if(isAuthorEligibleToUpdateSolution(solution.getAid(), solution.getSid()) &&
				isSolutionStillPending(solution.getSid())){
			flag = solutionRepository.update(solution);
		}
		
		return flag;
	}
	
	private boolean isAuthorEligibleToUpdateSolution(long aid, long sid){
		return solutionRepository.validateAuthorIdWithSolutionId(aid, sid);
	}
	
	private boolean isSolutionStillPending(long sid){
		return solutionRepository.isStatus(sid, ApplicationConstants.SOLUTION_STATUS_DEFAULT);
	}

	
	private boolean isAdmin(long aid){
		return authorRepository.isAdmin(aid);
	}

	private boolean isDeclined(long sid){
		return solutionRepository.isStatus(sid, ApplicationConstants.SOLUTION_STATUS_DECLINED);
	}
	public boolean deleteSolution(long sid, long aid) {
		/**
		 * Only solutions that are declined can be deleted
		 * Admin is the only person who can delete solutions
		 */
		boolean flag = false;
		if(isAdmin(aid) && isDeclined(sid)){
			flag = solutionRepository.delete(sid);
		}
		
		return flag;
	}
	
	
	
	
	
}
