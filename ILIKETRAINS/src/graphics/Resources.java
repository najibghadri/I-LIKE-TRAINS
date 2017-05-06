package graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class Resources {
	private static Map<String, BufferedImage> textures;
	
	public Resources(String filepath){
		//TODO textures feltöltése
		textures=new HashMap<String, BufferedImage>();
		try {
			BufferedImage im=ImageIO.read(new File(generateFilename("turn_side_active")));
			textures.put("entry", im);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static BufferedImage getTexture(String name){
		return textures.get(name);
	}
	
	public String generateFilename(String name) {
		String FILENAME =System.getProperty("user.dir");
		if(name.contains(".png")){
			FILENAME=FILENAME+"\\res\\textures\\"+name;
		}
		else
			FILENAME=FILENAME+"\\res\\textures\\"+name+".png";
		return FILENAME;
	}
}
