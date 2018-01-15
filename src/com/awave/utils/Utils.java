package com.awave.utils;

import com.awave.Polyomino.Block;
import com.awave.Polyomino.Grid;
import com.awave.Polyomino.Shape;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Utils {
    public static Grid parseFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner filein = new Scanner(file);

        int rows = filein.nextInt();
        int cols = filein.nextInt();
        Grid grid = new Grid(rows, cols);

        ArrayList<Shape> shapes = new ArrayList<>();

        int shapeCount = filein.nextInt();
        filein.nextLine(); // skip the newline
        for (int i = 0; i < shapeCount; i++) {
            ArrayList<String> shapeFormat =
                    new ArrayList<>(Arrays.asList(filein.nextLine().split("\\s{2}")));
            int blockCount = Integer.parseInt(shapeFormat.remove(0));
            ArrayList<Block> blocks = new ArrayList<>();

            if (blockCount == shapeFormat.size()) {
                shapeFormat.forEach(blockPos -> {
                    String pos[] = blockPos.split("\\s");
                    int row = Integer.parseInt(pos[0]);
                    int col = Integer.parseInt(pos[1]);
                    blocks.add(new Block(row, col));
                });
            }

            shapes.add(new Shape(blocks));
        }

        grid.addShapes(shapes);
        return grid;
    }
}
