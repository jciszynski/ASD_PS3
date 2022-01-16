package com.zadanie;
import com.zadanie.Graph;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class z2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in1 = new Scanner(new File("In0304.txt"));
        PrintWriter out1 = new PrintWriter("Out0304.txt");

        int n = in1.nextInt();
        in1.useDelimiter("\n");
        int tab[][] = new int[n+1][n+1];
        String tmp="";
        String tmp2[];
        in1.nextLine();
        for(int i=1;i<n+1;i++){
            tmp = in1.nextLine();
            tmp2=tmp.split(" ");
            for(int j=0;j<tmp2.length;j+=2){
                tab[i][Integer.parseInt(tmp2[j])]=Integer.parseInt(tmp2[j+1]);
            }
        }

        Graph graf = new Graph(tab,n);
        Graph mst = graf.MST(4);
        out1.println(mst.print());
        out1.println(mst.sumOfWeights());

        out1.close();
        in1.close();
    }
}
