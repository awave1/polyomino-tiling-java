package com.awave.Polyomino;


import com.awave.Position;

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

    public boolean placeShape(int x, int y, Shape shape) {
        boolean placed = false;
        if (placedShapes.isEmpty()) {
            shape.forEachBlock(block -> setElementAt(block.getX() + x, block.getY() + y, shape.getLabel()));
            placedShapes.add(shape);
            placed = true;
        } else {
            ArrayList<Position> positions = getClosestPositions(placedShapes.get(placedShapes.size() - 1));
            for (Position position : positions) {
                boolean canPlace = canPlaceShape(position.getX(), position.getY(), shape);
                if (canPlace) {
                    for (Block block : shape.getBlocks()) {
                        int dx = block.getX() + position.getX();
                        int dy = block.getY() + position.getY();
                        if (isOpen(dx, dy)) {
                            setElementAt(dx, dy, shape.getLabel());
                            block.setPos(dx, dy);
                        }
                    }
                    placedShapes.add(shape);
                    placed = true;
                    break;
                }
            }
        }

        return placed;
    }

    private boolean isOpen(int dx, int dy) {
        return this.grid[dy][dx] == null;
    }

    private ArrayList<Position> getClosestPositions(Shape neighbor) {
        ArrayList<Position> openPositions = new ArrayList<>();
        for (int y = 0; y < neighbor.getHeight(); y++) {
            for (int x = 0; x < neighbor.getWidth(); x++) {
                if (this.grid[y][x] == null)
                    openPositions.add(new Position(x, y));
            }
        }

        return openPositions;
    }

    public void removeShape(Shape shape) {
        iterateGrid((x, y) -> {
            if (this.grid[y][x] != null && getElementAt(x, y).equals(shape.getLabel()))
                removeElementAt(x, y);
        });
        placedShapes.remove(shape);
    }

    private boolean canPlaceShape(int x, int y, Shape shape) {

        boolean canPlace = true;

        for (int i = 0; i < shape.getBlocks().size() && canPlace; i++) {
            Block block = shape.getBlocks().get(i);
            int dx = block.getX() + x;
            int dy = block.getY() + y;

            if (dy >= rows || dx >= cols || !this.hasElementAt(dx, dy))
                canPlace = false;
        }

        return canPlace;
    }

    private boolean hasElementAt(int x, int y) {
        return this.grid[y][x] == null;
    }

    public void iterateGrid(LoopHandler handler) {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                handler.handle(x, y);
            }
        }
    }

    private String getElementAt(int x, int y) {
        return grid[y][x];
    }

    private void setElementAt(int x, int y, String el) {
        this.grid[y][x] = el;
    }

    private void removeElementAt(int x, int y) {
        setElementAt(x, y, null);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (grid[y][x] != null)
                    sb.append(String.format("[%s]", grid[y][x]));
                else
                    sb.append("[ ]");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
