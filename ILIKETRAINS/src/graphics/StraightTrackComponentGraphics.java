package graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Egyenes sínelem kirajzolását segítő osztály
 */
public class StraightTrackComponentGraphics extends Drawable{

	/**
	 * Konstruktor
	 *
	 * @param x pozíció
	 * @param y pozíció
	 * @param rotation elforgatás szöge
	 */
	public StraightTrackComponentGraphics(int x,int y,int rotation) {
		super(x, y, rotation);
		textures.add(Resources.getTexture("straightTrackComponent"));
	}

	/**
	 * Kirajzolást és transzfromációt végző függvény
	 * 
	 * @param g Amire kirajzoljuk az elemet
	 */
	@Override
	public void draw(Graphics g) {
		//Most az első képet kéri le
		BufferedImage img = textures.get(0);
		
        // kirajzolás
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(img, transform, null);
	}
}
