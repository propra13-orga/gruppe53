package progprak.gruppe53.game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class ImageLoader {

	private static HashMap<String, BufferedImage> images = new HashMap<String,BufferedImage>();
	
	public static BufferedImage loadImage(String imagePath){
		
		BufferedImage image = null;
		
		if (imagePath != null) {
			if ((image = images.get(imagePath)) == null) {
				URL picUrl = ImageLoader.class.getClassLoader().getResource(
						imagePath);
				System.out.println(picUrl);
				try {
					image = ImageIO.read(picUrl);
					images.put(imagePath, image);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return image;
	}
}
