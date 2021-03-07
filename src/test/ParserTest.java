package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import treasure_map.Adventurer;
import treasure_map.Map;
import treasure_map.Parser;

class ParserTest {

	@Test
	void readFileTest() {
		Parser p = new Parser();
		
		p.readFile("TestSim.txt");
		assertEquals("C - 6 - 7\nM - 1 - 0\nM - 2 - 1\nT - 0 - 3 - 2\nT - 1 - 3 - 3\nA - Lara - 1 - 1 - S - AADADAGGA\n", 
		p.getData(), "readFile : map readed incorrectly");
	}
	
	@Test
	void createMapTest() {
		Parser p = new Parser();
		Map m = new Map(6,7);
		
		//test with two map size described 
		p.setData("C - 6 - 7\nC - 5 - 5\nM - 1 - 0\nM - 2 - 1\nT - 0 - 3 - 2\nT - 1 - 3 - 3\nA - Lara - 1 - 1 - S - AADADAGGA\n");
		assertEquals(null, p.createMap(), "createMap should return null if two map size are described in the datas");
		
		//test with an incorrect map size
		p.setData("C - 6\nM - 1 - 0\nT - 0 - 3 - 2\nA - Lara - 1 - 1 - S - AADADAGGA\n");
		assertEquals(null, p.createMap(), "createMap should return null if an incorrect map size is described in the datas");
		
		//test with an incorrect field
		p.setData("C - 6 - 7\nM - 1\nT - 0 - 3 - 2\nA - Lara - 1 - 1 - S - AADADAGGA\n");
		assertEquals(null, p.createMap(), "createMap should return null if an incorrect field is described in the datas");
		
		//test with an incorrect treasure
		p.setData("C - 6 - 7\nM - 1 - 0\nT - 0 - 3\nA - Lara - 1 - 1 - S - AADADAGGA\n");
		assertEquals(null, p.createMap(), "createMap should return null if an incorrect treasure is described in the datas");
		
		//test with an incorrect adventurer
		p.setData("C - 6 - 7\nM - 1 - 0\nT - 0 - 3 - 2\nA - Lara - 1 - 1 - AADADAGGA\n");
		assertEquals(null, p.createMap(), "createMap should return null if an incorrect adventurer is described in the datas");
			
		//test with a standard map
		p.setData("C - 6 - 7\nM - 1 - 0\nM - 2 - 1\nT - 0 - 3 - 2\nT - 1 - 3 - 3\nA - Lara - 1 - 1 - S - AADADAGGA\n");
		m.getContent()[0][1].setField("MOUNTAIN");
		m.getContent()[1][2].setField("MOUNTAIN");
		m.getContent()[3][0].setTreasures(2);
		m.getContent()[3][1].setTreasures(3);
		m.getContent()[1][1].setResident(new Adventurer("Lara", "AADADAGGA", 'S', 1, 1));
		assertEquals(m.toString(), p.createMap().toString(), "createMap : Error in creation of a standard map");
		p.createMap();
	}
}
