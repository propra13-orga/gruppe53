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
		items = new Item[inventorySlots+1];
		this.hero = hero;
		this.gameLogic = gameLogic;
	}

	/**
	 * @return returns Weapon from Weaponslot
	 */
	public Weapon getWeapon() {
		return weapon;
	}

	/**
	 * @return returns Armor from Armorslot
	 */
	public Armor getArmor() {
		return armor;
	}

	/**
	 * @return returns true, if the Inventory has a empty Slot
	 */
	public boolean hasFreeSlot() {
		for(int i=0;i<inventorySlots;i++){
			if(items[i] == null){
				return true;
			}
		}
		return false;
	}

	/**
	 * @param item: Required new Item for Inventory
	 * Adds the new Item to an empty InventorySlot
	 */
	public void addItem(Item item) {
		for(int i=0;i<inventorySlots;i++){
			if(items[i] == null){
				items[i] = item;
				return;
			}
		}
	}

	/**
	 * @return Returns all Items
	 */
	public Item[] getItems() {
		return items;
	}

	/**
	 * @param slotNumber: Required the SlotNumber
	 * Uses the Item in the Inventoryslot with the SlotNumber
	 */
	public void slotClicked(int slotNumber) {
		if(items[slotNumber] != null){
			// if the Slot is empty, the click has no effect
			if(hero.isShopOpen()){
				hero.setMoney(hero.getMoney() + items[slotNumber].getPrice());
				items[slotNumber] = null;
			}
			else {
				// if the Slot contains a Weapon, it is added to the Weaponslot
				if(items[slotNumber] instanceof Weapon){
					items[inventorySlots] = weapon;
					weapon = (Weapon) items[slotNumber];
					items[slotNumber] = items[inventorySlots];
					gameLogic.addSprite(weapon);
				}
				// if the Slot contains an Armor , it is added to the Armorslot
				else if(items[slotNumber] instanceof Armor){
					items[inventorySlots] = armor;
					armor = (Armor) items[slotNumber];
					items[slotNumber] = items[inventorySlots];
					gameLogic.addSprite(armor);
				}
				// if the Slot contains a useable Item, it is used and removed from the Inventory
				else{
					items[slotNumber] .use();
					items[slotNumber] = null;
				}
			}
		}
	}

}
