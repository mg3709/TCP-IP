package forex;

import java.util.Scanner;

public class ForEx1 {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        System.out.print("정수를 입력하세요 : ");

        int num = scanner.nextInt();

        for(int i = 0; i < num; i++){
            for(int j = 0; j < num+1; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }

    }

}
