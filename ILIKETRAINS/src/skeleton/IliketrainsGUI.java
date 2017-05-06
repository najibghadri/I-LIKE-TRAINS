package skeleton;

import graphics.Drawable;
import graphics.EntryPointGraphics;
import iliketrains.Cart;
import iliketrains.Controllable;
import iliketrains.Station;
import iliketrains.TrackComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class IliketrainsGUI extends JPanel{

	private Application app;
	private Controller controller;
	private static Map<Integer,Drawable> map=new HashMap<Integer, Drawable>();
	
	public IliketrainsGUI(Application application, Controller controller) {
		app=application;
		this.controller=controller;
		setLayout(null);
		JButton btnStop = new JButton("Stop");
		btnStop.setBounds(10,10, 100, 20);
		add(btnStop);
		btnStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				app.stop();	
			}
		});
		setOpaque(true);
		setBackground(new Color(0,120,40));
	}

	/**
	 * Pálya grafikájának betöltése
	 * A controllertől lekéri a szükséges referenciákat a logikára
	 */
	public void loadGraphicsMap(){
		map.clear();
		//TODO load graphics map
		int id=controller.getNumberOfMap();
		List<Controllable> controllables=controller.getControllables();
		List<Cart> carts=controller.getCarts();
		List<Station> stations=controller.getStations();
		//controllables-ből id alapján kivenni és beállítani a megfelelő Drawablenek
		//stationok litából szín és id alapján lehet sín helye alapján beállítani
	}

	/**
	 * Elindítja a kirajzolás időzítőjét
	 */	
	public void start() {
		//TODO elindítás
	}

	public void stop() {
		// TODO kirajzoló timert leállítani		
	}
	
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

}
