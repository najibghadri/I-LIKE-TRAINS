package iliketrains;

import skeleton.Skeleton;

public class Switch extends TrackComponent implements Controllable {

	private Boolean state;

	/**
	 * konstruktor
	 */
	public Switch() {
		super();
		Skeleton.write("Switch konstruktor");
	}

	/**
	 * A váltó állapotát megfordítja, amennyiben nincsen rajta vonat
	 */
	public void change() {
		Skeleton.addIndent();
		if(Skeleton.askIN("Van-e rajta vonat?")){
			Skeleton.write("Switch.change returns with no changes");
		}else{
			if(Skeleton.askIN("Melyik irányba áll a váltó? (I-TrackObj2, N-TrackObj3)")){
				Skeleton.write("Switch.change returns with changes state to TrackObj2");
			}else{
				Skeleton.write("Switch.change returns with changes state to TrackObj3");
			}
		}
		Skeleton.removeIndent();
	}

	/**
	 * A következõ pályaelem lekérdezésére szolgáló függvény (vagyis amerre tovább fogunk haladni), 
	 * amennyiben a paraméterként megadott pályaelem irányából közelítünk a váltó felé
	 * @param previous Annak a pályaelemnek a referenciája ahonnan közelítünk a váltó felé
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