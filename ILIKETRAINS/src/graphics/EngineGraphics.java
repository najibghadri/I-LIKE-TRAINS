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
		move();

        // Most az első képet kéri le
        BufferedImage img = textures.get(0);
		// kirajzolás
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, transform, null);
	}

	public void setCartReference(Cart cart) {
		engine = (Engine) cart;
	}

	protected void move() {
		AffineTransform tr = new AffineTransform();

		if(engine.getCurrentTrack()==null){
			tr.scale(0, 0);
			transform=tr;
			return;
		}
        Drawable current=IliketrainsGUI.getTrackMap().get(engine.getCurrentTrack().getId());

        if(current == null){
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
