package graphics;

import iliketrains.Cart;
import skeleton.ILikeTrainsGameGUI;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Általános kocsi kirajzolását alapozza meg. Mozgás rajzolása forgatássa és
 * kocsi referencia beállítását kezeli.
 */
public abstract class CartDrawable extends Drawable {

	/** Kocsi referencia. */
	protected Cart cart;

	/**
	 * Konstruktor
	 *
	 * @param x
	 *            korodináta
	 * @param y
	 *            koordináta
	 * @param rotation
	 *            elforgatás szöge
	 */
	public CartDrawable(int x, int y, int rotation) {
		super(x, y, rotation);
	}

	/**
	 * Beállítja a paraméterben megadott kocsi referenciáját magának.
	 *
	 * @param cart
	 *            Egy kocsi referenciája
	 */
	public void setCartReference(Cart cart) {
		this.cart = cart;
	}

	/**
	 * Mozgatás kirajzolásának logikája.
	 * Meghatározza az új pozíciót és a kocsi iránynak megfelelő forgatását is 
	 */
	protected void move() {
		AffineTransform tr = new AffineTransform();

		if (cart.getCurrentTrack() == null) {
			tr.scale(0, 0);
			transform = tr;
			return;
		}

		TrackDrawable currentTrackGraphics = ILikeTrainsGameGUI.getTrackMap()
				.get(cart.getCurrentTrack().getId());

		if (currentTrackGraphics == null) {
			tr.scale(0, 0);
			transform = tr;
			return;
		}

		Point trackPos = currentTrackGraphics.getPos();

		int cartRot = 0;

		//Ha nincs előző sín, akkor entry, szóval 45 fok
		if (cart.getPrevious() == null) {
			cartRot = currentTrackGraphics.getRotation() + 45;
		} else {
			TrackDrawable previous = ILikeTrainsGameGUI.getTrackMap().get(
					cart.getPrevious().getId());
			//Ha van előző sín, de nincs grafikája, akkor alagút
			if (previous != null) {
				Point prev = previous.getPos();

				if (trackPos.x > prev.x && trackPos.y == prev.y) {
					cartRot = 0;
				}
				if (trackPos.x < prev.x && trackPos.y == prev.y) {
					cartRot = 180;
				}
				if (trackPos.x == prev.x && trackPos.y > prev.y) {
					cartRot = 90;
				}
				if (trackPos.x == prev.x && trackPos.y < prev.y) {
					cartRot = -90;
				}
				if (currentTrackGraphics.getClass().equals(
						TurnTrackComponentGraphics.class)) {
					cartRot = currentTrackGraphics.getCartRotation(cart
							.getPrevious());
				}
			} else
				//Alagútnál marad a sín iránya
				cartRot = currentTrackGraphics.getRotation();
		}

		// forgatás (a szög (cartRot) a negatív irányba való eltérést jelzi)
		tr.rotate((Math.PI * (cartRot)) / 180, trackPos.x + 30, trackPos.y + 30);

		// a megfelelő pontra való mozgatás
		tr.translate(trackPos.getX(), trackPos.getY());
		transform = tr;
	}

}
