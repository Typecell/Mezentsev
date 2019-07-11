import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static SizeConverter sizeConverter = new SizeConverter();
    private static ClipboardManager clipboardManager = new ClipboardManager();
    private static ImageDownloader imageDownloader = new ImageDownloader();
    private static LinkManager linkManager = new LinkManager();
    private static DirectoryManager directoryManager = new DirectoryManager();
    private static String condition1, condition2;


    public static void main(String[] args) throws IOException {
        createConnection("https://www.precor.com/ru/commercial/strength/modulars-multi-stations/products", "D:\\test\\mez\\");
        setConditions("https://www.precor.com/ru/commercial/strength/modulars-multi-stations/", "https");
        start();
    }

    private static void start() throws IOException {
        List<String> items = getItemLinks(condition1, condition2);
        System.out.println("Что будем парсить?\n1 - картинки, 2 - преимущества, 3 - одну картинку");
        int answer = Integer.parseInt(reader.readLine());

        switch (answer) {
            case 1: downloadPhotos(items); break;
            case 2: downloadAdvantages(items); break;
            case 3: downloadOnePhoto(items); break;
        }
    }

    private static void setConditions(String cond1, String cond2) {
        condition1 = cond1;
        condition2 = cond2;
    }

    private static void createConnection(String url, String sourcePath) throws IOException {
        directoryManager.setSourcePath(sourcePath);
        linkManager.connect(url);
    }

    private static double calcPercentage(int quantity) {
        double hundred = 100;
        return hundred / quantity;
    }

    private static List<String> getLinkList(LinkManager manager, String clazz, String tag, String attr) {
        manager.getElementsByClass(clazz);
        manager.selectTags(tag);
        return manager.createListByAttribute(attr);
    }

    private static void downloadAdvantages(List<String> items) throws IOException {
        double percent = calcPercentage(items.size());
        double progress = 0.00;

        for (String link : items) {
            LinkManager newLinkManager = new LinkManager();
            newLinkManager.connect(link);

            List<String> gallery = getLinkList(newLinkManager, "field-item even", "img", "src");

            String articul = linkManager.getPrecorArticul(link);
            directoryManager.createDirectory(articul);

            System.out.println((Math.round(progress)) + "% завершено");
            progress += percent;

            getAdvImages(gallery, articul);
        }

        System.out.println("100% завершено");
    }

    private static void getAdvImages(List<String> gallery, String articul) {
        for (int j = 0; j < gallery.size(); j++) {
            String imageLink = "https://www.precor.com" + gallery.get(j);
            imageLink = imageLink.split("\\?itok")[0];
            String extension = imageLink.substring(imageLink.length()-4);
            int n = j + 1;
            String imageName = n + extension;
            String resultPath = "D:\\test\\mez\\" + articul + "\\";
            imageDownloader.downloadImage(imageLink, resultPath + imageName);
        }
    }

    private static List<String> getItemLinks(String condition1, String condition2) {
        linkManager.getElementsByTag("a");
        List<String> list = linkManager.createListByAttribute("href");
        list = linkManager.sortLinks(list, condition1);
        list = linkManager.sortLinks(list, condition2);
        return list;
    }

    private static void downloadPhotos(List<String> items) throws IOException {
        double percent = calcPercentage(items.size());
        double progress = 0.00;

        for (String link : items) {
            LinkManager newLinkManager = new LinkManager();
            newLinkManager.connect(link);
            String articul = linkManager.getPrecorArticul(link);
            directoryManager.createDirectory(articul);

            List<String> image = getLinkList(newLinkManager, "label top outside fade", "a", "href");
            String firstImage = image.get(0);

            if (!firstImage.startsWith("https://www.precor.com"))
                firstImage = "https://www.precor.com" + firstImage;

            List<String> gallery = getLinkList(newLinkManager, "hidden-gallery", "a", "href");
            gallery.add(0, firstImage);

            System.out.println((Math.round(progress)) + "% завершено");
            progress += percent;

            getBigImages(gallery, articul);
        }

        System.out.println("100% завершено");
    }

    private static void getBigImages(List<String> gallery, String articul) {
        for (int j = 0; j < gallery.size(); j++) {
            String imageLink = gallery.get(j).split("\\?itok")[0];
            String extension = imageLink.substring(imageLink.length()-4);
            String imageName = j + extension;
            String resultPath = "D:\\test\\mez\\" + articul + "\\";
            imageDownloader.downloadImage(imageLink, resultPath + imageName);
        }
    }

    private static void downloadOnePhoto(List<String> items) throws IOException {
        double percent = calcPercentage(items.size());
        double progress = 0.00;

        for (String link : items) {
            LinkManager newLinkManager = new LinkManager();
            newLinkManager.connect(link);
            String articul = linkManager.getPrecorArticul(link);
            directoryManager.createDirectory(articul);

            List<String> gallery = getLinkList(newLinkManager, "field field-name-field-product-images", "img", "src");
            String firstImage = gallery.get(0);

            if (!firstImage.startsWith("https://www.precor.com"))
                firstImage = "https://www.precor.com" + firstImage;

            System.out.println((Math.round(progress)) + "% завершено");
            progress += percent;

            getOnePhoto(firstImage, articul);
        }

        System.out.println("100% завершено");
    }

    private static void getOnePhoto(String link, String articul) {
        String imageLink = link.split("\\?itok")[0];
        String extension = imageLink.substring(imageLink.length()-4);
        String imageName = "1" + extension;
        String resultPath = "D:\\test\\mez\\" + articul + "\\" + imageName;
        imageDownloader.downloadImage(imageLink, resultPath);
    }
}
