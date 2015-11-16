package org.varsum.game_of_life.domain

import spock.lang.Specification

/**
 * @author The Watchmaker
 */
public class BoardSpec extends Specification{
    def sut

    def setup() {
        sut = new Board(4)
    }

    def "should select cell as alive"() {
        when:
            sut.setActiveCellAt(1,1)
        then:
            sut.isCellActiveAt(1,1)
    }

    def "should select cell should not be alive"() {
        expect:
            !sut.isCellActiveAt(1,1)
    }

    def "should return non-negative number of active neighbours for cell"(){
        expect:
            sut.getActiveNeighbourCellsCount(1,1) >= 0
    }

    def "should return all 3 active neighbours for cell"() {
        when:
            sut.setActiveCellAt(0,1)
            sut.setActiveCellAt(1,0)
            sut.setActiveCellAt(1,1)
        then:
            sut.getActiveNeighbourCellsCount(0,0) == 3
    }

    def "should return at most 8 active neighbours for cell"(){
        when:
            sut.setActiveCellAt(0,0)
            sut.setActiveCellAt(0,1)
            sut.setActiveCellAt(0,2)
            sut.setActiveCellAt(0,3)
            sut.setActiveCellAt(1,0)
            sut.setActiveCellAt(1,2)
            sut.setActiveCellAt(1,3)
            sut.setActiveCellAt(2,0)
            sut.setActiveCellAt(2,1)
            sut.setActiveCellAt(2,2)
            sut.setActiveCellAt(2,3)
            sut.setActiveCellAt(3,0)
            sut.setActiveCellAt(3,1)
            sut.setActiveCellAt(3,2)
            sut.setActiveCellAt(3,3)
        then:
            sut.getActiveNeighbourCellsCount(1,1) == 8
    }

    def "tickle should return new board object"(){
        when:
            Board newBoard = sut.tickle()
        then:
            sut != newBoard
    }

    def "tickle should return new board object with the same size"(){
        when:
            Board newBoard = sut.tickle()
        then:
            sut.getBoardMatrixSize() == newBoard.getBoardMatrixSize()
    }

    def "cell dies because of under-population"(){
        when:
            sut.setActiveCellAt(0,0)
            Board newBoard = sut.tickle()
        then:
            !newBoard.isCellActiveAt(0,0)
    }

    def "cell dies because of over-population"(){
        when:
            sut.setActiveCellAt(0,0)
            sut.setActiveCellAt(0,1)
            sut.setActiveCellAt(0,2)
            sut.setActiveCellAt(1,0)
            sut.setActiveCellAt(1,2)
            sut.setActiveCellAt(2,0)
            sut.setActiveCellAt(2,1)
            sut.setActiveCellAt(2,2)

            Board newBoard = sut.tickle()
        then:
            !newBoard.isCellActiveAt(1,1)
    }


    def "cell still lives because of 2 active neighbors"(){
        when:
            sut.setActiveCellAt(0,0)
            sut.setActiveCellAt(0,1)
            sut.setActiveCellAt(1,0)

            Board newBoard = sut.tickle()
        then:
            newBoard.isCellActiveAt(0, 0)
    }

    def "cell still lives because of 3 active neighbors"(){
        when:
            sut.setActiveCellAt(0,0)
            sut.setActiveCellAt(0,1)
            sut.setActiveCellAt(0,2)
            sut.setActiveCellAt(1,1)

            Board newBoard = sut.tickle()
        then:
            newBoard.isCellActiveAt(1, 1)
    }


    def "cell reborn cause of exactly 3 active neighbors"(){
        when:
            sut.setActiveCellAt(0,0)
            sut.setActiveCellAt(0,1)
            sut.setActiveCellAt(1,0)

            Board newBoard = sut.tickle()
        then:
            newBoard.isCellActiveAt(1,1)
    }
}
