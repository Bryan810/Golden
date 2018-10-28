/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldenoo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class Metodos {

    public static final String KEYGOLDEN = "AIzaSyBMHhfr4Crs6OvrV7nEnWWSF7bmRDHkgOg";
    public String nombreDelCanal;
    ManejoFicheros manFic = new ManejoFicheros();

    public void idsCanal(String idCanal) throws Exception {
        ArrayList<String> infoVideo = new ArrayList<>();
        ArrayList<String> estadisticas = new ArrayList<>();
        ArrayList<String> comentarios = new ArrayList<>();
        String url = "https://www.googleapis.com/youtube/v3/search?order=date&"
                + "part=id,snippet&fields=items(id(videoId),snippet(title,description,channelTitle))&"
                + "channelId=" + idCanal + "&maxResults=50&key=" + KEYGOLDEN;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
//        System.out.println("\nSending 'GET' request to URL : " + url);
//        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONObject myResponse = new JSONObject(response.toString());
        JSONArray jsonArray = myResponse.getJSONArray("items");
        JSONObject jsonA = jsonArray.getJSONObject(1);
        JSONObject tituloA = jsonA.getJSONObject("snippet");
        manFic.crearCarpetaArchivos();
        manFic.crearCarpeta(tituloA.getString("channelTitle"));
        nombreDelCanal = tituloA.getString("channelTitle");
        try {

            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject json = jsonArray.getJSONObject(i);
                    JSONObject titulo = json.getJSONObject("snippet");
                    JSONObject id = json.getJSONObject("id");
                    String contenido = "\r\n\t" + (i + 1) + "\r\n\tId:" + id.getString("videoId") + "\r\n\tTitulo: "
                            + titulo.getString("title") + "\r\n\tDescripción: "
                            + titulo.getString("description")
                            + "\r\n";

                    list.add(id.getString("videoId"));
                    infoVideo.add(contenido);
                    //System.out.println((char) 27 + "[34;43mVideo Número: " + (i + 1) + contenido);
//                    System.out.println("****************************************");
//                    manFic.crearCarpeta(titulo.getString("channelTitle"));
                    System.out.println(list.get(i) + " " + list.size() + " " + jsonArray.length());
//                    System.out.println("****************************************");
                    manFic.crearArchivoDatos("Ids"+titulo.getString("channelTitle"), nombreDelCanal, infoVideo);
                    comentarios = comentariosID(list.get(i));
                    estadisticas = estadisticasVideos(list.get(i));

                } catch (JSONException e) {
                    // e.printStackTrace();
                }
            }

        } catch (Exception e) {
            //e.printStackTrace();
        }

    }

    public ArrayList<String> estadisticasVideos(String idVideo) throws Exception {
        String a = "";
        String url = "https://www.googleapis.com/youtube/v3/videos?id=" + idVideo
                + "&key=" + KEYGOLDEN + "&part=snippet,statistics,contentDetails"
                + "&fields=items(snippet(publishedAt,%20thumbnails(medium(url)))"
                + ",statistics,contentDetails(caption))";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
//        System.out.println("\nSending 'GET' request to URL : " + url);
//        System.out.println("Response Code : " + responseCode);
        BufferedReader in1 = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in1.readLine()) != null) {
            response.append(inputLine);
        }
        in1.close();
        JSONObject myResponse = new JSONObject(response.toString());
