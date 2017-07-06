package com.mazes.model.dungeon.visualizer;

import com.mazes.model.dungeon.generator.graph.bsp.BSPNode;
import com.mazes.model.dungeon.generator.graph.bsp.BSPTreeGenerator;
import com.mazes.model.dungeon.generator.dungeon.DungeonGenerator;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;
import java.util.Random;

public class BSPVisualizer extends Application {

    public static final int CELL_SIDE = 3;

    private BSPTreeGenerator bspTreeGenerator;
    private DungeonGenerator dungeonGenerator;

    private List<BSPNode> areas;

    private Group root;
    private Label label;
    private Canvas canvas;
    private int width;
    private int height;

    public static void main(String[] args) {
        DungeonVisualizerFX.launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        //init fields
        bspTreeGenerator = new BSPTreeGenerator();

        width = 100;
        height = 100;
        root = new Group();
        canvas = new Canvas(1000, 600);
        label = new Label("Status: ");
        //init java fx
        Scene scene = new Scene(root, Color.WHITE);
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 7, 10, 7));
        vbox.setSpacing(10);
        vbox.getChildren().addAll(createControlPanel(), canvas, label);

        root.getChildren().add(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createControlPanel(){
        final HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 7, 10, 7));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");
        final Button generateButton = new Button("Split into leafs");
        generateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                areas = bspTreeGenerator.treeLeafs(width, height);
                draw(canvas.getGraphicsContext2D());
            }
        });
        hbox.getChildren().addAll(generateButton);
        return hbox;
    }

    private void draw(GraphicsContext gc){
        Random r = new Random();
        for (BSPNode area : areas) {
            int x = area.j * CELL_SIDE;
            int y = area.i * CELL_SIDE;
            int w = area.width * CELL_SIDE;
            int h = area.height * CELL_SIDE;
            gc.setFill(Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
            gc.fillRect(x, y, w, h);
            gc.setFill(Color.BLACK);
            gc.fillText(String.format("w=%d h=%d", area.width, area.height), x, y + h/2);
        }
    }
}
