package main;

import game.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        //Container for components
        AnchorPane pane = new AnchorPane();
        //Component for information of the game
        TextArea infoArea = new TextArea();
        infoArea.editableProperty().setValue(false);
        infoArea.setPrefHeight(200.0);
        infoArea.setPrefWidth(250.0);
        AnchorPane.setRightAnchor(infoArea, 25.0);
        AnchorPane.setTopAnchor(infoArea, 20.0);
        //Component for game log
        Label logLabel = new Label();
        ScrollPane scrollPane = new ScrollPane(logLabel);
        scrollPane.setPrefViewportHeight(130);
        scrollPane.setPrefViewportWidth(665);
        scrollPane.vvalueProperty().bind(logLabel.heightProperty());
        AnchorPane.setLeftAnchor(scrollPane, 20.0);
        AnchorPane.setBottomAnchor(scrollPane, 0.0);
        //Fire button is alternative way to shoot
        Button fireButton = new Button("Fire!");
        fireButton.setMinSize(70, 20);
        AnchorPane.setRightAnchor(fireButton, 30.0);
        AnchorPane.setBottomAnchor(fireButton, 70.0);

        Label rowLabel = new Label("Row:");
        AnchorPane.setRightAnchor(rowLabel, 245.0);
        AnchorPane.setBottomAnchor(rowLabel, 95.0);

        Label columnLabel = new Label("Column:");
        AnchorPane.setRightAnchor(columnLabel, 140.0);
        AnchorPane.setBottomAnchor(columnLabel, 95.0);

        Spinner<Integer> rowField = new Spinner<>(0, 9, 0);
        rowField.setMaxSize(50.0, 20.0);
        AnchorPane.setRightAnchor(rowField, 220.0);
        AnchorPane.setBottomAnchor(rowField, 70.0);

        Spinner<Integer> columnField = new Spinner<>(0, 9, 0);
        columnField.setMaxSize(50.0, 20.0);
        AnchorPane.setRightAnchor(columnField, 135.0);
        AnchorPane.setBottomAnchor(columnField, 70.0);

        Label label1 = new Label("Player's Ocean");
        AnchorPane.setLeftAnchor(label1, 50.0);
        AnchorPane.setTopAnchor(label1, 4.0);
        Label label2 = new Label("Enemy's Ocean");
        AnchorPane.setLeftAnchor(label2, 400.0);
        AnchorPane.setTopAnchor(label2, 4.0);

        Game game = new Game(pane, logLabel, infoArea, fireButton);
        game.startGame();

        pane.getChildren().addAll(game.getPlayerBoard().getGridPane(), game.getEnemyBoard().getGridPane(),
                scrollPane, rowField, columnField, rowLabel, columnLabel, fireButton, infoArea, label1, label2);

        Scene scene = new Scene(pane, 1000, 500);

        primaryStage.setTitle("BattleShip");
        primaryStage.getIcons().add(new Image("assets/logo.png"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
