package progprak.gruppe53.game;

public class FireStaff extends Weapon{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4274376584235570942L;
	
	private CollisionEvent notAttackCe;
	private CollisionEvent attackCe;

	public FireStaff(int x, int y) {
		super(x, y, "");
		
		doInitalizations();

	}
	
	private void doInitalizations(){
		
	}

	@Override
	public void doLogic(long delta) {

		
	}

	@Override
	public void attack(boolean attack) {

	}

}
