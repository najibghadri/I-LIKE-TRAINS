package skeleton;

/**
 * Game, keretosztály a játék működéséhez, stdout logoláshoz a teszteléshez
 */
public class Game {

	//TODO gondolom így nincs kész
	public static void main(String args[]) throws InterruptedException{
		Controller controller=new Controller();
		controller.startGame();
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
