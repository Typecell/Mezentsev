package parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.List;

public class WellMirParser {
    private String url;
    private String sourcePath;
    private Document document;
    private Elements links;
    private Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

    public void setUrl(String url) throws IOException {
        this.url = url;
        createConnection();
    }

    private void createConnection() throws IOException {
        document = Jsoup.connect(url).get();
    }

    public void getCharacteristics() {
        Element characteristics = document.getElementById("divtext2");
        links = characteristics.getElementsByTag("td");
        List<String> list = links.eachText();

        String result = "";

        for (int i = 0; i < list.size(); i += 2) {
            String name = list.get(i);
            String value = null;
            if (i + 1 < list.size()) {
                value = list.get(i + 1);
            }
            else
                break;
            String line = name + " - " + value + "\n";
            result += line;
        }

        StringSelection stringSelection = new StringSelection(result);
        clipboard.setContents(stringSelection, null);
    }
}
