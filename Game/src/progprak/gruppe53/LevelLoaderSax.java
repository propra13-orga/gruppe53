package progprak.gruppe53;

import java.io.IOException;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LevelLoaderSax extends DefaultHandler {
	
	private Vector<Sprite> sprites;
	private Game game;
	private StringBuilder elementName;

	public LevelLoaderSax(Vector<Sprite> sprites, Game game) {
		this.sprites = sprites;
		this.game = game;
	}

	public static void generateLevel(String level, Vector<Sprite> sprites, Game game){
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new LevelLoaderSax(sprites,game);
		
			saxParser.parse( LevelLoaderSax.class.getClassLoader().getResource(level).getFile(), handler );
			
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
		System.out.println("End Document");
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		System.out.println("qName:" + qName);
		int start = elementName.length()-qName.length()-1;
		if(start<0)start=0;
		elementName.delete(start, elementName.length());
		System.out.println(elementName);
		System.out.println("End Element");
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		elementName = new StringBuilder();
		System.out.println("Start Document");
	}

	@Override
	public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		System.out.println("Start Element");
		System.out.println("qName:" + qName);
		for(int i=0;i < attributes.getLength();i++){
		}
		if(elementName.length() == 0){
			elementName.append(qName.toString());	
		}
		else {
			elementName.append("." + qName.toString());
		}
		System.out.println(elementName);
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
		case "level.enemies.enemy":
			spawnEnemy(content);
			break;
		case "level.traps.trap":
			spawnTrap(content);
			break;
		case "level.fireballtraps.fireballtrap":
			spawnFireballTrap(content);
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
	}

	private void setHeroSpawnPoint(String content) {
		String spawnArray[] = content.split(":");
		Hero hero = game.getGameLogic().getHero();
		hero.setX(Integer.parseInt(spawnArray[0]));
		hero.setY(Integer.parseInt(spawnArray[1]));
		sprites.add(hero);		
	}

	private void spawnLevelSwitch(String content) {
		String levelSwitchData[] = content.split(";");
		String levelSwitchLocation[] = levelSwitchData[0].split(":");
		String levelSwitchNewLevelPath = levelSwitchData[1];
		int xLocation = Integer.parseInt(levelSwitchLocation[0]);
		int yLocation = Integer.parseInt(levelSwitchLocation[1]);
		sprites.add(new LevelSwitch(xLocation, yLocation, levelSwitchNewLevelPath));
	}

	private void spawnFireballTrap(String content) {
		String fireballTrapData[] = content.split(";");
		String fireballTrapLocation[] = fireballTrapData[0].split(":");
		String fireballTrapMovement[] = fireballTrapData[1].split(":");
		String fireballTrapRespawn[] = fireballTrapData[2].split(":");
		int xLocation = Integer.parseInt(fireballTrapLocation[0]);
		int yLocation = Integer.parseInt(fireballTrapLocation[1]);
		int xMovement = Integer.parseInt(fireballTrapMovement[0]);
		int yMovement = Integer.parseInt(fireballTrapMovement[1]);
		int xRespawn  = Integer.parseInt(fireballTrapRespawn[0]);
		int yRespawn  = Integer.parseInt(fireballTrapRespawn[1]);
		sprites.add(new FireballTrap(xLocation, yLocation, game, xMovement, yMovement, xRespawn, yRespawn));		
	}

	private void spawnTrap(String content) {
		String trapData[] = content.split(":");
		int trapX = Integer.parseInt(trapData[0]);
		int trapY = Integer.parseInt(trapData[1]);
		sprites.add(new GroundTrap(trapX, trapY));		
	}

	private void spawnEnemy(String content) {
		String enemyData[] = content.split(":");
		int enemyX = Integer.parseInt(enemyData[0]);
		int enemyY = Integer.parseInt(enemyData[1]);
		sprites.add(new EnemyGhost(enemyX, enemyY));		
	}

	private void spawnWall(String content) {
		String wallData[] = content.split(":");
		System.out.println(wallData.length);
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
}
