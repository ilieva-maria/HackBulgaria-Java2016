package com.hackbulgaria.dungeons_and_zombies;

public class Fight {
	Hero hero;
	Enemy enemy;
	Coordinates heroPosition;
	Coordinates enemyPosition;
	public Fight(Hero hero, Enemy enemy) {
		this.hero = hero;
		this.enemy = enemy;
	}
	
	public String fight(int rangeBetweenHeroAndEnemy) {
		
		String report = "A fight has started! Hero has " + hero.getHealth() + ", enemy has " + enemy.getHealth() + " HP.\n";
		while(hero.isAlive() && enemy.isAlive()) {
			int heroSpellDamage = hero.getSpell() == null ? 0 : hero.getSpell().getDamage();
			int heroWeaponDamage = hero.getWeapon() == null ? 0 : hero.getWeapon().getDamage();
			String heroAttackMethod = getBiggestDamage(heroSpellDamage, heroWeaponDamage);
			String enemyAttackMethod;
			if(rangeBetweenHeroAndEnemy > 1) {
				enemyAttackMethod = "Enemy could not attack that far.";
				rangeBetweenHeroAndEnemy--;
			} else {
				enemyAttackMethod = getBiggestDamage(0, enemy.getDamage());
			}
			
			int heroDamage = hero.attack(heroAttackMethod);
			enemy.takeDamage(heroDamage);
			report += "Hero attacked with " + heroAttackMethod + " for " + heroDamage + " damage. Enemy is at " + enemy.getHealth() + " HP.\n";
			if(!enemy.isAlive()) {
				report += "Enemy is dead! Our hero has " + hero.getHealth() + " HP.\n";
				break; 
			}
			hero.takeMana(hero.getManaRegenRate());
			int enemyDamage = enemy.attack(enemyAttackMethod);
			hero.takeDamage(enemyDamage);
			if(rangeBetweenHeroAndEnemy > 1) {
				report += "Enemy is too far away to do any damage. Enemy move 1 step closer to hero.\n";
			} else {
				report += "Enemy attacked with " + enemyAttackMethod + " for " + enemyDamage + " damage. "
						+ "Hero is at " + hero.getHealth() + " HP, " + hero.getMana() + " mana.\n";
			}
			if(!hero.isAlive()) {
				report += "Hero is dead! The enemy has won this battle.\n";
			}
		}
		return report;
	}
	
	private String getBiggestDamage(int damageSpell, int damageWeapon) {
		String attackType;
		int biggestDamage = Math.max(damageSpell, damageWeapon);
		if(biggestDamage == damageSpell) {
			attackType = "spell";
		} else {
			attackType = "weapon";
		}
		return attackType;
	}
	public static void main(String[] args) {
		
	}
}
