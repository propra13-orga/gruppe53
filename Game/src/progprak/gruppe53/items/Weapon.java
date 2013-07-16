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

	protected int damageType;

	
	public Weapon(int x, int y, String imagePath,GameLogic gameLogic) {
		super(x, y, imagePath, gameLogic);
	}


	public void attack(boolean attack){
		if(gameLogic.getHero().getLastDx()<0){
			setAngle(Math.PI);
			xDrawOffset = (int) ((xOffset*-1)-getWidth());
			yDrawOffset = yOffset;
		}
		else {
			setAngle(0);
			xDrawOffset = xOffset;
			yDrawOffset = yOffset;
		}
		System.out.println(xDrawOffset);
	}
	
	protected void drawWeapon(boolean draw) {
		this.draw = draw;
		if(draw){
			setX(owner.getX()+owner.getWidth()/2+xDrawOffset);
			setY(owner.getY()+owner.getHeight()/2+yDrawOffset);
			setWidth(ww);
			setHeight(wh);
		}
		else {
			setWidth(setHeight(setX(setY(0))));
		}
	}
	public int getDamageType() {
		return damageType;
	}
	
}
