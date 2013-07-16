package progprak.gruppe53.game;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import progprak.gruppe53.items.ClothArmor;
import progprak.gruppe53.items.ChainArmor;
import progprak.gruppe53.items.LeatherArmor;
import progprak.gruppe53.items.PlateArmor;
import progprak.gruppe53.items.HealthPotion;
import progprak.gruppe53.items.PinkGlitterWand;
import progprak.gruppe53.items.WoodenSword;
import progprak.gruppe53.items.SteelSword;
import progprak.gruppe53.items.IronSword;
import progprak.gruppe53.items.StoneSword;
import progprak.gruppe53.items.CasterSword;
import progprak.gruppe53.sprites.ChargingBoss;
import progprak.gruppe53.sprites.SpawnerBoss;
import progprak.gruppe53.sprites.EnemyGhost;
import progprak.gruppe53.sprites.EnemyOldManNPC;
import progprak.gruppe53.sprites.EnemySpider;
import progprak.gruppe53.sprites.EnemyCharger;
import progprak.gruppe53.sprites.EnemyWizard;
import progprak.gruppe53.sprites.EnemyNecromancer;
import progprak.gruppe53.sprites.FireballTrap;
import progprak.gruppe53.sprites.FireballTrap2;
import progprak.gruppe53.sprites.FireballWaveTrap;
import progprak.gruppe53.sprites.Goal;
import progprak.gruppe53.sprites.GroundTrap;
import progprak.gruppe53.sprites.Hero;
import progprak.gruppe53.sprites.LevelSwitch;
import progprak.gruppe53.sprites.NecromancerBoss;
import progprak.gruppe53.sprites.OldManNPC;
import progprak.gruppe53.sprites.OldManNPCBoss;
import progprak.gruppe53.sprites.PortalEntrance;
import progprak.gruppe53.sprites.PressurePlate;
import progprak.gruppe53.sprites.Sprite;
import progprak.gruppe53.sprites.Wall;
import progprak.gruppe53.sprites.WallLevelSwitch;
import progprak.gruppe53.sprites.WizardBoss;


/** 
 * The LevelLoader
 * Loads the levels from the textfiles whenever
 * the game switches to a new level
 */
public class LevelLoaderSax extends DefaultHandler {

	private ArrayList<Sprite> sprites;
	private GameLogic gameLogic;
	private StringBuilder elementName;
	private Attributes attributes;
	private static ArrayList<Point2D> affectedWallsArrayList = new ArrayList<Point2D>();
	private static Point2D affectedWallsArray [] = null;

	/** 
	 * The constructor for the LevelLoader
	 * @param sp The list of sprites
	 * @param gameLogic the gameLogic / Loop
	 */
	public LevelLoaderSax(ArrayList<Sprite> sp, GameLogic gameLogic) {
		this.sprites = sp;
		this.gameLogic = gameLogic;
	}
	
	/** 
	 * starts up the level generation process
	 * @param level The levelfile to load from
	 * @param sp The list of sprites
	 * @param gameLogic the gameLogic / Loop
	 */
	public static void generateLevel(String level, ArrayList<Sprite> sp,
			GameLogic gameLogic) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new LevelLoaderSax(sp, gameLogic);

