package progprak.gruppe53.items;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.GameLogic;

abstract public class Weapon extends Item implements Collidable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	protected int manaCost = 0;
	
	protected int yOffset;
	protected int yDrawOffset;

	protected int xOffset;
	protected int xDrawOffset;

	
	protected int ww = 0;
	protected int wh = 0;


	
	public Weapon(int x, int y, String imagePath,GameLogic gameLogic) {
		super(x, y, imagePath, gameLogic);
	}


	public void attack(boolean attack){
		if(attack && gameLogic.getHero().getMana() >= manaCost){
			gameLogic.getHero().drainMana(manaCost);
		}
		if(gameLogic.getHero().getLastDx()<0){
			setAngle(Math.PI);
			xDrawOffset = (int) ((xOffset*-1)-width);
			yDrawOffset = yOffset;
		}
		else {
			setAngle(0);
			xDrawOffset = xOffset;
			yDrawOffset = yOffset;
		}
		drawWeapon(attack);
	}
	
	protected void drawWeapon(boolean draw) {
		this.draw = draw;
		if(draw){
			x = owner.getX()+owner.getWidth()/2+xDrawOffset;
			y = owner.getY()+owner.getHeight()/2+yDrawOffset;
			width = ww;
			height = wh;
		}
		else {
			width = height = x = y = 0;
		}
	}
	
}
