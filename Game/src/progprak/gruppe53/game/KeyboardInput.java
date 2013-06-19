package progprak.gruppe53.game;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

public class KeyboardInput implements KeyEventDispatcher {
	
	//Directions
	private boolean up 		= false;
	private boolean down 	= false;
	private boolean left	= false;
	private boolean right	= false;
	
	
	private boolean attack	= false;
	private boolean shop = false;


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
			if(keyCode==KeyEvent.VK_F)
				attack=true;
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
			if(keyCode==KeyEvent.VK_F)
				attack=false;
			if(keyCode==KeyEvent.VK_E)
				shop=!shop;
			
		}
		return false;
	}

	public boolean isShop() {
		return shop;
	}

}
