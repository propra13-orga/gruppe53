package progprak.gruppe53;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {
	
	//Directions
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	
	
	public void keyPressed(KeyEvent e)
	{	
		int keyCode = e.getKeyCode();
		if(keyCode==KeyEvent.VK_UP)
			up=true;
		if(keyCode==KeyEvent.VK_DOWN)
			down=true;
		if(keyCode==KeyEvent.VK_LEFT)
			left=true;
		if(keyCode==KeyEvent.VK_RIGHT)
			right=true;
	}
	
	public void keyReleased(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		if(keyCode==KeyEvent.VK_UP)
			up=false;
		if(keyCode==KeyEvent.VK_DOWN)
			down=false;
		if(keyCode==KeyEvent.VK_LEFT)
			left=false;
		if(keyCode==KeyEvent.VK_RIGHT)
			right=false;
	}

	
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
}
