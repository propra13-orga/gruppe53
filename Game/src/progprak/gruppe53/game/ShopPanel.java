package progprak.gruppe53.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import progprak.gruppe53.items.HealthPotion;
import progprak.gruppe53.items.Item;
import progprak.gruppe53.items.ManaPotion;

public class ShopPanel extends JPanel {

	private static final long serialVersionUID = -7147459534307392461L;

	private JPanel itemPanel;
	private Game game;
	
	
	public ShopPanel(Game game) {
		
		this.game = game;
		
		doInitalizations();
		
	}


	private void doInitalizations() {
		setLayout(new BorderLayout());
		
		itemPanel = new JPanel();
		add(itemPanel,BorderLayout.CENTER);
		
		itemPanel.setLayout(new GridLayout(0, 2,5,5));
		for(int i=0;i<12;i++){
			InventorySlot slot = new InventorySlot(new SlotAction() {
				
				@Override
				public void slotClicked(InventorySlot inventorySlot) {
					/*if(inventorySlot.isUsed()){
						buyItem(inventorySlot);
					}*/
					
				}
			}, i);
			slot.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			//if(i%2==0)slot.newItem(new HealthPotion(game.getGameLogic()));
			//else slot.newItem(new ManaPotion(game.getGameLogic()));
			itemPanel.add(slot);
		}
	}


	/*protected void buyItem(InventorySlot slot) {
		InventorySlot iSlot = game.getGameFrame().getInfoWindow().getInventoryPanel().getFreeSlot();
		if(iSlot != null){
			Item item = slot.getItem();
			if (game.getGameLogic().getHero().getMoney() >= item.getPrice()) {
				game.getGameLogic().getHero().setMoney(game.getGameLogic().getHero().getMoney() - item.getPrice());
				game.getGameFrame().getInfoWindow().getInventoryPanel().newItem(iSlot, item);
				slot.removeItem();
			}
		}
	}*/
	
}
