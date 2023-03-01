package Steam;

import Steam.dao.GameDAO;
import Steam.dao.UserDAO;
import Steam.vo.GameVO;
import Steam.vo.UserVO;

import java.util.List;
import java.util.Scanner;

public class jdbcMain {
    public static void main(String[] args) {
        MenuSelect();
    }

    public static void MenuSelect(){
        Scanner scanner = new Scanner(System.in);
        UserDAO dao = new UserDAO();
        GameDAO gdao = new GameDAO();
        boolean Login_result = false;

        while(true){
            System.out.println("======== 메뉴를 선택하세요 ========");
            System.out.print("[1]회원정보 [2]로그인 [3]회원가입 [4]회원탈퇴 [5]회원정보수정 [6]exit");
            int sel = scanner.nextInt();
            switch(sel){
                case 1:
                    List<UserVO> list = dao.userSelect();
                    dao.userSelectRst(list);
                    break;
                case 2:
                    List<UserVO> list2 = dao.userSelect();
                    String login = dao.UserLogin(list2);
                    System.out.println(login);

                    if(login.equals("로그인 성공")){
                        Login_result = true;
                        break;
                    }else{
                        return;
                    }
                case 3:
                    dao.UserInsert();
                    break;
                case 4:
                    dao.UserDelete();
                    break;
                case 5:
                    dao.UserUpdate();
                    break;
                case 6:
                    System.out.println("종료");
                    return;
            }
            break;
        }
        if(Login_result){
            while(true) {
                System.out.println("=== 메뉴를 선택하세요 ===");
                System.out.print("[1]게임샵 [2]exit : ");

                int sel2 = scanner.nextInt();

                switch (sel2) {
                    case 1:
                        List<GameVO> glist = gdao.GameSelect();
                        gdao.GameSelectRst(glist);
                        break;

                    case 2:
                        System.out.println("종료");
                        return;
                }
            }
        }
    }
}