package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import treasure_map.Adventurer;
import treasure_map.Map;

class MapTest {

	@Test
	void moveAdventurerTest() {
		Map mymap = new Map(6,6);
		Adventurer lara = new Adventurer("Lara", "", 'N', 0, 0);
		
		// testing north trespassing
		mymap.getContent()[0][0].setResident(lara);
		mymap.moveAdventurer(lara);
		assertEquals(0, lara.getPos_x(), "adventurer should stay at the same place if trying to quit the map");
		assertEquals(0, lara.getPos_y(), "adventurer should stay at the same place if trying to quit the map");
		assertEquals(mymap.getContent()[0][0].getResident(), lara, "adventurer should stay at the same place if trying to quit the map");
		
		// testing south trespassing
		mymap.getContent()[0][0].setResident(null);
		mymap.getContent()[5][0].setResident(lara);
		lara.setPos_y(5);
		lara.setDirection('S');
		mymap.moveAdventurer(lara);
		assertEquals(0, lara.getPos_x(), "adventurer should stay at the same place if trying to quit the map");
		assertEquals(5, lara.getPos_y(), "adventurer should stay at the same place if trying to quit the map");
		assertEquals(mymap.getContent()[5][0].getResident(), lara, "adventurer should stay at the same place if trying to quit the map");
		
		// testing west trespassing
		lara.setDirection('W');
		mymap.moveAdventurer(lara);
		assertEquals(0, lara.getPos_x(), "adventurer should stay at the same place if trying to quit the map");
		assertEquals(5, lara.getPos_y(), "adventurer should stay at the same place if trying to quit the map");
		assertEquals(mymap.getContent()[5][0].getResident(), lara, "adventurer should stay at the same place if trying to quit the map");
		
		// testing east trespassing
		mymap.getContent()[5][0].setResident(null);
		mymap.getContent()[5][5].setResident(lara);
		lara.setPos_x(5);
		lara.setDirection('E');
		mymap.moveAdventurer(lara);
		assertEquals(5, lara.getPos_x(), "adventurer should stay at the same place if trying to quit the map");
		assertEquals(5, lara.getPos_y(), "adventurer should stay at the same place if trying to quit the map");
		assertEquals(mymap.getContent()[5][5].getResident(), lara, "adventurer should stay at the same place if trying to quit the map");
		
		// testing normal north move
		mymap.getContent()[5][5].setResident(null);
		mymap.getContent()[3][3].setResident(lara);
		lara.setPos_x(3);
		lara.setPos_y(3);
		lara.setDirection('N');
		mymap.moveAdventurer(lara);
		assertEquals(null, mymap.getContent()[3][3].getResident(), "adventurer should have quit his/her old location");
		assertEquals(lara, mymap.getContent()[2][3].getResident(), "adventurer should be in the new location");
		assertEquals(2, lara.getPos_y(), "adventurer should have changed his/her y coordinate");
		assertEquals(3, lara.getPos_x(), "adventurer should not have changed his/her x coordinate");
		
		// testing normal south move
		mymap.getContent()[2][3].setResident(null);
		mymap.getContent()[3][3].setResident(lara);
		lara.setPos_x(3);
		lara.setPos_y(3);
		lara.setDirection('S');
		mymap.moveAdventurer(lara);
		assertEquals(null, mymap.getContent()[3][3].getResident(), "adventurer should have quit his/her old location");
		assertEquals(lara, mymap.getContent()[4][3].getResident(), "adventurer should be in the new location");
		assertEquals(4, lara.getPos_y(), "adventurer should have changed his/her y coordinate");
		assertEquals(3, lara.getPos_x(), "adventurer should not have changed his/her x coordinate");
		
		// testing normal east move
		mymap.getContent()[4][3].setResident(null);
		mymap.getContent()[3][3].setResident(lara);
		lara.setPos_x(3);
		lara.setPos_y(3);
		lara.setDirection('E');
		mymap.moveAdventurer(lara);
		assertEquals(null, mymap.getContent()[3][3].getResident(), "adventurer should have quit his/her old location");
		assertEquals(lara, mymap.getContent()[3][4].getResident(), "adventurer should be in the new location");
		assertEquals(3, lara.getPos_y(), "adventurer should not have changed his/her y coordinate");
		assertEquals(4, lara.getPos_x(), "adventurer should have changed his/her x coordinate");
		
		// testing normal west move
		mymap.getContent()[3][4].setResident(null);
		mymap.getContent()[3][3].setResident(lara);
		lara.setPos_x(3);
		lara.setPos_y(3);
		lara.setDirection('O');
		mymap.moveAdventurer(lara);
		assertEquals(null, mymap.getContent()[3][3].getResident(), "adventurer should have quit his/her old location");
		assertEquals(lara, mymap.getContent()[3][2].getResident(), "adventurer should be in the new location");
		assertEquals(3, lara.getPos_y(), "adventurer should not have changed his/her y coordinate");
		assertEquals(2, lara.getPos_x(), "adventurer should have changed his/her x coordinate");
		
		//final test
		int lara_count = 0;
		for (int i = 0; i < 6 ; i++)
		{
			for (int j = 0; j < 6; j++)
			{
				if (mymap.getContent()[i][j].getResident() != null && mymap.getContent()[i][j].getResident().equals(lara))
					lara_count++;
			}
		}
		assertEquals(1, lara_count, "There should be only one adventurer on the map !");
	}
	
