package skeleton;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.List;

/**
 * Game, keretosztály a játék működéséhez, stdout logoláshoz a teszteléshez
 */
public class Game {
	
	/** The file writer. */
	static FileWriter fileWriter;
    
    /** The buffered writer. */
    static BufferedWriter bufferedWriter;
    
    /** The file out. */
    static PrintWriter fileOut;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws InterruptedException the interrupted exception
	 */
	public static void main(String args[]) throws InterruptedException{
		fileWriter = null;
		bufferedWriter = null;
		
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
			FILENAME=FILENAME+"\\res\\testIn\\"+name;
		}
		else
			FILENAME=FILENAME+"\\res\\testIn\\"+name+".txt";
		return FILENAME;
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
			File file = new File(FILENAME+"\\res\\output.txt");

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
	
	/**
	 * Kiüríti a log output fájlját, hogy a következő teszteset kerülhessen bele
	 */
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
	public static void outputCompare(int testNum){
		try {
			String FILENAME = System.getProperty("user.dir");
			List<String> f1 = Files.readAllLines(Paths.get(FILENAME+"\\res\\output.txt"));
			List<String> f2 = Files.readAllLines(Paths.get(FILENAME+"\\res\\testOuts\\test"+testNum+".txt"));
			
			boolean flag = true;
			if(f1.size() != f2.size())
                flag = false;
            
            for(int i=0; i<f1.size() && flag; i++){
                if(!f1.get(i).equals(f2.get(i))){
                    flag = false;
                }
            }
			if (flag)
				System.out.println("[Test"+testNum+": SUCCESS]");
			else
				System.out.println("[Test"+testNum+": FAILED]");	
		} catch (IOException e) {
			System.out.println("Nem található a két összehasonlítandó fájl");
			e.printStackTrace();
		}
	}
}
