package progprak.gruppe53.game;


public class Player {

	KeyboardInput keyboardInput;
	private int inventorySlotClicked = -1;
	
	public Player() {
		keyboardInput = new KeyboardInput();
	}

	/**
	 * @return the keyboardInput
	 */
	public KeyboardInput getKeyboardInput() {
		return keyboardInput;
	}

	public void inventorySlotClicked(int slotNumber) {
		inventorySlotClicked = slotNumber;
	}

	/**
	 * @return the inventorySlotClicked
	 */
	public int getInventorySlotClicked() {
		return inventorySlotClicked;
	}

}
