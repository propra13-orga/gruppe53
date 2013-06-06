package levelEditor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import progprak.gruppe53.EnemyGhost;
import progprak.gruppe53.GroundTrap;
import progprak.gruppe53.Sprite;
import progprak.gruppe53.Wall;

public class LevelSaver {
	private String fileName = "";
	private Vector<Sprite> sprites;

	public LevelSaver(String fileName, Vector<Sprite> sprites) {
		this.fileName = fileName;
		this.sprites = sprites;
	}

	public void saveLevel() {
		String input = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" + "\n"+ "<level>" + "\n";
		input += createSpawnXml();
		input += createEnemyGhostXml();
		input += createTrapXml();
		input += createWallXml();
		input += "</level>";
		createFile(input);

	}

	private void createFile(String input) {
		File file = new File("./Game/src/levels/" + fileName + ".xml");
		try {
			file.createNewFile();
			FileWriter fstream = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(input);
			// Close the output stream
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String createSpawnXml() {
		String spawnXml = "	<spawn>";
		for (int i = 0; i < sprites.size(); i++) {
			Sprite sprite = sprites.get(i);
			if (sprite instanceof HeroEditor) {
				spawnXml += ((int)sprite.getX()) + ":" + ((int)sprite.getY());
			}
		}
		spawnXml += "</spawn>";
		spawnXml += "\n";
		return spawnXml;
	}
	
	private String createEnemyGhostXml() {
		String enemyGhostXml = "	<enemys>" + "\n";
		for (int i=0; i < sprites.size(); i++) {
			Sprite sprite = sprites.get(i);
			if (sprite instanceof EnemyGhost) {
				enemyGhostXml += "		<enemy type=\"ghost\">" + ((int)sprite.getX()) + ":" + ((int)sprite.getY()) + "</enemy>" +"\n";
			}
		}
		enemyGhostXml += "	</enemys>";
		enemyGhostXml += "\n";
		return enemyGhostXml;
	}
	private String createTrapXml() {
		String trapXml = "	<traps>" + "\n";
		for (int i=0; i < sprites.size(); i++) {
			Sprite sprite = sprites.get(i);
			if (sprite instanceof GroundTrap) {
				trapXml += "		<trap>" + ((int)sprite.getX()) + ":" + ((int)sprite.getY()) + "</trap>" +"\n";
			}
		}
		trapXml += "	</traps>";
		trapXml += "\n";
		return trapXml;
	}
	private String createWallXml() {
		String wallXml = "	<walls>" + "\n";
		for (int i=0; i < sprites.size(); i++) {
			Sprite sprite = sprites.get(i);
			if (sprite instanceof Wall) {
				wallXml += "		<wall>" + ((int)sprite.getX()) + ":" + ((int)sprite.getY()) + "</wall>" +"\n";
			}
		}
		wallXml += "	</walls>";
		wallXml += "\n";
		return wallXml;
	}
}
