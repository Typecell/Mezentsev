import java.util.ArrayList;
import java.util.List;

public class TextManager {
    private ClipboardManager clipboardManager = new ClipboardManager();

    public void removeHoistTrash() {
        String line = clipboardManager.getTextFromClipboard();

        line = line.replaceAll("</a>", "");
        line = line.replaceAll("</div><div>&nbsp;</div><h2>", "");
        line = line.replaceAll("<strong>", "\n\n");
        line = line.replaceAll("</strong></h2>", "\n\n");
        line = line.replaceAll("<li style=\"text-align: justify;\">", "<li>");
        line = line.replaceAll("&nbsp;", "");
        line = line.replaceAll("<br />", "");
        line = line.replaceAll("</li>", "</li>\n");
        line = line.replaceAll("</div>", "");
        line = line.replaceAll("</h2>", "");
        line = line.replaceAll("</p>", "");
        line = line.replaceAll("<a href=\"http://www.well-mir.ru\">", "");
        line = line.replaceAll("<p>", "");
        line = line.replaceAll("<h2>", "");
        line = line.replaceAll("&laquo;", "\"");
        line = line.replaceAll("&raquo;", "\"");
        line = line.replaceAll("&ndash;", "—");
        line = line.replaceAll("<a href=\"http://www.well-mir.ru/silovye-trenazhery/\" target=\"_blank\">", "");
        line = line.replaceAll("<a href=\"http://www.well-mir.ru\" target=\"_blank\">", "");
        line = line.replaceAll("<h2 style=\"text-align: justify;\">", "");
        line = line.replaceAll("<p style=\"text-align: justify;\">", "");

        clipboardManager.setTextToClipboard(line);
    }

    public void removeCFTrash() {
        String line = clipboardManager.getTextFromClipboard();
        List<String> list = new ArrayList<>();

        list.add("<tr>"); list.add("</tr>"); list.add("<td>"); list.add("</td>");
        list.add("<strong>"); list.add("</strong>");
        list.add("<ul>"); list.add("</ul>"); list.add("<li>"); list.add("</li>");

        for (int i = 0; i < list.size(); i++) {
            line = line.replaceAll(list.get(i), "");
        }

        line = line.replaceAll("Да", ",");
        clipboardManager.setTextToClipboard(line);
    }

    public void createGoodTitle(String regex, String replacement) {
        String line = clipboardManager.getTextFromClipboard();
        line = line.replaceAll(regex, replacement);
        clipboardManager.setTextToClipboard(line);
    }

    private void removeNewLines() {
        String line = clipboardManager.getTextFromClipboard();
        line = line.replaceAll("\n", " ");
        clipboardManager.setTextToClipboard(line);
    }

    public static void main(String[] args) {
        TextManager manager = new TextManager();
        manager.createForemanHeader();
    }

    public void createForemanTitle() {
        String line = clipboardManager.getTextFromClipboard();
        String[] arr = line.split(" ");
        String modelNumber = "Foreman " + arr[0];
        String result = "";

        if (arr.length > 2) {
            for (int i = 1; i < arr.length; i++) {
                if (i == 1) {
                    String firstLetter = arr[i].substring(0, 1);
                    String word = arr[i].substring(1).toLowerCase();
                    arr[i] = firstLetter + word;
                }
                else {
                    arr[i] = arr[i].toLowerCase();
                }
                result = result + arr[i] + " ";
            }
            result += modelNumber;
        }
        else {
            result = arr[1] + modelNumber;
        }
        clipboardManager.setTextToClipboard(result);
    }

    public void createForemanHeader() {
        String line = clipboardManager.getTextFromClipboard();
        String firstLetter = line.substring(0, 1);
        String word = line.substring(1).toLowerCase();
        line = "<h3>" + firstLetter + word + "</h3>";
        clipboardManager.setTextToClipboard(line);
    }
}
