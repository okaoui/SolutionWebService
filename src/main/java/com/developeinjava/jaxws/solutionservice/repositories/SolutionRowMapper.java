package com.developeinjava.jaxws.solutionservice.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.developeinjava.jaxws.solutionservice.models.Solution;

public class SolutionRowMapper implements RowMapper<Solution> {

	public Solution mapRow(ResultSet rs, int rowNum) throws SQLException {
		Solution sol = new Solution();
		sol.setSid(rs.getLong("sid"));
		sol.setAid(rs.getLong("aid"));
		
		return sol;
	}

}
