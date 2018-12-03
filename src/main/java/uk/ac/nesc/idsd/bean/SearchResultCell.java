package uk.ac.nesc.idsd.bean;

public class SearchResultCell {
    private String columnId;
    private Object value;

    public SearchResultCell() {

    }

    public SearchResultCell(String columnId, Object value) {
        this.columnId = columnId;
        this.value = value;
    }

    public String getColumnId() {
        return columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
