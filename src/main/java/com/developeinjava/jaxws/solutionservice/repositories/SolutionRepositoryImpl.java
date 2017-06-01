package com.developeinjava.jaxws.solutionservice.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.developeinjava.jaxws.solutionservice.constants.ApplicationConstants;
import com.developeinjava.jaxws.solutionservice.constants.SQLQueryConstants;
import com.developeinjava.jaxws.solutionservice.models.Attachment;
import com.developeinjava.jaxws.solutionservice.models.Solution;
import com.developeinjava.jaxws.solutionservice.utils.HelperClass;


public class SolutionRepositoryImpl implements SolutionRepository{
	private static Logger LOGGER = LoggerFactory.getLogger(SolutionRepositoryImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public long save(final Solution solution){
		long sid = 0l;
		try{
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(
			    new PreparedStatementCreator() {
			        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			            PreparedStatement ps =
			                connection.prepareStatement(SQLQueryConstants.INSERT_SOLUTION_RECORD, 
			                		new String[] {"sid"});
			            ps.setLong(1, solution.getAid());
			            ps.setLong(2, solution.getPid());
			            ps.setString(3, solution.getDescription());
			            ps.setString(4, ApplicationConstants.SOLUTION_STATUS_DEFAULT);
			            ps.setString(5, HelperClass.getCurrentTime());
			            return ps;
			        }
			    },
			    keyHolder);
			sid = keyHolder.getKey().longValue();
			//Save attachment
			if(sid != 0){
				for(Attachment att : solution.getAttachments().getList()){
					jdbcTemplate.update(SQLQueryConstants.INSERT_ATTACHMENT_RECORD, att.getFilename(),att.getFilepath(),att.getDescription(),sid);
				}
			}
			
		}catch(Exception e){
			LOGGER.error("Solution was not saved appropriately!",e);
			e.printStackTrace();
		}
		return sid;
	}
	
	public int totalSolutionByAuthorPerProblem(long aid, long pid){
		int total = 0;
		try{
			total = jdbcTemplate.queryForInt(SQLQueryConstants.TOTAL_SOLUTION_FOR_PROBLEM_PER_AUTHOR,
					aid,pid);
		}catch(Exception e){
			LOGGER.error("Failed!",e);
			e.printStackTrace();
		}
		
		return total;
	}
	
	
	public boolean changeSolutionStatus(long sid, String status){
		boolean flag = false;
		try{
			jdbcTemplate.update(SQLQueryConstants.UPDATE_SOLUTION_STATUS_QUERY, status,
					HelperClass.getCurrentTime(),sid);
			flag = true;
		}catch(Exception e){
			LOGGER.error("Failed!",e);
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean validateAuthorIdWithSolutionId(long aid, long sid) {
		Solution sol = null;
		try{
			sol = (Solution)getJdbcTemplate().queryForObject(
					SQLQueryConstants.GET_SOLUTION_WITH_SID_AND_AID, 
					new Object[] { sid,aid}, new SolutionRowMapper());
		}catch(Exception e){
			LOGGER.error("Failed to validate author with sid!", e);
			e.printStackTrace();
		}
		if(sol != null){
			return true;
		}else{
			return false;
		}
		
	}
	
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public boolean update(final Solution solution) {
		boolean flag = false;
		try{
			String basicQuery = SQLQueryConstants.UPDATE_SOLUTION_DETAILS;
			if(solution.getDescription() != null && solution.getDescription() != ""){
				basicQuery += ",description=? where sid=?";
			}else{
				basicQuery += "where sid=?";
			}
			
			if(basicQuery.contains(",description=?")){
				jdbcTemplate.update(basicQuery,HelperClass.getCurrentTime(),
						solution.getDescription(), solution.getSid());
			}else{
				jdbcTemplate.update(basicQuery,HelperClass.getCurrentTime(),
						solution.getSid());
			}	
			flag = true;
			//Save attachment
			
			if(solution.getAttachments() != null && solution.getAttachments().getList().size() != 0){
				for(Attachment att : solution.getAttachments().getList()){
					jdbcTemplate.update(SQLQueryConstants.INSERT_ATTACHMENT_RECORD, 
							att.getFilename(),att.getFilepath(),att.getDescription(),solution.getSid());
				}
			}
		
		}catch(Exception e){
			LOGGER.error("Solution was not updated properly!",e);
			e.printStackTrace();
		}
		return flag;
	}

	public boolean isStatus(long sid, String status) {
		Solution sol = null;
		try{
			sol = (Solution)getJdbcTemplate().queryForObject(
					SQLQueryConstants.GET_SOLUTION_STATUS, 
					new Object[] { sid,status}, new SolutionRowMapper());
		}catch(Exception e){
			LOGGER.error("Failed to get solution status!", e);
			e.printStackTrace();
		}
		if(sol != null){
			return true;
		}else{
			return false;
		}
	}

	public boolean delete(long sid) {
		int rows = 0;
		boolean flag = false;
		try{
			rows = getJdbcTemplate().update(SQLQueryConstants.DELETE_SOLUTION_QUERY, sid);
			
			if(rows != 0){
				rows = getJdbcTemplate().update(SQLQueryConstants.DELETE_ATTACHMENT_QUERY, sid);
			}
			
			
			flag = true;
			
		}catch(Exception e){
			LOGGER.error("Failed to get solution status!", e);
			e.printStackTrace();
		}
		
		return flag;
	}
	
	
}
