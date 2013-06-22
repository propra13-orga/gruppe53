package progprak.gruppe53.game;

import java.io.Serializable;

import progprak.gruppe53.items.Armor;
import progprak.gruppe53.items.Item;
import progprak.gruppe53.items.Weapon;
import progprak.gruppe53.sprites.Hero;

public class Inventory implements Serializable {

	private static final long serialVersionUID = 2765576774412774911L;
	private Weapon weapon;
	private Armor armor;
	private Item[] items;
	private int inventorySlots = 10;
	private Hero hero;
	private transient GameLogic gameLogic;
	
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
		if(items[slotNumber] != null){
			if(hero.isShopOpen()){
				hero.setMoney(hero.getMoney() + items[slotNumber].getPrice());
				items[slotNumber] = null;
			}
			else {
				if(items[slotNumber] instanceof Weapon){
					weapon = (Weapon) items[slotNumber];
					items[slotNumber] = null;
					gameLogic.addSprite(weapon);
				}
				else {
					items[slotNumber] .use();
					items[slotNumber] = null;
				}
			}
		}
	}

}
