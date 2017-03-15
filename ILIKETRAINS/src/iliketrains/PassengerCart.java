package iliketrains;

public class PassengerCart extends Cart {

	PassengerCart nextCart;
	private Color color;
	private boolean passengers;

	public Color getColor() {
		return this.color;
	}


	public Cart popPassengers(Color color) {
		// TODO - implement PassengerCart.popPassengers
		return this;
	}


	public void addNext(PassengerCart pCart) {
		// TODO - implement PassengerCart.addNext
	}

}