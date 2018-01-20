package com.awave;

import com.awave.Polyomino.Grid;
import com.awave.Polyomino.Shape;
import com.awave.utils.Utils;
import com.awave.utils.Utils.BoardContents;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        BoardContents boardContents = Utils.parseFile("p.txt");
        ArrayList<Shape> shapes = boardContents.shapes;
        Grid grid = new Grid(boardContents.rows, boardContents.cols);

        PolyominoSolver solver = PolyominoSolver.withGrid(grid);
        solver.solve(shapes);

    }
}
