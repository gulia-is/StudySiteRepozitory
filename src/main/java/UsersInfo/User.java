package UsersInfo;

public class User {

    private String nick;
    private String email;
    private boolean checkIn;

    public User(){
        this.checkIn = false;
    }
    public User(String nick, String email, boolean checkIn){
        this.nick = nick;
        this.email = email;
        this.checkIn = checkIn;
    }

    public boolean isCheckIn() {
        return checkIn;
    }
    public void setCheckIn(boolean checkIn) {
        this.checkIn = checkIn;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getNick(){return nick;}
    public void setNick(String nick) { this.nick = nick; }
}
