package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import treasure_map.Adventurer;

class AdventurerTest {

	@Test
	void toStringTest() {
		Adventurer adv = new Adventurer("Lara", "", 'E', 2, 1);
		String lara = new String("A - Lara - 1 - 2 - E - 0");
		assertEquals(adv.toString(), lara, "Error in adventurer toString");
	}

	@Test
	void turnTest()
	{
		Adventurer adv = new Adventurer("Lara", "", 'N', 0, 0);
		adv.turn('D');
		assertEquals( 'E', adv.getDirection(), "Turning right from north should go to east");
		adv.turn('D');
		assertEquals( 'S', adv.getDirection(), "Turning right from east should go to south");
		adv.turn('D');
		assertEquals( 'O', adv.getDirection(), "Turning right from south should go to west");
		adv.turn('D');
		assertEquals( 'N', adv.getDirection(), "Turning right from west should go to north");
		adv.turn('G');
		assertEquals('O', adv.getDirection(), "Turning left from north should go to west");
		adv.turn('G');
		assertEquals( 'S', adv.getDirection(),"Turning left from west should go to south");
		adv.turn('G');
		assertEquals('E', adv.getDirection(), "Turning left from south should go to east");
		adv.turn('G');
		assertEquals( 'N', adv.getDirection(), "Turning left from east should go to north");
	}
}
