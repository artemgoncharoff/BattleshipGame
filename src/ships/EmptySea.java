package ships;

public class EmptySea extends Ship {

    /**
     * The constructor of EmptySea class
     */
    public EmptySea(){
        length = 1;
        hit = new boolean[1];
        hit[0] = false;
    }

    /**
     * Overridden method isHit()
     * @param row
     * @param column
     * @return
     */
    @Override
    public boolean isHit(int row, int column){
        return hit[0];
    }

    /**
     * Overridden method shootAt()
     * @param row
     * @param column
     * @return
     */
    @Override
    public boolean shootAt(int row, int column){
        hit[0] = true;
        return false;
    }

    /**
     * Overridden method isSunk()
     * @return always false
     */
    @Override
    public boolean isSunk(){
        return false;
    }

    /**
     * Overridden method toString.
     * @return '.' to say that the location is empty
     */
    @Override
    public String toString(){
        return ".";
    }

    @Override
    public String getShipType() {
        return "EmptySea";
    }
}
