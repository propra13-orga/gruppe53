package progprak.gruppe53.game;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

public class KeyboardInput implements KeyEventDispatcher {
	
	//Directions
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	

	
	public void keyTyped(KeyEvent e) {
	
		
	}

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

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(e.getID() == KeyEvent.KEY_PRESSED){
			if(keyCode==KeyEvent.VK_UP)
				up=true;
			if(keyCode==KeyEvent.VK_DOWN)
				down=true;
			if(keyCode==KeyEvent.VK_LEFT)
				left=true;
			if(keyCode==KeyEvent.VK_RIGHT)
				right=true;
		}
		else if(e.getID() == KeyEvent.KEY_RELEASED){
			if(keyCode==KeyEvent.VK_UP)
				up=false;
			if(keyCode==KeyEvent.VK_DOWN)
				down=false;
			if(keyCode==KeyEvent.VK_LEFT)
				left=false;
			if(keyCode==KeyEvent.VK_RIGHT)
				right=false;			
			
		}
		return false;
	}
}
