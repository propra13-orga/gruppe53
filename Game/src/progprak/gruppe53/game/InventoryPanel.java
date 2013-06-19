package progprak.gruppe53.game;

import java.awt.Color;
import java.awt.Dimension;
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
		weaponSlot = new InventorySlot(null, -2);
		this.add(weaponSlot);
		armorSlot = new InventorySlot(null,-1);
		//saveSlot = new InventorySlot(new ClothArmor());
		this.add(armorSlot);
		JPanel space = new JPanel();
		space.setBackground(Color.black);
		space.setPreferredSize(new Dimension(2,40));
		this.add(space);
		
		inventorySlot = new InventorySlot[10];
		useAction = new SlotAction() {
			
			@Override
			public void slotClicked(InventorySlot inventorySlot) {
				game.getGameLogic().getHero().getInventory().slotClicked(inventorySlot.getSlotNumber());
			}
		};

		for(int i=0;i<inventorySlots;i++){
			inventorySlot[i] = new InventorySlot(useAction, i);		
			this.add(inventorySlot[i]);
		}
	}

	@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
		}

	
	public InventorySlot getWeaponSlot(){
		return weaponSlot;
	}
	
	public InventorySlot getArmorSlot(){
		return armorSlot;
	}
	public void render(Item items[], Weapon weapon){
		for(int i=0;i<inventorySlots;i++){
			inventorySlot[i].render(items[i]);
		}
		weaponSlot.render(weapon);
	}
}
