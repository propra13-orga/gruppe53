package progprak.gruppe53.game;

public class OldManNPC extends FriendlyNPC {
	private static final long serialVersionUID = 2838843254158087591L;
	
	private String speech;
	private String answer;
		
	public OldManNPC(int x, int y,Game game, String speech, String answer){
		super(x,y,"images/OldManNPC.png",game);
		this.speech = speech;
		this.answer = answer;
		
	}
	
		protected void handleCollisionEvent(CollisionEvent ce) {
		if (SpeechDialog.speechDialogOpened == false) {
			SpeechDialog.speechDialogOpened = true;
			new SpeechDialog (speech, answer);		
		}
	}
}
