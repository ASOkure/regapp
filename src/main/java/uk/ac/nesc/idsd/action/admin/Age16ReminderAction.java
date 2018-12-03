package uk.ac.nesc.idsd.action.admin;

import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.action.AbstractAction;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.service.DsdIdentifierService;
import uk.ac.nesc.idsd.util.Utility;
import uk.ac.nesc.idsd.util.comparator.PortalUserComparator;
import uk.ac.nesc.idsd.util.email.EmailService;

import java.util.Map;
import java.util.TreeMap;

public class Age16ReminderAction extends AbstractAction {
    private static final long serialVersionUID = 2901675192113974690L;

    @Autowired
    private DsdIdentifierService dsdIdentifierService;
    @Autowired
    private EmailService emailService;

    private Map<PortalUser, String> reminderMap = new TreeMap<PortalUser, String>(new PortalUserComparator());

    public String execute() {
        this.reminderMap = this.dsdIdentifierService.getAge16Reminder();
        log.info("Sending reminder email for patients who are turning 16 this year. ");
        for (PortalUser u : this.reminderMap.keySet()) {
            log.debug("User = " + u.getName() + " : registerIds = " + this.reminderMap.get(u));
            emailService.send(Utility.getEmailRecipient(u.getEmail()),
                    "Reminder: Your I-DSD patients are turning 16 this year",
                    Utility.constructAge16ReminderEmailMsg(u.getName(), this.reminderMap.get(u))
            );//u.getEmail());
        }
        return SUCCESS;
    }

    public void setDsdIdentifierService(DsdIdentifierService dsdIdentifierService) {
        this.dsdIdentifierService = dsdIdentifierService;
    }

    public Map<PortalUser, String> getReminderMap() {
        return reminderMap;
    }

    public void setReminderMap(Map<PortalUser, String> reminderMap) {
        this.reminderMap = reminderMap;
    }

}
