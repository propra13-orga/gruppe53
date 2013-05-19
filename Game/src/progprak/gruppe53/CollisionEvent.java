package progprak.gruppe53;

public class CollisionEvent {

	
	public static final int NOTHING				= 0;
	public static final int MASSIVE				= 1;
	public static final int DAMAGE				= 2;
	public static final int TELEPORT			= 3;
	public static final int SWITCH_LEVEL		= 4;
	
	private int event = 0;
	
	private int newX,newY;
	private int newLevel;
	
	public CollisionEvent(int event){
		this.event = event;
	}
	public int getEvent(){
		return event;
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
		if(event == TELEPORT || event == SWITCH_LEVEL){
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
		if(event == TELEPORT || event == SWITCH_LEVEL){
			this.newY = newY;
		}
		else {
			//TODO: ERROR
		}
	}
	/**
	 * @return the newLevel
	 */
	public int getNewLevel() {
		return newLevel;
	}
	/**
	 * @param newLevel the newLevel to set
	 */
	public void setNewLevel(int newLevel) {
		if(event == SWITCH_LEVEL){
			this.newLevel = newLevel;
		}
		else {
			//TODO: ERROR
		}
	}
}
