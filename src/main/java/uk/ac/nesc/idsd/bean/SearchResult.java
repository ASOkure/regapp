package uk.ac.nesc.idsd.bean;

import java.util.ArrayList;
import java.util.List;

public class SearchResult {

    private long actualSize = 0;
    private long consentSize = 0;
    private long columnSize = 0;

    private List<SearchResultHeadCell> headRow = new ArrayList<SearchResultHeadCell>();

    private List<SearchResultRow> resultRows = new ArrayList<SearchResultRow>();

    public SearchResult() {

    }

    public SearchResult(List<SearchResultHeadCell> headRow, List<SearchResultRow> resultRows) {
        this.headRow = headRow;
        this.resultRows = resultRows;
        this.columnSize = headRow.size();
    }

    public SearchResult(long actualSize, long consentSize, List<SearchResultHeadCell> headRow, List<SearchResultRow> resultRows) {
        this.actualSize = actualSize;
        this.consentSize = consentSize;
        this.headRow = headRow;
        this.resultRows = resultRows;
        this.columnSize = headRow.size();
    }

    public List<SearchResultRow> getResultRows() {
        return resultRows;
    }

    public void setResultRows(List<SearchResultRow> resultRows) {
        this.resultRows = resultRows;
    }

    public long getActualSize() {
        return actualSize;
    }

    public void setActualSize(long actualSize) {
        this.actualSize = actualSize;
    }

    public long getConsentSize() {
        return consentSize;
    }

    public void setConsentSize(long consentSize) {
        this.consentSize = consentSize;
    }

    public long getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(long columnSize) {
        this.columnSize = columnSize;
    }

    public List<SearchResultHeadCell> getHeadRow() {
        return headRow;
    }

    public void setHeadRow(List<SearchResultHeadCell> headRow) {
        this.headRow = headRow;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "actualSize=" + actualSize +
                ", consentSize=" + consentSize +
                ", columnSize=" + columnSize +
                ", headRow=" + headRow +
                ", resultRows=" + resultRows +
                '}';
    }
}
