package com.mazes.model.dungeon.generator.graph.rng;

public class Edge <T> implements Comparable<Edge>{

    public T from;
    public T to;
    public int weight;

    public Edge(T from, T to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    boolean isSameEdge(Edge another){
        return (this.from.equals(another.from) && this.to.equals(another.to)) ||
                (this.from.equals(another.to) && this.to.equals(another.from));
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (weight != edge.weight) return false;
        if (!from.equals(edge.from)) return false;
        return to.equals(edge.to);

    }

    @Override
    public int hashCode() {
        int result = from.hashCode();
        result = 31 * result + to.hashCode();
        result = 31 * result + weight;
        return result;
    }
}
