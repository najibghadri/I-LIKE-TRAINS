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
		textures=new HashMap<String, BufferedImage>();
		try {
			loadTexture("entryPoint");
			loadTexture("straightTrackComponent");
			loadTexture("switchActive");
			loadTexture("switchInactive");
			loadTexture("tunnelGate");
			loadTexture("turnTrackComponent");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadTexture(String name) throws IOException{
		BufferedImage im=ImageIO.read(new File(generateFilename(name)));
		textures.put(name, im);
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
