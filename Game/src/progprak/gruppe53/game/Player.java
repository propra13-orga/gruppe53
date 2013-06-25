package progprak.gruppe53.game;

import java.io.Serializable;


public class Player implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4443478595881828337L;
	KeyboardInput keyboardInput;
	private int inventorySlotClicked = -1;
	private int shopSlotClicked = -1;
	private int talentButtonClicked = -1;
	
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

	public void shopSlotClicked(int slotNumber) {
		shopSlotClicked = slotNumber;
		
	}

	/**
	 * @return the shopSlotClicked
	 */
	public int getShopSlotClicked() {
		return shopSlotClicked;
	}

	public void resetInventorySlotClicked() {
		inventorySlotClicked = -1;
	}
	public void resetShopSlotClicked() {
		shopSlotClicked = -1;
	}
	public void resetTalentButtonClicked() {
		talentButtonClicked = -1;
	}
	
	public int getTalentButtonClicked() {
		return talentButtonClicked;
	}

	public void talentButtonClicked(int buttonNumber){
		talentButtonClicked = buttonNumber;
	}
}
