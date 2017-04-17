package iliketrains;

import skeleton.Game;

/**
 * Mozdony osztály.
 */
public class Engine extends Cart {

	/**  RailCenter referenciáját tárolja. */
	private RailCenter center;
	
	/**  Első utaskocsi referenciáját tárolja. */
	private PassengerCart firstPassengerCart;
	
	/**  A korábbi sínelem referneciáját tárolja az előrehaladás miatt. */
	private TrackComponent previous;

	/**
	 * Konstruktor.
	 *
	 * @param id the id
	 * @param center A pályához tartozó RailCenter referenciája
	 * @param curr A jelenlegi pályaelem amin az Engine (mozdony) tartózkodik
	 * @param prev Az elõzõ mozgási esemény során elhagyott pályaelem
	 */
	public Engine(int id,RailCenter center,TrackComponent curr,TrackComponent prev){
		super(id);
		this.center=center;
		currentTrack=curr;
		previous=prev;
	}
	
	/**
	 * A mozdony mozgatása. Elõször megkérdezi az aktuális síntõl, hogy melyik a következõ sín,
	 * majd ütközésellenõriz azon a sínen, és rálép a sínre.
	 * Ha a 7-es teszteset van, akkor ez kimarad (csak ismétlés lenne) és csak az állomás ellenõrzés történik meg
	 */
	public void move() {
		TrackComponent newPrev=currentTrack;
		TrackComponent nextTrack=currentTrack.getNext(previous);
		if(nextTrack==null){	//ha nincs tovább, vagyis zsákutcába kerültünk
			Game.log("Engine("+this.getId()+"): collides with deadend at "+currentTrack.getType()+"("+currentTrack.getId()+")");
			center.reportCollided();
		}
		else{
			//checkCollison();
			moveCart(nextTrack);	//engine (és ezáltal az egész szerelvény) mozgatása a következő pályaelemre
			previous=newPrev;
			Game.log("Engine("+this.getId()+"): moves to "+nextTrack.getType()+"("+nextTrack.getId()+")");
			checkStation();
		}
	}

	/**
	 * Ütközésellenõrzés. Az Engine (mozdony) halad elõre, emiatt csak ez a kocsi mehet bele valamibe,
	 * ígyhát ennek az osztálynak a felelõssége az ütközések detektálása
	 */
	public void checkCollison() {	
		if(currentTrack.getCarts().size()>1){ //ha a következő pályaelemen van kocsi, ütközés lesz
			//A log fix-en a track-en lévők kocsik közül a listában lévő legelsőt írja ütközésnek
			Game.log("Engine("+this.getId()+"): collides with "
					+currentTrack.getCarts().get(0).getType() +"("+currentTrack.getCarts().get(0).getId()+") at "
					+currentTrack.getType()+"("+currentTrack.getId()+")");
			center.reportCollided();
		}
	}

	/**
	 * Állomásdetektálás. Ellenõrzi, hogy a vonat éppen állomáson halad-e át, és amennyiben igen,
	 * leszállítja az utasokat a specifikációnak megfelelõen.
	 */
	private void checkStation() {
		if(currentTrack.hasStation()==null)		//ha nem halad át állomáson
			return;
		else{														//ha igen
			Station s=currentTrack.getNext(previous).hasStation();
			
			/*leszállítás*/
			PassengerCart current= firstPassengerCart;
			while(current!=null){	//amíg van kocsi
				if(current.isNotEmpty()){	//ha nem üres a vizsgált
					if(current.getColor().equals(s.getColor())){	//ha egyezik a szín az állomáséval
						current.popPassengers();							//leszállnak (nincs előtte teli kocsi)
						Game.log("passengers got off Cart("+current.getId()+") at "+s.getColor()+"Station ("+s.getId()+")");
					}
					break;	//ha megtaláltuk az első nem üres kocsit, biztosan be kell fejeznünk a keresést (színtől függetlenül)
				}
				current=current.nextCart;	//egyébként vizsgáljuk a következőt
			}
			/*felszállítás*/
			if(s.getPassengers()==true){	//ha vannak az állomáson várakozó utasok
				current= firstPassengerCart;
				while(current!=null){	//amíg van kocsi
					if(!current.isNotEmpty())	//ha ÜRES a kocsi
						if(current.getColor().equals(s.getColor())){	//ha egyezik a szín
							current.addPassengers();							//felszállnak
							s.popPassengers();									//az állomásról pedig eltűnnek
							Game.log("passengers got on Cart("+current.getId()+") at "+s.getColor()+"Station ("+s.getId()+")");
						}
					current=current.nextCart;	//egyébként vizsgáljuk a következőt
				}
			}
		}
	}

	/**
	 * Utaskocsi hozzákapcsolása az Engine-hez (mozdony).
	 * @param pCart A kapcsolandó utaskocsi referenciája
	 */
	public void addNext(PassengerCart pCart) {
			firstPassengerCart=pCart;
	}

	/**
	 * Ellenőrzi, hogy a mozdonyhoz kapcsolódó vonatkocsik üresek-e
	 * Ha igen, akkor igazzal tér vissza, egyébként hamissal
	 * @return bool érték a fentiek szerint
	 */
	public boolean checkEmpty() {
		PassengerCart current=firstPassengerCart;
		while(current!=null){	//utaskocsik végigiterálása
			if(current.isNotEmpty())	//ha találtunk egy teli utaskocsit
				return false;
			else
				current=current.nextCart;
		}
		return true;
	}
	
	/**
     * Saját típusát sztringben
     * @return "Engine"
     */
    @Override
    public String getType(){
        return "Engine";
    }
    
    /**
     * Visszaadja az előző sín referenciáját
     *
     * @return Előző sín referenciája
     */
    public TrackComponent getPrevious(){
    	return previous;
    }
}