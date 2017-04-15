package iliketrains;

/**
 * Mozdony osztály
 */
public class Engine extends Cart {

	/** RailCenter referenciáját tárolja */
	private RailCenter center;
	
	/** Első utaskocsi referenciáját tárolja */
	private PassengerCart firstPassengerCart;
	
	/** A korábbi sínelem referneciáját tárolja az előrehaladás miatt */
	private TrackComponent previous;

	/**
	 * konstruktor
	 * @param center A pályához tartozó RailCenter referenciája
	 * @param curr A jelenlegi pályaelem amin az Engine (mozdony) tartózkodik
	 * @param prev Az elõzõ mozgási esemény során elhagyott pályaelem
	 */
	public Engine(int id,RailCenter center,TrackComponent curr,TrackComponent prev){
		super(id);
		this.center=center;
		currentTrack=curr;
		previous=prev;
		//Skeleton.write("Engine constructor");
	}
	
	/**
	 * A mozdony mozgatása. Elõször megkérdezi az aktuális síntõl, hogy melyik a következõ sín,
	 * majd ütközésellenõriz azon a sínen, és rálép a sínre.
	 * Ha a 7-es teszteset van, akkor ez kimarad (csak ismétlés lenne) és csak az állomás ellenõrzés történik meg
	 */
	public void move() {
	}

	/**
	 * Ütközésellenõrzés. Az Engine (mozdony) halad elõre, emiatt csak ez a kocsi mehet bele valamibe,
	 * ígyhát ennek az osztálynak a felelõssége az ütközések detektálása
	 * @return
	 */
	private boolean checkCollison() {
		//TODO Kell ellenőrizni hogy null-e a kövi sín (zsákutca bárhol lehet).
		return true;
	}

	/**
	 * Állomásdetektálás. Ellenõrzi, hogy a vonat éppen állomáson halad-e át, és amennyiben igen,
	 * leszállítja az utasokat a specifikációnak megfelelõen. Ha minden kocsi üres, azt jelzi a RailCenter felé
	 */
	private void checkStation() {

	}

	/**
	 * Utaskocsi hozzákapcsolása az Engine-hez (mozdony).
	 * @param pCart A kapcsolandó utaskocsi referenciája
	 */
	public void addNext(PassengerCart pCart) {
		firstPassengerCart=pCart;
	}

}