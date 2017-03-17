package iliketrains;

import skeleton.Skeleton;

public class PassengerCart extends Cart {

	PassengerCart nextCart;
	private Color color;
	private boolean passengers;

	public PassengerCart(){
		super();
		Skeleton.write("PassengerCart konstruktor");
	}
	
	public Color getColor() {
		return this.color;
	}


	public Cart popPassengers(Color color) {
		Skeleton.addIndent();
		boolean empty=Skeleton.askIN("Üres már a kocsi?");
		if(empty){
			boolean allEmpty=Skeleton.askIN("Ez az utolsó kocsi?");
			if(allEmpty){
				
			}else{
				Skeleton.write("Cart.popPassenger calls nextCart.popPassengers(color)");
				this.popPassengers(color);
			}
		}
		else{
			Skeleton.askIN("Egyezik a szín?");
		}
		Skeleton.removeIndent();
		return this;
	}


	public void addNext(PassengerCart pCart) {
	}

}