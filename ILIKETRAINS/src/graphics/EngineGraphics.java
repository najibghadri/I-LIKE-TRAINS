package graphics;

import iliketrains.Cart;
import iliketrains.Engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import skeleton.IliketrainsGUI;

/**
 * Mozdony kirajzolását segítő függvény
 */
public class EngineGraphics extends CartDrawable {

	/**
	 * Konstruktor
	 *
	 * @param x Kép x koordinátája
	 * @param y Kép y koordinátája
	 * @param rotation Elforgatás szöge
	 */
	public EngineGraphics(int x, int y, int rotation) {
		super(x, y, rotation);
		textures.add(Resources.getTexture("engine"));
	}

	/**
	 * Kirajzolást végző függvény
	 * 
	 * @param g Amin a kirajzolást végezzük
	 */
	@Override
	public void draw(Graphics g) {
		move();

        // Most az első képet kéri le
        BufferedImage img = textures.get(0);
		// kirajzolás
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, transform, null);
	}
}
