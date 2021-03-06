package progprak.gruppe53.game.inventory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

import progprak.gruppe53.game.Game;
import progprak.gruppe53.game.shop.SlotAction;
import progprak.gruppe53.items.Item;
import progprak.gruppe53.items.Weapon;
import progprak.gruppe53.items.armor.Armor;

public class InventoryPanel extends JPanel {

	/**
*
*/
	private static final long serialVersionUID = 1L;

	private InventorySlot weaponSlot;
	private InventorySlot armorSlot;
	private InventorySlot[] inventorySlot;

	private final int inventorySlots = 10;
	private Game game;
	private SlotAction useAction;

	/**
	 * @param game: Requires the Game
	 * Initialises the InventoryPanel for all Players
	 */
	public InventoryPanel(Game game) {
		super();
		this.game = game;
		doInitalizations();
	}

	/**
	 * Initialises the Object
	 */
	private void doInitalizations() {
		setLayout(new FlowLayout());
		weaponSlot = new InventorySlot(null, -2);
		this.add(weaponSlot);
		armorSlot = new InventorySlot(null, -1);
		// saveSlot = new InventorySlot(new ClothArmor());
		this.add(armorSlot);
		JPanel space = new JPanel();
		space.setBackground(Color.black);
		space.setPreferredSize(new Dimension(2, 40));
		this.add(space);

		inventorySlot = new InventorySlot[10];
		useAction = new SlotAction() {
			@Override
			public void slotClicked(InventorySlot inventorySlot) {
				game.getPlayer().inventorySlotClicked(
						inventorySlot.getSlotNumber());
			}
		};

		for (int i = 0; i < inventorySlots; i++) {
			inventorySlot[i] = new InventorySlot(useAction, i);
			this.add(inventorySlot[i]);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	/**
	 * @return Returns the Weapon stored in the Weaponslot
	 */
	public InventorySlot getWeaponSlot() {
		return weaponSlot;
	}

	/**
	 * @return Returns the Armor stored in the Armorslot
	 */
	public InventorySlot getArmorSlot() {
		return armorSlot;
	}

	/**
	 * 
	 * calls functions "render" of the different Slots.
	 * 
	 * @param items: Requires the Inventoryslots
	 * @param weapon: Requires the Weaponslot
	 * @param armor: Requires the Armorslot
	 *  
	 */
	public void render(Item items[], Weapon weapon, Armor armor) {
		for (int i = 0; i < inventorySlots; i++) {
			inventorySlot[i].render(items[i]);
		}
		weaponSlot.render(weapon);
		armorSlot.render(armor);
	}
}
