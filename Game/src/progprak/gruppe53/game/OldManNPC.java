package progprak.gruppe53.game;

public class OldManNPC extends FriendlyNPC {
	private static final long serialVersionUID = 2838843254158087591L;

	private String speech;
	private String answer;

	public OldManNPC(int x, int y,Game game, String speech){
		super(x,y,"images/OldManNPC.png",game);
		this.speech = speech;
		this.answer = answer;
		faction = 0;
	}
	@Override
	public void doLogic(long delta) {
		game.hideSpeechPane();
	}

	protected void handleCollisionEvent(CollisionEvent ce) {
		if(ce.actor instanceof Hero){
			game.showSpeechPane(speech);
		}
	}
}
