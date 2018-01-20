package com.awave.Polyomino;

public class Block {
    private int row;
    private int col;
    private String aChar;

    public Block(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return "Block{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}
