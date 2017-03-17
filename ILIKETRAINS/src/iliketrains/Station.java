package iliketrains;

import skeleton.Skeleton;

public class Station {
	public Station(){
		Skeleton.write("Station konstruktor");
	}

	public void getColor() {
		Skeleton.addIndent();
		Skeleton.write("Station returns with color");
		Skeleton.removeIndent();
	}

}