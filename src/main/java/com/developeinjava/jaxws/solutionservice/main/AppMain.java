package com.developeinjava.jaxws.solutionservice.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.developeinjava.jaxws.solutionservice.constants.ApplicationConstants;
import com.developeinjava.jaxws.solutionservice.models.Attachment;
import com.developeinjava.jaxws.solutionservice.models.Attachments;
import com.developeinjava.jaxws.solutionservice.models.Solution;
import com.developeinjava.jaxws.solutionservice.services.SolutionManagerServiceImpl;


public class AppMain {

	public static void main(String args[]) {
		AbstractApplicationContext appContext =
		    	  new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
        SolutionManagerServiceImpl service = (SolutionManagerServiceImpl) appContext.getBean("solutionManagerService");
 
        /*
         * Create Employee1
         */
       Solution s = new Solution();
       Attachments atts = new Attachments();
       Attachment att = new Attachment();
       att.setDescription("hibernate properties");
       att.setFilename("hibernate.properties");
       att.setFilepath("/root/sol/id/files/");
       List<Attachment> list = new ArrayList<Attachment>();
       list.add(att);
       Attachment att2 = new Attachment();
       att2.setDescription("log4j properties");
       att2.setFilename("log4j.xml");
       att2.setFilepath("/root/sol/id/files/");
       list.add(att2); 
 
       atts.setList(list);
       s.setAttachments(atts);
       s.setSid(15);
       s.setAid(5);
       s.setDescription("Jack solution for problem 4");
       //s.setPid(4);
       //s.setStatus(ApplicationConstants.SOLUTION_STATUS_DEFAULT);
       
       s.setAttachments(atts);
        /*
         * Persist both Employees
         */
       // System.out.println(service.save(s));
       //System.out.println(service.declineSolution(13));
       System.out.println(service.update(s));
       //System.out.println(service.acceptSolution(9));
        
        appContext.close();
    }
}
