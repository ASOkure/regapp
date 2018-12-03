package uk.ac.nesc.idsd.action.create;

import uk.ac.nesc.idsd.action.AbstractDsdAction;
import uk.ac.nesc.idsd.service.exception.ServiceException;
import uk.ac.nesc.idsd.util.Utility;

public class AbstractCreateAction extends AbstractDsdAction {
    private static final long serialVersionUID = 261042071993129575L;

    protected String afterSave() {
        if ("save".equals(buttonName)) {
            return "save";
        } else if ("next".equals(buttonName)) {
            try {
                if (null == this.registerId) {
                    if (null != this.dsdIdentifier && null != this.dsdIdentifier.getRegisterId()) {
                        this.registerId = this.dsdIdentifier.getRegisterId();
                    }
                }
                Utility.setUploadSessionRegisterId(this.registerId);
            } catch (ServiceException e) {
                log.error("Error happened in afterSave method for createAction", e);
            }
        }
        return SUCCESS;
    }

}
