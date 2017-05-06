package graphics;

import iliketrains.Cart;
import iliketrains.Engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class EngineGraphics extends Drawable {

	private Engine engine;

	public EngineGraphics(int x, int y, int rotation) {
		super(x, y, rotation);
		// TODO: engine textura
		textures.add(Resources.getTexture("engine"));
	}

	@Override
	public void draw(Graphics g) {
		// Most az első képet kéri le
		BufferedImage img = textures.get(0);

		// kirajzolás
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, transform, null);
	}

	public void setCartReference(Cart cart) {
		engine = (Engine) cart;
	}

}
