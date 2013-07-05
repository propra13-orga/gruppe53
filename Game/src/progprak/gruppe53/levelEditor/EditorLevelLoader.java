package progprak.gruppe53.levelEditor;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import progprak.gruppe53.items.HealthPotion;
import progprak.gruppe53.sprites.EnemyGhost;
import progprak.gruppe53.sprites.EnemyOldManNPC;
import progprak.gruppe53.sprites.FireballTrap;
import progprak.gruppe53.sprites.FireballTrap2;
import progprak.gruppe53.sprites.FireballWaveTrap;
import progprak.gruppe53.sprites.Goal;
import progprak.gruppe53.sprites.GroundTrap;
import progprak.gruppe53.sprites.LevelSwitch;
import progprak.gruppe53.sprites.PortalEntrance;
import progprak.gruppe53.sprites.Sprite;
import progprak.gruppe53.sprites.Wall;
import progprak.gruppe53.sprites.WallLevelSwitch;

public class EditorLevelLoader extends DefaultHandler {
	
	private ArrayList<Sprite> sprites;
	private StringBuilder elementName;
	private Attributes attributes;

	public EditorLevelLoader(ArrayList<Sprite> sp) {
		this.sprites = sp;
	}

	public static void generateLevel(String level, ArrayList<Sprite> sp){
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new EditorLevelLoader(sp);
		
			saxParser.parse( EditorLevelLoader.class.getClassLoader().getResource(level).getFile(), handler );
			
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		int start = elementName.length()-qName.length()-1;
		if(start<0)start=0;
		elementName.delete(start, elementName.length());
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		elementName = new StringBuilder();
	}

	@Override
	public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		this.attributes = attributes;
		if(elementName.length() == 0){
			elementName.append(qName.toString());	
		}
		else {
			elementName.append("." + qName.toString());
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		String content = new String(ch,start,length);
		switch (elementName.toString()) {
		case "level.walls.wall":
			spawnWall(content);
			break;
		case "level.enemies.ghost":
			spawnEnemyGhost(content);
			break;
		case "level.enemies.spider":
			spawnEnemySpider(content);
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
		case "level.levelswitch":
			spawnLevelSwitch(content);
			break;
		case "level.goal":
			setGoal(content);
			break;
		case "level.spawn":
			setHeroSpawnPoint(content);
			break;
		case "level.healthpotions.healthpotion":
			spawnHealthPotions(content);
			break;
		case "level.fireballwavetraps.fireballwavetrap":
			spawnFireballWaveTrap(content);
			break;
		default:
			break;
		}
	}

	private void setGoal(String content) {
		String goalEntry[] = content.split(":");
		int xLocation = Integer.parseInt(goalEntry[0]);
		int yLocation = Integer.parseInt(goalEntry[1]);
		sprites.add(new Goal(xLocation,yLocation));		
	}

	private void spawnPortal(String content) {
		String portalData[] = content.split(";");
		String portalEntry[] = portalData[0].split(":");
		String portalExit[] = portalData[1].split(":");
		int startLocationX = Integer.parseInt(portalEntry[0]);
		int startLocationY = Integer.parseInt(portalEntry[1]);
		int endLocationX = Integer.parseInt(portalExit[0]);
		int endLocationY = Integer.parseInt(portalExit[1]);
		sprites.add(new PortalEntrance(startLocationX, startLocationY, endLocationX, endLocationY));	
		LevelEditor.saveData[startLocationX][startLocationY]="		<portal>" + startLocationX + ":" + startLocationY + ";" + endLocationX + ":" + endLocationY + "</portal>" + "\n";
	}

	private void setHeroSpawnPoint(String content) {
		String spawnArray[] = content.split(":");
		int xSpawn = (Integer.parseInt(spawnArray[0]));
		int ySpawn = (Integer.parseInt(spawnArray[1]));
		sprites.add(new HeroEditor(xSpawn, ySpawn));		
	}

	private void spawnLevelSwitch(String content) {
		String levelSwitchData[] = content.split(";");
		String levelSwitchLocation[] = levelSwitchData[0].split(":");
		String levelSwitchNewLevelPath = levelSwitchData[1];
		int xLocation = Integer.parseInt(levelSwitchLocation[0]);
		int yLocation = Integer.parseInt(levelSwitchLocation[1]);
		if(attributes.getValue("type").equals("wall")){
			sprites.add(new WallLevelSwitch(xLocation, yLocation, Integer.parseInt(attributes.getValue("direction")),levelSwitchNewLevelPath));
			LevelEditor.saveData[xLocation][yLocation]="	<levelswitch type=\"wall\" direction=\"" + Integer.parseInt(attributes.getValue("direction")) + "\">" + xLocation + ":" + yLocation + ";" + levelSwitchNewLevelPath + "</levelswitch>" + "\n";
		}
		else {
			sprites.add(new LevelSwitch(xLocation, yLocation, levelSwitchNewLevelPath));
			LevelEditor.saveData[xLocation][yLocation]="	<levelswitch>" + xLocation + ":" + yLocation + ";" + levelSwitchNewLevelPath + "</levelswitch>" + "\n";
		}
	}
		
	

