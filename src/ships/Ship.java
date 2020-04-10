package ships;

import game.Ocean;

public class Ship {
    int bowRow;
    int bowColumn;
    int length;
    boolean horizontal;
    boolean[] hit = new boolean[4];
    boolean isDamaged = false;

    //Getters
    public boolean isDamaged(){
        return isDamaged;
    }
    /**
     * the row (0 to 9) which contains the bow (front) of the ship.
     * @return
     */
    public int getBowRow() {
        return bowRow;
    }

    /**
     * the column (0 to 9) which contains the bow (front) of the ship.
     * @return
     */
    public int getBowColumn() {
        return bowColumn;
    }

    /**
     * the number of squares occupied by the ship.
     * @return
     */
    public boolean isHorizontal() {
        return horizontal;
    }

    /**
     * Returns the length of this particular ship
     * @return
     */
    int getLength(){
        return length;
    }
    //Setters
    public void setDamaged(boolean isDamaged){
        this.isDamaged = true;
    }
    /**
     * Sets the value of bowRow
     * @param row
     */
    public void setBowRow(int row) {
        this.bowRow = row;
    }

    /**
     * Sets the value of bowColumn
     * @param column
     */
    public void setBowColumn(int column) {
        this.bowColumn = column;
    }

    /**
     * Sets the value of the instance variable horizontal
     * @param horizontal
     */
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    /**
     * Returns the type of this ship
     * @return
     */
    public String getShipType() {
        return "Ship";
    }

    /**
     * Returns true if it is okay to put a ship of this length with its bow in this location, with the given orientation, and returns false otherwise.
     * @param row
     * @param column
     * @param horizontal
     * @param ocean
     * @return
     */
    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {

        if (horizontal) {

            for (int i = 0; i <= length + 1; i++) {

                if (ocean.isOccupied(row, column - 1 + i) || ocean.isOccupied(row - 1, column - 1 + i) || ocean.isOccupied(row + 1, column - 1 + i) || column + length > 9) {
                    return false;
                }

            }
        } else {

            for (int i = 0; i <= length + 1; i++) {

                if (ocean.isOccupied(row - 1 + i, column) || ocean.isOccupied(row - 1 + i, column - 1) || ocean.isOccupied(row - 1 + i, column + 1) || row + length > 9) {
                    return false;
                }


            }
        }

        return true;
    }

    /**
     *"Puts" the ship in the ocean. This involves giving values to the bowRow, bowColumn, and horizontal instance variables in the ship,
     * and it also involves putting a reference to the ship in each of 1 or more locations (up to 4) in the ships array in the Ocean object.
     * @param row
     * @param column
     * @param horizontal
     * @param ocean
     */
    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        bowRow = row;
        bowColumn = column;
        this.horizontal = horizontal;
        if (horizontal) {
            for (int i = 1; i < this.length; i++)
                ocean.getShipArray()[row][column + i] = ocean.getShipArray()[row][column];
        } else {
            for (int i = 1; i < this.length; i++)
                ocean.getShipArray()[row + i][column] = ocean.getShipArray()[row][column];
        }
    }

    /**
     * If a part of the ship occupies the given row and column,
     * and the ship hasn't been sunk, mark that part of the ship as "hit"
     * and return true, otherwise return false.
     * @param row
     * @param column
     * @return
     */
    public boolean shootAt(int row, int column) {

        if (horizontal) {
            if (!hit[column - bowColumn]) {
                hit[column - bowColumn] = true;
                return true;
            } else
                return false;
        } else {
            if (!hit[row - bowRow]) {
                hit[row - bowRow] = true;
                return true;
            } else
                return false;
        }


    }

    /**
     * Returns true if the part of the ship has been hit, false otherwise
     * @param row
     * @param column
     * @return
     */
    public boolean isHit(int row, int column) {
        if (horizontal)
            return hit[column - bowColumn];
        else return hit[row - bowRow];
    }

    /**
     * Return true if every part of the ship has been hit, false otherwise.
     * @return
     */
    public boolean isSunk() {

        int sum = 0;

        for (boolean h : hit) {
            if (h)
                sum++;
        }

        if (length != 1) {
            if (sum == length)
                return true;
        } else {
            if (sum == length)
                return true;
        }


        return false;
    }

}
