package progprak.gruppe53.sprites.characters;

import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.PickupCollisionEvent;
import progprak.gruppe53.game.Player;
import progprak.gruppe53.game.inventory.Inventory;
import progprak.gruppe53.game.shop.Shop;
import progprak.gruppe53.game.talentTree.TalentTree;
import progprak.gruppe53.items.Weapon;
import progprak.gruppe53.items.armor.Armor;
import progprak.gruppe53.sprites.CombatObject;
import progprak.gruppe53.sprites.enemies.BossEnemy;
import progprak.gruppe53.sprites.objects.PressurePlate;

public class Hero extends CombatObject {

	private static final long serialVersionUID = -8077486599395198634L;

	private int mana;
	private int maxMana;

	private Weapon weapon;
	private Armor armor;

	private Inventory inventory;
	private Shop shop;
	private TalentTree talentTree;

	private long lastSlow;
	private double slow = 1;
	private double speed = 1;
	private double lastdx = 0;
	private double lastdy = 1;

	private int money = 100;
	private int spawnX, spawnY;

	private int lifes = 3;
	private int maxLevel = 54;

	private long lastRecover = 0;

	private transient GameLogic gameLogic;

	private int exp;
	private int heroLevel;
	private int reqExp;
	private int talentPoint = 0;

	private boolean shopOpen = false;
	private boolean talents = false;

	private Player player;

	private boolean speechPaneShow;

	private String speechPaneText;

	public Hero(int xPos, int yPos, GameLogic gameLogic) {
		super(xPos, yPos, "images/held.png", gameLogic);
		this.gameLogic = gameLogic;
		spawnX = xPos;
		spawnY = yPos;
		faction = 1;
		maxHealth = 100;
		health = maxHealth;
		maxMana = 1000;
		mana = maxMana;
		this.talentTree = new TalentTree(this, gameLogic);
		this.inventory = new Inventory(this, gameLogic);
		this.shop = new Shop(this, gameLogic);
		weapon = (Weapon) inventory.getWeapon();
		armor = (Armor) inventory.getArmor();
		if (weapon != null) {
			this.weapon.setOwner(this);
		}
		doInitalizations();
	}

