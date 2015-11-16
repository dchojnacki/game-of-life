package org.varsum.game_of_life.domain;

/**
 * @author The Watchmaker
 */
public class BoardAggregateRoot {

    public void start() {
        Board board = new Board(10);
        board.setActiveCellAt(0,0);
        board.setActiveCellAt(0,1);
        board.setActiveCellAt(1,0);
        board.setActiveCellAt(2,0);
        board.setActiveCellAt(3,0);
        board.setActiveCellAt(4,0);

        for (int i = 0; i < 2; i++) {
            System.out.println("----- run " + (i + 1) + " -----");
            System.out.println(board.debugBoardMatrix());

            board = board.tickle();
        }
    }
}
