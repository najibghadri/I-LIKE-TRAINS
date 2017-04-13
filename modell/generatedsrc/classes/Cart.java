package classes;

public abstract class Cart {

	private Cart nextCart;
	protected TrackComponent currentTrack;
	private int id;

	/**
	 * 
	 * @param whereTo
	 */
	public void moveCart(TrackComponent whereTo) {
		// TODO - implement Cart.moveCart
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param cart
	 */
	public void setNextCart(Cart cart) {
		this.nextCart = cart;
	}

}