package iliketrains;

import skeleton.Skeleton;

public class Engine extends Cart {

	RailCenter center;
	PassengerCart firstPassengerCart;
	private TrackComponent previous;

	public Engine(TrackComponent c,TrackComponent p){
		currentTrack=c;
		previous=p;
		Skeleton.write("Engine constructor");
	}
	
	public void move() {
		Skeleton.addIndent();
		Skeleton.write("Engine calls getNext('previous')");
		currentTrack.getNext(previous);
		Skeleton.removeIndent();
	}

	private void checkCollison() {
		// TODO - implement Engine.checkCollison
	}

	private void checkStation() {
		// TODO - implement Engine.checkStation
	}

	public void addNext(PassengerCart pCart) {
		// TODO - implement Engine.addNext
	}

}