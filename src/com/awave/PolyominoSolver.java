package com.awave;

import com.awave.Polyomino.Grid;
import com.awave.Polyomino.Shape;

import java.util.ArrayList;
import java.util.HashMap;

public class PolyominoSolver {

    private Grid grid;
    private ArrayList<Shape> shapes;
    private HashMap<String, ArrayList<Shape>> transformed;

    public PolyominoSolver(Grid g, ArrayList<Shape> shapes) {
        this.grid = g;
        this.shapes = shapes;
        this.transformed = new HashMap<>();
    }

    public void solve() {
        shapes.forEach(shape -> transformed.put(shape.getLabel(), shape.uniqueShapes()));
        long start = System.currentTimeMillis();
        System.out.println(this.recursiveSolve() ? grid : "No solution found.");
        System.out.printf("Took: %fs\n", (System.currentTimeMillis() - start) * 0.001);
    }

    /**
     * Recursive solver
     * @return
     */
    private boolean recursiveSolve() {

        // Base case - done when there are no shapes left to place
        if (shapes.size() == grid.shapeCount())
            return true;

        // Iterative + recursive backtracking case
        for (Shape shape : shapes) {

            for (int y = 0; y < grid.rows; y++) {
                for (int x = 0; x < grid.cols; x++) {
                    for (Shape transformedShape : transformed.get(shape.getLabel())) {
                        if (grid.tryPlacingShape(x, y, transformedShape)) {
                            if (recursiveSolve())
                                return true;
                            else
                                grid.removeShape(transformedShape);
                        }
                    }
                }
            }
        }

        return false;
    }
}
