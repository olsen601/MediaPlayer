package com.RyanOlsen;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.File;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group pane = new Group();
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPrefSize(770, 70);
        grid.setGridLinesVisible(false);
        grid.setAlignment(Pos.CENTER);

        GridPane gridList = new GridPane();
        gridList.setHgap(20);
        gridList.setVgap(20);
        gridList.setPrefSize(770, 200);
        gridList.setPadding(new Insets(10, 10, 10, 10));
        gridList.setAlignment(Pos.TOP_CENTER);

        String path = "src//music//game of drones.mp3"; //actual song
        Media song = new Media(new File(path).toURI().toString());
        MediaPlayer player = new MediaPlayer(song);
        MediaView screen = new MediaView();
        player.setStartTime(player.getCurrentTime());
        player.setStopTime(player.getTotalDuration());

        Label title = new Label("Java Media Player");
        title.setTextFill(Color.valueOf("#e81713"));
        title.setFont(Font.font("Helvetica", FontWeight.BOLD, 36));
        title.setPadding(new Insets(10, 260, 340, 260));
        pane.getChildren().add(title);

        Label trackHeader = new Label("#      |      Track      |      Artist      |      Album      |      Genre");
        trackHeader.setTextFill(Color.valueOf("e81713"));
        trackHeader.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));

        Label track = new Label(musicDB.getTrack(1)); //get sql track as name, later create button
        track.setTextFill(Color.valueOf("e81713"));
        track.setFont(Font.font("Helvetica", FontWeight.BOLD, 16));
        gridList.add(track,1,1);



        gridList.add(trackHeader, 1, 0);



        HBox list = new HBox();
        list.getChildren().add(gridList);
        list.setPrefSize(770, 225);
        list.setLayoutX(20);
        list.setLayoutY(70);
        list.setStyle("-fx-background-color: #403840;");


        HBox menu = new HBox();
        menu.getChildren().add(grid);
        menu.setPrefSize(770, 70);
        menu.setLayoutX(20);
        menu.setLayoutY(320);
        BorderPane.setAlignment(menu, Pos.BOTTOM_CENTER);
        menu.setStyle("-fx-background-color: #403840");

        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(new Double[]{0.0, 0.0, 0.0, 50.0, 45.0, 25.0});
        final Button playButton = new Button();
        playButton.setShape(triangle);
        playButton.setPrefSize(45, 50);
        playButton.setMaxSize(45, 50);
        playButton.setMaxSize(45, 50);
        playButton.setStyle("-fx-background-color: #e81713;");

        Rectangle square = new Rectangle();
        square.setHeight(50);
        square.setWidth(50);

        final Button stopButton = new Button();
        stopButton.setShape(square);
        stopButton.setPrefSize(50, 50);
        stopButton.setMaxSize(50, 50);
        stopButton.setMaxSize(50, 50);
        stopButton.setStyle("-fx-background-color: #e81713;");

        Polygon rewind = new Polygon();
        rewind.getPoints().addAll(new Double[]{50.0, 50.0, 5.0, 25.0, 50.0, 0.0});

       /* final Button rewindButton = new Button();
        rewindButton.setShape(rewind);
        rewindButton.setPrefSize(45,50);
        rewindButton.setMaxSize(45,50);
        rewindButton.setMaxSize(45,50);
        rewindButton.setStyle("-fx-background-color: #403840;");*/

        Slider timeSlider = new Slider();
        timeSlider.setMaxWidth(400);
        timeSlider.setMinWidth(400);
        timeSlider.setMin(0.0);
        timeSlider.setMax(400.0);
        timeSlider.setShowTickLabels(true);
        timeSlider.setShowTickMarks(true);
        timeSlider.setMajorTickUnit(60);
        timeSlider.setMinorTickCount(30);
        timeSlider.setBlockIncrement(15);
        timeSlider.setStyle("-fx-color: #e81713;" + "-fx-fill: #e81713");

        Slider volumeSlider = new Slider();
        volumeSlider.setMinWidth(100);
        volumeSlider.setMaxWidth(100);
        volumeSlider.setValue(100);
        //volumeSlider.setOrientation(Orientation.VERTICAL);
        volumeSlider.setStyle("-fx-color: #e81713;" + "-fx-fill: #e81713");

        grid.add(timeSlider, 0, 0);
        //grid.add(rewindButton,1,0);
        grid.add(stopButton, 1, 0);
        grid.add(playButton, 2, 0);
        grid.add(volumeSlider, 3, 0);

        pane.getChildren().add(list);
        pane.getChildren().add(menu);
        pane.getChildren().add(screen);
        pane.setStyle("-fx-background-color: #e81713");

        Scene scene = new Scene(pane, 800, 400, Color.BLACK);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();


        //player.play();
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MediaPlayer.Status playStatus = player.getStatus();
                if (playStatus == MediaPlayer.Status.HALTED || playStatus == MediaPlayer.Status.UNKNOWN) {
                    return;
                } else {
                    player.play();

                }
            }
        });

        stopButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MediaPlayer.Status stopStatus = player.getStatus();
                if (stopStatus == MediaPlayer.Status.HALTED || stopStatus == MediaPlayer.Status.UNKNOWN) {
                    return;
                } else {
                    player.pause();
                }
            }
        });

        timeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (timeSlider.isValueChanging() || timeSlider.isPressed()) {
                    player.seek((player.getTotalDuration().multiply((timeSlider.getValue() / 100.0))));
                }
            }
        });

        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (volumeSlider.isValueChanging()) {
                    player.setVolume(volumeSlider.getValue() / 100.0);
                }
            }
        });

        player.setOnPlaying(new Runnable() {
            @Override
            public void run() {
                while (player.getStatus() == MediaPlayer.Status.PLAYING) {
                    Duration currentTime = player.getCurrentTime();
                    Double totalTime = player.getTotalDuration().toMillis();
                    timeSlider.setDisable(player.getCurrentTime().isUnknown());
                    if (!timeSlider.isDisabled() && player.getCurrentTime().greaterThan(Duration.ZERO)
                            && !timeSlider.isValueChanging()) {
                        timeSlider.setValue((currentTime.divide(totalTime)).toSeconds()*400.0);
                    }
                }
            }
        });
        player.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                player.dispose();
            }
        });
        player.setOnError(new Runnable() {
            @Override
            public void run() {
                player.dispose();
            }
        });
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                player.dispose();
            }
        });
    }
}