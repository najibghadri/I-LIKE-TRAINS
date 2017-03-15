package iliketrains;

public class Switch extends TrackComponent implements Controllable {

	private Boolean state;

	public void change() {
		// TODO - implement Switch.change
	}

	/**
	 * 
	 * @param previous
	 */
	public TrackComponent getNext(TrackComponent previous) {
		// TODO - implement Switch.getNext
		return this;
	}

}