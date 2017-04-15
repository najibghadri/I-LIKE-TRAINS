package iliketrains;

/**
 * Az állomás osztálya
 */
public class Station {
	
	/** Állomás színe */
	private Color color;
	
	/** Van-e rajta utas */
	private boolean passengers;
	
	/** Állomás azonosítója */
	private int id;
	
	public Station(){}
	
	/**
	 * Konstruktor
	 *
	 * @param id Az állomás azonsoítója
	 * @param color Az állomás színe
	 * @param passengers Van-e utas az állomáson
	 */
	public Station(int id,String color,String passengers){
		this.id=id;
		//TODO-assign color based on string
		if(passengers.equals(1))
			this.passengers=true;
		else
			this.passengers=false;
	}

	/**
	 * Az állomás színének lekérdezésére szolgáló fv.
	 * @return Color Az állomás színe
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Lekérdezi, hogy van-e utas az állomáson
	 * @return Van-e utas az állomáson
	 */
	public boolean getPassangers(){
		return passengers;
	}
	
	/**
	 * A felszállnak az utasok és elhagyják az állomást
	 */
	public void popPassengers(){
		passengers=false;
	}

	/**
	 * Leírást ad magáról
	 * @return
	 */
	@Override
	public String toString() {
		//TODO - ez így nem jó
		if(passengers)
			return "Station: "+"blue"+" has passengers";
		else
			return "Station: "+"blue"+ " no passengers";
	}

}