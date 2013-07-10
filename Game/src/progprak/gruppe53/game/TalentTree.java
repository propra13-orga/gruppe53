package progprak.gruppe53.game;

import java.io.Serializable;

import progprak.gruppe53.sprites.Hero;

public class TalentTree implements Serializable {

	private static final long serialVersionUID = -1374283276042127462L;
	private Hero hero;
	private transient GameLogic gameLogic;
	// Creates an Array with all Talents, initialized with 0
	private int[] talent = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	// Creates an Array that contains the maximum Rank of a Talent
	private int[] talentMax = { 5, 5, 1, 5, 5, 5, 5, 7, 7, 3, 1, 1 };

	/**
	 * @param hero: Requires the Hero
	 * @param gameLogic: Requires the GameLogic
	 * Initializes the Heros TalentTree
	 */
	public TalentTree(Hero hero, GameLogic gameLogic) {
		this.hero = hero;
		this.gameLogic = gameLogic;
		talent = new int[12];

	}

	/**
	 * @param button Requires the Number of a Button
	 * Increases the Talent, connected with the Button,
	 * if the Hero has enough talentPoints
	 */
	public void talentButtonClicked(int button) {
		if (talent[button] < talentMax[button]) {
			if (hero.getTalentPoint() >= 1) {
				talent[button] += 1;
				hero.setTalentPoint(-1);
			}
		}
	}

	/**
	 * @param talentNumber: Requires a talentNumber
	 * @return Returns the Talent with the Number "talentNumber"
	 */
	public int getTalent(int talentNumber) {
		return talent[talentNumber];
	}
}