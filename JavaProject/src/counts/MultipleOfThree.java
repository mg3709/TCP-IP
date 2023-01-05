package counts;

import java.util.Scanner;
//정수를 입력해 3의배수인지 3의배수가 아닌지 출력하기

public class MultipleOfThree {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("정수를 입력하시오: ");

        int number = scanner.nextInt();

        if(number % 3 == 0){

            System.out.println("3의 배수가 맞습니다");
        }
        else {

            System.out.println("3의 배수가 아닙니다");
        }

        scanner.close();

    }
}
