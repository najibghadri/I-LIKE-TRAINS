package graphics;

import iliketrains.TrackComponent;

/**
 *
 */
public abstract class TrackDrawable extends Drawable {

    /* Sín elem referencia */
    protected TrackComponent trackComponent;


    public TrackDrawable(int x,int y,int rotation){
        super(x,y,rotation);
    }

    public void setTrackReference(TrackComponent trackReference){
        trackComponent = trackReference;
    }

    public abstract int getCartRotation(TrackComponent previous);

}
