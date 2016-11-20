package UsersInfo;


public class Post {

    private String nick;
    private String text;
    private String ref;

    public Post(String nick, String text, String ref){
        this.nick = nick;
        this.text = text;
        this.ref = ref;
    }

    public Post(){}

    public String getNick(){return nick;}
    public String getText(){return text;}
    public String getRef(){return ref;}
}
