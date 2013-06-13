package progprak.gruppe53.items;

import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.Game;

abstract public class MeleeWeapons extends Weapon {

	

	
	private static final long serialVersionUID = -9179616171179500701L;
	protected CollisionEvent notAttackCe;
	protected CollisionEvent attackCe;
	
	public MeleeWeapons(int x, int y, String imagePath,Game game) {
		super(x, y, imagePath, game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doLogic(long delta) {
		// TODO Auto-generated method stub

	}


	@Override
	public void attack(boolean attack) {

		if(attack){

			collisionEvent = attackCe;
		}
		else {
			width = height = x = y = 0;
			collisionEvent = notAttackCe;
		}
	}



}
