package game;


import com.sun.media.jfxmediaimpl.platform.Platform;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


import java.util.Random;

public class Game {
    /** Player's board
     * Represents the ocean on board
     */
    private Board playerBoard;
    /** Enemy's board
     * Represents the ocean on board
     */
    private Board enemyBoard;
    /** Player's ocean
     *  Represents the ocean with ships and contains main game logic
     */
    private Ocean playerOcean;
    /**
     * Enemy's ocean
     * Represents the ocean with ships and contains main game logic
     */
    private Ocean enemyOcean;
    /**
     * Container with scene objects
     */
    private AnchorPane pane;
    /**
     * Shows actual log on the scene
     */
    private Label logLabel;
    /**
     * Makes shot, when player push on it
     */
    private Button fireButton;
    /**
     * Shows actual game info on the scene
     */
    private TextArea infoArea;
    /**
     * Actual log of the game
     * Shows the result of player's or enemy's move
     */
    private String log;

    /**
     * Constructor
     * @param pane - the main container of objects on scene
     * @param logLabel - game log component
     * @param infoArea - game info component
     * @param fireButton - fire button
     */
    public Game(AnchorPane pane, Label logLabel, TextArea infoArea, Button fireButton) {
        this.pane = pane;
        this.logLabel = logLabel;
        this.infoArea = infoArea;
        this.fireButton = fireButton;

        playerOcean = new Ocean();
        enemyOcean = new Ocean();

        playerOcean.placeAllShipsRandomly();
        enemyOcean.placeAllShipsRandomly();
        playerBoard = new Board();
        for (int i = 1; i <= 10; ++i) {
            for (int j = 1; j <= 10; ++j)
                playerBoard.updateBoard(playerOcean, i, j);
        }

        printInfo();
    }

    /**
     * Starts the game
     */
    public void startGame() {
        log = "The game started!";
        logLabel.setText(log);

        enemyBoard = new Board(mouseEvent -> {
            ImageView im = (ImageView) mouseEvent.getSource();
            Integer rowIndex = GridPane.getRowIndex(im);
            Integer columnIndex = GridPane.getColumnIndex(im);
            if (!enemyOcean.isShoot(rowIndex - 1, columnIndex - 1)) {
                playerMove(rowIndex, columnIndex);
                enemyMove();
            } else {
                log += "\nYou have shoot at this already!";
                logLabel.setText(log);
            }

        });
        fireButton.setOnMouseClicked(mouseEvent -> {
            Spinner<Integer> row = (Spinner) pane.getChildren().get(3);
            Spinner<Integer> column = (Spinner) pane.getChildren().get(4);
            int rowIndex = row.getValue() + 1;
            int columnIndex = column.getValue() + 1;
            if (!enemyOcean.isShoot(rowIndex - 1, columnIndex - 1)) {
                playerMove(rowIndex, columnIndex);
                enemyMove();
            } else {
                log += "\nYou have shot at this already!";
                logLabel.setText(log);
            }

        });



    }

    /**
     * Makes the player's shot
     * @param row from board
     * @param column from board
     */
    private void playerMove(int row, int column) {
        enemyOcean.shootAt(row - 1, column - 1);
        enemyBoard.updateBoard(enemyOcean, row, column);
        pane.getChildren().set(1, enemyBoard.getGridPane());

        log += String.format("\nYou have shot at [%x, %x]", (row - 1), column - 1);
        if (enemyOcean.getShipArray()[row - 1][column - 1].getShipType().equals("EmptySea"))
            log += " and missed...";
        else
            log += " and hit!";
        logLabel.setText(log);
        printInfo();
        if (enemyOcean.isGameOver()) {
            log += "\n You Win!!!";
            logLabel.setText(log);
            endGame(true);
        }

    }

    /**
     * Makes the enemy's shot
     */
    private void enemyMove() {
        int rowIndex = new Random().nextInt(10) + 1;
        int columnIndex = new Random().nextInt(10) + 1;
        while (playerOcean.isShoot(rowIndex - 1, columnIndex - 1)) {
            rowIndex = new Random().nextInt(10) + 1;
            columnIndex = new Random().nextInt(10) + 1;
        }

        playerOcean.shootAt(rowIndex - 1, columnIndex - 1);
        playerBoard.updateBoard(playerOcean, rowIndex, columnIndex);
        pane.getChildren().set(0, playerBoard.getGridPane());
        log += String.format("\nComputer have shot at [%x, %x]", (rowIndex - 1), columnIndex - 1);
        if (enemyOcean.getShipArray()[rowIndex - 1][columnIndex - 1].getShipType().equals("EmptySea"))
            log += " and missed...";
        else
            log += " and hit!";
        logLabel.setText(log);
        printInfo();
        if (playerOcean.isGameOver()) {
            log += "\n You lose...";
            logLabel.setText(log);
            endGame(false);
        }
    }

    /**
     * Ends the game and shows to player result of the game
     * @param result of game
     */
    private void endGame(Boolean result) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        if (result)
            alert.setHeaderText("YOU WIN!!!");
        else
            alert.setHeaderText("YOU LOSE...");
        alert.setContentText("Restart?");
        alert.getButtonTypes().removeAll(ButtonType.CANCEL, ButtonType.OK);
        alert.getButtonTypes().add(ButtonType.YES);
        alert.getButtonTypes().add(ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            restartGame();
        } else
            System.exit(0);
        restartGame();
    }

    /**
     * Restarts the game
     */
    private void restartGame() {
        playerOcean = new Ocean();
        enemyOcean = new Ocean();

        playerOcean.placeAllShipsRandomly();
        enemyOcean.placeAllShipsRandomly();
        playerBoard = new Board();
        for (int i = 1; i <= 10; ++i) {
            for (int j = 1; j <= 10; ++j) {
                playerBoard.updateBoard(playerOcean, i, j);
            }
        }
        log = "The game started!";
        logLabel.setText(log);
        printInfo();
        startGame();
    }

    /**
     * Prints actual game information
     */
    private void printInfo() {
        String info = String.format("Step number: %d \n\nEnemy \n Sunk: %d \n Damaged: %d \n Ships stay: %d \n" +
                        "Player \n Sunk: %d \n Damaged: %d \n Ships stay: %d ", playerOcean.getShotsFired(),
                enemyOcean.getShipsSunk(), enemyOcean.getShipsDamaged(), enemyOcean.getShipsStay(),
                playerOcean.getShipsSunk(), playerOcean.getShipsDamaged(), playerOcean.getShipsStay());
        infoArea.setText(info);
    }

    /**
     * @return player's board
     */
    public Board getPlayerBoard() {
        return playerBoard;
    }

    /**
     * @return enemy's board
     */
    public Board getEnemyBoard() {
        return enemyBoard;
    }
}
