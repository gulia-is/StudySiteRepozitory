package Servlets;

import DataBase.MysqlDataBase;
import UsersInfo.PostsArray;
import UsersInfo.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        User user;
        try  {
            user = (User) session.getAttribute("corUser");
            PostsArray arrayP = null;
            if(user.isCheckIn()) {
                MysqlDataBase mysqlDataBase = new MysqlDataBase();
                arrayP = mysqlDataBase.readPost(user.getNick());
                mysqlDataBase.closeALL();
                req.setAttribute("user", user);
                req.setAttribute("mess", arrayP);
                req.getRequestDispatcher("jsp/home.jsp").forward(req, resp);
            } else{resp.sendRedirect("/enter");}
        }
        catch (NullPointerException e){resp.sendRedirect("/enter");} catch (SQLException e) {}
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/home");
    }

}
