package graphics;

import iliketrains.Cart;
import iliketrains.TrackComponent;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.List;

public abstract class Drawable {
	
	protected Point pos;
	protected List<Image> textures;
	protected int rotation;
	protected static Resources resources;
	
	public Drawable(String line){}
	
	// ezt a fv-t majd a játékpanel @override paintComponent()-jének fv-ébe kell meghívni, előtte egy super.paintcomponent() utána egy repaint() hívással (valszeg :))
	public abstract void draw(Graphics g);
	
	public void setTrackReference(TrackComponent track) {
		//Csak switchben és TunnelGateben kell felülírni, többinél nem kell ref
	}
	
	public void setCartReference(Cart cart){
		//Csak Kocsikat megjelenítő osztályban kell
	}
}
