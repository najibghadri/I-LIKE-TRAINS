package graphics;

import iliketrains.Cart;
import iliketrains.Color;
import iliketrains.PassengerCart;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import skeleton.IliketrainsGUI;

public class PassengerCartGraphics extends Drawable {

	private PassengerCart passengerCart;

	public PassengerCartGraphics(int x, int y, int rotation) {
		super(x, y, rotation);
	}

	@Override
	public void draw(Graphics g) {
		move();

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

	public void setCartReference(Cart pcart) {
		// ha átállítjuk a referenciát, akkor a régi textúrák helyett újakat kell betölteni
		textures.clear();
		passengerCart = (PassengerCart)pcart;
		Color c=passengerCart.getColor();
		textures.add(Resources.getTexture(c.toString()+"PassengerCartEmpty"));
		textures.add(Resources.getTexture(c.toString()+"PassengerCartFull"));
	}
	
	protected void move() {
		if(passengerCart.getCurrentTrack()==null){
			return;
		}
		AffineTransform tr = new AffineTransform();

		Drawable current=IliketrainsGUI.getTrackMap().get(passengerCart.getCurrentTrack().getId());
		Point p=current.getPos();
		
        // forgatás (a szög (rotation) a negatív irányba való eltérést jelzi)
        tr.rotate((Math.PI*current.getRotation())/180, p.x+30,p.y+30);
        
        // a megfelelő pontra való mozgatás
        tr.translate(p.getX(), p.getY());
        transform=tr;
	}

}
