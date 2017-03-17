package iliketrains;

import java.util.*;

import skeleton.Skeleton;

public class TrackComponent {

	Cart cart;
	Station station;
	protected List<TrackComponent> adjacentTracks;

	public TrackComponent() {
		adjacentTracks = new ArrayList<TrackComponent>();
		Skeleton.write("TrackComponent constructor");
	}

	public void addAdjacentTrack(TrackComponent track) {
		// TODO - implement TrackComponent.addAdjacentTrack
	}

	public TrackComponent getNext(TrackComponent previous) {
		return this;
	}

	public void putCart(Cart cart) {
		Skeleton.addIndent();
		Skeleton.write("TrackComponent.putCart returns");
		Skeleton.removeIndent();
	}

	public void removeCart() {
		Skeleton.addIndent();
		Skeleton.write("TrackComponent.removeCart returns");
		Skeleton.removeIndent();
	}

	public Cart getCart() {
		return this.cart;
	}

	public Station hasStation() {
		Skeleton.addIndent();
		boolean stationExist = Skeleton.askIN("Van állomás?");
		if (stationExist){
			Skeleton.write("TrackComponent.hasStation returns with station");
			Skeleton.removeIndent();
			return station;
		}
		else{
			Skeleton.write("TrackComponent.hasStation returns with null");	
			Skeleton.removeIndent();
			return null;
		}

	}

	public void setStation(Station station) {
		this.station = station;
	}

}