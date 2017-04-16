package skeleton;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Game, keretosztály a játék működéséhez, stdout logoláshoz a teszteléshez
 */
public class Game {

	//TODO gondolom így nincs kész
	public static void main(String args[]) throws InterruptedException{
		System.out.println("Játék vagy teszt? (1|2)");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		Controller controller=new Controller();

		try {
			line = reader.readLine();
			if(line == "1"){
				controller.startGame();
			}
			else{
				System.out.println("1. teszt)");
				System.out.println("2. teszt)");
				System.out.println("3. teszt)");
				System.out.println("4. teszt)");
				System.out.println("5. teszt)");
				System.out.println("6. teszt)");
				System.out.println("7. teszt)");
				System.out.println("8. teszt)");
				line = reader.readLine();
				controller.startGame();
				File file = null;
					switch (line){
						case "1":
							file = new File("file.txt");
							break;
						case "2":
							file = new File("file.txt");
							break;
						case "3":
							file = new File("file.txt");
							break;
						case "4":
							file = new File("file.txt");
							break;
						case "5":
							file = new File("file.txt");
							break;
						case "6":
							file = new File("file.txt");
							break;
						case "7":
							file = new File("file.txt");
							break;
						case "8":
							file = new File("file.txt");
							break;
						default:
							break;
					}
				reader = null;
				ArrayList<String> commands = new ArrayList<String>();
				try {
					reader = new BufferedReader(new FileReader(file));
					String text = null;
					while ((text = reader.readLine()) != null) {
						commands.add(text);
					}
				} catch (Exception e) {

				} finally {
					try {
						if (reader != null) {
							reader.close();
						}
					} catch (IOException e) {
					}
				}
				for (String in: commands) {
					InputStream fakeIn = new ByteArrayInputStream(in.getBytes());
					System.setIn(fakeIn);
				}


			}
		}
		catch (IOException e){

		}

	}
	
	public static void log(String s){
		System.out.println(s);
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
