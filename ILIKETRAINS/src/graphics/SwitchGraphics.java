package graphics;

import iliketrains.Controllable;
import iliketrains.Switch;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class SwitchGraphics extends Drawable {

	private Switch switchy;

	public SwitchGraphics(int x, int y, int rotation, int i) {
		super(x, y, rotation);
		if(i==1){
			textures.add(Resources.getTexture("switch2Active"));
			textures.add(Resources.getTexture("switch2Inactive"));	
		}else{
		textures.add(Resources.getTexture("switchActive"));
		textures.add(Resources.getTexture("switchInactive"));
		}
	}

	@Override
	public void draw(Graphics g) {
		// lekérdezzük a váltó állását
		int picNum;
		if (switchy.getState())
			picNum = 0;
		else
			picNum = 1;
		// ez alapján a megfelelő képet töltjük be
		BufferedImage img = textures.get(picNum);

		// kirajzolás
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, transform, null);
	}

	public void setTrackReference(Controllable controllable) {
		switchy = (Switch) controllable;
	}

}
