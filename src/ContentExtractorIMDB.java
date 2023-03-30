import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentExtractorIMDB implements ContentExtractor  {
    public List<Content> extractor(String json) {
         //extrair dados do json e atribuir em uma lista
        //var parser = new JsonParser();
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listAttribute = parser.parse(json);
        List<Content> contents = new ArrayList<>();

        for (Map<String, String> itemAttribute : listAttribute) {
            String title = itemAttribute.get("title");
            String urlImage = itemAttribute.get("image");
            Content content = new Content(title,urlImage);
            contents.add(content);
        }

        return contents;
    }
}
