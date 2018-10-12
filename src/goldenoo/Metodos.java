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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Metodos {

    public static final String KEYGOLDEN = "AIzaSyBMHhfr4Crs6OvrV7nEnWWSF7bmRDHkgOg";

    public void idsCanal(String idCanal) throws Exception {

        String url = "https://www.googleapis.com/youtube/v3/search?order=date&"
                + "part=id,snippet&fields=items(id(videoId),snippet(title,%20description))&"
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
        try {

            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < jsonArray.length() - 1; i++) {
                try {
                    JSONObject json = jsonArray.getJSONObject(i);
                    JSONObject id = json.getJSONObject("id");
                    JSONObject titulo = json.getJSONObject("snippet");
                    String contenido = id.getString("videoId") + " | = " + titulo.getString("title") + ".";
                    list.add(contenido);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < list.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + list.get(i));
                //System.out.println(list.get(i));
            }

            ArrayList<String> id = new ArrayList<>();
            System.out.println("Ids de videos del canal");
            for (int i = 0; i < list.size(); i++) {

                id.add(list.get(i).substring(0, 10));
                System.out.println(id.get(i));

            }

        } catch (Exception e) {
        }

    }

    public void estadisticasVideos(String idVideo) throws Exception {

        String url = "https://www.googleapis.com/youtube/v3/videos?id=8pkQv5V2MEc&key=" + KEYGOLDEN + "&part=snippet,statistics&fields=items(snippet(publishedAt),statistics)";
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
        System.out.println(jsonArray);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);
            JSONObject fechapublicacion = json.getJSONObject("snippet");
            JSONObject estadisticasBasicas = json.getJSONObject("statistics");
            String contenido = "Fecha Publicación: "
                    + fechapublicacion.getString("publishedAt")
                    + " \nVistas: " + estadisticasBasicas.getString("viewCount")
                    + " \nLikes: " + estadisticasBasicas.getString("likeCount")
                    + " \nDislikes: " + estadisticasBasicas.getString("dislikeCount")
                    + " \nTotal Comentarios: " + estadisticasBasicas.getString("commentCount");
            System.out.println(contenido);
        }
    }

}
