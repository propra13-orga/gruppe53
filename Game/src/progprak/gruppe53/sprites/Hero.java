package progprak.gruppe53.sprites;

import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.Inventory;
import progprak.gruppe53.game.PickupCollisionEvent;
import progprak.gruppe53.game.TalentPanel;
import progprak.gruppe53.items.Armor;
import progprak.gruppe53.items.Weapon;

public class Hero extends CombatObject{

	private static final long serialVersionUID = -8077486599395198634L;

	
	private int mana;
	private int maxMana;
	
	private Weapon weapon;
	private Armor armor;
	
	private Inventory inventory;
	
	private boolean talentTree = false;
	
	private int lastdx = 0;
	private int lastdy = 1;
	
	private int money = 100;
	private int spawnX,spawnY;
	
	private int lifes = 3;
	
	private long lastMana = 0;

	private GameLogic gameLogic;
	
	private int exp;
	private int heroLevel;
	private int reqExp;
	private int talentPoint;
	
	private boolean shop = false;

	public Hero(int xPos, int yPos, GameLogic gameLogic){
		super(xPos,yPos,"images/held.png",gameLogic);
		this.gameLogic = gameLogic;
		spawnX = xPos;
		spawnY = yPos;
		faction = 1;
		maxHealth = 100;
		health = maxHealth;
		maxMana = 1000;
		mana = maxMana;
		this.inventory = new Inventory(this, gameLogic);
		weapon = (Weapon) inventory.getWeapon();
		armor = (Armor) inventory.getArmor();
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
		recoverMana();
		weapon = inventory.getWeapon();
		if(weapon!= null){
			this.weapon.setOwner(this);
		}
		armor = inventory.getArmor();		
		talentTree = gameLogic.getPlayer().getKeyboardInput().isTalentTree();
		shop = gameLogic.getPlayer().getKeyboardInput().isShop();
		if(gameLogic.getPlayer().getInventorySlotClicked() != -1){
			inventory.slotClicked(gameLogic.getPlayer().getInventorySlotClicked());
		}
		
		if(gameLogic.getPlayer().getKeyboardInput().isUp()){
			dy = lastdy = -1;
			//lastdx = 0;
		}
		
		if(gameLogic.getPlayer().getKeyboardInput().isDown()){
			dy = lastdy = 1;
			//lastdx = 0;
		}	
		
		if(!gameLogic.getPlayer().getKeyboardInput().isUp() && !gameLogic.getPlayer().getKeyboardInput().isDown()){
			dy = 0;
		}
		
		if(gameLogic.getPlayer().getKeyboardInput().isLeft()){
			dx = lastdx = -1;
			lastdy = 0;
		}
		
		if(gameLogic.getPlayer().getKeyboardInput().isRight()){
			dx = lastdx = 1;
			lastdy = 0;
		}
		
		if(gameLogic.getPlayer().getKeyboardInput().isRight() && gameLogic.getPlayer().getKeyboardInput().isUp()){
			lastdx = 1;
			lastdy = -1;
		}
		
		if(gameLogic.getPlayer().getKeyboardInput().isRight() && gameLogic.getPlayer().getKeyboardInput().isDown()){
			lastdx = 1;
			lastdy = 1;
		}
		
		if(gameLogic.getPlayer().getKeyboardInput().isLeft() && gameLogic.getPlayer().getKeyboardInput().isUp()){
			lastdx = -1;
			lastdy = -1;
		}
		
		if(gameLogic.getPlayer().getKeyboardInput().isLeft() && gameLogic.getPlayer().getKeyboardInput().isDown()){
			lastdx = -1;
			lastdy = 1;
		}
		
		if(!gameLogic.getPlayer().getKeyboardInput().isLeft() && !gameLogic.getPlayer().getKeyboardInput().isRight()){
			dx = 0;
		}
		if(weapon!=null){
			weapon.attack(gameLogic.getPlayer().getKeyboardInput().isAttack());
		}
		if(exp >= reqExp){
			heroLevel += 1;
			talentPoint += 1;
			exp = exp - reqExp;
			reqExp = heroLevel*100;
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
		if(inventory.hasFreeSlot()){
			pe.getItem().setOwner(this);
			gameLogic.removeSprite(pe.getItem());
			inventory.addItem(pe.getItem());
		}
		/*InventorySlot slot = game.getGameFrame().getInfoWindow().getInventoryPanel().getFreeSlot();
		if(slot != null){
			pe.getItem().setOwner(this);
			gameLogic.removeSprite(pe.getItem());
			game.getGameFrame().getInfoWindow().getInventoryPanel().newItem(slot,pe.getItem());
			handleEvents = false;
		}*/
	}
	protected void handleDie(){
		if((--lifes)>=0){
			x = spawnX;
			y = spawnY;
			money = money/2;
			health = maxHealth;
			mana = maxMana;
		}
		else {
			gameLogic.loose();
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
		if(mana < maxMana){
			long current = System.nanoTime();
			if(current-lastMana >=1e9){
				mana = mana+8;
				lastMana = current;
			}
		}
	}

	@Override
	public void doneKill(CombatObject combatObject) {
		super.doneKill(combatObject);
		money += 50;
		exp += 25;
		if (combatObject instanceof BossEnemy) {
			gameLogic.switchLevel(((BossEnemy)combatObject).getNextLevel());
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
		gameLogic.switchLevel(ce.getNewLevel());
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
		gameLogic.win();
	}
	/**
	 * @return the inventory
	 */
	public Inventory getInventory() {
		return inventory;
	}
	public boolean isShop() {
		return shop;
	}
	
	public int getLevel(){
		return heroLevel;
	}
	
	public int getExperience(){
		return exp;
	}
	
	public int getReqExperience(){
		return reqExp;
	}
	
	public int getTalentPoint(){
		return talentPoint;
	}
	
	public boolean isTalentTree(){
		return talentTree;
	}
}
