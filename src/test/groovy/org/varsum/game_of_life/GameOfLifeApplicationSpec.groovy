package org.varsum.game_of_life

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification

@RunWith(SpringJUnit4ClassRunner)
@SpringApplicationConfiguration(classes = GameOfLifeApplication)
@WebAppConfiguration
public class GameOfLifeApplicationSpec extends Specification{

	@Test
	void contextLoads() {
	}

}