	private void spawnFireballTrap(String content) {
		String fireballTrapData[] = content.split(";");
		String fireballTrapLocation[] = fireballTrapData[0].split(":");
		String fireballTrapMovement[] = fireballTrapData[1].split(":");
		//String fireballTrapRespawn[] = fireballTrapData[2].split(":");
		int xLocation = Integer.parseInt(fireballTrapLocation[0]);
		int yLocation = Integer.parseInt(fireballTrapLocation[1]);
		double xMovement = Double.parseDouble(fireballTrapMovement[0]);
		double yMovement = Double.parseDouble(fireballTrapMovement[1]);
		//int xRespawn  = Integer.parseInt(fireballTrapRespawn[0]);
		//int yRespawn  = Integer.parseInt(fireballTrapRespawn[1]);
		sprites.add(new FireballTrap(xLocation, yLocation, null,xMovement, yMovement,"images/FireballRed.png"));	
		LevelEditor.saveData[xLocation][yLocation]="		<fireballtrap>" + xLocation + ":" + yLocation + ";" + xMovement + ":" + yMovement + "</fireballtrap>" + "\n";
	}
	private void spawnFireballTrap2(String content) {
		String fireballTrapData[] = content.split(";");
		String fireballTrapLocation[] = fireballTrapData[0].split(":");
		String fireballTrapMovement[] = fireballTrapData[1].split(":");
		String fireballTrapRespawn[] = fireballTrapData[2].split(":");
		int xLocation = Integer.parseInt(fireballTrapLocation[0]);
		int yLocation = Integer.parseInt(fireballTrapLocation[1]);
		double xMovement = Double.parseDouble(fireballTrapMovement[0]);
		double yMovement = Double.parseDouble(fireballTrapMovement[1]);
		int xRespawn  = Integer.parseInt(fireballTrapRespawn[0]);
		int yRespawn  = Integer.parseInt(fireballTrapRespawn[1]);
		sprites.add(new FireballTrap2(xLocation, yLocation, null, xMovement, yMovement,xRespawn,yRespawn));		
		LevelEditor.saveData[xLocation][yLocation]="		<fireballtrap2>" + xLocation + ":" + yLocation + ";" + xMovement + ":" + yMovement + xRespawn + yRespawn + "</fireballtrap2>" + "\n";
	}

	private void spawnTrap(String content) {
		String trapData[] = content.split(":");
		int trapX = Integer.parseInt(trapData[0]);
		int trapY = Integer.parseInt(trapData[1]);
		sprites.add(new GroundTrap(trapX, trapY,null));		
	}

	private void spawnEnemyGhost(String content) {
		String enemyData[] = content.split(":");
		int enemyX = Integer.parseInt(enemyData[0]);
		int enemyY = Integer.parseInt(enemyData[1]);
		sprites.add(new EnemyGhost(enemyX, enemyY,null));		
	}
	
	private void spawnEnemySpider(String content) {
		String enemyData[] = content.split(":");
		int enemyX = Integer.parseInt(enemyData[0]);
		int enemyY = Integer.parseInt(enemyData[1]);
		sprites.add(new EnemyOldManNPC(enemyX, enemyY,null));		
	}

	private void spawnWall(String content) {
		String wallData[] = content.split(":");
		String wallDataX[] = wallData[0].split("-");
		String wallDataY[] = wallData[1].split("-");
		int xStart,xEnd;
		if(wallDataX.length == 1){
			xStart = xEnd = Integer.parseInt(wallDataX[0]);
		}
		else {
			xStart = Integer.parseInt(wallDataX[0]);
			xEnd = Integer.parseInt(wallDataX[1]);
		}
		int yStart,yEnd;
		if(wallDataY.length == 1){
			yStart = yEnd = Integer.parseInt(wallDataY[0]);
		}
		else {
			yStart = Integer.parseInt(wallDataY[0]);
			yEnd = Integer.parseInt(wallDataY[1]);
		}
		for (int j = xStart;j<=xEnd;j+=32){
			for (int k = yStart;k<=yEnd;k+=32){
				sprites.add(new Wall(j, k));
			}
		}
	}
	private void spawnHealthPotions(String content) {
		String healthPotionData[] = content.split(":");
		int healthPotionX = Integer.parseInt(healthPotionData[0]);
		int healthPotionY = Integer.parseInt(healthPotionData[1]);
		sprites.add(new HealthPotion(healthPotionX, healthPotionY,null));	
	}
	private void spawnFireballWaveTrap(String content) {
		String fireballWaveTrapData[] = content.split(";");
		String fireballWaveTrapEntry[] = fireballWaveTrapData[0].split(":");
		String fireballWaveTrapExit[] = fireballWaveTrapData[1].split(":");
		int locationX = Integer.parseInt(fireballWaveTrapEntry[0]);
		int locationY = Integer.parseInt(fireballWaveTrapEntry[1]);
		int amount = Integer.parseInt(fireballWaveTrapExit[0]);
		int direction = Integer.parseInt(fireballWaveTrapExit[1]);
		sprites.add(new FireballWaveTrap(locationX, locationY, null, amount, direction, "images/FireballGreen.png"));
		LevelEditor.saveData[locationX][locationY]="		<fireballwavetrap>" + locationX + ":" + locationY + ";" + amount + ":" + direction + "</fireballwavetrap>" + "\n";
	}
}
