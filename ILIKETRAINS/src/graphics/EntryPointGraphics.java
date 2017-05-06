package graphics;

import iliketrains.EntryPoint;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class EntryPointGraphics extends Drawable{
	
	private EntryPoint entryPoint;
	private AffineTransform at;

	public EntryPointGraphics(String line) {
		super(line);
		pos=new Point();
		textures=new ArrayList<BufferedImage>();
		affinGod();
	}

	@Override
	public void draw(Graphics g) {
		//Most az első képet kéri le
		BufferedImage img = textures.get(0);

        // kirajzolás
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(img, at, null);
	}

	/**
	 * Affin transzformációkkal meghatározza a kép helyét a panelen. 
	 * Ezt elég egyszer számolni a síneknél, helyük nem változik
	 * @return
	 */
	private AffineTransform affinGod() {
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
