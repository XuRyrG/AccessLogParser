import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) { System.out.println("Введите текст и нажмите <Enter>: ");
 String text = new Scanner(System.in).nextLine();
 System .out.println("Длина текста: "+text.length());

        int N = 1;
        while (true) {
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();
            if ((!fileExists) || (isDirectory)) {
                System.out.println("Файл не существует или указанный путь является путём к папке, а не к файлу");
                continue;
            }
            System.out.println("Путь указан верно. Кол-во верно указанных путей к файлу: " + N);
            N++;
        }
    }
}
