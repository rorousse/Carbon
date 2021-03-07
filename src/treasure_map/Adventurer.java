package treasure_map;

public class Adventurer {
	private String name;
	private String path;
	private int treasures;
	private char direction;
	private int pos_x;
	private int pos_y;
	
	/**
	 * ----------------------------------- Constructors --------------------------
	 */
	
	public Adventurer(){
		this.treasures = 0;
	}
	;
	
	public Adventurer(int treasures) {
		this.treasures = treasures;
	}
	
	public Adventurer(String name, String path, char direction, int y, int x) {
		super();
		this.path = path;
		this.name = name;
		this.direction = direction;
		this.pos_x = x;
		this.pos_y = y;
		this.treasures = 0;
	}
	
	public Adventurer(String name, String path, char direction, int y, int x, int treasures) {
		super();
		this.path = path;
		this.name = name;
		this.direction = direction;
		this.pos_x = x;
		this.pos_y = y;
		this.treasures = treasures;
	}
	
	/**
	 * ----------------------------------- Getters / Setters --------------------------
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getTreasures() {
		return treasures;
	}

	public void setTreasures(int treasures) {
		this.treasures = treasures;
	}
	
	public void addTreasure()
	{
		this.treasures++;
	}

	public char getDirection() {
		return direction;
	}

	public void setDirection(char direction) {
		this.direction = direction;
	}
	
	public int getPos_x() {
		return pos_x;
	}

	public void setPos_x(int pos_x) {
		this.pos_x = pos_x;
	}

	public int getPos_y() {
		return pos_y;
	}

	public void setPos_y(int pos_y) {
		this.pos_y = pos_y;
	}

	/**
	 * ----------------------------------- Methods --------------------------
	 */
	
	/*
	 ** Change the orientation of an adventurer
	 ** Param : A char containing the first letter of Right or Left
	 */
	public void turn(char command)
	{
		switch(this.direction) {
		case 'N':
			this.direction = ( command == 'D') ? 'E' : 'O';
			break;
		case 'S':
			this.direction = ( command == 'D') ? 'O' : 'E';
			break;
		case 'O' :
			this.direction = ( command == 'D') ? 'N' : 'S';
			break;
		case 'E' :
			this.direction = ( command == 'D') ? 'S' : 'N';
			break;
		}
	}
	
	/*
	 ** Return a String describing the adventurer, used for the output of the programm
	 */
	public String toString()
	{
		return ("A - " + this.name + " - " + this.pos_x + " - " + this.pos_y 
		+ " - " + this.direction + " - " + this.treasures);
	}
	
}
