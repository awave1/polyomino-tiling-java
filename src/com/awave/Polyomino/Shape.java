package com.awave.Polyomino;

// todo: refactor to x and y


import com.awave.utils.Utils;
import sun.security.provider.SHA;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Shape is a list of blocks
 */
public class Shape {
    private ArrayList<Block> blocks;

    private String label;

    // By default set width and height to 1
    private int width = 1;
    private int height = 1;

    public Shape(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    public Shape() {
        this.blocks = new ArrayList<>();
    }

    public Shape(String label) {
        this.blocks = new ArrayList<>();
        this.label = label;
    }

    public void addBlock(Block block) {
        this.blocks.add(block);
        this.updateWidthHeight(block);
    }

    private void updateWidthHeight(Block block) {
        if (this.blocks.size() > 1) {
            if ((block.getX() + 1) > this.width)
                this.width = block.getX() + 1;

            if ((block.getY() + 1) > this.height)
                this.height = block.getY() + 1;
        }
    }

    public void addBlock(int x, int y) {
        Block block = new Block(x, y);
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
    public Shape rotate() {
        Shape shape = new Shape(getLabel());

        forEachBlock(block -> {
            int dx, dy;
            dx = -block.getY() + (height - 1) ;
            dy =  block.getX();
            shape.addBlock(dx, dy);
        });

        return shape;
    }

    /**
     * Reflection:
     *      0 - (x, y) => (-x, y)
     *      1 - (x, y) => (x, -y)
     */
    public Shape reflect() {
        Shape shape = new Shape(getLabel());
        forEachBlock(block -> {
            int dx = -block.getX() + (width - 1);
            shape.addBlock(dx, block.getY());
        });

        return shape;
    }

    private void addBlocks(ArrayList<Block> blocks) {
        blocks.forEach(this::addBlock);
    }

    public ArrayList<Shape> getTransformedShapes() {
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(this);

        for (int i = 0; i < 3; i++) {
            shapes.add(shapes.get(shapes.size() - 1).rotate());
        }

        shapes.addAll(new ArrayList<Shape>(){{
            shapes.forEach(shape -> add(shape.reflect()));
        }});

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

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public void forEachBlock(Consumer<Block> eachBlock) {
        this.blocks.forEach(eachBlock);
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    @Override
    public String toString() {
        return "Shape{" +
                "label='" + label + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
