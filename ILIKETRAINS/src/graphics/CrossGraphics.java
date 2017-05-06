package graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class CrossGraphics extends Drawable {

	public CrossGraphics(int x, int y, int rotation) {
		super(x, y, rotation);
		// TODO: cross erőforrás
		textures.add(Resources.getTexture("cross"));
	}

	@Override
	public void draw(Graphics g) {
		// Most az első képet kéri le
		BufferedImage img = textures.get(0);

		// kirajzolás
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, transform, null);
	}
}
