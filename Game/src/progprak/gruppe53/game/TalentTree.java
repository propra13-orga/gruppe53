package progprak.gruppe53.game;

import java.io.Serializable;

import progprak.gruppe53.sprites.Hero;

public class TalentTree implements Serializable {


	private static final long serialVersionUID = -1374283276042127462L;
	private Hero hero;
	private transient GameLogic gameLogic;
	private int[] talent;
	private int[] talentMax = {5,5,1,5,5,5,5,5,5,3,1,5};
	public TalentTree(Hero hero,GameLogic gameLogic) {
		this.hero = hero;
		this.gameLogic = gameLogic;
		talent = new int[12];
		
	}

	public void talentButtonClicked(int button){
		if(talent[button] < talentMax[button]){
			if(hero.getTalentPoint() >= 1){
				talent[button] += 1;
				hero.setTalentPoint(-1);
			}
		}
	}
	
	public int getTalent(int talentNumber){
		return talent[talentNumber];
	}
}