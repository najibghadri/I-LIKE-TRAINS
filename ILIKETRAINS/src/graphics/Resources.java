package graphics;

import java.awt.Image;
import java.util.Map;

public class Resources {
	private Map<String, Image> textures;
	
	public Resources(String filepath){
		//TODO textures feltöltése
	}
	
	public Image getTexture(String name){
		return textures.get(name);
	}
}
