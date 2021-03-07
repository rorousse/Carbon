package treasure_map;
import java.util.ArrayList;

public class Map {
	private int x_max;
	private int y_max;
	private int turn;
	private Zone[][] content;
	ArrayList<Adventurer> adventurers;
	
	/**
	 * ----------------------------------- Constructors --------------------------
	 */
	
	public Map(int x_max, int y_max) {
		super();
		this.x_max = x_max;
		this.y_max = y_max;
		this.content = new Zone[y_max][x_max];
		this.adventurers = new ArrayList<Adventurer>();
		this.turn = 0;
		for (int i = 0; i < this.y_max; i++)
		{
			for (int j = 0; j < this.x_max; j++)
			{
				this.content[i][j] = new Zone(i, j);
			}
		}
	}

	/**
	 * --------------------------------- Getters / Setters ------------------------------------
	 */
	
	public int getX_max() {
		return x_max;
	}

	public void setX_max(int x_max) {
		this.x_max = x_max;
	}

	public int getY_max() {
		return y_max;
	}

	public void setY_max(int y_max) {
		this.y_max = y_max;
	}

	public Zone[][] getContent() {
		return content;
	}

	public void setContent(Zone[][] content) {
		this.content = content;
	}
	
	public ArrayList<Adventurer> getAdventurers() {
		return adventurers;
	}

	public void setAdventurers(ArrayList<Adventurer> adventurers) {
		this.adventurers = adventurers;
	}
	
	public void addAdventurer(Adventurer adv)
	{
		this.adventurers.add(adv);
		this.getContent()[adv.getPos_y()][adv.getPos_x()].setResident(adv);
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	/**
	 * -------------------------------- Methods ------------------------------------------
	 */
	
	/*
	 * Move an adventurer forward, based on his orientation
	 * If something make the move impossible ( a mountain, another adventurer... )
	 * the adventurer will just stay in place
	 */
	public void moveAdventurer(Adventurer adventurer)
	{
		int new_x;
		int new_y;
		
		new_x = adventurer.getPos_x();
		new_y = adventurer.getPos_y();
		Zone currentZone = this.content[new_y][new_x];
		switch(adventurer.getDirection()) {
		case 'N':
			new_y--;
			break;
		case 'S':
			new_y++;
			break;
		case 'O' :
			new_x--;
			break;
		case 'E' :
			new_x++;
			break;
		}
		if (new_x >= 0 && new_y >= 0 && new_x < this.x_max && new_y < this.y_max)
		{
			Zone next_zone = this.content[new_y][new_x];
			if (next_zone.isWalkable())
			{	
				next_zone.setResident(adventurer);
				currentZone.setResident(null);
				adventurer.setPos_x(new_x);
				adventurer.setPos_y(new_y);
				next_zone.loot();
			}
		}
	}
	
	/*
	 * Play a "turn" on the map, wich means each adventurer of the map will play one action
	 * Return true if and adventurer actually took action
	 * Retunr false if no action was taken, wich means all adevnturers have ended their sequences and the
	 * programme can finish 
	 */
	
	public boolean playTurn()
	{
		char command;
		boolean action;
		
		action = false;
		for(Adventurer adv : adventurers)
		{
			if (this.turn < adv.getPath().length())
			{
				command = adv.getPath().charAt(this.turn);
				if ( command == 'A')
					moveAdventurer(adv);
				else if(command == 'D' || command == 'G')
					adv.turn(command);
				action = true;
			}
		}
		this.turn++;
		return action;
	}
	
	/*
	 * Launch the method playturn() until there is nothing left to do
	 * see the method playturn() below for more informations
	 */
	public void play()
	{
		while (playTurn());
	}
	
	/*
	 * Describes all the map : Dimensions, mountains, adventurers, treasures
	 * Return it in the form of a string wich will be used for the output
	 * of the programm
	 */
	public String toString()
	{
		String str = new String("C - " + this.x_max + " - " + this.y_max + "\n");
		for (int i = 0; i < this.y_max; i++)
		{
			for (int j = 0; j < this.x_max; j++)
			{
				str += this.content[i][j].printField();
			}
		}
		for (int i = 0; i < this.y_max; i++)
		{
			for (int j = 0; j < this.x_max; j++)
			{
				str += this.content[i][j].printTreasures();
			}
		}
		for (int i = 0; i < this.y_max; i++)
		{
			for (int j = 0; j < this.x_max; j++)
			{
				str += this.content[i][j].printResident();
			}
		}
		return str;
	}
	
	/*
	 ** Alternative description of the map
	 ** added a "padding" system to harmonize a bit the columns in the output 
	 */
	public String displayMap()
	{
		String ret = new String("");
		Zone current_zone;
		int padding;
		
		for (int i = 0; i < this.y_max; i++)
		{
			for (int j = 0; j < this.x_max; j++)
			{ 
				padding = 12; // change this value to change the length of the padding
				current_zone = this.content[i][j];
				if (current_zone.getResident() == null && current_zone.getTreasures() == 0)
				{
					if (current_zone.getField() == Field.PLAIN)
						ret += ".";
					else
						ret += "M";
					padding--;
				}
				else
				{	
					if (current_zone.getResident() != null)
					{
						ret += ("A(" + current_zone.getResident().getName() + ")");
						padding -= ("A(" + current_zone.getResident().getName() + ")").length();
					}
					if (current_zone.getTreasures() != 0)
					{
						ret += ("T(" + current_zone.getTreasures() + ")");
						padding -= ("T(" + current_zone.getTreasures() + ")").length();
					}
				}
				while (padding >=0 )
				{
					ret += " ";
					padding--;
				}
			}
			ret += "\n";
		}
		return ret;
	}
}
