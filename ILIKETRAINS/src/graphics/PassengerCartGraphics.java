package graphics;

import iliketrains.Cart;
import iliketrains.Color;
import iliketrains.PassengerCart;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Utaskocsi kirajzolásást segítő osztály
 */
public class PassengerCartGraphics extends CartDrawable {
	/**
	 * Konstruktor
	 *
	 * @param x X koordináta
	 * @param y Y koordináta
	 * @param rotation Elforgatás szöge
	 */
	public PassengerCartGraphics(int x, int y, int rotation) {
		super(x, y, rotation);

	}

	/**
	 * Kirajzolást végző függvény
	 * 
	 * @param g Amin a kirajzolást végezzük
	 */
	@Override
	public void draw(Graphics g) {
		move();

		// lekérdezzük a PC utasait (van/nincs)
		int picNum;
		if (((PassengerCart)cart).isNotEmpty())
			picNum = 1;
		else
			picNum = 0;
		// ez alapján a megfelelő képet töltjük be
		BufferedImage img = textures.get(picNum);

		// kirajzolás
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, transform, null);
	}

	/**
	 * Beállítja az utaskocsi referenciáját és a megfelelő texturát betölti
	 *
	 * @param cart A kocsi referenciája
	 */
	@Override
	public void setCartReference(Cart cart) {
		// ha átállítjuk a referenciát, akkor a régi textúrák helyett újakat kell betölteni
        super.setCartReference(cart);
		textures.clear();
		Color c= ((PassengerCart)cart).getColor();
		textures.add(Resources.getTexture(c.toString()+"PassengerCartEmpty"));
		textures.add(Resources.getTexture(c.toString()+"PassengerCartFull"));
	}
}
