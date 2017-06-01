package com.developeinjava.jaxws.solutionservice.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.developeinjava.jaxws.solutionservice.models.Author;

public class AuthorRowMapper implements RowMapper<Author>{

	public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
		Author author = new Author();
		author.setAid(rs.getLong("aid"));
		
		return author;
	}

}
