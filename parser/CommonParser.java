package parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class CommonParser {
    private String url;
    private String sourcePath;
    private Document document;
    private Elements links;

    public Elements getLinks() {
        return links;
    }

    public void setUrl(String url) throws IOException {
        this.url = url;
        createConnection();
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    private void createConnection() throws IOException {
        document = Jsoup.connect(url).get();
    }

    public void createDirectory(String dirName) {
        File dir = new File(sourcePath + dirName);
        dir.mkdir();
    }

    public void getElementsByClass(String clazz) {
        links = document.getElementsByClass(clazz);
    }

    public void getElementsByTag(String tag) {
        links = document.getElementsByTag(tag);
    }

    public void selectTags(String tag) {
        links = links.select(tag);
    }

    public void printCode() {
        System.out.println(links);
    }

    public List<String> createListByAttribute(String attr) {
        return links.eachAttr(attr);
    }

    public List<String> createListWithText() {
        return links.eachText();
    }

    public void downloadImage(String urlPath, String resultPath) {
        try {
            URL url = new URL(urlPath);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);

            BufferedInputStream inputStream = new BufferedInputStream(connection.getInputStream());
            FileOutputStream fileOutputStream = new FileOutputStream(new File(resultPath));

            fileOutputStream.write(inputStream.readAllBytes());

            fileOutputStream.flush();
            inputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> sortLinks(List<String> list, String condition) {
        List<String> result = new ArrayList<>();
        for (String link : list) {
            if (link.contains(condition))
                result.add(link);
        }
        return result;
    }

    public void disconnect() {
        document = null;
        links = null;
    }

    public static void main(String[] args) throws IOException {
        CommonParser foremanParser = new CommonParser();
        foremanParser.setUrl("http://www.foremanfitness.ru/catalog/silovye-trenazhery/nagruzhaemye-diskami/fp-805-mashina-smita-naklonnaya/");

        foremanParser.getElementsByClass("preview-text static-content js-static-content");
        foremanParser.selectTags("p");
        foremanParser.printCode();

        foremanParser.getElementsByClass("static-content js-static-content detail-text");
        foremanParser.selectTags("ul");

        String line = foremanParser.getLinks().toString();
        line = line.replaceAll("h5", "h3");
        line = line.replaceAll("<li>Рама из стального профиля 40х80, 100х50 мм толщиной 3 мм.</li>", "");
        line = line.replaceAll("<li>Рама из стального профиля 40х80, 50х50, 100х50 мм толщиной 3 мм.</li>", "");
        line = line.replaceAll("<li>Рама из стального профиля 40х80, 50х50 мм толщиной 3 мм.</li>", "");
        line = line.replaceAll(", ремень из полиуретана, армированный кевларом", "");
        System.out.println(line);
    }
}
