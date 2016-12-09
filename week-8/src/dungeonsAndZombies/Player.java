package dungeonsAndZombies;

public class Player {
	private int health;
	private int maxHealth;
	private int mana;
	private int maxMana;
	private Weapon weapon;
	private Spell spell;
	private enum TypeOfAttack {SPELL,WEAPON}
	
	
	public Player(int health, int mana) {
		super();
		this.health = health;
		setMaxHealth(health);
		this.mana = mana;
		setMaxMana(mana);
	}
	
	public boolean isAlive() {
		return getHealth() > 0;
	}
	
	public boolean canCast(Spell spell) {
		return getMana() >= spell.getManaCost();
	}
	
	public void takeDamage(int damagePoints) {
		setHealth(damagePoints > getHealth() ? 0 : damagePoints);
	}
	
	public void takeHealing(int healingPoints) {
		if (isAlive()) {
			int desiredHealth = getHealth() + healingPoints;
			setHealth(desiredHealth > getMaxHealth() ? getMaxHealth() : desiredHealth);
		} else {
			System.out.println("The hero is already dead!");
		}
		
	}
	
	public void takeMana(int manaPoints) {
		int desiredMana = getMana() + manaPoints;
		setMana(desiredMana > getMaxMana() ? getMaxMana() : desiredMana); 
	}
	
	public void equip(Weapon weapon) {
		setWeapon(weapon);
	}

	public void learnSpell(Spell spell) {
		setSpell(spell);
	}
	
	public int attack(String by) {
		int damage = 0;
		TypeOfAttack typeOfAttack = TypeOfAttack.valueOf(by.toUpperCase());
		if (typeOfAttack.equals(TypeOfAttack.SPELL)){
			damage = canCast(spell) ? getSpell().getDamage() : 0;
		} else if (typeOfAttack.equals(TypeOfAttack.WEAPON)) {
			damage = getWeapon().getDamage();
		}
		return damage;
	}
	
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getMana() {
		return mana;
	}
	public void setMana(int mana) {
		this.mana = mana;
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	public int getMaxMana() {
		return maxMana;
	}
	public void setMaxMana(int maxMana) {
		this.maxMana = maxMana;
	}
	public Weapon getWeapon() {
		return weapon;
	}
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	public Spell getSpell() {
		return spell;
	}
	public void setSpell(Spell spell) {
		this.spell = spell;
	}
}
