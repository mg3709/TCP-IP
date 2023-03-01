package Steam.dao;

import Steam.util.Common;
import Steam.vo.GameVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GameDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public List<GameVO> GameSelect(){
        List<GameVO> list = new ArrayList<>();
        try{
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM GAME";
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                String name = rs.getString("GAME_NAME");
                int price = rs.getInt("PRICE");
                String genre = rs.getString("GENRE");

                GameVO vo = new GameVO(name, price, genre);
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
    public void GameSelectRst(List<GameVO> list){
        for(GameVO e : list){
            System.out.println("게임 이름 : " + e.getName());
            System.out.println("게임 가격 : " + e.getPrice());
            System.out.println("게임 장르 : " + e.getGenre());
            System.out.println("====================================================");
        }
    }
}
