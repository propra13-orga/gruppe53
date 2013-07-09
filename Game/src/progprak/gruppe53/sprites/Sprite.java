package progprak.gruppe53.sprites;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import progprak.gruppe53.game.Drawable;
import progprak.gruppe53.game.ImageLoader;
import progprak.gruppe53.game.Movable;

abstract public class Sprite  implements Drawable,Movable,Serializable  {


	private static final long serialVersionUID = -3479809511724931446L;
	

	/*
	 * Path to the Image
	 */
	String imagePath;
	protected double dx = 0;
	protected double dy = 0;
	protected boolean draw = true;
	private double angle = 0;
	private double x;
	private double y;
	private double height;
	private double width;
	

	public Sprite(int x, int y, String imagePath) {
		this.imagePath = imagePath;
		//loadImage();
		this.setX(x);
		this.setY(y);
		this.setWidth(32);
		this.setHeight(32);
	}
	public void setImage(String imagePath) {
		this.imagePath = imagePath;
	}


	@Override
	public void drawObjects(Graphics g) {
		BufferedImage image = ImageLoader.loadImage(imagePath);
		if(angle != 0){
			AffineTransform trans = AffineTransform.getRotateInstance( angle,image.getWidth()/2,image.getHeight()/2);
			BufferedImage dest = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

			Graphics2D gd = dest.createGraphics();
			gd.drawImage(image, trans, null);

			g.drawImage(dest, (int)getX(), (int)getY(), null);
			gd.dispose();
		}
		else {
			g.drawImage(image, (int)getX(), (int)getY(), null);
		}
	}
	public void move(long delta){
		setX(getX()+dx);
		setY(getY()+dy);
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
	public Rectangle2D.Double getHorizontalCollsionRect(){
		return new Rectangle2D.Double((int)(getX()+dx), (int)(getY()), (int)getWidth(), (int)getHeight());
	}
	public Rectangle2D.Double getVerticalCollsionRect(){
		return new Rectangle2D.Double((int)(getX()), (int)(getY()+dy), (int)getWidth(), (int)getHeight());
	}
	public BufferedImage getImage(){
		return ImageLoader.loadImage(imagePath);
	}
	
	public synchronized void test(){
		System.out.println(imagePath);
	}
	
	/*private synchronized void writeObject( ObjectOutputStream s ) throws IOException {
		s.defaultWriteObject();
	}
	
	private synchronized void readObject( java.io.ObjectInputStream s )  throws IOException, ClassNotFoundException {
		s.defaultReadObject();
		
	}
	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}
	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}
	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}
	/**
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}
	public Rectangle2D.Double getRectangle() {
		return new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight());
	}
	/**
	 * @param x the x to set
	 */
	public double setX(double x) {
		this.x = x;
		return x;
	}
	/**
	 * @param y the y to set
	 */
	public double setY(double y) {
		this.y = y;
		return y;
	}
	/**
	 * @param height the height to set
	 */
	public double setHeight(double height) {
		this.height = height;
		return height;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(double width) {
		this.width = width;
	}
	@Override
	public String toString() {
		return super.toString() + "[x:" + x + "y:" + y + "h:" + height + "w:" + width + "]";
	}
}
