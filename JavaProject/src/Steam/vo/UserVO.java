package Steam.vo;

public class UserVO {
    private String name;
    private String id;
    private String pwd;
    private int money;
    private String email;
    private String addr;

    public UserVO(String name, String id, String pwd, int money, String email, String addr) {
        this.name = name;
        this.id = id;
        this.pwd = pwd;
        this.money = money;
        this.email = email;
        this.addr = addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}