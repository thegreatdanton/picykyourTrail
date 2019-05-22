package code;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MovieTitles {

    public static void main(String[] args){
        MovieTitles movieTitles = new MovieTitles();
        Scanner in = new Scanner(System.in);
        List<String> movies;
        String input;
        try {
            System.out.println("Enter keyword to search movie names");
            input = in.nextLine();
        } catch (Exception e) {
            input = null;
        }

        movies = movieTitles.getMovieTitles(input);
        System.out.println("----------Movies List:----------");
        if(movies.size() != 0){
            for(String movie: movies){
                System.out.println(movie);
            }
        }
    }

    public List<String> getMovieTitles(String input){
        List<String> titles = new ArrayList<>();
        String response;
        int startPage = 1;
        int totalPages = Integer.MAX_VALUE;
        JsonParser jsonParser = new JsonParser();
        while (startPage <= totalPages) {
            try {
                URL obj = new URL("https://jsonmock.hackerrank.com/api/movies/search/?Title=" + input + "&page=" + startPage);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                while ((response = in.readLine()) != null) {
                    JsonObject convertedObject = (JsonObject) jsonParser.parse(response);
                    totalPages = convertedObject.get("total_pages").getAsInt();
                    JsonArray data = convertedObject.getAsJsonArray("data");
                    for (int i = 0; i < data.size(); i++) {
                        String title = data.get(i).getAsJsonObject().get("Title").getAsString();
                        titles.add(title);
                    }
                }
                in.close();
                startPage++;
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        Collections.sort(titles);
        return titles;
    }
}
