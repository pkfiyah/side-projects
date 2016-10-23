package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by mitchellrichardson on 10/23/16.
 */
public class MovieDatabaseController {
    private final static String apiKey = "0a01de674fee96aca5f51fa50f3ab897";
    private String query;

    public MovieDatabaseController() {

    }

    public static String searchForMovie(String movieName) {
        HttpURLConnection connection = null;
        StringBuilder result = new StringBuilder();
        //Clean string before making query
        movieName = movieName.trim();
        movieName = movieName.replace(" ", "%20");

        //TMDb base url for Movie Search GET request
        String baseUrl = "https://api.themoviedb.org/3/search/movie?api_key=" + apiKey;
        String language = "&langauge=en-US";
        String movie = "&query=" + movieName;
        try {
            URL url = new URL(baseUrl + language + movie);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type","application/json;charset=utf-8");
            connection.setUseCaches(false);
            connection.setDoOutput(true);

            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            System.out.println(result.toString());

            return result.toString();
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL Error: " + e.getMessage());
        } catch (ProtocolException e) {
            System.out.println("Protocol Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Error: " + e.getMessage());
        } finally {
            return "";
        }
    }

    public static 
}
