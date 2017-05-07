package graphics;

import iliketrains.Controllable;
import iliketrains.Switch;
import iliketrains.TrackComponent;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Váltó kirajzolásást végző függvény
 */
public class SwitchGraphics extends TrackDrawable {

	/** A váltó referenciája, switch szó nem használható... */
	private Switch switchy;

	/**
	 * Konstruktor
	 *
	 * @param x x pozíció
	 * @param y y pozíció
	 * @param rotation elforgatás szöge
	 * @param i Tükrözéshez szükséges paraméter
	 */
	public SwitchGraphics(int x, int y, int rotation, int i) {
		super(x, y, rotation);
		if(i==1){
			textures.add(Resources.getTexture("switch2Active"));
			textures.add(Resources.getTexture("switch2Inactive"));	
		}else{
		textures.add(Resources.getTexture("switchActive"));
		textures.add(Resources.getTexture("switchInactive"));
		}
	}

	/**
	 * Rajzolást végző függvény
	 * 
	 * @param g Amire a rajzolást végezzük
	 */
	@Override
	public void draw(Graphics g) {
		// lekérdezzük a váltó állását
		int picNum;
		if (switchy.getState())
			picNum = 0;
		else
			picNum = 1;
		// ez alapján a megfelelő képet töltjük be
		BufferedImage img = textures.get(picNum);

		// kirajzolás
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, transform, null);
	}

	/**
	 * Beállítja a referenciát a paraméterben kapottra
	 *
	 * @param controllable Paraméterben kapott referencia
	 */
	public void setTrackReference(Controllable controllable) {
		switchy = (Switch) controllable;
	}

	/* (non-Javadoc)
	 * @see graphics.TrackDrawable#getCartRotation(iliketrains.TrackComponent)
	 */
	@Override
	public int getCartRotation(TrackComponent previous) {
		return 0;
	}
}
