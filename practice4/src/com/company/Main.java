package com.company;
import java.text.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.Scanner;
import static java.lang.Character.toUpperCase;

public class Main {
    public static String Sex(String name, String surname, String patronymic){
        if (patronymic.charAt(patronymic.length()-1) == 'а'){
            return "Пол: женский";
        }
        else if (patronymic.charAt(patronymic.length()-1) == 'ч'){
            return "Пол: мужской";
        }
        return "Не удалось определить пол";
    }
    public static int Check(String str){
        for (int i = 0; i < str.length(); ++i){
            if (str.charAt(i) < 'А' || str.charAt(i) > 'я' ){
                return 1;
            }
        }
        return 0;
    }
    public static void main(String[] args) {
	// write your code here
        Scanner sc = new Scanner(System.in);
        int success = 0;
        while(success == 0) {
            System.out.println("\nВведите ФИО и дату рождения:");
            String surname = sc.next();
            if (Check(surname) == 1) {
                System.out.println("Фамилия была введена в неверном формате");
            }
            String name = sc.next();
            if (Check(name) == 1) {
                System.out.println("Имя было введено в неверном формате");
            }
            String patronymic = sc.next();
            if (Check(patronymic) == 1) {
                System.out.println("Отчество было введено в неверном формате");
            }
            SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
            Date birthdate;
            try {
                birthdate = ft.parse(sc.next());
                LocalDate birth = LocalDate.ofInstant(birthdate.toInstant(), ZoneId.systemDefault());
                LocalDate now = LocalDate.now();
                System.out.println(toUpperCase(surname.charAt(0))+surname.substring(1)+" "+toUpperCase(name.charAt(0))+". "+toUpperCase(patronymic.charAt(0))+".");
                System.out.println(Sex(name, surname, patronymic));
                int age = Period.between(birth, now).getYears();
                if (age%10 == 1)
                    System.out.println("Возраст: "+age+" год");
                else if (age%10 >=2 && age%10 <= 4)
                    System.out.println("Возраст: "+age+" года");
                else
                    System.out.println("Возраст: "+age+" лет");
                success = 1;
            } catch (ParseException e) {
                System.out.println("Неверный формат даты");
            }
        }
    }
}
