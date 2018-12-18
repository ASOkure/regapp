package uk.ac.nesc.idsd.action.datetimepicker;

import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

public class IncludeTag extends ActionSupport {

	private Date date1;
	private Date date2;
	private Date date3;
	
	//return today date
	public Date getTodayDate(){
		
		return new Date();
	}

	//getter and setter methods
	public String execute() throws Exception{
		
		return SUCCESS;
	}

	public String display() {
		return NONE;
	}
	
}
