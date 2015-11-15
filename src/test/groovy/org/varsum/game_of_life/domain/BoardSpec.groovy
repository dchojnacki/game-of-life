package org.varsum.game_of_life.domain

import spock.lang.Specification

/**
 * @author The Watchmaker
 */
public class BoardSpec extends Specification{
    def sut;

    def setup() {
        sut = new Board(2);
    }

    def "should select cell as alive"() {
        when:
            sut.setActiveCellAt(1,1);
        then:
            sut.isCellActiveAt(1,1);
    }

    def "should select cell should not be alive"() {
        expect:
            !sut.isCellActiveAt(1,1);
    }

    def "should return non-negative number of active neighbours for cell"(){
        expect:
            sut.getActiveNeighbourCellsCount(1,1) >= 0;
    }

    def "should return all 3 active neighbours for cell"() {
        when:
            sut.setActiveCellAt(0,1);
            sut.setActiveCellAt(1,0);
            sut.setActiveCellAt(1,1);
        then:
            sut.getActiveNeighbourCellsCount(0,0) == 3;
    }

    // FIXME watchmaker add more tests here

    def "should return at most 8 active neighbours for cell"(){
        expect:
        sut.getActiveNeighbourCellsCount(1,1) <= 8;
    }
}
