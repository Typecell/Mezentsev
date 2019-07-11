import java.io.File;

public class DirectoryManager {
    private String sourcePath;

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public void createDirectory(String dirName) {
        File dir = new File(sourcePath + dirName);
        dir.mkdir();
    }

    public void renameAllFiles() {
        File dir = new File(sourcePath);
        File[] fileList = dir.listFiles();

        if (fileList == null) {
            System.out.println("Папка пуста");
            return;
        }

        for (int i = 1; i <= fileList.length; i++) {
            File file = fileList[i-1];

            if (file.isDirectory()) {
                DirectoryManager manager = new DirectoryManager();
                manager.setSourcePath(sourcePath + file.getName() + "\\");
                manager.renameAllFiles();
                continue;
            }

            String[] arr = file.getName().split("\\.");
            String extension = arr[arr.length - 1];
            String newName = sourcePath + i + "." + extension;
            File newFile = new File(newName);
            file.renameTo(newFile);
        }
    }

    public static void main(String[] args) {
        DirectoryManager manager = new DirectoryManager();
        manager.setSourcePath("C:\\Users\\User\\Desktop\\Эйкельман\\Фото\\Поставка и монтаж систем приточновытяжной системы вентиляции и монтаж и поставка системы кондиционирования Пекарня ул Савушкина 124 Площадь 120 м2\\");
        manager.renameAllFiles();
    }
}
