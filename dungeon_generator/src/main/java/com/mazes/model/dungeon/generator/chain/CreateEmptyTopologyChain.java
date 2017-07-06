package com.mazes.model.dungeon.generator.chain;

public class CreateEmptyTopologyChain implements LevelConstructionChain {

    private LevelConstructionChain nextChain;

    public CreateEmptyTopologyChain(LevelConstructionChain nextChain) {
        this.nextChain = nextChain;
    }

    @Override
    public void process(LevelConstructionContext context) {
        int[][] topology = new int[context.levelHeight][context.levelWidth];
        for (int y = 0; y < topology.length; y++) {
            for (int x = 0; x < topology[y].length; x++) {
                topology[y][x] = 1;
            }
        }
        context.topology = topology;
        nextChain.process(context);
    }

}
