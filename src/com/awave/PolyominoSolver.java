package com.awave;

import com.awave.Polyomino.Grid;
import com.awave.Polyomino.Shape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PolyominoSolver {

    private Grid grid;
    private ArrayList<Shape> shapes;
    private HashMap<String, HashSet<Shape>> transformed;

    public PolyominoSolver(Grid g, ArrayList<Shape> shapes) {
        this.grid = g;
        this.shapes = shapes;
        this.transformed = new HashMap<>();
    }

    public void solve() {
        shapes.forEach(shape -> transformed.put(shape.getLabel(), shape.uniqueShapes()));
        System.out.println(this.recursiveSolve() ? grid : "No solution found.");
    }

    /**
     * Recursive solver
     *
     * @return
     */
    private boolean recursiveSolve() {

        // Base case - done when there are no shapes left to place
        if (shapes.size() == grid.shapeCount())
            return true;

        // Iterative + recursive backtracking case
        for (Shape shape : shapes) {
            for (Shape transformedShape : shape.uniqueShapes()) {
                for (int y = 0; y < grid.rows; y++) {
                    for (int x = 0; x < grid.cols; x++) {
                        if (grid.tryPlacingShape(x, y, transformedShape)) {
                            System.out.println(grid);
                            if (recursiveSolve())
                                return true;
                            grid.removeShape(transformedShape);
                        }
                    }
                }
            }
        }

        return false;
    }
}
