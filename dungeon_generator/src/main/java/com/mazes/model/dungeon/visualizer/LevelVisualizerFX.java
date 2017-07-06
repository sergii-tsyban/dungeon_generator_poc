package com.mazes.model.dungeon.visualizer;

import com.mazes.model.dungeon.generator.DungeonConstructionFlow;
import com.mazes.model.dungeon.generator.chain.LevelConstructionContext;
import com.mazes.model.dungeon.generator.dungeon.Dungeon;
import com.mazes.model.dungeon.generator.dungeon.Level;
import com.mazes.model.dungeon.generator.dungeon.SubDungeon;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.*;

import static com.mazes.model.dungeon.allocator.TerrainTilesIds.WALL_SOLID;

public class LevelVisualizerFX extends Application{

    public static final int CELL_SIDE = 3;

    private DungeonConstructionFlow dungeonConstructionFlow;

    private Level level;

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
        dungeonConstructionFlow = new DungeonConstructionFlow();

        width = 130;
        height = 130;
        root = new Group();
        canvas = new Canvas(width * CELL_SIDE + 2, width * CELL_SIDE + 2);
        label = new Label("Status: ");
        //init java fx
        Scene scene = new Scene(root, Color.WHITE);
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 7, 10, 7));
        vbox.setSpacing(10);
        vbox.getChildren().addAll(createControlPanel(), canvas, label);

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
        final Button generateButton = new Button("Generate topology");
        generateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LevelConstructionContext lgc = new LevelConstructionContext();
                lgc.levelWidth = width;
                lgc.levelHeight = height;
                level = dungeonConstructionFlow.constructNew(lgc);
                draw(canvas.getGraphicsContext2D());
            }
        });
        final Button allocateIdsButton = new Button("Allocate tile ids");
        allocateIdsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        hbox.getChildren().addAll(generateButton, allocateIdsButton);
        return hbox;
    }

    private void draw(GraphicsContext gc){

        int x = 0;
        int y = 0;
        for (int[] row : level.getTopology()) {
            for (int cell : row) {
                gc.setFill(cell == WALL_SOLID ? Color.BROWN : Color.WHITE);
                gc.fillRect(x,y, CELL_SIDE, CELL_SIDE);
                x += CELL_SIDE;
            }
            x = 0;
            y += CELL_SIDE;
        }
        Collection<SubDungeon> subDungeons = level.getDungeon().getIdToSubDungeon().values();
        for (SubDungeon subDungeon : subDungeons) {
            double sdx = subDungeon.getX() * CELL_SIDE;
            double sdy = subDungeon.getY() * CELL_SIDE;
            double sdw = subDungeon.getWidth() * CELL_SIDE;
            double sdh = subDungeon.getHeight() * CELL_SIDE;
            gc.setStroke(Color.BLACK);
            gc.setFont(new Font(16));
            gc.strokeRect(sdx, sdy, sdw, sdh);
            gc.setFill(Color.BLACK);
            String text = String.valueOf(subDungeon.getId()) + "-";
            for(SubDungeon next: subDungeon.getNextSubDungeons().values()){
                text += next.getId();
            }
            gc.fillText(text, sdx + sdw / 2, sdy + sdh / 2);
        }
        System.out.println(level.getDungeon().getRootSubDungeonId());
    }

    private Integer subDungeonFromCell(int x, int y, Collection<SubDungeon> subDungeons){
        for (SubDungeon subDungeon : subDungeons) {
            if(subDungeon.contains(x, y)){
                return subDungeon.getId();
            }
        }
        return null;
    }

    private Map<Integer, Color> subDunIdToColor(Collection<SubDungeon> subDungeons){
        Map<Integer, Color> subDunIdToColor = new HashMap<>();
        float step = 1f / subDungeons.size();
        Color c = Color.color(1, 0, 0, 0.1f);
        for (SubDungeon subDungeon : subDungeons) {
            subDunIdToColor.put(subDungeon.getId(), c);
            c = Color.color(1, 0, 0, c.getOpacity() + step);
        }
        return subDunIdToColor;
    }
}
