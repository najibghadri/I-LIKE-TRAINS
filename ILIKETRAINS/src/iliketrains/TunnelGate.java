package iliketrains;

import skeleton.Skeleton;

/**
 * TunnelGate
 * Alagútszájat megvalósító típus
 * @author Najib
 */
public class TunnelGate extends TrackComponent implements Controllable {

	private boolean active;  //Meg van nyomva vagy nem
    private TrackComponent tunnelTrack;

	/**
	 * Constructor
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
            return;
		}else{
			if(active){
				tunnel.disconnect(this);
				active = false;
			}
			else{
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

}