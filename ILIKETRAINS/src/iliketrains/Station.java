package iliketrains;

import skeleton.Skeleton;

public class Station {
	private Color color;
	private boolean passengers;
	
	public Station(){}
	
	public Station(Color color,boolean passengers){
		this.color=color;
		this.passengers=passengers;
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