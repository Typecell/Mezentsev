import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LinkManager {
    private Document document;
    private Elements links;

    public Elements getLinks() {
        return links;
    }

    public void connect(String link) throws IOException {
        document = Jsoup.connect(link).get();
    }

    public void getElementsByTag(String tag) {
        links = document.getElementsByTag(tag);
    }

    public void selectTags(String tag) {
        links = links.select(tag);
    }

    public void getElementsByClass(String clazz) {
        links = document.getElementsByClass(clazz);
    }

    public List<String> createListByAttribute(String attr) {
        return links.eachAttr(attr);
    }

    public String getPrecorArticul(String link) {
        String[] arr = link.split("/");
        return arr[arr.length-1];
    }

    public List<String> sortLinks(List<String> list, String condition) {
        List<String> result = new ArrayList<>();
        for (String link : list) {
            if (link.contains(condition))
                result.add(link);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {

    }
}
