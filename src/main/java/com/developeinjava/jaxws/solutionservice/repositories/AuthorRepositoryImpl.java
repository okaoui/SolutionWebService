package com.developeinjava.jaxws.solutionservice.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.developeinjava.jaxws.solutionservice.constants.ApplicationConstants;
import com.developeinjava.jaxws.solutionservice.constants.SQLQueryConstants;
import com.developeinjava.jaxws.solutionservice.models.Author;
import com.developeinjava.jaxws.solutionservice.services.SolutionManagerServiceImpl;

public class AuthorRepositoryImpl implements AuthorRepository{

	private static Logger LOGGER = LoggerFactory.getLogger(AuthorRepositoryImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public boolean isAdmin(long aid) {
		
		Author author = null;
		try{
			author = (Author)getJdbcTemplate().queryForObject(
					SQLQueryConstants.GET_ADMIN_AUTHOR_BY_AID, 
					new Object[] { aid,ApplicationConstants.ADMIN_PROFILE}, new AuthorRowMapper());
		}catch(Exception e){
			LOGGER.error("Failed to get author profile!", e);
			//e.printStackTrace();
		}
		if(author != null){
			return true;
		}else{
			return false;
		}
	}
	
	public static void main(String[] args){
		AbstractApplicationContext appContext =
		    	  new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
      AuthorRepository rep = (AuthorRepositoryImpl) appContext.getBean("authorRepository");
      
      System.out.println(rep.isAdmin(1));
      
      appContext.close();
	}
}
