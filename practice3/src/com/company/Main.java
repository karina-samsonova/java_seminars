package com.company;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import static java.lang.String.valueOf;

public class Main {

    public static void main(String[] args) throws IOException, FileNotFoundException {
	// write your code here
        //String directory = System.getProperty("user.home");
        Scanner sc = new Scanner(System.in);
        //System.out.println("Введите путь до каталога или \"-\":");
        String pathname = "-";
        if (pathname.equals("-")){
            pathname = "C://Users//karin//IdeaProjects//practice3";
        }
        int success = 0;
        while (success == 0) {
            System.out.println("Введите имя входного файла:");
            String filename = sc.next();
            File in = new File(pathname, filename);
            if (!in.exists()) {
                System.out.println("Такого файла не существует, попробуйте снова\n");
                continue;
            }
            if (!in.canRead()) {
                System.out.println("Не удалось открыть файл на чтение");
                System.exit(0);
            }
            System.out.println("Введите имя выходного файла:");
            filename = sc.next();
            File out = new File(pathname, filename);
            if (!out.exists()) {
                if (out.createNewFile()) {
                    System.out.println("Новый файл " + filename + " был создан");
                } else {
                    System.out.println("Не удалось создать новый файл");
                    System.exit(0);
                }
            }
            if (!out.canWrite()) {
                System.out.println("Не удалось открыть файл на запись");
                System.exit(0);
            }
            InputStream source = new FileInputStream(in);
            OutputStream dest = new FileOutputStream(out);
            Map<Integer, Integer> dict = new HashMap<Integer, Integer>();
            int c;
            try {
                while ((c = source.read()) != -1) {
                    if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {
                        dict.put(c, dict.getOrDefault(c, 0) + 1);
                    }
                }
                for (int i = 65; i < 91; ++i) {
                    dest.write(((char) i + ": " + dict.getOrDefault(i, 0) + '\n').getBytes(StandardCharsets.UTF_8));
                }
                for (int i = 97; i < 123; ++i) {
                    dest.write(((char) i + ": " + dict.getOrDefault(i, 0) + '\n').getBytes(StandardCharsets.UTF_8));
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                source.close();
                dest.close();
                success = 1;
            }
        }
    }
}
