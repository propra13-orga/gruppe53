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
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import progprak.gruppe53.game.GamePanel;
import progprak.gruppe53.game.ImageLoader;
import progprak.gruppe53.items.armor.ClothArmor;
import progprak.gruppe53.items.meleeWeapons.WoodenSword;
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
import progprak.gruppe53.sprites.projectils.Fireball;
import progprak.gruppe53.sprites.projectils.FireballTrap;
import progprak.gruppe53.sprites.projectils.FireballTrap2;
import progprak.gruppe53.sprites.projectils.FireballWaveTrap;

/** 
 * The main class for the leveleditor
 */
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
	private static final String ENEMY_OLDMANNPC = "enemyOldManNPC";
	private static final String ENEMY_SPIDER = "enemySpider";
	private static final String TRAP_SPEARS = "trapSpears";
	private static final String TRAP_FIREBALL = "trapFireball";
	private static final String TRAP_FIREBALL2 = "trapFireball2";
	private static final String TRAP_FIREBALLWAVE = "fireballWaveTrap";
	private static final String OBJECT_PORTAL = "portal";
	private static final String OBJECT_LEVELSWITCH = "levelSwitch";
	private static final String OBJECT_GOAL = "goal";
	private static final String OBJECT_SPAWN = "spawn";
	private static final String OBJECT_SWORD = "woodenSword";
	private static final String OBJECT_JACKET = "clothArmor";
	private static final String OBJECT_HEALTHPOTION = "healthPotion";
	private static final String OBJECT_WALLLEVELSWITCH = "wallLevelSwitch";
	private static final String OBJECT_PRESSUREPLATE = "pressurePlate";
	private static final String SAVE = "save";
	private static final String DELETE = "delete";
	private static final String LOAD = "load";
	private static final String EDIT = "edit";
	private static final String GETXY = "getxy";
	
	
	
	private String labelString1 = "X: ";
	private String labelString2 = "Y: ";
	
	private int xPosition;
	private int yPosition;
	private int multiWallStartX = 0;
	private int multiWallStartY = 0;
	private int deleteNumber;
	private boolean multiWallStarted = false;
	public static String saveData[][]=new String[801][641];
	public boolean isEdited = false;
	/** 
	 * The main method for the leveleditor
	 * @param args command string parameter
	 */
	public static void main(String[] args) {
		new LevelEditor();

	}
	
	private JPanel tools, level, separator, attributeBar;
	
	
	private JTextField attribute1, attribute2;
	private JLabel attributeLabel1, attributeLabel2;

	private ArrayList<Sprite> sprites;
	private String currentSprite = "";
	private String saveFileName = "";
	
	/** 
	 * The constructor for the LevelEditor class
	 */
	public LevelEditor() {
		super(WINDOW_NAME);
		setupEditor();
		setVisible(true);
		loadLevel("levels/LevelTemplate.xml");
		((GamePanel)level).render(1,sprites);
		level.repaint();
	}

	/** 
	 * Sets up the layout of the editor
	 */
	private void setupEditor() {
		sprites = new ArrayList<Sprite>();
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
		
		attribute1 = new JTextField(10);
		attribute2 = new JTextField(10);
		attributeLabel1 = new JLabel(labelString1);
		attributeLabel2 = new JLabel(labelString2);
		
		separator.setPreferredSize(new Dimension(SEPARATOR_WIDTH,GAMEPANEL_HEIGHT));
		tools.setPreferredSize(new Dimension(DEFAULT_WINDOW_WIDTH - GAMEPANEL_WIDTH - SEPARATOR_WIDTH, GAMEPANEL_HEIGHT));
		attributeBar.setPreferredSize(new Dimension(DEFAULT_WINDOW_WIDTH, ATTRIBUTEBAR_HEIGHT));
		
		separator.setBackground(Color.YELLOW);
		tools.setBackground(Color.BLUE);
		attributeBar.setBackground(Color.RED);

		setupTools();

		level.addMouseListener(this);

		add(level,BorderLayout.WEST);
		add(separator,BorderLayout.CENTER);
		add(tools,BorderLayout.EAST);
		add(attributeBar,BorderLayout.SOUTH);
		
		attributeBar.add(attributeLabel1);
		attributeBar.add(attribute1);
		attributeBar.add(attributeLabel2);
		attributeBar.add(attribute2);
	}

	/** 
	 * Sets up all buttons for the leveleditor
	 */
	private void setupTools() {
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
		JButton enemyOldManNPC = new JButton(new ImageIcon(ImageLoader.loadImage("images/OldManNPC.png")));
		enemyOldManNPC.setActionCommand(ENEMY_OLDMANNPC);
		enemyOldManNPC.addActionListener(this);
		tools.add(enemyOldManNPC);
		JButton enemySpider = new JButton(new ImageIcon(ImageLoader.loadImage("images/spider.png")));
		enemySpider.setActionCommand(ENEMY_SPIDER);
		enemySpider.addActionListener(this);
		tools.add(enemySpider);
		JButton trapSpears = new JButton(new ImageIcon(ImageLoader.loadImage("images/groundTrap1.png")));
		trapSpears.setActionCommand(TRAP_SPEARS);
		trapSpears.addActionListener(this);
		tools.add(trapSpears);
		JButton trapFireball = new JButton(new ImageIcon(ImageLoader.loadImage("images/FireballRedEditor.png")));
		trapFireball.setPreferredSize(new Dimension(66,42));
		trapFireball.setActionCommand(TRAP_FIREBALL);
		trapFireball.addActionListener(this);
		tools.add(trapFireball);
		JButton trapFireball2 = new JButton("FBT");
		trapFireball2.setPreferredSize(new Dimension(66,42));
		trapFireball2.setActionCommand(TRAP_FIREBALL2);
		trapFireball2.addActionListener(this);
		tools.add(trapFireball2);
		JButton fireballWaveTrap = new JButton(new ImageIcon(ImageLoader.loadImage("images/FireballGreen.png")));
		fireballWaveTrap.setPreferredSize(new Dimension(66,42));
		fireballWaveTrap.setActionCommand(TRAP_FIREBALLWAVE);
		fireballWaveTrap.addActionListener(this);
		tools.add(fireballWaveTrap);
		JButton portal = new JButton(new ImageIcon(ImageLoader.loadImage("images/entrance.png")));
		portal.setActionCommand(OBJECT_PORTAL);
		portal.addActionListener(this);
		tools.add(portal);
		JButton goal = new JButton(new ImageIcon(ImageLoader.loadImage("images/exit.png")));
		goal.setActionCommand(OBJECT_GOAL);
		goal.addActionListener(this);
		tools.add(goal);
		JButton spawn = new JButton(new ImageIcon(ImageLoader.loadImage("images/held.png")));
		spawn.setActionCommand(OBJECT_SPAWN);
		spawn.addActionListener(this);
		tools.add(spawn);
		JButton woodenSword = new JButton(new ImageIcon(ImageLoader.loadImage("images/woodenSwordInventory.png")));
		woodenSword.setPreferredSize(new Dimension(66,42));
		woodenSword.setActionCommand(OBJECT_SWORD);
		woodenSword.addActionListener(this);
		tools.add(woodenSword);
		JButton healthPotion = new JButton(new ImageIcon(ImageLoader.loadImage("images/healthpot1.png")));
		healthPotion.setPreferredSize(new Dimension(66,42));
		healthPotion.setActionCommand(OBJECT_HEALTHPOTION);
		healthPotion.addActionListener(this);
		tools.add(healthPotion);
		JButton wallLevelSwitch = new JButton(new ImageIcon(ImageLoader.loadImage("images/tor1.png")));
		wallLevelSwitch.setActionCommand(OBJECT_WALLLEVELSWITCH);
		wallLevelSwitch.addActionListener(this);
		tools.add(wallLevelSwitch);
		JButton pressurePlate = new JButton(new ImageIcon(ImageLoader.loadImage("images/entrance2.png")));
		pressurePlate.setActionCommand(OBJECT_PRESSUREPLATE);
		pressurePlate.addActionListener(this);
		tools.add(pressurePlate);
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
		JButton edit = new JButton("Edit");
		edit.setPreferredSize(new Dimension(66,42));
		edit.setActionCommand(EDIT);
		edit.addActionListener(this);
		tools.add(edit);
		JButton getxy = new JButton("GetXY");
		getxy.setPreferredSize(new Dimension(66,42));
		getxy.setActionCommand(GETXY);
		getxy.addActionListener(this);
		tools.add(getxy);
	}

	/** 
	 * Sets the effects of the buttons
	 * @param e the actionevent that triggers this method
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
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
			multiWallStarted = false;			
		}
		
		else if (actionCommand == ENEMY_GHOST) {
			image = ImageLoader.loadImage("images/ghost1.png");
			c = toolkit.createCustomCursor(image, new Point(0, 0), "enemyGhost");
			level.setCursor(c);
			currentSprite = actionCommand;			
		}
		
		else if (actionCommand == ENEMY_OLDMANNPC) {
			image = ImageLoader.loadImage("images/OldManNPC.png");
			c = toolkit.createCustomCursor(image,  new Point(0, 0), "enemyOldManNPC");
			level.setCursor(c);
			currentSprite = actionCommand;
		}
		
		else if(actionCommand == ENEMY_SPIDER) {
			image = ImageLoader.loadImage("images/spider.png");
			c = toolkit.createCustomCursor(image,  new Point(0,0), "enemySpider");
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
		else if (actionCommand == TRAP_FIREBALL2) {
			image = ImageLoader.loadImage("images/FireballRedEditor2.png");
			c = toolkit.createCustomCursor(image, new Point(0, 0),"trapFireball2");
			level.setCursor(c);
			currentSprite = actionCommand;			
		}
		
		else if (actionCommand == TRAP_FIREBALLWAVE) {
			image = ImageLoader.loadImage("images/FireballGreen.png");
			c = toolkit.createCustomCursor(image, new Point(0, 0),"fireballWaveTrap");
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
		
		else if (actionCommand == OBJECT_HEALTHPOTION) {
			image = ImageLoader.loadImage("images/healthpot1.png");
			c = toolkit.createCustomCursor(image, new Point(0, 0), "healthPotion");
			level.setCursor(c);
			currentSprite = actionCommand;
		}
		
		else if (actionCommand == OBJECT_WALLLEVELSWITCH) {
			image = ImageLoader.loadImage("images/tor1.png");
			c = toolkit.createCustomCursor(image, new Point(0, 0), "wallLevelSwitch");
			level.setCursor(c);
			currentSprite = actionCommand;
		}
		
		else if (actionCommand == OBJECT_PRESSUREPLATE) {
			image = ImageLoader.loadImage("images/entrance2.png");
			c = toolkit.createCustomCursor(image, new Point(0, 0), "pressurePlate");
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
		
		else if (actionCommand == EDIT) {
			c = Cursor.getDefaultCursor();
			level.setCursor(c);
			currentSprite = actionCommand;
		}
		
		else if (actionCommand == GETXY) {
			c = Cursor.getDefaultCursor();
			level.setCursor(c);
			currentSprite = actionCommand;
		}
	}

	/** 
	 * Adds the currently selected sprite to the gamepanel on click, calls the attributedialog for additional parameters or calls the Save / Load / Edit / Delete / Get methods
	 * @param the mouseevent that triggers this method
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
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
		else if (currentSprite == ENEMY_OLDMANNPC) {
			if(checkCollision(e.getX(),e.getY(),32,32)==false){
				sprites.add(new EnemyOldManNPC(e.getX(),e.getY(),null));
			}
		}
		else if (currentSprite == ENEMY_SPIDER) {
			if(checkCollision(e.getX(),e.getY(),32,32)==false){
				sprites.add(new EnemySpider(e.getX(),e.getY(),null));
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
		else if (currentSprite == TRAP_FIREBALL2) {
			if(checkCollision(e.getX(),e.getY(),16,16)==false){
				xPosition = e.getX();
				yPosition = e.getY();
				new AttributeDialog(this,TRAP_FIREBALL2);
			}
		}
		else if (currentSprite == TRAP_FIREBALLWAVE) {
			if(checkCollision(e.getX(),e.getY(),16,16)==false){
				xPosition = e.getX();
				yPosition = e.getY();
				new AttributeDialog(this,TRAP_FIREBALLWAVE);
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
		else if (currentSprite == OBJECT_WALLLEVELSWITCH) {
			if(checkCollision(e.getX(),e.getY(),32,32)==false){
				xPosition = e.getX();
				yPosition = e.getY();
				new AttributeDialog(this,OBJECT_WALLLEVELSWITCH);
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
				sprites.add(new ClothArmor(e.getX(),e.getY(), null));
			}
		}
		else if (currentSprite == OBJECT_HEALTHPOTION) {
			if(checkCollision(e.getX(),e.getY(),32,32)==false){
				sprites.add(new HealthPotion(e.getX(),e.getY(), null));
			}
		}
		else if (currentSprite == OBJECT_PRESSUREPLATE) {
			if(checkCollision(e.getX(),e.getY(),32,32)==false){
				xPosition = e.getX();
				yPosition = e.getY();
				new AttributeDialog(this,OBJECT_PRESSUREPLATE);
			}
		}
		else if (currentSprite == DELETE) {
			int x = e.getX();
			int y = e.getY();
			for(int i=0;i<sprites.size();i++){
				Sprite sprite = sprites.get(i);
				int size = 32;
				
				if(sprite instanceof Fireball){
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
		else if (currentSprite == EDIT) {
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
						deleteNumber = i;
						isEdited = true;
						if(sprite instanceof FireballTrap){							
							new AttributeDialog(this,TRAP_FIREBALL);
						}
						else if(sprite instanceof FireballTrap2){							
							new AttributeDialog(this,TRAP_FIREBALL2);
						}
						else if(sprite instanceof FireballWaveTrap){							
							new AttributeDialog(this,TRAP_FIREBALLWAVE);
						}
						else if(sprite instanceof PortalEntrance){							
							new AttributeDialog(this,OBJECT_PORTAL);
						}
						else if(sprite instanceof LevelSwitch){							
							new AttributeDialog(this,OBJECT_LEVELSWITCH);
						}
						else if(sprite instanceof WallLevelSwitch){							
							new AttributeDialog(this,OBJECT_WALLLEVELSWITCH);
						}
						else if(sprite instanceof PressurePlate){							
							new AttributeDialog(this,OBJECT_PRESSUREPLATE);
						}
					}
					if(sprite.getY()>=y && sprite.getY()<y+1){
						deleteNumber = i;
						isEdited = true;
						if(sprite instanceof FireballTrap){							
							new AttributeDialog(this,TRAP_FIREBALL);
						}
						else if(sprite instanceof FireballTrap2){							
							new AttributeDialog(this,TRAP_FIREBALL2);
						}
						else if(sprite instanceof FireballWaveTrap){							
							new AttributeDialog(this,TRAP_FIREBALLWAVE);
						}
						else if(sprite instanceof PortalEntrance){							
							new AttributeDialog(this,OBJECT_PORTAL);
						}
						else if(sprite instanceof LevelSwitch){							
							new AttributeDialog(this,OBJECT_LEVELSWITCH);
						}
						else if(sprite instanceof WallLevelSwitch){							
							new AttributeDialog(this,OBJECT_WALLLEVELSWITCH);
						}
						else if(sprite instanceof PressurePlate){							
							new AttributeDialog(this,OBJECT_PRESSUREPLATE);
						}
					}
				}
				if(sprite.getX()>=x && sprite.getX()<x+1){
					if(sprite.getY()>=y && sprite.getY()<y+1){
						deleteNumber = i;
						isEdited = true;
						if(sprite instanceof FireballTrap){							
							new AttributeDialog(this,TRAP_FIREBALL);
						}
						else if(sprite instanceof FireballTrap2){							
							new AttributeDialog(this,TRAP_FIREBALL2);
						}
						else if(sprite instanceof FireballWaveTrap){							
							new AttributeDialog(this,TRAP_FIREBALLWAVE);
						}
						else if(sprite instanceof PortalEntrance){							
							new AttributeDialog(this,OBJECT_PORTAL);
						}
						else if(sprite instanceof LevelSwitch){							
							new AttributeDialog(this,OBJECT_LEVELSWITCH);
						}
						else if(sprite instanceof WallLevelSwitch){							
							new AttributeDialog(this,OBJECT_WALLLEVELSWITCH);
						}
						else if(sprite instanceof PressurePlate){							
							new AttributeDialog(this,OBJECT_PRESSUREPLATE);
						}
					}
					if(sprite.getY()<=y && sprite.getY()+size>y){
						deleteNumber = i;
						isEdited = true;
						if(sprite instanceof FireballTrap){							
							new AttributeDialog(this,TRAP_FIREBALL);
						}
						else if(sprite instanceof FireballTrap2){							
							new AttributeDialog(this,TRAP_FIREBALL2);
						}
						else if(sprite instanceof FireballWaveTrap){							
							new AttributeDialog(this,TRAP_FIREBALLWAVE);
						}
						else if(sprite instanceof PortalEntrance){							
							new AttributeDialog(this,OBJECT_PORTAL);
						}
						else if(sprite instanceof LevelSwitch){							
							new AttributeDialog(this,OBJECT_LEVELSWITCH);
						}
						else if(sprite instanceof WallLevelSwitch){							
							new AttributeDialog(this,OBJECT_WALLLEVELSWITCH);
						}
						else if(sprite instanceof PressurePlate){							
							new AttributeDialog(this,OBJECT_PRESSUREPLATE);
						}
					}
				}
				if(sprite.getX()==x && sprite.getX()==x+1){
					if(sprite.getY()==y && sprite.getY()==y+1){
						deleteNumber = i;
						isEdited = true;
						if(sprite instanceof FireballTrap){							
							new AttributeDialog(this,TRAP_FIREBALL);
						}
						else if(sprite instanceof FireballTrap2){							
							new AttributeDialog(this,TRAP_FIREBALL2);
						}
						else if(sprite instanceof FireballWaveTrap){							
							new AttributeDialog(this,TRAP_FIREBALLWAVE);
						}
						else if(sprite instanceof PortalEntrance){							
							new AttributeDialog(this,OBJECT_PORTAL);
						}
						else if(sprite instanceof LevelSwitch){							
							new AttributeDialog(this,OBJECT_LEVELSWITCH);
						}
						else if(sprite instanceof WallLevelSwitch){							
							new AttributeDialog(this,OBJECT_WALLLEVELSWITCH);
						}
						else if(sprite instanceof PressurePlate){							
							new AttributeDialog(this,OBJECT_PRESSUREPLATE);
						}
					}
				}
			}
		}
		else if (currentSprite == GETXY) {
			attribute1.setText("" + e.getX());
			attribute2.setText("" + e.getY());
		}
		((GamePanel)level).render(1,sprites);
		level.repaint();
	}
	/** 
	 * Required method for the mouselistener - empty
	 */
	@Override
	public void mousePressed(MouseEvent e) {
	}

	/** 
	 * Required method for the mouselistener - empty
	 */
	@Override
	public void mouseReleased(MouseEvent e) {	
	}

	/** 
	 * Required method for the mouselistener - empty
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	/** 
	 * Required method for the mouselistener - empty
	 */
	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	/** 
	 * Checks if the sprite that should be placed collides with another sprite already on the field
	 * @param x The x-coordinate of the sprite to be placed
	 * @param y The y-coordinate of the sprite to be placed
	 * @param width The width of the sprite to be placed
	 * @param heigth The heigth of the sprite to be placed
	 */
	private boolean checkCollision(int x, int y, int width, int heigth){
		//Prueft beim setzen von Sprites aufs Feld ob dort nicht bereits etwas ist, falls ja wird kein neuer Sprite gesetzt
		for(int i=0;i<sprites.size();i++){
			Sprite sprite = sprites.get(i);
			int size = 32;
			
			if(sprite instanceof FireballTrap){
				size = 16;				
			}
			else if(sprite instanceof FireballTrap2){
				size = 16;				
			}
			else if(sprite instanceof FireballWaveTrap){
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
	
	/** 
	 * Sets the filename under which the created level is saved
	 */
	public void setSaveFileName(String saveFileName){
		this.saveFileName = saveFileName;
	}
	
	/** 
	 * Calls the levelsaver to save the created level
	 */
	public void saveLevel(){
		new LevelSaver(saveFileName, sprites).saveLevel();
	}
	
	/** 
	 * Loads an existing level into the editor using the editorlevelloader
	 * @param newLevel The path to the level to be loaded
	 */
	public void loadLevel(String newLevel){
		ArrayList<Sprite> sp = new ArrayList<Sprite>();
		EditorLevelLoader.generateLevel(newLevel,sp);
		sprites = sp;
		((GamePanel)level).render(1,sprites);
		level.repaint();
	}
	
	/** 
	 * Adds the sprites with additional parameters to the gamepanel after they have been set in the attributedialog
	 */
	public void addSprite(String spriteType){
		if (spriteType == "trapFireball"){
			sprites.add(new FireballTrap(xPosition,yPosition,null,AttributeDialog.attribute6,AttributeDialog.attribute7,"images/FireballRed.png"));
			saveData[xPosition][yPosition]="		<fireballtrap>" + xPosition + ":" + yPosition + ";" + AttributeDialog.attribute6 + ":" + AttributeDialog.attribute7 + "</fireballtrap>" + "\n";
			((GamePanel)level).render(1,sprites);
			level.repaint();
		}
		if (spriteType == "trapFireball2"){
			sprites.add(new FireballTrap2(xPosition,yPosition,null,AttributeDialog.attribute6,AttributeDialog.attribute7,AttributeDialog.attribute1,AttributeDialog.attribute2,"images/FireballRed.png"));
			saveData[xPosition][yPosition]="		<fireballtrap2>" + xPosition + ":" + yPosition + ";" + AttributeDialog.attribute6 + ":" + AttributeDialog.attribute7 + AttributeDialog.attribute1 + ":" + AttributeDialog.attribute2 + "</fireballtrap2>" + "\n";
			((GamePanel)level).render(1,sprites);
			level.repaint();
		}
		if (spriteType == "fireballWaveTrap"){
			sprites.add(new FireballWaveTrap(xPosition,yPosition,null,AttributeDialog.attribute1,AttributeDialog.attribute2,"images/FireballGreen.png"));
			saveData[xPosition][yPosition]="		<fireballwavetrap>" + xPosition + ":" + yPosition + ";" + AttributeDialog.attribute1 + ":" + AttributeDialog.attribute2 + "</fireballwavetrap>" + "\n";
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
		else if (spriteType == "wallLevelSwitch"){
			sprites.add(new WallLevelSwitch(xPosition,yPosition,AttributeDialog.attribute1,AttributeDialog.attribute5));
			LevelEditor.saveData[xPosition][yPosition]="	<levelswitch type=\"wall\" direction=\"" + AttributeDialog.attribute1 + "\">" + xPosition + ":" + yPosition + ";" + AttributeDialog.attribute5 + "</levelswitch>" + "\n";
			((GamePanel)level).render(1,sprites);
			level.repaint();
		}
		else if (spriteType == "pressurePlate"){
			sprites.add(new PressurePlate(xPosition,yPosition,AttributeDialog.affectedWallsArray));
			LevelEditor.saveData[xPosition][yPosition]="	<pressureplate>" + xPosition + ":" + yPosition + "-";
			for (int i = 0; i<AttributeDialog.levelSaverArray.length;i++) {
				LevelEditor.saveData[xPosition][yPosition]+= AttributeDialog.levelSaverArray[i];
				if (i != AttributeDialog.levelSaverArray.length - 1) {
					LevelEditor.saveData[xPosition][yPosition]+= ";";
				}
			}
			LevelEditor.saveData[xPosition][yPosition]+="</pressureplate>" + "\n";
			((GamePanel)level).render(1,sprites);
			level.repaint();
		}
	}
	
	/** 
	 * After a sprite was edited, this method deletes the old sprite
	 */
	public void deleteEdited() {
		sprites.remove(deleteNumber);
	}
}
