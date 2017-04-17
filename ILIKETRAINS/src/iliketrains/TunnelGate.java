package iliketrains;

import skeleton.Game;

/**
 * TunnelGate
 * Alagútszájat megvalósító típus
 */
public class TunnelGate extends TrackComponent implements Controllable {

	/** Tárolja, hogy aktív-e vagy nem az alagútbejárat */
	private boolean active;
    
    /** Az alagútba vezető sínelem referenciája */
    private TrackComponent tunnelTrack;

	/**
	 * Konstruktor
	 * Létrehoz egy alagútszájat azonosítóval
	 * @param id
	 */
	public TunnelGate(int id){
		super(id);
		active = false;
	}

	/**
     * Az alagútszáj státuszát állítja
     * Az alagútszájak állása alapján jön létre és szűnik meg az alagút
	 * @see iliketrains.Controllable#change()
	 */
	public void change() {
		Tunnel tunnel=Tunnel.getInstance();
		if(carts.size()!=0){
            Game.log(this.getType()+"("+this.getId()+") remains "+this.getActive());
            return; //TODO: ez minek ide?
		}else{
			if(active){
				Game.log(this.getType()+"("+this.getId()+"): disconnected");
				tunnel.disconnect(this);
				active = false;
			}
			else{
				//a tunnel.register függvényen belül írjuk ki az erre a bejáratra vonatkozó logokat
				active = tunnel.register(this); //Ha van másik kettő akkor nem fog sikerülni!
			}
		}
	}

    /**
     * Az alagútba mutató utat állítja be. Felcsatlakozik az alagútra.
     * @param track Alagútra mutató sín elem referenciája
     */
    public void setTunnelTrack(TrackComponent track) {
        tunnelTrack = track;
        addAdjacentTrack(track);
    }

    /**
     * Lecsatlakoztatja magát az alagútról
     */
    public void removeTunnelTrack() {
        removeAdjacentTrack(tunnelTrack);
        tunnelTrack = null;
    }

    /**
     * Leírást ad magáról
     * @return
     */
    @Override
    public String getInfo() {
        String ret = "";
        ret +=  active ? "active, set to" + tunnelTrack.getType() + " " + tunnelTrack.getId() : "inactive" ;
        return  ret + ", ";
    }

    /**
     * Saját típusát sztringben
     * @return "TunnelGate"
     */
    @Override
    public String getType(){
        return "TunnelGate";
    }
    
    /**
     * Visszaadja szövegesen, hogy aktív-e a kapu
     * A logoláshoz haszons függvény
     * @return String az aktivitásról
     */
    private String getActive(){
    	if (active)
    		return "active";
    	else
    		return "inactive";
    }

}