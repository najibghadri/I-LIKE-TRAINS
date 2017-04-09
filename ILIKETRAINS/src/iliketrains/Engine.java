package iliketrains;

import skeleton.Skeleton;

public class Engine extends Cart {

	private RailCenter center;
	private PassengerCart firstPassengerCart;
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
		Skeleton.write("Engine constructor");
	}
	
	/**
	 * A mozdony mozgatása. Elõször megkérdezi az aktuális síntõl, hogy melyik a következõ sín,
	 * majd ütközésellenõriz azon a sínen, és rálép a sínre.
	 * Ha a 7-es teszteset van, akkor ez kimarad (csak ismétlés lenne) és csak az állomás ellenõrzés történik meg
	 */
	public void move() {
		Skeleton.addIndent();
		if(Skeleton.getCurrentTest()==7){									//A hetes teszteset
			Skeleton.write("Engine.move calls Engine.checkStation()");
			checkStation();
		}		
		else{																//A többi teszteset
			Skeleton.write("Engine.move calls getNext('previous')");
			TrackComponent next=currentTrack.getNext(previous);
			previous=currentTrack;
			Skeleton.write("Engine.move calls Engine.checkCollision()");
			if(!checkCollison()){
			Skeleton.write("Engine.move calls Cart.moveCart('next')");
			moveCart(next);	
			}	
		}
		
		Skeleton.write("Engine.move returns");
		Skeleton.removeIndent();
	}

	/**
	 * Ütközésellenõrzés. Az Engine (mozdony) halad elõre, emiatt csak ez a kocsi mehet bele valamibe,
	 * ígyhát ennek az osztálynak a felelõssége az ütközések detektálása
	 * @return
	 */
	private boolean checkCollison() {
		Skeleton.addIndent();
		if(Skeleton.askIN("Foglalt a következõ sín?")){
			Skeleton.write("Ütközés, VESZTETTÉL!");
			Skeleton.write("Engine.checkCollicion calls center.reportCollided()");
			center.reportCollided();
			Skeleton.removeIndent();
			return true;
		}else{
			Skeleton.write("Engine.checkCollision() returns false");
			Skeleton.removeIndent();
			return false;
		}
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