package skeleton;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Menüablakot megvalósító osztály
 */
@SuppressWarnings("serial")
public class Menu extends JPanel{
	
	/** Az app JFramre mutató referencia */
	private Application app;
	
	/**
	 * Menü panel csak 2 gombból áll, egyik az új játékot indítja, másik kilép az alkalmazásból.
	 * Az Application függvényeit hívják a gombnyomások
	 *
	 * @param application the application
	 */
	public Menu(Application application) {
		super();
		app=application;
		setSize(600, 600);
		setLayout(null);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setBounds(228, 191, 117, 59);
		add(btnNewGame);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(228, 331, 117, 59);
		add(btnExit);	
		
		btnNewGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				app.newGame();				
			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				app.exit();				
			}
		});
	}
}
