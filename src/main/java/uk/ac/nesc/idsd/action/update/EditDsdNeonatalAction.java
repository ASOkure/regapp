package uk.ac.nesc.idsd.action.update;

import com.opensymphony.xwork2.ActionSupport;

import uk.ac.nesc.idsd.model.DsdNeoLong;

public class EditDsdNeonatalAction  extends ActionSupport
{
   private static final long serialVersionUID = 1L;
   
   private DsdNeoLong DsdNeoLongBean;
	


public String execute() throws Exception{
	
	//call other classes here (Service Objects)  to perform business  processing of the form, such as storing the
	// users input into a data repository.
	
	return "SUCCESS";
}

public DsdNeoLong getDsdNeoLongBean() {
	
	
	
	return DsdNeoLongBean;
	
	
}


public void setDsdNeoLongBean( DsdNeoLong dsdNeoLong) {
	
	this.DsdNeoLongBean = dsdNeoLong;
}

}