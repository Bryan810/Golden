/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldenoo;

import java.io.File;
import java.io.PrintWriter;

public class ManejoFicheros {

    File archivo;
    PrintWriter escribir;

    public void crearArchivo(String nombre, String contenido) {
        archivo = new File(nombre + ".txt");
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                escribir = new PrintWriter(archivo, "utf-8");
                escribir.print(contenido);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
