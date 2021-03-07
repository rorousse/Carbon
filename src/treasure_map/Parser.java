package treasure_map;

import java.io.*;
import java.nio.file.*;

public class Parser {

	String data;
	
	/**
	 * ----------------------------------- Constructors --------------------------
	 */
	
	public Parser() {
		this.data = new String("");
	};
	
	/**
	 * ----------------------------------- Getters / Setters --------------------------
	 */

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	/**
	 * -------------------------------- Methods ------------------------------------------
	 */
	
	/*
	 ** read a file given in arguments and extract from it the datas needed to create a map
	 */
	public void readFile(String filename)
	{
		String s;
		Path map_file = Paths.get(filename);
	    InputStream input = null;
	    try {
	        input = Files.newInputStream(map_file);
	        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	        while ((s = reader.readLine()) != null)
	        	this.data += s + "\n";
	        input.close();
	    } catch (IOException e) {
	        System.out.println(e);
	    }
	}

	/*
	 ** Create a map from the string of characters extracted from a file
	 */
	public Map createMap()
	{
		Map t_map = null;
		Adventurer adv;
		int coos_x;
		int coos_y;
		int treasures;
		char init;
		String tokens[];
		
		String splitted[] = this.data.split("\n");
		for (int i = 0; i < splitted.length; i++)
		{
			tokens = splitted[i].split(" - ");
			init = tokens[0].charAt(0);
			if (init == 'C')
			{
				if (tokens.length < 3)
					return null;
				coos_x = Integer.parseInt(tokens[1]);
				coos_y = Integer.parseInt(tokens[2]);
				if (t_map != null)
					return null; 
				else
						t_map = new Map(coos_x, coos_y);
			}
			else if (init == 'M')
			{
				if (tokens.length < 3)
					return null;
				coos_x = Integer.parseInt(tokens[1]);
				coos_y = Integer.parseInt(tokens[2]);
				t_map.getContent()[coos_y][coos_x].setField("MOUNTAIN");
			}
			else if (init == 'T')
			{
				if (tokens.length < 4)
					return null;
				coos_x = Integer.parseInt(tokens[1]);
				coos_y = Integer.parseInt(tokens[2]);
				treasures = Integer.parseInt(tokens[3]);
				t_map.getContent()[coos_y][coos_x].setTreasures(treasures);
			}
			else if (init == 'A')
			{
				if (tokens.length < 6)
					return null;
				coos_x = Integer.parseInt(tokens[2]);
				coos_y = Integer.parseInt(tokens[3]);
				adv = new Adventurer(tokens[1], tokens[5], tokens[4].charAt(0), coos_y, coos_x);
				t_map.addAdventurer(adv);
			}
		}
		return t_map;
	}
	
	/*
	 ** Print a String describing a map into a txt file
	 */
	void printMapIntoFile(Map t_map)
	{
		 try {
		      PrintWriter fichier = new PrintWriter(new FileWriter("Simulation.txt"));
		      fichier.print(t_map.toString());
		      fichier.close();
		    } catch (Exception e) {
		      e.printStackTrace();
		    }     
	}
}
