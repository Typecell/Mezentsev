public class TagMarker {
    private static ClipboardManager clipboardManager = new ClipboardManager();

    public static void markWithP() {
        String line = clipboardManager.getTextFromClipboard();
        line = "<p>" + line + "</p>";
        clipboardManager.setTextToClipboard(line);
    }

    public static void markWithB() {
        String line = clipboardManager.getTextFromClipboard();
        line = "<b>" + line + "</b>";
        clipboardManager.setTextToClipboard(line);
    }

    public static void markWithLi() {
        String line = clipboardManager.getTextFromClipboard();
        line = "<li>" + line + "</li>";
        clipboardManager.setTextToClipboard(line);
    }

    public static void markWithStrong() {
        String line = clipboardManager.getTextFromClipboard();
        line = "<strong>" + line + "</strong>";
        clipboardManager.setTextToClipboard(line);
    }

    public static void createUList() {
        String line = clipboardManager.getTextFromClipboard();
        line = line.replaceAll("\n", "</li>\n<li>");
        line = "<ul>\n<li>" + line + "</li>\n</ul>";
        clipboardManager.setTextToClipboard(line);
    }

    public static void main(String[] args) {
        markWithB();
    }
}
