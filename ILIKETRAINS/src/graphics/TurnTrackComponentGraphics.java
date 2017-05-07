package graphics;

import iliketrains.TrackComponent;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * A kanyar sínelem kirajzolását végző osztály
 */
public class TurnTrackComponentGraphics extends TrackDrawable {

	/**
	 * Konstruktor
	 *
	 * @param x pozíció
	 * @param y pozíció
	 * @param rotation elforgatás szöge
	 */
	public TurnTrackComponentGraphics(int x, int y, int rotation) {
		super(x, y, rotation);
		textures.add(Resources.getTexture("turnTrackComponent"));
	}

	@Override
	public int getCartRotation(TrackComponent previous) {
		return trackComponent.getNextDir(previous) * 180 + 45;
	}

	/**
	 * Kirajzolást végző függvény
	 * 
	 * @param g Amire a kirajzolást végezzük
	 */
	@Override
	public void draw(Graphics g) {
		// Most az első képet kéri le
		BufferedImage img = textures.get(0);

		// kirajzolás
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, transform, null);
	}
}
