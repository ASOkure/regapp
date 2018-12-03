package uk.ac.nesc.idsd.action.json;

import uk.ac.nesc.idsd.action.AbstractAction;
import uk.ac.nesc.idsd.service.DsdIdentifierService;

public class TotalRecordNumberAction extends AbstractAction {
    private static final long serialVersionUID = 5204890984607475955L;
    private DsdIdentifierService dsdIdentifierService;

    private long totalNumber;

    public String getTotalNubmerJSONData() {
        this.totalNumber = this.dsdIdentifierService.getTotalRecordNumber();
        return SUCCESS;
    }

    public void setDsdIdentifierService(
            DsdIdentifierService dsdIdentifierService) {
        this.dsdIdentifierService = dsdIdentifierService;
    }

    public long getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(long totalNumber) {
        this.totalNumber = totalNumber;
    }

}