	protected void initCollisionEvent() {
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_NOTHING, this);
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public void doLogic(long delta) {
		recover();
		removeSlow();
		weapon = inventory.getWeapon();
		if (weapon != null) {
			this.weapon.setOwner(this);
			damageType = weapon.getDamageType();
		}
		armor = inventory.getArmor();
		talents = player.getKeyboardInput().isTalentTree();
		shopOpen = player.getKeyboardInput().isShop();
		maxHealth = 100 + talentTree.getTalent(0) * 20;
		maxMana = 1000 + talentTree.getTalent(1) * 200;
		if (player.getInventorySlotClicked() != -1) {
			inventory.slotClicked(player.getInventorySlotClicked());
			player.resetInventorySlotClicked();
		}
		if (player.getShopSlotClicked() != -1) {
			shop.slotClicked(player.getShopSlotClicked());
			player.resetShopSlotClicked();
		}
		if (player.getTalentButtonClicked() != -1) {
			talentTree.talentButtonClicked(player.getTalentButtonClicked());
			player.resetTalentButtonClicked();
		}

		if (player.getKeyboardInput().isUp()) {
			dy = (-speed - talentTree.getTalent(11)) * slow;
			lastdy = (-speed) * slow;
		}

		if (player.getKeyboardInput().isDown()) {
			dy = (speed + talentTree.getTalent(11)) * slow;
			lastdy = (speed) * slow;
		}

		if (!player.getKeyboardInput().isUp()
				&& !player.getKeyboardInput().isDown()) {
			dy = 0;
		}

		if (player.getKeyboardInput().isLeft()) {
			dx = (-speed - talentTree.getTalent(11)) * slow;
			lastdx = (-speed) * slow;
			lastdy = 0;
		}

		if (player.getKeyboardInput().isRight()) {
			dx = (speed + talentTree.getTalent(11)) * slow;
			lastdx = (speed) * slow;
			lastdy = 0;
		}

		if (player.getKeyboardInput().isRight()
				&& player.getKeyboardInput().isUp()) {
			lastdx = (speed) * slow;
			lastdy = (-speed) * slow;
		}

		if (player.getKeyboardInput().isRight()
				&& player.getKeyboardInput().isDown()) {
			lastdx = (speed) * slow;
			lastdy = (speed) * slow;
		}

		if (player.getKeyboardInput().isLeft()
				&& player.getKeyboardInput().isUp()) {
			lastdx = (-speed) * slow;
			lastdy = (-speed) * slow;
		}

		if (player.getKeyboardInput().isLeft()
				&& player.getKeyboardInput().isDown()) {
			lastdx = (-speed) * slow;
			lastdy = (speed) * slow;
		}

		if (!player.getKeyboardInput().isLeft()
				&& !player.getKeyboardInput().isRight()) {
			dx = 0;
		}
		if (weapon != null) {
			weapon.attack(player.getKeyboardInput().isAttack());
		}
		if (exp >= reqExp) {
			heroLevel += 1;
			talentPoint += 1;
			exp = exp - reqExp;
			reqExp = heroLevel * 30 + 25;
		}
		if (player.getKeyboardInput().isSlot()[0]) {
			inventory.slotClicked(0);
			player.getKeyboardInput().resetSlot(0);
		}
		if (player.getKeyboardInput().isSlot()[1]) {
			inventory.slotClicked(1);
			player.getKeyboardInput().resetSlot(1);
		}
		if (player.getKeyboardInput().isSlot()[2]) {
			inventory.slotClicked(2);
			player.getKeyboardInput().resetSlot(2);
		}
		if (player.getKeyboardInput().isSlot()[3]) {
			inventory.slotClicked(3);
			player.getKeyboardInput().resetSlot(3);
		}
		if (player.getKeyboardInput().isSlot()[4]) {
			inventory.slotClicked(4);
			player.getKeyboardInput().resetSlot(4);
		}
		if (player.getKeyboardInput().isSlot()[5]) {
			inventory.slotClicked(5);
			player.getKeyboardInput().resetSlot(5);
		}
		if (player.getKeyboardInput().isSlot()[6]) {
			inventory.slotClicked(6);
			player.getKeyboardInput().resetSlot(6);
		}
		if (player.getKeyboardInput().isSlot()[7]) {
			inventory.slotClicked(7);
			player.getKeyboardInput().resetSlot(7);
		}
		if (player.getKeyboardInput().isSlot()[8]) {
			inventory.slotClicked(8);
			player.getKeyboardInput().resetSlot(8);
		}
		if (player.getKeyboardInput().isSlot()[9]) {
			inventory.slotClicked(9);
			player.getKeyboardInput().resetSlot(9);
		}

	}

	/**
	 * @return the health
	 */
	public double getHealth() {
		return health;
	}

	public void setXSpawn(int newX) {
		setX(newX);
		spawnX = newX;
	}

