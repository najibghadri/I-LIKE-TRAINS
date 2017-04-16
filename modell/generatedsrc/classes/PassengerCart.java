package classes;

public class PassengerCart extends Cart {

	private PassengerCart nextPassengerCart;
	private Color color;
	private bool passengers;

	/**
	 * 
	 * @param pCart
	 */
	public void addNextPassengerCart(PassengerCart pCart) {
		// TODO - implement PassengerCart.addNextPassengerCart
		throw new UnsupportedOperationException();
	}

	public bool getPassengers() {
		return this.passengers;
	}

	/**
	 * 
	 * @param station
	 */
	public void getOnCart(Station station) {
		// TODO - implement PassengerCart.getOnCart
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param station
	 */
	public void getOffCart(Station station) {
		// TODO - implement PassengerCart.getOffCart
		throw new UnsupportedOperationException();
	}

}