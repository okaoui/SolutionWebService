package com.developeinjava.jaxws.solutionservice.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.developeinjava.jaxws.solutionservice.constants.ExceptionConstants;
import com.developeinjava.jaxws.solutionservice.exceptions.ServiceException;
import com.developeinjava.jaxws.solutionservice.models.Attachment;
import com.developeinjava.jaxws.solutionservice.models.Attachments;
import com.developeinjava.jaxws.solutionservice.models.Solution;
import com.developeinjava.jaxws.solutionservice.models.Solutions;
import com.developeinjava.jaxws.solutionservice.services.SolutionManagerServiceImpl;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

@WebService(endpointInterface="com.developeinjava.jaxws.solutionservice.web.SolutionService",
	name="SolutionServiceImpl", targetNamespace="http://jaxws.developeinjava.com")
public class SolutionServiceImpl implements SolutionService {

	@Autowired
	private SolutionManagerServiceImpl solutionManagerService;
	
	public long createSolution(long aid, long pid,
			Attachments list, String description) throws ServiceException{
		
		Solution solution = new Solution();
		solution.setAid(aid);
		solution.setPid(pid);
		solution.setDescription(description);
		solution.setAttachments(list);
		
		long sid = solutionManagerService.save(solution);
		
		if(sid == 0l){
			throw new ServiceException(ExceptionConstants.SOLUTION_NOT_SAVED_EXCEPTION_DESC);
		}
		return sid;
	}

	public long createAuthor(String firstname, String lastname, String email,
			String occupation, String mobile, Date birthday) throws ServiceException{
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean acceptSolution(long sid) throws ServiceException{
		return solutionManagerService.acceptSolution(sid);
	}

	public boolean declineSolution(long sid) throws ServiceException{
		
		return solutionManagerService.declineSolution(sid);
	}

	public boolean updateSolution(long sid, long aid,
			Attachments attachments, String description) throws ServiceException{
		boolean flag = false;
		
		if(sid==0l){
			throw new ServiceException("Solution id is incorrect");
		}
		
		if(aid == 0l){
			throw new ServiceException("Author id is incorrect");
		}
		
		if(attachments == null){
			attachments = new Attachments();
			List<Attachment> list = new ArrayList<Attachment>();
			attachments.setList(list);
		}
		
		if(description == null){
			description = "";
		}
		
		Solution sol = new Solution();
		sol.setDescription(description);
		sol.setAttachments(attachments);
		sol.setAid(aid);
		sol.setSid(sid);
		
		flag = solutionManagerService.update(sol);
		
		return flag;
	}

	public boolean deleteSolution(long sid, long aid) throws ServiceException{
		boolean flag = false;
		if(sid == 0l){
			throw new ServiceException("Solution id is incorrect!");
		}
		
		if(aid == 0l){
			throw new ServiceException("Author id is incorrect!");
		}
		
		flag = solutionManagerService.deleteSolution(sid, aid);
		
		if(flag == false){
			throw new ServiceException("Solution cannot be deleted due to one of the following reasons: "
											+ "1- Admin profile required."
											+ "2- Solution is pending or accepted (only declined solutions can be deleted)."
											+ "3- Solution not found.");
		}
		
		return flag;
	}

	public Solution getSolutionById(long sid) throws ServiceException{
		// TODO Auto-generated method stub
		return null;
	}

	public Solutions findSolutionsForProblem(long pid) throws ServiceException{
		// TODO Auto-generated method stub
		return null;
	}

	public Solutions getPendingSolutionsForProblem(long pid)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Solutions getDeclinedSolutionsForProblem(long pid)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Solutions getAcceptedSolutionsForProblem(long pid)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public int getTotalPendingSolutionsForProblem(long pid)
			throws ServiceException {
		// TODO Auto-generated method stub
		return 6;
	}

	public int getTotalAcceptedSolutionsForProblem(long pid)
			throws ServiceException {
		// TODO Auto-generated method stub
		return 3;
	}

	public int getTotalDeclinedSolutionsForProblem(long pid)
			throws ServiceException {
		// TODO Auto-generated method stub
		return 1;
	}

	public SolutionManagerServiceImpl getSolutionManagerService() {
		return solutionManagerService;
	}

	public void setSolutionManagerService(
			SolutionManagerServiceImpl solutionManagerService) {
		this.solutionManagerService = solutionManagerService;
	}

	
	
	
	

}
