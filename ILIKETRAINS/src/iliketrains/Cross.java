package iliketrains;

/**
 * A kereszteződést megvalósító osztály
 */
public class Cross extends TrackComponent{

	/**
	 * Konstruktor
	 * @param id A pályaelem id-ja, amit az ősosztályban kezelünk.
	 */
	public Cross(int id){
		super(id);
	}
	
	
	/**
	 * A haladási irány szerinti következő pálya elemet adja vissza.
     * Figyelembe veszi, hogy a kereszteződésből honnan érkeznek.
	 * @param previous Annak a pályaelemnek a referenciája ahonnan közelítünk a kereszteződs felé
	 */
	@Override
	public TrackComponent getNext(TrackComponent previous){
		if(previous.equals(adjacentTracks.get(0)))
			return adjacentTracks.get(1);
		else if(previous.equals(adjacentTracks.get(1)))
			return adjacentTracks.get(0);
		else if(previous.equals(adjacentTracks.get(2)))
			return adjacentTracks.get(3);
		else
			return adjacentTracks.get(2);
	}
}