//        System.out.println(response);
        JSONArray jsonArray = myResponse.getJSONArray("items");
        // System.out.println(jsonArray);
        //System.out.println((char) 27 + "[34;43mEstadisticas");
        ArrayList<String> estadisticasGrabar = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject json = jsonArray.getJSONObject(i);
                    JSONObject fechapublicacion = json.getJSONObject("snippet");
                    JSONObject contentDetails = json.getJSONObject("contentDetails");
                    JSONObject thumbnails = fechapublicacion.getJSONObject("thumbnails");
                    JSONObject urlImagen = thumbnails.getJSONObject("medium");
                    JSONObject estadisticasBasicas = json.getJSONObject("statistics");
                    if (contentDetails.getString("caption").equalsIgnoreCase("true")) {
                        //a = subtitulosVideos(idVideo);
                        a = "Si tiene Subtitulos";
                    } else {
                        a = "No tiene Subtitulos.";
                    }

                    String contenido = "\tID:" + idVideo + "\r\n" + "\tFecha Publicación: "
                            + fechapublicacion.getString("publishedAt")
                            + " \r\n\tURL Imagen: " + urlImagen.getString("url")
                            + " \r\n\tVistas: " + estadisticasBasicas.getString("viewCount")
                            + " \r\n\tLikes: " + estadisticasBasicas.getString("likeCount")
                            + " \r\n\tDislikes: " + estadisticasBasicas.getString("dislikeCount")
                            + " \r\n\tTotal Comentarios: " + estadisticasBasicas.getString("commentCount")
                            + " \r\n\tSubtitulos: " + a + "\r\n";

                    //System.out.println(contenido);
                    estadisticasGrabar.add(contenido);
                    manFic.crearArchivoEstadisticas("Estadisticas",nombreDelCanal, estadisticasGrabar);
                } catch (Exception e) {
                    //  e.printStackTrace();
                }

            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return estadisticasGrabar;
    }

    public ArrayList<String> comentariosID(String idActual) throws Exception {
        ArrayList<String> comentarios = new ArrayList<>();
        String contenido = "";
        String url = "https://www.googleapis.com/youtube/v3/commentThreads?"
                + "part=snippet&fields="
                + "items(snippet/topLevelComment/snippet(textDisplay,likeCount))"
                + "&videoId=" + idActual
                + "&maxResults=100"
                + "&key=" + KEYGOLDEN;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        BufferedReader in1 = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in1.readLine()) != null) {
            response.append(inputLine);
        }
        in1.close();
        JSONObject myResponse = new JSONObject(response.toString());
        JSONArray jsonArray = myResponse.getJSONArray("items");
        System.out.println((char) 27 + "[34;43mComentarios\r\n\t");
        for (int j = 0; j < jsonArray.length(); j++) {
            try {
                JSONObject json = jsonArray.getJSONObject(j);
                JSONObject snippet = json.getJSONObject("snippet");
                JSONObject topLevelComment = snippet.getJSONObject("topLevelComment");
                JSONObject snippet1 = topLevelComment.getJSONObject("snippet");

                contenido = (j + 1) + "\r\n" + idActual + "\r\n" + snippet1.getString("textDisplay") + "\r\n"
                        + "Likes: " + snippet1.getInt("likeCount") + "\r\n";
                comentarios.add(contenido);
                //System.out.println(contenido);
                manFic.crearArchivoComentarios("Comentarios De Video" + idActual,nombreDelCanal, comentarios);

            } catch (Exception e) {
                //e.printStackTrace();
            }

        }

        return comentarios;
    }

    public String subtitulosVideos(String idVideoSub) throws Exception {
        String contenido = "";
        ArrayList<String> contenidoSubtitulos = new ArrayList<>();
        String url = "http://video.google.com/timedtext?lang=es&v=" + idVideoSub;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
//        System.out.println("\nSending 'GET' request to URL : " + url);
//        System.out.println("Response Code : " + responseCode);
        BufferedReader in1 = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in1.readLine()) != null) {
            response.append(inputLine);
        }
        in1.close();
        //System.out.println(response);
        JSONObject xmlJSONObj = XML.toJSONObject(response.toString());
        JSONObject jsonObj = xmlJSONObj.getJSONObject("transcript");
        JSONArray jsonArray = jsonObj.getJSONArray("text");
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                contenido = "" + jsonArray.getJSONObject(i).getString("content");
                //contenidoSubtitulos.add(""+jsonArray.getJSONObject(i).getString("content"));
                //System.out.println(contenido);
            }
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return contenido;
    }

}