	@Test	
	void playTurnTest()
	{
		Map mymap = new Map(6,6);
		Adventurer lara = new Adventurer("Lara", "", 'N', 0, 0);
		Adventurer tom = new Adventurer("Tom", "", 'N', 3, 3);
		Adventurer loic = new Adventurer("Loic", "", 'N', 5, 0);
		ArrayList<Adventurer> adventurers = new ArrayList<Adventurer>();
		adventurers.add(lara);
		adventurers.add(tom);
		adventurers.add(loic);
		mymap.setAdventurers(adventurers);
		mymap.getContent()[0][0].setResident(lara);
		mymap.getContent()[3][3].setResident(tom);
		mymap.getContent()[5][0].setResident(loic);
		
		// test turn with no action
		assertFalse(mymap.playTurn(), "on turn with no actions, return should be false");
		assertEquals(1, mymap.getTurn(), "We should be now at turn 1");
		
		//test basic turn ( three players with one action each )
		mymap.setTurn(0);
		lara.setPath("A");
		tom.setPath("G");
		loic.setPath("R");
		assertTrue(mymap.playTurn(), "when adventurers act playturn() sould return true");
		assertFalse(mymap.playTurn(), "there is no action left so playturn() should return false");
		
		// test multiples turns (three players with three actions each)
		mymap.setTurn(0);
		lara.setPath("AAA");
		tom.setPath("GAA");
		loic.setPath("RAG");
		for (int i=0 ; i < 3 ; i++)
			assertTrue(mymap.playTurn(), "when adventurers act playturn() sould return true");
		assertFalse(mymap.playTurn(), "there is no action left so playturn() should return false");
		
		// test multiples turns with different length of sequences ( 5 actions / 3 actions / 1 action for each player )
		mymap.setTurn(0);
		lara.setPath("AAAAA");
		tom.setPath("GAAAG");
		loic.setPath("RGAAG");
		for (int i = 0; i < 5; i++)
			assertTrue(mymap.playTurn(), "when adventurers act playturn() sould return true");
		assertFalse(mymap.playTurn(), "there is no action left so playturn() should return false");
	}
	
	@Test
	void toStringTest() {
		Map mymap = new Map(3,4);
		Adventurer lara = new Adventurer("Lara", "", 'S', 3, 0, 3);
		mymap.getContent()[3][0].setResident(lara);
		mymap.getContent()[0][1].setField("MOUNTAIN");
		mymap.getContent()[1][2].setField("MOUNTAIN");
		mymap.getContent()[3][1].setTreasures(2);
		
		String res = new String("");
		res += "C - 3 - 4\n";
		res += "M - 1 - 0\n";
		res += "M - 2 - 1\n";
		res += "T - 1 - 3 - 2\n";
		res += "A - Lara - 0 - 3 - S - 3\n";
		assertEquals(res, mymap.toString(), "ToString : map not equal to result expected");
	}
	
	@Test
	void displayMapTest() {
		Map mymap = new Map(6,5);
		Adventurer lara = new Adventurer("Lara", "", 'N', 1, 2);
		Adventurer tom = new Adventurer("Tom", "", 'N', 3, 4);
		mymap.getContent()[1][2].setResident(lara);
		mymap.getContent()[4][3].setResident(tom);
		mymap.getContent()[2][3].setField("MOUNTAIN");
		mymap.getContent()[4][5].setTreasures(3);
		mymap.getContent()[1][2].setTreasures(2);
		
		String res = new String("");
		res += ".            .            .            .            .            .            \n";
		res	+= ".            .            A(Lara)T(2)  .            .            .            \n";
		res += ".            .            .            M            .            .            \n";
		res	+= ".            .            .            .            .            .            \n";
		res += ".            .            .            A(Tom)       .            T(3)         \n";
		
		assertEquals(res, mymap.displayMap(), "Error : alternate map invalid");
	}

}
