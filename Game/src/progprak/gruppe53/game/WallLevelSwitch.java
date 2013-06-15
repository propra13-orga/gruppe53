package progprak.gruppe53.game;

public class WallLevelSwitch extends LevelSwitch {

	
	public static final int DIRECTION_HORIZONTAL = 0;
	public static final int DIRECTION_VERTICAL = 1;
	
	public WallLevelSwitch(int x, int y,int direction, String newLevel) {
		super(x, y,null, newLevel);
		if(direction == DIRECTION_HORIZONTAL){
			setImage("images/tor1.png");
		}
		else if(direction == DIRECTION_VERTICAL){
			setImage("images/tor2.png");
		}
		// TODO Auto-generated constructor stub
	}

}
