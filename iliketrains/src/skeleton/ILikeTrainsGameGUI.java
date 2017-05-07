package skeleton;

import graphics.*;
import iliketrains.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *  Játékot kirajzolás logikát megvalósító osztály.
 */
public class ILikeTrainsGameGUI {

	/** JFrame referencia */
	private Application app;
	
	/** Controller referencia*/
	private Controller controller;

	/** Pályaelemek tárolója */
	private static Map<Integer, TrackDrawable> trackMap = new HashMap<>();
	
	/** Vonatok tárolója */
	private Map<Integer, Drawable> trainMap = new HashMap<>();
	
	/** Állomások tárolója */
	private Map<Integer, Drawable> stationMap = new HashMap<>();
	
	/** Állítható elemek tárolója */
	private Map<Integer,Drawable> controllableGraphics=new HashMap<>();

	/** Megjelenítendő panel */
	private GUIPanel panel;

	/**
	 * Visszaadja a panel referenciáját
	 *
	 * @return panel referenciája
	 */
	public JPanel panel() {
		return panel;
	}

	/**
	 * JPanel osztály
	 */
	@SuppressWarnings("serial")
	private class GUIPanel extends JPanel {
		
		/** alagút hossza */
		private JLabel tunnelLengthLabel;
		
		/**
		 * Konstruktor
		 */
		public GUIPanel(){
			setLayout(null);
			JButton btnStop = new JButton("Stop");
			btnStop.setBounds(10, 10, 100, 20);
			add(btnStop);
			btnStop.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					app.stop();
				}
			});

			//Alagút hosszát írja ki egy JLabelre, 25-ös méret, fehér szín
			tunnelLengthLabel = new JLabel();
			tunnelLengthLabel.setFont(new Font("Serif", Font.PLAIN, 25));
			tunnelLengthLabel.setForeground(Color.WHITE);
			tunnelLengthLabel.setBounds(400, 10,200, 30);
			add(tunnelLengthLabel);
			tunnelLengthLabel.setText("Alagút hossza: "+String.valueOf(Tunnel.getInstance().getLength()));

			setOpaque(true);
			setBackground(new Color(0, 120, 40));

			addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int mx=arg0.getX();
					int my=arg0.getY();
					for (Map.Entry<Integer, Drawable> control : controllableGraphics.entrySet())
					{
						Point p=control.getValue().getPos();
						if(mx>p.x && mx<p.x+60){
							if(my>p.y && my< p.y+60){
								controller.change(control.getKey());
								repaint();
							}
						}
					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
				}

				@Override
				public void mouseExited(MouseEvent e) {
				}

				@Override
				public void mousePressed(MouseEvent e) {
				}

				@Override
				public void mouseReleased(MouseEvent e) {
				}
			});
		}

		/**
		 * A kirajzolás metódusa. Előszot a szöveget rajzolja, majd a síneket, erre a vonatokat,
		 * utoljára az állomásokat.
		 *
		 * @param g Amire rajzolni fogunk
		 */
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			tunnelLengthLabel.setText("Alagút hossza: "+String.valueOf(Tunnel.getInstance().getLength()));
			for (Map.Entry<Integer, TrackDrawable> entry : trackMap.entrySet())
			{
				entry.getValue().draw(g);
			}
			for (Map.Entry<Integer, Drawable> entry : trainMap.entrySet())
			{
				entry.getValue().draw(g);
			}
			for (Map.Entry<Integer, Drawable> entry : stationMap.entrySet())
			{
				entry.getValue().draw(g);
			}
		}

	}

	/**
	 * Konstruktor létrehozza a Panelt benne Stop gombot, az alagút hosszát jelző szöveget,
	 * illetve a kattintást kezelő függvény itt van implementálva.
	 *
	 * @param application the application
	 * @param controller the controller
	 */
	public ILikeTrainsGameGUI(Application application, final Controller controller) {
		app = application;
		this.controller = controller;
		panel = new GUIPanel();
	}

	/**
	 * Pálya grafikájának betöltése A controllertől lekéri a szükséges
	 * referenciákat a logikára
	 * Külön map-be tölti be a síneket, a kattintható objektumokat, vonatokat és állomásokat
	 * A logikára mutató referenciákat beállítja a grafikai elemeknek, ahol kell.
	 */
	public void loadGraphicsMap() {
		trackMap.clear();
		trainMap.clear();
		stationMap.clear();

		int id = controller.getNumberOfMap();
		String file = System.getProperty("user.dir")
				+ "\\res\\graphic_maps\\map" + id+".txt";
		BufferedReader br = null;

		try {
			//Az összes TrackDrawable-nek beállítja a TrackComponent referenciát.
			Map<Integer,TrackComponent> trackComponents = controller.getTrackComponents();

			br = new BufferedReader(new FileReader(file));
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				String[] line = currentLine.split(",");
				
				int componentId = Integer.parseInt(line[0]);	//sín id
				
				String[] pos = line[1].split(":");				//sín pozíciója a pályán
				int x = Integer.parseInt(pos[0]);
				int y = Integer.parseInt(pos[1]);
				int rotation = Integer.parseInt(line[3]);		//sín forgatási értéke fokban
				
				//Eldönti hogy ebben a sorban milyen típusú sínt kell példányosítani
				//Ezt behelyezi a trackMap-ba
				switch (line[2]) {
				case "curve":
					TurnTrackComponentGraphics ttcg = new TurnTrackComponentGraphics(
							x, y, rotation);
					trackMap.put(componentId, ttcg);
					ttcg.setTrackReference(trackComponents.get(componentId));
					break;
				case "straight":
					StraightTrackComponentGraphics stcg=new StraightTrackComponentGraphics(x,y,rotation);
					stcg.setTrackReference(trackComponents.get(componentId));
					trackMap.put(componentId,stcg);
					break;
				case "switch":
					SwitchGraphics sg=new SwitchGraphics(x,y,rotation,Integer.parseInt(line[4]));
					sg.setTrackReference(trackComponents.get(componentId));
					trackMap.put(componentId,sg);
					controllableGraphics.put(componentId, sg);
					break;
				case "tunnel":
					TunnelGateGraphics tgg=new TunnelGateGraphics(x,y,rotation);
					tgg.setTrackReference(trackComponents.get(componentId));
					trackMap.put(componentId, tgg);
					controllableGraphics.put(componentId, tgg);
					break;
				case "entry":
					EntryPointGraphics epg=new EntryPointGraphics(x, y, rotation);
					epg.setTrackReference(trackComponents.get(componentId));
					trackMap.put(componentId, epg);
					break;
				case "cross":
					CrossGraphics cross = new CrossGraphics(x, y, rotation);
					cross.setTrackReference(trackComponents.get(componentId));
					trackMap.put(componentId, cross);
					break;
				default:
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		//Logikában betöltött elemek lekérése és összekapcsolása
		
		// controllables-ből id alapján kivenni és beállítani a megfelelő Drawablenek a sínelemet
		List<Controllable> controllables = controller.getControllables();
		for(Controllable c : controllables){
			String type=c.getType();
			switch (type) {
			case "Switch":
				SwitchGraphics sg=(SwitchGraphics)trackMap.get(c.getId());
				sg.setTrackReference(c);
				break;
			case "TunnelGate":
				TunnelGateGraphics tgc=(TunnelGateGraphics) trackMap.get(c.getId());
				tgc.setTrackReference(c);
				break;
			default:
				break;
			}
		}

		//Az összes kocsin végigiterál, mindhez megfelelő CartDrawable leszármazottat példányosít
		//Beállítja nekik a referenciát, és behelyezi pket a trainMap-ba
		List<Cart> carts = controller.getCarts();
		for(Cart c : carts){
			String type=c.getType();
			switch (type) {
			case "Engine":
				EngineGraphics eg=new EngineGraphics(0,0,0);
				eg.setCartReference(c);
				trainMap.put(c.getId(), eg);
				break;
			case "PassengerCart":
				PassengerCartGraphics pcg=new PassengerCartGraphics(0,0,0);
				pcg.setCartReference(c);
				trainMap.put(c.getId(), pcg);
				break;
			case "CoalCart":
				CoalCartGraphics ccg=new CoalCartGraphics(0,0,0);
				ccg.setCartReference(c);
				trainMap.put(c.getId(), ccg);
				break;
			default:
				break;
			}
		}
		
		// stationok litát feldolgozza, mindhez létrehoz grafikai megfelelőt, és megkeresí a sínt ahol van
		//Ennek a helyét állítja be magának is
		List<Station> stations = controller.getStations();
		for(Station s:stations){
			Point pos=trackMap.get(s.getId()).getPos();
			int rot=trackMap.get(s.getId()).getRotation();
			StationGraphics sg=new StationGraphics(pos.x,pos.y, rot);
			sg.setStationReference(s);
			stationMap.put(s.getId(), sg);
		}
		
	}

	/**
	 * Visszaadja a pályaelemeket tartalmazó listát.
	 *
	 * @return pályaelem lista
	 */
	public static Map<Integer, TrackDrawable> getTrackMap() {
		return trackMap;
	}

}
