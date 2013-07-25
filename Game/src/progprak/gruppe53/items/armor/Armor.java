package progprak.gruppe53.items.armor;

import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.items.Item;


abstract public class Armor extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected int armorLVL;
	protected double blowResistance;
	protected double pierceResistance;
	protected double bluntResistance;
	protected double fireResistance;
	protected double arcaneResistance;

	public Armor(int x, int y, String imagePath,GameLogic gameLogic) {
		super(x, y, imagePath, gameLogic);
		// TODO Auto-generated constructor stub
	}
	
	public int getArmorLVL(){
		
		return armorLVL;
	}
	
	public double getBlowResistance(){
		return blowResistance;
	}
	
	public double getPierceResistance(){
		return pierceResistance;
	}
	
	public double getBluntResistance(){
		return bluntResistance;
	}
	
	public double getFireResistance(){
		return fireResistance;
	}
	
	public double getArcaneResistance(){
		return arcaneResistance;
	}
}
