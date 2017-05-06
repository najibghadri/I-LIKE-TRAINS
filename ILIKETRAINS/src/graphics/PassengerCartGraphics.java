package graphics;

import iliketrains.Cart;
import iliketrains.PassengerCart;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class PassengerCartGraphics extends Drawable{
	
	private PassengerCart passengerCart;

	public PassengerCartGraphics() {
		super(0,0,0);
		
		// TODO: passengercart texturák színnek megfelelő
		// utaso van-e rajta vagy nincs
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
	
	public void setCartReference(Cart cart){
		passengerCart= (PassengerCart) cart;
	}

}
