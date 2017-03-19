package iliketrains;

import java.util.*;

import skeleton.Skeleton;

public class Tunnel {

	Collection<TunnelGate> activeGates;
	
	/**
	 * konstruktor
	 */
	public Tunnel(){
		Skeleton.write("Tunnel constructor");
	}

	/**
	 * Az alagút referenciájának lekérdezése
	 * @return Tunnel Az alagút referenciája
	 */
	public static Tunnel getInstance() {
		Skeleton.write("Tunnel.getInstance() returns references to tunnel");
		return new Tunnel();
	}

	/**
	 * A paraméterként kapott alagútszájat "kikapcsoló" függvény
	 * @param gate A kikapcsolandó alagútszáj
	 */
	public void disconnect(TunnelGate gate) {
		Skeleton.write("Tunnel.disconnect(TunnelGate t) calls activeGate.remove(gate)");
		//TODO: implement closing tunnel
	}

	/**
	 * A paraméterként kapott alagútszájat "bekapcsoló" függvény
	 * @param gate A bekapcsolandó alagútszáj
	 */
	public boolean register(TunnelGate gate) {
		if(gate == null){
			Skeleton.write("Tunnel.register(TunnelGate g) returns false");
			return false;
		}
		else{
			Skeleton.write("Tunnel.register(TunnelGate g) calls activeGates.add(g)");
			if(Skeleton.askIN("Van-e másik aktív tunnelGate?")){
				Skeleton.write("Tunnel.register(TunnelGate g) calls createTunnel(TunnelGate in, TunnelGate out)");
				Skeleton.addIndent();
				createTunnel(null,null);
				Skeleton.removeIndent();
			}
			Skeleton.write("Tunnel.register(TunnelGate g) returns true");
			return true;
		}
	}

	/**
	 * Alagút létesítõ függvény
	 * @param in Az egyik alagútszáj
	 * @param out A másik alagútszáj
	 */
	private void createTunnel(TunnelGate in, TunnelGate out) {
		Skeleton.write("Tunnel.createTunnel(TunnelGate g1, TunnelGate g2) creates tunnel");
		// TODO - implement create track from random number of TrackComponents 
	}

}