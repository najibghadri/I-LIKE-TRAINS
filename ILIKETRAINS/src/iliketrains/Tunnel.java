package iliketrains;

import java.util.*;

import skeleton.Skeleton;

/**
 * Tunnel
 * Alagútkezelő típus
 * @author Najib
 */
public class Tunnel {

	private List<TunnelGate> activeGates;
	private List<TrackComponent> tunnelTracks; //ha nem csak 1 hosszú lesz akkor szét kell választani őket
	private static int firstID;
	private static Tunnel singleton;

	/**
	 * Constructor
	 * Létrehoz egy tunnel elemet
	 */
	private Tunnel(int firstID){
		this.firstID = firstID;
		activeGates = new ArrayList<TunnelGate>();
		tunnelTracks = new ArrayList<TrackComponent>();
		Skeleton.write("Tunnel constructor");
	}

	/**
	 * Az alagút referenciájának lekérdezése
	 * Egyetlen Tunnel elem létrehozását engedélyezi, aminek firstID a kezdő száma
	 * firstID a további alagút elemek id-ja innen kezdődik
	 * @return Tunnel Az alagút referenciája
	 */
	public static Tunnel getInstance() {
		if(singleton == null)
			singleton = new Tunnel(firstID);
		return singleton;
	}

	/**
	 * A paraméterként kapott alagútszájat leregisztrálja és leválasztja az alagútról
	 * Ha csak ő van regisztrálva akkor csak leregisztrál
	 * @param gate A leválasztandó alagútszáj
	 */
	public void disconnect(TunnelGate gate) {
		if(activeGates.size()==2) { //can not be more
			activeGates.get(0).removeTunnelTrack();
			activeGates.get(1).removeTunnelTrack();
			//TODO ha nem csak egy hosszú az alagút, akkor itt szedjük szét
		}
		activeGates.remove(gate);
	}

	/**
	 * A paraméterként kapott alagútszájat csatlakozásra regisztrálja
	 * @param gate alagútszáj
	 * @return Sikerült e a regisztrálás
	 */
	public boolean register(TunnelGate gate) {
		if(activeGates.size()==	0){
			activeGates.add(gate);
			return true;
		}
		else if(activeGates.size() == 1){
			activeGates.add(gate);
			createTunnel(activeGates.get(0),activeGates.get(1));
			return true;
		} else
			return false;
	}

	/**
	 * Alagút létrehzozó függvény
	 * @param in Az egyik alagútszáj
	 * @param out A másik alagútszáj
	 */
	private void createTunnel(TunnelGate in, TunnelGate out) {
		//TODO Csak egy hosszú lesz?

		TrackComponent trackObj2= new TrackComponent(firstID);
		tunnelTracks.add(trackObj2);

		trackObj2.addAdjacentTrack(in);
		trackObj2.addAdjacentTrack(out);

		in.setTunnelTrack(trackObj2);
		out.setTunnelTrack(trackObj2);
	}

}