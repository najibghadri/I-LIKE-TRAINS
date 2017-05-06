package graphics;

import iliketrains.Controllable;
import iliketrains.Switch;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class SwitchGraphics extends Drawable{
	
	private Switch switchy;

	public SwitchGraphics(int x,int y,int rotation) {
		super(x, y, rotation);
		textures.add(Resources.getTexture("switchActive"));
		textures.add(Resources.getTexture("switchInactive"));
	}

	@Override
	public void draw(Graphics g) {
		//lekérdezzük a váltó állását
		int picNum;
		if(switchy.getState())
			picNum = 1;
		else
			picNum = 0;
		//ez alapján a megfelelő képet töltjük be
		BufferedImage img = textures.get(picNum);
		
        // kirajzolás
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(img, transform, null);
	}
	
	public void setTrackReference(Controllable controllable) {
		switchy = (Switch) controllable;
	}

}
