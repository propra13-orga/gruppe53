package progprak.gruppe53.game;

import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

public class InventoryPanel extends JPanel {

/**
*
*/
private static final long serialVersionUID = 1L;

private InventorySlot[] inventorySlot;

	private final int inventorySlots = 10;
	private Game game;
	
	public InventoryPanel(Game game) {
		super();
		this.game = game;
		doInitalizations();
	}

	
	private void doInitalizations(){
		setLayout(new FlowLayout());
		inventorySlot = new InventorySlot[10];
		for(int i=0;i<inventorySlots;i++){
			inventorySlot[i] = new InventorySlot(new SlotAction() {
				
				@Override
				public void slotClicked(InventorySlot inventorySlot) {
					// TODO Auto-generated method stub
					
				}
			});
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
	@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
		}


	public void setSell() {
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


	protected void sellItem(InventorySlot slot) {
		game.getGameLogic().getHero().setMoney(game.getGameLogic().getHero().getMoney() + slot.getItem().getPrice());
		slot.removeItem();
	}

}