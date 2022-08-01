package com.sepsoh.menschbos.playwithbots;

import javafx.scene.image.ImageView;

public class Character {

    private ImageView img;

    private int columnIndex;
    private int rowIndex;

    public Character(ImageView img, int columnIndex, int rowIndex) {
        this.img = img;
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
    }

    public ImageView getImg() {
        return img;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }
}
