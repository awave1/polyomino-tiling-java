package com.awave.Polyomino;


import java.util.ArrayList;

public class Grid {
    public int rows;
    public int cols;
    private String grid[][];
    private ArrayList<String> placedShapes = new ArrayList<>();

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        this.grid = new String[rows][cols];

        initGrid();
    }

    public boolean tryPlacingShape(int x, int y, Shape shape) {
        boolean canPlace = canPlace(x, y, shape) && !placedShapes.contains(shape.getLabel());

        if (canPlace) {
            shape.forEachBlock(block -> grid[block.getY() + y][block.getX() + x] = shape.getLabel());
            placedShapes.add(shape.getLabel());
        }

        return canPlace;
    }

    public boolean hasShape(Shape s) {
        return placedShapes.contains(s.getLabel());
    }

    private boolean canPlace(int x, int y, Shape shape) {
        boolean canPlace = true;

        for (Block block : shape.getBlocks()) {
            int dx = block.getX() + x;
            int dy = block.getY() + y;

            if (dx < 0 || dy < 0 || dx >= cols || dy >= rows || !isOpen(dx, dy)) {
                canPlace = false;
                break;
            }

        }

        return canPlace;
    }

    public void removeShape(Shape shape) {
        iterateGrid((x, y) -> {
            if (grid[y][x].equals(shape.getLabel()))
                grid[y][x] = "";
        });
        placedShapes.remove(shape.getLabel());
    }

    private boolean isOpen(int x, int y) {
        return grid[y][x].isEmpty();
    }

    private void initGrid() {
        iterateGrid((x, y) -> this.grid[y][x] = "");
    }

    private void iterateGrid(LoopHandler handler) {
        for (int y = 0; y < rows; y++)
            for (int x = 0; x < cols; x++)
                handler.handle(x, y);
    }

    public int shapeCount() {
        return placedShapes.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (!grid[y][x].isEmpty())
                    sb.append(String.format("[%s]", grid[y][x]));
                else
                    sb.append("[ ]");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    private boolean compareWith(Grid other) {
        if (this.rows != other.rows || this.cols != other.cols || this.placedShapes.size() != other.placedShapes.size())
            return false;
        else {
            boolean same = true;
            for (int y = 0; y < rows && same; y++) {
                for (int x = 0; x < cols; x++) {
                    if (!(this.grid[y][x].equals(other.grid[y][x]))) {
                        same = false;
                        break;
                    }
                }
            }
            return same;
        }

    }

    @Override
    public boolean equals(Object other) {

        if (other == null || !(other instanceof Grid))
            return false;
        else {
            Grid otherGrid = (Grid) other;
            return this.compareWith(otherGrid);
        }
    }

    public boolean isFull() {
        int emptyCount = 0;
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (grid[y][x].isEmpty())
                    emptyCount++;
            }
        }
        return emptyCount == 0;
    }
}
