package au.cgs.payroll.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This servlet class removes the access of both employee and admin when they log out. It prevents users from accessing other accounts
 * without first logging on when sharing computers.
 */
public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.removeAttribute("username");
        session.removeAttribute("admin");
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }

    public void destroy() {

    }

}