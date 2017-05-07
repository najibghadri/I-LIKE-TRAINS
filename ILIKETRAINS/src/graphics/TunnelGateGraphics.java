package graphics;

import iliketrains.Controllable;
import iliketrains.TunnelGate;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Alagútbejárat kirajzolását végző osztály
 */
public class TunnelGateGraphics extends Drawable {

	/** Referencia az alagútosztályra */
	private TunnelGate tunnelGate;

	/**
	 * Konstruktor
	 *
	 * @param x pozíció
	 * @param y pozíció
	 * @param rotation elforgatás szöge
	 */
	public TunnelGateGraphics(int x, int y, int rotation) {
		super(x, y, rotation);
		textures.add(Resources.getTexture("tunnelGateInactive"));
		textures.add(Resources.getTexture("tunnelGateActive"));
	}

	/**
	 * Kirajzolást végző függvény
	 * 
	 * @param g Amire a rajzolást végezük
	 */
	@Override
	public void draw(Graphics g) {
		// lekérdezzük a tunnelGate állapotát
		int picNum;
		if (tunnelGate.getState())
			picNum = 1;
		else
			picNum = 0;
		// ez alapján a megfelelő képet töltjük be
		BufferedImage img = textures.get(picNum);

		// kirajzolás
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, transform, null);

	}

	/**
	 * Eltárolja a kapott referenciát
	 *
	 * @param controllable Alagútbejárat referencia
	 */
	public void setTrackReference(Controllable controllable) {
		tunnelGate = (TunnelGate) controllable;
	}
}
