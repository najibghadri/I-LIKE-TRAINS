package graphics;

import iliketrains.Cart;
import skeleton.IliketrainsGUI;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Általános kocsi kirajzolását alapozza meg.
 * Mozgás rajzolása forgatássa és kocsi referencia beállítását kezeli.
 */
public abstract class CartDrawable extends Drawable {

    /** Kocsi referencia */
    protected Cart cart;

    public CartDrawable(int x,int y,int rotation){
        super(x,y,rotation);
    }

    /**
     * Beállítja a paraméterben megadott kocsi referenciáját magának
     *
     * @param cart Egy kocsi referenciája
     */
    public void setCartReference(Cart cart) {
        this.cart = cart;
    }

    /**
     * Mozgatás kirajzolásának logikája
     */
    protected void move() {
        AffineTransform tr = new AffineTransform();

        if(cart.getCurrentTrack()==null){
            tr.scale(0, 0);
            transform=tr;
            return;
        }

        Drawable current= IliketrainsGUI.getTrackMap().get(cart.getCurrentTrack().getId());

        if(current == null){
            tr.scale(0, 0);
            transform=tr;
            return;
        }

        Point p=current.getPos();

        // forgatás (a szög (rotation) a negatív irányba való eltérést jelzi)
        tr.rotate((Math.PI*current.getRotation())/180, p.x+30,p.y+30);


        // a megfelelő pontra való mozgatás
        tr.translate(p.getX(), p.getY());
        transform=tr;
    }

}
