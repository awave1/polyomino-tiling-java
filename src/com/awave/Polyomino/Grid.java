package com.awave.Polyomino;


import java.util.ArrayList;

public class Grid {

    private int rows;
    private int cols;
    private Block grid[][];
    private ArrayList<Shape> shapes;

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        this.shapes = new ArrayList<>();
        this.grid = new Block[rows][cols];
    }

    public void addShape(Shape shape) {
        this.shapes.add(shape);
        this.addBlocksToGrid(shape);
    }

    public void addShapes(ArrayList<Shape> shapes) {
        this.shapes.addAll(shapes);
        this.addShapesToGrid(this.shapes);
    }

    private boolean addToNextOpen(Shape shape) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

            }
        }

        return false;
    }

    private void addBlocksToGrid(Shape shape) {
        shape.getBlocks().forEach(block -> {
            int row = block.getRow();
            int col = block.getCol();
            if (inBounds(row, col) && !hasBlock(row, col))
                this.grid[block.getRow()][block.getCol()] = block;
        });
    }

    private void addShapesToGrid(ArrayList<Shape> shapes) {
        shapes.forEach(this::addBlocksToGrid);
    }

    private boolean hasBlock(int row, int col) {
        return this.grid[row][col] != null;
    }

    private boolean inBounds(int row, int col) {
        return ( row >= 0 && row < rows ) && ( col >= 0 && col < cols );
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (hasBlock(row, col))
                    buffer.append("[*]");
                else
                    buffer.append("[ ]");
            }
            buffer.append("\n");
        }

        return buffer.toString();
    }
}
