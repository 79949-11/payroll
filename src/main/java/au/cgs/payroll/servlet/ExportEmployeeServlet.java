package au.cgs.payroll.servlet;

import au.cgs.payroll.TextFileExport;
import au.cgs.payroll.dao.EmployeeDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This servlet class accesses the employee database table and exports the table information from the mySQL management
 * system into an excel file
 */
public class ExportEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute("username");
        if (username == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        if (session.getAttribute("admin") != "admin") {
            request.getRequestDispatcher("/permission.html").forward(request, response);
        } else {

            new TextFileExport().exportEmployee(new EmployeeDAO().selectAll());
            request.getRequestDispatcher("/show_employee").forward(request, response);

        }
    }

    public void destroy() {

    }
}