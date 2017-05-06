package graphics;

import iliketrains.Cart;
import iliketrains.CoalCart;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class CoalCartGraphics extends Drawable {

	private CoalCart coalCart;

	public CoalCartGraphics(int x, int y, int rotation) {
		super(x, y, rotation);
		// TODO: coalcart textura
		textures.add(Resources.getTexture(""));
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
		coalCart = (CoalCart) cart;
	}
}
