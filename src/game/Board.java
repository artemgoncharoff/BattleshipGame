package game;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import ships.Ship;

import java.io.File;
import java.net.MalformedURLException;


public class Board {

    private static Image SEA = new Image("assets/sea.png");
    private static Image SEA_SHOT = new Image("assets/sea_shot.png");
    private static Image SHIP_SHOT = new Image("assets/ship_shot.png");
    private static Image[] BATTLESHIP = new Image[]{new Image("assets/battleship/1.png"),
            new Image("assets/battleship/2.png"),
            new Image("assets/battleship/3.png"),
            new Image("assets/battleship/4.png"),};
    private static Image[] CRUISER = new Image[]{new Image("assets/cruiser/1.png"),
            new Image("assets/cruiser/2.png"),
            new Image("assets/cruiser/3.png"),};
    private static Image[] DESTROYER = new Image[]
            {new Image("assets/destroyer/1.png"),
                    new Image("assets/destroyer/2.png"),};
    private static Image SUBMARINE = new Image("assets/submarine/1.png");
    private static Image[] NUMBERS = new Image[]{new Image("assets/numbers/0.png"),
            new Image("assets/numbers/1.png"),new Image("assets/numbers/2.png"),
            new Image("assets/numbers/3.png"),new Image("assets/numbers/4.png"),
            new Image("assets/numbers/5.png"),new Image("assets/numbers/6.png"),
            new Image("assets/numbers/7.png"),new Image("assets/numbers/8.png"),
            new Image("assets/numbers/9.png"),};

    /**
     * Represents the projection of ocean
     */
    private GridPane gridPane = new GridPane();

    /**
     * Sets the start Grid Pane
     * @param handler - mouse handler
     */
    public Board(EventHandler<? super MouseEvent> handler) {
        AnchorPane.setLeftAnchor(gridPane, 370.0);
        AnchorPane.setTopAnchor(gridPane, 20.0);

        for (int i = 1; i <= 10; ++i){
            ImageView imageView1 = new ImageView(NUMBERS[i - 1]);
            ImageView imageView2 = new ImageView(NUMBERS[i - 1]);
            imageView1.setFitWidth(30);
            imageView1.setFitHeight(30);
            imageView2.setFitWidth(30);
            imageView2.setFitHeight(30);
            gridPane.add(imageView1, i, 0);
            gridPane.add(imageView2, 0, i);
        }
        for (int i = 1; i <= 10; ++i) {
            for (int j = 1; j <= 10; ++j) {
                ImageView imageView = new ImageView(SEA);
                imageView.setOnMouseClicked(handler);
                imageView.setFitHeight(30);
                imageView.setFitWidth(30);
                gridPane.add(imageView, j, i);
            }
        }

    }

    /**
     * Sets the start Grid Pane
     */
    public Board() {
        AnchorPane.setLeftAnchor(gridPane, 20.0);
        AnchorPane.setTopAnchor(gridPane, 20.0);
        for (int i = 1; i <= 10; ++i){
            ImageView imageView1 = new ImageView(NUMBERS[i - 1]);
            ImageView imageView2 = new ImageView(NUMBERS[i - 1]);
            imageView1.setFitWidth(30);
            imageView1.setFitHeight(30);
            imageView2.setFitWidth(30);
            imageView2.setFitHeight(30);
            gridPane.add(imageView1, i, 0);
            gridPane.add(imageView2, 0, i);
        }
        for (int i = 1; i <= 10; ++i) {
            for (int j = 1; j <= 10; ++j) {
                ImageView imageView = new ImageView(SEA);
                imageView.setFitHeight(30);
                imageView.setFitWidth(30);
                gridPane.add(imageView, j, i);
            }
        }
    }

    /**
     * Updates the gridPlane by actual ocean
     * @param ocean - actual ocean
     * @param row -
     * @param column
     */
    public void updateBoard(Ocean ocean, Integer row, Integer column) {
        Ship ship = ocean.getShipArray()[row - 1][column - 1];
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                ImageView imageView = (ImageView) node;
                switch (ship.getShipType()) {
                    case "EmptySea":
                        if (ship.isHit(row - 1, column - 1))
                            imageView.setImage(SEA_SHOT);
                        else imageView.setImage(SEA);
                        break;
                    case "Battleship":
                        if (ship.isHit(row - 1, column - 1))
                            imageView.setImage(SHIP_SHOT);
                        else if (ship.isHorizontal())
                            imageView.setImage(BATTLESHIP[column - ship.getBowColumn() - 1]);
                        else {
                            imageView.setImage(BATTLESHIP[row - ship.getBowRow() - 1]);
                            imageView.setRotate(90);
                        }
                        break;
                    case "Cruiser":
                        if (ship.isHit(row - 1, column - 1))
                            imageView.setImage(SHIP_SHOT);
                        else if (ship.isHorizontal())
                            imageView.setImage(CRUISER[column - ship.getBowColumn() - 1]);
                        else {
                            imageView.setImage(CRUISER[row - ship.getBowRow() - 1]);
                            imageView.setRotate(90);
                        }
                        break;
                    case "Destroyer":
                        if (ship.isHit(row - 1, column - 1))
                            imageView.setImage(SHIP_SHOT);
                        else if (ship.isHorizontal())
                            imageView.setImage(DESTROYER[column - ship.getBowColumn() - 1]);
                        else {
                            imageView.setImage(DESTROYER[row - ship.getBowRow() - 1]);
                            imageView.setRotate(90);
                        }
                        break;
                    case "Boat":
                        if (ship.isHit(row - 1, column - 1))
                            imageView.setImage(SHIP_SHOT);
                        else if (ship.isHorizontal())
                            imageView.setImage(SUBMARINE);
                        else {
                            imageView.setImage(SUBMARINE);
                            imageView.setRotate(90);
                        }
                        break;
                }
                break;
            }
        }
    }

    /**
     * @return projection of ocean on GridPane
     */
    public GridPane getGridPane() {
        return gridPane;
    }


}
