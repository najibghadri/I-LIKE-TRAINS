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
public class EngineGraphics extends Drawable {

	/** Mozdony referenciája */
	private Engine engine;

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
		// Most az első képet kéri le
		BufferedImage img = textures.get(0);

		move();
		
		// kirajzolás
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, transform, null);
	}

	/**
	 * Beállítja a referenciáját a paraméterben megadott
	 * mozdonynak
	 *
	 * @param cart Mozdonyreferencia
	 */
	public void setCartReference(Cart cart) {
		engine = (Engine) cart;
	}

	/**
	 * Lépést megvalósító függvény
	 */
	protected void move() {
		AffineTransform tr = new AffineTransform();

		Drawable current=IliketrainsGUI.getTrackMap().get(engine.getCurrentTrack().getId());
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
