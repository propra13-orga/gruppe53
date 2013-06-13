package progprak.gruppe53.game;

import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

import progprak.gruppe53.items.Item;

public class InventoryPanel extends JPanel {

/**
*
*/
private static final long serialVersionUID = 1L;

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
		inventorySlot = new InventorySlot[10];
		useAction = new SlotAction() {
			
			@Override
			public void slotClicked(InventorySlot inventorySlot) {
				
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
			inventorySlot[i].repaint();
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

}
