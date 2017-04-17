package iliketrains;

// TODO: Auto-generated Javadoc
/**
 * Az állomás osztálya.
 *
 * @author Imi
 */
public class Station {
	
	/**  Állomás színe. */
	private Color color;
	
	/**  Van-e rajta utas. */
	private boolean passengers;
	
	/**  Állomás azonosítója. */
	private int id;
	
	
	/**
	 * Konstruktor.
	 *
	 * @param id Az állomás azonsoítója
	 * @param color Az állomás színe
	 * @param passengers Van-e utas az állomáson
	 */
	public Station(int id,String color,String passengers){
		this.id=id;
		if(passengers.equals(0))
			this.passengers=false;
		else if (passengers.equals(1))
			this.passengers=true;

		this.color=iliketrains.Color.valueOf(color);
	}

	/**
	 * Az állomás színének lekérdezésére szolgáló fv.
	 * @return Color Az állomás színe
	 */
	public final Color getColor() {
		return color;
	}
	
	/**
	 * @return Állomás azonosítót visszaadja
	 */
	public final int getId(){
		return id;
	}
	
	/**
	 * Lekérdezi, hogy van-e utas az állomáson.
	 *
	 * @return Van-e utas az állomáson
	 */
	public final boolean getPassengers(){
		return passengers;
	}
	
	/**
	 * A felszállnak az utasok és elhagyják az állomást.
	 */
	public void popPassengers(){
		passengers=false;
	}

	/**
	 * Leírást ad magáról.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		String temp= new String("Station: ");
		temp+=getColor().toString();
		temp+=", ";
		if(passengers)
			temp+="Has passengers";
		else
			temp+="Has no passengers";
		return temp;
	}

}