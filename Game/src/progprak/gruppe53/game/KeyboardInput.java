package progprak.gruppe53.game;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import java.io.Serializable;

public class KeyboardInput implements KeyEventDispatcher,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7895064104386555018L;
	//Directions
	private boolean up 		= false;
	private boolean down 	= false;
	private boolean left	= false;
	private boolean right	= false;
	private boolean slot1   = false;
	private boolean slot2   = false;
	private boolean slot3   = false;
	private boolean slot4   = false;
	private boolean slot5   = false;
	private boolean slot6   = false;
	private boolean slot7   = false;
	private boolean slot8   = false;
	private boolean slot9   = false;
	private boolean slot10   = false;
	
	
	
	private boolean attack	= false;
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
		if(e.getID() == KeyEvent.KEY_PRESSED){
			if(keyCode==KeyEvent.VK_W)
				up=true;
			if(keyCode==KeyEvent.VK_S)
				down=true;
			if(keyCode==KeyEvent.VK_A)
				left=true;
			if(keyCode==KeyEvent.VK_D)
				right=true;
			if(keyCode==KeyEvent.VK_ENTER)
				attack=true;
			if(keyCode==KeyEvent.VK_NUMPAD1)
				slot1=true;			
			if(keyCode==KeyEvent.VK_NUMPAD2)
				slot2=true;
			if(keyCode==KeyEvent.VK_NUMPAD3)
				slot3=true;
			if(keyCode==KeyEvent.VK_NUMPAD4)
				slot4=true;
			if(keyCode==KeyEvent.VK_NUMPAD5)
				slot5=true;
			if(keyCode==KeyEvent.VK_NUMPAD6)
				slot6=true;
			if(keyCode==KeyEvent.VK_NUMPAD7)
				slot7=true;
			if(keyCode==KeyEvent.VK_NUMPAD8)
				slot8=true;
			if(keyCode==KeyEvent.VK_NUMPAD9)
				slot9=true;
			if(keyCode==KeyEvent.VK_NUMPAD0)
				slot10=true;				
		}
		else if(e.getID() == KeyEvent.KEY_RELEASED){
			if(keyCode==KeyEvent.VK_W)
				up=false;
			if(keyCode==KeyEvent.VK_S)
				down=false;
			if(keyCode==KeyEvent.VK_A)
				left=false;
			if(keyCode==KeyEvent.VK_D)
				right=false;
			if(keyCode==KeyEvent.VK_ENTER)
				attack=false;
			if(keyCode==KeyEvent.VK_E)
				shop=!shop;
			if(keyCode==KeyEvent.VK_R)
				talentTree=!talentTree;
			if(keyCode==KeyEvent.VK_NUMPAD1)
				slot1=false;			
			if(keyCode==KeyEvent.VK_NUMPAD2)
				slot2=false;
			if(keyCode==KeyEvent.VK_NUMPAD3)
				slot3=false;
			if(keyCode==KeyEvent.VK_NUMPAD4)
				slot4=false;
			if(keyCode==KeyEvent.VK_NUMPAD5)
				slot5=false;
			if(keyCode==KeyEvent.VK_NUMPAD6)
				slot6=false;
			if(keyCode==KeyEvent.VK_NUMPAD7)
				slot7=false;
			if(keyCode==KeyEvent.VK_NUMPAD8)
				slot8=false;
			if(keyCode==KeyEvent.VK_NUMPAD9)
				slot9=false;
			if(keyCode==KeyEvent.VK_NUMPAD0)
				slot10=false;
			
		}
		return false;
	}

	public boolean isShop() {
		return shop;
	}
	
	public boolean isTalentTree() {
		return talentTree;
	}
	
	public boolean isSlot1() {
		return slot1;
	}
	
	public boolean isSlot2() {
		return slot2;
	}
	
	public boolean isSlot3() {
		return slot3;
	}
	
	public boolean isSlot4() {
		return slot4;
	}
	
	public boolean isSlot5() {
		return slot5;
	}
	
	public boolean isSlot6() {
		return slot6;
	}
	
	public boolean isSlot7() {
		return slot7;
	}
	
	public boolean isSlot8() {
		return slot8;
	}
	
	public boolean isSlot9() {
		return slot9;
	}
	
	public boolean isSlot10() {
		return slot10;
	}

}
