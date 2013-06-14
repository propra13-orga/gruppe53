package progprak.gruppe53.items;

import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.DamageCollisionEvent;
import progprak.gruppe53.game.Game;
import progprak.gruppe53.game.Hero;

abstract public class MeleeWeapons extends Weapon {

	

	
	private static final long serialVersionUID = -9179616171179500701L;
	protected CollisionEvent notAttackCe;
	protected CollisionEvent attackCe;
	protected int damage;
	
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
		super.attack(attack);
		if(attack){
			//setAngle(Math.PI);
			collisionEvent = attackCe;
		}
		else {
			width = height = x = y = 0;
			collisionEvent = notAttackCe;
		}
	}
	@Override
	public void setOwner(Hero owner) {
		super.setOwner(owner);
		attackCe = new DamageCollisionEvent(damage, owner.getFaction(), owner);
	}


}
