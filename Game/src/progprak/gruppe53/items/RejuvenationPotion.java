package progprak.gruppe53.items;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.GameLogic;

public class RejuvenationPotion extends Potion implements Collidable  {
	
	private static final long serialVersionUID = 1L;
	
	
	public RejuvenationPotion(int x, int y,GameLogic gameLogic){
		super( x, y, "images/OldManNPC.png",gameLogic);
		price = 100;
	}
	
	public RejuvenationPotion(GameLogic gameLogic) {
		super(0,0,"images/OldManNPC.png",gameLogic);
		price = 100;
	}

	@Override
	public void doLogic(long delta){
		
	}
	@Override
	public void use() {
		super.use();
		gameLogic.getHero().addHealth(50);
		gameLogic.getHero().drainMana(-500);
	}

}
