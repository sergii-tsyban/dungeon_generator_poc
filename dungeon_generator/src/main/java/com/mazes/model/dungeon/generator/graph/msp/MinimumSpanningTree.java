package com.mazes.model.dungeon.generator.graph.msp;

import com.mazes.model.dungeon.generator.graph.rng.Edge;

import java.util.*;

public class MinimumSpanningTree<T> {

    public List<Edge<T>> minimumSpanningTree(List<Edge<T>> edges){
        Collections.sort(edges);
        List<Edge<T>> result = new ArrayList<>();
        UnionFind<Object> unionFind = new UnionFind<>();

        for (Edge edge : edges) {
            unionFind.add(edge.from);
            unionFind.add(edge.to);
        }
        for (Edge edge : edges) {

            if (unionFind.recFind(edge.from) == unionFind.recFind(edge.to))
                continue;
            result.add(edge);
            unionFind.union(edge.from, edge.to);
        }

        return result;
    }

    private static final class Link<T> {
        public T parent;
        public int rank = 0;

        Link(T parent) {
            this.parent = parent;
        }
    }

    public static class UnionFind<T> {

        private final Map<T, Link<T>> elems = new HashMap<>();

        public UnionFind() {
            // Handled implicitly
        }

        public UnionFind(Collection<? extends T> elems) {
            for (T elem: elems)
                add(elem);
        }

        public boolean add(T elem) {
            if (elems.containsKey(elem))
                return false;

            elems.put(elem, new Link<>(elem));
            return true;
        }

        public T recFind(T elem) {
        /* Get the info on this object. */
            Link<T> info = elems.get(elem);

        /* If the element is its own parent, it's the representative of its
         * class and we should say so.
         */
            if (info.parent.equals(elem))
                return elem;

        /* Otherwise, look up the parent of this element, then compress the
         * path.
         */
            info.parent = recFind(info.parent);
            return info.parent;
        }

        public void union(T one, T two) {
        /* Get the link info for the parents.  This also handles the exception
         * guarantee.
         */
            Link<T> oneLink = elems.get(recFind(one));
            Link<T> twoLink = elems.get(recFind(two));

        /* If these are the same object, we're done. */
            if (oneLink == twoLink) return;

        /* Otherwise, link the two.  We'll do a union-by-rank, where the parent
         * with the lower rank will merge with the parent with higher rank.
         */
            if (oneLink.rank > twoLink.rank) {
            /* Since each parent must link to itself, the value of oneLink.parent
             * is the representative of one.
             */
                twoLink.parent = oneLink.parent;
            } else if (oneLink.rank < twoLink.rank) {
            /* Same logic as above. */
                oneLink.parent = twoLink.parent;
            } else {
            /* Arbitrarily wire one to be the parent of two. */
                twoLink.parent = oneLink.parent;

            /* Bump up the representative of one to the next rank. */
                oneLink.rank++;
            }
        }
    }


}
