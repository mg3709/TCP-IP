package Steam.dao;

import Steam.util.Common;
import java.util.List;
import Steam.vo.UserVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class UserDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Scanner scanner = new Scanner(System.in);

    public List<UserVO> userSelect(){
        List<UserVO> list = new ArrayList<>();
        try{
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM USER_INFO";
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                String name = rs.getString("USER_NAME");
                String id = rs.getString("ID");
                String pwd = rs.getString("PWD");
                int money = rs.getInt("MONEY");
                String email = rs.getString("EMAIL");
                String addr = rs.getString("ADDR");

                UserVO vo = new UserVO(name, id, pwd, money, email, addr);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public void UserInsert(){
        System.out.println("======= 회원 가입 =========");
        System.out.println("회원 정보 입력를 입력하세요");
        System.out.print("회원 이름 : ");
        String name = scanner.next();

        System.out.print("아이디 : ");
        String id = scanner.next();

        System.out.print("비밀번호 : ");
        String pwd = scanner.next();

        System.out.print("보유중인 금액 : ");
        int money = scanner.nextInt();

        System.out.print("이메일 : ");
        String email = scanner.next();

        System.out.print("주소 : ");
        String addr = scanner.next();

        String sql = "INSERT INTO USER_INFO(USER_NAME, ID, PWD, MONEY, EMAIL, ADDR) VALUES(?,?,?,?,?,?)";

        try{
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, id);
            pstmt.setString(3, pwd);
            pstmt.setInt(4, money);
            pstmt.setString(5, email);
            pstmt.setString(6, addr);
            pstmt.executeUpdate();

            System.out.println("회원가입 완료");

        }catch (Exception e){
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }

    public void UserDelete(){
        System.out.print("삭제할 회원의 이름을 입력하세요 : ");
        String name = scanner.next();
        String sql = "DELETE FROM USER_INFO WHERE USER_NAME = ?";

        try{
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.executeUpdate();

            System.out.println("회원 탈퇴 완료");

        }catch (Exception e){
            e.printStackTrace();
        }
        Common.close(stmt);
        Common.close(conn);
    }


    public void UserUpdate(){
        System.out.print("변경할 회원의 이름을 입력하세요 : ");
        String name = scanner.next();
        System.out.print("아이디 : ");
        String id = scanner.next();
        System.out.print("비밀번호 : ");
        String pwd = scanner.next();
        System.out.print("이메일 : ");
        String email = scanner.next();
        System.out.print("주소 : ");
        String addr = scanner.next();

        String sql = "UPDATE USER_INFO SET ID = ?, PWD = ?, EMAIL = ?, ADDR = ? WHERE USER_NAME = ?";

        try{
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pwd);
            pstmt.setString(3, email);
            pstmt.setString(4, addr);
            pstmt.setString(5,name);
            pstmt.executeUpdate();

            System.out.println("회원 정보 변경 완료");

        }catch (Exception e){
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }
    public String UserLogin(List<UserVO> list){
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT ID, PWD FROM USER_INFO";
            rs = stmt.executeQuery(sql);
            System.out.print("아이디를 입력하세요 : ");
            String id = scanner.next();
            System.out.print("비밀번호를 입력하세요 : ");
            String pwd = scanner.next();
            while(rs.next()){
                if(id.equals(rs.getString("ID"))){
                    if(pwd.equals(rs.getString("PWD"))){
                        return "로그인 성공";
                    }
                }
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "로그인 실패";
    }

    public void userSelectRst(List<UserVO> list){
        for(UserVO e : list){
            System.out.println("회원이름 : " + e.getName());
            System.out.println("아이디 : " + e.getId());
            System.out.println("비밀번호 : " + e.getPwd());
            System.out.println("소지금 : " + e.getMoney());
            System.out.println("이메일 : " + e.getEmail());
            System.out.println("주소 : " + e.getAddr());
            System.out.println("============================================");
        }
    }

}