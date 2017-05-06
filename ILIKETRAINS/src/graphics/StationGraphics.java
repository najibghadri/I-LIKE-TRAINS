package graphics;

import iliketrains.Station;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class StationGraphics extends Drawable{
	
	private Station station;

	public StationGraphics(int x, int y, int rotation) {
		super(x, y, rotation);
		
	}

	@Override
	public void draw(Graphics g) {
		// Most az első képet kéri le
		BufferedImage img = textures.get(0);

		// kirajzolás
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, transform, null);
	}
	
	public void setStationReference(Station s){
		station= s;
	}
}
