package test.com.awave;

import com.agolo124.Polyomino.Grid;
import com.agolo124.Polyomino.Shape;
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
