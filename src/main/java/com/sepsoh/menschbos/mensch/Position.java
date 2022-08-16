package com.sepsoh.menschbos.mensch;

public class Position {
    private int columnIndex;
    private int rowIndex;

    public int getColumn() {
        return columnIndex;
    }

    public void setColumn(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public void setRow(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getRow() {
        return rowIndex;
    }
    public Position(int columnIndex, int rowIndex) {
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
    }
    public Position(){}


    public boolean isEqual(Position obj) {
        if(this.columnIndex == obj.columnIndex && this.rowIndex == obj.rowIndex)
            return true;
        return false;
    }

}
