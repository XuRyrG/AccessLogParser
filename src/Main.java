
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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

            int yandexCounter = 0;
            int googleCounter = 0;
            int lineCounter = 0;
            String line;
            try {
                FileReader fileReader = new FileReader(path);
                BufferedReader reader = new BufferedReader(fileReader);
                while ((line = reader.readLine()) != null) {
                    int length = line.length();
                    if (line.length() > 1024) {
                        throw new LineTooLongException("Длинная строка (Строка >= 1024 символа).");
                    }
                    String[] firstBrackets = line.split("\"");
                    String parts[] = firstBrackets[5].split(";");
                    String fragment = null;
                    if (parts.length >= 2) {
                        fragment = parts[1];
                        fragment.split("/");
                        for (String part : parts) {
                            if (part.contains("YandexBot")) {
                                part.trim();
                                yandexCounter++;
                            }

                            if (part.contains("Googlebot")) {
                                part.trim();
                                googleCounter++;
                            }
                        }
                    }
                    lineCounter++;
                }
                System.out.println("Общее кол-во строк в файле: " + lineCounter);
                System.out.println("YandexBot: " + yandexCounter + " или " + Math.round(percent(yandexCounter, lineCounter)) + "% от общего");
                System.out.println("Googlebot: " + googleCounter + " или " + Math.round(percent(googleCounter, lineCounter)) + "% от общего");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    private static double percent(int a, int b) {
        return (double) a / (double) b * 100;
    }
}
