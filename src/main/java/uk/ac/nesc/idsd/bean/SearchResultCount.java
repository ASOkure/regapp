package uk.ac.nesc.idsd.bean;

/**
 * Created by jiangj on 26/07/2016.
 */
public class SearchResultCount {

    private long actualSize = 0;
    private long consentSize = 0;
    private long columnSize = 0;

    public SearchResultCount(long actualSize, long consentSize, long columnSize) {
        this.actualSize = actualSize;
        this.consentSize = consentSize;
        this.columnSize = columnSize;
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

    @Override
    public String toString() {
        return "SearchResultCount{" +
                "actualSize=" + actualSize +
                ", consentSize=" + consentSize +
                ", columnSize=" + columnSize +
                '}';
    }
}
