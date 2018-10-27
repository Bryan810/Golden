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

    public File archivo;
    public PrintWriter escribir;

    public void crearArchivoDatos(String nombre, ArrayList<String> datos) {
        archivo = new File(nombre.replaceAll("\\s", "") + ".txt");

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
                for (int i = 1; i < datos.size(); i++) {
                    bw.write(datos.get(i));
                }
                bw.close();
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }

    }

    public void crearArchivoComentarios(String nombre, ArrayList<String> comentarios) {
        archivo = new File(nombre.replaceAll("\\s", "") + ".txt");

        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true));

                //DEPENDIENDO DEL TIPO DE OBJETOS QUE ESTE GUARDANDO DENTRO DEL ARRAYLIST, RECORRES EL 
                //ARRAY Y SEPARAS CADA ATRIBUTO POR TABULACION O COMO QUIERAS.
                //Y AL FINAL DE CADA LINEA HACES UN SALTO.
                for (int i = 0; i < comentarios.size(); i++) {
                    bw.newLine();
                    bw.write(comentarios.get(i));

                }

                bw.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void crearArchivoEstadisticas(String nombre, ArrayList<String> estadisticas) {
        archivo = new File(nombre.replaceAll("\\s", "") + ".txt");

        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true));
                //DEPENDIENDO DEL TIPO DE OBJETOS QUE ESTE GUARDANDO DENTRO DEL ARRAYLIST, RECORRES EL 
                //ARRAY Y SEPARAS CADA ATRIBUTO POR TABULACION O COMO QUIERAS.
                //Y AL FINAL DE CADA LINEA HACES UN SALTO.
                for (int i = 0; i < estadisticas.size(); i++) {
                    bw.write(estadisticas.get(i));
                }
                bw.newLine();
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
