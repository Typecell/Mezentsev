import parser.CommonParser;
import parser.MFitnessParser;
import parser.WellMirParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static String sourcePath = "D:\\test\\mez\\";

    private static void clearSourcePath(String path) {
        File dir = new File(path);
        File[] files = dir.listFiles();

        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                files[i].delete();
            }
        }
    }

    private static String getModelName(String link) {
        String[] arr = link.split("/");
        return arr[arr.length-1];
    }

    private static double calcPercentage(int quantity) {
        double hundred = 100;
        return hundred / quantity;
    }

    public static void main(String[] args) throws IOException {
        String url = "http://www.foremanfitness.ru/catalog/silovye-trenazhery/nagruzhaemye-diskami/";

        CommonParser imageParser = new CommonParser();
        CommonParser linkParser = new CommonParser();

        imageParser.setUrl(url);
        imageParser.getElementsByClass("product-image");
        imageParser.selectTags("img");

        linkParser.setUrl(url);
        linkParser.getElementsByClass("product-details-container");
        linkParser.selectTags("a");

        List<String> imageLinkList = imageParser.createListByAttribute("src");
        List<String> folderNames = imageParser.createListByAttribute("title");

        List<String> productLinkList = linkParser.createListByAttribute("href");
        linkParser.disconnect();

        String start = "http://www.foremanfitness.ru";

        for (int i = 0; i < imageLinkList.size(); i++) {
            String imageLink = start + imageLinkList.get(i);
            String previewName = "preview.jpg";
            String folderName = folderNames.get(i).replaceAll("/", "-");

            DirectoryManager manager = new DirectoryManager();
            manager.setSourcePath(sourcePath);
            manager.createDirectory(folderName);

            folderName = folderName.trim() + "\\";
            String resultPath = sourcePath + folderName;

            imageParser.downloadImage(imageLink, resultPath + previewName);

            String productLink = start + productLinkList.get(i);
            linkParser.setUrl(productLink);

            linkParser.getElementsByTag("a");
            List<String> extraPhotoLinks = linkParser.createListByAttribute("href");

            for (int j = 0; j < extraPhotoLinks.size(); j++) {
                if (extraPhotoLinks.get(j).endsWith(".jpg")) {
                    String extraPhotoLink = start + extraPhotoLinks.get(j);
                    String name = j + ".jpg";
                    linkParser.downloadImage(extraPhotoLink, resultPath + name);
                }
            }
        }
    }
}
