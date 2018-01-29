package test.com.awave;

import com.awave.Polyomino.Grid;
import com.awave.Polyomino.Shape;
import org.junit.Before;
import org.junit.Test;

public class GridTest {

    Grid grid;

    @Test
    public void addShape_shouldAddSingleShape() throws Exception {
        Shape shape = new Shape("a");
        shape.addBlock(0, 0);
        shape.addBlock(1, 0);
        shape.addBlock(1, 1);
        shape.addBlock(0, 1);
        shape.addBlock(0, 2);
    }
}
