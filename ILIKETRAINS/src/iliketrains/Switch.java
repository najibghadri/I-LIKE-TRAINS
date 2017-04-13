package iliketrains;

import skeleton.Skeleton;

/**
 * Switch
 * Váltó osztálya
 * @author Najib
 */
public class Switch extends TrackComponent implements Controllable {

	private boolean direction;

	/**
	 * Constructor
	 * Azonosítóval létrehozza a switch-et
	 */
	public Switch(int id) {
		super(id);
		this.direction=false;
	}

	/**
	 * A váltó állapotát megfordítja, amennyiben nincsen rajta vonat
	 */
	public void change() {
		if(carts.size()!=0){
		    return;
		}else{
			if(direction){
				direction = false; //Y szerint balra
			}else{
                direction = true; //Y szerint jobbra
			}
		}
	}

	/**
	 * A haladási irány szerinti következő pálya elemet adja vissza.
     * Figyelembe veszi a váltó állását.
	 * @param previous Annak a pályaelemnek a referenciája ahonnan közelítünk a váltó felé
	 */
	@Override
	public TrackComponent getNext(TrackComponent previous) {
        //TODO mindig lesz 3 csatlakoztatva? Ha nem akkor a listából nem lehet többet indexelni mint ami van!
        //TODO Ezért lehet hogy nem lista kell hanem annyi TC elem amennyi kell (itt is, T.G.-nál és T.C.-nál is)
	    if(adjacentTracks.size()<3){
	        return null;
        }

		if (previous.equals(adjacentTracks.get(0))) { // Y szerint lentről jön
			if(direction) { //jobbra áll
			    return adjacentTracks.get(2);
            }
            else {
			    return adjacentTracks.get(1);
            }
		} else {
			return adjacentTracks.get(0);
		}
	}

    /**
     * Leírást ad magáról
     * @return
     */
    @Override
    public String getInfo() {
        String ret = "set to ";
        if(direction)
            ret +=  "right: " + adjacentTracks.get(2).getType() + " " + adjacentTracks.get(2).getId();
        else
            ret += "left: " + adjacentTracks.get(1).getType() + " " + adjacentTracks.get(1).getId();
        return  ret + ", ";
    }

    /**
     * Saját típusát sztringben
     * @return "Switch"
     */
    @Override
    public String getType(){
        return "Switch";
    }

}