package DataBase;

import UsersInfo.Post;
import UsersInfo.PostsArray;
import UsersInfo.User;
import com.mysql.fabric.jdbc.*;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlDataBase {


    private final String URL = "jdbc:mysql://localhost:3306/test";
    private final String NAME = "root";
    private final String PASSWORD = "iskandarova";

    private final String CREATE_USER = "INSERT INTO myusers (`nick`, `email`, `password`) VALUES(?,?,?)";
    private final String READ_USER = "SELECT * FROM myusers WHERE email=?";
    private final String DELETE_USER = "DELETE FROM myusers WHERE email=?";
    private final String UPDATE_USER = "UPDATE myusers SET ?=? WHERE email=?";
    private final String READ_INFO = "SELECT * FROM myusers WHERE nicl=? OR email=?";

    private final String CREATE_POST = "INSERT INTO myposts (`userNick`, `text`, `ref`) VALUES(?,?,?)";
    private final String READ_POST = "SELECT userNick, text, ref FROM myposts WHERE userNick=?";



    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;


    public  MysqlDataBase() {

        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeALL(){
        try { resultSet.close(); } catch (Exception e) { /* ignored */ }
        try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
        try { connection.close(); } catch (Exception e) { /* ignored */ }
    }

    public void createPost(String nick, String text, String ref) throws SQLException {
        preparedStatement = connection.prepareStatement(CREATE_POST);
        preparedStatement.setString(1, nick);
        preparedStatement.setString(2, text);
        preparedStatement.setString(3, ref);

        preparedStatement.execute();

        try { resultSet.close(); } catch (Exception e) {}
        try { preparedStatement.close(); } catch (Exception e) {}
    }

    public PostsArray readPost(String nick) throws SQLException {
        preparedStatement = connection.prepareStatement(READ_POST);
        preparedStatement.setString(1, nick);

        resultSet = preparedStatement.executeQuery();

        PostsArray arrayP = new PostsArray();
        if(resultSet.isBeforeFirst()){
            while (resultSet.next()){
                arrayP.addPost(new Post(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)));
            }
        } else {return new PostsArray();}

        try { resultSet.close(); } catch (Exception e) {}
        try { preparedStatement.close(); } catch (Exception e) {}
        return arrayP;
    }

    public void createUser(String userNick, String userEmail, String userPassword) throws SQLException {
        preparedStatement = connection.prepareStatement(CREATE_USER);
        preparedStatement.setString(1, userNick);
        preparedStatement.setString(2, userEmail);
        preparedStatement.setString(3, userPassword);

        preparedStatement.execute();

        try { resultSet.close(); } catch (Exception e) { /* ignored */ }
        try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
    }

    public boolean hasInfo(String nick, String email) throws SQLException {
        preparedStatement = connection.prepareStatement(READ_INFO);
        preparedStatement.setString(1, nick);
        preparedStatement.setString(2, email);

        resultSet = preparedStatement.executeQuery();
        boolean result = resultSet.next();

        try { resultSet.close(); } catch (Exception e) { /* ignored */ }
        try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }

        return result;
    }

    public User readUser(String userEmail, String userPass) throws SQLException {
        preparedStatement = connection.prepareStatement(READ_USER);
        preparedStatement.setString(1, userEmail);

        resultSet = preparedStatement.executeQuery();
        String DBNick = null;
        String DBemail = null;
        String DBpassword = null;
        if(resultSet.isBeforeFirst()) {
            while (resultSet.next()) {
                DBNick = resultSet.getString(2);
                DBemail = resultSet.getString(3);
                DBpassword = resultSet.getString(4);
            }
        } else{new User();}

        try { resultSet.close(); } catch (Exception e) { /* ignored */ }
        try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
        if(!DBpassword.equals(userPass)){return new User();}
        return new User(DBNick, DBemail, true);
    }

    public void updateInfo(String userEmail, int col ,String userInfo) throws SQLException {
        preparedStatement = connection.prepareStatement(UPDATE_USER);
        preparedStatement.setInt(1, col);
        preparedStatement.setString(2, userInfo);
        preparedStatement.setString(3, userEmail);

        preparedStatement.executeUpdate();

        try { resultSet.close(); } catch (Exception e) { /* ignored */ }
        try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
    }

    public void deleteUser(String userEmail, String userPassword) throws SQLException {
        preparedStatement = connection.prepareStatement(DELETE_USER);
        preparedStatement.setString(1, userEmail);

        preparedStatement.executeUpdate();

        try { resultSet.close(); } catch (Exception e) { /* ignored */ }
        try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
    }

}
