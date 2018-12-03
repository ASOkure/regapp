package uk.ac.nesc.idsd.action.datetimepicker;

import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

public class IncludeTag extends ActionSupport {

	 private Date myBirthday;
	  public String execute() throws Exception{
	  setMyBirthday(new Date("Jan 12, 1984 11:21:30 AM"));
	  return SUCCESS;
	  }
	  public void setMyBirthday(Date date){
	  this.myBirthday = date;
	  }
	  public Date getMyBirthday(){
	  return myBirthday;
	  }
	}
