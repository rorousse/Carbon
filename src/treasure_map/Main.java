package treasure_map;

public class Main {
	public static void main(String[] args)
	{
		Map t_map;
		Parser mp = new Parser();

		mp.readFile(args[0]);
		t_map = mp.createMap();
		t_map.play();
		mp.printMapIntoFile(t_map);
	}
}


