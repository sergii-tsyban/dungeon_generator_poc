package com.mazes.model.dungeon.visualizer;

import com.mazes.model.dungeon.allocator.TerrainTileType;
import com.mazes.model.dungeon.generator.BSPTreeGenerator;
import com.mazes.model.dungeon.generator.CellularAutomatonCaveGenerator;
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

public class BSPTreeVisualizerFX extends Application{

    public static final int CELL_SIDE = 2;

    private BSPTreeGenerator bspTreeGeneratorCave;
    private CellularAutomatonCaveGenerator cacg;
    private Group root = new Group();
    private Canvas canvas;
    private Label label;

    public static void main(String[] args) {
        DungeonVisualizerFX.launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        int width = 300;
        int height = 300;
        canvas = new Canvas(width * CELL_SIDE, height * CELL_SIDE);
        Scene scene = new Scene(root, Color.WHITE);
        label = new Label("Status: ");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 7, 10, 7));
        vbox.setSpacing(10);
        vbox.getChildren().addAll(createControlPanel(), canvas, label);

        bspTreeGeneratorCave = new BSPTreeGenerator(width, height);
        cacg = new CellularAutomatonCaveGenerator(width, height);

//        draw(canvas.getGraphicsContext2D());

        root.getChildren().add(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createControlPanel(){
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 7, 10, 7));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");
        final Button generateButton = new Button("Generate BSP");
        generateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                bspTreeGeneratorCave.generateLeafs();
                cacg.clear();
                drawLeafs(canvas.getGraphicsContext2D());
            }
        });
        final Button automatonButton = new Button("Launch cellular automaton");
        automatonButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for(BSPTreeGenerator.Node node: bspTreeGeneratorCave.getLeafs()){
                    cacg.addRoom(node.getI(), node.getJ(), node.getWidth(), node.getHeight());
                }
                cacg.generateRooms();
                draw(canvas.getGraphicsContext2D());
            }
        });
        hbox.getChildren().addAll(generateButton, automatonButton);
        return hbox;
    }

    private void drawLeafs(GraphicsContext gc){
        Random r = new Random();
        List<BSPTreeGenerator.Node> leafs = bspTreeGeneratorCave.getLeafs();
        for(BSPTreeGenerator.Node leaf: leafs){
            int w = leaf.getWidth();
            int h = leaf.getHeight();
            int x = leaf.getJ();
            int y = leaf.getI();
            Color c = Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255));
            gc.setFill(c);
            gc.fillRect(x*CELL_SIDE, y*CELL_SIDE, w*CELL_SIDE, h*CELL_SIDE);
        }
        label.setText(String.format("Status: %d leafs generated", leafs.size()));
    }

    private void draw(GraphicsContext gc){
        int x = 0;
        int y = 0;
        for (int[] row : cacg.getCave()) {
            for (int cell : row) {
                gc.setFill(cell == TerrainTileType.WALL_SOLID.getId() ? Color.BROWN : Color.WHITE);
                gc.fillRect(x,y, CELL_SIDE, CELL_SIDE);
                x += CELL_SIDE;
            }
            x = 0;
            y += CELL_SIDE;
        }
    }
}
