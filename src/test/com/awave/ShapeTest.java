package test.com.awave;

import com.awave.Polyomino.Block;
import com.awave.Polyomino.Grid;
import com.awave.Polyomino.Shape;
import org.junit.Before;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ShapeTest {

    private Shape shape;
    private ArrayList<Block> blocks = new ArrayList<>();

    @org.junit.Test
    public void addBlock_shouldAddSuccessfully() throws Exception {
        shape = new Shape();

        shape.addBlock(new Block(0, 0));
        shape.addBlock(new Block(0, 1));
        shape.addBlock(new Block(0, 3));
        shape.addBlock(new Block(1, 0));
        shape.addBlock(new Block(0, 3));

        assertNotEquals(shape.size(), 0);
    }

    @org.junit.Test
    public void addBlock_shouldSkipOneBlock() throws Exception {
        shape = new Shape();

        shape.addBlock(new Block(0, 0));
        shape.addBlock(new Block(0, 1));
        shape.addBlock(new Block(0, 3));
        shape.addBlock(new Block(1, 0));
        shape.addBlock(new Block(0, 3));

        assertNotEquals(shape.size(), 5);
    }

    @org.junit.Test
    public void rotate_shouldRotate90() throws Exception {

        // todo: swap x and y and negate y

        Grid grid1 = new Grid(20, 20);
        shape = new Shape();

        shape.addBlock(0, 0);
        shape.addBlock(0, 1);
        shape.addBlock(0, 2);
        shape.addBlock(0, 3);
        shape.addBlock(1, 0);
        shape.addBlock(1, 1);

        grid1.addShape(shape);

        shape.rotate();

        Grid grid2 = new Grid(20, 20);
        grid2.addShape(shape);

        System.out.println(grid1);
        System.out.println();
        System.out.println(grid2);


    }

    @org.junit.Test
    public void rotate_shouldRotate180() throws Exception {
        shape = new Shape();

    }

    @org.junit.Test
    public void rotate_shouldRotate270() throws Exception {
        shape = new Shape();

    }

    @org.junit.Test
    public void rotate_shouldRotate360() throws Exception {
        shape = new Shape();

    }

    @org.junit.Test
    public void reflect() throws Exception {
    }

    @org.junit.Test
    public void isSquare_shouldBeSquare() throws Exception {
        shape = new Shape();

        shape.addBlock(0, 0);
        shape.addBlock(0, 1);
        shape.addBlock(1, 0);
        shape.addBlock(1, 1);

        shape.isSquare();
    }


    private void format(String s, String args) {
        System.out.format(s, args);
    }
}