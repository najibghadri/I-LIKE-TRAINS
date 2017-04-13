package classes;

import java.util.*;

public class TrackComponent {

	Cart cart;
	Station station;
	protected Collection<TrackComponent> adjacentTracks;
	private int id;
	private list carts;

	/**
	 * 
	 * @param track
	 */
	public void addAdjacentTrack(TrackComponent track) {
		// TODO - implement TrackComponent.addAdjacentTrack
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param previous
	 */
	public TrackComponent getNext(TrackComponent previous) {
		// TODO - implement TrackComponent.getNext
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param cart
	 */
	public void putCart(Cart cart) {
		// TODO - implement TrackComponent.putCart
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public void removeCart(int id) {
		// TODO - implement TrackComponent.removeCart
		throw new UnsupportedOperationException();
	}

	public Cart getCart() {
		return this.cart;
	}

	public Station hasStation() {
		// TODO - implement TrackComponent.hasStation
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param station
	 */
	public void setStation(Station station) {
		this.station = station;
	}

	public list getCarts() {
		return this.carts;
	}

}