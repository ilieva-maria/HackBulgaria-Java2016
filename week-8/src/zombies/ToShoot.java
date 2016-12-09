package zombies;

public class ToShoot extends Weapon {

	public ToShoot(int damage, int durability) {
		super(damage, durability);
	}

	public int hit() {
		if (durability > 0) {
			durability--;
			return damage;
		}
		return 1;
	}
}
