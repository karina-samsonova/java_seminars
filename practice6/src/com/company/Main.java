package com.company;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException {
	// write your code here
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите слово или фразу для поиска:");
        String search = URLEncoder.encode(scan.next(), "utf-8");
        URL url  = new URL("https://ru.wikipedia.org/w/index.php?search="+search+"&title=%D0%A1%D0%BB%D1%83%D0%B6%D0%B5%D0%B1%D0%BD%D0%B0%D1%8F:%D0%9F%D0%BE%D0%B8%D1%81%D0%BA&profile=advanced&fulltext=1&ns0=1");
        //System.out.println(url);
        BufferedInputStream in = new BufferedInputStream(url.openStream());
        byte[] contents = new byte[1024];
        int bytesRead = 0;
        String strFileContents = new String();
        while((bytesRead = in.read(contents)) != -1) {
            strFileContents += new String(contents, 0, bytesRead);
        }
        String[] parts = strFileContents.split("<div class=\"mw-search-result-heading\"><a href=\"");
        printArticle(parts[1].substring(0, parts[1].indexOf("\"")));
        printArticle(parts[2].substring(0, parts[2].indexOf("\"")));
        printArticle(parts[3].substring(0, parts[3].indexOf("\"")));
    }
    public static void printArticle(String title) throws IOException {
        URL url  = new URL("https://ru.wikipedia.org/"+title);
        BufferedInputStream in = new BufferedInputStream(url.openStream());
        byte[] contents = new byte[1024];
        int bytesRead = 0;
        String strFileContents = new String();
        while((bytesRead = in.read(contents)) != -1) {
            strFileContents += new String(contents, 0, bytesRead);
        }
        strFileContents = strFileContents.substring(strFileContents.indexOf("<title>")+7);

        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+strFileContents.substring(0, strFileContents.indexOf("</title>"))+"\n");
        strFileContents = strFileContents.substring(strFileContents.indexOf("<div class=\"mw-parser-output\">")+30);
        strFileContents = strFileContents.substring(strFileContents.indexOf("<p>")+3);
        printString(strFileContents);
        System.out.println("\nПерейти на статью: "+url+"\n");
        //System.out.println(strFileContents);
    }
    public static void printString(String str){
        int len = 0;
        int strings = 0;
        boolean flag = true;
        for (int i = 0; i < str.length(); ++i) {
            if (str.indexOf("</p>") == i){
                if (!str.contains("<p>") && !str.contains("<li>"))
                    return;
                if (str.contains("<p>") && !str.contains("<li>")){
                    str = str.substring(str.indexOf("<p>")+3);
                    i = 0;
                }
                else if (str.contains("<li>") && !str.contains("<p>")){
                    str = str.substring(str.indexOf("<li>")+4);
                    i = 0;
                }
                else if (str.contains("<p>") && str.contains("<li>")){
                    if (str.indexOf("<p>") < str.indexOf("<li>"))
                        str = str.substring(str.indexOf("<p>")+3);
                    else
                        str = str.substring(str.indexOf("<li>")+4);
                    i = 0;
                }
            }
            if (str.indexOf("<style") == i && str.contains("</style>")) {
                str = str.substring(str.indexOf("</style>") + 8);
                i = 0;
            }
            if (str.charAt(i) == '&' && str.charAt(i+1) == '#' && str.charAt(i+6) == ';')
                i = i + 7;
            if (str.charAt(i) == '&' && str.charAt(i+1) == '#' && str.charAt(i+5) == ';')
                i = i + 6;
            if (str.charAt(i) == '&' && str.charAt(i+1) == '#' && str.charAt(i+4) == ';')
                i = i + 5;
            if (str.charAt(i) == '.' && strings > 5) {
                System.out.println(".");
                return;
            }
            if (str.charAt(i) == '\n'){
                if (strings > 5)
                    return;
                len = 0;
                strings++;
            }
            if (str.charAt(i) == ' ' && len >= 130){
                System.out.print('\n');
                len = 0;
                strings++;
            }
            else {
                if (str.charAt(i) == '<')
                    flag = false;
                if (flag) {
                    System.out.print(str.charAt(i));
                    len++;
                }
                if (str.charAt(i) == '>')
                    flag = true;
            }
        }
    }
}
