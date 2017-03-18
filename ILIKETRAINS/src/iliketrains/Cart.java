package iliketrains;

import skeleton.Skeleton;

public class Cart {

	protected TrackComponent currentTrack;

	/**
	 * A vonatkocsit mozgató függvény, a paraméteréül kapott objektumra mozgat
	 * @param whereTo Pályaelem amire a kocsit mozgatni kívánjuk
	 */
	public void moveCart(TrackComponent whereTo) {
		Skeleton.addIndent();
		Skeleton.write("Cart.moveCart calls currentTrack.removeCart()");
		currentTrack.removeCart();
		Skeleton.write("Cart.moveCart calls whereTo.putCart()");
		whereTo.putCart(this);
		Skeleton.write("Cart.moveCart returns");
		Skeleton.removeIndent();
	}

}