package scanner;

import java.util.Scanner;

//이름, 도시, 나이, 체중, 독신여부 입력하고 출력하기

public class ScannerEx1 {

    public static void main(String[] args){

        System.out.println("이름, 도시, 나이, 체중, 독신여부");

        Scanner scanner = new Scanner(System.in);//객체 생성

        String name = scanner.next();//문자열 읽기
        System.out.println("이름은 " + name + ",");

        String city = scanner.next();
        System.out.println("도시는" + city + ",");

        int age = scanner.nextInt();//정수형 읽기
        System.out.println("나이는" + age + "살,");

        double weight = scanner.nextDouble();//실수형 읽기
        System.out.println("체중은" + weight + "kg,");

        boolean isSingle = scanner.nextBoolean();//논리형 읽기
        System.out.println("독신여부는" + isSingle + ",");

        System.out.println(" 이름은 " + name + " 도시는 " + city + " 나이는 " + age + " 체중은 " + weight + " 독신여부는" + isSingle);

        scanner.close();//객체 종료



    }

}
