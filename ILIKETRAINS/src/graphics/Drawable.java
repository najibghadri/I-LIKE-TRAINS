package graphics;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Absztrakt grafikuselem osztály (közös ősnek)
 */
public abstract class Drawable {
	
	/** pozíció */
	protected Point pos;
	
	/** Hozzátartozó textúrák */
	protected List<BufferedImage> textures;
	
	/** Elforgatás szöge */
	protected int rotation;
	
	/** Az erőforrásokat tároló objektumra mutató referencia */
	protected static Resources resources;
	
	/** A transzformációhoz szügéges objektum */
	protected AffineTransform transform;

	
	/**
	 * Konstruktor (transzformációt is végez)
	 *
	 * @param x koordináta
	 * @param y koordináta
	 * @param rotation elforgatás szöge
	 */
	public Drawable(int x,int y,int rotation){
		pos=new Point();
		pos.x=x;
		pos.y=y;
		this.rotation=rotation;
		textures=new ArrayList<BufferedImage>();
		transform=transform();
	}
	
	/**
	 * Kirajzolás abszrakt függvény
	 * Ezt valósítja meg minden leszármazott.
	 * Ezt a fv-t majd a játékpanel @override paintComponent()-jének fv-ébe 
	 * kell meghívni, előtte egy super.paintcomponent() utána egy repaint() hívással)
	 * 
	 * @param g Referenciaértéke arra az objektumra, amire rajzolni fogunk
	 */
	public abstract void draw(Graphics g);
	
	/**
	 * Affin transzformációkkal meghatározza a kép helyét a panelen. 
	 * Ezt elég egyszer számolni a síneknél, helyük nem változik
	 *
	 * @return A transzormált objektummal tér vissza
	 */
	protected AffineTransform transform() {
		AffineTransform tr = new AffineTransform();

		//Méretezi a képet
        //tr.scale(0.1,0.1);

        // forgatás (a szög (rotation) a negatív irányba való eltérést jelzi)
        // az utolsó két paraméter a forgópont (középpont)
        //Középpont: pozíció*1/scale+kép szélesség/2*1/scale
        tr.rotate((Math.PI*rotation)/180, pos.x+30,pos.y+30);
        
        // a megfelelő pontra való mozgatás
        tr.translate(pos.getX(), pos.getY());
        return tr;
	}

	/**
	 * Visszaadja a pozíciót
	 *
	 * @return Pozíció
	 */
	public Point getPos() {
		return pos;
	}

	/**
	 * Visszaadja az elforgatás szögét
	 *
	 * @return Elforgatás szöge
	 */
	public int getRotation() {
		return rotation;
	}
	
	
}
