package iliketrains;

/**
 * A vonatok belépési pontját megvalósító osztály.
 */
public class EntryPoint extends TrackComponent{

	/**
	 * Konstruktor
	 * A pályaelem azonosítóját inicializálja
	 * @param id Pályaelem azonosítója
	 */
	public EntryPoint(int id){
		super(id);
	}
	
	/**
	 * Amennyiben a 0-s szomszéd felől közeledünk, akkor az 1-eset adjuk vissza,
	 * különben a 0-ásat. Így nem lehet a 2-es felé menni, ami az entryPoint
	 * @param previous Előző sín
	 * @return TrackComponent következő sín
	 */
	@Override
	public TrackComponent getNext(TrackComponent previous){
		if(previous.equals(adjacentTracks.get(0))){
			return adjacentTracks.get(1);
		}
		else
			return adjacentTracks.get(0);
	}
}
