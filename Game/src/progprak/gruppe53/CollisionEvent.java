package progprak.gruppe53;

public class CollisionEvent {

	
	public static final int NOTHING			= 0;
	public static final int MASSIVE			= 1;
	public static final int DAMAGE			= 2;
	public static final int TO_NEXT_LEVEL	= 3;
	
	private int event = 0;
	
	public CollisionEvent(int event){
		this.event = event;
	}
	public int getEvent(){
		return event;
	}
}
