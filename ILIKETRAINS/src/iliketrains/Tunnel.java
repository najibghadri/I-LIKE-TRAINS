	package iliketrains;

import java.util.*;

import skeleton.Game;

/**
 * Tunnel
 * Alagútkezelő típus
 * @author Najib
 */
public class Tunnel {
	
	/** Az aktív alagútbejárator listája */
	private List<TunnelGate> activeGates;
	
	/** Az alagútat alkotó sínelemek listája */
	private List<TrackComponent> tunnelTracks; //ha nem csak 1 hosszú lesz akkor szét kell választani őket
	private static int firstID;
	private static Tunnel singleton;

	
	/**
	 * Constructor
	 * Létrehoz egy tunnel elemet
	 */
	private Tunnel(){
		activeGates = new ArrayList<TunnelGate>();
		tunnelTracks = new ArrayList<TrackComponent>();
	}

	/**
	 * Az alagút referenciájának lekérdezése
	 * Egyetlen Tunnel elem létrehozását engedélyezi, aminek a legmagasabb sín ID a kezdő száma
	 * @return Tunnel Az alagút referenciája
	 */
	public static Tunnel getInstance() {
		if(singleton == null)
			singleton = new Tunnel();
		return singleton;
	}

	/**
 	 * firstID-t állítja be, ami a tunnel által generált trackComponent-eket azonosítja	
	 * @param firstID
	 */
	public static void setFirstID( int firstID ) {
		Tunnel.firstID = firstID;
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
			Game.log("Tunnel: destroyed Tunnel");
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
			Game.log(gate.getType()+"("+gate.getId()+"): registered");
			return true;
		}
		else if(activeGates.size() == 1){
			activeGates.add(gate);
			Game.log(gate.getType()+"("+gate.getId()+"): registered");
			
			createTunnel(activeGates.get(0),activeGates.get(1));
			Game.log("Tunnel: created Tunnel");
			return true;
		} else{
			Game.log(gate.getType()+"("+gate.getId()+"): not registered");
			return false;
		}
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