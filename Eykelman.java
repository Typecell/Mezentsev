import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Eykelman {
    private static DirectoryManager directoryManager = new DirectoryManager();
    private static ClipboardManager clipboardManager = new ClipboardManager();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static String divOpen = "<div class=\"iblock_images\">\n\n";
    private static String divClose = "</div>";
    private static String aPart1 = "<a class=\"fancybox\" href=\"/upload/images/completed-objects/";
    private static String aPart2 = ".jpeg\" rel=\"group-15\">\n<img class=\"animate1\" src=\"/upload/images/completed-objects/";
    private static String aPart3 = "resized/";
    private static String aPart4 = ".jpeg\" alt=\"Фото\" title=\"Фото\" style=\"margin: 5px\"/></a>\n\n";

    private static void compilePhotoText() throws IOException {
        System.out.print("Введите количество фото: ");
        int quantity = Integer.parseInt(reader.readLine());
        System.out.print("Введите название папки объекта: ");
        String dirName = reader.readLine();

        List<String> text = new ArrayList<>();

        for (int i = 1; i <= quantity; i++) {
            String line = aPart1 + dirName + "/" + i + aPart2 + dirName + "/" + aPart3 + i + aPart4;
            text.add(line);
        }

        String res = divOpen;

        for (int i = 0; i < text.size(); i++) {
            res += text.get(i);
        }

        res += divClose;

        clipboardManager.setTextToClipboard(res);
    }

    public static void main(String[] args) throws IOException {
        compilePhotoText();
    }
}
