package com.zadanie;

import java.util.List;

/***
 * Klasa implementująca sortowanie krawędzi ze względu na ich wagi, z wykorzystaniem algorytmu QuickSort
 */
public class Sort {
    private static void swap(List<Edge> array,int a, int b){
        Edge tmp = new Edge(array.get(a));
        array.set(a, array.get(b));
        array.set(b,tmp);
    }

    public static void sort(List<Edge> array,int startIndex, int stopIndex){
        if(startIndex>=stopIndex)
            return;

        int pivot = array.get(stopIndex).weight;
        int sorted=startIndex-1;

        for(int i=startIndex;i<stopIndex;i++){
            if(array.get(i).weight<pivot){
                sorted++;
                swap(array,i,sorted);
            }
        }

        swap(array,++sorted,stopIndex);
        sort(array,startIndex,sorted-1);
        sort(array, sorted+1, stopIndex);
    }

    public static void sort(List<Edge> array){
        sort(array,0, array.size()-1);
    }


}
