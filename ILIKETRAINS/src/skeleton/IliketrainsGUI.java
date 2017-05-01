package skeleton;

import graphics.Drawable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

public class IliketrainsGUI extends JPanel{

	private Application app;
	private Map<Integer,Drawable> map;
	
	public IliketrainsGUI(Application application) {
		app=application;
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
	}

	/**
	 * Pálya grafikájának betöltése
	 * @param id
	 */
	public void loadGraphicsMap(int id){
		//TODO load graphics map
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
}
