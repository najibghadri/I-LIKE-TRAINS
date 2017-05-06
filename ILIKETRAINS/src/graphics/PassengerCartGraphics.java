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
		textures.add(Resources.getTexture("passengerCartBlueEmpty"));
		textures.add(Resources.getTexture("passengerCartBlueFull"));	
		textures.add(Resources.getTexture("passengerCartGreenEmpty"));
		textures.add(Resources.getTexture("passengerCartGreenFull"));
		textures.add(Resources.getTexture("passengerCartRedEmpty"));
		textures.add(Resources.getTexture("passengerCartRedFull"));
		textures.add(Resources.getTexture("passengerCartBlackEmpty"));
		textures.add(Resources.getTexture("passengerCartBlackFull"));
		textures.add(Resources.getTexture("passengerCartBrownEmpty"));
		textures.add(Resources.getTexture("passengerCartBrownFull"));
		
	}

	@Override
	public void draw(Graphics g) {
		int colorNum;
		switch(passengerCart.getColor()){
		case Blue: 		colorNum=0; break;
		case Green: 	colorNum=2; break;
		case Red: 		colorNum=4; break;
		case Yellow:	colorNum=6; break;
		case Brown:		colorNum=8; break;
		default: break;
		}
				
		// lekérdezzük a PC utasait (van/nincs)
		int picNum;
		if (passangerCart.isNotEmpty())
			picNum = 1 + colorNum;
		else
			picNum = 0 + colorNum;
		// ez alapján a megfelelő képet töltjük be
		BufferedImage img = textures.get(picNum);
		
		// kirajzolás
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, transform, null);
	}
	
	public void setCartReference(Cart cart){
		passengerCart= (PassengerCart) cart;
	}

}
