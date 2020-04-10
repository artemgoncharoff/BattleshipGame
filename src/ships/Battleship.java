package ships;

public class Battleship extends Ship {

    /**
     * The Constructor of Battleship class
     */
    public Battleship(){
        length = 4;
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
        return "Battleship";
    }



}
