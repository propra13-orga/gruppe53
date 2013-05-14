package progprak.gruppe53;

public class Hero extends Sprite{

	private static final long serialVersionUID = 1L;
	
	public Hero(int x, int y){
		super(x,y,"images/profi.png");
	}
	
	@Override
	public void doLogic(long delta)
	{
		//Can't move through left border
		if(this.x<0)
			this.x = 0;
		//Can't move through top border
		if(this.y<0)
			this.y = 0;
		
	}

}
