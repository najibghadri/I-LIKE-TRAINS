package graphics;

import iliketrains.Color;
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
		// lekérdezzük a station várakozó utasait (van/nincs)
		int picNum;
		if (station.getPassengers())
			picNum = 1;
		else
			picNum = 0;
		// ez alapján a megfelelő képet töltjük be
		BufferedImage img = textures.get(picNum);

		// kirajzolás
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, transform, null);
	}
	
	public void setStationReference(Station s){
		station= s;
		Color c=station.getColor();
		textures.add(Resources.getTexture(c.toString()+"StationFull"));
		textures.add(Resources.getTexture(c.toString()+"StationEmpty"));
	}
}
