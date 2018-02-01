package com.awave;

import com.awave.utils.BoardRunner;

public class Main {
    public static void main(String[] args) {

        BoardRunner boardRunner = new BoardRunner();
        if (args.length == 0)
            boardRunner.addBoards("medium1-success.txt", "medium2-success.txt");
        else
            boardRunner.addBoards(args);
        boardRunner.runAll();
    }
}
