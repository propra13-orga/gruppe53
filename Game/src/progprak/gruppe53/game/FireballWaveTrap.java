package progprak.gruppe53.game;

public class FireballWaveTrap extends CombatObject implements Shooter {
	private static final long serialVersionUID = 2838843254158087591L;
	
	private long cooldown = 0;
	private int castOrder;
	private int amount;
	private int direction;

	public FireballWaveTrap(int x, int y,GameLogic gameLogic, int amount, int direction) {
		super(x, y, null, gameLogic);
		castOrder = amount;
		this.amount = amount;
		this.direction = direction;
	}
	public FireballWaveTrap(int x, int y,GameLogic gameLogic, int amount, int direction, String imagePath) {
		super(x, y, imagePath, gameLogic);
		castOrder = amount;
		this.amount = amount;
		this.direction = direction;
	}

	@Override
	public void doLogic(long delta) {
		if(System.nanoTime() >= cooldown){
			if (direction == 1) {
				if (castOrder == amount) {
					gameLogic.addSprite(new Fireball((int)(this.x+width/2), (int)(this.y+height/2), gameLogic,this, 1, 0, 2));
					castOrder = 1;
					cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
				else {
					gameLogic.addSprite(new Fireball((int)(this.x+width/2), (int)(this.y+height/2)-16*castOrder, gameLogic,this, 1, 0, 2));
					castOrder = castOrder+1;
					if (castOrder == 8) cooldown = System.nanoTime() + (long)((5L)*1e8);
					else cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
			}
			else if (direction == 2) {
				if (castOrder == amount) {
					gameLogic.addSprite(new Fireball((int)(this.x+width/2), (int)(this.y+height/2), gameLogic,this, 0, -1, 2));
					castOrder = 1;
					cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
				else {
					gameLogic.addSprite(new Fireball((int)(this.x+width/2)+16*castOrder, (int)(this.y+height/2), gameLogic,this, 0, -1, 2));
					castOrder = castOrder+1;
					if (castOrder == 8) cooldown = System.nanoTime() + (long)((5L)*1e8);
					else cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
			}
			else if (direction == 3) {
				if (castOrder == amount) {
					gameLogic.addSprite(new Fireball((int)(this.x+width/2), (int)(this.y+height/2), gameLogic,this, -1, 0, 2));
					castOrder = 1;
					cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
				else {
					gameLogic.addSprite(new Fireball((int)(this.x+width/2), (int)(this.y+height/2)-16*castOrder, gameLogic,this, -1, 0, 2));
					castOrder = castOrder+1;
					if (castOrder == 8) cooldown = System.nanoTime() + (long)((5L)*1e8);
					else cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
			}
			else if (direction == 4) {
				if (castOrder == amount) {
					gameLogic.addSprite(new Fireball((int)(this.x+width/2), (int)(this.y+height/2), gameLogic,this, 0, 1, 2));
					castOrder = 1;
					cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
				else {
					gameLogic.addSprite(new Fireball((int)(this.x+width/2)+16*castOrder, (int)(this.y+height/2), gameLogic,this, 0, 1, 2));
					castOrder = castOrder+1;
					if (castOrder == 8) cooldown = System.nanoTime() + (long)((5L)*1e8);
					else cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
			}
			else if (direction == 5) {
				if (castOrder == amount) {
					gameLogic.addSprite(new Fireball((int)(this.x+width/2), (int)(this.y+height/2), gameLogic,this, 1, 0, 2));
					castOrder = 1;
					cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
				else {
					gameLogic.addSprite(new Fireball((int)(this.x+width/2), (int)(this.y+height/2)+16*castOrder, gameLogic,this, 1, 0, 2));
					castOrder = castOrder+1;
					if (castOrder == 8) cooldown = System.nanoTime() + (long)((5L)*1e8);
					else cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
			}
			else if (direction == 6) {
				if (castOrder == amount) {
					gameLogic.addSprite(new Fireball((int)(this.x+width/2), (int)(this.y+height/2), gameLogic,this, 0, -1, 2));
					castOrder = 1;
					cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
				else {
					gameLogic.addSprite(new Fireball((int)(this.x+width/2)-16*castOrder, (int)(this.y+height/2), gameLogic,this, 0, -1, 2));
					castOrder = castOrder+1;
					if (castOrder == 8) cooldown = System.nanoTime() + (long)((5L)*1e8);
					else cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
			}
			else if (direction == 7) {
				if (castOrder == amount) {
					gameLogic.addSprite(new Fireball((int)(this.x+width/2), (int)(this.y+height/2), gameLogic,this, -1, 0, 2));
					castOrder = 1;
					cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
				else {
					gameLogic.addSprite(new Fireball((int)(this.x+width/2), (int)(this.y+height/2)+16*castOrder, gameLogic,this, -1, 0, 2));
					castOrder = castOrder+1;
					if (castOrder == 8) cooldown = System.nanoTime() + (long)((5L)*1e8);
					else cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
			}
			else if (direction == 8) {
				if (castOrder == amount) {
					gameLogic.addSprite(new Fireball((int)(this.x+width/2), (int)(this.y+height/2), gameLogic,this, 0, 1, 2));
					castOrder = 1;
					cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
				else {
					gameLogic.addSprite(new Fireball((int)(this.x+width/2)-16*castOrder, (int)(this.y+height/2), gameLogic,this, 0, 1, 2));
					castOrder = castOrder+1;
					if (castOrder == 8) cooldown = System.nanoTime() + (long)((5L)*1e8);
					else cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
			}
			
		}
	}

	@Override
	public void shootRemoved() {
		
	}

	@Override
	public void doneKill(CombatObject combatObject) {
		
	}

}
