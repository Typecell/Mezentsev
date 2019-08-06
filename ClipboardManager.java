import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

public class ClipboardManager {
    private Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

    public void setTextToClipboard(String text) {
        StringSelection stringSelection = new StringSelection(text);
        clipboard.setContents(stringSelection, null);
    }

    public String getTextFromClipboard() {
        String res = "";
        Transferable contents = clipboard.getContents(null);
        try {
            res = (String) contents.getTransferData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException | IOException e) {
            System.out.println("Некорректные данные");
            e.printStackTrace();
        }
        return res;
    }
}
