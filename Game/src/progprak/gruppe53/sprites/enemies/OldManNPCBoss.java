package progprak.gruppe53.sprites.enemies;

import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.Shooter;
import progprak.gruppe53.sprites.CombatObject;
import progprak.gruppe53.sprites.projectils.Fireball;

public class OldManNPCBoss extends BossEnemy implements Shooter{
	private static final long serialVersionUID = 2838843254158087591L;
	
	private int castOrder=1;
	private double speed = 3;
		
	public OldManNPCBoss(int x, int y, GameLogic gameLogic) {
		super(x, y, "images/OldManNPC.png",gameLogic);
		health = 50;
		dx = speed;
		nextLevel = "levels/GoalLevel.xml";
		damageType = 1;
		blowResistance = 0.2;
		pierceResistance = 0.1;
		bluntResistance = -0.3;
		fireResistance = -0.3;
		arcaneResistance = 0.3;
	}
	
	private void shootFireballs(){
		if(castOrder == 1){
			gameLogic.addSprite(new Fireball((int)this.getX()-20,(int)this.getY(),gameLogic, this,-1.5,0,2));
			gameLogic.addSprite(new Fireball((int)(this.getX()-18.5),(int)(this.getY()+7.6),gameLogic, this,-0.57,0.33,2));
			gameLogic.addSprite(new Fireball((int)(this.getX()-14.1),(int)(this.getY()+14.1),gameLogic, this,-1.0575,1.0575,2));
			gameLogic.addSprite(new Fireball((int)(this.getX()-7.6),(int)(this.getY()+18.5),gameLogic, this,-0.33,0.57,2));
			gameLogic.addSprite(new Fireball((int)this.getX(),(int)this.getY()+20,gameLogic, this,0,1.5,2));
		}
		else if(castOrder == 2){
			gameLogic.addSprite(new Fireball((int)this.getX(),(int)this.getY()-20,gameLogic, this,0,-1.5,2));
			gameLogic.addSprite(new Fireball((int)(this.getX()-7.6),(int)(this.getY()-18.5),gameLogic, this,-0.33,-0.57,2));
			gameLogic.addSprite(new Fireball((int)(this.getX()-14.1),(int)(this.getY()-14.1),gameLogic, this,-1.0575,-1.0575,2));
			gameLogic.addSprite(new Fireball((int)(this.getX()-18.5),(int)(this.getY()-7.6),gameLogic, this,-0.57,-0.33,2));
			gameLogic.addSprite(new Fireball((int)this.getX()-20,(int)this.getY(),gameLogic, this,-1.5,0,2));
			
		}
		else if(castOrder == 3){
			gameLogic.addSprite(new Fireball((int)this.getX()+20,(int)this.getY(),gameLogic, this,1.5,0,2));
			gameLogic.addSprite(new Fireball((int)(this.getX()+18.5),(int)(this.getY()-7.6),gameLogic, this,0.57,-0.33,2));
			gameLogic.addSprite(new Fireball((int)(this.getX()+14.1),(int)(this.getY()-14.1),gameLogic, this,1.0575,-1.0575,2));
			gameLogic.addSprite(new Fireball((int)(this.getX()+7.6),(int)(this.getY()-18.5),gameLogic, this,0.33,-0.57,2));
			gameLogic.addSprite(new Fireball((int)this.getX(),(int)this.getY()-20,gameLogic, this,0,-1.5,2));
			
		}
		else if(castOrder == 4){
			gameLogic.addSprite(new Fireball((int)this.getX(),(int)this.getY()+20,gameLogic, this,0,1.5,2));
			gameLogic.addSprite(new Fireball((int)(this.getX()+7.6),(int)(this.getY()+18.5),gameLogic, this,0.33,0.57,2));
			gameLogic.addSprite(new Fireball((int)(this.getX()+14.1),(int)(this.getY()+14.1),gameLogic, this,1.0575,1.0575,2));
			gameLogic.addSprite(new Fireball((int)(this.getX()+18.5),(int)(this.getY()+7.6),gameLogic, this,0.57,0.33,2));
			gameLogic.addSprite(new Fireball((int)this.getX()+20,(int)this.getY(),gameLogic, this,1.5,0,2));
			
		}
		castOrder += 1;
		if(castOrder == 5)
			castOrder = 1;
		
	}
	@Override
	protected void handleMassiveEvent(CollisionEvent ce){
		if(ce.getDirection() == CollisionEvent.DIRECTION_VERTICAL && Math.signum(dy) == -1){
			shootFireballs();
			dx = speed;
			dy = 0;
		}
		if(ce.getDirection() == CollisionEvent.DIRECTION_VERTICAL && Math.signum(dy) == 1){
			shootFireballs();
			dx = -speed;
			dy = 0;
		}
		if(ce.getDirection() == CollisionEvent.DIRECTION_HORIZONTAL && Math.signum(dx) == 1 ){
			shootFireballs();
			dx = 0;
			dy = speed;
		}
		if(ce.getDirection() == CollisionEvent.DIRECTION_HORIZONTAL && Math.signum(dx) == -1 ){
			shootFireballs();
			dx = 0;
			dy = -speed;
		}
		handleEvents = false;
	}
	
	public void doLogic(long delta) {

			/*if(castOrder == 4){
				dy = 1;
				dx = 0;
			}
			else if(castOrder == 1){
				dy = 0;
				dx = -1;
			}
			else if(castOrder == 2){
				dy = -1;
				dx = 0;
			}
			else if(castOrder == 3){
				dy = 0;
				dx = 1;
			}*/
		
	}
	
	@Override
	public void doneKill(CombatObject combatObject){
		
	}

	@Override
	public void shootRemoved() {
		
	}
}
