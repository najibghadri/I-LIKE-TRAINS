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
	 * Konstructor
	 * Létrehoz egy tunnel elemet
	 */
	private Tunnel(){
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
	 * Alagút létrehzozó függvény, minimum 3 elem
	 * Tesztelé lehetősége miatt pontosan 3 elem
	 * @param in Az egyik alagútszáj
	 * @param out A másik alagútszáj
	 */	
	private void createTunnel(TunnelGate in, TunnelGate out) {
		int localId=firstID;
		int lenghtOfTunnel=3; 											//Később random lesz
		List<TrackComponent> tunnellista=new ArrayList<TrackComponent>();
		
		for(int i=0;i<lenghtOfTunnel;i++){
			TrackComponent temp= new TrackComponent(localId);
			tunnellista.add(temp);
			localId++;
		}
		
		//elsőhöz másodikat és bemenetet, utolsóhoz utolsó előtti és kimenetet külön adjuk hozzá
		tunnellista.get(0).addAdjacentTrack(in);
		tunnellista.get(0).addAdjacentTrack(tunnellista.get(1));
		tunnellista.get(tunnellista.size()-1).addAdjacentTrack(out);
		tunnellista.get(tunnellista.size()-1).addAdjacentTrack(tunnellista.get(tunnellista.size()-2));

		//Többihez az előtte és utána lévőt adja szomszédnak
		for(int i=1;i<tunnellista.size()-1;i++){
			tunnellista.get(i).addAdjacentTrack(tunnellista.get(i-1));
			tunnellista.get(i).addAdjacentTrack(tunnellista.get(i+1));
		}
		
		in.setTunnelTrack(tunnellista.get(0));
		out.setTunnelTrack(tunnellista.get(tunnellista.size()-1));
	}
	
	/**
	 * Új alagutat hoz létre, ezzel újrainicializálva
	 */
	public static void setNewInstance(){
		singleton = new Tunnel();
    }

}