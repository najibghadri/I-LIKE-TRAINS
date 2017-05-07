package iliketrains;

import java.util.*;

import skeleton.Game;

/**
 * Tunnel
 * Alagútkezelő típus
 */
public class Tunnel {
	
	/** Az aktív alagútbejárator listája */
	private List<TunnelGate> activeGates;
	
	/** Az alagút létehozáshoz kellő id*/
	private static int firstID;
	
	/** A singleton referencia */
	private static Tunnel singleton;
	
	/**
	 * A jelenlegi alagút hossza
	 */
	private int lengthOfTunnel=0;
	
	/**
	 * Konstructor
	 * Létrehoz egy tunnel elemet
	 */
	private Tunnel(){
		lengthOfTunnel=0;
		activeGates = new ArrayList<TunnelGate>();
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
		if(activeGates.size()==2) { //cannot be more
			activeGates.get(0).removeTunnelTrack();
			activeGates.get(1).removeTunnelTrack();
			Game.log("Tunnel: destroyed Tunnel");
			lengthOfTunnel=0;
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
	 * Alagút létrehzozó függvény, minimum 3 elem
	 * Tesztelé lehetősége miatt pontosan 3 elem
	 * @param in Az egyik alagútszáj
	 * @param out A másik alagútszáj
	 */	
	private void createTunnel(TunnelGate in, TunnelGate out) {
		int localId=firstID;
		Random rand = new Random();
		//random alagút, 3-5 között
		lengthOfTunnel=rand.nextInt(2) + 3;											
		List<TrackComponent> tunnelTracks=new ArrayList<TrackComponent>();
		
		for(int i=0;i<lengthOfTunnel;i++){
			TrackComponent temp= new TrackComponent(localId);
			tunnelTracks.add(temp);
			localId++;
		}
		
		//elsőhöz másodikat és bemenetet, utolsóhoz utolsó előtti és kimenetet külön adjuk hozzá
		tunnelTracks.get(0).addAdjacentTrack(in);
		tunnelTracks.get(0).addAdjacentTrack(tunnelTracks.get(1));
		tunnelTracks.get(tunnelTracks.size()-1).addAdjacentTrack(out);
		tunnelTracks.get(tunnelTracks.size()-1).addAdjacentTrack(tunnelTracks.get(tunnelTracks.size()-2));

		//Többihez az előtte és utána lévőt adja szomszédnak
		for(int i=1;i<tunnelTracks.size()-1;i++){
			tunnelTracks.get(i).addAdjacentTrack(tunnelTracks.get(i-1));
			tunnelTracks.get(i).addAdjacentTrack(tunnelTracks.get(i+1));
		}
		
		in.setTunnelTrack(tunnelTracks.get(0));
		out.setTunnelTrack(tunnelTracks.get(tunnelTracks.size()-1));
	}
	
	/**
	 * Új alagutat hoz létre, ezzel újrainicializálva
	 */
	public static void setNewInstance(){
		singleton = new Tunnel();
    }

	/**
	 * Visszaadja az alagút hosszát
	 *
	 * @return Alagút hossza
	 */
	public int getLength() {
		return lengthOfTunnel;
	}

}