package iliketrains;

/**
 * Szeneskocsi, működésében azonos a sima vonatkocsival
 * Felszállni nem tudnak az utasok
 * @author Imi
 */
public class CoalCart extends Cart{

	/**
	 * Konstruktor
	 * @param id A szeneskocsi azonosítója
	 */
	public CoalCart(int id){
		super(id);
	}
	
	/**
     * Saját típusát sztringben
     * @return "CoalCart"
     */
    @Override
    public String getType(){
        return "CoalCart";
    }
}
