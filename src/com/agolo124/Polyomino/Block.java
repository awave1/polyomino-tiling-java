/**
 * @author: Artem Golovin
 */

package com.agolo124.Polyomino;

public class Block {
    private int y;
    private int x;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Block{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public void setPos(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Block block = (Block) o;

        if (y != block.y) return false;
        return x == block.x;
    }

    @Override
    public int hashCode() {
        int result = y;
        result = 31 * result + x;
        return result;
    }
}
