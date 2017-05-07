package skeleton;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

/**
 * Game, keretosztály a játék működéséhez, stdout logoláshoz a teszteléshez
 */
public class Game {
	
	/** Filebaíráshoz szükséges objektum */
	static FileWriter fileWriter;
    
    /** Filebaíráshoz szükséges objektum */
    static BufferedWriter bufferedWriter;

	/**
	 * Main metódus
	 *
	 * @param args Alapértelmezett argumentumok
	 * @throws InterruptedException Megszakító hibakezeléshez
	 */
	public static void main(String args[]) throws InterruptedException{
		fileWriter = null;
		bufferedWriter = null;
		
		new Application();
	}
	
	/**
	 * Logolást és stdout-ra kiírást végző függvény
	 *
	 * @param s A log információ kiírása
	 */
	public synchronized static void log(String s){
		System.out.println(s);
		
		try {
			String FILENAME = System.getProperty("user.dir");
            new File(FILENAME+"\\res\\test_logs").mkdir();
			File file = new File(FILENAME+"\\res\\test_logs\\game_log.txt");

			if (!file.exists()) {
				file.createNewFile();
			}

			fileWriter = new FileWriter(file.getAbsoluteFile(), true);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(s+"\n");

		}
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if (bufferedWriter != null)
					bufferedWriter.close();
				if (fileWriter != null)
					fileWriter.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
