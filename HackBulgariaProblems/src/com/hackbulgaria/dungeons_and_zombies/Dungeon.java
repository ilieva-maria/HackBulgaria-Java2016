package com.hackbulgaria.dungeons_and_zombies;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Dungeon {
	private char[][] map;
	Hero hero;
	private Coordinates heroPosition = new Coordinates(0, 0);
	private Coordinates enemyPosition;
	private Map<Coordinates, Enemy> enemies = new HashMap<>();
	private boolean reachedGateway = false;

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

	private boolean heroAttack(String by) {
		boolean canAttack = false;
		if (hero.attack(by) > 0) {
			canAttack = true;
			if (hero.getSpell() == null || enemyInRange() == null) {
				canAttack = false;
			} 
		}
		return canAttack;
	}
	
	public void heroAttack() {
		if(heroAttack("spell")){
//			Enemy enemy = enemyInRange();
//			Fight fight = new Fight(hero, enemy);
//			BFS way = new BFS(map, heroPosition, enemyPosition);
//			System.out.println(Arrays.toString(way.findWay().toArray()));
		}
	}

	private Enemy enemyInRange() {
		Enemy enemy = null;
		int range = hero.getSpell().getCastRange();
		for (int i = heroPosition.getRowPosition() - range; i <= heroPosition.getRowPosition() + range; i++) {
			for (int j = heroPosition.getColPosition() - range; j <= heroPosition.getColPosition() + range; j++) {
				if (inTheMap(i, j)) {
					if (map[i][j] == 'E') {
						enemyPosition = new Coordinates(i, j);
						enemy = enemies.get(enemyPosition);
						break;
					}
				}
			}
		}
		if (enemy == null) {
			System.out.println("Nothing in casting range " + hero.getSpell().getCastRange());
		}
		return enemy;
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
			Spell spellToPick = spells[indexOfSpell];
			if (hero.getSpell() == null || shouldPickSpell(spellToPick)) {
				hero.learnSpell(spells[indexOfSpell]);
				System.out.println("Learned new spell. Hero spell is " + hero.getSpell().toString());
			}
			break;
		case 1:
			Random randWeapon = new Random();
			int indexOfWeapon = randWeapon.nextInt(weapons.length);
			if (hero.getWeapon() == null || shouldPickWeapon(weapons[indexOfWeapon])) {
				hero.equip(weapons[indexOfWeapon]);
				System.out.println("Found new weapon. Hero weapon is " + hero.getWeapon().toString());
			}
			break;
		case 2:
			Random randMana = new Random();
			int manaPoints = randMana.nextInt(100);
			hero.takeMana(manaPoints);
			System.out.println("Found mana potion. Hero mana is " + hero.getMana());
			break;
		case 3:
			Random randHealing = new Random();
			int healingPoints = randHealing.nextInt(100);
			hero.takeHealing(healingPoints);
			System.out.println("Found health potion. Hero health is " + hero.getHealth());
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
		return i >= 0 && i < map.length && j >= 0 && j < map[0].length;
	}
	
	public boolean isWalkable(int i, int j) {
		List<String> chars = Arrays.asList("#", "E", "G");
		return inTheMap(i, j) && !chars.contains(map[i][j]);
	}
	
	private void moveTo(int i, int j){
		map[heroPosition.getRowPosition()][heroPosition.getColPosition()] = '.';
		heroPosition.setRowPosition(i);
		heroPosition.setColPosition(j);
		map[i][j] = 'H';
	}

	public boolean makeMove(int i, int j) {
		if (!inTheMap(i, j)) {
			return false;
		}
		char targetCell = map[i][j];
		if (targetCell == '#') {
			System.out.println("Cannot complete the move. There is an obsticle in the way!");
			return false;
		} else if (targetCell == 'E') {
			hero.takeMana(hero.getManaRegenRate());
			System.out.println("A figth has started!");
			moveTo(i,j);
			Fight fight = new Fight(hero, enemies.get(new Coordinates(i, j)));
			System.out.println(fight.fight(Math.abs(heroPosition.getRowPosition()-i) + Math.abs(heroPosition.getColPosition()-j)));
	
			return true;
		} else if (targetCell == 'T') {
			hero.takeMana(hero.getManaRegenRate());
			System.out.println("Found treasure!");
			moveTo(i, j);
			pickTreasure();
			return true;
		} else if (targetCell == '.') {
			hero.takeMana(hero.getManaRegenRate());
			moveTo(i, j);
			return true;
		} else if (targetCell == 'G') {
			System.out.println("The game is over!");
			reachedGateway = true;
			moveTo(i, j);
			return true;
		} else {
			throw new RuntimeException("Incorrect move: " + i + " " + j);
		}
	}
	
	public boolean reachedGateway() {
		return reachedGateway;
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
