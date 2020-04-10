package ships;

public class Cruiser extends Ship {
    /**
     * The Constructor of Cruiser class
     */
    public Cruiser(){
        length = 3;
        hit = new boolean[length];
        for(int i = 0; i < length; i++)
            hit[i] = false;
    }

    /**
     * Overridden method GetShipType()
     * @return type of the class
     */
    @Override
    public String getShipType(){
        return "Cruiser";
    }

}
