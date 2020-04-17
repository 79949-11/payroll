package au.cgs.payroll.servlet;

import au.cgs.payroll.dao.EmployeeDAO;
import au.cgs.payroll.entity.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * This servlet class accesses the employee database table and returns
 * the information in each row as a list
 */
public class ShowAllEmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)
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

            List<Employee> employee = new EmployeeDAO().selectAll();
            request.setAttribute("employeeList", employee);
            request.getRequestDispatcher("/employee.jsp").forward(request, response);

        }
    }

    public void destroy() {

    }

}

