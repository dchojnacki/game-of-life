package org.varsum.game_of_life.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * @author The Watchmaker
 */
public class Board {
    // FIXME watchmaker make board "matrix" endless
    private final int boardMatrixSize;
    private Set<Cell> activeCells;


    public Board(int boardMatrixSize) {
        this.boardMatrixSize = boardMatrixSize;
        this.activeCells = new HashSet<>(boardMatrixSize * boardMatrixSize);
    }

    public void setActiveCellAt(int x, int y) {
        activeCells.add(Cell.at(x, y));
    }

    public boolean isCellActiveAt(int x, int y) {
        return activeCells.contains(Cell.at(x, y));
    }

    public int getActiveNeighbourCellsCount(int x, int y) {
        int startX = calculateStartIndex(x);
        int endX = calculateEndIndex(x);
        int startY = calculateStartIndex(y);
        int endY = calculateEndIndex(y);

        int count = 0;
        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                if (i == x && j == y){
                    continue;
                }
                if (activeCells.contains(Cell.at(i, j))){
                    count++;
                }
            }
        }

        return count;
    }

    private int calculateEndIndex(int x) {
        return x != boardMatrixSize - 1 ? x + 1 : boardMatrixSize - 1;
    }

    private int calculateStartIndex(int x) {
        return x != 0 ? x - 1 : 0;
    }


    public Board tickle() {
        Board board = new Board(this.boardMatrixSize);

        for (int i = 0; i < boardMatrixSize ; i++) {
            for (int j = 0; j < boardMatrixSize; j++) {
                int activeNeighbourCells = getActiveNeighbourCellsCount(i, j);
                boolean cellIsAlive = activeNeighbourCells == 3 || (activeNeighbourCells == 2 && isCellActiveAt(i, j));
                if (cellIsAlive) {
                    board.setActiveCellAt(i, j);
                }
            }
        }

        return board;
    }

    public int getBoardMatrixSize() {
        return boardMatrixSize;
    }
}
