package progprak.gruppe53.levelEditor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import progprak.gruppe53.items.potions.HealthPotion;
import progprak.gruppe53.sprites.Sprite;
import progprak.gruppe53.sprites.enemies.EnemyGhost;
import progprak.gruppe53.sprites.enemies.EnemyOldManNPC;
import progprak.gruppe53.sprites.enemies.EnemySpider;
import progprak.gruppe53.sprites.objects.Goal;
import progprak.gruppe53.sprites.objects.GroundTrap;
import progprak.gruppe53.sprites.objects.LevelSwitch;
import progprak.gruppe53.sprites.objects.PortalEntrance;
import progprak.gruppe53.sprites.objects.PressurePlate;
import progprak.gruppe53.sprites.objects.Wall;
import progprak.gruppe53.sprites.objects.WallLevelSwitch;
import progprak.gruppe53.sprites.projectils.FireballTrap;
import progprak.gruppe53.sprites.projectils.FireballWaveTrap;

/** 
 * The class that creates the level files 
 */
public class LevelSaver {
	private String fileName = "";
	private ArrayList<Sprite> sprites;

	/** 
	 * The contructor for the LevelSaver class
	 * @param fileName The name the level should be saved under
	 * @param sprites2 The list of all sprites in the level
	 */
	public LevelSaver(String fileName, ArrayList<Sprite> sprites2) {
		this.fileName = fileName;
		this.sprites = sprites2;
	}


	/** 
	 * The method that combines all created text into one
	 */
	public void saveLevel() {
		String input = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" + "\n"+ "<level>" + "\n";
		input += createSpawnXml();
		input += createEnemyGhostXml();
		input += createEnemyOldManNPCXml();
		input += createEnemySpiderXml();
		input += createTrapXml();
		input += createFireballTrapXml();
		input += createFireballTrap2Xml();
		input += createFireballWaveTrapXml();
		input += createPortalXml();
		input += createHealthPotionXml();
		input += createGoalXml();
		input += createLevelSwitchXml();
		input += createPressurePlateXml();
		input += createWallXml();
		input += "</level>";
		createFile(input);

	}

	/** 
	 * The method that creates the level file
	 * @param input The input created by the saveLevel method
	 */
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
	/** 
	 * Creates the string required to save pressureplates
	 * @return The String for pressureplates
	 */
	private String createPressurePlateXml() {
			String pressurePlateXml = " <pressureplates>" + "\n";
			for (int i=0; i < sprites.size(); i++) {
				Sprite sprite = sprites.get(i);
				if (sprite instanceof PressurePlate) {
					int x = (int)sprite.getX();
					int y = (int)sprite.getY();
					pressurePlateXml += LevelEditor.saveData[x][y];
				}
			}
			pressurePlateXml += "</pressureplates>";
			pressurePlateXml += "\n";
			return pressurePlateXml;
				
	}

	/** 
	 * Creates the string required to save the spawn
	 * @return The String for the spawn
	 */
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
	
	/** 
	 * Creates the string required to save ghost enemies
	 * @return The String for ghost enemies
	 */
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
	
	/** 
	 * Creates the string required to save old man enemies
	 * @return The String for old man enemies
	 */
	private String createEnemyOldManNPCXml() {
		String enemyOldManNPCXml = "	<enemies>" + "\n";
		for (int i=0; i < sprites.size(); i++) {
			Sprite sprite = sprites.get(i);
			if (sprite instanceof EnemyOldManNPC) {
				enemyOldManNPCXml += "		<enemy type=\"oldman\">" + ((int)sprite.getX()) + ":" + ((int)sprite.getY()) + "</enemy>" +"\n";
			}
		}
		enemyOldManNPCXml += "	</enemies>";
		enemyOldManNPCXml += "\n";
		return enemyOldManNPCXml;
	}
	
	/** 
	 * Creates the string required to save spider enemies
	 * @return The String for spider enemies
	 */
	private String createEnemySpiderXml() {
		String enemySpiderXml = "	<enemies>" + "\n";
		for( int i=0; i<sprites.size();i++) {
			Sprite sprite = sprites.get(i);
			if( sprite instanceof EnemySpider) {
				enemySpiderXml += "		<enemy type=\"spider\">" + ((int)sprite.getX()) + ":" + ((int)sprite.getY()) + "</enemy>" + "\n";
			}
		}
		enemySpiderXml += "  </enemies>";
		enemySpiderXml += "\n";
		return enemySpiderXml;
	}
	
	/** 
	 * Creates the string required to save traps
	 * @return The String for traps
	 */
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
	
	/** 
	 * Creates the string required to save walls
	 * @return The String for walls
	 */
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
	
	/** 
	 * Creates the string required to save fireballtraps
	 * @return The String for fireballtraps
	 */
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
	
	/** 
	 * Creates the string required to save advanced fireballtraps
	 * @return The String for advanced fireballtraps
	 */
	private String createFireballTrap2Xml() {
		String fireballTrap2Xml = "	<fireballtraps2>" + "\n";
		for (int i=0; i < sprites.size(); i++) {
			Sprite sprite = sprites.get(i);
			if (sprite instanceof FireballTrap) {
				int x = (int)sprite.getX();
				int y = (int)sprite.getY();
				fireballTrap2Xml += LevelEditor.saveData[x][y];
			}
		}
		fireballTrap2Xml += "	</fireballtraps2>";
		fireballTrap2Xml += "\n";
		return fireballTrap2Xml;
	}
	
	/** 
	 * Creates the string required to save the goal
	 * @return The String for the goal
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
	
	/** 
	 * Creates the string required to save portals
	 * @return The String for portals
	 */
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
	
	/** 
	 * Creates the string required to save potions
	 * @return The String for potions
	 */
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
	
	/** 
	 * Creates the string required to save the levelswitch
	 * @return The String for the levelswitch
	 */
	private String createLevelSwitchXml() {
		String levelSwitchXml = "";
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
		return levelSwitchXml;
	}
	
	/** 
	 * Creates the string required to save fireballwavetraps
	 * @return The String for fireballwavetraps
	 */
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
