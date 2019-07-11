import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SpanMaker {
    private static List<String> spans = new ArrayList<>();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static String advantages = "\"/upload/images/advantages/";
    private static String brand = "precor/";
    private static String span1 = "<span style=\"display:inline-block; text-align:center; margin:16px; width:285px; vertical-align:top; position:relative\"><img alt=\"";
    private static String span2 = "\" src=";
    private static String span3 = "\" style=\"display:block; zzborder:1px solid #edeeed; border-radius:13px\" title=\"";
    private static String span4 = "\"><br>\n<b>";
    private static String span5 = "</b><br>\n";
    private static String span6 = "\n</span>";
    private static ClipboardManager clipboardManager = new ClipboardManager();

    public static void main(String[] args) throws IOException {
        System.out.println("Введите количество SPAN-блоков");
        int spanNumber = Integer.parseInt(reader.readLine());
        System.out.println("Введите модель");
        String model = reader.readLine();
        model += "/";

        for (int i = 1; i <= spanNumber; i++) {
            System.out.println("Введите заголовок " + i + "-го блока");
            String header = reader.readLine();
            System.out.println("Введите описание " + i + "-го блока");
            String description = reader.readLine();
            description = enlargeFirstLetter(description);

            String imageName = i + ".jpg";
            String resultSpan = span1 + header + span2 + advantages + brand + model + imageName + span3 + header + span4 + header + span5 + description + span6;
            spans.add(resultSpan);
        }

        String resultLine = "<h3>Особенности</h3>\n\n";

        for (int i = 0; i < spans.size(); i++) {
            resultLine += spans.get(i);
            resultLine += "\n\n";
        }

        clipboardManager.setTextToClipboard(resultLine);
    }

    private static String enlargeFirstLetter(String line) {
        String firstLetter = line.substring(0, 1);
        firstLetter = firstLetter.toUpperCase();
        line = firstLetter + line.substring(1);
        return line;
    }
}
