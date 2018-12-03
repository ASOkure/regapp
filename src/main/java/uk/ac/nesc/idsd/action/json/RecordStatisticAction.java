package uk.ac.nesc.idsd.action.json;

import uk.ac.nesc.idsd.action.AbstractAction;
import uk.ac.nesc.idsd.bean.StatisticResultBean;
import uk.ac.nesc.idsd.service.DsdIdentifierService;

public class RecordStatisticAction extends AbstractAction {
    private static final long serialVersionUID = 5204890984607475955L;
    private DsdIdentifierService dsdIdentifierService;

    private StatisticResultBean resultBean;
    private long totalNumber;

    public String getStatisticJSONData() {
//		log.debug("start getting statistic data");
//		long start = System.currentTimeMillis();
        this.resultBean = this.dsdIdentifierService.getRecordStatistic();
//		long end = System.currentTimeMillis();
//		log.debug("processing took " + (end - start));
        return SUCCESS;
    }

    public String getTotalNubmerJSONData() {

        this.totalNumber = this.dsdIdentifierService.getTotalRecordNumber();

        return SUCCESS;
    }

    /**
     * Setters
     *
     * @return
     */
    public StatisticResultBean getResultBean() {
        return resultBean;
    }

    public void setResultBean(StatisticResultBean resultBean) {
        this.resultBean = resultBean;
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
