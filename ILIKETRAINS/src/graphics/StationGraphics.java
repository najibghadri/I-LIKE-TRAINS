package graphics;

import iliketrains.Color;
import iliketrains.Station;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Állomáskirajzolását segítő osztály
 */
public class StationGraphics extends Drawable{
	
	/** Az állomás referenciája */
	private Station station;

	/**
	 * Konstruktor
	 *
	 * @param x X pozíció
	 * @param y Y pozíció
	 * @param rotation Elforgatás szöge
	 */
	public StationGraphics(int x, int y, int rotation) {
		super(x, y, rotation);
	}

	/**
	 * Kirajzolást végző függvény
	 * 
	 * @param g Amire a kirazolást végezzük
	 */
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
	
	/**
	 * Beállítja a referenciát az állomásra
	 *
	 * @param s Állomás referenciája
	 */
	public void setStationReference(Station s){
		// ha átállítjuk a referenciát, akkor a régi textúrák helyett újakat kell betölteni
		textures.clear();
		station= s;
		Color c=station.getColor();
		textures.add(Resources.getTexture(c.toString()+"StationEmpty"));
		textures.add(Resources.getTexture(c.toString()+"StationFull"));
	}
}
