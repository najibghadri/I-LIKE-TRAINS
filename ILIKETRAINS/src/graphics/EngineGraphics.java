package graphics;

import iliketrains.Cart;
import iliketrains.Engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import skeleton.IliketrainsGUI;

public class EngineGraphics extends Drawable {

	private Engine engine;

	public EngineGraphics(int x, int y, int rotation) {
		super(x, y, rotation);
		textures.add(Resources.getTexture("engine"));
	}

	@Override
	public void draw(Graphics g) {
		// Most az első képet kéri le
		BufferedImage img = textures.get(0);

		move();
		
		// kirajzolás
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, transform, null);
	}

	public void setCartReference(Cart cart) {
		engine = (Engine) cart;
	}

	protected void move() {
		AffineTransform tr = new AffineTransform();

		Point current=IliketrainsGUI.getTrackMap().get(engine.getCurrentTrack().getId()).getPos();
		
        // forgatás (a szög (rotation) a negatív irányba való eltérést jelzi)
        tr.rotate((Math.PI*rotation)/180, current.x+30,current.y+30);
        
        // a megfelelő pontra való mozgatás
        tr.translate(current.getX(), current.getY());
        transform=tr;
	}
}
