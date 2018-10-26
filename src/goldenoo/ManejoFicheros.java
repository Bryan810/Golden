/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldenoo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ManejoFicheros {

    public static File archivo;
    public PrintWriter escribir;

    public void crearArchivo(String nombre,ArrayList<String> datos,ArrayList<String> estadisticas, ArrayList<String> comentarios) {
        archivo = new File(nombre + ".txt");

        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
//                escribir = new PrintWriter(archivo, "utf-8");
//                escribir.println(contenido);
//                escribir.close();
                BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));

                //DEPENDIENDO DEL TIPO DE OBJETOS QUE ESTE GUARDANDO DENTRO DEL ARRAYLIST, RECORRES EL 
                //ARRAY Y SEPARAS CADA ATRIBUTO POR TABULACION O COMO QUIERAS.
                //Y AL FINAL DE CADA LINEA HACES UN SALTO.
                for (int i = 0; i < datos.size(); i++) {
                    bw.write(datos.get(i)+estadisticas.get(i)+comentarios.get(i));
                }
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void guardar_datos_de_arraylist(ArrayList<String> datos) {

        try {
            //SE CREA UN OBJETO DE TIPO BUFFEREDWRITER PARA PODER ESCRIBIR DENTRO DEL ARCHIVO

            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));

            //DEPENDIENDO DEL TIPO DE OBJETOS QUE ESTE GUARDANDO DENTRO DEL ARRAYLIST, RECORRES EL 
            //ARRAY Y SEPARAS CADA ATRIBUTO POR TABULACION O COMO QUIERAS.
            //Y AL FINAL DE CADA LINEA HACES UN SALTO.
            for (int i = 0; i < datos.size(); i++) {
                bw.write(datos.get(i));
            }
            bw.close();
        } catch (Exception ex) {
            //Captura un posible error le imprime en pantalla   
            ex.printStackTrace();
        }
    }
}
