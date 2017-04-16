package iliketrains;

/**
 * A színek megvalósításához szükséges enum
 */
public enum Color {
    Red, Green, Blue, Yellow, Brown;

    public boolean equals(Color other){
         return other.name().equals(this.name());
    }
}
