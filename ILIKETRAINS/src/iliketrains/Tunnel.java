package iliketrains;

import java.util.*;

public class Tunnel {

	Collection<TunnelGate> activeGates;

	public static Tunnel getInstance() {
		// TODO - implement Tunnel.getInstance
		return new Tunnel();
	}

	/**
	 * 
	 * @param gate
	 */
	public void disconnect(TunnelGate gate) {
		// TODO - implement Tunnel.disconnect
	}

	/**
	 * 
	 * @param gate
	 */
	public boolean register(TunnelGate gate) {
		// TODO - implement Tunnel.register
		return false;
	}

	/**
	 * 
	 * @param in
	 * @param out
	 */
	private void createTunnel(TunnelGate in, TunnelGate out) {
		// TODO - implement Tunnel.createTunnel
	}

}