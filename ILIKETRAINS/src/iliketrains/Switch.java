package iliketrains;

import skeleton.Skeleton;

public class Switch extends TrackComponent implements Controllable {

	private Boolean state;

	public Switch() {
		super();
		Skeleton.write("Switch konstruktor");
	}

	public void change() {
		// TODO - implement Switch.change
	}

	/**
	 * 
	 * @param previous
	 */
	@Override
	public TrackComponent getNext(TrackComponent previous) {
		// Kérdés felvetés
		Skeleton.addIndent();
		boolean answer = Skeleton.askIN("Váltási irányból?(I/N)");
		if (answer) {
			boolean switchChoice = Skeleton.askIN("Balra áll a váltó?(I/N)");
			if(switchChoice)
			Skeleton.write("switch returns with leftTrackComponent");
			else
				Skeleton.write("switch returns with rightTrackComponent");
		} else {
			Skeleton.write("switch returns with nextTrackComponent");
		}
		Skeleton.removeIndent();
		return this;
	}

}