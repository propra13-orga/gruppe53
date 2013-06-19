package progprak.gruppe53.game;

import progprak.gruppe53.items.Armor;
import progprak.gruppe53.items.Item;
import progprak.gruppe53.items.Weapon;

public class Inventory {

	private Weapon weapon;
	private Armor armor;
	private Item[] items;
	private int inventorySlots = 10;
	private Hero hero;
	private GameLogic gameLogic;
	
	public Inventory(Hero hero,GameLogic gameLogic) {
		items = new Item[inventorySlots];
		this.hero = hero;
		this.gameLogic = gameLogic;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public Armor getArmor() {
		return armor;
	}

	public boolean hasFreeSlot() {
		for(int i=0;i<inventorySlots;i++){
			if(items[i] == null){
				return true;
			}
		}
		return false;
	}

	public void addItem(Item item) {
		for(int i=0;i<inventorySlots;i++){
			if(items[i] == null){
				items[i] = item;
				return;
			}
		}
	}

	public Item[] getItems() {
		return items;
	}

	public void slotClicked(int slotNumber) {
		if(hero.isShop()){
			if(items[slotNumber] != null){
				hero.setMoney(hero.getMoney() + items[slotNumber].getPrice());
				items[slotNumber] = null;
			}
		}
		else {
			if(items[slotNumber] instanceof Weapon){
				weapon = (Weapon) items[slotNumber];
				items[slotNumber] = null;
				gameLogic.addSprite(weapon);
			}
		}
	}

}