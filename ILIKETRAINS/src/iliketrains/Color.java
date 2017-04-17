package iliketrains;

/**
 * A színek megvalósításához szükséges enum
 */
public enum Color {
    Red, Green, Blue, Yellow, Brown;
    
    /**
     * Összehasonlító függvény az állomásra érkezéshez
     * @param A másik szín
     * @return Igaz, ha ugyanaz a két szín, hamis ha nem
     */
    public boolean equals(Color other){
         return other.name().equals(this.name());
    }
}
