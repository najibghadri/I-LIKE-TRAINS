package iliketrains;

import skeleton.Skeleton;

public class Station {
	private Color color;
	private boolean passengers;
	private int id;
	
	public Station(){}
	
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
	
	public boolean getPassangers(){
		return passengers;
	}
	
	public void popPassengers(){
		passengers=false;
	}

	public String print() {
		//TODO - színt a stationtól kérni
		if(passengers)
			return "Station: "+"blue"+" has passengers";
		else
			return "Station: "+"blue"+ " no passengers";
	}

}