package com.developeinjava.jaxws.solutionservice.web;

import java.util.Date;

import javax.jws.*;

import com.developeinjava.jaxws.solutionservice.exceptions.ServiceException;
import com.developeinjava.jaxws.solutionservice.models.Attachments;
import com.developeinjava.jaxws.solutionservice.models.Solution;
import com.developeinjava.jaxws.solutionservice.models.Solutions;

@WebService(name="SolutionService", targetNamespace="http://jaxws.developeinjava.com")
public interface SolutionService {
	
	/**
	 * 
	 * @param aid author id
	 * @param pid problem id
	 * @param attahchments list contains details of the files attached to this solution
	 * @param description description of the solution
	 * 
	 */
	@WebMethod(operationName="createSolution")
	@WebResult(name="sid")
	long createSolution(@WebParam(name="aid") long aid,@WebParam(name="pid") long pid,
			@WebParam(name="attachments") Attachments attachments, 
			@WebParam(name="description") String description) throws ServiceException;
	/**
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param occupation
	 * @param mobile
	 * @param birthday
	 * @return
	 */
	@WebMethod(operationName="createAuthor")
	@WebResult(name="aid")
	long createAuthor(@WebParam(name="firstname") String firstname, @WebParam(name="lastname") String lastname, 
			@WebParam(name="email") String email, @WebParam(name="occupation") String occupation, 
			@WebParam(name="mobile") String mobile, @WebParam(name="birthday") Date birthday) throws ServiceException;
	/**
	 * 
	 * @param sid solution id
	 * @return
	 */
	@WebMethod(operationName="acceptSolution")
	@WebResult(name="accepted")
	boolean acceptSolution(@WebParam(name="sid") long sid) throws ServiceException;
	/**
	 * @param sid solution id
	 */
	@WebMethod(operationName="declineSolution")
	@WebResult(name="declined")
	boolean declineSolution(@WebParam(name="sid") long sid) throws ServiceException;
	
	/**
	 * 
	 * @param sid
	 * @param aid
	 * @param attachments
	 * @param description
	 * @return
	 */
	@WebMethod(operationName="updateSolution")
	@WebResult(name="updated")
	boolean updateSolution(@WebParam(name="sid") long sid, @WebParam(name="aid") long aid, 
			@WebParam(name="attachments")Attachments attachments, @WebParam(name="description") String description) throws ServiceException;
	
	/**
	 * 
	 * @param sid
	 * @param aid
	 * @return
	 */
	@WebMethod(operationName="deleteSolution")
	@WebResult(name="deleted")
	boolean deleteSolution(@WebParam(name="sid") long sid, @WebParam(name="aid") long aid) throws ServiceException;

	/**
	 * 
	 * @param sid
	 * @return
	 */
	@WebMethod(operationName="getSolutionById")
	@WebResult(name="solution")
	Solution getSolutionById(@WebParam(name="sid") long sid) throws ServiceException;
	
	/**
	 * 
	 * @param pid
	 * @return
	 */
	@WebMethod(operationName="findSolutionsForProblem")
	@WebResult(name="solutions")
	Solutions findSolutionsForProblem(@WebParam(name="pid") long pid) throws ServiceException;
	
	/**
	 * 
	 * @param pid
	 * @return
	 */
	@WebMethod(operationName="getPendingSolutionsForProblem")
	@WebResult(name="solutions")
	Solutions getPendingSolutionsForProblem(@WebParam(name="pid") long pid) throws ServiceException;
	
	/**
	 * 
	 * @param pid
	 * @return
	 */
	@WebMethod(operationName="getDeclinedSolutionsForProblem")
	@WebResult(name="solutions")
	Solutions getDeclinedSolutionsForProblem(@WebParam(name="pid") long pid) throws ServiceException;
	
	/**
	 * 
	 * @param pid
	 * @return
	 */
	@WebMethod(operationName="getAcceptedSolutionsForProblem")
	@WebResult(name="solutions")
	Solutions getAcceptedSolutionsForProblem(@WebParam(name="pid") long pid) throws ServiceException;
	/**
	 * 
	 * @param aid
	 * @return
	 */
	@WebMethod(operationName="getTotalPendingSolutionsForProblem")
	@WebResult(name="total")
	int getTotalPendingSolutionsForProblem(@WebParam(name="pid") long pid) throws ServiceException;
	 
	/**
	 * 
	 * @param aid
	 * @return
	 */
	@WebMethod(operationName="getTotalAcceptedSolutionsForProblem")
	@WebResult(name="total")
	int getTotalAcceptedSolutionsForProblem(@WebParam(name="pid") long pid) throws ServiceException;
	
	/**
	 * 
	 * @param aid
	 * @return
	 */
	@WebMethod(operationName="getTotalDeclinedSolutionsForProblem")
	@WebResult(name="total")
	int getTotalDeclinedSolutionsForProblem(@WebParam(name="pid") long pid) throws ServiceException;
}
