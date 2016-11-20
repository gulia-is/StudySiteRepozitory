package Servlets;

import DataBase.MysqlDataBase;
import UsersInfo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet("/signup")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/signup.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (checkingForm(req) && checkingDB(req)){
            try{
                createUser(req);}
            catch (SQLException e){
                resp.sendRedirect("/true");
            }
            resp.sendRedirect("/home");
        } else{
            resp.sendRedirect("/signup");
        }
    }

    private static void createUser(HttpServletRequest request) throws SQLException {
        MysqlDataBase mysqlDataBase = new MysqlDataBase();
        mysqlDataBase.createUser(request.getParameter("nick_su"), request.getParameter("email_su"), request.getParameter("pass_su1"));
        mysqlDataBase.closeALL();
    }

    private static boolean checkingDB(HttpServletRequest request){
        MysqlDataBase mysqlDataBase = new MysqlDataBase();
        boolean result = true;
        try {
            if(mysqlDataBase.hasInfo(request.getParameter("nick_su") , request.getParameter("email_su"))){
                result = false;
            }
        } catch (SQLException e){
            //result = false;
        } finally {
            mysqlDataBase.closeALL();
        }

        return result;
    }

    private static boolean checkingForm(HttpServletRequest request){
        if(!(request.getParameter("nick_su").length()!=0)){return false;}
        if(!(request.getParameter("pass_su1").length()!=0)){return false;}
        if(!request.getParameter("pass_su1").equals(request.getParameter("pass_su2"))){return false;}
        if(!checkEmail(request.getParameter("email_su"))){return false;}

        return true;
    }

    public static boolean checkEmail(String email){
        Pattern p = Pattern.compile("^([A-z0-9])+@([A-z0-9]+\\.)+[A-z0-9]+$");
        Matcher m = p.matcher(email);

        return m.matches();
    }


}
