package graphics;

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
			loadTexture("switch2Active");
			loadTexture("switch2Inactive");
			loadTexture("tunnelGateActive");
			loadTexture("tunnelGateInactive");
			loadTexture("turnTrackComponent");
			loadTexture("BlueStationFull");
			loadTexture("BlueStationEmpty");
			loadTexture("RedStationFull");
			loadTexture("RedStationEmpty");
			loadTexture("GreenStationFull");
			loadTexture("GreenStationEmpty");
			loadTexture("YellowStationFull");
			loadTexture("YellowStationEmpty");
			loadTexture("BrownStationFull");
			loadTexture("BrownStationEmpty");
			loadTexture("engine");
			loadTexture("BluePassengerCartEmpty");
			loadTexture("BluePassengerCartFull");
			loadTexture("RedPassengerCartEmpty");
			loadTexture("RedPassengerCartFull");
			loadTexture("GreenPassengerCartEmpty");
			loadTexture("GreenPassengerCartFull");
			loadTexture("BrownPassengerCartEmpty");
			loadTexture("BrownPassengerCartFull");
			loadTexture("YellowPassengerCartEmpty");
			loadTexture("YellowPassengerCartFull");
			loadTexture("CoalCart");
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
