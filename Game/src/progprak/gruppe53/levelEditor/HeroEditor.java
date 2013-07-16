package progprak.gruppe53.levelEditor;

import progprak.gruppe53.sprites.Sprite;

/** 
 * A class required for the editor to set the spawnpoint of the hero
 */
public class HeroEditor extends Sprite{
	private static final long serialVersionUID = 1;
	
	/** 
	 * The contructor of the HeroEditor class
	 * @param x The x-position of the spawnpoint
	 * @param y The y-position of the spawnpoint
	 */
	public HeroEditor(int x, int y) {
		super(x, y, "images/held.png");
	}

	/** 
	 * The loop method
	 */
	@Override
	public void doLogic(long delta) {
				
	}
}
