package com.mazes.model.dungeon.visualizer;

import com.mazes.model.dungeon.generator.CellularAutomatonRoomGeneration;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Arrays;

/**
 * Created by sergii.tsyban on 10/26/2016.
 */
public class DungeonVisualizerFX extends Application{

    public static final int CELL_SIDE = 8;

    private CellularAutomatonRoomGeneration carg;
    private Group root = new Group();
    private Canvas canvas = new Canvas();

    public static void main(String[] args) {
        DungeonVisualizerFX.launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        int width = 100;
        int height = 100;
        Scene scene = new Scene(root, Color.WHITE);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 7, 10, 7));
        vbox.setSpacing(10);
        vbox.getChildren().addAll(createControlPanel(), canvas);

        carg = new CellularAutomatonRoomGeneration(width, height);
        carg.initWithRandom();
        carg.makeGenerationStep();

        draw(canvas.getGraphicsContext2D());
        
        root.getChildren().add(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createControlPanel(){
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 7, 10, 7));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");
        Button generateButton = new Button("Re-Generate");
        generateButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                carg.initWithRandom();
                draw(canvas.getGraphicsContext2D());
            }
        });
        Button addGeneration = new Button("Add Generation");
        addGeneration.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                carg.makeGenerationStep();
                draw(canvas.getGraphicsContext2D());
            }
        });
        hbox.getChildren().addAll(generateButton, addGeneration);
        return hbox;
    }

    private void draw(GraphicsContext gc){
//        List<Rectangle> rectangles = new ArrayList<Rectangle>();
        int x = 0;
        int y = 0;
        for (int[] row : carg.getRoom()) {
            for (int cell : row) {
                gc.setFill(cell == CellularAutomatonRoomGeneration.WALL ? Color.BROWN : Color.WHITE);
                gc.fillRect(x,y, CELL_SIDE, CELL_SIDE);
//                rectangles.add(r);
                x += CELL_SIDE;
            }
            x = 0;
            y += CELL_SIDE;
        }
    }
}
