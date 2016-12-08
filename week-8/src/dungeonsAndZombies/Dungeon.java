package dungeonsAndZombies;

import java.util.Random;

public class Dungeon {
	private char[][] map;
	Hero hero;
	private int rowPosition;
	private int colPosition;
	private enum TypeOfMove {UP, DOWN, LEFT, RIGHT}
	
	
	Spell[] spels = {new Spell("Expeliarmus", 10, 5, 2), new Spell("Confusion", 5, 3, 8)};
	Weapon[] weapons = {new Weapon("Sword", 8), new Weapon("Bat", 11)};
	

	public boolean heroAttack(String by) {
		if (hero.attack(by) > 0) {
			if (hero.getSpell() != null) {
				if(hero.canCast(hero.getSpell()) && inRange()){
					return true;
				} 
			}
		}
		return false;
	}
	
	private boolean inRange(){
		boolean isInRange = false;
		int range = hero.getSpell().getCastRange();
		for (int i = rowPosition - range; i <= rowPosition + range; i++){
			for (int j = colPosition - range; j <= colPosition + range; j++){
				if (inTheMap(i, j)) {
					if (map[i][j] == 'E') {
						isInRange = true;
						break;
					}
				}
			}
		}
		return isInRange;
	}
	
	private void pickTreasure() {
		Random r = new Random();
		int n = r.nextInt(4);
		switch (n) {
		case 0:
			Random randSpell = new Random();
			int indexOfSpell = randSpell.nextInt(spels.length);
			hero.learnSpell(spels[indexOfSpell]);
			break;
		case 1:
			Random randWeapon = new Random();
			int indexOfWeapon = randWeapon.nextInt(weapons.length);
			hero.equip(weapons[indexOfWeapon]);
			break;
		case 2:
			Random randMana = new Random();
			int manaPoints = randMana.nextInt(100);
			hero.takeMana(manaPoints);
			break;
		case 3:
			Random randHealing = new Random();
			int healingPoints = randHealing.nextInt(100);
			hero.takeHealing(healingPoints);
			break;
		}
	}

	public Dungeon(char[][] map) {
		this.map = map;
	}
	
	public void printMap() {
		for(int i = 0; i < map.length; i++){
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	
	public boolean spawn(Hero hero) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if(map[i][j] == 'S') {
					map[i][j] = 'H';
					rowPosition = i;
					colPosition = j;
					this.hero = hero;
					return true;
				}
			}
		}
		return false;
	}
	

	public boolean inTheMap(int i, int j) {
		return i < map.length && j < map[0].length;
	}
	
	public void makeMove(int i, int j) {
		if (!inTheMap(i, j)) {
			return;
		}
		char targetCell = map[i][j];
		if(targetCell == '#') {
			System.out.println("Cannot complete the move. There is an obsticle in the way!");
			return;
		} else if(targetCell  == 'E') {
			System.out.println("A figth has started!");
			return;
		} else if(targetCell == 'T') {
			System.out.println("Found treasure!");
			pickTreasure();
			return;
		} else if(targetCell == '.') {
			rowPosition = i;
			colPosition = j;
			return;
		} else {
			throw new RuntimeException("Incorrect move: " + i + " " + j);
		}
	}
	
	public void moveHero(String direction) {
		TypeOfMove move = TypeOfMove.valueOf(direction.toUpperCase());
		switch(move) {
		case LEFT:
			makeMove(rowPosition, colPosition - 1);
			break;
		case RIGHT:
			makeMove(rowPosition, colPosition + 1);
			break;
		case UP:
			makeMove(rowPosition - 1, colPosition);
			break;
		case DOWN:
			makeMove(rowPosition + 1, colPosition);
			break;
			
		}
	}
}
