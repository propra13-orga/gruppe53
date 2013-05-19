package progprak.gruppe53;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;
import java.util.Vector;

public class LevelLoader {

	private String levelString = "";
	
	public boolean loadLevel(String levelPath, Vector<Sprite> sprites) {
		if(!loadLevelData(levelPath)){return false;}
		System.out.println(levelString);
		generateLevel(sprites);
		return true;
	}
	private boolean loadLevelData(String levelPath) {
		URL levelUrl = getClass().getClassLoader().getResource(levelPath);
		File levelFile;		 
		try {
			levelFile = new File(levelUrl.toURI());
			Scanner scanner = new Scanner(levelFile, "UTF-8");
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				levelString += line + "\n";
			}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
			return false;
		}
		catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();			
			return false;
		}
		return true;
	}
	private void generateLevel(Vector<Sprite> sprites) {
		int dimensionStart = levelString.indexOf("<dimension>");
		int dimensionEnd = levelString.indexOf("</dimension>");
		String dimensions = levelString.substring(dimensionStart + "<dimension>".length(), dimensionEnd);
		System.out.println("dimensions:" + dimensions);
		String dimensions2 [] = dimensions.split("x");
	}
	
}
