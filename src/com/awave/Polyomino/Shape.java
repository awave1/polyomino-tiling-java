package com.awave.Polyomino;

// todo: refactor to x and y


import com.awave.utils.Utils;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Shape is a list of blocks
 */
public class Shape {
    private ArrayList<Block> blocks;

    private String name;

    // By default set width and height to 1
    private int width = 1;
    private int height = 1;

    public Shape(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    public Shape() {
        this.blocks = new ArrayList<>();
    }

    public Shape(String name) {
        this.blocks = new ArrayList<>();
        this.name = name;
    }

    public void addBlock(Block block) {
        this.blocks.add(block);
        this.updateWidthHeight(block);
    }

    private void updateWidthHeight(Block block) {
        if (this.blocks.size() > 1) {
            if ((block.getCol() + 1) > this.width)
                this.width = block.getCol() + 1;

            if ((block.getRow() + 1) > this.height)
                this.height = block.getRow() + 1;
        }
    }

    public void addBlock(int row, int col) {
        Block block = new Block(row, col);
        this.blocks.add(block);
        this.updateWidthHeight(block);
    }

    /**
     * Rotation map:
     *        (row, col)
     *      - (x, y) => (x, y)   0
     *      - (x, y) => (-y, x)  90 (row, col) => (-col, row)
     *      - (x, y) => (-x, -y) 180 (row, col) => (-row, -col)
     *      - (x, y) => (y, -x)  270 (row, col) => (col, -row)
     */
    public Shape rotate(int angle) {
        Shape shape = this.clone();

        if (angle == 90 || angle == 270) {
            int temp = shape.height;
            shape.height = shape.width;
            shape.width = temp;
        }

        if (angle != 0) {
            shape.forEachBlock(block -> {
                int newRow;
                int newCol;
                // rotate
                switch (angle) {
                    case 90:
                        newRow = -block.getCol();
                        newCol = block.getRow();

                        newRow += shape.height;

                        block.setRow(newRow);
                        block.setCol(newCol);
                        break;
                    case 180:
                        newRow = -block.getRow();
                        newCol = -block.getCol();

                        newRow += (shape.height - 1);
                        newCol += shape.width;

                        block.setRow(newRow);
                        block.setCol(newCol);
                        break;
                    case 270:
                        newRow = block.getCol();
                        newCol = -block.getRow();

                        newCol += shape.width - 1;

                        block.setRow(newRow);
                        block.setCol(newCol);
                        break;
                }
            });
        }

        return shape;
    }

    /**
     * Reflection:
     *      0 - (x, y) => (-x, y)
     *      1 - (x, y) => (x, -y)
     */
    public Shape reflect() {
        Shape shape = this.clone();

        shape.forEachBlock(block -> {

            int col = -block.getCol();
            col += shape.width;
            block.setCol(col);

        });

        return shape;
    }

    private void addBlocks(ArrayList<Block> blocks) {
        blocks.forEach(this::addBlock);
    }

    public ArrayList<Shape> getTransformedShapes() {
        ArrayList<Shape> shapes = new ArrayList<>();

        for (int angle = 0; angle < 360; angle+=90) {
            Shape rotated = rotate(angle);
            shapes.add(rotated);
            shapes.add(rotated.reflect());
        }

        return shapes;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int size() {
        return this.blocks.size();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public void forEachBlock(Consumer<Block> eachBlock) {
        this.blocks.forEach(eachBlock);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shape clone() {
        Shape shape = new Shape(Utils.randChar());
        for (Block block : this.getBlocks()) {
            shape.addBlock(new Block(block.getRow(), block.getCol()));
        }
        return shape;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
