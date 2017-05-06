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

	public EntryPointGraphics(int x,int y,int rotation) {
		super(x,y,rotation);
		textures.add(Resources.getTexture("entryPoint"));
	}

	@Override
	public void draw(Graphics g) {
		//Most az első képet kéri le
		BufferedImage img = textures.get(0);
		
        // kirajzolás
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(img, transform, null);
	}


}
