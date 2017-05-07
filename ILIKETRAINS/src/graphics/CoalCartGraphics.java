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
public class CoalCartGraphics extends Drawable {

	/** Referencia a logikai kocsihoz */
	private CoalCart coalCart;

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

	/**
	 * Beállítja a paraméterben megadott kocsi referenciáját magának
	 *
	 * @param cart Egy kocsi referenciája
	 */
	public void setCartReference(Cart cart) {
		coalCart = (CoalCart) cart;
	}
	
	/**
	 * Lépés megvalósítása
	 */
	protected void move() {
		if(coalCart.getCurrentTrack()==null){
			return;
		}
		AffineTransform tr = new AffineTransform();

		Drawable current=IliketrainsGUI.getTrackMap().get(coalCart.getCurrentTrack().getId());
		if(current==null){
			tr.scale(0, 0);
			transform=tr;
			return;
		}
		Point p=current.getPos();
		
        // forgatás (a szög (rotation) a negatív irányba való eltérést jelzi)
        tr.rotate((Math.PI*current.getRotation())/180, p.x+30,p.y+30);
        
        // a megfelelő pontra való mozgatás
        tr.translate(p.getX(), p.getY());
        transform=tr;
	}
}
