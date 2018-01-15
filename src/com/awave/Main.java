package com.awave;

import com.awave.Polyomino.Grid;
import com.awave.utils.Utils;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Grid g = Utils.parseFile("p.txt");
        System.out.println(g);
    }
}
