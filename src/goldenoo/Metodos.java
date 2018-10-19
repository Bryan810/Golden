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

    public void idsCanal(String idCanal) throws Exception {

        String url = "https://www.googleapis.com/youtube/v3/search?order=date&"
                + "part=id,snippet&fields=items(id(videoId),snippet(title,%20description))&"
                + "channelId=" + idCanal + "&maxResults=50&key=" + KEYGOLDEN;
        ArrayList<String> idVideos = new ArrayList<>();
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
        try {

            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < jsonArray.length() - 1; i++) {
                try {
                    JSONObject json = jsonArray.getJSONObject(i);
                    JSONObject titulo = json.getJSONObject("snippet");
                    JSONObject id = json.getJSONObject("id");
                    String contenido = id.getString("videoId") + "\n\tTitulo: "
                            + titulo.getString("title") + "\n\tDescripción: "
                            + titulo.getString("description") + ".";
                    list.add(contenido);

                } catch (JSONException e) {
                    //e.printStackTrace();
                }
            }

            for (int i = 0; i < list.size(); i++) {
                System.out.println((char) 27 + "[34;43mVideo Número: "//se agrega aqui el salto de pagina y tabulacion junto con
                        + (i + 1) + "\n\tId:" //etiqueta id para no afectar el substring(0 - 11) que extrae solo el
                        + list.get(i));             //id
                idVideos.add(list.get(i).substring(0, 11));
                estadisticasVideos(idVideos.get(i));
                comentariosID(idVideos.get(i));
//                System.out.println("[" + (i + 1) + "]" + idVideos.get(i));//Imprimo solo los ids de los videos
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
//        for (int i = 0; i < idVideos.size(); i++) {
//            comentariosID(idVideos.get(i));
//        }

        //return idVideos;
    }

    public void comentariosID(String idActual) throws Exception {

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
        System.out.println((char) 27 + "[34;43mComentarios");
        try {
            for (int j = 0; j < jsonArray.length(); j++) {
                JSONObject json = jsonArray.getJSONObject(j);
                JSONObject snippet = json.getJSONObject("snippet");
                JSONObject topLevelComment = snippet.getJSONObject("topLevelComment");
                JSONObject snippet1 = topLevelComment.getJSONObject("snippet");
                String contenido = "[" + (j + 1) + "]"
                        + snippet1.getString("textDisplay")
                        + "\n\tLikes: " + snippet1.getInt("likeCount");
                System.out.println("\t" + contenido);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void estadisticasVideos(String idVideo) throws Exception {
        String url = "https://www.googleapis.com/youtube/v3/videos?id=" + idVideo
                + "&key=" + KEYGOLDEN + "&part=snippet,statistics"
                + "&fields=items(snippet(publishedAt),statistics)";
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
        System.out.println((char) 27 + "[34;43mEstadisticas");
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                JSONObject fechapublicacion = json.getJSONObject("snippet");
                JSONObject estadisticasBasicas = json.getJSONObject("statistics");
                String contenido = "\tFecha Publicación: "
                        + fechapublicacion.getString("publishedAt")
                        + " \n\tVistas: " + estadisticasBasicas.getString("viewCount")
                        + " \n\tLikes: " + estadisticasBasicas.getString("likeCount")
                        + " \n\tDislikes: " + estadisticasBasicas.getString("dislikeCount")
                        + " \n\tTotal Comentarios: " + estadisticasBasicas.getString("commentCount");
                System.out.println(contenido);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void subtitulosVideos(String idVideoSub) throws Exception {
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
                String contenido = "" + jsonArray.getJSONObject(i).getString("content");
                //contenidoSubtitulos.add(""+jsonArray.getJSONObject(i).getString("content"));
                System.out.println(contenido);
            }
        } catch (Exception e) {

        }

    }
}
