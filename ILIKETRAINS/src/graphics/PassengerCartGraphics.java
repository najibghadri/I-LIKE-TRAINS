package graphics;

import iliketrains.Cart;
import iliketrains.Color;
import iliketrains.PassengerCart;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class PassengerCartGraphics extends Drawable {

	private PassengerCart passengerCart;

	public PassengerCartGraphics(int x, int y, int rotation) {
		super(x, y, rotation);
	}

	@Override
	public void draw(Graphics g) {

		// lekérdezzük a PC utasait (van/nincs)
		int picNum;
		if (passengerCart.isNotEmpty())
			picNum = 1;
		else
			picNum = 0;
		// ez alapján a megfelelő képet töltjük be
		BufferedImage img = textures.get(picNum);

		// kirajzolás
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, transform, null);
	}

	public void setCartReference(PassengerCart pcart) {
		// ha átállítjuk a referenciát, akkor a régi textúrák helyett újakat kell betölteni
		textures.clear();
		passengerCart = pcart;
		Color c=pcart.getColor();
		// TODO: passengercart texturák színnek megfelelő
		// utaso van-e rajta vagy nincs
		textures.add(Resources.getTexture(c.toString()+"PassengerCartFull"));
		textures.add(Resources.getTexture(c.toString()+"PassengerCartEmpty"));
	}

}
