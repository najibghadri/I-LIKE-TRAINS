package graphics;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class Drawable {
	
	protected Point pos;
	protected List<BufferedImage> textures;
	protected int rotation;
	protected static Resources resources;
	protected AffineTransform transform;

	
	public Drawable(int x,int y,int rotation){
		pos=new Point();
		pos.x=x;
		pos.y=y;
		this.rotation=rotation;
		textures=new ArrayList<BufferedImage>();
		transform=transform();
	}
	
	// ezt a fv-t majd a játékpanel @override paintComponent()-jének fv-ébe kell meghívni, előtte egy super.paintcomponent() utána egy repaint() hívással (valszeg :))
	public abstract void draw(Graphics g);
	
	/**
	 * Affin transzformációkkal meghatározza a kép helyét a panelen. 
	 * Ezt elég egyszer számolni a síneknél, helyük nem változik
	 * @return
	 */
	protected AffineTransform transform() {
		AffineTransform tr = new AffineTransform();

		//Méretezi a képet
        tr.scale(0.1,0.1);

        // forgatás (a szög (rotation) a negatív irányba való eltérést jelzi)
        // az utolsó két paraméter a forgópont (középpont)
        //Középpont: pozíció*1/scale+kép szélesség/2*1/scale
        tr.rotate((Math.PI*rotation)/180, pos.x*10+300,pos.y*10+300);
        
        // a megfelelő pontra való mozgatás
        tr.translate(pos.getX()*10, pos.getY()*10);
        return tr;
	}
}
