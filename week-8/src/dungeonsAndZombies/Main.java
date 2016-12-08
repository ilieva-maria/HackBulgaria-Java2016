package dungeonsAndZombies;

public class Main {
	public static void main(String[] args) {
		Hero hero = new Hero("Bron", "Dragonslayer", 100, 100, 2);
		System.out.println(hero.knownAs());
		
		char[][] map = {
				{'S', '.', '#', '#', '.', '.', '.', '.', '.', 'T'},
				{'#', 'T', '#', '#', '.', '.', '#', '#', '#', '.'},
				{'#', '.', '#', '#', '#', 'E', '#', '#', '#', 'E'},
				{'#', '.', 'E', '.', '.', '.', '#', '#', '#', '.'},
				{'#', '#', '#', 'T', '#', '#', '#', '#', '#', 'G'}
		};
		
		Dungeon dungeon = new Dungeon(map);
		dungeon.spawn(hero);
		dungeon.printMap();
	}
}
