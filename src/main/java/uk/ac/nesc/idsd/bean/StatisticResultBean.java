package uk.ac.nesc.idsd.bean;

import java.util.ArrayList;
import java.util.List;

public class StatisticResultBean {
    private List<StatisticBean> resultList = new ArrayList<StatisticBean>();
    private long total;

    public StatisticResultBean(List<StatisticBean> resultList, long total) {
        this.resultList = resultList;
        this.total = total;
    }

    public List<StatisticBean> getResultList() {
        return resultList;
    }

    public void setResultList(List<StatisticBean> resultList) {
        this.resultList = resultList;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

}
