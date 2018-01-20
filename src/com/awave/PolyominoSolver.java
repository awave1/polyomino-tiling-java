package com.awave;

import com.awave.Polyomino.Grid;
import com.awave.Polyomino.Shape;
import sun.security.provider.SHA;

import java.util.ArrayList;

public class PolyominoSolver {

    private Grid grid;
    private static PolyominoSolver solver;

    private PolyominoSolver(Grid g) {
        this.grid = g;
    }

    public static PolyominoSolver withGrid(Grid g) {
        if (solver == null)
            return new PolyominoSolver(g);
        return solver;
    }

    public void solve(ArrayList<Shape> shapes) {
        System.out.println();
        this.solve(shapes, shapes.size());
        System.out.println(grid);
    }

    public boolean solve(ArrayList<Shape> shapes, int elementCount) {

        if (elementCount == 0)
            return true;


        return false;
    }
}
