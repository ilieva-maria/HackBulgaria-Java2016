package dungeonsAndZombies;

public class Enemy extends Player{
	private int damage;
	
	public Enemy(int health, int mana, int damage) {
		super(health, mana);
		this.setDamage(damage);
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	
}
