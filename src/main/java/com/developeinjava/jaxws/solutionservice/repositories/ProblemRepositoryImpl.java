package com.developeinjava.jaxws.solutionservice.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.developeinjava.jaxws.solutionservice.constants.SQLQueryConstants;

public class ProblemRepositoryImpl implements ProblemRepository{

	private static Logger LOGGER = LoggerFactory.getLogger(SolutionRepositoryImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public boolean isOpen(long pid){
		String result = null;
		try{
			result = jdbcTemplate.queryForObject(SQLQueryConstants.OPEN_STATUS_PROBLEM_QUERY, 
					String.class, pid);
		}catch(Exception e){
			e.printStackTrace();
			LOGGER.error("Checking problem status failed! ", e);
		}
		
		if(result == null || result.isEmpty()){
			return false;
		}else{
			return true;
		}
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
}
