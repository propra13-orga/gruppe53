package progprak.gruppe53.levelEditor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import progprak.gruppe53.game.EnemyGhost;
import progprak.gruppe53.game.FireballTrap;
import progprak.gruppe53.game.Goal;
import progprak.gruppe53.game.GroundTrap;
import progprak.gruppe53.game.LevelSwitch;
import progprak.gruppe53.game.PortalEntrance;
import progprak.gruppe53.game.Sprite;
import progprak.gruppe53.game.Wall;
import progprak.gruppe53.items.HealthPotion;

public class LevelSaver {
	private String fileName = "";
	private Vector<Sprite> sprites;

	public LevelSaver(String fileName, Vector<Sprite> sprites2) {
		this.fileName = fileName;
		this.sprites = sprites2;
	}


	public void saveLevel() {
		String input = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" + "\n"+ "<level>" + "\n";
		input += createSpawnXml();
		input += createEnemyGhostXml();
		input += createTrapXml();
		input += createFireballTrapXml();
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
		Aussenwände -  hier nicht mehr nötig
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
	Für später um mehrere Goals möglich zu machen
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
	
	private String createLevelSwitchXml() {
		String levelSwitchXml = "";
		for (int i=0; i < sprites.size(); i++) {
			Sprite sprite = sprites.get(i);
			if (sprite instanceof LevelSwitch) {
				int x = (int)sprite.getX();
				int y = (int)sprite.getY();
				levelSwitchXml += LevelEditor.saveData[x][y];
			}
		}
		return levelSwitchXml;
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
}
