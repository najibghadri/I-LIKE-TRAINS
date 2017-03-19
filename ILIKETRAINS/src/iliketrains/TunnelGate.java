package iliketrains;

import skeleton.Skeleton;

/**
 * 
 * @author Najib
 *
 */
public class TunnelGate extends TrackComponent implements Controllable {

	private Boolean state;  //Meg van nyomva vagy nem
	private Tunnel tunnel;

	/**
	 * konstruktor
	 * @param t Annak az alagútnak a referenciája, amihez az alagútszájunkat a létrehozás pillanatában kapcsolni szeretnénk
	 */
	public TunnelGate(Tunnel t){
		super();
		Skeleton.write("TunnelGate constructor");
		tunnel = t;
	}
	
	/**
	 * konstruktor
	 */
	public TunnelGate(){
		super();
		Skeleton.write("TunnelGate constructor");
	}

	/* (non-Javadoc)
	 * @see iliketrains.Controllable#change()
	 */
	public void change() {
		Skeleton.addIndent();
		if(Skeleton.askIN("Van-e rajta vonat?")){
			Skeleton.write("TunnelGate.change() changes nothing");
		}else{
			if(Skeleton.askIN("Aktív a tunnelGate?")){
				if(Skeleton.askIN("Van-e másik aktív tunnelGate?")){
					tunnel.disconnect(this);
						removeTunnel(); //Nem itt a helye, a disconnect hívja ha van másik aktív
				}else{
					tunnel.disconnect(this);
				}
			}
			else{
				if(Skeleton.askIN("Van-e másik kettõ aktív tunnelGate?")){
					Skeleton.write("TunnelGate.change() calls tunnel.register(this)");
					Skeleton.addIndent();
					tunnel.register(null);
					Skeleton.removeIndent();
					Skeleton.write("TunnelGate.change() changes nothing");
				}
				else {
					Skeleton.write("TunnelGate.change() calls tunnel.register(this)");
					Skeleton.addIndent();
					tunnel.register(this);
					Skeleton.removeIndent();
					Skeleton.write("TunnelGate.change() connected this gate");
				}
			}
		}
		Skeleton.write("TunnelGate.change() returns");
		Skeleton.removeIndent();
	}

	/**
	 * A jelenlegi alagútszáj "kikapcsolása" a hozzá tartozó alagútból
	 * (meghívja az alagút megfelelõ függvényét)
	 */
	public void removeTunnel() {
		Skeleton.addIndent();
		Skeleton.write("TunnelGate.removeTunnelTrack()");
		Skeleton.removeIndent();
	}

	/* (non-Javadoc)
	 * @see iliketrains.TrackComponent#getNext(iliketrains.TrackComponent)
	 */
	@Override
	public TrackComponent getNext(TrackComponent previous) {
		//Kérdés felvetés
		Skeleton.addIndent();
		boolean answer = Skeleton.askIN("Aktív az alagút?(I/N)");
		if(answer){
		Skeleton.write("tunnelGate returns with trackSetByTunnel");
		}
		else {
			Skeleton.write("tunnelGate returns with nextTrackComponent");
		}
		Skeleton.removeIndent();
		return this;
	}

}