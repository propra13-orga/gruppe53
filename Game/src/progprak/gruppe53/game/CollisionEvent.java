package progprak.gruppe53.game;

import progprak.gruppe53.sprites.QuestWall;

public class CollisionEvent {

	
	public static final int EVENT_NOTHING				= 0;
	public static final int EVENT_MASSIVE				= 1;
	public static final int EVENT_DAMAGE				= 2;
	public static final int EVENT_TELEPORT				= 3;
	public static final int EVENT_SWITCH_LEVEL			= 4;
	public static final int EVENT_GOAL					= 5;
	public static final int EVENT_PICKUP				= 6;
	public static final int EVENT_PLATE_PRESSED			= 7;
	
	public static final int DIRECTION_HORIZONTAL		= 1;
	public static final int DIRECTION_VERTICAL			= 2;
	
	
	private int event = 0;
	private int direction = 0;
	
	private int newX,newY;
	private String newLevel;
	public Collidable actor;
	private long lastLeverSwitch;
	
	public CollisionEvent(int event,Collidable actor){
		this.event = event;
		this.actor = actor;
	}
	public int getEvent(){
		return event;
	}
	public void setDirection(int d){
		direction = d;
	}
	/**
	 * @return the newX
	 */
	public int getNewX() {
		return newX;
	}
	/**
	 * @return the newY
	 */
	public int getNewY() {
		return newY;
	}
	/**
	 * @param newX the newX to set
	 */
	public void setNewX(int newX) {
		if(event == EVENT_TELEPORT){
			this.newX = newX;
		}
		else {
			//TODO: ERROR
		}
	}
	/**
	 * @param newY the newY to set
	 */
	public void setNewY(int newY) {
		if(event == EVENT_TELEPORT){
			this.newY = newY;
		}
		else {
			//TODO: ERROR
		}
	}
	/**
	 * @return the newLevel
	 */
	public String getNewLevel() {
		return newLevel;
	}
	/**
	 * @param newLevel the newLevel to set
	 */
	public void setNewLevel(String newLevel) {
		if(event == EVENT_SWITCH_LEVEL){
			this.newLevel = newLevel;
		}
		else {
			//TODO: ERROR
		}
	}
	
	public void setNewStatus(QuestWall QuestWall1, QuestWall QuestWall2, QuestWall QuestWall3){
		long current = System.nanoTime();
		if(event == EVENT_PLATE_PRESSED && current - lastLeverSwitch >= 2e9){

			
			lastLeverSwitch = current;
		}
	}
	/**
	 * @return the direction
	 */
	public int getDirection() {
		return direction;
	}
	/**
	 * @return the actor
	 */
	public Collidable getActor() {
		return actor;
	}
	public void setActor(Collidable actor) {
		this.actor = actor;
	}
}
