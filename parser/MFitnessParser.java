package parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MFitnessParser {
    private String url;
    private String sourcePath;
    private Document document;
    private Elements links;

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

    private void createDirectory(String dirName) {
        File dir = new File(sourcePath + dirName);
        dir.mkdir();
    }

    private void getElementsByClass(String clazz) {
        links = document.getElementsByClass(clazz);
    }

    private void getElementsByTag(String tag) {
        links = document.getElementsByTag(tag);
    }

    private void selectTags(String tag) {
        links = links.select(tag);
    }

    private List<String> createListByAttribute(String attr) {
        return links.eachAttr(attr);
    }

    private void downloadImage(String urlPath, String resultPath) {
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

    private List<String> sortLinks(List<String> list, String condition) {
        List<String> result = new ArrayList<>();
        for (String link : list) {
            if (link.contains(condition))
                result.add(link);
        }
        return result;
    }

    public void downloadPhotos() {
        getElementsByClass("thumbnail-item js-item");
        List<String> linkList = createListByAttribute("data-big-image");

        for (int i = 0; i < linkList.size(); i++) {
            String imageUrl = "http://www.mfitness.ru" + linkList.get(i);
            String imageName = i + ".jpg";
            downloadImage(imageUrl, sourcePath + imageName);
        }
    }
}
