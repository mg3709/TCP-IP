package Steam.vo;

import java.util.Date;

public class BuyVO {
    private String user_name;
    private String game_name;
    private String card;
    private int cnt;//게임 산 갯수
    private Date date;

    BuyVO(String user_name, String game_name, String card, int cnt, Date date){
        this.user_name = user_name;
        this.game_name = game_name;
        this.card = card;
        this.cnt = cnt;
        this.date = date;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
