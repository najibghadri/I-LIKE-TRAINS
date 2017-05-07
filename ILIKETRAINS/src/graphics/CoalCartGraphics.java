package graphics;

import iliketrains.Cart;
import iliketrains.CoalCart;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import skeleton.IliketrainsGUI;

/**
 * Szenes kocsi grafikájáhzo
 */
public class CoalCartGraphics extends CartDrawable {

	/**
	 * Konstruktor
	 *
	 * @param x x koordináta
	 * @param y y koordinátáa
	 * @param rotation elforgatás szöge
	 */
	public CoalCartGraphics(int x, int y, int rotation) {
		super(x, y, rotation);
		textures.add(Resources.getTexture("CoalCart"));
	}

	/**
	 *  Rajzolás megvalósítása.
	 * 
	 * @param g Amin a rajzolást végezzük
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
