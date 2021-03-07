package treasure_map;

enum Field {PLAIN, MOUNTAIN};

public class Zone {
	private Adventurer resident;
	private int treasures;
	private Field field;
	private int x;
	private int y;
	
	/**
	 * ----------------------------------- Constructors --------------------------
	 */
	
	public Zone() {
		this.field = Field.PLAIN;
		this.treasures = 0;
		this.resident = null;
	}
	
	public Zone(int y, int x)
	{
		this.y = y;
		this.x = x;
		this.field = Field.PLAIN;
		this.resident = null;
	}

	public Zone(int y, int x, int treasures)
	{
		this.y = y;
		this.x = x;
		this.treasures = treasures;
		this.field = Field.PLAIN;
		this.resident = null;
	}
	
	public Zone(Adventurer resident, String field, int treasures)
	{
		this.resident = resident;
		this.field = Field.valueOf(field);
		this.treasures = treasures;
	}
	
	/**
	 * --------------------------------- Getters/Setters ------------------------------------
	 */

	public Adventurer getResident() {
		return resident;
	}

	public void setResident(Adventurer resident) {
		this.resident = resident;
	}

	public int getTreasures() {
		return treasures;
	}

	public void setTreasures(int treasures) {
		this.treasures = treasures;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}
	
	public void setField(String field)
	{
		this.field = Field.valueOf(field);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	/**
	 * -------------------------------- Methods ------------------------------------------
	 */
	
	/*
	 ** Return true if an adventurer can move to this zone
	 ** no else
	 */
	public boolean isWalkable()
	{
		if (this.field == Field.MOUNTAIN || this.resident != null)
			return false;
		return true;
	}
	
	/*
	 ** loot one treasure of a zone by an adventurer
	 */
	public void loot()
	{
		if (this.treasures > 0)
		{
			this.treasures--;
			this.resident.addTreasure();
		}
	}

	/*
	 ** Return a String describing the field of this zone
	 */
	public String printField()
	{
		String ret = new String("");
		if (this.field == Field.MOUNTAIN)
			ret += ("M - " + this.x + " - " + this.y + "\n");
		return ret;
	}
	
	/*
	 ** Return a String describing the number of treasures of this zone
	 */
	public String printTreasures()
	{
		String ret = new String("");
		if (this.treasures > 0)
			ret += "T - " + this.x + " - " + this.y + " - " + this.treasures + "\n";
		return ret;
	}
	
	/*
	 ** Return a String describing the adventurer present on this zone
	 */
	public String printResident()
	{
		String ret = new String("");
		if (this.resident != null)
			ret += ("A - " + this.resident.getName() + " - " + this.resident.getPos_x() + " - " + this.resident.getPos_y() + " - " 
			+ this.resident.getDirection() + " - " + this.resident.getTreasures() + "\n");
		return ret;
	}
	
	/*
	 ** Return a String describing the whole zone
	 */
	public String toString()
	{
		String ret = new String(printField() + printTreasures() + printResident());
		return ret;
	}
}
