package 회원정보;

import java.util.Scanner;

public class Member {
    // 필드 추가 하기
    String name;
    int age;
    char gender;
    int jobs;
    Scanner sc = new Scanner(System.in);

    public void setName() {
        System.out.print("이름을 입력 하세요 : ");
        name = sc.nextLine();
    }

    public char getGender() {
        return gender;
    }

    public int getGenderType() {
        if (gender == 'M' || gender == 'm') return 1;
        else return 2;
    }

    public void setGender() {
        while(true) {
            System.out.print("성별을 입력 하세요 : ");
            gender = sc.next().charAt(0);
            switch(gender) {
                case 'M' :
                case 'm' :
                case 'F' :
                case 'f' :
                    break;
                default :
                    System.out.println("성별을 잘 못 입력 하셨습니다. ");
                    continue;
            }
            break;
        }
    }

    public int getJobs() {
        return jobs;
    }

    public void setJobs() {
        while(true) {
            System.out.print("직업을 입력 하세요 : ");
            jobs = sc.nextInt();
            if (jobs > 0 && jobs < 5) break;
            else System.out.println("직업이 잘 못 입력 되었습니다. 다시 입력 하세요.");
        }
    }

    public String getName() {
        return name;
    }

    public void setAge() {
        while(true) {
            System.out.print("나이를 입력 하세요 : ");
            age = sc.nextInt();
            if (age > 0 && age < 200) break;
            else System.out.println("나이를 잘못 입력 하셨습니다. 다시 입력 하세요.");
        }
    }
    public int getAge() {
        return age;
    }

    public void getInfo() {
        String[] genderStr = {"", "남성", "여성"};
        String[] jobsStr = {"", "학생", "회사원", "주부", "무직"};
        System.out.println("======= 회원 정보 =======");
        System.out.println("이름 : " + name);
        System.out.println("나이 : " + age);
        System.out.println("성별 : " + genderStr[getGenderType()]);
        System.out.println("직업 : " + jobsStr[jobs]);
    }
}