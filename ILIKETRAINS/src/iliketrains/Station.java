package iliketrains;

import skeleton.Skeleton;

public class Station {
	Color color;
	
	public Station(){
		Skeleton.write("Station konstruktor");
	}

	/**
	 * Az állomás színének lekérdezésére szolgáló fv.
	 * @return Color Az állomás színe
	 */
	public Color getColor() {
		Skeleton.addIndent();
		Skeleton.write("Station returns with color");
		Skeleton.removeIndent();
		return color;
	}

}