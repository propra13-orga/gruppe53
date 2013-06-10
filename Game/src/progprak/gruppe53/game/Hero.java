package progprak.gruppe53.game;

public class Hero extends CombatObject{

	private static final long serialVersionUID = -8077486599395198634L;

	private KeyboardInput keyboardInput;
	
	
	private int mana;
	private int maxMana;
	
	private Weapon weapon;
	private Armor armor;
	public Hero(int x, int y, Game game, Weapon weapon, Armor armor){
		super(x,y,"images/hero.png",game);
		faction = 1;
		this.keyboardInput = game.getKeyboardInput();
		maxHealth = 10;
		health = maxHealth;
		maxMana = 100;
		mana = maxMana;
		this.weapon = weapon;
		this.weapon.setOwner(this);
		this.armor = armor;
		//this.armor.setOwner(this);
		doInitalizations();
	} 
	protected void initCollisionEvent() {
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_NOTHING);
	}
	
	@Override
	public void doLogic(long delta)
	{
		if(keyboardInput.isUp())
			dy = -1;
		
		if(keyboardInput.isDown())
			dy = 1;
		
		if(!keyboardInput.isUp() && !keyboardInput.isDown())
			dy = 0;
		
		if(keyboardInput.isLeft())
			dx = -1;
		
		if(keyboardInput.isRight())
			dx = 1;
		
		if(!keyboardInput.isLeft() && !keyboardInput.isRight())
			dx = 0;
		
		if(keyboardInput.isAttack())
			weapon.attack(true);
		
		else
			weapon.attack(false);
	}
	
	
	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	public void setX(int newX) {
		x = newX;		
	}
	public void setY(int newY) {
		y = newY;		
	}

	/**
	 * @return the maxHealth
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	
	/**
	 * @return the mana
	 */
	public int getMana(){
		return mana;
	}
	
	/**
	 * @return the maxMana
	 */
	public int getMaxMana(){
		return maxMana;
	}

	/**
	 * @return the weapon
	 */
	public Weapon getWeapon() {
		return weapon;
	}
	/**
	 * @return the armor
	 */
	public Armor getArmor(){
		return armor;
	}
	protected void handlePickupEvent(CollisionEvent ce){
		PickupCollisionEvent pe = (PickupCollisionEvent) ce;
		InventorySlot slot = game.getInfoWindow().getInventoryPanel().getFreeSlot();
		if(slot != null){
			game.getGameLogic().removeSprite(pe.getItem());
			game.getInfoWindow().getInventoryPanel().newItem(slot,pe.getItem());
			handleEvents = false;
		}
	}
}