			saxParser.parse(
					LevelLoaderSax.class.getClassLoader().getResource(level)
							.getFile(), handler);

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * The endDocument method for SAX
	 */
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}
	
	/** 
	 * The endElement method for SAX
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		int start = elementName.length() - qName.length() - 1;
		if (start < 0)
			start = 0;
		elementName.delete(start, elementName.length());
	}

	/** 
	 * The startDocument method for SAX
	 */
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		elementName = new StringBuilder();
	}

	/** 
	 * The startElement method for SAX
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		this.attributes = attributes;
		if (elementName.length() == 0) {
			elementName.append(qName.toString());
		} else {
			elementName.append("." + qName.toString());
		}
	}

	/** 
	 * The characters method for SAX
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		String content = new String(ch, start, length);
		switch (elementName.toString()) {
		case "level.walls.wall":
			spawnWall(content);
			break;
		case "level.enemies.enemy":
			spawnEnemy(content);
			break;
		case "level.traps.trap":
			spawnTrap(content);
			break;
		case "level.fireballtraps.fireballtrap":
			spawnFireballTrap(content);
			break;
		case "level.fireballtraps2.fireballtrap2":
			spawnFireballTrap2(content);
			break;
		case "level.portals.portal":
			spawnPortal(content);
			break;
		case "level.pressureplates.pressureplate":
			spawnPressurePlate(content);
			break;
		case "level.levelswitch":
			spawnLevelSwitch(content);
			break;
		case "level.goal":
			setGoal(content);
			break;
		case "level.spawn":
			setHeroSpawnPoint(content);
			break;
		case "level.npc":
			setNpc(content);
			break;
		case "level.healthpotions.healthpotion":
			spawnHealthPotion(content);
			break;
		case "level.fireballwavetraps.fireballwavetrap":
			spawnFireballWaveTrap(content);
			break;
		case "level.items.item":
			spawnItem(content);
			break;
		default:
			break;
		}
	}

	/** 
	 * creates Pressureplates
	 * @param content the parsed content of the levelfile 
	 */
	private void spawnPressurePlate(String content) {
		String pressurePlateData[] = content.split("-");
		String wallCoordinates [] = pressurePlateData[1].split(";");
		String plateLocation [] = pressurePlateData[0].split(":");
		int xLocation = Integer.parseInt(plateLocation[0]);
		int yLocation = Integer.parseInt(plateLocation[1]);
		 for (int i=0;i<wallCoordinates.length;i++) {
			 String location [] = wallCoordinates [i].split(":");
			 int x = Integer.parseInt(location [0]);
			 int y = Integer.parseInt(location [1]);
			  Point2D obj = new Point2D.Double(x,y);
			 affectedWallsArrayList.add(obj);
		}
		affectedWallsArray = affectedWallsArrayList.toArray(new Point2D[affectedWallsArrayList.size()]);
		affectedWallsArrayList.clear();
		sprites.add(new PressurePlate(xLocation, yLocation, affectedWallsArray));
	}

	/** 
	 * creates all equipment
	 * @param content the parsed content of the levelfile 
	 */
	private void spawnItem(String content) {
		String itemEntry[] = content.split(":");
		int xLocation = Integer.parseInt(itemEntry[0]);
		int yLocation = Integer.parseInt(itemEntry[1]);
		if (attributes.getValue("type").equals("pinkglitterwand")) {
			sprites.add(new PinkGlitterWand(xLocation, yLocation, gameLogic));
		} else if (attributes.getValue("type").equals("woodensword")) {
			sprites.add(new WoodenSword(xLocation, yLocation, gameLogic));
		} else if (attributes.getValue("type").equals("steelsword")) {
			sprites.add(new SteelSword(xLocation, yLocation, gameLogic));
		} else if (attributes.getValue("type").equals("ironsword")) {
			sprites.add(new IronSword(xLocation, yLocation, gameLogic));
		} else if (attributes.getValue("type").equals("stonesword")) {
			sprites.add(new StoneSword(xLocation, yLocation, gameLogic));
		} else if (attributes.getValue("type").equals("castersword")) {
			sprites.add(new CasterSword(xLocation, yLocation, gameLogic));
		} else if (attributes.getValue("type").equals("clotharmor")) {
			sprites.add(new ClothArmor(xLocation, yLocation, gameLogic));
		} else if (attributes.getValue("type").equals("chainarmor")) {
			sprites.add(new ChainArmor(xLocation, yLocation, gameLogic));
		} else if (attributes.getValue("type").equals("leatherarmor")) {
			sprites.add(new LeatherArmor(xLocation, yLocation, gameLogic));
		} else if (attributes.getValue("type").equals("platearmor")) {
			sprites.add(new PlateArmor(xLocation, yLocation, gameLogic));
		}
	}

	/** 
	 * creates NPCs
	 * @param content the parsed content of the levelfile 
	 */
	private void setNpc(String content) {
		String npcData[] = content.split(";");
		String npcEntry[] = npcData[0].split(":");
		int xLocation = Integer.parseInt(npcEntry[0]);
		int yLocation = Integer.parseInt(npcEntry[1]);
		sprites.add(new OldManNPC(xLocation, yLocation, gameLogic, npcData[1]));
	}

	/** 
	 * creates the goal
	 * @param content the parsed content of the levelfile 
	 */
	private void setGoal(String content) {
		String goalEntry[] = content.split(":");
		int xLocation = Integer.parseInt(goalEntry[0]);
		int yLocation = Integer.parseInt(goalEntry[1]);
		sprites.add(new Goal(xLocation, yLocation));
	}

	/** 
	 * creates portals
	 * @param content the parsed content of the levelfile 
	 */
	private void spawnPortal(String content) {
		String portalData[] = content.split(";");
		String portalEntry[] = portalData[0].split(":");
		String portalExit[] = portalData[1].split(":");
		int startLocationX = Integer.parseInt(portalEntry[0]);
		int startLocationY = Integer.parseInt(portalEntry[1]);
		int endLocationX = Integer.parseInt(portalExit[0]);
		int endLocationY = Integer.parseInt(portalExit[1]);
		sprites.add(new PortalEntrance(startLocationX, startLocationY,
				endLocationX, endLocationY));
	}

	/** 
	 * sets the spawnpoint
	 * @param content the parsed content of the levelfile 
	 */
	private void setHeroSpawnPoint(String content) {
		String spawnArray[] = content.split(":");
		Hero hero = gameLogic.getHero();
		hero.setXSpawn(Integer.parseInt(spawnArray[0]));
		hero.setYSpawn(Integer.parseInt(spawnArray[1]));
		// sprites.add(hero);
	}

	/** 
	 * creates a levelswitch / door
	 * @param content the parsed content of the levelfile 
	 */
	private void spawnLevelSwitch(String content) {
		String levelSwitchData[] = content.split(";");
		String levelSwitchLocation[] = levelSwitchData[0].split(":");
		String levelSwitchNewLevelPath = levelSwitchData[1];
		int xLocation = Integer.parseInt(levelSwitchLocation[0]);
		int yLocation = Integer.parseInt(levelSwitchLocation[1]);
		if (attributes.getValue("type").equals("wall")) {
			sprites.add(new WallLevelSwitch(xLocation, yLocation, Integer
					.parseInt(attributes.getValue("direction")),
					levelSwitchNewLevelPath));
		} else {
			sprites.add(new LevelSwitch(xLocation, yLocation,
					levelSwitchNewLevelPath));
		}
	}

	/** 
	 * creates fireballTraps that spawns fireballs
	 * @param content the parsed content of the levelfile 
	 */
	private void spawnFireballTrap(String content) {
		String fireballTrapData[] = content.split(";");
		String fireballTrapLocation[] = fireballTrapData[0].split(":");
		String fireballTrapMovement[] = fireballTrapData[1].split(":");
		// String fireballTrapRespawn[] = fireballTrapData[2].split(":");
		int xLocation = Integer.parseInt(fireballTrapLocation[0]);
		int yLocation = Integer.parseInt(fireballTrapLocation[1]);
		double xMovement = Double.parseDouble(fireballTrapMovement[0]);
		double yMovement = Double.parseDouble(fireballTrapMovement[1]);
		// int xRespawn = Integer.parseInt(fireballTrapRespawn[0]);
		// int yRespawn = Integer.parseInt(fireballTrapRespawn[1]);
		sprites.add(new FireballTrap(xLocation, yLocation, gameLogic,
				xMovement, yMovement));
	}

	/** 
	 * creates more customizable versions of the fireballTrap
	 * @param content the parsed content of the levelfile 
	 */
	private void spawnFireballTrap2(String content) {
		String fireballTrapData[] = content.split(";");
		String fireballTrapLocation[] = fireballTrapData[0].split(":");
		String fireballTrapMovement[] = fireballTrapData[1].split(":");
		String fireballTrapRespawn[] = fireballTrapData[2].split(":");
		int xLocation = Integer.parseInt(fireballTrapLocation[0]);
		int yLocation = Integer.parseInt(fireballTrapLocation[1]);
		double xMovement = Double.parseDouble(fireballTrapMovement[0]);
		double yMovement = Double.parseDouble(fireballTrapMovement[1]);
		int xRespawn = Integer.parseInt(fireballTrapRespawn[0]);
		int yRespawn = Integer.parseInt(fireballTrapRespawn[1]);
		sprites.add(new FireballTrap2(xLocation, yLocation, gameLogic,
				xMovement, yMovement, xRespawn, yRespawn));
	}

	/** 
	 * creates groundTraps
	 * @param content the parsed content of the levelfile 
	 */
	private void spawnTrap(String content) {
		String trapData[] = content.split(":");
		int trapX = Integer.parseInt(trapData[0]);
		int trapY = Integer.parseInt(trapData[1]);
		sprites.add(new GroundTrap(trapX, trapY, gameLogic));
	}

	/** 
	 * creates all enemies
	 * @param content the parsed content of the levelfile 
	 */
	private void spawnEnemy(String content) {
		String enemyData[] = content.split(":");
		int enemyX = Integer.parseInt(enemyData[0]);
		int enemyY = Integer.parseInt(enemyData[1]);
		if (attributes.getValue("type").equals("ghost")) {
			sprites.add(new EnemyGhost(enemyX, enemyY, gameLogic));
		} else if (attributes.getValue("type").equals("spider")) {
			sprites.add(new EnemySpider(enemyX, enemyY, gameLogic));
		} else if (attributes.getValue("type").equals("oldman")) {
			sprites.add(new EnemyOldManNPC(enemyX, enemyY, gameLogic));
		} else if (attributes.getValue("type").equals("wizard")) {
			sprites.add(new EnemyWizard(enemyX, enemyY, gameLogic));
		} else if (attributes.getValue("type").equals("charger")) {
			sprites.add(new EnemyCharger(enemyX, enemyY, gameLogic));
		} else if (attributes.getValue("type").equals("necromancer")) {
			sprites.add(new EnemyNecromancer(enemyX, enemyY, gameLogic));
		} else if (attributes.getValue("type").equals("wizboss")) {
			sprites.add(new WizardBoss(enemyX, enemyY, gameLogic));
		} else if (attributes.getValue("type").equals("chargeboss")) {
			sprites.add(new ChargingBoss(enemyX, enemyY, gameLogic));
		} else if (attributes.getValue("type").equals("necboss")) {
			sprites.add(new NecromancerBoss(enemyX, enemyY, gameLogic));
		} else if (attributes.getValue("type").equals("oldmanboss")) {
			sprites.add(new OldManNPCBoss(enemyX, enemyY, gameLogic));
		} else if (attributes.getValue("type").equals("spawnboss")) {
			sprites.add(new SpawnerBoss(enemyX, enemyY, gameLogic));
		}

	}

	/** 
	 * creates the walls
	 * @param content the parsed content of the levelfile 
	 */
	private void spawnWall(String content) {
		String wallData[] = content.split(":");
		String wallDataX[] = wallData[0].split("-");
		String wallDataY[] = wallData[1].split("-");
		int xStart, xEnd;
		if (wallDataX.length == 1) {
			xStart = xEnd = Integer.parseInt(wallDataX[0]);
		} else {
			xStart = Integer.parseInt(wallDataX[0]);
			xEnd = Integer.parseInt(wallDataX[1]);
		}
		int yStart, yEnd;
		if (wallDataY.length == 1) {
			yStart = yEnd = Integer.parseInt(wallDataY[0]);
		} else {
			yStart = Integer.parseInt(wallDataY[0]);
			yEnd = Integer.parseInt(wallDataY[1]);
		}
		for (int j = xStart; j <= xEnd; j += 32) {
			for (int k = yStart; k <= yEnd; k += 32) {
				sprites.add(new Wall(j, k));
			}
		}
	}

	/** 
	 * creates the potions
	 * @param content the parsed content of the levelfile 
	 */
	private void spawnHealthPotion(String content) {
		String healthPotionData[] = content.split(":");
		int healthPotionX = Integer.parseInt(healthPotionData[0]);
		int healthPotionY = Integer.parseInt(healthPotionData[1]);
		sprites.add(new HealthPotion(healthPotionX, healthPotionY, gameLogic));
	}

	/** 
	 * creates special fireballTraps that spawn multiple fireballs in a row
	 * @param content the parsed content of the levelfile 
	 */
	private void spawnFireballWaveTrap(String content) {
		String fireballWaveTrapData[] = content.split(";");
		String fireballWaveTrapEntry[] = fireballWaveTrapData[0].split(":");
		String fireballWaveTrapExit[] = fireballWaveTrapData[1].split(":");
		int locationX = Integer.parseInt(fireballWaveTrapEntry[0]);
		int locationY = Integer.parseInt(fireballWaveTrapEntry[1]);
		int amount = Integer.parseInt(fireballWaveTrapExit[0]);
		int direction = Integer.parseInt(fireballWaveTrapExit[1]);
		sprites.add(new FireballWaveTrap(locationX, locationY, gameLogic,
				amount, direction));
	}
}
