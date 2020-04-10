package ships;

public class Boat extends Ship {
    /**
     * The Constructor of Ship class
     */
    public Boat(){
        length = 1;
        hit = new boolean[length];
        for(int i = 0; i < length; i++)
            hit[i] = false;
    }

    /**
     * Overridden method GetShipType()
     * @return type of class
     */
    @Override
    public String getShipType(){
        return "Boat";
    }

}
