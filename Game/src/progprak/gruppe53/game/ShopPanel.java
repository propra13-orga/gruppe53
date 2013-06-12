package progprak.gruppe53.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

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
		JButton close = new JButton("Close");
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				game.hideShop();
			}
		});
		add(close,BorderLayout.NORTH);
		add(itemPanel,BorderLayout.CENTER);
		
		itemPanel.setLayout(new GridLayout(0, 2,5,5));
		for(int i=0;i<12;i++){
			InventorySlot slot = new InventorySlot();
			slot.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			slot.newItem(new Sword());
			slot.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					buyItem((InventorySlot) e.getSource());
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			itemPanel.add(slot);
		}
	}


	protected void buyItem(InventorySlot slot) {
		InventorySlot iSlot = game.getInfoWindow().getInventoryPanel().getFreeSlot();
		if(iSlot != null){
			Item item = slot.getItem();
			if (game.getGameLogic().getHero().getMoney() >= item.getPrice()) {
				game.getGameLogic().getHero().setMoney(game.getGameLogic().getHero().getMoney() - item.getPrice());
				game.getInfoWindow().getInventoryPanel().newItem(iSlot, item);
				slot.removeItem();
			}
		}
		
	}
	
}
