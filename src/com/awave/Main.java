package com.awave;

import com.awave.Polyomino.Grid;
import com.awave.Polyomino.Shape;
import com.awave.utils.BoardRunner;
import com.awave.utils.Utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Utils.BoardContents boardContents = Utils.parseFile();
        Grid grid = new Grid(boardContents.rows, boardContents.cols);
        new PolyominoSolver(grid, boardContents.shapes).solve();
    }
}
