package com.zadanie;

/***
 * Klasa reprezentująca pojedyńczą krawędź grafu
 */
public class Edge {

        int vertex1,vertex2;
        int weight;

        public Edge(int vertex1, int vertex2, int weight) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.weight = weight;
        }

        public Edge(Edge e) {
            this.vertex1=e.vertex1;
            this.vertex2=e.vertex2;
            this.weight=e.weight;
        }

        @Override
        public String toString() {
            return vertex1 + " " + vertex2 + "[" + weight + "]";
        }

}
