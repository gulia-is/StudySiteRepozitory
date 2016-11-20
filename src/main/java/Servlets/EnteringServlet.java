package Servlets;

import DataBase.MysqlDataBase;
import UsersInfo.User;
import com.mysql.jdbc.EscapeTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static Servlets.RegistrationServlet.checkEmail;

@WebServlet("/enter")
public class EnteringServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        User user;
        try {
            user = (User) session.getAttribute("corUser");
            if (user.isCheckIn()) {
                resp.sendRedirect("/home");
            } else {
                resp.sendRedirect("/enter");
            }
        } catch (Exception e){req.getRequestDispatcher("jsp/loginIn.jsp").forward(req, resp);}
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!checkingForm(req)){
            resp.sendRedirect("/enter");}

        MysqlDataBase mysqlDataBase = new MysqlDataBase();
        String userEmail = (String) req.getParameter("userEmail");
        String userPass = (String) req.getParameter("userPassword");

        HttpSession session = req.getSession(true);
        User user = new User();
            try {
                user = mysqlDataBase.readUser(userEmail, userPass);
                session.setAttribute("corUser", user);
            } catch (SQLException e) {
                req.setAttribute("str", "sorry");
                req.getRequestDispatcher("jsp/true.jsp").forward(req, resp);
                e.printStackTrace();
            }
        resp.sendRedirect("/home");
    }

    private static boolean checkingForm(HttpServletRequest request){
        if(!(request.getParameter("userPassword").length()!=0)){return false;}
        if(!checkEmail(request.getParameter("userEmail"))){return false;}
        return true;
    }
}
