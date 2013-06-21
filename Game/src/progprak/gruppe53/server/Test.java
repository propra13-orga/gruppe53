package progprak.gruppe53.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import progprak.gruppe53.sprites.Sprite;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectOutputStream oos;
		ObjectInputStream ios;
		File file = new File("test");
		oos = new ObjectOutputStream(new FileOutputStream(file));
		Sprite r;
		Sprite s = new Sprite(0, 0, null) {
			
			@Override
			public void doLogic(long delta) {
				// TODO Auto-generated method stub
				
			}
		};
		oos.writeObject(s);
		s.setX(1);
		//oos.writeObject(s);
		s.setX(2);
		//oos.writeObject(s);
		oos.close();
		
		ios = new ObjectInputStream(new FileInputStream(file));
		
		r = (Sprite) ios.readObject();
		//System.out.println(r);
		r = (Sprite) ios.readObject();
		//System.out.println(r);
		ios.close();
		/*GameLogic gameLogic = new GameLogic();
		gameLogic.addHero(new Hero(0, 0, gameLogic));
		gameLogic.switchLevel("levels/TestLevel.xml");
		Player player = new Player();
		Sprite s,r;
		int i = 0;
		try {
			while(true){
				oos = new ObjectOutputStream(new FileOutputStream(file));
				ios = new ObjectInputStream(new FileInputStream(file));
				s = gameLogic.getActors().get(0);
				gameLogic.tick(0, player);
				System.out.println(s);
				oos.writeObject(s);
				r = (Sprite) ios.readObject();
				System.out.println(r);
				oos.close();
				ios.close();
				//r.test();
				//System.exit(0);
			}
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}

}
