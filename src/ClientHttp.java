import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ClientHttp {

    public String getData(String url) {

        try {
            URI endUri = URI.create(url);
            var client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(endUri).GET().build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            String body = response.body();
            return body;
        } catch (IOException | InterruptedException ex) {
            // TODO: handle exception
            throw new RuntimeException(ex);
        }

    }
}
