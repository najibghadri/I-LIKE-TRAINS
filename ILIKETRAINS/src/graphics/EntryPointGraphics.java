package graphics;

import iliketrains.TrackComponent;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

// TODO: Auto-generated Javadoc
/**
 * Belépési pontokat kirajzoló osztály.
 */
public class EntryPointGraphics extends TrackDrawable{

	/**
	 * Konstruktor.
	 *
	 * @param x koordináta
	 * @param y koordináta
	 * @param rotation Elforgatás szöge
	 */
	public EntryPointGraphics(int x, int y, int rotation) {
		super(x, y, rotation);
		textures.add(Resources.getTexture("entryPoint"));
	}

	/* (non-Javadoc)
	 * @see graphics.TrackDrawable#getCartRotation(iliketrains.TrackComponent)
	 */
	@Override
	public int getCartRotation(TrackComponent previous) {
		return 0;
	}

	/**
	 * Kirajzolást végző függvény.
	 *
	 * @param g Amire rajzolunk
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
