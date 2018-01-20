package test.com.awave;

import com.awave.Polyomino.Block;
import com.awave.Polyomino.Grid;
import com.awave.Polyomino.Shape;
import com.awave.utils.Utils;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ShapeTest {

    private Shape shape;
    private ArrayList<Block> blocks = new ArrayList<>();
    private Grid grid = new Grid(20, 20);


    @org.junit.Test
    public void addBlock_shouldAddSingleSuccessfully() throws Exception {
        shape = new Shape();

        shape.addBlock(new Block(0, 2));

        assertNotEquals(shape.size(), 0);
        assertEquals(shape.getWidth(), 1);
        assertEquals(shape.getHeight(), 1);
    }

    @org.junit.Test
    public void addBlock_shouldAddLineSuccessfully() throws Exception {
        shape = new Shape();

        shape.addBlock(new Block(0, 0));
        shape.addBlock(new Block(0, 1));
        shape.addBlock(new Block(0, 2));


        assertNotEquals(shape.size(), 0);
        assertEquals(shape.size(), 3);
        assertEquals(shape.getWidth(), 3);
        assertEquals(shape.getHeight(), 1);
    }

    @org.junit.Test
    public void addBlock_shouldAddSquareSuccessfully() throws Exception {
        shape = new Shape();

        shape.addBlock(new Block(0, 0));
        shape.addBlock(new Block(0, 1));
        shape.addBlock(new Block(0, 2));

        shape.addBlock(new Block(1, 0));
        shape.addBlock(new Block(1, 1));
        shape.addBlock(new Block(1, 2));

        shape.addBlock(new Block(2, 0));
        shape.addBlock(new Block(2, 1));
        shape.addBlock(new Block(2, 2));

        assertNotEquals(shape.size(), 0);
        assertEquals(shape.size(), 9);
        assertEquals(shape.getWidth(), 3);
        assertEquals(shape.getHeight(), 3);
    }

    @org.junit.Test
    public void addBlock_shouldAddRectangleSuccessfully() throws Exception {
        shape = new Shape();

        shape.addBlock(new Block(0, 0));
        shape.addBlock(new Block(0, 1));
        shape.addBlock(new Block(0, 2));

        shape.addBlock(new Block(1, 0));
        shape.addBlock(new Block(1, 1));
        shape.addBlock(new Block(1, 2));

        assertNotEquals(shape.size(), 0);
        assertEquals(shape.size(), 6);
        assertEquals(shape.getWidth(), 3);
        assertEquals(shape.getHeight(), 2);
    }

    @org.junit.Test
    public void addBlock_shouldAddShapeSuccessfully() throws Exception {
        shape = new Shape();

        shape.addBlock(new Block(0, 0));
        shape.addBlock(new Block(0, 1));
        shape.addBlock(new Block(0, 2));
        shape.addBlock(new Block(0, 3));

        shape.addBlock(new Block(1, 0));
        shape.addBlock(new Block(1, 1));
        shape.addBlock(new Block(1, 2));
        shape.addBlock(new Block(1, 3));
        shape.addBlock(new Block(1, 4));

        shape.addBlock(new Block(2, 3));

        assertNotEquals(shape.size(), 0);
        assertEquals(shape.size(), 10);
        assertEquals(shape.getWidth(), 5);
        assertEquals(shape.getHeight(), 3);
    }

    @org.junit.Test
    public void rotate_rotate90() throws Exception {
        shape = new Shape("a");
        grid = new Grid(4, 4);

        shape.addBlock(new Block(0, 0));
        shape.addBlock(new Block(0, 1));
        shape.addBlock(new Block(1, 0));
        shape.addBlock(new Block(2, 0));
        shape.addBlock(new Block(3, 0));

        grid.tryPlaceAt(0, 0, shape);
        System.out.println(grid);
        grid.removeShape(shape);
        Shape res = shape.rotate(90);
        grid.tryPlaceAt(0, 0, res);
        System.out.println(grid);

        assertEquals(res.getWidth(), shape.getHeight());
        assertEquals(res.getHeight(), shape.getWidth());
    }

    @org.junit.Test
    public void rotate_rotate180() throws Exception {
        shape = new Shape();

        shape.addBlock(new Block(0, 0));
        shape.addBlock(new Block(0, 1));
        shape.addBlock(new Block(1, 0));
        shape.addBlock(new Block(2, 0));
        shape.addBlock(new Block(3, 0));

        shape.rotate(180);
    }

    @org.junit.Test
    public void rotate_rotate270() throws Exception {
        shape = new Shape();

        shape.addBlock(new Block(0, 0));
        shape.addBlock(new Block(0, 1));
        shape.addBlock(new Block(1, 0));
        shape.addBlock(new Block(2, 0));
        shape.addBlock(new Block(3, 0));

        int oldWidth = shape.getWidth();
        int oldHeight = shape.getHeight();

        shape.rotate(270);

        assertEquals(shape.getWidth(), oldHeight);
        assertEquals(shape.getHeight(), oldWidth);
    }

    @org.junit.Test
    public void reflect_x() throws Exception {
        shape = new Shape("A");
        grid = new Grid(4, 4);

        shape.addBlock(new Block(0, 0));
        shape.addBlock(new Block(0, 1));
        shape.addBlock(new Block(1, 0));
        shape.addBlock(new Block(2, 0));
        shape.addBlock(new Block(3, 0));

        grid.tryPlaceAt(0, 0, shape);
        System.out.println(grid);
        grid.removeShape(shape);

        Shape r1 = shape.reflect();
        grid.tryPlaceAt(0, 0, r1);
        System.out.println(grid);
        grid.removeShape(r1);
        grid.tryPlaceAt(0, 0, r1.reflect());
        System.out.println(grid);

    }

    // TODO: add failing tests
}
