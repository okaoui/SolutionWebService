package com.developeinjava.jaxws.solutionservice.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelperClass {

	public static String getCurrentTime(){
		SimpleDateFormat sdf = 
			     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		
		return sdf.format(now);
	}
	
}
