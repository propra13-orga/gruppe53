package progprak.gruppe53.game;

abstract public class Items extends Sprite {

	protected CollisionEvent collisionEvent;
	
	
	public Items(int x, int y, String imagePath) {
		super(x, y, imagePath);
	}
	public CollisionEvent getCollisionEvent(){
		return collisionEvent;
	}

}
