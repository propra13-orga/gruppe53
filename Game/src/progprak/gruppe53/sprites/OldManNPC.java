package progprak.gruppe53.sprites;

import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.GameLogic;

public class OldManNPC extends FriendlyNPC {
	private static final long serialVersionUID = 2838843254158087591L;

	private String speech;

	public OldManNPC(int x, int y,GameLogic gameLogic, String speech){
		super(x,y,"images/OldManNPC.png",gameLogic);
		this.speech = speech;
		faction = 0;
		doInitalizations();
	}
	@Override
	public void doLogic(long delta) {
		//game.hideSpeechPane();
	}

	protected void handleCollisionEvent(CollisionEvent ce) {
		if(ce.actor instanceof Hero){
			//game.showSpeechPane(speech);
		}
	}
}