	public void setYSpawn(int newY) {
		setY(newY);
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
	public int getMana() {
		return mana;
	}

	/**
	 * @return the maxMana
	 */
	public int getMaxMana() {
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
	public Armor getArmor() {
		return armor;
	}

	protected void handlePickupEvent(CollisionEvent ce) {
		PickupCollisionEvent pe = (PickupCollisionEvent) ce;
		if (inventory.hasFreeSlot()) {
			pe.getItem().setOwner(this);
			gameLogic.removeSprite(pe.getItem());
			inventory.addItem(pe.getItem());
		}
		/*
		 * InventorySlot slot =
		 * game.getGameFrame().getInfoWindow().getInventoryPanel
		 * ().getFreeSlot(); if(slot != null){ pe.getItem().setOwner(this);
		 * gameLogic.removeSprite(pe.getItem());
		 * game.getGameFrame().getInfoWindow
		 * ().getInventoryPanel().newItem(slot,pe.getItem()); handleEvents =
		 * false; }
		 */
	}

	protected void handleDie() {
		if ((--lifes) >= 0) {
			setX(spawnX);
			setY(spawnY);
			money = money / 2;
			health = maxHealth;
			mana = maxMana;
		} else {
			gameLogic.loose();
		}
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public double getLastDx() {
		return lastdx;
	}

	public double getLastDy() {
		return lastdy;
	}

	public int getFaction() {
		return faction;
	}

	public void drainMana(int manaCost) {
		mana -= manaCost;
	}

	public void recover() {
		long current = System.nanoTime();
		if (current - lastRecover >= 1e9) {
			if (mana <= maxMana - (8 + talentTree.getTalent(7))) {
				mana = mana + 8 + ((talentTree.getTalent(7) * 2));
			}
			if (health <= maxHealth - (talentTree.getTalent(6))) {
				health = health + Math.floor(talentTree.getTalent(6) / 2);
			}

			if (heroLevel <= maxLevel) {
				exp = exp + talentTree.getTalent(8);
			}
			lastRecover = current;
		}
	}

	@Override
	public void doneKill(CombatObject combatObject) {
		super.doneKill(combatObject);
		money += 50;
		if (heroLevel <= maxLevel) {
			exp += 25 + 5 * talentTree.getTalent(5);
		}
		if (combatObject instanceof BossEnemy) {
			gameLogic.switchLevel(((BossEnemy) combatObject).getNextLevel());
		}
	}

	public void addHealth(int aHp) {
		health += aHp + (aHp / 5 * talentTree.getTalent(3));
		if (health > maxHealth) {
			health = maxHealth;
		}
	}

	@Override
	protected void handleSwitchLevelEvent(CollisionEvent ce) {
		super.handleSwitchLevelEvent(ce);
		gameLogic.switchLevel(ce.getNewLevel());
	}
	
	@Override
	protected void handlePressurePlateEvent(CollisionEvent ce) {
		super.handlePressurePlateEvent(ce);
		((PressurePlate) ce.getActor()).press();
		if(!((PressurePlate) ce.getActor()).isPressed()){
			((PressurePlate) ce.getActor()).setPressed(true);
			gameLogic.switchQuestWalls(ce.getQuestWalls());
		}
		
	}

	/**
	 * @return the lifes
	 */
	public int getLifes() {
		return lifes;
	}

	@Override
	protected double damageReduce() {
		if (armor != null) {
			if (takenDamageType == 1) {
			return 1 / ((armor.getArmorLVL() + talentTree.getTalent(2)) * 1.3)*(1+armor.getBlowResistance());
			}
			else if (takenDamageType == 2) {
				return 1 / ((armor.getArmorLVL() + talentTree.getTalent(2)) * 1.3)*(1+armor.getPierceResistance());
			}
			else if (takenDamageType == 3) {
				return 1 / ((armor.getArmorLVL() + talentTree.getTalent(2)) * 1.3)*(1+armor.getBluntResistance());
			}
			else if (takenDamageType == 4) {
				return 1 / ((armor.getArmorLVL() + talentTree.getTalent(2)) * 1.3)*(1+armor.getFireResistance());
			}
			else if (takenDamageType == 5) {
				return 1 / ((armor.getArmorLVL() + talentTree.getTalent(2)) * 1.3)*(1+armor.getArcaneResistance());
			}
			else
				return 1 / ((armor.getArmorLVL() + talentTree.getTalent(2)) * 1.3);
		} else
			return 1;
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

	public boolean isShopOpen() {
		return shopOpen;
	}

	public int getLevel() {
		return heroLevel;
	}

	public int getExperience() {
		return exp;
	}

	public int getReqExperience() {
		return reqExp;
	}

	public int getTalentPoint() {
		return talentPoint;
	}

	public void setTalentPoint(int dif) {
		talentPoint = talentPoint + dif;
	}

	public TalentTree getTalentTree() {
		return talentTree;
	}

	public boolean isTalentTree() {
		return talents;
	}

	public Shop getShop() {
		return shop;
	}
	
	public void setSlow(double slow){
		this.slow = slow;
		lastSlow = System.nanoTime();
	}
	
	private void removeSlow(){
		if(System.nanoTime() - lastSlow >= 5e9){
			slow = 1;
		}
	}

	public void showSpeechPane(boolean b, String speech) {
		this.speechPaneShow = b;
		this.speechPaneText = speech;
		
	}
	public String getSpeechPaneText() {
		return speechPaneText;
	}
	public boolean isSpeechPaneShow() {
		return speechPaneShow;
	};
}
