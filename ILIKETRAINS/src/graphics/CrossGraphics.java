package graphics;

import iliketrains.Cross;

import java.awt.Graphics;

public class CrossGraphics extends Drawable{
	
	private Cross cross;

	public CrossGraphics(String line) {
		super(line);
		
	}

	@Override
	public void draw(Graphics g) {
        AffineTransform at = new AffineTransform();
        img = textures.get(0);
        
        // a megfelelő pontra való mozgatás
        at.translate(pos.getX(), pos.getY());

        // forgatás (a szög (rotation) a negatív irányba való eltérést jelzi)
        // az utolsó két paraméter a forgópont (középpont)
        at.rotate(Math.PI/(rotation/180), img.getWidth()/2, img.getHeight()/2);

        // átméretezés
        // TODO milyen legyen a méretezés? Honnan kéne tudni?
        // (alapvetően két Drawable elem középpontjainak távolságától függ, illetve attól, hogy alapvetően mekkora egy textúra)
        // at.scale(x-koordináta szerinti arányszám, y-koordináta szerinti arányszám);

        // kirajzolás
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(img, at, null);
	}

}
