package com.awave.utils;

import com.awave.Polyomino.Block;
import com.awave.Polyomino.Shape;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {
    public static BoardContents parseFile(String filename) throws FileNotFoundException {
        BoardContents boardContents = new BoardContents();

        File file = new File(filename);
        Scanner filein = new Scanner(file);

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
    }
}
