package progprak.gruppe53;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;
import java.util.Vector;

public class LevelLoader {

	private String levelString = "";

	private int xDimension, yDimension;

	public LevelLoader(String levelPath) {
		loadLevelData(levelPath);
	}

	private boolean loadLevelData(String levelPath) {
		URL levelUrl = getClass().getClassLoader().getResource(levelPath);
		File levelFile;
		try {
			levelFile = new File(levelUrl.toURI());
			Scanner scanner = new Scanner(levelFile, "UTF-8");
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				levelString += line + "\n";
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		return true;
	}

	public void generateLevel(Vector<Sprite> sprites, Game game) {

		setDimensions();

		spawnHero(sprites, game);

		generateWalls(sprites, game);

	}

	private void generateWalls(Vector<Sprite> sprites, Game game) {
		int wallStart = levelString.indexOf("<wall>");
		int wallEnd = levelString.indexOf("</wall>");
		String wall = levelString.substring(wallStart + "<wall>".length(),
				wallEnd);
		System.out.println("Wände:" + wall);
		String wallArray[] = wall.split(",");
		for (int i = 0; i < wallArray.length; i++) {
			String wallData[] = wallArray[i].split(":");
			String wallDataX[] = wallData[0].split("-");
			String wallDataY[] = wallData[1].split("-");
			int xStart = Integer.parseInt(wallDataX[0]);
			int xEnd = Integer.parseInt(wallDataX[1]);
			int yStart = Integer.parseInt(wallDataY[0]);
			int yEnd = Integer.parseInt(wallDataY[1]);
			for (int j = xStart;j<=xEnd;j+=16){
				for (int k = yStart;k<=yEnd;k+=16){
					sprites.add(new Wall(j, k));
				}
			}
		}

	}

	private void setDimensions() {
		int dimensionStart = levelString.indexOf("<dimension>");
		int dimensionEnd = levelString.indexOf("</dimension>");
		String dimensions = levelString.substring(dimensionStart
				+ "<dimension>".length(), dimensionEnd);
		System.out.println("dimensions:" + dimensions);
		String dimensionArray[] = dimensions.split("x");
		xDimension = Integer.parseInt(dimensionArray[0]);
		yDimension = Integer.parseInt(dimensionArray[1]);
	}

	public Hero spawnHero(Vector<Sprite> sprites, Game game) {
		int spawnStart = levelString.indexOf("<spawn>");
		int spawnEnd = levelString.indexOf("</spawn>");
		String spawn = levelString.substring(spawnStart + "<spawn>".length(),
				spawnEnd);
		System.out.println("Spawnpunkt:" + spawn);
		String spawnArray[] = spawn.split(":");
		Hero hero;
		sprites.add(hero = new Hero(Integer.parseInt(spawnArray[0]), Integer
				.parseInt(spawnArray[1]), game, game.getKeyboardInput()));
		return hero;
	}

}
