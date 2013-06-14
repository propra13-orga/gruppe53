package progprak.gruppe53.game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

abstract public class Sprite extends Rectangle2D.Double implements Drawable,Movable  {


	private static final long serialVersionUID = -3479809511724931446L;
	
	/*
	 * The Spriteimage (no Animation!!!!)
	 */
	protected BufferedImage image;
	/*
	 * Path to the Image
	 */
	String imagePath;
	protected double dx = 0;
	protected double dy = 0;
	protected boolean draw = true;
	private double angle = 0;
	

	public Sprite(int x, int y, String imagePath) {
		this.imagePath = imagePath;
		loadImage();
		this.x = x;
		this.y = y;
		this.width = 32;
		this.height = 32;
	}

	private void loadImage(){
		image = ImageLoader.loadImage(imagePath);	
	}

	@Override
	public void drawObjects(Graphics g) {
		if(angle != 0){
			AffineTransform trans = AffineTransform.getRotateInstance( angle,image.getWidth()/2,image.getHeight()/2);
			BufferedImage dest = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

			Graphics2D gd = dest.createGraphics();
			gd.drawImage(image, trans, null);

			g.drawImage(dest, (int)x, (int)y, null);
			gd.dispose();
		}
		else {
			g.drawImage(image, (int)x, (int)y, null);
		}
	}
	public void move(long delta){
		x = x+dx;
		y = y+dy;
	}
	
	//declared in subclasses
	public void doLogic()
	{
		
	}

	public boolean isDraw() {
		return draw;
	}

	/**
	 * @param angle the angle to set
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}

}
