/**
 * @author: Artem Golovin
 */

package com.agolo124.Polyomino;

import java.util.*;
import java.util.function.Consumer;

public class Shape {
    private ArrayList<Block> blocks;

    private String label;

    private int width = 0;
    private int height = 0;

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
        width = Math.max(block.getX() + 1, width);
        height = Math.max(block.getY() + 1, height);
    }

    /**
     * Rotation map:
     * (row, col)
     * - (x, y) => (-y, x)
     */
    public Shape rotate() {
        Shape shape = new Shape(getLabel());

        for (Block block : getBlocks()) {
            int dx, dy;
            dx = -block.getY() + (height - 1);
            dy = block.getX();
            shape.addBlock(dx, dy);
        }

        return shape;
    }

    /**
     * Reflect using following map:
     *      (x, y) => (-x, y)
     */
    public Shape reflectY() {
        Shape shape = new Shape(getLabel());
        forEachBlock(block -> {
            int dx = -block.getX() + (width - 1);
            shape.addBlock(dx, block.getY());
        });

        return shape;
    }

    /**
     * Reflex using following map:
     *      (x, y) => (x, -y)
     */
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

    /**
     * Generates array of unique transformed elements
     * @return list of Shapes
     */
    public ArrayList<Shape> getTransformedShapes() {
        ArrayList<Shape> shapes = new ArrayList<>();
        ArrayList<Shape> temp = new ArrayList<>(4);
        shapes.add(this);

        if (!this.isSquare() && !this.isLine() && !this.isRectangle()) {

            for (int i = 0; i < 3; i++)
                shapes.add(shapes.get(shapes.size() - 1).rotate());

            shapes.forEach(shape -> temp.add(shape.reflectX()));
            shapes.addAll(new ArrayList<Shape>() {{
                shapes.forEach(shape -> add(shape.reflectY()));
            }});
            shapes.addAll(temp);


        } else if (this.isLine() || this.isRectangle()) {
            shapes.add(this.rotate());
        }


        return new ArrayList<>(new LinkedHashSet<>(shapes));
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shape shape = (Shape) o;
        boolean blocksMatch = true;

        if (width != shape.width) return false;
        if (height != shape.height) return false;
        if (!blocks.equals(shape.blocks)) return false;

        for (int i = 0; i < blocks.size(); i++) {
            Block a = blocks.get(i);
            Block b = shape.blocks.get(i);
            if (!a.equals(b)) {
                blocksMatch = false;
                break;
            }
        }

        return blocksMatch && label.equals(shape.label);

    }

    @Override
    public int hashCode() {
        int result = blocks.hashCode();
        result = 31 * result + label.hashCode();
        result = 31 * result + width;
        result = 31 * result + height;

        for (Block block : blocks) {
            result += block.hashCode();
        }

        return result;
    }
}
