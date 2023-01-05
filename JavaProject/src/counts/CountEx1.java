package counts;

import java.util.Scanner;

//정수를 입력했을 때 시간, 분, 초, 계산

import java.util.Scanner;

public class CountEx1 {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        System.out.print("정수를 입력하시요: ");

        int time = scanner.nextInt();

        int second = time % 60;

        int minute = (time / 60) % 60;

        int hour = (time % 60) % 60;

        System.out.print(time + " 초는" + hour + "시간" + minute + "분" + second + "초입니다");

        scanner.close();
    }
}
