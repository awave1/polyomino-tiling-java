package com.awave.Polyomino;


import com.awave.utils.Utils;

import java.util.ArrayList;

public class Grid {
    private int rows;
    private int cols;
    private String grid[][];

    private ArrayList<Shape> placedShapes = new ArrayList<>();

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        this.grid = new String[rows][cols];
    }

    public boolean tryPlaceAt(int row, int col, Shape shape) {
        boolean isPlaced = canPlace(shape, row, col);

        if (isPlaced && !placedShapes.contains(shape)) {
            // Try and place shape
            shape.forEachBlock(block -> grid[block.getRow() + row][block.getCol() + col] = shape.getName());
            placedShapes.add(shape);
        }

        return isPlaced;
    }

    public void removeShape(Shape shape) {
        iterateGrid((row, col) -> {
            if (grid[row][col] != null && grid[row][col].equals(shape.getName()))
                grid[row][col] = null;
        });
        placedShapes.remove(shape);
    }

    public void iterateGrid(LoopHandler handler) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                handler.handle(row, col);
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    private boolean canPlace(Shape shape, int row, int col) {

        boolean canPlace = true;

        for (int i = 0; i < shape.getBlocks().size() && canPlace; i++) {
            Block block = shape.getBlocks().get(i);
            int _row = block.getRow() + row;
            int _col = block.getCol() + col;

            if (_row >= rows || _col >= cols || !this.isOpen(_row, _col))
                canPlace = false;
        }

        return canPlace;
    }

    private boolean isOpen(int row, int col) {
        return grid[row][col] == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] != null)
                    sb.append(String.format("[%s]", grid[i][j]));
                else
                    sb.append("[ ]");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
