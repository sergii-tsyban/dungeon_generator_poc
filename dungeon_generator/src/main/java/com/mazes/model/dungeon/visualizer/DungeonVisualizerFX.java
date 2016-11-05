package com.mazes.model.dungeon.visualizer;

import com.mazes.model.dungeon.generator.CellularAutomatonCaveGeneration;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DungeonVisualizerFX extends Application{

    public static final int CELL_SIDE = 8;

    public static final int CAVE_WIDTH = 150;
    public static final int CAVE_HEIGHT = 100;

    private CellularAutomatonCaveGeneration carg;
    private Group root = new Group();
    private Canvas canvas;
    private Label label;

    public static void main(String[] args) {
        DungeonVisualizerFX.launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        canvas = new Canvas(CAVE_WIDTH * CELL_SIDE, CAVE_HEIGHT * CELL_SIDE);
        Scene scene = new Scene(root, Color.WHITE);
        label = new Label("");

        addCanvasMouseHandler();

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 7, 10, 7));
        vbox.setSpacing(10);
        vbox.getChildren().addAll(createControlPanel(), canvas, label);

        carg = new CellularAutomatonCaveGeneration(CAVE_WIDTH, CAVE_HEIGHT);

        draw(canvas.getGraphicsContext2D());
        
        root.getChildren().add(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addCanvasMouseHandler(){
        canvas.setOnMouseClicked(event -> {
            System.out.println("?: x=" + event.getX() + ", y=" + event.getY());
//                carg.fill((int) Math.floor(event.getY()/CELL_SIDE), (int) Math.floor(event.getX()/CELL_SIDE));
            draw(canvas.getGraphicsContext2D());
        });
    }

    private HBox createControlPanel(){
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 7, 10, 7));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");
        Button generateCave = new Button("Generate Cave");
        generateCave.setOnAction(event -> {
            label.setText("Status: processing");
            long millisBefore = System.currentTimeMillis();
            carg.addRoom(0,0,CAVE_WIDTH, CAVE_HEIGHT);
            carg.generateRooms();
            long generationTime = System.currentTimeMillis() - millisBefore;
            draw(canvas.getGraphicsContext2D());
            label.setText(String.format("Generated in %d millis",  generationTime));
        });
        hbox.getChildren().addAll(generateCave);
        return hbox;
    }

    private void draw(GraphicsContext gc){
        int x = 0;
        int y = 0;
        for (int[] row : carg.getCave()) {
            for (int cell : row) {
                gc.setFill(cell == CellularAutomatonCaveGeneration.WALL ? Color.BROWN : Color.WHITE);
                gc.fillRect(x,y, CELL_SIDE, CELL_SIDE);
                x += CELL_SIDE;
            }
            x = 0;
            y += CELL_SIDE;
        }
    }
}
