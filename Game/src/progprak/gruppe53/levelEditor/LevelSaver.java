package progprak.gruppe53.levelEditor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import progprak.gruppe53.items.HealthPotion;
import progprak.gruppe53.sprites.EnemyGhost;
import progprak.gruppe53.sprites.FireballTrap;
import progprak.gruppe53.sprites.FireballWaveTrap;
import progprak.gruppe53.sprites.Goal;
import progprak.gruppe53.sprites.GroundTrap;
import progprak.gruppe53.sprites.LevelSwitch;
import progprak.gruppe53.sprites.PortalEntrance;
import progprak.gruppe53.sprites.Sprite;
import progprak.gruppe53.sprites.Wall;
import progprak.gruppe53.sprites.WallLevelSwitch;

public class LevelSaver {
	private String fileName = "";
	private ArrayList<Sprite> sprites;

	public LevelSaver(String fileName, ArrayList<Sprite> sprites2) {
		this.fileName = fileName;
		this.sprites = sprites2;
	}


	public void saveLevel() {
		String input = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" + "\n"+ "<level>" + "\n";
		input += createSpawnXml();
		input += createEnemyGhostXml();
		input += createTrapXml();
		input += createFireballTrapXml();
		input += createFireballWaveTrapXml();
		input += createPortalXml();
		input += createHealthPotionXml();
		input += createGoalXml();
		input += createLevelSwitchXml();
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
		String enemyGhostXml = "	<enemies>" + "\n";
		for (int i=0; i < sprites.size(); i++) {
			Sprite sprite = sprites.get(i);
			if (sprite instanceof EnemyGhost) {
				enemyGhostXml += "		<enemy type=\"ghost\">" + ((int)sprite.getX()) + ":" + ((int)sprite.getY()) + "</enemy>" +"\n";
			}
		}
		enemyGhostXml += "	</enemies>";
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
		/*
		Aussenwaende -  hier nicht mehr noetig
		String wallXml += "		<wall>0:0-608</wall>" + "\n" + "		<wall>768:0-608</wall>" + "\n" + "		<wall>32-736:0</wall>" + "\n" + "		<wall>32-736:608</wall>" + "\n"
		*/
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
	
	private String createFireballTrapXml() {
		String fireballTrapXml = "	<fireballtraps>" + "\n";
		for (int i=0; i < sprites.size(); i++) {
			Sprite sprite = sprites.get(i);
			if (sprite instanceof FireballTrap) {
				int x = (int)sprite.getX();
				int y = (int)sprite.getY();
				fireballTrapXml += LevelEditor.saveData[x][y];
			}
		}
		fireballTrapXml += "	</fireballtraps>";
		fireballTrapXml += "\n";
		return fireballTrapXml;
	}
	
	/* 
	Fuer spaeter um mehrere Goals moeglich zu machen
	private String createGoalXml() {
		String goalXml = "	<goals>" + "\n";
		for (int i=0; i < sprites.size(); i++) {
			Sprite sprite = sprites.get(i);
			if (sprite instanceof Goal) {
				goalXml += "		<goal>" + ((int)sprite.getX()) + ":" + ((int)sprite.getY()) + "</goal>" +"\n";
			}
		}
		goalXml += "	</goals>";
		goalXml += "\n";
		return goalXml;
	}
	*/
	
	private String createGoalXml() {
		String goalXml = "	<goal>";
		for (int i = 0; i < sprites.size(); i++) {
			Sprite sprite = sprites.get(i);
			if (sprite instanceof Goal) {
				goalXml += ((int)sprite.getX()) + ":" + ((int)sprite.getY());
			}
		}
		goalXml += "</goal>";
		goalXml += "\n";
		return goalXml;
	}
	
	private String createPortalXml() {
		String portalXml = "	<portals>" + "\n";
		for (int i=0; i < sprites.size(); i++) {
			Sprite sprite = sprites.get(i);
			if (sprite instanceof PortalEntrance) {
				int x = (int)sprite.getX();
				int y = (int)sprite.getY();
				portalXml += LevelEditor.saveData[x][y];
			}
		}
		portalXml += "	</portals>";
		portalXml += "\n";
		return portalXml;
	}
	private String createHealthPotionXml() {
		String healthPotionXml = "	<healthpotions>" + "\n";
		for (int i=0; i < sprites.size(); i++) {
			Sprite sprite = sprites.get(i);
			if (sprite instanceof HealthPotion) {
				healthPotionXml += "		<healthpotion>" + ((int)sprite.getX()) + ":" + ((int)sprite.getY()) + "</healthpotion>" +"\n";
			}
		}
		healthPotionXml += "	</healthpotions>";
		healthPotionXml += "\n";
		return healthPotionXml;
	}
	private String createLevelSwitchXml() {
		String levelSwitchXml = "";//	<levelswitches>" + "\n";
		for (int i=0; i < sprites.size(); i++) {
			Sprite sprite = sprites.get(i);
			if (sprite instanceof WallLevelSwitch) {
				int x = (int)sprite.getX();
				int y = (int)sprite.getY();
				levelSwitchXml += LevelEditor.saveData[x][y];
			}
			else if (sprite instanceof LevelSwitch) {
				int x = (int)sprite.getX();
				int y = (int)sprite.getY();
				levelSwitchXml += LevelEditor.saveData[x][y];
			}
		}
		//levelSwitchXml += "	</levelswitches>";
		//levelSwitchXml += "\n";
		return levelSwitchXml;
	}
	private String createFireballWaveTrapXml() {
		String fireballWaveTrapXml = "	<fireballwavetraps>" + "\n";
		for (int i=0; i < sprites.size(); i++) {
			Sprite sprite = sprites.get(i);
			if (sprite instanceof FireballWaveTrap) {
				int x = (int)sprite.getX();
				int y = (int)sprite.getY();
				fireballWaveTrapXml += LevelEditor.saveData[x][y];
			}
		}
		fireballWaveTrapXml += "	</fireballwavetraps>";
		fireballWaveTrapXml += "\n";
		return fireballWaveTrapXml;
	}
}
