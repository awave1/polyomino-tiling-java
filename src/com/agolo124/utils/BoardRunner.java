/**
 * @author: Artem Golovin
 */

package com.agolo124.utils;

import com.agolo124.Polyomino.Grid;
import com.agolo124.Polyomino.Shape;
import com.agolo124.PolyominoSolver;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class BoardRunner {

    private String filename;
    private ArrayList<BoardRunner> files;

    public BoardRunner() {
        files = new ArrayList<>();
    }

    private BoardRunner(String filename) {
        this.filename = filename;
    }

    public void addBoard(String filename) {
        files.add(new BoardRunner(filename));
    }

    public void addBoards(String... boards) {
        Arrays.asList(boards).forEach(board -> files.add(new BoardRunner(board)));
    }

    public void runAll() {
        files.forEach(BoardRunner::runBoard);
    }

    private void runBoard() {
        System.out.println(String.format("running %s", filename));
        Utils.BoardContents boardContents = null;
        try {
            boardContents = Utils.parseFile(this.filename);
            ArrayList<Shape> shapes = boardContents.shapes;
            Grid grid = new Grid(boardContents.rows, boardContents.cols);
            PolyominoSolver solver = new PolyominoSolver(grid, shapes);
            solver.solve();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("done %s\n", filename));
    }
}
