package com.zadanie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class z1 {

    /***
     * Klasa reprezentująca przedmiot, który można włożyć do plecaka
     * Przechowuje jego numer, wartość i wagę.
     */
    static class item{
        int id;
        int value;
        int weight;

        public item(int id, int value, int weight) {
            this.id = id;
            this.value = value;
            this.weight = weight;
        }
    }

    public static int[][] knapsack(item items[], int maxCapacity, PrintWriter out){
        int valSum[][]=new int[maxCapacity+1][items.length+1];
        int picked[][]=new int[maxCapacity+1][items.length+1];
        int aux;

        for(int curItem=1;curItem<=items.length;curItem++){
            for(int curWeight=1;curWeight<=maxCapacity;curWeight++){

                //Jeśli waga przedmiotu nie jest większa od aktualnej pojemności plecaka...
                if(curWeight-items[curItem-1].weight>=0){
                    //obliczam potencjalną wartość plecaka, dla danego przedmiotu
                    aux = valSum[curWeight-items[curItem-1].weight][curItem-1] + items[curItem-1].value;
                    //jeśli ta wartość jest niemniejsza od wartości dla poprzedniej wagi, to ta wartość jest wpisana do tablicy
                    if(aux>=valSum[curWeight][curItem-1]) { valSum[curWeight][curItem] = aux; picked[curWeight][curItem]=1; }
                    //w przeciwnym wypdaku wpisuje wartość z poprzedniego przedmiotu dla tej samej wagi plecaka.
                    else valSum[curWeight][curItem] = valSum[curWeight][curItem-1];
                }else{
                    //Jeśli waga przedmiotu jest większa od aktualnej wagi plecaka to wpisuje wartość plecaka skopiowaną z poprzedniego przedmiotu dla tej samej wagi plecaka.
                    valSum[curWeight][curItem] =valSum[curWeight][curItem-1];
                }
            }
        }

        //Sprawdzam jaka jest możliwa max wartość plecaka
        int maxValue = 0;
        for(int i = 1;i<=items.length;i++){
            if(maxValue<valSum[maxCapacity][i]) maxValue = valSum[maxCapacity][i];
        }

        int tmp,curWeight, curItem;
        List<item> solve = new ArrayList<>();

        //iteruje po ostatnum wierszu tablicy reprezuntującej możliwe wartości plecaka
        for(int i = 1;i<=items.length;i++){
            tmp = maxValue;
            curWeight = maxCapacity;
            curItem=i;
            //jesli ta wartość jest = maxValue oraz przedmiot został wzięty to, tzn. że to jest jedno z rozwiązań,
            if(maxValue==valSum[maxCapacity][i]&&picked[curWeight][curItem]==1){
                //czyszczę listę przedmiotów
                solve.clear();

                while(tmp!=0&&curItem!=0){
                    //Jeśli przedmiot wchodzi w skład plecaka, to wrzucam go do listy przedmiotów, i zaczynam przeglądanie poprzednich przedmiotów
                    if(picked[curWeight][curItem]==1){

                        solve.add(items[curItem-1]);
                        curWeight -=items[curItem-1].weight;
                        tmp -=items[curItem-1].value;
                        curItem--;

                        //"przechodzę" w lewo po tablicy, aż nie trafię na jedynkę
                        while(picked[curWeight][curItem]!=1&&curItem!=0) curItem--;
                    }
                    else curWeight--;
                }

                for(item a:solve)
                    out.print(a.id + " ");
                out.println();
            }
        }
        return valSum;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in1 = new Scanner(new File("In0302.txt"));

        PrintWriter out1 = new PrintWriter("Out0302.txt");
        int n = in1.nextInt();
        int maxWeight = in1.nextInt();
        int value;
        int weight;

        item items[] = new item[n];

        for(int i =0;i<n;i++){
            value=in1.nextInt();
            weight=in1.nextInt();
            items[i] = new item(i+1,value,weight);
        }

        int[][] test = knapsack(items,maxWeight,out1);

        /*
        for(int i=0;i<test.length;i++){
            for(int j =0; j< test[0].length;j++){
                System.out.print(test[i][j]+ " ");
            }
            System.out.println("\n");
        }
         */
        out1.close();

    }


}
