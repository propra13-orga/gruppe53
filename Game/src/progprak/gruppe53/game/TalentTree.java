package progprak.gruppe53.game;

import java.io.Serializable;

import progprak.gruppe53.sprites.Hero;

public class TalentTree implements Serializable {


	private TalentPanel talentPanel;
	private Hero hero;
	private transient GameLogic gameLogic;
	
	public TalentTree(Hero hero,GameLogic gameLogic) {
		talentPanel = new TalentPanel(hero, gameLogic);
		this.hero = hero;
		this.gameLogic = gameLogic;
	}
	
	public TalentPanel getTalentPanel(){
		return talentPanel;
	}
}