import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class ImageDownloader {

    public void downloadImage(String urlPath, String resultPath) {
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
}