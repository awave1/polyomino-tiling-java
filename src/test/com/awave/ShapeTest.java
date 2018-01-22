package test.com.awave;

import com.awave.Polyomino.Block;
import com.awave.Polyomino.Grid;
import com.awave.Polyomino.Shape;

import java.util.ArrayList;

import static org.junit.Assert.*;
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
    public void rotate_rotate90() throws Exception {
        shape = new Shape("a");
        grid = new Grid(4, 4);

        shape.addBlock(new Block(0, 0));
        shape.addBlock(new Block(0, 1));
        shape.addBlock(new Block(1, 0));
        shape.addBlock(new Block(2, 0));
        shape.addBlock(new Block(3, 0));

        grid.placeShape(0, 0, shape);
        System.out.println(grid);
        grid.removeShape(shape);

        Shape res = shape.rotate();
        grid.placeShape(0, 0, res);
        System.out.println(grid);
        grid.removeShape(res);
    }

    @Test
    public void reflect_reflect() throws Exception {
        shape = new Shape("a");
        grid = new Grid(4, 4);

        shape.addBlock(new Block(0, 0));
        shape.addBlock(new Block(0, 1));
        shape.addBlock(new Block(1, 0));
        shape.addBlock(new Block(2, 0));
        shape.addBlock(new Block(3, 0));



        grid.placeShape(0, 0, shape);
        System.out.println(grid);
        grid.removeShape(shape);

        Shape res = shape.reflect();
        res.forEachBlock(System.out::println);
        grid.placeShape(0,0,shape.reflect());
        System.out.println(grid);
    }

    @Test
    public void transform_all() throws Exception {
        shape = new Shape("a");
        grid = new Grid(3, 4);

        shape.addBlock(new Block(0, 0));
        shape.addBlock(new Block(0, 1));
        shape.addBlock(new Block(0, 2));
        shape.addBlock(new Block(1, 0));
        shape.addBlock(new Block(1, 1));

//        for (Shape shape1 : shape.getTransformedShapes()) {
//            grid.placeShape(0,0,shape1);
//            System.out.println(grid);
//            System.out.println();
//            grid.removeShape(shape1);
//        }
//
//        grid.placeShape(0, 0, shape.getTransformedShapes().get(0));
        grid.placeShape(1, 1, shape.getTransformedShapes().get(7));
        System.out.println(grid);

    }
}
