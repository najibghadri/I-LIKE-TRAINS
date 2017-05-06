package graphics;

import iliketrains.Cart;
import iliketrains.TrackComponent;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.List;

public abstract class Drawable {
	
	protected Point pos;
	protected List<BufferedImage> textures;
	protected int rotation;
	protected static Resources resources;
	protected AffineTransform at;

	
	public Drawable(String line){}
	
	// ezt a fv-t majd a játékpanel @override paintComponent()-jének fv-ébe kell meghívni, előtte egy super.paintcomponent() utána egy repaint() hívással (valszeg :))
	public abstract void draw(Graphics g);
	
	public void setTrackReference(TrackComponent track) {
		//Csak switchben és TunnelGateben kell felülírni, többinél nem kell ref
	}
	
	public void setCartReference(Cart cart){
		//Csak Kocsikat megjelenítő osztályban kell
	}
	
	/**
	 * Affin transzformációkkal meghatározza a kép helyét a panelen. 
	 * Ezt elég egyszer számolni a síneknél, helyük nem változik
	 * @return
	 */
	protected AffineTransform affinGod() {
		at = new AffineTransform();

		//Méretezi a képet
        at.scale(0.1,0.1);

        // forgatás (a szög (rotation) a negatív irányba való eltérést jelzi)
        // az utolsó két paraméter a forgópont (középpont)
        //Középpont: pozíció*1/scale+kép szélesség/2*1/scale
        at.rotate((Math.PI*rotation)/180, pos.x*10+300,pos.y*10+300);
        
        // a megfelelő pontra való mozgatás
        at.translate(pos.getX()*10, pos.getY()*10);
		return at;
	}
}
