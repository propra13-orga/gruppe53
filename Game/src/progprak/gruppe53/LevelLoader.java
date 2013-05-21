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
		System.out.println(levelPath);
		URL levelUrl = getClass().getClassLoader().getResource(levelPath);
		File levelFile;
		try {
			levelFile = new File(levelUrl.toURI());
			Scanner scanner = new Scanner(levelFile, "UTF-8");
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				levelString += line + "\n";
			}
			scanner.close();
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

	public void generateLevel(Vector<Sprite> sprites, Game game) {

		setDimensions();
		
		generateGround(sprites, game);

		spawnHero(sprites, game);

		generateWalls(sprites, game);
		
		spawnEnemies(sprites, game);
		
		spawnTraps(sprites, game);
		
		spawnFireballTraps(sprites, game);
		
		spawnPortalEntrances(sprites, game);
		
		spawnGoal(sprites, game);
		
		spawnLevelSwitch(sprites, game);

	}

	private void generateWalls(Vector<Sprite> sprites, Game game) {
		int wallStart = levelString.indexOf("<wall>");
		int wallEnd = levelString.indexOf("</wall>");
		String wall = levelString.substring(wallStart + "<wall>".length(),
				wallEnd);
		System.out.println("Waende:" + wall);
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
		System.out.println("dimensions: " + dimensions);
		String dimensionArray[] = dimensions.split("x");
		xDimension = Integer.parseInt(dimensionArray[0]);
		yDimension = Integer.parseInt(dimensionArray[1]);
	}

	public Hero spawnHero(Vector<Sprite> sprites, Game game) {
		int spawnStart = levelString.indexOf("<spawn>");
		int spawnEnd = levelString.indexOf("</spawn>");
		String spawn = levelString.substring(spawnStart + "<spawn>".length(),
				spawnEnd);
		System.out.println("Spawnpoint: " + spawn);
		String spawnArray[] = spawn.split(":");
		Hero hero = game.getGameLogic().getHero();
		hero.setX(Integer.parseInt(spawnArray[0]));
		hero.setY(Integer.parseInt(spawnArray[1]));
		sprites.add(hero);
		//sprites.add(hero = new Hero(Integer.parseInt(spawnArray[0]), Integer
		//		.parseInt(spawnArray[1]), game));
		return hero;
	}
	public Enemy [] spawnEnemies(Vector<Sprite> sprites, Game game){
		int enemyStart = levelString.indexOf("<enemy>");
		int enemyEnd = levelString.indexOf("</enemy>");
		String spawnEnemy = levelString.substring(enemyStart + "<enemy>".length(),
				enemyEnd);
		System.out.println("Enemies: " + spawnEnemy);
		String enemyArray[] = spawnEnemy.split(",");
		Enemy enemies [] = new Enemy [enemyArray.length];
		for (int i = 0; i < enemyArray.length; i++){
			String enemyData[] = enemyArray[i].split(":");
			int enemyX = Integer.parseInt(enemyData[0]);
			int enemyY = Integer.parseInt(enemyData[1]);
			sprites.add(enemies [i] = new Enemy(enemyX, enemyY));
		}
		return enemies;
	}
	public Trap [] spawnTraps(Vector<Sprite> sprites, Game game){
		int trapStart = levelString.indexOf("<trap>");
		int trapEnd = levelString.indexOf("</trap>");
		String spawnTrap = levelString.substring(trapStart + "<trap>".length(),
				trapEnd);
		System.out.println("Traps: " + spawnTrap);
		String trapArray[] = spawnTrap.split(",");
		Trap traps [] = new Trap [trapArray.length];
		for (int i = 0; i < trapArray.length; i++){
			String trapData[] = trapArray[i].split(":");
			int trapX = Integer.parseInt(trapData[0]);
			int trapY = Integer.parseInt(trapData[1]);
			sprites.add(traps [i] = new Trap(trapX, trapY));
		}
		return traps;
	}
	public FireballTrap [] spawnFireballTraps(Vector<Sprite> sprites, Game game) {
		int fireballTrapStart = levelString.indexOf("<fireballtrap>");
		int fireballTrapEnd = levelString.indexOf("</fireballtrap>");
		String spawnFireballTrap = levelString.substring(fireballTrapStart + "<fireballtrap>".length(),
				fireballTrapEnd);
		System.out.println("Fireballtraps: " + spawnFireballTrap);
		String fireballTrapArray[] = spawnFireballTrap.split(",");
		FireballTrap fireballTraps [] = new FireballTrap [fireballTrapArray.length];
		for (int i = 0; i < fireballTrapArray.length; i++) {
			String fireballTrapData[] = fireballTrapArray[i].split(";");
			String fireballTrapLocation[] = fireballTrapData[0].split(":");
			String fireballTrapMovement[] = fireballTrapData[1].split(":");
			int xLocation = Integer.parseInt(fireballTrapLocation[0]);
			int yLocation = Integer.parseInt(fireballTrapLocation[1]);
			int xMovement = Integer.parseInt(fireballTrapMovement[0]);
			int yMovement = Integer.parseInt(fireballTrapMovement[1]);
			sprites.add(fireballTraps [i] = new FireballTrap(xLocation, yLocation, game, xMovement, yMovement));
		}
		return fireballTraps;
	}
	public PortalEntrance [] spawnPortalEntrances(Vector<Sprite> sprites, Game game) {
		int portalEntranceStart = levelString.indexOf("<portal>");
		int portalEntranceEnd = levelString.indexOf("</portal>");
		String spawnPortalEntrance = levelString.substring(portalEntranceStart + "<portal>".length(),
				portalEntranceEnd);
		System.out.println("Portals: " + spawnPortalEntrance);
		String portalEntranceArray[] = spawnPortalEntrance.split(",");
		PortalEntrance portalEntrances [] = new PortalEntrance [portalEntranceArray.length];
		for (int i = 0; i < portalEntranceArray.length; i++) {
			String portalEntranceData[] = portalEntranceArray[i].split(";");
			String portalEntranceEntry[] = portalEntranceData[0].split(":");
			String portalEntranceExit[] = portalEntranceData[1].split(":");
			int startLocationX = Integer.parseInt(portalEntranceEntry[0]);
			int startLocationY = Integer.parseInt(portalEntranceEntry[1]);
			int endLocationX = Integer.parseInt(portalEntranceExit[0]);
			int endLocationY = Integer.parseInt(portalEntranceExit[1]);
			sprites.add(portalEntrances [i] = new PortalEntrance(startLocationX, startLocationY, endLocationX, endLocationY));
		}
		return portalEntrances;
	}
	public Goal spawnGoal(Vector<Sprite> sprites, Game game) {
		int goalStart = levelString.indexOf("<goal>");
		int goalEnd = levelString.indexOf("</goal>");
		String spawnGoal = levelString.substring(goalStart + "<goal>".length(),goalEnd);
		System.out.println("Goal: " + spawnGoal);
		String goalArray[] = spawnGoal.split(":");
		Goal goal;
		sprites.add(goal = new Goal(Integer.parseInt(goalArray[0]), Integer.parseInt(goalArray[1])));
		return goal;
	}
	private void generateGround(Vector<Sprite> sprites, Game game) {
		int groundStart = levelString.indexOf("<ground>");
		int groundEnd = levelString.indexOf("</ground>");
		String ground = levelString.substring(groundStart + "<ground>".length(),groundEnd);
		System.out.println("Boden:" + ground);
		String groundArray[] = ground.split(",");
		for (int i = 0; i < groundArray.length; i++) {
			String groundData[] = groundArray[i].split(":");
			String groundDataX[] = groundData[0].split("-");
			String groundDataY[] = groundData[1].split("-");
			int xStart = Integer.parseInt(groundDataX[0]);
			int xEnd = Integer.parseInt(groundDataX[1]);
			int yStart = Integer.parseInt(groundDataY[0]);
			int yEnd = Integer.parseInt(groundDataY[1]);
			for (int j = xStart;j<=xEnd;j+=16){
				for (int k = yStart;k<=yEnd;k+=16){
					sprites.add(new Ground(j, k));
				}
			}
		}
	}
	public LevelSwitch [] spawnLevelSwitch(Vector<Sprite> sprites, Game game) {
		int levelSwitchStart = levelString.indexOf("<levelswitch>");
		int levelSwitchEnd = levelString.indexOf("</levelswitch>");
		String spawnLevelSwitch = levelString.substring(levelSwitchStart + "<levelswitch>".length(),
				levelSwitchEnd);
		System.out.println("LevelSwitch: " + spawnLevelSwitch);
		String levelSwitchArray[] = spawnLevelSwitch.split(",");
		LevelSwitch levelSwitches [] = new LevelSwitch [levelSwitchArray.length];
		for (int i = 0; i < levelSwitchArray.length; i++) {
			String levelSwitchData[] = levelSwitchArray[i].split(";");
			String levelSwitchLocation[] = levelSwitchData[0].split(":");
			String levelSwitchNewLevelPath = levelSwitchData[1];
			int xLocation = Integer.parseInt(levelSwitchLocation[0]);
			int yLocation = Integer.parseInt(levelSwitchLocation[1]);
			sprites.add(levelSwitches [i] = new LevelSwitch(xLocation, yLocation, levelSwitchNewLevelPath));
		}
		return levelSwitches;
	}
}
