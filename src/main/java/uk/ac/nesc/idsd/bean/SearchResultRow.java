package uk.ac.nesc.idsd.bean;

import java.util.ArrayList;
import java.util.List;

public class SearchResultRow {
    private Long registerId;
    private boolean editable;

    @SuppressWarnings("rawtypes")
    private List<SearchResultCell> cells = new ArrayList<>();

    public SearchResultRow() {

    }

    @SuppressWarnings("rawtypes")
    public SearchResultRow(List cells) {
        this.cells = cells;
    }

    @SuppressWarnings("rawtypes")
    public SearchResultRow(Long registerId, List cells) {
        this.registerId = registerId;
        this.cells = cells;
    }

    @SuppressWarnings("rawtypes")
    public SearchResultRow(Long registerId, boolean editable, List cells) {
        this.registerId = registerId;
        this.editable = editable;
        this.cells = cells;
    }

    public Long getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Long registerId) {
        this.registerId = registerId;
    }

    @SuppressWarnings("rawtypes")
    public List<SearchResultCell> getCells() {
        return cells;
    }

    @SuppressWarnings("rawtypes")
    public void setCells(List<SearchResultCell> cells) {
        this.cells = cells;
    }

    public int columnSize() {
        return this.cells.size();
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

}
