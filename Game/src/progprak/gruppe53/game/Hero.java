package progprak.gruppe53.game;

import progprak.gruppe53.items.Armor;
import progprak.gruppe53.items.Weapon;

public class Hero extends CombatObject{

	private static final long serialVersionUID = -8077486599395198634L;

	private KeyboardInput keyboardInput;
	
	private int mana;
	private int maxMana;
	
	private Weapon weapon;
	private Armor armor;
	
	private int lastdx = 0;
	private int lastdy = 1;

	private int money = 100;
	
	public Hero(int xPos, int yPos, Game game, Weapon weapon, Armor armor){
		super(xPos,yPos,"images/held.png",game);
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
		if(keyboardInput.isUp()){
			dy = lastdy = -1;
			lastdx = 0;
		}
		
		if(keyboardInput.isDown()){
			dy = lastdy = 1;
			lastdx = 0;
		}	
		
		if(!keyboardInput.isUp() && !keyboardInput.isDown()){
			dy = 0;
		}
		
		if(keyboardInput.isLeft()){
			dx = lastdx = -1;
			lastdy = 0;
		}
		
		if(keyboardInput.isRight()){
			dx = lastdx = 1;
			lastdy = 0;
		}
		
		if(keyboardInput.isRight() && keyboardInput.isUp()){
			lastdx = 1;
			lastdy = -1;
		}
		
		if(keyboardInput.isRight() && keyboardInput.isDown()){
			lastdx = 1;
			lastdy = 1;
		}
		
		if(keyboardInput.isLeft() && keyboardInput.isUp()){
			lastdx = -1;
			lastdy = -1;
		}
		
		if(keyboardInput.isLeft() && keyboardInput.isDown()){
			lastdx = -1;
			lastdy = 1;
		}
		
		if(!keyboardInput.isLeft() && !keyboardInput.isRight()){
			dx = 0;
		}
		
		if(keyboardInput.isAttack()){
			weapon.attack(true);
		}
		else{
			weapon.attack(false);
		}
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
		System.out.println(pe.getItem());
		InventorySlot slot = game.getInfoWindow().getInventoryPanel().getFreeSlot();
		if(slot != null){
			game.getGameLogic().removeSprite(pe.getItem());
			game.getInfoWindow().getInventoryPanel().newItem(slot,pe.getItem());
			handleEvents = false;
		}
	}
	protected void handleDie(){
		game.loose();
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
	public int getLastDx(){
		return lastdx;
	}
	
	public int getLastDy(){
		return lastdy;
	}
	
	public int getFaction(){
		return faction;
	}
	public void drainMana(int manaCost) {
		mana -= manaCost;
		
	}
}
