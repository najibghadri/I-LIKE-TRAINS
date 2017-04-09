package skeleton;

public class Game {

	public static void main(String args[]) throws InterruptedException{
		Controller controller=new Controller();
		controller.startGame();
	}
	
	public static void log(String s){
		System.out.println(s);
	}
}
