import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

// Executar após rodar o stickers-api, projeto faz a extração de acordo com o endpoint, serviço api local ou nasa por exemplo.
public class App {

  public static void main(String[] args) throws Exception {
    // System.out.println(body);
    // extrair os dados

    //String url = "https://api.nasa.gov/planetary/apod?api_key=VyXkKsAF08PROugvEhpwQbvC3nADaOcGp5GRRrSb&start_date=2023-03-01&end_date=2023-03-04";

    // extrair conteúdo do json e popular em uma lista
    // String url = "https://api.nasa.gov/planetary/apod?api_key=VyXkKsAF08PROugvEhpwQbvC3nADaOcGp5GRRrSb&start_date=2023-03-01&end_date=2023-03-04";
    // ContentExtractor extractor = new ContentExtractorNASA();

    String url = "http://localhost:8080/languages";
    ContentExtractor extractor = new ContentExtractorIMDB();

    //String url = "IMDB";
    //ContentExtractor extractor = new ContentExtractorIMDB();
    var http = new ClientHttp();
    String json = http.getData(url);
    List<Content> contents = extractor.extractor(json);

    // System.out.println(listCard);
    System.out.println(contents.size());
    for (Content item : contents) {
      String title = item.getTitulo();
      String urlImage = item.getUrlImagem();

      InputStream inputStream = new URL(urlImage).openStream();
      String nameFile = title + ".png";

      GeneratorStickers generatorStickers = new GeneratorStickers();
      generatorStickers.create(inputStream, nameFile, "Extrator de volta!");

      System.out.println(title);
      System.out.println(urlImage);
      System.out.println();
    }
  }
}
