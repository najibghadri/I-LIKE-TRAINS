package skeleton;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Game, keretosztály a játék működéséhez, stdout logoláshoz a teszteléshez
 */
public class Game {
	static FileWriter fw;
    static BufferedWriter bw;
    static PrintWriter fileOut;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws InterruptedException the interrupted exception
	 * @throws IOException 
	 */
	//TODO gondolom így nincs kész
	public static void main(String args[]) throws InterruptedException{
		fw = null;
		bw = null;
		
		Controller controller=new Controller();
		controller.startGame();
	}
	
	/**
	 * Generate filename.
	 *
	 * @param name the name
	 * @return the string
	 */
	public static String generateFilename(String name) {
		String FILENAME =System.getProperty("user.dir");
		if(name.contains(".txt")){
			FILENAME=FILENAME+"\\res\\"+name;
		}
		else
			FILENAME=FILENAME+"\\res\\"+name+".txt";
		return FILENAME;
	}
	
	/**
	 * Logolást és stdout-ra kiírást végző függvény
	 *
	 * @param s A log információ kiírása
	 */
	public static void log(String s){
		System.out.println(s);
		
		try {
			String FILENAME = System.getProperty("user.dir");
			File file = new File(FILENAME+"\\res\\output.txt");

			if (!file.exists()) {
				file.createNewFile();
			}

			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write(s+"\n");
		}
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void clearOutput(){
		try {
			String FILENAME = System.getProperty("user.dir");
			File file = new File(FILENAME+"\\res\\output.txt");
			PrintWriter writer = new PrintWriter(file);
			writer.print("");
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * A teszteset stdoutputra logolt részét egy fájlbaírja és összehasonlítja 
	 * az előredefiniált helyes működést leíró outputtal.
	 * Ha minden egyezik, kiírja a stdoutputra az egyezést és a teszteset sikeres lefutását.
	 * Jöhet a következő teszteset.
	 */
	public static void outputCompare(){
		
	}
}
