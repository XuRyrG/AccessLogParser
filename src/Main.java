import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите первое число");
        int numberOne = new Scanner(System.in).nextInt();
        System.out.println("Введите второе число");
        int numberTwo = new Scanner(System.in).nextInt();

        int sum = numberOne+numberTwo;
        System.out.println("Сумма чисел равна: " + sum);
        int diff = numberOne-numberTwo;
        System.out.println("Разность чисел равна: " + diff);
        int multiply= numberOne*numberTwo;
        System.out.println("Произведение чисел равно: " + multiply);
        double quotient = (double) numberOne / numberTwo;
        System.out.println("Частное: " + quotient);
    }
}
