package progprak.gruppe53.game;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import java.io.Serializable;

public class KeyboardInput implements KeyEventDispatcher, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7895064104386555018L;
	// Directions
	private boolean up = false;
	private boolean down = false;
	private boolean left = false;
	private boolean right = false;
	private boolean[] slot = { false, false, false, false, false, false, false,
			false, false, false };

	private boolean attack = false;
	private boolean shop = false;
	private boolean talentTree = false;

	/**
	 * @return the up
	 */
	public boolean isUp() {
		return up;
	}

	/**
	 * @return the down
	 */
	public boolean isDown() {
		return down;
	}

	/**
	 * @return the left
	 */
	public boolean isLeft() {
		return left;
	}

	/**
	 * @return the right
	 */
	public boolean isRight() {
		return right;
	}

	/**
	 * @return the attack
	 */
	public boolean isAttack() {
		return attack;
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (e.getID() == KeyEvent.KEY_PRESSED) {
			if (keyCode == KeyEvent.VK_W)
				up = true;
			if (keyCode == KeyEvent.VK_S)
				down = true;
			if (keyCode == KeyEvent.VK_A)
				left = true;
			if (keyCode == KeyEvent.VK_D)
				right = true;
			if (keyCode == KeyEvent.VK_ENTER)
				attack = true;
			if (keyCode == KeyEvent.VK_NUMPAD2)
				slot[1] = true;
			if (keyCode == KeyEvent.VK_NUMPAD3)
				slot[2] = true;
			if (keyCode == KeyEvent.VK_NUMPAD4)
				slot[3] = true;
			if (keyCode == KeyEvent.VK_NUMPAD5)
				slot[4] = true;
			if (keyCode == KeyEvent.VK_NUMPAD6)
				slot[5] = true;
			if (keyCode == KeyEvent.VK_NUMPAD7)
				slot[6] = true;
			if (keyCode == KeyEvent.VK_NUMPAD8)
				slot[7] = true;
			if (keyCode == KeyEvent.VK_NUMPAD9)
				slot[8] = true;
			if (keyCode == KeyEvent.VK_NUMPAD0)
				slot[9] = true;


		} else if (e.getID() == KeyEvent.KEY_RELEASED) {
			if (keyCode == KeyEvent.VK_W)
				up = false;
			if (keyCode == KeyEvent.VK_S)
				down = false;
			if (keyCode == KeyEvent.VK_A)
				left = false;
			if (keyCode == KeyEvent.VK_D)
				right = false;
			if (keyCode == KeyEvent.VK_ENTER)
				attack = false;
			if (keyCode == KeyEvent.VK_E)
				shop = !shop;
			if (keyCode == KeyEvent.VK_R)
				talentTree = !talentTree;
			if (keyCode == KeyEvent.VK_NUMPAD1)
				slot[0] = true;

		}
		return false;
	}

	public boolean isShop() {
		return shop;
	}

	public boolean isTalentTree() {
		return talentTree;
	}

	public boolean[] isSlot(){
		return slot;
	}
	
	public void setSlot(int slotNumber){
		slot[slotNumber] = false;
	}
}
