import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endUri = URI.create(url);
        var client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endUri).GET().build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        // System.out.println(body);

        var parser = new JsonParser();
        List<Map<String, String>> listMovie = parser.parse(body);

        // System.out.println(listCard);

        System.out.println(listMovie.size());
        System.out.println(listMovie.get(0));

        for (Map<String, String> movie : listMovie) {
            var urlImage = movie.get("image");
            var title = movie.get("title");
            InputStream inputStream = new URL(urlImage).openStream();

            String nameFile = title + ".png";

            var generatorStickers = new GeneratorStickers();
            generatorStickers.create(inputStream, nameFile);

            System.out.println(title);
            System.out.println(urlImage);
            System.out.println(movie.get("imDbRating"));
            System.out.println();
        }
    }
}
