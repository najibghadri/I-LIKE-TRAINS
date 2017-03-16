package iliketrains;

import skeleton.Skeleton;

public class Engine extends Cart {

	RailCenter center;
	PassengerCart firstPassengerCart;
	private TrackComponent previous;

	public Engine(RailCenter center,TrackComponent c,TrackComponent p){
		this.center=center;
		currentTrack=c;
		previous=p;
		Skeleton.write("Engine constructor");
	}
	
	public void move() {
		Skeleton.addIndent();
		Skeleton.write("Engine calls getNext('previous')");
		TrackComponent next=currentTrack.getNext(previous);
		previous=currentTrack;
		Skeleton.write("Engine.move calls Engine.checkCollision()");
		checkCollison();
		Skeleton.write("Engine.move calls Cart.moveCart('next')");
		moveCart(next);
		Skeleton.write("Engine.move returns");
		Skeleton.removeIndent();
	}

	private void checkCollison() {
		Skeleton.addIndent();
		if(Skeleton.askIN("Foglalt a következõ sín?")){
			Skeleton.write("Ütközés, vesztettél!");
			Skeleton.write("Engine.checkCollicion calls center.reportCollided()");
			center.reportCollided();
		}
		Skeleton.write("Engine.checkCollision() returns");
		Skeleton.removeIndent();
	}

	private void checkStation() {
		// TODO - implement Engine.checkStation
	}

	public void addNext(PassengerCart pCart) {
		// TODO - implement Engine.addNext
	}

}