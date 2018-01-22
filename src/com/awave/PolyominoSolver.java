package com.awave;

import com.awave.Polyomino.Grid;
import com.awave.Polyomino.LoopHandler;
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
            solver = new PolyominoSolver(g);
        return solver;
    }

    public void solve(ArrayList<Shape> shapes) {
        System.out.println();
        System.out.println((this.solve(shapes, shapes.size())));
        System.out.println(grid);
    }

    private boolean solve(ArrayList<Shape> shapes, int elementCount) {



        return false;
    }
}
