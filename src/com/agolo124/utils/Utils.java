/**
 * @author: Artem Golovin
 */

package com.agolo124.utils;

import com.agolo124.Polyomino.Block;
import com.agolo124.Polyomino.Shape;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Utils {
    public static BoardContents parseFile(String filename) throws FileNotFoundException {
        return parse(new Scanner(new File(filename)));
    }

    public static BoardContents parseFile() throws FileNotFoundException {
        return parse(new Scanner(System.in));
    }

    private static BoardContents parse(Scanner filein) {
        BoardContents boardContents = new BoardContents();

        int rows = filein.nextInt();
        int cols = filein.nextInt();

        boardContents.rows = rows;
        boardContents.cols = cols;

        ArrayList<Shape> shapes = new ArrayList<>();

        int shapeCount = filein.nextInt();
        filein.nextLine(); // skip the newline

        char label = 'a';
        for (int i = 0; i < shapeCount; i++) {
            ArrayList<String> shapeFormat = new ArrayList<>(Arrays.asList(filein.nextLine().split("\\s{2}")));
            int blockCount = Integer.parseInt(shapeFormat.remove(0));
            boardContents.totalBlocks += blockCount;
            Shape shape = new Shape();

            if (blockCount == shapeFormat.size()) {
                shapeFormat.forEach(blockPos -> {
                    String pos[] = blockPos.split("\\s");
                    int x = Integer.parseInt(pos[0]);
                    int y = Integer.parseInt(pos[1]);
                    shape.addBlock(new Block(x, y));
                });
            }
            shape.setLabel(String.valueOf(label++));
            shapes.add(shape);
        }

        boardContents.shapes = shapes;

        return boardContents;
    }


    public static class BoardContents {
        public ArrayList<Shape> shapes;
        public int rows;
        public int cols;
        public static int totalBlocks;
    }
}
