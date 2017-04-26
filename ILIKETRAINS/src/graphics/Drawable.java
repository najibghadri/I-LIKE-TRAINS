package graphics;

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
	
	public abstract void draw(Graphics g);
}
