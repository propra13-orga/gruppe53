package progprak.gruppe53;

import java.util.ListIterator;
import java.util.Vector;

public class GameLogic {
	
	private Vector<Sprite> sprites;
	private Game game;
	
	public Hero hero;
	

	public GameLogic(Game game) {
		sprites =  new Vector<Sprite>();
		this.game = game;
		doInitalizations();
	}
	
	private void doInitalizations(){
		//Horizontal Walls
		for(int i=0;i<21;i++)
			sprites.add(new Wall(100 +i*16 ,100));
		for(int i=0;i<21;i++)
			sprites.add(new Wall(100 +i*16 ,244));
		//for(int i=0;i<7;i++)
		//	sprites.add(new Wall(132 +i*16 ,132));		
		//Vertical Walls
		for(int i=0;i<8;i++)
			sprites.add(new Wall(100, 116 +i*16));
		for(int i=0;i<6;i++)
			sprites.add(new Wall(260, 116 +i*16));
		for(int i=0;i<8;i++)
			sprites.add(new Wall(420, 116 +i*16));

		
		sprites.add(new PortalEntrance(244,116,380,200));
		sprites.add(hero = new Hero(180,180,game));
		sprites.add(new Trap(120,145));
		sprites.add(new Enemy(200,200));

	}
	
	//Do Logics for every Sprite-Object
	public void doLogic(long delta){
		for(ListIterator<Sprite> it = sprites.listIterator();it.hasNext();){
			Sprite s = it.next();
			s.doLogic(delta);
		}
	}
	public void move(long delta){
		for(ListIterator<Sprite> it = sprites.listIterator();it.hasNext();){
			Sprite s = it.next();
			s.move(delta);
		}
	}
	public Vector<Sprite> getSprites() {
		return sprites;
	}
}