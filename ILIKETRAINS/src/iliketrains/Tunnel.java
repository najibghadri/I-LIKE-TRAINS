package iliketrains;

import java.util.*;

import skeleton.Skeleton;

public class Tunnel {

	Collection<TunnelGate> activeGates;
	
	public Tunnel(){
		Skeleton.write("Tunnel constructor");
	}

	public static Tunnel getInstance() {
		Skeleton.write("Tunnel.getInstance() returns references to tunnel");
		return new Tunnel();
	}

	/**
	 * 
	 * @param gate
	 */
	public void disconnect(TunnelGate gate) {
		Skeleton.write("Tunnel.disconnect(TunnelGate t) calls activeGate.remove(gate)");
		//TODO: implement closing tunnel
	}

	/**
	 * 
	 * @param gate
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
	 * 
	 * @param in
	 * @param out
	 */
	private void createTunnel(TunnelGate in, TunnelGate out) {
		Skeleton.write("Tunnel.createTunnel(TunnelGate g1, TunnelGate g2) creates tunnel");
		// TODO - implement create track from random number of TrackComponents 
	}

}