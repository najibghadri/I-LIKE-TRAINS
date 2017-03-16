package iliketrains;

import java.util.*;

import skeleton.Skeleton;

public class RailCenter {

	Collection<Engine> engines;
	private Boolean collided;
	private int numberOfArrived;
	private int entryPoints;

	public RailCenter() {
		Skeleton.write("RailCenter konstruktor");
	}

	public void moveEngines() {
		// TODO - implement RailCenter.moveEngines
	}

	public void reportArrived() {
		// TODO - implement RailCenter.reportArrived
	}

	public void reportCollided() {
		Skeleton.addIndent();
		Skeleton.write("RailCenter.reportCollided() returns");
		Skeleton.removeIndent();
	}

	public void startTest6() {
		TrackComponent previousTrack=new TrackComponent();
		TrackComponent trackSetByTunnelTrack=new TrackComponent();
		TunnelGate actualTrack=new TunnelGate(null);
		Engine engineObject=new Engine(this,actualTrack,previousTrack);		
		
		engineObject.move();
	}

}