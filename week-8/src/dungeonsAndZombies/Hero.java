package dungeonsAndZombies;

public class Hero extends Player{
	private String name;
	private String title;
	private int manaRegenRate;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getManaRegenRate() {
		return manaRegenRate;
	}
	public void setManaRegenRate(int manaRegenRate) {
		this.manaRegenRate = manaRegenRate;
	}
	public Hero(String name, String title, int health, int mana, int manaRegenRate) {
		super(health, mana);
		this.name = name;
		this.title = title;
		this.manaRegenRate = manaRegenRate;
	}
	
	public String knownAs() {
		return name + " the " + title;
	}
	
	
	
	

	
	
	
}
