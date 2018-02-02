/**
 * Polyomino tiling problem solver
 *
 * @course: COMP 3649-001
 * @instructor: Charles Hepler
 * @assignment: 1
 * @author: Artem Golovin
 * @email: agolo124@mtroyal.ca
 */

package com.agolo124;

import com.agolo124.Polyomino.Grid;
import com.agolo124.utils.BoardRunner;
import com.agolo124.utils.Utils;
import com.agolo124.utils.Utils.BoardContents;

import java.io.FileNotFoundException;

public class Tiling {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length == 0) {
            BoardContents boardContents = Utils.parseFile();
            Grid grid = new Grid(boardContents.rows, boardContents.cols);
            new PolyominoSolver(grid, boardContents.shapes).solve();
        } else {
            BoardRunner boardRunner = new BoardRunner();
            boardRunner.addBoards(args);
            boardRunner.runAll();
        }
    }
}
