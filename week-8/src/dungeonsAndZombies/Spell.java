package dungeonsAndZombies;

public class Spell {
	private String name;
	private int damage;
	private int manaCost;
	private int castRange;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public int getManaCost() {
		return manaCost;
	}
	public void setManaCost(int manaCost) {
		this.manaCost = manaCost;
	}
	public int getCastRange() {
		return castRange;
	}
	public void setCastRange(int castRange) {
		this.castRange = castRange;
	}
	public Spell(String name, int damage, int manaCost, int castRange) {
		this.name = name;
		this.damage = damage;
		this.manaCost = manaCost;
		this.castRange = castRange;
	}
	@Override
	public String toString() {
		return name + ": damage:" + damage + ", manaCost:" + manaCost + ", castRange:" + castRange;
	}
	
}
