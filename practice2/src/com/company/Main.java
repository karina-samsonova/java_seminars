package com.company;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //testComplex();
        testMatrix();
    }
    public static void enterComp(Complex a){
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите вещественную часть:");
        double real = sc.nextDouble();
        System.out.println("Введите мнимую часть:");
        double imag = sc.nextDouble();
        a.set(real, imag);
    }
    public static void testComplex(){
        System.out.println("Введите первое число:");
        Complex a = new Complex(0);
        enterComp(a);
        System.out.println("Введите второе число:");
        Complex b = new Complex(0);
        enterComp(b);
        System.out.println("a = " + a.alg() + "\n" + "b = " + b.alg());

        System.out.println("\nСложение:\n"+(a.add(b)).alg());
        System.out.println("\nВычитание:\n"+(a.sub(b)).alg());
        System.out.println("\nУмножение:\n"+(a.mul(b)).alg());
        String temp = "";
        try{
            temp = (a.div(b)).alg();
        } catch (ArithmeticException e){
            System.out.println(e.getMessage());
        }
        System.out.println("\nДеление:\n"+temp);
        System.out.println("\nМодуль a:\n"+(a.mod()));
        System.out.println("\nАргумент a:\n"+(a.arg()));
        System.out.println("\nАлгебраическая форма a:\n"+(a.alg()));
        System.out.println("\nТригонометрическая форма а:\n"+(a.trig()));
    }
    public static void testMatrix()
    {
        /*Random random = new Random();
        Matrix mat1 = new Matrix(3, 3);
        for (int i = 0; i < 3; ++i){
            for (int j = 0; j < 3; ++j){
                mat1.set(i, j, new Complex((double) (random.nextInt(9)) + 1, random.nextInt(8) + 1));
            }
        }
        Matrix mat2 = new Matrix(3, 3);
        for (int i = 0; i < 3; ++i){
            for (int j = 0; j < 3; ++j){
                mat2.set(i, j, new Complex((double) (random.nextInt(9)) + 1, random.nextInt(8) + 1));
            }
        }*/

        Scanner sc = new Scanner(System.in);
        System.out.println("Введите размерность для матрицы 1:");
        int m = sc.nextInt();
        int n = sc.nextInt();
        Matrix mat1 = new Matrix(m, n);
        for (int i = 0; i < m; ++i){
            for (int j = 0; j < n; ++j){
                System.out.println("Элемент ["+i+"]["+j+"]");
                Complex a = new Complex(0);
                enterComp(a);
                mat1.set(i, j, a);
            }
        }
        System.out.println("\nВведите размерность для матрицы 2:");
        m = sc.nextInt();
        n = sc.nextInt();
        Matrix mat2 = new Matrix(m, n);
        for (int i = 0; i < m; ++i){
            for (int j = 0; j < n; ++j){
                Complex a = new Complex(0);
                enterComp(a);
                mat2.set(i, j, a);
            }
        }

        System.out.println("Матрица 1:");
        mat1.print();
        System.out.println("\nМатрица 2:");
        mat2.print();
        System.out.println("\nСложение:");
        Matrix result = new Matrix(mat1.add(mat2));
        result.print();
        System.out.println("\nУмножение:");
        result = new Matrix(mat1.mul(mat2));
        result.print();
        System.out.println("\nТранспонирование матрицы 1:");
        result = new Matrix(mat1.T());
        result.print();
        System.out.println("\nДетерминант матрицы 1: " + mat1.getDet().alg());
    }
}
