package iliketrains;

import skeleton.Skeleton;

public class TunnelGate extends TrackComponent implements Controllable {

	private Boolean state;
	private Tunnel tunnel;

	public TunnelGate(Tunnel t){
		super();
		Skeleton.write("TunnelGate constructor");
		tunnel = t;
	}
	
	public TunnelGate(){
		super();
		Skeleton.write("TunnelGate constructor");
	}

	public void change() {
		Skeleton.addIndent();
		if(Skeleton.askIN("Van-e rajta vonat?")){
			Skeleton.write("TunnelGate.change() changes nothing");
		}else{
			if(Skeleton.askIN("Aktív a tunnelGate?")){
				if(Skeleton.askIN("Van-e másik aktív tunnelGate?")){
					removeTunnelTrack();
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
				else{
					Skeleton.write("TunnelGate.change() calls tunnel.register(this)");
					Skeleton.addIndent();
					tunnel.register(this);
					Skeleton.removeIndent();
				}
			}
		}
		Skeleton.write("TunnelGate.change() returns");
		Skeleton.removeIndent();
	}

	public void removeTunnelTrack() {
		Skeleton.write("TunnelGate.removeTunnelTrack() calls tunnel.disconnect(this)");
		Skeleton.addIndent();
		tunnel.disconnect(this);
		Skeleton.removeIndent();
		Skeleton.write("TunnelGate.removeTunnelTrack() sets trackSetByTunnel to null");
	}

	@Override
	public TrackComponent getNext(TrackComponent previous) {
		//Kérdés felvetés
		Skeleton.addIndent();
		boolean answer=Skeleton.askIN("Aktív az alagút?(I/N)");
		if(answer){
		Skeleton.write("tunnelGate returns with trackSetByTunnel");
		}
		else{
			Skeleton.write("tunnelGate returns with nextTrackComponent");
		}
		Skeleton.removeIndent();
		return this;
	}

}