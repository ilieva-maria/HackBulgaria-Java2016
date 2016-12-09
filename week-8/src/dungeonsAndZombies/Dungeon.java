package dungeonsAndZombies;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Dungeon {
	private char[][] map;
	Hero hero;
	private Coordinates heroPosition;
	private Map<Coordinates, Enemy> enemies = new HashMap<>();

	private enum TypeOfMove {
		UP, DOWN, LEFT, RIGHT
	}
	
	private void placeEnemies() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if(map[i][j] == 'E') {
					Random rand = new Random();
					int health = rand.nextInt(100);
					int mana = rand.nextInt(100);
					int damage = rand.nextInt(20);
					enemies.put(new Coordinates(i, j), new Enemy(health, mana, damage));
				}
			}
		}
	}

	Spell[] spells = { new Spell("Expeliarmus", 10, 5, 2), new Spell("Confusion", 5, 3, 8) };
	Weapon[] weapons = { new Weapon("Sword", 8), new Weapon("Bat", 11) };

	public boolean heroAttack(String by) {
		boolean canAttack = false;
		if (hero.attack(by) > 0) {
			canAttack = true;
			if (hero.getSpell() == null || !inRange()) {
				canAttack = false;
			} 
		}
		return canAttack;
	}

	private boolean inRange() {
		boolean isInRange = false;
		int range = hero.getSpell().getCastRange();
		for (int i = heroPosition.getRowPosition() - range; i <= heroPosition.getRowPosition() + range; i++) {
			for (int j = heroPosition.getColPosition() - range; j <= heroPosition.getColPosition() + range; j++) {
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

	private boolean shouldPickSpell(Spell spell) {
		boolean shouldPick = false;
		Spell currentSpell = hero.getSpell();
		int powerOfCurrentSpell = currentSpell.getDamage() * currentSpell.getCastRange();
		int powerOfSpell = spell.getDamage() * spell.getCastRange();
		if (powerOfSpell > powerOfCurrentSpell && spell.getManaCost() < currentSpell.getManaCost()) {
			shouldPick = true;
		}
		return shouldPick;
	}

	private boolean shouldPickWeapon(Weapon weapon) {
		boolean shouldPick = false;
		Weapon currentWeapon = hero.getWeapon();
		int powerOfCurrentWeapon = currentWeapon.getDamage();
		int powerOfWeapon = weapon.getDamage();
		if (powerOfWeapon > powerOfCurrentWeapon) {
			shouldPick = true;
		}
		return shouldPick;
	}

	private void pickTreasure() {
		Random r = new Random();
		int n = r.nextInt(4);
		switch (n) {
		case 0:
			Random randSpell = new Random();
			int indexOfSpell = randSpell.nextInt(spells.length);
			if (shouldPickSpell(spells[indexOfSpell])) {
				hero.learnSpell(spells[indexOfSpell]);
			}
			break;
		case 1:
			Random randWeapon = new Random();
			int indexOfWeapon = randWeapon.nextInt(weapons.length);
			if (shouldPickWeapon(weapons[indexOfWeapon])) {
				hero.equip(weapons[indexOfWeapon]);
			}
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
		placeEnemies();
	}

	public void printMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	public boolean spawn(Hero hero) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == 'S') {
					map[i][j] = 'H';
					heroPosition.setRowPosition(i);
					heroPosition.setColPosition(j);
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
		if (targetCell == '#') {
			System.out.println("Cannot complete the move. There is an obsticle in the way!");
			return;
		} else if (targetCell == 'E') {
			hero.takeMana(hero.getManaRegenRate());
			System.out.println("A figth has started!");

			//Fight fight = new Fight(hero, enemy);
			return;
		} else if (targetCell == 'T') {
			hero.takeMana(hero.getManaRegenRate());
			System.out.println("Found treasure!");
			heroPosition.setRowPosition(i);
			heroPosition.setColPosition(j);
			pickTreasure();
			return;
		} else if (targetCell == '.') {
			hero.takeMana(hero.getManaRegenRate());
			heroPosition.setRowPosition(i);
			heroPosition.setColPosition(j);
			return;
		} else {
			throw new RuntimeException("Incorrect move: " + i + " " + j);
		}
	}

	public void moveHero(String direction) {
		TypeOfMove move = TypeOfMove.valueOf(direction.toUpperCase());
		switch (move) {
		case LEFT:
			makeMove(heroPosition.getRowPosition(), heroPosition.getColPosition() - 1);
			break;
		case RIGHT:
			makeMove(heroPosition.getRowPosition(), heroPosition.getColPosition() + 1);
			break;
		case UP:
			makeMove(heroPosition.getRowPosition() - 1, heroPosition.getColPosition());
			break;
		case DOWN:
			makeMove(heroPosition.getRowPosition() + 1, heroPosition.getColPosition());
			break;

		}
	}
}
