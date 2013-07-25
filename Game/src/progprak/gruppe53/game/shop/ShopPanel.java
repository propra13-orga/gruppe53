package progprak.gruppe53.game.shop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import progprak.gruppe53.game.Game;
import progprak.gruppe53.game.inventory.InventorySlot;
import progprak.gruppe53.items.Item;

public class ShopPanel extends JPanel {

	private static final long serialVersionUID = -7147459534307392461L;

	private JPanel itemPanel;
	private Game game;
	private InventorySlot[] shopSlot;
	
	private final int shopSlots = 12;
	
	
	public ShopPanel(Game game) {
		
		this.game = game;
		
		doInitalizations();
		
	}


	private void doInitalizations() {
		setLayout(new BorderLayout());
		shopSlot = new InventorySlot[shopSlots];
		itemPanel = new JPanel();
		add(itemPanel,BorderLayout.CENTER);
		
		itemPanel.setLayout(new GridLayout(0, 2,5,5));
		for(int i=0;i<shopSlots;i++){
			shopSlot[i] = new InventorySlot(new SlotAction() {
				
				@Override
				public void slotClicked(InventorySlot inventorySlot) {
					game.getPlayer().shopSlotClicked(inventorySlot.getSlotNumber());
				}
			}, i);
			shopSlot[i].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			itemPanel.add(shopSlot[i]);
		}
	}

	public void render(Item items[]){
		for(int i=0;i<shopSlots;i++){
			shopSlot[i].render(items[i]);
		}
	}
	
}
