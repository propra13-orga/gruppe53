package progprak.gruppe53.levelEditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import progprak.gruppe53.game.EnemyGhost;
import progprak.gruppe53.game.FireballTrap;
import progprak.gruppe53.game.GamePanel;
import progprak.gruppe53.game.Goal;
import progprak.gruppe53.game.GroundTrap;
import progprak.gruppe53.game.ImageLoader;
import progprak.gruppe53.game.LevelSwitch;
import progprak.gruppe53.game.PortalEntrance;
import progprak.gruppe53.game.Sprite;
import progprak.gruppe53.game.Wall;
import progprak.gruppe53.items.ClothArmor;
import progprak.gruppe53.items.WoodenSword;

public class LevelEditor extends JFrame implements ActionListener,
		MouseListener {
	private static final long serialVersionUID = 1;

	private static final String WINDOW_NAME = "LevelEditor";
	private static final int DEFAULT_WINDOW_WIDTH = 980;
	private static final int DEFAULT_WINDOW_HEIGHT = 698;
	private static final int GAMEPANEL_WIDTH = 800;
	private static final int GAMEPANEL_HEIGHT = 640;
	private static final int SEPARATOR_WIDTH = 20;
	private static final int ATTRIBUTEBAR_HEIGHT = 30;
	private static final String OBJECT_WALL = "wall";
	private static final String OBJECT_MULTIWALL = "multiwall";
	private static final String ENEMY_GHOST = "enemyGhost";
	private static final String TRAP_SPEARS = "trapSpears";
	private static final String TRAP_FIREBALL = "trapFireball";
	private static final String OBJECT_PORTAL = "portal";
	private static final String OBJECT_LEVELSWITCH = "levelSwitch";
	private static final String OBJECT_GOAL = "goal";
	private static final String OBJECT_SPAWN = "spawn";
	private static final String OBJECT_SWORD = "woodenSword";
	private static final String OBJECT_JACKET = "clothArmor";
	private static final String SAVE = "save";
	private static final String DELETE = "delete";
	private static final String LOAD = "load";
	//private static final String SELECT = "select";
	
	/*
	Zum Auswählen von Sprites am Feld - unfertig, vorerst deaktiviert
	private String labelString1 = "X: ";
	private String labelString2 = "Y: ";
	private String labelString3 = "XSpeed: ";
	private String labelString4 = "YSpeed: ";
	private String labelString5 = "XSpawn: ";
	private String labelString6 = "YSpawn: ";
	private String labelString7 = "TeleportLocation: ";
	private String labelString8 = "NextLevelPath: ";
	*/
	private int xPosition;
	private int yPosition;
	private int multiWallStartX = 0;
	private int multiWallStartY = 0;
	private boolean multiWallStarted = false;
	public static String saveData[][]=new String[801][641];
	
	public static void main(String[] args) {
		new LevelEditor();

	}
	
	private JPanel tools, level, separator, attributeBar;
	/*
	Zum Auswählen von Sprites am Feld - unfertig, vorerst deaktiviert
	private JTextField attribute1, attribute2, attribute3, attribute4, attribute5, attribute6;
	private JLabel attributeLabel1, attributeLabel2, attributeLabel3, attributeLabel4, attributeLabel5, attributeLabel6;
	*/

	private Vector<Sprite> sprites;
	private String currentSprite = "";
	private String saveFileName = "";
	
	public LevelEditor() {
		super(WINDOW_NAME);
		setupEditor();
		setVisible(true);
		loadLevel("levels/LevelTemplate.xml");
		((GamePanel)level).render(1,sprites);
		level.repaint();
	}

	private void setupEditor() {
		// Setzt Größe und Position des Fensters und der nötigen Panels
		sprites = new Vector<Sprite>();
		setTitle(WINDOW_NAME);
		setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		getContentPane().setLayout(new BorderLayout());
		level = new GamePanel();
		separator = new JPanel();
		tools = new JPanel();
		attributeBar = new JPanel();
		/*
		Zum Auswählen von Sprites am Feld - unfertig, vorerst deaktiviert
		attribute1 = new JTextField(10);
		attribute2 = new JTextField(10);
		attribute3 = new JTextField(10);
		attribute4 = new JTextField(10);
		attribute5 = new JTextField(10);
		attribute6 = new JTextField(10);
		attributeLabel1 = new JLabel(labelString1);
		attributeLabel2 = new JLabel(labelString2);
		JButton saveAttributes = new JButton("Save");
		saveAttributes.setPreferredSize(new Dimension(66,20));
		*/
				
		
		separator.setPreferredSize(new Dimension(SEPARATOR_WIDTH,GAMEPANEL_HEIGHT));
		tools.setPreferredSize(new Dimension(DEFAULT_WINDOW_WIDTH - GAMEPANEL_WIDTH - SEPARATOR_WIDTH, GAMEPANEL_HEIGHT));
		attributeBar.setPreferredSize(new Dimension(DEFAULT_WINDOW_WIDTH, ATTRIBUTEBAR_HEIGHT));
		
		separator.setBackground(Color.YELLOW);
		tools.setBackground(Color.BLUE);
		attributeBar.setBackground(Color.RED);

		setupTools();

		level.addMouseListener(this);

		/*
		Rest vom alten Layout - vorerst nicht mehr nötig
		JPanel editorPanel = new JPanel();
		editorPanel.setLayout(new BoxLayout(editorPanel, BoxLayout.X_AXIS));
		editorPanel.add(level);
		editorPanel.add(separator);
		editorPanel.add(tools);
		add(editorPanel,BorderLayout.CENTER);
		add(attributeBar,BorderLayout.SOUTH);
		*/
		add(level,BorderLayout.WEST);
		add(separator,BorderLayout.CENTER);
		add(tools,BorderLayout.EAST);
		add(attributeBar,BorderLayout.SOUTH);
		/*
		Zum Auswählen von Sprites am Feld - unfertig, vorerst deaktiviert
		attributeBar.add(attributeLabel1);
		attributeBar.add(attribute1);
		attributeBar.add(attributeLabel2);
		attributeBar.add(attribute2);
		attributeBar.add(attribute3);
		attributeBar.add(attribute4);
		attributeBar.add(attribute5);
		attributeBar.add(attribute6);
		attributeBar.add(saveAttributes);
		*/
		
	}

	private void setupTools() {
		//Alle Buttons in der Toolbar
		JButton wall = new JButton(new ImageIcon(ImageLoader.loadImage("images/wall.png")));
		wall.setActionCommand(OBJECT_WALL);
		wall.addActionListener(this);
		tools.add(wall);
		JButton multiwall = new JButton(new ImageIcon(ImageLoader.loadImage("images/wall.png")));
		multiwall.setActionCommand(OBJECT_MULTIWALL);
		multiwall.addActionListener(this);
		tools.add(multiwall);
		JButton enemyGhost = new JButton(new ImageIcon(ImageLoader.loadImage("images/ghost1.png")));
		enemyGhost.setActionCommand(ENEMY_GHOST);
		enemyGhost.addActionListener(this);
		tools.add(enemyGhost);
		JButton trapSpears = new JButton(new ImageIcon(ImageLoader.loadImage("images/groundTrap1.png")));
		trapSpears.setActionCommand(TRAP_SPEARS);
		trapSpears.addActionListener(this);
		tools.add(trapSpears);
		JButton trapFireball = new JButton(new ImageIcon(ImageLoader.loadImage("images/FireballRedEditor.png")));
		trapFireball.setActionCommand(TRAP_FIREBALL);
		trapFireball.addActionListener(this);
		tools.add(trapFireball);
		JButton portal = new JButton(new ImageIcon(ImageLoader.loadImage("images/entrance.png")));
		portal.setActionCommand(OBJECT_PORTAL);
		portal.addActionListener(this);
		tools.add(portal);
		JButton levelSwitch = new JButton(new ImageIcon(ImageLoader.loadImage("images/entrance2.png")));
		levelSwitch.setActionCommand(OBJECT_LEVELSWITCH);
		levelSwitch.addActionListener(this);
		tools.add(levelSwitch);
		JButton goal = new JButton(new ImageIcon(ImageLoader.loadImage("images/exit.png")));
		goal.setActionCommand(OBJECT_GOAL);
		goal.addActionListener(this);
		tools.add(goal);
		JButton spawn = new JButton(new ImageIcon(ImageLoader.loadImage("images/held.png")));
		spawn.setActionCommand(OBJECT_SPAWN);
		spawn.addActionListener(this);
		tools.add(spawn);
		JButton woodenSword = new JButton(new ImageIcon(ImageLoader.loadImage("images/sword.png")));
		woodenSword.setPreferredSize(new Dimension(66,42));
		woodenSword.setActionCommand(OBJECT_SWORD);
		woodenSword.addActionListener(this);
		tools.add(woodenSword);
		JButton clothArmor = new JButton(new ImageIcon(ImageLoader.loadImage("images/hero.png")));
		clothArmor.setActionCommand(OBJECT_JACKET);
		clothArmor.addActionListener(this);
		tools.add(clothArmor);
		JButton save = new JButton("Save");
		save.setPreferredSize(new Dimension(66,42));
		save.setActionCommand(SAVE);
		save.addActionListener(this);
		tools.add(save);		
		JButton delete = new JButton("Del");
		delete.setPreferredSize(new Dimension(66,42));
		delete.setActionCommand(DELETE);
		delete.addActionListener(this);
		tools.add(delete);
		JButton load = new JButton("Load");
		load.setPreferredSize(new Dimension(66,42));
		load.setActionCommand(LOAD);
		load.addActionListener(this);
		tools.add(load);
		/* Zum Auswählen von Sprites am Feld - unfertig, vorerst deaktiviert
		JButton select = new JButton("Sel");
		select.setPreferredSize(new Dimension(66,42));
		select.setActionCommand(SELECT);
		select.addActionListener(this);
		tools.add(select);
		*/
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Sagt dem Editor welcher Button geklickt wurde und setzt den jeweiligen Custom Cursor
		String actionCommand = e.getActionCommand();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image;
		Cursor c;
		if (actionCommand == OBJECT_WALL) {
			image = ImageLoader.loadImage("images/wall.png");
			c = toolkit.createCustomCursor(image, new Point(0, 0), "wall");
			level.setCursor(c);
			currentSprite = actionCommand;
			
		}
		else if (actionCommand == OBJECT_MULTIWALL) {
			image = ImageLoader.loadImage("images/wall.png");
			c = toolkit.createCustomCursor(image, new Point(0, 0), "multiwall");
			level.setCursor(c);
			currentSprite = actionCommand;
			
		}
		else if (actionCommand == ENEMY_GHOST) {
			image = ImageLoader.loadImage("images/ghost1.png");
			c = toolkit.createCustomCursor(image, new Point(0, 0), "enemyGhost");
			level.setCursor(c);
			currentSprite = actionCommand;
			
		}
		else if (actionCommand == TRAP_SPEARS) {
			image = ImageLoader.loadImage("images/groundTrap1.png");
			c = toolkit.createCustomCursor(image, new Point(0, 0), "trapSpears");
			level.setCursor(c);
			currentSprite = actionCommand;
			
		}
		else if (actionCommand == TRAP_FIREBALL) {
			image = ImageLoader.loadImage("images/FireballRedEditor2.png");
			c = toolkit.createCustomCursor(image, new Point(0, 0),"trapFireball");
			level.setCursor(c);
			currentSprite = actionCommand;
			
		}
		else if (actionCommand == OBJECT_PORTAL) {
			image = ImageLoader.loadImage("images/entrance.png");
			c = toolkit.createCustomCursor(image, new Point(0, 0), "portal");
			level.setCursor(c);
			currentSprite = actionCommand;
		}
		else if (actionCommand == OBJECT_LEVELSWITCH) {
			image = ImageLoader.loadImage("images/entrance2.png");
			c = toolkit.createCustomCursor(image, new Point(0, 0),"levelSwitch");
			level.setCursor(c);
			currentSprite = actionCommand;
		}
		else if (actionCommand == OBJECT_GOAL) {
			image = ImageLoader.loadImage("images/exit.png");
			c = toolkit.createCustomCursor(image, new Point(0, 0), "goal");
			level.setCursor(c);
			currentSprite = actionCommand;
		}
		else if (actionCommand == OBJECT_SPAWN) {
			image = ImageLoader.loadImage("images/held.png");
			c = toolkit.createCustomCursor(image, new Point(0, 0), "spawn");
			level.setCursor(c);
			currentSprite = actionCommand;
		}
		else if (actionCommand == OBJECT_SWORD) {
			image = ImageLoader.loadImage("images/sword.png");
			c = toolkit.createCustomCursor(image, new Point(0, 0), "woodenSword");
			level.setCursor(c);
			currentSprite = actionCommand;
		}
		else if (actionCommand == OBJECT_JACKET) {
			image = ImageLoader.loadImage("images/hero.png");
			c = toolkit.createCustomCursor(image, new Point(0, 0), "clothArmor");
			level.setCursor(c);
			currentSprite = actionCommand;
		}
		else if (actionCommand == SAVE) {
			c = Cursor.getDefaultCursor();
			level.setCursor(c);
			new SaveDialog(this);
			currentSprite = actionCommand;
		}
		else if (actionCommand == DELETE) {
			c = Cursor.getDefaultCursor();
			level.setCursor(c);
			currentSprite = actionCommand;
			}
		else if (actionCommand == LOAD) {
			c = Cursor.getDefaultCursor();
			level.setCursor(c);
			new LoadDialog(this);
			currentSprite = actionCommand;
		}
		/* Zum Auswählen von Sprites am Feld - unfertig, vorerst deaktiviert
		else if (actionCommand == SELECT) {
			c = Cursor.getDefaultCursor();
			level.setCursor(c);
			currentSprite = actionCommand;
		}
		*/
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//Führt je nachdem welcher Button zuvor geklickt wurde die passende Aktion aus
		if (currentSprite == OBJECT_WALL) {
			if(checkCollision(e.getX(),e.getY(),32,32)==false){
				sprites.add(new Wall(e.getX(),e.getY()));
			}
		}
		else if (currentSprite == OBJECT_MULTIWALL) {
			if(multiWallStarted == false){
				if(checkCollision(e.getX(),e.getY(),32,32)==false){
					sprites.add(new Wall(e.getX(),e.getY()));
					multiWallStartX = e.getX();
					multiWallStartY = e.getY();
					multiWallStarted = true;
				}
			}
			else if(multiWallStarted == true){
				if (e.getX()+32 < multiWallStartX) {
					for (int i = multiWallStartX;i >= e.getX()-32;i-=32){
						if(checkCollision(i,multiWallStartY,32,32)==false){
						sprites.add(new Wall(i,multiWallStartY));
						}
					}
				}
				else if (e.getX() > multiWallStartX+32) {
					for (int i = multiWallStartX+32; i<=e.getX()+32;i+=32){
						if(checkCollision(i,multiWallStartY,32,32)==false){
						sprites.add(new Wall(i,multiWallStartY));
						}
					}
				}
				else if (e.getY()+32 < multiWallStartY) {
					for (int i = multiWallStartY; i >= e.getY()-32;i-=32){
						if(checkCollision(multiWallStartX,i,32,32)==false){
						sprites.add(new Wall(multiWallStartX,i));
						}
					}
				}
				else if (e.getY() > multiWallStartY+32) {
					for (int i = multiWallStartY+32; i <= e.getY()+32;i+=32){
						if(checkCollision(multiWallStartX,i,32,32)==false){
						sprites.add(new Wall(multiWallStartX,i));
						}
					}
				}
				multiWallStarted = false;
			}
		}
		else if (currentSprite == ENEMY_GHOST) {
			if(checkCollision(e.getX(),e.getY(),32,32)==false){
				sprites.add(new EnemyGhost(e.getX(),e.getY(),null));
			}
		} 
		else if (currentSprite == TRAP_SPEARS) {
			if(checkCollision(e.getX(),e.getY(),32,32)==false){
				sprites.add(new GroundTrap(e.getX(),e.getY(),null));
			}
		}
		else if (currentSprite == TRAP_FIREBALL) {
			if(checkCollision(e.getX(),e.getY(),16,16)==false){
				xPosition = e.getX();
				yPosition = e.getY();
				new AttributeDialog(this,TRAP_FIREBALL);
			}
		}
		else if (currentSprite == OBJECT_PORTAL) {
			if(checkCollision(e.getX(),e.getY(),32,32)==false){
				xPosition = e.getX();
				yPosition = e.getY();
				new AttributeDialog(this,OBJECT_PORTAL);
			}
		} 
		else if (currentSprite == OBJECT_LEVELSWITCH) {
			if(checkCollision(e.getX(),e.getY(),32,32)==false){
				xPosition = e.getX();
				yPosition = e.getY();
				new AttributeDialog(this,OBJECT_LEVELSWITCH);
			}
		} 
		else if (currentSprite == OBJECT_GOAL) {
			if(checkCollision(e.getX(),e.getY(),32,32)==false){
				sprites.add(new Goal(e.getX(),e.getY()));
			}
		} 
		else if (currentSprite == OBJECT_SPAWN) {
			if(checkCollision(e.getX(),e.getY(),32,32)==false){
				sprites.add(new HeroEditor(e.getX(),e.getY()));
			}
		}
		else if (currentSprite == OBJECT_SWORD) {
			if(checkCollision(e.getX(),e.getY(),32,32)==false){
				sprites.add(new WoodenSword(e.getX(),e.getY(), null));
			}
		}
		else if (currentSprite == OBJECT_JACKET) {
			if(checkCollision(e.getX(),e.getY(),32,32)==false){
				sprites.add(new ClothArmor(e.getX(),e.getY()));
			}
		}
		else if (currentSprite == DELETE){
			int x = e.getX();
			int y = e.getY();
			for(int i=0;i<sprites.size();i++){
				Sprite sprite = sprites.get(i);
				int size = 32;
				
				if(sprite instanceof FireballTrap){
					size = 16;
					
				}
				if(sprite.getX()<=x && sprite.getX()+size>x){
					if(sprite.getY()<=y && sprite.getY()+size>y){
						sprites.remove(i);
					}
					if(sprite.getY()>=y && sprite.getY()<y+1){
						
						sprites.remove(i);
					}
				}
				if(sprite.getX()>=x && sprite.getX()<x+1){
					if(sprite.getY()>=y && sprite.getY()<y+1){
						
						sprites.remove(i);
					}
					if(sprite.getY()<=y && sprite.getY()+size>y){
						
						sprites.remove(i);
					}
				}
				if(sprite.getX()==x && sprite.getX()==x+1){
					if(sprite.getY()==y && sprite.getY()==y+1){
						
						sprites.remove(i);
					}
				}
			}
		}
		/* Zum Auswählen von Sprites am Feld - unfertig, vorerst deaktiviert
		else if (currentSprite == SELECT){
			int x = e.getX();
			int y = e.getY();
			for(int i=0;i<sprites.size();i++){
				Sprite sprite = sprites.get(i);
				int size = 32;
				
				if(sprite instanceof FireballTrap){
					size = 16;
					
				}
				if(sprite instanceof FireballTrap){
				if(sprite.getX()<=x && sprite.getX()+size>x){
					if(sprite.getY()<=y && sprite.getY()+size>y){
						
						attribute1.setText("" + sprite.getX());
					}
					if(sprite.getY()>=y && sprite.getY()<y){
						
						attribute1.setText("" + sprite.getX());
					}
				}
				if(sprite.getX()>=x && sprite.getX()<x){
					if(sprite.getY()>=y && sprite.getY()<y){
						
						attribute1.setText("" + sprite.getX());
					}
					if(sprite.getY()<=y && sprite.getY()+size>y){
						
						attribute1.setText("" + sprite.getX());
					}
				}
				if(sprite.getX()==x && sprite.getX()==x){
					if(sprite.getY()==y && sprite.getY()==y){
						
						attribute1.setText("" + sprite.getX());
					}
				}
				}
			}
		}
		*/
		((GamePanel)level).render(1,sprites);
		level.repaint();
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	private boolean checkCollision(int x, int y, int width, int heigth){
		//Prüft beim setzen von Sprites aufs Feld ob dort nicht bereits etwas ist, falls ja wird kein neuer Sprite gesetzt
		for(int i=0;i<sprites.size();i++){
			Sprite sprite = sprites.get(i);
			int size = 32;
			
			if(sprite instanceof FireballTrap){
				size = 16;
				
			}
			if(sprite.getX()<=x && sprite.getX()+size>x){
				if(sprite.getY()<=y && sprite.getY()+size>y){
					
					return true;
				}
				if(sprite.getY()>=y && sprite.getY()<y+heigth){
					
					return true;
				}
			}
			if(sprite.getX()>=x && sprite.getX()<x+width){
				if(sprite.getY()>=y && sprite.getY()<y+heigth){
					
					return true;
				}
				if(sprite.getY()<=y && sprite.getY()+size>y){
					
					return true;
				}
			}
			if(sprite.getX()==x && sprite.getX()==x+width){
				if(sprite.getY()==y && sprite.getY()==y+heigth){
					
					return true;
				}
			}
		}
		return false;
	}
	public void setSaveFileName(String saveFileName){
		this.saveFileName = saveFileName;
	}
	public void saveLevel(){
		new LevelSaver(saveFileName, sprites).saveLevel();
	}
	public void loadLevel(String newLevel){
		Vector<Sprite> sp = new Vector<Sprite>();
		EditorLevelLoader.generateLevel(newLevel,sp);
		sprites = sp;
		((GamePanel)level).render(1,sprites);
		level.repaint();
	}
	public void addSprite(String spriteType){
		if (spriteType == "trapFireball"){
			sprites.add(new FireballTrap(xPosition,yPosition,null,AttributeDialog.attribute1,AttributeDialog.attribute2,AttributeDialog.attribute3,AttributeDialog.attribute4));
			saveData[xPosition][yPosition]="		<fireballtrap>" + xPosition + ":" + yPosition + ";" + AttributeDialog.attribute1 + ":" + AttributeDialog.attribute2 + ";" + AttributeDialog.attribute3 + ":" + AttributeDialog.attribute4 + "</fireballtrap>" + "\n";
			((GamePanel)level).render(1,sprites);
			level.repaint();
		}
		else if (spriteType == "portal"){
			sprites.add(new PortalEntrance(xPosition,yPosition,AttributeDialog.attribute1,AttributeDialog.attribute2));
			saveData[xPosition][yPosition]="		<portal>" + xPosition + ":" + yPosition + ";" + AttributeDialog.attribute1 + ":" + AttributeDialog.attribute2 + "</portal>" + "\n";
			((GamePanel)level).render(1,sprites);
			level.repaint();
		}
		else if (spriteType == "levelSwitch"){
			sprites.add(new LevelSwitch(xPosition,yPosition,AttributeDialog.attribute5));
			saveData[xPosition][yPosition]="	<levelswitch>" + xPosition + ":" + yPosition + ";" + AttributeDialog.attribute5 + "</levelswitch>" + "\n";
			((GamePanel)level).render(1,sprites);
			level.repaint();
		}
	}
}
