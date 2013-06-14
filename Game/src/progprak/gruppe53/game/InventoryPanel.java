package progprak.gruppe53.game;

import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

import progprak.gruppe53.items.Armor;
import progprak.gruppe53.items.Item;
import progprak.gruppe53.items.Weapon;

public class InventoryPanel extends JPanel {

/**
*
*/
private static final long serialVersionUID = 1L;

	//private InventorySlot	saveSlot;
	private InventorySlot	weaponSlot;
	private InventorySlot 	armorSlot;
	private InventorySlot[] inventorySlot;

	private final int inventorySlots = 10;
	private Game game;
	private SlotAction useAction;
	
	public InventoryPanel(Game game) {
		super();
		this.game = game;
		doInitalizations();
	}

	
	private void doInitalizations(){
		setLayout(new FlowLayout());
		weaponSlot = new InventorySlot(null);
		this.add(weaponSlot);
		armorSlot = new InventorySlot(null);
		//saveSlot = new InventorySlot(new ClothArmor());
		this.add(armorSlot);
		
		inventorySlot = new InventorySlot[10];
		useAction = new SlotAction() {
			
			@Override
			public void slotClicked(InventorySlot inventorySlot) {
				if(inventorySlot.getItem() instanceof Weapon){
					Item tmp = weaponSlot.getItem();
					weaponSlot.newItem(inventorySlot.getItem());
					game.getGameLogic().removeSprite(tmp);
					game.getGameLogic().addSprite(inventorySlot.getItem());
					inventorySlot.newItem(tmp);
					//inventorySlot.newItem(saveSlot.getItem());
				}
				if(inventorySlot.getItem() instanceof Armor){
					//saveSlot.newItem(armorSlot.getArmor());
					//armorSlot.removeItem();
					armorSlot.newItem(inventorySlot.getItem());
					inventorySlot.removeItem();
					//inventorySlot.newItem(saveSlot.getItem());
				}
			}
		};

		for(int i=0;i<inventorySlots;i++){
			inventorySlot[i] = new InventorySlot(useAction);		
			this.add(inventorySlot[i]);
		}
	}


	// Add new Item to Inventory(-Slot)
	public void newItem(Item item){
		InventorySlot slot = getFreeSlot();
		if(slot != null){
			newItem(slot,item);
		}
	}
	public void newItem(InventorySlot slot,Item item){
		slot.newItem(item);
		//slot.repaint();
	}
	
	// Remove the Item
	public void removeItem(Item item)
	{
		// Check for the right Item
		for(int i=0; i<inventorySlots;i++){
			//Remove the Item
			if(inventorySlot[i].getItem() == item){
				inventorySlot[i].removeItem();
				break;
			}
		}
	}
	
	// Check for Free InventorySlot
	public InventorySlot getFreeSlot(){
		for(int i=0; i<inventorySlots; i++){
			if(inventorySlot[i].isUsed() == false){
				return inventorySlot[i];
			}
		}
		return null;
	}
	
	// Resets the Inventory
	public void resetInventory()
	{
		for(int i=0; i<inventorySlots;i++)
		{
			inventorySlot[i].removeItem();
			//inventorySlot[i].repaint();
		}
	}
	@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
		}


	public void slotsSell() {
		for(int i=0;i<inventorySlots;i++){
			inventorySlot[i].setSlotAction(new SlotAction() {
				
				@Override
				public void slotClicked(InventorySlot inventorySlot) {
					if(inventorySlot.isUsed()){
						sellItem(inventorySlot);
					}
				}
			});
		}
	}
	public void slotsUse() {
		for(int i=0;i<inventorySlots;i++){
			inventorySlot[i].setSlotAction(useAction);	 
		}
	}


	protected void sellItem(InventorySlot slot) {
		game.getGameLogic().getHero().setMoney(game.getGameLogic().getHero().getMoney() + slot.getItem().getPrice());
		slot.removeItem();
	}
	
	public InventorySlot getWeaponSlot(){
		return weaponSlot;
	}
	
	public InventorySlot getArmorSlot(){
		return armorSlot;
	}
}
