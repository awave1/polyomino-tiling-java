package com.awave.Polyomino;

// todo: refactor to x and y


import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Shape is a list of blocks
 */
public class Shape {
    private ArrayList<Block> blocks;
    private String ch;

    public Shape(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    public Shape() {
        this.blocks = new ArrayList<>();
    }

    public void addBlock(Block block) {
        if (!blockExists(block.getCol(), block.getRow()))
            this.blocks.add(block);
    }


    public void addBlock(int row, int col) {
        if (!blockExists(row, col))
            this.blocks.add(new Block(row, col));
    }

    public boolean isSquare() {
        boolean isSquare = false;

        for (Block block : this.blocks) {
            System.out.println(block.getRow() + ", " + block.getCol());
        }

        return isSquare;
    }

    public int size() {
        return this.blocks.size();
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public String getChar() {
        return ch;
    }

    public void setChar(String ch) {
        this.ch = ch;
    }

    public boolean rotate() {

        boolean rotated = false;

        this.blocks.forEach(block -> {
            int temp = block.getCol();
            block.setCol(block.getRow());
            block.setRow(temp);
        });

        return true;
    }

    public boolean reflect() {
        return false;
    }

    private boolean blockExists(int row, int col) {
        boolean exists = false;
        for (Block block : blocks) {
            if (block.getRow() == row && block.getCol() == col) {
                exists = true;
                break;
            }
        }
        return exists;
    }
}
