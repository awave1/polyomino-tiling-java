package com.awave.Polyomino;

// todo: refactor to x and y


import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Shape is a list of blocks
 */
public class Shape {
    private ArrayList<Block> blocks;

    private String label;

    // By default set width and height to 1
    private int width  = 0;
    private int height = 0;
    private int firstOccupiedBlockIndex;

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

    public void addBlock(int x, int y) {
        Block block = new Block(x, y);
        this.addBlock(block);
    }

    private void updateWidthHeight(Block block) {
        width  = Math.max(block.getX() + 1, width);
        height = Math.max(block.getY() + 1, height);
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

        for (Block block : getBlocks()) {
            int dx, dy;
            dx = -block.getY() + (height - 1) ;
            dy =  block.getX();
            shape.addBlock(dx, dy);
        }

        return shape;
    }

    /**
     * Reflection:
     *      0 - (x, y) => (-x, y)
     *      1 - (x, y) => (x, -y)
     */
    public Shape reflectY() {
        Shape shape = new Shape(getLabel());
        forEachBlock(block -> {
            int dx = -block.getX() + (width - 1);
            shape.addBlock(dx, block.getY());
        });

        return shape;
    }

    public Shape reflectX() {
        Shape shape = new Shape(getLabel());
        forEachBlock(block -> {
            int dy = -block.getY() + (height - 1);
            shape.addBlock(block.getX(), dy);
        });

        return shape;
    }

    private void addBlocks(ArrayList<Block> blocks) {
        blocks.forEach(this::addBlock);
    }

    public ArrayList<Shape> getTransformedShapes() {
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(this);

        if (!this.isSquare() && !this.isLine() && !this.isRectangle()) {

            for (int i = 0; i < 3; i++) {
                Shape rotated = shapes.get(shapes.size() - 1).rotate();
                shapes.add(rotated);
            }

            shapes.addAll(new ArrayList<Shape>(){{
                shapes.forEach(shape -> add(shape.reflectX()));
            }});

            shapes.addAll(new ArrayList<Shape>() {{
                shapes.forEach(shape -> add(shape.reflectY()));
            }});


        } else if (this.isLine() || this.isRectangle()) {
            shapes.add(this.rotate());
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
        Grid grid = new Grid(height, width);
        grid.tryPlacingShape(0, 0, this);
        String gridResult = grid.toString();
        grid.removeShape(this);

        StringBuilder sb = new StringBuilder();
        String shapeInfo = "Shape{" +
                "label='" + label + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
        sb.append(shapeInfo).append("\n").append(gridResult);

        return sb.toString();
    }

    private boolean isSquare() {
        return width == height && (width * height) == getBlocks().size();
    }

    private boolean isRectangle() {
        return (width * height) == getBlocks().size();
    }

    private boolean isLine() {
        return width == 1 || height == 1;
    }

    public int getFirstOccupiedBlockIndex() {

        return firstOccupiedBlockIndex;
    }
}
