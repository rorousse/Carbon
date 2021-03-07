package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import treasure_map.Adventurer;
import treasure_map.Zone;

class ZoneTest {

	@Test
	void isWalkableTest() {
		Zone zoneA = new Zone(null, "MOUNTAIN", 0 );
		Zone zoneB = new Zone(new Adventurer(), "PLAIN", 0 );
		Zone zoneC = new Zone(null, "PLAIN", 0 );
		
		assertFalse(zoneA.isWalkable(), "a zone with a mountain is walkable");
		assertFalse(zoneB.isWalkable(), "a zone with an adventurer is walkable");
		assertTrue(zoneC.isWalkable(), "cannot walk in an empty zone (should be walkable)");
	}
	
	@Test
	void testLoot() {
		Zone test_zone = new Zone(new Adventurer(0), "PLAIN", 4);
		
		test_zone.loot();
		assertEquals(3, test_zone.getTreasures(), "Incorrect numbers of treasures in zone after looting");
		assertEquals(1, test_zone.getResident().getTreasures(), 
		"Incorrect number of treasures carried by adventurer after looting)");
		test_zone.setTreasures(0);
		test_zone.loot();
		assertEquals(0, test_zone.getTreasures(), "Error : must have 0 treasures in an empty zone");
		assertEquals(1, test_zone.getResident().getTreasures(), 
		"Incorrect number of treasures carried by adventurer after looting an empty zone)");
	}
	
	@Test
	void printFieldTest()
	{
		Zone test_zone = new Zone(1,4,0);
		
		// test with nothing in the zone
		assertEquals("", test_zone.printField(), "empty zone should return an empty String");
		
		//test with one mountain in the zone
		test_zone.setTreasures(0);
		test_zone.setField("MOUNTAIN");
		assertEquals("M - 4 - 1\n", test_zone.printField(), "Zone should display a mountain");
	}
	
	@Test
	void printTreasuresTest()
	{
		Zone test_zone = new Zone(1,4,0);
		
		// test with nothing in the zone
		assertEquals("", test_zone.printTreasures(), "empty zone should return an empty String");
		
		//test with treasures in the zone
		test_zone.setTreasures(3);
		assertEquals("T - 4 - 1 - 3\n", test_zone.printTreasures(), "Zone should display treasures");
	}
	
	@Test
	void printAdventurer()
	{
		Zone test_zone = new Zone(1,4,0);
		Adventurer adv = new Adventurer("Lara", "", 'N', 1, 4, 3);
		
		// test with nothing in the zone
		assertEquals("", test_zone.toString(), "empty zone should return an empty String");
		
		// test with an adventurer in the zone
		test_zone.setResident(adv);
		assertEquals(("A - Lara - 4 - 1 - N - 3\n"), test_zone.printResident(),"Zone should display an aventurer");
	}
}
