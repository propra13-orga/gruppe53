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
	
	private InventoryPanel inventory;
	
	private int lastdx = 0;
	private int lastdy = 1;
	
	private int money = 100;
	private int spawnX,spawnY;
	
	private int lifes = 3;
	
	public Hero(int xPos, int yPos, Game game,InventoryPanel inventory){
		super(xPos,yPos,"images/held.png",game);
		spawnX = xPos;
		spawnY = yPos;
		faction = 1;
		this.keyboardInput = game.getKeyboardInput();
		maxHealth = 100;
		health = maxHealth;
		maxMana = 1000;
		mana = maxMana;
		this.inventory = inventory;
		weapon = (Weapon) inventory.getWeaponSlot().getItem();
		armor = (Armor) inventory.getArmorSlot().getItem();
		if(weapon!= null){
			this.weapon.setOwner(this);
		}
		doInitalizations();
	} 
	protected void initCollisionEvent() {
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_NOTHING,this);
	}
	
	@Override
	public void doLogic(long delta)
	{
		weapon = (Weapon) inventory.getWeaponSlot().getItem();
		if(weapon!= null){
			this.weapon.setOwner(this);
		}
		armor = (Armor) inventory.getArmorSlot().getItem();		

		if(keyboardInput.isUp()){
			dy = lastdy = -1;
			//lastdx = 0;
		}
		
		if(keyboardInput.isDown()){
			dy = lastdy = 1;
			//lastdx = 0;
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
		if(weapon!=null){
			weapon.attack(keyboardInput.isAttack());
		}
	}
	
	
	/**
	 * @return the health
	 */
	public double getHealth() {
		return health;
	}

	public void setXSpawn(int newX) {
		x = newX;
		spawnX = newX;
	}
	public void setYSpawn(int newY) {
		y = newY;
		spawnY = newY;
	}

	/**
	 * @return the maxHealth
	 */
	public double getMaxHealth() {
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
			pe.getItem().setOwner(this);
			game.getGameLogic().removeSprite(pe.getItem());
			game.getInfoWindow().getInventoryPanel().newItem(slot,pe.getItem());
			handleEvents = false;
		}
	}
	protected void handleDie(){
		if((--lifes)>=0){
			x = spawnX;
			y = spawnY;
			money = money/2;
			health = maxHealth;
		}
		else {
			game.loose();
		}
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
	public void recoverMana(){
		if(mana < maxMana)
		mana = mana+1;
	}

	@Override
	public void doneKill(CombatObject combatObject) {
		super.doneKill(combatObject);
		money += 50;
		if (combatObject instanceof BossEnemy) {
			game.switchLevel(((BossEnemy)combatObject).getNextLevel());
		}
	}
	public void addHealth(int aHp) {
		health += aHp;
		if(health>maxHealth){
			health = maxHealth;
		}
	}
	@Override
	protected void handleSwitchLevelEvent(CollisionEvent ce) {
		super.handleSwitchLevelEvent(ce);
		game.switchLevel(ce.getNewLevel());
	}
	/**
	 * @return the lifes
	 */
	public int getLifes() {
		return lifes;
	}
	@Override
	protected double damageReduce() {
		if(armor != null){
			return 1/(armor.getarmorLVL()*1.3);
		}
		else return 1;
	}
	@Override
	protected void handleGoalEvent(CollisionEvent ce) {
		super.handleGoalEvent(ce);
		game.win();
	}
}
