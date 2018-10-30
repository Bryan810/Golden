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

    private File archivo;   //Variable del tipo FILE que usaremos para generar los txt
    private PrintWriter escribir;

    /**
     * Crea una carpeta "Global" en la que se almacenará toda la información que
     * se extraiga mediante el consumo de la API, dentro de esta carpeta se
     * generan carpetas con nombres acorde al canal al que se analiza.
     *
     */
    public void crearCarpetaArchivos() {
        File directorio = new File(System.getProperty("user.home")
                + "\\GoldenYoutube");
        directorio.mkdir();
    }

    /**
     * Crea una carpeta con el nombre del canal al que se realiza la extracción
     * de datos.
     *
     * @param nombreCarpeta parametro que da el nombre que tendrá la carpeta se
     * aconseja usar el mismo nombre del canal al que se esta analizando la
     * información
     *
     */
    public void crearCarpeta(String nombreCarpeta) {
        File directorio = new File(System.getProperty("user.home")
                + "\\GoldenYoutube" + "\\"
                + nombreCarpeta.replaceAll("\\s", ""));
        directorio.mkdir();
    }

    /**
     * Método general para la verificación de existencia de archivos; Si el
     * archivo a crear existe se lo almacena en un path
     * C:\\Users\\Usuario\\GoldenYoutube el cual consta de la carpeta "global"
     * con nombre GoldenYouTube creada en el método crearCarpetaArchivos() caso
     * contrario se crea el archivo en el path previamente descrito.
     *
     * @param nombre parámetro que da el nombre que tendrá el archivo de datos.
     * @param path parámetro que establecerá el path en el que se almacenará el
     * archivo generado, se recomienda que este parametro sea el nombre del
     * canal.
     *
     */
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

    /**
     * Método que escribe la información relacionada a los datos consumidos del
     * API en disco duro, esto previamente realizada la comprobacion de que
     * dichos archivos existan y se pueda realizar su escritura.
     *
     * @param nombreArchivo parámetro que da el nombre que tendrá el archivo de
     * datos
     * @param nombrePath parámetro que establecerá el path en el que se
     * almacenará el archivo generado, se recomienda que este parametro sea el
     * nombre del canal.
     * @param datos Colecciòn de datos en la que se encuentra almacenada la
     * informaciòn a ser grabada.
     */
    public void crearArchivoDatos(String nombreArchivo, String nombrePath, ArrayList<String> datos) {
        crearArchivo(nombreArchivo, nombrePath);
        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
            for (int i = 0; i < datos.size(); i++) {
                bw.write(datos.get(i));
            }
            bw.close();
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    /**
     * Método que escribe la información relacionada a los datos consumidos del
     * API en disco duro, esto previamente realizada la comprobacion de que
     * dichos archivos existan y se pueda realizar su escritura.
     *
     * @param nombreArchivo parámetro que da el nombre que tendrá el archivo de
     * datos
     * @param nombrePath parámetro que establecerá el path en el que se
     * almacenará el archivo generado, se recomienda que este parametro sea el
     * nombre del canal.
     * @param estadisticas Colecciòn de datos en la que se encuentra almacenada
     * la informaciòn a ser grabada.
     */
    public void crearArchivoEstadisticas(String nombreArchivo, String nombrePath, ArrayList<String> estadisticas) {
        crearArchivo(nombreArchivo, nombrePath);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true));
            for (int i = 0; i < estadisticas.size(); i++) {
                bw.write(estadisticas.get(i));
//                System.out.println("Grabe: " + estadisticas.get(i));
            }
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que escribe la información relacionada a los datos consumidos del
     * API en disco duro, esto previamente realizada la comprobacion de que
     * dichos archivos existan y se pueda realizar su escritura.
     *
     * @param nombreArchivo parámetro que da el nombre que tendrá el archivo de
     * datos
     * @param nombrePath parámetro que establecerá el path en el que se
     * almacenará el archivo generado, se recomienda que este parametro sea el
     * nombre del canal.
     * @param comentarios Colecciòn de datos en la que se encuentra almacenada
     * la informaciòn a ser grabada.
     */
    public void crearArchivoComentarios(String nombreArchivo, String nombrePath, ArrayList<String> comentarios) {
        crearArchivo(nombreArchivo, nombrePath);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
            for (int i = 0; i < comentarios.size(); i++) {
                bw.write(comentarios.get(i));
            }
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
