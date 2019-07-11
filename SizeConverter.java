import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class SizeConverter {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void convertSize() {
        String width = null;
        String length = null;
        String height = null;

        try {
            width = reader.readLine();
            length = reader.readLine();
            height = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();

        }

        String result = width + " x " + length + " x " + height + " см.";

//        try {
//            result = new String(result.getBytes(), StandardCharsets.UTF_8);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        new ClipboardManager().setTextToClipboard(result);

    }

    public void convertStarsToX() {
        ClipboardManager manager = new ClipboardManager();
        String line = manager.getTextFromClipboard();
        line = line.replaceAll("\\*", " x ");
        manager.setTextToClipboard(line);
    }

    public static void convertPrecorIndications() {
        ClipboardManager manager = new ClipboardManager();
        String line = manager.getTextFromClipboard();
        line = line.replaceAll("\n", ", ");
        line = "Показания: " + line.toLowerCase() + ".";
        manager.setTextToClipboard(line);
    }

    public static void deleteNewLines() {
        ClipboardManager manager = new ClipboardManager();
        String line = manager.getTextFromClipboard();
        line = line.replaceAll("\n", " ");
        manager.setTextToClipboard(line);
    }

    public static void main(String[] args) {
        deleteNewLines();
    }
}
