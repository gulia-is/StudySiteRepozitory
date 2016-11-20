package Servlets;

import DataBase.MysqlDataBase;
import UsersInfo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        User user;
        try  {
            user = (User) session.getAttribute("corUser");
            if(user.isCheckIn()) {
                req.setAttribute("user", user);
                req.getRequestDispatcher("jsp/profile.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("/enter");
            }
        }
        catch (NullPointerException e){
            resp.sendRedirect("/enter");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(checkingForm(req)){
            try {
                HttpSession session = req.getSession(true);
                MysqlDataBase mysqlDataBase = new MysqlDataBase();
                mysqlDataBase.createPost(((User) session.getAttribute("corUser")).getNick(), (String) req.getParameter("text_post"),
                        (String) req.getParameter("ref_post"));
                resp.sendRedirect("/home");
            } catch (Exception e){
                resp.sendRedirect("/true");
            }
        } else {resp.sendRedirect("/profile");}
    }

    private static boolean checkingForm(HttpServletRequest request){
        if(!(request.getParameter("text_post").length()!=0)){
            return false;
        }
        if(!(request.getParameter("ref_post").length()!=0)){
            return false;
        }
        return true;
    }
}
