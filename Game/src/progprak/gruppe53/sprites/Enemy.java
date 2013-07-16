package progprak.gruppe53.sprites;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.GameLogic;

public abstract class Enemy extends CombatObject implements Collidable{
	
	private static final long serialVersionUID = 1L;
	protected double blowResistance;
	protected double pierceResistance;
	protected double bluntResistance;
	protected double fireResistance;
	protected double arcaneResistance;
	
	public Enemy(int x, int y,String imagePath,GameLogic gameLogic){
		super(x,y,imagePath,gameLogic);
		faction = 2;
		doInitalizations();
	}


	@Override
	public void doLogic(long delta){
		
	}
	@Override
	protected void handleTeleportEvent(int newX, int newY) {
	}
	
	@Override
	protected double damageReduce() {
		if (takenDamageType == 1) {
			return 1 / (1+blowResistance);
		}
		else if (takenDamageType == 2) {
			return 1 / (1+pierceResistance);
		}
		else if (takenDamageType == 3) {
			return 1 / (1+bluntResistance);
		}
		else if (takenDamageType == 4) {
			return 1 / (1+fireResistance);
		}
		else if (takenDamageType == 5) {
			return 1 /(1+arcaneResistance);
		} 
		else
			return 1;
		}

	public double getBlowResistance() {
		return blowResistance;
	}
	
	public double getPierceResistance() {
		return pierceResistance;
	}
	
	public double getBluntResistance() {
		return bluntResistance;
	}
	
	public double getFireResistance() {
		return fireResistance;
	}
	
	public double getArcaneResistance() {
		return arcaneResistance;
	}
}
