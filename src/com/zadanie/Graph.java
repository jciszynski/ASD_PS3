package com.zadanie;

import java.util.ArrayList;

/***
 * Klasa reprezentująca graf niezorientowany
 */
public class Graph {
    int numOfVertex;
    ArrayList<Edge> edgeArray;

    /***
     * Konstruktor tworzący graf na podsawie podanej macierzy sąsiedztwa wierzchołków
     * @param tab Macierz sąsiedztwa wierzchołków
     * @param numOfVertex Liczba wierzchołków
     */
    public Graph(int tab[][],int numOfVertex){
        edgeArray = new ArrayList<>();
        this.numOfVertex = numOfVertex;
        for(int i=0;i<tab.length;i++){
            for(int j=0;j<tab[0].length;j++){
                if(tab[i][j]!=0&&i<=j)
                    edgeArray.add(new Edge(i,j,tab[i][j]));
            }
        }
    }

    /***
     * Konstruktor tworzący pusty graf niezorientowany
     */
    public Graph(){
        edgeArray = new ArrayList<>();
    }

    /***
     * Dodaje krawędź do istniejącego grafu, jednocześnie sprawdza czy dodana krawedź nie zawiera nowego wierzchołka
     * @param e Krawędź do dodania
     */
    public void addEdge(Edge e){
        edgeArray.add(new Edge(e));
        updateNumOfVertex();
    }

    private void updateNumOfVertex(){
        ArrayList<Integer> v= new ArrayList<>();
        for(Edge e:edgeArray){
            v.add(e.vertex1);
            v.add(e.vertex2);
        }
        numOfVertex = (int)v.stream().distinct().count();
    }

    /***
     * Oblicza sumę wag krawędzi grafu
     * @return Suma wag krawędzi
     */
    public int sumOfWeights(){
        int sum=0;
        for(Edge e:edgeArray)
            sum+=e.weight;
        return sum;
    }

    /***
     * Zwraca wszystkie krawędzi grafu w postacie Stringa, zgodnie ze specyfikacją zadania
     * @return
     */
    public String print(){
        String s = "";
        for(Edge e: edgeArray){
            s = s + e + ", ";
        }
        return s;
    }

    /***
     * Generuje nowy podgraf, będący Minimalnym drzewem rozpinającym grafu z wykorzystaniem algorytmu Jarnika-Prima
     * @param startVertex Wierchołek od którego jest generowane drzewo
     * @return Podgraf będący MST grafu
     */
    public Graph MST(int startVertex){
        //Tworzę liste Q będącą kopią listy krawędzi grafu i sortuje ją względem wag krawędzi
        ArrayList<Edge> Q = new ArrayList<>(edgeArray);
        Sort.sort(Q);

        //Tworzę pusty graf, do którego będę dodawał składowe krawędzie drzewa rozpinającego
        Graph MST = new Graph();
        //Lista odwiedzonych wierzchołków, na początku dodaje startowy wierzchołek
        ArrayList<Integer> visited = new ArrayList<>();
        visited.add(startVertex);


        for(int i=1;i<numOfVertex;i++){
            for(int j=0;j<Q.size();j++){
                //Jeśli aktualna krawędź posiada dokładnie jeden wierzchołek należący do zbioru odwiedzonych wierzchołków, to dodaje go do MDR
                if(visited.contains(Q.get(j).vertex1) ^ visited.contains(Q.get(j).vertex2)){
                    //Dodaje oznaczone wierzchołki
                    visited.add(Q.get(j).vertex2);
                    visited.add(Q.get(j).vertex1);
                    MST.addEdge(Q.get(j));
                    //wyrzucam krawędź z listy.
                    Q.remove(j);
                    //Przerywam wykonywanie pętli zagnieżdzonej - zaczynam przeszukiwanie krawędzi od początku
                    break;
                }
            }
        }

        return MST;
    }
}
