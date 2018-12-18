package uk.ac.nesc.idsd.action.update;

import org.springframework.security.access.prepost.PreAuthorize;

import uk.ac.nesc.idsd.model.DsdCah;
import uk.ac.nesc.idsd.model.DsdNeonatalVisits;
import uk.ac.nesc.idsd.service.exception.ServiceException;

public class EditDsdNeonatalAction extends AbstractEditAction
{
   private static final long serialVersionUID = 1L;
   
 private  DsdNeonatalVisits dsdNeonatalVisitsBean;
   
   
 
 
// @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
 public String editNeonatalView() {
    
	 // call service class to store dsdNeonatalVisitsBean's state in database
	 
     return SUCCESS;
 }
 
 public DsdNeonatalVisits getDsdNeonatalVisitsBean() {
	 
	  return  dsdNeonatalVisitsBean;
 }
 
 
 
 public void DsdNeoantalVisitsSetDsdNeonatalVisitsBean(DsdNeonatalVisits dsdneonatalvisits) {
	 
	 
	  dsdNeonatalVisitsBean = dsdneonatalvisits;
	 
 }
 
 
 
    /**
    * Save neonatal visit.
    */
   
   @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
   public String saveDsdNeonatalVisits() {
       String returnStatus = checkConsentAndOwner();
      log.info("birth weight = " + this.dsdIdentifier.getBirthWeight());
       if (null != this.dsdIdentifier) {
           try {
               this.dsdIdentifier = this.dsdIdentifierService.saveNeonatal(this.dsdIdentifier, dsdNeonatalVisitsBean);
           } catch (ServiceException e) {
               this.addActionError("Error when editing Neonatal record for registerId: " + this.registerId);
               log.error("Error when editing Neonatal record for registerId: " + this.registerId, e);
               return INPUT;
           }
       }
       return afterSave(returnStatus);
   }

   
   
   
}


