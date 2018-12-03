package uk.ac.nesc.idsd.bean;

public class SearchResultHeadCell {
    private String columnId;
    private String label;

    public SearchResultHeadCell() {

    }

    public SearchResultHeadCell(String columnId, String label) {
        this.columnId = columnId;
        this.label = label;
    }

    public String getColumnId() {
        return columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
