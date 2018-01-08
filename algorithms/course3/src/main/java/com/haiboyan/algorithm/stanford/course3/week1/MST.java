package com.haiboyan.algorithm.stanford.course3.week1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;

public class MST {
    private static class Edge {
        int from;

        int to;

        int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        Edge(String from, String to, String weight) {
            this(Integer.parseInt(from), Integer.parseInt(to), Integer.parseInt(weight));
        }
    }

    private final Edge[] edges;

    /**
     *  Vertices number
     */
    private final int m;

    /**
     *  Edges number
     */
    private final int n;

    public MST(Edge[] edges, int m, int n) {
        this.edges = edges;
        this.m = m;
        this.n = n;
    }

    private int minCostMN() {
        HashSet<Integer> vertices = new HashSet<>();

        vertices.add(1);

        int minC = 0;

        while (vertices.size() < m) {
            int cm = Integer.MAX_VALUE;
            int vertex = -1;
            for (Edge edge : edges) {
                if (vertices.contains(edge.from) ^ vertices.contains(edge.to)) {
                    if (edge.weight < cm) {
                        cm = edge.weight;
                        vertex = vertices.contains(edge.from) ? edge.to : edge.from;
                    }
                }
            }
            vertices.add(vertex);
            System.out.printf("add vertex = %d, vertices.size() = %d\n", vertex, vertices.size());
            minC += cm;
        }
        return minC;
    }

    private int minCostMNLogN() {
        HashSet<Integer> integer = new HashSet<>();
        int minC = 0;
        for (Edge edge: edges) {

        }
        return minC;
    }

    private int minCostMPlusNLogN() {
        HashSet<Integer> integer = new HashSet<>();
        int minC = 0;
        for (Edge edge: edges) {

        }
        return minC;
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(args[0]));
        String[] mn = lines.get(0).split(" ");
        int m = Integer.parseInt(mn[0]), n = Integer.parseInt(mn[01]);
        Edge[] edges = lines.stream().skip(1).map(line -> line.split(" "))
                .map(el -> new Edge(el[0], el[1], el[2])).toArray(Edge[]::new);

        MST mst = new MST(edges, m, n);
        System.out.printf("Min cost = %d\n", mst.minCostMN());
    }
}
