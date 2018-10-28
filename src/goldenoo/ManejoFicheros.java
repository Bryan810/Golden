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

    public void crearArchivoDatos(String nombreArchivo,String nombrePath, ArrayList<String> datos) {
        crearArchivo(nombreArchivo, nombrePath);
        try {
//                escribir = new PrintWriter(archivo, "utf-8");
//                escribir.println(contenido);
//                escribir.close();
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));

            //DEPENDIENDO DEL TIPO DE OBJETOS QUE ESTE GUARDANDO DENTRO DEL ARRAYLIST, RECORRES EL 
            //ARRAY Y SEPARAS CADA ATRIBUTO POR TABULACION O COMO QUIERAS.
            //Y AL FINAL DE CADA LINEA HACES UN SALTO.
            for (int i = 0; i < datos.size(); i++) {
                bw.write(datos.get(i));
            }
            bw.close();
        } catch (Exception e) {
            //e.printStackTrace();
        }

    }

    public void crearArchivoEstadisticas(String nombreArchivo,String nombrePath, ArrayList<String> estadisticas) {
        crearArchivo(nombreArchivo, nombrePath);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true));
            for (int i = 0; i < estadisticas.size(); i++) {

                bw.write(estadisticas.get(i));
                System.out.println("Grabe: " + estadisticas.get(i));
            }
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void crearArchivoComentarios(String nombreArchivo,String nombrePath, ArrayList<String> comentarios) {
        crearArchivo(nombreArchivo, nombrePath);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));

            //DEPENDIENDO DEL TIPO DE OBJETOS QUE ESTE GUARDANDO DENTRO DEL ARRAYLIST, RECORRES EL 
            //ARRAY Y SEPARAS CADA ATRIBUTO POR TABULACION O COMO QUIERAS.
            //Y AL FINAL DE CADA LINEA HACES UN SALTO.
            for (int i = 0; i < comentarios.size(); i++) {
                bw.write(comentarios.get(i));

            }
            bw.newLine();
            bw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void crearArchivo(String nombre, String path) {
        archivo = new File(System.getProperty("user.home") 
                + "\\GoldenYoutube" + "\\" + path.replaceAll("\\s", "") 
                + "\\" + nombre.replaceAll("\\s", "") + ".txt");
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

   

    public void crearCarpeta(String nombreCarpeta) {
        File directorio = new File(System.getProperty("user.home") 
                + "\\GoldenYoutube" + "\\" 
                + nombreCarpeta.replaceAll("\\s", ""));
        directorio.mkdir();
//        System.out.println(System.getProperty("user.home"));
//        return directorio.getPath();
    }

    public void crearCarpetaArchivos() {
        File directorio = new File(System.getProperty("user.home") 
                + "\\GoldenYoutube");
        directorio.mkdir();

    }
}
