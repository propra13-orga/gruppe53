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
		hero = new Hero(0, 0, game);
		//new LevelLoader("levels/Level1.xml").generateLevel(sprites, game);
		
		/*
		//Horizontal Walls
		for(int i=0;i<21;i++)
			sprites.add(new Wall(100 +i*16 ,100));
		for(int i=0;i<21;i++)
			sprites.add(new Wall(100 +i*16 ,244));
		for(int i=0;i<21;i++)
			sprites.add(new Wall(100 +i*16 ,372));
		
		
		//Vertical Walls
		for(int i=0;i<8;i++)
			sprites.add(new Wall(100, 116 +i*16));
		for(int i=0;i<8;i++)
			sprites.add(new Wall(100, 260 +i*16));
		for(int i=0;i<8;i++)
			sprites.add(new Wall(260, 116 +i*16));
		for(int i=0;i<8;i++)
			sprites.add(new Wall(260, 260 +i*16));
		for(int i=0;i<8;i++)
			sprites.add(new Wall(420, 116 +i*16));
		for(int i=0;i<8;i++)
			sprites.add(new Wall(420, 260 +i*16));
		
		//Background
		for(int i=0;i<8;i++)
			sprites.add(new Ground(116 +i*16, 116));
		for(int i=0;i<8;i++) 
			sprites.add(new Ground(292 +i*16, 116));
		for(int j=0;j<7;j++){
		for(int i=0;i<9;i++)
			sprites.add(new Ground(116 +i*16, 132 +j*16));
		for(int i=0;i<9;i++)
			sprites.add(new Ground(276 +i*16, 132 +j*16));
		}
		for(int i=0;i<8;i++)
			sprites.add(new Ground(116 +i*16, 260));
		for(int i=0;i<8;i++)
			sprites.add(new Ground(292 +i*16, 260));
		for(int j=0;j<6;j++){
			for(int i=0;i<9;i++)
				sprites.add(new Ground(116 +i*16, 276 +j*16));
			for(int i=0;i<9;i++)
				sprites.add(new Ground(276 +i*16, 276 +j*16));
		}
		
		sprites.add(new PortalEntrance(244,116,403,227));
		sprites.add(new PortalEntrance(276,116,117,355));
		sprites.add(new PortalEntrance(244,260,403,355));
		sprites.add(new Goal(276,260));
		sprites.add(hero = new Hero(180,180,game));
		sprites.add(new Trap(120,145));
		sprites.add(new Trap(350,145));
		sprites.add(new Enemy(150,290));
		*/
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

	public void switchLevel(String newLevel) {
		Vector<Sprite> sp = new Vector<Sprite>();
		new LevelLoader(newLevel).generateLevel(sp, game);
		sprites = sp;
	}

	public Hero getHero() {
		return hero;
	}
}