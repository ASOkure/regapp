package uk.ac.nesc.idsd.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class RecordStatisticBean {
    private long totalNumber;
    private List<String> centres = new ArrayList<String>();
    private Map<String, Map<Integer, Integer>> statisticMap = new TreeMap<String, Map<Integer, Integer>>();

    public RecordStatisticBean() {

    }

    public RecordStatisticBean(long totalNumber, List<String> centres, Map<String, Map<Integer, Integer>> statisticMap) {
        this.totalNumber = totalNumber;
        this.centres = centres;
        this.statisticMap = statisticMap;
    }

    public long getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(long totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<String> getCentres() {
        return centres;
    }

    public void setCentres(List<String> centres) {
        this.centres = centres;
    }

    public Map<String, Map<Integer, Integer>> getStatisticMap() {
        return statisticMap;
    }

    public void setStatisticMap(Map<String, Map<Integer, Integer>> statisticMap) {
        this.statisticMap = statisticMap;
    }

}
