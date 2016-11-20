package UsersInfo;

import java.util.ArrayList;

public class PostsArray {

    private ArrayList<Post> arrayP = new ArrayList<Post>();

    public PostsArray(){}
    public PostsArray(PostsArray arp){
        this.arrayP = arp.getArrayP();
    }

    public ArrayList<Post> getArrayP(){return arrayP;}
    public int getCount(){return  arrayP.size();}

    public Post getPost(int i){return arrayP.get(i);}
    public void addPost(Post post){arrayP.add(post);}
}
