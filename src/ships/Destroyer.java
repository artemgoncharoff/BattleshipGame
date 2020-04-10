package ships;

public class Destroyer extends Ship {
    /**
     * The Constructor of Destroyer class
     */
    public Destroyer(){
        length = 2;
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
        return "Destroyer";
    }

}
