package graphics;

import iliketrains.TrackComponent;

/**
 * Sínelemek grafikus ősosztálya
 */
public abstract class TrackDrawable extends Drawable {

    /** Referencia a hozzátartozó sínelemhez */
    protected TrackComponent trackComponent;


    /**
     * Konstruktor
     *
     * @param x koordináta
     * @param y koordináta
     * @param rotation elforgatás szöge
     */
    public TrackDrawable(int x,int y,int rotation){
        super(x,y,rotation);
    }

    /**
     * Beállítja a kapott referenciát magának
     *
     * @param trackReference sní referencia
     */
    public void setTrackReference(TrackComponent trackReference){
        trackComponent = trackReference;
    }

    /**
     * Visszaadja a forgatás szögét
     *
     * @param previous kelőző sínelem
     * @return forgatás szöge
     */
    public abstract int getCartRotation(TrackComponent previous);

}
