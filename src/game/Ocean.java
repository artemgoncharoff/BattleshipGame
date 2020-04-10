package game;

import ships.*;

import java.util.Random;

public class Ocean {

    private Ship[][] ships = new Ship[10][10];
    private static Random rnd = new Random();
    private int shotsFired;
    private int hitCount;
    private int shipsSunk;
    private int shipsDamaged;
    private int shipsStay;

    /**
     * The Constructor of class Ocean
     * Creates an "empty" ocean (fills the ships array with EmptySeas).
     * Also initializes any game variables, such as how many shots have been fired.
     */
    public Ocean() {
        for (var i = 0; i < 10; i++)
            for (var j = 0; j < 10; j++)
                ships[i][j] = new EmptySea();

        shotsFired = 0;
        hitCount = 0;
        shipsSunk = 0;
        shipsDamaged = 0;
        shipsStay = 10;
    }

    /**
     * Places all ten ships randomly on the (initially empty) ocean.
     */
    public void placeAllShipsRandomly() {
        boolean flag;

        flag = false;
        while (!flag) {
            int row = rnd.nextInt(10);
            int column = rnd.nextInt(10);
            boolean horizontal = rnd.nextBoolean();
            if (new Battleship().okToPlaceShipAt(row, column, horizontal, this)) {
                ships[row][column] = new Battleship();
                ships[row][column].placeShipAt(row, column, horizontal, this);
                flag = true;
            }

        }
        for (int i = 0; i < 2; i++) {
            flag = false;
            while (!flag) {
                int row = rnd.nextInt(10);
                int column = rnd.nextInt(10);
                boolean horizontal = rnd.nextBoolean();
                if (new Cruiser().okToPlaceShipAt(row, column, horizontal, this)) {
                    ships[row][column] = new Cruiser();
                    ships[row][column].placeShipAt(row, column, horizontal, this);
                    flag = true;
                }

            }
        }
        for (int i = 0; i < 3; i++) {

            flag = false;
            while (!flag) {
                int row = rnd.nextInt(10);
                int column = rnd.nextInt(10);
                boolean horizontal = rnd.nextBoolean();
                if (new Destroyer().okToPlaceShipAt(row, column, horizontal, this)) {
                    ships[row][column] = new Destroyer();
                    ships[row][column].placeShipAt(row, column, horizontal, this);
                    flag = true;
                }

            }

        }

        for (int i = 0; i < 4; i++) {
            flag = false;
            while (!flag) {
                int row = rnd.nextInt(10);
                int column = rnd.nextInt(10);
                boolean horizontal = rnd.nextBoolean();
                if (new Boat().okToPlaceShipAt(row, column, horizontal, this)) {
                    ships[row][column] = new Boat();
                    ships[row][column].placeShipAt(row, column, horizontal, this);
                    flag = true;
                }

            }
        }

    }

    public boolean isShoot(int row, int column) {
        if (ships[row][column].isHit(row, column))
            return true;
        return false;
    }

    /**
     * Returns true if the given location contains a ship, false if it does not.
     *
     * @param row
     * @param column
     * @return
     */
    public boolean isOccupied(int row, int column) {

        if (row < 0 || row > 9 || column < 0 || column > 9)
            return false;
        if (!ships[row][column].toString().equals("."))
            return true;

        return false;
    }

    /**
     * Returns true if the given location contains a "real" ship, still afloat, (not an EmptySea), false if it does not.
     * In addition, this method updates the number of shots that have been fired, and the number of hits.
     *
     * @param row
     * @param column
     * @return
     */
    public boolean shootAt(int row, int column) {
        if (!ships[row][column].toString().equals("."))
            if (ships[row][column].shootAt(row, column)) {
                if(!ships[row][column].isDamaged()){
                    ships[row][column].setDamaged(true);
                    ++shipsDamaged;
                }
                ++hitCount;
                ++shotsFired;
                if (ships[row][column].isSunk()) {
                    ++shipsSunk;
                    --shipsDamaged;
                    --shipsStay;
                }
                return true;
            }
        shotsFired++;
        ships[row][column].shootAt(row, column);

        return false;

    }


    /**
     * Returns the number of shots fired (in this game).
     *
     * @return
     */
    public int getShotsFired() {
        return shotsFired;
    }

    /**
     * Returns the number of hits recorded (in this game).
     *
     * @return
     */
    public int getHitCount() {
        return hitCount;
    }

    /**
     * Returns the number of ships sunk (in this game).
     *
     * @return
     */
    public int getShipsSunk() {
        return shipsSunk;
    }

    /**
     * Returns true if all ships have been sunk, otherwise false.
     *
     * @return
     */
    public boolean isGameOver() {

        if (shipsSunk == 10)
            return true;
        return false;
    }

    /**
     * Returns the 10x10 array of ships.
     *
     * @return
     */
    public Ship[][] getShipArray() {
        return ships;
    }

    /**
     * @return how much ships were damaged
     */
    public int getShipsDamaged() {
        return shipsDamaged;
    }

    /**
     * @return how mush ships are stayed at the field
     */
    public int getShipsStay(){
        return shipsStay;
    }
}
