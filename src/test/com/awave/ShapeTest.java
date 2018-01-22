package test.com.awave;

import com.awave.Polyomino.Block;
import com.awave.Polyomino.Grid;
import com.awave.Polyomino.Shape;

import java.util.ArrayList;

import static org.junit.Assert.*;

import com.awave.utils.Utils;
import org.junit.Test;

public class ShapeTest {

    private Shape shape;
    private Grid grid;

    @Test
    public void addBlock_shouldAddSingleSuccessfully() throws Exception {
        shape = new Shape();

        shape.addBlock(new Block(0, 2));

        assertNotEquals(shape.size(), 0);
        assertEquals(shape.getWidth(), 1);
        assertEquals(shape.getHeight(), 1);
    }

    @Test
    public void addBlock_shouldAddLineSuccessfully() throws Exception {
        shape = new Shape();

        shape.addBlock(new Block(0, 0));
        shape.addBlock(new Block(1, 0));
        shape.addBlock(new Block(2, 0));


        assertNotEquals(shape.size(), 0);
        assertEquals(shape.size(), 3);
        assertEquals(shape.getWidth(), 3);
        assertEquals(shape.getHeight(), 1);
    }

    @Test
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

    @Test
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

    @Test
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

    @Test
    public void rotate_rotate90_shouldRotateNoErrors() throws Exception {
        System.out.println("Rotating 90 degrees");
        shape = new Shape("a");
        grid = new Grid(4, 4);
        Grid grid2 = new Grid(4, 4);

        shape.addBlock(new Block(0, 0));
        shape.addBlock(new Block(0, 1));
        shape.addBlock(new Block(1, 0));
        shape.addBlock(new Block(2, 0));
        shape.addBlock(new Block(3, 0));

        grid.placeShape(0, 0, shape);
        System.out.println(grid);

        Shape res = shape.rotate();
        grid2.placeShape(0, 0, res);
        System.out.println(grid2);

        assertEquals(res.getHeight(), shape.getWidth());
        assertEquals(res.getWidth(), shape.getHeight());
        assertFalse(grid2.equals(grid));
    }

    @Test
    public void rotate_rotate180_shouldRotateNoErrors() throws Exception {
        System.out.println("Rotating 180 degrees");
        shape = new Shape("a");
        grid = new Grid(4, 4);
        Grid grid2 = new Grid(4, 4);

        shape.addBlock(new Block(0, 0));
        shape.addBlock(new Block(0, 1));
        shape.addBlock(new Block(1, 0));
        shape.addBlock(new Block(2, 0));
        shape.addBlock(new Block(3, 0));

        grid.placeShape(0, 0, shape);
        System.out.println(grid);

        Shape res = shape.rotate().rotate();
        grid2.placeShape(0, 0, res);
        System.out.println(grid2);

        System.out.println(shape);
        System.out.println(res);

        assertEquals(res.getWidth(), shape.getWidth());
        assertEquals(res.getHeight(), shape.getHeight());
        assertFalse(grid2.equals(grid));
    }

    @Test
    public void rotate_rotate270_shouldRotateNoErrors() throws Exception {
        System.out.println("Rotating 270 degrees");
        shape = new Shape("a");
        grid = new Grid(4, 4);
        Grid grid2 = new Grid(4, 4);

        shape.addBlock(new Block(0, 0));
        shape.addBlock(new Block(0, 1));
        shape.addBlock(new Block(1, 0));
        shape.addBlock(new Block(2, 0));
        shape.addBlock(new Block(3, 0));

        grid.placeShape(0, 0, shape);
        System.out.println(grid);

        Shape res = shape.rotate().rotate().rotate();
        grid2.placeShape(0, 0, res);
        System.out.println(grid2);

        System.out.println(shape);
        System.out.println(res);

        assertEquals(res.getHeight(), shape.getWidth());
        assertEquals(res.getWidth(), shape.getHeight());
        assertFalse(grid2.equals(grid));
    }

    @Test
    public void reflect_reflect() throws Exception {
        shape = new Shape("a");
        grid = new Grid(4, 4);
        Grid grid1 = new Grid(4, 4);

        shape.addBlock(new Block(0, 0));
        shape.addBlock(new Block(0, 1));
        shape.addBlock(new Block(1, 0));
        shape.addBlock(new Block(2, 0));
        shape.addBlock(new Block(3, 0));


        grid.placeShape(0, 0, shape);
        System.out.println(grid);

        Shape res = shape.reflect();
        grid1.placeShape(0,0, res);
        System.out.println(grid1);

        assertEquals(shape.getWidth(), res.getWidth());
        assertEquals(shape.getHeight(), res.getHeight());
        assertFalse(grid.equals(grid1));
    }

    @Test
    public void transform_all() throws Exception {
        shape = new Shape("a");

        shape.addBlock(new Block(0, 0));
        shape.addBlock(new Block(0, 1));
        shape.addBlock(new Block(0, 2));
        shape.addBlock(new Block(1, 0));
        shape.addBlock(new Block(1, 1));
        assertEquals(8, shape.getTransformedShapes().size());
    }
}
