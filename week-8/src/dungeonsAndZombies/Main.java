package dungeonsAndZombies;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Hero hero = new Hero("Bron", "Dragonslayer", 100, 100, 2);
		System.out.println(hero.knownAs());
		List<String> directions = Arrays.asList("left", "right", "down", "up");
		char[][] map = {
				{'S', '.', '#', '#', '.', '.', '.', '.', '.', 'T'},
				{'#', 'T', '#', '#', '.', '.', '#', '#', '#', '.'},
				{'#', '.', '#', '#', '#', 'E', '#', '#', '#', 'E'},
				{'#', '.', 'E', '.', '.', '.', '#', '#', '#', '.'},
				{'#', '#', '#', 'T', '#', '#', '#', '#', '#', 'G'}
		};
		Scanner scanner = new Scanner(System.in);
		
		Dungeon dungeon = new Dungeon(map);
		dungeon.spawn(hero);
		dungeon.printMap();
		while (hero.isAlive() && !dungeon.reachedGateway()) {
			
			String direction;
			do {
				direction = scanner.nextLine();
			} while (!directions.contains(direction));
			
			dungeon.moveHero(direction);
			dungeon.printMap();
		}
		
		
//		dungeon.moveHero("down");
//		dungeon.moveHero("down");
//		dungeon.moveHero("down");
//		dungeon.moveHero("right"); 
//		dungeon.moveHero("down");
//		
//		dungeon.moveHero("right"); 
//		dungeon.moveHero("right");
//		dungeon.moveHero("up");
		
		scanner.close();
	}
}
