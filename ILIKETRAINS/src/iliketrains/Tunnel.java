package iliketrains;

import java.util.*;

import skeleton.Skeleton;

/**
 * 
 * @author Najib
 *
 */
public class Tunnel {

	Collection<TunnelGate> activeGates;
	
	/**
	 * konstruktor
	 */
	private Tunnel(){
		Skeleton.write("Tunnel constructor");
	}

	/**
	 * Az alagút referenciájának lekérdezése
	 * @return Tunnel Az alagút referenciája
	 */
	public static Tunnel getInstance() {
		Skeleton.write("Tunnel.getInstance() returns reference to singleton tunnel object");
		return new Tunnel();
		//TODO: Ez így még nem singleton: El kell menteni hogy készítettünk-e korábban, ha igen akkor azt kell visszaadni, 
		//ha nem csak akkor new
	}

	/**
	 * A paraméterként kapott alagútszájat "kikapcsoló" függvény
	 * @param gate A kikapcsolandó alagútszáj
	 */
	public void disconnect(TunnelGate gate) {
		Skeleton.write("Tunnel.disconnect(TunnelGate t) calls activeGate.removeTunnel()");
		//TODO: implement closing tunnel
		//TODO: Ha van másik aktív akkor azt disconnektálni kell
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