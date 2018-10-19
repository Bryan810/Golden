/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldenoo;

import java.util.Scanner;

/**
 *
 * @author Bryan
 */
public class GoldenOO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Metodos met = new Metodos();
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Ingrese Id= ");
            String idCanal = sc.next();
            //met.idsCanal(idCanal);
            met.subtitulosVideos(idCanal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
