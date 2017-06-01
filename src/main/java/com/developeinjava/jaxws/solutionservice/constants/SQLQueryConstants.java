package com.developeinjava.jaxws.solutionservice.constants;

public class SQLQueryConstants {
	
	public static final String FIND_USER_BY_USERNAME_AND_PASSWORD_QUERY = "select username,password,non_tech, first_name, last_name from users where username=?";
	public static final String FIND_USER_BY_FULLNAME = "select username,password,non_tech, first_name, last_name from users where first_name=? and last_name=?";
	public static final String INSERT_SOLUTION_RECORD = "INSERT INTO solution " +
			"(aid, pid, description, status, creation_date) VALUES (?,?,?,?,?)";
	public static final String OPEN_STATUS_PROBLEM_QUERY = "select status from problem where pid=? and status='open'";
	public static final String TOTAL_SOLUTION_FOR_PROBLEM_PER_AUTHOR = "select count(sid) as total from solution where aid=? and pid=?";
	public static final String INSERT_ATTACHMENT_RECORD = "INSERT INTO attachment " +
			"(filename, filepath, description,sid) VALUES (?,?,?,?)";
	public static final String UPDATE_SOLUTION_STATUS_QUERY = "update solution set status=?, modification_date=? where sid=?";
	public static final String GET_SOLUTION_WITH_SID_AND_AID = "select sid,aid from solution where sid=? and aid=?";
	public static final String UPDATE_SOLUTION_DETAILS = "update solution set modification_date=? ";
	public static final String GET_SOLUTION_STATUS = "select sid,aid from solution where sid=? and status=?";
	public static final String DELETE_SOLUTION_QUERY = "delete from solution where sid=?";
	public static final String DELETE_ATTACHMENT_QUERY = "delete from attachment where sid=?";
	public static final String GET_ADMIN_AUTHOR_BY_AID = "select aid from author where aid=? and profile=?";
	
}
