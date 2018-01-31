package com.awave;

import com.awave.utils.BoardRunner;

public class Main {
    public static void main(String[] args) {
        BoardRunner boardRunner = new BoardRunner();
        boardRunner.addBoards(
//                "simple1-success.txt",
//                "simple2-success.txt",
//                "simple3-success.txt",
//                "simple4-success.txt",
                "medium1-success.txt"
//                "fail1.txt",
//                "fail2.txt"
        );
        boardRunner.runAll();
    }
}
